import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class PaintMoveListener extends MouseMotionAdapter
{
    public void mouseDragged(final MouseEvent mouseEvent) {
        ColoringBook.mousex = mouseEvent.getX();
        ColoringBook.mousey = mouseEvent.getY();
        ColoringBook.validP = true;
        mouseEvent.getComponent().repaint();
    }
}
