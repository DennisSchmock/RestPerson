/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.google.gson.Gson;
import entity.Person;
import java.util.List;

/**
 *
 * @author dennisschmock
 */
public class JSONConverter {
    
    private Gson gson = new Gson();
    
    public Person getPersonFromJson(String json){
        Person p = new Person(json, json, json);
        return null;
    }
    
    public String getJsonFromPerson(Person p){
        return null;
    }
    
    public String getJsonFromPersons(List<Person> persons){
        return null;
    }
}
