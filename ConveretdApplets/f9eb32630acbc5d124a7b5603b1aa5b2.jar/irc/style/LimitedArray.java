// 
// Decompiled by Procyon v0.5.30
// 

package irc.style;

class LimitedArray
{
    private int _size;
    private int _maximum;
    private int _missing;
    private Object[] _array;
    
    public LimitedArray(final int maximum) {
        this._size = 0;
        this._missing = 0;
        this._maximum = maximum;
        this._array = new Object[4];
    }
    
    public void expand() {
        if (this._array.length >= this._maximum) {
            return;
        }
        final Object[] array = new Object[this._array.length << 1];
        System.arraycopy(this._array, 0, array, 0, this._array.length);
        this._array = array;
    }
    
    public void add(final Object o) {
        if (this.size() >= this._array.length) {
            this.expand();
        }
        if (this.size() >= this._array.length) {
            ++this._missing;
        }
        this._array[this.size() % this._array.length] = o;
        ++this._size;
    }
    
    public Object get(final int n) {
        if (n < this._missing) {
            return null;
        }
        return this._array[n % this._array.length];
    }
    
    public int size() {
        return this._size;
    }
}
