import java.awt.Color;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseListener;
import javax.swing.JTable;

// 
// Decompiled by Procyon v0.5.30
// 

public class BlackBoxTable
{
    private JTable _tableBlackBox;
    private static String[] ARRAY_HEADER;
    
    public BlackBoxTable(final MouseListener ml) {
        final DefaultTableModel model = new DefaultTableModel((Object[][])null, (Object[])BlackBoxTable.ARRAY_HEADER) {
            public boolean isCellEditable(final int row, final int column) {
                return false;
            }
        };
        (this._tableBlackBox = new JTable(model)).setSelectionMode(0);
        this._tableBlackBox.getTableHeader().setReorderingAllowed(false);
        this._tableBlackBox.setBackground(new Color(192, 192, 192));
        this._tableBlackBox.setSelectionBackground(Color.orange);
        this._tableBlackBox.clearSelection();
        this.setColumnWidths();
        this._tableBlackBox.addMouseListener(ml);
    }
    
    public JTable getTable() {
        return this._tableBlackBox;
    }
    
    public void update(final String[][] values) {
        final DefaultTableModel model = new DefaultTableModel((Object[][])values, (Object[])BlackBoxTable.ARRAY_HEADER) {
            public boolean isCellEditable(final int row, final int column) {
                return false;
            }
        };
        this._tableBlackBox.setModel(model);
        this.setColumnWidths();
    }
    
    private void setColumnWidths() {
        this._tableBlackBox.getColumnModel().getColumn(0).setPreferredWidth(100);
        this._tableBlackBox.getColumnModel().getColumn(1).setPreferredWidth(400);
        this._tableBlackBox.getColumnModel().getColumn(2).setPreferredWidth(400);
        this._tableBlackBox.getColumnModel().getColumn(3).setPreferredWidth(100);
    }
    
    static {
        BlackBoxTable.ARRAY_HEADER = new String[] { "<html><font color=\"#00009B\"><b>Test</b></font></html>", "<html><font color=\"#00009B\"><b>Input</b></font></html>", "<html><font color=\"#00009B\"><b>Output</b></font></html>", "<html><font color=\"#00009B\"><b>Pass/Fail</b></font></html>" };
    }
}
