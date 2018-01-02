// 
// Decompiled by Procyon v0.5.30
// 

class IntVector
{
    private int[] data;
    private int size;
    private int index;
    
    protected void addElement(final int n) {
        if (this.size == this.data.length) {
            final int[] data = new int[2 * this.size];
            System.arraycopy(this.data, 0, data, 0, this.size);
            this.data = data;
        }
        this.data[this.size++] = n;
    }
    
    protected void trimToSize() {
        if (this.size < this.data.length) {
            final int[] data = new int[this.size];
            System.arraycopy(this.data, 0, data, 0, this.size);
            this.data = data;
        }
    }
    
    protected void reset() {
        this.index = 0;
    }
    
    protected boolean hasMoreElements() {
        return this.index < this.size;
    }
    
    protected int nextElement() {
        return this.data[this.index++];
    }
    
    IntVector() {
        this.data = new int[100];
    }
}
