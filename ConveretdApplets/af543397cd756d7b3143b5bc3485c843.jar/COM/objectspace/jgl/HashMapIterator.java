// 
// Decompiled by Procyon v0.5.30
// 

package COM.objectspace.jgl;

public final class HashMapIterator implements ForwardIterator
{
    public static final int PAIR = 1;
    public static final int KEY = 2;
    public static final int VALUE = 3;
    HashMap myHashMap;
    HashMap.HashMapNode myNode;
    int myMode;
    
    public HashMapIterator() {
        this.myMode = 1;
    }
    
    public HashMapIterator(final HashMapIterator hashMapIterator) {
        this.myMode = 1;
        this.myHashMap = hashMapIterator.myHashMap;
        this.myNode = hashMapIterator.myNode;
        this.myMode = hashMapIterator.myMode;
    }
    
    HashMapIterator(final HashMap.HashMapNode myNode, final HashMap myHashMap, final int myMode) {
        this.myMode = 1;
        this.myHashMap = myHashMap;
        this.myNode = myNode;
        this.myMode = myMode;
    }
    
    public Object clone() {
        return new HashMapIterator(this);
    }
    
    public boolean equals(final Object o) {
        return o instanceof HashMapIterator && this.equals((HashMapIterator)o);
    }
    
    public boolean equals(final HashMapIterator hashMapIterator) {
        return this.myNode == hashMapIterator.myNode;
    }
    
    public boolean atBegin() {
        for (int i = 0; i < this.myHashMap.length; ++i) {
            if (this.myHashMap.buckets[i] != null) {
                return this.myNode == this.myHashMap.buckets[i];
            }
        }
        return true;
    }
    
    public boolean atEnd() {
        return this.myNode == null;
    }
    
    public boolean hasMoreElements() {
        return this.myNode != null;
    }
    
    public void advance() {
        this.myNode = ((this.myNode.next != null) ? this.myNode.next : this.next(this.myNode));
    }
    
    public void advance(int n) {
        if (n < 0) {
            throw new InvalidOperationException("Attempt to advance a ForwardIterator in the wrong direction.");
        }
        while (n-- > 0) {
            this.advance();
        }
    }
    
    public Object nextElement() {
        Object o = null;
        switch (this.myMode) {
            case 1: {
                o = new Pair(this.myNode.key, this.myNode.value);
                break;
            }
            case 2: {
                o = this.myNode.key;
                break;
            }
            case 3: {
                o = this.myNode.value;
                break;
            }
        }
        this.myNode = ((this.myNode.next != null) ? this.myNode.next : this.next(this.myNode));
        return o;
    }
    
    public Object get() {
        switch (this.myMode) {
            case 1: {
                return new Pair(this.myNode.key, this.myNode.value);
            }
            case 2: {
                return this.myNode.key;
            }
            case 3: {
                return this.myNode.value;
            }
            default: {
                return null;
            }
        }
    }
    
    public void put(final Object o) {
        switch (this.myMode) {
            case 1: {
                final Pair pair = (Pair)o;
                this.myNode.key = pair.first;
                this.myNode.value = pair.second;
                break;
            }
            case 2: {
                this.myNode.key = o;
                break;
            }
            case 3: {
                this.myNode.value = o;
                break;
            }
        }
    }
    
    public Object key() {
        return this.myNode.key;
    }
    
    public Object value() {
        return this.myNode.value;
    }
    
    public void value(final Object value) {
        this.myNode.value = value;
    }
    
    public int distance(final ForwardIterator forwardIterator) {
        final HashMap.HashMapNode myNode = this.myNode;
        final HashMap.HashMapNode myNode2 = ((HashMapIterator)forwardIterator).myNode;
        int n = 0;
        while (this.myNode != myNode2) {
            ++n;
            this.myNode = ((this.myNode.next != null) ? this.myNode.next : this.next(this.myNode));
        }
        this.myNode = myNode;
        return n;
    }
    
    private HashMap.HashMapNode next(final HashMap.HashMapNode hashMapNode) {
        for (int i = hashMapNode.hash % this.myHashMap.length + 1; i < this.myHashMap.length; ++i) {
            if (this.myHashMap.buckets[i] != null) {
                return this.myHashMap.buckets[i];
            }
        }
        return null;
    }
    
    public Container getContainer() {
        return this.myHashMap;
    }
}
