/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rhosapp1.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "inventory_transaction_types")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InventoryTransactionTypes.findAll", query = "SELECT i FROM InventoryTransactionTypes i")
    , @NamedQuery(name = "InventoryTransactionTypes.findById", query = "SELECT i FROM InventoryTransactionTypes i WHERE i.id = :id")
    , @NamedQuery(name = "InventoryTransactionTypes.findByTypeName", query = "SELECT i FROM InventoryTransactionTypes i WHERE i.typeName = :typeName")})
public class InventoryTransactionTypes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @Column(name = "type_name")
    private String typeName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transactionType")
    private List<InventoryTransactions> inventoryTransactionsList;

    public InventoryTransactionTypes() {
    }

    public InventoryTransactionTypes(Short id) {
        this.id = id;
    }

    public InventoryTransactionTypes(Short id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @XmlTransient
    public List<InventoryTransactions> getInventoryTransactionsList() {
        return inventoryTransactionsList;
    }

    public void setInventoryTransactionsList(List<InventoryTransactions> inventoryTransactionsList) {
        this.inventoryTransactionsList = inventoryTransactionsList;
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
        if (!(object instanceof InventoryTransactionTypes)) {
            return false;
        }
        InventoryTransactionTypes other = (InventoryTransactionTypes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.rhosapp1.InventoryTransactionTypes[ id=" + id + " ]";
    }
    
}
