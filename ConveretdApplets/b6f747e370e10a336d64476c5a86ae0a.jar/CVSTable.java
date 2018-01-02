import java.awt.Color;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

// 
// Decompiled by Procyon v0.5.30
// 

public class CVSTable
{
    private JTable _tableCVS;
    private static String[] ARRAY_HEADER;
    
    public CVSTable() {
        final DefaultTableModel model = new DefaultTableModel((Object[][])null, (Object[])CVSTable.ARRAY_HEADER) {
            public boolean isCellEditable(final int row, final int column) {
                return false;
            }
        };
        (this._tableCVS = new JTable(model)).setSelectionMode(0);
        this._tableCVS.getTableHeader().setReorderingAllowed(false);
        this._tableCVS.setBackground(new Color(192, 192, 192));
        this._tableCVS.setSelectionBackground(Color.orange);
        this._tableCVS.clearSelection();
        this._tableCVS.getColumnModel().getColumn(0).setPreferredWidth(200);
        this._tableCVS.getColumnModel().getColumn(1).setPreferredWidth(200);
        this._tableCVS.getColumnModel().getColumn(2).setPreferredWidth(600);
    }
    
    public JTable getTable() {
        return this._tableCVS;
    }
    
    public void update(final String[][] values) {
        final DefaultTableModel model = new DefaultTableModel((Object[][])values, (Object[])CVSTable.ARRAY_HEADER) {
            public boolean isCellEditable(final int row, final int column) {
                return false;
            }
        };
        this._tableCVS.setModel(model);
        this._tableCVS.getColumnModel().getColumn(0).setPreferredWidth(200);
        this._tableCVS.getColumnModel().getColumn(1).setPreferredWidth(200);
        this._tableCVS.getColumnModel().getColumn(2).setPreferredWidth(600);
    }
    
    static {
        CVSTable.ARRAY_HEADER = new String[] { "<html><font color=\"#00009B\"><b>Version</b></font></html>", "<html><font color=\"#00009B\"><b>Action</b></font></html>", "<html><font color=\"#00009B\"><b>Comment</b></font></html>" };
    }
}
