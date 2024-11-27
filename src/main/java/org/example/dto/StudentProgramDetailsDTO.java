package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.Payment;
import org.example.entity.Program;
import org.example.entity.Student;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentProgramDetailsDTO {
    private int detailsId;
    private double registrationFee;
    private String sPhoneNumber;
    private String programId;
}
