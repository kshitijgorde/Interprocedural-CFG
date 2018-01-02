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
import a.a.a.o;
import java.security.Security;
import a.a.a.n;
import a.a.a.q;
import a.a.a.l;
import a.a.a.t;
import a.a.a.p;
import java.applet.Applet;
import a.a.a.g;

// 
// Decompiled by Procyon v0.5.30
// 

final class f implements Runnable
{
    private /* synthetic */ String a;
    private /* synthetic */ RunApplet b;
    
    f(final RunApplet b, final String a) {
        this.b = b;
        this.a = a;
    }
    
    public final void run() {
        boolean b = false;
        final String a;
        if ((a = this.b.h.a(b.b)) == null) {
            a.a.a.f.c("No runClass param given.");
            return;
        }
        final g g;
        if (!(g = new g("RunApplet")).a()) {
            this.b.a.a(this.a, "locked");
            return;
        }
        try {
            final g g2;
            if (!(g2 = new g(a)).a()) {
                this.b.a.a(this.a, "locked");
                return;
            }
            g2.b();
            try {
                RunApplet.a(this.b, a);
                b = true;
            }
            catch (Exception ex) {
                a.a.a.f.a("Failed while trying to setup run.", ex);
            }
        }
        finally {
            g.b();
        }
        final f f;
        f.b.a.a(f.a, b ? "true" : "false");
    }
}
