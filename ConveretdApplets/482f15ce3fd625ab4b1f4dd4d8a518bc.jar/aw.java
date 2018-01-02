// 
// Decompiled by Procyon v0.5.30
// 

public final class aw extends at
{
    private int b;
    private int c;
    private int d;
    private int[] a;
    public int a;
    
    public aw() {
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.a = new int[9];
        this.a = 65535;
    }
    
    public final int a() {
        return 23;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a(final long n) {
        this.a = 65535;
        if (n == 3254515289L) {
            this.a = 61452;
        }
    }
    
    public final void a() {
        this.a(0, 1, this.b() - 2, this.b() - 1);
        this.a(0, 1, 2, 3, 4, 5, 6, 7);
        this.a[0] = 0;
        this.a[1] = 1;
        this.a[2] = 2;
        this.a[3] = 3;
        this.a[4] = 4;
        this.a[5] = 5;
        this.a[6] = 6;
        this.a[7] = 7;
        this.a[8] = 0;
        this.d = 0;
        this.c = 0;
        this.b = 0;
    }
    
    public final void a(final int n, int n2) {
        if (n < 32768) {
            return;
        }
        switch (n & this.a) {
            case 32768:
            case 32772:
            case 32776:
            case 32780: {
                if (this.a[8] != 0) {
                    this.e(n2);
                    return;
                }
                this.c(n2);
            }
            case 36864: {
                if (n2 == 255) {
                    break;
                }
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
            case 36872: {
                this.a[8] = (n2 & 0x2);
            }
            case 40960:
            case 40964:
            case 40968:
            case 40972: {
                this.d(n2);
            }
            case 45056: {
                this.g(this.a[0] = ((this.a[0] & 0xF0) | (n2 & 0xF)));
            }
            case 45057:
            case 45060: {
                this.g(this.a[0] = ((this.a[0] & 0xF) | (n2 & 0xF) << 4));
            }
            case 45058:
            case 45064: {
                this.h(this.a[1] = ((this.a[1] & 0xF0) | (n2 & 0xF)));
            }
            case 45059:
            case 45068: {
                this.h(this.a[1] = ((this.a[1] & 0xF) | (n2 & 0xF) << 4));
            }
            case 49152: {
                this.i(this.a[2] = ((this.a[2] & 0xF0) | (n2 & 0xF)));
            }
            case 49153:
            case 49156: {
                this.i(this.a[2] = ((this.a[2] & 0xF) | (n2 & 0xF) << 4));
            }
            case 49154:
            case 49160: {
                this.j(this.a[3] = ((this.a[3] & 0xF0) | (n2 & 0xF)));
            }
            case 49155:
            case 49164: {
                this.j(this.a[3] = ((this.a[3] & 0xF) | (n2 & 0xF) << 4));
            }
            case 53248: {
                this.k(this.a[4] = ((this.a[4] & 0xF0) | (n2 & 0xF)));
            }
            case 53249:
            case 53252: {
                this.k(this.a[4] = ((this.a[4] & 0xF) | (n2 & 0xF) << 4));
            }
            case 53250:
            case 53256: {
                this.l(this.a[5] = ((this.a[5] & 0xF0) | (n2 & 0xF)));
            }
            case 53251:
            case 53260: {
                this.l(this.a[5] = ((this.a[5] & 0xF) | (n2 & 0xF) << 4));
            }
            case 57344: {
                this.m(this.a[6] = ((this.a[6] & 0xF0) | (n2 & 0xF)));
            }
            case 57345:
            case 57348: {
                this.m(this.a[6] = ((this.a[6] & 0xF) | (n2 & 0xF) << 4));
            }
            case 57346:
            case 57352: {
                this.n(this.a[7] = ((this.a[7] & 0xF0) | (n2 & 0xF)));
            }
            case 57347:
            case 57356: {
                this.n(this.a[7] = ((this.a[7] & 0xF) | (n2 & 0xF) << 4));
            }
            case 61440: {
                this.c = ((this.c & 0xF0) | (n2 & 0xF));
            }
            case 61444: {
                this.c = ((this.c & 0xF) | (n2 & 0xF) << 4);
            }
            case 61448: {
                this.d = (n2 & 0x3);
                if ((this.d & 0x2) != 0x0) {
                    this.b = this.c;
                    return;
                }
                break;
            }
            case 61452: {
                this.d = (this.d & 0x1) * 3;
                break;
            }
        }
    }
    
    public final int a(final int n) {
        if ((this.d & 0x2) != 0x0) {
            if (this.b == 255) {
                this.b = this.c;
                this.d = (this.d & 0x1) * 3;
                return 3;
            }
            ++this.b;
        }
        return 0;
    }
}
