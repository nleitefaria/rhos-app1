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
@Table(name = "order_details_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderDetailsStatus.findAll", query = "SELECT o FROM OrderDetailsStatus o")
    , @NamedQuery(name = "OrderDetailsStatus.findById", query = "SELECT o FROM OrderDetailsStatus o WHERE o.id = :id")
    , @NamedQuery(name = "OrderDetailsStatus.findByStatusName", query = "SELECT o FROM OrderDetailsStatus o WHERE o.statusName = :statusName")})
public class OrderDetailsStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "status_name")
    private String statusName;
    @OneToMany(mappedBy = "statusId")
    private List<OrderDetails> orderDetailsList;

    public OrderDetailsStatus() {
    }

    public OrderDetailsStatus(Integer id) {
        this.id = id;
    }

    public OrderDetailsStatus(Integer id, String statusName) {
        this.id = id;
        this.statusName = statusName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @XmlTransient
    public List<OrderDetails> getOrderDetailsList() {
        return orderDetailsList;
    }

    public void setOrderDetailsList(List<OrderDetails> orderDetailsList) {
        this.orderDetailsList = orderDetailsList;
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
        if (!(object instanceof OrderDetailsStatus)) {
            return false;
        }
        OrderDetailsStatus other = (OrderDetailsStatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.rhosapp1.OrderDetailsStatus[ id=" + id + " ]";
    }
    
}
