// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.graph;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collection;
import com.stonewall.cornerstone.entity.util.IpAddr;

public class Interface extends Endpoint
{
    private String identifier;
    private Node node;
    
    public Interface(final String id, final IpAddr address) {
        super(id, T.Interface, address);
        this.identifier = null;
        this.node = null;
    }
    
    @Override
    public String name() {
        return (this.identifier == null) ? super.name() : this.identifier;
    }
    
    public String getIdentifier() {
        return this.identifier;
    }
    
    public void setIdentifier(final String v) {
        this.identifier = v;
    }
    
    public Node getNode() {
        return this.node;
    }
    
    public Collection<Network> getDirectNetworks() {
        final Collection<Network> result = new ArrayList<Network>();
        this.lock(Mutex.LM.Read);
        try {
            for (final Link l : this.enabledLinks()) {
                final Endpoint farEnd = l.otherEnd(this);
                if (farEnd.type(T.Network)) {
                    result.add((Network)farEnd);
                }
            }
        }
        finally {
            this.unlock(Mutex.LM.Read);
        }
        this.unlock(Mutex.LM.Read);
        return result;
    }
    
    @Override
    public boolean contains(final Endpoint e) {
        return false;
    }
    
    @Override
    public GraphObject getParent() {
        return this.node;
    }
    
    @Override
    public Iterator<NetObject> iterator() {
        final List<NetObject> list = new ArrayList<NetObject>(1);
        list.add(this);
        return list.iterator();
    }
    
    void setNode(final Node n) {
        this.node = n;
    }
}
