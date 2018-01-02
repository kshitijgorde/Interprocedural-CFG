import java.util.Enumeration;
import java.net.URLConnection;
import java.util.StringTokenizer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.util.zip.GZIPInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import au.com.rocketdog.project.awc.applet.Main;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class c extends d
{
    private Hashtable a;
    
    public c() {
        this.a = new Hashtable();
        this.setName(Main.p.a("listupdater.room.name"));
        this.a(h.f().at);
        this.a(60L);
        try {
            this.a(new URL("http://www.anywebcam.com/res/channels" + n.b().s() + ".gz?" + Math.random()));
        }
        catch (MalformedURLException ex) {
            b.a(ex, 3);
        }
    }
    
    public void a() {
        h.ac.clear();
        this.a.clear();
    }
    
    public void b() {
        h.ac.clear();
        this.a.clear();
    }
    
    public void c() {
        this.a.clear();
        try {
            final h f = h.f();
            final URLConnection openConnection = this.f().openConnection();
            openConnection.setUseCaches(false);
            openConnection.connect();
            String line;
            while ((line = new BufferedReader(new InputStreamReader(new BufferedInputStream(new GZIPInputStream(openConnection.getInputStream())))).readLine()) != null) {
                this.a.put(new StringTokenizer(line, " ").nextToken(), line);
            }
            final Enumeration<Object> keys = h.ac.keys();
            while (keys.hasMoreElements()) {
                final String string = keys.nextElement().toString();
                if (!this.a.containsKey(string)) {
                    f.b(string);
                }
            }
            final Enumeration<Object> keys2 = this.a.keys();
            while (keys2.hasMoreElements()) {
                final String string2 = keys2.nextElement().toString();
                if (!h.ac.containsKey(string2)) {
                    final bj bj = new bj(this.a.get(string2).toString());
                    h.ac.put(string2, bj);
                    f.a(bj);
                }
                else {
                    if (h.c(string2).toString().equalsIgnoreCase(this.a.get(string2).toString())) {
                        continue;
                    }
                    final bj c = h.c(string2);
                    final bj bj2 = new bj(this.a.get(string2).toString());
                    c.b(bj2.j());
                    c.f(bj2.v());
                    c.g(bj2.w());
                    c.c(bj2.q());
                    c.a(bj2.f());
                    c.c(bj2.e());
                    c.d(bj2.c());
                    c.d(bj2.t());
                    c.e(bj2.u());
                    c.a(bj2.r());
                }
            }
        }
        catch (Exception ex) {
            b.a(ex, 3);
        }
    }
}
