package com.springapi.donatecharity.repository;


import com.springapi.donatecharity.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {


    @Query("select  distinct role from roles role where role.name =:roleName")
    Optional<Role> findByName(@Param("roleName") String roleName);

}
