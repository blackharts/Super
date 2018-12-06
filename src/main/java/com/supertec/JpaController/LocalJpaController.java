/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supertec.JpaController;

import com.supertec.JpaController.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.supertec.clases.Tecnico;
import com.supertec.clases.Comuna;
import com.supertec.clases.Local;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author alfon
 */
public class LocalJpaController implements Serializable {

    public LocalJpaController(EntityManagerFactory emf) {
        this.emf = Persistence.createEntityManagerFactory("com.supertec_Supertec_war_1.0-SNAPSHOTPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Local local) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tecnico tecnico = local.getTecnico();
            if (tecnico != null) {
                tecnico = em.getReference(tecnico.getClass(), tecnico.getId());
                local.setTecnico(tecnico);
            }
            Comuna comuna = local.getComuna();
            if (comuna != null) {
                comuna = em.getReference(comuna.getClass(), comuna.getId());
                local.setComuna(comuna);
            }
            em.persist(local);
            if (tecnico != null) {
                tecnico.getLocalCollection().add(local);
                tecnico = em.merge(tecnico);
            }
            if (comuna != null) {
                comuna.getLocalCollection().add(local);
                comuna = em.merge(comuna);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Local local) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Local persistentLocal = em.find(Local.class, local.getId());
            Tecnico tecnicoOld = persistentLocal.getTecnico();
            Tecnico tecnicoNew = local.getTecnico();
            Comuna comunaOld = persistentLocal.getComuna();
            Comuna comunaNew = local.getComuna();
            if (tecnicoNew != null) {
                tecnicoNew = em.getReference(tecnicoNew.getClass(), tecnicoNew.getId());
                local.setTecnico(tecnicoNew);
            }
            if (comunaNew != null) {
                comunaNew = em.getReference(comunaNew.getClass(), comunaNew.getId());
                local.setComuna(comunaNew);
            }
            local = em.merge(local);
            if (tecnicoOld != null && !tecnicoOld.equals(tecnicoNew)) {
                tecnicoOld.getLocalCollection().remove(local);
                tecnicoOld = em.merge(tecnicoOld);
            }
            if (tecnicoNew != null && !tecnicoNew.equals(tecnicoOld)) {
                tecnicoNew.getLocalCollection().add(local);
                tecnicoNew = em.merge(tecnicoNew);
            }
            if (comunaOld != null && !comunaOld.equals(comunaNew)) {
                comunaOld.getLocalCollection().remove(local);
                comunaOld = em.merge(comunaOld);
            }
            if (comunaNew != null && !comunaNew.equals(comunaOld)) {
                comunaNew.getLocalCollection().add(local);
                comunaNew = em.merge(comunaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = local.getId();
                if (findLocal(id) == null) {
                    throw new NonexistentEntityException("The local with id " + id + " no longer exists.");
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
            Local local;
            try {
                local = em.getReference(Local.class, id);
                local.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The local with id " + id + " no longer exists.", enfe);
            }
            Tecnico tecnico = local.getTecnico();
            if (tecnico != null) {
                tecnico.getLocalCollection().remove(local);
                tecnico = em.merge(tecnico);
            }
            Comuna comuna = local.getComuna();
            if (comuna != null) {
                comuna.getLocalCollection().remove(local);
                comuna = em.merge(comuna);
            }
            em.remove(local);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Local> findLocalEntities() {
        return findLocalEntities(true, -1, -1);
    }

    public List<Local> findLocalEntities(int maxResults, int firstResult) {
        return findLocalEntities(false, maxResults, firstResult);
    }

    private List<Local> findLocalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Local.class));
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

    public Local findLocal(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Local.class, id);
        } finally {
            em.close();
        }
    }

    public int getLocalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Local> rt = cq.from(Local.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
