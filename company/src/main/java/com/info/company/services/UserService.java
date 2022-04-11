package com.info.company.services;

import com.info.company.customexception.EmptyInputException;
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

    //get all users
    public List<User> getAllUsers()
    {
        List<User> list = (List<User>) this.userRepository.findAll();
        //List<User> list = null;
        /*try
        {
            list = (List<User>)this.userRepository.findAll();
        }
        catch(Exception e)
        {
            throw new BusinessException("605" , "Something went wrong in the service layer while fetching all users" + e.getMessage());
        }
        if(list.isEmpty())
            throw new BusinessException("604" , "List is empty");*/
        return list;
    }

    //get single users
    public User getUserById(int id)
    {
        User user = null;
        this.userRepository.findById(id);
        return user;
        /*try
        {
            this.userRepository.findById(id);
        }*/
        /*catch (IllegalArgumentException e)
        {
            throw new BusinessException("606", "Given user is null , give some valid id" + e.getMessage());
        }
        catch (NoSuchElementException e)
        {
            throw new BusinessException("607" , "Given user is doesn't exist" + e.getMessage());
        }*/
       /* catch(Exception e)
        {
            e.printStackTrace();
            throw new EmptyInputException("608", "Something went wrong in service layer while fetching the employee" + e.getMessage());
        }
        return user;*/
    }

    //adding user
    public User addUser(User u)
    {
        if(u.getName().isEmpty() || u.getName().length() == 0)
            throw new EmptyInputException("601" , "Please send proper name, It's blank");
        else if(u.getId() == 0)
        {
            throw new EmptyInputException("" , "");
        }
        /*try
        {
                return this.userRepository.save(u);
        }
        catch (IllegalArgumentException e)
        {
            throw new BusinessException("602", "Given user is null" + e.getMessage());
        }
        catch(Exception e)
        {
            throw new BusinessException("603", "Something went wrong in service layer while saving the employee" + e.getMessage());
        }*/
        User save = this.userRepository.save(u);
        return save;

    }

    //delete user
    public void deleteUser(int uid)
    {
        /*try
        {
            this.userRepository.deleteById(uid);
        }
        catch(IllegalArgumentException e)
        {
            throw new BusinessException("609" , "Given user is null , give some valid id");
        }
        catch (NoSuchElementException e)
        {
            throw new BusinessException("610" , "Given user is doesn't exist");
        }*/
        this.userRepository.deleteById(uid);
    }

    public void updateUser(User user , int userId)
    {
        /*try
        {
            user.setId(userId);
            userRepository.save(user);
        }
        catch (IllegalArgumentException e)
        {
            throw new BusinessException("611", "Given user is null , give some valid id" + e.getMessage());
        }
        catch (NoSuchElementException e)
        {
            throw new BusinessException("612" , "Given user is doesn't exist");
        }*/
        if(user.getId() == 0)
        {
            throw new EmptyInputException("" , "");
        }
        else if(user.getName().isEmpty() || user.getName().length() == 0)
            throw new EmptyInputException("" , "");
        user.setId(userId);
        userRepository.save(user);
    }
}
