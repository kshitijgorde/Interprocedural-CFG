// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.graph;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import com.stonewall.cornerstone.entity.util.IpAddr;

public abstract class Endpoint extends GraphObject implements NetObject
{
    protected IpAddr address;
    protected final Map<String, Link> links;
    protected static List<IpAddr> emptyAddresses;
    
    static {
        Endpoint.emptyAddresses = Collections.emptyList();
    }
    
    Endpoint(final String id, final T type, final IpAddr address) {
        super(id, type);
        this.address = null;
        this.links = new HashMap<String, Link>();
        this.setAddress(address);
    }
    
    Endpoint(final Endpoint e) {
        super(e);
        this.address = null;
        this.links = new HashMap<String, Link>();
        this.address = e.address;
        this.lock(Mutex.LM.Write);
        try {
            this.links.putAll(e.links);
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
    }
    
    @Override
    public String name() {
        return (this.address == null) ? this.id : this.address.toString();
    }
    
    @Override
    public IpAddr getAddress() {
        return this.address;
    }
    
    public void setAddress(final IpAddr address) {
        if (this.graph != null) {
            this.graph.unmap(this.address, this);
        }
        this.address = address;
        if (this.graph != null) {
            this.graph.map(this);
        }
    }
    
    @Override
    public String toString() {
        this.lock(Mutex.LM.Read);
        try {
            return String.valueOf(super.toString()) + " [" + this.address + "] " + this.links.values();
        }
        finally {
            this.unlock(Mutex.LM.Read);
        }
    }
    
    @Override
    public boolean contains(final IpAddr a) {
        return this.address != null && this.address.contains(a);
    }
    
    public abstract boolean contains(final Endpoint p0);
    
    public abstract GraphObject getParent();
    
    @Override
    public boolean isAny() {
        return this.address != null && this.address.isAny();
    }
    
    @Override
    public boolean isHost() {
        return this.address != null && this.address.isHost();
    }
    
    public Collection<Link> getLinks() {
        final List<Link> list = new ArrayList<Link>();
        this.lock(Mutex.LM.Read);
        try {
            list.addAll(this.links.values());
        }
        finally {
            this.unlock(Mutex.LM.Read);
        }
        this.unlock(Mutex.LM.Read);
        return list;
    }
    
    void addLink(final Link l) {
        this.links.put(l.getId(), l);
    }
    
    void deleteLink(final String id) {
        this.links.remove(id);
    }
    
    Link getLink(final String id) {
        return this.links.get(id);
    }
    
    Collection<Link> enabledLinks() {
        final List<Link> list = new ArrayList<Link>();
        for (final Link l : this.links.values()) {
            if (l.enabled()) {
                list.add(l);
            }
        }
        return list;
    }
    
    Collection<Link> links() {
        return this.links.values();
    }
    
    int getLinkCount() {
        int result = 0;
        for (final Link l : this.links.values()) {
            if (l.enabled()) {
                ++result;
            }
        }
        return result;
    }
    
    Collection<Link> getLinks(final Link except) {
        if (except == null) {
            return this.getLinks();
        }
        final List<Link> list = new ArrayList<Link>();
        for (final Link l : this.getLinks()) {
            if (l.getId() != except.getId()) {
                list.add(l);
            }
        }
        return list;
    }
    
    boolean connected() {
        boolean result = false;
        for (final Link l : this.links.values()) {
            if (l.enabled()) {
                result = true;
                break;
            }
        }
        return result;
    }
    
    boolean connected(final Endpoint e) {
        boolean result = false;
        for (final Link l : this.links.values()) {
            if (l.enabled() && l.hasEndpoint(e)) {
                result = true;
                break;
            }
        }
        return result;
    }
    
    boolean indirectlyConnected(final Endpoint e) {
        boolean result = false;
        for (final Link l : this.links.values()) {
            if (l.enabled() && (l.hasEndpoint(e) || l.hasParentOf(e))) {
                result = true;
                break;
            }
        }
        return result;
    }
}
