// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.ui;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import prefuse.util.StringLib;
import prefuse.util.collections.IntIterator;
import prefuse.util.collections.CopyOnWriteArrayList;
import java.awt.Component;
import javax.swing.JScrollPane;
import prefuse.visual.VisualTable;
import javax.swing.JFrame;
import prefuse.data.event.TableListener;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import prefuse.data.Table;
import javax.swing.JTable;

public class JPrefuseTable extends JTable
{
    private Table m_table;
    private TableCellRenderer m_tcr;
    
    public JPrefuseTable(final Table table) {
        this.m_tcr = new DefaultTableCellRenderer();
        this.m_table = table;
        final PrefuseTableModel model = new PrefuseTableModel();
        super.setModel(model);
        this.m_table.addTableListener(model);
    }
    
    public Table getTable() {
        return this.m_table;
    }
    
    public TableCellRenderer getCellRenderer(final int n, final int n2) {
        return this.m_tcr;
    }
    
    public static JFrame showTableWindow(final Table table) {
        final JPrefuseTable prefuseTable = new JPrefuseTable(table);
        String s = table.toString();
        if (table instanceof VisualTable) {
            s = ((VisualTable)table).getGroup() + " " + s;
        }
        final JFrame frame = new JFrame(s);
        frame.getContentPane().add(new JScrollPane(prefuseTable));
        frame.pack();
        frame.setVisible(true);
        return frame;
    }
    
    public class PrefuseTableModel implements TableModel, TableListener
    {
        private CopyOnWriteArrayList m_listeners;
        private int[] m_rowmap;
        
        public PrefuseTableModel() {
            this.m_listeners = new CopyOnWriteArrayList();
        }
        
        private void initRowMap() {
            this.m_rowmap = new int[JPrefuseTable.this.m_table.getRowCount()];
            final IntIterator rows = JPrefuseTable.this.m_table.rows();
            int n = 0;
            while (rows.hasNext()) {
                this.m_rowmap[n] = rows.nextInt();
                ++n;
            }
        }
        
        private int getRow(final int n) {
            if (this.m_rowmap == null) {
                this.initRowMap();
            }
            return this.m_rowmap[n];
        }
        
        public int getColumnCount() {
            return JPrefuseTable.this.m_table.getColumnCount();
        }
        
        public int getRowCount() {
            return JPrefuseTable.this.m_table.getRowCount();
        }
        
        public boolean isCellEditable(final int n, final int n2) {
            return JPrefuseTable.this.m_table.isCellEditable(n, n2);
        }
        
        public Class getColumnClass(final int n) {
            return JPrefuseTable.this.m_table.getColumnType(n);
        }
        
        public Object getValueAt(final int n, final int n2) {
            final Object value = JPrefuseTable.this.m_table.get(this.getRow(n), n2);
            if (value != null && value.getClass().isArray()) {
                return StringLib.getArrayString(value);
            }
            return value;
        }
        
        public void setValueAt(final Object o, final int n, final int n2) {
            JPrefuseTable.this.m_table.set(this.getRow(n), n2, o);
        }
        
        public String getColumnName(final int n) {
            return JPrefuseTable.this.m_table.getColumnName(n);
        }
        
        public void addTableModelListener(final TableModelListener tableModelListener) {
            this.m_listeners.add(tableModelListener);
        }
        
        public void removeTableModelListener(final TableModelListener tableModelListener) {
            this.m_listeners.remove(tableModelListener);
        }
        
        public void tableChanged(final Table table, final int n, final int n2, final int n3, final int n4) {
            if (n4 == 1 || n4 == -1) {
                this.m_rowmap = null;
            }
            final Object[] array = this.m_listeners.getArray();
            if (array.length == 0) {
                return;
            }
            final TableModelEvent tableModelEvent = new TableModelEvent(this, n, n2, n3, n4);
            for (int i = 0; i < array.length; ++i) {
                ((TableModelListener)array[i]).tableChanged(tableModelEvent);
            }
        }
    }
}
