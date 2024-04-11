package com.springapi.donatecharity.service.project;

import com.springapi.donatecharity.dto.CharityProjectDto;
import com.springapi.donatecharity.models.CharityProject;

import java.util.List;

public interface ProjectService {
    void addProject(CharityProjectDto charityProjectDto);
    CharityProject get(int projectId);
    List<CharityProject> getAll();
    void delete(int projectId);

    CharityProject update(CharityProject charityProject);

}
