package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import jm.task.core.jdbc.util.cmdSQL;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao, cmdSQL {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSession();) {
            Transaction tx = session.beginTransaction();

            session.createSQLQuery(cmdSQL.CREATE_TABLES).executeUpdate();
            try {
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                e.printStackTrace();
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSession();) {
            Transaction tx = session.beginTransaction();

            session.createSQLQuery(cmdSQL.DROP_TABLES).executeUpdate();
            try {
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSession();) {
            Transaction tx = session.beginTransaction();

            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);

            session.save(user);

            try {
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSession();) {
            Transaction tx = session.beginTransaction();

            User user = session.get(User.class, id);

            if (user != null) {
                session.delete(user);
            } else {
                System.out.println("User not found");
            }
            try {
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Session session = Util.getSession();) {
            session.beginTransaction();

            users = session.createQuery("FROM User", User.class).getResultList();

            for (User user : users) {
                System.out.println(user);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSession();) {
            Transaction tx = session.beginTransaction();

            session.createSQLQuery(cmdSQL.CLEAN_TABLES).executeUpdate();

            try {
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
