package ru.roboart.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

/**
 * Created by toktar.
 */
@Entity
public class Gift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String code = UUID.randomUUID().toString();

    public String getCode() {
        return code;
    }
}
