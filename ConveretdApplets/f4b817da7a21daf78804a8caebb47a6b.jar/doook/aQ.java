// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Graphics;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.awt.Image;
import java.net.URL;

class aQ
{
    private String a;
    private URL b;
    private boolean m;
    private int b;
    private int c;
    private int d;
    private Image p;
    private String o;
    private final aE b;
    private static Hashtable c;
    
    public void a(final String s, final String a) {
        if (aQ.c == null) {
            aQ.c = new Hashtable();
        }
        if (aQ.c.containsKey(this.o)) {
            this.p = aQ.c.get(this.o);
        }
        else {
            this.p = this.b.createImage(this.d, this.c);
            aQ.c.put(this.o, this.p);
        }
        final Graphics graphics = this.p.getGraphics();
        graphics.setColor(aE.b(this.b));
        graphics.fillRect(0, 0, this.d, this.c);
        graphics.setColor(aE.a(this.b));
        graphics.setFont(aE.a(this.b));
        graphics.drawString(this.o, 0, this.c / 2 + graphics.getFontMetrics().getHeight() / 2 - 2);
        try {
            this.b = new URL(this.b.a.getDocumentBase(), s);
        }
        catch (MalformedURLException ex) {
            System.out.println("Bad Link URL in ScrollText [" + s + "]");
        }
        this.a = a;
        this.m = true;
    }
    
    private boolean a() {
        return this.b != null;
    }
    
    public Image a(final boolean b) {
        return this.p;
    }
    
    private void a() {
        if (this.b != null) {
            if (this.a != null) {
                this.b.a.getAppletContext().showDocument(this.b, this.a);
            }
            else {
                this.b.a.getAppletContext().showDocument(this.b);
            }
        }
    }
    
    public boolean b(final int n, final int n2) {
        return this.b > n - this.d && this.b < n2;
    }
    
    static void a(final aQ aq) {
        aq.a();
    }
    
    static boolean a(final aQ aq) {
        return aq.a();
    }
    
    static int a(final aQ aq) {
        return aq.d;
    }
    
    static int b(final aQ aq) {
        return aq.b;
    }
    
    aQ(final aE b, final String o, final int b2, final int c) {
        this.b = b;
        this.o = null;
        this.p = null;
        this.d = 0;
        this.c = 0;
        this.b = 0;
        this.m = false;
        this.b = null;
        this.a = null;
        this.c = c;
        this.b = b2;
        this.o = o;
        b.getGraphics();
        this.d = aE.a(b, o);
    }
}
