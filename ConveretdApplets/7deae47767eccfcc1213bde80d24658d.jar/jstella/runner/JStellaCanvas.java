// 
// Decompiled by Procyon v0.5.30
// 

package jstella.runner;

import java.awt.event.ComponentEvent;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.event.ComponentListener;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.Rectangle;
import jstella.core.IfcCanvas;
import javax.swing.JPanel;

public class JStellaCanvas extends JPanel implements IfcCanvas
{
    private static final long serialVersionUID = 7155147791878716385L;
    private Rectangle myClippingRectangle;
    private AffineTransform myTransform;
    private BufferedImage myImage;
    private int myOriginalWidth;
    private int myOriginalHeight;
    private int myPreviousCanvasWidth;
    private int myPreviousCanvasHeight;
    private double myScaleX;
    private double myScaleY;
    private int myOffsetX;
    private int myOffsetY;
    private boolean myLetterBoxMode;
    private Toolkit myDefaultToolkit;
    private boolean myRefreshNeeded;
    
    public JStellaCanvas() {
        this.myClippingRectangle = new Rectangle();
        this.myTransform = new AffineTransform();
        this.myImage = null;
        this.myOriginalWidth = 10;
        this.myOriginalHeight = 10;
        this.myPreviousCanvasWidth = 10;
        this.myPreviousCanvasHeight = 10;
        this.myScaleX = 1.0;
        this.myScaleY = 1.0;
        this.myOffsetX = 0;
        this.myOffsetY = 0;
        this.myLetterBoxMode = false;
        this.myDefaultToolkit = null;
        this.myRefreshNeeded = false;
        this.setOpaque(true);
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addComponentListener(new CanvasComponentListener());
    }
    
    public void paint(final Graphics g) {
        final Graphics2D z2D = (Graphics2D)g;
        if (this.myRefreshNeeded) {
            z2D.setColor(Color.BLACK);
            z2D.fillRect(0, 0, this.getWidth(), this.getHeight());
            this.myRefreshNeeded = false;
        }
        if (this.myImage != null) {
            z2D.drawImage(this.myImage, this.myTransform, null);
        }
        this.syncPainting();
    }
    
    private void syncPainting() {
        if (this.myDefaultToolkit == null) {
            this.myDefaultToolkit = Toolkit.getDefaultToolkit();
        }
        if (this.myDefaultToolkit != null) {
            this.myDefaultToolkit.sync();
        }
    }
    
    private void setOriginalDimensions(final int aOriginalWidth, final int aOriginalHeight) {
        if (aOriginalWidth != this.myOriginalWidth || aOriginalHeight != this.myOriginalHeight || this.myPreviousCanvasWidth != this.getCanvasWidth() || this.myPreviousCanvasHeight != this.getCanvasHeight()) {
            this.myOriginalWidth = aOriginalWidth;
            this.myOriginalHeight = aOriginalHeight;
            this.myPreviousCanvasWidth = this.getCanvasWidth();
            this.myPreviousCanvasHeight = this.getCanvasHeight();
            this.updateScale();
        }
    }
    
    private void updateScale() {
        this.myTransform.setToIdentity();
        this.myScaleX = this.getCanvasWidth() / this.myOriginalWidth;
        this.myScaleY = this.getCanvasHeight() / this.myOriginalHeight;
        if (this.myLetterBoxMode) {
            final double zOriginalRatio = this.myOriginalWidth * 1.6 / this.myOriginalHeight;
            final double zCanvasRatio = this.getCanvasWidth() / this.getCanvasHeight();
            if (zCanvasRatio > zOriginalRatio) {
                this.myScaleX = this.myScaleY * 1.6;
                final double zBorderWidth = (this.getCanvasWidth() - this.myOriginalWidth * this.myScaleX) / 2.0;
                this.myOffsetX = (int)zBorderWidth;
                this.myOffsetY = 0;
                this.myTransform.translate(zBorderWidth, 0.0);
            }
            else {
                this.myScaleY = this.myScaleX / 1.6;
                final double zBorderWidth = (this.getCanvasHeight() - this.myOriginalHeight * this.myScaleY) / 2.0;
                this.myOffsetY = (int)zBorderWidth;
                this.myOffsetX = 0;
                this.myTransform.translate(0.0, zBorderWidth);
            }
        }
        this.myTransform.scale(this.myScaleX, this.myScaleY);
    }
    
    public void setLetterBoxMode(final boolean aEnable) {
        if (aEnable != this.myLetterBoxMode) {
            this.myLetterBoxMode = aEnable;
            this.updateScale();
            this.refreshCanvas();
        }
    }
    
    public void refreshCanvas() {
        this.updateScale();
        this.myRefreshNeeded = true;
        this.repaint();
    }
    
    public boolean getLetterBoxMode() {
        return this.myLetterBoxMode;
    }
    
    public int getCanvasWidth() {
        return this.getWidth();
    }
    
    public int getCanvasHeight() {
        return this.getHeight();
    }
    
    private void setClippingRectangle(final Rectangle aOriginalClip) {
        this.setClippingRectangle(aOriginalClip.x, aOriginalClip.y, aOriginalClip.width, aOriginalClip.height);
    }
    
    private void setClippingRectangle(final int aClipX, final int aClipY, final int aClipWidth, final int aClipHeight) {
        int zX = (int)(aClipX * this.myScaleX);
        int zY = (int)(aClipY * this.myScaleY);
        final int zW = (int)((aClipX + aClipWidth) * this.myScaleX) + 1 - zX;
        final int zH = (int)((aClipY + aClipHeight) * this.myScaleY) + 1 - zY;
        if (this.myLetterBoxMode) {
            zX += this.myOffsetX;
            zY += this.myOffsetY;
        }
        this.myClippingRectangle.setRect(zX, zY, zW, zH);
    }
    
    public void paintCanvas(final BufferedImage aImage, final int aOriginalWidth, final int aOriginalHeight, final Rectangle aOriginalClip) {
        this.myImage = aImage;
        this.setOriginalDimensions(aOriginalWidth, aOriginalHeight);
        this.setClippingRectangle(aOriginalClip);
        this.repaint(this.myClippingRectangle);
    }
    
    public void paintCanvas(final BufferedImage aImage, final int aOriginalWidth, final int aOriginalHeight) {
        this.myImage = aImage;
        this.setOriginalDimensions(aOriginalWidth, aOriginalHeight);
        this.repaint();
    }
    
    public String toString() {
        return "Canvas : " + this.getWidth() + " x " + this.getHeight() + "; scale=" + this.myScaleX + " x " + this.myScaleY;
    }
    
    private class CanvasComponentListener implements ComponentListener
    {
        public void componentShown(final ComponentEvent componentEvent) {
        }
        
        public void componentResized(final ComponentEvent componentEvent) {
            JStellaCanvas.this.updateScale();
            JStellaCanvas.this.refreshCanvas();
        }
        
        public void componentMoved(final ComponentEvent componentEvent) {
        }
        
        public void componentHidden(final ComponentEvent componentEvent) {
        }
    }
}
