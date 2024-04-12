package com.springapi.donatecharity.service.user_projects;

import com.springapi.donatecharity.models.User;
import com.springapi.donatecharity.models.Users_Projects;

import java.util.List;

public interface UserProjectService {
    List<Users_Projects> usersProjectsAnonymous();
    List<Users_Projects> usersProjectsLogin();

    List<User> getListUserDonate(int projectId);
}
