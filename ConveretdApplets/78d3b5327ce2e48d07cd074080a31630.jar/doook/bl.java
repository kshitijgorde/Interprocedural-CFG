// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Graphics;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.awt.Image;
import java.net.URL;

class bl
{
    private String l;
    private URL b;
    private boolean b;
    private int B;
    private int C;
    private int c;
    private Image k;
    private String g;
    private final aH b;
    private static Hashtable i;
    
    public void a(final String s, final String l) {
        if (bl.i == null) {
            bl.i = new Hashtable();
        }
        if (bl.i.containsKey(this.g)) {
            this.k = bl.i.get(this.g);
        }
        else {
            this.k = this.b.createImage(this.c, this.C);
            bl.i.put(this.g, this.k);
        }
        final Graphics graphics = this.k.getGraphics();
        graphics.setColor(aH.b(this.b));
        graphics.fillRect(0, 0, this.c, this.C);
        graphics.setColor(aH.a(this.b));
        graphics.setFont(aH.a(this.b));
        graphics.drawString(this.g, 0, this.C / 2 + graphics.getFontMetrics().getHeight() / 2 - 2);
        try {
            this.b = new URL(this.b.a.getDocumentBase(), s);
        }
        catch (MalformedURLException ex) {
            System.out.println("Bad Link URL in ScrollText [" + s + "]");
        }
        this.l = l;
        this.b = true;
    }
    
    private boolean b() {
        return this.b != null;
    }
    
    public Image a(final boolean b) {
        return this.k;
    }
    
    private void c() {
        if (this.b != null) {
            if (this.l != null) {
                this.b.a.getAppletContext().showDocument(this.b, this.l);
            }
            else {
                this.b.a.getAppletContext().showDocument(this.b);
            }
        }
    }
    
    public boolean b(final int n, final int n2) {
        return this.B > n - this.c && this.B < n2;
    }
    
    static void a(final bl bl) {
        bl.c();
    }
    
    static boolean a(final bl bl) {
        return bl.b();
    }
    
    static int a(final bl bl) {
        return bl.c;
    }
    
    static int b(final bl bl) {
        return bl.B;
    }
    
    bl(final aH b, final String g, final int b2, final int c) {
        this.b = b;
        this.g = null;
        this.k = null;
        this.c = 0;
        this.C = 0;
        this.B = 0;
        this.b = false;
        this.b = null;
        this.l = null;
        this.C = c;
        this.B = b2;
        this.g = g;
        b.getGraphics();
        this.c = aH.a(b, g);
    }
}
