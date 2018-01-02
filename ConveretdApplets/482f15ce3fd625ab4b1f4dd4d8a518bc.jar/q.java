// 
// Decompiled by Procyon v0.5.30
// 

public final class q extends at
{
    private int a;
    private int b;
    private int[] a;
    
    public final int a() {
        return this.a;
    }
    
    public q(final int a) {
        this.a = 0;
        this.b = 0;
        this.a = new int[2];
        switch (a) {
            case 2:
            case 7:
            case 8:
            case 11:
            case 34:
            case 58:
            case 60:
            case 62:
            case 66:
            case 71:
            case 72:
            case 77:
            case 78:
            case 79:
            case 86:
            case 87:
            case 89:
            case 92:
            case 93:
            case 94:
            case 97:
            case 99:
            case 101:
            case 140:
            case 151:
            case 180:
            case 181:
            case 184:
            case 185:
            case 222:
            case 229:
            case 231:
            case 232:
            case 233:
            case 240:
            case 242:
            case 244:
            case 246: {
                break;
            }
            default: {
                System.out.println("FATAL: Incorrect Mapper for MapperHandler " + a);
                break;
            }
        }
        this.a = a;
    }
    
    public final void b(final int n, final int n2) {
        switch (this.a) {
            case 79: {
                if ((n & 0x100) != 0x0) {
                    final int n3 = (n2 & 0x8) >> 3;
                    final int n4 = n2 & 0x7;
                    this.c(n3 * 4 + 0);
                    this.d(n3 * 4 + 1);
                    this.e(n3 * 4 + 2);
                    this.f(n3 * 4 + 3);
                    this.g(n4 * 8 + 0);
                    this.h(n4 * 8 + 1);
                    this.i(n4 * 8 + 2);
                    this.j(n4 * 8 + 3);
                    this.k(n4 * 8 + 4);
                    this.l(n4 * 8 + 5);
                    this.m(n4 * 8 + 6);
                    this.n(n4 * 8 + 7);
                    return;
                }
                break;
            }
            case 99: {
                if (n != 16406) {
                    break;
                }
                if ((n2 & 0x4) != 0x0) {
                    this.a(8, 9, 10, 11, 12, 13, 14, 15);
                    return;
                }
                this.a(0, 1, 2, 3, 4, 5, 6, 7);
            }
            case 181: {
                if (n == 16672) {
                    final int n5 = (n2 & 0x8) >> 3;
                    final int n6 = n2 & 0x7;
                    this.c(n5 * 4 + 0);
                    this.d(n5 * 4 + 1);
                    this.e(n5 * 4 + 2);
                    this.f(n5 * 4 + 3);
                    this.g(n6 * 8 + 0);
                    this.h(n6 * 8 + 1);
                    this.i(n6 * 8 + 2);
                    this.j(n6 * 8 + 3);
                    this.k(n6 * 8 + 4);
                    this.l(n6 * 8 + 5);
                    this.m(n6 * 8 + 6);
                    this.n(n6 * 8 + 7);
                    return;
                }
                break;
            }
            case 240: {
                if (n >= 16640 && n <= 20479) {
                    final int n7 = n2 >> 4;
                    final int n8 = n2 & 0xF;
                    this.c(n7 * 4 + 0);
                    this.d(n7 * 4 + 1);
                    this.e(n7 * 4 + 2);
                    this.f(n7 * 4 + 3);
                    this.g(n8 * 8 + 0);
                    this.h(n8 * 8 + 1);
                    this.i(n8 * 8 + 2);
                    this.j(n8 * 8 + 3);
                    this.k(n8 * 8 + 4);
                    this.l(n8 * 8 + 5);
                    this.m(n8 * 8 + 6);
                    this.n(n8 * 8 + 7);
                    break;
                }
                break;
            }
        }
    }
    
