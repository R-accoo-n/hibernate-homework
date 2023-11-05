package com.example.hibernatehomework.services;

import com.example.hibernatehomework.models.Address;
import com.example.hibernatehomework.models.Person;
import com.example.hibernatehomework.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

public class MainService {
    private static SessionFactory sessionFactory;
    private static PersonService personService;

    private MainService() {

    }

    public static void startMain(){
        try{
            MainService.sessionFactory = HibernateUtil.getSessionFactory();
            MainService.personService = new PersonService(sessionFactory);
            MainService.action();
        }catch (HibernateException he){
            System.out.println(he.getMessage());
        }finally {
            MainService.finishMain();
        }
    }

    public static void action(){
        Person person = new Person();
        person.setName("Joe Biden");
        person.setAge(50);
        Address address = new Address();
        address.setCity("Washington, D.C.");
        address.setPerson(person);
        address.setStreet("Pennsylvania Avenue NW Washington, D.C.");
        address.setZipCode("20500");
        person.setAddress(address);

        person.setName("Donald Trump");
        person.setAge(48);

        personService.createPerson(person);
        personService.getPerson(person.getId());
        personService.updatePerson(person.getId(), person);
        personService.getPerson(person.getId());
        personService.deletePerson(person.getId());
        personService.getPerson(person.getId());
    }

    public static void finishMain(){
        MainService.sessionFactory.close();
    }
}
