package com.springapi.donatecharity.service.user_projects;

import com.springapi.donatecharity.models.User;
import com.springapi.donatecharity.models.Users_Projects;
import com.springapi.donatecharity.repository.DonateRepository;
import com.springapi.donatecharity.repository.ProjectRepository;
import com.springapi.donatecharity.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserProjectServiceImpl implements UserProjectService{
    @Autowired
    private DonateRepository donateRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Users_Projects> usersProjectsAnonymous() {
        try {
            return donateRepository.findAllByUserAndType("Private");
        }catch (Exception e){
            log.error("Can't get list user anonymous: "+ e.getMessage());
        }
        return null;
    }

    @Override
    public List<Users_Projects> usersProjectsLogin() {
        try {
            return donateRepository.findAllByUserAndType("Public");
        }catch (Exception e){
            log.error("Can't get list user anonymous: "+ e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> getListUserDonate(int projectId) {
        try {
            return userRepository.findUserByProjects(projectId);
        }catch (Exception e){
            log.error("Can't get list user anonymous: "+ e.getMessage());
        }
        return null;
    }
}
