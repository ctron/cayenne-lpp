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

import de.dentrassi.iot.cayenne.lpp.types.Accelerometer;

public class AccelerometerCodec implements Codec<Accelerometer> {

    @Override
    public void encode(final ByteBuffer buffer, final Accelerometer value) {
        Codec.putShort(buffer, value.getX(), 0.001f);
        Codec.putShort(buffer, value.getY(), 0.001f);
        Codec.putShort(buffer, value.getZ(), 0.001f);
    }

    @Override
    public Accelerometer decode(final ByteBuffer buffer) {
        return new Accelerometer(
                Codec.getShort(buffer, 0.001f),
                Codec.getShort(buffer, 0.001f),
                Codec.getShort(buffer, 0.001f));
    }

}
