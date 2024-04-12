package com.springapi.donatecharity.repository;

import com.springapi.donatecharity.models.Users_Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DonateRepository extends JpaRepository<Users_Projects, Integer> {
    @Query("select distinct users_project from Users_Projects  users_project where  users_project.charityProject.id =:charityProject_Id")
    Optional<Users_Projects> findByCharityProject_Id(@Param("charityProject_Id") int charityProject_Id);

    @Query("select distinct users_project from Users_Projects users_project where users_project.user.id =:userId")
    Optional<Users_Projects> findByUserId(@Param("userId") int userId);

    @Query("select distinct users_project from Users_Projects users_project join CharityProjects project join users user where project.id=:charityProject_Id and user.id=:userId")
    Optional<Users_Projects> findByCharityProject_IdAndUserId(@Param("charityProject_Id") int charityProject_Id, @Param("userId") int userId);

    @Query("SELECT user_project FROM Users_Projects user_project where user_project.type=:type")
    List<Users_Projects> findAllByUserAndType(@Param("type") String type);


}
