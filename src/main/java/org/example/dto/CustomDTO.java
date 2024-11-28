package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.Program;
import org.example.entity.Student;
import org.example.entity.StudentProgramDetails;
import org.example.entity.User;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomDTO {
    private int paymentId;
    private double total;
    private double payment;
    private String paymentDate;
    private StudentProgramDetails studentProgramDetails;

    //program
    private String programId;
    private String programName;
    private String duration;
    private double fee;
    private List<StudentProgramDetails> studentProgramDetailsList;

    //student
    private String sPhoneNumber;
    private String name;
    private String dateOfBirth;
    private String address;
    private String registrationDate;
    private User user;

    //studentProgramDetail
    private int detailsId;
    private double registrationFee;
    private Student student;
    private Program program;

    //user
    private String uPhoneNumber;
    private String userName;
    private String password;
    private String uAddress;
    private String userState;
    private List<Student> studentsList;
}
