// 
// Decompiled by Procyon v0.5.30
// 

package dlt.a.c;

import dlt.a.b.a;
import dlt.a.b.h;
import dlt.a.b.c;

public class f
{
    private c int;
    private c for;
    private c a;
    private c if;
    private b do;
    
    public f(final c int1) {
        this.if = int1.if();
        this.int = int1;
        this.for = new c(0.0, 0.0, 0.0);
        this.a = new c(0.0, 0.0, 0.0);
        this.do = new b();
    }
    
    public f(final c for1, final h h, final double n, final c if1) {
        this.if = if1;
        this.int = new c(0.0, 0.0, 0.0);
        this.for = for1;
        this.a = new c(0.0, 0.0, 0.0);
        (this.do = new b()).a(h, n);
    }
    
    public c a() {
        return this.int.if();
    }
    
    public c for() {
        return this.if.if();
    }
    
    public c do() {
        return this.for.if();
    }
    
    public c if() {
        return this.a;
    }
    
    public void do(final a a) {
        a.a(this.int);
    }
    
    public void a(final a a) {
        a.a(this.int, this.for);
    }
    
    public void if(final a a) {
        a.a(this.for, this.for);
    }
    
    public b new() {
        return this.do;
    }
    
    public void int() {
        this.do.a();
    }
    
    public void if(final h h) {
        this.do.a(h);
    }
    
    public double a(final h h) {
        return this.do.if(h);
    }
}
