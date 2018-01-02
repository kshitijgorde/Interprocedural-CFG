// 
// Decompiled by Procyon v0.5.30
// 

package ru.kryshen.graphg;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Component;

class AboutPane extends Component implements MouseListener, MouseMotionListener
{
    private boolean showURLBorder;
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (this.showURLBorder) {
            this.showURLBorder = false;
            this.repaint();
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    AboutPane() {
        this.showURLBorder = false;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(439, 247);
    }
}
