// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl.gui;

import java.util.List;

public class MouseKeyboardSortingStrategy implements SortingStrategy
{
    public void a(final SortingState sortingState, final int n, final int n2, final boolean b, final boolean b2) {
        final SortingState$SortingColumn sortingState$SortingColumn = sortingState.e().get(n);
        if (sortingState$SortingColumn.d().isEmpty()) {
            return;
        }
        final List f = sortingState.f();
        final int c = sortingState$SortingColumn.c();
        int n3;
        if (c == -1) {
            n3 = 0;
        }
        else {
            n3 = (sortingState$SortingColumn.f() ? 2 : 1);
        }
        final boolean b3 = c + 1 < sortingState$SortingColumn.d().size();
        final boolean b4 = b ? (n3 == 1) : (n3 == 2);
        int n4;
        int n5;
        if (b3 && b4) {
            n4 = (c + 1) % sortingState$SortingColumn.d().size();
            n5 = ((n3 == 1) ? 2 : 1);
        }
        else {
            n4 = ((c != -1) ? c : 0);
            n5 = (b ? (n3 + 2) : (n3 + 1)) % 3;
        }
        if (!b2) {
            sortingState.d();
        }
        if (n5 == 0) {
            sortingState$SortingColumn.a();
            f.remove(sortingState$SortingColumn);
        }
        else {
            sortingState$SortingColumn.a(n4);
            sortingState$SortingColumn.a(n5 == 2);
            if (!f.contains(sortingState$SortingColumn)) {
                f.add(sortingState$SortingColumn);
            }
        }
        sortingState.a();
    }
}
