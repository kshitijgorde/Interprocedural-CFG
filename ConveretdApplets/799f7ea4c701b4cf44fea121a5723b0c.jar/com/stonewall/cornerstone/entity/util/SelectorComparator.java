// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.util;

import com.stonewall.cornerstone.entity.policy.EndpointComparator;
import com.stonewall.cornerstone.entity.policy.Selector;

public interface SelectorComparator
{
    boolean contains(final Selector p0, final Selector p1);
    
    boolean equals(final Selector p0, final Selector p1);
    
    void setEndpointComparator(final EndpointComparator p0);
}
