// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.graph;

import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import com.stonewall.cornerstone.entity.util.IpAddr;

class Route
{
    private final IpAddr src;
    private final IpAddr dst;
    private final List<Interface> interfaces;
    
    public Route(final IpAddr src, final IpAddr dst, final List<Interface> intfs) {
        this.interfaces = new ArrayList<Interface>();
        this.src = src;
        this.dst = dst;
        this.interfaces.addAll(intfs);
    }
    
    public Route(final IpAddr src, final IpAddr dst, final Interface intf) {
        this(src, dst, Collections.singletonList(intf));
    }
    
    public boolean matches(final Packet packet) {
        return this.src.contains(packet.src.getAddress()) && this.dst.contains(packet.dst.getAddress());
    }
    
    public List<Interface> interfaces() {
        return this.interfaces;
    }
    
    @Override
    public String toString() {
        return "Route: " + this.src + " --> " + this.dst + " : " + this.interfaces;
    }
}
