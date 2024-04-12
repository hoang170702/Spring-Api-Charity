package com.springapi.donatecharity.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DonateDto implements Serializable {
    private BigDecimal donateMoney;
    private String phoneNumber;
}
