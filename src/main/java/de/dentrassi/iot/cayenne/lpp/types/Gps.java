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

public class Gps {
    private final float latitude;
    private final float longitude;
    private final float altitude;

    public Gps(final float latitude, final float longitude, final float altitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public float getAltitude() {
        return this.altitude;
    }

    public float getLatitude() {
        return this.latitude;
    }

    public float getLongitude() {
        return this.longitude;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.altitude, this.latitude, this.longitude);
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
        final Gps other = (Gps) obj;

        return this.latitude - other.latitude < 0.0001 &&
                this.longitude - other.longitude < 0.0001 &&
                this.altitude - other.altitude < 0.01;
    }

    @Override
    public String toString() {
        return String.format("[GPS: lat: %s, lan: %s, alt: %s]", this.latitude, this.longitude, this.altitude);
    }

}
