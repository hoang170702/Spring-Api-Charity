package com.springapi.donatecharity.repository;


import com.springapi.donatecharity.models.CharityProject;
import com.springapi.donatecharity.models.Users_Projects;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
@Transactional
public interface ProjectRepository extends JpaRepository<CharityProject, Integer> {

    @Modifying
    @Query("UPDATE CharityProjects cp SET cp.ProjectName = :projectName, cp.ExpectTotalMoney = :expectTotalMoney, cp.CurrentMoney = :currentMoney WHERE cp.id = :projectId")
    void updateCharityProjectById(@Param("projectId") int projectId,
                                            @Param("projectName") String projectName,
                                            @Param("expectTotalMoney") BigDecimal expectTotalMoney,
                                            @Param("currentMoney") BigDecimal currentMoney);



}
