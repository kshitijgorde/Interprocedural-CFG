// 
// Decompiled by Procyon v0.5.30
// 

public final class ab extends ad
{
    private int l;
    private int m;
    private int[] n;
    
    public ab() {
        this.l = 0;
        this.m = 0;
        this.n = new int[9];
    }
    
    public final void a(final U u) {
        super.a(u);
        this.a();
    }
    
    public final void a(final int n, final short n2) {
        if (n < 32768) {
            super.a(n, n2);
            return;
        }
        switch (n & 0xF0CF) {
            case 32768: {
                if ((this.n[8] & 0x2) != 0x0) {
                    this.g(n2, 49152);
                    return;
                }
                this.g(n2, 32768);
            }
            case 40960: {
                this.g(n2, 40960);
            }
            case 36864: {
                final short n3;
                if ((n3 = (short)(n2 & 0x3)) == 0) {
                    this.a.c.a(0);
                    return;
                }
                if (n3 == 1) {
                    this.a.c.a(1);
                    return;
                }
                if (n3 == 2) {
                    this.a.c.a(3);
                    return;
                }
                this.a.c.a(4);
            }
            case 36866:
            case 36992: {
                this.n[8] = n2;
            }
            case 45056: {
                this.e(this.n[0] = ((this.n[0] & 0xF0) | (n2 & 0xF)), 0);
            }
            case 45058:
            case 45120: {
                this.e(this.n[0] = ((this.n[0] & 0xF) | (n2 & 0xF) << 4), 0);
            }
            case 45057:
            case 45060:
            case 45184: {
                this.e(this.n[1] = ((this.n[1] & 0xF0) | (n2 & 0xF)), 1024);
            }
            case 45059:
            case 45062:
            case 45248: {
                this.e(this.n[1] = ((this.n[1] & 0xF) | (n2 & 0xF) << 4), 1024);
            }
            case 49152: {
                this.e(this.n[2] = ((this.n[2] & 0xF0) | (n2 & 0xF)), 2048);
            }
            case 49154:
            case 49216: {
                this.e(this.n[2] = ((this.n[2] & 0xF) | (n2 & 0xF) << 4), 2048);
            }
            case 49153:
            case 49156:
            case 49280: {
                this.e(this.n[3] = ((this.n[3] & 0xF0) | (n2 & 0xF)), 3072);
            }
            case 49155:
            case 49158:
            case 49344: {
                this.e(this.n[3] = ((this.n[3] & 0xF) | (n2 & 0xF) << 4), 3072);
            }
            case 53248: {
                this.e(this.n[4] = ((this.n[4] & 0xF0) | (n2 & 0xF)), 4096);
            }
            case 53250:
            case 53312: {
                this.e(this.n[4] = ((this.n[4] & 0xF) | (n2 & 0xF) << 4), 4096);
            }
            case 53249:
            case 53252:
            case 53376: {
                this.e(this.n[5] = ((this.n[5] & 0xF0) | (n2 & 0xF)), 5120);
            }
            case 53251:
            case 53254:
            case 53440: {
                this.e(this.n[5] = ((this.n[5] & 0xF) | (n2 & 0xF) << 4), 5120);
            }
            case 57344: {
                this.e(this.n[6] = ((this.n[6] & 0xF0) | (n2 & 0xF)), 6144);
            }
            case 57346:
            case 57408: {
                this.e(this.n[6] = ((this.n[6] & 0xF) | (n2 & 0xF) << 4), 6144);
            }
            case 57345:
            case 57348:
            case 57472: {
                this.e(this.n[7] = ((this.n[7] & 0xF0) | (n2 & 0xF)), 7168);
            }
            case 57347:
            case 57350:
            case 57536: {
                this.e(this.n[7] = ((this.n[7] & 0xF) | (n2 & 0xF) << 4), 7168);
            }
            case 61440: {
                this.l = ((this.l & 0xF0) | (n2 & 0xF));
            }
            case 61442:
            case 61504: {
                this.l = ((this.l & 0xF) | (n2 & 0xF) << 4);
            }
            case 61443:
            case 61632: {
                this.m = (this.m & 0x1) * 3;
            }
            case 61444:
            case 61568: {
                this.m = (n2 & 0x3);
                break;
            }
        }
    }
    
    public final void a(final a a) {
        if (!a.a()) {
            System.out.println("VRC4: Invalid ROM! Unable to load.");
            return;
        }
        final int n = a.b() << 1;
        this.g(0, 32768);
        this.g(1, 40960);
        this.g(n - 2, 49152);
        this.g(n - 1, 57344);
        this.h();
        this.c();
        this.a.b.a(2);
    }
    
    public final void a() {
        this.n[0] = 0;
        this.n[1] = 1;
        this.n[2] = 2;
        this.n[3] = 3;
        this.n[4] = 4;
        this.n[5] = 5;
        this.n[6] = 6;
        this.n[7] = 7;
        this.n[8] = 0;
        this.m = 0;
        this.l = 0;
    }
}
