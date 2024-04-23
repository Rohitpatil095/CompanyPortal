package com.office.portal.leaves.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Leaves {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String employeeRef;
    float sickLeave;
    float earnedLeave;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmployeeRef() {
        return employeeRef;
    }

    public void setEmployeeRef(String employeeRef) {
        this.employeeRef = employeeRef;
    }

    public float getSickLeave() {
        return sickLeave;
    }

    public void setSickLeave(float sickLeave) {
        this.sickLeave = sickLeave;
    }

    public float getEarnedLeave() {
        return earnedLeave;
    }

    public void setEarnedLeave(float earnedLeave) {
        this.earnedLeave = earnedLeave;
    }


}
