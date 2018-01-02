// 
// Decompiled by Procyon v0.5.30
// 

package com.stackframe.pathfinder;

import java.util.Map;
import com.google.common.collect.Ordering;

class Utilities
{
    static <K, V extends Comparable<V>> Ordering<Map.Entry<K, V>> orderByEntryValue() {
        return new Ordering<Map.Entry<K, V>>() {
            public int compare(final Map.Entry<K, V> o1, final Map.Entry<K, V> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        };
    }
}
