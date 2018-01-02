import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

public final class o extends MouseAdapter
{
    public final x a;
    
    public o(final x a) {
        this.a = a;
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        this.a.b(mouseEvent.getX(), mouseEvent.getY());
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
    }
}
