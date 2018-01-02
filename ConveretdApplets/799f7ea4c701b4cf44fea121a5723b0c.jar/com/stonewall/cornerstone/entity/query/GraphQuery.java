// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.query;

import com.stonewall.cornerstone.entity.EntityFactory;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Collection;
import com.stonewall.cornerstone.entity.util.IpAddr;
import com.stonewall.cornerstone.tp.graph.Interface;
import com.stonewall.cornerstone.entity.IPInterface;
import com.stonewall.cornerstone.entity.Host;
import com.stonewall.cornerstone.entity.Network;
import com.stonewall.cornerstone.entity.Entity;
import com.stonewall.cornerstone.entity.EntityReference;
import org.xmodel.log.Log;

public class GraphQuery extends Query
{
    static final Log log;
    
    static {
        log = Log.getLog(GraphQuery.class);
    }
    
    public Entity getEntity(final EntityReference ref) {
        switch (ref.getEntityType()) {
            case network: {
                return this.getNetwork(ref.getId());
            }
            case host: {
                return this.getHost(ref.getId());
            }
            case ipInterface: {
                return this.getInterface(ref.getId());
            }
            default: {
                GraphQuery.log.debug("Not valid graph entity: " + ref);
                return null;
            }
        }
    }
    
    public Network getNetwork(final String id) {
        final com.stonewall.cornerstone.tp.query.GraphQuery query = new com.stonewall.cornerstone.tp.query.GraphQuery();
        final com.stonewall.cornerstone.tp.graph.Network n = query.getNetwork(id);
        if (n == null) {
            return null;
        }
        return this.network(n);
    }
    
    public Host getHost(final String id) {
        final com.stonewall.cornerstone.tp.query.GraphQuery query = new com.stonewall.cornerstone.tp.query.GraphQuery();
        final com.stonewall.cornerstone.tp.graph.Host h = query.getHost(id);
        if (h == null) {
            return null;
        }
        return this.host(h);
    }
    
    public IPInterface getInterface(final String id) {
        final com.stonewall.cornerstone.tp.query.GraphQuery query = new com.stonewall.cornerstone.tp.query.GraphQuery();
        final Interface iface = query.getInterface(id);
        if (iface == null) {
            return null;
        }
        return this.iface(iface);
    }
    
    public boolean endpointContains(final String eA, final String eB) {
        final com.stonewall.cornerstone.tp.query.GraphQuery query = new com.stonewall.cornerstone.tp.query.GraphQuery();
        return query.endpointContains(eA, eB);
    }
    
    public Collection<Network> networksContaining(final IpAddr a) {
        final Collection<Network> networks = new HashSet<Network>();
        final com.stonewall.cornerstone.tp.query.GraphQuery query = new com.stonewall.cornerstone.tp.query.GraphQuery();
        final Collection<com.stonewall.cornerstone.tp.graph.Network> nets = query.networksContaining(a);
        for (final com.stonewall.cornerstone.tp.graph.Network n : nets) {
            networks.add(this.network(n));
        }
        return networks;
    }
}
