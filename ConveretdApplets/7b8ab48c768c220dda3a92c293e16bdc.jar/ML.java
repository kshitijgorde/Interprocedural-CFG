import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// 
// Decompiled by Procyon v0.5.30
// 

class ML implements MouseListener
{
    Interface pInterface;
    
    ML(final Interface pInterface) {
        this.pInterface = pInterface;
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.pInterface.PressMouse(mouseEvent.getX(), mouseEvent.getY());
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.pInterface.ReleaseMouse(mouseEvent.getX(), mouseEvent.getY());
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.pInterface.ClickMouse(mouseEvent.getX(), mouseEvent.getY());
    }
}
