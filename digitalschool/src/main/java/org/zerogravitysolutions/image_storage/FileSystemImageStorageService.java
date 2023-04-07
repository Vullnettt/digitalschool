package org.zerogravitysolutions.image_storage;

import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileSystemImageStorageService implements ImageStorageService {

    @Value("${app.images.storage-path}")
    private String storagePath;


    private final Environment environment;

    private final Logger log = LoggerFactory.getLogger(FileSystemImageStorageService.class);

    @Autowired
    public FileSystemImageStorageService(Environment environment) {
        this.environment = environment;
    }

    @Override
    public String saveImage(MultipartFile file, String fileNameFromEntity) {

        try {
            BufferedImage originalImage = ImageIO.read(file.getInputStream());

            String fileName;
            if(fileNameFromEntity != null && !fileNameFromEntity.isEmpty()) {
                fileName = fileNameFromEntity;
            } else {
                fileName = generateUniqueFileName(file.getOriginalFilename());
            }

            for(ImageSize imageSize : ImageSize.values()) {

                String imagePath = storagePath + imageSize.name().toLowerCase() + "/" + fileName;
                BufferedImage resizedImage = resizeImage(originalImage, imageSize);
                saveImageToStorage(resizedImage, imagePath);
                log.info("Image saved successfully: {}", imagePath);
            }

            return fileName;
        } catch (IOException e) {
            log.error("Error saving image: " + e);
            throw new RuntimeException("Could not save image: " + e);
        }
    }

    @Override
    public Resource loadImage(String fileName, ImageSize imageSize) {

        String imagePath = storagePath + imageSize.name().toLowerCase() + "/" + fileName;
        Path path = Paths.get(imagePath);

        try {
            Resource resource = new UrlResource(path.toUri());

            if(resource.exists() && resource.isReadable()) {
                return  resource;
            }
            else {
                log.error("Could not read image file: {}", imagePath);
                throw new RuntimeException("Could not read image file " + imageSize);
            }
        } catch (MalformedURLException e) {

            log.error("Error loading image: ", e);
            throw new RuntimeException("Could not load image", e);
        }
    }

    private BufferedImage resizeImage(BufferedImage originalImage, ImageSize imageSize) {

        try {

            int targetWidth = environment.getProperty("app.images." + imageSize.name().toLowerCase() + "-width", Integer.class, 0);
            int targetHeight = environment.getProperty("app.images." + imageSize.name().toLowerCase() + "-height", Integer.class, 0);

            if(targetWidth == 0 || targetHeight == 0) {
                return originalImage;
            }

            return Thumbnails.of(originalImage)
                    .size(targetWidth, targetHeight)
                    .keepAspectRatio(true)
                    .asBufferedImage();

        } catch (IOException e) {

            log.error("Error resizing image: " + e);
            throw new RuntimeException("Couldn't resize image " + e);
        }
    }

    private String saveImageToStorage(BufferedImage image, String imagePath) {

        try {
            Path path = Paths.get(imagePath);
            Files.createDirectories(path.getParent());

            ImageIO.write(image, "png", path.toFile());
            return imagePath;

        } catch (IOException e) {
            log.error("Error saving image to storage: " + e);
            throw new RuntimeException("Could not save image to storage" + e);
        }
    }

    private String generateUniqueFileName(String originalFileName) {

        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        UUID uuid = UUID.randomUUID();

        return uuid.toString() + fileExtension;
    }
}
