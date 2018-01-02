// 
// Decompiled by Procyon v0.5.30
// 

package ji.ext;

import java.awt.Window;
import ji.io.h;
import ji.util.i;
import ji.util.e;
import ji.v1event.af;
import java.awt.Component;
import ji.util.d;
import ji.util.m;
import ji.applet.jiApplet;

public class cr implements n, cs, cb
{
    private int a;
    private boolean b;
    private jiJNIUtil c;
    private jiApplet d;
    private m e;
    private v f;
    private Object g;
    
    public cr(final jiApplet d) throws Exception {
        this.a = 0;
        this.b = false;
        this.g = new Object();
        if (ji.util.d.ak(d.getInstanceId()) || ji.util.d.av(d.getInstanceId())) {
            this.d = d;
            this.e = new m(ji.util.d.a2("daeja.ji.jiex1"));
            this.a("Creating instance of ".concat(String.valueOf(String.valueOf(this.getClass().getName()))));
            if (this.a()) {
                this.a("Retrieving parent window handle");
                final int a = this.a(d);
                if (a > 0) {
                    this.c = new jiJNIUtil(d.getInstanceId(), d);
                    try {
                        this.a("Adding shutdown callback to external DLL");
                        final Object a2 = this.e.a("addShutdownCallback", d, new Integer(a), this, this.c);
                        if (a2 != null && a2 instanceof Integer) {
                            this.a = (int)a2;
                        }
                        else {
                            this.a("unable to add shutdown callback handler");
                        }
                    }
                    catch (UnsatisfiedLinkError unsatisfiedLinkError) {
                        this.a("UnsatisfiedLinkError when trying to use native method.");
                    }
                }
                else {
                    this.a("Could not obtain parent window handle, not installing shutdown callback");
                }
            }
        }
    }
    
    public boolean a(final Object[] array) throws Exception {
        this.a("Testing library");
        this.e.c("jiCheck");
        this.a("Library test complete");
        return true;
    }
    
    public boolean h(final String s) {
        this.a("releaseResources(String parentId) called");
        try {
            if (this.d != null && this.d.getInstanceId().equals(s)) {
                this.c();
                return true;
            }
        }
        catch (Exception ex) {
            this.a("An exception occurred when releasing resources: ".concat(String.valueOf(String.valueOf(ex.getMessage()))));
        }
        return false;
    }
    
    private boolean a() {
        boolean a = false;
        this.a("initNativeLibrary() called");
        synchronized (this.g) {
            this.f = new v();
            if (this.d != null) {
                this.f.b(this.d, this.d.getInstanceId(), null);
            }
            this.a("initNativeLibrary() proceeding...");
            try {
                if (!ji.util.e.av() && !ji.util.d.dp() && (ji.util.d.em() || ji.util.d.av(this.d.getInstanceId()))) {
                    this.a("jExt already loaded: ".concat(String.valueOf(String.valueOf(ji.util.d.lx))));
                    if (!ji.util.d.lx) {
                        this.a("Library already loaded: ".concat(String.valueOf(String.valueOf(a))));
                        if (!a && !ji.util.d.lx) {
                            this.a("about to install external DLL library");
                            a = w.a(this.d, this.d.getInstanceId(), null, this, "jiex1", 767, 1, false);
                            if ((ji.util.d.lx = a) && i.c(124)) {
                                this.a("initialising logging for jiex1");
                                try {
                                    if (this.e != null) {
                                        this.e.a("setDebugging", new Boolean(true));
                                    }
                                    else {
                                        this.a("jiex1Invoker object is null");
                                    }
                                }
                                catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    }
                    else {
                        a = true;
                    }
                    this.a("library loaded=".concat(String.valueOf(String.valueOf(a))));
                }
            }
            catch (Exception ex2) {
                this.a("installation of external DLL library failed: ".concat(String.valueOf(String.valueOf(ex2.getMessage()))));
                a = false;
            }
        }
        // monitorexit(this.g)
        return a;
    }
    
    private final int a(final Component component) {
        if (this.f != null && this.d != null) {
            return this.f.a(this.d.getInstanceId(), component);
        }
        return -1;
    }
    
    private void c() {
        this.a("releaseResources() called");
        try {
            this.a("releaseResources thread=".concat(String.valueOf(String.valueOf(Thread.currentThread().getName()))));
            if (this.a > 0) {
                this.e.a("removeShutdownCallback", this.d, new Integer(this.a), this);
            }
        }
        catch (UnsatisfiedLinkError unsatisfiedLinkError) {
            this.a("UnsatisfiedLinkError when trying to use native method");
        }
        catch (Exception ex) {
            this.a("problem when removing ourselves as a shutdown callback from external DLL");
        }
        try {
            if (this.c != null) {
                this.c.releaseResources();
                this.c = null;
            }
        }
        catch (Exception ex2) {}
        if (this.d != null) {
            this.d = null;
        }
    }
    
    public void b() {
        this.a("shutdown() called thread=".concat(String.valueOf(String.valueOf(Thread.currentThread().getName()))));
        this.c();
    }
    
    private void a(final String s) {
        if (ji.util.d.cy() || i.c(124)) {
            if (this.d != null) {
                h.d(this.d.getInstanceId(), s);
            }
            else {
                h.d(null, s);
            }
        }
    }
    
    public void a(final String s, final Window window, final jiApplet jiApplet) {
    }
    
    public void b(final Window window) {
    }
    
    public void a(final Window window) {
    }
}
