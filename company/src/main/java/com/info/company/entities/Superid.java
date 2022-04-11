package com.info.company.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Supervisor")
public class Superid
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Superid;
    private String fname;
    private String lname;

   /* @OneToOne(mappedBy = "superid")
    @JsonBackReference
    private User user;*/

    @JsonIgnore
    @OneToMany(mappedBy = "superid")
    private List<User> user;

    public Superid()
    {
        super();
    }

    public Superid(int superid, String fname, String lname)
    {
        Superid = superid;
        this.fname = fname;
        this.lname = lname;
    }

    public Superid(List<User> user) {
        this.user = user;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    /* public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }*/

    public int getSuperid() {
        return Superid;
    }

    public void setSuperid(int superid) {
        Superid = superid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
}
