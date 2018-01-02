// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.graph;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.HashSet;
import com.stonewall.cornerstone.entity.util.IpAddr;
import java.util.Set;

public class Network extends Endpoint
{
    Network parent;
    private boolean _public;
    private boolean managed;
    private final Set<Network> children;
    private final Set<Host> hosts;
    
    public Network(final String id, final IpAddr address) {
        super(id, T.Network, address);
        this.parent = null;
        this._public = true;
        this.managed = true;
        this.children = new HashSet<Network>();
        this.hosts = new HashSet<Host>();
        this._public = this.hasPublicAddress();
    }
    
    protected Network(final Network n) {
        super(n);
        this.parent = null;
        this._public = true;
        this.managed = true;
        this.children = new HashSet<Network>();
        this.hosts = new HashSet<Host>();
        this.parent = n.parent;
        this.lock(Mutex.LM.Write);
        try {
            this.children.addAll(n.children);
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
    }
    
    public Network setParent(final Network parent) {
        this.lock(Mutex.LM.Write);
        try {
            this.unparent();
            (this.parent = parent).addChild(this);
            return this;
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
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
    
    public boolean managed() {
        return this.managed;
    }
    
    public boolean unmanaged() {
        return !this.managed;
    }
    
    public void setManaged(final boolean managed) {
        this.managed = managed;
    }
    
    public void setPublic(final boolean flag) {
        this._public = flag;
    }
    
    public boolean isPublic() {
        return this._public;
    }
    
    public boolean isPrivate() {
        return !this._public;
    }
    
    public boolean hasPublicAddress() {
        return this.addressed() && this.address.isPublic();
    }
    
    public boolean hasPrivateAddress() {
        return this.addressed() && this.address.isPrivate();
    }
    
    public boolean addressed() {
        return this.address != null;
    }
    
    @Override
    public boolean contains(final IpAddr a) {
        return this.addressed() && this.address.contains(a);
    }
    
    public boolean match(final IpAddr a) {
        return this.addressed() && this.address.bidirectionallyContains(a);
    }
    
    public boolean containedBy(final IpAddr a) {
        return this.addressed() && a.contains(this.address);
    }
    
    public void addChild(final Network n) {
        this.lock(Mutex.LM.Write);
        try {
            n.parent = this;
            this.children.add(n);
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
    }
    
    public void deleteChild(final Network n) {
        this.lock(Mutex.LM.Write);
        try {
            this.children.remove(n);
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
    }
    
    public List<Network> getAncestors() {
        final List<Network> result = new ArrayList<Network>();
        this.lock(Mutex.LM.Read);
        try {
            for (Network p = this.parent; p != null; p = p.getParent()) {
                result.add(p);
            }
        }
        finally {
            this.unlock(Mutex.LM.Read);
        }
        this.unlock(Mutex.LM.Read);
        return result;
    }
    
    public Collection<Network> getChildren() {
        final List<Network> result = new ArrayList<Network>();
        this.lock(Mutex.LM.Read);
        try {
            this.getChildren(result);
        }
        finally {
            this.unlock(Mutex.LM.Read);
        }
        this.unlock(Mutex.LM.Read);
        return result;
    }
    
    public void addHost(final Host host) {
        this.lock(Mutex.LM.Write);
        try {
            this.hosts.add(host);
            host.parent = this;
            if (this.graph != null) {
                this.graph.added(host);
            }
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
    }
    
    public void deleteHost(final Host host) {
        this.lock(Mutex.LM.Write);
        try {
            this.hosts.remove(host);
            if (this.graph != null) {
                this.graph.removed(host);
            }
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
    }
    
    public Collection<Host> getHosts() {
        final List<Host> result = new ArrayList<Host>();
        this.lock(Mutex.LM.Read);
        try {
            result.addAll(this.hosts);
        }
        finally {
            this.unlock(Mutex.LM.Read);
        }
        this.unlock(Mutex.LM.Read);
        return result;
    }
    
    @Override
    public boolean contains(final Endpoint e) {
        boolean result = false;
        this.lock(Mutex.LM.Read);
        try {
            switch (e.type) {
                case Network: {
                    final Network net = (Network)e;
                    final Network parent = net.getParent();
                    result = (parent == this);
                    if (!result) {
                        result = this.hasDecendant(parent);
                        break;
                    }
                    break;
                }
                case Host: {
                    final Host host = (Host)e;
                    final Network parent = host.getParent();
                    result = (parent == this);
                    if (!result) {
                        result = this.hasDecendant(parent);
                        break;
                    }
                    break;
                }
            }
        }
        finally {
            this.unlock(Mutex.LM.Read);
        }
        this.unlock(Mutex.LM.Read);
        return result;
    }
    
    public boolean hasDecendant(final Network n) {
        boolean result = false;
        for (final Network child : this.children) {
            if (child == n) {
                result = true;
                break;
            }
        }
        return result;
    }
    
    @Override
    public Iterator<NetObject> iterator() {
        final List<NetObject> list = new ArrayList<NetObject>(1);
        list.add(this);
        return list.iterator();
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        this.lock(Mutex.LM.Read);
        try {
            if (this.parent != null) {
                sb.append(", parent: ");
                sb.append(this.parent.getId());
            }
            for (final Host h : this.hosts) {
                sb.append("\n\t\t( ");
                sb.append(h);
                sb.append(" )");
            }
        }
        finally {
            this.unlock(Mutex.LM.Read);
        }
        this.unlock(Mutex.LM.Read);
        return sb.toString();
    }
    
    Collection<Network> directChildren() {
        return this.children;
    }
    
    Collection<Host> hosts() {
        return this.hosts;
    }
    
    @Override
    protected void added(final Graph g) {
        for (final Network n : this.children) {
            g.added(n);
        }
        for (final Host h : this.hosts) {
            g.added(h);
        }
    }
    
    @Override
    protected void removed(final Graph g) {
        for (final Network n : this.children) {
            g.removed(n);
        }
        for (final Host h : this.hosts) {
            g.removed(h);
        }
    }
    
    private void getChildren(final List<Network> result) {
        result.addAll(this.children);
        for (final Network n : this.children) {
            n.getChildren(result);
        }
    }
    
    private void unparent() {
        if (this.parent != null) {
            this.parent.deleteChild(this);
        }
    }
}
