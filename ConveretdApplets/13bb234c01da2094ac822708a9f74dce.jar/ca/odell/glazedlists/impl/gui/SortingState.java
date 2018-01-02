// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl.gui;

import ca.odell.glazedlists.gui.TableFormat;
import ca.odell.glazedlists.impl.sort.ReverseComparator;
import java.util.Arrays;
import ca.odell.glazedlists.impl.sort.ComparatorChain;
import java.util.Collections;
import java.util.Iterator;
import ca.odell.glazedlists.GlazedLists;
import java.util.Comparator;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.beans.PropertyChangeSupport;
import ca.odell.glazedlists.gui.AbstractTableComparatorChooser;
import java.util.List;
import java.util.regex.Pattern;

public class SortingState
{
    private static final Pattern c;
    protected List a;
    protected List b;
    private final AbstractTableComparatorChooser d;
    private final PropertyChangeSupport e;
    
    public SortingState(final AbstractTableComparatorChooser d) {
        this.b = new ArrayList(2);
        this.e = new PropertyChangeSupport(this);
        this.d = d;
    }
    
    public void a() {
        this.e.firePropertyChange("comparator", null, null);
    }
    
    public void a(final PropertyChangeListener propertyChangeListener) {
        this.e.addPropertyChangeListener(propertyChangeListener);
    }
    
    public Comparator b() {
        if (this.b.isEmpty()) {
            return null;
        }
        final ArrayList<Comparator> list = new ArrayList<Comparator>(this.b.size());
        final Iterator<SortingState$SortingColumn> iterator = this.b.iterator();
        while (iterator.hasNext()) {
            final Comparator e = iterator.next().e();
            if (e == null) {
                throw new IllegalStateException();
            }
            list.add(e);
        }
        return GlazedLists.a(list);
    }
    
    public List c() {
        final ArrayList<Integer> list = new ArrayList<Integer>();
        final List f = this.f();
        for (int i = 0; i < f.size(); ++i) {
            list.add(new Integer(f.get(i).b()));
        }
        return list;
    }
    
    public void a(final Comparator comparator) {
        this.d();
        Object o;
        if (comparator == null) {
            o = Collections.EMPTY_LIST;
        }
        else if (comparator instanceof ComparatorChain) {
            o = Arrays.asList((Comparator[])((ComparatorChain)comparator).a());
        }
        else {
            o = Collections.singletonList(comparator);
        }
        for (Comparator a : o) {
            boolean b = false;
            if (a instanceof ReverseComparator) {
                b = true;
                a = ((ReverseComparator)a).a();
            }
            for (int i = 0; i < this.a.size(); ++i) {
                if (!this.b.contains(this.a.get(i))) {
                    final int index = this.a.get(i).d().indexOf(a);
                    if (index != -1) {
                        final SortingState$SortingColumn sortingState$SortingColumn = this.a.get(i);
                        sortingState$SortingColumn.a(index);
                        sortingState$SortingColumn.a(b);
                        this.b.add(sortingState$SortingColumn);
                    }
                }
            }
        }
    }
    
    public void d() {
        final Iterator<SortingState$SortingColumn> iterator = this.b.iterator();
        while (iterator.hasNext()) {
            iterator.next().a();
        }
        this.b.clear();
    }
    
    public void a(final TableFormat tableFormat) {
        final int a = tableFormat.a();
        this.a = new ArrayList(a);
        for (int i = 0; i < a; ++i) {
            this.a.add(this.a(tableFormat, i));
        }
        this.b.clear();
    }
    
    protected SortingState$SortingColumn a(final TableFormat tableFormat, final int n) {
        return new SortingState$SortingColumn(this, tableFormat, n);
    }
    
    public List e() {
        return this.a;
    }
    
    public List f() {
        return this.b;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        final Iterator<Integer> iterator = (Iterator<Integer>)this.c().iterator();
        while (iterator.hasNext()) {
            final int intValue = iterator.next();
            final SortingState$SortingColumn sortingState$SortingColumn = this.e().get(intValue);
            sb.append("column ");
            sb.append(intValue);
            final int c = sortingState$SortingColumn.c();
            if (c != 0) {
                sb.append(" comparator ");
                sb.append(c);
            }
            if (sortingState$SortingColumn.f()) {
                sb.append(" reversed");
            }
            if (iterator.hasNext()) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
    
    static {
        c = Pattern.compile("^\\s*column\\s+(\\d+)(\\s+comparator\\s+(\\d+))?(\\s+(reversed))?\\s*$", 2);
    }
}
