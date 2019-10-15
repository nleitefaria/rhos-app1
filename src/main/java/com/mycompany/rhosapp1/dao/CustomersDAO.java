/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rhosapp1.dao;

import com.mycompany.rhosapp1.entity.Customers;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.rhosapp1.entity.Orders;
import com.mycompany.rhosapp1.EMF;
import com.mycompany.rhosapp1.dao.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author nleitefaria
 */
public class CustomersDAO implements Serializable {

    public CustomersDAO() {
    }
    
    public EntityManager getEntityManager() {
    	return EMF.get().createEntityManager();
    }

    public void create(Customers customers) {
        if (customers.getOrdersList() == null) {
            customers.setOrdersList(new ArrayList<Orders>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Orders> attachedOrdersList = new ArrayList<Orders>();
            for (Orders ordersListOrdersToAttach : customers.getOrdersList()) {
                ordersListOrdersToAttach = em.getReference(ordersListOrdersToAttach.getClass(), ordersListOrdersToAttach.getId());
                attachedOrdersList.add(ordersListOrdersToAttach);
            }
            customers.setOrdersList(attachedOrdersList);
            em.persist(customers);
            for (Orders ordersListOrders : customers.getOrdersList()) {
                Customers oldCustomerIdOfOrdersListOrders = ordersListOrders.getCustomerId();
                ordersListOrders.setCustomerId(customers);
                ordersListOrders = em.merge(ordersListOrders);
                if (oldCustomerIdOfOrdersListOrders != null) {
                    oldCustomerIdOfOrdersListOrders.getOrdersList().remove(ordersListOrders);
                    oldCustomerIdOfOrdersListOrders = em.merge(oldCustomerIdOfOrdersListOrders);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Customers customers) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Customers persistentCustomers = em.find(Customers.class, customers.getId());
            List<Orders> ordersListOld = persistentCustomers.getOrdersList();
            List<Orders> ordersListNew = customers.getOrdersList();
            List<Orders> attachedOrdersListNew = new ArrayList<Orders>();
            for (Orders ordersListNewOrdersToAttach : ordersListNew) {
                ordersListNewOrdersToAttach = em.getReference(ordersListNewOrdersToAttach.getClass(), ordersListNewOrdersToAttach.getId());
                attachedOrdersListNew.add(ordersListNewOrdersToAttach);
            }
            ordersListNew = attachedOrdersListNew;
            customers.setOrdersList(ordersListNew);
            customers = em.merge(customers);
            for (Orders ordersListOldOrders : ordersListOld) {
                if (!ordersListNew.contains(ordersListOldOrders)) {
                    ordersListOldOrders.setCustomerId(null);
                    ordersListOldOrders = em.merge(ordersListOldOrders);
                }
            }
            for (Orders ordersListNewOrders : ordersListNew) {
                if (!ordersListOld.contains(ordersListNewOrders)) {
                    Customers oldCustomerIdOfOrdersListNewOrders = ordersListNewOrders.getCustomerId();
                    ordersListNewOrders.setCustomerId(customers);
                    ordersListNewOrders = em.merge(ordersListNewOrders);
                    if (oldCustomerIdOfOrdersListNewOrders != null && !oldCustomerIdOfOrdersListNewOrders.equals(customers)) {
                        oldCustomerIdOfOrdersListNewOrders.getOrdersList().remove(ordersListNewOrders);
                        oldCustomerIdOfOrdersListNewOrders = em.merge(oldCustomerIdOfOrdersListNewOrders);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = customers.getId();
                if (findCustomers(id) == null) {
                    throw new NonexistentEntityException("The customers with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Customers customers;
            try {
                customers = em.getReference(Customers.class, id);
                customers.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The customers with id " + id + " no longer exists.", enfe);
            }
            List<Orders> ordersList = customers.getOrdersList();
            for (Orders ordersListOrders : ordersList) {
                ordersListOrders.setCustomerId(null);
                ordersListOrders = em.merge(ordersListOrders);
            }
            em.remove(customers);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Customers> findCustomersEntities() {
        return findCustomersEntities(true, -1, -1);
    }

    public List<Customers> findCustomersEntities(int maxResults, int firstResult) {
        return findCustomersEntities(false, maxResults, firstResult);
    }

    private List<Customers> findCustomersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Customers.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Customers findCustomers(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Customers.class, id);
        } finally {
            em.close();
        }
    }

    public int getCustomersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Customers> rt = cq.from(Customers.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
