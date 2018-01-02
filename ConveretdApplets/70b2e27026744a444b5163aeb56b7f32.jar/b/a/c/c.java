// 
// Decompiled by Procyon v0.5.30
// 

package b.a.c;

public class c implements a
{
    protected int a;
    
    public c(final int a) {
        this.a = 0;
        this.a = a;
    }
    
    public int a(final int n, final int n2, final int n3) {
        final boolean a = this.a(n, n2, n3, 0, 0, false);
        if (a == this.a(n, n2, n3 + 1, 0, 0, false)) {
            return 1440;
        }
        if (a) {
            return 1440 + this.a(n, n2);
        }
        return 1440 - this.a(n, n2);
    }
    
    public boolean a(final int n, final int n2, final int n3, final int n4, final int n5, final boolean b) {
        return false;
    }
    
    public int a(final int n, final int n2) {
        return 60;
    }
    
    public boolean a(final int[] array, final boolean b, final boolean b2) {
        final boolean a = this.a(array[0], array[1], array[2], array[3], array[4], b);
        final int a2 = this.a(array[0], array[1]);
        if (a == b) {
            return false;
        }
        if (b2 && this.a(array[0], array[1], array[2], array[3], array[4], !b) == !b) {
            return true;
        }
        if (a) {
            final int n = 4;
            array[n] += a2;
            if (array[4] >= 60) {
                final int n2 = 4;
                array[n2] -= 60;
                final int n3 = 3;
                ++array[n3];
                if (array[3] == 24) {
                    array[3] = 0;
                    final int n4 = 2;
                    ++array[n4];
                    if (array[2] > k.f(array[0], array[1])) {
                        array[2] = 1;
                        final int n5 = 1;
                        ++array[n5];
                        if (array[1] > 12) {
                            array[1] = 1;
                            final int n6 = 0;
                            ++array[n6];
                        }
                    }
                }
            }
        }
        else {
            final int n7 = 4;
            array[n7] -= a2;
            if (array[4] < 0) {
                final int n8 = 4;
                array[n8] += 60;
                final int n9 = 3;
                --array[n9];
                if (array[3] < 0) {
                    array[3] = 23;
                    final int n10 = 2;
                    --array[n10];
                    if (array[2] < 1) {
                        final int n11 = 1;
                        --array[n11];
                        array[2] = k.f(array[0], array[1]);
                        if (array[1] < 1) {
                            array[1] = 12;
                            final int n12 = 0;
                            --array[n12];
                        }
                    }
                }
            }
        }
        return true;
    }
}
