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
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dennisschmock
 */
public class PersonFacadeTest {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_test");
    private static IPersonFacade pf = new PersonFacade(emf);

    public PersonFacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.createQuery("delete from Person").executeUpdate();
            em.getTransaction().commit();
        }finally{
            
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddPerson() {
        System.out.println("addPerson");
        Person p = new Person("XXX", "Fname", "34324");
        
        Person expResult = new Person("XXX", "Fname", "34324");
        
        
        Person result = pf.addPerson(p);
        expResult.setId(result.getId());
        assertEquals(expResult, result);
        EntityManager em = emf.createEntityManager();
        try{
            Person result2 = em.find(Person.class, result.getId());
            assertEquals(expResult, result2);
        } finally {
            em.close();
        }
    }

//    @Test
//    public void testDeletePerson() {
//        System.out.println("deletePerson");
//        int id = 0;
//        PersonFacade instance = new PersonFacade();
//        Person expResult = null;
//        Person result = instance.deletePerson(id);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testGetPerson() {
//        System.out.println("getPerson");
//        int id = 0;
//        PersonFacade instance = new PersonFacade();
//        Person expResult = null;
//        Person result = instance.getPerson(id);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testGetPersons() {
//        System.out.println("getPersons");
//        PersonFacade instance = new PersonFacade();
//        List<Person> expResult = null;
//        List<Person> result = instance.getPersons();
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testEditPerson() {
//        System.out.println("editPerson");
//        Person p = null;
//        PersonFacade instance = new PersonFacade();
//        Person expResult = null;
//        Person result = instance.editPerson(p);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }

}
