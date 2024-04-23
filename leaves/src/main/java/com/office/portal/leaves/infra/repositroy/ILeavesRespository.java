package com.office.portal.leaves.infra.repositroy;

import com.office.portal.leaves.domain.entity.Leaves;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ILeavesRespository extends JpaRepository<Leaves,Long> {
    public List<Leaves> findByEmployeeRef(String employeeRef);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Leaves set sick_leave =:numberOfLeaves", nativeQuery = true)
    void updateSickLeaves(float numberOfLeaves);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Leaves set earned_leave =:numberOfLeaves", nativeQuery = true)
    void updateEarnedLeaves(float numberOfLeaves);
}
