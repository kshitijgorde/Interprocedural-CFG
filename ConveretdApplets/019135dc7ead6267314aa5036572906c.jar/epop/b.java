// 
// Decompiled by Procyon v0.5.30
// 

package epop;

import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.PopupMenu;
import java.awt.Component;

public class b extends Component implements Runnable
{
    String j;
    String c;
    String a;
    PopupMenu g;
    Graphics f;
    Image e;
    int b;
    int i;
    int d;
    Color h;
    
    public b(final String j) {
        this.b = 15;
        this.h = Color.yellow;
        this.j = j;
        this.enableEvents(128L);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.e == null) {
            this.e = this.createImage(this.getSize().width, this.getSize().height);
        }
        (this.f = this.e.getGraphics()).setColor(this.getBackground());
        this.f.fillRect(0, 0, this.getSize().width, this.getSize().height);
        this.f.setColor(Color.black);
        this.f.fillRect(0, this.getSize().height - 1, this.getSize().width, this.getSize().height);
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        final int n = (this.getSize().height - fontMetrics.getMaxAscent() - fontMetrics.getMaxDescent()) / 2 + fontMetrics.getMaxAscent();
        this.f.setColor(this.getForeground());
        this.f.setFont(this.getFont());
        this.f.drawString(this.j, this.b, n);
        if (this.g != null) {
            if (this.d == 0) {
                this.d = this.getSize().height / 5;
                this.i = this.d;
            }
            final int[] array = new int[3];
            final int[] array2 = new int[3];
            array[0] = this.getSize().width - this.b + Math.abs(this.i);
            array2[0] = this.getSize().height / 2 - 1;
            array[1] = array[0] - 2 * Math.abs(this.i);
            array2[1] = array2[0] - 2 * Math.abs(this.i);
            array[2] = array[1];
            array2[2] = array2[0] + 2 * Math.abs(this.i);
            this.f.setColor(this.h);
            this.f.fillPolygon(array, array2, 3);
        }
        graphics.drawImage(this.e, 0, 0, this);
    }
    
    public void run() {
        while (true) {
            final long currentTimeMillis = System.currentTimeMillis();
            try {
                Thread.sleep(Math.max(0L, currentTimeMillis + 120L - System.currentTimeMillis()));
            }
            catch (InterruptedException ex) {}
            --this.i;
            if (Math.abs(this.i) > this.d) {
                this.i = this.d;
            }
            this.repaint();
        }
    }
}
