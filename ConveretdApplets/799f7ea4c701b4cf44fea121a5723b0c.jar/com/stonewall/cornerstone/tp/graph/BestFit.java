// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.graph;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import java.util.Collection;
import com.stonewall.cornerstone.entity.util.IpAddr;
import com.stonewall.cornerstone.dsort.SortedCollection;

public class BestFit
{
    private final SortedCollection<Network> networks;
    
    public BestFit(final SortedCollection<Network> networks) {
        this.networks = networks;
    }
    
    public Collection<Network> networksContaining(final IpAddr a) {
        final Set<Network> result = new HashSet<Network>();
        for (final Network n : this.networks) {
            if (n.contains(a)) {
                if (n.connected()) {
                    result.add(n);
                }
                for (final Network ancestor : n.getAncestors()) {
                    if (ancestor.connected()) {
                        result.add(ancestor);
                    }
                }
                for (final Network child : n.getChildren()) {
                    if (child.connected() && child.containedBy(a)) {
                        result.add(child);
                    }
                }
                break;
            }
        }
        return result;
    }
    
    public void sort() {
        this.networks.sort(new Sort<Object>());
    }
    
    class Sort<E> implements Comparator<Network>
    {
        @Override
        public int compare(final Network na, final Network nb) {
            final IpAddr a = na.getAddress();
            final IpAddr b = nb.getAddress();
            if (a == null || b == null) {
                return 0;
            }
            if (b.isInternet() || b.contains(a)) {
                return -1;
            }
            if (a.isInternet() || a.contains(b)) {
                return 1;
            }
            return 0;
        }
    }
}
