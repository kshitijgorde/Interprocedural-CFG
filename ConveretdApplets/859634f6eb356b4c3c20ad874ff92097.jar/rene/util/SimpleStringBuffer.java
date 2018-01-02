// 
// Decompiled by Procyon v0.5.30
// 

package rene.util;

public class SimpleStringBuffer
{
    private int Size;
    private int N;
    private char[] Buf;
    
    public SimpleStringBuffer(final int size) {
        this.Size = size;
        this.Buf = new char[size];
        this.N = 0;
    }
    
    public SimpleStringBuffer(final char[] buf) {
        this.Size = buf.length;
        this.Buf = buf;
        this.N = 0;
    }
    
    public void append(final char c) {
        if (this.N < this.Size) {
            this.Buf[this.N++] = c;
            return;
        }
        this.Size *= 2;
        final char[] buf = new char[this.Size];
        for (int i = 0; i < this.N; ++i) {
            buf[i] = this.Buf[i];
        }
        this.Buf = buf;
    }
    
    public void append(final String s) {
        for (int length = s.length(), i = 0; i < length; ++i) {
            this.append(s.charAt(i));
        }
    }
    
    public void clear() {
        this.N = 0;
    }
    
    public String toString() {
        if (this.N == 0) {
            return "";
        }
        return new String(this.Buf, 0, this.N);
    }
}
