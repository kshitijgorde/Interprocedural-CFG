import java.awt.event.AdjustmentListener;
import java.awt.Scrollbar;

// 
// Decompiled by Procyon v0.5.30
// 

class WScrollPane$WScrollBar extends Scrollbar
{
    private final WScrollPane this$0;
    
    WScrollPane$WScrollBar(final WScrollPane this$0, final int n) {
        super(n);
        this.this$0 = this$0;
        this.disableEvents(4L);
        this.addAdjustmentListener(this$0);
    }
    
    public final boolean isFocusTraversable() {
        return false;
    }
}
