// 
// Decompiled by Procyon v0.5.30
// 

package ji.v1event;

import ji.awt.bb;
import ji.res.z;
import ji.util.d;

public class ex implements Runnable
{
    private af a;
    private String b;
    int c;
    long d;
    boolean e;
    boolean f;
    
    public ex(final String b, final af a, final int c, final int n, final boolean e) {
        this.e = e;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = System.currentTimeMillis() + n;
        this.b();
    }
    
    public void run() {
        while (this.f) {
            try {
                if (this.f && this.d < System.currentTimeMillis()) {
                    this.a.a(new a6(this, this.c, ""));
                    this.f = false;
                }
                else {
                    ji.util.d.b(100, 1080, this.b);
                }
            }
            catch (Exception ex) {}
        }
        this.a = null;
    }
    
    private final void b() {
        if (this.a == null) {
            return;
        }
        try {
            if (this.e || z.d(this.b)) {
                this.f = true;
                final bb bb = new bb(this.b, this);
                ji.util.d.b(bb);
                bb.start();
            }
        }
        catch (Exception ex) {}
    }
    
    public final void a() {
        this.f = false;
    }
}
