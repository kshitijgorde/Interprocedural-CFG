// 
// Decompiled by Procyon v0.5.30
// 

package ji.ext;

import ji.sec.f;
import ji.io.ac;
import ji.util.y;
import ji.res.z;
import ji.util.e;
import ji.util.d;
import ji.v1event.af;
import java.awt.Component;
import ji.io.q;
import java.awt.Canvas;

public abstract class f4 extends Canvas
{
    public abstract boolean isLoaded();
    
    public abstract void testLibrary(final Object[] p0) throws Exception;
    
    public abstract boolean isTested();
    
    public abstract boolean isFileCacheSet();
    
    public abstract String getLibFileName(final String p0);
    
    public abstract void setLibFileName(final String p0, final String p1);
    
    public abstract void setLoaded(final boolean p0);
    
    public abstract void setFileCache(final q p0);
    
    public abstract q getFileCache();
    
    public abstract void setTested(final boolean p0);
    
    public void a(final String s, final String s2, final int n, final Component component, final af af, final String s3, final boolean b, final Object[] array) {
        if (!this.isTested() && d.a0(s3)) {
            try {
                byte[] a = null;
                e.a(true, n, null, null);
                String s4 = z.a(component, s, d.b(s, d.bh(s), "v1"), af, s3, component);
                if (s4 == null) {
                    a = z.a(component, s, d.b(s, d.bh(s), "v1"), af, null, s3, true, new y());
                }
                if (!this.isFileCacheSet()) {
                    this.setFileCache(q.a(component, s3));
                }
                if (d.eg() && d.av(s3)) {
                    final String property = System.getProperty("file.separator");
                    this.setLibFileName(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(d.a1(s3)))).append(property).append("java").append(property).append("bin").append(property).append(s2))), s3);
                    try {
                        ac.c(this.getLibFileName(s3), s3);
                    }
                    catch (Exception ex3) {}
                    if (s4 != null) {
                        try {
                            final ac ac = new ac(s4, false, false, 0, component, s3);
                            a = new byte[(int)ac.v()];
                            ac.a(a);
                            ac.a(component);
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                else {
                    s4 = z.a(component, s, d.b(s, d.bh(s), "v1"), af, s3, component);
                    if (s4 == null) {
                        this.setLibFileName(this.getFileCache().o(), s3);
                    }
                    else {
                        this.setLibFileName(s4, s3);
                    }
                }
                Label_0386: {
                    if (s4 != null) {
                        if (!d.eg() || !d.av(s3)) {
                            break Label_0386;
                        }
                    }
                    try {
                        final ac ac2 = new ac(this.getLibFileName(s3), true, false, 0, component, s3);
                        ac2.b(a);
                        ac2.a(component);
                    }
                    catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                }
                if (!b) {
                    f.a(this.getLibFileName(s3), component, s3, this.getFileCache().o());
                }
                this.setLoaded(true);
            }
            catch (Throwable t) {
                t.printStackTrace();
            }
            finally {
                e.a(false, 0, af, component);
            }
        }
        try {
            if (this.isLoaded()) {
                this.testLibrary(array);
            }
        }
        catch (Throwable t2) {
            this.setLoaded(false);
            t2.printStackTrace();
        }
        this.setTested(true);
    }
}
