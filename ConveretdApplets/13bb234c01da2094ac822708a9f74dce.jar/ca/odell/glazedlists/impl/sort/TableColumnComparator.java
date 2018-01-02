// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl.sort;

import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.gui.TableFormat;
import java.util.Comparator;

public class TableColumnComparator implements Comparator
{
    private TableFormat a;
    private int b;
    private Comparator c;
    
    public TableColumnComparator(final TableFormat tableFormat, final int n) {
        this(tableFormat, n, GlazedLists.a());
    }
    
    public TableColumnComparator(final TableFormat a, final int b, final Comparator c) {
        this.c = null;
        this.b = b;
        this.a = a;
        this.c = c;
    }
    
    public int compare(final Object o, final Object o2) {
        final Object a = this.a.a(o, this.b);
        final Object a2 = this.a.a(o2, this.b);
        try {
            return this.c.compare(a, a2);
        }
        catch (ClassCastException ex2) {
            IllegalStateException ex;
            if (this.c == GlazedLists.a()) {
                ex = new IllegalStateException("TableComparatorChooser can not sort objects \"" + a + "\", \"" + a2 + "\" that do not implement Comparable.");
            }
            else {
                ex = new IllegalStateException("TableComparatorChooser can not sort objects \"" + a + "\", \"" + a2 + "\" using the provided Comparator.");
            }
            ex.initCause(ex2);
            throw ex;
        }
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final TableColumnComparator tableColumnComparator = (TableColumnComparator)o;
        return this.b == tableColumnComparator.b && this.c.equals(tableColumnComparator.c) && this.a.equals(tableColumnComparator.a);
    }
    
    public int hashCode() {
        return 29 * (29 * this.a.hashCode() + this.b) + this.c.hashCode();
    }
}
