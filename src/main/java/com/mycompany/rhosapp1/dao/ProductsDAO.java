/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rhosapp1.dao;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.rhosapp1.entity.OrderDetails;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.rhosapp1.entity.PurchaseOrderDetails;
import com.mycompany.rhosapp1.entity.InventoryTransactions;
import com.mycompany.rhosapp1.entity.Products;
import com.mycompany.rhosapp1.EMF;
import com.mycompany.rhosapp1.dao.exceptions.IllegalOrphanException;
import com.mycompany.rhosapp1.dao.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author nleitefaria
 */
public class ProductsDAO implements Serializable {

    public ProductsDAO(EntityManagerFactory emf) {
        //this.emf = emf;
    }
    //private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        //return emf.createEntityManager();
        return EMF.get().createEntityManager();
    }

    public void create(Products products) {
        if (products.getOrderDetailsList() == null) {
            products.setOrderDetailsList(new ArrayList<OrderDetails>());
        }
        if (products.getPurchaseOrderDetailsList() == null) {
            products.setPurchaseOrderDetailsList(new ArrayList<PurchaseOrderDetails>());
        }
        if (products.getInventoryTransactionsList() == null) {
            products.setInventoryTransactionsList(new ArrayList<InventoryTransactions>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<OrderDetails> attachedOrderDetailsList = new ArrayList<OrderDetails>();
            for (OrderDetails orderDetailsListOrderDetailsToAttach : products.getOrderDetailsList()) {
                orderDetailsListOrderDetailsToAttach = em.getReference(orderDetailsListOrderDetailsToAttach.getClass(), orderDetailsListOrderDetailsToAttach.getId());
                attachedOrderDetailsList.add(orderDetailsListOrderDetailsToAttach);
            }
            products.setOrderDetailsList(attachedOrderDetailsList);
            List<PurchaseOrderDetails> attachedPurchaseOrderDetailsList = new ArrayList<PurchaseOrderDetails>();
            for (PurchaseOrderDetails purchaseOrderDetailsListPurchaseOrderDetailsToAttach : products.getPurchaseOrderDetailsList()) {
                purchaseOrderDetailsListPurchaseOrderDetailsToAttach = em.getReference(purchaseOrderDetailsListPurchaseOrderDetailsToAttach.getClass(), purchaseOrderDetailsListPurchaseOrderDetailsToAttach.getId());
                attachedPurchaseOrderDetailsList.add(purchaseOrderDetailsListPurchaseOrderDetailsToAttach);
            }
            products.setPurchaseOrderDetailsList(attachedPurchaseOrderDetailsList);
            List<InventoryTransactions> attachedInventoryTransactionsList = new ArrayList<InventoryTransactions>();
            for (InventoryTransactions inventoryTransactionsListInventoryTransactionsToAttach : products.getInventoryTransactionsList()) {
                inventoryTransactionsListInventoryTransactionsToAttach = em.getReference(inventoryTransactionsListInventoryTransactionsToAttach.getClass(), inventoryTransactionsListInventoryTransactionsToAttach.getId());
                attachedInventoryTransactionsList.add(inventoryTransactionsListInventoryTransactionsToAttach);
            }
            products.setInventoryTransactionsList(attachedInventoryTransactionsList);
            em.persist(products);
            for (OrderDetails orderDetailsListOrderDetails : products.getOrderDetailsList()) {
                Products oldProductIdOfOrderDetailsListOrderDetails = orderDetailsListOrderDetails.getProductId();
                orderDetailsListOrderDetails.setProductId(products);
                orderDetailsListOrderDetails = em.merge(orderDetailsListOrderDetails);
                if (oldProductIdOfOrderDetailsListOrderDetails != null) {
                    oldProductIdOfOrderDetailsListOrderDetails.getOrderDetailsList().remove(orderDetailsListOrderDetails);
                    oldProductIdOfOrderDetailsListOrderDetails = em.merge(oldProductIdOfOrderDetailsListOrderDetails);
                }
            }
            for (PurchaseOrderDetails purchaseOrderDetailsListPurchaseOrderDetails : products.getPurchaseOrderDetailsList()) {
                Products oldProductIdOfPurchaseOrderDetailsListPurchaseOrderDetails = purchaseOrderDetailsListPurchaseOrderDetails.getProductId();
                purchaseOrderDetailsListPurchaseOrderDetails.setProductId(products);
                purchaseOrderDetailsListPurchaseOrderDetails = em.merge(purchaseOrderDetailsListPurchaseOrderDetails);
                if (oldProductIdOfPurchaseOrderDetailsListPurchaseOrderDetails != null) {
                    oldProductIdOfPurchaseOrderDetailsListPurchaseOrderDetails.getPurchaseOrderDetailsList().remove(purchaseOrderDetailsListPurchaseOrderDetails);
                    oldProductIdOfPurchaseOrderDetailsListPurchaseOrderDetails = em.merge(oldProductIdOfPurchaseOrderDetailsListPurchaseOrderDetails);
                }
            }
            for (InventoryTransactions inventoryTransactionsListInventoryTransactions : products.getInventoryTransactionsList()) {
                Products oldProductIdOfInventoryTransactionsListInventoryTransactions = inventoryTransactionsListInventoryTransactions.getProductId();
                inventoryTransactionsListInventoryTransactions.setProductId(products);
                inventoryTransactionsListInventoryTransactions = em.merge(inventoryTransactionsListInventoryTransactions);
                if (oldProductIdOfInventoryTransactionsListInventoryTransactions != null) {
                    oldProductIdOfInventoryTransactionsListInventoryTransactions.getInventoryTransactionsList().remove(inventoryTransactionsListInventoryTransactions);
                    oldProductIdOfInventoryTransactionsListInventoryTransactions = em.merge(oldProductIdOfInventoryTransactionsListInventoryTransactions);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Products products) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Products persistentProducts = em.find(Products.class, products.getId());
            List<OrderDetails> orderDetailsListOld = persistentProducts.getOrderDetailsList();
            List<OrderDetails> orderDetailsListNew = products.getOrderDetailsList();
            List<PurchaseOrderDetails> purchaseOrderDetailsListOld = persistentProducts.getPurchaseOrderDetailsList();
            List<PurchaseOrderDetails> purchaseOrderDetailsListNew = products.getPurchaseOrderDetailsList();
            List<InventoryTransactions> inventoryTransactionsListOld = persistentProducts.getInventoryTransactionsList();
            List<InventoryTransactions> inventoryTransactionsListNew = products.getInventoryTransactionsList();
            List<String> illegalOrphanMessages = null;
            for (InventoryTransactions inventoryTransactionsListOldInventoryTransactions : inventoryTransactionsListOld) {
                if (!inventoryTransactionsListNew.contains(inventoryTransactionsListOldInventoryTransactions)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InventoryTransactions " + inventoryTransactionsListOldInventoryTransactions + " since its productId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<OrderDetails> attachedOrderDetailsListNew = new ArrayList<OrderDetails>();
            for (OrderDetails orderDetailsListNewOrderDetailsToAttach : orderDetailsListNew) {
                orderDetailsListNewOrderDetailsToAttach = em.getReference(orderDetailsListNewOrderDetailsToAttach.getClass(), orderDetailsListNewOrderDetailsToAttach.getId());
                attachedOrderDetailsListNew.add(orderDetailsListNewOrderDetailsToAttach);
            }
            orderDetailsListNew = attachedOrderDetailsListNew;
            products.setOrderDetailsList(orderDetailsListNew);
            List<PurchaseOrderDetails> attachedPurchaseOrderDetailsListNew = new ArrayList<PurchaseOrderDetails>();
            for (PurchaseOrderDetails purchaseOrderDetailsListNewPurchaseOrderDetailsToAttach : purchaseOrderDetailsListNew) {
                purchaseOrderDetailsListNewPurchaseOrderDetailsToAttach = em.getReference(purchaseOrderDetailsListNewPurchaseOrderDetailsToAttach.getClass(), purchaseOrderDetailsListNewPurchaseOrderDetailsToAttach.getId());
                attachedPurchaseOrderDetailsListNew.add(purchaseOrderDetailsListNewPurchaseOrderDetailsToAttach);
            }
            purchaseOrderDetailsListNew = attachedPurchaseOrderDetailsListNew;
            products.setPurchaseOrderDetailsList(purchaseOrderDetailsListNew);
            List<InventoryTransactions> attachedInventoryTransactionsListNew = new ArrayList<InventoryTransactions>();
            for (InventoryTransactions inventoryTransactionsListNewInventoryTransactionsToAttach : inventoryTransactionsListNew) {
                inventoryTransactionsListNewInventoryTransactionsToAttach = em.getReference(inventoryTransactionsListNewInventoryTransactionsToAttach.getClass(), inventoryTransactionsListNewInventoryTransactionsToAttach.getId());
                attachedInventoryTransactionsListNew.add(inventoryTransactionsListNewInventoryTransactionsToAttach);
            }
            inventoryTransactionsListNew = attachedInventoryTransactionsListNew;
            products.setInventoryTransactionsList(inventoryTransactionsListNew);
            products = em.merge(products);
            for (OrderDetails orderDetailsListOldOrderDetails : orderDetailsListOld) {
                if (!orderDetailsListNew.contains(orderDetailsListOldOrderDetails)) {
                    orderDetailsListOldOrderDetails.setProductId(null);
                    orderDetailsListOldOrderDetails = em.merge(orderDetailsListOldOrderDetails);
                }
            }
            for (OrderDetails orderDetailsListNewOrderDetails : orderDetailsListNew) {
                if (!orderDetailsListOld.contains(orderDetailsListNewOrderDetails)) {
                    Products oldProductIdOfOrderDetailsListNewOrderDetails = orderDetailsListNewOrderDetails.getProductId();
                    orderDetailsListNewOrderDetails.setProductId(products);
                    orderDetailsListNewOrderDetails = em.merge(orderDetailsListNewOrderDetails);
                    if (oldProductIdOfOrderDetailsListNewOrderDetails != null && !oldProductIdOfOrderDetailsListNewOrderDetails.equals(products)) {
                        oldProductIdOfOrderDetailsListNewOrderDetails.getOrderDetailsList().remove(orderDetailsListNewOrderDetails);
                        oldProductIdOfOrderDetailsListNewOrderDetails = em.merge(oldProductIdOfOrderDetailsListNewOrderDetails);
                    }
                }
            }
            for (PurchaseOrderDetails purchaseOrderDetailsListOldPurchaseOrderDetails : purchaseOrderDetailsListOld) {
                if (!purchaseOrderDetailsListNew.contains(purchaseOrderDetailsListOldPurchaseOrderDetails)) {
                    purchaseOrderDetailsListOldPurchaseOrderDetails.setProductId(null);
                    purchaseOrderDetailsListOldPurchaseOrderDetails = em.merge(purchaseOrderDetailsListOldPurchaseOrderDetails);
                }
            }
            for (PurchaseOrderDetails purchaseOrderDetailsListNewPurchaseOrderDetails : purchaseOrderDetailsListNew) {
                if (!purchaseOrderDetailsListOld.contains(purchaseOrderDetailsListNewPurchaseOrderDetails)) {
                    Products oldProductIdOfPurchaseOrderDetailsListNewPurchaseOrderDetails = purchaseOrderDetailsListNewPurchaseOrderDetails.getProductId();
                    purchaseOrderDetailsListNewPurchaseOrderDetails.setProductId(products);
                    purchaseOrderDetailsListNewPurchaseOrderDetails = em.merge(purchaseOrderDetailsListNewPurchaseOrderDetails);
                    if (oldProductIdOfPurchaseOrderDetailsListNewPurchaseOrderDetails != null && !oldProductIdOfPurchaseOrderDetailsListNewPurchaseOrderDetails.equals(products)) {
                        oldProductIdOfPurchaseOrderDetailsListNewPurchaseOrderDetails.getPurchaseOrderDetailsList().remove(purchaseOrderDetailsListNewPurchaseOrderDetails);
                        oldProductIdOfPurchaseOrderDetailsListNewPurchaseOrderDetails = em.merge(oldProductIdOfPurchaseOrderDetailsListNewPurchaseOrderDetails);
                    }
                }
            }
            for (InventoryTransactions inventoryTransactionsListNewInventoryTransactions : inventoryTransactionsListNew) {
                if (!inventoryTransactionsListOld.contains(inventoryTransactionsListNewInventoryTransactions)) {
                    Products oldProductIdOfInventoryTransactionsListNewInventoryTransactions = inventoryTransactionsListNewInventoryTransactions.getProductId();
                    inventoryTransactionsListNewInventoryTransactions.setProductId(products);
                    inventoryTransactionsListNewInventoryTransactions = em.merge(inventoryTransactionsListNewInventoryTransactions);
                    if (oldProductIdOfInventoryTransactionsListNewInventoryTransactions != null && !oldProductIdOfInventoryTransactionsListNewInventoryTransactions.equals(products)) {
                        oldProductIdOfInventoryTransactionsListNewInventoryTransactions.getInventoryTransactionsList().remove(inventoryTransactionsListNewInventoryTransactions);
                        oldProductIdOfInventoryTransactionsListNewInventoryTransactions = em.merge(oldProductIdOfInventoryTransactionsListNewInventoryTransactions);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = products.getId();
                if (findProducts(id) == null) {
                    throw new NonexistentEntityException("The products with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Products products;
            try {
                products = em.getReference(Products.class, id);
                products.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The products with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<InventoryTransactions> inventoryTransactionsListOrphanCheck = products.getInventoryTransactionsList();
            for (InventoryTransactions inventoryTransactionsListOrphanCheckInventoryTransactions : inventoryTransactionsListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Products (" + products + ") cannot be destroyed since the InventoryTransactions " + inventoryTransactionsListOrphanCheckInventoryTransactions + " in its inventoryTransactionsList field has a non-nullable productId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<OrderDetails> orderDetailsList = products.getOrderDetailsList();
            for (OrderDetails orderDetailsListOrderDetails : orderDetailsList) {
                orderDetailsListOrderDetails.setProductId(null);
                orderDetailsListOrderDetails = em.merge(orderDetailsListOrderDetails);
            }
            List<PurchaseOrderDetails> purchaseOrderDetailsList = products.getPurchaseOrderDetailsList();
            for (PurchaseOrderDetails purchaseOrderDetailsListPurchaseOrderDetails : purchaseOrderDetailsList) {
                purchaseOrderDetailsListPurchaseOrderDetails.setProductId(null);
                purchaseOrderDetailsListPurchaseOrderDetails = em.merge(purchaseOrderDetailsListPurchaseOrderDetails);
            }
            em.remove(products);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Products> findProductsEntities() {
        return findProductsEntities(true, -1, -1);
    }

    public List<Products> findProductsEntities(int maxResults, int firstResult) {
        return findProductsEntities(false, maxResults, firstResult);
    }

    private List<Products> findProductsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Products.class));
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

    public Products findProducts(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Products.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Products> rt = cq.from(Products.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
