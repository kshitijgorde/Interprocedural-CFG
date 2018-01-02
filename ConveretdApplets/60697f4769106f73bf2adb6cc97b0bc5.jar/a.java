import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Graphics;
import java.util.Hashtable;
import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

class a implements d
{
    private Applet g;
    private int a;
    private String c;
    private String d;
    private Color f;
    private String b;
    private Font e;
    
    a(final String b, final Hashtable hashtable, final Applet g) {
        this.a = 0;
        this.g = g;
        this.b = b;
        int n = (hashtable.get("BOLD") != null) ? 1 : 0;
        if (hashtable.get("ITALIC") != null) {
            n |= 0x2;
        }
        int int1 = 12;
        if (hashtable.get("SIZE") != null) {
            try {
                int1 = Integer.parseInt(hashtable.get("SIZE"));
            }
            catch (NumberFormatException ex) {}
        }
        this.e = new Font((hashtable.get("FACE") == null) ? "Dialog" : hashtable.get("FACE"), n, int1);
        this.f = ((hashtable.get("COLOR") == null) ? Color.black : Color.decode(hashtable.get("COLOR")));
        this.d = hashtable.get("HREF");
        this.c = hashtable.get("TARGET");
        final Graphics graphics = g.getGraphics();
        graphics.setFont(this.e);
        this.a = graphics.getFontMetrics().stringWidth(b);
    }
    
    public int a() {
        return this.a;
    }
    
    public String a(final Graphics graphics, final int n, final int n2, final boolean b) {
        graphics.setFont(this.e);
        graphics.setColor(this.f);
        if (this.d != null) {
            if (b) {
                graphics.setColor(Color.blue);
            }
            graphics.drawLine(n + graphics.getFontMetrics().stringWidth(" "), n2 + 2, n + this.a(), n2 + 2);
            graphics.drawString(this.b, n, n2);
            if (b) {
                return this.d;
            }
        }
        graphics.drawString(this.b, n, n2);
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
                if (this.c == null) {
                    this.g.getAppletContext().showDocument(url);
                }
                else {
                    this.g.getAppletContext().showDocument(url, this.c);
                }
            }
            catch (MalformedURLException ex) {
                System.out.println("ScrollerII -> MalformedURLException (TextLink).");
            }
        }
    }
}
