package com.info.company.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.util.List;

@Entity
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "User_id")
    private int id;
    @Column(name = "Designation")
    private String desig;
    private String name;
    private String supervisor;

   /* @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    //@Column(name = "Super_id")*/
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "super_id" , referencedColumnName = "Superid")
    private Superid superid;

    public User()
    {
        super();
    }

    public User(int id, String desig, String name, String supervisor, Superid superid)
    {
        this.id = id;
        this.desig = desig;
        this.name = name;
        this.supervisor = supervisor;
        this.superid = superid;
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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
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
                ", name='" + name + '\'' +
                ", supervisor='" + supervisor + '\'' +
                ", superid=" + superid +
                '}';
    }
}
