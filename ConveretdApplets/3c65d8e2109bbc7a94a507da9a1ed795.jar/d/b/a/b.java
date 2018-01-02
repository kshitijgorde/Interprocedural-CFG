// 
// Decompiled by Procyon v0.5.30
// 

package d.b.a;

public class b
{
    public static final String new = "Java Speex Encoder v0.9.7 ($Revision: 1.6 $)";
    private n for;
    private ad int;
    private float[] try;
    private int do;
    private int a;
    private int if;
    
    public b() {
        this.int = new ad();
    }
    
    public boolean a(final int n, final int n2, final int do1, final int a) {
        switch (n) {
            case 0: {
                this.for = new l();
                ((l)this.for).for();
                break;
            }
            case 1: {
                this.for = new i();
                ((i)this.for).goto();
                break;
            }
            case 2: {
                this.for = new i();
                ((i)this.for).char();
                break;
            }
            default: {
                return false;
            }
        }
        this.for.if(n2);
        this.if = this.for.try();
        this.do = do1;
        this.a = a;
        this.try = new float[a * this.if];
        this.int.for();
        return true;
    }
    
    public n int() {
        return this.for;
    }
    
    public int do() {
        return this.do;
    }
    
    public int if() {
        return this.a;
    }
    
    public int a() {
        return this.if;
    }
    
    public int a(final byte[] array, final int n) {
        final int do1 = this.int.do();
        System.arraycopy(this.int.if(), 0, array, n, do1);
        this.int.for();
        return do1;
    }
    
    public int for() {
        return this.int.do();
    }
    
    public boolean a(final byte[] array, final int n, final int n2) {
        a(array, n, this.try, 0, n2 / 2);
        return this.a(this.try, n2 / 2);
    }
    
    public boolean a(final short[] array, final int n, final int n2) {
        final int n3 = this.a * this.if;
        if (n2 != n3) {
            throw new IllegalArgumentException("SpeexEncoder requires " + n3 + " samples to process a Frame, not " + n2);
        }
        for (int i = 0; i < n2; ++i) {
            this.try[i] = array[n + i];
        }
        return this.a(this.try, n2);
    }
    
    public boolean a(final float[] array, final int n) {
        final int n2 = this.a * this.if;
        if (n != n2) {
            throw new IllegalArgumentException("SpeexEncoder requires " + n2 + " samples to process a Frame, not " + n);
        }
        if (this.a == 2) {
            w.a(this.int, array, this.if);
        }
        this.for.if(this.int, array);
        return true;
    }
    
    public static void a(final byte[] array, final int n, final float[] array2, final int n2, final int n3) {
        if (array.length - n < 2 * n3) {
            throw new IllegalArgumentException("Insufficient Samples to convert to floats");
        }
        if (array2.length - n2 < n3) {
            throw new IllegalArgumentException("Insufficient float buffer to convert the samples");
        }
        for (int i = 0; i < n3; ++i) {
            array2[n2 + i] = ((array[n + 2 * i] & 0xFF) | array[n + 2 * i + 1] << 8);
        }
    }
}
