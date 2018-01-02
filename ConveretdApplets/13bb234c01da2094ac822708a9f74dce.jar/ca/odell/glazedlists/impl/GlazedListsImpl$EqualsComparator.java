// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl;

import java.util.Comparator;

class GlazedListsImpl$EqualsComparator implements Comparator
{
    public int compare(final Object o, final Object o2) {
        return ((o == null) ? (o2 == null) : o.equals(o2)) ? 0 : 1;
    }
}
