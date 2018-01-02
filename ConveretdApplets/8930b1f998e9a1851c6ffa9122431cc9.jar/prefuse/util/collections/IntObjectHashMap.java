// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.collections;

import java.util.ArrayList;
import java.util.Arrays;

public class IntObjectHashMap extends AbstractHashMap implements Cloneable
{
    protected static final int defaultCapacity = 277;
    protected static final double defaultMinLoadFactor = 0.2;
    protected static final double defaultMaxLoadFactor = 0.5;
    protected static final byte FREE = 0;
    protected static final byte FULL = 1;
    protected static final byte REMOVED = 2;
    protected int[] table;
    protected Object[] values;
    protected byte[] state;
    protected int freeEntries;
    
    public IntObjectHashMap() {
        this(277);
    }
    
    public IntObjectHashMap(final int n) {
        this(n, 0.2, 0.5);
    }
    
    public IntObjectHashMap(final int n, final double n2, final double n3) {
        this.setUp(n, n2, n3);
    }
    
    public void clear() {
        Arrays.fill(this.state, (byte)0);
        Arrays.fill(this.values, null);
        this.distinct = 0;
        this.freeEntries = this.table.length;
        this.trimToSize();
    }
    
    public Object clone() {
        try {
            final IntObjectHashMap intObjectHashMap = (IntObjectHashMap)super.clone();
            intObjectHashMap.table = intObjectHashMap.table.clone();
            intObjectHashMap.values = intObjectHashMap.values.clone();
            intObjectHashMap.state = intObjectHashMap.state.clone();
            return intObjectHashMap;
        }
        catch (CloneNotSupportedException ex) {
            return null;
        }
    }
    
    public boolean containsKey(final int n) {
        return this.indexOfKey(n) >= 0;
    }
    
    public boolean containsValue(final Object o) {
        return this.indexOfValue(o) >= 0;
    }
    
    public void ensureCapacity(final int n) {
        if (this.table.length < n) {
            this.rehash(this.nextPrime(n));
        }
    }
    
    public Object get(final int n) {
        final int indexOfKey = this.indexOfKey(n);
        if (indexOfKey < 0) {
            return null;
        }
        return this.values[indexOfKey];
    }
    
    protected int indexOfInsertion(final int n) {
        final int[] table = this.table;
        final byte[] state = this.state;
        final int length = table.length;
        final int n2 = n & Integer.MAX_VALUE;
        int n3 = n2 % length;
        int n4 = n2 % (length - 2);
        if (n4 == 0) {
            n4 = 1;
        }
        while (state[n3] == 1 && table[n3] != n) {
            n3 -= n4;
            if (n3 < 0) {
                n3 += length;
            }
        }
        if (state[n3] == 2) {
            final int n5 = n3;
            while (state[n3] != 0 && (state[n3] == 2 || table[n3] != n)) {
                n3 -= n4;
                if (n3 < 0) {
                    n3 += length;
                }
            }
            if (state[n3] == 0) {
                n3 = n5;
            }
        }
        if (state[n3] == 1) {
            return -n3 - 1;
        }
        return n3;
    }
    
    protected int indexOfKey(final int n) {
        final int[] table = this.table;
        final byte[] state = this.state;
        final int length = table.length;
        final int n2 = n & Integer.MAX_VALUE;
        int n3 = n2 % length;
        int n4 = n2 % (length - 2);
        if (n4 == 0) {
            n4 = 1;
        }
        while (state[n3] != 0 && (state[n3] == 2 || table[n3] != n)) {
            n3 -= n4;
            if (n3 < 0) {
                n3 += length;
            }
        }
        if (state[n3] == 0) {
            return -1;
        }
        return n3;
    }
    
    protected int indexOfValue(final Object o) {
        final Object[] values = this.values;
        final byte[] state = this.state;
        int length = state.length;
        while (--length >= 0) {
            if (state[length] == 1 && values[length] == o) {
                return length;
            }
        }
        return -1;
    }
    
    public int keyOf(final Object o) {
        final int indexOfValue = this.indexOfValue(o);
        if (indexOfValue < 0) {
            return Integer.MIN_VALUE;
        }
        return this.table[indexOfValue];
    }
    
