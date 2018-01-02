// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.graph;

import java.util.Iterator;
import java.util.HashSet;
import java.util.Collections;
import java.util.Set;
import com.stonewall.cornerstone.entity.util.IpAddr;

public class NatAction
{
    private final Method method;
    private NetObject pool;
    
    public NatAction() {
        this.pool = null;
        this.method = Method.none;
    }
    
    public NatAction(final Method m) {
        this.pool = null;
        this.method = m;
    }
    
    public NatAction(final IpAddr ip) {
        this(new InetAddress(ip));
    }
    
    public NatAction(final NetObject n) {
        this.pool = null;
        this.pool = n;
        this.method = (n.isHost() ? Method.direct : Method.pool);
    }
    
    public Method method() {
        return this.method;
    }
    
    public Set<NetObject> translate(final NatRule rule, final NetObject address) {
        Set<NetObject> result = null;
        switch (this.method) {
            case none: {
                result = Collections.singleton(address);
                break;
            }
            case direct: {
                result = Collections.singleton(this.pool);
                break;
            }
            case pool: {
                result = new HashSet<NetObject>();
                for (final NetObject m : this.pool) {
                    result.add(m);
                }
                break;
            }
            case egress: {
                result = this.getEgressAddress(rule);
                break;
            }
        }
        return result;
    }
    
    public NetObject translate() {
        return (this.method == Method.direct) ? this.pool : null;
    }
    
    @Override
    public String toString() {
        return "(" + this.method + ") " + this.pool;
    }
    
    public String toString(final NatRule r) {
        final StringBuilder sb = new StringBuilder();
        sb.append('(');
        sb.append(this.method);
        sb.append(") ");
        switch (this.method) {
            case direct:
            case pool: {
                sb.append(this.pool);
                break;
            }
            case egress: {
                sb.append(this.getEgressAddress(r));
                break;
            }
        }
        return sb.toString();
    }
    
    Set<NetObject> getEgressAddress(final NatRule r) {
        final Set<NetObject> result = new HashSet<NetObject>(1);
        final Graph g = r.graph();
        final Interface intf = g.getInterface(r.selector.egress);
        if (intf != null) {
            result.add(intf);
        }
        return result;
    }
    
    NetObject pool() {
        return this.pool;
    }
    
    public enum Method
    {
        none("none", 0), 
        direct("direct", 1), 
        pool("pool", 2), 
        egress("egress", 3);
        
        private Method(final String s, final int n) {
        }
    }
}
