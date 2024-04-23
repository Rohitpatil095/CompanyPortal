package com.office.portal.leaves.infra.request;

import java.time.LocalDate;

public class ApplyLeaveRequest {
    String employeeRef;
    String leaveType;
    float numberOfLeaves;
    LocalDate fromDate;
    LocalDate toDate;

    public ApplyLeaveRequest() {
    }

    public ApplyLeaveRequest(String employeeRef, String leaveType, float numberOfLeaves, LocalDate fromDate, LocalDate toDate) {
        this.employeeRef = employeeRef;
        this.leaveType = leaveType;
        this.numberOfLeaves = numberOfLeaves;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public float getNumberOfLeaves() {
        return numberOfLeaves;
    }

    public void setNumberOfLeaves(float numberOfLeaves) {
        this.numberOfLeaves = numberOfLeaves;
    }

    public String getEmployeeRef() {
        return employeeRef;
    }

    public void setEmployeeRef(String employeeRef) {
        this.employeeRef = employeeRef;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }



    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }
}
