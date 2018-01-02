import java.awt.Cursor;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class BrandPanel extends Panel implements MouseListener
{
    Ticker applet;
    Image image;
    Dimension mySize;
    
    public void mouseClicked(final MouseEvent evt) {
        this.applet.openBrandPage();
    }
    
    public void mousePressed(final MouseEvent evt) {
    }
    
    public BrandPanel(final Ticker a, final Image img, final Dimension d) {
        this.applet = a;
        this.image = img;
        this.mySize = d;
        this.addMouseListener(this);
    }
    
    public void mouseReleased(final MouseEvent evt) {
    }
    
    public void paint(final Graphics g) {
        g.setColor(this.getBackground());
        g.fillRect(0, 0, this.getSize().width, this.getSize().height);
        final int w = (this.mySize.width - this.image.getWidth(this)) / 2;
        final int h = (this.mySize.height - this.image.getHeight(this)) / 2;
        g.drawImage(this.image, w, h, this);
    }
    
    public void mouseEntered(final MouseEvent evt) {
        this.setCursor(Cursor.getPredefinedCursor(12));
    }
    
    public void mouseExited(final MouseEvent evt) {
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    public Dimension getMinimumSize() {
        return this.mySize;
    }
    
    public Dimension getPreferredSize() {
        return this.mySize;
    }
}
