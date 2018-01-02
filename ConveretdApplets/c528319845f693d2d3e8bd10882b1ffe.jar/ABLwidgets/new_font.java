// 
// Decompiled by Procyon v0.5.30
// 

package ABLwidgets;

import java.util.Enumeration;
import java.io.InputStream;
import java.io.IOException;
import java.awt.Font;
import java.util.Hashtable;
import java.util.Vector;
import java.net.URL;

public class new_font implements Runnable
{
    protected URL a;
    protected String b;
    protected int c;
    protected static int d;
    protected static Vector e;
    protected static Vector f;
    protected static Hashtable g;
    protected static boolean h;
    private static Object i;
    static /* synthetic */ Class j;
    
    public new_font(final URL a, final String b, final int c) {
        this.a = a;
        this.b = b;
        this.c = c;
        b();
    }
    
    public void run() {
        InputStream openStream = null;
        try {
            int n;
            if (a(this.b).equalsIgnoreCase("ttf")) {
                n = 0;
            }
            else {
                if (!a(this.b).equalsIgnoreCase("pfa")) {
                    abljem.b("Font file name " + this.b + " ignored, extension not .ttf or .pfa");
                    return;
                }
                n = 1;
            }
            openStream = new URL(this.a, this.b).openStream();
            final Font font = Font.createFont(n, openStream);
            a(new font_entry(font, font.getName()));
            final String b = b(this.b);
            a(new font_entry(font, b));
            a(this.c, b);
            new_font.f.addElement(this.b);
        }
        catch (Throwable t) {
            abljem.b("font_loader: " + t.toString());
        }
        finally {
            c();
            if (openStream != null) {
                try {
                    openStream.close();
                }
                catch (IOException ex) {}
            }
        }
    }
    
    public static void a(final URL url, final String s, final int n) {
        if (new_font.h) {
            if (d(s)) {
                a(n, b(s));
            }
            else {
                new Thread(new new_font(url, s, n)).start();
            }
        }
    }
    
    public static Font a(String c, final int n, final int n2) {
        c = c(c);
        final Font d = d(c, n, n2);
        if (d != null) {
            return d;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        final long n3 = currentTimeMillis + 1000L;
        if (new_font.d > 0) {
            while (new_font.d > 0 && System.currentTimeMillis() < n3) {
                a(50L);
            }
        }
        if (new_font.d > 0) {
            abljem.b("Waiting for font(s) to load");
            while (new_font.d > 0) {
                a(50L);
            }
            abljem.b("Font(s) loaded after " + (System.currentTimeMillis() - currentTimeMillis + 1000L) / 1000L + " seconds");
            final Font d2 = d(c, n, n2);
            if (d2 != null) {
                return d2;
            }
        }
        final Font font = new Font(c, n, n2);
        a(new font_entry(font, c));
        return font;
    }
    
    public static void a() {
        synchronized (new_font.i) {
            new_font.e = new Vector();
            new_font.f = new Vector();
            new_font.g = new Hashtable();
            new_font.d = 0;
        }
    }
    
    public static String a(final String s) {
        if (s == null) {
            return "";
        }
        final int lastIndex = s.lastIndexOf(".");
        if (lastIndex < 0) {
            return "";
        }
        return s.substring(lastIndex + 1);
    }
    
    public static String b(final String s) {
        int n = s.lastIndexOf(92);
        if (n == -1) {
            n = s.lastIndexOf(47);
        }
        if (n == -1) {
            n = 0;
        }
        else {
            ++n;
        }
        final int lastIndex = s.lastIndexOf(46);
        if (lastIndex > 0) {
            return s.substring(n, lastIndex);
        }
        return s;
    }
    
    private static void a(final font_entry font_entry) {
        new_font.e.addElement(font_entry);
    }
    
    private static void a(final int n, final String s) {
        new_font.g.put("*LoadedFont" + n, s);
    }
    
    private static String c(final String s) {
        final String s2 = new_font.g.get(s);
        if (s2 != null) {
            return s2;
        }
        return s;
    }
    
    private static Font b(final String s, final int n, final int n2) {
        final Enumeration<font_entry> elements = new_font.e.elements();
        while (elements.hasMoreElements()) {
            final font_entry font_entry = elements.nextElement();
            if (font_entry.b.equals(s)) {
                final Font a = font_entry.a;
                if (a.getStyle() == n && a.getSize() == n2) {
                    return a;
                }
                continue;
            }
        }
        return null;
    }
    
    private static Font c(final String s, final int n, final int n2) {
        final Enumeration<font_entry> elements = new_font.e.elements();
        while (elements.hasMoreElements()) {
            final font_entry font_entry = elements.nextElement();
            if (font_entry.b.equals(s)) {
                Font a;
                if (new_font.h) {
                    a = derive_font.a(font_entry.a, n, n2);
                }
                else {
                    a = new Font(font_entry.a.getName(), n, n2);
                }
                a(new font_entry(a, s));
                return a;
            }
        }
        return null;
    }
    
    private static Font d(final String s, final int n, final int n2) {
        Font font = b(s, n, n2);
        if (font == null) {
            font = c(s, n, n2);
        }
        return font;
    }
    
    private static boolean d(final String s) {
        final Enumeration<String> elements = new_font.f.elements();
        while (elements.hasMoreElements()) {
            if (elements.nextElement().equals(s)) {
                return true;
            }
        }
        return false;
    }
    
    private static boolean a(final long n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {
            return false;
        }
        return true;
    }
    
    private static void b() {
        synchronized (new_font.i) {
            ++new_font.d;
        }
    }
    
    private static void c() {
        synchronized (new_font.i) {
            if (new_font.d > 0) {
                --new_font.d;
            }
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        new_font.h = false;
        new_font.i = new Object();
        a();
        try {
            ((new_font.j == null) ? (new_font.j = class$("java.awt.Font")) : new_font.j).getMethod("deriveFont", Integer.TYPE, Float.TYPE);
            new_font.h = true;
        }
        catch (Throwable t) {}
    }
    
    static class derive_font
    {
        public static Font a(final Font font, final int n, final int n2) {
            return font.deriveFont(n, n2);
        }
    }
    
    static class font_entry
    {
        public Font a;
        public String b;
        
        font_entry(final Font a, final String b) {
            this.a = a;
            this.b = b;
        }
    }
}
