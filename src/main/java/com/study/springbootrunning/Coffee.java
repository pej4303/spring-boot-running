package com.study.springbootrunning;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
@Table(name = "TB_COFFEE")
public class Coffee {
    @Id
    private String id;
    @Column(name = "name", nullable = false)
    private String name;

    public Coffee(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public Coffee(String name) {
        this( UUID.randomUUID().toString(), name);
    }
}