// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsx;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.AWTEventMulticaster;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.Canvas;

public class ImageButton extends Canvas
{
    private ActionListener actionListener;
    private Image staticImage;
    private Image activeImage;
    private Image clickImage;
    private Image currentImage;
    private int defaultWidth;
    private int defaultHeight;
    private String buttonID;
    
    public ImageButton() {
        this.staticImage = null;
        this.activeImage = null;
        this.clickImage = null;
        this.currentImage = null;
        this.defaultWidth = 16;
        this.defaultHeight = 16;
        this.buttonID = null;
    }
    
    public void addActionListener(final ActionListener actionListener) {
        this.actionListener = AWTEventMulticaster.add(this.actionListener, actionListener);
        this.enableEvents(16L);
    }
    
    public void removeActionListener(final ActionListener actionListener) {
        this.actionListener = AWTEventMulticaster.remove(this.actionListener, actionListener);
    }
    
    public void setID(final String buttonID) {
        this.buttonID = buttonID;
    }
    
    public String getID() {
        return this.buttonID;
    }
    
    public void setDefaultSize(final int defaultWidth, final int defaultHeight) {
        this.defaultWidth = defaultWidth;
        this.defaultHeight = defaultHeight;
    }
    
    public void setStaticImage(final Image staticImage) {
        this.staticImage = staticImage;
    }
    
    public void setActiveImage(final Image activeImage) {
        this.activeImage = activeImage;
    }
    
    public void setClickImage(final Image clickImage) {
        this.clickImage = clickImage;
    }
    
    public void setStaticImgAsCurrent() {
        this.currentImage = this.staticImage;
        this.repaint();
    }
    
    public void setActiveImgAsCurrent() {
        this.currentImage = this.activeImage;
        this.repaint();
    }
    
    public void setClickImgAsCurrent() {
        this.currentImage = this.clickImage;
        this.repaint();
    }
    
    public Dimension preferredSize() {
        return this.getPreferredSize();
    }
    
    public Dimension getPreferredSize() {
        if (this.staticImage != null) {
            return new Dimension(this.staticImage.getWidth(null), this.staticImage.getHeight(null));
        }
        return new Dimension(this.defaultWidth, this.defaultHeight);
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(this.defaultWidth, this.defaultHeight);
    }
    
    public void paint(final Graphics graphics) {
        if (this.currentImage == null) {
            this.currentImage = this.staticImage;
        }
        if (this.currentImage != null) {
            graphics.drawImage(this.currentImage, 0, 0, this);
        }
        else {
            System.out.println("Err #D843.");
        }
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 501: {
                this.doMousePressed();
                break;
            }
            case 502: {
                this.doMouseReleased(mouseEvent);
                break;
            }
            case 504: {
                this.doMouseEnter();
                break;
            }
            case 505: {
                this.doMouseExit();
                break;
            }
        }
        super.processMouseEvent(mouseEvent);
    }
    
    private void doMousePressed() {
    }
    
    private void doMouseReleased(final MouseEvent mouseEvent) {
        if (this.actionListener != null) {
            this.actionListener.actionPerformed(new ActionEvent(this, 1001, "imageButtonRelease"));
        }
    }
    
    private void doMouseEnter() {
        this.currentImage = this.activeImage;
        this.repaint();
    }
    
    private void doMouseExit() {
        this.currentImage = this.staticImage;
        this.repaint();
    }
}
