// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.runtime;

import java.util.Enumeration;

public class Hashtable
{
    private transient HashtableEntry[] table;
    private transient int count;
    private int threshold;
    private float loadFactor;
    
    public Hashtable(int initialCapacity, float loadFactor) {
        if (initialCapacity <= 0) {
            initialCapacity = 11;
        }
        if (loadFactor <= 0.0) {
            loadFactor = 0.75f;
        }
        this.loadFactor = loadFactor;
        this.table = new HashtableEntry[initialCapacity];
        this.threshold = (int)(initialCapacity * loadFactor);
    }
    
    public Hashtable(final int initialCapacity) {
        this(initialCapacity, 0.75f);
    }
    
    public Hashtable() {
        this(101, 0.75f);
    }
    
    public int size() {
        return this.count;
    }
    
    public boolean isEmpty() {
        return this.count == 0;
    }
    
    public Enumeration keys() {
        return new HashtableEnumerator(this.table, true);
    }
    
    public Enumeration elements() {
        return new HashtableEnumerator(this.table, false);
    }
    
    public boolean contains(final Object value) {
        if (value == null) {
            throw new NullPointerException();
        }
        final HashtableEntry[] tab = this.table;
        int i = tab.length;
        while (i-- > 0) {
            for (HashtableEntry e = tab[i]; e != null; e = e.next) {
                if (e.value.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean containsKey(final Object key) {
        final HashtableEntry[] tab = this.table;
        final int hash = key.hashCode();
        final int index = (hash & Integer.MAX_VALUE) % tab.length;
        for (HashtableEntry e = tab[index]; e != null; e = e.next) {
            if (e.hash == hash && e.key.equals(key)) {
                return true;
            }
        }
        return false;
    }
    
    public Object get(final Object key) {
        final HashtableEntry[] tab = this.table;
        final int hash = key.hashCode();
        final int index = (hash & Integer.MAX_VALUE) % tab.length;
        for (HashtableEntry e = tab[index]; e != null; e = e.next) {
            if (e.hash == hash && e.key.equals(key)) {
                return e.value;
            }
        }
        return null;
    }
    
    protected void rehash() {
        final int oldCapacity = this.table.length;
        final HashtableEntry[] oldTable = this.table;
        final int newCapacity = oldCapacity * 2 + 1;
        final HashtableEntry[] newTable = new HashtableEntry[newCapacity];
        this.threshold = (int)(newCapacity * this.loadFactor);
        this.table = newTable;
        int i = oldCapacity;
        while (i-- > 0) {
            HashtableEntry e;
            int index;
            for (HashtableEntry old = oldTable[i]; old != null; old = old.next, index = (e.hash & Integer.MAX_VALUE) % newCapacity, e.next = newTable[index], newTable[index] = e) {
                e = old;
            }
        }
    }
    
    public Object put(final Object key, final Object value) {
        if (value == null) {
            throw new NullPointerException();
        }
        final HashtableEntry[] tab = this.table;
        final int hash = key.hashCode();
        final int index = (hash & Integer.MAX_VALUE) % tab.length;
        for (HashtableEntry e = tab[index]; e != null; e = e.next) {
            if (e.hash == hash && e.key.equals(key)) {
                final Object old = e.value;
                e.value = value;
                return old;
            }
        }
        if (this.count >= this.threshold) {
            this.rehash();
            return this.put(key, value);
        }
        HashtableEntry e = new HashtableEntry();
        e.hash = hash;
        e.key = key;
        e.value = value;
        e.next = tab[index];
        tab[index] = e;
        ++this.count;
        return null;
    }
    
    public Object remove(final Object key) {
        final HashtableEntry[] tab = this.table;
        final int hash = key.hashCode();
        final int index = (hash & Integer.MAX_VALUE) % tab.length;
        HashtableEntry e = tab[index];
        HashtableEntry prev = null;
        while (e != null) {
            if (e.hash == hash && e.key.equals(key)) {
                if (prev != null) {
                    prev.next = e.next;
                }
                else {
                    tab[index] = e.next;
                }
                --this.count;
                return e.value;
            }
            prev = e;
            e = e.next;
        }
        return null;
    }
    
    public void clear() {
        final HashtableEntry[] tab = this.table;
        int index = tab.length;
        while (--index >= 0) {
            tab[index] = null;
        }
        this.count = 0;
    }
    
    public String toString() {
        final int max = this.size() - 1;
        final StringBuffer buf = new StringBuffer();
        final Enumeration k = this.keys();
        final Enumeration e = this.elements();
        buf.append("{");
        for (int i = 0; i <= max; ++i) {
            final String s1 = k.nextElement().toString();
            final String s2 = e.nextElement().toString();
            buf.append(s1 + "=" + s2);
            if (i < max) {
                buf.append(", ");
            }
        }
        buf.append("}");
        return buf.toString();
    }
    
    class HashtableEnumerator implements Enumeration
    {
        boolean keys;
        int index;
        HashtableEntry[] table;
        HashtableEntry entry;
        
        HashtableEnumerator(final HashtableEntry[] table, final boolean keys) {
            this.table = table;
            this.keys = keys;
            this.index = table.length;
        }
        
        public boolean hasMoreElements() {
            if (this.entry != null) {
                return true;
            }
            while (this.index-- > 0) {
                if ((this.entry = this.table[this.index]) != null) {
                    return true;
                }
            }
            return false;
        }
        
        public Object nextElement() {
            if (this.entry == null) {
                while (this.index-- > 0 && (this.entry = this.table[this.index]) == null) {}
            }
            if (this.entry != null) {
                final HashtableEntry e = this.entry;
                this.entry = e.next;
                return this.keys ? e.key : e.value;
            }
            return null;
        }
    }
}
