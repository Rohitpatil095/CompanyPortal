package com.office.portal.leaves.domain.dto;

import com.office.portal.leaves.domain.entity.ApplyLeave;
import com.office.portal.leaves.domain.entity.Leaves;
import com.office.portal.leaves.infra.request.ApplyLeaveRequest;
import com.office.portal.leaves.infra.response.ApplyLeaveResponse;

public class DTO {
    private ApplyLeaveResponse applyLeaveResponse;
    private ApplyLeaveRequest applyLeaveRequest;
    private ApplyLeave applyLeave;
    private Leaves leaves;

    public ApplyLeave getApplyLeave() {
        return applyLeave;
    }

    public void setApplyLeave(ApplyLeave applyLeave) {
        this.applyLeave = applyLeave;
    }

    public Leaves getLeaves() {
        return leaves;
    }

    public void setLeaves(Leaves leaves) {
        this.leaves = leaves;
    }

    public ApplyLeaveResponse getApplyLeaveResponse() {
        return applyLeaveResponse;
    }

    public void setApplyLeaveResponse(ApplyLeaveResponse applyLeaveResponse) {
        this.applyLeaveResponse = applyLeaveResponse;
    }

    public ApplyLeaveRequest getApplyLeaveRequest() {
        return applyLeaveRequest;
    }

    public void setApplyLeaveRequest(ApplyLeaveRequest applyLeaveRequest) {
        this.applyLeaveRequest = applyLeaveRequest;
    }
}
