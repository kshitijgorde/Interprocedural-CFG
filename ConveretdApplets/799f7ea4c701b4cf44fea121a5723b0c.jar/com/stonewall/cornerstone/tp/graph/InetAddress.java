// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.graph;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import com.stonewall.cornerstone.entity.util.IpAddr;

public class InetAddress implements NetObject
{
    private final IpAddr address;
    
    public static InetAddress any() {
        return new InetAddress(IpAddr.ipAny());
    }
    
    @Override
    public String getId() {
        return "anonymous";
    }
    
    public InetAddress(final IpAddr address) {
        this.address = address;
    }
    
    public InetAddress(final String address) throws Exception {
        this.address = new IpAddr(address);
    }
    
    @Override
    public IpAddr getAddress() {
        return this.address;
    }
    
    @Override
    public boolean contains(final IpAddr a) {
        return this.address.contains(a);
    }
    
    @Override
    public boolean isAny() {
        return this.address.isAny();
    }
    
    @Override
    public boolean isHost() {
        return this.address.isHost();
    }
    
    @Override
    public String toString() {
        return this.address.toString();
    }
    
    @Override
    public Iterator<NetObject> iterator() {
        final List<NetObject> list = new ArrayList<NetObject>(1);
        list.add(this);
        return list.iterator();
    }
    
    @Override
    public GraphObject.T type() {
        return GraphObject.T.None;
    }
    
    public int size() {
        return 1;
    }
}
