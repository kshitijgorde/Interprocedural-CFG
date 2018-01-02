// 
// Decompiled by Procyon v0.5.30
// 

package JAVACharter.b;

public class h extends e
{
    private float[] byte;
    
    public h() {
        this.byte = new float[0];
    }
    
    public float[] case() {
        final float[] array = new float[this.a()];
        System.arraycopy(this.byte, super.if, array, 0, this.a());
        return array;
    }
    
    public float for(final int n) {
        try {
            return this.byte[super.if + n];
        }
        catch (Exception ex) {
            return 9.223372E18f;
        }
    }
    
    public float int(final int n) {
        try {
            return this.byte[n];
        }
        catch (Exception ex) {
            return 9.223372E18f;
        }
    }
    
    public int for() {
        return this.byte.length;
    }
    
    public void if(final int n, final Object o) {
        final float[] array = (float[])o;
        final float[] byte1 = new float[array.length + this.byte.length];
        System.arraycopy(this.byte, 0, byte1, 0, n);
        System.arraycopy(array, 0, byte1, n, array.length);
        System.arraycopy(this.byte, n, byte1, n + array.length, this.byte.length - n);
        this.byte = byte1;
    }
    
    public void a(final int n, final Object o) {
        if (n < this.byte.length) {
            this.byte[n] = (float)o;
        }
    }
    
    public void a(final Object o, final int n, final int n2) {
        final float[] array = (float[])o;
        final float[] byte1 = new float[n2 + this.byte.length];
        System.arraycopy(this.byte, 0, byte1, 0, this.byte.length);
        System.arraycopy(array, n, byte1, this.byte.length, n2);
        this.byte = byte1;
    }
    
    public void a(final Object o, final int n) {
        final float[] array = (float[])o;
        final float[] byte1 = new float[n + this.byte.length];
        System.arraycopy(array, 0, byte1, 0, n);
        System.arraycopy(this.byte, 0, byte1, n, this.byte.length);
        this.byte = byte1;
    }
    
    public void if() {
        final float[] array = this.byte;
        final float[] byte1 = new float[this.byte.length - 1];
        System.arraycopy(array, 1, byte1, 0, array.length - 1);
        this.byte = byte1;
        if (super.int >= array.length - 1) {
            this.a(super.if - 1, super.int);
        }
    }
    
    public void do() {
        final float[] array = this.byte;
        final float[] byte1 = new float[this.byte.length - 1];
        System.arraycopy(array, 0, byte1, 0, array.length - 1);
        this.byte = byte1;
        if (super.int >= array.length - 1) {
            this.a(super.if, super.int - 1);
        }
    }
}
