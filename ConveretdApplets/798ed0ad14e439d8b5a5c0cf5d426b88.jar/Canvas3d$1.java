import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class Canvas3d$1 extends MouseAdapter
{
    private final Canvas3d this$0;
    
    Canvas3d$1(final Canvas3d this$0) {
        this.this$0 = this$0;
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
        if (Canvas3d.I(this.this$0).getPlanes() == 1) {
            this.this$0.repaintTrue();
        }
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        if (Canvas3d.I(this.this$0).getPlanes() == 1) {
            this.this$0.repaintTrue();
        }
    }
}
