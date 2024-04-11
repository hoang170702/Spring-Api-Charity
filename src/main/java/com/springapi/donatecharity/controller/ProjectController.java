package com.springapi.donatecharity.controller;


import com.springapi.donatecharity.dto.CharityProjectDto;
import com.springapi.donatecharity.models.CharityProject;
import com.springapi.donatecharity.service.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/get-all")
    public ResponseEntity<List<CharityProject>> getAllProject(){
        List<CharityProject> charityProjects = this.projectService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(charityProjects);
    }

    @PostMapping("/add-project")
    public ResponseEntity<String> getAllProject(@RequestBody CharityProjectDto charityProjectDto){
        this.projectService.addProject(charityProjectDto);
        return ResponseEntity.status(HttpStatus.OK).body("Add new project successfully");
    }
}
