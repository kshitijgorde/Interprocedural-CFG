// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

import java.awt.Graphics;
import java.awt.Component;

final class q extends c implements h
{
    private int g;
    private int h;
    private Component i;
    private char[] j;
    private static int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private char[] t;
    private char u;
    private int v;
    private char w;
    private char x;
    private char y;
    private k z;
    private static byte[][] A;
    
    public q(final Component i) {
        this.g = 256;
        this.h = 192;
        this.i = null;
        this.j = null;
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.p = 0;
        this.q = 0;
        this.r = 0;
        this.s = 0;
        this.t = new char[] { '\0', '\u0010', '\0', '\0', '\0', '\0', '\0', '\0' };
        this.u = '\0';
        this.v = 0;
        this.w = '\0';
        this.x = '\0';
        this.y = '\0';
        this.i = i;
    }
    
    public final String toString() {
        return "Texas Instruments TMS9928A video chip emulation";
    }
    
    public final int b(final int n) {
        return this.j[15762];
    }
    
    public final void a(final int n) {
    }
    
    public final boolean a() {
        super.a(this.g, this.h);
        this.j = new char[16384];
        this.b();
        return true;
    }
    
    public final char a(final char c) {
        char u;
        if ((c & '\u0001') == '\u0001') {
            this.v = 1;
            u = this.u;
            this.u = (char)((this.u ^ '\u0080') & '\u00df');
            a.a.a.k.a(false);
        }
        else {
            final char[] j = this.j;
            final char y = this.y;
            this.y = (char)(y + '\u0001');
            u = j[y];
            this.y &= '\u3fff';
        }
        return u;
    }
    
    public final void a(final char c, final char w) {
        if ((c & '\u0001') == '\u0001') {
            if (this.v != 0) {
                this.w = w;
                final q q = this;
                --q.v;
                return;
            }
            final q q2 = this;
            ++q2.v;
            switch (w & '\u00c0') {
                case '\u0080': {
                    final q q3 = this;
                    final char c2 = (char)(w & '\u0007');
                    char w2 = this.w;
                    final char c3 = c2;
                    this = q3;
                    switch (c3) {
                        case 0: {
                            int s = 0;
                            switch ((w2 & '\u000e') >> 1 | (this.t[1] & '\u0018')) {
                                case '\u0010': {
                                    s = 0;
                                    break;
                                }
                                case '\0': {
                                    s = 1;
                                    break;
                                }
                                case '\u0001': {
                                    s = 2;
                                    break;
                                }
                                case '\b': {
                                    s = 3;
                                    break;
                                }
                                default: {
                                    s = this.s;
                                    break;
                                }
                            }
                            final q q4 = this;
                            q4.s &= 0x3;
                            if (s != this.s) {
                                this.m = (this.t[2] & a.a.a.q.A[s][0]) << 10;
                                this.n = (this.t[3] & a.a.a.q.A[s][1]) << 6;
                                this.l = (this.t[4] & a.a.a.q.A[s][2]) << 11;
                                this.p = (this.t[5] & a.a.a.q.A[s][3]) << 7;
                                this.o = this.t[6] << 11;
                                this.s = s;
                                break;
                            }
                            break;
                        }
                        case 1: {
                            int s2 = 0;
                            switch ((this.t[0] & '\u000e') >> 1 | (w2 & '\u0018')) {
                                case '\u0010': {
                                    s2 = 0;
                                    break;
                                }
                                case '\0': {
                                    s2 = 1;
                                    break;
                                }
                                case '\u0001': {
                                    s2 = 2;
                                    break;
                                }
                                case '\b': {
                                    s2 = 3;
                                    break;
                                }
                                default: {
                                    s2 = this.s;
                                    break;
                                }
                            }
                            final q q5 = this;
                            q5.s &= 0x3;
                            if (s2 != this.s) {
                                this.m = (this.t[2] & a.a.a.q.A[s2][0]) << 10;
                                this.n = (this.t[3] & a.a.a.q.A[s2][1]) << 6;
                                this.l = (this.t[4] & a.a.a.q.A[s2][2]) << 11;
                                this.p = (this.t[5] & a.a.a.q.A[s2][3]) << 7;
                                this.o = this.t[6] << 11;
                                this.s = s2;
                                break;
                            }
                            break;
                        }
                        case 2: {
                            this.m = (w2 & a.a.a.q.A[this.s][0]) << 10;
                            break;
                        }
                        case 3: {
                            this.n = (w2 & a.a.a.q.A[this.s][1]) << 6;
                            break;
                        }
                        case 4: {
                            this.l = (w2 & a.a.a.q.A[this.s][2]) << 11;
                            break;
                        }
                        case 5: {
                            this.p = (w2 & a.a.a.q.A[this.s][3]) << 7;
                            break;
                        }
                        case 6: {
                            w2 &= '?';
                            this.o = w2 << 11;
                            break;
                        }
                        case 7: {
                            this.q = (w2 >> 4 & '\u000f');
                            this.r = (w2 & '\u000f');
                            this.c();
                            break;
                        }
                    }
                    this.t[c3] = (char)(w2 & '\u00ff');
                }
                case '@': {
                    this.x = (char)((this.x & '\uff00') | (this.w & '\u00ff'));
                    this.x = (char)((this.x & '\u00ff') | (w & '?') << 8);
                }
                case '\0': {
                    this.y = (char)((this.y & '\uff00') | (this.w & '\u00ff'));
                    this.y = (char)((this.y & '\u00ff') | w << 8);
                    break;
                }
            }
        }
        else if (this.v > 0) {
            final char[] j = this.j;
            final q q6 = this;
            final char x = q6.x;
            q6.x = (char)(x + '\u0001');
            j[x] = w;
            final q q7 = this;
            q7.x &= '\u3fff';
        }
    }
    
