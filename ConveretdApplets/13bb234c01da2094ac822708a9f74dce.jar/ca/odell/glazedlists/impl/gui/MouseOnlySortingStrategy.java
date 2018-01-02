// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl.gui;

import java.util.Iterator;
import java.util.List;

public final class MouseOnlySortingStrategy implements SortingStrategy
{
    private final boolean a;
    
    public MouseOnlySortingStrategy(final boolean a) {
        this.a = a;
    }
    
    public void a(final SortingState sortingState, final int n, final int n2, final boolean b, final boolean b2) {
        final SortingState$SortingColumn sortingState$SortingColumn = sortingState.e().get(n);
        if (sortingState$SortingColumn.d().isEmpty()) {
            return;
        }
        final List f = sortingState.f();
        if (n2 == 2) {
            final Iterator<SortingState$SortingColumn> iterator = f.iterator();
            while (iterator.hasNext()) {
                iterator.next().a();
            }
            f.clear();
        }
        else if (!this.a) {
            for (final SortingState$SortingColumn sortingState$SortingColumn2 : f) {
                if (sortingState$SortingColumn2 != sortingState$SortingColumn) {
                    sortingState$SortingColumn2.a();
                }
            }
            f.clear();
        }
        final int n3 = 1 + sortingState$SortingColumn.c() * 2 + (sortingState$SortingColumn.f() ? 1 : 0);
        sortingState$SortingColumn.a(n3 / 2 % sortingState$SortingColumn.d().size());
        sortingState$SortingColumn.a(n3 % 2 == 1);
        if (!f.contains(sortingState$SortingColumn)) {
            f.add(sortingState$SortingColumn);
        }
        sortingState.a();
    }
}
