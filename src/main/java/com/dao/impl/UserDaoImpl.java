package com.dao.impl;

import com.dao.UserDao;
import com.models.User;
import com.models.UserRole;
import com.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao{
    private Transaction tx;
    private Session session;

    @SuppressWarnings("unchecked")
    @Override
    public User findUserByLogin(String login) {
        List<User> users = new ArrayList<User>();
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        users = session
                .createQuery("from User where login=?")
                .setParameter(0, login)
                .list();
        if (session.getTransaction().getStatus().equals(TransactionStatus.ACTIVE)){
            session.getTransaction().commit();
            session.close();
        }

        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

    public void add(Object object){
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(object);
            if(object instanceof User){
                session.save(new UserRole("ROLE_USER",(User)object));
            }
            if (tx.getStatus().equals(TransactionStatus.ACTIVE)){
                tx.commit();
            }
        }catch (Exception ex){
            tx.rollback();
            session.close();
        }
    }
}
