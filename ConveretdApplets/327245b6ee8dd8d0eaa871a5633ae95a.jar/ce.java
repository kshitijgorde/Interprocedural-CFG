// 
// Decompiled by Procyon v0.5.30
// 

public class ce extends n
{
    private float a;
    private boolean b;
    private boolean c;
    
    public ce() {
        this.b = false;
        this.c = false;
    }
    
    synchronized void a(final j j) {
        super.a(j);
        super.b = true;
    }
    
    synchronized boolean a(final String s, final float n, final String s2, final Object o) {
        final boolean l = c.l;
        if (s.equals("rotationAngle")) {
            this.a = n * 3.1415927f / 180.0f;
            if (!l) {
                return true;
            }
        }
        if (s.equals("reverse")) {
            if ((int)n == 0) {
                this.b = false;
                if (!l) {
                    return true;
                }
            }
            this.b = true;
            if (!l) {
                return true;
            }
        }
        if (s.equals("stretchToFit")) {
            if ((int)n == 1) {
                this.c = true;
                if (!l) {
                    return true;
                }
            }
            this.c = false;
            if (!l) {
                return true;
            }
        }
        return super.a(s, n, s2, o);
    }
    
    void a(final g g, final a9 a9, final float[] array, final l l) {
        final boolean i = c.l;
        final ba f = a9.f;
        if (f == null) {
            return;
        }
        final float n = (float)Math.sin(this.a);
        final float n2 = (float)Math.cos(this.a);
        float n3 = 1.0f;
        float n4 = 1.0f;
        float n5 = 999999.0f;
        float n6 = 999999.0f;
        if (this.c) {
            float n7 = -999999.0f;
            float n8 = -999999.0f;
            int n9 = 0;
            while (true) {
                Label_0191: {
                    if (!i) {
                        break Label_0191;
                    }
                    final float[] array2 = f.b[n9];
                    int n10 = 0;
                    while (true) {
                        Label_0182: {
                            if (!i) {
                                break Label_0182;
                            }
                            float n11 = 0.0f;
                            Label_0122: {
                                if (this.b) {
                                    n11 = 1.0f - array2[n10];
                                    if (!i) {
                                        break Label_0122;
                                    }
                                }
                                n11 = array2[n10];
                            }
                            final float n12 = array2[n10 + 3];
                            if (n11 < n5) {
                                n5 = n11;
                            }
                            if (n11 > n7) {
                                n7 = n11;
                            }
                            if (n12 < n6) {
                                n6 = n12;
                            }
                            if (n12 > n8) {
                                n8 = n12;
                            }
                            ++n10;
                        }
                        if (n10 < 3) {
                            continue;
                        }
                        break;
                    }
                    ++n9;
                }
                if (n9 < a9.b) {
                    continue;
                }
                break;
            }
            n3 = n7 - n5;
            n4 = n8 - n6;
        }
        final int c = l.c;
        final int d = l.d;
        int n13 = 0;
        while (true) {
            Label_0444: {
                if (!i) {
                    break Label_0444;
                }
                final float[] array3 = f.b[n13];
                int n14 = n13 * 3 * 2;
                int n15 = 0;
                while (true) {
                    Label_0435: {
                        if (!i) {
                            break Label_0435;
                        }
                        float n16 = 0.0f;
                        Label_0290: {
                            if (this.b) {
                                n16 = 1.0f - array3[n15];
                                if (!i) {
                                    break Label_0290;
                                }
                            }
                            n16 = array3[n15];
                        }
                        float n17 = array3[n15 + 3];
                        if (this.c) {
                            n16 = (n16 - n5) / n3;
                            n17 = (n17 - n6) / n4;
                        }
                        if (this.a != 0.0f) {
                            final float n18 = n16 * n2 - n17 * n;
                            final float n19 = n16 * n + n17 * n2;
                            n16 = n18 + (0.5f - (0.5f * n2 - 0.5f * n));
                            n17 = n19 + (0.5f - (0.5f * n + 0.5f * n2));
                        }
                        final float n20 = n16 * c;
                        final float n21 = n17 * d;
                        array[n14] = n20;
                        array[n14 + 1] = n21;
                        n14 += 2;
                        ++n15;
                    }
                    if (n15 < 3) {
                        continue;
                    }
                    break;
                }
                ++n13;
            }
            if (n13 >= a9.b) {
                return;
            }
            continue;
        }
    }
}
