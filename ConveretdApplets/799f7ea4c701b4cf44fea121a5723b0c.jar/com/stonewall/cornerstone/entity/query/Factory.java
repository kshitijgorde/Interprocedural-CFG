// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.query;

import com.stonewall.cornerstone.tp.graph.GraphObject;
import com.stonewall.cornerstone.entity.IPInterface;
import java.util.Iterator;
import com.stonewall.cornerstone.entity.AddressGroup;
import com.stonewall.cornerstone.tp.graph.Interface;
import com.stonewall.cornerstone.tp.graph.Network;
import com.stonewall.cornerstone.tp.graph.Host;
import com.stonewall.cornerstone.tp.graph.AddressRange;
import com.stonewall.cornerstone.tp.graph.ObjectGroup;
import com.stonewall.cornerstone.entity.Entity;
import com.stonewall.cornerstone.tp.graph.NetObject;
import org.xmodel.log.Log;

public class Factory
{
    protected static final Log log;
    
    static {
        log = Log.getLog(Factory.class);
    }
    
    public Entity create(final NetObject no) {
        switch (no.type()) {
            case ObjectGroup: {
                return this.addressGroup((ObjectGroup)no);
            }
            case AddressRange: {
                return this.addressRange((AddressRange)no);
            }
            case Host: {
                return this.host((Host)no);
            }
            case Network: {
                return this.network((Network)no);
            }
            case Interface: {
                return this.iface((Interface)no);
            }
            default: {
                return null;
            }
        }
    }
    
    protected AddressGroup addressGroup(final ObjectGroup og) {
        final AddressGroup ag = new AddressGroup(og.getId());
        if (og.getId().equals("anonymous")) {
            ag.resetIds();
        }
        final Iterator<NetObject> iter = og.iterator();
        while (iter.hasNext()) {
            ag.addPart(this.create(iter.next()).getReference());
        }
        return ag;
    }
    
    protected com.stonewall.cornerstone.entity.AddressRange addressRange(final AddressRange r) {
        final com.stonewall.cornerstone.entity.AddressRange ar = new com.stonewall.cornerstone.entity.AddressRange(r.getId());
        if (r.getId().equals("anonymous")) {
            ar.resetIds();
        }
        ar.setStart(this.create(r.start()).getReference());
        ar.setEnd(this.create(r.end()).getReference());
        return ar;
    }
    
    protected com.stonewall.cornerstone.entity.Network network(final Network n) {
        final com.stonewall.cornerstone.entity.Network net = new com.stonewall.cornerstone.entity.Network(n.getId());
        final Network parent = n.getParent();
        if (parent != null) {
            net.setParentId(parent.getId());
        }
        net.setIpAddress(n.getAddress());
        net.setManaged(n.managed());
        return net;
    }
    
    protected com.stonewall.cornerstone.entity.Host host(final Host h) {
        final com.stonewall.cornerstone.entity.Host host = new com.stonewall.cornerstone.entity.Host(h.getId());
        final Network net = h.getParent();
        host.setParentId(net.getId());
        host.setIpAddress(h.getAddress());
        return host;
    }
    
    protected IPInterface iface(final Interface i) {
        final IPInterface iface = new IPInterface(i.getId());
        iface.setIpAddress(i.getAddress());
        return iface;
    }
}
