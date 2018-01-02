import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class MyMouseAdapter extends MouseAdapter
{
    Geo geo;
    
    public MyMouseAdapter(final Geo geo) {
        this.geo = geo;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.geo.mouseClicked(mouseEvent.getX(), mouseEvent.getY());
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.geo.mousePressed(mouseEvent.getX(), mouseEvent.getY());
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.geo.mouseReleased();
    }
}
