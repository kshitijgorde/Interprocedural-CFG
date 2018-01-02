import java.awt.FontMetrics;
import java.awt.Scrollbar;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.StringBufferInputStream;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class z4 extends Canvas
{
    private int z0;
    private int z1;
    private int z2;
    private int z3;
    private String z4;
    private int z5;
    
    public void paint(final Graphics graphics) {
        this.z3 = z1.z164.size().height;
        if (z1.z56 != null) {
            graphics.drawImage(z1.z56, -z1.z14, -z1.z161, this);
        }
        graphics.translate(-this.z1, -this.z2);
        final int n = 0;
        final int n2 = 0;
        final Font font = new Font("Helvetica", 1, 12);
        graphics.setFont(font);
        final FontMetrics fontMetrics = graphics.getFontMetrics(font);
        int n3 = n2 + fontMetrics.getHeight();
        try {
            String line;
            while ((line = new DataInputStream(new StringBufferInputStream(this.z4)).readLine()) != null) {
                if (n3 - fontMetrics.getHeight() >= this.z2) {
                    graphics.drawString(line, n, n3);
                }
                n3 += fontMetrics.getHeight();
            }
            if (n3 >= this.z3 + fontMetrics.getHeight()) {
                final int n4 = n3 - fontMetrics.getHeight();
                final Scrollbar scrollbar = new Scrollbar(0, 0, 10, 0, 100);
                scrollbar.setValues(90, 10, 0, 90);
                if (scrollbar.getValue() == 90) {
                    z1.z176.setValues(z1.z176.getValue(), 1, 0, (n4 - this.z3 + fontMetrics.getHeight()) / fontMetrics.getHeight());
                }
                else {
                    z1.z176.setValues(z1.z176.getValue(), 1, 0, (n4 - this.z3 + 2 * fontMetrics.getHeight()) / fontMetrics.getHeight());
                }
                if (z1.z46 || this.z0 == z1.z163) {
                    this.z0 = z1.z163 - z1.z124;
                    z1.z164.reshape(z1.z14, z1.z161, z1.z163 - z1.z124, z1.z162);
                    z1.z176.reshape(z1.z14 + z1.z163 - z1.z124, z1.z161, z1.z124, z1.z162);
                    z1.z46 = false;
                }
                this.z0 = z1.z163 - z1.z124;
                z1.z176.show();
                z1.z54 = false;
            }
            else {
                if (z1.z46 || this.z0 != z1.z163) {
                    z1.z164.reshape(z1.z14, z1.z161, z1.z163, z1.z162);
                    z1.z176.hide();
                    z1.z54 = true;
                    z1.z46 = false;
                }
                this.z0 = z1.z163;
            }
        }
        catch (Exception ex) {}
    }
    
    public void setText(final String s, final int z5, final int n) {
        this.z4 = s.trim();
        this.z5 = z5;
        this.z1 = 0;
        this.z2 = 0;
        this.repaint();
    }
    
    public void translate(final int z1, final int z2) {
        this.z1 = z1;
        this.z2 = z2;
        this.repaint();
    }
    
    z4() {
        this.z0 = 0;
        this.z4 = "";
        this.z1 = 0;
        this.z2 = 0;
        this.z5 = 1000;
        this.z3 = 300;
    }
}
