// 
// Decompiled by Procyon v0.5.30
// 

package wordle;

import java.util.Map;
import java.util.List;
import javax.swing.table.AbstractTableModel;

final class A extends AbstractTableModel
{
    private final /* synthetic */ List a;
    
    A(final k k, final List a) {
        this.a = a;
    }
    
    public final Object getValueAt(final int n, final int n2) {
        if (n2 == 0) {
            return this.a.get(n).getKey();
        }
        return this.a.get(n).getValue();
    }
    
    public final int getRowCount() {
        return this.a.size();
    }
    
    public final int getColumnCount() {
        return 2;
    }
    
    public final String getColumnName(final int n) {
        if (n == 0) {
            return "Word";
        }
        return "Frequency";
    }
    
    public final Class getColumnClass(final int n) {
        if (n == 0) {
            return String.class;
        }
        return Integer.class;
    }
}
