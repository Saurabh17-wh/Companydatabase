package com.info.company.controllers;

import com.info.company.entities.Authentication;
import com.info.company.entities.User;
import com.info.company.services.AuthenDetailsService;
import com.info.company.services.AuthenService;
import com.info.company.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController
{
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenService authenService;

    //get all users
    @GetMapping("/user/users")
    public ResponseEntity<List<User>> getUsers()
    {
        List<User> list = userService.getAllUsers();
        if(list.size() <= 0)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/admin/getauth")
    public ResponseEntity<List<Authentication>> getAuthen()
    {
        List<Authentication> list = authenService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    //get single user
    @GetMapping("/user/users/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") int id)
    {
        User user = userService.getUserById(id);
        return ResponseEntity.of(Optional.of(user));
        /*if(user == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(user));*/
//        try
//        {
//            User user = userService.getUserById(id);
//            return new ResponseEntity<User>(user , HttpStatus.OK);
//        }
//        catch(BusinessException e)
//        {
//            ControllerException ce = new ControllerException(e.getErrorCode() , e.getErrorMessage());
//            return new ResponseEntity<ControllerException>(ce , HttpStatus.BAD_REQUEST);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//            //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//            ControllerException ce = new ControllerException("613" , "Something went wrong");
//            return new ResponseEntity<ControllerException>(ce , HttpStatus.BAD_REQUEST);
//        }
    }

    //new user
    @PostMapping("/admin/users")
    public ResponseEntity<?> addUser(@RequestBody User user)
    {
        //User u = null;
        User u = this.userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
            /*return new ResponseEntity<User>(u , HttpStatus.CREATED);
        catch(BusinessException e)
        {
             ControllerException ce = new ControllerException(e.getErrorCode() , e.getErrorMessage());
            return new ResponseEntity<ControllerException>(ce , HttpStatus.BAD_REQUEST);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            ControllerException ce = new ControllerException("614" , "Something went wrong");
            return new ResponseEntity<ControllerException>(ce , HttpStatus.BAD_REQUEST);
        }*/
    }

    @PostMapping("/admin/saveauth")
    public ResponseEntity<?> addAuth(@RequestBody Authentication authentication)
    {
        //User u = null;
        Authentication a = this.authenService.addAuthen(authentication);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //Delete user
    @DeleteMapping("/admin/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") int userId)
    {
        this.userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //Update user
    @PutMapping("/admin/users/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("userId") int userId)
    {
        this.userService.updateUser(user , userId);
        return ResponseEntity.ok().body(user);
    }
}
