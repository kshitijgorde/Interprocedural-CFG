// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler.util;

import java.util.Vector;
import java.util.Hashtable;

public final class MultiHashtable extends Hashtable
{
    public Object put(final Object key, final Object value) {
        Vector vector = this.get(key);
        if (vector == null) {
            super.put(key, vector = new Vector());
        }
        vector.add(value);
        return vector;
    }
    
    public Object maps(final Object from, final Object to) {
        if (from == null) {
            return null;
        }
        final Vector vector = this.get(from);
        if (vector != null) {
            for (int n = vector.size(), i = 0; i < n; ++i) {
                final Object item = vector.elementAt(i);
                if (item.equals(to)) {
                    return item;
                }
            }
        }
        return null;
    }
}
