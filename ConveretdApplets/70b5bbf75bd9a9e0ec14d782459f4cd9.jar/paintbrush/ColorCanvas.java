// 
// Decompiled by Procyon v0.5.30
// 

package paintbrush;

import java.awt.image.ImageObserver;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Color;
import java.awt.Canvas;

public class ColorCanvas extends Canvas
{
    PaintCanvas canvas;
    int width;
    int height;
    int whBox;
    int whDBox;
    Color backLight;
    Color darkGray;
    Color lightGray;
    Color[] colorsDiff;
    Color[] colorsGray;
    Color[] colors;
    Image offscreen;
    Dimension offscreensize;
    Graphics offgraphics;
    boolean first;
    int activeBack;
    int activeFront;
    
    public ColorCanvas(final PaintCanvas canvas) {
        this.whBox = 15;
        this.whDBox = 30;
        this.backLight = new Color(252, 254, 252);
        this.darkGray = new Color(132, 130, 132);
        this.lightGray = new Color(212, 210, 204);
        this.colorsDiff = new Color[] { Color.black, new Color(132, 130, 132), new Color(252, 130, 68), new Color(132, 130, 4), new Color(4, 130, 4), new Color(4, 130, 132), new Color(4, 2, 132), new Color(132, 2, 132), Color.white, new Color(196, 194, 196), new Color(252, 2, 4), new Color(252, 254, 4), new Color(4, 254, 4), new Color(4, 254, 252), new Color(4, 2, 252), new Color(252, 2, 252) };
        this.colorsGray = new Color[] { Color.black, new Color(51, 51, 51), new Color(70, 70, 70), new Color(84, 84, 84), new Color(96, 96, 96), new Color(112, 112, 112), new Color(128, 128, 128), new Color(144, 144, 144), Color.white, new Color(242, 242, 242), new Color(229, 229, 229), new Color(217, 217, 217), new Color(204, 204, 204), new Color(192, 192, 192), new Color(176, 176, 176), new Color(160, 160, 160) };
        this.first = true;
        this.activeBack = 8;
        this.activeFront = 0;
        this.canvas = canvas;
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent e) {
                ColorCanvas.this.click(e.getX(), e.getY(), e.getModifiers() == 16);
            }
        });
        this.colors = this.colorsDiff;
    }
    
    private void click(final int x, final int y, final boolean rightButton) {
        final int col = (this.colors.length + 1) / 2;
        if (x < this.whDBox || x > col * this.whBox + this.whDBox) {
            return;
        }
        if (y > this.whDBox) {
            return;
        }
        final int i = this.getActiveLine(y, 2);
        final int j = this.getActiveColumn(x, col);
        final int oldal = rightButton ? this.activeFront : this.activeBack;
        final int act = i * col + j;
        if (rightButton) {
            this.activeFront = act;
        }
        else {
            this.activeBack = act;
        }
        if (oldal != act) {
            if (rightButton) {
                this.drawFrontColor(this.offgraphics);
                this.canvas.setFrontColor(this.colors[act]);
            }
            else {
                this.drawBackColor(this.offgraphics);
                this.canvas.setBackColor(this.colors[act]);
            }
            this.repaint();
        }
    }
    
    private synchronized void createPicture() {
        if (this.offscreen == null || this.width != this.offscreensize.width || this.height != this.offscreensize.height) {
            this.offscreen = this.createImage(this.width, this.height);
            this.offscreensize = new Dimension(this.width, this.height);
            this.offgraphics = this.offscreen.getGraphics();
        }
        this.offgraphics.setColor(this.lightGray);
        this.offgraphics.fillRect(0, 0, this.width, this.height);
        this.drawColorCanvas(this.offgraphics);
        for (int cn = this.colors.length, i = 0; i < cn; ++i) {
            this.drawColorButton(this.offgraphics, i);
        }
        this.offgraphics.setColor(this.darkGray);
        final int x = (this.colors.length + 1) / 2 * this.whBox + this.whDBox + 1;
        this.offgraphics.drawLine(this.whDBox, 31, x, 31);
        this.offgraphics.drawLine(x, 0, x, 31);
    }
    
    private void drawBackColor(final Graphics g) {
        g.setColor(this.colors[this.activeBack]);
        g.fillRect(12, 18, 11, 5);
        g.fillRect(18, 12, 5, 11);
    }
    
    private void drawColorButton(final Graphics g, final int num) {
        final int col = (this.colors.length + 1) / 2;
        final int x = this.whBox * (num % col) + this.whDBox + 1;
        final int y = (num < col) ? 0 : this.whBox;
        g.setColor(this.darkGray);
        g.drawLine(x, y, x + this.whBox - 1, y);
        g.drawLine(x, y, x, y + this.whBox - 1);
        g.setColor(Color.black);
        g.drawLine(x + 1, y + 1, x + this.whBox - 2, y + 1);
        g.drawLine(x + 1, y + 1, x + 1, y + this.whBox - 2);
        g.setColor(this.lightGray);
        g.drawLine(x + 1, y + this.whBox - 2, x + this.whBox - 1, y + this.whBox - 2);
        g.drawLine(x + this.whBox - 2, y + 1, x + this.whBox - 2, y + this.whBox - 1);
        g.setColor(Color.white);
        g.drawLine(x, y + this.whBox - 1, x + this.whBox, y + this.whBox - 1);
        g.drawLine(x + this.whBox - 1, y, x + this.whBox - 1, y + this.whBox);
        g.setColor(this.colors[num]);
        g.fillRect(x + 2, y + 2, 11, 11);
    }
    
    private void drawColorCanvas(final Graphics g) {
        g.setColor(this.backLight);
        g.fillRect(2, 2, this.whDBox - 4, this.whDBox - 4);
        g.setColor(this.darkGray);
        g.drawLine(0, 0, this.whDBox - 1, 0);
        g.drawLine(0, 0, 0, this.whDBox - 1);
        g.setColor(Color.black);
        g.drawLine(1, 1, this.whDBox - 2, 1);
        g.drawLine(1, 1, 1, this.whDBox - 2);
        g.setColor(this.lightGray);
        g.drawLine(1, this.whDBox - 2, this.whDBox - 1, this.whDBox - 2);
        g.drawLine(this.whDBox - 2, 1, this.whDBox - 2, this.whDBox - 1);
        g.setColor(Color.white);
        g.drawLine(0, this.whDBox - 1, this.whDBox, this.whDBox - 1);
        g.drawLine(this.whDBox - 1, 0, this.whDBox - 1, this.whDBox);
        g.setColor(Color.white);
        g.drawLine(10, 10, 10 + this.whBox, 10);
        g.drawLine(10, 10, 10, this.whBox + 10);
        g.setColor(this.darkGray);
        g.drawLine(11, this.whBox + 9, this.whBox + 9, this.whBox + 9);
        g.drawLine(this.whBox + 9, 11, this.whBox + 9, this.whBox + 9);
        g.setColor(this.lightGray);
        g.fillRect(11, 11, this.whBox - 2, this.whBox - 2);
        g.setColor(Color.white);
        g.drawLine(3, 3, 3 + this.whBox, 3);
        g.drawLine(3, 3, 3, this.whBox + 3);
        g.setColor(this.darkGray);
        g.drawLine(4, this.whBox + 2, this.whBox + 2, this.whBox + 2);
        g.drawLine(this.whBox + 2, 4, this.whBox + 2, this.whBox + 2);
        g.setColor(this.lightGray);
        g.fillRect(4, 4, this.whBox - 2, this.whBox - 2);
        this.drawFrontColor(g);
        this.drawBackColor(g);
    }
    
    private void drawFrontColor(final Graphics g) {
        g.setColor(this.colors[this.activeFront]);
        g.fillRect(5, 5, 11, 11);
    }
    
    private int getActiveColumn(final int x, final int denom) {
        int curX = this.whDBox + 1;
        final int wei = this.whBox;
        for (int i = 0; i < denom; ++i) {
            if (x > curX && x < curX + wei) {
                return i;
            }
            curX += wei;
        }
        return 0;
    }
    
    private int getActiveLine(final int y, final int denom) {
        int curY = 0;
        final int hei = this.whBox;
        for (int i = 0; i < denom; ++i) {
            if (y > curY && y < curY + hei) {
                return i;
            }
            curY += hei;
        }
        return 0;
    }
    
    public void paint(final Graphics g) {
        if (this.first) {
            this.preparation(g);
        }
        g.drawImage(this.offscreen, 0, 0, null);
    }
    
    public void preparation(final Graphics g) {
        this.width = this.getSize().width;
        this.height = this.getSize().height;
        this.createPicture();
        this.first = false;
    }
    
    public void setGrayscale(final boolean b) {
        if (!b) {
            this.colors = this.colorsDiff;
        }
        else {
            this.colors = this.colorsGray;
        }
        if (!this.first) {
            this.createPicture();
            this.repaint();
            this.canvas.setFrontColor(this.colors[this.activeFront]);
            this.canvas.setBackColor(this.colors[this.activeBack]);
        }
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
}