    public final boolean b() {
        final char[] array = { '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0' };
        for (int i = 0; i < 8; ++i) {
            this.t[i] = array[i];
        }
        this.v = 1;
        this.x = '\0';
        this.y = '\0';
        this.u = '\0';
        this.q = 0;
        this.r = 0;
        this.s = 0;
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.p = 0;
        for (int j = 0; j < this.j.length; ++j) {
            this.j[j] = '\0';
        }
        return true;
    }
    
    public final void a(final k z) {
        this.z = z;
    }
    
    public final void a(final Graphics graphics) {
        switch (this.s) {
            case 0: {
                break;
            }
            case 1: {
                final int[] a = super.a;
                final int g = this.g;
                final int[] array = a;
                int n = 0;
                if ((this.t[1] & '@') == '@') {
                    int n2 = 0 + (g * (this.h - 192) >> 1) + (this.g - 256 >> 1);
                    int m = this.m;
                    for (int i = 24; i != 0; --i, n2 += (g << 3) - 256) {
                        for (int j = 0; j < 32; ++j, ++m) {
                            int n3 = this.l + (this.j[m] << 3);
                            final char c2;
                            final char c = (char)((c2 = this.j[this.n + (this.j[m] >> 3)]) >> 4);
                            final char c3 = (char)(c2 & '\u000f');
                            for (int k = 0; k < 8; ++k) {
                                final char c4 = this.j[n3++];
                                array[n2++] = (((c4 & '\u0080') == '\u0080') ? c : c3);
                                array[n2++] = (((c4 & '@') == '@') ? c : c3);
                                array[n2++] = (((c4 & ' ') == ' ') ? c : c3);
                                array[n2++] = (((c4 & '\u0010') == '\u0010') ? c : c3);
                                array[n2++] = (((c4 & '\b') == '\b') ? c : c3);
                                array[n2++] = (((c4 & '\u0004') == '\u0004') ? c : c3);
                                array[n2++] = (((c4 & '\u0002') == '\u0002') ? c : c3);
                                array[n2] = (((c4 & '\u0001') == '\u0001') ? c : c3);
                                n2 += g - 7;
                            }
                            n2 += 8 - (g << 3);
                        }
                    }
                    this.a(array, g);
                    break;
                }
                for (int l = 0; l < this.h; ++l, n += g) {
                    for (int n4 = 0; n4 < this.g; ++n4) {
                        array[n + n4] = this.r;
                    }
                }
                break;
            }
            case 2: {
                break;
            }
            case 3: {
                final int[] a2 = super.a;
                final int g2 = this.g;
                final int[] array2 = a2;
                int n5 = 0;
                if ((this.t[1] & '@') == '@') {
                    int n6 = 0 + (g2 * (this.h - 192) >> 1) + (this.g - 256 >> 1);
                    int m2 = this.m;
                    for (int n7 = 0; n7 < 24; ++n7, n6 += (g2 << 3) - 256) {
                        for (int n8 = 0; n8 < 32; ++n8, ++m2) {
                            int n9 = this.n + (this.j[m2] << 3) + ((n7 & 0x3) << 1);
                            final char c5 = (char)(this.j[n9] >> 4 & '\u000f');
                            final char c6 = (char)(this.j[n9] & '\u000f');
                            for (int n10 = 0; n10 < 4; ++n10, n6 += g2 - 7) {
                                array2[n6++] = c5;
                                array2[n6++] = c5;
                                array2[n6++] = c5;
                                array2[n6++] = c5;
                                array2[n6++] = c6;
                                array2[n6++] = c6;
                                array2[n6] = (array2[n6++] = c6);
                            }
                            ++n9;
                            final char c7 = (char)(this.j[n9] >> 4 & '\u000f');
                            final char c8 = (char)(this.j[n9] & '\u000f');
                            for (int n11 = 0; n11 < 4; ++n11, n6 += g2 - 7) {
                                array2[n6++] = c7;
                                array2[n6++] = c7;
                                array2[n6++] = c7;
                                array2[n6++] = c7;
                                array2[n6++] = c8;
                                array2[n6++] = c8;
                                array2[n6] = (array2[n6++] = c8);
                            }
                            n6 += 8 - g2 * 8;
                        }
                    }
                    this.a(array2, g2);
                    break;
                }
                for (int n12 = 0; n12 < this.h; ++n12, n5 += g2) {
                    for (int n13 = 0; n13 < this.g; ++n13) {
                        array2[n5 + n13] = this.r;
                    }
                }
                break;
            }
            default: {
                final int[] a3 = super.a;
                final int g3 = this.g;
                final int[] array3 = a3;
                for (int n14 = 0, n15 = 0; n15 < this.h; ++n15, n14 += g3) {
                    for (int n16 = 0; n16 < this.g; ++n16) {
                        array3[n14 + n16] = this.r;
                    }
                }
                break;
            }
        }
        super.a(graphics, this.i);
    }
    
