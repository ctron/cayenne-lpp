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

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import de.dentrassi.iot.cayenne.lpp.codec.AccelerometerCodec;
import de.dentrassi.iot.cayenne.lpp.codec.Codec;
import de.dentrassi.iot.cayenne.lpp.codec.GpsCodec;
import de.dentrassi.iot.cayenne.lpp.codec.GyrometerCodec;
import de.dentrassi.iot.cayenne.lpp.codec.SimpleCodec;
import de.dentrassi.iot.cayenne.lpp.types.AnalogInput;
import de.dentrassi.iot.cayenne.lpp.types.AnalogOutput;
import de.dentrassi.iot.cayenne.lpp.types.BarometricPressure;
import de.dentrassi.iot.cayenne.lpp.types.DigitalInput;
import de.dentrassi.iot.cayenne.lpp.types.DigitalOutput;
import de.dentrassi.iot.cayenne.lpp.types.Luminosity;
import de.dentrassi.iot.cayenne.lpp.types.Presence;
import de.dentrassi.iot.cayenne.lpp.types.RelativeHumidity;
import de.dentrassi.iot.cayenne.lpp.types.Temperature;

public final class Parser {

    private final Codec<?>[] typeMap;

    public static class Builder {

        private final Codec<?>[] typeMap = new Codec<?>[255];

        public Builder() {
        }

        public Builder registerType(final byte id, final Codec<?> codec) {
            this.typeMap[Byte.toUnsignedInt(id)] = codec;
            return this;
        }

        public Parser build() {
            return new Parser(this.typeMap);
        }
    }

    private static final Parser DEFAULT_PARSER;

    static {
        DEFAULT_PARSER = new Builder()

                .registerType((byte) 0,

                        new SimpleCodec<>(
                                ByteBuffer::get, DigitalInput::new,
                                ByteBuffer::put, DigitalInput::getValue))

                .registerType((byte) 1,

                        new SimpleCodec<>(
                                ByteBuffer::get, DigitalOutput::new,
                                ByteBuffer::put, DigitalOutput::getValue))

                .registerType((byte) 2,

                        new SimpleCodec<>(
                                b -> Codec.getShort(b, 0.01f), AnalogInput::new,
                                (b, v) -> Codec.putShort(b, v, 0.01f), AnalogInput::getValue))

                .registerType((byte) 3,

                        new SimpleCodec<>(
                                b -> Codec.getShort(b, 0.01f), AnalogOutput::new,
                                (b, v) -> Codec.putShort(b, v, 0.01f), AnalogOutput::getValue))

                .registerType((byte) 101,

                        new SimpleCodec<>(
                                b -> Short.toUnsignedInt(b.getShort()), Luminosity::new,
                                (b, v) -> b.putShort((short) v.intValue()), Luminosity::getValue))

                .registerType((byte) 102,

                        new SimpleCodec<>(ByteBuffer::get, Presence::new,
                                ByteBuffer::put, Presence::getValue))

                .registerType((byte) 103,

                        new SimpleCodec<>(b -> Codec.getShort(b, 0.1f), Temperature::new,
                                (b, v) -> Codec.putShort(b, v, 0.1f), Temperature::getValue))

                .registerType((byte) 104,

                        new SimpleCodec<>(b -> Codec.getByte(b, 0.5f), RelativeHumidity::new,
                                (b, v) -> Codec.putByte(b, v, 0.5f), RelativeHumidity::getValue))

                .registerType((byte) 113, new AccelerometerCodec())

                .registerType((byte) 115,

                        new SimpleCodec<>(b -> Codec.getShort(b, 0.1f), BarometricPressure::new,
                                (b, v) -> Codec.putShort(b, v, 0.1f), BarometricPressure::getValue))

                .registerType((byte) 134, new GyrometerCodec())

                .registerType((byte) 136, new GpsCodec())

                .build();
    }

    public static Parser defaultParser() {
        return DEFAULT_PARSER;
    }

    private Parser(final Codec<?>[] typeMap) {
        this.typeMap = typeMap;
    }

    private Entry parseNext(final ByteBuffer buffer) {
        final byte channel = buffer.get();
        final byte id = buffer.get();
        final Codec<?> codec = this.typeMap[Byte.toUnsignedInt(id)];
        if (codec == null) {
            throw new IllegalArgumentException(String.format("Unknown object type: %02x", id));
        }
        return new Entry(channel, codec.decode(buffer));
    }

    public Stream<Entry> decode(final ByteBuffer buffer) {
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(
                        toIterator(buffer.order(ByteOrder.BIG_ENDIAN)),
                        Spliterator.ORDERED | Spliterator.IMMUTABLE | Spliterator.NONNULL),
                false);
    }

    private Iterator<Entry> toIterator(final ByteBuffer buffer) {
        return new Iterator<Entry>() {

            @Override
            public Entry next() {
                return parseNext(buffer);
            }

            @Override
            public boolean hasNext() {
                return buffer.hasRemaining();
            }
        };
    }

    public static Stream<Entry> stream(final ByteBuffer buffer) {
        return DEFAULT_PARSER.decode(buffer);
    }

    public static Message parseMessage(final ByteBuffer buffer) {
        return new Message(stream(buffer).collect(Collectors.toList()));
    }
}
