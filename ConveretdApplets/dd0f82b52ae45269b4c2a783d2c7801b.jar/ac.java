// 
// Decompiled by Procyon v0.5.30
// 

public final class ac extends ag
{
    private int n;
    private int[] o;
    
    public ac() {
        this.n = 0;
        this.o = new int[3];
    }
    
    public final void a(final V v) {
        super.a(v);
        this.a();
    }
    
    public final void a(final int n, final short n2) {
        if (n < 32768) {
            super.a(n, n2);
            return;
        }
        Label_0518: {
            switch (n & 0xF003) {
                case 32768: {
                    this.o[0] = (n2 & 0xF);
                    this.o[1] = (n2 & 0x40);
                    this.o[2] = (n2 & 0x80);
                    return;
                }
                case 32769: {
                    switch (this.o[0]) {
                        case 0: {
                            if (this.o[2] != 0) {
                                this.f(n2, 4096);
                                break Label_0518;
                            }
                            this.f(n2, 0);
                            break Label_0518;
                        }
                        case 1: {
                            if (this.o[2] != 0) {
                                this.f(n2, 6144);
                                break Label_0518;
                            }
                            this.f(n2, 2048);
                            break Label_0518;
                        }
                        case 2: {
                            if (this.o[2] != 0) {
                                this.e(n2, 0);
                                break Label_0518;
                            }
                            this.e(n2, 4096);
                            break Label_0518;
                        }
                        case 3: {
                            if (this.o[2] != 0) {
                                this.e(n2, 1024);
                                break Label_0518;
                            }
                            this.e(n2, 5120);
                            break Label_0518;
                        }
                        case 4: {
                            if (this.o[2] != 0) {
                                this.e(n2, 2048);
                                break Label_0518;
                            }
                            this.e(n2, 6144);
                            break Label_0518;
                        }
                        case 5: {
                            if (this.o[2] != 0) {
                                this.e(n2, 3072);
                                break Label_0518;
                            }
                            this.e(n2, 7168);
                            break Label_0518;
                        }
                        case 6: {
                            if (this.o[1] != 0) {
                                this.g(n2, 40960);
                                break Label_0518;
                            }
                            this.g(n2, 32768);
                            break Label_0518;
                        }
                        case 7: {
                            if (this.o[1] == 0) {
                                this.g(n2, 40960);
                                break Label_0518;
                            }
                            break;
                        }
                        case 8: {
                            this.e(n2, 1024);
                            break Label_0518;
                        }
                        case 9: {
                            this.e(n2, 3072);
                            break Label_0518;
                        }
                        case 15: {
                            if (this.o[1] != 0) {
                                this.g(n2, 32768);
                                break Label_0518;
                            }
                            break;
                        }
                        default: {
                            return;
                        }
                    }
                    this.g(n2, 49152);
                    return;
                }
                case 40960: {
                    if ((n2 & 0x1) == 0x0) {
                        this.a.c.a(0);
                        return;
                    }
                    this.a.c.a(1);
                    return;
                }
                case 49152: {
                    this.n = n2;
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
        this.n = 0;
        this.o[0] = 0;
        this.o[1] = 0;
        this.o[2] = 0;
    }
}
