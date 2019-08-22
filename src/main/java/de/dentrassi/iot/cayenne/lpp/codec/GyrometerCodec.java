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

import de.dentrassi.iot.cayenne.lpp.types.Gyrometer;

public class GyrometerCodec implements Codec<Gyrometer> {

    @Override
    public void encode(final ByteBuffer buffer, final Gyrometer value) {
        Codec.putShort(buffer, value.getX(), 0.01f);
        Codec.putShort(buffer, value.getY(), 0.01f);
        Codec.putShort(buffer, value.getZ(), 0.01f);
    }

    @Override
    public Gyrometer decode(final ByteBuffer buffer) {
        return new Gyrometer(
                Codec.getShort(buffer, 0.01f),
                Codec.getShort(buffer, 0.01f),
                Codec.getShort(buffer, 0.01f));
    }

}
