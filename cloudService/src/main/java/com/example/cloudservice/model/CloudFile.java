package com.example.cloudservice.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "files")
@Entity
public class CloudFile extends BaseEntity{


    @Column(name = "file_name")
    @NotBlank
    String name;


    @Column(name = "file_type", nullable = false)
    String fileType;

    @Column(nullable = false)
    long size;


    @Lob
    @Column(nullable = false)
    byte[] bytes;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "name")
    User owner;







}
