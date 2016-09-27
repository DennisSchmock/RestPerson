/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import entity.Person;
import facade.PersonFacade;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author dennisschmock
 */
public class Tester {
    public static void main(String[] args) {
       // Persistence.generateSchema("pu",null);
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("pu");
        PersonFacade pf = new PersonFacade(emf);
        Person p1 = new Person("Dennis", "Schmock","+4560787336");
        Person p2 = new Person("2Dennis", "Schmock","+4560787336");
        Person p3 = new Person("3Dennis", "Schmock","+4560787336");
        Person p4 = new Person("4Dennis", "Schmock","+4560787336");
        pf.addPerson(p1);
        pf.addPerson(p2);
        pf.addPerson(p3);
        pf.addPerson(p4);
        
    }
}
