// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.query;

import com.stonewall.cornerstone.tp.graph.Network;
import com.stonewall.cornerstone.entity.util.IpAddr;
import com.stonewall.cornerstone.tp.graph.Node;
import java.util.Map;
import java.util.LinkedHashMap;
import com.stonewall.cornerstone.tp.graph.GraphObject;
import java.util.Iterator;
import java.util.Set;
import com.stonewall.cornerstone.tp.graph.Interface;
import com.stonewall.cornerstone.tp.graph.Frame;
import java.util.List;
import com.stonewall.cornerstone.tp.graph.NetObject;
import com.stonewall.cornerstone.tp.graph.Packet;
import java.util.HashSet;
import java.util.Collection;
import com.stonewall.cornerstone.tp.graph.BasicWalker;
import com.stonewall.cornerstone.tp.graph.Endpoint;
import com.stonewall.cornerstone.tp.graph.Constraint;
import com.stonewall.cornerstone.tp.graph.Metric;
import com.stonewall.cornerstone.tp.graph.Graph;
import org.xmodel.log.Log;
import com.stonewall.cornerstone.tp.graph.FrameCollection;

public class NodeQuery extends GraphQuery
{
    private FrameCollection paths;
    public static final Log log;
    
    static {
        log = Log.getLog(NodeQuery.class);
    }
    
    public NodeQuery() {
        this(Graph.instance());
    }
    
    public NodeQuery(final Graph graph) {
        super(graph);
        this.paths = new FrameCollection();
    }
    
    public boolean endpointsLinked(final String eA, final String eB) {
        NodeQuery.log.debug(String.valueOf(eA) + "/" + eB);
        final Endpoint b = this.graph.getEndpoint(eB);
        if (b == null) {
            return false;
        }
        final BasicWalker walker = this.getWalker();
        walker.add(new Metric(this.maxMetric));
        walker.add(new EndpointConstraint(b));
        walker.start(eA);
        this.paths = walker.getPaths();
        return this.paths.size() > 0;
    }
    
    public Collection<String> getNodesBetween(final String eA, final String eB) {
        NodeQuery.log.debug(String.valueOf(eA) + "/" + eB);
        final Set<String> result = new HashSet<String>();
        final Endpoint a = this.graph.getEndpoint(eA);
        final Endpoint b = this.graph.getEndpoint(eB);
        if (a == null || b == null) {
            return result;
        }
        final BasicWalker walker = this.getWalker();
        walker.add(new Metric(this.maxMetric));
        walker.add(new EndpointConstraint(b));
        walker.setPacket(new Packet(a, b));
        walker.start(a);
        this.paths = walker.getPaths();
        for (final List<Frame> list : this.paths) {
            for (final Frame f : list) {
                final Endpoint object = f.endpoint();
                switch (object.type()) {
                    default: {
                        continue;
                    }
                    case Network: {
                        continue;
                    }
                    case Interface: {
                        final Interface intf = (Interface)object;
                        result.add(intf.getNode().getId());
                        continue;
                    }
                }
            }
        }
        return result;
    }
    
    public Collection<String> getNodesBetween(final List<String> listA, final List<String> listB) {
        final Set<String> result = new HashSet<String>();
        for (final String eA : listA) {
            for (final String eB : listB) {
                result.addAll(this.getNodesBetween(eA, eB));
            }
        }
        return result;
    }
    
    public Collection<String> getDirectlyConnected(final String nid) {
        final Set<String> result = new HashSet<String>();
        final EndpointCollector collector = new EndpointCollector(GraphObject.T.Interface, 0);
        final BasicWalker walker = this.getWalker();
        walker.add(collector);
        walker.start(nid);
        this.paths = walker.getPaths();
        for (final Frame f : collector.list()) {
            final Endpoint object = f.endpoint();
            final Interface intf = (Interface)object;
            result.add(intf.getNode().getId());
        }
        return result;
    }
    
    public Collection<Item> getItemsBetween(final List<String> listA, final List<String> listB) {
        NodeQuery.log.debug(listA + "/" + listB);
        final Map<String, Item> result = new LinkedHashMap<String, Item>();
        for (final String eA : listA) {
            for (final String eB : listB) {
                for (final Item item : this.getItemsBetween(eA, eB, eA, eB)) {
                    final Item existing = result.get(item.getNode());
                    if (existing != null) {
                        existing.merge(item);
                    }
                    else {
                        result.put(item.getNode(), item);
                    }
                }
            }
        }
        return result.values();
    }
    
