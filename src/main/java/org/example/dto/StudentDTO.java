package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.User;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDTO {
    private String sPhoneNumber;
    private String name;
    private String dateOfBirth;
    private String address;
    private String registrationDate;
    private String uPhoneNumber;
}
