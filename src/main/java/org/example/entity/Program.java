package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Program {
    @Id
    private String programId;
    private String programName;
    private String duration;
    private double fee;

    @OneToMany(mappedBy = "program",cascade = CascadeType.ALL)
    private List<StudentProgramDetails> studentProgramDetails;
}
