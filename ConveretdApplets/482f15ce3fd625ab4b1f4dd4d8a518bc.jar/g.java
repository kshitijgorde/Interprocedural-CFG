// 
// Decompiled by Procyon v0.5.30
// 

public final class g extends at
{
    public int[] a;
    
    public g() {
        this.a = new int[4];
    }
    
    public final int a() {
        return 243;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a() {
        this.a(0, 1, 2, 3);
        if (this.c() > 32) {
            this.a(24, 25, 26, 27, 28, 29, 30, 31);
        }
        this.c();
        this.a[0] = 0;
        this.a[1] = 0;
        this.a[2] = 3;
        this.a[3] = 0;
    }
    
    public final void b(final int n, final int n2) {
        if ((n & 0x4101) == 0x4100) {
            this.a[0] = n2;
            return;
        }
        if ((n & 0x4101) == 0x4101) {
            switch (this.a[0] & 0x7) {
                case 0: {
                    this.a[1] = 0;
                    this.a[2] = 3;
                    break;
                }
                case 4: {
                    this.a[2] = ((this.a[2] & 0x6) | (n2 & 0x1));
                    break;
                }
                case 5: {
                    this.a[1] = (n2 & 0x1);
                    break;
                }
                case 6: {
                    this.a[2] = ((this.a[2] & 0x1) | (n2 & 0x3) << 1);
                    break;
                }
                case 7: {
                    this.a[3] = (n2 & 0x1);
                    break;
                }
            }
            this.a(this.a[1] * 4 + 0, this.a[1] * 4 + 1, this.a[1] * 4 + 2, this.a[1] * 4 + 3);
            this.a(this.a[2] * 8 + 0, this.a[2] * 8 + 1, this.a[2] * 8 + 2, this.a[2] * 8 + 3, this.a[2] * 8 + 4, this.a[2] * 8 + 5, this.a[2] * 8 + 6, this.a[2] * 8 + 7);
            if (this.a[3] != 0) {
                this.b();
                return;
            }
            this.c();
        }
    }
    
    public final void a(final int n, final int n2) {
        if (n >= 32768) {
            return;
        }
        if ((n & 0x4101) == 0x4100) {
            this.a[0] = n2;
            return;
        }
        if ((n & 0x4101) == 0x4101) {
            switch (this.a[0] & 0x7) {
                case 0: {
                    this.a[1] = 0;
                    this.a[2] = 3;
                    break;
                }
                case 4: {
                    this.a[2] = ((this.a[2] & 0x6) | (n2 & 0x1));
                    break;
                }
                case 5: {
                    this.a[1] = (n2 & 0x1);
                    break;
                }
                case 6: {
                    this.a[2] = ((this.a[2] & 0x1) | (n2 & 0x3) << 1);
                    break;
                }
                case 7: {
                    this.a[3] = (n2 & 0x1);
                    break;
                }
            }
            this.a(this.a[1] * 4 + 0, this.a[1] * 4 + 1, this.a[1] * 4 + 2, this.a[1] * 4 + 3);
            this.a(this.a[2] * 8 + 0, this.a[2] * 8 + 1, this.a[2] * 8 + 2, this.a[2] * 8 + 3, this.a[2] * 8 + 4, this.a[2] * 8 + 5, this.a[2] * 8 + 6, this.a[2] * 8 + 7);
            if (this.a[3] != 0) {
                this.b();
                return;
            }
            this.c();
        }
    }
}
