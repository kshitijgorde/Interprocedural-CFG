// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter;

import ji.filter.tiff.hm;
import ji.awt.c;
import ji.filter.tiff.hl;

public class hk
{
    public int a;
    public int b;
    public int c;
    public boolean d;
    public boolean e;
    public boolean f;
    public boolean g;
    public hl[] h;
    private c i;
    private c j;
    
    public hk() {
        this.a = 1;
        this.b = 0;
        this.d = false;
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = null;
        this.i = null;
        this.j = null;
    }
    
    public final void a(final hm hm) {
        if (this.i == null) {
            this.i = new c("jiTIFWriterImageData1");
        }
        if (this.i.d("".concat(String.valueOf(String.valueOf(hm.a)))) != null) {
            this.i.a("".concat(String.valueOf(String.valueOf(hm.a))));
        }
        this.i.a("".concat(String.valueOf(String.valueOf(hm.a))), hm);
    }
    
    public final hm a(final int n) {
        if (this.i != null) {
            return (hm)this.i.b(n);
        }
        return null;
    }
    
    public final int a() {
        if (this.i != null) {
            return this.i.b();
        }
        return 0;
    }
    
    public final void b() {
        try {
            if (this.i != null) {
                this.i.c();
                this.i = null;
            }
        }
        catch (Exception ex) {}
    }
    
    public final hm b(final int n) {
        if (this.j != null) {
            return (hm)this.j.b(n);
        }
        return null;
    }
    
    public final int c() {
        if (this.j != null) {
            return this.j.b();
        }
        return 0;
    }
    
    public final void d() {
        try {
            if (this.j != null) {
                this.j.c();
                this.j = null;
            }
        }
        catch (Exception ex) {}
    }
    
    public void e() {
        try {
            if (this.h != null) {
                for (int i = 0; i < this.h.length; ++i) {
                    this.h[i].d = null;
                    this.h[i] = null;
                }
                this.h = null;
            }
        }
        catch (Exception ex) {}
        this.b();
        this.d();
    }
}
