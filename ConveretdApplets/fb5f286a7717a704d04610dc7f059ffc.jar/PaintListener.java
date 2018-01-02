import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class PaintListener extends MouseAdapter
{
    public void mousePressed(final MouseEvent mouseEvent) {
        ColoringBook.mousex = mouseEvent.getX();
        ColoringBook.mousey = mouseEvent.getY();
        ColoringBook.validP = true;
        mouseEvent.getComponent().repaint();
    }
}
