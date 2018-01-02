import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.awt.Component;
import java.awt.MediaTracker;
import java.net.URL;
import java.util.Hashtable;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

class e implements d
{
    private Applet g;
    private String b;
    private String d;
    private int f;
    private int e;
    private int a;
    private Image c;
    
    e(final Applet g, final Hashtable hashtable) {
        this.c = null;
        this.a = 0;
        this.e = 0;
        this.f = 0;
        this.g = g;
        if (hashtable.get("SRC") != null) {
            try {
                this.c = g.getImage(new URL(g.getDocumentBase(), hashtable.get("SRC")));
                final MediaTracker mediaTracker = new MediaTracker(g);
                mediaTracker.addImage(this.c, 0);
                mediaTracker.waitForID(0);
            }
            catch (InterruptedException ex) {
                System.out.println("-> Interrupted Loading Images.");
            }
            catch (MalformedURLException ex2) {
                System.out.println("-> Malformend URL Excpetion.");
            }
            this.a = this.c.getWidth(null) + 4;
            this.e = this.c.getHeight(null);
        }
        this.d = hashtable.get("HREF");
        this.b = hashtable.get("TARGET");
        final String s = (hashtable.get("ALIGN") == null) ? "BOTTOM" : hashtable.get("ALIGN");
        if (s.equalsIgnoreCase("MIDDLE")) {
            this.f = -(this.e / 2) - 1;
        }
        else if (s.equalsIgnoreCase("BOTTOM")) {
            this.f = -this.e + 1;
        }
    }
    
    public int a() {
        return this.a;
    }
    
    public String a(final Graphics graphics, final int n, final int n2, final boolean b) {
        graphics.drawImage(this.c, n + 4, n2 + this.f, null);
        if (this.d != null) {
            graphics.drawLine(n + 4, n2 + 2, n + this.a(), n2 + 2);
            if (b) {
                return this.d;
            }
        }
        return null;
    }
    
    public void b() {
        if (this.d != null) {
            try {
                URL url;
                if (this.d.indexOf(":") != -1) {
                    url = new URL(this.d);
                }
                else {
                    url = new URL(String.valueOf(String.valueOf(this.g.getDocumentBase())) + "/../" + this.d);
                }
                if (this.b == null) {
                    this.g.getAppletContext().showDocument(url);
                }
                else {
                    this.g.getAppletContext().showDocument(url, this.b);
                }
            }
            catch (MalformedURLException ex) {
                System.out.println("ScrollerII -> MalformedURLException (ImageLink).");
            }
        }
    }
}
