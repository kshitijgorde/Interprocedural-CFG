import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

// 
// Decompiled by Procyon v0.5.30
// 

class MaxYPEABC4 implements ItemListener
{
    final MaxYPE thisABC0;
    
    public void itemStateChanged(final ItemEvent e) {
        this.thisABC0.checkbox1_itemStateChanged(e);
    }
    
    MaxYPEABC4(final MaxYPE thisABC0) {
        this.thisABC0 = thisABC0;
    }
}
