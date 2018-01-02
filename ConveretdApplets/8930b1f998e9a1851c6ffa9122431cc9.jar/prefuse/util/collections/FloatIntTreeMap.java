// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.collections;

public class FloatIntTreeMap extends AbstractTreeMap implements FloatIntSortedMap
{
    private FloatEntry dummy;
    
    public FloatIntTreeMap() {
        this(null, false);
    }
    
    public FloatIntTreeMap(final boolean b) {
        this(null, b);
    }
    
    public FloatIntTreeMap(final LiteralComparator literalComparator) {
        this(literalComparator, false);
    }
    
    public FloatIntTreeMap(final LiteralComparator literalComparator, final boolean b) {
        super(literalComparator, b);
        this.dummy = new FloatEntry(Float.MIN_VALUE, Integer.MAX_VALUE, FloatIntTreeMap.NIL, 0);
    }
    
    public void clear() {
        ++this.modCount;
        this.size = 0;
        this.root = FloatIntTreeMap.NIL;
    }
    
    public boolean containsKey(final float n) {
        return this.find(n, 0) != FloatIntTreeMap.NIL;
    }
    
    public int get(final float n) {
        final Entry find = this.find(n, 0);
        return (find == FloatIntTreeMap.NIL) ? Integer.MIN_VALUE : find.val;
    }
    
    public int put(final float key, final int value) {
        Entry entry = this.root;
        this.lastOrder = 0;
        if (entry == FloatIntTreeMap.NIL) {
            this.incrementSize(true);
            this.root = new FloatEntry(key, value, FloatIntTreeMap.NIL, this.lastOrder);
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
                if (entry.left == FloatIntTreeMap.NIL) {
                    this.incrementSize(this.lastOrder == 0);
                    this.fixUpInsert(entry.left = new FloatEntry(key, value, entry, this.lastOrder));
                    return Integer.MIN_VALUE;
                }
                entry = entry.left;
            }
            else {
                if (entry.right == FloatIntTreeMap.NIL) {
                    this.incrementSize(this.lastOrder == 0);
                    this.fixUpInsert(entry.right = new FloatEntry(key, value, entry, this.lastOrder));
                    return Integer.MIN_VALUE;
                }
                entry = entry.right;
            }
        }
    }
    
    public int remove(final float n) {
        Entry entry;
        if (this.allowDuplicates) {
            entry = this.findPredecessor(n, Integer.MAX_VALUE);
        }
        else {
            entry = this.find(n, 0);
        }
        if (entry == FloatIntTreeMap.NIL) {
            return Integer.MIN_VALUE;
        }
        final int val = entry.val;
        this.remove(entry);
        return val;
    }
    
    public int remove(final float n, final int n2) {
        Entry entry = this.findCeiling(n, 0);
        if (entry != FloatIntTreeMap.NIL && entry.getFloatKey() != n) {
            entry = this.successor(entry);
        }
        if (entry == FloatIntTreeMap.NIL || entry.getFloatKey() != n) {
            return Integer.MIN_VALUE;
        }
        while (entry.val != n2 && entry != FloatIntTreeMap.NIL) {
            entry = this.successor(entry);
        }
        if (entry == FloatIntTreeMap.NIL) {
            return Integer.MIN_VALUE;
        }
        this.remove(entry);
        return n2;
    }
    
    public float firstKey() {
        return this.minimum(this.root).getFloatKey();
    }
    
    public float lastKey() {
        return this.maximum(this.root).getFloatKey();
    }
    
    public LiteralIterator keyIterator() {
        return new KeyIterator();
    }
    
    public LiteralIterator keyRangeIterator(final float n, final boolean b, final float n2, final boolean b2) {
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
    
    public IntIterator valueRangeIterator(final float n, final boolean b, final float n2, final boolean b2) {
        return new ValueIterator(this, (EntryIterator)this.keyRangeIterator(n, b, n2, b2));
    }
    
    protected int compare(final Entry entry, final Entry entry2) {
        int compare = this.cmp.compare(entry.getFloatKey(), entry2.getFloatKey());
        if (this.allowDuplicates && compare == 0) {
            compare = ((entry.order < entry2.order) ? -1 : ((entry.order > entry2.order) ? 1 : 0));
            this.lastOrder = 1 + ((compare < 0) ? entry.order : entry2.order);
        }
        return compare;
    }
    
    private Entry find(final float key, final int order) {
        this.dummy.key = key;
        this.dummy.order = order;
        return this.find(this.dummy);
    }
    
    private Entry findPredecessor(final float key, final int order) {
        this.dummy.key = key;
        this.dummy.order = order;
        return this.findPredecessor(this.dummy);
    }
    
    private Entry findCeiling(final float key, final int order) {
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
        
        public boolean isFloatSupported() {
            return true;
        }
        
        public float nextFloat() {
            return this.nextEntry().getFloatKey();
        }
    }
    
    static class FloatEntry extends Entry
    {
        float key;
        
        public FloatEntry(final float key, final int n) {
            super(n);
            this.key = key;
        }
        
        public FloatEntry(final float key, final int n, final Entry entry, final int n2) {
            super(n, entry, n2);
            this.key = key;
        }
        
        public float getFloatKey() {
            return this.key;
        }
        
        public Object getKey() {
            return new Float(this.key);
        }
        
        public boolean keyEquals(final Entry entry) {
            return entry instanceof FloatEntry && this.key == ((FloatEntry)entry).key;
        }
        
        public boolean equals(final Object o) {
            if (!(o instanceof FloatEntry)) {
                return false;
            }
            final FloatEntry floatEntry = (FloatEntry)o;
            return this.key == floatEntry.key && this.val == floatEntry.val;
        }
        
        public int hashCode() {
            return Float.floatToIntBits(this.key) ^ this.val ^ this.order;
        }
        
        public String toString() {
            return this.key + "=" + this.val;
        }
        
        public void copyFields(final Entry entry) {
            super.copyFields(entry);
            this.key = entry.getFloatKey();
        }
    }
}
