// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter;

import ji.util.d;
import ji.v1event.af;
import java.awt.Component;
import ji.io.h;
import ji.io.ac;
import java.io.File;
import ji.util.cn;
import ji.document.ad;
import ji.util.e;

public class n2
{
    private String a;
    private String b;
    private String c;
    private String[] d;
    private String[] e;
    private String[] f;
    private String[] g;
    private String[] h;
    
    public n2(final String c, final String b, final String a) {
        this.c = c;
        this.b = b;
        this.a = a;
    }
    
    public void a(final String[] d, final String[] g) {
        this.d = d;
        this.g = g;
    }
    
    public void b(final String[] e, final String[] f) {
        this.e = e;
        this.f = f;
    }
    
    public String[] a(final String s) {
        if (ji.util.e.s(s) && this.e != null && this.e.length > 0) {
            if (this.h == null) {
                this.h = new String[this.d.length + this.e.length];
                System.arraycopy(this.e, 0, this.h, 0, this.e.length);
                System.arraycopy(this.d, 0, this.h, this.e.length, this.d.length);
            }
            return this.h;
        }
        return this.d;
    }
    
    public String a() {
        return String.valueOf(String.valueOf(this.c)).concat(".zip");
    }
    
    public String b() {
        return this.b;
    }
    
    public void a(final ad ad, final String s, final String[] array) {
        this.b(ad, s, array);
        this.a(ad, s);
        this.b(s);
    }
    
    private void b(final String s) {
        if (this.f != null) {
            final String a = cn.a(this.a, s);
            for (int i = 0; i < this.f.length; ++i) {
                final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(a))).append(File.separator).append(this.f[i])));
                try {
                    if (ac.d(value, s)) {
                        ji.io.h.c(s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.b))).append(": Removing Runtime library file: ").append(value))));
                        ac.c(value, s);
                    }
                }
                catch (Exception ex) {}
            }
        }
    }
    
    private void a(final ad ad, final String s) {
        if (ji.util.e.s(s)) {
            ji.io.h.c(s, String.valueOf(String.valueOf(this.b)).concat("Win2000 Unloading and removing Runtime Files."));
            try {
                cn.a(ad, s, null, this.a, this.e, null, true, true, false);
            }
            catch (Exception ex) {
                ji.util.d.a(ex);
            }
        }
    }
    
    private void b(final ad ad, final String s, final String[] array) {
        int length = 0;
        if (array != null) {
            length = array.length;
        }
        int length2 = 0;
        if (this.g != null) {
            length2 = this.g.length;
        }
        final String[] c = ji.util.d.c(this.a(s));
        final String[] array2 = new String[length + c.length + length2];
        if (length > 0) {
            System.arraycopy(array, 0, array2, 0, length);
        }
        System.arraycopy(c, 0, array2, length, c.length);
        if (length2 > 0) {
            System.arraycopy(this.g, 0, array2, length + c.length, this.g.length);
        }
        cn.a(ad, s, null, this.a, array2, null, true, true, false);
    }
}
