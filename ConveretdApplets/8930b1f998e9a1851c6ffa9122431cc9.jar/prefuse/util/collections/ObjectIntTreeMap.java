// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.collections;

import java.util.Collections;
import java.util.Iterator;
import java.util.Comparator;

public class ObjectIntTreeMap extends AbstractTreeMap implements ObjectIntSortedMap
{
    private ObjectEntry dummy;
    private Comparator cmp;
    
    public ObjectIntTreeMap() {
        this((Comparator)null, false);
    }
    
    public ObjectIntTreeMap(final boolean b) {
        this((Comparator)null, b);
    }
    
    public ObjectIntTreeMap(final Comparator comparator) {
        this(comparator, false);
    }
    
    public ObjectIntTreeMap(final Comparator comparator, final boolean b) {
        super(null, b);
        this.dummy = new ObjectEntry(null, Integer.MIN_VALUE, ObjectIntTreeMap.NIL, 0);
        this.cmp = null;
        this.cmp = ((comparator == null) ? super.comparator() : comparator);
    }
    
    public Comparator comparator() {
        return this.cmp;
    }
    
    public boolean containsKey(final Object o) {
        return this.find(o, 0) != ObjectIntTreeMap.NIL;
    }
    
    public int get(final Object o) {
        final Entry find = this.find(o, 0);
        return (find == ObjectIntTreeMap.NIL) ? Integer.MIN_VALUE : find.val;
    }
    
    public int put(final Object key, final int value) {
        Entry entry = this.root;
        this.lastOrder = 0;
        if (entry == ObjectIntTreeMap.NIL) {
            this.incrementSize(true);
            this.root = new ObjectEntry(key, value, ObjectIntTreeMap.NIL, this.lastOrder);
            return Integer.MIN_VALUE;
        }
        this.dummy.key = key;
        this.dummy.order = Integer.MAX_VALUE;
        while (true) {
            final int compare = this.compare(this.dummy, entry);
            if (compare == 0) {
                return entry.setValue(value);
            }
            if (compare < 0) {
                if (entry.left == ObjectIntTreeMap.NIL) {
                    this.incrementSize(this.lastOrder == 0);
                    this.fixUpInsert(entry.left = new ObjectEntry(key, value, entry, this.lastOrder));
                    return Integer.MIN_VALUE;
                }
                entry = entry.left;
            }
            else {
                if (entry.right == ObjectIntTreeMap.NIL) {
                    this.incrementSize(this.lastOrder == 0);
                    this.fixUpInsert(entry.right = new ObjectEntry(key, value, entry, this.lastOrder));
                    return Integer.MIN_VALUE;
                }
                entry = entry.right;
            }
        }
    }
    
    public int remove(final Object o) {
        Entry entry;
        if (this.allowDuplicates) {
            entry = this.findPredecessor(o, Integer.MAX_VALUE);
        }
        else {
            entry = this.find(o, 0);
        }
        if (entry == ObjectIntTreeMap.NIL) {
            return Integer.MIN_VALUE;
        }
        final int val = entry.val;
        this.remove(entry);
        return val;
    }
    
    public int remove(final Object o, final int n) {
        Entry entry = this.findCeiling(o, 0);
        if (entry != ObjectIntTreeMap.NIL && ((o == null && entry.getKey() != null) || (o != null && !entry.getKey().equals(o)))) {
            entry = this.successor(entry);
        }
        if (entry == ObjectIntTreeMap.NIL || (o == null && entry.getKey() != null) || (o != null && !entry.getKey().equals(o))) {
            return Integer.MIN_VALUE;
        }
        while (entry.val != n && entry != ObjectIntTreeMap.NIL) {
            entry = this.successor(entry);
        }
        if (entry == ObjectIntTreeMap.NIL) {
            return Integer.MIN_VALUE;
        }
        this.remove(entry);
        return n;
    }
    
    public Object firstKey() {
        return this.minimum(this.root).getKey();
    }
    
    public Object lastKey() {
        return this.maximum(this.root).getKey();
    }
    
    public Iterator keyIterator() {
        return new KeyIterator(this);
    }
    
    public Iterator keyRangeIterator(final Object o, final boolean b, final Object o2, final boolean b2) {
        if (o == o2 && (o == ObjectIntTreeMap.MIN_KEY || o == ObjectIntTreeMap.MAX_KEY)) {
            return Collections.EMPTY_LIST.iterator();
        }
        final boolean b3 = o == ObjectIntTreeMap.MIN_KEY || o2 == ObjectIntTreeMap.MAX_KEY;
        Entry entry;
        Entry entry2;
        if (o != ObjectIntTreeMap.MAX_KEY && o2 != ObjectIntTreeMap.MIN_KEY && (b3 || this.cmp.compare(o, o2) <= 0)) {
            entry = this.findCeiling(o, b ? 0 : Integer.MAX_VALUE);
            entry2 = this.findCeiling(o2, b2 ? Integer.MAX_VALUE : 0);
        }
        else {
            entry = this.predecessor(this.findCeiling(o, b ? Integer.MAX_VALUE : 0));
            entry2 = this.predecessor(this.findCeiling(o2, b2 ? 0 : Integer.MAX_VALUE));
        }
        return new KeyIterator(this, entry, entry2);
    }
    
    public IntIterator valueRangeIterator(final Object o, final boolean b, final Object o2, final boolean b2) {
        return new ValueIterator(this, (EntryIterator)this.keyRangeIterator(o, b, o2, b2));
    }
    
    protected int compare(final Entry entry, final Entry entry2) {
        final Object key = entry.getKey();
        final Object key2 = entry2.getKey();
        if (key == key2 && (key == ObjectIntTreeMap.MIN_KEY || key == ObjectIntTreeMap.MAX_KEY)) {
            return 0;
        }
        if (key == ObjectIntTreeMap.MIN_KEY || key2 == ObjectIntTreeMap.MAX_KEY) {
            return -1;
        }
        if (key == ObjectIntTreeMap.MAX_KEY || key2 == ObjectIntTreeMap.MIN_KEY) {
            return 1;
        }
        int compare = this.cmp.compare(entry.getKey(), entry2.getKey());
        if (this.allowDuplicates && compare == 0) {
            compare = ((entry.order < entry2.order) ? -1 : ((entry.order > entry2.order) ? 1 : 0));
            this.lastOrder = 1 + ((compare < 0) ? entry.order : entry2.order);
        }
        return compare;
    }
    
    private Entry find(final Object key, final int order) {
        this.dummy.key = key;
        this.dummy.order = order;
        final Entry find = this.find(this.dummy);
        this.dummy.key = null;
        return find;
    }
    
    private Entry findPredecessor(final Object key, final int order) {
        this.dummy.key = key;
        this.dummy.order = order;
        final Entry predecessor = this.findPredecessor(this.dummy);
        this.dummy.key = null;
        return predecessor;
    }
    
    private Entry findCeiling(final Object key, final int order) {
        this.dummy.key = key;
        this.dummy.order = order;
        final Entry ceiling = this.findCeiling(this.dummy);
        this.dummy.key = null;
        return ceiling;
    }
    
    static class ObjectEntry extends Entry
    {
        Object key;
        
        public ObjectEntry(final Object key, final int n) {
            super(n);
            this.key = key;
        }
        
        public ObjectEntry(final Object key, final int n, final Entry entry, final int n2) {
            super(n, entry, n2);
            this.key = key;
        }
        
        public Object getKey() {
            return this.key;
        }
        
        public void copyFields(final Entry entry) {
            super.copyFields(entry);
            this.key = entry.getKey();
        }
    }
}
