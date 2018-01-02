// 
// Decompiled by Procyon v0.5.30
// 

class nb extends Thread
{
    private final b a;
    int b;
    
    nb(final b a) {
        this.a = a;
    }
    
    public void run() {
        final int m = fb.m;
        try {
            while (true) {
                Thread.sleep(250L);
                final boolean l = this.a.l;
                int i = 0;
                while (i != 0) {
                    int n;
                    int b;
                    i = (b = (n = this.b));
                    if (m == 0) {
                        final b a;
                        Label_0113: {
                            Label_0083: {
                                if (m == 0) {
                                    if (i > 0) {
                                        a = this.a;
                                        if (m != 0) {
                                            break Label_0113;
                                        }
                                        if (a.p < this.a.q - 1) {
                                            break Label_0083;
                                        }
                                    }
                                    n = (b = this.b);
                                }
                                if (m == 0) {
                                    if (b >= 0) {
                                        break;
                                    }
                                    final b a2 = this.a;
                                    if (m != 0) {
                                        break Label_0113;
                                    }
                                    n = a2.p;
                                }
                                if (n <= 0) {
                                    break;
                                }
                            }
                            this.a.p += this.b;
                            this.a.repaint();
                            final b a3 = this.a;
                        }
                        a.c();
                        break;
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public void a(final int b) {
        this.b = b;
    }
}
