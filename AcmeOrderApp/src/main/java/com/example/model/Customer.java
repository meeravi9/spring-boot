package com.example.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy="customer", cascade = CascadeType.ALL)
    private List<Order> order;
    
    private String name;
    

    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_info_id", referencedColumnName = "id")
    private ContactInfo contactInfo;

    public Customer(long l, String john) {
    }

    public Customer() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public List<Order> getOrder() {
        return null;
    }

    public void setOrder(List<Order> orders) {
    }
}
