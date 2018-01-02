// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Component;

class bI extends o
{
    l f;
    private final cg a;
    
    public void d() {
    }
    
    public void c() {
    }
    
    public bI(final cg a) {
        super(ao.e("Sites"), cg.a(a));
        this.a = a;
        (this.f = new l()).resize(630, 300);
        final j j = new j(ao.e("Name"), "name");
        final j i = new j(ao.e("Max"));
        final j k = new j(ao.e("Current"));
        final j l = new j(ao.e("Hits"));
        final j m = new j(ao.e("Auto"));
        final j j2 = new j("ID");
        final j j3 = new j(ao.e("Last Connection"));
        final j j4 = new j(ao.e("Service End Date"));
        j.b(100);
        i.b(43);
        k.b(56);
        l.b(43);
        m.b(43);
        j2.b(43);
        j3.b(200);
        j.b(true);
        i.b(true);
        l.b(true);
        m.b(true);
        j2.b(true);
        k.b(true);
        j.c(true);
        i.c(true);
        l.c(true);
        m.c(true);
        j2.c(true);
        k.c(true);
        j3.c(true);
        j4.c(true);
        this.f.a(true);
        this.f.a(j2);
        this.f.b(j2);
        this.f.b(j);
        this.f.b(k);
        this.f.b(i);
        this.f.b(l);
        this.f.b(m);
        this.f.b(j3);
        this.f.b(j4);
        this.a(new aR(this.f), 1, 1.0f, 1.0f);
    }
}