    public int keys(final int[] array) {
        final int[] table = this.table;
        final byte[] state = this.state;
        if (array.length < this.distinct) {
            return -1;
        }
        int n = 0;
        int length = table.length;
        while (length-- > 0) {
            if (state[length] == 1) {
                array[n++] = table[length];
            }
        }
        return this.distinct;
    }
    
    public boolean put(final int n, final Object o) {
        final int indexOfInsertion = this.indexOfInsertion(n);
        if (indexOfInsertion < 0) {
            this.values[-indexOfInsertion - 1] = o;
            return false;
        }
        if (this.distinct > this.highWaterMark) {
            this.rehash(this.chooseGrowCapacity(this.distinct + 1, this.minLoadFactor, this.maxLoadFactor));
            return this.put(n, o);
        }
        this.table[indexOfInsertion] = n;
        this.values[indexOfInsertion] = o;
        if (this.state[indexOfInsertion] == 0) {
            --this.freeEntries;
        }
        this.state[indexOfInsertion] = 1;
        ++this.distinct;
        if (this.freeEntries < 1) {
            this.rehash(this.chooseGrowCapacity(this.distinct + 1, this.minLoadFactor, this.maxLoadFactor));
        }
        return true;
    }
    
    protected void rehash(final int n) {
        final int length = this.table.length;
        final int[] table = this.table;
        final Object[] values = this.values;
        final byte[] state = this.state;
        final int[] table2 = new int[n];
        final Object[] values2 = new Object[n];
        final byte[] state2 = new byte[n];
        this.lowWaterMark = this.chooseLowWaterMark(n, this.minLoadFactor);
        this.highWaterMark = this.chooseHighWaterMark(n, this.maxLoadFactor);
        this.table = table2;
        this.values = values2;
        this.state = state2;
        this.freeEntries = n - this.distinct;
        int n2 = length;
        while (n2-- > 0) {
            if (state[n2] == 1) {
                final int n3 = table[n2];
                final int indexOfInsertion = this.indexOfInsertion(n3);
                table2[indexOfInsertion] = n3;
                values2[indexOfInsertion] = values[n2];
                state2[indexOfInsertion] = 1;
            }
        }
    }
    
    public boolean removeKey(final int n) {
        final int indexOfKey = this.indexOfKey(n);
        if (indexOfKey < 0) {
            return false;
        }
        this.state[indexOfKey] = 2;
        this.values[indexOfKey] = null;
        --this.distinct;
        if (this.distinct < this.lowWaterMark) {
            this.rehash(this.chooseShrinkCapacity(this.distinct, this.minLoadFactor, this.maxLoadFactor));
        }
        return true;
    }
    
    protected void setUp(final int n, final double minLoadFactor, final double maxLoadFactor) {
        super.setUp(n, minLoadFactor, maxLoadFactor);
        int nextPrime = this.nextPrime(n);
        if (nextPrime == 0) {
            nextPrime = 1;
        }
        this.table = new int[nextPrime];
        this.values = new Object[nextPrime];
        this.state = new byte[nextPrime];
        this.minLoadFactor = minLoadFactor;
        if (nextPrime == Integer.MAX_VALUE) {
            this.maxLoadFactor = 1.0;
        }
        else {
            this.maxLoadFactor = maxLoadFactor;
        }
        this.distinct = 0;
        this.freeEntries = nextPrime;
        this.lowWaterMark = 0;
        this.highWaterMark = this.chooseHighWaterMark(nextPrime, this.maxLoadFactor);
    }
    
    public void trimToSize() {
        final int nextPrime = this.nextPrime((int)(1.0 + 1.2 * this.size()));
        if (this.table.length > nextPrime) {
            this.rehash(nextPrime);
        }
    }
    
    public void values(final ArrayList list) {
        final Object[] values = this.values;
        final byte[] state = this.state;
        int length = state.length;
        while (length-- > 0) {
            if (state[length] == 1) {
                list.add(values[length]);
            }
        }
    }
}
