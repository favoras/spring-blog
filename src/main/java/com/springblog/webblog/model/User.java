package com.springblog.webblog.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, message = "*Username mus have at least 3 characters")
    @NotEmpty(message = "*Username can't be empty")
    private String username;

    @Size(min = 3, message = "*Your password must have at least 3 characters")
    @NotEmpty(message = "*Password can't be empty")
    private String password;

    @Column(name = "is_registered")
    private boolean isRegistered;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") } )
    private Collection<Role> roles;

    @OneToMany(mappedBy = "user")
    private Collection<Post> posts;

    @OneToMany(mappedBy = "user")
    private Collection<Comment> comments;

}
