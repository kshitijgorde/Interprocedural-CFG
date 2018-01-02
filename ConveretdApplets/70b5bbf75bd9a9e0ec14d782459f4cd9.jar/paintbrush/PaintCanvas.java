// 
// Decompiled by Procyon v0.5.30
// 

package paintbrush;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.applet.Applet;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Canvas;

public class PaintCanvas extends Canvas implements MouseListener, MouseMotionListener, KeyListener
{
    SprayThread sprayThread;
    int width;
    int height;
    boolean ViewOnly;
    Applet applet;
    Font font;
    Color frontColor;
    Color backColor;
    int thickness;
    int spraySize;
    int penSize;
    int eraserSize;
    int rectType;
    int circType;
    int activeButton;
    int rOfSpray;
    Image initImage;
    Image offscreen;
    Image offscreen2;
    Image bufer1;
    Image bufer2;
    Dimension offscreensize;
    Graphics offgraphics;
    Graphics offgraphics2;
    Graphics bufgraphics1;
    Graphics bufgraphics2;
    FontMetrics fm;
    int lastX;
    int lastY;
    int newX;
    int newY;
    int oldX;
    int oldY;
    boolean drag;
    boolean input;
    boolean spray;
    boolean notType;
    int[] xs;
    int[] ys;
    String inputString;
    String addn;
    int inputStringBorder;
    int inputCursorNudgeX;
    int inputCursorNudgeY;
    int inputCursorPos;
    
    public PaintCanvas(final Applet applet, final int width, final int height, final boolean ViewOnly) {
        this.font = new Font("Courier", 0, 10);
        this.frontColor = Color.black;
        this.backColor = Color.white;
        this.thickness = 1;
        this.spraySize = 3;
        this.penSize = 1;
        this.eraserSize = 1;
        this.rectType = 0;
        this.circType = 0;
        this.activeButton = 0;
        this.drag = false;
        this.input = false;
        this.spray = false;
        this.notType = false;
        this.xs = new int[4];
        this.ys = new int[4];
        this.inputString = "";
        this.addn = "|";
        this.inputStringBorder = 3;
        this.inputCursorNudgeX = -3;
        this.inputCursorNudgeY = -2;
        this.applet = applet;
        this.width = width;
        this.height = height;
        this.sprayThread = new SprayThread(this);
        if (!ViewOnly) {
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
            this.addKeyListener(this);
        }
    }
    
    public void clear() {
        this.bufgraphics2.drawImage(this.bufer1, 0, 0, null);
        this.bufgraphics1.drawImage(this.offscreen, 0, 0, null);
        this.offgraphics.setColor(this.backColor);
        this.offgraphics.fillRect(0, 0, this.width, this.height);
        this.repaint();
    }
    
    private boolean clickInsideText(final int x, final int y) {
        if (this.activeButton != 1 || this.fm == null) {
            return false;
        }
        final int minx = this.lastX - this.inputStringBorder;
        final int miny = this.lastY - this.fm.getMaxAscent() - this.inputStringBorder;
        final int maxx = minx + this.fm.stringWidth(String.valueOf(this.inputString) + this.addn) + this.inputStringBorder;
        final int maxy = this.lastY + this.fm.getMaxDescent() + this.inputStringBorder;
        return x >= minx && x <= maxx && y >= miny && y <= maxy;
    }
    
    private synchronized void createPicture() {
        if (this.offscreen == null || this.width != this.offscreensize.width || this.height != this.offscreensize.height) {
            this.offscreen = this.createImage(this.width, this.height);
            this.offscreensize = new Dimension(this.width, this.height);
            this.offgraphics = this.offscreen.getGraphics();
            this.offscreen2 = this.createImage(this.width, this.height);
            this.offgraphics2 = this.offscreen2.getGraphics();
            this.bufer1 = this.createImage(this.width, this.height);
            this.bufgraphics1 = this.bufer1.getGraphics();
            this.bufer2 = this.createImage(this.width, this.height);
            this.bufgraphics2 = this.bufer2.getGraphics();
        }
        this.offgraphics.setColor(this.backColor);
        this.offgraphics.fillRect(0, 0, this.width, this.height);
        this.bufgraphics1.setColor(this.backColor);
        this.bufgraphics1.fillRect(0, 0, this.width, this.height);
        this.bufgraphics2.setColor(this.backColor);
        this.bufgraphics2.fillRect(0, 0, this.width, this.height);
        this.offgraphics.setColor(this.frontColor);
    }
    
