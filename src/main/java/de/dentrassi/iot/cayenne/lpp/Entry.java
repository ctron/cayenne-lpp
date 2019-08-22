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

import java.util.Objects;

public class Entry {
    private final int channel;
    private final Object value;

    public Entry(final int channel, final Object value) {
        this.channel = channel;
        this.value = Objects.requireNonNull(value);
    }

    public int getChannel() {
        return this.channel;
    }

    public Object getValue() {
        return this.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.channel, this.value);
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
        final Entry other = (Entry) obj;
        return this.channel == other.channel && Objects.equals(this.value, other.value);
    }

}
