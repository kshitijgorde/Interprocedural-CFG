// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.query;

import com.stonewall.cornerstone.tp.graph.Constraint;
import com.stonewall.cornerstone.tp.graph.BasicWalker;
import java.util.List;
import com.stonewall.cornerstone.tp.graph.Interface;
import com.stonewall.cornerstone.tp.graph.Host;
import com.stonewall.cornerstone.tp.graph.Endpoint;
import java.util.Collection;
import com.stonewall.cornerstone.entity.util.IpAddr;
import com.stonewall.cornerstone.tp.graph.Network;
import com.stonewall.cornerstone.tp.graph.Node;
import org.xmodel.log.Log;
import com.stonewall.cornerstone.tp.graph.Graph;

public class GraphQuery
{
    protected final Graph graph;
    protected Mode mode;
    protected int maxMetric;
    public static final Log log;
    
    static {
        log = Log.getLog(GraphQuery.class);
    }
    
    public GraphQuery() {
        this(Graph.instance());
    }
    
    public GraphQuery(final Graph graph) {
        this.mode = Mode.Basic;
        this.maxMetric = Integer.MAX_VALUE;
        this.graph = graph;
    }
    
    public Node getNode(final String id) {
        return this.graph.getNode(id);
    }
    
    public Network getNetwork(final String id) {
        return this.graph.getNetwork(id);
    }
    
    public Collection<Endpoint> getByAddress(final IpAddr a) {
        return this.graph.inspector().findByAddress(a);
    }
    
    public Host getHost(final String id) {
        return this.graph.getHost(id);
    }
    
    public Interface getInterface(final String id) {
        return this.graph.getInterface(id);
    }
    
    public boolean endpointExists(final String eid) {
        return this.graph.getEndpoint(eid) != null;
    }
    
    public boolean endpointContains(final String eA, final String eB) {
        boolean result = eA.equals(eB);
        if (!result) {
            final Endpoint a = this.graph.getEndpoint(eA);
            final Endpoint b = this.graph.getEndpoint(eB);
            if (a != null && b != null) {
                result = a.contains(b);
            }
        }
        return result;
    }
    
    public Collection<Network> networksContaining(final IpAddr a) {
        return this.graph.inspector().networksContaining(a);
    }
    
    public Collection<Network> networksContaining(final List<IpAddr> list) {
        return this.graph.inspector().networksContaining(list);
    }
    
    public void setMode(final Mode mode) {
        this.mode = mode;
    }
    
    public void setMaxMetric(final int metric) {
        this.maxMetric = metric;
    }
    
    protected BasicWalker getWalker() {
        return this.getWalker(this.mode);
    }
    
    protected BasicWalker getWalker(final Mode mode) {
        final BasicWalker walker = new BasicWalker(this.graph);
        switch (mode) {
            case Basic: {
                walker.add(new LinkConstraint());
                walker.add(new TunnelConstraint());
                break;
            }
            case Limited: {
                walker.add(new LinkConstraint());
                walker.add(NetworkConstraint.publicConstraint());
                walker.add(new TunnelConstraint());
                break;
            }
            case Secure: {
                walker.add(new LinkConstraint());
                walker.add(NetworkConstraint.publicConstraint());
                walker.add(new TunnelConstraint(TunnelConstraint.Mode.Tunnel));
                break;
            }
            case Complete: {
                walker.add(new LinkConstraint(LinkConstraint.Mode.None));
                walker.add(NetworkConstraint.publicConstraint());
                walker.add(new TunnelConstraint(TunnelConstraint.Mode.Tunnel));
                break;
            }
        }
        return walker;
    }
    
    public enum Mode
    {
        Basic("Basic", 0), 
        Limited("Limited", 1), 
        Secure("Secure", 2), 
        Complete("Complete", 3);
        
        private Mode(final String s, final int n) {
        }
    }
}
