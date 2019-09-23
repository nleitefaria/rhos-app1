/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rhosapp1.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nleitefaria
 */
@Entity
@Table(name = "purchase_order_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PurchaseOrderStatus.findAll", query = "SELECT p FROM PurchaseOrderStatus p")
    , @NamedQuery(name = "PurchaseOrderStatus.findById", query = "SELECT p FROM PurchaseOrderStatus p WHERE p.id = :id")
    , @NamedQuery(name = "PurchaseOrderStatus.findByStatus", query = "SELECT p FROM PurchaseOrderStatus p WHERE p.status = :status")})
public class PurchaseOrderStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "status")
    private String status;
    @OneToMany(mappedBy = "statusId")
    private List<PurchaseOrders> purchaseOrdersList;

    public PurchaseOrderStatus() {
    }

    public PurchaseOrderStatus(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public List<PurchaseOrders> getPurchaseOrdersList() {
        return purchaseOrdersList;
    }

    public void setPurchaseOrdersList(List<PurchaseOrders> purchaseOrdersList) {
        this.purchaseOrdersList = purchaseOrdersList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchaseOrderStatus)) {
            return false;
        }
        PurchaseOrderStatus other = (PurchaseOrderStatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.rhosapp1.PurchaseOrderStatus[ id=" + id + " ]";
    }
    
}
