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
import com.supertec.clases.Cliente;
import com.supertec.clases.Solicitud;
import com.supertec.clases.Tecnico;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author alfon
 */
public class SolicitudJpaController implements Serializable {

    public SolicitudJpaController(EntityManagerFactory emf) {
       this.emf = Persistence.createEntityManagerFactory("com.supertec_Supertec_war_1.0-SNAPSHOTPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Solicitud solicitud) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente cliente = solicitud.getCliente();
            if (cliente != null) {
                cliente = em.getReference(cliente.getClass(), cliente.getId());
                solicitud.setCliente(cliente);
            }
            Tecnico tecnico = solicitud.getTecnico();
            if (tecnico != null) {
                tecnico = em.getReference(tecnico.getClass(), tecnico.getId());
                solicitud.setTecnico(tecnico);
            }
            em.persist(solicitud);
            if (cliente != null) {
                cliente.getSolicitudCollection().add(solicitud);
                cliente = em.merge(cliente);
            }
            if (tecnico != null) {
                tecnico.getSolicitudCollection().add(solicitud);
                tecnico = em.merge(tecnico);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Solicitud solicitud) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Solicitud persistentSolicitud = em.find(Solicitud.class, solicitud.getId());
            Cliente clienteOld = persistentSolicitud.getCliente();
            Cliente clienteNew = solicitud.getCliente();
            Tecnico tecnicoOld = persistentSolicitud.getTecnico();
            Tecnico tecnicoNew = solicitud.getTecnico();
            if (clienteNew != null) {
                clienteNew = em.getReference(clienteNew.getClass(), clienteNew.getId());
                solicitud.setCliente(clienteNew);
            }
            if (tecnicoNew != null) {
                tecnicoNew = em.getReference(tecnicoNew.getClass(), tecnicoNew.getId());
                solicitud.setTecnico(tecnicoNew);
            }
            solicitud = em.merge(solicitud);
            if (clienteOld != null && !clienteOld.equals(clienteNew)) {
                clienteOld.getSolicitudCollection().remove(solicitud);
                clienteOld = em.merge(clienteOld);
            }
            if (clienteNew != null && !clienteNew.equals(clienteOld)) {
                clienteNew.getSolicitudCollection().add(solicitud);
                clienteNew = em.merge(clienteNew);
            }
            if (tecnicoOld != null && !tecnicoOld.equals(tecnicoNew)) {
                tecnicoOld.getSolicitudCollection().remove(solicitud);
                tecnicoOld = em.merge(tecnicoOld);
            }
            if (tecnicoNew != null && !tecnicoNew.equals(tecnicoOld)) {
                tecnicoNew.getSolicitudCollection().add(solicitud);
                tecnicoNew = em.merge(tecnicoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = solicitud.getId();
                if (findSolicitud(id) == null) {
                    throw new NonexistentEntityException("The solicitud with id " + id + " no longer exists.");
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
            Solicitud solicitud;
            try {
                solicitud = em.getReference(Solicitud.class, id);
                solicitud.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The solicitud with id " + id + " no longer exists.", enfe);
            }
            Cliente cliente = solicitud.getCliente();
            if (cliente != null) {
                cliente.getSolicitudCollection().remove(solicitud);
                cliente = em.merge(cliente);
            }
            Tecnico tecnico = solicitud.getTecnico();
            if (tecnico != null) {
                tecnico.getSolicitudCollection().remove(solicitud);
                tecnico = em.merge(tecnico);
            }
            em.remove(solicitud);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Solicitud> findSolicitudEntities() {
        return findSolicitudEntities(true, -1, -1);
    }

    public List<Solicitud> findSolicitudEntities(int maxResults, int firstResult) {
        return findSolicitudEntities(false, maxResults, firstResult);
    }

    private List<Solicitud> findSolicitudEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Solicitud.class));
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

    public Solicitud findSolicitud(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Solicitud.class, id);
        } finally {
            em.close();
        }
    }

    public int getSolicitudCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Solicitud> rt = cq.from(Solicitud.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
