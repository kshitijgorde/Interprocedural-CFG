// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import java.awt.event.MouseEvent;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;

final class P extends MouseAdapter
{
    private /* synthetic */ JTable a;
    private /* synthetic */ SwingEventDialog b;
    
    P(final SwingEventDialog b, final JTable a) {
        this.b = b;
        this.a = a;
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        final int selectedRow;
        if ((selectedRow = this.a.getSelectedRow()) != -1) {
            final String s = (String)this.a.getModel().getValueAt(selectedRow, 0);
            if ((mouseEvent.getModifiers() & 0x8) != 0x0) {
                this.b._agencyManager.l(s);
                return;
            }
            this.b._agencyManager.k(s);
        }
    }
}
