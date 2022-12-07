package com.example.cloudservice.repository;

import com.example.cloudservice.model.CloudFile;
import com.example.cloudservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CloudFilesRepository extends JpaRepository<CloudFile, Long> {

    //поиск всех фалов по имени владельца
    @Modifying
    @Query("select files from CloudFile files where files.owner.username = :username")
    List<CloudFile> findAllByUsername(String username);

    //поиск файлов по имени файла
    Optional<CloudFile> findCloudFileByName(String fileName);


    //удаление файла имени файла

    void deleteByName(String fileName);





    //существует ли файл с таким именем
    boolean existsCloudFileByName(String name);




}
