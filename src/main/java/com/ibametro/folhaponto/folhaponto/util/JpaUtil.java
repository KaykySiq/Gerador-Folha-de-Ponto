package com.ibametro.folhaponto.folhaponto.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");

    public static EntityManager getEntityManager(){
        return entityManagerFactory.createEntityManager();
    }
}
