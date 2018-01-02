// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.awt.FontMetrics;
import java.awt.Shape;
import java.awt.Polygon;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.util.Stack;
import java.awt.Font;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics;

class SwingGraphics extends Graphics implements GraphicsWrapper
{
    Graphics graphics;
    Graphics originalGraphics;
    Rectangle clipRect;
    Color currentColor;
    Font currentFont;
    Color currentXORMode;
    int translateX;
    int translateY;
    SwingGraphics previous;
    private static final boolean TRACE = false;
    private static final boolean VERBOSE = false;
    private static final boolean DEBUG = false;
    private static Stack pool;
    
    static {
        SwingGraphics.pool = new Stack();
        if (SwingUtilities.is1dot2) {
            System.err.println("warning: running 1.1 version of SwingGraphics");
        }
    }
    
    SwingGraphics(final Graphics graphics) {
        this.translateX = 0;
        this.translateY = 0;
        if (graphics == null) {
            Thread.dumpStack();
        }
        this.init(graphics);
    }
    
    private void _changeClip(final int x, final int y, final int width, final int height, final boolean b) {
        if (b) {
            this.clipRect.x = x;
            this.clipRect.y = y;
            this.clipRect.width = width;
            this.clipRect.height = height;
        }
        else {
            SwingUtilities.computeIntersection(x, y, width, height, this.clipRect);
        }
    }
    
    public void clearRect(final int n, final int n2, final int n3, final int n4) {
        this.graphics.clearRect(n, n2, n3, n4);
    }
    
    public void clipRect(final int n, final int n2, final int n3, final int n4) {
        this.graphics.clipRect(n, n2, n3, n4);
        this._changeClip(n, n2, n3, n4, false);
    }
    
    public void copyArea(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.graphics.copyArea(n, n2, n3, n4, n5, n6);
    }
    
    public Graphics create() {
        return createSwingGraphics(this);
    }
    
    public Graphics create(final int n, final int n2, final int n3, final int n4) {
        return createSwingGraphics(this, n, n2, n3, n4);
    }
    
    public static Graphics createSwingGraphics(final Graphics graphics) {
        if (graphics == null) {
            Thread.dumpStack();
            return null;
        }
        SwingGraphics recycledSwingGraphics = getRecycledSwingGraphics();
        if (recycledSwingGraphics == null) {
            recycledSwingGraphics = new SwingGraphics(graphics);
        }
        else {
            recycledSwingGraphics.init(graphics);
        }
        return recycledSwingGraphics;
    }
    
    static Graphics createSwingGraphics(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        final SwingGraphics swingGraphics = (SwingGraphics)createSwingGraphics(graphics);
        if (n != 0 || n2 != 0) {
            swingGraphics.translate(n, n2);
        }
        if (swingGraphics.clipRect.x != 0 || swingGraphics.clipRect.y != 0 || swingGraphics.clipRect.width != n3 || swingGraphics.clipRect.height != n4) {
            final int n5 = (swingGraphics.clipRect.x > 0) ? swingGraphics.clipRect.x : 0;
            final int n6 = (swingGraphics.clipRect.y > 0) ? swingGraphics.clipRect.y : 0;
            swingGraphics.setClip(n5, n6, Math.min(n3, swingGraphics.clipRect.x + swingGraphics.clipRect.width) - n5, Math.min(n4, swingGraphics.clipRect.y + swingGraphics.clipRect.height) - n6);
        }
        return swingGraphics;
    }
    
    public void dispose() {
        if (this.graphics != null) {
            if (this.previous != null) {
                this.resetGraphics();
            }
            else {
                this.graphics.dispose();
                final boolean b = false;
                this.translateY = (b ? 1 : 0);
                this.translateX = (b ? 1 : 0);
            }
        }
        else {
            final boolean b2 = false;
            this.translateY = (b2 ? 1 : 0);
            this.translateX = (b2 ? 1 : 0);
        }
        this.graphics = null;
        recycleSwingGraphics(this);
    }
    
    public void draw3DRect(final int n, final int n2, final int n3, final int n4, final boolean b) {
        this.graphics.draw3DRect(n, n2, n3, n4, b);
    }
    
    public void drawArc(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.graphics.drawArc(n, n2, n3, n4, n5, n6);
    }
    
    public void drawBytes(final byte[] array, final int n, final int n2, final int n3, final int n4) {
        this.graphics.drawBytes(array, n, n2, n3, n4);
    }
    
    public void drawChars(final char[] array, final int n, final int n2, final int n3, final int n4) {
        this.graphics.drawChars(array, n, n2, n3, n4);
    }
    
    public boolean drawImage(final Image image, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final Color color, final ImageObserver imageObserver) {
        return this.graphics.drawImage(image, n, n2, n3, n4, n5, n6, n7, n8, color, imageObserver);
    }
    
    public boolean drawImage(final Image image, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final ImageObserver imageObserver) {
        return this.graphics.drawImage(image, n, n2, n3, n4, n5, n6, n7, n8, imageObserver);
    }
    
