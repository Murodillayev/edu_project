package uz.pdp.library.model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public class IdEntity {
    @Id
    private String id;
    public IdEntity() {
        this.id = UUID.randomUUID().toString();
    }
}
