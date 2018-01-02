// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.graph;

public class Frame
{
    private Packet packet;
    private boolean pending;
    private Endpoint endpoint;
    private Link link;
    private Constraint.Direction direction;
    private int metric;
    private int depth;
    private long sequence;
    public static boolean verbose;
    
    static {
        Frame.verbose = false;
    }
    
    Frame(final Endpoint e, final Constraint.Direction d, final int m) {
        this.packet = null;
        this.pending = true;
        this.endpoint = null;
        this.link = null;
        this.metric = 0;
        this.depth = 0;
        this.sequence = 0L;
        this.endpoint = e;
        this.direction = d;
        this.metric = m;
    }
    
    Frame(final Endpoint e, final Constraint.Direction d) {
        this(e, d, 0);
    }
    
    public boolean pending() {
        return this.pending;
    }
    
    public void processed() {
        this.pending = false;
    }
    
    public Constraint.Direction direction() {
        return this.direction;
    }
    
    public boolean inbound() {
        return this.direction == Constraint.Direction.Inbound;
    }
    
    public boolean outbound() {
        return this.direction == Constraint.Direction.Outbound;
    }
    
    public Endpoint endpoint() {
        return this.endpoint;
    }
    
    public int metric() {
        return this.metric;
    }
    
    public int depth() {
        return this.depth;
    }
    
    public long sequence() {
        return this.sequence;
    }
    
    public Link link() {
        return this.link;
    }
    
    public void link(final Link l) {
        this.link = l;
    }
    
    @Override
    public String toString() {
        return this.toString("\n\tF/");
    }
    
    public String toString(final String h) {
        final StringBuilder sb = new StringBuilder(h);
        sb.append(" (" + this.sequence + ")");
        sb.append(" pnd:" + this.pending);
        sb.append(" ep:" + this.nm(this.endpoint));
        sb.append(String.valueOf(this.outbound() ? " lnk(ib):" : " lnk(ob):") + ((this.link == null) ? "_" : this.nm(this.link)));
        sb.append(" dir:" + this.direction.name());
        sb.append(" metric:" + this.metric);
        sb.append(" depth:" + this.depth);
        sb.append(" pkt:" + ((this.packet != null) ? this.packet : ""));
        return sb.toString();
    }
    
    public Packet getPacket() {
        return this.packet;
    }
    
    public void setPacket(final Packet p) {
        this.packet = p;
    }
    
    String nm(final GraphObject g) {
        return Frame.verbose ? g.name() : g.getId();
    }
    
    void depth(final int n) {
        this.depth = n;
    }
    
    void sequence(final long s) {
        this.sequence = s;
    }
}
