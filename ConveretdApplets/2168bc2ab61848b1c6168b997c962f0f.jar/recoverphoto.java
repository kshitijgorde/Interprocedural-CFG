import java.awt.Font;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Insets;
import java.awt.image.PixelGrabber;
import java.applet.AudioClip;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class recoverphoto extends Canvas
{
    Image oi;
    Graphics og;
    boolean registered;
    boolean showCopyright;
    Image pic;
    Color bg;
    Applet app;
    int width;
    int height;
    int w;
    int h;
    int row;
    int col;
    int[] data;
    Image[] pieces;
    boolean gameover;
    int[] order;
    int moves;
    int movex;
    int movey;
    AudioClip winaudio;
    AudioClip moveaudio;
    
    public recoverphoto(final Image pic, final int width, final int height, final int row, final int col, final Applet app) {
        this.registered = false;
        this.showCopyright = false;
        this.gameover = false;
        this.moves = 0;
        this.movex = -1;
        this.movey = -1;
        this.pic = pic;
        this.app = app;
        this.width = width;
        this.height = height;
        this.row = row;
        this.col = col;
        this.resize(width, height);
        this.data = new int[width * height];
        this.grabPixels();
        this.makePieces(row, col);
        this.rescramble();
    }
    
    private void grabPixels() {
        final PixelGrabber pixelGrabber = new PixelGrabber(this.pic, 0, 0, this.width, this.height, this.data, 0, this.width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
    }
    
    public Insets insets() {
        return new Insets(0, 0, 0, 0);
    }
    
    public void makePieces(final int row, final int col) {
        this.row = row;
        this.col = col;
        this.w = this.width / col;
        this.h = this.height / row;
        this.pieces = null;
        this.pieces = new Image[row * col];
        this.order = null;
        this.order = new int[row * col];
        for (int i = 0; i < row * col; ++i) {
            final int[] array = new int[this.w * this.h];
            final int n = i % col * this.w;
            final int n2 = i / col * this.h;
            for (int j = 0; j < this.h; ++j) {
                for (int k = 0; k < this.w; ++k) {
                    final int n3 = (j + n2) * this.width + (k + n);
                    array[j * this.w + k] = new Color((this.data[n3] & 0xFF0000) >> 16, (this.data[n3] & 0xFF00) >> 8, this.data[n3] & 0xFF).getRGB();
                }
            }
            this.pieces[i] = this.createImage(new MemoryImageSource(this.w, this.h, array, 0, this.w));
            int width;
            for (int height = width = -1; width < 0 || height < 0; width = this.pieces[i].getWidth(this), height = this.pieces[i].getHeight(this)) {}
            this.order[i] = i;
        }
    }
    
    public Dimension minimizeSize() {
        return new Dimension(this.width, this.height);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        int n3 = -1;
        int n4 = 0;
        final int n5 = this.row * this.col;
        int i = 0;
        while (i < n5) {
            final int n6 = i % this.col * this.w;
            final int n7 = i / this.col * this.h;
            if (n >= n6 && n < n6 + this.w && n2 >= n7 && n2 < n7 + this.h) {
                this.moveaudio.play();
                n3 = i;
                if (this.order[n3] != n5 - 1) {
                    ++this.moves;
                    break;
                }
                break;
            }
            else {
                ++i;
            }
        }
        if (n3 > -1 && n3 % this.col != 0 && this.order[n3 - 1] == n5 - 1) {
            this.order[n3 - 1] = this.order[n3];
            this.order[n3] = n5 - 1;
            n4 = 1;
        }
        if (n4 == 0 && n3 > -1 && n3 % this.col != this.col - 1 && this.order[n3 + 1] == n5 - 1) {
            this.order[n3 + 1] = this.order[n3];
            this.order[n3] = n5 - 1;
            n4 = 1;
        }
        if (n4 == 0 && n3 > -1 && n3 / this.col >= 1 && this.order[n3 - this.col] == n5 - 1) {
            this.order[n3 - this.col] = this.order[n3];
            this.order[n3] = n5 - 1;
            n4 = 1;
        }
        if (n4 == 0 && n3 > -1 && n3 / this.col < this.row - 1 && this.order[n3 + this.col] == n5 - 1) {
            this.order[n3 + this.col] = this.order[n3];
            this.order[n3] = n5 - 1;
        }
        this.repaint();
        if (this.moves > 0) {
            for (int j = 0; j < n5; ++j) {
                if (this.order[j] != j) {
                    return true;
                }
            }
            this.winaudio.play();
            this.gameover = true;
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.showCopyright = false;
        this.app.showStatus(" recover (C) 2000 by The J Maker");
        this.repaint();
        this.movex = -1;
        this.movey = -1;
        return true;
    }
    
    public boolean mouseMove(final Event event, final int movex, final int movey) {
        this.movex = movex;
        this.movey = movey;
        this.showCopyright = true;
        this.app.showStatus(" http://www.thejmaker.com/");
        this.repaint();
        return true;
    }
    
    public void paint(final Graphics graphics) {
        final int n = this.row * this.col;
        if (this.og == null) {
            this.oi = this.createImage(this.width, this.height);
            this.og = this.oi.getGraphics();
        }
        this.og.setColor(this.bg);
        this.og.fillRect(0, 0, this.width, this.height);
        for (int i = 0; i < n; ++i) {
            if (this.order[i] != n - 1) {
                final int n2 = i % this.col * this.w;
                final int n3 = i / this.col * this.h;
                this.og.drawImage(this.pieces[this.order[i]], n2, n3, this);
                this.og.draw3DRect(n2, n3, this.w - 1, this.h - 1, true);
                this.og.draw3DRect(n2 + 1, n3 + 1, this.w - 3, this.h - 3, true);
            }
        }
        if (this.movex > -1 && this.movey > -1) {
            this.og.setFont(new Font("Helvetica", 1, 12));
            final String string = "Moves: " + this.moves;
            this.og.setColor(Color.blue);
            this.og.drawString(string, this.movex - 1, this.movey - 1);
            this.og.drawString(string, this.movex - 1, this.movey + 1);
            this.og.drawString(string, this.movex + 1, this.movey - 1);
            this.og.drawString(string, this.movex + 1, this.movey + 1);
            this.og.setColor(Color.yellow);
            this.og.drawString(string, this.movex, this.movey);
        }
        if (this.gameover) {
            this.og.setFont(new Font("Helvetica", 1, 50));
            final String s = "Well done!!!";
            final int n4 = (this.width - this.og.getFontMetrics().stringWidth(s)) / 2;
            final int n5 = this.height / 2;
            this.og.setColor(Color.blue);
            this.og.drawString(s, n4 - 1, n5 - 1);
            this.og.drawString(s, n4 - 1, n5 + 1);
            this.og.drawString(s, n4 + 1, n5 - 1);
            this.og.drawString(s, n4 + 1, n5 + 1);
            this.og.setColor(Color.cyan);
            this.og.drawString(s, n4, n5);
        }
        if (!this.registered && this.showCopyright) {
            this.og.setFont(new Font("Helvetica", 1, 12));
            final String s2 = "(C) The J Maker";
            final int n6 = this.width - this.og.getFontMetrics().stringWidth(s2) - 4;
            final int n7 = 15;
            this.og.setColor(Color.black);
            this.og.drawString(s2, n6 - 1, n7 - 1);
            this.og.drawString(s2, n6 - 1, n7 + 1);
            this.og.drawString(s2, n6 + 1, n7 - 1);
            this.og.drawString(s2, n6 + 1, n7 + 1);
            this.og.setColor(Color.green);
            this.og.drawString(s2, n6, n7);
        }
        graphics.drawImage(this.oi, 0, 0, this);
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    public void rescramble() {
        for (int n = this.row * this.col, i = 0; i < n; ++i) {
            final int n2 = (int)(Math.round(Math.random() * 10.0 * n * n) % n);
            int n3 = (int)(Math.round(Math.random() * 10.0 * n * n) % n);
            if (n2 == n3) {
                n3 = (n2 + 1) % n;
            }
            final int n4 = this.order[n2];
            this.order[n2] = this.order[n3];
            this.order[n3] = n4;
        }
        this.repaint();
    }
    
    public void resetPositions() {
        for (int i = 0; i < this.row * this.col; ++i) {
            this.order[i] = i;
        }
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
