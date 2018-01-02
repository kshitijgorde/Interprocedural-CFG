// 
// Decompiled by Procyon v0.5.30
// 

public final class b extends at
{
    public int a;
    public int b;
    
    public b() {
        this.a = 0;
        this.b = 0;
    }
    
    public final void a(final int n, final int b) {
        if (n < 32768) {
            return;
        }
        switch (n) {
            case 32768: {
                this.a = ((this.a & 0xFFF0) | (b & 0xF));
            }
            case 36864: {
                this.a = ((this.a & 0xFF0F) | (b & 0xF) << 4);
            }
            case 40960: {
                this.a = ((this.a & 0xF0FF) | (b & 0xF) << 8);
            }
            case 45056: {
                this.a = ((this.a & 0xFFF) | (b & 0xF) << 12);
            }
            case 49152: {
                this.b = b;
            }
            case 61440: {
                this.c(b * 2 + 0);
                this.d(b * 2 + 1);
                break;
            }
        }
    }
    
    public final int a() {
        return 73;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a() {
        this.a = 0;
        this.a(this.b = 0, 1, this.b() - 2, this.b() - 1);
    }
    
    public final int a(final int n) {
        if ((this.b & 0x2) != 0x0) {
            if (this.a > 65421) {
                this.b = 0;
                return 3;
            }
            this.a += 114;
        }
        return 0;
    }
}
