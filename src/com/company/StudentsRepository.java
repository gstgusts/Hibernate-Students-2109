package com.company;

import lombok.NonNull;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;

public class StudentsRepository {
    private static SessionFactory factory;

    public StudentsRepository() {
        try {
            factory = new Configuration().configure("com/company/hibernate/hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create session factory" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public Iterable<Student> getList() {
        var session = factory.openSession();

        try {
            var students = session.createQuery("FROM Student").list();
            return students;
        } catch (HibernateException ex) {
            System.err.println(ex);
        }
        finally {
            session.close();
        }

        return new ArrayList<>();
    }

    public Integer add(@NonNull Student student) {
        var session = factory.openSession();
        Integer studentId = null;

        try {
            studentId = (Integer) session.save(student);
        } catch (HibernateException ex) {
            System.err.println(ex);
        }
        finally {
            session.close();
        }

        return studentId;
    }

    public void update(@NonNull Student student) {
        if(student.getId() == 0) {
            throw new IllegalArgumentException("Id field value must not be null");
        }

        var session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(student);
            tx.commit();
        } catch (HibernateException ex) {
            if(tx != null) {
                tx.rollback();
            }
            System.err.println(ex);
        }
        finally {
            session.close();
        }
    }

    public Student getById(int id) {
        var session = factory.openSession();
        try {
            return session.get(Student.class, id);
        } catch (HibernateException ex) {
            System.err.println(ex);
        }
        finally {
            session.close();
        }

        return null;
    }

    public void delete(int id) {
        var session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            var st = session.get(Student.class, id);
            session.delete(st);
            tx.commit();
        } catch (HibernateException ex) {
            if(tx != null) {
                tx.rollback();
            }
            System.err.println(ex);
        }
        finally {
            session.close();
        }
    }

}
