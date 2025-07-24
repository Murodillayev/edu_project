package uz.pdp.library.model;

import lombok.Getter;

import java.util.UUID;

@Getter
public class IdEntity {
    private String id;
    public IdEntity() {
        this.id = UUID.randomUUID().toString();
    }
}
