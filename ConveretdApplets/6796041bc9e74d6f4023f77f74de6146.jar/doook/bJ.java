// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Component;
import java.awt.Label;

class bJ extends o
{
    Label b;
    Label c;
    Label d;
    Label e;
    Label f;
    Label g;
    Label h;
    c b;
    private final cg b;
    
    public void d() {
    }
    
    public void c() {
    }
    
    public bJ(final cg b) {
        super(ao.e("Status"), cg.a(b));
        this.b = b;
        this.b = new Label();
        this.c = new Label();
        this.d = new Label();
        this.e = new Label();
        this.f = new Label(ao.e("N/A"));
        this.g = new Label();
        this.h = new Label();
        this.b = new c();
        this.a(ao.e("Running Since: "), this.g);
        this.a(ao.e("Current Connections: "), this.c);
        this.a(ao.e("Maximum Connections: "), this.d);
        this.a(ao.e("Total Hits: "), this.b);
        this.a(ao.e("Active Threads: "), this.h);
        this.a(ao.e("Last Connection: "), this.f);
        this.a(ao.e("Applet Location:"), this.b);
    }
}
