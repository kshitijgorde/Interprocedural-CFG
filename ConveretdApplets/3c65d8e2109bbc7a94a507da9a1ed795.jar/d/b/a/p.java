// 
// Decompiled by Procyon v0.5.30
// 

package d.b.a;

public class p
{
    public static final int d = 5;
    public static final int int = 6000;
    public static final float b = 0.3f;
    public static final float[][] e;
    public static final float[][] else;
    public static final float[][] case;
    private float a;
    private float do;
    private float byte;
    private float[] try;
    private float void;
    private float for;
    private float c;
    private float long;
    private float goto;
    private float char;
    private float if;
    private int new;
    
    public p() {
        this.do = 0.0f;
        this.byte = 1.0f;
        this.void = 0.0f;
        this.a = 0.1f;
        this.c = 0.0f;
        this.for = 0.0f;
        this.long = 0.0f;
        this.char = (float)(0.05 * Math.pow(6000.0, 0.30000001192092896));
        this.if = 0.05f;
        this.goto = this.char / this.if;
        this.new = 0;
        this.try = new float[5];
        for (int i = 0; i < 5; ++i) {
            this.try[i] = (float)Math.log(6000.0);
        }
    }
    
    public float a(final float[] array, final int n, final int n2, final float for1) {
        float n3 = 0.0f;
        float n4 = 0.0f;
        float n5 = 7.0f;
        float n6 = 0.0f;
        for (int i = 0; i < n >> 1; ++i) {
            n3 += array[i] * array[i];
        }
        for (int j = n >> 1; j < n; ++j) {
            n4 += array[j] * array[j];
        }
        final float byte1 = n3 + n4;
        final float n7 = (float)Math.log(byte1 + 6000.0f);
        for (int k = 0; k < 5; ++k) {
            n6 += (n7 - this.try[k]) * (n7 - this.try[k]);
        }
        float n8 = n6 / 150.0f;
        if (n8 > 1.0f) {
            n8 = 1.0f;
        }
        final float n9 = 3.0f * (for1 - 0.4f) * Math.abs(for1 - 0.4f);
        this.do = (1.0f - this.a) * this.do + this.a * byte1;
        this.goto = this.char / this.if;
        final float n10 = (float)Math.pow(byte1, 0.30000001192092896);
        if (this.if < 0.06f && byte1 > 6000.0f) {
            this.char = 0.05f * n10;
        }
        if ((n9 < 0.3f && n8 < 0.2f && n10 < 1.2f * this.goto) || (n9 < 0.3f && n8 < 0.05f && n10 < 1.5f * this.goto) || (n9 < 0.4f && n8 < 0.05f && n10 < 1.2f * this.goto) || (n9 < 0.0f && n8 < 0.05f)) {
            ++this.new;
            float n11;
            if (n10 > 3.0f * this.goto) {
                n11 = 3.0f * this.goto;
            }
            else {
                n11 = n10;
            }
            if (this.new >= 4) {
                this.char = 0.95f * this.char + 0.05f * n11;
                this.if = 0.95f * this.if + 0.05f;
            }
        }
        else {
            this.new = 0;
        }
        if (n10 < this.goto && byte1 > 6000.0f) {
            this.char = 0.95f * this.char + 0.05f * n10;
            this.if = 0.95f * this.if + 0.05f;
        }
        if (byte1 < 30000.0f) {
            n5 -= 0.7f;
            if (byte1 < 10000.0f) {
                n5 -= 0.7f;
            }
            if (byte1 < 3000.0f) {
                n5 -= 0.7f;
            }
        }
        else {
            float n12 = (float)Math.log((byte1 + 1.0f) / (1.0f + this.byte));
            float n13 = (float)Math.log((byte1 + 1.0f) / (1.0f + this.do));
            if (n13 < -5.0f) {
                n13 = -5.0f;
            }
            if (n13 > 2.0f) {
                n13 = 2.0f;
            }
            if (n13 > 0.0f) {
                n5 += 0.6f * n13;
            }
            if (n13 < 0.0f) {
                n5 += 0.5f * n13;
            }
            if (n12 > 0.0f) {
                if (n12 > 5.0f) {
                    n12 = 5.0f;
                }
                n5 += 0.5f * n12;
            }
            if (n4 > 1.6f * n3) {
                n5 += 0.5f;
            }
        }
        this.byte = byte1;
        this.c = 0.6f * this.c + 0.4f * for1;
        float long1 = (float)(n5 + 2.200000047683716 * (for1 - 0.4 + (this.c - 0.4)));
        if (long1 < this.long) {
            long1 = 0.5f * long1 + 0.5f * this.long;
        }
        if (long1 < 4.0f) {
            long1 = 4.0f;
        }
        if (long1 > 10.0f) {
            long1 = 10.0f;
        }
        if (this.new >= 3) {
            long1 = 4.0f;
        }
        if (this.new != 0) {
            long1 -= (float)(1.0 * (Math.log(3.0 + this.new) - Math.log(3.0)));
        }
        if (long1 < 0.0f) {
            long1 = 0.0f;
        }
        if (byte1 < 60000.0f) {
            if (this.new > 2) {
                long1 -= (float)(0.5 * (Math.log(3.0 + this.new) - Math.log(3.0)));
            }
            if (byte1 < 10000.0f && this.new > 2) {
                long1 -= (float)(0.5 * (Math.log(3.0 + this.new) - Math.log(3.0)));
            }
            if (long1 < 0.0f) {
                long1 = 0.0f;
            }
            long1 += (float)(0.3 * Math.log(byte1 / 60000.0));
        }
        if (long1 < -1.0f) {
            long1 = -1.0f;
        }
        this.for = for1;
        this.long = long1;
        for (int l = 4; l > 0; --l) {
            this.try[l] = this.try[l - 1];
        }
        this.try[0] = n7;
        return long1;
    }
    
