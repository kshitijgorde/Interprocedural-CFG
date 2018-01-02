// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.controls;

import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseEvent;
import prefuse.visual.VisualItem;

public class ControlAdapter implements Control
{
    private boolean m_enabled;
    
    public ControlAdapter() {
        this.m_enabled = true;
    }
    
    public boolean isEnabled() {
        return this.m_enabled;
    }
    
    public void setEnabled(final boolean enabled) {
        this.m_enabled = enabled;
    }
    
    public void itemDragged(final VisualItem visualItem, final MouseEvent mouseEvent) {
    }
    
    public void itemMoved(final VisualItem visualItem, final MouseEvent mouseEvent) {
    }
    
    public void itemWheelMoved(final VisualItem visualItem, final MouseWheelEvent mouseWheelEvent) {
    }
    
    public void itemClicked(final VisualItem visualItem, final MouseEvent mouseEvent) {
    }
    
    public void itemPressed(final VisualItem visualItem, final MouseEvent mouseEvent) {
    }
    
    public void itemReleased(final VisualItem visualItem, final MouseEvent mouseEvent) {
    }
    
    public void itemEntered(final VisualItem visualItem, final MouseEvent mouseEvent) {
    }
    
    public void itemExited(final VisualItem visualItem, final MouseEvent mouseEvent) {
    }
    
    public void itemKeyPressed(final VisualItem visualItem, final KeyEvent keyEvent) {
    }
    
    public void itemKeyReleased(final VisualItem visualItem, final KeyEvent keyEvent) {
    }
    
    public void itemKeyTyped(final VisualItem visualItem, final KeyEvent keyEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mouseWheelMoved(final MouseWheelEvent mouseWheelEvent) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
}
