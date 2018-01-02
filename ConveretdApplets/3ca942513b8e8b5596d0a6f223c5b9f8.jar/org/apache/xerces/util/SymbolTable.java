// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.util;

public class SymbolTable
{
    protected static final int TABLE_SIZE = 101;
    protected Entry[] fBuckets;
    protected int fTableSize;
    protected transient int fCount;
    protected int fThreshold;
    protected float fLoadFactor;
    
    public SymbolTable(int fTableSize, final float fLoadFactor) {
        this.fBuckets = null;
        if (fTableSize < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + fTableSize);
        }
        if (fLoadFactor <= 0.0f || Float.isNaN(fLoadFactor)) {
            throw new IllegalArgumentException("Illegal Load: " + fLoadFactor);
        }
        if (fTableSize == 0) {
            fTableSize = 1;
        }
        this.fLoadFactor = fLoadFactor;
        this.fTableSize = fTableSize;
        this.fBuckets = new Entry[this.fTableSize];
        this.fThreshold = (int)(this.fTableSize * fLoadFactor);
        this.fCount = 0;
    }
    
    public SymbolTable(final int n) {
        this(n, 0.75f);
    }
    
    public SymbolTable() {
        this(101, 0.75f);
    }
    
    public String addSymbol(final String s) {
        int n = this.hash(s) % this.fTableSize;
        for (Entry next = this.fBuckets[n]; next != null; next = next.next) {
            if (next.symbol.equals(s)) {
                return next.symbol;
            }
        }
        if (this.fCount >= this.fThreshold) {
            this.rehash();
            n = this.hash(s) % this.fTableSize;
        }
        final Entry entry = new Entry(s, this.fBuckets[n]);
        this.fBuckets[n] = entry;
        ++this.fCount;
        return entry.symbol;
    }
    
    public String addSymbol(final char[] array, final int n, final int n2) {
        int n3 = this.hash(array, n, n2) % this.fTableSize;
    Label_0077:
        for (Entry next = this.fBuckets[n3]; next != null; next = next.next) {
            if (n2 == next.characters.length) {
                for (int i = 0; i < n2; ++i) {
                    if (array[n + i] != next.characters[i]) {
                        continue Label_0077;
                    }
                }
                return next.symbol;
            }
        }
        if (this.fCount >= this.fThreshold) {
            this.rehash();
            n3 = this.hash(array, n, n2) % this.fTableSize;
        }
        final Entry entry = new Entry(array, n, n2, this.fBuckets[n3]);
        this.fBuckets[n3] = entry;
        ++this.fCount;
        return entry.symbol;
    }
    
    public int hash(final String s) {
        return s.hashCode() & 0x7FFFFFF;
    }
    
    public int hash(final char[] array, final int n, final int n2) {
        int n3 = 0;
        for (int i = 0; i < n2; ++i) {
            n3 = n3 * 31 + array[n + i];
        }
        return n3 & 0x7FFFFFF;
    }
    
    protected void rehash() {
        final int length = this.fBuckets.length;
        final Entry[] fBuckets = this.fBuckets;
        final int n = length * 2 + 1;
        final Entry[] fBuckets2 = new Entry[n];
        this.fThreshold = (int)(n * this.fLoadFactor);
        this.fBuckets = fBuckets2;
        this.fTableSize = this.fBuckets.length;
        int n2 = length;
        while (n2-- > 0) {
            Entry entry;
            int n3;
            for (Entry next = fBuckets[n2]; next != null; next = next.next, n3 = this.hash(entry.characters, 0, entry.characters.length) % n, entry.next = fBuckets2[n3], fBuckets2[n3] = entry) {
                entry = next;
            }
        }
    }
    
    public boolean containsSymbol(final String s) {
        final int n = this.hash(s) % this.fTableSize;
        final int length = s.length();
    Label_0074:
        for (Entry next = this.fBuckets[n]; next != null; next = next.next) {
            if (length == next.characters.length) {
                for (int i = 0; i < length; ++i) {
                    if (s.charAt(i) != next.characters[i]) {
                        continue Label_0074;
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    public boolean containsSymbol(final char[] array, final int n, final int n2) {
    Label_0073:
        for (Entry next = this.fBuckets[this.hash(array, n, n2) % this.fTableSize]; next != null; next = next.next) {
            if (n2 == next.characters.length) {
                for (int i = 0; i < n2; ++i) {
                    if (array[n + i] != next.characters[i]) {
                        continue Label_0073;
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    protected static final class Entry
    {
        public String symbol;
        public char[] characters;
        public Entry next;
        
        public Entry(final String s, final Entry next) {
            this.symbol = s.intern();
            this.characters = new char[s.length()];
            s.getChars(0, this.characters.length, this.characters, 0);
            this.next = next;
        }
        
        public Entry(final char[] array, final int n, final int n2, final Entry next) {
            System.arraycopy(array, n, this.characters = new char[n2], 0, n2);
            this.symbol = new String(this.characters).intern();
            this.next = next;
        }
    }
}
