package com.tw.apistackbase.beans;


import javax.persistence.*;

@Entity
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(length = 255,nullable = false)
    private String caseName;

    @Column(nullable = false)
    private long caseTime;

    @OneToOne(cascade= CascadeType.ALL, fetch= FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    private CaseDetail caseDetail;

    @OneToOne(cascade= CascadeType.ALL, fetch= FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    private Procuratorate procuratorate;

    public Case(String caseName, long caseTime, Procuratorate procuratorate) {
        this.caseName = caseName;
        this.caseTime = caseTime;
        this.procuratorate = procuratorate;
    }

    public void setProcuratorate(Procuratorate procuratorate) {
        this.procuratorate = procuratorate;
    }

    public Procuratorate getProcuratorate() {
        return procuratorate;
    }

    public Case(String caseName, long caseTime, CaseDetail caseDetail, Procuratorate procuratorate) {
        this.caseName = caseName;
        this.caseTime = caseTime;
        this.caseDetail = caseDetail;
        this.procuratorate = procuratorate;
    }

    public void setCaseDetail(CaseDetail caseDetail) {
        this.caseDetail = caseDetail;
    }

    public CaseDetail getCaseDetail() {
        return caseDetail;
    }

    public Case(String caseName, long caseTime, CaseDetail caseDetail) {
        this.caseName = caseName;
        this.caseTime = caseTime;
        this.caseDetail = caseDetail;
    }

    public Case()
    {

    }

    public Case(String caseName, long caseTime) {
        this.caseName = caseName;
        this.caseTime = caseTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public void setCaseTime(long caseTime) {
        this.caseTime = caseTime;
    }

    public String getId() {
        return id;
    }

    public String getCaseName() {
        return caseName;
    }

    public long getCaseTime() {
        return caseTime;
    }
}
