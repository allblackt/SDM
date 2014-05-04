package com.tudor.sdm.dao;

import com.tudor.sdm.entity.Trainer;

/**
 * Created by tudor on 04/05/14.
 * DAO to manage the trainers.
 */
public class TrainerDAO extends DAO<Trainer, Long> {

    private static TrainerDAO instance = null;

    protected TrainerDAO(Class<Trainer> clazz) {
        super(clazz);
    }

    public static TrainerDAO get() {
        if (instance == null) {
            instance = new TrainerDAO(Trainer.class);
        }
        return instance;
    }
}
