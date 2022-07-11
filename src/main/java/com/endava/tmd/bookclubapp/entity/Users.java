package com.endava.tmd.bookclubapp.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = true, unique = true)
    private Long iduser;

    @Column(unique = true)
    private String first_name;

    @Column(unique = true)
    private String last_name;

    @Column(unique = true)
    private String username;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(unique = true)
    private String email;

    @Override
    public String toString(){
        return "User{" +
                "iduser=" + getIduser() +
                ", first name=" + getFirst_name() + '\'' +
                ", last_name=" + getLast_name() + '\'' +
                ", username=" + getUsername()+'\''+
                ", email=" + getEmail() + '\''+
                ", password=" + getPassword() + '\'' +
                "}";
    }
}
