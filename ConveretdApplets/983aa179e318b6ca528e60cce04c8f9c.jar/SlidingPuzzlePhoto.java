import java.awt.Event;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class SlidingPuzzlePhoto extends Canvas
{
    Image oi;
    Graphics og;
    boolean registered;
    boolean showCopyright;
    Image pic;
    Color bg;
    Color overc;
    Color shadowup;
    Color shadowdown;
    int delay;
    SlidingPuzzle app;
    int width;
    int height;
    int w;
    int h;
    int row;
    int col;
    int[] data;
    Image[] pieces;
    Image shad;
    boolean gameover;
    int[] order;
    int[] ordernew;
    int target;
    int targetx;
    int targety;
    int mouseover;
    int moves;
    int movex;
    int movey;
    int best;
    AudioClip winaudio;
    AudioClip moveaudio;
    String MovesCountText;
    String FinishText;
    String BestText;
    int sw1;
    int sw2;
    int transit;
    int maxtransit;
    int oldrow;
    int oldcol;
    int soff;
    boolean bMoveAll;
    
    public SlidingPuzzlePhoto(final Image pic, final int width, final int height, final int row, final int col, final SlidingPuzzle app) {
        this.registered = false;
        this.showCopyright = false;
        this.shadowup = new Color(220, 220, 220);
        this.shadowdown = new Color(50, 50, 50);
        this.delay = 20;
        this.gameover = false;
        this.target = -1;
        this.targetx = 0;
        this.targety = 0;
        this.mouseover = -1;
        this.moves = 0;
        this.movex = -1;
        this.movey = -1;
        this.best = 9999;
        this.sw1 = -1;
        this.sw2 = -1;
        this.transit = 0;
        this.maxtransit = 10;
        this.oldrow = 0;
        this.oldcol = 0;
        this.soff = 5;
        this.bMoveAll = false;
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
        this.repaint();
    }
    
    public void makePieces(final int n, final int n2) {
        this.row = n;
        this.col = n2;
        if (n == this.oldrow && n2 == this.oldcol) {
            return;
        }
        final int n3 = n * n2;
        this.w = this.width / n2;
        this.h = this.height / n;
        this.pieces = null;
        this.pieces = new Image[n3];
        this.order = null;
        this.order = new int[n3];
        this.ordernew = new int[n3];
        final int[] array = new int[n3];
        final int[] array2 = new int[n3];
        this.shad = null;
        final int[] array3 = new int[this.w * this.h];
        for (int i = 0; i < n3; ++i) {
            final int n4 = i % n2 * this.w;
            final int n5 = i / n2 * this.h;
            for (int j = 0; j < this.h; ++j) {
                for (int k = 0; k < this.w; ++k) {
                    final int n6 = (j + n5) * this.width + (k + n4);
                    array3[j * this.w + k] = new Color((this.data[n6] & 0xFF0000) >> 16, (this.data[n6] & 0xFF00) >> 8, this.data[n6] & 0xFF).getRGB();
                }
            }
            this.pieces[i] = this.createImage(new MemoryImageSource(this.w, this.h, array3, 0, this.w));
            array[i] = (array2[i] = -1);
            while (array[i] < 0 || array2[i] < 0) {
                array[i] = this.pieces[i].getWidth(this);
                array2[i] = this.pieces[i].getHeight(this);
            }
            this.order[i] = i;
            this.ordernew[i] = i;
        }
        final int n9;
        final int n8;
        final int n7 = n8 = (n9 = 50);
        final int n10 = 0x0 | (n8 & 0xFF) << 16 | (n7 & 0xFF) << 8 | (n9 & 0xFF);
        final int n11 = 0xFF000000 | (n8 & 0xFF) << 16 | (n7 & 0xFF) << 8 | (n9 & 0xFF);
        for (int l = 0; l < this.h; ++l) {
            for (int n12 = 0; n12 < this.w; ++n12) {
                if (n12 % 2 == 1 || l % 2 == 1) {
                    array3[l * this.w + n12] = n11;
                }
                else {
                    array3[l * this.w + n12] = n10;
                }
            }
        }
        this.shad = this.createImage(new MemoryImageSource(this.w, this.h, array3, 0, this.w));
        array[0] = (array2[0] = -1);
        while (array[0] < 0 || array2[0] < 0) {
            array[0] = this.shad.getWidth(this);
            array2[0] = this.shad.getHeight(this);
        }
        this.oldrow = n;
        this.oldcol = n2;
    }
    
    public void resetPositions() {
        final int n = this.row * this.col;
        final Graphics graphics = this.getGraphics();
        this.bMoveAll = true;
        final int n2 = -1;
        this.sw2 = n2;
        this.sw1 = n2;
        for (int i = 0; i < n; ++i) {
            this.ordernew[i] = this.order[i];
        }
        this.soff = 5;
        for (int j = 1; j <= this.maxtransit; ++j) {
            this.transit = j;
            this.paint(graphics);
            try {
                Thread.sleep(this.delay);
            }
            catch (Exception ex) {}
        }
        for (int k = 0; k < n; ++k) {
            this.order[k] = k;
        }
        this.transit = 0;
        this.bMoveAll = false;
    }
    
    public void rescramble() {
        final int n = this.row * this.col;
        final Graphics graphics = this.getGraphics();
        this.bMoveAll = true;
        final int n2 = -1;
        this.sw2 = n2;
        this.sw1 = n2;
        final int[] array = new int[n];
        for (int i = 0; i < n; ++i) {
            array[i] = this.order[i];
        }
        for (int j = 0; j < n; ++j) {
            final int n3 = j;
            int n4 = (int)(Math.round(Math.random() * 10.0 * n * n) % n);
            if (n3 == n4) {
                n4 = (n3 + 1) % n;
            }
            final int n5 = array[n3];
            array[n3] = array[n4];
            array[n4] = n5;
        }
        for (int k = 0; k < n; ++k) {
            for (int l = 0; l < n; ++l) {
                if (array[l] == this.order[k]) {
                    this.ordernew[k] = l;
                    break;
                }
            }
        }
        this.soff = 5;
        for (int transit = 1; transit <= this.maxtransit; ++transit) {
            this.transit = transit;
            this.paint(graphics);
            try {
                Thread.sleep(this.delay);
            }
            catch (Exception ex) {}
        }
        for (int n6 = 0; n6 < n; ++n6) {
            this.order[n6] = array[n6];
        }
        this.transit = 0;
        this.bMoveAll = false;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void grabPixels() {
        final PixelGrabber pixelGrabber = new PixelGrabber(this.pic, 0, 0, this.width, this.height, this.data, 0, this.width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
    }
    
    public Dimension minimizeSize() {
        return new Dimension(this.width, this.height);
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    public Insets insets() {
        return new Insets(0, 0, 0, 0);
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
            if (this.order[i] != n - 1 && i != this.target && i != this.sw1 && i != this.sw2) {
                if (this.bMoveAll) {
                    final int n2 = i % this.col * this.w;
                    final int n3 = i / this.col * this.h;
                    final int n4 = this.ordernew[i] % this.col * this.w;
                    final int n5 = this.ordernew[i] / this.col * this.h;
                    final int n6 = n2 + this.transit * (n4 - n2) / this.maxtransit;
                    final int n7 = n3 + this.transit * (n5 - n3) / this.maxtransit;
                    this.og.drawImage(this.shad, n6 + this.soff, n7 + this.soff, this);
                    this.og.drawImage(this.pieces[this.order[i]], n6 - this.soff, n7 - this.soff, this);
                    if (!this.gameover) {
                        this.og.setColor(this.shadowdown);
                        this.og.drawRect(n6 - this.soff, n7 - this.soff, this.w - 1, this.h - 1);
                    }
                }
                else {
                    final int n8 = i % this.col * this.w;
                    final int n9 = i / this.col * this.h;
                    this.og.drawImage(this.pieces[this.order[i]], n8, n9, this);
                    if (!this.gameover) {
                        if (i == this.mouseover) {
                            this.og.setColor(this.overc);
                        }
                        else {
                            this.og.setColor(this.shadowdown);
                        }
                        this.og.drawRect(n8, n9, this.w - 1, this.h - 1);
                    }
                }
            }
        }
        if (this.target > -1) {
            this.og.setColor(this.shadowdown);
            this.og.drawImage(this.shad, this.targetx + 10, this.targety + 10, this);
            this.og.drawImage(this.pieces[this.order[this.target]], this.targetx, this.targety, this);
            if (!this.gameover) {
                this.og.setColor(this.shadowdown);
                this.og.drawRect(this.targetx + 1, this.targety + 1, this.w - 2, this.h - 2);
                this.og.setColor(this.shadowup);
                this.og.drawRect(this.targetx, this.targety, this.w - 1, this.h - 1);
            }
        }
        else if (this.sw1 > -1) {
            for (int j = 0; j < 2; ++j) {
                if (j == 0 && this.order[this.sw1] != n - 1) {
                    final int n10 = this.sw1 % this.col * this.w;
                    final int n11 = this.sw1 / this.col * this.h;
                    final int n12 = this.sw2 % this.col * this.w;
                    final int n13 = this.sw2 / this.col * this.h;
                    final int n14 = n10 + this.transit * (n12 - n10) / this.maxtransit;
                    final int n15 = n11 + this.transit * (n13 - n11) / this.maxtransit;
                    this.og.drawImage(this.shad, n14 + this.soff, n15 + this.soff, this);
                    this.og.drawImage(this.pieces[this.order[this.sw1]], n14 - this.soff, n15 - this.soff, this);
                    this.og.setColor(this.shadowdown);
                    this.og.drawRect(n14 - this.soff, n15 - this.soff, this.w - 1, this.h - 1);
                }
                else if (j == 1 && this.order[this.sw2] != n - 1) {
                    final int n16 = this.sw2 % this.col * this.w;
                    final int n17 = this.sw2 / this.col * this.h;
                    final int n18 = this.sw1 % this.col * this.w;
                    final int n19 = this.sw1 / this.col * this.h;
                    final int n20 = n16 + this.transit * (n18 - n16) / this.maxtransit;
                    final int n21 = n17 + this.transit * (n19 - n17) / this.maxtransit;
                    this.og.drawImage(this.shad, n20 + this.soff, n21 + this.soff, this);
                    this.og.drawImage(this.pieces[this.order[this.sw2]], n20 - this.soff, n21 - this.soff, this);
                    this.og.setColor(this.shadowdown);
                    this.og.drawRect(n20 - this.soff, n21 - this.soff, this.w - 1, this.h - 1);
                }
            }
        }
        if (this.movex > -1 && this.movey > -1) {
            this.og.setFont(new Font("Helvetica", 1, 11));
            final String string = this.MovesCountText + " " + this.moves;
            this.og.setColor(Color.black);
            final int n22 = 5;
            final int n23 = 24;
            this.og.drawString(string, n22, n23 - 13);
            this.og.drawString(string, n22, n23 - 11);
            this.og.drawString(string, n22 - 1, n23 - 12);
            this.og.drawString(string, n22 + 1, n23 - 12);
            this.og.setColor(Color.white);
            this.og.drawString(string, n22, n23 - 12);
            String string2 = this.BestText + " " + this.best;
            if (this.best >= 9999) {
                string2 = "";
            }
            this.og.setColor(Color.black);
            this.og.drawString(string2, n22, n23 - 1);
            this.og.drawString(string2, n22, n23 + 1);
            this.og.drawString(string2, n22 - 1, n23);
            this.og.drawString(string2, n22 + 1, n23);
            this.og.setColor(Color.white);
            this.og.drawString(string2, n22, n23);
        }
        if (this.gameover) {
            if (this.moves < this.best) {
                this.best = this.moves;
            }
            this.og.setFont(new Font("Helvetica", 1, 20));
            final String finishText = this.FinishText;
            final int n24 = (this.width - this.og.getFontMetrics().stringWidth(finishText)) / 2;
            final int n25 = this.height / 2;
            this.og.setColor(Color.black);
            this.og.drawString(finishText, n24, n25 - 1);
            this.og.drawString(finishText, n24, n25 + 1);
            this.og.drawString(finishText, n24 - 1, n25);
            this.og.drawString(finishText, n24 + 1, n25);
            this.og.setColor(Color.white);
            this.og.drawString(finishText, n24, n25);
        }
        if (!this.registered && this.showCopyright) {
            this.og.setFont(new Font("Helvetica", 1, 11));
            final String s = "SlidingPuzzle (C) thejmaker.com 2002";
            final int n26 = this.width - this.og.getFontMetrics().stringWidth(s) - 4;
            final int n27 = this.height - 5;
            this.og.setColor(Color.blue);
            this.og.drawString(s, n26, n27 - 1);
            this.og.drawString(s, n26, n27 + 1);
            this.og.drawString(s, n26 - 1, n27);
            this.og.drawString(s, n26 + 1, n27);
            this.og.setColor(Color.cyan);
            this.og.drawString(s, n26, n27);
        }
        graphics.drawImage(this.oi, 0, 0, this);
    }
    
    public void animatedMove() {
        this.moveaudio.stop();
        this.moveaudio.play();
        final int maxtransit = this.maxtransit;
        final Graphics graphics = this.getGraphics();
        this.soff = 0;
        this.maxtransit = 15;
        for (int i = 1; i <= this.maxtransit; ++i) {
            this.transit = i;
            this.paint(graphics);
            try {
                Thread.sleep(this.delay);
            }
            catch (Exception ex) {}
        }
        final int n = -1;
        this.sw2 = n;
        this.sw1 = n;
        this.transit = 0;
        this.maxtransit = maxtransit;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        int n3 = -1;
        int n4 = 0;
        final int n5 = this.row * this.col;
        for (int i = 0; i < n5; ++i) {
            final int n6 = i % this.col * this.w;
            final int n7 = i / this.col * this.h;
            if (n >= n6 && n < n6 + this.w && n2 >= n7 && n2 < n7 + this.h) {
                n3 = i;
                break;
            }
        }
        if (n3 > -1 && n3 % this.col != 0 && this.order[n3 - 1] == n5 - 1) {
            this.sw1 = n3;
            this.sw2 = n3 - 1;
            this.animatedMove();
            this.order[n3 - 1] = this.order[n3];
            this.order[n3] = n5 - 1;
            n4 = 1;
        }
        if (n4 == 0 && n3 > -1 && n3 % this.col != this.col - 1 && this.order[n3 + 1] == n5 - 1) {
            this.sw1 = n3;
            this.sw2 = n3 + 1;
            this.animatedMove();
            this.order[n3 + 1] = this.order[n3];
            this.order[n3] = n5 - 1;
            n4 = 1;
        }
        if (n4 == 0 && n3 > -1 && n3 / this.col >= 1 && this.order[n3 - this.col] == n5 - 1) {
            this.sw1 = n3;
            this.sw2 = n3 - this.col;
            this.animatedMove();
            this.order[n3 - this.col] = this.order[n3];
            this.order[n3] = n5 - 1;
            n4 = 1;
        }
        if (n4 == 0 && n3 > -1 && n3 / this.col < this.row - 1 && this.order[n3 + this.col] == n5 - 1) {
            this.sw1 = n3;
            this.sw2 = n3 + this.col;
            this.animatedMove();
            this.order[n3 + this.col] = this.order[n3];
            this.order[n3] = n5 - 1;
            n4 = 1;
        }
        if (n4 != 0) {
            ++this.moves;
        }
        this.repaint();
        if (this.moves > 0) {
            for (int j = 0; j < n5; ++j) {
                if (this.order[j] != j) {
                    return true;
                }
            }
            this.gameover = true;
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.showCopyright = false;
        if (!this.registered) {
            this.app.showStatus("SlidingPuzzle (C) thejmaker.com 2002");
        }
        else {
            this.app.showStatus("");
        }
        this.repaint();
        this.movex = -1;
        this.movey = -1;
        this.mouseover = -1;
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        final int n3 = this.row * this.col;
        if (this.target > -1) {
            final int n4 = this.targetx + this.w / 2;
            final int n5 = this.targety + this.h / 2;
            int target = this.target;
            int n6 = this.w * this.w + this.h * this.h;
            for (int i = 0; i < n3; ++i) {
                final int n7 = i % this.col * this.w + this.w / 2;
                final int n8 = i / this.col * this.h + this.h / 2;
                final int n9 = (n4 - n7) * (n4 - n7) + (n5 - n8) * (n5 - n8);
                if (n9 < n6) {
                    target = i;
                    n6 = n9;
                }
            }
            if (this.target != target) {
                ++this.moves;
            }
            final int n10 = this.order[this.target];
            this.order[this.target] = this.order[target];
            this.order[target] = n10;
            this.target = -1;
        }
        this.showCopyright = true;
        this.repaint();
        for (int j = 0; j < n3; ++j) {
            if (this.order[j] != j) {
                return true;
            }
        }
        this.winaudio.play();
        this.app.tstart = 0L;
        return this.gameover = true;
    }
    
    public boolean mouseMove(final Event event, final int movex, final int movey) {
        for (int n = this.row * this.col, i = 0; i < n; ++i) {
            final int n2 = i % this.col * this.w;
            final int n3 = i / this.col * this.h;
            if (movex >= n2 && movex < n2 + this.w && movey >= n3 && movey < n3 + this.h) {
                this.mouseover = i;
                break;
            }
        }
        this.movex = movex;
        this.movey = movey;
        this.showCopyright = true;
        if (this.registered) {
            this.app.showStatus("");
        }
        else {
            this.app.showStatus("SlidingPuzzle (C) thejmaker.com 2002");
        }
        this.repaint();
        return true;
    }
}
