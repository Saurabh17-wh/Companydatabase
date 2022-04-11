package com.info.company.services;

import com.info.company.dao.AuthenRepo;
import com.info.company.entities.AuthenDetails;
import com.info.company.entities.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuthenDetailsService implements UserDetailsService
{
    @Autowired
    AuthenRepo authenRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Authentication authentication = authenRepo.getUserByUsername(username);

        if(authentication == null)
        {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new AuthenDetails(authentication);
    }
}
