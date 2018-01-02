import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.image.PixelGrabber;
import java.awt.Insets;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class diaphoto extends Canvas
{
    Image pic;
    Image oi;
    Graphics og;
    Color photobg;
    int fxl;
    int fyl;
    int length;
    boolean copyright;
    int width;
    int height;
    int w;
    int h;
    int w2;
    int h2;
    int mousex;
    int mousey;
    int[] data;
    int[] dataNew;
    float scale;
    boolean resizeArray;
    boolean registered;
    
    public diaphoto(final Image pic, final int width, final int height, final int n, final int n2, final Color photobg) {
        this.pic = null;
        this.oi = null;
        this.fxl = 0;
        this.fyl = 0;
        this.length = 0;
        this.copyright = false;
        this.scale = 1.0f;
        this.resizeArray = false;
        this.registered = false;
        this.width = width;
        this.height = height;
        this.w2 = n;
        this.w = n;
        this.h2 = n2;
        this.h = n2;
        this.pic = pic;
        this.photobg = photobg;
        this.resize(width, height);
        this.setCursor(new Cursor(12));
        if (this.pic != null) {
            this.data = new int[n * n2];
            this.grabPixels();
        }
    }
    
    public int blue(final int n, final int n2) {
        return this.data[n * this.w + n2] & 0xFF;
    }
    
    public void brighten(final int n) {
        final float n2 = (100.0f + n) / 100.0f;
        if (this.resizeArray) {
            this.resizeAndCopyArray();
        }
        for (int i = 0; i < this.h2; ++i) {
            for (int j = 0; j < this.w2; ++j) {
                this.setRGB(i, j, (int)(this.red(i, j) * n2 + 0.5f), (int)(this.green(i, j) * n2 + 0.5f), (int)(this.blue(i, j) * n2 + 0.5f));
            }
        }
        this.buildImage();
        this.resizeArray = false;
        System.arraycopy(this.dataNew, 0, this.data, 0, this.length);
    }
    
    private void buildImage() {
        this.pic = this.createImage(new MemoryImageSource(this.w2, this.h2, this.dataNew, 0, this.w2));
    }
    
    public void contrast(final float n) {
        if (this.resizeArray) {
            this.resizeAndCopyArray();
        }
        int n4;
        int n3;
        int n2 = n3 = (n4 = 0);
        for (int i = 0; i < this.h2; ++i) {
            for (int j = 0; j < this.w2; ++j) {
                n3 += this.red(i, j);
                n2 += this.green(i, j);
                n4 += this.blue(i, j);
            }
        }
        final int n5 = n3 / (this.w2 * this.h2);
        final int n6 = n2 / (this.w2 * this.h2);
        final int n7 = n4 / (this.w2 * this.h2);
        for (int k = 0; k < this.h2; ++k) {
            for (int l = 0; l < this.w2; ++l) {
                this.setRGB(k, l, (int)(n * (this.red(k, l) - n5) + n5), (int)(n * (this.green(k, l) - n6) + n6), (int)(n * (this.blue(k, l) - n7) + n7));
            }
        }
        this.buildImage();
        this.resizeArray = false;
        System.arraycopy(this.dataNew, 0, this.data, 0, this.length);
    }
    
    public Insets getInsets() {
        return new Insets(0, 0, 0, 0);
    }
    
    private void grabPixels() {
        final PixelGrabber pixelGrabber = new PixelGrabber(this.pic, 0, 0, this.w, this.h, this.data, 0, this.w);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
    }
    
    public int green(final int n, final int n2) {
        return (this.data[n * this.w + n2] & 0xFF00) >> 8;
    }
    
    public Dimension minimizeSize() {
        return new Dimension(this.width, this.height);
    }
    
    public boolean mouseDrag(final Event event, final int mousex, final int mousey) {
        this.fxl += mousex - this.mousex;
        this.fyl += mousey - this.mousey;
        this.mousex = mousex;
        this.mousey = mousey;
        this.copyright = false;
        this.repaint();
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.copyright = false;
        this.repaint();
        return true;
    }
    
    public boolean mouseMove(final Event event, final int mousex, final int mousey) {
        this.copyright = true;
        this.mousex = mousex;
        this.mousey = mousey;
        this.repaint();
        return true;
    }
    
    public void newphoto(final Image pic, final int n, final int n2) {
        this.w2 = n;
        this.w = n;
        this.h2 = n2;
        this.h = n2;
        this.pic = pic;
        this.data = null;
        final boolean b = false;
        this.fyl = (b ? 1 : 0);
        this.fxl = (b ? 1 : 0);
        this.scale = 1.0f;
        this.resizeArray = false;
        if (pic != null) {
            this.length = n * n2;
            this.data = new int[this.length];
            this.dataNew = new int[this.length];
            this.grabPixels();
            System.arraycopy(this.data, 0, this.dataNew, 0, this.length);
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.og == null) {
            this.oi = this.createImage(this.width, this.height);
            this.og = this.oi.getGraphics();
        }
        this.og.setColor(this.photobg);
        this.og.fillRect(0, 0, this.width - 1, this.height - 1);
        if (this.pic != null) {
            this.og.drawImage(this.pic, this.fxl, this.fyl, this);
        }
        if (!this.registered && this.copyright) {
            final String s = "The J Maker (C) 2000";
            this.og.setFont(new Font("Helvetica", 1, 15));
            this.og.setColor(Color.blue);
            this.og.drawString(s, this.mousex - 1, this.mousey - 1);
            this.og.drawString(s, this.mousex - 1, this.mousey + 1);
            this.og.drawString(s, this.mousex + 1, this.mousey - 1);
            this.og.drawString(s, this.mousex + 1, this.mousey + 1);
            this.og.setColor(Color.cyan);
            this.og.drawString(s, this.mousex, this.mousey);
        }
        this.og.setColor(new Color(50, 50, 50));
        this.og.drawRect(0, 0, this.width - 1, this.height - 1);
        this.og.drawRect(2, 2, this.width - 5, this.height - 5);
        this.og.setColor(new Color(220, 220, 220));
        this.og.drawRect(1, 1, this.width - 3, this.height - 3);
        graphics.drawImage(this.oi, 0, 0, this);
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    public int red(final int n, final int n2) {
        return (this.data[n * this.w + n2] & 0xFF0000) >> 16;
    }
    
    public void resizeAndCopyArray() {
        this.data = null;
        this.length = this.w2 * this.h2;
        this.data = new int[this.length];
        this.w = this.w2;
        this.h = this.h2;
        System.arraycopy(this.dataNew, 0, this.data, 0, this.length);
    }
    
    public void saturate(final int n) {
        if (this.resizeArray) {
            this.resizeAndCopyArray();
        }
        for (int i = 0; i < this.h2; ++i) {
            for (int j = 0; j < this.w2; ++j) {
                final int n2 = (70 * this.red(i, j) - 59 * this.green(i, j) - 11 * this.blue(i, j)) / 100;
                final int n3 = (-30 * this.red(i, j) + 41 * this.green(i, j) - 11 * this.blue(i, j)) / 100;
                final int n4 = (-30 * this.red(i, j) - 59 * this.green(i, j) + 89 * this.blue(i, j)) / 100;
                final int n5 = (30 * this.red(i, j) + 59 * this.green(i, j) + 11 * this.blue(i, j)) / 100;
                this.setRGB(i, j, n5 + n2 * n / 100, n5 + n3 * n / 100, n5 + n4 * n / 100);
            }
        }
        this.buildImage();
        this.resizeArray = false;
        System.arraycopy(this.dataNew, 0, this.data, 0, this.length);
    }
    
    public void scalePhoto(final float n) {
        this.scale += n;
        this.w2 = (int)(this.scale * this.w + 0.5f);
        this.h2 = (int)(this.scale * this.h + 0.5f);
        if (this.w2 > 1500) {
            this.w2 = 1500;
            this.scale = 1500.0f / this.w;
            this.h2 = (int)(this.scale * this.h + 0.5f);
        }
        if (this.h2 > 1500) {
            this.h2 = 1500;
            this.scale = 1500.0f / this.h;
            this.w2 = (int)(this.scale * this.w + 0.5f);
        }
        if (this.w2 < 10) {
            this.w2 = 10;
            this.scale = 10.0f / this.w;
            this.h2 = (int)(this.scale * this.h + 0.5f);
        }
        if (this.h2 < 10) {
            this.h2 = 10;
            this.scale = 10.0f / this.h;
            this.w2 = (int)(this.scale * this.w + 0.5f);
        }
        this.dataNew = null;
        this.dataNew = new int[this.w2 * this.h2];
        final int n2 = this.w / 2;
        final int n3 = this.h / 2;
        final int n4 = this.w2 / 2;
        final int n5 = this.h2 / 2;
        for (int i = 0; i < this.h2; ++i) {
            for (int j = 0; j < this.w2; ++j) {
                final float n6 = (j - n4) / this.scale + n2;
                final float n7 = (i - n5) / this.scale + n3;
                if (n6 > 0.0f && n6 < this.w - 1 && n7 > 0.0f && n7 < this.h - 1) {
                    final float n8 = n6 - (int)n6;
                    final float n9 = n7 - (int)n7;
                    this.setRGB(i, j, (int)((1.0f - n8) * (1.0f - n9) * this.red((int)n7, (int)n6) + (1.0f - n8) * n9 * this.red((int)n7 + 1, (int)n6) + n8 * (1.0f - n9) * this.red((int)n7, (int)n6 + 1) + n8 * n9 * this.red((int)n7 + 1, (int)n6 + 1)), (int)((1.0f - n8) * (1.0f - n9) * this.green((int)n7, (int)n6) + (1.0f - n8) * n9 * this.green((int)n7 + 1, (int)n6) + n8 * (1.0f - n9) * this.green((int)n7, (int)n6 + 1) + n8 * n9 * this.green((int)n7 + 1, (int)n6 + 1)), (int)((1.0f - n8) * (1.0f - n9) * this.blue((int)n7, (int)n6) + (1.0f - n8) * n9 * this.blue((int)n7 + 1, (int)n6) + n8 * (1.0f - n9) * this.blue((int)n7, (int)n6 + 1) + n8 * n9 * this.blue((int)n7 + 1, (int)n6 + 1)));
                }
                else {
                    this.setRGB(i, j, 0, 0, 0);
                }
            }
        }
        this.buildImage();
        this.resizeArray = true;
    }
    
    public void setRGB(final int n, final int n2, int n3, int n4, int n5) {
        if (n3 > 255) {
            n3 = 255;
        }
        if (n4 > 255) {
            n4 = 255;
        }
        if (n5 > 255) {
            n5 = 255;
        }
        if (n3 < 0) {
            n3 = 0;
        }
        if (n4 < 0) {
            n4 = 0;
        }
        if (n5 < 0) {
            n5 = 0;
        }
        this.dataNew[n * this.w2 + n2] = new Color(n3, n4, n5).getRGB();
    }
    
    public void sharpen(final int n, final int n2) {
        final int w2 = this.w2;
        final int h2 = this.h2;
        final int[][] array = new int[h2][w2];
        final int[][] array2 = new int[h2][w2];
        final int[][] array3 = new int[h2][w2];
        if (this.resizeArray) {
            this.resizeAndCopyArray();
        }
        for (int i = 0; i < h2; ++i) {
            for (int j = 0; j < w2; ++j) {
                array[i][j] = this.red(i, j);
                array2[i][j] = this.green(i, j);
                array3[i][j] = this.blue(i, j);
            }
        }
        for (int k = 0; k < h2; ++k) {
            for (int l = 0; l < w2; ++l) {
                this.setRGB(k, l, (array[k][l] * n - array[(k - 1 + h2) % h2][l] - array[(k + 1 + h2) % h2][l] - array[k][(l - 1 + w2) % w2] - array[k][(l + 1 + w2) % w2] - array[(k - 1 + h2) % h2][(l - 1 + w2) % w2] - array[(k - 1 + h2) % h2][(l + 1 + w2) % w2] - array[(k + 1 + h2) % h2][(l - 1 + w2) % w2] - array[(k + 1 + h2) % h2][(l + 1 + w2) % w2]) / n2, (array2[k][l] * n - array2[(k - 1 + h2) % h2][l] - array2[(k + 1 + h2) % h2][l] - array2[k][(l - 1 + w2) % w2] - array2[k][(l + 1 + w2) % w2] - array2[(k - 1 + h2) % h2][(l - 1 + w2) % w2] - array2[(k - 1 + h2) % h2][(l + 1 + w2) % w2] - array2[(k + 1 + h2) % h2][(l - 1 + w2) % w2] - array2[(k + 1 + h2) % h2][(l + 1 + w2) % w2]) / n2, (array3[k][l] * n - array3[(k - 1 + h2) % h2][l] - array3[(k + 1 + h2) % h2][l] - array3[k][(l - 1 + w2) % w2] - array3[k][(l + 1 + w2) % w2] - array3[(k - 1 + h2) % h2][(l - 1 + w2) % w2] - array3[(k - 1 + h2) % h2][(l + 1 + w2) % w2] - array3[(k + 1 + h2) % h2][(l - 1 + w2) % w2] - array3[(k + 1 + h2) % h2][(l + 1 + w2) % w2]) / n2);
            }
        }
        this.buildImage();
        this.resizeArray = false;
        System.arraycopy(this.dataNew, 0, this.data, 0, this.length);
    }
    
    public void tint(final int n) {
        final double n2 = 3.14159 * n / 180.0;
        if (this.resizeArray) {
            this.resizeAndCopyArray();
        }
        final int n3 = (int)(256.0 * Math.sin(n2));
        final int n4 = (int)(256.0 * Math.cos(n2));
        for (int i = 0; i < this.h2; ++i) {
            for (int j = 0; j < this.w2; ++j) {
                final int n5 = (70 * this.red(i, j) - 59 * this.green(i, j) - 11 * this.blue(i, j)) / 100;
                final int n6 = (-30 * this.red(i, j) + 41 * this.green(i, j) - 11 * this.blue(i, j)) / 100;
                final int n7 = (-30 * this.red(i, j) - 59 * this.green(i, j) + 89 * this.blue(i, j)) / 100;
                final int n8 = (30 * this.red(i, j) + 59 * this.green(i, j) + 11 * this.blue(i, j)) / 100;
                final int n9 = (n3 * n7 + n4 * n5) / 256;
                final int n10 = (n4 * n7 - n3 * n5) / 256;
                this.setRGB(i, j, n8 + n9, n8 + (-51 * n9 - 19 * n10) / 100, n8 + n10);
            }
        }
        this.buildImage();
        this.resizeArray = false;
        System.arraycopy(this.dataNew, 0, this.data, 0, this.length);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
