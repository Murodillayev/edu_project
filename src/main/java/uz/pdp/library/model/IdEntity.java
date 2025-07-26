package uz.pdp.library.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class IdEntity {
    private String id;
    public IdEntity() {
        this.id = UUID.randomUUID().toString();
    }
}
