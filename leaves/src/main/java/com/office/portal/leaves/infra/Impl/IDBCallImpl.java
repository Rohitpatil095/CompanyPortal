package com.office.portal.leaves.infra.Impl;

import com.office.portal.leaves.domain.dto.DTO;
import com.office.portal.leaves.domain.entity.ApplyLeave;
import com.office.portal.leaves.domain.entity.Leaves;
import com.office.portal.leaves.infra.repositroy.IApplyLeave;
import com.office.portal.leaves.infra.repositroy.ILeavesRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IDBCallImpl implements IDBCall{
    private ILeavesRespository iLeavesRespository;
    private IApplyLeave iApplyLeave;

    @Autowired
    public IDBCallImpl(ILeavesRespository iLeavesRespository, IApplyLeave iApplyLeave) {
        this.iLeavesRespository = iLeavesRespository;
        this.iApplyLeave = iApplyLeave;
    }

    @Override
    public List<Leaves> getLeavesByEmpRef(String empRef) {
        ArrayList<Leaves> empRecod = (ArrayList<Leaves>) iLeavesRespository.findByEmployeeRef(empRef);

        return empRecod;
    }

    @Override
    public void saveApplyLeave(DTO dto) {
        iApplyLeave.save(dto.getApplyLeave());

    }

    @Override
    public ArrayList<ApplyLeave> getLeavesByEmpRefForLeaveId(DTO dto) {
        ArrayList<ApplyLeave> leaveRecord = iApplyLeave.findByEmployeeReference(dto.getApplyLeaveRequest().getEmployeeRef());
        return leaveRecord;
    }

    @Override
    public void updateLeaveTable(DTO dto, ArrayList<Leaves> balanceLeaves) {

        String leaveType = dto.getApplyLeaveRequest().getLeaveType();
        if(leaveType.equalsIgnoreCase("sickleave")){
            float calculatedLeaves=0;
            System.out.println("balanceLeaves.get(0).getSickLeave()"+balanceLeaves.get(0).getSickLeave());
            float b = balanceLeaves.get(0).getSickLeave();
            float c = dto.getApplyLeaveRequest().getNumberOfLeaves();
            System.out.println("dto.getApplyLeaveRequest().getNumberOfLeaves()"+dto.getApplyLeaveRequest().getNumberOfLeaves());
            calculatedLeaves = (balanceLeaves.get(0).getSickLeave())-(dto.getApplyLeaveRequest().getNumberOfLeaves());
            iLeavesRespository.updateSickLeaves(calculatedLeaves);

        }
        if(leaveType.equalsIgnoreCase("earnedleave")) {
            float calculatedLeaves=0;
            calculatedLeaves = balanceLeaves.get(0).getEarnedLeave()-dto.getApplyLeaveRequest().getNumberOfLeaves();
            iLeavesRespository.updateEarnedLeaves(dto.getApplyLeaveRequest().getNumberOfLeaves());
        }

    }


}
