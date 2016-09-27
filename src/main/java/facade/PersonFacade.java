/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author dennisschmock
 */
public class PersonFacade implements IPersonFacade {

    private static EntityManagerFactory emf;
    

    public PersonFacade() {
        
    }

    public PersonFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void addEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
         
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public Person addPerson(Person p) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return p;
    }

    @Override
    public Person deletePerson(int id) {
        EntityManager em = getEntityManager();
        Person person = em.find(Person.class, id);
        try {
            em.remove(em);
        } finally {
            em.close();
        }
        return person;
    }

    @Override
    public Person getPerson(int id) {
        EntityManager em = getEntityManager();
        Person person = null;
        try {
            person = em.find(Person.class, id);
        } finally {
            em.close();
        }
        return person;
    }

    @Override
    public List<Person> getPersons() {
        List<Person> persons;
        Query q = getEntityManager().createQuery("SELECT p FROM Person p");
        return persons = q.getResultList();
    }

    @Override
    public Person editPerson(Person p) {
        EntityManager em = getEntityManager();
        try {
            em.merge(p);
        } finally {
            em.close();
        }
        return p;
    }

}
