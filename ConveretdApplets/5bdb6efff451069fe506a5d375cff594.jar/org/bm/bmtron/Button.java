// 
// Decompiled by Procyon v0.5.30
// 

package org.bm.bmtron;

import java.awt.Dimension;
import java.awt.AWTEventMulticaster;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.Component;

public class Button extends Component implements MouseListener
{
    private final Game bmt;
    private Image image;
    private boolean active;
    private boolean pressed;
    private ActionListener actionListener;
    
    Button(final Image image, final Game bmt) {
        this.active = false;
        this.pressed = false;
        this.actionListener = null;
        this.bmt = bmt;
        this.image = image;
        this.setSize(this.getPreferredSize());
        this.setBackground(bmt.skin.buttonBackColor);
        this.setForeground(bmt.skin.buttonTextColor);
        this.setFont(bmt.skin.buttonFont);
        this.addMouseListener(this);
    }
    
    public void setImage(final Image image) {
        this.image = image;
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        int n;
        if (this.pressed) {
            n = 1;
        }
        else {
            n = 0;
        }
        graphics.drawImage(this.image, n + 1, n + 1, this);
        if (this.pressed) {
            graphics.setColor(Color.black);
            graphics.drawRect(n, n, this.getSize().width - 1, this.getSize().height - 1);
        }
        if (this.active) {
            graphics.setColor(this.bmt.skin.activeButtonBorderColor);
        }
        else {
            graphics.setColor(this.bmt.skin.buttonBorderColor);
        }
        graphics.drawRect(0, 0, this.getSize().width - 1, this.getSize().height - 1);
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.active = true;
        this.repaint();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.active = false;
        this.repaint();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.pressed = true;
        this.repaint();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.pressed = false;
        this.repaint();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (this.actionListener != null) {
            this.actionListener.actionPerformed(new ActionEvent(this, 0, null));
        }
    }
    
    public synchronized void addActionListener(final ActionListener actionListener) {
        this.actionListener = AWTEventMulticaster.add(this.actionListener, actionListener);
    }
    
    public synchronized void removeActionListener(final ActionListener actionListener) {
        this.actionListener = AWTEventMulticaster.remove(this.actionListener, actionListener);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.image.getWidth(this) + 2, this.image.getHeight(this) + 2);
    }
}
