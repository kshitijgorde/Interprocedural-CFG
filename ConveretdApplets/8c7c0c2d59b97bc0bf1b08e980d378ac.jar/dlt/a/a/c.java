// 
// Decompiled by Procyon v0.5.30
// 

package dlt.a.a;

import dlt.a.b.h;

public class c
{
    private h if;
    private dlt.a.b.c a;
    private dlt.a.b.c for;
    private dlt.a.b.c do;
    
    public c(final dlt.a.b.c a, final dlt.a.b.c for1, final dlt.a.b.c do1) {
        this.a = a;
        this.for = for1;
        this.do = do1;
        this.if = new h(new dlt.a.b.c[] { a, for1, do1 });
    }
    
    public h if() {
        return this.if;
    }
    
    public dlt.a.b.c a() {
        return this.for;
    }
}
