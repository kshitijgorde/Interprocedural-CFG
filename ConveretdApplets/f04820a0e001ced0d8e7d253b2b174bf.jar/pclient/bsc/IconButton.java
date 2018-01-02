// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsc;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.Canvas;

public class IconButton extends Canvas implements MouseListener
{
    private ActionListener actionListener;
    private Image iconImage;
    private Image mouseOverImage;
    private Image pressedImage;
    private Image grayImage;
    private Image currentImage;
    private int defaultWidth;
    private int defaultHeight;
    private boolean isEnabled;
    private BaseChat pChat;
    private String overMessage;
    
    public IconButton(final BaseChat pChat, final String overMessage) {
        this.currentImage = null;
        this.isEnabled = true;
        this.pChat = pChat;
        this.overMessage = overMessage;
        this.isEnabled = true;
        this.iconImage = null;
        this.defaultWidth = 20;
        this.defaultHeight = 20;
        this.enableEvents(16L);
        this.addMouseListener(this);
    }
    
    public void addActionListener(final ActionListener actionListener) {
        this.actionListener = actionListener;
    }
    
    public void removeActionListener(final ActionListener actionListener) {
        this.actionListener = null;
    }
    
    public void setEnabled(final boolean isEnabled) {
        this.isEnabled = isEnabled;
        if (this.isEnabled) {
            this.currentImage = this.iconImage;
        }
        else {
            this.currentImage = this.grayImage;
        }
        this.repaint();
    }
    
    public void setImage(final Image iconImage) {
        if (iconImage != null) {
            this.iconImage = iconImage;
        }
    }
    
    public void setMouseOver(final Image mouseOverImage) {
        this.mouseOverImage = mouseOverImage;
    }
    
    public void setPressed(final Image pressedImage) {
        this.pressedImage = pressedImage;
    }
    
    public void setGray(final Image grayImage) {
        this.grayImage = grayImage;
    }
    
    public Dimension getPreferredSize() {
        int n;
        int n2;
        if (this.iconImage != null) {
            n = this.iconImage.getWidth(null);
            n2 = this.iconImage.getHeight(null);
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
        if (this.currentImage == null) {
            this.currentImage = this.iconImage;
        }
        if (this.currentImage != null) {
            graphics.drawImage(this.currentImage, 0, 0, this);
        }
    }
    
    public void drawButton() {
        this.currentImage = null;
        this.repaint();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (!this.isEnabled) {
            return;
        }
        this.currentImage = null;
        this.repaint();
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        final int width = this.getPreferredSize().width;
        final int height = this.getPreferredSize().height;
        if (x < 0 || x > width || y < 0 || y > height) {
            return;
        }
        if (this.actionListener != null) {
            this.actionListener.actionPerformed(new ActionEvent(this, 1001, "IBR"));
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (!this.isEnabled) {
            return;
        }
        if (this.pressedImage == null) {
            return;
        }
        this.currentImage = this.pressedImage;
        this.repaint();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (!this.isEnabled) {
            return;
        }
        if (this.pChat != null && this.overMessage != null) {
            this.pChat.showStatus(this.overMessage);
        }
        if (this.mouseOverImage == null) {
            return;
        }
        this.currentImage = this.mouseOverImage;
        this.repaint();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (!this.isEnabled) {
            return;
        }
        if (this.pChat != null && this.overMessage != null) {
            this.pChat.showStatus("");
        }
        this.currentImage = null;
        this.repaint();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
}
