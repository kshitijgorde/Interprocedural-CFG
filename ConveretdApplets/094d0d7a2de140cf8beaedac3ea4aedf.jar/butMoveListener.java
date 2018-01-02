import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class butMoveListener extends MouseMotionAdapter
{
    int x;
    int y;
    int width;
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.x = mouseEvent.getX();
        this.y = mouseEvent.getY();
    }
}
