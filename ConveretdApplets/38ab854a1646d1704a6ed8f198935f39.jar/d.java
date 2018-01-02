import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.Vector;
import javax.swing.JTable;

// 
// Decompiled by Procyon v0.5.30
// 

public class d extends JTable implements t
{
    private Vector a;
    public TableModel if;
    
    public d(final i i) {
        this.a = new Vector();
        this.setModel(this.if = new DefaultTableModel() {
            public int getColumnCount() {
                return 7;
            }
            
            public Object getValueAt(final int n, final int n2) {
                final n n3 = d.this.a.elementAt(n);
                switch (n2) {
                    case 0: {
                        return n3.for;
                    }
                    case 1: {
                        return n3.byte;
                    }
                    case 2: {
                        return n3.if;
                    }
                    case 3: {
                        return n3.int;
                    }
                    case 4: {
                        return n3.try;
                    }
                    case 5: {
                        return n3.a();
                    }
                    case 6: {
                        return new Integer(n3.a);
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
                        return "Visitor";
                    }
                    case 1: {
                        return "Time";
                    }
                    case 2: {
                        return "Type";
                    }
                    case 3: {
                        return "Path";
                    }
                    case 4: {
                        return "Version";
                    }
                    case 5: {
                        return "Return code";
                    }
                    case 6: {
                        return "Size";
                    }
                    default: {
                        return "";
                    }
                }
            }
            
            public int getRowCount() {
                return (d.this == null) ? 0 : d.this.a.size();
            }
        });
        i.a(this);
    }
    
    public void a(final n n) {
        this.a.addElement(n);
        this.tableChanged(new TableModelEvent(this.if));
        this.getSelectionModel().setSelectionInterval(this.a.size() - 1, this.a.size() - 1);
    }
}
