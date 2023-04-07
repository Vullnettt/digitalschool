package org.zerogravitysolutions.image_storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageStorageService {
    String saveImage(MultipartFile file, String fileNameFromEntity);
    Resource loadImage(String fileName, ImageSize imageSize);
}
