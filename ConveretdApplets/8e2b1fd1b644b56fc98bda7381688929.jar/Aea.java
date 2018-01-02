import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

// 
// Decompiled by Procyon v0.5.30
// 

class Aea implements ItemListener
{
    private final super da;
    
    Aea(final super da) {
        this.da = da;
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        synchronized (this.da) {
            if (itemEvent.getSource() == super.c(this.da)) {
                final String selectedItem = super.c(this.da).getSelectedItem();
                if (super.b(this.da).a().a("cbRange_1month").equals(selectedItem)) {
                    super._(this.da).c(10);
                }
                else if (super.b(this.da).a().a("cbRange_3months").equals(selectedItem)) {
                    super._(this.da).c(11);
                }
                else if (super.b(this.da).a().a("cbRange_6months").equals(selectedItem)) {
                    super._(this.da).c(12);
                }
                else if (super.b(this.da).a().a("cbRange_1year").equals(selectedItem)) {
                    super._(this.da).c(13);
                }
                else if (super.b(this.da).a().a("cbRange_2years").equals(selectedItem)) {
                    super._(this.da).c(14);
                }
                else if (super.b(this.da).a().a("cbRange_3years").equals(selectedItem)) {
                    super._(this.da).c(15);
                }
                else if (super.b(this.da).a().a("cbRange_5years").equals(selectedItem)) {
                    super._(this.da).c(16);
                }
                else if (super.b(this.da).a().a("cbRange_10years").equals(selectedItem)) {
                    super._(this.da).c(17);
                }
                else if (super.b(this.da).a().a("cbRange_all").equals(selectedItem)) {
                    super._(this.da).c(0);
                }
                super._(this.da).repaint();
            }
        }
    }
}
