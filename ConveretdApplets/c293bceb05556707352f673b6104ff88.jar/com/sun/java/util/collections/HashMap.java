// 
// Decompiled by Procyon v0.5.30
// 

package com.sun.java.util.collections;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class HashMap extends AbstractMap implements Map, Cloneable, Serializable
{
    private transient Entry[] table;
    private transient int count;
    private int threshold;
    private float loadFactor;
    private transient int modCount;
    private transient Set keySet;
    private transient Set entrySet;
    private transient Collection values;
    private static final int KEYS = 0;
    private static final int VALUES = 1;
    private static final int ENTRIES = 2;
    
    public HashMap(int n, final float loadFactor) {
        if (n < 0) {
            throw new IllegalArgumentException("Illegal Initial Capacity: " + n);
        }
        if (loadFactor <= 0.0f) {
            throw new IllegalArgumentException("Illegal Load factor: " + loadFactor);
        }
        if (n == 0) {
            n = 1;
        }
        this.loadFactor = loadFactor;
        this.table = new Entry[n];
        this.threshold = (int)(n * loadFactor);
    }
    
    public HashMap(final int n) {
        this(n, 0.75f);
    }
    
    public HashMap() {
        this(101, 0.75f);
    }
    
    public HashMap(final Map map) {
        this(Math.max(2 * map.size(), 11), 0.75f);
        this.putAll(map);
    }
    
    public int size() {
        return this.count;
    }
    
    public boolean isEmpty() {
        return this.count == 0;
    }
    
    public boolean containsValue(final Object o) {
        final Entry[] table = this.table;
        if (o == null) {
            int length = table.length;
            while (length-- > 0) {
                for (Entry next = table[length]; next != null; next = next.next) {
                    if (next.value == null) {
                        return true;
                    }
                }
            }
        }
        else {
            int length2 = table.length;
            while (length2-- > 0) {
                for (Entry next2 = table[length2]; next2 != null; next2 = next2.next) {
                    if (o.equals(next2.value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean containsKey(final Object o) {
        final Entry[] table = this.table;
        if (o != null) {
            final int hashCode = o.hashCode();
            for (Entry next = table[(hashCode & Integer.MAX_VALUE) % table.length]; next != null; next = next.next) {
                if (next.hash == hashCode && o.equals(next.key)) {
                    return true;
                }
            }
        }
        else {
            for (Entry next2 = table[0]; next2 != null; next2 = next2.next) {
                if (next2.key == null) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public Object get(final Object o) {
        final Entry[] table = this.table;
        if (o != null) {
            final int hashCode = o.hashCode();
            for (Entry next = table[(hashCode & Integer.MAX_VALUE) % table.length]; next != null; next = next.next) {
                if (next.hash == hashCode && o.equals(next.key)) {
                    return next.value;
                }
            }
        }
        else {
            for (Entry next2 = table[0]; next2 != null; next2 = next2.next) {
                if (next2.key == null) {
                    return next2.value;
                }
            }
        }
        return null;
    }
    
    private void rehash() {
        final int length = this.table.length;
        final Entry[] table = this.table;
        final int n = length * 2 + 1;
        final Entry[] table2 = new Entry[n];
        ++this.modCount;
        this.threshold = (int)(n * this.loadFactor);
        this.table = table2;
        int n2 = length;
        while (n2-- > 0) {
            Entry entry;
            int n3;
            for (Entry next = table[n2]; next != null; next = next.next, n3 = (entry.hash & Integer.MAX_VALUE) % n, entry.next = table2[n3], table2[n3] = entry) {
                entry = next;
            }
        }
    }
    
    public Object put(final Object o, final Object o2) {
        Entry[] array = this.table;
        int hashCode = 0;
        int n = 0;
        if (o != null) {
            hashCode = o.hashCode();
            n = (hashCode & Integer.MAX_VALUE) % array.length;
            for (Entry next = array[n]; next != null; next = next.next) {
                if (next.hash == hashCode && o.equals(next.key)) {
                    final Object value = next.value;
                    next.value = o2;
                    return value;
                }
            }
        }
        else {
            for (Entry next2 = array[0]; next2 != null; next2 = next2.next) {
                if (next2.key == null) {
                    final Object value2 = next2.value;
                    next2.value = o2;
                    return value2;
                }
            }
        }
        ++this.modCount;
        if (this.count >= this.threshold) {
            this.rehash();
            array = this.table;
            n = (hashCode & Integer.MAX_VALUE) % array.length;
        }
        array[n] = new Entry(hashCode, o, o2, array[n]);
        ++this.count;
        return null;
    }
    
    public Object remove(final Object o) {
        final Entry[] table = this.table;
        if (o != null) {
            final int hashCode = o.hashCode();
            final int n = (hashCode & Integer.MAX_VALUE) % table.length;
            Entry next = table[n];
            Entry entry = null;
            while (next != null) {
                if (next.hash == hashCode && o.equals(next.key)) {
                    ++this.modCount;
                    if (entry != null) {
                        entry.next = next.next;
                    }
                    else {
                        table[n] = next.next;
                    }
                    --this.count;
                    final Object value = next.value;
                    next.value = null;
                    return value;
                }
                entry = next;
                next = next.next;
            }
        }
        else {
            Entry next2 = table[0];
            Entry entry2 = null;
            while (next2 != null) {
                if (next2.key == null) {
                    ++this.modCount;
                    if (entry2 != null) {
                        entry2.next = next2.next;
                    }
                    else {
                        table[0] = next2.next;
                    }
                    --this.count;
                    final Object value2 = next2.value;
                    next2.value = null;
                    return value2;
                }
                entry2 = next2;
                next2 = next2.next;
            }
        }
        return null;
    }
    
    public void putAll(final Map map) {
        for (final Map.Entry entry : map.entrySet()) {
            this.put(entry.getKey(), entry.getValue());
        }
    }
    
    public void clear() {
        final Entry[] table = this.table;
        ++this.modCount;
        int length = table.length;
        while (--length >= 0) {
            table[length] = null;
        }
        this.count = 0;
    }
    
    public Object clone() {
        try {
            final HashMap hashMap = (HashMap)super.clone();
            hashMap.table = new Entry[this.table.length];
            int length = this.table.length;
            while (length-- > 0) {
                hashMap.table[length] = ((this.table[length] != null) ? ((Entry)this.table[length].clone()) : null);
            }
            hashMap.keySet = null;
            hashMap.entrySet = null;
            hashMap.values = null;
            hashMap.modCount = 0;
            return hashMap;
        }
        catch (CloneNotSupportedException ex) {
            throw new InternalError();
        }
    }
    
    public Set keySet() {
        if (this.keySet == null) {
            this.keySet = new AbstractSet() {
                public Iterator iterator() {
                    return new HashIterator(HashMap.access$0());
                }
                
                public int size() {
                    return HashMap.this.count;
                }
                
                public boolean contains(final Object o) {
                    return HashMap.this.containsKey(o);
                }
                
                public boolean remove(final Object o) {
                    return HashMap.this.remove(o) != null;
                }
                
                public void clear() {
                    HashMap.this.clear();
                }
            };
        }
        return this.keySet;
    }
    
    public Collection values() {
        if (this.values == null) {
            this.values = (Collection)new HashMap.HashMap$2(this);
        }
        return this.values;
    }
    
    public Set entrySet() {
        if (this.entrySet == null) {
            this.entrySet = (Set)new HashMap.HashMap$3(this);
        }
        return this.entrySet;
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(this.table.length);
        objectOutputStream.writeInt(this.count);
        for (int i = this.table.length - 1; i >= 0; --i) {
            for (Entry next = this.table[i]; next != null; next = next.next) {
                objectOutputStream.writeObject(next.key);
                objectOutputStream.writeObject(next.value);
            }
        }
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.table = new Entry[objectInputStream.readInt()];
        for (int int1 = objectInputStream.readInt(), i = 0; i < int1; ++i) {
            this.put(objectInputStream.readObject(), objectInputStream.readObject());
        }
    }
    
    int capacity() {
        return this.table.length;
    }
    
    float loadFactor() {
        return this.loadFactor;
    }
    
    static /* synthetic */ int access$0() {
        return 0;
    }
    
    static /* synthetic */ int access$2() {
        return 1;
    }
    
    static /* synthetic */ void access$6(final HashMap hashMap, final int modCount) {
        hashMap.modCount = modCount;
    }
    
    static /* synthetic */ void access$7(final HashMap hashMap, final int count) {
        hashMap.count = count;
    }
    
    private static class Entry implements Map.Entry
    {
        int hash;
        Object key;
        Object value;
        Entry next;
        
        Entry(final int hash, final Object key, final Object value, final Entry next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
        
        protected Object clone() {
            return new Entry(this.hash, this.key, this.value, (this.next == null) ? null : ((Entry)this.next.clone()));
        }
        
        public Object getKey() {
            return this.key;
        }
        
        public Object getValue() {
            return this.value;
        }
        
        public Object setValue(final Object value) {
            final Object value2 = this.value;
            this.value = value;
            return value2;
        }
        
        public boolean equals(final Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            final Map.Entry entry = (Map.Entry)o;
            Label_0050: {
                boolean equals;
                if (this.key == null) {
                    if (entry.getKey() == null) {
                        break Label_0050;
                    }
                    equals = false;
                }
                else {
                    equals = this.key.equals(entry.getKey());
                }
                if (!equals) {
                    return false;
                }
            }
            boolean equals2;
            if (this.value == null) {
                if (entry.getValue() == null) {
                    return true;
                }
                equals2 = false;
            }
            else {
                equals2 = this.value.equals(entry.getValue());
            }
            if (!equals2) {
                return false;
            }
            return true;
        }
        
        public int hashCode() {
            return this.hash ^ ((this.value == null) ? 0 : this.value.hashCode());
        }
        
        public String toString() {
            return String.valueOf(this.key.toString()) + "=" + this.value.toString();
        }
    }
    
    private class HashIterator implements Iterator
    {
        Entry[] table;
        int index;
        Entry entry;
        Entry lastReturned;
        int type;
        private int expectedModCount;
        
        HashIterator(final int type) {
            this.table = HashMap.this.table;
            this.index = this.table.length;
            this.expectedModCount = HashMap.this.modCount;
            this.type = type;
        }
        
        public boolean hasNext() {
            while (this.entry == null && this.index > 0) {
                final Entry[] table = this.table;
                final int index = this.index - 1;
                this.index = index;
                this.entry = table[index];
            }
            return this.entry != null;
        }
        
        public Object next() {
            if (HashMap.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
            while (this.entry == null && this.index > 0) {
                final Entry[] table = this.table;
                final int index = this.index - 1;
                this.index = index;
                this.entry = table[index];
            }
            if (this.entry == null) {
                throw new NoSuchElementException();
            }
            final Entry entry = this.entry;
            this.lastReturned = entry;
            final Entry entry2 = entry;
            this.entry = entry2.next;
            if (this.type == HashMap.access$0()) {
                return entry2.key;
            }
            if (this.type == HashMap.access$2()) {
                return entry2.value;
            }
            return entry2;
        }
        
        public void remove() {
            if (this.lastReturned == null) {
                throw new IllegalStateException();
            }
            if (HashMap.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
            final Entry[] access$4 = HashMap.this.table;
            final int n = (this.lastReturned.hash & Integer.MAX_VALUE) % access$4.length;
            Entry next = access$4[n];
            Entry entry = null;
            while (next != null) {
                if (next == this.lastReturned) {
                    final HashMap this$0 = HashMap.this;
                    HashMap.access$6(this$0, this$0.modCount + 1);
                    ++this.expectedModCount;
                    if (entry == null) {
                        access$4[n] = next.next;
                    }
                    else {
                        entry.next = next.next;
                    }
                    final HashMap this$2 = HashMap.this;
                    HashMap.access$7(this$2, this$2.count - 1);
                    this.lastReturned = null;
                    return;
                }
                entry = next;
                next = next.next;
            }
            throw new ConcurrentModificationException();
        }
    }
}
