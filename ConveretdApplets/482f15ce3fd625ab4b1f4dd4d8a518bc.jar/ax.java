// 
// Decompiled by Procyon v0.5.30
// 

public final class ax extends at
{
    private int[] a;
    private int[] b;
    
    public ax() {
        this.a = new int[11];
        this.b = new int[4];
    }
    
    public final int a() {
        return 251;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a(final int n, final int n2) {
        switch (n & 0xE001) {
            case 24576: {
                if (this.a[9] == 0) {
                    break;
                }
                this.b[this.a[10]++] = n2;
                if (this.a[10] == 4) {
                    this.a[10] = 0;
                    this.f();
                    return;
                }
                break;
            }
            case 32768: {
                this.a[8] = n2;
                this.f();
            }
            case 32769: {
                this.a[this.a[8] & 0x7] = n2;
                this.f();
            }
            case 40961: {
                if ((n2 & 0x80) != 0x0) {
                    this.a[9] = 1;
                    this.a[10] = 0;
                    return;
                }
                this.a[9] = 0;
                break;
            }
        }
    }
    
    public final void a() {
        this.a(0, 1, this.b() - 2, this.b() - 1);
        this.b();
        for (int i = 0; i < this.a.length; ++i) {
            this.a[i] = 0;
        }
        for (int j = 0; j < this.b.length; ++j) {
            this.b[j] = 0;
        }
    }
    
    private void f() {
        final int[] array = new int[6];
        final int[] array2 = new int[4];
        for (int i = 0; i < array.length; ++i) {
            array[i] = ((this.a[i] | this.b[1] << 4) & (this.b[2] << 4 | 0xF));
        }
        if ((this.a[8] & 0x80) != 0x0) {
            this.a(array[2], array[3], array[4], array[5], array[0], array[0] + 1, array[1], array[1] + 1);
        }
        else {
            this.a(array[0], array[0] + 1, array[1], array[1] + 1, array[2], array[3], array[4], array[5]);
        }
        array2[0] = ((this.a[6] & ((this.b[3] & 0x3F) ^ 0x3F)) | this.b[1]);
        array2[1] = ((this.a[7] & ((this.b[3] & 0x3F) ^ 0x3F)) | this.b[1]);
        array2[2] = (array2[3] = (((this.b[3] & 0x3F) ^ 0x3F) | this.b[1]));
        final int[] array3 = array2;
        final int n = 2;
        array3[n] &= this.b() - 1;
        if ((this.a[8] & 0x40) != 0x0) {
            this.a(array2[2], array2[1], array2[0], array2[3]);
            return;
        }
        this.a(array2[0], array2[1], array2[2], array2[3]);
    }
}
