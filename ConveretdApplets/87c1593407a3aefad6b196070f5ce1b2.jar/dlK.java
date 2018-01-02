import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

// 
// Decompiled by Procyon v0.5.30
// 

class dlK implements ItemListener
{
    private final rush l;
    
    dlK(final rush l) {
        this.l = l;
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (this.l.bdhBlF.getSelectedItem().compareTo("Completed!") != 0) {
            rush.BDhblf(this.l, this.l.bdhBlF.getSelectedIndex() + 1);
        }
    }
}
