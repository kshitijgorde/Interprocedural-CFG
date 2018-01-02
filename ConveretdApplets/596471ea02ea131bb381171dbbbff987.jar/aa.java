// 
// Decompiled by Procyon v0.5.30
// 

public final class aa extends ad
{
    private int l;
    private int[] m;
    
    public aa() {
        this.l = 0;
        this.m = new int[3];
    }
    
    public final void a(final U u) {
        super.a(u);
        this.a();
    }
    
    public final void a(final int n, final short l) {
        if (n < 32768) {
            super.a(n, l);
            return;
        }
        Label_0518: {
            switch (n & 0xF003) {
                case 32768: {
                    this.m[0] = (l & 0xF);
                    this.m[1] = (l & 0x40);
                    this.m[2] = (l & 0x80);
                    return;
                }
                case 32769: {
                    switch (this.m[0]) {
                        case 0: {
                            if (this.m[2] != 0) {
                                this.f(l, 4096);
                                break Label_0518;
                            }
                            this.f(l, 0);
                            break Label_0518;
                        }
                        case 1: {
                            if (this.m[2] != 0) {
                                this.f(l, 6144);
                                break Label_0518;
                            }
                            this.f(l, 2048);
                            break Label_0518;
                        }
                        case 2: {
                            if (this.m[2] != 0) {
                                this.e(l, 0);
                                break Label_0518;
                            }
                            this.e(l, 4096);
                            break Label_0518;
                        }
                        case 3: {
                            if (this.m[2] != 0) {
                                this.e(l, 1024);
                                break Label_0518;
                            }
                            this.e(l, 5120);
                            break Label_0518;
                        }
                        case 4: {
                            if (this.m[2] != 0) {
                                this.e(l, 2048);
                                break Label_0518;
                            }
                            this.e(l, 6144);
                            break Label_0518;
                        }
                        case 5: {
                            if (this.m[2] != 0) {
                                this.e(l, 3072);
                                break Label_0518;
                            }
                            this.e(l, 7168);
                            break Label_0518;
                        }
                        case 6: {
                            if (this.m[1] != 0) {
                                this.g(l, 40960);
                                break Label_0518;
                            }
                            this.g(l, 32768);
                            break Label_0518;
                        }
                        case 7: {
                            if (this.m[1] == 0) {
                                this.g(l, 40960);
                                break Label_0518;
                            }
                            break;
                        }
                        case 8: {
                            this.e(l, 1024);
                            break Label_0518;
                        }
                        case 9: {
                            this.e(l, 3072);
                            break Label_0518;
                        }
                        case 15: {
                            if (this.m[1] != 0) {
                                this.g(l, 32768);
                                break Label_0518;
                            }
                            break;
                        }
                        default: {
                            return;
                        }
                    }
                    this.g(l, 49152);
                    return;
                }
                case 40960: {
                    if ((l & 0x1) == 0x0) {
                        this.a.c.a(0);
                        return;
                    }
                    this.a.c.a(1);
                    return;
                }
                case 49152: {
                    this.l = l;
                    break;
                }
            }
        }
    }
    
    public final void a(final a a) {
        if (!a.a()) {
            return;
        }
        final int n = a.c() << 2;
        final int n2 = a.b() << 1;
        this.g(0, 32768);
        this.g(1, 40960);
        this.g(n2 - 2, 49152);
        this.g(n2 - 1, 57344);
        this.d(0, 0);
        System.out.println("CHR = " + n + "");
        this.a.b.a(2);
    }
    
    public final void a() {
        this.l = 0;
        this.m[0] = 0;
        this.m[1] = 0;
        this.m[2] = 0;
    }
}
