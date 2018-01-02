// 
// Decompiled by Procyon v0.5.30
// 

public final class Y extends at
{
    public final int a() {
        return 15;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a(final int n, final int n2) {
        if (n < 32768) {
            return;
        }
        switch (n) {
            case 32768: {
                if ((n2 & 0x80) != 0x0) {
                    this.c((n2 & 0x3F) * 2 + 1);
                    this.d((n2 & 0x3F) * 2 + 0);
                    this.e((n2 & 0x3F) * 2 + 3);
                    this.f((n2 & 0x3F) * 2 + 2);
                }
                else {
                    this.c((n2 & 0x3F) * 2 + 0);
                    this.d((n2 & 0x3F) * 2 + 1);
                    this.e((n2 & 0x3F) * 2 + 2);
                    this.f((n2 & 0x3F) * 2 + 3);
                }
                if ((n2 & 0x40) != 0x0) {
                    this.c();
                    return;
                }
                this.b();
            }
            case 32769: {
                if ((n2 & 0x80) != 0x0) {
                    this.e((n2 & 0x3F) * 2 + 1);
                    this.f((n2 & 0x3F) * 2 + 0);
                    return;
                }
                this.e((n2 & 0x3F) * 2 + 0);
                this.f((n2 & 0x3F) * 2 + 1);
            }
            case 32770: {
                if ((n2 & 0x80) != 0x0) {
                    this.c((n2 & 0x3F) * 2 + 1);
                    this.d((n2 & 0x3F) * 2 + 1);
                    this.e((n2 & 0x3F) * 2 + 1);
                    this.f((n2 & 0x3F) * 2 + 1);
                    return;
                }
                this.c((n2 & 0x3F) * 2);
                this.d((n2 & 0x3F) * 2);
                this.e((n2 & 0x3F) * 2);
                this.f((n2 & 0x3F) * 2);
            }
            case 32771: {
                if ((n2 & 0x80) != 0x0) {
                    this.e((n2 & 0x3F) * 2 + 1);
                    this.f((n2 & 0x3F) * 2 + 0);
                }
                else {
                    this.e((n2 & 0x3F) * 2 + 0);
                    this.f((n2 & 0x3F) * 2 + 1);
                }
                if ((n2 & 0x40) != 0x0) {
                    this.c();
                    return;
                }
                this.b();
                break;
            }
        }
    }
    
    public final void a() {
        this.a(0, 1, 2, 3);
    }
}
