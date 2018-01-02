// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Label;

final class aD extends cV
{
    Label q;
    Label w;
    Label e;
    Label r;
    Label t;
    Label y;
    Label u;
    Label i;
    u q;
    
    public final void w() {
    }
    
    public final void q() {
    }
    
    public aD(final aS as, final aS as2) {
        super(be.w("Status"));
        this.q = new Label();
        this.w = new Label();
        this.e = new Label();
        this.r = new Label();
        new Label();
        this.t = new Label(be.w("N/A"));
        this.y = new Label();
        this.u = new Label();
        this.i = new Label();
        this.q = new u();
        this.q(be.w("Running Since: "), this.y);
        this.q(be.w("Current Connections: "), this.w);
        this.q(be.w("Current Vitual Connections: "), this.e);
        this.q(be.w("Maximum Connections: "), this.r);
        this.q(be.w("Total Hits: "), this.q);
        this.q(be.w("Active Threads: "), this.u);
        this.q(be.w("Free Memory: "), this.i);
        this.q(be.w("Last Connection: "), this.t);
        this.q(be.w("Applet Location:"), this.q);
    }
}
