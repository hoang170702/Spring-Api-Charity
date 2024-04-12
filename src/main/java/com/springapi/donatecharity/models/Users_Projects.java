package com.springapi.donatecharity.models;



import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "Users_Projects")
public class Users_Projects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "projectId", nullable = false)
    private CharityProject charityProject;

    @Column(name = "From_Date")
    private LocalDateTime fromDate;

    @Column(name = "DonateMoney")
    private BigDecimal donateMoney;


    private String phonenumber;

    private String type;

}

