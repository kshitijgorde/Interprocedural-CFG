// 
// Decompiled by Procyon v0.5.30
// 

public final class aM extends at
{
    private int a;
    private int b;
    private int c;
    private int[] a;
    
    public aM() {
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.a = new int[9];
    }
    
    public final int a() {
        return 21;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a() {
        this.a(0, 1, this.b() - 2, this.b() - 1);
        this.a[0] = 0;
        this.a[1] = 1;
        this.a[2] = 2;
        this.a[3] = 3;
        this.a[4] = 4;
        this.a[5] = 5;
        this.a[6] = 6;
        this.a[7] = 7;
        this.a[8] = 0;
        this.c = 0;
        this.b = 0;
        this.a = 0;
    }
    
    public final void a(final int n, int n2) {
        if (n < 32768) {
            return;
        }
        switch (n & 0xF0CF) {
            case 32768: {
                if ((this.a[8] & 0x2) != 0x0) {
                    this.e(n2);
                    return;
                }
                this.c(n2);
            }
            case 40960: {
                this.d(n2);
            }
            case 36864: {
                if ((n2 &= 0x3) == 0x0) {
                    this.b();
                    return;
                }
                if (n2 == 1) {
                    this.c();
                    return;
                }
                if (n2 == 2) {
                    this.b(0, 0, 0, 0);
                    return;
                }
                this.b(1, 1, 1, 1);
            }
            case 36866:
            case 36992: {
                this.a[8] = n2;
            }
            case 45056: {
                this.g(this.a[0] = ((this.a[0] & 0xF0) | (n2 & 0xF)));
            }
            case 45058:
            case 45120: {
                this.g(this.a[0] = ((this.a[0] & 0xF) | (n2 & 0xF) << 4));
            }
            case 45057:
            case 45060:
            case 45184: {
                this.h(this.a[1] = ((this.a[1] & 0xF0) | (n2 & 0xF)));
            }
            case 45059:
            case 45062:
            case 45248: {
                this.h(this.a[1] = ((this.a[1] & 0xF) | (n2 & 0xF) << 4));
            }
            case 49152: {
                this.i(this.a[2] = ((this.a[2] & 0xF0) | (n2 & 0xF)));
            }
            case 49154:
            case 49216: {
                this.i(this.a[2] = ((this.a[2] & 0xF) | (n2 & 0xF) << 4));
            }
            case 49153:
            case 49156:
            case 49280: {
                this.j(this.a[3] = ((this.a[3] & 0xF0) | (n2 & 0xF)));
            }
            case 49155:
            case 49158:
            case 49344: {
                this.j(this.a[3] = ((this.a[3] & 0xF) | (n2 & 0xF) << 4));
            }
            case 53248: {
                this.k(this.a[4] = ((this.a[4] & 0xF0) | (n2 & 0xF)));
            }
            case 53250:
            case 53312: {
                this.k(this.a[4] = ((this.a[4] & 0xF) | (n2 & 0xF) << 4));
            }
            case 53249:
            case 53252:
            case 53376: {
                this.l(this.a[5] = ((this.a[5] & 0xF0) | (n2 & 0xF)));
            }
            case 53251:
            case 53254:
            case 53440: {
                this.l(this.a[5] = ((this.a[5] & 0xF) | (n2 & 0xF) << 4));
            }
            case 57344: {
                this.m(this.a[6] = ((this.a[6] & 0xF0) | (n2 & 0xF)));
            }
            case 57346:
            case 57408: {
                this.m(this.a[6] = ((this.a[6] & 0xF) | (n2 & 0xF) << 4));
            }
            case 57345:
            case 57348:
            case 57472: {
                this.n(this.a[7] = ((this.a[7] & 0xF0) | (n2 & 0xF)));
            }
            case 57347:
            case 57350:
            case 57536: {
                this.n(this.a[7] = ((this.a[7] & 0xF) | (n2 & 0xF) << 4));
            }
            case 61440: {
                this.b = ((this.b & 0xF0) | (n2 & 0xF));
            }
            case 61442:
            case 61504: {
                this.b = ((this.b & 0xF) | (n2 & 0xF) << 4);
            }
            case 61443:
            case 61632: {
                this.c = (this.c & 0x1) * 3;
            }
            case 61444:
            case 61568: {
                this.c = (n2 & 0x3);
                if ((this.c & 0x2) != 0x0) {
                    this.a = this.b;
                    break;
                }
                break;
            }
        }
    }
    
    public final int a(final int n) {
        if ((this.c & 0x2) != 0x0) {
            if (this.a == 0) {
                this.a = this.b;
                this.c = (this.c & 0x1) * 3;
                return 3;
            }
            ++this.a;
        }
        return 0;
    }
}
