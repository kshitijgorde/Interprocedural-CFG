// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.graph;

import java.util.List;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Collection;
import java.util.HashMap;
import org.xmodel.log.Log;
import java.util.Set;
import com.stonewall.cornerstone.entity.util.IpAddr;
import com.stonewall.cornerstone.dsort.SortedCollection;
import java.util.Map;

public class Graph
{
    static final int MaxMetric = 1000;
    private final Mutex lock;
    private boolean autoSort;
    private final Map<String, Node> nodes;
    private final Map<String, Host> hostidx;
    private final Map<String, Network> netidx;
    private final SortedCollection<Network> networks;
    private final Map<String, Link> links;
    private final Map<String, ObjectGroup> groups;
    private final Map<String, AddressRange> ranges;
    private final Map<String, Interface> ifidx;
    private final Map<IpAddr, Set<Endpoint>> ipidx;
    private final Map<String, Link> epidx;
    private final Map<String, Link> tnlidx;
    private final Inspector inspector;
    private static Graph inst;
    static final Log log;
    
    static {
        Graph.inst = new Graph();
        log = Log.getLog(Graph.class);
    }
    
    public static Graph instance() {
        return Graph.inst;
    }
    
    public Graph() {
        this.lock = Mutex.instance();
        this.autoSort = true;
        this.nodes = new HashMap<String, Node>();
        this.hostidx = new HashMap<String, Host>();
        this.netidx = new HashMap<String, Network>();
        this.networks = new SortedCollection<Network>();
        this.links = new HashMap<String, Link>();
        this.groups = new HashMap<String, ObjectGroup>();
        this.ranges = new HashMap<String, AddressRange>();
        this.ifidx = new HashMap<String, Interface>();
        this.ipidx = new HashMap<IpAddr, Set<Endpoint>>();
        this.epidx = new HashMap<String, Link>();
        this.tnlidx = new HashMap<String, Link>();
        this.inspector = new Inspector();
    }
    
