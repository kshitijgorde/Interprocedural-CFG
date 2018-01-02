import au.com.rocketdog.project.awc.applet.Main;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.event.MouseListener;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class b9 extends JPanel
{
    public Image a;
    public String b;
    public String c;
    public Dimension d;
    
    public b9(final String b, final String c, final Image a) {
        this.a = a;
        this.c = c;
        this.b = b;
        this.addMouseListener(new ca(this));
        this.d = new Dimension(a.getWidth(this), a.getHeight(this));
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.a, 0, 0, this);
    }
    
    public Dimension getSize() {
        return this.d;
    }
    
    public Dimension getPreferredSize() {
        return this.d;
    }
    
    public void a() {
        Main.b(this.b, this.c);
    }
}
