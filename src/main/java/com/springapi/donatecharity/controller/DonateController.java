package com.springapi.donatecharity.controller;


import com.springapi.donatecharity.Enum.DonateType;
import com.springapi.donatecharity.dto.DonateDto;
import com.springapi.donatecharity.models.User;
import com.springapi.donatecharity.models.Users_Projects;
import com.springapi.donatecharity.service.donate.DonateService;
import com.springapi.donatecharity.service.user_projects.UserProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donate")
public class DonateController {


    @Qualifier("donateLoginServiceImpl")
    @Autowired
    private DonateService donateServiceLogin;


    @Qualifier("donateAnonymousServiceImpl")
    @Autowired
    private DonateService donateServiceAnonymous;

    @Autowired
    private UserProjectService userProjectService;

    @PostMapping("/donate")
    public ResponseEntity<String> donate(@RequestParam("type") DonateType donateType,
                                              @RequestParam("projectId") int projectId,
                                              @RequestBody DonateDto donateDto){
        if(donateType == DonateType.LOGIN){
            this.donateServiceLogin.donate(donateDto, projectId);
        }else if(donateType == DonateType.ANONYMOUS){
            this.donateServiceAnonymous.donate(donateDto, projectId);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Donate successfully");
    }


    @GetMapping("/user-donate-login")
    public ResponseEntity<List<Users_Projects>> getAllUserLogin(){
        return  ResponseEntity.status(HttpStatus.OK).body(this.userProjectService.usersProjectsLogin());
    }

    @GetMapping("/user-donate-anonymous")
    public ResponseEntity<List<Users_Projects>> getAllUserAnonymous(){
        return  ResponseEntity.status(HttpStatus.OK).body(this.userProjectService.usersProjectsAnonymous());
    }

    @GetMapping("/get-all-user-in-project/{projectId}")
    public ResponseEntity<List<User>> getAllUserByProject(@PathVariable("projectId") int projectId){
        return ResponseEntity.status(HttpStatus.OK).body(this.userProjectService.getListUserDonate(projectId));
    }


}
