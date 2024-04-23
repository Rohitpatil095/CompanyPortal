package com.office.portal.leaves.infra;

import com.office.portal.leaves.domain.dto.DTO;
import com.office.portal.leaves.domain.entity.ApplyLeave;
import com.office.portal.leaves.infra.response.ApplyLeaveResponse;

import java.util.ArrayList;

public class Transformer {
    public DTO transformDtoToApplyLeave(DTO dto) {
        ApplyLeave applyLeave = new ApplyLeave();
        dto.setApplyLeave(applyLeave);
        dto.getApplyLeave().setNumberOfLeaves( dto.getApplyLeaveRequest().getNumberOfLeaves());
        dto.getApplyLeave().setLeaveType(dto.getApplyLeaveRequest().getLeaveType());
        dto.getApplyLeave().setStatus("Pending");
        dto.getApplyLeave().setFromDate(dto.getApplyLeaveRequest().getFromDate());
        dto.getApplyLeave().setToDate(dto.getApplyLeaveRequest().getToDate());
        dto.getApplyLeave().setEmployeeReference(dto.getApplyLeaveRequest().getEmployeeRef());
        return dto;
    }

    public DTO transformDtoToApplyLeaveResponse(DTO dto, ArrayList<ApplyLeave> leaveRecord) {
        ApplyLeaveResponse applyLeaveResponse = new ApplyLeaveResponse();
        String LevId = leaveRecord.get(0).getId() + leaveRecord.get(0).getEmployeeReference();
        applyLeaveResponse.setStatus("Pending");
        applyLeaveResponse.setAcknowledgement("Success");
        applyLeaveResponse.setLeaveId(LevId);
        dto.setApplyLeaveResponse(applyLeaveResponse);
        return dto;
    }
}
