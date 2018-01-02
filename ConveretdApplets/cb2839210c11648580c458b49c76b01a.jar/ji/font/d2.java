// 
// Decompiled by Procyon v0.5.30
// 

package ji.font;

import ji.image.dw;
import java.awt.Image;

public class d2
{
    public Image a;
    public byte[] b;
    public dw c;
    public int d;
    public dw e;
    public String f;
    public String g;
    public boolean h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    public boolean p;
    
    public d2() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = 0;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = false;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.p = false;
    }
    
    public final void a() {
        try {
            this.a = null;
            this.b = null;
            this.f = null;
            if (this.e != null) {
                this.e.c(12);
                this.e = null;
            }
        }
        catch (Exception ex) {}
        try {
            if (this.c != null) {
                this.c.c(0);
                this.c = null;
            }
        }
        catch (Exception ex2) {}
    }
}
