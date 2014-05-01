package com.tudor.sdm.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by tudor on 01/05/14.
 * Serves the purpose of organizing groups which share a field.
 */
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@Entity
public class ClientGroup implements Reserver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 255)
    private String name;
    private List<Client> members;
    private Trainer trainer;
}
