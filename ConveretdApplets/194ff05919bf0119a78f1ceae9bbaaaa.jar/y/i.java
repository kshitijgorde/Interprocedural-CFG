// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.io.DataOutput;
import java.io.DataInput;

public final class i implements Cloneable, cw
{
    public int[] a;
    public int a;
    
    private i(final byte b) {
        this.a = new int[10];
    }
    
    private i(final char c) {
        this((byte)0);
    }
    
    public i() {
        this('\0');
    }
    
    public final int a(final int n) {
        if (n >= this.a) {
            throw new ArrayIndexOutOfBoundsException("index=" + n);
        }
        return this.a[n];
    }
    
    public final Object clone() {
        try {
            final i i;
            (i = (i)super.clone()).a = new int[this.a];
            System.arraycopy(this.a, 0, i.a, 0, this.a);
            return i;
        }
        catch (CloneNotSupportedException ex) {
            throw new InternalError();
        }
    }
    
    public final void a(final DataInput dataInput) {
        this.a = 0;
        for (int int1 = dataInput.readInt(), i = 0; i < int1; ++i) {
            final int int2 = dataInput.readInt();
            final int n = this.a + 1;
            final int length = this.a.length;
            if (n > length) {
                final int[] a = this.a;
                int n2;
                if ((n2 = length << 1) < n) {
                    n2 = n;
                }
                System.arraycopy(a, 0, this.a = new int[n2], 0, this.a);
            }
            this.a[this.a++] = int2;
        }
    }
    
    public final void a(final DataOutput dataOutput) {
        dataOutput.writeInt(this.a);
        for (int i = 0; i < this.a; ++i) {
            dataOutput.writeInt(this.a(i));
        }
    }
    
    public final String toString() {
        final int n = this.a - 1;
        final StringBuffer sb;
        (sb = new StringBuffer()).append("[");
        for (int i = 0; i <= n; ++i) {
            sb.append(Integer.toString(this.a[i]));
            if (i < n) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
