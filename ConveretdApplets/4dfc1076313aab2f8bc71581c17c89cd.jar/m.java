import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class m extends Panel
{
    private boolean p;
    private final EggApplet d;
    
    public final Dimension getPreferredSize() {
        return EggApplet.n(this.d);
    }
    
    public final void setLocation(final int n, final int n2) {
        super.setLocation(0, 0);
    }
    
    public final void paint(final Graphics graphics) {
        if (this.p) {
            graphics.drawImage(EggApplet.d(this.d), 0, 0, this);
        }
        else {
            graphics.drawImage(EggApplet.h(this.d), 0, 0, null);
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (EggApplet.d(this.d) != null) {
            this.p = true;
            this.repaint();
        }
        this.d.p();
        return true;
    }
    
    public final boolean mouseUp(final Event event, final int n, final int n2) {
        try {
            this.d.getAppletContext().showDocument(new URL("http://www.eggsolution.com"), "_blank");
        }
        catch (MalformedURLException ex) {
            System.out.println("Caught Exception: ".concat(String.valueOf(String.valueOf(ex))));
        }
        this.p = false;
        this.repaint();
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int n, final int n2) {
        if (EggApplet.p(this.d)) {
            EggApplet.p(this.d, false);
            EggApplet.b(this.d).repaint();
            EggApplet.v(this.d).repaint();
        }
        return true;
    }
    
    public m(final EggApplet d) {
        this.d = d;
        this.p = false;
        this.setSize(EggApplet.h(d).getWidth(null), EggApplet.h(d).getHeight(null));
    }
}
