// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.util;

public class SymbolTable
{
    protected static final int TABLE_SIZE = 101;
    protected Entry[] fBuckets;
    protected int fTableSize;
    
    public SymbolTable() {
        this(101);
    }
    
    public SymbolTable(final int tableSize) {
        this.fBuckets = null;
        this.fTableSize = tableSize;
        this.fBuckets = new Entry[this.fTableSize];
    }
    
    public String addSymbol(final String symbol) {
        final int bucket = this.hash(symbol) % this.fTableSize;
        final int length = symbol.length();
    Label_0078:
        for (Entry entry = this.fBuckets[bucket]; entry != null; entry = entry.next) {
            if (length == entry.characters.length) {
                for (int i = 0; i < length; ++i) {
                    if (symbol.charAt(i) != entry.characters[i]) {
                        continue Label_0078;
                    }
                }
                return entry.symbol;
            }
        }
        final Entry entry2 = new Entry(symbol, this.fBuckets[bucket]);
        this.fBuckets[bucket] = entry2;
        return entry2.symbol;
    }
    
    public String addSymbol(final char[] buffer, final int offset, final int length) {
        final int bucket = this.hash(buffer, offset, length) % this.fTableSize;
    Label_0077:
        for (Entry entry = this.fBuckets[bucket]; entry != null; entry = entry.next) {
            if (length == entry.characters.length) {
                for (int i = 0; i < length; ++i) {
                    if (buffer[offset + i] != entry.characters[i]) {
                        continue Label_0077;
                    }
                }
                return entry.symbol;
            }
        }
        final Entry entry2 = new Entry(buffer, offset, length, this.fBuckets[bucket]);
        this.fBuckets[bucket] = entry2;
        return entry2.symbol;
    }
    
    public int hash(final String symbol) {
        int code = 0;
        for (int length = symbol.length(), i = 0; i < length; ++i) {
            code = code * 37 + symbol.charAt(i);
        }
        return code & 0x7FFFFFF;
    }
    
    public int hash(final char[] buffer, final int offset, final int length) {
        int code = 0;
        for (int i = 0; i < length; ++i) {
            code = code * 37 + buffer[offset + i];
        }
        return code & 0x7FFFFFF;
    }
    
    public boolean containsSymbol(final String symbol) {
        final int bucket = this.hash(symbol) % this.fTableSize;
        final int length = symbol.length();
    Label_0074:
        for (Entry entry = this.fBuckets[bucket]; entry != null; entry = entry.next) {
            if (length == entry.characters.length) {
                for (int i = 0; i < length; ++i) {
                    if (symbol.charAt(i) != entry.characters[i]) {
                        continue Label_0074;
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    public boolean containsSymbol(final char[] buffer, final int offset, final int length) {
        final int bucket = this.hash(buffer, offset, length) % this.fTableSize;
    Label_0073:
        for (Entry entry = this.fBuckets[bucket]; entry != null; entry = entry.next) {
            if (length == entry.characters.length) {
                for (int i = 0; i < length; ++i) {
                    if (buffer[offset + i] != entry.characters[i]) {
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
        
        public Entry(final String symbol, final Entry next) {
            this.symbol = symbol.intern();
            this.characters = new char[symbol.length()];
            symbol.getChars(0, this.characters.length, this.characters, 0);
            this.next = next;
        }
        
        public Entry(final char[] ch, final int offset, final int length, final Entry next) {
            System.arraycopy(ch, offset, this.characters = new char[length], 0, length);
            this.symbol = new String(this.characters).intern();
            this.next = next;
        }
    }
}
