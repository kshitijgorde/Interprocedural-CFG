// 
// Decompiled by Procyon v0.5.30
// 

package dlt.mandelbrot;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import dlt.mandelbrot.a.j;
import java.awt.Image;
import javax.swing.JPanel;

public class b extends JPanel
{
    private Image a;
    private h if;
    j do;
    
    public b(final j do1) {
        this.setSize(new Dimension(256, 20));
        this.setBackground(Color.black);
        this.do = do1;
    }
    
    private void if() {
        int width = this.getWidth();
        int height = this.getHeight();
        if (width < 1) {
            width = 1;
        }
        if (height < 1) {
            height = 1;
        }
        this.a = this.createImage(width, height);
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        if (this.a != null) {
            graphics.drawImage(this.a, 0, 0, this);
        }
    }
    
    public void a() {
        if (this.if != null) {
            this.if.a();
        }
        this.if();
        (this.if = new h(this.do, this.a.getGraphics(), this)).start();
    }
    
    public void a(final j do1) {
        this.do = do1;
    }
}
