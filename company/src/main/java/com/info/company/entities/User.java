package com.info.company.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "User_id")
    private int id;
    @Column(name = "Designation")
    private String desig;
    @Column(name = "Supervisor")
    private String supervisor;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    //@Column(name = "Super_id")
    private Superid superid;

    public User(int id, String desig, String supervisor, Superid superid)
    {
        this.id = id;
        this.desig = desig;
        this.supervisor = supervisor;
        this.superid = superid;
    }

    public User()
    {
        super();
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getDesig()
    {
        return desig;
    }

    public void setDesig(String desig)
    {
        this.desig = desig;
    }

    public String getSupervisor()
    {
        return supervisor;
    }

    public void setSupervisor(String supervisor)
    {
        this.supervisor = supervisor;
    }

    public Superid getSuperid()
    {
        return superid;
    }

    public void setSuperid(Superid superid)
    {
        this.superid = superid;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "id=" + id +
                ", desig='" + desig + '\'' +
                ", supervisor='" + supervisor + '\'' +
                ", superid=" + superid +
                '}';
    }
}
