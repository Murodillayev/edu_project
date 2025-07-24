package uz.pdp.library.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Author extends IdEntity {
    private String firstName;
    private String lastName;
}
