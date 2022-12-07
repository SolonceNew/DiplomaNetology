package com.example.cloudservice.model;

import lombok.*;
import lombok.experimental.FieldDefaults;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")
})
@Entity
public class User extends BaseEntity implements Serializable {

    @NotBlank(message = "Username shouldn't be empty")
    @Size(min=2, max=20, message = "Username should be between 2 and 20 characters")
    @Column(unique = true)
    String username;

    @NotBlank
    @Size(min=3, max=20, message = "Password should be between 3 and 20 characters")
    @Column(nullable = false)
    String password;

    @ManyToOne
    @JoinColumn(name = "roles_id", referencedColumnName = "role_id")
    Role role;





}
