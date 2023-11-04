package it.discovery.book.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table
/**
 * Person who writes the books
 * @author morenets
 *
 */
public class Person extends BaseEntity {
    private String name;

    private String biography;

    @OneToMany
    private List<Book> books;

    private LocalDate birthDate;

}
