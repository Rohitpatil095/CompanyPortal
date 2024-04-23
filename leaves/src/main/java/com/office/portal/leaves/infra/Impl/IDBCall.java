package com.office.portal.leaves.infra.Impl;

import com.office.portal.leaves.domain.dto.DTO;
import com.office.portal.leaves.domain.entity.ApplyLeave;
import com.office.portal.leaves.domain.entity.Leaves;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface IDBCall {
    List<Leaves> getLeavesByEmpRef(String empRef);
    void saveApplyLeave(DTO dto);

    ArrayList<ApplyLeave> getLeavesByEmpRefForLeaveId(DTO dto);

    void updateLeaveTable(DTO dto,ArrayList<Leaves> balanceLeaves);
}
