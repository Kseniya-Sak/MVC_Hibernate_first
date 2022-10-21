package web.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import web.config.Util;
import web.model.User;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImp implements UserService {

    private SessionFactory sessionFactory = Util.getSessionFactory();

    @Override
    public void addUser(User user) {
      Session session = sessionFactory.openSession();
      session.beginTransaction();
      session.persist(user);
      session.getTransaction().commit();
      session.close();
    }

    @Override
    public void removeUser(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(getUser(id));
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> listUsers = new ArrayList<>();
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        listUsers = session.createQuery("FROM User").getResultList();
        session.getTransaction().commit();
        session.close();
        return listUsers;
    }

    @Override
    public User getUser(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.getTransaction().commit();
        session.close();

        return user;
    }
}

