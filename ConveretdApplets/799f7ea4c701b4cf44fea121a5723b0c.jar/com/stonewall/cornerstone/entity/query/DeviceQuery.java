// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.query;

import java.util.ArrayList;
import com.stonewall.cornerstone.tp.graph.Interface;
import com.stonewall.cornerstone.entity.IPInterface;
import com.stonewall.cornerstone.entity.policy.Endpoint;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import com.stonewall.cornerstone.tp.query.GraphQuery;
import com.stonewall.cornerstone.tp.query.NodeQuery;
import java.util.Collection;
import com.stonewall.cornerstone.entity.policy.Selector;
import java.util.List;

public class DeviceQuery extends Query
{
    public Collection<String> affectedDevices(final List<Selector> selectors, final boolean secure) {
        final NodeQuery nodeQuery = new NodeQuery();
        if (secure) {
            nodeQuery.setMode(GraphQuery.Mode.Secure);
        }
        final Set<String> ids = new HashSet<String>();
        for (final Selector s : selectors) {
            final Endpoint srcEp = s.getSource();
            final Endpoint dstEp = s.getDestination();
            ids.addAll(nodeQuery.getNodesBetween(srcEp.getPartIds(), dstEp.getPartIds()));
        }
        return ids;
    }
    
    public Collection<IPInterface> getIntfsDirectlyConnected(final String nid, final String eid) {
        final Set<IPInterface> result = new HashSet<IPInterface>();
        final NodeQuery query = new NodeQuery();
        final Collection<Interface> ifaces = query.getIntfsDirectlyConnected(nid, eid);
        for (final Interface i : ifaces) {
            result.add(this.iface(i));
        }
        return result;
    }
    
    public Collection<IPInterface> getIntfsDirectlyConnected(final String nid, final List<String> eids) {
        final Collection<IPInterface> ifaces = new ArrayList<IPInterface>();
        for (final String id : eids) {
            ifaces.addAll(this.getIntfsDirectlyConnected(nid, id));
        }
        return ifaces;
    }
}
