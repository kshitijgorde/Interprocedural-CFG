// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter.jpeg2k.tile;

import ji.util.i;
import ji.image.ds;
import ji.io.h;
import ji.util.d;
import ji.io.q;
import ji.io.ac;

public class oy implements k5
{
    private ac a;
    private String b;
    private Object c;
    private String d;
    private boolean e;
    private int f;
    
    public oy(final Object c, final String d, final int f) throws Exception {
        this.f = 0;
        this.c = c;
        this.d = d;
        this.f = f;
        this.b = q.a(c, d).n();
        this.a = new ac(this.b, true, false, 0, false, c, d);
    }
    
    public Object a() {
        return this.a;
    }
    
    public void a(final Object o) {
        this.a = (ac)o;
    }
    
    protected void finalize() throws Throwable {
        super.finalize();
        if (this.a != null) {
            try {
                this.a.a(this.c);
            }
            catch (Exception ex) {
                if (ji.util.d.cy()) {
                    h.d(this.d, "Error closing temp file ".concat(String.valueOf(String.valueOf(this.b))));
                }
            }
        }
        try {
            if (ac.d(this.b, this.d)) {
                ac.c(this.b, this.d);
            }
        }
        catch (Exception ex2) {
            if (ji.util.d.cy()) {
                h.d(this.d, "Error removing temp file ".concat(String.valueOf(String.valueOf(this.b))));
            }
        }
    }
    
    public boolean a(final ds ds, final int n) throws Exception {
        try {
            this.a.a(this.c);
        }
        catch (Exception ex) {
            h.b(this.d, "Attempted close on temp file failed - probably already closed", 0);
        }
        this.a = new ac(this.b, false, false, 0, false, this.c, this.d);
        int n2 = 0;
        try {
            if (this.f == 24) {
                if (i.c(87)) {
                    ds.e(true);
                }
                final byte[] array = new byte[n * 4];
                while (this.a.a(array) != -1 && !this.e) {
                    ds.a(array, array.length, this.c, n2 + 1, n2 + 2, true);
                    ++n2;
                }
            }
            else {
                final byte[] array2 = new byte[n];
                while (this.a.a(array2) != -1 && !this.e) {
                    ds.b(array2, array2.length, this.c, n2 + 1, n2 + 2, true);
                    ++n2;
                }
            }
        }
        catch (Exception ex2) {
            this.a.a(this.c);
        }
        finally {
            try {
                this.a.a(this.c);
            }
            catch (Exception ex3) {
                System.err.println("Eror closing temp file ".concat(String.valueOf(String.valueOf(this.b))));
            }
            try {
                if (ac.d(this.b, this.d)) {
                    ac.c(this.b, this.d);
                }
            }
            catch (Exception ex4) {
                if (ji.util.d.cy()) {
                    h.d(this.d, "Error removing temp file ".concat(String.valueOf(String.valueOf(this.b))));
                }
            }
        }
        return true;
    }
    
    public void c() {
        this.a = null;
    }
    
    public void b() {
        this.e = true;
    }
}
