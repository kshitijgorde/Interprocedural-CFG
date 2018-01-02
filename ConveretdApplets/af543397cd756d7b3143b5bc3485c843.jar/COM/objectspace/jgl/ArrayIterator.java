// 
// Decompiled by Procyon v0.5.30
// 

package COM.objectspace.jgl;

import java.io.Serializable;

public final class ArrayIterator implements RandomAccessIterator, Serializable
{
    Array myArray;
    int myIndex;
    
    public ArrayIterator() {
    }
    
    public ArrayIterator(final ArrayIterator arrayIterator) {
        this.myArray = arrayIterator.myArray;
        this.myIndex = arrayIterator.myIndex;
    }
    
    public ArrayIterator(final Array myArray, final int myIndex) {
        this.myArray = myArray;
        this.myIndex = myIndex;
    }
    
    public Object clone() {
        return new ArrayIterator(this);
    }
    
    public boolean equals(final Object o) {
        return o instanceof ArrayIterator && this.equals((ArrayIterator)o);
    }
    
    public boolean equals(final ArrayIterator arrayIterator) {
        return arrayIterator.myIndex == this.myIndex && arrayIterator.myArray == this.myArray;
    }
    
    public boolean less(final RandomAccessIterator randomAccessIterator) {
        return this.myIndex < ((ArrayIterator)randomAccessIterator).myIndex;
    }
    
    public Object get(final int n) {
        return this.myArray.at(this.myIndex + n);
    }
    
    public void put(final int n, final Object o) {
        this.myArray.put(this.myIndex + n, o);
    }
    
    public boolean atBegin() {
        return this.myIndex == 0;
    }
    
    public boolean atEnd() {
        return this.myIndex == this.myArray.size();
    }
    
    public boolean hasMoreElements() {
        return this.myIndex < this.myArray.size();
    }
    
    public void advance() {
        ++this.myIndex;
    }
    
    public void advance(final int n) {
        this.myIndex += n;
    }
    
    public void retreat() {
        --this.myIndex;
    }
    
    public void retreat(final int n) {
        this.myIndex -= n;
    }
    
    public Object nextElement() {
        return this.myArray.at(this.myIndex++);
    }
    
    public Object get() {
        return this.myArray.at(this.myIndex);
    }
    
    public void put(final Object o) {
        this.myArray.put(this.myIndex, o);
    }
    
    public int distance(final ForwardIterator forwardIterator) {
        return ((ArrayIterator)forwardIterator).myIndex - this.myIndex;
    }
    
    public int index() {
        return this.myIndex;
    }
    
    public Container getContainer() {
        return this.myArray;
    }
}
