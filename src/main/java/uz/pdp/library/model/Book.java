package uz.pdp.library.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Book extends IdEntity {
    private String name;

    private String authorId;

    @ManyToOne
    @JoinColumn(name = "a_id")
    private Author author;

    private Integer publishYear;
    private String imageUrl;
}

