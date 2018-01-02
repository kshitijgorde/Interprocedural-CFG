// 
// Decompiled by Procyon v0.5.30
// 

public class cf extends n
{
    int a;
    int b;
    
    void a(final g g, final a9 a9, final float[] array, final l l) {
        final float[] ab = g.ab;
        final float[] ad = g.ad;
        final int[] i = a9.i;
        final int n = l.c >> 1;
        final int n2 = l.d >> 1;
        int n3 = 0;
        while (true) {
            Label_0279: {
                if (!c.l) {
                    break Label_0279;
                }
                final int n4 = n3 * 2;
                int n5 = n3 * 2;
                final float[] array2 = ab;
                final int a10 = i[n4];
                this.a = a10;
                final float n6 = array2[a10];
                final float n7 = ab[this.a + 1];
                final float n8 = ab[this.a + 2];
                final float n9 = -n6;
                final float[] array3 = ad;
                final int b = i[n4 + 1];
                this.b = b;
                final float n10 = array3[b];
                final float n11 = ad[this.b + 1];
                final float n12 = ad[this.b + 2] - 1.0f;
                final float n13 = 2.0f * (n11 * n7 + n10 * n9 + n12 * n8) / (n11 * n11 + n10 * n10 + n12 * n12);
                final float n14 = n7 - n13 * n11;
                final float n15 = n9 - n13 * n10;
                final float n16 = n8 - n13 * n12;
                final float n17 = (float)Math.sqrt(n14 * n14 + n15 * n15 + n16 * n16) + 0.1f;
                array[n5++] = n * (n14 / n17 + 1.0f);
                array[n5++] = n2 * (n15 / n17 + 1.0f);
                ++n3;
            }
            if (n3 >= a9.g) {
                return;
            }
            continue;
        }
    }
}
