import com.daysofwonder.applet.k;
import java.awt.Component;
import javax.swing.JTable;
import java.awt.Color;
import com.daysofwonder.applet.aG;
import javax.swing.table.TableCellRenderer;

// 
// Decompiled by Procyon v0.5.30
// 

public class t extends Z implements TableCellRenderer
{
    private aG a;
    private String b;
    private static Color c;
    
    public t(final int n, final aG a, final String b) {
        super(n);
        this.b = b;
        this.a = a;
    }
    
    public Component getTableCellRendererComponent(final JTable table, final Object o, final boolean b, final boolean b2, final int n, final int n2) {
        super.getTableCellRendererComponent(table, o, b, b2, n, n2);
        if (o instanceof k) {
            final k k = (k)o;
            this.setText(k.a() + (k.c() ? this.b : ""));
            if (this.a.q(k.b())) {
                this.setForeground(Color.red);
            }
            else if (this.a.r(k.b())) {
                this.setForeground(t.c);
            }
            else {
                this.setForeground(table.getForeground());
            }
        }
        else if (o instanceof Integer) {
            this.setForeground(y.d((int)o));
            this.setText(y.c(y.b((int)o)));
        }
        return this;
    }
    
    static {
        t.c = new Color(0, 22, 255);
    }
}
