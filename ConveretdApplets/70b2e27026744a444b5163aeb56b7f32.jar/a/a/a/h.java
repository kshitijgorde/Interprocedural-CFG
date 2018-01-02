// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

import java.io.IOException;
import b.a.d.c;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Locale;
import b.a.d.g;
import java.util.Vector;
import b.a.c.q;

public class h extends Thread
{
    protected String a;
    protected q[] b;
    protected String c;
    protected String[] d;
    protected a e;
    protected boolean f;
    protected static String g;
    
    public static void a(final String g) {
        h.g = g;
    }
    
    public h(final a e, final String a, final boolean f) {
        this.e = e;
        this.a = a;
        this.f = f;
        if (h.g == null) {
            h.g = e.c("ATLAS_QUERY_URL");
        }
    }
    
    public q[] a() {
        return this.b;
    }
    
    public String b() {
        return this.c;
    }
    
    public String[] c() {
        return this.d;
    }
    
    public void run() {
        this.d();
    }
    
    protected void d() {
        final Vector vector = new Vector<q>();
        final Vector vector2 = new Vector<String>();
        boolean b = false;
        this.b = null;
        this.c = null;
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(h.g + "?city=" + b.a.d.g.a(this.a) + "&version=6" + "&locale=" + b.a.d.g.a(Locale.getDefault().toString())).openStream()));
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                if (s.endsWith("<BR>") || s.endsWith("<br>")) {
                    s = b.a.d.c.a(s, -4);
                }
                else if (s.endsWith("<BR />") || s.endsWith("<br />")) {
                    s = b.a.d.c.a(s, -5);
                }
                if (s.indexOf("[END-OF-LIST]") >= 0) {
                    b = true;
                    break;
                }
                if (s.startsWith("ERROR: ")) {
                    this.c = s.substring(7);
                }
                else if (s.startsWith("MSG: ")) {
                    vector2.addElement(s.substring(5));
                }
                else {
                    q q;
                    if (s.length() < 1 || s.charAt(0) <= 'C') {
                        q = b.a.c.q.a(b.a.d.g.c(s));
                    }
                    else {
                        q = b.a.c.q.a(b.a.d.g.b(s));
                    }
                    if (q == null) {
                        continue;
                    }
                    vector.addElement(q);
                }
            }
            bufferedReader.close();
        }
        catch (IOException ex) {
            System.err.println(ex);
        }
        if (vector2.size() > 0) {
            vector2.copyInto(this.d = new String[vector2.size()]);
        }
        if (b && this.c == null) {
            vector.copyInto(this.b = new q[vector.size()]);
        }
    }
}