    public void clear() {
        this.lock(Mutex.LM.Write);
        try {
            this.nodes.clear();
            this.hostidx.clear();
            this.netidx.clear();
            this.networks.clear();
            this.links.clear();
            this.groups.clear();
            this.ifidx.clear();
            this.ipidx.clear();
            this.epidx.clear();
            this.tnlidx.clear();
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
    }
    
    public void setAutoSort(final boolean flag) {
        this.autoSort = flag;
    }
    
    public Node addNode(final Node n) {
        this.lock(Mutex.LM.Write);
        try {
            if (this.nodes.get(n.id) == null) {
                this.nodes.put(n.id, n);
                this.added(n);
            }
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
        return n;
    }
    
    public void deleteNode(final String nid) {
        this.lock(Mutex.LM.Write);
        try {
            final Node n = this.nodes.remove(nid);
            if (n != null) {
                this.removed(n);
            }
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
    }
    
    public Node getNode(final String id) {
        this.lock(Mutex.LM.Read);
        try {
            return this.nodes.get(id);
        }
        finally {
            this.unlock(Mutex.LM.Read);
        }
    }
    
    public Network addNetwork(final Network n) {
        this.lock(Mutex.LM.Write);
        try {
            if (this.netidx.get(n.id) == null) {
                this.added(n);
            }
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
        return n;
    }
    
    public void deleteNetwork(final String id) {
        this.lock(Mutex.LM.Write);
        try {
            final Network net = this.netidx.remove(id);
            if (net != null) {
                this.removed(net);
            }
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
    }
    
    public Network getNetwork(final String id) {
        this.lock(Mutex.LM.Read);
        try {
            return this.netidx.get(id);
        }
        finally {
            this.unlock(Mutex.LM.Read);
        }
    }
    
    public Endpoint getEndpoint(final String eid) {
        Endpoint result = null;
        this.lock(Mutex.LM.Read);
        try {
            if (result == null) {
                result = this.netidx.get(eid);
            }
            if (result == null) {
                result = this.ifidx.get(eid);
            }
            if (result == null) {
                result = this.hostidx.get(eid);
            }
        }
        finally {
            this.unlock(Mutex.LM.Read);
        }
        this.unlock(Mutex.LM.Read);
        return result;
    }
    
    public NetObject getNetObject(final String id) {
        NetObject result = id.equals(Any.any.getId()) ? Any.any : null;
        this.lock(Mutex.LM.Read);
        try {
            if (result == null) {
                result = this.getEndpoint(id);
            }
            if (result == null) {
                result = this.groups.get(id);
            }
        }
        finally {
            this.unlock(Mutex.LM.Read);
        }
        this.unlock(Mutex.LM.Read);
        return result;
    }
    
    public Interface getInterface(final String ifid) {
        this.lock(Mutex.LM.Read);
        try {
            return this.ifidx.get(ifid);
        }
        finally {
            this.unlock(Mutex.LM.Read);
        }
    }
    
    public Link getLink(final String lid) {
        this.lock(Mutex.LM.Read);
        try {
            return this.links.get(lid);
        }
        finally {
            this.unlock(Mutex.LM.Read);
        }
    }
    
    public void addLink(final Link link) {
        this.lock(Mutex.LM.Write);
        try {
            this.links.put(link.id, link);
            this.added(link);
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
    }
    
    public void deleteLink(final String id) {
        this.lock(Mutex.LM.Write);
        try {
            final Link link = this.links.remove(id);
            if (link != null) {
                this.removed(link);
            }
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
    }
    
    public Host getHost(final String hid) {
        this.lock(Mutex.LM.Read);
        try {
            return this.hostidx.get(hid);
        }
        finally {
            this.unlock(Mutex.LM.Read);
        }
    }
    
    public ObjectGroup addGroup(final ObjectGroup g) {
        this.lock(Mutex.LM.Write);
        try {
            if (this.groups.get(g.id) == null) {
                this.groups.put(g.id, g);
                this.added(g);
            }
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
        return g;
    }
    
    public void deleteGroup(final String gid) {
        this.lock(Mutex.LM.Write);
        try {
            final ObjectGroup g = this.groups.remove(gid);
            if (g != null) {
                this.removed(g);
            }
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
    }
    
    public ObjectGroup getGroup(final String gid) {
        this.lock(Mutex.LM.Read);
        try {
            return this.groups.get(gid);
        }
        finally {
            this.unlock(Mutex.LM.Read);
        }
    }
    
    public AddressRange addRange(final AddressRange r) {
        this.lock(Mutex.LM.Write);
        try {
            if (this.ranges.get(r.id) == null) {
                this.ranges.put(r.id, r);
                this.added(r);
            }
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
        return r;
    }
    
    public void deleteRange(final String rid) {
        this.lock(Mutex.LM.Write);
        try {
            final AddressRange r = this.ranges.remove(rid);
            if (r != null) {
                this.removed(r);
            }
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
    }
    
    public AddressRange getRange(final String rid) {
        this.lock(Mutex.LM.Read);
        try {
            return this.ranges.get(rid);
        }
        finally {
            this.unlock(Mutex.LM.Read);
        }
    }
    
    public void enableTunnels(final Collection<String> ids) {
        this.lock(Mutex.LM.Write);
        try {
            for (final Link l : this.tnlidx.values()) {
                final boolean flag = ids.contains(l.getId());
                l.enabled(flag);
            }
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Graph\n{\nnodes:\n");
        for (final Node n : this.nodes.values()) {
            sb.append('\t');
            sb.append(n);
            sb.append('\n');
        }
        sb.append("networks:\n");
        for (final Network n2 : this.networks) {
            sb.append('\t');
            sb.append(n2);
            sb.append('\n');
        }
        sb.append("}");
        return sb.toString();
    }
    
    public Inspector inspector() {
        return this.inspector;
    }
    
    public void refreshNetworkAssociations() {
        this.lock(Mutex.LM.Write);
        try {
            for (final Network n : this.networks) {
                n.directChildren().clear();
            }
            for (final Network n : this.networks) {
                final Network parent = n.getParent();
                if (parent != null) {
                    parent.addChild(n);
                }
            }
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
    }
    
    public void sortNetworks() {
        this.lock(Mutex.LM.Write);
        try {
            new BestFit(this.networks).sort();
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
    }
    
    protected void added(final GraphObject g) {
        g.setGraph(this);
        this.lock(Mutex.LM.Write);
        try {
            switch (g.type) {
                case Network: {
                    final Network network = (Network)g;
                    this.netidx.put(network.id, network);
                    this.networks.add(network);
                    this.endpointAdded(network);
                    if (this.autoSort) {
                        this.sortNetworks();
                        break;
                    }
                    break;
                }
                case Interface: {
                    final Interface intf = (Interface)g;
                    this.ifidx.put(intf.id, intf);
                    this.endpointAdded(intf);
                    break;
                }
                case Link: {
                    final Link link = (Link)g;
                    link.endpoints[0].addLink(link);
                    link.endpoints[1].addLink(link);
                    this.epidx.put(link.endpoints[0].getId(), link);
                    this.epidx.put(link.endpoints[1].getId(), link);
                    if (link.tunnel()) {
                        this.tnlidx.put(link.id, link);
                        break;
                    }
                    break;
                }
                case Host: {
                    final Host host = (Host)g;
                    this.hostidx.put(host.id, host);
                    this.endpointAdded(host);
                    break;
                }
            }
            g.added(this);
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
    }
    
    protected void changed(final GraphObject g) {
        this.lock(Mutex.LM.Write);
        try {
            switch (g.type) {
                case Network: {
                    if (this.autoSort) {
                        this.sortNetworks();
                        break;
                    }
                    break;
                }
            }
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
    }
    
    protected void removed(final GraphObject g) {
        this.lock(Mutex.LM.Write);
        try {
            switch (g.type) {
                case Network: {
                    final Network net = (Network)g;
                    this.netidx.remove(net.id);
                    this.networks.remove(net);
                    this.endpointRemoved(net);
                    break;
                }
                case Interface: {
                    final Interface intf = (Interface)g;
                    this.ifidx.remove(intf.id);
                    this.endpointRemoved(intf);
                    if (this.autoSort) {
                        this.sortNetworks();
                        break;
                    }
                    break;
                }
                case Link: {
                    final Link link = (Link)g;
                    link.endpoints[0].deleteLink(link.id);
                    link.endpoints[1].deleteLink(link.id);
                    this.epidx.remove(link.endpoints[0].getId());
                    this.epidx.remove(link.endpoints[1].getId());
                    this.tnlidx.remove(link.id);
                    break;
                }
                case Host: {
                    final Host host = (Host)g;
                    this.hostidx.remove(host.id);
                    this.endpointRemoved(host);
                    break;
                }
            }
            g.removed(this);
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
        g.setGraph(null);
    }
    
    protected void map(final Endpoint e) {
        this.lock(Mutex.LM.Write);
        try {
            final IpAddr address = e.getAddress();
            Set<Endpoint> list = this.ipidx.get(address);
            if (list == null) {
                list = new HashSet<Endpoint>();
                this.ipidx.put(address, list);
            }
            list.add(e);
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
    }
    
    protected void unmap(final IpAddr address, final Endpoint e) {
        this.lock(Mutex.LM.Write);
        try {
            final Set<Endpoint> list = this.ipidx.get(e.getAddress());
            if (list != null) {
                list.remove(e);
            }
            if (list.isEmpty()) {
                this.ipidx.remove(e.getAddress());
            }
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
    }
    
    protected void lock(final Mutex.LM mode) {
        this.lock.lock(mode);
    }
    
    protected void unlock(final Mutex.LM mode) {
        this.lock.unlock(mode);
    }
    
    private void endpointAdded(final Endpoint e) {
        this.map(e);
    }
    
    private void endpointRemoved(final Endpoint e) {
        this.unmap(e.getAddress(), e);
        for (final Link link : e.links()) {
            final Endpoint other = link.otherEnd(e);
            if (other != null) {
                other.deleteLink(link.id);
            }
        }
    }
    
    public class Inspector
    {
        public Collection<Endpoint> findByAddress(final IpAddr a) {
            Graph.this.lock(Mutex.LM.Read);
            try {
                return Graph.this.ipidx.get(a);
            }
            finally {
                Graph.this.unlock(Mutex.LM.Read);
            }
        }
        
        public Collection<Network> networksContaining(final IpAddr a) {
            Graph.this.lock(Mutex.LM.Read);
            try {
                return new BestFit(Graph.this.networks).networksContaining(a);
            }
            finally {
                Graph.this.unlock(Mutex.LM.Read);
            }
        }
        
        public Collection<Network> networksContaining(final List<IpAddr> list) {
            final Set<Network> result = new HashSet<Network>();
            Graph.this.lock(Mutex.LM.Read);
            try {
                for (final IpAddr a : list) {
                    result.addAll(this.networksContaining(a));
                }
            }
            finally {
                Graph.this.unlock(Mutex.LM.Read);
            }
            Graph.this.unlock(Mutex.LM.Read);
            return result;
        }
        
        public Network networkContaining(final IpAddr address) {
            Network result = null;
            Graph.this.lock(Mutex.LM.Read);
            try {
                final Iterator<Network> iterator = this.networksContaining(address).iterator();
                if (iterator.hasNext()) {
                    final Network n = result = iterator.next();
                }
            }
            finally {
                Graph.this.unlock(Mutex.LM.Read);
            }
            Graph.this.unlock(Mutex.LM.Read);
            return result;
        }
        
        public Network networkWithAddress(final IpAddr a) {
            Network result = null;
            Graph.this.lock(Mutex.LM.Read);
            try {
                for (final Network n : Graph.this.networks) {
                    if (n.addressed() && n.getAddress().equals((Object)a)) {
                        result = n;
                        break;
                    }
                }
            }
            finally {
                Graph.this.unlock(Mutex.LM.Read);
            }
            Graph.this.unlock(Mutex.LM.Read);
            return result;
        }
        
        public Link linkContaining(final Endpoint e) {
            Graph.this.lock(Mutex.LM.Read);
            try {
                return Graph.this.epidx.get(e.getId());
            }
            finally {
                Graph.this.unlock(Mutex.LM.Read);
            }
        }
    }
}
