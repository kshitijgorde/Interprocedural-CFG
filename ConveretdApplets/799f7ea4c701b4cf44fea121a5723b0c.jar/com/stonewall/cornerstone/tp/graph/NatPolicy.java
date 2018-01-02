// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.graph;

import java.util.Iterator;
import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

public class NatPolicy
{
    public static final NatAction None;
    private final GraphObject parent;
    private final List<NatRule> rules;
    private final List<NatRule> inverted;
    private final List<List<NatRule>> ruleset;
    
    static {
        None = new NatAction();
    }
    
    public NatPolicy(final GraphObject parent) {
        this.rules = new ArrayList<NatRule>();
        this.inverted = new ArrayList<NatRule>();
        this.ruleset = new ArrayList<List<NatRule>>();
        this.parent = parent;
        this.ruleset.add(this.rules);
        this.ruleset.add(this.inverted);
    }
    
    public void clear() {
        this.lock(Mutex.LM.Write);
        try {
            this.rules.clear();
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
    }
    
    public void replace(final List<NatRule> rules) {
        this.lock(Mutex.LM.Write);
        try {
            this.rules.clear();
            this.rules.addAll(rules);
            this.invertRules();
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
    }
    
    public void add(final NatRule r) {
        this.lock(Mutex.LM.Write);
        try {
            r.setPolicy(this);
            this.rules.add(r);
            final NatRule inv = r.invert();
            if (inv != null) {
                inv.setPolicy(this);
                this.inverted.add(inv);
            }
        }
        finally {
            this.unlock(Mutex.LM.Write);
        }
        this.unlock(Mutex.LM.Write);
    }
    
    public List<Packet> xlate(final String in, final String out, final Packet p) {
        this.lock(Mutex.LM.Read);
        try {
            if (p == null || this.isEmpty()) {
                return Collections.singletonList(p);
            }
            final List<Packet> result = new ArrayList<Packet>();
            NatRule rule = null;
            for (final List<NatRule> list : this.ruleset) {
                rule = this.match(in, out, p, list);
                if (rule != null) {
                    break;
                }
            }
            if (rule == null) {
                return Collections.singletonList(p);
            }
            for (final NetObject s : rule.sx().translate(rule, p.src)) {
                for (final NetObject d : rule.dx().translate(rule, p.dst)) {
                    final Packet xp = new Packet(s, d);
                    xp.matching = p.matching;
                    xp.routing = p.routing;
                    final Packet packet = xp;
                    p.getClass();
                    packet.translated = p.new Translated(p, rule);
                    result.add(xp);
                }
            }
            return result;
        }
        finally {
            this.unlock(Mutex.LM.Read);
        }
    }
    
    @Override
    public String toString() {
        this.lock(Mutex.LM.Read);
        try {
            final StringBuilder sb = new StringBuilder("NAT:\n");
            for (final NatRule r : this.rules) {
                sb.append(r.toString(1));
                sb.append('\n');
            }
            sb.append("-- (R) --\n");
            for (final NatRule r : this.inverted) {
                sb.append(r.toString(1));
                sb.append('\n');
            }
            return sb.toString();
        }
        finally {
            this.unlock(Mutex.LM.Read);
        }
    }
    
    public boolean isEmpty() {
        return this.rules.isEmpty();
    }
    
    List<NatRule> rules() {
        return this.rules;
    }
    
    List<NatRule> invertedRules() {
        return this.inverted;
    }
    
    Graph graph() {
        return (this.parent != null) ? this.parent.graph : Graph.instance();
    }
    
    private NatRule match(final String in, final String out, final Packet p, final List<NatRule> list) {
        NatRule result = null;
        for (final NatRule r : list) {
            if (r.match(in, out, p)) {
                result = r;
                break;
            }
        }
        return result;
    }
    
    private void invertRules() {
        this.inverted.clear();
        for (final NatRule r : this.rules) {
            final NatRule inv = r.invert();
            if (inv != null) {
                inv.setPolicy(this);
                this.inverted.add(inv);
            }
        }
    }
    
    private void lock(final Mutex.LM mode) {
        this.parent.lock(mode);
    }
    
    private void unlock(final Mutex.LM mode) {
        this.parent.unlock(mode);
    }
}