    private void drawCirc(final Graphics g, final int x1, final int y1, final int x2, final int y2) {
        final int nx1 = Math.min(x1, x2);
        final int ny1 = Math.min(y1, y2);
        final int nx2 = Math.max(x1, x2);
        final int ny2 = Math.max(y1, y2);
        if (this.circType > 0) {
            g.setColor(this.backColor);
            g.fillOval(nx1, ny1, nx2 - nx1, ny2 - ny1);
        }
        if (this.circType < 2) {
            g.setColor(this.frontColor);
            g.drawOval(nx1, ny1, nx2 - nx1, ny2 - ny1);
        }
    }
    
    public void drawLine(final Graphics g, final int thick, final int x1, final int y1, final int x2, final int y2, final Color c, final boolean circ) {
        g.setColor(c);
        if (thick == 1) {
            g.drawLine(x1, y1, x2, y2);
        }
        else {
            this.fillMask(g, x1, y1, 2 * thick, 2 * thick, circ);
            this.fillMask(g, x2, y2, 2 * thick, 2 * thick, circ);
            final int dx = Math.abs(x1 - x2);
            final int dy = Math.abs(y1 - y2);
            if (x1 == x2) {
                g.fillRect(x1, Math.min(y1, y2) + thick, 2 * thick, Math.abs(y2 - y1));
            }
            else if (y1 == y2) {
                g.fillRect(Math.min(x1, x2) + thick, y1, Math.abs(x2 - x1), 2 * thick);
            }
            else if (dx > dy) {
                if (x1 < x2) {
                    for (int i = x1; i < x2; ++i) {
                        final int yi = (i - x1) * (y2 - y1) / (x2 - x1);
                        this.fillMask(g, i, y1 + yi, 2 * thick, 2 * thick, circ);
                    }
                }
                else {
                    for (int i = x2; i < x1; ++i) {
                        final int yi = (i - x2) * (y2 - y1) / (x2 - x1);
                        this.fillMask(g, i, y2 + yi, 2 * thick, 2 * thick, circ);
                    }
                }
            }
            else if (y1 < y2) {
                for (int i = y1; i < y2; ++i) {
                    final int xi = (i - y1) * (x2 - x1) / (y2 - y1);
                    this.fillMask(g, x1 + xi, i, 2 * thick, 2 * thick, circ);
                }
            }
            else {
                for (int i = y2; i < y1; ++i) {
                    final int xi = (i - y2) * (x2 - x1) / (y2 - y1);
                    this.fillMask(g, x2 + xi, i, 2 * thick, 2 * thick, circ);
                }
            }
        }
    }
    
    private void drawPenEraser(final Graphics g, final int type) {
        if (type == 2) {
            this.drawLine(g, this.penSize, this.oldX, this.oldY, this.newX, this.newY, this.frontColor, true);
        }
        else {
            this.drawLine(g, this.eraserSize, this.oldX, this.oldY, this.newX, this.newY, this.backColor, false);
        }
        this.oldX = this.newX;
        this.oldY = this.newY;
    }
    
    private void drawRect(final Graphics g, final int x1, final int y1, final int x2, final int y2) {
        final int nx1 = Math.min(x1, x2);
        final int ny1 = Math.min(y1, y2);
        final int nx2 = Math.max(x1, x2);
        final int ny2 = Math.max(y1, y2);
        if (this.rectType > 0) {
            g.setColor(this.backColor);
            g.fillRect(nx1, ny1, nx2 - nx1, ny2 - ny1);
        }
        if (this.rectType < 2) {
            g.setColor(this.frontColor);
            g.drawRect(nx1, ny1, nx2 - nx1, ny2 - ny1);
        }
    }
    
    public void drawSpray() {
        this.offgraphics.setColor(this.frontColor);
        for (int i = 0; i < this.spraySize; ++i) {
            final double r = Math.random() * this.spraySize;
            final double al = Math.random() * 2.0 * 3.141592653589793;
            final int x = this.newX + (int)(r * Math.cos(al));
            final int y = this.newY + (int)(r * Math.sin(al));
            this.offgraphics.fillOval(x, y, 1, 1);
        }
        this.repaint();
    }
    
    private void drawTextInBuf() {
        this.offgraphics.setColor(this.frontColor);
        this.offgraphics.setFont(this.font);
        this.offgraphics.drawString(this.inputString, this.lastX, this.lastY);
        this.repaint();
        this.inputString = "";
        this.inputCursorPos = 0;
    }
    
    private void fillMask(final Graphics g, final int x, final int y, final int w, final int h, final boolean c) {
        if (c) {
            g.fillOval(x, y, w, h);
        }
        else {
            g.fillRect(x, y, w, h);
        }
    }
    
    public Color getBackColor() {
        return this.backColor;
    }
    
    public Image getCurrentImage() {
        this.drawTextInBuf();
        return this.offscreen;
    }
    
