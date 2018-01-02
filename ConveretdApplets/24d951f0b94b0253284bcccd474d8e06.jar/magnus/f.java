// 
// Decompiled by Procyon v0.5.30
// 

package magnus;

import java.awt.Event;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Canvas;

class f extends Canvas
{
    private a if;
    private Eyring a;
    
    public f(final a if1) {
        this.if = if1;
    }
    
    public void a(final a if1) {
        this.if = if1;
    }
    
    public void update(final Graphics graphics) {
        final Dimension size = this.size();
        final Image image = this.createImage(size.width, size.height);
        final Graphics graphics2 = image.getGraphics();
        graphics2.setColor(this.getBackground());
        graphics2.fillRect(0, 0, size.width, size.height);
        this.paint(graphics2);
        graphics.drawImage(image, 0, 0, this);
    }
    
    public void paint(final Graphics graphics) {
        this.if.a(graphics);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (n >= 30 && n <= 650) {
            magnus.a.p = 1;
            magnus.a.v = n;
            switch (magnus.a.A) {
                case 0: {
                    Eyring.l = (n - 30) / 620.0 * (Eyring.case - Eyring.h) + Eyring.h;
                    break;
                }
                case 1: {
                    Eyring.n = (n - 30) / 620.0 * (Eyring.int - Eyring.a) + Eyring.a;
                    break;
                }
            }
            Eyring.else = Eyring.a(Eyring.n, Eyring.l);
            magnus.a.t = (int)((Eyring.char - Eyring.else) / (Eyring.char - Eyring.null) * 300.0);
            this.repaint();
        }
        return false;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (n >= 30 && n <= 650) {
            magnus.a.p = 1;
            magnus.a.v = n;
            switch (magnus.a.A) {
                case 0: {
                    Eyring.l = (n - 30) / 620.0 * (Eyring.case - Eyring.h) + Eyring.h;
                    break;
                }
                case 1: {
                    Eyring.n = (n - 30) / 620.0 * (Eyring.int - Eyring.a) + Eyring.a;
                    break;
                }
            }
            Eyring.else = Eyring.a(Eyring.n, Eyring.l);
            magnus.a.t = (int)((Eyring.char - Eyring.else) / (Eyring.char - Eyring.null) * 300.0);
            this.repaint();
        }
        return false;
    }
}
