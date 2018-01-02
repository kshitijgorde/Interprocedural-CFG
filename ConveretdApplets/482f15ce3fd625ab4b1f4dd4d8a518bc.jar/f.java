// 
// Decompiled by Procyon v0.5.30
// 

public final class f extends at
{
    public int[] a;
    public int a;
    public int b;
    
    public f() {
        this.a = new int[1];
        this.a = 0;
        this.b = 0;
    }
    
    public final int a() {
        return 245;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a() {
        this.a(0, 1, this.b() - 2, this.b() - 1);
        this.a[0] = 0;
        this.a = 0;
        this.b = 0;
    }
    
    public final void a(final int n, final int b) {
        if (n < 32768) {
            return;
        }
        switch (n & 0xF007) {
            case 32768: {
                this.a[0] = b;
            }
            case 32769: {
                switch (this.a[0] & 0x7) {
                    case 6: {
                        this.c(b);
                        break;
                    }
                    case 7: {
                        this.d(b);
                        break;
                    }
                }
            }
            case 40960: {}
            case 40961: {
                this.b = b;
            }
            case 57344: {
                this.a = ((this.a & 0xFF00) | b);
            }
            case 57345: {
                this.a = ((this.a & 0xFF) | b << 8);
                break;
            }
        }
    }
    
    public final int a(final int n) {
        if (this.b != 0 && n < 241) {
            this.a -= 114;
            if (this.a <= 0) {
                this.b = 0;
                return 3;
            }
        }
        return 0;
    }
}
