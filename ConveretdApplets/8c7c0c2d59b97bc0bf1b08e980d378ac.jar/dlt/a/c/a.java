// 
// Decompiled by Procyon v0.5.30
// 

package dlt.a.c;

import dlt.a.b.g;
import dlt.a.b.c;
import dlt.a.b.h;
import java.awt.Color;

public class a
{
    private boolean do;
    private f[] for;
    private Color a;
    private h if;
    
    public a(final f[] for1, final Color a) {
        this.do = false;
        this.for = new f[3];
        this.for = for1;
        this.a = a;
        this.if = new h(new c[] { this.for[0].a(), this.for[1].a(), this.for[2].a() });
        for (int i = 0; i < this.for.length; ++i) {
            this.for[i].if(this.if);
        }
    }
    
    public a(final f[] for1, final Color a, final h if1) {
        this.do = false;
        this.for = new f[3];
        this.for = for1;
        this.a = a;
        this.if = if1;
    }
    
    public f[] new() {
        return this.for;
    }
    
    public Color if() {
        return this.a;
    }
    
    public h a() {
        return this.if;
    }
    
    public void int() {
        this.if.a(new c[] { this.for[0].a(), this.for[1].a(), this.for[2].a() });
    }
    
    public void for() {
        this.do = false;
    }
    
    public void a(final c c) {
        this.do = (g.if(this.if, new g(this.for[0].a(), c)) > 0.0);
    }
    
    public boolean do() {
        return this.do;
    }
}