    public boolean drawImage(final Image image, final int n, final int n2, final int n3, final int n4, final Color color, final ImageObserver imageObserver) {
        return this.graphics.drawImage(image, n, n2, n3, n4, color, imageObserver);
    }
    
    public boolean drawImage(final Image image, final int n, final int n2, final int n3, final int n4, final ImageObserver imageObserver) {
        return this.graphics.drawImage(image, n, n2, n3, n4, imageObserver);
    }
    
    public boolean drawImage(final Image image, final int n, final int n2, final Color color, final ImageObserver imageObserver) {
        return this.graphics.drawImage(image, n, n2, color, imageObserver);
    }
    
    public boolean drawImage(final Image image, final int n, final int n2, final ImageObserver imageObserver) {
        return this.graphics.drawImage(image, n, n2, imageObserver);
    }
    
    public void drawLine(final int n, final int n2, final int n3, final int n4) {
        this.graphics.drawLine(n, n2, n3, n4);
    }
    
    public void drawOval(final int n, final int n2, final int n3, final int n4) {
        this.graphics.drawOval(n, n2, n3, n4);
    }
    
    public void drawPolygon(final Polygon polygon) {
        this.graphics.drawPolygon(polygon);
    }
    
    public void drawPolygon(final int[] array, final int[] array2, final int n) {
        this.graphics.drawPolygon(array, array2, n);
    }
    
    public void drawPolyline(final int[] array, final int[] array2, final int n) {
        this.graphics.drawPolyline(array, array2, n);
    }
    
    public void drawRect(final int n, final int n2, final int n3, final int n4) {
        this.graphics.drawRect(n, n2, n3, n4);
    }
    
    public void drawRoundRect(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.graphics.drawRoundRect(n, n2, n3, n4, n5, n6);
    }
    
    public void drawString(final String s, final int n, final int n2) {
        this.graphics.drawString(s, n, n2);
    }
    
    public void fill3DRect(final int n, final int n2, final int n3, final int n4, final boolean b) {
        this.graphics.fill3DRect(n, n2, n3, n4, b);
    }
    
    public void fillArc(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.graphics.fillArc(n, n2, n3, n4, n5, n6);
    }
    
    public void fillOval(final int n, final int n2, final int n3, final int n4) {
        this.graphics.fillOval(n, n2, n3, n4);
    }
    
    public void fillPolygon(final Polygon polygon) {
        this.graphics.fillPolygon(polygon);
    }
    
    public void fillPolygon(final int[] array, final int[] array2, final int n) {
        this.graphics.fillPolygon(array, array2, n);
    }
    
    public void fillRect(final int n, final int n2, final int n3, final int n4) {
        this.graphics.fillRect(n, n2, n3, n4);
    }
    
    public void fillRoundRect(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.graphics.fillRoundRect(n, n2, n3, n4, n5, n6);
    }
    
    public void finalize() {
        this.graphics.finalize();
    }
    
    public Shape getClip() {
        return this.graphics.getClip();
    }
    
    public Rectangle getClipBounds() {
        return new Rectangle(this.clipRect);
    }
    
    public int getClipHeight() {
        return this.clipRect.height;
    }
    
    public Rectangle getClipRect() {
        return this.graphics.getClipRect();
    }
    
    public int getClipWidth() {
        return this.clipRect.width;
    }
    
    public int getClipX() {
        return this.clipRect.x;
    }
    
    public int getClipY() {
        return this.clipRect.y;
    }
    
    public Color getColor() {
        return this.currentColor;
    }
    
    public Font getFont() {
        return this.currentFont;
    }
    
    public FontMetrics getFontMetrics() {
        return this.graphics.getFontMetrics();
    }
    
    public FontMetrics getFontMetrics(final Font font) {
        return this.graphics.getFontMetrics(font);
    }
    
    private static SwingGraphics getRecycledSwingGraphics() {
        SwingGraphics swingGraphics = null;
        synchronized (SwingGraphics.pool) {
            if (SwingGraphics.pool.size() > 0) {
                swingGraphics = SwingGraphics.pool.pop();
            }
        }
        // monitorexit(SwingGraphics.pool)
        return swingGraphics;
    }
    
