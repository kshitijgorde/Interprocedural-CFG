import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class SI extends MouseMotionAdapter
{
    private volatile boolean I;
    private final ztmPlayer Z;
    
    SI(final ztmPlayer z) {
        this.Z = z;
    }
    
    public final void mouseMoved(final MouseEvent mouseEvent) {
        if (!this.Z.b && !this.I && this.Z.N - mouseEvent.getX() <= 30 && this.Z.O - mouseEvent.getY() <= 30) {
            this.Z.setCursor(Cursor.getPredefinedCursor(12));
            this.I = true;
        }
        else if (this.I && (mouseEvent.getX() < this.Z.N - 30 || mouseEvent.getY() < this.Z.O - 30)) {
            this.Z.setCursor(Cursor.getPredefinedCursor(0));
            this.I = false;
        }
    }
}
