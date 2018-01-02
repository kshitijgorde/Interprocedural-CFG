// 
// Decompiled by Procyon v0.5.30
// 

public final class K extends ad
{
    private int l;
    private int[] m;
    
    public K() {
        this.l = 0;
        this.m = new int[11];
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
        switch (n) {
            case 32768: {
                this.g(this.m[0] = ((this.m[0] & 0xF0) | (n2 & 0xF)), 32768);
            }
            case 32769: {
                this.g(this.m[0] = ((this.m[0] & 0xF) | (n2 & 0xF) << 4), 32768);
            }
            case 32770: {
                this.g(this.m[1] = ((this.m[1] & 0xF0) | (n2 & 0xF)), 40960);
            }
            case 32771: {
                this.g(this.m[1] = ((this.m[1] & 0xF) | (n2 & 0xF) << 4), 40960);
            }
            case 36864: {
                this.g(this.m[2] = ((this.m[2] & 0xF0) | (n2 & 0xF)), 49152);
            }
            case 36865: {
                this.g(this.m[2] = ((this.m[2] & 0xF) | (n2 & 0xF) << 4), 49152);
            }
            case 40960: {
                this.e(this.m[3] = ((this.m[3] & 0xF0) | (n2 & 0xF)), 0);
            }
            case 40961: {
                this.e(this.m[3] = ((this.m[3] & 0xF) | (n2 & 0xF) << 4), 0);
            }
            case 40962: {
                this.e(this.m[4] = ((this.m[4] & 0xF0) | (n2 & 0xF)), 1024);
            }
            case 40963: {
                this.e(this.m[4] = ((this.m[4] & 0xF) | (n2 & 0xF) << 4), 1024);
            }
            case 45056: {
                this.e(this.m[5] = ((this.m[5] & 0xF0) | (n2 & 0xF)), 2048);
            }
            case 45057: {
                this.e(this.m[5] = ((this.m[5] & 0xF) | (n2 & 0xF) << 4), 2048);
            }
            case 45058: {
                this.e(this.m[6] = ((this.m[6] & 0xF0) | (n2 & 0xF)), 3072);
            }
            case 45059: {
                this.e(this.m[6] = ((this.m[6] & 0xF) | (n2 & 0xF) << 4), 3072);
            }
            case 49152: {
                this.e(this.m[7] = ((this.m[7] & 0xF0) | (n2 & 0xF)), 4096);
            }
            case 49153: {
                this.e(this.m[7] = ((this.m[7] & 0xF) | (n2 & 0xF) << 4), 4096);
            }
            case 49154: {
                this.e(this.m[8] = ((this.m[8] & 0xF0) | (n2 & 0xF)), 5120);
            }
            case 49155: {
                this.e(this.m[8] = ((this.m[8] & 0xF) | (n2 & 0xF) << 4), 5120);
            }
            case 53248: {
                this.e(this.m[9] = ((this.m[9] & 0xF0) | (n2 & 0xF)), 6144);
            }
            case 53249: {
                this.e(this.m[9] = ((this.m[9] & 0xF) | (n2 & 0xF) << 4), 6144);
            }
            case 53250: {
                this.e(this.m[10] = ((this.m[10] & 0xF0) | (n2 & 0xF)), 7168);
            }
            case 53251: {
                this.e(this.m[10] = ((this.m[10] & 0xF) | (n2 & 0xF) << 4), 7168);
            }
            case 57344: {
                this.l = ((this.l & 0xFFF0) | (n2 & 0xF));
            }
            case 57345: {
                this.l = ((this.l & 0xFF0F) | (n2 & 0xF) << 4);
            }
            case 57346: {
                this.l = ((this.l & 0xF0FF) | (n2 & 0xF) << 8);
            }
            case 57347: {
                this.l = ((this.l & 0xFFF) | (n2 & 0xF) << 12);
            }
            case 61440: {}
            case 61441: {}
            case 61442: {
                final short n3;
                if ((n3 = (short)(n2 & 0x3)) == 0) {
                    this.a.c.a(1);
                    return;
                }
                if (n3 == 1) {
                    this.a.c.a(0);
                    return;
                }
                this.a.c.a(3);
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
        this.c();
        this.a.b.a(2);
    }
    
    public final void a() {
        this.m[0] = 0;
        this.m[1] = 1;
        this.m[2] = -2;
        this.m[3] = -1;
        this.m[4] = 0;
        this.m[5] = 0;
        this.m[6] = 0;
        this.m[7] = 0;
        this.m[8] = 0;
        this.m[9] = 0;
        this.m[10] = 0;
        this.l = 0;
    }
}
