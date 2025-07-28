package uz.pdp.library.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book extends IdEntity {
    private String name;
    private String authorId;
    private Integer publishYear;
    private String imageUrl;
}
