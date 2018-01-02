// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.collections;

public class LongIntTreeMap extends AbstractTreeMap implements LongIntSortedMap
{
    private LongEntry dummy;
    
    public LongIntTreeMap() {
        this(null, false);
    }
    
    public LongIntTreeMap(final boolean b) {
        this(null, b);
    }
    
    public LongIntTreeMap(final LiteralComparator literalComparator) {
        this(literalComparator, false);
    }
    
    public LongIntTreeMap(final LiteralComparator literalComparator, final boolean b) {
        super(literalComparator, b);
        this.dummy = new LongEntry(Long.MIN_VALUE, Integer.MAX_VALUE, LongIntTreeMap.NIL, 0);
    }
    
    public void clear() {
        ++this.modCount;
        this.size = 0;
        this.root = LongIntTreeMap.NIL;
    }
    
    public boolean containsKey(final long n) {
        return this.find(n, 0) != LongIntTreeMap.NIL;
    }
    
    public int get(final long n) {
        final Entry find = this.find(n, 0);
        return (find == LongIntTreeMap.NIL) ? Integer.MIN_VALUE : find.val;
    }
    
    public int put(final long key, final int value) {
        Entry entry = this.root;
        this.lastOrder = 0;
        if (entry == LongIntTreeMap.NIL) {
            this.incrementSize(true);
            this.root = new LongEntry(key, value, LongIntTreeMap.NIL, this.lastOrder);
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
                if (entry.left == LongIntTreeMap.NIL) {
                    this.incrementSize(this.lastOrder == 0);
                    this.fixUpInsert(entry.left = new LongEntry(key, value, entry, this.lastOrder));
                    return Integer.MIN_VALUE;
                }
                entry = entry.left;
            }
            else {
                if (entry.right == LongIntTreeMap.NIL) {
                    this.incrementSize(this.lastOrder == 0);
                    this.fixUpInsert(entry.right = new LongEntry(key, value, entry, this.lastOrder));
                    return Integer.MIN_VALUE;
                }
                entry = entry.right;
            }
        }
    }
    
    public int remove(final long n) {
        Entry entry;
        if (this.allowDuplicates) {
            entry = this.findPredecessor(n, Integer.MAX_VALUE);
        }
        else {
            entry = this.find(n, 0);
        }
        if (entry == LongIntTreeMap.NIL) {
            return Integer.MIN_VALUE;
        }
        final int val = entry.val;
        this.remove(entry);
        return val;
    }
    
    public int remove(final long n, final int n2) {
        Entry entry = this.findCeiling(n, 0);
        if (entry != LongIntTreeMap.NIL && entry.getLongKey() != n) {
            entry = this.successor(entry);
        }
        if (entry == LongIntTreeMap.NIL || entry.getLongKey() != n) {
            return Integer.MIN_VALUE;
        }
        while (entry.val != n2 && entry != LongIntTreeMap.NIL) {
            entry = this.successor(entry);
        }
        if (entry == LongIntTreeMap.NIL) {
            return Integer.MIN_VALUE;
        }
        this.remove(entry);
        return n2;
    }
    
    public long firstKey() {
        return this.minimum(this.root).getLongKey();
    }
    
    public long lastKey() {
        return this.maximum(this.root).getLongKey();
    }
    
    public LiteralIterator keyIterator() {
        return new KeyIterator();
    }
    
    public LiteralIterator keyRangeIterator(final long n, final boolean b, final long n2, final boolean b2) {
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
    
    public IntIterator valueRangeIterator(final long n, final boolean b, final long n2, final boolean b2) {
        return new ValueIterator(this, (EntryIterator)this.keyRangeIterator(n, b, n2, b2));
    }
    
    protected int compare(final Entry entry, final Entry entry2) {
        int compare = this.cmp.compare(entry.getLongKey(), entry2.getLongKey());
        if (this.allowDuplicates && compare == 0) {
            compare = ((entry.order < entry2.order) ? -1 : ((entry.order > entry2.order) ? 1 : 0));
            this.lastOrder = 1 + ((compare < 0) ? entry.order : entry2.order);
        }
        return compare;
    }
    
    private Entry find(final long key, final int order) {
        this.dummy.key = key;
        this.dummy.order = order;
        return this.find(this.dummy);
    }
    
    private Entry findPredecessor(final long key, final int order) {
        this.dummy.key = key;
        this.dummy.order = order;
        return this.findPredecessor(this.dummy);
    }
    
    private Entry findCeiling(final long key, final int order) {
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
        
        public boolean isLongSupported() {
            return true;
        }
        
        public long nextLong() {
            return this.nextEntry().getLongKey();
        }
    }
    
    static class LongEntry extends Entry
    {
        long key;
        
        public LongEntry(final long key, final int n) {
            super(n);
            this.key = key;
        }
        
        public LongEntry(final long key, final int n, final Entry entry, final int n2) {
            super(n, entry, n2);
            this.key = key;
        }
        
        public long getLongKey() {
            return this.key;
        }
        
        public Object getKey() {
            return new Long(this.key);
        }
        
        public boolean keyEquals(final Entry entry) {
            return entry instanceof LongEntry && this.key == ((LongEntry)entry).key;
        }
        
        public boolean equals(final Object o) {
            if (!(o instanceof LongEntry)) {
                return false;
            }
            final LongEntry longEntry = (LongEntry)o;
            return this.key == longEntry.key && this.val == longEntry.val;
        }
        
        public int hashCode() {
            return (int)(this.key ^ this.key >>> 32) ^ this.val ^ this.order;
        }
        
        public String toString() {
            return this.key + "=" + this.val;
        }
        
        public void copyFields(final Entry entry) {
            super.copyFields(entry);
            this.key = entry.getLongKey();
        }
    }
}