    public final void c(final int n) {
        switch (this.s) {
            case 0: {
                final int[] a = super.a;
                final int g = this.g;
                final boolean b = (this.t[1] & '@') == '@' && n < 192;
                final int n2;
                if ((n2 = (n + (this.h - 192 >> 1)) % a.a.a.q.k) < this.h) {
                    int n3 = g * n2;
                    if (b) {
                        final int n4 = this.g - 240 >> 1;
                        final int n5 = this.g - 240 - n4;
                        for (int i = 0; i < n4; ++i) {
                            a[n3++] = this.r;
                        }
                        int n6 = this.m + (n >> 3) * 40;
                        final int n7 = this.l + (n & 0x7);
                        for (int j = 0; j < 40; ++j, ++n6) {
                            final char c = this.j[n7 + (this.j[n6] << 3)];
                            a[n3++] = (((c & '\u0080') == '\u0080') ? this.q : this.r);
                            a[n3++] = (((c & '@') == '@') ? this.q : this.r);
                            a[n3++] = (((c & ' ') == ' ') ? this.q : this.r);
                            a[n3++] = (((c & '\u0010') == '\u0010') ? this.q : this.r);
                            a[n3++] = (((c & '\b') == '\b') ? this.q : this.r);
                            a[n3++] = (((c & '\u0004') == '\u0004') ? this.q : this.r);
                        }
                        for (int k = 0; k < n5; ++k) {
                            a[n3++] = this.r;
                        }
                        break;
                    }
                    for (int l = 0; l < this.g; ++l) {
                        a[n3++] = this.r;
                    }
                }
            }
            case 1: {}
            case 2: {
                final int[] a2 = super.a;
                final int g2 = this.g;
                final boolean b2 = (this.t[1] & '@') == '@' && n < 192;
                final int n8;
                if ((n8 = (n + (this.h - 192 >> 1)) % a.a.a.q.k) < this.h) {
                    final int n9 = n8 >> 6 << 11;
                    int n10 = g2 * n8;
                    if (b2) {
                        final int n11 = this.g - 256 >> 1;
                        for (int n12 = 0; n12 < n11; ++n12) {
                            a2[n10++] = this.r;
                        }
                        int n13 = this.m + (n >> 3 << 5);
                        final int n14 = this.l + n9 + (n & 0x7);
                        final int n15 = this.n + n9 + (n & 0x7);
                        for (int n16 = 0; n16 < 32; ++n16, ++n13) {
                            final char c2 = (char)(this.j[n13] << 3);
                            final char c3 = (char)(n14 + c2);
                            final char c4 = this.j[n15 + c2];
                            final char c5 = this.j[c3];
                            final char c6 = (char)(c4 >> 4 & '\u00ff');
                            final char c7 = (char)(c4 & '\u000f');
                            a2[n10++] = (((c5 & '\u0080') == '\u0080') ? c6 : c7);
                            a2[n10++] = (((c5 & '@') == '@') ? c6 : c7);
                            a2[n10++] = (((c5 & ' ') == ' ') ? c6 : c7);
                            a2[n10++] = (((c5 & '\u0010') == '\u0010') ? c6 : c7);
                            a2[n10++] = (((c5 & '\b') == '\b') ? c6 : c7);
                            a2[n10++] = (((c5 & '\u0004') == '\u0004') ? c6 : c7);
                            a2[n10++] = (((c5 & '\u0002') == '\u0002') ? c6 : c7);
                            a2[n10++] = (((c5 & '\u0001') == '\u0001') ? c6 : c7);
                        }
                        for (int n17 = 0; n17 < n11; ++n17) {
                            a2[n10++] = this.r;
                        }
                    }
                    else {
                        for (int n18 = 0; n18 < this.g; ++n18) {
                            a2[n10++] = this.r;
                        }
                    }
                }
                if (n == 191) {
                    this.a(super.a, this.g);
                    break;
                }
                break;
            }
        }
    }
    
