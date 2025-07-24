package uz.pdp.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthUser extends IdEntity {
    private String fullName;
    private String username;
    private String password;
}
