// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.graph;

import com.stonewall.cornerstone.entity.util.IpAddr;

public class NatSelector
{
    public final String ingress;
    public final String egress;
    public final NetObject src;
    public final NetObject dst;
    
    public NatSelector(final String ingress, final String egress, final NetObject src, final NetObject dst) {
        this.ingress = ingress;
        this.egress = egress;
        this.src = src;
        this.dst = dst;
    }
    
    public NatSelector(final String ingress, final String egress, final NetObject src) {
        this.ingress = ingress;
        this.egress = egress;
        this.src = src;
        this.dst = Any.any;
    }
    
    public NatSelector(final String ingress, final String egress, final IpAddr src, final IpAddr dst) {
        this.ingress = ingress;
        this.egress = egress;
        this.src = new InetAddress(src);
        this.dst = new InetAddress(dst);
    }
    
    public boolean match(final String in, final String out, final Packet p) {
        return this.match(in, out) && this.match(p);
    }
    
    public NatSelector invert() {
        return new NatSelector(this.egress, this.ingress, this.dst, this.src);
    }
    
    boolean match(final String in, final String out) {
        return this.ingress.equals(in) && this.egress.equals(out);
    }
    
    boolean match(final Packet p) {
        boolean result = false;
        switch (p.matching) {
            case Normal: {
                result = (this.src.contains(p.src.getAddress()) && this.dst.contains(p.dst.getAddress()));
                break;
            }
            case AnySrc: {
                result = this.dst.contains(p.dst.getAddress());
                break;
            }
            case AnyDst: {
                result = this.src.contains(p.src.getAddress());
                break;
            }
        }
        return result;
    }
    
    boolean isFlat() {
        return (this.src.isHost() || this.src.isAny()) && (this.dst.isHost() || this.dst.isAny());
    }
}
