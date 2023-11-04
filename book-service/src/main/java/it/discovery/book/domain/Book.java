package it.discovery.book.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class Book extends BaseEntity {
    private String name;

    @Column(name = "publishingYear")
    private int year;

    private int pages;

    private double price;

    private int amount;

    @OneToOne
    private Person author;
}
