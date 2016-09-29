/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restapi;

import com.google.gson.Gson;
import entity.Person;
import facade.IPersonFacade;
import facade.PersonFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author dennisschmock
 */
@Path("person")
public class PersonService {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static IPersonFacade pf = new PersonFacade(emf);
    private static Gson gson = new Gson();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PersonService
     */
    public PersonService() {
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public String editPerson(String p) {
        System.out.println("PUT");

        Person person = gson.fromJson(p, Person.class);
        pf.editPerson(person);
        return gson.toJson(person);

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addPerson(String p) {
        System.out.println("POST");
        Person person = gson.fromJson(p, Person.class);
        pf.addPerson(person);
        return gson.toJson(person);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersons() {
        System.out.println("GET all");
        List<Person> persons = pf.getPersons();
        return gson.toJson(persons);

    }

    @Path("{id}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public String deletePerson(@PathParam("id") int id,String p) {
        System.out.println("DELETE: " + id);
        Person person = pf.deletePerson(id);
        System.out.println(person);
        return gson.toJson(person);
    }

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPerson(@PathParam("id") int id) {
        Person person = pf.getPerson(id);
        return gson.toJson(person);

    }

}
