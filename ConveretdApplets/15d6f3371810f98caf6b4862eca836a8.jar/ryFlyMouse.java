import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

public class ryFlyMouse extends MouseAdapter
{
    ryFlyButton \u013f;
    
    public ryFlyMouse(final ryFlyButton \u0140) {
        this.\u013f = \u0140;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.\u013f.\u010c = true;
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.\u013f.\u010c = false;
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.\u013f.\u010b = true;
        this.\u013f.repaint();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.\u013f.\u010b = false;
        this.\u013f.repaint();
    }
}
