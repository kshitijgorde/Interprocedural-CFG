import au.com.rocketdog.project.awc.applet.Main;
import java.awt.PopupMenu;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import au.com.rocketdog.project.awc.applet.images.ImageRes;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Dimension;

// 
// Decompiled by Procyon v0.5.30
// 

public class az extends av
{
    private final Dimension a;
    private String b;
    private String c;
    private String d;
    private int e;
    
    public az(final String s, final int n, final String b, final String d, final String c) {
        this.a = new Dimension(178, 18);
        this.a(n);
        this.a(s);
        this.b(s);
        this.b = b;
        this.d = d;
        this.c = c;
    }
    
    public void a(final int e) {
        this.e = e;
    }
    
    public void b(final Graphics graphics, final Component component, final int n, final int n2) {
        graphics.fillRect(n, n2, this.g().width, this.g().height);
        graphics.setColor(dj.x);
        graphics.setFont(dj.aj);
        graphics.drawImage(ImageRes.a5, n + 4, n2 + 4, component);
        String s = this.r();
        final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(graphics.getFont());
        for (int i = fontMetrics.stringWidth(s) + 19; i > 132; i = fontMetrics.stringWidth(s) + 19) {
            s = s.substring(0, s.length() - 4) + "...";
        }
        graphics.drawString(s, 19, n2 + 14);
    }
    
    public PopupMenu a(final int n, final int n2, final Component component) {
        return null;
    }
    
    public void c(final int n, final int n2) {
        Main.b(this.c, this.d);
    }
    
    public Dimension g() {
        return this.a;
    }
    
    public String b(final int n, final int n2, final Component component) {
        return "<html><body bgcolor=\"#CDDBE8\" text=\"#1F5285\"><font face=\"verdana\" size=\"2\"> " + this.c + " </font>" + " </body></html>";
    }
}
