import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.event.MouseEvent;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class c extends Canvas implements MouseListener
{
    Image a;
    bb b;
    String c;
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.b.a(this.c);
        this.b.requestFocus();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public c(final Image a, final bb b, final String c) {
        this.a = a;
        this.setSize(a.getWidth(this), a.getHeight(this));
        this.b = b;
        this.c = c;
        this.addMouseListener(this);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public synchronized void paint(final Graphics graphics) {
        graphics.drawImage(this.a, 0, 0, super.getSize().width, this.getSize().height, this);
    }
    
    public synchronized void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
}
