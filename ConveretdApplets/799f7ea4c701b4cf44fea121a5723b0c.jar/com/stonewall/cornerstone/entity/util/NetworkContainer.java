// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.util;

import java.util.Collection;

public interface NetworkContainer
{
    Collection<IpAddr> getRootNetworks();
    
    boolean containedByAnyNetwork(final IpAddr p0);
    
    boolean notContainedByAnyNetwork(final IpAddr p0);
}
