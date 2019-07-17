package com.tw.apistackbase.beans;

import javax.persistence.*;
import java.util.List;

public class Procuratorate {

    @Id
    @GeneratedValue
    private String id;

    @Column(length = 50,unique = true)
    private String procuratorateName;

    @OneToMany(cascade= CascadeType.ALL, fetch= FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    private List<Justice> justices;

    public void setJustices(List<Justice> justices) {
        this.justices = justices;
    }

    public List<Justice> getJustices() {
        return justices;
    }

    public Procuratorate(String procuratorateName, List<Justice> justices) {
        this.procuratorateName = procuratorateName;
        this.justices = justices;
    }

    public Procuratorate() {

    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProcuratorateName(String procuratorateName) {
        this.procuratorateName = procuratorateName;
    }

    public String getProcuratorateName() {
        return procuratorateName;
    }

    public Procuratorate(String procuratorateName) {
        this.procuratorateName = procuratorateName;
    }
}
