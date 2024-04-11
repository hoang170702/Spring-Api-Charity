package com.springapi.donatecharity.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CharityProjectDto implements Serializable {
    public String projectName;
    public BigDecimal expectTotalMoney;
}
