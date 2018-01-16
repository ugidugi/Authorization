package com.models;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "roles", catalog = "dbase")
public class UserRole {

    private long role_id;
    private String role;
    private User user;

    public UserRole(){}
    public UserRole(String role, User user){
        this.role = role;
        this.user = user;
    }
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "role_id")
    public long getRole_id() {
        return role_id;
    }
    public void setRole_id(long role_id) {
        this.role_id = role_id;
    }

    @Column(name = "role")
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public String toString(){
        return "Role: " + role;
    }
}