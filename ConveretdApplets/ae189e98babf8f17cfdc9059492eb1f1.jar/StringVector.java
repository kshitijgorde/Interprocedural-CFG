// 
// Decompiled by Procyon v0.5.30
// 

class StringVector
{
    private String[] data;
    private int size;
    private int index;
    
    protected void addElement(final String s) {
        if (this.size == this.data.length) {
            final String[] data = new String[2 * this.size];
            System.arraycopy(this.data, 0, data, 0, this.size);
            this.data = data;
        }
        this.data[this.size++] = s;
    }
    
    protected void trimToSize() {
        if (this.size < this.data.length) {
            final String[] data = new String[this.size];
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
    
    protected String nextElement() {
        return this.data[this.index++];
    }
    
    StringVector() {
        this.data = new String[100];
    }
}
