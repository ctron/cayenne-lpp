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
import java.util.function.BiConsumer;
import java.util.function.Function;

public class SimpleCodec<T> implements Codec<T> {

    private final Function<ByteBuffer, T> getter;
    private final BiConsumer<ByteBuffer, T> setter;

    public SimpleCodec(final Function<ByteBuffer, T> getter, final BiConsumer<ByteBuffer, T> setter) {
        this.getter = getter;
        this.setter = setter;
    }

    public <I> SimpleCodec(final Function<ByteBuffer, I> getter, final Function<I, T> from,
            final BiConsumer<ByteBuffer, I> setter, final Function<T, I> to) {
        this.getter = b -> getter.andThen(from).apply(b);
        this.setter = (b, v) -> setter.accept(b, to.apply(v));
    }

    @Override
    public void encode(final ByteBuffer buffer, final T value) {
        this.setter.accept(buffer, value);
    }

    @Override
    public T decode(final ByteBuffer buffer) {
        return this.getter.apply(buffer);
    }

}
