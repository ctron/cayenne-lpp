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

public class Dumper {

    public static void main(final String[] args) {

        System.out.println(Parser.parseMessage(ParserTest.toBuffer("15 65 00 07 16 65 00 00")));

    }

}
