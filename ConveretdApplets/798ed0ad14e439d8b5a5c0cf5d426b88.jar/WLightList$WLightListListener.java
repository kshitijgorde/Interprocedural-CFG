import java.awt.ItemSelectable;
import java.awt.event.ItemEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

// 
// Decompiled by Procyon v0.5.30
// 

class WLightList$WLightListListener implements ListSelectionListener
{
    private WLightList list;
    private final WLightList this$0;
    
    public WLightList$WLightListListener(final WLightList this$0, final WLightList list) {
        this.this$0 = this$0;
        this.list = list;
    }
    
    public final void valueChanged(final ListSelectionEvent listSelectionEvent) {
        if (!this.this$0.B) {
            this.this$0.B = true;
            return;
        }
        if (this.this$0.Z != null) {
            this.this$0.Z.itemStateChanged(new ItemEvent(this.list, 701, this.this$0.getSelectedItem(), 1));
        }
    }
}
