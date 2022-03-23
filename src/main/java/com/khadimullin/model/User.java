package com.khadimullin.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String email;

    private String hashPassword;

    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
    private List<Appeal> appeals;

    @Size(min = 8, max = 64, message = "Password should contains from 8 to 64 symbols")
    @Column(nullable = false, length = 64)
    private String password;

    private boolean enabled;

    @Column(length = 64)
    private String verificationCode;

    public User(String name, String email) {}

    public User() {

    }


    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public String getPassword() {
        return password;
    }

    public User(String name, String email, String verificationCode, String password) {
        this.name = name;
        this.email = email;
        this.verificationCode = verificationCode;
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String hashPassword(String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.hashPassword = hashPassword(password);
    }
}
