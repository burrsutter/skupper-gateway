package com.burrsutter;

import java.time.LocalDate;
import java.time.Month;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/stuff")
public class StuffResource {

    int cnt = 1;

    @GET    
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Stuff: " + cnt++;
    }

    @GET
    @Path("/add")
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public String addMany() {
        Todo one = new Todo();
        cnt++;
        one.id = null;
        one.title = "One:" + cnt;
        one.persist();

        System.out.println("todo isPersistent: " + one.isPersistent());

        Todo two = new Todo();    
        two.id = null;
        two.title = "Two:" + cnt;
        two.persist();


        Person person = new Person();
        person.name = "Burr";
        person.birth = LocalDate.of(1970, Month.JANUARY, 1);
        person.status = Status.Alive;
        person.persist();

        Person person2 = new Person();
        person2.name = "Hugo";
        person2.birth = LocalDate.of(1980, Month.FEBRUARY, 2);
        person2.status = Status.Alive;
        person2.persist();
        
        Person person3 = new Person();
        person3.name = "Eric";
        person3.birth = LocalDate.of(1990, Month.MARCH, 3);
        person3.status = Status.Alive;
        person3.persist();

        return "Added Records";
    }

    @GET
    @Path("/delete")
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public String deleteAll() {

        Todo.deleteAll();
        Person.deleteAll();

        return "Deleted All";
    }



}