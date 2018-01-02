// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.graph;

public class Link extends GraphObject
{
    protected boolean enabled;
    protected final LT type;
    protected final Endpoint[] endpoints;
    
    public static Link tunnel(final String id, final Endpoint a, final Endpoint b) {
        return new Link(id, LT.Tunnel, a, b).enabled(true);
    }
    
    public Link(final String id, final Endpoint a, final Endpoint b) {
        this(id, LT.Basic, a, b);
    }
    
    public Link(final String id, final LT type, final Endpoint a, final Endpoint b) {
        super(id, T.Link);
        this.enabled = true;
        this.endpoints = new Endpoint[2];
        this.type = type;
        this.endpoints[0] = a;
        this.endpoints[1] = b;
    }
    
    @Override
    public String name() {
        return "(" + this.endpoints[0].name() + ";" + this.endpoints[1].name() + ")";
    }
    
    public Endpoint[] endpoints() {
        return this.endpoints;
    }
    
    public Endpoint otherEnd(final Endpoint e) {
        return (e.getId() == this.endpoints[0].getId()) ? this.endpoints[1] : this.endpoints[0];
    }
    
    public boolean hasEndpoint(final Endpoint endpoint) {
        return endpoint == this.endpoints[0] || endpoint == this.endpoints[1];
    }
    
    public boolean hasParentOf(final Endpoint endpoint) {
        boolean result = false;
        final GraphObject parent = endpoint.getParent();
        if (parent instanceof Endpoint) {
            final Endpoint pE = (Endpoint)parent;
            result = this.hasEndpoint(pE);
        }
        return result;
    }
    
    public boolean basic() {
        return this.type == LT.Basic;
    }
    
    public boolean tunnel() {
        return this.type == LT.Tunnel;
    }
    
    public boolean enabled() {
        return this.enabled;
    }
    
    public boolean disabled() {
        return !this.enabled;
    }
    
    public Link enabled(final boolean flag) {
        this.enabled = flag;
        return this;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        switch (this.type) {
            case Tunnel: {
                sb.append(this.enabled ? " (T)" : " (t)");
                break;
            }
        }
        sb.append("[");
        sb.append(this.endpoints[0].getId());
        sb.append('/');
        sb.append(this.endpoints[1].getId());
        sb.append(']');
        return sb.toString();
    }
    
    enum LT
    {
        Basic("Basic", 0), 
        Tunnel("Tunnel", 1);
        
        private LT(final String s, final int n) {
        }
    }
}
