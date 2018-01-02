// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.collections;

import java.util.NoSuchElementException;
import java.util.Comparator;
import java.util.BitSet;

public class BooleanIntBitSetMap implements BooleanIntSortedMap
{
    private BitSet m_true;
    private BitSet m_false;
    
    public BooleanIntBitSetMap() {
        this.m_true = new BitSet();
        this.m_false = new BitSet();
    }
    
    public boolean firstKey() {
        return false;
    }
    
    public boolean lastKey() {
        return true;
    }
    
    public boolean containsKey(final boolean b) {
        return (b ? this.m_true : this.m_false).cardinality() > 0;
    }
    
    public IntIterator valueRangeIterator(final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        if (!b2 && !b4) {
            return new BitSetIterator(null);
        }
        if (b == b3 || !b4) {
            return new BitSetIterator(b ? this.m_true : this.m_false);
        }
        if (!b2) {
            return new BitSetIterator(b3 ? this.m_true : this.m_false);
        }
        return new BitSetIterator(b ? this.m_true : this.m_false, b3 ? this.m_true : this.m_false);
    }
    
    public LiteralIterator keyIterator() {
        return new BitSetIterator(this.m_false, this.m_true);
    }
    
    public LiteralIterator keyRangeIterator(final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        if (!b2 && !b4) {
            return new BitSetIterator(null);
        }
        if (b == b3 || !b4) {
            return new BitSetIterator(b ? this.m_true : this.m_false);
        }
        if (!b2) {
            return new BitSetIterator(b3 ? this.m_true : this.m_false);
        }
        return new BitSetIterator(b ? this.m_true : this.m_false, b3 ? this.m_true : this.m_false);
    }
    
    public int get(final boolean b) {
        return (b ? this.m_true : this.m_false).nextSetBit(0);
    }
    
    public int remove(final boolean b) {
        final BitSet set = b ? this.m_true : this.m_false;
        final int n = set.length() - 1;
        set.clear(n);
        return n;
    }
    
    public int remove(final boolean b, final int n) {
        final BitSet set = b ? this.m_true : this.m_false;
        if (set.get(n)) {
            set.clear(n);
            return n;
        }
        return Integer.MIN_VALUE;
    }
    
    public int put(final boolean b, final int n) {
        final BitSet set = b ? this.m_true : this.m_false;
        final boolean value = set.get(n);
        set.set(n);
        return value ? n : Integer.MIN_VALUE;
    }
    
    public int getMinimum() {
        if (this.m_false.cardinality() > 0) {
            return this.m_false.nextSetBit(0);
        }
        if (this.m_true.cardinality() > 0) {
            return this.m_true.nextSetBit(0);
        }
        return Integer.MIN_VALUE;
    }
    
    public int getMaximum() {
        final int n = this.m_true.length() - 1;
        return (n > 0) ? n : (this.m_false.length() - 1);
    }
    
    public int getMedian() {
        final int cardinality = this.m_false.cardinality();
        final int cardinality2 = this.m_true.cardinality();
        if (cardinality == 0 && cardinality2 == 0) {
            return Integer.MIN_VALUE;
        }
        final int n = (cardinality + cardinality2) / 2;
        final BitSet set = (cardinality > cardinality2) ? this.m_false : this.m_true;
        for (int i = set.nextSetBit(0), n2 = 0; i >= 0; i = set.nextSetBit(i + 1), ++n2) {
            if (n2 == n) {
                return i;
            }
        }
        return Integer.MIN_VALUE;
    }
    
    public int getUniqueCount() {
        int n = 0;
        if (this.m_false.cardinality() > 0) {
            ++n;
        }
        if (this.m_true.cardinality() > 0) {
            ++n;
        }
        return n;
    }
    
    public boolean isAllowDuplicates() {
        return true;
    }
    
    public int size() {
        return this.m_true.cardinality() + this.m_false.cardinality();
    }
    
    public boolean isEmpty() {
        return this.m_true.isEmpty() && this.m_false.isEmpty();
    }
    
    public Comparator comparator() {
        return DefaultLiteralComparator.getInstance();
    }
    
    public void clear() {
        this.m_true.clear();
        this.m_false.clear();
    }
    
    public boolean containsValue(final int n) {
        return this.m_false.get(n) || this.m_true.get(n);
    }
    
    public IntIterator valueIterator(final boolean b) {
        if (!b) {
            return new BitSetIterator(this.m_true, this.m_false);
        }
        return new BitSetIterator(this.m_false, this.m_true);
    }
    
    public class BitSetIterator extends IntIterator
    {
        private BitSet m_cur;
        private BitSet m_next;
        private int m_val;
        
        public BitSetIterator(final BooleanIntBitSetMap booleanIntBitSetMap, final BitSet set) {
            this(booleanIntBitSetMap, set, null);
        }
        
        public BitSetIterator(final BitSet cur, final BitSet next) {
            this.m_val = -1;
            this.m_cur = cur;
            this.m_next = next;
            if (cur == null) {
                this.m_val = -2;
            }
            else {
                this.m_val = -1;
                this.advance();
            }
        }
        
        private void advance() {
            final int nextSetBit = this.m_cur.nextSetBit(this.m_val + 1);
            if (nextSetBit < 0) {
                if (this.m_next != null) {
                    this.m_cur = this.m_next;
                    this.m_next = null;
                    this.m_val = -1;
                    this.advance();
                }
                else {
                    this.m_val = -2;
                }
                return;
            }
            this.m_val = nextSetBit;
        }
        
        public int nextInt() {
            if (this.m_val < 0) {
                throw new NoSuchElementException();
            }
            final int val = this.m_val;
            this.advance();
            return val;
        }
        
        public boolean nextBoolean() {
            if (this.m_cur == BooleanIntBitSetMap.this.m_true) {
                this.advance();
                return true;
            }
            if (this.m_cur == BooleanIntBitSetMap.this.m_false) {
                this.advance();
                return false;
            }
            throw new NoSuchElementException();
        }
        
        public boolean hasNext() {
            return this.m_val >= 0;
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
