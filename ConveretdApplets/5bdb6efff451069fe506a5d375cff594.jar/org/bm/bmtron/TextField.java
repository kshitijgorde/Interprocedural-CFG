// 
// Decompiled by Procyon v0.5.30
// 

package org.bm.bmtron;

import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.Container;

public class TextField extends Container implements MouseListener, KeyListener, FocusListener
{
    private final Game bmt;
    private boolean active;
    private java.awt.TextField tf;
    private final int width = 333;
    private final int height = 35;
    
    TextField(final String s, final Game bmt) {
        this.active = false;
        this.bmt = bmt;
        this.setLayout(null);
        (this.tf = new java.awt.TextField(s)).setSize(333, 35);
        this.tf.setLocation(0, 0);
        this.tf.setVisible(false);
        this.tf.setBackground(bmt.skin.buttonBackColor);
        this.tf.setForeground(bmt.skin.buttonTextColor);
        this.tf.setFont(bmt.skin.buttonFont);
        this.add(this.tf);
        this.tf.addKeyListener(this);
        this.tf.addFocusListener(this);
        this.setSize(333, 35);
        this.addMouseListener(this);
    }
    
    public void setText(final String text) {
        this.tf.setText(text);
        this.repaint();
    }
    
    public String getText() {
        return this.tf.getText();
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.bmt.skin.buttonBackColor);
        graphics.fillRect(0, 0, 332, 34);
        if (this.active) {
            graphics.setColor(this.bmt.skin.activeButtonBorderColor);
        }
        else {
            graphics.setColor(this.bmt.skin.buttonBorderColor);
        }
        graphics.drawRect(0, 0, 332, 34);
        graphics.setColor(this.bmt.skin.buttonTextColor);
        final String text = this.tf.getText();
        graphics.setFont(this.bmt.skin.buttonFont);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        graphics.drawString(text, (333 - fontMetrics.stringWidth(text)) / 2, (35 - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent());
        super.paint(graphics);
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
        this.tf.setVisible(true);
        this.tf.requestFocus();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 10) {
            this.tf.setVisible(false);
            this.requestFocus();
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void focusGained(final FocusEvent focusEvent) {
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        this.tf.setVisible(false);
    }
}
