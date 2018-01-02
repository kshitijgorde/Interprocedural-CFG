import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class EfloyCanvas extends Canvas
{
    Image image;
    Color color;
    boolean first;
    boolean reset;
    Image OffImg;
    Graphics OffGra;
    
    public void Clear() {
        this.reset = true;
        this.repaint();
    }
    
    Graphics GetGra() {
        return this.getGraphics();
    }
    
    public EfloyCanvas(final Image img) {
        this.image = img;
        this.color = Color.white;
        this.setBackground(Color.black);
        this.setForeground(Color.white);
        this.first = true;
        this.reset = false;
    }
    
    public EfloyCanvas(final Color c) {
        this.image = null;
        this.setBackground(Color.black);
        this.setForeground(Color.white);
        this.color = c;
        this.first = true;
        this.reset = false;
    }
    
    public void paint(final Graphics g) {
        if (Efloys.Efloys != null) {
            for (int i = 0; i < Efloys.Efloys.length; ++i) {
                try {
                    final Efloy Efloy = Efloys.Efloys[i];
                    Efloy.GetNeighbors();
                    Efloy.Process();
                    Efloy.Draw(g);
                }
                catch (Exception ex) {}
            }
        }
    }
    
    public void xpaint(final Graphics g) {
        this.OffImg = this.createImage(this.size().width, this.size().height);
        try {
            this.OffGra = this.OffImg.getGraphics();
        }
        catch (Exception e) {
            Efloys.appcontext.showStatus("After OffImg e= " + e.toString());
        }
        this.OffGra.setColor(this.getBackground());
        this.OffGra.fillRect(0, 0, this.size().width, this.size().height);
        for (int i = 0; i < Efloys.Efloys.length; ++i) {
            final Efloy Efloy = Efloys.Efloys[i];
            Efloy.GetNeighbors();
            Efloy.Process();
            Efloy.Draw(this.OffGra);
        }
        g.drawImage(this.OffImg, 0, 0, this);
    }
}
