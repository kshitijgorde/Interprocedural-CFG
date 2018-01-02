import java.awt.FontMetrics;
import java.util.Enumeration;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.Color;
import java.io.PrintStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class f
{
    public static int b;
    PrintStream e;
    String f;
    String d;
    Color c;
    Vector a;
    g g;
    
    public void a(String s) {
        while (s.length() > 0 && this.a(s.charAt(0))) {
            s = s.substring(1);
        }
        while (s.length() > 0 && this.a(s.charAt(s.length() - 1))) {
            s = s.substring(0, s.length() - 1);
        }
        if (s.length() > 0) {
            this.a.addElement(s);
        }
    }
    
    public boolean a(final char c) {
        boolean b = false;
        if (c == ' ' || c == '\r' || c == '\n' || c == '\t' || c == '-') {
            b = true;
        }
        return b;
    }
    
    public int a() {
        return this.a.size();
    }
    
    public void a(int n, final Graphics graphics) {
        graphics.setColor(this.c);
        int b = 0;
        final Enumeration<String> elements = this.a.elements();
        while (elements.hasMoreElements()) {
            graphics.drawString(elements.nextElement(), b + 5, n);
            n += this.g.e.getHeight();
            b = f.b;
        }
    }
    
    private final void b() {
        this.e = System.err;
    }
    
    public f(final g g, final String f, final String d, final Color c, final boolean b) {
        this.b();
        this.g = g;
        this.f = f;
        this.d = d;
        this.c = c;
        if (!b) {
            this.a = new Vector(1);
            this.a(this.f);
        }
        else {
            final FontMetrics e = g.e;
            if (e.stringWidth(this.f) <= g.h) {
                this.a = new Vector(1);
                this.a(this.f);
            }
            else {
                this.a = new Vector(5);
                int n;
                String s;
                int n2;
                int n3;
                int i;
                char char1;
                for (n = 0, s = this.f; e.stringWidth(s) > n; n = this.g.h - f.b) {
                    n2 = 0;
                    n3 = 0;
                    for (i = 0; i < s.length(); ++i) {
                        char1 = s.charAt(i);
                        if (this.a(char1)) {
                            n3 = i;
                        }
                        n2 += e.charWidth(char1);
                        if (n2 > n) {
                            if (n3 > 0) {
                                this.a(s.substring(0, n3));
                                s = s.substring(n3);
                            }
                            else {
                                this.a(s.substring(0, i));
                                s = s.substring(i);
                            }
                            break;
                        }
                    }
                }
                if (e.stringWidth(s) > 0) {
                    this.a(s);
                }
            }
        }
    }
    
    static {
        f.b = 0;
    }
}
