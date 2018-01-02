// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.query;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import com.stonewall.cornerstone.tp.graph.Constraint;
import java.util.HashMap;
import com.stonewall.cornerstone.tp.graph.Packet;
import java.util.Set;
import java.util.Map;

public class Item
{
    private final String node;
    private final Map<String, Set<Packet>> inbound;
    private final Map<String, Set<Packet>> outbound;
    
    Item(final String nid) {
        this.inbound = new HashMap<String, Set<Packet>>();
        this.outbound = new HashMap<String, Set<Packet>>();
        this.node = nid;
    }
    
    public String getNode() {
        return this.node;
    }
    
    public Set<String> getInterfaces(final Constraint.Direction d) {
        return this.table(d).keySet();
    }
    
    public Set<Packet> getPackets(final Constraint.Direction d, final String ifid) {
        return this.table(d).get(ifid);
    }
    
    public Map<String, Set<Packet>> table(final Constraint.Direction d) {
        return d.equals(Constraint.Direction.Inbound) ? this.inbound : this.outbound;
    }
    
    public boolean hasTranslated() {
        boolean result = false;
        Constraint.Direction[] values;
        for (int length = (values = Constraint.Direction.values()).length, i = 0; i < length; ++i) {
            final Constraint.Direction d = values[i];
            if (this.hasTranslated(d)) {
                result = true;
                break;
            }
        }
        return result;
    }
    
    public boolean hasTranslated(final Constraint.Direction d) {
        for (final Set<Packet> set : this.table(d).values()) {
            for (final Packet p : set) {
                if (p.hasTranslated()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Node (");
        sb.append(this.node);
        sb.append(")\n\tInbound:\n");
        for (final String s : this.inbound.keySet()) {
            sb.append("\t\t(");
            sb.append(s);
            sb.append(")\n\t\t\tPackets:\n");
            for (final Packet p : this.inbound.get(s)) {
                sb.append("\t\t\t\t");
                sb.append((p == null) ? "<null>" : p);
                sb.append('\n');
            }
        }
        sb.append("\tOutbound:\n");
        for (final String s : this.outbound.keySet()) {
            sb.append("\t\t(");
            sb.append(s);
            sb.append(")\n\t\t\tPackets:\n");
            for (final Packet p : this.outbound.get(s)) {
                sb.append("\t\t\t\t");
                sb.append((p == null) ? "<null>" : p);
                sb.append('\n');
            }
        }
        return sb.toString();
    }
    
    protected void put(final String ifid, final Constraint.Direction d, final Packet p) {
        final Map<String, Set<Packet>> map = this.table(d);
        Set<Packet> set = map.get(ifid);
        if (set == null) {
            set = new HashSet<Packet>();
            map.put(ifid, set);
        }
        set.add(p);
    }
    
    protected void merge(final Item item) {
        Constraint.Direction[] values;
        for (int length = (values = Constraint.Direction.values()).length, i = 0; i < length; ++i) {
            final Constraint.Direction d = values[i];
            this.merge(item, d);
        }
    }
    
    protected void merge(final Item item, final Constraint.Direction d) {
        for (final String k : item.table(d).keySet()) {
            final Set<Packet> mgPkts = item.table(d).get(k);
            final Set<Packet> myPkts = this.table(d).get(k);
            if (myPkts != null) {
                myPkts.addAll(mgPkts);
            }
            else {
                this.table(d).put(k, mgPkts);
            }
        }
    }
}