    public final void a(int n, int n2) {
        Label_4591: {
            switch (this.a) {
                case 2: {
                    if (n < 32768) {
                        return;
                    }
                    final int b = this.b();
                    n2 &= b - 1;
                    this.a(n2 * 2, n2 * 2 + 1, b - 2, b - 1);
                    return;
                }
                case 7: {
                    if (n < 32768) {
                        return;
                    }
                    final int n3 = (n2 & 0x7) << 2;
                    this.a(n3 + 0, n3 + 1, n3 + 2, n3 + 3);
                    if ((n2 & 0x10) != 0x0) {
                        this.d();
                    }
                    else {
                        this.e();
                    }
                    return;
                }
                case 8: {
                    if (n < 32768) {
                        return;
                    }
                    final int n4 = (n2 & 0xF8) >> 3;
                    final int n5 = n2 & 0x7;
                    this.c(n4 * 2 + 0);
                    this.d(n4 * 2 + 1);
                    this.g(n5 * 8 + 0);
                    this.h(n5 * 8 + 1);
                    this.i(n5 * 8 + 2);
                    this.j(n5 * 8 + 3);
                    this.k(n5 * 8 + 4);
                    this.l(n5 * 8 + 5);
                    this.m(n5 * 8 + 6);
                    this.n(n5 * 8 + 7);
                    return;
                }
                case 11: {
                    if (n < 32768) {
                        return;
                    }
                    final int n6 = n2 & 0x1;
                    final int n7 = (n2 & 0x70) >> 4;
                    this.c(n6 * 4 + 0);
                    this.d(n6 * 4 + 1);
                    this.e(n6 * 4 + 2);
                    this.f(n6 * 4 + 3);
                    this.g(n7 * 8 + 0);
                    this.h(n7 * 8 + 1);
                    this.i(n7 * 8 + 2);
                    this.j(n7 * 8 + 3);
                    this.k(n7 * 8 + 4);
                    this.l(n7 * 8 + 5);
                    this.m(n7 * 8 + 6);
                    this.n(n7 * 8 + 7);
                    return;
                }
                case 34: {
                    if (n < 32768) {
                        switch (n) {
                            case 32765: {
                                this.a(n2 * 4, n2 * 4 + 1, n2 * 4 + 2, n2 * 4 + 3);
                            }
                            case 32766: {
                                this.g(n2 * 4 + 0);
                                this.h(n2 * 4 + 1);
                                this.i(n2 * 4 + 2);
                                this.j(n2 * 4 + 3);
                            }
                            case 32767: {
                                this.k(n2 * 4 + 0);
                                this.l(n2 * 4 + 1);
                                this.m(n2 * 4 + 2);
                                this.n(n2 * 4 + 3);
                                break;
                            }
                        }
                        return;
                    }
                    this.a(n2 * 4, n2 * 4 + 1, n2 * 4 + 2, n2 * 4 + 3);
                    return;
                }
                case 58: {
                    if (n < 32768) {
                        return;
                    }
                    if ((n & 0x40) != 0x0) {
                        this.c(2 * (n & 0x7) + 0);
                        this.d(2 * (n & 0x7) + 1);
                        this.e(2 * (n & 0x7) + 0);
                        this.f(2 * (n & 0x7) + 1);
                    }
                    else {
                        this.c(4 * ((n & 0x6) >> 1) + 0);
                        this.d(4 * ((n & 0x6) >> 1) + 1);
                        this.e(4 * ((n & 0x6) >> 1) + 2);
                        this.f(4 * ((n & 0x6) >> 1) + 3);
                    }
                    this.g(8 * ((n & 0x38) >> 3) + 0);
                    this.h(8 * ((n & 0x38) >> 3) + 1);
                    this.i(8 * ((n & 0x38) >> 3) + 2);
                    this.j(8 * ((n & 0x38) >> 3) + 3);
                    this.k(8 * ((n & 0x38) >> 3) + 4);
                    this.l(8 * ((n & 0x38) >> 3) + 5);
                    this.m(8 * ((n & 0x38) >> 3) + 6);
                    this.n(8 * ((n & 0x38) >> 3) + 7);
                    if ((n2 & 0x2) != 0x0) {
                        this.b();
                        return;
                    }
                    this.c();
                    return;
                }
                case 60: {
                    if (n < 32768) {
                        return;
                    }
                    if ((n & 0x80) != 0x0) {
                        this.c(2 * ((n & 0x70) >> 4) + 0);
                        this.d(2 * ((n & 0x70) >> 4) + 1);
                        this.e(2 * ((n & 0x70) >> 4) + 0);
                        this.f(2 * ((n & 0x70) >> 4) + 1);
                    }
                    else {
                        this.c(4 * ((n & 0x70) >> 5) + 0);
                        this.d(4 * ((n & 0x70) >> 5) + 1);
                        this.e(4 * ((n & 0x70) >> 5) + 2);
                        this.f(4 * ((n & 0x70) >> 5) + 3);
                    }
                    this.g(8 * (n & 0x7) + 0);
                    this.h(8 * (n & 0x7) + 1);
                    this.i(8 * (n & 0x7) + 2);
                    this.j(8 * (n & 0x7) + 3);
                    this.k(8 * (n & 0x7) + 4);
                    this.l(8 * (n & 0x7) + 5);
                    this.m(8 * (n & 0x7) + 6);
                    this.n(8 * (n & 0x7) + 7);
                    if ((n2 & 0x8) != 0x0) {
                        this.c();
                        return;
                    }
                    this.b();
                    return;
                }
                case 62: {
                    if (n < 32768) {
                        return;
                    }
                    switch (n & 0xFF00) {
                        case 33024: {
                            this.c(n2);
                            this.d(n2 + 1);
                            return;
                        }
                        case 34048: {
                            this.c(n2);
                            return;
                        }
                        case 34560: {
                            this.d(n2);
                            return;
                        }
                        default: {
                            this.g(n2);
                            this.h(n2 + 1);
                            this.i(n2 + 2);
                            this.j(n2 + 3);
                            this.k(n2 + 4);
                            this.l(n2 + 5);
                            this.m(n2 + 6);
                            this.n(n2 + 7);
                            return;
                        }
                    }
                    break;
                }
                case 66: {
                    if (n >= 24576) {
                        final int n8 = n2 & 0xF;
                        final int n9 = (n2 & 0xF0) >> 4;
                        this.c(n9 * 4 + 0);
                        this.d(n9 * 4 + 1);
                        this.e(n9 * 4 + 2);
                        this.f(n9 * 4 + 3);
                        this.g(n8 * 8 + 0);
                        this.h(n8 * 8 + 1);
                        this.i(n8 * 8 + 2);
                        this.j(n8 * 8 + 3);
                        this.k(n8 * 8 + 4);
                        this.l(n8 * 8 + 5);
                        this.m(n8 * 8 + 6);
                        this.n(n8 * 8 + 7);
                        return;
                    }
                    break;
                }
                case 71: {
                    if (n >= 24576 && n < 32768) {
                        this.c(n2 * 2 + 0);
                        this.d(n2 * 2 + 1);
                    }
                    if (n >= 32768) {
                        switch (n & 0xF000) {
                            case 36864: {
                                if ((n2 & 0x10) != 0x0) {
                                    this.b(1, 1, 1, 1);
                                    break;
                                }
                                this.b(0, 0, 0, 0);
                                break;
                            }
                            case 49152:
                            case 53248:
                            case 57344:
                            case 61440: {
                                this.c(n2 * 2 + 0);
                                this.d(n2 * 2 + 1);
                                break;
                            }
                        }
                        return;
                    }
                    break;
                }
                case 72: {
                    if (n < 32768) {
                        return;
                    }
                    final int n10 = n2 & 0xF;
                    if ((n2 & 0x80) != 0x0) {
                        this.a(n10 * 2, n10 * 2 + 1, this.b() - 2, this.b() - 1);
                    }
                    if ((n2 & 0x40) != 0x0) {
                        this.a(n10 * 8, n10 * 8 + 1, n10 * 8 + 2, n10 * 8 + 3, n10 * 8 + 4, n10 * 8 + 5, n10 * 8 + 6, n10 * 8 + 7);
                    }
                    return;
                }
                case 77: {
                    if (n < 32768) {
                        return;
                    }
                    final int n11 = n2 & 0x7;
                    final int n12 = (n2 & 0xF0) >> 4;
                    this.c(n11 * 4 + 0);
                    this.d(n11 * 4 + 1);
                    this.e(n11 * 4 + 2);
                    this.f(n11 * 4 + 3);
                    this.g(n12 * 2 + 0);
                    this.h(n12 * 2 + 1);
                    return;
                }
                case 78: {
                    if (n < 32768) {
                        return;
                    }
                    final int n13 = n2 & 0xF;
                    final int n14 = (n2 & 0xF0) >> 4;
                    this.c(n13 * 2 + 0);
                    this.d(n13 * 2 + 1);
                    this.g(n14 * 8 + 0);
                    this.h(n14 * 8 + 1);
                    this.i(n14 * 8 + 2);
                    this.j(n14 * 8 + 3);
                    this.k(n14 * 8 + 4);
                    this.l(n14 * 8 + 5);
                    this.m(n14 * 8 + 6);
                    this.n(n14 * 8 + 7);
                    if ((n & 0xFE00) != 0xFE00) {
                        if ((n2 & 0x8) != 0x0) {
                            this.b(1, 1, 1, 1);
                        }
                        else {
                            this.b(0, 0, 0, 0);
                        }
                    }
                    return;
                }
                case 86: {
                    if (n == 24576) {
                        final int n15 = (n2 & 0x3) | (n2 & 0x40) >> 4;
                        final int n16 = (n2 & 0x30) >> 4;
                        this.c(n16 * 4 + 0);
                        this.d(n16 * 4 + 1);
                        this.e(n16 * 4 + 2);
                        this.f(n16 * 4 + 3);
                        this.g(n15 * 8 + 0);
                        this.h(n15 * 8 + 1);
                        this.i(n15 * 8 + 2);
                        this.j(n15 * 8 + 3);
                        this.k(n15 * 8 + 4);
                        this.l(n15 * 8 + 5);
                        this.m(n15 * 8 + 6);
                        this.n(n15 * 8 + 7);
                        return;
                    }
                    break;
                }
                case 87: {
                    if (n == 24576) {
                        final int n17 = (n2 & 0x2) >> 1;
                        this.g(n17 * 8 + 0);
                        this.h(n17 * 8 + 1);
                        this.i(n17 * 8 + 2);
                        this.j(n17 * 8 + 3);
                        this.k(n17 * 8 + 4);
                        this.l(n17 * 8 + 5);
                        this.m(n17 * 8 + 6);
                        this.n(n17 * 8 + 7);
                        return;
                    }
                    break;
                }
                case 89: {
                    if (n < 32768) {
                        return;
                    }
                    if ((n & 0xFF00) == 0xC000) {
                        final int n18 = (n2 & 0x70) >> 4;
                        final int n19 = (n2 & 0x80) >> 4 | (n2 & 0x7);
                        this.c(n18 * 2 + 0);
                        this.d(n18 * 2 + 1);
                        this.g(n19 * 8 + 0);
                        this.h(n19 * 8 + 1);
                        this.i(n19 * 8 + 2);
                        this.j(n19 * 8 + 3);
                        this.k(n19 * 8 + 4);
                        this.l(n19 * 8 + 5);
                        this.m(n19 * 8 + 6);
                        this.n(n19 * 8 + 7);
                        if ((n2 & 0x8) != 0x0) {
                            this.b(1, 1, 1, 1);
                        }
                        else {
                            this.b(0, 0, 0, 0);
                        }
                        return;
                    }
                    break;
                }
                case 92: {
                    if (n < 32768) {
                        return;
                    }
                    final int n20 = ((n2 = (n & 0xFF)) & 0xF) << 1;
                    final int n21 = n2 & 0xF;
                    if (n >= 36864) {
                        if ((n2 & 0xF0) == 0xD0) {
                            this.a(0, 1, n20, n20 + 1);
                        }
                        else if ((n2 & 0xF0) == 0xE0) {
                            this.a(n21 * 8, n21 * 8 + 1, n21 * 8 + 2, n21 * 8 + 3, n21 * 8 + 4, n21 * 8 + 5, n21 * 8 + 6, n21 * 8 + 7);
                        }
                    }
                    else if ((n2 & 0xF0) == 0xB0) {
                        this.a(0, 1, n20, n20 + 1);
                    }
                    else if ((n2 & 0xF0) == 0x70) {
                        this.a(n21 * 8, n21 * 8 + 1, n21 * 8 + 2, n21 * 8 + 3, n21 * 8 + 4, n21 * 8 + 5, n21 * 8 + 6, n21 * 8 + 7);
                    }
                    return;
                }
                case 93: {
                    if (n == 24576) {
                        this.c(n2 * 2 + 0);
                        this.d(n2 * 2 + 1);
                        return;
                    }
                    break;
                }
                case 94: {
                    if ((n & 0xFFF0) == 0xFF00) {
                        final int n22 = (n2 & 0x1C) >> 2;
                        this.c(n22 * 2 + 0);
                        this.d(n22 * 2 + 1);
                        return;
                    }
                    break;
                }
                case 97: {
                    if (n >= 32768 && n < 49152) {
                        final int n23 = n2 & 0xF;
                        this.e(n23 * 2 + 0);
                        this.f(n23 * 2 + 1);
                        if ((n2 & 0x80) == 0x0) {
                            this.c();
                        }
                        else {
                            this.b();
                        }
                        return;
                    }
                    break;
                }
                case 101: {
                    if (n >= 24576 && n < 32768) {
                        n2 &= 0x3;
                        this.g(n2 * 8 + 0);
                        this.h(n2 * 8 + 1);
                        this.i(n2 * 8 + 2);
                        this.j(n2 * 8 + 3);
                        this.k(n2 * 8 + 4);
                        this.l(n2 * 8 + 5);
                        this.m(n2 * 8 + 6);
                        this.n(n2 * 8 + 7);
                    }
                    if (n >= 32768) {
                        n2 &= 0x3;
                        this.g(n2 * 8 + 0);
                        this.h(n2 * 8 + 1);
                        this.i(n2 * 8 + 2);
                        this.j(n2 * 8 + 3);
                        this.k(n2 * 8 + 4);
                        this.l(n2 * 8 + 5);
                        this.m(n2 * 8 + 6);
                        this.n(n2 * 8 + 7);
                        return;
                    }
                    break;
                }
                case 140: {
                    if (n >= 24576 && n < 32768) {
                        final int n24 = (n2 & 0xF0) >> 4;
                        final int n25 = n2 & 0xF;
                        this.c(n24 * 4 + 0);
                        this.d(n24 * 4 + 1);
                        this.e(n24 * 4 + 2);
                        this.f(n24 * 4 + 3);
                        this.g(n25 * 8 + 0);
                        this.h(n25 * 8 + 1);
                        this.i(n25 * 8 + 2);
                        this.j(n25 * 8 + 3);
                        this.k(n25 * 8 + 4);
                        this.l(n25 * 8 + 5);
                        this.m(n25 * 8 + 6);
                        this.n(n25 * 8 + 7);
                        return;
                    }
                    break;
                }
                case 151: {
                    if (n < 32768) {
                        return;
                    }
                    switch (n & 0xF000) {
                        case 32768: {
                            this.c(n2);
                            break;
                        }
                        case 40960: {
                            this.d(n2);
                            break;
                        }
                        case 49152: {
                            this.e(n2);
                            break;
                        }
                        case 57344: {
                            this.g(n2 * 4 + 0);
                            this.h(n2 * 4 + 1);
                            this.i(n2 * 4 + 2);
                            this.j(n2 * 4 + 3);
                            break;
                        }
                        case 61440: {
                            this.k(n2 * 4 + 0);
                            this.l(n2 * 4 + 1);
                            this.m(n2 * 4 + 2);
                            this.n(n2 * 4 + 3);
                            break;
                        }
                    }
                    return;
                }
                case 180: {
                    this.e((n2 & 0x7) * 2 + 0);
                    this.f((n2 & 0x7) * 2 + 1);
                    return;
                }
                case 184: {
                    if (n >= 24576 && n < 32768) {
                        final int n26 = (n2 & 0x20) >> 2;
                        final int n27 = (n2 & 0x2) << 2 | (n2 & 0x4);
                        this.a(n27, n27 + 1, n27 + 2, n27 + 3, n26, n26 + 1, n26 + 2, n26 + 3);
                        return;
                    }
                    break;
                }
                case 185: {
                    if (n < 32768) {
                        return;
                    }
                    if ((this.b == 0 && (n2 & 0x3) != 0x0) || (this.b == 1 && n2 == 33)) {
                        this.a(0, 1, 2, 3, 4, 5, 6, 7);
                        return;
                    }
                    for (int i = 0; i < 8192; ++i) {
                        super.a.a.a.c[i] = 255;
                    }
                    return;
                }
                case 222: {
                    if (n < 32768) {
                        return;
                    }
                    switch (n & 0xF003) {
                        case 32768: {
                            this.c(n2);
                            break;
                        }
                        case 40960: {
                            this.d(n2);
                            break;
                        }
                        case 45056: {
                            this.g(n2);
                            break;
                        }
                        case 45058: {
                            this.h(n2);
                            break;
                        }
                        case 49152: {
                            this.i(n2);
                            break;
                        }
                        case 49154: {
                            this.j(n2);
                            break;
                        }
                        case 53248: {
                            this.k(n2);
                            break;
                        }
                        case 53250: {
                            this.l(n2);
                            break;
                        }
                        case 57344: {
                            this.m(n2);
                            break;
                        }
                        case 57346: {
                            this.n(n2);
                            break;
                        }
                    }
                    return;
                }
                case 229: {
                    if (n < 32768) {
                        return;
                    }
                    if (((n &= 0xFFF) & 0x20) != 0x0) {
                        this.c();
                    }
                    else {
                        this.b();
                    }
                    if ((n & 0x1E) != 0x0) {
                        final int n28 = n & 0x1F;
                        final int n29 = n & 0xFFF;
                        this.c(n28 * 2 + 0);
                        this.d(n28 * 2 + 1);
                        this.e(n28 * 2 + 0);
                        this.f(n28 * 2 + 1);
                        this.g(n29 * 8 + 0);
                        this.h(n29 * 8 + 1);
                        this.i(n29 * 8 + 2);
                        this.j(n29 * 8 + 3);
                        this.k(n29 * 8 + 4);
                        this.l(n29 * 8 + 5);
                        this.m(n29 * 8 + 6);
                        this.n(n29 * 8 + 7);
                        return;
                    }
                    this.a(0, 1, 2, 3);
                    this.a(0, 1, 2, 3, 4, 5, 6, 7);
                    return;
                }
                case 231: {
                    if (n < 32768) {
                        return;
                    }
                    if ((n & 0x20) != 0x0) {
                        final int n30 = n >> 1 & 0xF;
                        this.c(n30 * 4 + 0);
                        this.d(n30 * 4 + 1);
                        this.e(n30 * 4 + 2);
                        this.f(n30 * 4 + 3);
                    }
                    else {
                        final int n31 = n & 0x1E;
                        this.c(n31 * 2 + 0);
                        this.d(n31 * 2 + 1);
                        this.e(n31 * 2 + 0);
                        this.f(n31 * 2 + 1);
                    }
                    if ((n & 0x80) != 0x0) {
                        this.c();
                        return;
                    }
                    this.b();
                    return;
                }
                case 232: {
                    if (n < 32768) {
                        return;
                    }
                    if (n == 36864) {
                        this.a[0] = (n2 & 0x18) >> 1;
                    }
                    else if (40960 <= n && n <= 65535) {
                        this.a[1] = (n2 & 0x3);
                    }
                    this.c((this.a[0] | this.a[1]) * 2 + 0);
                    this.d((this.a[0] | this.a[1]) * 2 + 1);
                    this.e((this.a[0] | 0x3) * 2 + 0);
                    this.f((this.a[0] | 0x3) * 2 + 1);
                    return;
                }
                case 233: {
                    if (n < 32768) {
                        return;
                    }
                    if ((n2 & 0x20) != 0x0) {
                        final int n32 = n2 & 0x1F;
                        this.c(n32 * 2 + 0);
                        this.d(n32 * 2 + 1);
                        this.e(n32 * 2 + 0);
                        this.f(n32 * 2 + 1);
                    }
                    else {
                        final int n33 = (n2 & 0x1E) >> 1;
                        this.c(n33 * 4 + 0);
                        this.d(n33 * 4 + 1);
                        this.e(n33 * 4 + 2);
                        this.f(n33 * 4 + 3);
                    }
                    if ((n2 & 0xC0) == 0x0) {
                        this.b(0, 0, 0, 1);
                        return;
                    }
                    if ((n2 & 0xC0) == 0x40) {
                        this.b();
                        return;
                    }
                    if ((n2 & 0xC0) == 0x80) {
                        this.c();
                        return;
                    }
                    this.b(1, 1, 1, 1);
                    return;
                }
                case 242: {
                    if ((n & 0x1) != 0x0) {
                        this.c(((n & 0x78) >> 1) + 0);
                        this.d(((n & 0x78) >> 1) + 1);
                        this.e(((n & 0x78) >> 1) + 2);
                        this.f(((n & 0x78) >> 1) + 3);
                        return;
                    }
                    break;
                }
                case 244: {
                    if (n < 32768) {
                        return;
                    }
                    if (n2 < 4) {
                        n2 <<= 2;
                        this.a(n2, n2 + 1, n2 + 2, n2 + 3);
                        return;
                    }
                    if (n2 >= 8 && n2 <= 15) {
                        n2 -= 8;
                        n2 <<= 3;
                        this.a(n2, n2 + 1, n2 + 2, n2 + 3, n2 + 4, n2 + 5, n2 + 6, n2 + 7);
                        return;
                    }
                    break;
                }
                case 246: {
                    if (!(n >= 24576 & n < 32768)) {
                        break;
                    }
                    switch (n) {
                        case 24576: {
                            this.c(n2);
                            return;
                        }
                        case 24577: {
                            this.d(n2);
                            return;
                        }
                        case 24578: {
                            this.e(n2);
                            return;
                        }
                        case 24579: {
                            this.f(n2);
                            return;
                        }
                        case 24580: {
                            this.g(n2 * 2 + 0);
                            this.h(n2 * 2 + 1);
                            return;
                        }
                        case 24581: {
                            this.i(n2 * 2 + 0);
                            this.j(n2 * 2 + 1);
                            return;
                        }
                        case 24582: {
                            this.k(n2 * 2 + 0);
                            this.l(n2 * 2 + 1);
                            return;
                        }
                        case 24583: {
                            this.m(n2 * 2 + 0);
                            this.n(n2 * 2 + 1);
                            break Label_4591;
                        }
                    }
                    break;
                }
            }
        }
    }
    
