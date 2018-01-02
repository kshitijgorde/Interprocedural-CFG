// 
// Decompiled by Procyon v0.5.30
// 

public final class V extends ad
{
    private int l;
    private int m;
    private int[] n;
    private int o;
    
    public V() {
        this.l = 0;
        this.m = 0;
        this.n = new int[9];
        this.o = 65535;
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
        switch (n & this.o) {
            case 32768:
            case 32772:
            case 32776:
            case 32780: {
                if (this.n[8] != 0) {
                    this.g(n2, 49152);
                    return;
                }
                this.g(n2, 32768);
            }
            case 36864: {
                if (n2 == 255) {
                    break;
                }
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
            case 36872: {
                this.n[8] = (n2 & 0x2);
            }
            case 40960:
            case 40964:
            case 40968:
            case 40972: {
                this.g(n2, 40960);
            }
            case 45056: {
                this.e(this.n[0] = ((this.n[0] & 0xF0) | (n2 & 0xF)), 0);
            }
            case 45057:
            case 45060: {
                this.e(this.n[0] = ((this.n[0] & 0xF) | (n2 & 0xF) << 4), 0);
            }
            case 45058:
            case 45064: {
                this.e(this.n[1] = ((this.n[1] & 0xF0) | (n2 & 0xF)), 1024);
            }
            case 45059:
            case 45068: {
                this.e(this.n[1] = ((this.n[1] & 0xF) | (n2 & 0xF) << 4), 1024);
            }
            case 49152: {
                this.e(this.n[2] = ((this.n[2] & 0xF0) | (n2 & 0xF)), 2048);
            }
            case 49153:
            case 49156: {
                this.e(this.n[2] = ((this.n[2] & 0xF) | (n2 & 0xF) << 4), 2048);
            }
            case 49154:
            case 49160: {
                this.e(this.n[3] = ((this.n[3] & 0xF0) | (n2 & 0xF)), 3072);
            }
            case 49155:
            case 49164: {
                this.e(this.n[3] = ((this.n[3] & 0xF) | (n2 & 0xF) << 4), 3072);
            }
            case 53248: {
                this.e(this.n[4] = ((this.n[4] & 0xF0) | (n2 & 0xF)), 4096);
            }
            case 53249:
            case 53252: {
                this.e(this.n[4] = ((this.n[4] & 0xF) | (n2 & 0xF) << 4), 4096);
            }
            case 53250:
            case 53256: {
                this.e(this.n[5] = ((this.n[5] & 0xF0) | (n2 & 0xF)), 5120);
            }
            case 53251:
            case 53260: {
                this.e(this.n[5] = ((this.n[5] & 0xF) | (n2 & 0xF) << 4), 5120);
            }
            case 57344: {
                this.e(this.n[6] = ((this.n[6] & 0xF0) | (n2 & 0xF)), 6144);
            }
            case 57345:
            case 57348: {
                this.e(this.n[6] = ((this.n[6] & 0xF) | (n2 & 0xF) << 4), 6144);
            }
            case 57346:
            case 57352: {
                this.e(this.n[7] = ((this.n[7] & 0xF0) | (n2 & 0xF)), 7168);
            }
            case 57347:
            case 57356: {
                this.e(this.n[7] = ((this.n[7] & 0xF) | (n2 & 0xF) << 4), 7168);
            }
            case 61440: {
                this.l = ((this.l & 0xF0) | (n2 & 0xF));
            }
            case 61444: {
                this.l = ((this.l & 0xF) | (n2 & 0xF) << 4);
            }
            case 61448: {
                this.m = (n2 & 0x3);
                if ((this.m & 0x2) != 0x0) {
                    return;
                }
                break;
            }
            case 61452: {
                this.m = (this.m & 0x1) * 3;
                break;
            }
        }
    }
    
    public final void a(final a a) {
        if (!a.a()) {
            System.out.println("VRC2: Invalid ROM! Unable to load.");
            return;
        }
        final int n = a.b() << 1;
        this.g(0, 32768);
        this.g(1, 40960);
        this.g(n - 2, 49152);
        this.g(n - 1, 57344);
        this.h();
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
