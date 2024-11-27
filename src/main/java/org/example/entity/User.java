package org.example.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    private String uPhoneNumber;
    private String userName;
    private String password;
    private String address;
    private String userState;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Student> students;
}
