package com.project.polyclinic.models;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username, password;
    private boolean active, isDoctor;

    @Column(name = "is_admin")
    private boolean isAdmin;

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean admin) {
        isAdmin = admin;
    }


//    @ManyToMany(mappedBy = "subscribers", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private Set<Clothes> favorites = new HashSet<>();

//    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
//    @CollectionTable(name="user_role", joinColumns = @JoinColumn(name = "user_id"))
//    @Enumerated
//    private Set<Role> roles;


    public boolean getIsDoctor() {
        return isDoctor;
    }

    public void setIsDoctor(boolean doctor) {
        isDoctor = doctor;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("USER"));
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

//    public Set<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<Role> roles) {
//        this.roles = roles;
//    }

//    public Set<Clothes> getFavorites() {
//        return favorites;
//    }
//
//    public void setFavorites(Set<Clothes> favorites) {
//        this.favorites = favorites;
//    }
}
