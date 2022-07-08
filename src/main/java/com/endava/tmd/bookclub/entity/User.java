package com.endava.tmd.bookclub.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.List;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    public Long getIduser() {
        return iduser;
    }

    public void setUser(Long iduser) {
        this.iduser = iduser;
    }

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

    @JsonIgnore
    @OneToMany
    private List<Book> bookList;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List <Loan> loans;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List <Reservation> reservations;

    @Override
    public String toString(){
        return "User{" +
                "id=" + getIduser() +
                ", first name=" + getFirst_name() + '\'' +
                ", last_name=" + getLast_name() + '\'' +
                ", username=" + getUsername() +'\''+
                ", email=" + getEmail() + '\''+
                ", password=" + getPassword() + '\'' +
                "}";
    }

    public Long getId() {
        return iduser;
    }


}
