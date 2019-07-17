package com.tw.apistackbase.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CaseDetail {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String subjectiveDetail;

    @Column(nullable = false)
    private String objectiveDetail;

    public CaseDetail() {
    }

    public CaseDetail(String subjectiveDetail, String objectiveDetail) {
        this.subjectiveDetail = subjectiveDetail;
        this.objectiveDetail = objectiveDetail;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSubjectiveDetail(String subjectiveDetail) {
        this.subjectiveDetail = subjectiveDetail;
    }

    public void setObjectiveDetail(String objectiveDetail) {
        this.objectiveDetail = objectiveDetail;
    }

    public Integer getId() {
        return id;
    }

    public String getSubjectiveDetail() {
        return subjectiveDetail;
    }

    public String getObjectiveDetail() {
        return objectiveDetail;
    }
}
