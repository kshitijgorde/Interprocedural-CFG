// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter.pdf;

import ji.util.d;
import ji.util.fn;
import ji.document.ap;
import ji.document.es;
import ji.document.ad;
import ji.image.dx;
import ji.filter.fh;

class od
{
    private boolean a;
    public int b;
    public fh c;
    public String d;
    public double e;
    public int f;
    public dx g;
    public ad h;
    private boolean i;
    private boolean j;
    private Object k;
    private Throwable l;
    public es m;
    public ap n;
    public int o;
    
    od(final int b) {
        this.a = true;
        this.b = 0;
        this.c = null;
        this.d = null;
        this.e = 0.0;
        this.f = 0;
        this.g = null;
        this.h = null;
        this.i = false;
        this.j = false;
        this.b = b;
        this.i = this.a;
    }
    
    public final boolean a() {
        return this.i;
    }
    
    public void b() {
        this.j = true;
    }
    
    public void a(final Throwable l) {
        this.l = l;
        this.j = true;
    }
    
    public boolean c() {
        if (this.j && this.l != null) {
            throw new fn(this.l);
        }
        return this.j;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        try {
            switch (this.b) {
                case 4: {
                    sb.append("cmdClose");
                    if (this.g != null && this.g.f != null) {
                        sb.append(":");
                        sb.append(this.g.f);
                        sb.append(":");
                        sb.append(this.g.a0);
                        break;
                    }
                    break;
                }
                case 5: {
                    sb.append("cmdCloseDoc");
                    break;
                }
                case 2: {
                    sb.append("cmdFillDib");
                    if (this.c != null && this.c.d != null && this.c.d.f != null) {
                        sb.append(":");
                        sb.append(this.c.d.f);
                        sb.append(":");
                        sb.append(this.c.d.a0);
                        break;
                    }
                    break;
                }
                case 6: {
                    sb.append("cmdFind");
                    if (this.m != null) {
                        sb.append(":");
                        sb.append(this.m.a());
                        sb.append(":");
                        sb.append(this.m.e());
                        sb.append(":");
                        sb.append(this.m.f());
                        break;
                    }
                    break;
                }
                case 3: {
                    sb.append("cmdGetAnnotations");
                    if (this.g != null) {
                        sb.append(":");
                        sb.append(this.g.f);
                        sb.append(":");
                        sb.append(this.f);
                        break;
                    }
                    break;
                }
                case 1: {
                    sb.append("cmdLoadHeader");
                    if (this.c != null && this.c.d != null && this.c.d.f != null) {
                        sb.append(":");
                        sb.append(this.c.d.f);
                        sb.append(":");
                        sb.append(this.c.d.a0);
                        sb.append(":");
                        sb.append(this.e);
                        break;
                    }
                    break;
                }
            }
        }
        catch (Throwable t) {
            ji.util.d.a(t);
        }
        return sb.toString();
    }
    
    public void a(final Object k) {
        this.k = k;
    }
    
    public Object d() {
        return this.k;
    }
}
