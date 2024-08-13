/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Paciente;
import logica.Odontologo;
import logica.Turno;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author danie
 */
public class TurnoJpaController implements Serializable {

    public TurnoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public TurnoJpaController() {
        emf = Persistence.createEntityManagerFactory("ConsultorioOdontologico_PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Turno turno) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paciente paciente = turno.getPaciente();
            if (paciente != null) {
                paciente = em.getReference(paciente.getClass(), paciente.getId());
                turno.setPaciente(paciente);
            }
            Odontologo odontologo = turno.getOdontologo();
            if (odontologo != null) {
                odontologo = em.getReference(odontologo.getClass(), odontologo.getId());
                turno.setOdontologo(odontologo);
            }
            em.persist(turno);
            if (paciente != null) {
                paciente.getTurnos().add(turno);
                paciente = em.merge(paciente);
            }
            if (odontologo != null) {
                odontologo.getTurno().add(turno);
                odontologo = em.merge(odontologo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Turno turno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Turno persistentTurno = em.find(Turno.class, turno.getId());
            Paciente pacienteOld = persistentTurno.getPaciente();
            Paciente pacienteNew = turno.getPaciente();
            Odontologo odontologoOld = persistentTurno.getOdontologo();
            Odontologo odontologoNew = turno.getOdontologo();
            if (pacienteNew != null) {
                pacienteNew = em.getReference(pacienteNew.getClass(), pacienteNew.getId());
                turno.setPaciente(pacienteNew);
            }
            if (odontologoNew != null) {
                odontologoNew = em.getReference(odontologoNew.getClass(), odontologoNew.getId());
                turno.setOdontologo(odontologoNew);
            }
            turno = em.merge(turno);
            if (pacienteOld != null && !pacienteOld.equals(pacienteNew)) {
                pacienteOld.getTurnos().remove(turno);
                pacienteOld = em.merge(pacienteOld);
            }
            if (pacienteNew != null && !pacienteNew.equals(pacienteOld)) {
                pacienteNew.getTurnos().add(turno);
                pacienteNew = em.merge(pacienteNew);
            }
            if (odontologoOld != null && !odontologoOld.equals(odontologoNew)) {
                odontologoOld.getTurno().remove(turno);
                odontologoOld = em.merge(odontologoOld);
            }
            if (odontologoNew != null && !odontologoNew.equals(odontologoOld)) {
                odontologoNew.getTurno().add(turno);
                odontologoNew = em.merge(odontologoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = turno.getId();
                if (findTurno(id) == null) {
                    throw new NonexistentEntityException("The turno with id " + id + " no longer exists.");
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
            Turno turno;
            try {
                turno = em.getReference(Turno.class, id);
                turno.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The turno with id " + id + " no longer exists.", enfe);
            }
            Paciente paciente = turno.getPaciente();
            if (paciente != null) {
                paciente.getTurnos().remove(turno);
                paciente = em.merge(paciente);
            }
            Odontologo odontologo = turno.getOdontologo();
            if (odontologo != null) {
                odontologo.getTurno().remove(turno);
                odontologo = em.merge(odontologo);
            }
            em.remove(turno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Turno> findTurnoEntities() {
        return findTurnoEntities(true, -1, -1);
    }

    public List<Turno> findTurnoEntities(int maxResults, int firstResult) {
        return findTurnoEntities(false, maxResults, firstResult);
    }

    private List<Turno> findTurnoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Turno.class));
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

    public Turno findTurno(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Turno.class, id);
        } finally {
            em.close();
        }
    }

    public int getTurnoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Turno> rt = cq.from(Turno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
