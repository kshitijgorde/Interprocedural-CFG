import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.Vector;
import javax.swing.JTable;

// 
// Decompiled by Procyon v0.5.30
// 

public class ad extends JTable implements t
{
    private Vector q;
    public TableModel r;
    
    public ad(final i i) {
        this.q = new Vector();
        this.setModel(this.r = new DefaultTableModel() {
            public int getColumnCount() {
                return 2;
            }
            
            public Object getValueAt(final int n, final int n2) {
                final aa aa = ad.this.q.elementAt(n);
                switch (n2) {
                    case 0: {
                        return aa.if;
                    }
                    case 1: {
                        return new Integer(aa.a());
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
                        return "IP";
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
                return (ad.this == null) ? 0 : ad.this.q.size();
            }
        });
        i.a(this);
    }
    
    public void a(final n n) {
        if (!this.q.contains(n.for())) {
            this.q.addElement(n.for());
        }
        this.tableChanged(new TableModelEvent(this.r));
    }
}
