package com.springapi.donatecharity.service.donate;

import com.springapi.donatecharity.dto.DonateDto;
import com.springapi.donatecharity.models.CharityProject;
import com.springapi.donatecharity.models.User;
import com.springapi.donatecharity.models.Users_Projects;
import com.springapi.donatecharity.repository.DonateRepository;
import com.springapi.donatecharity.repository.ProjectRepository;
import com.springapi.donatecharity.service.user.UserService;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@Slf4j
public class DonateLoginServiceImpl implements DonateService{

    @Autowired
    private DonateRepository donateRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    @Transactional
    public void donate(DonateDto donateDto, int projectId) {
        try {
            User curentUser = userService.getCurrentAccount();
            Users_Projects usersProjects = new Users_Projects();
            usersProjects.setUser(curentUser);
            usersProjects.setCharityProject(projectRepository.findById(projectId).get());
            usersProjects.setFromDate(LocalDateTime.now());
            usersProjects.setDonateMoney(donateDto.getDonateMoney());
            usersProjects.setPhonenumber(donateDto.getPhoneNumber());
            usersProjects.setType("Public");
            donateRepository.save(usersProjects);

            var esxitProject = projectRepository.findById(projectId);
            if (esxitProject.isPresent()){
                BigDecimal currentMoney = esxitProject.get().getCurrentMoney();
                BigDecimal donateMoney = donateDto.getDonateMoney();
                esxitProject.get().setCurrentMoney(currentMoney.add(donateMoney));
                projectRepository.save(esxitProject.get());
            }

            // Thêm số tiền donate vào totalDonateMoney của user
            BigDecimal donateMoney = donateDto.getDonateMoney();
            BigDecimal totalDonateMoney = curentUser.getTotalDonateMoney();
            if (totalDonateMoney == null) {
                totalDonateMoney = BigDecimal.ZERO;
            }
            totalDonateMoney = totalDonateMoney.add(donateMoney);
            curentUser.setTotalDonateMoney(totalDonateMoney);
            userService.addUser(curentUser);

        }catch (Exception e){
            log.error("Can't donate, error: "+e.getMessage());
        }
    }
}
