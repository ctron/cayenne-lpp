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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Message {

    private final List<Entry> entries;
    private final Map<Integer, Entry> map;

    public Message(final Collection<Entry> entries) {
        this.entries = new ArrayList<>(entries);
        this.map = new HashMap<>(entries.size());
        for (final Entry entry : entries) {
            this.map.put(entry.getChannel(), entry);
        }
    }

    public List<Entry> getEntries() {
        return this.entries;
    }

    public Entry getEntry(final int channel) {
        return this.map.get(channel);
    }

    public <T> T getEntry(final int channel, final Class<T> clazz) {

        final Entry entry = getEntry(channel);
        if (entry == null) {
            return null;
        }

        final Object v = entry.getValue();
        if (clazz.isInstance(v)) {
            return clazz.cast(v);
        }

        return null;
    }

}
