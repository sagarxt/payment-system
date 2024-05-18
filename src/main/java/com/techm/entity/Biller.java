package com.techm.entity;

import com.techm.enums.*;
import jakarta.persistence.*;

@Entity
public class Biller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billerId;
    private String billerName;
    private String email;
    private String password;
    private String about;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private BillerCategory category;
    private boolean active;

    public Biller() {
    }

    public Biller(String billerName, String email, String password, String about, String category) {
        this.billerName = billerName;
        this.email = email;
        this.password = password;
        this.about = about;
        this.active = true;
        this.category = BillerCategory.valueOf(category.toUpperCase());
        this.role = Role.BILLER;
    }

    public Long getBillerId() {
        return billerId;
    }

    public void setBillerId(Long billerId) {
        this.billerId = billerId;
    }

    public String getBillerName() {
        return billerName;
    }

    public void setBillerName(String billerName) {
        this.billerName = billerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public BillerCategory getCategory() {
        return category;
    }

    public void setCategory(BillerCategory category) {
        this.category = category;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
