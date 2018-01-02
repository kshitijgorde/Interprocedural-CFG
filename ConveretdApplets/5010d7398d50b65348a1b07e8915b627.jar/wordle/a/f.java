// 
// Decompiled by Procyon v0.5.30
// 

package wordle.a;

import java.util.Arrays;
import javax.swing.Icon;
import javax.swing.table.TableCellRenderer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.event.TableModelListener;
import java.awt.event.MouseListener;
import javax.swing.table.JTableHeader;
import java.util.Comparator;
import javax.swing.table.TableModel;
import javax.swing.table.AbstractTableModel;
import java.util.Iterator;

final class f implements Comparable
{
    private int a;
    private /* synthetic */ i b;
    
    public f(final i b, final int a) {
        this.b = b;
        this.a = a;
    }
    
    public final boolean equals(final Object o) {
        return super.equals(o);
    }
    
    public final int hashCode() {
        return 31 * this.a;
    }
    
    public final int compareTo(final Object o) {
        final int a = this.a;
        final int a2 = ((f)o).a;
        final Iterator<d> iterator = this.b.k.iterator();
        while (iterator.hasNext()) {
            final d d;
            final int a3 = (d = iterator.next()).a;
            final Object value = this.b.a.getValueAt(a, a3);
            final Object value2 = this.b.a.getValueAt(a2, a3);
            int compare;
            if (value == null && value2 == null) {
                compare = 0;
            }
            else if (value == null) {
                compare = -1;
            }
            else if (value2 == null) {
                compare = 1;
            }
            else {
                compare = this.b.b(a3).compare(value, value2);
            }
            if (compare != 0) {
                if (d.b == -1) {
                    return -compare;
                }
                return compare;
            }
        }
        return 0;
    }
}
