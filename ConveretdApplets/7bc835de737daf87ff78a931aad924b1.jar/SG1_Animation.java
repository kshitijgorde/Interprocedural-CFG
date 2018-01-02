import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class SG1_Animation implements Runnable
{
    int w;
    int h;
    int numberOfImages;
    int pozx;
    int pozy;
    
    public void line(final Point center, final int radius, final Graphics br) {
        final int ic = (int)(Math.random() * 100.0);
        br.setColor(new Color(90, 130, 190));
        br.drawLine(center.x - radius, center.y - radius, center.x - radius, center.y - radius);
    }
    
    public SG1_Animation(final int x, final int y) {
        this.numberOfImages = 20;
        this.w = x;
        this.h = y;
    }
    
    public void getImage(final int number, final Graphics gr) {
        gr.setColor(new Color(10, 20, 80));
        gr.fillRect(0, 0, this.w, this.h);
        for (int z = 0; z < this.h; ++z) {
            int c = 0;
            do {
                this.pozx = (int)(Math.random() * this.w);
                this.pozy = this.h - z;
                final Point p = new Point(this.pozx, this.pozy);
                this.line(p, 1, gr);
            } while (++c < 20);
        }
    }
    
    public void run() {
    }
}
