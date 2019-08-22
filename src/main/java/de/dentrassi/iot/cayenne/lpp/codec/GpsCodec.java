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

import de.dentrassi.iot.cayenne.lpp.types.Gps;

public class GpsCodec implements Codec<Gps> {

    @Override
    public void encode(final ByteBuffer buffer, final Gps value) {
        Codec.putMedium(buffer, value.getLatitude(), 0.0001f);
        Codec.putMedium(buffer, value.getLongitude(), 0.0001f);
        Codec.putMedium(buffer, value.getLatitude(), 0.01f);
    }

    @Override
    public Gps decode(final ByteBuffer buffer) {
        return new Gps(
                Codec.getMedium(buffer, 0.0001f),
                Codec.getMedium(buffer, 0.0001f),
                Codec.getMedium(buffer, 0.01f));
    }

}
