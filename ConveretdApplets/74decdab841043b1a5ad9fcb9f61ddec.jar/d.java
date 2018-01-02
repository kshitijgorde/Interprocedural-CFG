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
import java.io.InputStream;
import java.io.IOException;
import a.a.a.f;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import a.a.a.h;
import a.a.a.k;

// 
// Decompiled by Procyon v0.5.30
// 

final class d implements Runnable
{
    private /* synthetic */ Process a;
    private /* synthetic */ RunApplet b;
    
    d(final RunApplet b, final Process a) {
        this.b = b;
        this.a = a;
    }
    
    public final void run() {
        try {
            final InputStream inputStream = this.a.getInputStream();
            final k k = new k(this.b.a);
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            try {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    f.b("From subprocess: " + line);
                    k.a(line);
                }
            }
            finally {
                inputStream.close();
            }
        }
        catch (IOException ex) {
            f.a(ex);
        }
    }
}
