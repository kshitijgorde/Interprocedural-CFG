// 
// Decompiled by Procyon v0.5.30
// 

package b;

public class q
{
    float[] a;
    int case;
    float[] new;
    float[] char;
    float for;
    int try;
    g int;
    g do;
    y if;
    z byte;
    
    public q() {
        this.a = new float[234];
        this.new = new float[] { 0.9595f, 0.8413f, 0.6549f, 0.4154f, 0.1423f, -0.1423f, -0.4154f, -0.6549f, -0.8413f, -0.9595f };
        this.char = new float[10];
        this.int = new g();
        this.do = new g();
        this.if = new y();
        this.byte = new z();
    }
    
    public void a() {
        this.case = 154;
        j.if(this.a, 154);
        j.if(this.char, 10);
        this.for = 0.2f;
        this.try = 60;
        this.int.a = new Float(0.0f);
        this.do.a = new Float(0.0f);
        this.if.a();
    }
    
    public void a(final int[] array, int n, final int n2, final float[] array2, final int n3, final float[] array3, final af af) {
        final float[] array4 = new float[10];
        final float[] array5 = new float[40];
        final af af2 = new af();
        final af af3 = new af();
        final int n4 = array[n++];
        this.if.a(array, n, array4, n4);
        n += 2;
        ag.a(this.new, array4, array3);
        j.a(array4, this.new, 10);
        int n5 = 0;
        for (int i = 0; i < 80; i += 40) {
            final int n6 = array[n++];
            if (i == 0) {
                if (n4 + array[n++] == 0) {
                    v.a(n6, 20, 143, i, af2, af3);
                    this.try = af2.a;
                }
                else {
                    af2.a = new Integer(this.try);
                    af3.a = new Integer(0);
                    ++this.try;
                    if (this.try > 143) {
                        this.try = 143;
                    }
                }
                af.a = af2.a;
            }
            else if (n4 == 0) {
                v.a(n6, 20, 143, i, af2, af3);
                this.try = af2.a;
            }
            else {
                af2.a = new Integer(this.try);
                af3.a = new Integer(0);
                ++this.try;
                if (this.try > 143) {
                    this.try = 143;
                }
            }
            aa.a(this.a, this.case + i, af2.a, af3.a, 40);
            if (n4 != 0) {
                array[n + 0] = (j.a() & 0x1FFF);
                array[n + 1] = (j.a() & 0xF);
            }
            e.a(array[n + 1], array[n + 0], array5);
            n += 2;
            for (int j = af2.a; j < 40; ++j) {
                final float[] array6 = array5;
                final int n7 = j;
                array6[n7] += this.for * array5[j - af2.a];
            }
            this.byte.a(array[n++], array5, 40, n4, this.do, this.int);
            this.for = this.do.a;
            if (this.for > 0.7945f) {
                this.for = 0.7945f;
            }
            if (this.for < 0.2f) {
                this.for = 0.2f;
            }
            if (n4 != 0) {
                if (n2 == 0) {
                    for (int k = 0; k < 40; ++k) {
                        this.a[this.case + k + i] = this.int.a * array5[k];
                    }
                }
                else {
                    for (int l = 0; l < 40; ++l) {
                        this.a[this.case + l + i] *= this.do.a;
                    }
                }
            }
            else {
                for (int n8 = 0; n8 < 40; ++n8) {
                    this.a[this.case + n8 + i] = this.do.a * this.a[this.case + n8 + i] + this.int.a * array5[n8];
                }
            }
            ah.a(array3, n5, this.a, this.case + i, array2, n3 + i, 40, this.char, 0, 1);
            n5 += 11;
        }
        j.a(this.a, 80, this.a, 0, 154);
    }
}
