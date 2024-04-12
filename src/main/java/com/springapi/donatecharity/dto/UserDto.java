package com.springapi.donatecharity.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class UserDto implements Serializable {
    private String name;
    private String phonenumber;
    private BigDecimal totalDonateMoney;
}
