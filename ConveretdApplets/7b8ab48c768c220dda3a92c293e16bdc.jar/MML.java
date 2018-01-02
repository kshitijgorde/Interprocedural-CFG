import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

// 
// Decompiled by Procyon v0.5.30
// 

class MML implements MouseMotionListener
{
    Interface pInterface;
    
    MML(final Interface pInterface) {
        this.pInterface = pInterface;
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.pInterface.MoveMouse(mouseEvent.getX(), mouseEvent.getY());
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.pInterface.DragMouse(mouseEvent.getX(), mouseEvent.getY());
    }
}
