package com.office.portal.leaves.api.controller;

import com.office.portal.leaves.domain.businessservice.LeaveBusinessService;
import com.office.portal.leaves.domain.dto.DTO;
import com.office.portal.leaves.infra.request.ApplyLeaveRequest;
import com.office.portal.leaves.infra.response.ApplyLeaveResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/leaves")
public class LeaveController {

    private LeaveBusinessService leaveBusinessService;

    @Autowired
    public LeaveController(LeaveBusinessService leaveBusinessService) {
        this.leaveBusinessService = leaveBusinessService;
    }

    @PostMapping("/applyleave")
    public ApplyLeaveResponse applyleave(@RequestBody ApplyLeaveRequest applyLeaveRequest){
        DTO dto = new DTO();
        dto.setApplyLeaveRequest(applyLeaveRequest);
        leaveBusinessService.applyleaveservice(dto);
        return dto.getApplyLeaveResponse();
    }
}
