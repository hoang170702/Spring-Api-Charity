package com.springapi.donatecharity.service.donate;

import com.springapi.donatecharity.Enum.DonateType;
import com.springapi.donatecharity.dto.DonateDto;
import com.springapi.donatecharity.models.Role;
import com.springapi.donatecharity.models.User;
import com.springapi.donatecharity.models.Users_Projects;
import com.springapi.donatecharity.repository.DonateRepository;
import com.springapi.donatecharity.repository.ProjectRepository;
import com.springapi.donatecharity.repository.RoleRepository;
import com.springapi.donatecharity.service.user.UserService;
import com.springapi.donatecharity.utils.Helper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@Slf4j
public class DonateAnonymousServiceImpl implements DonateService{
    @Autowired
    private DonateRepository donateRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public void donate(DonateDto donateDto, int projectId) {
        try {
            User user = new User();
            user.setUsername(Helper.generateRandomString(15));
            user.setName("Private");
            user.setTotalDonateMoney(donateDto.getDonateMoney());

            Role role = roleRepository.findByName("ROLE_USER_PRIVATE").orElse(null);
            user.getRoles().add(role);
            userService.addUser(user);

            Users_Projects usersProjects = new Users_Projects();
            usersProjects.setUser(user);
            usersProjects.setCharityProject(projectRepository.findById(projectId).get());
            usersProjects.setFromDate(LocalDateTime.now());
            usersProjects.setDonateMoney(donateDto.getDonateMoney());
            usersProjects.setPhonenumber(donateDto.getPhoneNumber());
            usersProjects.setType("Private");
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
            BigDecimal totalDonateMoney = user.getTotalDonateMoney();
            if (totalDonateMoney == null) {
                totalDonateMoney = BigDecimal.ZERO;
            }
            totalDonateMoney = totalDonateMoney.add(donateMoney);
            user.setTotalDonateMoney(totalDonateMoney);
            userService.addUser(user);


        }catch (Exception e){
            log.error("Can't donate, error: "+e.getMessage());
        }
    }
}
