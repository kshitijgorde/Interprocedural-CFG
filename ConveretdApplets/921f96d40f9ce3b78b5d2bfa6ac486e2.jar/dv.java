import java.awt.event.MouseEvent;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class dv extends Canvas implements MouseListener, MouseMotionListener
{
    private Image p;
    private y d;
    private do a;
    private int n;
    private Color v;
    private Color i;
    
    public dv(final y d) {
        this.n = 430;
        this.v = Color.white;
        this.i = Color.black;
        this.d = d;
        this.a = d.p();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    public final void p() {
        final int p = this.d.p();
        this.v = dw.p[p][0];
        this.i = dw.p[p][1];
        this.repaint();
    }
    
    public final void p(final int n) {
        if (this.n == n) {
            return;
        }
        this.n = n;
        du.p(this);
    }
    
    public final Dimension getPreferredSize() {
        return new Dimension(this.n, 0);
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void paint(final Graphics graphics) {
        if (this.getSize().width == 0 || this.getSize().height == 0) {
            return;
        }
        final k p = this.d.p();
        try {
            if (this.p == null || this.p.getWidth(this) < this.getSize().width || this.p.getHeight(this) < this.getSize().height) {
                if (this.p != null) {
                    this.p.flush();
                }
                this.p = this.createImage(this.getSize().width, this.getSize().height);
            }
            final Graphics graphics2 = this.p.getGraphics();
            final Rectangle clipBounds = graphics.getClipBounds();
            if (clipBounds != null) {
                graphics2.clipRect(clipBounds.x, clipBounds.y, clipBounds.width, clipBounds.height);
            }
            try {
                p.p(graphics2, this.v, this.getSize().width, this.getSize().height);
                p.p(graphics2);
            }
            catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
                ex.printStackTrace();
            }
            if (p.p() != null) {
                graphics2.setColor(this.i);
                graphics2.setFont(dw.i);
                graphics2.drawString(p.p(), 6, this.getSize().height - 6);
            }
            graphics.drawImage(this.p, 0, 0, this);
            graphics2.dispose();
        }
        catch (Exception ex2) {
            this.a.l("<4>***Graphics error " + ex2.getMessage());
            System.out.println("Error: " + ex2.getMessage());
            ex2.printStackTrace();
        }
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        this.d.requestFocus();
        if (!mouseEvent.isMetaDown()) {
            this.d.p().a(mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
        if (mouseEvent.isMetaDown()) {
            this.d.p().d(mouseEvent.getX(), mouseEvent.getY());
        }
        else {
            this.d.p().v(mouseEvent.getX(), mouseEvent.getY());
        }
        mouseEvent.consume();
    }
    
    public final void mouseMoved(final MouseEvent mouseEvent) {
        this.d.p().i(mouseEvent.getX(), mouseEvent.getY());
    }
    
    public final void mouseDragged(final MouseEvent mouseEvent) {
        this.d.p().p(mouseEvent.getX(), mouseEvent.getY());
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
    }
}
