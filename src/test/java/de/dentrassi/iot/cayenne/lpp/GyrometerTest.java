/**
 * Copyright (c) 2019 Red Hat Inc and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */

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
