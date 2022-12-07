package com.example.cloudservice.dto;

import com.example.cloudservice.model.CloudFile;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Component
public class CloudFileDto {
    String fileName;
    long fileSize;



    public CloudFile toFileFromMultipart(MultipartFile file) {
        try {
            CloudFile cloudFile = new CloudFile();
            cloudFile.setName(file.getOriginalFilename());
            cloudFile.setFileType(file.getContentType());
            cloudFile.setSize(file.getSize());
            cloudFile.setBytes(file.getBytes());
            return cloudFile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public CloudFileDto fromCloudFiletoDto(CloudFile file) {
         CloudFileDto cloudFileDto = new CloudFileDto();
         cloudFileDto.setFileName(file.getName());
         cloudFileDto.setFileSize(file.getSize());
         return cloudFileDto;
    }
}
