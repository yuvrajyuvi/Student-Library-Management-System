package com.demo.example.student_library_management_system.model;

import com.demo.example.student_library_management_system.Enums.CardStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="card")
public class Card {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name="card_status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;
    @Column(name = "createDate",nullable = false)
    @CreationTimestamp
    private Date createDate;

    @Column(name = "updateDate",nullable = false)
    @UpdateTimestamp
    private Date updateDate;

    @JsonBackReference
    @OneToOne
    @JoinColumn
    private Student student;

    @JsonManagedReference
    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
    private List<Book>  noOfBooks  = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
    private List<Transaction>  transactionList  = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Book> getNoOfBooks() {
        return noOfBooks;
    }

    public void setNoOfBooks(List<Book> noOfBooks) {
        this.noOfBooks = noOfBooks;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
}
