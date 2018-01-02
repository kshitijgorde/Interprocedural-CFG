import java.awt.CheckboxMenuItem;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

// 
// Decompiled by Procyon v0.5.30
// 

class v implements ItemListener
{
    bk a;
    private final be b;
    
    v(final be b, final bk a) {
        this.b = b;
        this.a = null;
        this.a = a;
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        ((CheckboxMenuItem)itemEvent.getSource()).setState(((CheckboxMenuItem)itemEvent.getSource()).getState());
        final String label = ((CheckboxMenuItem)itemEvent.getSource()).getLabel();
        if (label.equals("Enable Hot Spots")) {
            this.a.b().G = ((CheckboxMenuItem)itemEvent.getSource()).getState();
        }
        if (label.equals("Show Hot Spot Targets")) {
            this.a.b().H = ((CheckboxMenuItem)itemEvent.getSource()).getState();
        }
        if (label.equals("Enable Popup Text")) {
            this.a.b().I = ((CheckboxMenuItem)itemEvent.getSource()).getState();
        }
    }
}
