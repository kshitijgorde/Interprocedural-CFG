// 
// Decompiled by Procyon v0.5.30
// 

package ji.res;

import ji.util.d;
import ji.util.e;
import java.net.URL;

public class jiClassLoaderWrapper
{
    la a;
    lb b;
    
    public jiClassLoaderWrapper() {
        this.a = null;
        this.b = null;
    }
    
    public void init(final Class clazz, final URL[] array, final String s) throws Exception {
        if (e.av()) {
            this.a = new la(d.a3("java.lang.String"), array, s);
        }
        else {
            this.b = new lb(d.a3("java.lang.String"), s);
        }
    }
    
    public Class loadClass(final String s) throws ClassNotFoundException {
        if (this.a != null) {
            return this.a.loadClass(s);
        }
        return this.b.loadClass(s);
    }
    
    public void setCustom(final boolean b) {
        if (this.a != null) {
            this.a.a(b);
        }
        else {
            this.b.a(b);
        }
    }
    
    public final void releaseResources() {
        try {
            if (this.b != null) {
                this.b.a();
                this.b = null;
            }
            if (this.a != null) {
                this.a.a();
                this.a = null;
            }
        }
        catch (Exception ex) {}
    }
}
