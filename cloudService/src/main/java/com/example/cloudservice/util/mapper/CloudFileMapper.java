package com.example.cloudservice.util.mapper;

import com.example.cloudservice.dto.CloudFileDto;
import com.example.cloudservice.model.CloudFile;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CloudFileMapper {

    ModelMapper modelMapper;

    @Autowired
    public CloudFileMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CloudFile toFileFromMultipart(MultipartFile file) {
        try {
            com.example.cloudservice.model.CloudFile cloudFile = new CloudFile();
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
        return this.modelMapper.map(file, CloudFileDto.class);
    }

    public CloudFile fromCloudFiletoDto(CloudFileDto fileDto) {
        return this.modelMapper.map(fileDto, CloudFile.class);
    }
}

