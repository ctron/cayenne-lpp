package de.dentrassi.iot.cayenne.lpp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import de.dentrassi.iot.cayenne.lpp.types.Gyrometer;

public class GyrometerTest {

    @Test
    public void testEquals() {
        assertEquals(new Gyrometer(1, 2, 3), new Gyrometer(1, 2, 3));
    }

    @Test
    public void testNotEquals1() {
        assertNotEquals(new Gyrometer(1, 2, 3), new Gyrometer(0, 2, 3));
    }

    @Test
    public void testNotEquals2() {
        assertNotEquals(new Gyrometer(1, 2, 3), new Gyrometer(1, 0, 3));
    }

    @Test
    public void testNotEquals3() {
        assertNotEquals(new Gyrometer(1, 2, 3), new Gyrometer(1, 2, 0));
    }

}