    public void initImage(final Image image) {
        this.initImage = image;
    }
    
    public void initImage2(final Image image) {
        this.initImage = image;
        this.repaint();
    }
    
    public void keyPressed(final KeyEvent e) {
        if (this.activeButton == 1 && this.input) {
            this.notType = false;
            if (e.getKeyCode() == 10) {
                this.drawTextInBuf();
                this.lastY += this.fm.getHeight();
                this.repaint();
                this.notType = true;
            }
            else if (e.getKeyCode() == 8) {
                if (this.inputString.length() > 0 && this.inputCursorPos != 0) {
                    final int l = this.inputString.length();
                    String newString = "";
                    if (this.inputCursorPos > 0) {
                        newString = String.valueOf(newString) + this.inputString.substring(0, this.inputCursorPos - 1);
                    }
                    if (this.inputCursorPos < l) {
                        newString = String.valueOf(newString) + this.inputString.substring(this.inputCursorPos, l);
                    }
                    this.inputString = newString;
                    --this.inputCursorPos;
                    this.repaint();
                }
                this.notType = true;
            }
            else if (e.getKeyCode() == 127) {
                if (this.inputString.length() > 0 && this.inputCursorPos != this.inputString.length()) {
                    final int l = this.inputString.length();
                    String newString = "";
                    newString = String.valueOf(newString) + this.inputString.substring(0, this.inputCursorPos);
                    if (this.inputCursorPos < l) {
                        newString = String.valueOf(newString) + this.inputString.substring(this.inputCursorPos + 1, l);
                    }
                    this.inputString = newString;
                    this.repaint();
                }
                this.notType = true;
            }
            else if (e.getKeyCode() == 37) {
                if (this.inputCursorPos > 0) {
                    --this.inputCursorPos;
                    this.repaint();
                }
            }
            else if (e.getKeyCode() == 39) {
                if (this.inputCursorPos < this.inputString.length()) {
                    ++this.inputCursorPos;
                    this.repaint();
                }
            }
            else if (e.getKeyCode() == 9 || e.getKeyCode() == 27 || e.getKeyChar() == '\0') {
                this.notType = true;
            }
        }
    }
    
    public void keyReleased(final KeyEvent e) {
    }
    
    public void keyTyped(final KeyEvent e) {
        if (this.activeButton == 1 && this.input && !this.notType) {
            String newString = "";
            newString = String.valueOf(newString) + this.inputString.substring(0, this.inputCursorPos);
            newString = String.valueOf(newString) + e.getKeyChar();
            newString = String.valueOf(newString) + this.inputString.substring(this.inputCursorPos);
            this.inputString = newString;
            ++this.inputCursorPos;
            this.repaint();
        }
    }
    
    public void loadImage(final Image image) {
        this.bufgraphics2.drawImage(this.bufer1, 0, 0, null);
        this.bufgraphics1.drawImage(this.offscreen, 0, 0, null);
        this.offgraphics.drawImage(image, 0, 0, null);
        this.repaint();
    }
    
    public void mouseClicked(final MouseEvent e) {
    }
    
    public void mouseDragged(final MouseEvent e) {
        this.newX = e.getX();
        this.newY = e.getY();
        switch (this.activeButton) {
            case 0:
            case 4:
            case 5: {
                this.drag = true;
                this.repaint();
                break;
            }
            case 2: {
                this.drawPenEraser(this.offgraphics, 2);
                this.repaint();
                break;
            }
            case 6: {
                this.drawPenEraser(this.offgraphics, 6);
                this.repaint();
                break;
            }
        }
    }
    
    public void mouseEntered(final MouseEvent e) {
        if (this.activeButton == 1) {
            this.setCursor(Cursor.getPredefinedCursor(2));
        }
        else if (this.getCursor().getType() != Cursor.getDefaultCursor().getType()) {
            this.setCursor(Cursor.getDefaultCursor());
        }
    }
    
    public void mouseExited(final MouseEvent e) {
    }
    
    public void mouseMoved(final MouseEvent e) {
    }
    
