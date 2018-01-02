import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.Vector;
import javax.swing.JTable;

// 
// Decompiled by Procyon v0.5.30
// 

public class f extends JTable implements t
{
    private Vector do;
    public TableModel for;
    
    public f(final i i) {
        this.do = new Vector();
        this.setModel(this.for = new DefaultTableModel() {
            public int getColumnCount() {
                return 2;
            }
            
            public Object getValueAt(final int n, final int n2) {
                final x x = f.this.do.elementAt(n);
                switch (n2) {
                    case 0: {
                        return x.if();
                    }
                    case 1: {
                        return new Integer(x.a());
                    }
                    default: {
                        return null;
                    }
                }
            }
            
            public boolean isCellEditable(final int n, final int n2) {
                return false;
            }
            
            public String getColumnName(final int n) {
                switch (n) {
                    case 0: {
                        return "Path";
                    }
                    case 1: {
                        return "Hits";
                    }
                    default: {
                        return "";
                    }
                }
            }
            
            public int getRowCount() {
                return (f.this == null) ? 0 : f.this.do.size();
            }
        });
        i.a(this);
    }
    
    public void a(final n n) {
        if (!this.do.contains(n.if())) {
            this.do.addElement(n.if());
        }
        this.tableChanged(new TableModelEvent(this.for));
    }
}
