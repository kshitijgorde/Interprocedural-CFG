import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import a.a.a.r;
import java.util.ArrayList;
import java.io.File;
import java.io.FileOutputStream;
import java.net.Proxy;
import a.a.a.a;
import java.net.URL;
import a.a.a.i;
import a.a.a.f;
import a.a.a.o;
import java.security.Security;
import a.a.a.n;
import a.a.a.q;
import a.a.a.l;
import a.a.a.t;
import a.a.a.p;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class RunApplet extends Applet
{
    private p a;
    private t b;
    private boolean c;
    private boolean d;
    private boolean e;
    private l f;
    private q g;
    private n h;
    
    public synchronized void init() {
        this.a = new p(this);
        try {
            Security.setProperty("networkaddress.cache.ttl", "0");
        }
        catch (Exception ex) {
            this.e = true;
            return;
        }
        this.h = new n(this);
        this.g = new q(this);
        if (!this.g.b()) {
            this.d = true;
            return;
        }
        o.a(this.g.a());
        a.a.a.f.b("--- Init RunApplet: " + this);
        this.b = new t("RunApplet");
        a.a.a.f.b("Setting up to start callbacks from start().");
        this.c = true;
        this.f = new l(new g(this));
    }
    
    public synchronized void start() {
        a.a.a.f.b("--- Starting RunApplet: " + this);
        final n n = new n(this);
        if (this.e) {
            final String a;
            if ((a = n.a(i.c)) != null) {
                this.a.a(a, new String[0]);
            }
            return;
        }
        if (this.d) {
            final String a2;
            if ((a2 = n.a(i.b)) != null) {
                final String s = a2;
                a.a.a.f.b("Adding doIfVerifyFailed with callback: " + s);
                this.b.a(new h(this, s));
            }
            return;
        }
        if (this.c) {
            final String a3;
            if ((a3 = n.a(i.a)) != null) {
                final String s2 = a3;
                a.a.a.f.b("Adding doSetup with callback: " + s2);
                this.b.a(new e(this, s2));
            }
            final String a4;
            if ((a4 = n.a(b.a)) != null) {
                final String s3 = a4;
                a.a.a.f.b("Adding doRun with callback: " + s3);
                this.b.a(new f(this, s3));
            }
            final String a5;
            if ((a5 = n.a(b.e)) != null) {
                final String s4 = a5;
                final String a6 = n.a(b.f);
                final String s5 = s4;
                a.a.a.f.b("Adding doUploadLog with callback: " + s5);
                this.b.a(new c(this, a6, s5));
            }
            this.c = false;
        }
    }
    
    public synchronized void stop() {
        a.a.a.f.b("--- Stopping RunApplet: " + this);
    }
    
    public synchronized void destroy() {
        a.a.a.f.b("--- Destroy RunApplet: " + this);
        if (this.f != null) {
            this.f.a();
        }
        if (this.b != null) {
            this.b.a();
        }
        if (this.a != null) {
            this.a.a();
        }
        this.removeAll();
        this.a = null;
        this.b = null;
        this.g = null;
        this.h = null;
        this.f = null;
    }
    
    private static String a(final URL url) {
        return url.getFile().substring(url.getFile().lastIndexOf("/") + 1);
    }
}
