package com.example.cloudservice.service.impl;

import com.example.cloudservice.model.CloudFile;
import com.example.cloudservice.model.User;
import com.example.cloudservice.repository.CloudFilesRepository;
import com.example.cloudservice.security.jwt.JwtUtils;
import com.example.cloudservice.service.CloudFilesService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j

 public class CloudFilesServiceImpl implements CloudFilesService {

    CloudFilesRepository cloudFilesRepository;
    UserService userService;
    JwtUtils jwtUtils;



    @Autowired
    public CloudFilesServiceImpl(CloudFilesRepository cloudFilesRepository,  UserService userService,
                                 JwtUtils jwtUtils) {
        this.cloudFilesRepository = cloudFilesRepository;
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    public Optional<CloudFile> findByFilename(String fileName) {
        return cloudFilesRepository.findCloudFileByName(fileName);
    }

    @Transactional(rollbackFor = Exception.class)
    public CloudFile upload(MultipartFile resource, HttpServletRequest request) throws IOException {
        String token = request.getHeader("auth-token");
        String usernameFromToken = jwtUtils.validateTokenRetrieveClaim(token);
        User user = userService.findByUsername(usernameFromToken).orElse(new User());
        CloudFile file = CloudFile.builder()
                .name(resource.getOriginalFilename())
                .fileType(resource.getContentType())
                .size(resource.getSize())
                .bytes(resource.getBytes())

                .owner(user)
                .build();
        cloudFilesRepository.save(file);
        log.info("user {} successfully uploaded a file {}", user.getUsername(), resource);
        return file;
        }


    public CloudFile download(String filename) {
        Optional<CloudFile> file = cloudFilesRepository.findCloudFileByName(filename);
        return file.orElse(null);
    }

    public void deleteFile(String filename) {

        cloudFilesRepository.deleteByName(filename);
    }


    public List<CloudFile> showListOfFiles(String username) {
        return cloudFilesRepository.findAllByUsername(username);
    }


  }
