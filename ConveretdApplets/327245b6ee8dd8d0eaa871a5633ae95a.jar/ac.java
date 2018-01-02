import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class ac
{
    int a;
    int b;
    int c;
    int d;
    int e;
    int f;
    public float g;
    public float h;
    public float i;
    private static final int[] j;
    
    ac() {
        this.a = 65536;
        this.d = 65536;
        this.g = 0.0f;
        this.h = 1.0f;
        this.i = 1.0f;
    }
    
    ac(final ac ac) {
        this.a(ac.a, ac.b, ac.c, ac.d, ac.e, ac.f);
    }
    
    void a(final int a, final int b, final int c, final int d, final int e, final int f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.a();
    }
    
    final void a(final float g) {
        final float n = (float)Math.sin(0.017453292f * g);
        final float n2 = (float)Math.cos(0.017453292f * g);
        final float n3 = this.h * 65536.0f;
        final float n4 = this.i * 65536.0f;
        this.a = (int)(n3 * n2);
        this.b = (int)(n3 * n);
        this.c = (int)(-n4 * n);
        this.d = (int)(n4 * n2);
        this.g = g;
    }
    
    final void b(final float h) {
        this.a = (int)(Math.cos(this.g * 0.017453292f) * h * 65536.0);
        this.b = (int)(Math.sin(this.g * 0.017453292f) * h * 65536.0);
        this.h = h;
    }
    
    void a() {
        this.g = (float)(57.295780181884766 * Math.atan2(this.b, this.a));
        this.h = (float)(Math.sqrt(this.a * this.a + this.b * this.b) / 65536.0);
        this.i = (float)(Math.sqrt(this.c * this.c + this.d * this.d) / 65536.0);
    }
    
    final void c(final float i) {
        this.c = -(int)(Math.sin(this.g * 0.017453292f) * i * 65536.0);
        this.d = (int)(Math.cos(this.g * 0.017453292f) * i * 65536.0);
        this.i = i;
    }
    
    final void a(final int a, final int d) {
        this.a = a;
        this.d = d;
        final boolean b = false;
        this.c = (b ? 1 : 0);
        this.b = (b ? 1 : 0);
        final boolean b2 = false;
        this.f = (b2 ? 1 : 0);
        this.e = (b2 ? 1 : 0);
    }
    
    final void a(final Point point, final Point point2) {
        final long n = point.x;
        final long n2 = point.y;
        point2.x = (int)(this.a * n + 32768L >> 16) + this.e;
        if (this.c != 0) {
            point2.x += (int)(this.c * n2 + 32768L >> 16);
        }
        point2.y = (int)(this.d * n2 + 32768L >> 16) + this.f;
        if (this.b != 0) {
            point2.y += (int)(this.b * n + 32768L >> 16);
        }
    }
    
    final void a(final Point point) {
        final long n = point.x;
        final long n2 = point.y;
        point.x = (int)(this.a * n + 32768L >> 16) + this.e;
        if (this.c != 0) {
            point.x += (int)(this.c * n2 + 32768L >> 16);
        }
        point.y = (int)(this.d * n2 + 32768L >> 16) + this.f;
        if (this.b != 0) {
            point.y += (int)(this.b * n + 32768L >> 16);
        }
    }
    
    final void b(final Point point) {
        final long n = point.x;
        final long n2 = point.y;
        point.x = (int)(this.a * n + 32768L >> 16);
        if (this.c != 0) {
            point.x += (int)(this.c * n2 + 32768L >> 16);
        }
        point.y = (int)(this.d * n2 + 32768L >> 16);
        if (this.b != 0) {
            point.y += (int)(this.b * n + 32768L >> 16);
        }
    }
    
    final f a(final f f) {
        final f f2 = new f();
        if (f.a != Integer.MIN_VALUE) {
            final Point point = new Point(f.a, f.b);
            final Point point2 = new Point(0, 0);
            this.a(point, point2);
            f2.a(point2);
            point.x = f.c;
            this.a(point, point2);
            f2.a(point2);
            point.y = f.d;
            this.a(point, point2);
            f2.a(point2);
            point.x = f.a;
            this.a(point, point2);
            f2.a(point2);
        }
        return f2;
    }
    
    int a(final int n) {
        final Point point = new Point(n, n);
        this.b(point);
        int max = (int)(46341L * c(point.x, point.y) + 32768L >> 16);
        if (n > 0) {
            max = Math.max(1, max);
        }
        return max;
    }
    
    final ac b() {
        final boolean l = c.l;
        final ac ac = new ac();
        if (this.b == 0 && this.c == 0) {
            Label_0058: {
                if (this.a != 0) {
                    ac.a = (int)(4294967296L / this.a);
                    if (!l) {
                        break Label_0058;
                    }
                }
                ac.a = 0;
            }
            Label_0089: {
                if (this.d != 0) {
                    ac.d = (int)(4294967296L / this.d);
                    if (!l) {
                        break Label_0089;
                    }
                }
                ac.d = 0;
            }
            ac.e = -(int)(ac.a * this.e + 32768L >> 16);
            ac.f = -(int)(ac.d * this.f + 32768L >> 16);
            if (!l) {
                return ac;
            }
        }
        final double n = this.a * 1.52587890625E-5;
        final double n2 = this.b * 1.52587890625E-5;
        final double n3 = this.c * 1.52587890625E-5;
        final double n4 = this.d * 1.52587890625E-5;
        final double n5 = n * n4 - n2 * n3;
        if (n5 != 0.0) {
            final double n6 = 1.0 / n5;
            ac.a = (int)(n4 * n6 * 65536.0);
            ac.b = -(int)(n2 * n6 * 65536.0);
            ac.c = -(int)(n3 * n6 * 65536.0);
            ac.d = (int)(n * n6 * 65536.0);
            final Point point = new Point(this.e, this.f);
            ac.b(point);
            ac.e = -point.x;
            ac.f = -point.y;
        }
        return ac;
    }
    
    static final ac a(final ac ac, final ac ac2) {
        final ac ac3 = new ac();
        ac3.a = (int)(ac.a * ac2.a + 32768L >> 16);
        ac3.d = (int)(ac.d * ac2.d + 32768L >> 16);
        ac3.e = (int)(ac.e * ac2.a + 32768L >> 16) + ac2.e;
        ac3.f = (int)(ac.f * ac2.d + 32768L >> 16) + ac2.f;
        if (ac.b != 0 || ac.c != 0 || ac2.b != 0 || ac2.c != 0) {
            final ac ac4 = ac3;
            ac4.a += (int)(ac.b * ac2.c + 32768L >> 16);
            final ac ac5 = ac3;
            ac5.d += (int)(ac.c * ac2.b + 32768L >> 16);
            final ac ac6 = ac3;
            ac6.b += (int)(ac.a * ac2.b + 32768L >> 16) + (int)(ac.b * ac2.d + 32768L >> 16);
            final ac ac7 = ac3;
            ac7.c += (int)(ac.c * ac2.a + 32768L >> 16) + (int)(ac.d * ac2.c + 32768L >> 16);
            final ac ac8 = ac3;
            ac8.e += (int)(ac.f * ac2.c + 32768L >> 16);
            final ac ac9 = ac3;
            ac9.f += (int)(ac.e * ac2.b + 32768L >> 16);
        }
        return ac3;
    }
    
    static final int b(final int n, final int n2) {
        final int n3 = (n <= 0) ? (-n) : n;
        final int n4 = (n2 <= 0) ? (-n2) : n2;
        return n3 + n4 - (Math.min(n3, n4) >> 1);
    }
    
    static final int c(final int n, final int n2) {
        int n3 = (n <= 0) ? (-n) : n;
        int n4 = (n2 <= 0) ? (-n2) : n2;
        if (n3 > n4) {
            final int n5 = n3;
            n3 = n4;
            n4 = n5;
        }
        if (n4 == 0) {
            return 0;
        }
        final int n6 = (n3 << 16) / n4;
        final int n7 = n6 >> 10;
        final int n8 = (n6 & 0x3FF) << 6;
        return (int)(n4 * ((int)((65536 - n8) * ac.j[n7] + 32768L >> 16) + (int)(n8 * ac.j[n7 + 1] + 32768L >> 16) >> 14) + 32768L >> 16);
    }
    
    static ac a(final f f, final f f2, final int n) {
        final boolean l = c.l;
        final int max = Math.max(f2.c - f2.a, 16);
        final int max2 = Math.max(f2.d - f2.b, 16);
        final ac ac = new ac();
        ac.a = (max << 16) / Math.max(f.c - f.a, 16);
        ac.d = (max2 << 16) / Math.max(f.d - f.b, 16);
        Label_0199: {
            switch (n & 0xF) {
                case 1: {
                    final ac ac2 = ac;
                    final ac ac3 = ac;
                    final int min = Math.min(ac.a, ac.d);
                    ac3.d = min;
                    ac2.a = min;
                    if (l) {
                        break Label_0199;
                    }
                    break;
                }
                case 2: {
                    final ac ac4 = ac;
                    final ac ac5 = ac;
                    final int max3 = Math.max(ac.a, ac.d);
                    ac5.d = max3;
                    ac4.a = max3;
                    if (l) {
                        break Label_0199;
                    }
                    break;
                }
                case 0: {
                    if (l) {
                        break Label_0199;
                    }
                    break;
                }
                case 3: {
                    final ac ac6 = ac;
                    final ac ac7 = ac;
                    final int n2 = 13107;
                    ac7.d = n2;
                    ac6.a = n2;
                    break;
                }
            }
        }
        int n3 = 0;
        int n4 = 0;
        Label_0287: {
            if ((n & 0x10) != 0x0) {
                n3 = f.a;
                n4 = f2.a;
                if (!l) {
                    break Label_0287;
                }
            }
            if ((n & 0x20) != 0x0) {
                n3 = f.c;
                n4 = f2.c;
                if (!l) {
                    break Label_0287;
                }
            }
            n3 = (f.a + f.c) / 2;
            n4 = (f2.a + f2.c) / 2;
        }
        int n5 = 0;
        int n6 = 0;
        Label_0362: {
            if ((n & 0x40) != 0x0) {
                n5 = f.b;
                n6 = f2.b;
                if (!l) {
                    break Label_0362;
                }
            }
            if ((n & 0x80) != 0x0) {
                n5 = f.d;
                n6 = f2.d;
                if (!l) {
                    break Label_0362;
                }
            }
            n5 = (f.b + f.d) / 2;
            n6 = (f2.b + f2.d) / 2;
        }
        ac.e = n4 - (int)(n3 * ac.a + 32768L >> 16);
        ac.f = n6 - (int)(n5 * ac.d + 32768L >> 16);
        return ac;
    }
    
    static {
        j = new int[] { 1073741824, 1073872888, 1074265984, 1074920825, 1075836932, 1077013639, 1078450093, 1080145258, 1082097918, 1084306681, 1086769986, 1089486107, 1092453157, 1095669100, 1099131748, 1102838780, 1106787739, 1110976045, 1115401003, 1120059807, 1124949552, 1130067241, 1135409791, 1140974043, 1146756771, 1152754686, 1158964447, 1165382668, 1172005924, 1178830760, 1185853694, 1193071229, 1200479854, 1208076055, 1215856315, 1223817123, 1231954981, 1240266402, 1248747921, 1257396097, 1266207514, 1275178788, 1284306569, 1293587545, 1303018442, 1312596028, 1322317116, 1332178565, 1342177280, 1352310217, 1362574382, 1372966831, 1383484673, 1394125071, 1404885240, 1415762448, 1426754019, 1437857331, 1449069814, 1460388955, 1471812291, 1483337417, 1494961978, 1506683672, 1518500250, 1518500250 };
    }
}
