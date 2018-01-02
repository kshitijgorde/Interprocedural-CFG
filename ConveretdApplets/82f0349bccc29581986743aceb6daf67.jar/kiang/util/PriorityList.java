// 
// Decompiled by Procyon v0.5.30
// 

package kiang.util;

import java.util.ListIterator;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.AbstractCollection;

public class PriorityList extends AbstractCollection
{
    private LinkedList list;
    private Comparator comparator;
    private boolean allowsDuplicates;
    private boolean ascendingOrder;
    
    public PriorityList() {
        this(true, true);
    }
    
    public PriorityList(final Comparator comparator) {
        this(comparator, true, true);
    }
    
    public PriorityList(final boolean allowsDuplicates, final boolean ascendingOrder) {
        this(new ComparableComparator(), allowsDuplicates, ascendingOrder);
    }
    
    public PriorityList(final Comparator comparator, final boolean allowsDuplicates, final boolean ascendingOrder) {
        this.list = new LinkedList();
        this.comparator = comparator;
        this.allowsDuplicates = allowsDuplicates;
        this.ascendingOrder = ascendingOrder;
    }
    
    public Comparator getComparator() {
        if (this.comparator instanceof ComparableComparator) {
            return null;
        }
        return this.comparator;
    }
    
    public void setComparator(final Comparator comparator) {
        this.comparator = comparator;
        this.resort();
    }
    
    public boolean getOrder() {
        return this.ascendingOrder;
    }
    
    public void setOrder(final boolean ascendingOrder) {
        final boolean previousOrder = this.ascendingOrder;
        this.ascendingOrder = ascendingOrder;
        if (previousOrder != this.ascendingOrder) {
            this.resort();
        }
    }
    
    private void resort() {
        Collections.sort((List<Object>)this.list, this.comparator);
        if (!this.ascendingOrder) {
            Collections.reverse(this.list);
        }
    }
    
    public void setAllowsDuplicates(final boolean allowsDuplicates) {
        if (this.allowsDuplicates && !allowsDuplicates) {
            this.removeDuplicates();
        }
        this.allowsDuplicates = allowsDuplicates;
    }
    
    public boolean getAllowsDuplicates() {
        return this.allowsDuplicates;
    }
    
    private void removeDuplicates() {
        Object previous = null;
        final Iterator iter = this.iterator();
        while (iter.hasNext()) {
            final Object next = iter.next();
            if (previous != null && previous.equals(next)) {
                iter.remove();
            }
            previous = next;
        }
    }
    
    public boolean add(final Object o) {
        final ListIterator listIter = this.list.listIterator();
        while (listIter.hasNext()) {
            final Object next = listIter.next();
            final int compareVal = this.compare(o, next);
            if (compareVal < 0) {
                listIter.previous();
                break;
            }
            if (compareVal != 0) {
                continue;
            }
            if (this.allowsDuplicates) {
                break;
            }
            return false;
        }
        listIter.add(o);
        return true;
    }
    
    private int compare(final Object o1, final Object o2) {
        final int compareVal = this.comparator.compare(o1, o2);
        if ((this.ascendingOrder && compareVal < 0) || (!this.ascendingOrder && compareVal > 0)) {
            return -1;
        }
        if (compareVal != 0) {
            return 1;
        }
        return 0;
    }
    
    public Iterator iterator() {
        return this.list.iterator();
    }
    
    public int size() {
        return this.list.size();
    }
    
    public Object getFirst() {
        return this.list.getFirst();
    }
    
    public Object removeFirst() {
        return this.list.removeFirst();
    }
    
    public Object getLast() {
        return this.list.getLast();
    }
    
    public Object removeLast() {
        return this.list.removeLast();
    }
}
