// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.graph;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import com.stonewall.cornerstone.entity.util.IpAddr;

public class Any implements NetObject
{
    public static Any any;
    
    static {
        Any.any = new Any();
    }
    
    @Override
    public String getId() {
        return "ANY";
    }
    
    @Override
    public IpAddr getAddress() {
        return IpAddr.ipAny();
    }
    
    @Override
    public boolean contains(final IpAddr a) {
        return true;
    }
    
    @Override
    public boolean isAny() {
        return true;
    }
    
    @Override
    public boolean isHost() {
        return false;
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
    
    @Override
    public String toString() {
        return "Any";
    }
}
