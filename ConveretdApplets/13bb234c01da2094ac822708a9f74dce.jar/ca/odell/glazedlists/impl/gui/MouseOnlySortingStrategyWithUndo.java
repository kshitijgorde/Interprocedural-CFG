// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl.gui;

import java.util.List;

public class MouseOnlySortingStrategyWithUndo implements SortingStrategy
{
    private final SortingStrategy a;
    
    public MouseOnlySortingStrategyWithUndo() {
        this.a = new MouseOnlySortingStrategy(true);
    }
    
    public void a(final SortingState sortingState, final int n, final int n2, final boolean b, final boolean b2) {
        final SortingState$SortingColumn sortingState$SortingColumn = sortingState.e().get(n);
        if (sortingState$SortingColumn.d().isEmpty()) {
            return;
        }
        final List f = sortingState.f();
        final boolean b3 = !f.isEmpty() && sortingState$SortingColumn == f.get(0);
        final boolean f2 = sortingState$SortingColumn.f();
        final boolean b4 = sortingState$SortingColumn.c() == sortingState$SortingColumn.d().size() - 1;
        if (b3 && f2 && b4) {
            sortingState.d();
            sortingState.a();
            return;
        }
        this.a.a(sortingState, n, n2, b, b2);
    }
}
