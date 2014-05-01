package com.tudor.sdm.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by tudor on 01/05/14.
 * A trainer/lead for a group.
 */
@Entity
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class Trainer implements Reserver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100)
    private String name;

    @Column(length = 100)
    private String phoneNumber;
}
