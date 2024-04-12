package com.springapi.donatecharity.service.donate;

import com.springapi.donatecharity.dto.DonateDto;
import com.springapi.donatecharity.models.Users_Projects;

public interface DonateService {
    void donate(DonateDto donateDto, int projectId);
}
