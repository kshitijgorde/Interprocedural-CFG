// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Graphics;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.awt.Image;
import java.net.URL;

class aL
{
    private String W;
    private URL a;
    private boolean i;
    private int Z;
    private int o;
    private int g;
    private Image o;
    private String e;
    private final cv b;
    private static Hashtable g;
    
    public void a(final String s, final String w) {
        if (aL.g == null) {
            aL.g = new Hashtable();
        }
        if (aL.g.containsKey(this.e)) {
            this.o = aL.g.get(this.e);
        }
        else {
            this.o = this.b.createImage(this.g, this.o);
            aL.g.put(this.e, this.o);
        }
        final Graphics graphics = this.o.getGraphics();
        graphics.setColor(cv.b(this.b));
        graphics.fillRect(0, 0, this.g, this.o);
        graphics.setColor(cv.a(this.b));
        graphics.setFont(cv.a(this.b));
        graphics.drawString(this.e, 0, this.o / 2 + graphics.getFontMetrics().getHeight() / 2 - 2);
        try {
            this.a = new URL(this.b.a.getDocumentBase(), s);
        }
        catch (MalformedURLException ex) {
            System.out.println("Bad Link URL in ScrollText [" + s + "]");
        }
        this.W = w;
        this.i = true;
    }
    
    private boolean a() {
        return this.a != null;
    }
    
    public Image a(final boolean b) {
        return this.o;
    }
    
    private void c() {
        if (this.a != null) {
            if (this.W != null) {
                this.b.a.getAppletContext().showDocument(this.a, this.W);
            }
            else {
                this.b.a.getAppletContext().showDocument(this.a);
            }
        }
    }
    
    public boolean a(final int n, final int n2) {
        return this.Z > n - this.g && this.Z < n2;
    }
    
    static void a(final aL al) {
        al.c();
    }
    
    static boolean a(final aL al) {
        return al.a();
    }
    
    static int a(final aL al) {
        return al.g;
    }
    
    static int b(final aL al) {
        return al.Z;
    }
    
    aL(final cv b, final String e, final int z, final int o) {
        this.b = b;
        this.e = null;
        this.o = null;
        this.g = 0;
        this.o = 0;
        this.Z = 0;
        this.i = false;
        this.a = null;
        this.W = null;
        this.o = o;
        this.Z = z;
        this.e = e;
        b.getGraphics();
        this.g = cv.a(b, e);
    }
}
