// 
// Decompiled by Procyon v0.5.30
// 

package anon.util;

public final class MyStringBuilder
{
    private char[] value;
    private int aktPos;
    private int capacity;
    
    public MyStringBuilder(final int capacity) {
        this.value = new char[capacity];
        this.aktPos = 0;
        this.capacity = capacity;
    }
    
    public void append(final String s) {
        final int length = s.length();
        if (s.length() > this.capacity) {
            this.capacity = length + this.value.length + 512;
            final char[] value = new char[this.capacity];
            System.arraycopy(this.value, 0, value, 0, this.aktPos);
            this.value = value;
            this.capacity -= this.aktPos;
        }
        s.getChars(0, length, this.value, this.aktPos);
        this.aktPos += length;
        this.capacity -= length;
    }
    
    public void append(final int n) {
        this.append(Integer.toString(n));
    }
    
    public void append(final long n) {
        this.append(Long.toString(n));
    }
    
    public void setLength(final int aktPos) {
        this.aktPos = aktPos;
    }
    
    public String toString() {
        return new String(this.value, 0, this.aktPos);
    }
}