    private void a(final int[] array, final int n) {
        int i;
        int p2;
        for (i = 0, p2 = this.p; i < 32 && this.j[p2] != '\u00d0'; ++i, p2 += 4) {}
        if ((this.t[1] & '\u0002') == '\u0002') {
            for (p2 -= 4; i != 0; --i, p2 -= 4) {
                final char c = (char)(((this.j[p2 + 3] & '\u0080') == '\u0080') ? (this.j[p2 + 1] - ' ') : this.j[p2 + 1]);
                final char c2 = (char)(this.j[p2 + 3] & '\u000f');
                if (c <= '\u00f0' && c >= '\0' && c2 != '\0') {
                    int n2 = n * (this.h - 192 >> 1) + (this.g - 256 >> 1) + c;
                    int n3 = this.o + ((this.j[p2 + 2] & '\u00fc') << 3);
                    final char c3;
                    int j;
                    if ((c3 = (char)(this.j[p2] + '\u0001')) < '\u00c0') {
                        n2 += n * c3;
                        j = ((c3 > '°') ? ('\u00c0' - c3) : '\u0010');
                    }
                    else {
                        n3 += '\u0100' - c3;
                        j = c3 - ((c3 > '\u00f0') ? '\u00f0' : c3);
                    }
                    while (j != 0) {
                        for (int k = 0, n4 = this.j[n3], n5 = this.j[n3 + '\u0010']; k < 8; ++k, n4 <<= 1, n5 <<= 1, ++n2) {
                            if ((n4 & 0x80) == 0x80) {
                                array[n2] = c2;
                            }
                            if ((n5 & 0x80) == 0x80) {
                                array[n2 + 8] = c2;
                            }
                        }
                        --j;
                        ++n3;
                        n2 += n - 8;
                    }
                }
            }
        }
        else {
            while (true) {
                p2 -= 4;
                if (i == 0) {
                    break;
                }
                final char c4 = (char)(((this.j[p2 + 3] & '\u0080') == '\u0080') ? (this.j[p2 + 1] - ' ') : this.j[p2 + 1]);
                final char c5 = (char)(this.j[p2 + 3] & '\u000f');
                if (c4 <= '\u00f8' && c4 >= '\0' && c5 != '\0') {
                    int n6 = (n << 3) + 8 + c4;
                    int n7 = this.p + (this.j[p2 + 2] << 3);
                    final char c6;
                    int l;
                    if ((c6 = (char)(this.j[p2] + '\u0001')) < '\u00c0') {
                        n6 += n * c6;
                        l = ((c6 > '¸') ? ('\u00c0' - c6) : '\b');
                    }
                    else {
                        n7 += '\u0100' - c6;
                        l = c6 - ((c6 > '\u00f8') ? '\u00f8' : c6);
                    }
                    while (l != 0) {
                        for (int n8 = 0, n9 = this.j[n7]; n8 < 8; ++n8, n9 <<= 1, ++n6) {
                            if ((n9 & 0x80) == 0x80) {
                                array[n6] = c5;
                            }
                        }
                        --l;
                        ++n7;
                        n6 += n - 8;
                    }
                }
                --i;
            }
        }
    }
    
    private void c() {
        if (this.r != 0) {
            this.d[0] = this.d[this.r];
            this.e[0] = this.e[this.r];
            this.f[0] = this.f[this.r];
            return;
        }
        this.d[0] = this.d[1];
        this.e[0] = this.e[1];
        this.f[0] = this.f[1];
    }
    
