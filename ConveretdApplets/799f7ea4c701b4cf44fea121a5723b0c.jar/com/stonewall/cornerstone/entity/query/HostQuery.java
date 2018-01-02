// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.query;

import java.util.Iterator;
import java.util.Collection;
import com.stonewall.cornerstone.tp.graph.Network;
import com.stonewall.cornerstone.tp.graph.Host;
import com.stonewall.cornerstone.tp.query.NetQuery;
import java.util.ArrayList;
import java.util.List;

public class HostQuery extends Query
{
    public List<String> getHostIdsByNetwork(final String netId) {
        final List<String> ids = new ArrayList<String>();
        final NetQuery netQuery = new NetQuery();
        final Network n = netQuery.getNetwork(netId);
        final Collection<Host> hosts = n.getHosts();
        for (final Host h : hosts) {
            ids.add(h.getId());
        }
        return ids;
    }
}
