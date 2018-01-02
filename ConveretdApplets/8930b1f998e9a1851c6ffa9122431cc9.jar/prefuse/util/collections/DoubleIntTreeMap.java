// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.collections;

public class DoubleIntTreeMap extends AbstractTreeMap implements DoubleIntSortedMap
{
    private DoubleEntry dummy;
    
    public DoubleIntTreeMap() {
        this(null, false);
    }
    
    public DoubleIntTreeMap(final boolean b) {
        this(null, b);
    }
    
    public DoubleIntTreeMap(final LiteralComparator literalComparator) {
        this(literalComparator, false);
    }
    
    public DoubleIntTreeMap(final LiteralComparator literalComparator, final boolean b) {
        super(literalComparator, b);
        this.dummy = new DoubleEntry(Double.MIN_VALUE, Integer.MAX_VALUE, DoubleIntTreeMap.NIL, 0);
    }
    
    public void clear() {
        ++this.modCount;
        this.size = 0;
        this.root = DoubleIntTreeMap.NIL;
    }
    
    public boolean containsKey(final double n) {
        return this.find(n, 0) != DoubleIntTreeMap.NIL;
    }
    
    public int get(final double n) {
        final Entry find = this.find(n, 0);
        return (find == DoubleIntTreeMap.NIL) ? Integer.MIN_VALUE : find.val;
    }
    
    public int put(final double key, final int value) {
        Entry entry = this.root;
        this.lastOrder = 0;
        if (entry == DoubleIntTreeMap.NIL) {
            this.incrementSize(true);
            this.root = new DoubleEntry(key, value, DoubleIntTreeMap.NIL, this.lastOrder);
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
                if (entry.left == DoubleIntTreeMap.NIL) {
                    this.incrementSize(this.lastOrder == 0);
                    this.fixUpInsert(entry.left = new DoubleEntry(key, value, entry, this.lastOrder));
                    return Integer.MIN_VALUE;
                }
                entry = entry.left;
            }
            else {
                if (entry.right == DoubleIntTreeMap.NIL) {
                    this.incrementSize(this.lastOrder == 0);
                    this.fixUpInsert(entry.right = new DoubleEntry(key, value, entry, this.lastOrder));
                    return Integer.MIN_VALUE;
                }
                entry = entry.right;
            }
        }
    }
    
    public int remove(final double n) {
        Entry entry;
        if (this.allowDuplicates) {
            entry = this.findPredecessor(n, Integer.MAX_VALUE);
        }
        else {
            entry = this.find(n, 0);
        }
        if (entry == DoubleIntTreeMap.NIL) {
            return Integer.MIN_VALUE;
        }
        final int val = entry.val;
        this.remove(entry);
        return val;
    }
    
    public int remove(final double n, final int n2) {
        Entry entry = this.findCeiling(n, 0);
        if (entry != DoubleIntTreeMap.NIL && entry.getDoubleKey() != n) {
            entry = this.successor(entry);
        }
        if (entry == DoubleIntTreeMap.NIL || entry.getDoubleKey() != n) {
            return Integer.MIN_VALUE;
        }
        while (entry.val != n2 && entry != DoubleIntTreeMap.NIL) {
            entry = this.successor(entry);
        }
        if (entry == DoubleIntTreeMap.NIL) {
            return Integer.MIN_VALUE;
        }
        this.remove(entry);
        return n2;
    }
    
    public double firstKey() {
        return this.minimum(this.root).getDoubleKey();
    }
    
    public double lastKey() {
        return this.maximum(this.root).getDoubleKey();
    }
    
    public LiteralIterator keyIterator() {
        return new KeyIterator();
    }
    
    public LiteralIterator keyRangeIterator(final double n, final boolean b, final double n2, final boolean b2) {
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
    
    public IntIterator valueRangeIterator(final double n, final boolean b, final double n2, final boolean b2) {
        return new ValueIterator(this, (EntryIterator)this.keyRangeIterator(n, b, n2, b2));
    }
    
    protected int compare(final Entry entry, final Entry entry2) {
        int compare = this.cmp.compare(entry.getDoubleKey(), entry2.getDoubleKey());
        if (this.allowDuplicates && compare == 0) {
            compare = ((entry.order < entry2.order) ? -1 : ((entry.order > entry2.order) ? 1 : 0));
            this.lastOrder = 1 + ((compare < 0) ? entry.order : entry2.order);
        }
        return compare;
    }
    
    private Entry find(final double key, final int order) {
        this.dummy.key = key;
        this.dummy.order = order;
        return this.find(this.dummy);
    }
    
    private Entry findPredecessor(final double key, final int order) {
        this.dummy.key = key;
        this.dummy.order = order;
        return this.findPredecessor(this.dummy);
    }
    
    private Entry findCeiling(final double key, final int order) {
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
        
        public boolean isDoubleSupported() {
            return true;
        }
        
        public double nextDouble() {
            return this.nextEntry().getDoubleKey();
        }
    }
    
    static class DoubleEntry extends Entry
    {
        double key;
        
        public DoubleEntry(final double key, final int n) {
            super(n);
            this.key = key;
        }
        
        public DoubleEntry(final double key, final int n, final Entry entry, final int n2) {
            super(n, entry, n2);
            this.key = key;
        }
        
        public double getDoubleKey() {
            return this.key;
        }
        
        public Object getKey() {
            return new Double(this.key);
        }
        
        public boolean keyEquals(final Entry entry) {
            return entry instanceof DoubleEntry && this.key == ((DoubleEntry)entry).key;
        }
        
        public boolean equals(final Object o) {
            if (!(o instanceof DoubleEntry)) {
                return false;
            }
            final DoubleEntry doubleEntry = (DoubleEntry)o;
            return this.key == doubleEntry.key && this.val == doubleEntry.val;
        }
        
        public int hashCode() {
            final long doubleToLongBits = Double.doubleToLongBits(this.key);
            return (int)(doubleToLongBits ^ doubleToLongBits >>> 32) ^ this.val ^ this.order;
        }
        
        public String toString() {
            return this.key + "=" + this.val;
        }
        
        public void copyFields(final Entry entry) {
            super.copyFields(entry);
            this.key = entry.getDoubleKey();
        }
    }
}
