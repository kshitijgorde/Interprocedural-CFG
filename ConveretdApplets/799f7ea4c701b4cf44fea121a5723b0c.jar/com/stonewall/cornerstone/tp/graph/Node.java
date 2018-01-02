// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.graph;

import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import org.xmodel.log.Log;
import java.util.Map;

public class Node extends GraphObject
{
    private boolean hasTransport;
    private final Router router;
    private final NatPolicy nat;
    private final Map<String, Interface> interfaces;
    public static final Log log;
    
    static {
        log = Log.getLog(Node.class);
    }
    
    public Node(final String id) {
        super(id, T.Node);
        this.hasTransport = true;
        this.interfaces = new HashMap<String, Interface>();
        this.router = new Router(this);
        this.nat = new NatPolicy(this);
    }
    
    public void addInterface(final Interface i) {
        this.lock(Mutex.LM.Write);
        try {
            this.interfaces.put(i.getId(), i);
            i.setNode(this);
            if (this.graph != null) {
                this.graph.added(i);
            }
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
    }
    
    public Collection<Interface> getInterfaces() {
        final Collection<Interface> result = new ArrayList<Interface>();
        this.lock(Mutex.LM.Read);
        try {
            result.addAll(this.interfaces());
        }
        finally {
            this.unlock(Mutex.LM.Read);
        }
        this.unlock(Mutex.LM.Read);
        return result;
    }
    
    public Interface getInterface(final String id) {
        this.lock(Mutex.LM.Read);
        try {
            return this.interfaces.get(id);
        }
        finally {
            this.unlock(Mutex.LM.Read);
        }
    }
    
    public Interface getInterfaceByIdentifier(final String identifier) {
        Interface result = null;
        this.lock(Mutex.LM.Read);
        try {
            for (final Interface i : this.interfaces.values()) {
                if (identifier.equals(i.getIdentifier())) {
                    result = i;
                    break;
                }
            }
        }
        finally {
            this.unlock(Mutex.LM.Read);
        }
        this.unlock(Mutex.LM.Read);
        return result;
    }
    
    public void replaceInterfaces(final List<Interface> list) {
        this.lock(Mutex.LM.Write);
        try {
            final Collection<String> existing = new HashSet<String>(this.interfaces.keySet());
            final Set<String> replacement = new HashSet<String>();
            for (final Interface i : list) {
                replacement.add(i.id);
                final Interface intf = this.interfaces.get(i.id);
                if (intf != null) {
                    intf.setAddress(i.getAddress());
                    intf.setIdentifier(i.getIdentifier());
                }
                else {
                    this.addInterface(i);
                }
            }
            for (final String ifid : existing) {
                if (!replacement.contains(ifid)) {
                    this.deleteInterface(ifid);
                }
            }
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
    }
    
    public void deleteInterface(final String ifid) {
        this.lock(Mutex.LM.Write);
        try {
            final Interface i = this.interfaces.remove(ifid);
            if (i != null) {
                i.setNode(null);
                if (this.graph != null) {
                    this.graph.removed(i);
                }
            }
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
    }
    
    public boolean contains(final Endpoint e) {
        this.lock(Mutex.LM.Read);
        try {
            return this.interfaces.get(e.getId()) != null;
        }
        finally {
            this.unlock(Mutex.LM.Read);
        }
    }
    
    public Collection<Interface> getLinked() {
        final List<Interface> list = new ArrayList<Interface>();
        this.lock(Mutex.LM.Read);
        try {
            for (final Interface i : this.interfaces.values()) {
                if (i.connected()) {
                    list.add(i);
                }
            }
        }
        finally {
            this.unlock(Mutex.LM.Read);
        }
        this.unlock(Mutex.LM.Read);
        return list;
    }
    
    public Collection<Interface> getDirectlyConnected(final Endpoint e) {
        final List<Interface> list = new ArrayList<Interface>();
        this.lock(Mutex.LM.Read);
        try {
            for (final Interface i : this.interfaces.values()) {
                if (i.indirectlyConnected(e)) {
                    list.add(i);
                }
            }
        }
        finally {
            this.unlock(Mutex.LM.Read);
        }
        this.unlock(Mutex.LM.Read);
        return list;
    }
    
    public Router router() {
        return this.router;
    }
    
    public NatPolicy nat() {
        return this.nat;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        this.lock(Mutex.LM.Read);
        try {
            sb.append(super.toString());
            sb.append(" (");
            sb.append(" transport: " + this.hasTransport);
            sb.append(" )\n");
            this.printInterfaces(sb);
            this.printRoutes(sb);
            this.printNatRules(sb);
        }
        finally {
            this.unlock(Mutex.LM.Read);
        }
        this.unlock(Mutex.LM.Read);
        return sb.toString();
    }
    
    public boolean hasTransport() {
        return this.hasTransport;
    }
    
    public void hasTransport(final boolean flag) {
        this.hasTransport = flag;
    }
    
    Collection<Interface> interfaces() {
        return this.interfaces.values();
    }
    
    Collection<Interface> getInterfaces(final Endpoint except) {
        final List<Interface> list = new ArrayList<Interface>();
        for (final Interface i : this.interfaces.values()) {
            if (i.id != except.id) {
                list.add(i);
            }
        }
        return list;
    }
    
    Collection<Interface> getInterfaces(final Endpoint except, final Packet packet) {
        Collection<Interface> result = (Collection<Interface>)Collections.emptyList();
        if (this.mayRoute(packet)) {
            final List<Interface> list = new ArrayList<Interface>();
            for (final Interface i : this.router.getInterfaces(packet)) {
                if (i.id != except.id) {
                    list.add(i);
                }
            }
            result = list;
        }
        else {
            result = this.getInterfaces(except);
            Node.log.debug("Router(empty), pkt (null) or routed=no, all interfaces matched.");
        }
        return result;
    }
    
    Collection<Interface> getInterfaces(final Packet packet) {
        Collection<Interface> result = (Collection<Interface>)Collections.emptyList();
        if (this.mayRoute(packet)) {
            final List<Interface> list = new ArrayList<Interface>();
            list.addAll(this.router.getInterfaces(packet));
            result = list;
        }
        else {
            result = this.interfaces();
            Node.log.debug("Router(empty), pkt (null) or routed=no, all interfaces matched.");
        }
        return result;
    }
    
    boolean mayRoute(final Packet p) {
        return this.router.hasRoutes() && p != null && p.routing == Packet.Routing.Normal;
    }
    
    @Override
    protected void added(final Graph g) {
        for (final Interface i : this.interfaces.values()) {
            g.added(i);
        }
    }
    
    @Override
    protected void removed(final Graph g) {
        for (final Interface i : this.interfaces.values()) {
            g.removed(i);
        }
    }
    
    private void printInterfaces(final StringBuilder sb) {
        sb.append("\tInterfaces:\n");
        for (final Interface i : this.interfaces.values()) {
            sb.append("\t\t");
            sb.append(i);
            sb.append('\n');
        }
    }
    
    private void printRoutes(final StringBuilder sb) {
        sb.append("\tRoutes:\n");
        for (final Route r : this.router.getRoutes()) {
            sb.append("\t\t");
            sb.append(r);
            sb.append('\n');
        }
    }
    
    private void printNatRules(final StringBuilder sb) {
        sb.append("\tNAT:\n");
        for (final NatRule r : this.nat.rules()) {
            sb.append(r.toString(2));
            sb.append('\n');
        }
        if (this.nat.invertedRules().size() > 0) {
            sb.append("\t\t-- (R) --\n");
            for (final NatRule r : this.nat.invertedRules()) {
                sb.append(r.toString(2));
                sb.append('\n');
            }
        }
    }
}