    public final void a() {
        switch (this.a) {
            case 7:
            case 185:
            case 233: {
                this.a(0, 1, 2, 3);
                break;
            }
            case 58: {
                this.a(0, 1, 0, 1);
                this.a(0, 1, 2, 3, 4, 5, 6, 7);
                break;
            }
            case 66: {
                this.a(0, 1, 2, 3);
                this.a(0, 1, 2, 3, 4, 5, 6, 7);
                break;
            }
            case 71:
            case 246: {
                this.a(0, 1, this.b() - 2, this.b() - 1);
                break;
            }
            case 2:
            case 34:
            case 72:
            case 78:
            case 89:
            case 93:
            case 94:
            case 151:
            case 184:
            case 232:
            case 240: {
                this.a(0, 1, this.b() - 2, this.b() - 1);
                if (this.c() > 0) {
                    this.a(0, 1, 2, 3, 4, 5, 6, 7);
                    break;
                }
                break;
            }
            case 92:
            case 222: {
                this.a(0, 1, this.b() - 2, this.b() - 1);
                if (this.c() >= 8) {
                    this.a(0, 1, 2, 3, 4, 5, 6, 7);
                    break;
                }
                break;
            }
            case 97: {
                this.a(this.b() - 2, this.b() - 1, 0, 1);
                if (this.c() > 0) {
                    this.a(0, 1, 2, 3, 4, 5, 6, 7);
                    break;
                }
                break;
            }
            case 99: {
                if (this.b() > 0) {
                    this.a(0, 1, 2, 3);
                }
                else if (this.b() > 1) {
                    this.a(0, 1, 0, 1);
                }
                else {
                    this.a(0, 0, 0, 0);
                }
                if (this.c() > 0) {
                    this.a(0, 1, 2, 3, 4, 5, 6, 7);
                    break;
                }
                break;
            }
            case 8:
            case 11:
            case 77:
            case 79:
            case 86:
            case 87:
            case 101:
            case 140:
            case 180:
            case 181:
            case 231:
            case 242:
            case 244: {
                this.a(0, 1, 2, 3);
                if (this.c() > 0) {
                    this.a(0, 1, 2, 3, 4, 5, 6, 7);
                    break;
                }
                break;
            }
            case 62:
            case 229: {
                this.a(0, 1, 2, 3);
                if (this.c() >= 8) {
                    this.a(0, 1, 2, 3, 4, 5, 6, 7);
                    break;
                }
                break;
            }
            default: {
                System.out.println("FATAL: No Reset Routine for Mapper " + this.a);
                break;
            }
        }
        switch (this.a) {
            case 11: {
                this.b();
            }
            case 222: {
                this.c();
            }
            case 232: {
                this.a[0] = 12;
                this.a[1] = 0;
                break;
            }
        }
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a(final long n) {
        switch (this.a) {
            case 66: {
                if (n == 292343108L) {
                    super.a.a.b = 1;
                    return;
                }
                break;
            }
            case 185: {
                if (n == 3473710487L) {
                    this.b = 1;
                    break;
                }
                break;
            }
        }
    }
}
