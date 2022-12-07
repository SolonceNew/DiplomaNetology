package com.example.cloudservice.service;

import com.example.cloudservice.model.CloudFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface CloudFilesService {

    CloudFile upload (MultipartFile resource, HttpServletRequest request) throws IOException;

    List<CloudFile> showListOfFiles(String username);

    CloudFile download(String filename);



    void deleteFile(String filename);



}
