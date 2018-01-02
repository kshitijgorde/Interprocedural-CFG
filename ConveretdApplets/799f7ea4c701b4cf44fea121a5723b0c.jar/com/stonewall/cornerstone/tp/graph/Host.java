// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.graph;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import com.stonewall.cornerstone.entity.util.IpAddr;

public class Host extends Endpoint
{
    Network parent;
    
    public Host(final String id, final IpAddr address) {
        super(id, T.Host, address);
        this.parent = null;
    }
    
    public Host(final String id, final IpAddr address, final Network parent) {
        this(id, address);
        this.setParent(parent);
    }
    
    @Override
    public Network getParent() {
        this.lock(Mutex.LM.Read);
        try {
            return this.parent;
        }
        finally {
            this.unlock(Mutex.LM.Read);
        }
    }
    
    public void setParent(final Network parent) {
        this.lock(Mutex.LM.Write);
        try {
            this.unparent();
            (this.parent = parent).addHost(this);
            if (this.graph != null) {
                this.graph.addLink(new Link(this.id, this, parent));
            }
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
    }
    
    @Override
    public boolean contains(final Endpoint e) {
        return false;
    }
    
    @Override
    public Iterator<NetObject> iterator() {
        final List<NetObject> list = new ArrayList<NetObject>(1);
        list.add(this);
        return list.iterator();
    }
    
    private void unparent() {
        if (this.graph != null) {
            this.graph.deleteLink(this.id);
        }
        if (this.parent != null) {
            this.parent.deleteHost(this);
        }
    }
}