    public Collection<Item> getItemsBetween(final List<String> listA, final List<String> listB, final String src, final String dst) {
        NodeQuery.log.debug(listA + "/" + listB);
        final Map<String, Item> result = new LinkedHashMap<String, Item>();
        for (final String eA : listA) {
            for (final String eB : listB) {
                for (final Item item : this.getItemsBetween(eA, eB, src, dst)) {
                    final Item existing = result.get(item.getNode());
                    if (existing != null) {
                        existing.merge(item);
                    }
                    else {
                        result.put(item.getNode(), item);
                    }
                }
            }
        }
        return result.values();
    }
    
    public Collection<Item> getItemsBetween(final String eA, final String eB) {
        return this.getItemsBetween(eA, eB, eA, eB);
    }
    
    public Collection<Item> getItemsBetween(final String eA, final String eB, final String src, final String dst) {
        NodeQuery.log.debug(String.valueOf(eA) + "/" + eB);
        final Map<String, Item> result = new LinkedHashMap<String, Item>();
        final Endpoint a = this.graph.getEndpoint(eA);
        final Endpoint b = this.graph.getEndpoint(eB);
        final Endpoint c = this.graph.getEndpoint(src);
        final Endpoint d = this.graph.getEndpoint(dst);
        if (a == null || b == null) {
            return result.values();
        }
        final BasicWalker walker = this.getWalker();
        walker.add(new EndpointConstraint(d));
        walker.setPackets(this.getPackets(a, b));
        walker.start(c);
        this.paths = walker.getPaths();
        for (final Frame f : this.paths.flatten()) {
            final Endpoint endpoint = f.endpoint();
            if (endpoint.type(GraphObject.T.Interface)) {
                final Interface intf = (Interface)endpoint;
                final Node n = intf.getNode();
                Item item = result.get(n.getId());
                if (item == null) {
                    item = new Item(n.getId());
                    result.put(n.getId(), item);
                }
                item.put(intf.getId(), f.direction(), f.getPacket());
            }
        }
        return result.values();
    }
    
    public Collection<Interface> getIntfsDirectlyConnected(final String nid, final String eid) {
        final Set<Interface> result = new HashSet<Interface>();
        final Node node = this.graph.getNode(nid);
        final Endpoint endpoint = this.graph.getEndpoint(eid);
        if (node != null && endpoint != null) {
            result.addAll(node.getDirectlyConnected(endpoint));
        }
        return result;
    }
    
    public Collection<Interface> getIntfsDirectlyConnected(final String nid, final IpAddr addr) {
        final Set<Interface> result = new HashSet<Interface>();
        final Node node = this.graph.getNode(nid);
        final Network net = this.graph.inspector().networkContaining(addr);
        if (node != null && net != null) {
            result.addAll(node.getDirectlyConnected(net));
        }
        return result;
    }
    
    public Interface getInterfaceByIdentifier(final String nid, final String identifier) {
        Interface result = null;
        final Node node = this.graph.getNode(nid);
        if (node != null) {
            result = node.getInterfaceByIdentifier(identifier);
        }
        return result;
    }
    
    public FrameCollection paths() {
        return this.paths;
    }
    
    private Set<Packet> getPackets(final Endpoint a, final Endpoint b) {
        final Set<Packet> result = new HashSet<Packet>();
        BasicWalker walker = this.getWalker();
        walker.add(new EndpointConstraint(b));
        walker.start(a);
        for (final List<Frame> p : walker.getPaths()) {
            walker = this.getWalker();
            final Packet pkt = new Packet(b, a);
            pkt.matching = Packet.Matching.AnyDst;
            walker.add(new EndpointConstraint(a));
            walker.filter(p);
            walker.setPacket(pkt);
            walker.start(b);
            final FrameCollection paths = walker.getPaths();
            if (paths.notEmpty()) {
                final List<Frame> p2 = paths.get(0);
                final Packet rcvd = p2.get(p2.size() - 1).getPacket();
                result.add(new Packet(a, rcvd.src));
            }
        }
        if (result.isEmpty()) {
            final Packet pkt2 = new Packet(a, b);
            result.add(pkt2);
        }
        return result;
    }
}
