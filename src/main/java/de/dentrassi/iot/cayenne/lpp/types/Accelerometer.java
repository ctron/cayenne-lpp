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

public class Accelerometer {
    private final float x;
    private final float y;
    private final float z;

    public Accelerometer(final float x, final float y, final float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getZ() {
        return this.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y, this.z);
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
        final Accelerometer other = (Accelerometer) obj;

        return this.x - other.x < 0.01 &&
                this.y - other.y < 0.01 &&
                this.z - other.z < 0.01;

    }

    @Override
    public String toString() {
        return String.format("[Accelerometer: x: %s, y: %s, z: %s]", this.x, this.y, this.z);
    }
}
