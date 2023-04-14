package org.zerogravitysolutions.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Service
@FeignClient(value = "email-service", url = "${feign.address.email-service}")
public interface EmailFeignClient {

    @PostMapping(path = "/v1/send", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    void send(@RequestPart(name = "recipients", required = true) String recipients,
                                  @RequestPart(name = "subject") String subject,
                                  @RequestPart(name = "body", required = false) String body,
                                  @RequestPart(name = "attachments", required = false) MultipartFile[] attachments,
                                  @RequestPart(name = "templateFile", required = false) MultipartFile templateFile,
                                  @RequestPart(name = "templateData", required = false) String templateData);
}
