// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import javax.swing.table.TableModel;
import java.awt.event.MouseListener;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.Frame;
import java.awt.Component;
import COM.NextBus.DBModel.a;
import COM.NextBus.Predictor2Comm.BusReport;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

class SwingEventDialog$BusEventTableModel extends AbstractTableModel
{
    private static final long serialVersionUID = -3028207091320430295L;
    private List _busEvents;
    final /* synthetic */ SwingEventDialog this$0;
    
    private SwingEventDialog$BusEventTableModel(final SwingEventDialog this$0, final byte b) {
        this.this$0 = this$0;
        this._busEvents = new ArrayList();
    }
    
    public int getRowCount() {
        return this._busEvents.size();
    }
    
    public int getColumnCount() {
        return this.this$0.COLUMN_NAMES.length;
    }
    
    public Object getValueAt(final int n, final int n2) {
        String s = "";
        try {
            final BusReport busReport = this._busEvents.get(n);
            if (n2 == 0) {
                s = busReport.c();
            }
            else if (n2 == 1) {
                s = busReport.l();
            }
            else if (n2 == 2) {
                s = this.this$0._agencyManager.a(busReport.e());
            }
            else if (n2 == 3) {
                s = a.a(-1 * busReport.g());
            }
            else {
                s = null;
            }
        }
        catch (IndexOutOfBoundsException ex) {}
        if (s == null) {
            s = "";
        }
        return s;
    }
    
    public void setValueAt(final Object o, final int n, final int n2) {
    }
    
    public boolean isCellEditable(final int n, final int n2) {
        return false;
    }
    
    public String getColumnName(final int n) {
        return this.this$0.COLUMN_NAMES[n];
    }
    
    public Class getColumnClass(final int n) {
        return SwingEventDialog.a[n];
    }
}
