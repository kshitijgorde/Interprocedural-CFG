// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.collections;

public class IntIntTreeMap extends AbstractTreeMap implements IntIntSortedMap
{
    private IntEntry dummy;
    
    public IntIntTreeMap() {
        this(null, false);
    }
    
    public IntIntTreeMap(final boolean b) {
        this(null, b);
    }
    
    public IntIntTreeMap(final LiteralComparator literalComparator) {
        this(literalComparator, false);
    }
    
    public IntIntTreeMap(final LiteralComparator literalComparator, final boolean b) {
        super(literalComparator, b);
        this.dummy = new IntEntry(Integer.MIN_VALUE, Integer.MAX_VALUE, IntIntTreeMap.NIL, 0);
    }
    
    public void clear() {
        ++this.modCount;
        this.size = 0;
        this.root = IntIntTreeMap.NIL;
    }
    
    public boolean containsKey(final int n) {
        return this.find(n, 0) != IntIntTreeMap.NIL;
    }
    
    public int get(final int n) {
        final Entry find = this.find(n, 0);
        return (find == IntIntTreeMap.NIL) ? Integer.MIN_VALUE : find.val;
    }
    
    public int put(final int key, final int value) {
        Entry entry = this.root;
        this.lastOrder = 0;
        if (entry == IntIntTreeMap.NIL) {
            this.incrementSize(true);
            this.root = new IntEntry(key, value, IntIntTreeMap.NIL, this.lastOrder);
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
                if (entry.left == IntIntTreeMap.NIL) {
                    this.incrementSize(this.lastOrder == 0);
                    this.fixUpInsert(entry.left = new IntEntry(key, value, entry, this.lastOrder));
                    return Integer.MIN_VALUE;
                }
                entry = entry.left;
            }
            else {
                if (entry.right == IntIntTreeMap.NIL) {
                    this.incrementSize(this.lastOrder == 0);
                    this.fixUpInsert(entry.right = new IntEntry(key, value, entry, this.lastOrder));
                    return Integer.MIN_VALUE;
                }
                entry = entry.right;
            }
        }
    }
    
    public int remove(final int n) {
        Entry entry;
        if (this.allowDuplicates) {
            entry = this.findPredecessor(n, Integer.MAX_VALUE);
        }
        else {
            entry = this.find(n, 0);
        }
        if (entry == IntIntTreeMap.NIL) {
            return Integer.MIN_VALUE;
        }
        final int val = entry.val;
        this.remove(entry);
        return val;
    }
    
    public int remove(final int n, final int n2) {
        Entry entry = this.findCeiling(n, 0);
        if (entry != IntIntTreeMap.NIL && entry.getIntKey() != n) {
            entry = this.successor(entry);
        }
        if (entry == IntIntTreeMap.NIL || entry.getIntKey() != n) {
            return Integer.MIN_VALUE;
        }
        while (entry.val != n2 && entry != IntIntTreeMap.NIL) {
            entry = this.successor(entry);
        }
        if (entry == IntIntTreeMap.NIL) {
            return Integer.MIN_VALUE;
        }
        this.remove(entry);
        return n2;
    }
    
    public int getLast(final int n) {
        final Entry predecessor = this.findPredecessor(n, Integer.MAX_VALUE);
        return (predecessor == IntIntTreeMap.NIL || ((IntEntry)predecessor).key != n) ? Integer.MIN_VALUE : predecessor.val;
    }
    
    public int getPreviousValue(final int n, final int n2) {
        return this.predecessor(this.find(n, n2)).val;
    }
    
    public int getNextValue(final int n, final int n2) {
        return this.successor(this.find(n, n2)).val;
    }
    
    public int firstKey() {
        return this.minimum(this.root).getIntKey();
    }
    
    public int lastKey() {
        return this.maximum(this.root).getIntKey();
    }
    
    public LiteralIterator keyIterator() {
        return new KeyIterator();
    }
    
    public LiteralIterator keyRangeIterator(final int n, final boolean b, final int n2, final boolean b2) {
        Entry entry;
        Entry entry2;
        if (this.cmp.compare(n, n2) <= 0) {
            entry = this.findCeiling(n, b ? 0 : Integer.MAX_VALUE);
            entry2 = this.findCeiling(n2, b2 ? Integer.MAX_VALUE : 0);
        }
        else {
            entry = this.predecessor(this.findCeiling(n, b ? Integer.MAX_VALUE : 0));
            entry2 = this.predecessor(this.findCeiling(n2, b2 ? 0 : Integer.MAX_VALUE));
        }
        return new KeyIterator(entry, entry2);
    }
    
    public IntIterator valueRangeIterator(final int n, final boolean b, final int n2, final boolean b2) {
        return new ValueIterator(this, (EntryIterator)this.keyRangeIterator(n, b, n2, b2));
    }
    
    protected int compare(final Entry entry, final Entry entry2) {
        int compare = this.cmp.compare(entry.getIntKey(), entry2.getIntKey());
        if (this.allowDuplicates && compare == 0) {
            compare = ((entry.order < entry2.order) ? -1 : ((entry.order > entry2.order) ? 1 : 0));
            this.lastOrder = 1 + ((compare < 0) ? entry.order : entry2.order);
        }
        return compare;
    }
    
    private Entry find(final int key, final int order) {
        this.dummy.key = key;
        this.dummy.order = order;
        return this.find(this.dummy);
    }
    
    private Entry findPredecessor(final int key, final int order) {
        this.dummy.key = key;
        this.dummy.order = order;
        return this.findPredecessor(this.dummy);
    }
    
    private Entry findCeiling(final int key, final int order) {
        this.dummy.key = key;
        this.dummy.order = order;
        return this.findCeiling(this.dummy);
    }
    
    private class KeyIterator extends AbstractTreeMap.KeyIterator
    {
        public KeyIterator() {
        }
        
        public KeyIterator(final Entry entry, final Entry entry2) {
            super(entry, entry2);
        }
        
        public boolean isIntSupported() {
            return true;
        }
        
        public int nextInt() {
            return this.nextEntry().getIntKey();
        }
    }
    
    static class IntEntry extends Entry
    {
        int key;
        
        public IntEntry(final int key, final int n) {
            super(n);
            this.key = key;
        }
        
        public IntEntry(final int key, final int n, final Entry entry, final int n2) {
            super(n, entry, n2);
            this.key = key;
        }
        
        public int getIntKey() {
            return this.key;
        }
        
        public Object getKey() {
            return new Integer(this.key);
        }
        
        public boolean keyEquals(final Entry entry) {
            return entry instanceof IntEntry && this.key == ((IntEntry)entry).key;
        }
        
        public boolean equals(final Object o) {
            if (!(o instanceof IntEntry)) {
                return false;
            }
            final IntEntry intEntry = (IntEntry)o;
            return this.key == intEntry.key && this.val == intEntry.val;
        }
        
        public int hashCode() {
            return this.key ^ this.val ^ this.order;
        }
        
        public String toString() {
            return this.key + "=" + this.val;
        }
        
        public void copyFields(final Entry entry) {
            super.copyFields(entry);
            this.key = entry.getIntKey();
        }
    }
}