    void init(final Graphics originalGraphics) {
        if (originalGraphics instanceof SwingGraphics) {
            final SwingGraphics previous = (SwingGraphics)originalGraphics;
            this.originalGraphics = previous.originalGraphics;
            this.graphics = previous.graphics;
            this.previous = previous;
            if (this.clipRect == null) {
                this.clipRect = new Rectangle(previous.clipRect.x, previous.clipRect.y, previous.clipRect.width, previous.clipRect.height);
            }
            else {
                this.clipRect.x = previous.clipRect.x;
                this.clipRect.y = previous.clipRect.y;
                this.clipRect.width = previous.clipRect.width;
                this.clipRect.height = previous.clipRect.height;
            }
            this.currentColor = previous.currentColor;
            this.currentFont = previous.currentFont;
            this.currentXORMode = previous.currentXORMode;
        }
        else {
            this.originalGraphics = originalGraphics;
            this.graphics = originalGraphics.create();
            this.previous = null;
            final Rectangle clipBounds = originalGraphics.getClipBounds();
            if (clipBounds == null) {
                if (this.clipRect == null) {
                    this.clipRect = new Rectangle(0, 0, Integer.MAX_VALUE, Integer.MAX_VALUE);
                }
                else {
                    final Rectangle clipRect = this.clipRect;
                    final Rectangle clipRect2 = this.clipRect;
                    final boolean b = false;
                    clipRect2.y = (b ? 1 : 0);
                    clipRect.x = (b ? 1 : 0);
                    final Rectangle clipRect3 = this.clipRect;
                    final Rectangle clipRect4 = this.clipRect;
                    final int n = Integer.MAX_VALUE;
                    clipRect4.height = n;
                    clipRect3.width = n;
                }
            }
            else {
                this.clipRect = clipBounds;
            }
            this.currentColor = originalGraphics.getColor();
            this.currentFont = originalGraphics.getFont();
            this.currentXORMode = null;
        }
    }
    
    public boolean isClipIntersecting(final Rectangle rectangle) {
        return this.clipRect.x < rectangle.x + rectangle.width && this.clipRect.x + this.clipRect.width > rectangle.x && this.clipRect.y < rectangle.y + rectangle.height && this.clipRect.y + this.clipRect.height > rectangle.y && ((this.clipRect.width == 0 || this.clipRect.height == 0 || rectangle.width == 0 || rectangle.height == 0) ^ true);
    }
    
    private static void recycleSwingGraphics(final SwingGraphics swingGraphics) {
        synchronized (SwingGraphics.pool) {
            SwingGraphics.pool.push(swingGraphics);
        }
        // monitorexit(SwingGraphics.pool)
    }
    
    private void resetGraphics() {
        if (this.currentFont != this.previous.currentFont) {
            this.setFont(this.previous.currentFont);
        }
        if (this.currentColor != this.previous.currentColor) {
            this.setColor(this.previous.currentColor);
        }
        if (this.currentXORMode != this.previous.currentXORMode) {
            if (this.previous.currentXORMode == null) {
                this.setPaintMode();
            }
            else {
                this.setXORMode(this.previous.currentXORMode);
            }
        }
        if (this.translateX != 0 || this.translateY != 0) {
            this.translate(-this.translateX, -this.translateY);
        }
        if (this.clipRect.x != this.previous.clipRect.x || this.clipRect.y != this.previous.clipRect.y || this.clipRect.width != this.previous.clipRect.width || this.clipRect.height != this.previous.clipRect.height) {
            this.setClip(this.previous.clipRect.x, this.previous.clipRect.y, this.previous.clipRect.width, this.previous.clipRect.height);
        }
    }
    
    public void setClip(final int n, final int n2, final int n3, final int n4) {
        this.graphics.setClip(n, n2, n3, n4);
        this._changeClip(n, n2, n3, n4, true);
    }
    
    public void setClip(final Shape clip) {
        this.graphics.setClip(clip);
        if (clip instanceof Rectangle) {
            final Rectangle rectangle = (Rectangle)clip;
            this._changeClip(rectangle.x, rectangle.y, rectangle.width, rectangle.height, true);
        }
    }
    
    public void setColor(final Color color) {
        this.graphics.setColor(color);
        this.currentColor = color;
    }
    
    public void setFont(final Font font) {
        this.graphics.setFont(font);
        this.currentFont = font;
    }
    
    public void setPaintMode() {
        this.graphics.setPaintMode();
        this.currentXORMode = null;
    }
    
    public void setXORMode(final Color color) {
        this.graphics.setXORMode(color);
        this.currentXORMode = color;
    }
    
    public Graphics subGraphics() {
        return this.graphics;
    }
    
    public String toString() {
        final String string = this.currentFont.toString();
        return "SwingGraphics(0x" + Integer.toHexString(this.hashCode()) + ") [subGraphics " + this.originalGraphics.getClass().getName() + "\n   translate [x=" + this.translateX + ",y=" + this.translateY + "] clip [x=" + this.clipRect.x + ",y=" + this.clipRect.y + ",w=" + this.clipRect.width + ",h=" + this.clipRect.height + "]\n   color [r=" + this.currentColor.getRed() + ",g=" + this.currentColor.getGreen() + ",b=" + this.currentColor.getBlue() + "] font " + string.substring(string.indexOf(91)) + "]";
    }
    
    public void translate(final int n, final int n2) {
        this.graphics.translate(n, n2);
        this.translateX += n;
        this.translateY += n2;
        final Rectangle clipRect = this.clipRect;
        clipRect.x -= n;
        final Rectangle clipRect2 = this.clipRect;
        clipRect2.y -= n2;
    }
}
