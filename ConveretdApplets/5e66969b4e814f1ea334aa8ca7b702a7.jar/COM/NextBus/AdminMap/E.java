// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import COM.NextBus.DBModel.a;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import COM.NextBus.Predictor2Comm.BusReport;
import javax.swing.table.TableModel;
import java.awt.event.MouseListener;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.Frame;
import java.awt.Component;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;

final class E extends MouseAdapter
{
    private /* synthetic */ JTable a;
    private /* synthetic */ SwingEventDialog b;
    
    E(final SwingEventDialog b, final JTable a) {
        this.b = b;
        this.a = a;
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        final int convertColumnIndexToModel = this.a.convertColumnIndexToModel(this.a.getColumnModel().getColumnIndexAtX(mouseEvent.getX()));
        if (mouseEvent.getClickCount() == 1 && convertColumnIndexToModel != -1) {
            SwingEventDialog$BusEventTableModel.a(this.b._busEventTableModel, convertColumnIndexToModel, (mouseEvent.getModifiers() & 0x1) == 0x0);
        }
    }
}
