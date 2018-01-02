// 
// Decompiled by Procyon v0.5.30
// 

class dm
{
    int a;
    int b;
    char[] c;
    char[] d;
    char[] e;
    char[] f;
    c1 g;
    
    dm(final c1 g) {
        this.c = new char[32];
        this.d = new char[256];
        this.e = new char[4096];
        this.f = new char[4096];
        this.a = 0;
        this.g = g;
    }
    
    void a() {
        this.e = null;
        this.f = null;
        this.d = null;
        this.c = null;
    }
    
    void b() throws Exception {
        final boolean l = c.l;
        this.a = this.g.a(4, 15);
        this.g.a(4);
        this.b = (1 << this.a) - 1;
        Label_0067: {
            if (this.a == 0) {
                this.c[0] = '\u0001';
                if (!l) {
                    break Label_0067;
                }
            }
            this.c[0] = '\0';
        }
        int n = 1;
        int n2;
        while (true) {
            while (true) {
                Label_0105: {
                    if (!l) {
                        break Label_0105;
                    }
                    this.c[n] = (char)this.g.a(8, 255);
                    this.g.a(8);
                    ++n;
                }
                if (n <= this.a) {
                    continue;
                }
                break;
            }
            n2 = 0;
            n = 0;
            if (l) {
                continue;
            }
            break;
        }
        while (true) {
            Label_0183: {
                if (!l) {
                    break Label_0183;
                }
                char c = '\0';
                while (true) {
                    Label_0170: {
                        if (!l) {
                            break Label_0170;
                        }
                        this.d[n2++] = (char)this.g.a(8, 255);
                        this.g.a(8);
                        ++c;
                    }
                    if (c < this.c[n]) {
                        continue;
                    }
                    break;
                }
                ++n;
            }
            if (n > this.a) {
                this.c();
                return;
            }
            continue;
        }
    }
    
    void c() {
        final boolean l = c.l;
        if (this.a == 0) {
            int n = 0;
            while (true) {
                Label_0041: {
                    if (!l) {
                        break Label_0041;
                    }
                    this.f[n] = '\0';
                    this.e[n] = this.d[0];
                    ++n;
                }
                if (n >= 4096) {
                    return;
                }
                continue;
            }
        }
        else {
            final char[] array = new char[257];
            final short[] array2 = new short[256];
            int n2 = 0;
            int n3 = 1;
            char c;
            char c2;
            while (true) {
                while (true) {
                    Label_0105: {
                        if (!l) {
                            break Label_0105;
                        }
                        c = '\0';
                        while (true) {
                            Label_0091: {
                                if (!l) {
                                    break Label_0091;
                                }
                                array[n2++] = (char)n3;
                                ++c;
                            }
                            if (c < this.c[n3]) {
                                continue;
                            }
                            break;
                        }
                        ++n3;
                    }
                    if (n3 <= this.a) {
                        continue;
                    }
                    break;
                }
                array[n2] = '\0';
                c = '\0';
                n2 = 0;
                c2 = array[0];
                if (l) {
                    continue;
                }
                break;
            }
            int n4;
            while (true) {
                while (true) {
                    Label_0153: {
                        if (array[n2] != '\0') {
                            break Label_0153;
                        }
                        n2 = 0;
                        n4 = 1;
                        if (!l) {
                            break;
                        }
                        ++c;
                    }
                    if (array[n2] == c2) {
                        array2[n2++] = (short)c;
                        continue;
                    }
                    c <<= 1;
                    if (l) {
                        continue;
                    }
                    break;
                }
                ++c2;
            }
            while (true) {
                Label_0298: {
                    if (!l) {
                        break Label_0298;
                    }
                    char c3 = '\u0001';
                Label_0260_Outer:
                    while (true) {
                        Label_0284: {
                            if (!l) {
                                break Label_0284;
                            }
                            int n5 = array2[n2] << this.a - n4;
                            int n6 = 1 << this.a - n4;
                            while (true) {
                                while (true) {
                                    Label_0263: {
                                        if (!l) {
                                            break Label_0263;
                                        }
                                        this.e[n5] = this.d[n2];
                                        this.f[n5] = (char)n4;
                                        ++n5;
                                    }
                                    if (n6-- != 0) {
                                        continue Label_0260_Outer;
                                    }
                                    break;
                                }
                                ++n2;
                                if (l) {
                                    continue;
                                }
                                break;
                            }
                            ++c3;
                        }
                        if (c3 <= this.c[n4]) {
                            continue;
                        }
                        break;
                    }
                    ++n4;
                }
                if (n4 > this.a) {
                    return;
                }
                continue;
            }
        }
    }
    
    final int d() throws Exception {
        return this.g.a(this.f, this.e, this.a, this.b);
    }
    
    final int e() throws Exception {
        return this.g.d();
    }
}
