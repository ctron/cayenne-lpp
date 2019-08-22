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

public abstract class SimpleType<T> {

    private final T value;

    public SimpleType(final T value) {
        this.value = Objects.requireNonNull(value);
    }

    public T getValue() {
        return this.value;
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
        final SimpleType<?> other = (SimpleType<?>) obj;
        return Objects.equals(this.value, other.value);
    }

}
