// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.graph;

import java.util.Iterator;
import com.stonewall.cornerstone.entity.util.IpAddr;

public interface NetObject extends Iterable<NetObject>
{
    String getId();
    
    IpAddr getAddress();
    
    boolean contains(final IpAddr p0);
    
    boolean isAny();
    
    boolean isHost();
    
    Iterator<NetObject> iterator();
    
    GraphObject.T type();
}
