// 
// Decompiled by Procyon v0.5.30
// 

package ji.decode;

class oq
{
    public short[][] a;
    private int b;
    public int c;
    
    oq() {
        this.a = null;
        this.b = 0;
        this.c = 0;
    }
    
    protected final int a(final short[] array) {
        short n = 999;
        if (this.a == null) {
            this.b = 0;
            this.c = 0;
            for (int i = 0; i < array.length; i += 3) {
                if (this.c < array[i + 1]) {
                    this.c = array[i + 1];
                }
                if (this.b < array[i]) {
                    this.b = array[i];
                }
                if (n > array[i + 1]) {
                    n = array[i + 1];
                }
            }
            if (this.c < 12) {
                this.c = 12;
            }
            if (this.b < 1) {
                this.b = 1;
            }
            this.a = new short[this.b + 1 + 50][this.c + 1 + 50];
            for (int j = 0; j < this.b + 1; ++j) {
                for (int k = 0; k < this.c + 1; ++k) {
                    this.a[j][k] = -9;
                }
            }
            for (int l = 0; l < array.length; l += 3) {
                this.a[array[l]][array[l + 1]] = array[l + 2];
            }
            this.a[1][12] = 32000;
            for (int n2 = 0; n2 < this.b + 1 + 50; ++n2) {
                for (int n3 = this.c + 1; n3 < this.c + 1 + 50; ++n3) {
                    this.a[n2][n3] = -9;
                }
            }
            for (int n4 = 12; n4 < this.c + 1 + 50; ++n4) {
                this.a[1][n4] = 32000;
            }
        }
        return n;
    }
}
