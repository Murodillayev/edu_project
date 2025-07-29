package uz.pdp.library.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
//@Table(name = "authors")
public class Author extends IdEntity {

    @Column(name = "first_name", unique = true, nullable = false)
    private String firstName;

    private String lastName;
}
