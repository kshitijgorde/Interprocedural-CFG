// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy.security;

import com.stonewall.cornerstone.entity.ServiceSet;
import com.stonewall.cornerstone.entity.policy.Endpoint;
import com.stonewall.cornerstone.entity.policy.Selector;
import com.stonewall.cornerstone.entity.policy.EndpointComparator;
import com.stonewall.cornerstone.entity.util.SelectorComparator;

public class VPNSelectorComparator implements SelectorComparator
{
    private EndpointComparator comparator;
    
    @Override
    public boolean contains(final Selector s1, final Selector s2) {
        final Endpoint src = s1.getSource();
        final Endpoint dst = s1.getDestination();
        if (!(s2 instanceof IPHeader)) {
            return false;
        }
        final Endpoint srcEp = s2.getSource();
        final Endpoint dstEp = s2.getDestination();
        if ((this.comparator.contains(src, srcEp) && this.comparator.contains(dst, dstEp)) || (this.comparator.contains(dst, srcEp) && this.comparator.contains(src, dstEp))) {
            final ServiceSet set1 = ((IPHeader)s1).getServiceSet();
            final ServiceSet set2 = ((IPHeader)s2).getServiceSet();
            return set1.contains(set2);
        }
        return false;
    }
    
    @Override
    public boolean equals(final Selector s1, final Selector s2) {
        final Endpoint src = s1.getSource();
        final Endpoint dst = s1.getDestination();
        if (!(s2 instanceof IPHeader)) {
            return false;
        }
        final Endpoint srcEp = s2.getSource();
        final Endpoint dstEp = s2.getDestination();
        if ((src.equals(srcEp) && dst.equals(dstEp)) || (dst.equals(srcEp) && src.equals(dstEp))) {
            final ServiceSet set1 = ((IPHeader)s1).getServiceSet();
            final ServiceSet set2 = ((IPHeader)s2).getServiceSet();
            return set1.equals(set2);
        }
        return false;
    }
    
    @Override
    public void setEndpointComparator(final EndpointComparator comparator) {
        this.comparator = comparator;
    }
}
