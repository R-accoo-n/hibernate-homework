package com.example.hibernatehomework.services;

import com.example.hibernatehomework.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class PersonService {

    private final SessionFactory sessionFactory;

    public PersonService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createPerson(Person person){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(person);
        transaction.commit();
        session.close();
    }

    public Person getPerson(int id){
        Session session = sessionFactory.openSession();
        Person loadedPerson = session.get(Person.class, id);
        session.close();

        System.out.println(loadedPerson);

        return loadedPerson;
    }

    public void updatePerson(int id, Person newPerson){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Person personToUpdate = session.get(Person.class, id);
        if (personToUpdate != null) {
            personToUpdate.setName(newPerson.getName());
            personToUpdate.setAge(newPerson.getAge());
            session.merge(newPerson);
        }

        transaction.commit();
        session.close();
    }

    public void deletePerson(int id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Person personToDelete = session.get(Person.class, id);
        if (personToDelete != null) {
            session.remove(personToDelete);
        }

        transaction.commit();
        session.close();
    }
}
