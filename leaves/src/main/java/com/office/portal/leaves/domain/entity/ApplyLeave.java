package com.office.portal.leaves.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class ApplyLeave {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String employeeReference;
    String leaveType;
    String status;
    float numberOfLeaves;
    LocalDate fromDate;
    LocalDate toDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmployeeReference() {
        return employeeReference;
    }

    public void setEmployeeReference(String employeeReference) {
        this.employeeReference = employeeReference;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getNumberOfLeaves() {
        return numberOfLeaves;
    }

    public void setNumberOfLeaves(float numberOfLeaves) {
        this.numberOfLeaves = numberOfLeaves;
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
