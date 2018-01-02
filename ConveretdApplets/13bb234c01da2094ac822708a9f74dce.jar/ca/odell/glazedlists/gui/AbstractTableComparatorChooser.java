// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.gui;

import ca.odell.glazedlists.impl.gui.MouseKeyboardSortingStrategy;
import ca.odell.glazedlists.impl.gui.MouseOnlySortingStrategyWithUndo;
import ca.odell.glazedlists.impl.gui.MouseOnlySortingStrategy;
import ca.odell.glazedlists.impl.gui.SortingState$SortingColumn;
import java.beans.PropertyChangeListener;
import ca.odell.glazedlists.impl.gui.SortingState;
import java.util.Comparator;
import ca.odell.glazedlists.SortedList;

public abstract class AbstractTableComparatorChooser
{
    public static final Object a;
    public static final Object b;
    public static final Object c;
    public static final Object d;
    protected SortedList e;
    private TableFormat h;
    protected Comparator f;
    protected SortingState g;
    
    protected AbstractTableComparatorChooser(final SortedList e, final TableFormat tableFormat) {
        this.f = null;
        this.e = e;
        this.g = this.a();
        this.a(tableFormat);
        this.g.a(new AbstractTableComparatorChooser$SortingStateListener(this, null));
    }
    
    protected SortingState a() {
        return new SortingState(this);
    }
    
    protected void b() {
        final Comparator b = this.g.b();
        this.e.b().a().a();
        try {
            this.f = b;
            this.e.a(b);
        }
        finally {
            this.e.b().a().b();
        }
    }
    
    protected void a(final TableFormat h) {
        this.h = h;
        this.g.a(h);
    }
    
    protected void a(final Comparator f) {
        this.f = f;
        this.g.a(f);
    }
    
    protected int a(final int n) {
        return this.g.e().get(n).g();
    }
    
    public String toString() {
        return this.g.toString();
    }
    
    static {
        a = new MouseOnlySortingStrategy(false);
        b = new MouseOnlySortingStrategy(true);
        c = new MouseOnlySortingStrategyWithUndo();
        d = new MouseKeyboardSortingStrategy();
    }
}