    public void mousePressed(final MouseEvent e) {
        if (this.activeButton == 1 && this.clickInsideText(e.getX(), e.getY())) {
            final int textPos = e.getX() - this.lastX;
            for (int i = 0; i < this.inputString.length(); ++i) {
                final int cstart = this.fm.stringWidth(this.inputString.substring(0, i));
                final int cend = this.fm.stringWidth(this.inputString.substring(0, i + 1));
                if (cstart < textPos && cend >= textPos) {
                    final int midpoint = (cend + cstart) / 2;
                    this.inputCursorPos = ((textPos < midpoint) ? i : (i + 1));
                    this.repaint();
                    return;
                }
            }
            this.inputCursorPos = this.inputString.length();
            this.repaint();
            return;
        }
        if (this.activeButton == 1 && this.input) {
            this.drawTextInBuf();
        }
        this.bufgraphics2.drawImage(this.bufer1, 0, 0, null);
        this.bufgraphics1.drawImage(this.offscreen, 0, 0, null);
        this.lastX = e.getX();
        this.lastY = e.getY();
        this.oldX = this.lastX;
        this.oldY = this.lastY;
        this.newX = this.lastX;
        this.newY = this.lastY;
        switch (this.activeButton) {
            case 1: {
                this.input = true;
                this.requestFocus();
                this.repaint();
                break;
            }
            case 3: {
                this.sprayThread.setSuspended(false);
                break;
            }
            case 2: {
                this.drawPenEraser(this.offgraphics, 2);
                this.repaint();
                break;
            }
            case 6: {
                this.drawPenEraser(this.offgraphics, 6);
                this.repaint();
                break;
            }
        }
    }
    
    public void mouseReleased(final MouseEvent e) {
        this.newX = e.getX();
        this.newY = e.getY();
        if (this.drag) {
            if (this.activeButton == 0) {
                this.drawLine(this.offgraphics, this.thickness, this.lastX, this.lastY, this.newX, this.newY, this.frontColor, true);
            }
            else if (this.activeButton == 4) {
                this.drawRect(this.offgraphics, this.lastX, this.lastY, this.newX, this.newY);
            }
            else if (this.activeButton == 5) {
                this.drawCirc(this.offgraphics, this.lastX, this.lastY, this.newX, this.newY);
            }
            this.drag = false;
            this.repaint();
        }
        if (this.activeButton == 3) {
            this.sprayThread.setSuspended(true);
        }
    }
    
    public void paint(final Graphics g) {
        this.preparation(g);
        this.offgraphics2.drawImage(this.offscreen, 0, 0, null);
        if (this.drag) {
            switch (this.activeButton) {
                case 0: {
                    this.drawLine(this.offgraphics2, this.thickness, this.lastX, this.lastY, this.newX, this.newY, this.frontColor, true);
                    break;
                }
                case 4: {
                    this.drawRect(this.offgraphics2, this.lastX, this.lastY, this.newX, this.newY);
                    break;
                }
                case 5: {
                    this.drawCirc(this.offgraphics2, this.lastX, this.lastY, this.newX, this.newY);
                    break;
                }
            }
        }
        if (this.input) {
            this.offgraphics2.setColor(this.frontColor);
            this.offgraphics2.setFont(this.font);
            this.fm = this.offgraphics2.getFontMetrics();
            this.offgraphics2.drawString(this.inputString, this.lastX, this.lastY);
            this.offgraphics2.setColor(Color.black);
            this.offgraphics2.drawString(this.addn, this.lastX + this.fm.stringWidth(this.inputString.substring(0, this.inputCursorPos)) + this.inputCursorNudgeX, this.lastY + this.inputCursorNudgeY);
        }
        g.drawImage(this.offscreen2, 0, 0, null);
    }
    
    private void preparation(final Graphics g) {
        if (this.initImage != null) {
            this.createPicture();
            this.loadImage(this.initImage);
            this.initImage = null;
        }
    }
    
    public void setActiveButton(final int active) {
        this.activeButton = active;
        if (this.activeButton != 1 && this.input) {
            this.drawTextInBuf();
            this.input = false;
        }
    }
    
    public void setBackColor(final Color c) {
        this.backColor = c;
        this.requestFocus();
    }
    
    public void setCircType(final int type) {
        this.circType = type;
    }
    
    public void setEraserSize(final int eSize) {
        this.eraserSize = eSize;
    }
    
    public void setFont(final Font font) {
        this.font = font;
        this.requestFocus();
        this.repaint();
    }
    
    public void setFrontColor(final Color c) {
        this.frontColor = c;
        this.requestFocus();
    }
    
    public void setPenSize(final int penSize) {
        this.penSize = penSize;
    }
    
    public void setRectType(final int type) {
        this.rectType = type;
    }
    
    public void setSpraySize(final int spraySize) {
        this.spraySize = spraySize;
    }
    
    public void setThickness(final int t) {
        this.thickness = t;
    }
    
    public void undo() {
        if (this.activeButton == 1 && this.input) {
            this.drawTextInBuf();
            this.requestFocus();
        }
        this.offgraphics.drawImage(this.bufer1, 0, 0, null);
        this.bufgraphics1.drawImage(this.bufer2, 0, 0, null);
        this.repaint();
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
}
