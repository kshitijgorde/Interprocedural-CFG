// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsc;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.AWTEventMulticaster;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.Canvas;

public class ToggleButton extends Canvas
{
    private ActionListener actionListener;
    private Image trueImage;
    private Image falseImage;
    private boolean toggleState;
    private int defaultWidth;
    private int defaultHeight;
    private BaseChat pChat;
    private String mouseOver;
    
    public ToggleButton(final BaseChat pChat, final String mouseOver) {
        this.pChat = pChat;
        this.mouseOver = mouseOver;
        this.trueImage = null;
        this.falseImage = null;
        this.toggleState = false;
        this.defaultWidth = 16;
        this.defaultHeight = 16;
    }
    
    public void addActionListener(final ActionListener actionListener) {
        this.actionListener = AWTEventMulticaster.add(this.actionListener, actionListener);
        this.enableEvents(16L);
    }
    
    public void removeActionListener(final ActionListener actionListener) {
        this.actionListener = AWTEventMulticaster.remove(this.actionListener, actionListener);
    }
    
    public boolean getState() {
        return this.toggleState;
    }
    
    public void setState(final boolean toggleState) {
        this.toggleState = toggleState;
    }
    
    public void setTrueImage(final Image trueImage) {
        this.trueImage = trueImage;
    }
    
    public void setFalseImage(final Image falseImage) {
        this.falseImage = falseImage;
    }
    
    public Dimension getPreferredSize() {
        int n;
        int n2;
        if (this.trueImage != null) {
            n = this.trueImage.getWidth(null);
            n2 = this.trueImage.getHeight(null);
        }
        else {
            n = this.defaultWidth;
            n2 = this.defaultHeight;
        }
        return new Dimension(n, n2);
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(this.defaultWidth, this.defaultHeight);
    }
    
    public void paint(final Graphics graphics) {
        Image image;
        if (this.toggleState) {
            image = this.trueImage;
        }
        else {
            image = this.falseImage;
        }
        if (image != null) {
            graphics.drawImage(image, 0, 0, this);
        }
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 502: {
                this.doMouseReleased(mouseEvent);
                break;
            }
            case 504: {
                if (this.pChat != null && this.mouseOver != null) {
                    this.pChat.showStatus(this.mouseOver);
                    break;
                }
                break;
            }
            case 505: {
                if (this.pChat != null && this.mouseOver != null) {
                    this.pChat.showStatus("");
                    break;
                }
                break;
            }
        }
        super.processMouseEvent(mouseEvent);
    }
    
    private void doMouseReleased(final MouseEvent mouseEvent) {
        this.toggleState = !this.toggleState;
        this.repaint();
        if (this.actionListener != null) {
            this.actionListener.actionPerformed(new ActionEvent(this, 1001, "ToggleButtonRelease"));
        }
    }
}
