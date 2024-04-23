package com.office.portal.leaves.infra.repositroy;

import com.office.portal.leaves.domain.entity.ApplyLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface IApplyLeave extends JpaRepository<ApplyLeave,Long> {
    ArrayList<ApplyLeave> findByEmployeeReference(String employeeReference);
}
