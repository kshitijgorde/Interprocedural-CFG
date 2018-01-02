import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class am implements MouseWheelListener
{
    ak a;
    
    public void a() {
        this.a = null;
    }
    
    public void a(final ak a) {
        this.a = a;
        a.long.addMouseWheelListener(this);
    }
    
    public void mouseWheelMoved(final MouseWheelEvent mouseWheelEvent) {
        this.a.a(mouseWheelEvent.getWheelRotation());
    }
}
