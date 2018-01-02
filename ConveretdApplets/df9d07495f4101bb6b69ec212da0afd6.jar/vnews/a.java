// 
// Decompiled by Procyon v0.5.30
// 

package vnews;

import java.util.StringTokenizer;
import java.awt.Color;
import java.util.Vector;
import java.awt.FontMetrics;

class a
{
    private String g;
    private FontMetrics c;
    private int h;
    private int f;
    private int b;
    private int i;
    private String l;
    private String j;
    private Vector m;
    private boolean a;
    private int e;
    private int d;
    private Color k;
    
    a() {
        this.l = new String();
        this.j = new String();
        this.m = new Vector();
        this.a = false;
        this.k = Color.black;
        this.g = "";
        this.c = null;
        this.h = 0;
        final boolean f = false;
        this.i = (f ? 1 : 0);
        this.b = (f ? 1 : 0);
        this.f = (f ? 1 : 0);
        this.l = "";
    }
    
    a(final a a) {
        this.l = new String();
        this.j = new String();
        this.m = new Vector();
        this.a = false;
        this.k = Color.black;
        this.g = a.g;
        this.c = a.c;
        this.h = a.h;
        this.l = a.l;
        this.j = a.j;
        this.f = a.f;
        this.b = a.b;
        this.i = a.i;
        this.g();
    }
    
    a(final String g, final FontMetrics c, final int h, final String l, final String j, final int f, final int b, final int i) {
        this.l = new String();
        this.j = new String();
        this.m = new Vector();
        this.a = false;
        this.k = Color.black;
        this.g = g;
        this.c = c;
        this.h = h;
        this.l = l;
        this.j = j;
        this.f = f;
        this.b = b;
        this.i = i;
        this.g();
    }
    
    public Vector j() {
        final int stringWidth = this.c.stringWidth(" ");
        final int lastIndex = this.g.lastIndexOf("- (");
        String substring = "";
        StringTokenizer stringTokenizer;
        if (lastIndex != -1) {
            substring = this.g.substring(lastIndex + 2);
            stringTokenizer = new StringTokenizer(this.g.substring(0, lastIndex - 1));
        }
        else {
            stringTokenizer = new StringTokenizer(this.g);
        }
        final Vector<String> vector = new Vector<String>();
        String s = "";
        int n = 0;
        final String s2 = new String();
        while (stringTokenizer.hasMoreTokens()) {
            String s3 = stringTokenizer.nextToken();
            int i = this.c.stringWidth(s3);
            if (n + i > this.h) {
                if (n != 0) {
                    vector.addElement(s);
                }
                while (i > this.h) {
                    int n2;
                    for (n2 = 0; this.c.stringWidth(s3.substring(0, n2)) < this.h; ++n2) {}
                    vector.addElement(s3.substring(0, n2));
                    s3 = s3.substring(n2);
                    i = this.c.stringWidth(s3);
                }
                if (i == this.h) {
                    vector.addElement(s3);
                    s = "";
                    n = 0;
                }
                else {
                    s = String.valueOf(s3) + " ";
                    n = i + stringWidth;
                }
            }
            else if (n + i == this.h) {
                vector.addElement(String.valueOf(s) + s3);
                s = "";
                n = 0;
            }
            else {
                s = String.valueOf(s) + s3 + " ";
                n += i + stringWidth;
            }
        }
        if (n > 0) {
            vector.addElement(s);
        }
        if (lastIndex != -1) {
            vector.addElement(substring);
        }
        return vector;
    }
    
    public void g() {
        if (this.b == 0) {
            this.a = true;
        }
        this.m = this.j();
        this.e = this.m.size() * this.c.getHeight();
    }
    
    public int a() {
        return this.e;
    }
    
    public void a(final int d) {
        this.d = d;
    }
    
    public int c() {
        return this.d;
    }
    
    public boolean d() {
        return this.a;
    }
    
    public int i() {
        return this.f;
    }
    
    public int k() {
        return this.i;
    }
    
    public String h() {
        return this.l;
    }
    
    public String e() {
        return this.j;
    }
    
    public Vector b() {
        return this.m;
    }
    
    public FontMetrics f() {
        return this.c;
    }
}
