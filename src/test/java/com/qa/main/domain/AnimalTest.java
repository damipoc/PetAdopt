package com.qa.main.domain;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class AnimalTest {

    @Test
    public void testEquals() {
        EqualsVerifier.simple().forClass(Animal.class).verify();
    }
}
