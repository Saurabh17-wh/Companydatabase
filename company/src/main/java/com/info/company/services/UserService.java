package com.info.company.services;

import com.info.company.dao.UserRepository;
import com.info.company.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    //get single user
    public List<User> getAllUsers()
    {
        List<User> list = (List<User>)this.userRepository.findAll();
        return list;
    }

    //get all users
    public User getUserById(int id) {
        User user = null;
        try {
            user = this.userRepository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    //adding user
    public User addUser(User u)
    {
        User save = this.userRepository.save(u);
        return save;
    }

    //delete user
    public void deleteUser(int uid)
    {
        this.userRepository.deleteById(uid);
    }

    public void updateUser(User user , int userId)
    {
        user.setId(userId);
        userRepository.save(user);
    }
}
