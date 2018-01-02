import java.awt.Color;
import java.net.URLConnection;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.util.zip.GZIPInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

// 
// Decompiled by Procyon v0.5.30
// 

public class f extends d implements g
{
    private ArrayList a;
    
    public f() {
        this.a = new ArrayList();
        this.setName("imLivelist");
        this.a(this);
        this.a(600L);
        try {
            this.a(new URL("http://www.anywebcam.com/res/imlive.gz?" + Math.random()));
        }
        catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void a() {
    }
    
    public void b() {
    }
    
    public void c() {
        final h f = h.f();
        try {
            final URLConnection openConnection = this.f().openConnection();
            openConnection.setUseCaches(false);
            openConnection.connect();
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(new GZIPInputStream(openConnection.getInputStream()))));
            this.a.clear();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                try {
                    final bh bh = new bh(line);
                    f.a(bh);
                    this.a.add(bh);
                }
                catch (Exception ex) {
                    b.a(line, 3);
                    b.a(ex, 3);
                }
            }
            bufferedReader.close();
            final av[] d = f.d();
            for (int i = 0; i < d.length; ++i) {
                final bh bh2 = (bh)d[i];
                if (!this.a.contains(bh2)) {
                    f.b(bh2);
                }
            }
        }
        catch (Exception ex2) {
            b.a(ex2, 3);
        }
    }
    
    public void setBackground(final Color color) {
    }
    
    public void a(final String s) {
    }
    
    public void b(final long n) {
    }
}
