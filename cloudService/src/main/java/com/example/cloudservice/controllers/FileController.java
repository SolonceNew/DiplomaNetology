package com.example.cloudservice.controllers;

import com.example.cloudservice.model.CloudFile;
import com.example.cloudservice.service.CloudFilesService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/files")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FileController {

    CloudFilesService cloudFilesService;

    @PostMapping
    public ResponseEntity<CloudFile> upload(@RequestParam MultipartFile file, HttpServletRequest request) {
        try {
            return new ResponseEntity<>(cloudFilesService.upload(file, request), HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> download(@RequestParam ("filename") String filename) {

            CloudFile cloudFile = cloudFilesService.download(filename);
            if(cloudFile != null) {
                return ResponseEntity.ok()
                        .header("Content-Disposition", "attachment; filename=" + cloudFile.getName())
                        .body(cloudFile);
            } else {

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }




}
