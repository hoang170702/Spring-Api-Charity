package com.springapi.donatecharity.service.project;


import com.springapi.donatecharity.dto.CharityProjectDto;
import com.springapi.donatecharity.models.CharityProject;
import com.springapi.donatecharity.repository.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService{
    @Autowired
    private ProjectRepository projectRepository;
    @Override
    public void addProject(CharityProjectDto charityProjectDto) {
        try {
            CharityProject charityProject = new CharityProject();
            charityProject.setProjectName(charityProjectDto.getProjectName());
            charityProject.setExpectTotalMoney(charityProjectDto.getExpectTotalMoney());
            this.projectRepository.save(charityProject);
            log.info("Successfully added new charity project: {}", charityProject.getProjectName());
        }catch (Exception e){
            log.error("Failed to add charity project: "+e);
        }
    }

    @Override
    public CharityProject get(int projectId) {
        try {
            Optional<CharityProject> project = this.projectRepository.findById(projectId);
            project.ifPresent(charityProject -> log.info("Successfully to get charity project: {}", charityProject.getProjectName()));
            return project.orElse(null);
        }catch (Exception e){
            log.error("Failed to get charity project"+ e.getMessage());
        }
        return null;
    }

    @Override
    public List<CharityProject> getAll() {
        try {
            List<CharityProject> projects = this.projectRepository.findAll();
            if(projects != null){
                log.info("Successfully to get all charity projects");
            }
            return projects;
        }catch (Exception e){
            log.error("Failed to get all charity project"+ e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(int projectId) {
        try {
            Optional<CharityProject> project = this.projectRepository.findById(projectId);
            project.ifPresent(charityProject -> log.info("Successfully to delete charity project: {}", charityProject.getProjectName()));
            this.projectRepository.deleteById(projectId);
        }catch (Exception e){
            log.error("Failed to delete charity project"+ e.getMessage());
        }
    }

    @Override
    public CharityProject update(CharityProject charityProject) {
        try {
                this.projectRepository.updateCharityProjectById(
                       charityProject.getId(),
                       charityProject.getProjectName(),
                       charityProject.getExpectTotalMoney(),
                       charityProject.getCurrentMoney()
               );
            return charityProject;
        }catch (Exception e){
            log.error("Can't update project name: "+e.getMessage());
        }
        return null;
    }
}
