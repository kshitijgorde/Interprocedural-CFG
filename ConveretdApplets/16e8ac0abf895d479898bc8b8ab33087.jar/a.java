import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

public final class a extends MouseMotionAdapter
{
    public final x a;
    
    public a(final x a) {
        this.a = a;
    }
    
    public final void mouseMoved(final MouseEvent mouseEvent) {
        this.a.c(mouseEvent.getX(), mouseEvent.getY());
    }
}
