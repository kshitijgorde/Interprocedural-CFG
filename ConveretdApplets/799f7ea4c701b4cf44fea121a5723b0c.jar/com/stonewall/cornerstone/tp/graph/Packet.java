// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.graph;

import java.util.ArrayList;
import java.util.Stack;
import java.util.List;
import com.stonewall.cornerstone.entity.util.IpAddr;

public class Packet
{
    public Matching matching;
    public Routing routing;
    public Translated translated;
    public Encapsulated encapsulated;
    public final NetObject src;
    public final NetObject dst;
    
    public Packet(final NetObject dst) {
        this(InetAddress.any(), dst);
    }
    
    public Packet(final IpAddr src, final IpAddr dst) {
        this.matching = Matching.Normal;
        this.routing = Routing.Normal;
        this.translated = null;
        this.encapsulated = null;
        this.src = new InetAddress(src);
        this.dst = new InetAddress(dst);
    }
    
    public Packet(final NetObject src, final NetObject dst) {
        this.matching = Matching.Normal;
        this.routing = Routing.Normal;
        this.translated = null;
        this.encapsulated = null;
        this.src = src;
        this.dst = dst;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof Packet) {
            final Packet rhs = (Packet)o;
            return this.src.equals(rhs.src) && this.dst.equals(rhs.dst);
        }
        return super.equals(o);
    }
    
    @Override
    public int hashCode() {
        return (String.valueOf(this.src.getId()) + this.dst.getId()).hashCode();
    }
    
    public boolean contains(final IpAddr s, final IpAddr d) {
        boolean result = false;
        switch (this.matching) {
            case Normal: {
                result = (this.src.contains(s) && this.dst.contains(d));
                break;
            }
            case AnySrc: {
                result = this.dst.contains(d);
                break;
            }
            case AnyDst: {
                result = this.src.contains(s);
                break;
            }
        }
        return result;
    }
    
    public boolean contains(final Packet p) {
        return this.contains(p.src.getAddress(), p.dst.getAddress());
    }
    
    public Packet invert() {
        final Packet result = new Packet(this.dst, this.src);
        result.matching = this.matching;
        result.routing = this.routing;
        result.translated = this.translated;
        return result;
    }
    
    public List<Packet> natHistory() {
        final Stack<Packet> stack = new Stack<Packet>();
        for (Packet p = this.translated.packet; p != null; p = p.translated.packet) {
            stack.push(p);
        }
        final List<Packet> result = new ArrayList<Packet>(stack.size());
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }
    
    public boolean hasTranslated() {
        return this.translated != null;
    }
    
    @Override
    public String toString() {
        return "(" + this.src.getAddress() + " --> " + this.dst.getAddress() + ")";
    }
    
    public enum Matching
    {
        Normal("Normal", 0), 
        AnySrc("AnySrc", 1), 
        AnyDst("AnyDst", 2);
        
        private Matching(final String s, final int n) {
        }
    }
    
    public enum Routing
    {
        Normal("Normal", 0), 
        None("None", 1);
        
        private Routing(final String s, final int n) {
        }
    }
    
    public class Translated
    {
        public final Packet packet;
        public final NetObject sxpool;
        public final NetObject dxpool;
        
        Translated(final Packet packet, final NatRule rule) {
            this.packet = packet;
            if (rule != null) {
                this.sxpool = rule.sx.pool();
                this.dxpool = rule.dx.pool();
            }
            else {
                this.sxpool = null;
                this.dxpool = null;
            }
        }
    }
    
    class Encapsulated
    {
        final Packet packet;
        final Interface sg;
        final Interface dg;
        
        Encapsulated(final Packet packet, final Interface sg, final Interface dg) {
            this.packet = packet;
            this.sg = sg;
            this.dg = dg;
        }
    }
}
