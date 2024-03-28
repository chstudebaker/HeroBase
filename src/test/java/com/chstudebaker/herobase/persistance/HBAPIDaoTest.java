package com.chstudebaker.herobase.persistance;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HBAPIDaoTest {

    @Test
    void getPlanetSuccess() {
        HBAPIDao dao = new HBAPIDao();
        assertEquals("Windchild", dao.getHero().getCodeName());

    }
}