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

public class AnalogOutput extends NumericType<Float> {

    private static final long serialVersionUID = 1L;

    public AnalogOutput(final float value) {
        super(value);
    }

}
