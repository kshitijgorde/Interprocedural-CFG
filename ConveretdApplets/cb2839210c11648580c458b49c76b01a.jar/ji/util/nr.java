// 
// Decompiled by Procyon v0.5.30
// 

package ji.util;

public class nr
{
    byte[] a;
    int b;
    boolean c;
    
    public nr(final int n) {
        this.a = new byte[n];
        this.c = false;
        this.b = 0;
    }
    
    public void a(final byte[] array) {
        for (int i = 0; i < array.length; ++i) {
            this.a[this.b++] = array[i];
            if (this.b == this.a.length) {
                this.c = true;
                this.b = 0;
            }
        }
    }
    
    public byte[] a(final int n, final int n2) {
        final byte[] array = new byte[n2];
        int n3 = n;
        for (int i = 0; i < n2; ++i) {
            array[i] = this.a[n3];
            if (array[i] == 0 && i > 0) {
                while (i < n2) {
                    array[i] = array[i - 1];
                    ++i;
                }
            }
            if (++n3 == this.a.length) {
                n3 = 0;
            }
        }
        return array;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(this.getClass().getName());
        sb.append(": insertionIndex=").append(this.b).append("; full=").append(this.c).append("; bufferContent=").append(new String(this.a).trim());
        return sb.toString();
    }
}
