// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.graph;

import java.util.Iterator;
import java.util.Collection;
import com.stonewall.cornerstone.entity.util.IpAddr;
import java.util.ArrayList;
import java.util.List;

public class ObjectGroup extends GraphObject implements NetObject
{
    protected final List<NetObject> members;
    
    public ObjectGroup() {
        super("anonymous", T.ObjectGroup);
        this.members = new ArrayList<NetObject>();
    }
    
    public ObjectGroup(final String id) {
        super(id, T.ObjectGroup);
        this.members = new ArrayList<NetObject>();
    }
    
    public ObjectGroup(final IpAddr a) {
        this();
        this.members.add(new InetAddress(a));
    }
    
    public ObjectGroup(final NetObject n) {
        this();
        this.members.add(n);
    }
    
    public ObjectGroup(final String id, final IpAddr a) {
        this(id);
        this.members.add(new InetAddress(a));
    }
    
    public ObjectGroup(final String id, final NetObject n) {
        this(id);
        this.members.add(n);
    }
    
    public ObjectGroup(final String id, final Collection<NetObject> list) {
        this(id);
        this.members.addAll(list);
    }
    
    public void add(final IpAddr a) {
        this.add(new InetAddress(a));
    }
    
    public void add(final NetObject n) {
        this.lock(Mutex.LM.Write);
        try {
            this.members.add(n);
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
    }
    
    public void add(final Collection<NetObject> list) {
        this.lock(Mutex.LM.Write);
        try {
            this.members.addAll(list);
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
    }
    
    public void clear() {
        this.lock(Mutex.LM.Write);
        try {
            this.members.clear();
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
    }
    
    public void replaceMembers(final Collection<NetObject> list) {
        this.lock(Mutex.LM.Write);
        try {
            this.members.clear();
            this.members.addAll(list);
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
    }
    
    public void remove(final NetObject n) {
        this.lock(Mutex.LM.Write);
        try {
            this.members.remove(n);
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
    }
    
    @Override
    public boolean contains(final IpAddr a) {
        boolean result = false;
        this.lock(Mutex.LM.Read);
        try {
            for (final NetObject n : this.members) {
                if (n.contains(a)) {
                    result = true;
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
    
    @Override
    public IpAddr getAddress() {
        return this.members.isEmpty() ? null : this.members.get(0).getAddress();
    }
    
    public List<IpAddr> getAddresses() {
        final List<IpAddr> result = new ArrayList<IpAddr>();
        this.lock(Mutex.LM.Read);
        try {
            for (final NetObject n : this.members) {
                result.add(n.getAddress());
            }
        }
        finally {
            this.unlock(Mutex.LM.Read);
        }
        this.unlock(Mutex.LM.Read);
        return result;
    }
    
    @Override
    public boolean isAny() {
        return false;
    }
    
    @Override
    public boolean isHost() {
        return false;
    }
    
    @Override
    public Iterator<NetObject> iterator() {
        final List<NetObject> list = new ArrayList<NetObject>();
        this.lock(Mutex.LM.Read);
        try {
            list.addAll(this.members);
        }
        finally {
            this.unlock(Mutex.LM.Read);
        }
        this.unlock(Mutex.LM.Read);
        return list.iterator();
    }
    
    public int size() {
        return this.members.size();
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("[");
        for (final NetObject m : this) {
            sb.append(m.getAddress());
            sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }
}
