// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.query;

import com.stonewall.cornerstone.entity.util.IpAddr;
import java.util.Iterator;
import com.stonewall.cornerstone.tp.graph.Interface;
import java.util.ArrayList;
import com.stonewall.cornerstone.tp.graph.Network;
import java.util.Collection;
import com.stonewall.cornerstone.tp.graph.Graph;
import org.xmodel.log.Log;

public class NetQuery extends GraphQuery
{
    public static final Log log;
    
    static {
        log = Log.getLog(NetQuery.class);
    }
    
    public NetQuery() {
        this(Graph.instance());
    }
    
    public NetQuery(final Graph graph) {
        super(graph);
    }
    
    public Collection<Network> getDirectlyLinked(final String ifid) {
        final Collection<Network> result = new ArrayList<Network>();
        final Interface intf = this.graph.getInterface(ifid);
        for (final Network n : intf.getDirectNetworks()) {
            result.add(n);
        }
        return result;
    }
    
    public Network findByAddress(final IpAddr address) {
        return this.graph.inspector().networkWithAddress(address);
    }
}
