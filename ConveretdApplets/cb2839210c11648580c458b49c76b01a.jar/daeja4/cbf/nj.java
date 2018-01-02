// 
// Decompiled by Procyon v0.5.30
// 

package daeja4.cbf;

public class nj
{
    private long[] a;
    private String b;
    protected int c;
    
    public nj(final byte[] array, final int n) {
        this(array, n, "jiFAT");
    }
    
    public nj(final byte[] array, final int c, final String b) {
        this.b = b;
        this.c = c;
        this.a = new long[array.length / 4];
        int n = 0;
        for (int i = 0; i < array.length; i += 4) {
            this.a[n++] = nh.b(array, i, 4);
        }
    }
    
    public long[] a(final long n) {
        if (n == 4294967294L || n == 4294967295L) {
            return new long[0];
        }
        boolean b = true;
        long n2 = -1L;
        if (n < this.a.length) {
            n2 = this.a[(int)n];
        }
        int n3 = 1;
        if (n2 == -1) {
            if (n >= this.a.length) {
                System.out.println(String.valueOf(String.valueOf(new StringBuffer("ERROR: index out of range:  max=").append(this.a.length - 1).append("; index=").append(n))));
            }
            else if (this.a[(int)n] != 4294967295L) {
                System.out.println("ERROR: ".concat(String.valueOf(String.valueOf(this.a[(int)n]))));
            }
            b = false;
        }
        else {
            while (n2 != 4294967294L && n3 < this.a.length) {
                if (n2 == 4294967295L) {
                    break;
                }
                ++n3;
                final long n4 = n2;
                if (n4 < this.a.length) {
                    n2 = this.a[(int)n4];
                }
                else {
                    if (n4 <= Integer.MAX_VALUE) {
                        continue;
                    }
                    System.out.println("Out of range: ".concat(String.valueOf(String.valueOf(n4))));
                    b = false;
                }
            }
        }
        long[] array;
        if (b) {
            if (n3 >= this.a.length) {
                throw new IllegalStateException(String.valueOf(String.valueOf(new StringBuffer("File addressing error. ").append(this.b).append("; index=").append(n))));
            }
            array = new long[n3];
            array[0] = this.b(n);
            int n5 = 1;
            long n7;
            for (long n6 = this.a[(int)n]; n6 != 4294967294L && n6 != 4294967295L; n6 = this.a[(int)n7], ++n5) {
                n7 = n6;
                if (n7 > Integer.MAX_VALUE) {
                    throw new IllegalStateException("Out of range: ".concat(String.valueOf(String.valueOf(n7))));
                }
                array[n5] = this.b(n7);
            }
        }
        else {
            array = new long[0];
        }
        return array;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer("\n\t");
        sb.append(this.b).append(":  ");
        for (int i = 0; i < this.a.length; ++i) {
            sb.append("\n\t\t");
            sb.append(i);
            sb.append(": ");
            nh.b(sb, this.a[i]);
        }
        return sb.toString();
    }
    
    protected long b(final long n) {
        return (n << this.c) + 512;
    }
}
