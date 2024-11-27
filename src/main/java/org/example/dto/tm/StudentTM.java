package org.example.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentTM {
    private String sPhoneNumber;
    private String name;
    private String dateOfBirth;
    private String address;
    private String registrationDate;

}
