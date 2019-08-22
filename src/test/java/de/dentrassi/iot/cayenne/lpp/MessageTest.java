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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import de.dentrassi.iot.cayenne.lpp.types.Accelerometer;
import de.dentrassi.iot.cayenne.lpp.types.Gps;
import de.dentrassi.iot.cayenne.lpp.types.Temperature;

public class MessageTest {

    @Test
    public void test1() {

        final Message message = Parser.parseMessage(ParserTest.toBuffer("01 67 FF D7 06 71 04 D2 FB 2E 00 00"));

        assertNotNull(message.getEntry(1));
        assertNotNull(message.getEntry(6));
        assertNull(message.getEntry(2));

        assertEquals(new Temperature(-4.1f), message.getEntry(1, Temperature.class));
        assertNull(message.getEntry(1, Gps.class));

        assertEquals(new Accelerometer(1.234f, -1.234f, 0f), message.getEntry(6, Accelerometer.class));
        assertNull(message.getEntry(6, Gps.class));

        assertNull(message.getEntry(2, Gps.class));
    }

    /*
     * assertParse("01 67 FF D7 06 71 04 D2 FB 2E 00 00", new Object[] { new Entry(1, new Temperature(-4.1f)), new
     * Entry(6, new Accelerometer(1.234f, -1.234f, 0f)) });
     */
}
