// 
// Decompiled by Procyon v0.5.30
// 

package com.magelang.splitter;

import java.awt.AWTEventMulticaster;
import com.magelang.Colors;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.Canvas;

public class SwapButton extends Canvas implements MouseListener
{
    protected transient ActionListener aActionListener;
    private boolean state;
    
    public SwapButton() {
        this.aActionListener = null;
        this.state = true;
        this.initialize();
    }
    
    public Dimension getPreferredSize() {
        return this.preferredSize();
    }
    
    public Dimension getMinimumSize() {
        return this.minimumSize();
    }
    
    private void initialize() {
        this.setName("SwapButton");
        this.setName("SwapButton");
        this.setSize(150, 150);
        this.initConnections();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.state = false;
        this.repaint();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.state = true;
        this.repaint();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == this) {
            this.conn0(mouseEvent);
        }
        this.state = true;
        this.repaint();
    }
    
    private void initConnections() {
        this.addMouseListener(this);
    }
    
    private void conn0(final MouseEvent mouseEvent) {
        try {
            if (this.state) {
                return;
            }
            this.fireActionPerformed(new ActionEvent(this, 1001, ""));
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    protected void fireActionPerformed(final ActionEvent actionEvent) {
        if (this.aActionListener == null) {
            return;
        }
        this.aActionListener.actionPerformed(actionEvent);
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        graphics.setColor(Color.gray);
        graphics.fill3DRect(0, 0, size.width - 1, size.height - 1, this.state);
        graphics.setColor(Colors.lightSkyBlue3.brighter());
        graphics.drawLine(2, 2, size.width - 4, size.height - 4);
        graphics.drawLine(2, size.height - 4, size.width - 4, 2);
    }
    
    private void handleException(final Throwable t) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void addActionListener(final ActionListener actionListener) {
        this.aActionListener = AWTEventMulticaster.add(this.aActionListener, actionListener);
    }
    
    public Dimension minimumSize() {
        return new Dimension(10, 10);
    }
    
    public Dimension preferredSize() {
        return new Dimension(10, 10);
    }
    
    public void removeActionListener(final ActionListener actionListener) {
        this.aActionListener = AWTEventMulticaster.remove(this.aActionListener, actionListener);
    }
}
