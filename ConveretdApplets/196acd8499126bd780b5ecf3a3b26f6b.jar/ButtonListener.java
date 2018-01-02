import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class ButtonListener extends MouseAdapter
{
    public void mouseEntered(final MouseEvent mouseEvent) {
        Framer.mouseEntered(mouseEvent);
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        Framer.mouseExited(mouseEvent);
    }
}
