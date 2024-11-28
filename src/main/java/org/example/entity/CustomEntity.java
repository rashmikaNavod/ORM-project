package org.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomEntity {
    //payment
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
