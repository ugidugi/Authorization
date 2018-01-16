package com.service;

import com.dao.impl.UserDaoImpl;
import com.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = new UserDaoImpl().findUserByLogin(login);
        if(user == null){
            throw  new UsernameNotFoundException("User not found");
        }
        AuthUser authUser = new AuthUser(user);
        return authUser;
    }
}
