// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.query;

import com.stonewall.cornerstone.tp.graph.Node;
import com.stonewall.cornerstone.tp.graph.Interface;
import com.stonewall.cornerstone.tp.graph.Frame;
import com.stonewall.cornerstone.tp.graph.FrameCollection;
import com.stonewall.cornerstone.tp.graph.BasicWalker;
import com.stonewall.cornerstone.tp.graph.Endpoint;
import com.stonewall.cornerstone.tp.graph.Link;
import com.stonewall.cornerstone.tp.graph.NetObject;
import com.stonewall.cornerstone.tp.graph.Packet;
import com.stonewall.cornerstone.tp.graph.Constraint;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import java.util.Collection;
import java.util.List;
import com.stonewall.cornerstone.tp.graph.Graph;
import org.xmodel.log.Log;

public class TunnelQuery extends GraphQuery
{
    public static final Log log;
    
    static {
        log = Log.getLog(TunnelQuery.class);
    }
    
    public TunnelQuery() {
        this(Graph.instance());
    }
    
    public TunnelQuery(final Graph graph) {
        super(graph);
        this.mode = Mode.Secure;
    }
    
    public Collection<Tunnel> enabledTunnelsBetween(final List<String> listA, final List<String> listB) {
        final Set<Tunnel> result = new HashSet<Tunnel>();
        for (final String eA : listA) {
            for (final String eB : listB) {
                result.addAll(this.enabledTunnelsBetween(eA, eB));
            }
        }
        return result;
    }
    
    public Collection<Tunnel> enabledTunnelsBetween(final String eA, final String eB) {
        return this.tunnelsBetween(eA, eB);
    }
    
    public Collection<Tunnel> allTunnelsBetween(final List<String> listA, final List<String> listB) {
        final Set<Tunnel> result = new HashSet<Tunnel>();
        for (final String eA : listA) {
            for (final String eB : listB) {
                result.addAll(this.allTunnelsBetween(eA, eB));
            }
        }
        return result;
    }
    
    public Collection<Tunnel> allTunnelsBetween(final String eA, final String eB) {
        this.mode = Mode.Complete;
        return this.tunnelsBetween(eA, eB);
    }
    
    private Collection<Tunnel> tunnelsBetween(final String eA, final String eB) {
        TunnelQuery.log.debug(String.valueOf(eA) + "/" + eB);
        final List<Tunnel> result = new ArrayList<Tunnel>();
        final Endpoint a = this.graph.getEndpoint(eA);
        final Endpoint b = this.graph.getEndpoint(eB);
        if (a == null || b == null) {
            return result;
        }
        final BasicWalker walker = this.getWalker();
        walker.add(new EndpointConstraint(b));
        walker.setPacket(new Packet(a, b));
        walker.start(a);
        final FrameCollection paths = walker.getPaths();
        for (final Link link : paths.tunnels()) {
            result.add(new Tunnel(link));
        }
        return result;
    }
    
    public String getClosestNode(final String tid, final String eid) {
        String result = null;
        final Link tunnel = this.graph.getLink(tid);
        if (tunnel == null) {
            return null;
        }
        final Endpoint[] endpoints = tunnel.endpoints();
        final int[] metric = new int[2];
        final Endpoint endpoint = this.graph.getEndpoint(eid);
        if (endpoint == null) {
            return result;
        }
        final BasicWalker walker = this.getWalker(Mode.Limited);
        walker.setPacket(new Packet(endpoint));
        walker.add(new EndpointConstraint(endpoint));
        for (int i = 0; i < 2; ++i) {
            walker.start(endpoints[i], Constraint.Direction.Inbound);
            final FrameCollection paths = walker.getPaths();
            paths.sortPathsByMetric();
            metric[i] = (paths.isEmpty() ? Integer.MAX_VALUE : this.pathMetric(paths.get(0)));
        }
        if (metric[0] < metric[1]) {
            final Interface intf = (Interface)endpoints[0];
            result = intf.getNode().getId();
        }
        if (metric[1] < metric[0]) {
            final Interface intf = (Interface)endpoints[1];
            result = intf.getNode().getId();
        }
        return result;
    }
    
    public Tunnel getTunnel(final String tid) {
        final Link link = this.graph.getLink(tid);
        return (link != null && link.tunnel()) ? new Tunnel(link) : null;
    }
    
    private int pathMetric(final List<Frame> path) {
        return path.isEmpty() ? Integer.MAX_VALUE : path.get(path.size() - 1).metric();
    }
    
    public class Tunnel
    {
        private final Link link;
        
        Tunnel(final Link link) {
            this.link = link;
        }
        
        public String getId() {
            return this.link.getId();
        }
        
        public String[] getEndpoints() {
            final Endpoint[] e = this.link.endpoints();
            final String[] result = { e[0].getId(), e[1].getId() };
            return result;
        }
        
        public Endpoint getLocalEndpoint(final String nid) {
            Endpoint result = null;
            final Node n = TunnelQuery.this.graph.getNode(nid);
            if (n == null) {
                return null;
            }
            Endpoint[] endpoints;
            for (int length = (endpoints = this.link.endpoints()).length, i = 0; i < length; ++i) {
                final Endpoint e = endpoints[i];
                if (n.contains(e)) {
                    result = e;
                    break;
                }
            }
            return result;
        }
        
        public Endpoint otherEnd(final Endpoint e) {
            final Endpoint[] ea = this.link.endpoints();
            return (e == ea[0]) ? ea[1] : ea[0];
        }
        
        @Override
        public String toString() {
            final String[] e = this.getEndpoints();
            return "tunnel: (" + this.getId() + ") [" + e[0] + ", " + e[1] + "]";
        }
        
        public boolean hasEndpoint(final Endpoint endpoint) {
            return this.link.hasEndpoint(endpoint);
        }
    }
}
