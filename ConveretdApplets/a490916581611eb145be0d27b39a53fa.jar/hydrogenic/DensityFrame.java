// 
// Decompiled by Procyon v0.5.30
// 

package hydrogenic;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.WindowListener;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Frame;

public class DensityFrame extends Frame
{
    Image img;
    
    public DensityFrame(final Image img) {
        this.img = null;
        this.img = img;
        this.setSize(img.getWidth(this), img.getHeight(this));
        this.setLocation((int)(300 * Math.random()), (int)(300 * Math.random()));
        this.addWindowListener(new 1());
        this.setTitle(this.getClass().getName());
    }
    
    public void paint(final Graphics graphics) {
        final String s = "Error: No Image.";
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        final int n = (Math.max(width, height) - Math.min(width, height)) / 2;
        if (this.img == null) {
            super.paint(graphics);
            graphics.setColor(Color.black);
            graphics.drawString(s, (width - graphics.getFontMetrics().stringWidth(s)) / 2, height / 2);
        }
        else {
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, width, height);
            final int min = Math.min(width, height);
            final int min2 = Math.min(min, height);
            if (this.getSize().width < this.getSize().height) {
                graphics.setColor(Color.red);
                graphics.drawImage(this.img, 0, n, min, min2, this);
                graphics.drawLine(0, this.getSize().height - n, this.getSize().width, this.getSize().height - n);
                graphics.drawLine(this.getSize().width - 5, n, this.getSize().width - 5, this.getSize().height - n);
                graphics.drawLine(0, n, this.getSize().width, n);
                graphics.drawLine(5, n, 5, this.getSize().height - n);
            }
            else {
                graphics.drawImage(this.img, n, 0, min, min2, this);
                graphics.setColor(Color.red);
                graphics.drawLine(n, 0, n, this.getSize().height);
                graphics.drawLine(this.getSize().width - n, 0, this.getSize().width - n, this.getSize().height);
                graphics.drawLine(n, 23, this.getSize().width - n, 23);
                graphics.drawLine(n, this.getSize().height - 5, this.getSize().width - n, this.getSize().height - 5);
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    class 1 extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            DensityFrame.this.dispose();
        }
    }
}
