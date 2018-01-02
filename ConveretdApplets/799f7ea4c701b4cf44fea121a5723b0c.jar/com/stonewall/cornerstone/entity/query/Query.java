// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.query;

import com.stonewall.cornerstone.entity.IPInterface;
import com.stonewall.cornerstone.tp.graph.Interface;
import com.stonewall.cornerstone.tp.graph.Host;
import com.stonewall.cornerstone.tp.graph.Network;
import org.xmodel.log.Log;

public class Query
{
    protected static final Log log;
    
    static {
        log = Log.getLog(Query.class);
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
