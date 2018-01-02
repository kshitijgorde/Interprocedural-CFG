// 
// Decompiled by Procyon v0.5.30
// 

class ao extends m
{
    int a;
    int b;
    int c;
    int d;
    int e;
    int[] f;
    int[] g;
    int[] h;
    int[][] i;
    int[] j;
    int k;
    
    ao(final int a) {
        super.a = 3;
        this.a = a;
        this.k = ((a < 3) ? 1024 : 20480);
    }
    
    int a(final x x) {
        final boolean l = c.l;
        super.d = x.e();
        super.h = x.a;
        super.i = x.b;
        if (this.a == 1) {
            final int b = x.b;
            this.e = x.e() >> 1;
            this.f = new int[this.e];
            this.i = new int[this.e][];
            this.j = new int[this.e];
            x.b = b;
            int n = 0;
            while (true) {
                Label_0114: {
                    if (!l) {
                        break Label_0114;
                    }
                    this.f[n] = b + x.e();
                    ++n;
                }
                if (n < this.e) {
                    continue;
                }
                break;
            }
        }
        else {
            final int d = x.d();
            final boolean b2 = (d & 0x4) != 0x0;
            final boolean b3 = (d & 0x8) != 0x0;
            x.d();
            final int d2 = x.d();
            final char[] array = new char[d2 - 1];
            int n2 = 0;
            while (true) {
                while (true) {
                    Label_0198: {
                        if (!l) {
                            break Label_0198;
                        }
                        array[n2] = (char)x.d();
                        ++n2;
                    }
                    if (n2 < d2 - 1) {
                        continue;
                    }
                    break;
                }
                x.d();
                super.e = new String(array);
                if (l) {
                    continue;
                }
                break;
            }
            if ((d & 0x1) != 0x0) {
                super.e += " Bold";
            }
            if ((d & 0x2) != 0x0) {
                super.e += " Italic";
            }
            this.e = x.e();
            this.f = new int[this.e];
            this.i = new int[this.e][];
            this.j = new int[this.e];
            final int b4 = x.b;
            int n3 = 0;
            boolean b5 = false;
            while (true) {
                Label_0384: {
                    if (!l) {
                        break Label_0384;
                    }
                    int n4 = 0;
                    Label_0369: {
                        if (b5) {
                            n4 = x.g();
                            if (!l) {
                                break Label_0369;
                            }
                        }
                        n4 = x.e();
                    }
                    this.f[n3] = b4 + n4;
                    ++n3;
                }
                if (n3 < this.e) {
                    continue;
                }
                b5 = b3;
                if (l) {
                    continue;
                }
                break;
            }
            int n5 = 0;
            Label_0420: {
                if (b5) {
                    n5 = x.g();
                    if (!l) {
                        break Label_0420;
                    }
                }
                n5 = x.e();
            }
            x.b = b4 + n5;
            int[] array2 = null;
            Label_0451: {
                if (b2) {
                    array2 = new int[65536];
                    if (!l) {
                        break Label_0451;
                    }
                }
                array2 = new int[256];
            }
            int n6 = 0;
            int n7;
            int n8;
            while (true) {
                while (true) {
                    Label_0468: {
                        if (!l) {
                            break Label_0468;
                        }
                        array2[n6] = -1;
                        ++n6;
                    }
                    if (n6 < array2.length) {
                        continue;
                    }
                    break;
                }
                n7 = -1;
                n8 = 0;
                if (l) {
                    continue;
                }
                break;
            }
            while (true) {
                while (true) {
                    Label_0537: {
                        if (!l) {
                            break Label_0537;
                        }
                        int n9 = 0;
                        Label_0516: {
                            if (b2) {
                                n9 = x.e();
                                if (!l) {
                                    break Label_0516;
                                }
                            }
                            n9 = x.d();
                        }
                        array2[n9] = n8;
                        if (n9 > n7) {
                            n7 = n9;
                        }
                        ++n8;
                    }
                    if (n8 < this.e) {
                        continue;
                    }
                    break;
                }
                System.arraycopy(array2, 0, this.g = new int[n7 + 1], 0, n7 + 1);
                if (l) {
                    continue;
                }
                break;
            }
            if ((d & 0x80) != 0x0) {
                this.b = x.f();
                this.c = x.f();
                this.d = x.f();
                this.h = new int[this.e];
                int n10 = 0;
                while (true) {
                    Label_0643: {
                        if (!l) {
                            break Label_0643;
                        }
                        this.h[n10] = x.f();
                        ++n10;
                    }
                    if (n10 < this.e) {
                        continue;
                    }
                    break;
                }
            }
        }
        return super.d;
    }
    
    int[] a(final int n, final ad ad) {
        if (this.i[n] == null) {
            ad.aq = null;
            ad.b.a(super.h, this.f[n]);
            ad.b();
            this.i[n] = ad.aq;
        }
        return this.i[n];
    }
}
