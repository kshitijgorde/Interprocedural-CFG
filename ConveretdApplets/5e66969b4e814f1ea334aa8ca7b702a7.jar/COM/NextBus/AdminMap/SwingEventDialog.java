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
import javax.swing.JTable;
import java.awt.Frame;
import java.awt.Component;

public class SwingEventDialog extends EventDialog
{
    private static final long serialVersionUID = 7940908224470636328L;
    private static Class[] a;
    private String[] COLUMN_NAMES;
    private static int[] b;
    private Component _component;
    private SwingEventDialog$BusEventTableModel _busEventTableModel;
    
    public SwingEventDialog(final Frame frame, final String s, final Component component, final O o, final t t) {
        super(frame, s, component, o, t);
        (this.COLUMN_NAMES = new String[4])[0] = "Vehicle";
        this.COLUMN_NAMES[1] = o.l();
        this.COLUMN_NAMES[2] = "Event Time";
        this.COLUMN_NAMES[3] = "Event Type";
        this._busEventTableModel = new SwingEventDialog$BusEventTableModel(this);
    }
    
    private static int a(String s, String s2) {
        if (s == null) {
            s = "";
        }
        if (s2 == null) {
            s2 = "";
        }
        return s.compareTo(s2);
    }
    
    public final Component b() {
        if (this._component == null) {
            final JTable table = new JTable();
            final JPanel component = new JPanel();
            final JScrollPane scrollPane = new JScrollPane(table);
            final JPanel panel = new JPanel();
            component.add(scrollPane);
            component.add(panel);
            final JTable table2 = table;
            table2.addMouseListener(new P(this, table2));
            final JTable table3;
            (table3 = table).setColumnSelectionAllowed(false);
            table3.getTableHeader().addMouseListener(new E(this, table3));
            table.setModel(this._busEventTableModel);
            for (int i = 0; i < SwingEventDialog.b.length; ++i) {
                table.getColumnModel().getColumn(i).setPreferredWidth(SwingEventDialog.b[i]);
            }
            this._component = component;
        }
        return this._component;
    }
    
    public final void c() {
        SwingEventDialog$BusEventTableModel.a(this._busEventTableModel);
    }
    
    static {
        SwingEventDialog.a = new Class[] { String.class, String.class, String.class, String.class };
        SwingEventDialog.b = new int[] { 50, 50, 150, 120 };
    }
}
