// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Label;

final class dq extends G
{
    Label q;
    Label w;
    Label e;
    Label r;
    Label t;
    Label y;
    Label u;
    Label i;
    H q;
    
    public final void w() {
    }
    
    public final void q() {
    }
    
    public dq(final dn dn, final dn dn2) {
        final String q = eb.q("Status");
        dn.q(dn);
        super(q);
        this.q = new Label();
        this.w = new Label();
        this.e = new Label();
        this.r = new Label();
        new Label();
        this.t = new Label(eb.q("N/A"));
        this.y = new Label();
        this.u = new Label();
        this.i = new Label();
        this.q = new H();
        this.q(eb.q("Running Since: "), this.y);
        this.q(eb.q("Current Connections: "), this.w);
        this.q(eb.q("Current Vitual Connections: "), this.e);
        this.q(eb.q("Maximum Connections: "), this.r);
        this.q(eb.q("Total Hits: "), this.q);
        this.q(eb.q("Active Threads: "), this.u);
        this.q(eb.q("Free Memory: "), this.i);
        this.q(eb.q("Last Connection: "), this.t);
        this.q(eb.q("Applet Location:"), this.q);
    }
}
