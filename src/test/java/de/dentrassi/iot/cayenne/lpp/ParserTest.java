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

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import de.dentrassi.iot.cayenne.lpp.types.Accelerometer;
import de.dentrassi.iot.cayenne.lpp.types.Gps;
import de.dentrassi.iot.cayenne.lpp.types.Temperature;

public class ParserTest {

    @Test
    public void testGps1() {
        assertParse("01 88 06 76 5f f2 96 0a 00 03 e8", new Entry[] {
                new Entry(1, new Gps(42.3519f, -87.9094f, 10))
        });
    }

    @Test
    public void testTempAndAccel() {
        assertParse("01 67 FF D7 06 71 04 D2 FB 2E 00 00", new Entry[] {
                new Entry(1, new Temperature(-4.1f)),
                new Entry(6, new Accelerometer(1.234f, -1.234f, 0f))
        });
    }

    @Test
    public void testTwoTemps() {
        assertParse("03 67 01 10 05 67 00 FF", new Entry[] {
                new Entry(3, new Temperature(27.2f)),
                new Entry(5, new Temperature(25.5f)),
        });
    }

    protected static void assertParse(final String string, final Entry[] objects) {
        final ByteBuffer buffer = toBuffer(string);
        final List<Entry> actual = Parser.stream(buffer).collect(Collectors.toList());
        assertArrayEquals(objects, actual.toArray(new Entry[actual.size()]));
    }

    public static ByteBuffer toBuffer(final String string) {

        final StringTokenizer tok = new StringTokenizer(string, " ");
        final int len = tok.countTokens();
        final ByteBuffer result = ByteBuffer.allocate(len);

        while (tok.hasMoreTokens()) {
            final String next = tok.nextToken();
            result.put((byte) Integer.parseUnsignedInt(next, 16));
        }

        result.flip();
        return result;

    }

}
