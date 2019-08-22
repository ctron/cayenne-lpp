/**
 * Copyright (c) 2019 Red Hat Inc and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package de.dentrassi.iot.cayenne.lpp.codec;

import java.nio.ByteBuffer;

public interface Codec<T> {
    void encode(ByteBuffer buffer, T value);

    T decode(ByteBuffer buffer);

    static void putShort(final ByteBuffer buffer, final float value, final float factor) {
        final int v = (int) (value / factor);
        buffer.put((byte) (v >> 8));
        buffer.put((byte) v);
    }

    static float getShort(final ByteBuffer buffer, final float factor) {
        final byte b1 = buffer.get();
        final byte b2 = buffer.get();

        final int value = b1 << 8 | b2 & 0xFF;

        return value * factor;
    }

    static void putMedium(final ByteBuffer buffer, final float value, final float factor) {
        final int v = (int) (value / factor);
        buffer.put((byte) (v >> 16));
        buffer.put((byte) (v >> 8));
        buffer.put((byte) v);
    }

    static float getMedium(final ByteBuffer buffer, final float factor) {
        final byte b1 = buffer.get();
        final byte b2 = buffer.get();
        final byte b3 = buffer.get();

        final int value = b1 << 16 | (b2 & 0xFF) << 8 | b3 & 0xFF;

        return value * factor;
    }
}
