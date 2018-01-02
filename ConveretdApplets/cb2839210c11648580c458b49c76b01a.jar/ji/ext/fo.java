// 
// Decompiled by Procyon v0.5.30
// 

package ji.ext;

import ji.res.z;
import ji.util.y;
import ji.io.ac;
import ji.v1event.a6;
import ji.util.e;
import ji.v1event.af;
import java.awt.Component;
import ji.util.d;
import ji.sec.f;
import java.awt.Canvas;

public class fo extends Canvas
{
    private String a;
    private String b;
    private String c;
    private String d;
    
    public fo(final String d) {
        this.a = null;
        this.b = "/";
        this.c = "";
        this.d = null;
        this.d = d;
        try {
            this.b = f.a("file.separator", d);
        }
        catch (Exception ex) {}
        if (ji.util.d.eg() && ji.util.d.av(d)) {
            this.c = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.a1(d)))).append(this.b).append("java").append(this.b).append("classes").append(this.b).append("v1com")));
        }
    }
    
    public Object a(final String s, final String s2, final String s3, final int n, final String s4, final String s5, final Component component, final af af, final String s6, final boolean b, final Object[] array) throws Exception {
        String ai = "";
        f4 f4 = null;
        try {
            if (af != null) {
                e.ag(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.b(710, s6)))).append(" ").append(ji.util.d.e("".concat(String.valueOf(String.valueOf(n))), s6)))));
                af.a(new a6(this, 9, "..."));
            }
            ai = e.ai(s6);
            if (ji.util.d.eg() && ji.util.d.av(s6)) {
                this.a(s2, s3, n, component, af, s6);
            }
            f4 = (f4)ji.util.d.a2(s);
            f4.a(s4, s5, n, component, af, s6, b, array);
        }
        finally {
            e.ag(ai);
            if (af != null) {
                af.a(new a6(this, 13, ""));
            }
        }
        return f4;
    }
    
    public void a() throws Exception {
        if (ji.util.d.eg() && ji.util.d.av(this.d)) {
            this.b();
        }
    }
    
    private void a(final String s, final String s2, final int n, final Component component, final af af, final String s3) throws Exception {
        try {
            e.a(true, n, null, null);
            if (ji.util.d.a0(s3)) {
                ac.e(this.a = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.c))).append(this.b).append("daeja").append(this.b).append("v1").append(this.b).append("v1base"))), s3);
                if (s != null && s2 != null) {
                    final byte[] a = z.a(component, s, ji.util.d.b(s, ji.util.d.bh(s), "v1"), af, null, s3, new y());
                    if (a == null) {
                        throw new Exception(ji.util.d.b(254, s3));
                    }
                    this.a = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a))).append(this.b).append(s2)));
                    try {
                        ac.c(this.a, s3);
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    final ac ac = new ac(this.a, true, false, 0, component, s3);
                    ac.b(a);
                    ac.a(component);
                }
            }
        }
        finally {
            e.a(false, 0, af, component);
        }
    }
    
    private final void b() {
        try {
            if (this.a != null) {
                ac.c(this.a, this.d);
                this.a = null;
                ac.c(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.c))).append(this.b).append("daeja").append(this.b).append("v1").append(this.b).append("v1base"))), this.d);
                ac.c(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.c))).append(this.b).append("daeja").append(this.b).append("v1"))), this.d);
                ac.c(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.c))).append(this.b).append("daeja"))), this.d);
            }
        }
        catch (Exception ex) {}
    }
}
