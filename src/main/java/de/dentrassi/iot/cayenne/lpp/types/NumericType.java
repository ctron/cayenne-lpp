/**
 * Copyright (c) 2019 Red Hat Inc and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package de.dentrassi.iot.cayenne.lpp.types;

import java.util.Objects;

public abstract class NumericType<T extends Number> extends Number {

    private static final long serialVersionUID = 1L;

    private final T value;

    public NumericType(final T value) {
        this.value = Objects.requireNonNull(value);
    }

    public T getValue() {
        return this.value;
    }

    @Override
    public int intValue() {
        return this.value.intValue();
    }

    @Override
    public long longValue() {
        return this.value.longValue();
    }

    @Override
    public float floatValue() {
        return this.value.floatValue();
    }

    @Override
    public double doubleValue() {
        return this.value.doubleValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NumericType<?> other = (NumericType<?>) obj;
        return Objects.equals(this.value, other.value);
    }

    @Override
    public String toString() {
        return String.format("[%s: %s]", getClass().getSimpleName(), this.value);
    }
}
