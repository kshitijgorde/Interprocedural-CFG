// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser;

import java.util.Iterator;
import java.util.HashMap;

public class Dictionary<V> extends HashMap<String, V>
{
    static final long serialVersionUID = 0L;
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("(\n");
        for (final String k : this.keySet()) {
            sb.append('\t');
            sb.append(k);
            sb.append('=');
            sb.append(this.get(k));
            sb.append('\n');
        }
        sb.append(')');
        return sb.toString();
    }
    
    public V get(final String k, final V d) {
        final V v = super.get(k);
        return (v != null) ? v : d;
    }
}
