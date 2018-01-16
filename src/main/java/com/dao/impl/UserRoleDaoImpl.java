package com.dao.impl;

import com.dao.UserRoleDao;
import com.models.UserRole;
import com.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

public class UserRoleDaoImpl implements UserRoleDao {

    @Override
    public void add(UserRole userRole) {
    }
}
