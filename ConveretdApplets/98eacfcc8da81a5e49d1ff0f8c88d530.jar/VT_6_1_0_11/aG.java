// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

final class aG
{
    private transient ac[] a;
    private transient int b;
    private int c;
    private float d;
    
    public aG(final cs cs) {
        this(cs, 20, 0.75f);
    }
    
    private aG(final cs cs, final int n, final float n2) {
        this.d = 0.75f;
        this.a = new ac[20];
        this.c = 15;
    }
    
    public final Object a(final int n) {
        final ac[] a = this.a;
        for (ac c = a[(n & Integer.MAX_VALUE) % a.length]; c != null; c = c.c) {
            if (c.a == n) {
                return c.b;
            }
        }
        return null;
    }
    
    public final Object a(final int n, final Object b) {
        ac[] array = this.a;
        int n2 = (n & Integer.MAX_VALUE) % array.length;
        for (ac c = array[n2]; c != null; c = c.c) {
            if (c.a == n) {
                final Object b2 = c.b;
                c.b = b;
                return b2;
            }
        }
        if (this.b >= this.c) {
            final int length = this.a.length;
            final ac[] a = this.a;
            final int n3;
            final ac[] a2 = new ac[n3 = (length << 1) + 1];
            this.c = (int)(n3 * this.d);
            this.a = a2;
            int n4 = length;
            while (n4-- > 0) {
                ac ac;
                int n5;
                for (ac c2 = a[n4]; c2 != null; c2 = c2.c, n5 = (ac.a & Integer.MAX_VALUE) % n3, ac.c = a2[n5], a2[n5] = ac) {
                    ac = c2;
                }
            }
            array = this.a;
            n2 = (n & Integer.MAX_VALUE) % array.length;
        }
        array[n2] = new ac(this, n, n, b, array[n2]);
        ++this.b;
        return null;
    }
}
