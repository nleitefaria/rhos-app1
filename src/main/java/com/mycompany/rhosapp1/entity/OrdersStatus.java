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
@Table(name = "orders_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdersStatus.findAll", query = "SELECT o FROM OrdersStatus o")
    , @NamedQuery(name = "OrdersStatus.findById", query = "SELECT o FROM OrdersStatus o WHERE o.id = :id")
    , @NamedQuery(name = "OrdersStatus.findByStatusName", query = "SELECT o FROM OrdersStatus o WHERE o.statusName = :statusName")})
public class OrdersStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @Column(name = "status_name")
    private String statusName;
    @OneToMany(mappedBy = "statusId")
    private List<Orders> ordersList;

    public OrdersStatus() {
    }

    public OrdersStatus(Short id) {
        this.id = id;
    }

    public OrdersStatus(Short id, String statusName) {
        this.id = id;
        this.statusName = statusName;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @XmlTransient
    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
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
        if (!(object instanceof OrdersStatus)) {
            return false;
        }
        OrdersStatus other = (OrdersStatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.rhosapp1.OrdersStatus[ id=" + id + " ]";
    }
    
}
