import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class MyMouseMotionAdapter extends MouseMotionAdapter
{
    Geo geo;
    Line a;
    
    public MyMouseMotionAdapter(final Geo geo) {
        this.geo = geo;
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.geo.mouseMoved(mouseEvent.getX(), mouseEvent.getY());
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.geo.mouseDragged(mouseEvent.getX(), mouseEvent.getY());
    }
}
