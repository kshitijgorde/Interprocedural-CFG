// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.query;

import com.stonewall.cornerstone.entity.Host;
import com.stonewall.cornerstone.tp.graph.Interface;
import com.stonewall.cornerstone.tp.query.GraphQuery;
import com.stonewall.cornerstone.entity.util.IpAddr;
import java.util.Iterator;
import java.util.Collection;
import com.stonewall.cornerstone.tp.query.NetQuery;
import java.util.ArrayList;
import com.stonewall.cornerstone.entity.Network;
import java.util.List;

public class NetworkQuery extends Query
{
    public List<Network> getAssociated(final String ifaceId) {
        final List<Network> networks = new ArrayList<Network>();
        final NetQuery netQuery = new NetQuery();
        final Collection<com.stonewall.cornerstone.tp.graph.Network> connected = netQuery.getDirectlyLinked(ifaceId);
        for (final com.stonewall.cornerstone.tp.graph.Network n : connected) {
            networks.add(this.network(n));
        }
        return networks;
    }
    
    public Network findByAddress(final IpAddr addr) {
        final NetQuery netQuery = new NetQuery();
        final com.stonewall.cornerstone.tp.graph.Network n = netQuery.findByAddress(addr);
        if (n == null) {
            return null;
        }
        return this.network(n);
    }
    
    public List<Network> directlyConnected(final String ifaceId) {
        final Interface iface = new GraphQuery().getInterface(ifaceId);
        final Collection<com.stonewall.cornerstone.tp.graph.Network> nets = iface.getDirectNetworks();
        final List<Network> networks = new ArrayList<Network>();
        for (final com.stonewall.cornerstone.tp.graph.Network n : nets) {
            networks.add(this.network(n));
        }
        return networks;
    }
    
    public List<String> getChildrenIds(final String id) {
        final List<String> ids = new ArrayList<String>();
        final com.stonewall.cornerstone.tp.graph.Network n = new GraphQuery().getNetwork(id);
        for (final com.stonewall.cornerstone.tp.graph.Network child : n.getChildren()) {
            ids.add(child.getId());
        }
        return ids;
    }
    
    public Network findContaining(final Network child) {
        return null;
    }
    
    public Network findContaining(final Host host) {
        return null;
    }
}