    static {
        e = new float[][] { { -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f }, { 3.5f, 2.5f, 2.0f, 1.2f, 0.5f, 0.0f, -0.5f, -0.7f, -0.8f, -0.9f, -1.0f }, { 10.0f, 6.5f, 5.2f, 4.5f, 3.9f, 3.5f, 3.0f, 2.5f, 2.3f, 1.8f, 1.0f }, { 11.0f, 8.8f, 7.5f, 6.5f, 5.0f, 3.9f, 3.9f, 3.9f, 3.5f, 3.0f, 1.0f }, { 11.0f, 11.0f, 9.9f, 9.0f, 8.0f, 7.0f, 6.5f, 6.0f, 5.0f, 4.0f, 2.0f }, { 11.0f, 11.0f, 11.0f, 11.0f, 9.5f, 9.0f, 8.0f, 7.0f, 6.5f, 5.0f, 3.0f }, { 11.0f, 11.0f, 11.0f, 11.0f, 11.0f, 11.0f, 9.5f, 8.5f, 8.0f, 6.5f, 4.0f }, { 11.0f, 11.0f, 11.0f, 11.0f, 11.0f, 11.0f, 11.0f, 11.0f, 9.8f, 7.5f, 5.5f }, { 8.0f, 5.0f, 3.7f, 3.0f, 2.5f, 2.0f, 1.8f, 1.5f, 1.0f, 0.0f, 0.0f } };
        else = new float[][] { { -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f }, { -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f }, { 11.0f, 11.0f, 9.5f, 8.5f, 7.5f, 6.0f, 5.0f, 3.9f, 3.0f, 2.0f, 1.0f }, { 11.0f, 11.0f, 11.0f, 11.0f, 11.0f, 9.5f, 8.7f, 7.8f, 7.0f, 6.5f, 4.0f }, { 11.0f, 11.0f, 11.0f, 11.0f, 11.0f, 11.0f, 11.0f, 11.0f, 9.8f, 7.5f, 5.5f } };
        case = new float[][] { { -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f }, { 3.9f, 2.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f } };
    }
}
