package com.info.company.services;

import com.info.company.dao.AuthenRepo;
import com.info.company.entities.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthenService
{
    @Autowired
    private AuthenRepo authenRepo;

    public List<Authentication> getAll()
    {
        List<Authentication> list = (List<Authentication>)this.authenRepo.findAll();
        return list;
    }

    public Authentication addAuthen(Authentication a)
    {
        Authentication response = this.authenRepo.save(a);
        return response;
    }
}
