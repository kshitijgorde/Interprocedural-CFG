// 
// Decompiled by Procyon v0.5.30
// 

package ji.decode;

class o5 extends o2
{
    int a;
    int b;
    int c;
    int d;
    int e;
    
    o5(final byte[] array, final int a, final int n, final byte[] array2, final int b, final Object o, final String s) {
        super(o, s);
        this.e = n >> 3;
        this.a = a;
        this.b = b;
        this.c = a + this.e;
        if (this.e > 0) {
            while (this.b != this.c) {
                this.d = array2[this.b++];
                if ((this.d & 0x1) == 0x1) {
                    final int n2 = this.a++;
                    array[n2] |= (byte)128;
                }
                else {
                    ++this.a;
                }
                this.d >>= 1;
                if ((this.d & 0x1) == 0x1) {
                    final int n3 = this.a++;
                    array[n3] |= (byte)128;
                }
                else {
                    ++this.a;
                }
                this.d >>= 1;
                if ((this.d & 0x1) == 0x1) {
                    final int n4 = this.a++;
                    array[n4] |= (byte)128;
                }
                else {
                    ++this.a;
                }
                this.d >>= 1;
                if ((this.d & 0x1) == 0x1) {
                    final int n5 = this.a++;
                    array[n5] |= (byte)128;
                }
                else {
                    ++this.a;
                }
                this.d >>= 1;
                if ((this.d & 0x1) == 0x1) {
                    final int n6 = this.a++;
                    array[n6] |= (byte)128;
                }
                else {
                    ++this.a;
                }
                this.d >>= 1;
                if ((this.d & 0x1) == 0x1) {
                    final int n7 = this.a++;
                    array[n7] |= (byte)128;
                }
                else {
                    ++this.a;
                }
                this.d >>= 1;
                if ((this.d & 0x1) == 0x1) {
                    final int n8 = this.a++;
                    array[n8] |= (byte)128;
                }
                else {
                    ++this.a;
                }
                this.d >>= 1;
                if ((this.d & 0x1) == 0x1) {
                    final int n9 = this.a++;
                    array[n9] |= (byte)128;
                }
                else {
                    ++this.a;
                }
            }
        }
        this.e = n - (this.e << 3);
        if (this.e > 0) {
            this.d = array2[this.b];
            while (this.e > 0) {
                if ((this.d & 0x1) == 0x1) {
                    final int n10 = this.a++;
                    array[n10] |= (byte)128;
                }
                else {
                    ++this.a;
                }
                --this.e;
            }
        }
    }
}
