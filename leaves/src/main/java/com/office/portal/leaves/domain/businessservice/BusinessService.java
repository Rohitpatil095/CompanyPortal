package com.office.portal.leaves.domain.businessservice;

import com.office.portal.leaves.domain.dto.DTO;
import com.office.portal.leaves.domain.entity.Leaves;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BusinessService {

    public boolean checkIfLeavesAvailable(ArrayList<Leaves> balanceLeaves, DTO dto) {
        String leaveType = dto.getApplyLeaveRequest().getLeaveType();
        if(leaveType.equalsIgnoreCase("sickleave")){
            if(balanceLeaves.get(0).getSickLeave()>=dto.getApplyLeaveRequest().getNumberOfLeaves()){
                return true;
            }


        }
        if(leaveType.equalsIgnoreCase("earnedleave")){
            if(balanceLeaves.get(0).getEarnedLeave()>=dto.getApplyLeaveRequest().getNumberOfLeaves()){
                return true;
            }


        }
        return false;
    }
}
