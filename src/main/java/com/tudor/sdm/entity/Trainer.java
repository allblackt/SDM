package com.tudor.sdm.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by tudor on 01/05/14.
 * A trainer/lead for a group.
 */
@Entity
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Trainer implements Reserver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100)
    private String name;

    @Column(length = 100)
    private String phoneNumber;

    private Trainer(Builder builder) {
        setName(builder.name);
        setPhoneNumber(builder.phoneNumber);
    }

    public static final class Builder {
        private String name;
        private String phoneNumber;

        public Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Trainer build() {
            return new Trainer(this);
        }
    }
}
