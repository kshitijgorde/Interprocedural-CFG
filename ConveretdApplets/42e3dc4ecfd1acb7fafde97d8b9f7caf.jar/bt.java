import javax.swing.JComboBox;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class bt implements ItemListener
{
    private bm a;
    
    public bt(final bm a) {
        this.a = a;
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        this.a.b();
        if (itemEvent.getSource() instanceof JComboBox && ((JComboBox)itemEvent.getSource()).getName() != null && ((JComboBox)itemEvent.getSource()).getName().equals("langchange")) {
            this.a.b = true;
        }
    }
}
