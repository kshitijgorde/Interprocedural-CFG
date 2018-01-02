// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

import java.awt.FontMetrics;
import java.awt.image.PixelGrabber;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Component;

public class i extends ad
{
    Component w;
    boolean x;
    l z;
    protected Image y;
    ad v;
    
    public i() {
        this.x = false;
    }
    
    public void a(final Component component, final boolean b, final l l) throws Exception {
    }
    
    public void for() {
    }
    
    public void try() {
    }
    
    public void new() {
        if (this.y != null) {
            this.a(this.z.getGraphics(), super.i, super.t);
        }
    }
    
    protected void a(final Graphics graphics, final int n, final int n2) {
        final String s = "Powered by";
        final String s2 = "ImmerVision";
        final String s3 = "Technology";
        if (!this.z.a) {
            return;
        }
        final int t = this.z.t;
        boolean b = true;
        final int n3 = (t & 0xFF0000) >> 16;
        final int n4 = (t & 0xFF00) >> 8;
        final int n5 = t & 0xFF;
        if ((n5 + n4 + n3) / 3 > 128) {
            b = false;
        }
        final int n6 = 15;
        final int n7 = n3 + (b ? n6 : (-n6));
        int n8;
        if (n7 > 255) {
            n8 = n3 - n6;
        }
        else if (n7 < 0) {
            n8 = n3 + n6;
        }
        else {
            n8 = n7;
        }
        final int n9 = n4 + (b ? n6 : (-n6));
        int n10;
        if (n9 > 255) {
            n10 = n4 - n6;
        }
        else if (n9 < 0) {
            n10 = n4 + n6;
        }
        else {
            n10 = n9;
        }
        final int n11 = n5 + (b ? n6 : (-n6));
        int n12;
        if (n11 > 255) {
            n12 = n5 - n6;
        }
        else if (n11 < 0) {
            n12 = n5 + n6;
        }
        else {
            n12 = n11;
        }
        final Color color = new Color(0xFF000000 | n8 << 16 | n10 << 8 | n12);
        if (n8 != 255) {
            ++n8;
        }
        final Color color2 = new Color(t);
        int n13;
        if (n < 320) {
            n13 = n * 32 / 320;
        }
        else {
            n13 = 32;
        }
        final Font font = new Font("Arial", 1, n13);
        graphics.setFont(font);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int stringWidth = fontMetrics.stringWidth(s2);
        final int height = fontMetrics.getHeight();
        int n14;
        if (n < 320) {
            n14 = n * 20 / 320;
        }
        else {
            n14 = 20;
        }
        final Font font2 = new Font("Arial", 1, n14);
        graphics.setFont(font2);
        final FontMetrics fontMetrics2 = graphics.getFontMetrics();
        final int stringWidth2 = fontMetrics2.stringWidth(s);
        final int height2 = fontMetrics2.getHeight();
        final Image image = ac.F.createImage(stringWidth, 3 * height2 + height);
        final Graphics graphics2 = image.getGraphics();
        graphics2.setColor(color2);
        graphics2.fillRect(0, 0, stringWidth, 3 * height2 + height);
        this.v = new ad();
        this.v.i = stringWidth;
        this.v.t = 3 * height2 + height;
        this.v.p = new int[this.v.i * this.v.t];
        graphics2.setColor(color);
        graphics2.setFont(font);
        graphics2.drawString(s2, (this.v.i - stringWidth) / 2, height + height2);
        graphics2.setFont(font2);
        graphics2.drawString(s, (this.v.i - stringWidth2) / 2, height2);
        graphics2.drawString(s3, (this.v.i - stringWidth2) / 2, height + (height2 << 1) + 10);
        final int n15 = graphics2.getColor().getRGB() | 0xFF000000;
        graphics2.dispose();
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.v.i, this.v.t, this.v.p, 0, this.v.i);
        try {
            pixelGrabber.grabPixels();
        }
        catch (Exception ex) {}
        this.v.u = true;
    }
}
