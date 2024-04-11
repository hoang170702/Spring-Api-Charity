package com.springapi.donatecharity.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity(name = "CharityProjects")
public class CharityProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CharityProjectId")
    private int id;
    @Column(name = "ProjectName")
    @NotBlank(message = "Project name is required")
    private String ProjectName;
    @NotNull(message = "Expect Total Money is required")
    @Column(name = "Money")
    private BigDecimal ExpectTotalMoney;
    @Column(name = "CurrentMoney")
    private BigDecimal CurrentMoney = BigDecimal.valueOf(0);

    @JsonIgnore
    @OneToMany(mappedBy = "charityProject")
    private Set<Users_Projects> usersProjects = new HashSet<>();


}