    final void a(final k k, int n) {
        this.c();
        if (n >= 192) {
            switch (n) {
                case 193: {
                    if ((this.t[1] & ' ') == ' ') {
                        k.a(true);
                        return;
                    }
                    break;
                }
                case 212: {
                    final q q = this;
                    q.u |= '\u0080';
                    if ((this.u & ' ') == '\0') {
                        final q q2 = this = this;
                        q2.u &= '\u0080';
                        int n2 = 0;
                        for (int p2 = this.p; n2 < 32 && this.j[p2] != this.h; ++n2, p2 += 4) {}
                        Label_0810: {
                            if ((this.t[1] & '\u0002') == '\u0002') {
                                for (int i = 0, p3 = this.p; i < n2; ++i, p3 += 4) {
                                    if ((this.j[p3 + 3] & '\u000f') == '\u000f') {
                                        for (int j = i + 1, n3 = p3 + 4; j < n2; ++j, n3 += 4) {
                                            int l;
                                            int n4;
                                            if ((this.j[n3 + 3] & '\u000f') == '\u000f' && ((l = this.j[p3] - this.j[n3]) < 16 || l > 240) && ((n4 = this.j[p3 + 1] - this.j[n3 + 1]) < 16 || n4 > 240)) {
                                                int n5 = this.o + ((this.j[p3 + 2] & '\u00fc') << 3);
                                                int n6 = this.o + ((this.j[n3 + 2] & '\u00fc') << 3);
                                                if (l < 16) {
                                                    n6 += l;
                                                }
                                                else {
                                                    l = 256 - l;
                                                    n5 += l;
                                                }
                                                if (n4 > 240) {
                                                    n4 = 256 - n4;
                                                    final int n7 = n5;
                                                    n5 = n6;
                                                    n6 = n7;
                                                }
                                                while (l < 16) {
                                                    final char c = (char)((this.j[n5] << 8) + this.j[n5 + 16]);
                                                    if (((n = (this.j[n6] << 8) + this.j[n6 + 16]) & c >> n4) == c >> n4) {
                                                        break;
                                                    }
                                                    ++l;
                                                    ++n5;
                                                    ++n6;
                                                }
                                                if (l < 16) {
                                                    final q q3 = this;
                                                    q3.u |= ' ';
                                                    break Label_0810;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            else {
                                for (int n8 = 0, p4 = this.p; n8 < n2; ++n8, p4 += 4) {
                                    if ((this.j[p4 + 3] & '\u000f') == '\u000f') {
                                        for (int n9 = n8 + 1, n10 = p4 + 4; n9 < n2; ++n9, n10 += 4) {
                                            int n11;
                                            int n12;
                                            if ((this.j[n10 + 3] & '\u000f') == '\u000f' && ((n11 = this.j[p4] - this.j[n10]) < 8 || n11 > 248) && ((n12 = this.j[p4 + 1] - this.j[n10 + 1]) < 8 || n12 > 248)) {
                                                int n13 = this.o + (this.j[p4 + 2] << 3);
                                                int n14 = this.o + (this.j[n10 + 2] << 3);
                                                if (n11 < 8) {
                                                    n14 += n11;
                                                }
                                                else {
                                                    n11 = 256 - n11;
                                                    n13 += n11;
                                                }
                                                if (n12 > 248) {
                                                    n12 = 256 - n12;
                                                    final int n15 = n13;
                                                    n13 = n14;
                                                    n14 = n15;
                                                }
                                                while (n11 < 8 && (this.j[n14] & this.j[n13] >> n12) == '\0') {
                                                    ++n11;
                                                    ++n13;
                                                    ++n14;
                                                }
                                                if (n11 < 8) {
                                                    final q q4 = this;
                                                    q4.u |= ' ';
                                                    break Label_0810;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        return;
                    }
                    break;
                }
                case 261: {
                    final q q5 = this;
                    q5.u &= (char)(-65);
                    final q q6 = this;
                    q6.u &= (char)(-129);
                    k.a(false);
                    break;
                }
            }
            return;
        }
        if ((this.u & '@') == '@' && (this.t[0] & '\u0010') == '\u0010') {
            k.a(true);
        }
    }
    
    static {
        q.k = 313;
        q.A = new byte[][] { { 127, 0, 63, 0 }, { 127, -1, 63, -1 }, { 127, -128, 60, -1 }, { 127, 0, 63, -1 } };
    }
}
