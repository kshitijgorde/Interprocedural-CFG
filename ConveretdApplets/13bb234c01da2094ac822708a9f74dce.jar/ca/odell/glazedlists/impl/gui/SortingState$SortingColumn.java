// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl.gui;

import ca.odell.glazedlists.GlazedLists;
import java.util.Comparator;
import ca.odell.glazedlists.impl.sort.TableColumnComparator;
import ca.odell.glazedlists.gui.AdvancedTableFormat;
import java.util.ArrayList;
import ca.odell.glazedlists.gui.TableFormat;
import java.util.List;

public class SortingState$SortingColumn
{
    private final int c;
    private final List d;
    private boolean e;
    private int f;
    static final /* synthetic */ boolean a;
    final /* synthetic */ SortingState b;
    
    public SortingState$SortingColumn(final SortingState b, final TableFormat tableFormat, final int c) {
        this.b = b;
        this.d = new ArrayList(1);
        this.e = false;
        this.f = -1;
        this.c = c;
        if (tableFormat instanceof AdvancedTableFormat) {
            final Comparator c2 = ((AdvancedTableFormat)tableFormat).c(c);
            if (c2 != null) {
                this.d.add(new TableColumnComparator(tableFormat, c, c2));
            }
        }
        else {
            this.d.add(new TableColumnComparator(tableFormat, c));
        }
    }
    
    public void a() {
        this.e = false;
        this.f = -1;
    }
    
    public int b() {
        return this.c;
    }
    
    public void a(final int f) {
        if (!SortingState$SortingColumn.a && f >= this.d.size()) {
            throw new AssertionError();
        }
        this.f = f;
    }
    
    public int c() {
        return this.f;
    }
    
    public List d() {
        return this.d;
    }
    
    public Comparator e() {
        if (this.f == -1) {
            return null;
        }
        Comparator a = this.d.get(this.c());
        if (this.f()) {
            a = GlazedLists.a(a);
        }
        return a;
    }
    
    public boolean f() {
        return this.e;
    }
    
    public void a(final boolean e) {
        this.e = e;
    }
    
    public int g() {
        if (this.f == -1) {
            return 0;
        }
        final boolean b = !this.b.b.isEmpty() && this.b.b.get(0) == this;
        final boolean b2 = this.c() == 0;
        if (b) {
            if (!this.f()) {
                if (b2) {
                    return 1;
                }
                return 3;
            }
            else {
                if (b2) {
                    return 2;
                }
                return 4;
            }
        }
        else if (!this.f()) {
            if (b2) {
                return 5;
            }
            return 7;
        }
        else {
            if (b2) {
                return 6;
            }
            return 8;
        }
    }
    
    static {
        a = !SortingState.class.desiredAssertionStatus();
    }
}
