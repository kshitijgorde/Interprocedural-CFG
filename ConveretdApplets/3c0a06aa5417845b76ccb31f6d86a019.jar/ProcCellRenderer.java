import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.JProgressBar;

// 
// Decompiled by Procyon v0.5.30
// 

public class ProcCellRenderer extends JProgressBar implements TableCellRenderer
{
    private static final String IDLE_STRING = "Idle & Switch = ";
    
    public ProcCellRenderer() {
        super(0);
        this.setBorderPainted(false);
        this.setStringPainted(true);
    }
    
    public ProcCellRenderer(final int max) {
        super(0, 0, max);
        this.setBorderPainted(false);
        this.setStringPainted(true);
    }
    
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
        int n = 0;
        if (!(value instanceof Number)) {
            String str;
            if (value instanceof String) {
                str = (String)value;
            }
            else {
                str = value.toString();
            }
            try {
                n = Integer.valueOf(str);
            }
            catch (NumberFormatException ex) {}
        }
        else {
            n = ((Number)value).intValue();
        }
        this.setForeground(isSelected ? Color.ORANGE : new Color(0, 0, 128));
        this.setBackground(Color.LIGHT_GRAY);
        this.setValue(n);
        this.setString(((n < 0) ? ("Idle & Switch = " + -n) : ("" + n)) + " ms");
        return this;
    }
}
