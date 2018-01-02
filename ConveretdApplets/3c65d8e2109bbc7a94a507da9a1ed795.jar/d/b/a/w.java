// 
// Decompiled by Procyon v0.5.30
// 

package d.b.a;

public class w
{
    public static final int int = 9;
    public static final float[] do;
    private float new;
    private float if;
    private float for;
    private float a;
    
    public w() {
        this.new = 1.0f;
        this.if = 0.5f;
        this.for = 1.0f;
        this.a = 1.0f;
    }
    
    public static void a(final ad ad, final float[] array, final int n) {
        float n2 = 0.0f;
        float n3 = 0.0f;
        float n4 = 0.0f;
        for (int i = 0; i < n; ++i) {
            n2 += array[2 * i] * array[2 * i];
            n3 += array[2 * i + 1] * array[2 * i + 1];
            array[i] = 0.5f * (array[2 * i] + array[2 * i + 1]);
            n4 += array[i] * array[i];
        }
        final float n5 = (n2 + 1.0f) / (n3 + 1.0f);
        final float n6 = n4 / (1.0f + n2 + n3);
        ad.a(14, 5);
        ad.a(9, 4);
        final float n7 = (float)(4.0 * Math.log(n5));
        if (n7 > 0.0f) {
            ad.a(0, 1);
        }
        else {
            ad.a(1, 1);
        }
        float n8 = (float)Math.floor(0.5f + Math.abs(n7));
        if (n8 > 30.0f) {
            n8 = 31.0f;
        }
        ad.a((int)n8, 5);
        ad.a(r.a(n6, w.do, 4), 2);
    }
    
    public void a(final float[] array, final int n) {
        float n2 = 0.0f;
        for (int i = n - 1; i >= 0; --i) {
            n2 += array[i] * array[i];
        }
        final float n3 = n2 / this.if;
        final float n4 = n3 * this.new / (1.0f + this.new);
        final float n5 = n3 - n4;
        final float n6 = (float)Math.sqrt(n4 / (n2 + 0.01f));
        final float n7 = (float)Math.sqrt(n5 / (n2 + 0.01f));
        for (int j = n - 1; j >= 0; --j) {
            final float n8 = array[j];
            this.for = 0.98f * this.for + 0.02f * n6;
            this.a = 0.98f * this.a + 0.02f * n7;
            array[2 * j] = this.for * n8;
            array[2 * j + 1] = this.a * n8;
        }
    }
    
    public void a(final ad ad) {
        float n = 1.0f;
        if (ad.a(1) != 0) {
            n = -1.0f;
        }
        this.new = (float)Math.exp(n * 0.25 * ad.a(5));
        this.if = w.do[ad.a(2)];
    }
    
    static {
        do = new float[] { 0.25f, 0.315f, 0.397f, 0.5f };
    }
}
