/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Turno;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Odontologo;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author danie
 */
public class OdontologoJpaController implements Serializable {

    public OdontologoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public OdontologoJpaController() {
        emf = Persistence.createEntityManagerFactory("ConsultorioOdontologico_PU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Odontologo odontologo) {
        if (odontologo.getTurno() == null) {
            odontologo.setTurno(new ArrayList<Turno>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Turno> attachedTurno = new ArrayList<Turno>();
            for (Turno turnoTurnoToAttach : odontologo.getTurno()) {
                turnoTurnoToAttach = em.getReference(turnoTurnoToAttach.getClass(), turnoTurnoToAttach.getId());
                attachedTurno.add(turnoTurnoToAttach);
            }
            odontologo.setTurno(attachedTurno);
            em.persist(odontologo);
            for (Turno turnoTurno : odontologo.getTurno()) {
                Odontologo oldOdontologoOfTurnoTurno = turnoTurno.getOdontologo();
                turnoTurno.setOdontologo(odontologo);
                turnoTurno = em.merge(turnoTurno);
                if (oldOdontologoOfTurnoTurno != null) {
                    oldOdontologoOfTurnoTurno.getTurno().remove(turnoTurno);
                    oldOdontologoOfTurnoTurno = em.merge(oldOdontologoOfTurnoTurno);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Odontologo odontologo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Odontologo persistentOdontologo = em.find(Odontologo.class, odontologo.getId());
            List<Turno> turnoOld = persistentOdontologo.getTurno();
            List<Turno> turnoNew = odontologo.getTurno();
            List<Turno> attachedTurnoNew = new ArrayList<Turno>();
            for (Turno turnoNewTurnoToAttach : turnoNew) {
                turnoNewTurnoToAttach = em.getReference(turnoNewTurnoToAttach.getClass(), turnoNewTurnoToAttach.getId());
                attachedTurnoNew.add(turnoNewTurnoToAttach);
            }
            turnoNew = attachedTurnoNew;
            odontologo.setTurno(turnoNew);
            odontologo = em.merge(odontologo);
            for (Turno turnoOldTurno : turnoOld) {
                if (!turnoNew.contains(turnoOldTurno)) {
                    turnoOldTurno.setOdontologo(null);
                    turnoOldTurno = em.merge(turnoOldTurno);
                }
            }
            for (Turno turnoNewTurno : turnoNew) {
                if (!turnoOld.contains(turnoNewTurno)) {
                    Odontologo oldOdontologoOfTurnoNewTurno = turnoNewTurno.getOdontologo();
                    turnoNewTurno.setOdontologo(odontologo);
                    turnoNewTurno = em.merge(turnoNewTurno);
                    if (oldOdontologoOfTurnoNewTurno != null && !oldOdontologoOfTurnoNewTurno.equals(odontologo)) {
                        oldOdontologoOfTurnoNewTurno.getTurno().remove(turnoNewTurno);
                        oldOdontologoOfTurnoNewTurno = em.merge(oldOdontologoOfTurnoNewTurno);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = odontologo.getId();
                if (findOdontologo(id) == null) {
                    throw new NonexistentEntityException("The odontologo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Odontologo odontologo;
            try {
                odontologo = em.getReference(Odontologo.class, id);
                odontologo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The odontologo with id " + id + " no longer exists.", enfe);
            }
            List<Turno> turno = odontologo.getTurno();
            for (Turno turnoTurno : turno) {
                turnoTurno.setOdontologo(null);
                turnoTurno = em.merge(turnoTurno);
            }
            em.remove(odontologo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Odontologo> findOdontologoEntities() {
        return findOdontologoEntities(true, -1, -1);
    }

    public List<Odontologo> findOdontologoEntities(int maxResults, int firstResult) {
        return findOdontologoEntities(false, maxResults, firstResult);
    }

    private List<Odontologo> findOdontologoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Odontologo.class));
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

    public Odontologo findOdontologo(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Odontologo.class, id);
        } finally {
            em.close();
        }
    }

    public int getOdontologoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Odontologo> rt = cq.from(Odontologo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
