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

public class e extends d
{
    private Hashtable a;
    
    public e() {
        this.a = new Hashtable();
        this.setName(Main.p.a("listupdater.cam.name"));
        this.a(h.f().as);
        this.a(30L);
        try {
            this.a(new URL("http://www.anywebcam.com/res/camstest.gz?" + Math.random()));
        }
        catch (MalformedURLException ex) {
            b.a(ex, 3);
        }
    }
    
    public void a() {
        h.f().ab.clear();
        h.f().ad.clear();
        this.a.clear();
    }
    
    public void b() {
        h.f().ab.clear();
        h.f().ad.clear();
        this.a.clear();
    }
    
    public void c() {
        final h f = h.f();
        this.a.clear();
        try {
            final URLConnection openConnection = this.f().openConnection();
            openConnection.setUseCaches(false);
            openConnection.connect();
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(new GZIPInputStream(openConnection.getInputStream()))));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                try {
                    final StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
                    if (!stringTokenizer.hasMoreTokens()) {
                        continue;
                    }
                    stringTokenizer.nextToken();
                    final String nextToken = stringTokenizer.nextToken();
                    stringTokenizer.nextToken();
                    stringTokenizer.nextToken();
                    stringTokenizer.nextToken();
                    stringTokenizer.nextToken();
                    stringTokenizer.nextToken();
                    stringTokenizer.nextToken();
                    this.a.put((nextToken + "_" + stringTokenizer.nextToken()).toLowerCase(), line);
                }
                catch (Exception ex) {
                    b.a(line, 3);
                    b.a(ex, 3);
                }
            }
            bufferedReader.close();
            final Enumeration keys = f.ab.keys();
            while (keys.hasMoreElements()) {
                final String string = keys.nextElement().toString();
                if (!this.a.containsKey(string)) {
                    f.b((ba)f.ab.get(string));
                }
            }
            final Enumeration<Object> elements = (Enumeration<Object>)l.b().c.elements();
            while (elements.hasMoreElements()) {
                final String string2 = elements.nextElement().toString();
                if (this.a.containsKey(string2)) {
                    this.a.remove(string2);
                }
            }
            l.b().c.clear();
            final Enumeration<Object> keys2 = (Enumeration<Object>)this.a.keys();
            while (keys2.hasMoreElements()) {
                final String string3 = keys2.nextElement().toString();
                if (!h.f().ab.containsKey(string3)) {
                    h.f().a(new ba(this.a.get(string3).toString()));
                }
                else {
                    if (f.ab.get(string3).equals(this.a.get(string3).toString())) {
                        continue;
                    }
                    final ba ba = new ba(this.a.get(string3).toString());
                    f.b((ba)f.ab.get(string3));
                    f.a(ba);
                }
            }
        }
        catch (Exception ex2) {
            b.a(ex2, 3);
        }
        f.b();
        n.b().i();
    }
}
