// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.io.Serializable;
import java.util.Comparator;

class SortString implements Comparator, Serializable
{
    @Override
    public int compare(final Object o, final Object o2) {
        return ((String)o).compareTo((String)o2);
    }
}
