import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.Insets;
import java.awt.event.MouseListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class colorchooser extends Frame implements MouseListener
{
    Insets ins;
    final int w = 128;
    BufferedImage bi;
    BufferedImage sat;
    options o;
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (x >= this.ins.left && x < this.ins.left + this.bi.getWidth() && y >= this.ins.top && y < this.ins.top + this.bi.getHeight()) {
            final int rgb = this.bi.getRGB(x - this.ins.left, y - this.ins.top);
            final int n = rgb & 0xFF;
            final int n2 = rgb >> 8 & 0xFF;
            final int n3 = rgb >> 16 & 0xFF;
            for (int i = 0; i < 32; ++i) {
                for (int j = 0; j < 256; ++j) {
                    this.sat.setRGB(i, j, j * n / 255 + (j * n2 / 255 << 8) + (j * n3 / 255 << 16) - 16777216);
                }
            }
            this.repaint();
        }
        if (x >= this.ins.left + this.bi.getWidth() + 32 && x < this.ins.left + this.bi.getWidth() + 32 + this.sat.getWidth() && y >= this.ins.top && y < this.ins.top + this.sat.getHeight()) {
            final int rgb2 = this.sat.getRGB(x - this.ins.left - this.bi.getWidth() - 32, y - this.ins.top);
            this.o.setcol(new Color(rgb2 >> 16 & 0xFF, rgb2 >> 8 & 0xFF, rgb2 & 0xFF));
            this.setVisible(false);
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    colorchooser(final options o) {
        this.bi = new BufferedImage(385, 256, 2);
        this.sat = new BufferedImage(32, 256, 2);
        this.setSize(this.bi.getWidth() + 64, this.bi.getHeight());
        this.o = o;
        this.ins = this.getInsets();
        this.addMouseListener(this);
        for (int i = 0; i <= 128; ++i) {
            this.bi.setRGB(i, 0, 0 + (255 * (128 - i) / 128 << 8) + (255 * i / 128 << 16) - 16777216);
        }
        for (int j = 129; j <= 256; ++j) {
            this.bi.setRGB(j, 0, 255 * (j - 128) / 128 + (0 << 8) + (255 * (256 - j) / 128 << 16) - 16777216);
        }
        for (int k = 257; k <= 384; ++k) {
            this.bi.setRGB(k, 0, 255 * (384 - k) / 128 + (255 * (k - 128 - 128) / 128 << 8) + (0 << 16) - 16777216);
        }
        for (int l = 0; l < this.bi.getWidth(); ++l) {
            final int rgb = this.bi.getRGB(l, 0);
            final int n = rgb & 0xFF;
            final int n2 = rgb >> 8 & 0xFF;
            final int n3 = rgb >> 16 & 0xFF;
            final int n4 = (n3 + n2 + n) / 3;
            for (int n5 = 1; n5 < this.bi.getHeight(); ++n5) {
                this.bi.setRGB(l, n5, (n * (255 - n5) + n4 * n5) / 255 + ((n2 * (255 - n5) + n4 * n5) / 255 << 8) + ((n3 * (255 - n5) + n4 * n5) / 255 << 16) - 16777216);
            }
        }
        for (int n6 = 0; n6 < this.bi.getWidth(); ++n6) {
            for (int n7 = 0; n7 < this.bi.getHeight(); ++n7) {
                final int rgb2 = this.bi.getRGB(n6, n7);
                final int n8 = rgb2 & 0xFF;
                final int n9 = rgb2 >> 8 & 0xFF;
                final int n10 = rgb2 >> 16 & 0xFF;
                final int max = Math.max(Math.max(n10, n9), n8);
                this.bi.setRGB(n6, n7, n8 * 255 / max + (n9 * 255 / max << 8) + (n10 * 255 / max << 16) - 16777216);
            }
        }
        for (int n11 = 0; n11 < 32; ++n11) {
            for (int n12 = 0; n12 < 256; ++n12) {
                this.sat.setRGB(n11, n12, n12 + (n12 << 8) + (n12 << 16) - 16777216);
            }
        }
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        this.ins = this.getInsets();
        if (this.getSize().width != this.bi.getWidth() + 64 + this.ins.left + this.ins.right || this.getSize().height != this.bi.getHeight() + this.ins.top + this.ins.bottom) {
            this.setSize(this.bi.getWidth() + 64 + this.ins.left + this.ins.right, this.bi.getHeight() + this.ins.top + this.ins.bottom);
        }
        graphics.drawImage(this.bi, this.ins.left, this.ins.top, this);
        graphics.drawImage(this.sat, this.ins.left + this.bi.getWidth() + 32, this.ins.top, this);
    }
}
