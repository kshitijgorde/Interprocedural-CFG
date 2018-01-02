// 
// Decompiled by Procyon v0.5.30
// 

package JAVACharter.b;

public class c extends e
{
    private long[] case;
    
    public c() {
        this.case = new long[0];
    }
    
    public long new(final int n) {
        try {
            return this.case[super.if + n];
        }
        catch (Exception ex) {
            return Long.MAX_VALUE;
        }
    }
    
    public long try(final int n) {
        try {
            return this.case[n];
        }
        catch (Exception ex) {
            return Long.MAX_VALUE;
        }
    }
    
    public long[] char() {
        final long[] array = new long[this.a()];
        System.arraycopy(this.case, super.if, array, 0, this.a());
        return array;
    }
    
    public int for() {
        return this.case.length;
    }
    
    public void if(final int n, final Object o) {
        final long[] array = (long[])o;
        final long[] case1 = new long[array.length + this.case.length];
        System.arraycopy(this.case, 0, case1, 0, n);
        System.arraycopy(array, 0, case1, n, array.length);
        System.arraycopy(this.case, n, case1, n + array.length, this.case.length - n);
        this.case = case1;
    }
    
    public void a(final int n, final Object o) {
        if (n < this.case.length) {
            this.case[n] = (long)o;
        }
    }
    
    public void a(final Object o, final int n, final int n2) {
        final long[] array = (long[])o;
        final long[] case1 = new long[n2 + this.case.length];
        System.arraycopy(this.case, 0, case1, 0, this.case.length);
        System.arraycopy(array, n, case1, this.case.length, n2);
        this.case = case1;
    }
    
    public void a(final Object o, final int n) {
        final long[] array = (long[])o;
        final long[] case1 = new long[n + this.case.length];
        System.arraycopy(array, 0, case1, 0, n);
        System.arraycopy(this.case, 0, case1, n, this.case.length);
        this.case = case1;
    }
    
    public void if() {
        final long[] array = this.case;
        final long[] case1 = new long[this.case.length - 1];
        System.arraycopy(array, 1, case1, 0, array.length - 1);
        this.case = case1;
        if (super.int >= array.length - 1) {
            this.a(super.if - 1, super.int);
        }
    }
    
    public void do() {
        final long[] array = this.case;
        final long[] case1 = new long[this.case.length - 1];
        System.arraycopy(array, 0, case1, 0, array.length - 1);
        this.case = case1;
        if (super.int > array.length - 1) {
            this.a(super.if, super.int - 1);
        }
    }
}
