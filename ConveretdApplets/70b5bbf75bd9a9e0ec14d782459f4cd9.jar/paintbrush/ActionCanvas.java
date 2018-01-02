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
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.Canvas;

public class ActionCanvas extends Canvas
{
    public static final int LINE = 0;
    public static final int TEXT = 1;
    public static final int PEN = 2;
    public static final int SPRAY = 3;
    public static final int RECT = 4;
    public static final int CIRC = 5;
    public static final int ERASER = 6;
    PaintCanvas canvas;
    FontChoice fontChoice;
    int width;
    int height;
    int whBox;
    int wDoubleBox;
    int hDoubleBox;
    Image spray;
    Image spray_on;
    Image pen;
    Image pen_on;
    Image eraser;
    Image eraser_on;
    int imageDim;
    Color back;
    Color backLight;
    Color backDark;
    Color activeLineColor;
    Font font;
    FontMetrics fm;
    String[] fontnames;
    int[] fontstyles;
    int[] fontsizes;
    int[] spraysizes;
    int[] pensizes;
    Font curFont;
    Image offscreen;
    Dimension offscreensize;
    Graphics offgraphics;
    boolean first;
    boolean needCreate;
    int active;
    int activeLine;
    int activeName;
    int activeStyle;
    int activeSize;
    int activePen;
    int activeSpray;
    int activeRect;
    int activeCirc;
    int activeEraser;
    
