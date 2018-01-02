import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class ap implements ItemListener
{
    public final void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getSource() == aq.hd) {
            aq.ha.setText(String.valueOf(ca.mi[ca.mr(aq.hd.getSelectedItem())]));
        }
        aq.fv();
    }
}
