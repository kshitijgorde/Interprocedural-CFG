// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.graph;

import com.stonewall.cornerstone.entity.util.IpAddrRange;
import java.util.Collection;
import java.util.Iterator;
import com.stonewall.cornerstone.entity.util.IpAddr;
import java.util.ArrayList;
import org.xmodel.log.Log;
import java.util.List;

public class AddressRange extends GraphObject implements NetObject
{
    private NetObject start;
    private NetObject end;
    private List<NetObject> expanded;
    static final Log log;
    
    static {
        log = Log.getLog(AddressRange.class);
    }
    
    public AddressRange(final String id, final NetObject start, final NetObject end) {
        super(id, T.AddressRange);
        this.start = null;
        this.end = null;
        this.expanded = new ArrayList<NetObject>();
        this.start = start;
        this.end = end;
        this.expand();
    }
    
    public AddressRange(final NetObject start, final NetObject end) {
        this("anonymous", start, end);
    }
    
    public void update(final NetObject start, final NetObject end) {
        this.lock(Mutex.LM.Write);
        try {
            this.start = start;
            this.end = end;
            this.expand();
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
    }
    
    @Override
    public IpAddr getAddress() {
        return this.expanded.get(0).getAddress();
    }
    
    public NetObject start() {
        return this.start;
    }
    
    public NetObject end() {
        return this.end;
    }
    
    @Override
    public Iterator<NetObject> iterator() {
        final List<NetObject> list = new ArrayList<NetObject>();
        this.lock(Mutex.LM.Read);
        try {
            list.addAll(this.expanded);
        }
        finally {
            this.unlock(Mutex.LM.Read);
        }
        this.unlock(Mutex.LM.Read);
        return list.iterator();
    }
    
    public int size() {
        return this.expanded.size();
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
    public boolean contains(final IpAddr a) {
        boolean result = false;
        this.lock(Mutex.LM.Read);
        try {
            for (final NetObject m : this.expanded) {
                final IpAddr ip = m.getAddress();
                if (ip.contains(a)) {
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
    public String toString() {
        final StringBuilder sb = new StringBuilder("[");
        for (final NetObject m : this) {
            sb.append(m.getAddress());
            sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }
    
    private void expand() {
        this.lock(Mutex.LM.Write);
        try {
            this.expanded.clear();
            final IpAddrRange range = new IpAddrRange(this.start().getAddress(), this.end().getAddress());
            for (final IpAddr a : range.getAddresses()) {
                this.expanded.add(new InetAddress(a));
            }
            final int n = this.expanded.size();
            if (n > 255) {
                AddressRange.log.warn("range: " + this.id + " may be incorrectly bounded, len=" + n);
            }
        }
        catch (Exception e) {
            AddressRange.log.error("start: " + this.start + " end: " + this.end, e);
            this.expanded = new ArrayList<NetObject>();
            return;
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
    }
}
