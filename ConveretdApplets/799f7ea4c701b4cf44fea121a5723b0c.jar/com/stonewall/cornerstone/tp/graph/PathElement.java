// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.graph;

public class PathElement
{
    private final long sequence;
    private final GraphObject object;
    private final Constraint.Direction direction;
    private final int metric;
    private final int hops;
    
    PathElement(final Frame f) {
        this(f, false);
    }
    
    PathElement(final Frame f, final boolean useLink) {
        this.sequence = f.sequence();
        this.object = (useLink ? f.link() : f.endpoint());
        this.direction = f.direction();
        this.metric = f.metric();
        this.hops = f.depth();
    }
    
    public long sequence() {
        return this.sequence;
    }
    
    public GraphObject object() {
        return this.object;
    }
    
    public GraphObject.T type() {
        return this.object.type();
    }
    
    public boolean type(final GraphObject.T t) {
        return this.object.type(t);
    }
    
    public Constraint.Direction direction() {
        return this.direction;
    }
    
    public int metric() {
        return this.metric;
    }
    
    public int hops() {
        return this.hops;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-9s", this.object.getId()));
        sb.append(String.format(" %-11s", this.tStr()));
        sb.append(String.format(" %-5s", this.dStr()));
        sb.append(String.format(" metric: %03d", this.metric));
        sb.append(String.format(" hops: %03d", this.hops));
        return sb.toString();
    }
    
    private String tStr() {
        final GraphObject.T type = this.object.type();
        String name = type.name();
        switch (type) {
            case Network: {
                name = "net";
                break;
            }
            case Interface: {
                name = "interface";
                break;
            }
            case Link: {
                final Link link = (Link)this.object;
                name = (link.tunnel() ? "tunnel" : "link");
                break;
            }
        }
        return "(" + name + ")";
    }
    
    private String dStr() {
        if (this.object.type(GraphObject.T.Link)) {
            return "(ovr)";
        }
        switch (this.direction) {
            case Inbound: {
                return "(in)";
            }
            case Outbound: {
                return "(out)";
            }
            default: {
                return "(both)";
            }
        }
    }
}
