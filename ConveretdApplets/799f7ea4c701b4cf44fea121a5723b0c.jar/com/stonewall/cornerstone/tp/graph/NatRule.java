// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.graph;

public class NatRule
{
    NatPolicy policy;
    final String id;
    final NatSelector selector;
    final NatAction sx;
    final NatAction dx;
    
    public NatRule(final String id, final NatSelector selector, final NatAction sx, final NatAction dx) {
        this.policy = null;
        this.id = id;
        this.selector = selector;
        this.sx = ((sx == null) ? NatPolicy.None : sx);
        this.dx = ((dx == null) ? NatPolicy.None : dx);
    }
    
    public NatRule(final String id, final NatSelector selector, final NatAction sx) {
        this(id, selector, sx, NatPolicy.None);
    }
    
    public boolean match(final String in, final String out, final Packet p) {
        return this.selector.match(in, out, p);
    }
    
    public boolean mayInvert() {
        return this.isFlat() && this.selector.isFlat();
    }
    
    public NatRule invert() {
        NatRule result = null;
        if (this.mayInvert()) {
            NetObject src = this.selector.dst;
            NetObject dst = this.selector.src;
            if (this.sx.method() == NatAction.Method.direct) {
                dst = this.sx.translate();
            }
            if (this.dx.method() == NatAction.Method.direct) {
                src = this.dx.translate();
            }
            final NatSelector s = new NatSelector(this.selector.egress, this.selector.ingress, src, dst);
            final NatAction sx = this.selector.dst.isAny() ? NatPolicy.None : new NatAction(this.selector.dst);
            final NatAction dx = this.selector.src.isAny() ? NatPolicy.None : new NatAction(this.selector.src);
            result = new NatRule(this.id, s, sx, dx);
        }
        return result;
    }
    
    @Override
    public String toString() {
        return this.toString(0);
    }
    
    public String toString(final int indent) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indent; ++i) {
            sb.append('\t');
        }
        final String _indent = sb.toString();
        sb = new StringBuilder();
        sb.append(_indent);
        sb.append(this.selector.ingress);
        sb.append(", ");
        sb.append(this.selector.egress);
        sb.append(" : ");
        sb.append(this.selector.src);
        sb.append(" --> ");
        sb.append(this.selector.dst);
        sb.append('\n');
        sb.append(_indent);
        sb.append("\tsx: ");
        sb.append(this.sx.toString(this));
        sb.append("\n\t");
        sb.append(_indent);
        sb.append("dx: ");
        sb.append(this.dx.toString(this));
        return sb.toString();
    }
    
    boolean isFlat() {
        return this.sx.method() != NatAction.Method.pool && this.dx.method() != NatAction.Method.pool;
    }
    
    Graph graph() {
        return (this.policy != null) ? this.policy.graph() : Graph.instance();
    }
    
    NatPolicy getPolicy() {
        return this.policy;
    }
    
    void setPolicy(final NatPolicy p) {
        this.policy = p;
    }
    
    NatAction sx() {
        return this.sx;
    }
    
    NatAction dx() {
        return this.dx;
    }
}
