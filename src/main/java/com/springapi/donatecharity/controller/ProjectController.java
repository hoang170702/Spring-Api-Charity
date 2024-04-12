package com.springapi.donatecharity.controller;


import com.springapi.donatecharity.dto.CharityProjectDto;
import com.springapi.donatecharity.models.CharityProject;
import com.springapi.donatecharity.service.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRange;
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
        return ResponseEntity.status(HttpStatus.OK).body(this.projectService.getAll());
    }

    @PostMapping("/add-project")
    public ResponseEntity<String> getAllProject(@RequestBody CharityProjectDto charityProjectDto){
        this.projectService.addProject(charityProjectDto);
        return ResponseEntity.status(HttpStatus.OK).body("Add new project successfully");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CharityProject> getCharityProject(@PathVariable("id") int projectId){
        return ResponseEntity.status(HttpStatus.OK).body(this.projectService.get(projectId));
    }

    @PutMapping("/update")
    public ResponseEntity<CharityProject> updateProject(@RequestBody CharityProject charityProject){
        return ResponseEntity.status(HttpStatus.OK).body(this.projectService.update(charityProject));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable("id") int projectId){
        this.projectService.delete(projectId);
        return ResponseEntity.status(HttpStatus.OK).body("Delete project successfully - projectId: "+ projectId);
    }
}
