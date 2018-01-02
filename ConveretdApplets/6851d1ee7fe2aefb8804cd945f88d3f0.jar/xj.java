import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

// 
// Decompiled by Procyon v0.5.30
// 

class xj implements ItemListener
{
    private final n ta;
    
    xj(final n ta) {
        this.ta = ta;
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        synchronized (this.ta) {
            if (itemEvent.getSource() == n.f(this.ta)) {
                final String selectedItem = n.f(this.ta).getSelectedItem();
                if (n.a(this.ta).a().b("cbRange_1month").equals(selectedItem)) {
                    n._(this.ta).m(10);
                }
                else if (n.a(this.ta).a().b("cbRange_3months").equals(selectedItem)) {
                    n._(this.ta).m(11);
                }
                else if (n.a(this.ta).a().b("cbRange_6months").equals(selectedItem)) {
                    n._(this.ta).m(12);
                }
                else if (n.a(this.ta).a().b("cbRange_1year").equals(selectedItem)) {
                    n._(this.ta).m(13);
                }
                else if (n.a(this.ta).a().b("cbRange_2years").equals(selectedItem)) {
                    n._(this.ta).m(14);
                }
                else if (n.a(this.ta).a().b("cbRange_3years").equals(selectedItem)) {
                    n._(this.ta).m(15);
                }
                else if (n.a(this.ta).a().b("cbRange_5years").equals(selectedItem)) {
                    n._(this.ta).m(16);
                }
                else if (n.a(this.ta).a().b("cbRange_10years").equals(selectedItem)) {
                    n._(this.ta).m(17);
                }
                else if (n.a(this.ta).a().b("cbRange_all").equals(selectedItem)) {
                    n._(this.ta).m(0);
                }
                n._(this.ta).repaint();
            }
        }
    }
}
