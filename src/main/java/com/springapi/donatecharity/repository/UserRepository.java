package com.springapi.donatecharity.repository;

import com.springapi.donatecharity.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select distinct user from users user where user.username = ?1")
    Optional<User> findByUsername(@Param("username") String username);

    @Query("select distinct user from users user join Users_Projects user_project on user.id = user_project.user.id where user_project.charityProject.id =:projectId")
    List<User> findUserByProjects(@Param("projectId") int projectId);

}
