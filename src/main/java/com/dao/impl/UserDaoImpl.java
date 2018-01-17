package com.dao.impl;

import com.dao.UserDao;
import com.models.User;
import com.models.UserRole;
import com.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

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

    @Override
    public List<User> showAll(String user_role) {
        List<User> users = new ArrayList<User>();
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
        //users = session.createQuery("from User left join UserRole on " +
                //"User.user_id = UserRole.user.user_id where UserRole.role =?").setParameter(0,"ROLE_ADMIN").list();
        Query query = session.createQuery("from User as user left join UserRole as role on user.user_id=role.user.user_id where role.role='"+user_role+"'");
        List<Object[]> list = query.list();
        for(Object object[]: list){
            User user = (User) object[0];
            users.add(user);
        }
        if (session.getTransaction().getStatus().equals(TransactionStatus.ACTIVE)) {
            session.getTransaction().commit();
            session.close();
        }
        return users;
    }


}
