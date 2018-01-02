// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

public final class N
{
    private static float[] a;
    private float b;
    private float c;
    private float d;
    private float e;
    
    public N() {
        this.b = 1.0f;
        this.c = 0.5f;
        this.d = 1.0f;
        this.e = 1.0f;
    }
    
    public static void a(final cH ch, final float[] array, final int n) {
        float n2 = 0.0f;
        float n3 = 0.0f;
        float n4 = 0.0f;
        for (int i = 0; i < n; ++i) {
            n2 += array[i * 2] * array[i * 2];
            n3 += array[i * 2 + 1] * array[i * 2 + 1];
            array[i] = 0.5f * (array[i * 2] + array[i * 2 + 1]);
            n4 += array[i] * array[i];
        }
        final float n5 = (n2 + 1.0f) / (n3 + 1.0f);
        final float n6 = n4 / (n2 + 1.0f + n3);
        ch.a(14, 5);
        ch.a(9, 4);
        final float n7;
        if ((n7 = (float)(4.0 * Math.log(n5))) > 0.0f) {
            ch.a(0, 1);
        }
        else {
            ch.a(1, 1);
        }
        float n8;
        if ((n8 = (float)Math.floor(0.5f + Math.abs(n7))) > 30.0f) {
            n8 = 31.0f;
        }
        ch.a((int)n8, 5);
        ch.a(aK.a(n6, N.a, 4), 2);
    }
    
    public final void a(final float[] array, final int n) {
        float n2 = 0.0f;
        for (int i = n - 1; i >= 0; --i) {
            n2 += array[i] * array[i];
        }
        final float n4;
        final float n3 = (n4 = n2 / this.c) * this.b / (1.0f + this.b);
        final float n5 = n4 - n3;
        final float n6 = (float)Math.sqrt(n3 / (n2 + 0.01f));
        final float n7 = (float)Math.sqrt(n5 / (n2 + 0.01f));
        for (int j = n - 1; j >= 0; --j) {
            final float n8 = array[j];
            this.d = 0.98f * this.d + 0.02f * n6;
            this.e = 0.98f * this.e + 0.02f * n7;
            array[j * 2] = this.d * n8;
            array[j * 2 + 1] = this.e * n8;
        }
    }
    
    public final void a(final cH ch) {
        float n = 1.0f;
        if (ch.b(1) != 0) {
            n = -1.0f;
        }
        this.b = (float)Math.exp(n * 0.25 * ch.b(5));
        this.c = N.a[ch.b(2)];
    }
    
    static {
        N.a = new float[] { 0.25f, 0.315f, 0.397f, 0.5f };
    }
}