    public ActionCanvas(final PaintCanvas canvas, final FontChoice fontChoice) {
        this.whBox = 25;
        this.wDoubleBox = 50;
        this.hDoubleBox = 60;
        this.back = new Color(212, 210, 204);
        this.backLight = new Color(252, 254, 252);
        this.backDark = new Color(132, 130, 132);
        this.activeLineColor = Color.blue;
        this.font = new Font("Serif", 1, 16);
        this.fontnames = new String[] { "San-Serif", "Serif", "Courier", "Serif" };
        this.fontstyles = new int[] { 0, 1, 2, 1 };
        this.fontsizes = new int[] { 26, 36, 46, 16 };
        this.spraysizes = new int[] { 5, 12, 19, 26, 5 };
        this.pensizes = new int[] { 1, 3, 5, 10, 2 };
        this.first = true;
        this.needCreate = false;
        this.active = 0;
        this.activeLine = 0;
        this.activeName = 2;
        this.activeStyle = 1;
        this.activeSize = 0;
        this.activePen = 0;
        this.activeSpray = 0;
        this.activeRect = 0;
        this.activeCirc = 0;
        this.activeEraser = 0;
        this.canvas = canvas;
        this.fontChoice = fontChoice;
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent e) {
                ActionCanvas.this.click(e.getX(), e.getY());
            }
        });
    }
    
    private void click(final int x, final int y) {
        if (x > 2 * this.whBox + 2) {
            return;
        }
        if ((y > 4 * this.whBox + 6 && y < 4 * this.whBox + 17) || y > 4 * this.whBox + 19 + this.hDoubleBox) {
            return;
        }
        final boolean b = x > 0 && x < this.whBox + 2;
        if (y > 0 && y < this.whBox + 2) {
            if (b) {
                this.setActive(0);
            }
            else {
                this.setActive(1);
            }
        }
        else if (y < 2 * this.whBox + 4) {
            if (b) {
                this.setActive(2);
            }
            else {
                this.setActive(3);
            }
        }
        else if (y < 3 * this.whBox + 6) {
            if (b) {
                this.setActive(4);
            }
            else {
                this.setActive(5);
            }
        }
        else if (y < 4 * this.whBox + 8) {
            if (b) {
                this.setActive(6);
            }
        }
        else {
            switch (this.active) {
                case 0: {
                    final int old = this.activeLine;
                    this.activeLine = this.getActiveLine(y, 5);
                    if (old != this.activeLine) {
                        this.canvas.setThickness(this.activeLine + 1);
                        this.drawNumLine(this.offgraphics, old);
                        this.drawNumLine(this.offgraphics, this.activeLine);
                        this.repaint();
                        break;
                    }
                    break;
                }
                case 2: {
                    final int i = this.getActiveLine(y, 2);
                    final int j = this.getActiveColumn(x, 2);
                    final int old = this.activePen;
                    this.activePen = 2 * i + j;
                    if (old != this.activePen) {
                        this.canvas.setPenSize(this.pensizes[this.activePen]);
                        this.drawNumPen(this.offgraphics, old, 2);
                        this.drawNumPen(this.offgraphics, this.activePen, 2);
                        this.repaint();
                        break;
                    }
                    break;
                }
                case 3: {
                    final int i = this.getActiveLine(y, 2);
                    final int j = this.getActiveColumn(x, 2);
                    final int old = this.activeSpray;
                    this.activeSpray = 2 * i + j;
                    if (old != this.activeSpray) {
                        this.canvas.setSpraySize(this.spraysizes[this.activeSpray]);
                        this.drawNumSpray(this.offgraphics, old);
                        this.drawNumSpray(this.offgraphics, this.activeSpray);
                        this.repaint();
                        break;
                    }
                    break;
                }
                case 4: {
                    final int old = this.activeRect;
                    this.activeRect = this.getActiveLine(y, 3);
                    if (old != this.activeRect) {
                        this.canvas.setRectType(this.activeRect);
                        this.drawNumRect(this.offgraphics, old, 4);
                        this.drawNumRect(this.offgraphics, this.activeRect, 4);
                        this.repaint();
                        break;
                    }
                    break;
                }
                case 5: {
                    final int old = this.activeCirc;
                    this.activeCirc = this.getActiveLine(y, 3);
                    if (old != this.activeCirc) {
                        this.canvas.setCircType(this.activeCirc);
                        this.drawNumRect(this.offgraphics, old, 5);
                        this.drawNumRect(this.offgraphics, this.activeCirc, 5);
                        this.repaint();
                    }
                }
                case 6: {
                    final int i = this.getActiveLine(y, 2);
                    final int j = this.getActiveColumn(x, 2);
                    final int old = this.activeEraser;
                    this.activeEraser = 2 * i + j;
                    if (old != this.activeEraser) {
                        this.canvas.setEraserSize(this.pensizes[this.activeEraser]);
                        this.drawNumPen(this.offgraphics, old, 6);
                        this.drawNumPen(this.offgraphics, this.activePen, 6);
                        this.repaint();
                        break;
                    }
                    break;
                }
            }
        }
    }
    
    private synchronized void createPicture() {
        if (this.offscreen == null || this.width != this.offscreensize.width || this.height != this.offscreensize.height) {
            this.offscreen = this.createImage(this.width, this.height);
            this.offscreensize = new Dimension(this.width, this.height);
            this.offgraphics = this.offscreen.getGraphics();
        }
        this.offgraphics.setColor(this.back);
        this.offgraphics.fillRect(0, 0, this.width, this.height);
        for (int i = 0; i < 7; ++i) {
            this.drawButton(this.offgraphics, i);
        }
        this.drawButtonCanvas(this.offgraphics);
    }
    
    private void drawButton(final Graphics g, final int num) {
        final int x = (num % 2 == 0) ? 0 : (this.whBox + 2);
        final int y = (num < 2) ? 0 : ((num < 4) ? (this.whBox + 2) : ((num < 6) ? (2 * this.whBox + 4) : (3 * this.whBox + 6)));
        Color c = (this.active == num) ? this.backLight : this.back;
        g.setColor(c);
        g.fillRect(x, y, this.whBox, this.whBox);
        c = ((this.active == num) ? Color.black : Color.white);
        g.setColor(c);
        g.drawLine(x, y, x, y + this.whBox);
        g.drawLine(x, y, x + this.whBox, y);
        c = ((this.active == num) ? Color.white : Color.black);
        g.setColor(c);
        g.drawLine(x + this.whBox, y, x + this.whBox, y + this.whBox);
        g.drawLine(x, y + this.whBox, x + this.whBox, y + this.whBox);
        this.drawIcon(g, num);
    }
    
    private void drawButtonCanvas(final Graphics g) {
        g.setColor(Color.black);
        g.drawRect(0, 4 * this.whBox + 17, this.wDoubleBox, this.hDoubleBox + 1);
        switch (this.active) {
            case 0: {
                for (int i = 0; i < 5; ++i) {
                    this.drawNumLine(g, i);
                }
                break;
            }
            case 2: {
                for (int i = 0; i < 4; ++i) {
                    this.drawNumPen(g, i, 2);
                }
                break;
            }
            case 3: {
                for (int i = 0; i < 4; ++i) {
                    this.drawNumSpray(g, i);
                }
                break;
            }
            case 4: {
                for (int i = 0; i < 3; ++i) {
                    this.drawNumRect(g, i, 4);
                }
                break;
            }
            case 5: {
                for (int i = 0; i < 3; ++i) {
                    this.drawNumRect(g, i, 5);
                }
                break;
            }
            case 6: {
                for (int i = 0; i < 4; ++i) {
                    this.drawNumPen(g, i, 6);
                }
                break;
            }
        }
    }
    
    private void drawIcon(final Graphics g, final int num) {
        g.setColor(Color.black);
        if (num == 0) {
            final int shift = this.whBox / 5;
            g.drawLine(shift, shift, this.whBox - shift, this.whBox - shift);
        }
        else if (num == 1) {
            g.setFont(this.font);
            final int x = this.whBox + (this.whBox - this.fm.stringWidth("A")) / 2 + 2;
            g.drawString("A", x, 19);
        }
        else if (num == 2) {
            final int x = (this.whBox - this.imageDim) / 2 + 2;
            if (this.active == num) {
                g.drawImage(this.pen_on, x, this.whBox + x, null);
            }
            else {
                g.drawImage(this.pen, x, this.whBox + x, null);
            }
        }
        else if (num == 3) {
            final int x = (this.whBox - this.imageDim) / 2 + 2;
            if (this.active == num) {
                g.drawImage(this.spray_on, this.whBox + x, this.whBox + x, null);
            }
            else {
                g.drawImage(this.spray, this.whBox + x, this.whBox + x, null);
            }
        }
        else if (num == 4) {
            final int shift = this.whBox / 5;
            g.drawRect(shift, 2 * this.whBox + shift + 4, this.whBox - 2 * shift, this.whBox - 2 * shift);
        }
        else if (num == 5) {
            final int shift = this.whBox / 5;
            g.drawOval(this.whBox + shift + 1, 2 * this.whBox + shift + 4, this.whBox - 2 * shift, this.whBox - 2 * shift);
        }
        else if (num == 6) {
            final int x = (this.whBox - this.imageDim) / 2;
            if (this.active == num) {
                g.drawImage(this.eraser_on, x, 3 * this.whBox + 6 + x, null);
            }
            else {
                g.drawImage(this.eraser, x, 3 * this.whBox + 6 + x, null);
            }
        }
    }
    
    private void drawNumLine(final Graphics g, final int num) {
        final int hei = this.hDoubleBox / 5;
        final int x = 1;
        final int y = 4 * this.whBox + 18 + num * hei;
        Color c = (this.activeLine == num) ? this.activeLineColor : this.back;
        g.setColor(c);
        g.fillRect(x, y, this.wDoubleBox - 1, hei);
        c = ((this.activeLine == num) ? Color.white : Color.black);
        g.setColor(c);
        g.fillRect(x + 5, y + (hei - num - 1) / 2, this.wDoubleBox - 10, num + 1);
    }
    
    private void drawNumPen(final Graphics g, final int num, final int type) {
        final int wei = this.wDoubleBox / 2;
        final int hei = this.hDoubleBox / 2;
        final int x = 1 + num % 2 * wei;
        final int y = (num < 2) ? (4 * this.whBox + 18) : (4 * this.whBox + 18 + hei);
        final int act = (type == 2) ? this.activePen : this.activeEraser;
        Color c = (act == num) ? this.activeLineColor : this.back;
        g.setColor(c);
        g.fillRect(x, y, wei - 1, hei);
        c = ((act == num) ? Color.white : Color.black);
        g.setColor(c);
        final int r = this.pensizes[4] + num * 2;
        if (type == 2) {
            g.fillOval(x + (wei - 2 * r) / 2, y + (hei - 2 * r) / 2, 2 * r, 2 * r);
        }
        else {
            g.fillRect(x + (wei - 2 * r) / 2, y + (hei - 2 * r) / 2, 2 * r, 2 * r);
        }
    }
    
    private void drawNumRect(final Graphics g, final int num, final int type) {
        final int hei = this.hDoubleBox / 3;
        final int x = 1;
        final int y = 4 * this.whBox + 18 + num * hei;
        final int act = (type == 4) ? this.activeRect : this.activeCirc;
        Color c = (act == num) ? this.activeLineColor : this.back;
        g.setColor(c);
        g.fillRect(x, y, this.wDoubleBox - 1, hei);
        c = ((act == num) ? Color.white : Color.black);
        g.setColor(this.backDark);
        if (num > 0) {
            g.fillRect(x + 10, y + 5, this.wDoubleBox - 21, hei - 10);
        }
        g.setColor(c);
        if (num < 2) {
            g.drawRect(x + 10, y + 5, this.wDoubleBox - 21, hei - 10);
        }
    }
    
    private void drawNumSpray(final Graphics g, final int num) {
        final int wei = this.wDoubleBox / 2;
        final int hei = this.hDoubleBox / 2;
        int x = 1 + num % 2 * wei;
        int y = (num < 2) ? (4 * this.whBox + 18) : (4 * this.whBox + 18 + hei);
        Color c = (this.activeSpray == num) ? this.activeLineColor : this.back;
        g.setColor(c);
        g.fillRect(x, y, wei - 1, hei);
        c = ((this.activeSpray == num) ? Color.white : Color.black);
        g.setColor(c);
        final int maxr = this.spraysizes[4] + num * 3;
        x += wei / 2;
        y += hei / 2;
        for (int i = 0; i < 4 * maxr; ++i) {
            final double r = Math.random() * maxr;
            final double al = Math.random() * 2.0 * 3.141592653589793;
            g.fillOval(x + (int)(r * Math.cos(al)), y + (int)(r * Math.sin(al)), 1, 1);
        }
    }
    
    private int getActiveColumn(final int x, final int denom) {
        int curX = 1;
        final int wei = this.wDoubleBox / denom;
        for (int i = 0; i < denom; ++i) {
            if (x > curX && x < curX + wei) {
                return i;
            }
            curX += wei;
        }
        return 0;
    }
    
    private int getActiveLine(final int y, final int denom) {
        int curY = 4 * this.whBox + 18;
        final int hei = this.hDoubleBox / denom;
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
        if (this.needCreate) {
            this.createPicture();
        }
        g.drawImage(this.offscreen, 0, 0, null);
    }
    
    public void preparation(final Graphics g) {
        this.width = this.getSize().width;
        this.height = this.getSize().height;
        g.setFont(this.font);
        this.fm = g.getFontMetrics();
        this.createPicture();
        this.first = false;
    }
    
    private void setActive(final int i) {
        this.canvas.setActiveButton(i);
        this.active = i;
        this.fontChoice.setEnabled(i == 1);
        this.needCreate = true;
        this.repaint();
    }
    
    public void setImages(final Image spray, final Image spray_on, final Image pen, final Image pen_on, final Image eraser, final Image eraser_on) {
        this.spray = spray;
        this.spray_on = spray_on;
        this.pen = pen;
        this.pen_on = pen_on;
        this.eraser = eraser;
        this.eraser_on = eraser_on;
        this.imageDim = spray.getWidth(null);
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
}
