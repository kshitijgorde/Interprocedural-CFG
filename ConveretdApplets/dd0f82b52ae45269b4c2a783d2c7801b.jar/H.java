// 
// Decompiled by Procyon v0.5.30
// 

public final class H extends ag
{
    private int[] n;
    
    public H() {
        this.n = new int[1];
    }
    
    public final void a(final V v) {
        super.a(v);
    }
    
    public final void a(final int n, final short n2) {
        if (n < 32768) {
            super.a(n, n2);
            return;
        }
        Label_0223: {
            switch (n & 0xF003) {
                case 32769: {
                    if ((n2 & 0x1) != 0x0) {
                        this.a.c.a(1);
                        return;
                    }
                    this.a.c.a(0);
                    return;
                }
                case 40960: {
                    this.n[0] = (n2 & 0x7);
                    return;
                }
                case 49152: {
                    switch (this.n[0]) {
                        case 0: {
                            this.f(n2, 0);
                            return;
                        }
                        case 1: {
                            this.e(n2, 5120);
                            return;
                        }
                        case 2: {
                            this.f(n2, 2048);
                            return;
                        }
                        case 3: {
                            this.e(n2, 7168);
                            return;
                        }
                        case 4: {
                            this.g(n2, 32768);
                            return;
                        }
                        case 5: {
                            this.g(n2, 40960);
                            return;
                        }
                        case 6: {
                            this.e(n2, 4096);
                            return;
                        }
                        case 7: {
                            this.e(n2, 6144);
                            break Label_0223;
                        }
                    }
                    break;
                }
            }
        }
    }
    
    public final void a(final a a) {
        if (!a.a()) {
            System.out.println("182: Invalid ROM! Unable to load.");
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
    }
}
