package com.example.grpc.client.model;
import jakarta.persistence.*;


@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Primary key generation
    private Integer clientId; // Changed to Long for auto-generated ID

    @Column(nullable = false)
    private String name;         // Client's name

    @Column(nullable = false)
    private String phone;        // Client's phone number

    @Column(nullable = false)
    private String mail;         // Client's email address

    @ManyToOne(cascade = CascadeType.PERSIST)
    private CreditCard creditCard; // Reference to a CreditCard object

    // Constructor (no need for clientId in the constructor, it will be auto-generated)
    public Client(String name, String phone, String mail, CreditCard creditCard) {
        this.name = name;
        this.phone = phone;
        this.mail = mail;
        this.creditCard = creditCard;
    }

    // Getter and setter methods for each attribute
    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    // Override toString() method for easy printing
    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", mail='" + mail + '\'' +
                ", creditCard=" + creditCard +
                '}';
    }
}
