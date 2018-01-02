import java.io.Serializable;
import java.util.List;
import a.a.a.r;
import java.util.ArrayList;
import java.io.File;
import java.io.FileOutputStream;
import java.net.Proxy;
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
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.io.OutputStream;
import a.a.a.f;

// 
// Decompiled by Procyon v0.5.30
// 

final class c implements Runnable
{
    private /* synthetic */ String a;
    private /* synthetic */ String b;
    private /* synthetic */ RunApplet c;
    
    c(final RunApplet c, final String a, final String b) {
        this.c = c;
        this.a = a;
        this.b = b;
    }
    
    public final void run() {
        try {
            final a a;
            f.a(a = new a(this, 1048576));
            this.c.a.a(this.b, "done", a.a.a.a.a(this.a, Collections.emptyMap(), new ByteArrayInputStream(a.toByteArray(), 0, a.size())).trim());
        }
        catch (IOException ex) {
            f.a(ex);
            this.c.a.a(this.b, "error");
        }
    }
}
