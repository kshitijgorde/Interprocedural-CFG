import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class d extends Canvas
{
    public Image a;
    public Graphics b;
    public static int c;
    
    public d() {
    }
    
    public d(final int n, final int n2) {
        this.setSize(n, n2);
    }
    
    public void setBounds(final int n, final int n2, final int n3, final int n4) {
        super.setBounds(n, n2, n3, n4);
        if (n3 != 0 && n4 != 0) {
            this.a = this.createImage(n3, n4);
            if (this.a != null) {
                this.b = this.a.getGraphics();
            }
            if (this.b != null) {
                this.a();
                this.repaint();
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.a != null) {
            graphics.drawImage(this.a, 0, 0, this);
        }
    }
    
    public void a() {
        this.repaint();
    }
}
