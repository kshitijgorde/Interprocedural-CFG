import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.event.MouseEvent;
import java.awt.Label;
import java.awt.event.MouseListener;

// 
// Decompiled by Procyon v0.5.30
// 

class MouseMan implements MouseListener
{
    E_Menu X;
    Label L;
    int idx;
    
    MouseMan(final E_Menu x, final Label l, final int i) {
        this.X = x;
        this.L = l;
        this.idx = i;
    }
    
    public void mouseEntered(final MouseEvent E) {
        final Graphics g = this.L.getGraphics();
        g.drawImage(this.X.btnimg2[this.idx], 0, 0, this.X);
        this.X.setCursor(1);
    }
    
    public void mouseExited(final MouseEvent theEv) {
        final Graphics g = this.L.getGraphics();
        g.drawImage(this.X.btnimg1[this.idx], 0, 0, this.X);
        this.X.setCursor(0);
    }
    
    public void mousePressed(final MouseEvent theEv) {
        this.X.btnHit = this.idx;
        this.X.bPop[this.idx].show(this.X, this.idx * this.X.btnWidth, this.X.btnHeight);
    }
    
    public void mouseReleased(final MouseEvent theEv) {
    }
    
    public void mouseClicked(final MouseEvent theEv) {
    }
}
