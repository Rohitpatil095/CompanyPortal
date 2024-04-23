package com.office.portal.leaves.domain.businessservice;

import com.office.portal.leaves.domain.dto.DTO;
import com.office.portal.leaves.domain.entity.ApplyLeave;
import com.office.portal.leaves.domain.entity.Leaves;
import com.office.portal.leaves.infra.Impl.IDBCall;
import com.office.portal.leaves.infra.Transformer;
import com.office.portal.leaves.infra.request.ApplyLeaveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LeaveBusinessService {
    private IDBCall idbCall;
    private BusinessService businessService;

    @Autowired
    public LeaveBusinessService(IDBCall idbCall, BusinessService businessService) {
        this.idbCall = idbCall;
        this.businessService = businessService;
    }

    public DTO applyleaveservice(DTO dto) {
        ArrayList<Leaves> balanceLeaves = (ArrayList<Leaves>) idbCall.getLeavesByEmpRef(dto.getApplyLeaveRequest().getEmployeeRef());
        boolean isEnoughLeaves = businessService.checkIfLeavesAvailable(balanceLeaves,dto);
        if(!isEnoughLeaves){
            dto.getApplyLeaveResponse().setAcknowledgement("Failed");
            dto.getApplyLeaveResponse().setStatus("Failed Due to not enough leaves");
            return dto;
        }
        idbCall.updateLeaveTable(dto,balanceLeaves);

        Transformer transformer = new Transformer();
        dto = transformer.transformDtoToApplyLeave(dto);
        idbCall.saveApplyLeave(dto);
        ArrayList<ApplyLeave> leaveRecord = idbCall.getLeavesByEmpRefForLeaveId(dto);
        transformer.transformDtoToApplyLeaveResponse(dto,leaveRecord);
        return dto;
    }
}
