// 
// Decompiled by Procyon v0.5.30
// 

package neat.system.graphics.renderer;

import neat.bb;
import neat.lb;
import neat.system.gb;
import neat.g;
import neat.system.hb;
import neat.kb;
import neat.i;
import neat.system.f;
import neat.system.cb;

public class e implements cb
{
    private static f a;
    static int b;
    static i c;
    kb d;
    AnimShadow e;
    private i f;
    private j g;
    private boolean h;
    private hb i;
    private int j;
    private g k;
    private boolean l;
    private static /* synthetic */ Class m;
    private static String[] z;
    
    void a(final neat.system.graphics.renderer.g g) {
        if (g == null) {
            throw new RuntimeException(neat.system.graphics.renderer.e.z[8]);
        }
        if (this.f.b(g)) {
            throw new RuntimeException(neat.system.graphics.renderer.e.z[7]);
        }
        this.f.a(g);
    }
    
    void b(final neat.system.graphics.renderer.g g) {
        if (g == null) {
            throw new RuntimeException(neat.system.graphics.renderer.e.z[2]);
        }
        if (!this.f.b(g)) {
            throw new RuntimeException(neat.system.graphics.renderer.e.z[3]);
        }
        this.f.d(g);
    }
    
    public void a() {
        if (this.f.e()) {
            this.f();
        }
    }
    
    public int b() {
        if (this.g == null) {
            return 0;
        }
        return this.g.a();
    }
    
    public int c() {
        if (this.g == null) {
            return 0;
        }
        return this.g.b();
    }
    
    public j d() {
        if (this.h) {
            return this.i().d();
        }
        return this.g;
    }
    
    public kb e() {
        return this.d;
    }
    
    boolean f() {
        return !this.h && this.e != null && this.e.hasTransparency;
    }
    
    int g() {
        if (this.h) {
            return -1;
        }
        if (this.e == null) {
            return 0;
        }
        return this.e.transparencyColor;
    }
    
    private AnimShadow a(final gb gb, final kb kb) {
        AnimShadow animShadow = null;
        final lb a = lb.a();
        a.a(kb);
        a.c(neat.system.graphics.renderer.e.z[1]);
        final kb b = a.b();
        final kb a2 = kb.a(neat.system.graphics.renderer.e.z[0]);
        final neat.gb d = neat.gb.d();
        final neat.bb a3 = d.a(gb, null, b, a2);
        d.f();
        b.f();
        a2.f();
        if (a3 instanceof AnimShadow) {
            animShadow = (AnimShadow)a3;
            if (animShadow.frames != null) {
                animShadow.a(this.b(), this.c());
            }
        }
        return animShadow;
    }
    
    protected boolean h() {
        return this.h;
    }
    
    protected void a(int j) {
        if (!this.h) {
            return;
        }
        if (j < 0) {
            j = 0;
        }
        else if (j >= this.k.f()) {
            j = this.k.f() - 1;
        }
        this.j = j;
    }
    
    private e i() {
        if (!this.h) {
            return null;
        }
        return (e)this.k.a(this.j);
    }
    
    protected int j() {
        if (!this.h) {
            return 0;
        }
        return this.k.f();
    }
    
    protected int k() {
        if (!this.h) {
            return -1;
        }
        return this.j;
    }
    
    public static e a(final gb gb, final kb kb, final int n) {
        return a(gb, kb, n, -1);
    }
    
    public static e a(final gb gb, final kb kb, final int n, final boolean b) {
        return a(gb, kb, n, b ? 1 : 0);
    }
    
    public static e a(final gb gb, final kb kb, int transparencyColor, int keepingResource) {
        final e e = (e)neat.system.graphics.renderer.e.a.a();
        e.d = kb.b();
        e.e = e.a(gb, kb);
        if (keepingResource == -1 && e.e != null) {
            keepingResource = e.e.keepingResource;
        }
        boolean l = keepingResource == -1 || keepingResource == 1;
        if (!gb.a()) {
            l = true;
        }
        boolean isRAWAnim = false;
        if (e.e != null) {
            isRAWAnim = e.e.isRAWAnim;
        }
        if (isRAWAnim) {
            e.h = isRAWAnim;
            (e.i = gb.b(kb)).a();
            int frameNumber = e.e.frameNumber;
            if (frameNumber <= 0) {
                frameNumber = 1;
            }
            int frameFirst = e.e.frameFirst;
            if (frameFirst < 0) {
                frameFirst = 0;
            }
            if (e.k == null) {
                e.k = g.j();
            }
            e.k.c(frameNumber);
            for (int i = 0; i < frameNumber; ++i) {
                final lb a = lb.a();
                a.a(kb);
                a.d(i + frameFirst);
                a.c(neat.system.graphics.renderer.e.z[5]);
                final kb b = a.b();
                final j a2 = e.i.a(gb, e.e, b);
                if (a2 == null) {
                    break;
                }
                final e a3 = a(gb, b, a2);
                b.f();
                e.k.a(i, a3);
            }
            e.j = 0;
            e.g = e.d();
            e.l = true;
        }
        else {
            if (e.e != null) {
                if (e.e.hasTransparency) {
                    transparencyColor = e.e.transparencyColor;
                }
                else {
                    transparencyColor = -1;
                }
            }
            e.g = gb.a(kb, transparencyColor, l);
            if (e.g == null) {
                throw new RuntimeException(neat.system.graphics.renderer.e.z[4] + kb);
            }
            e.l = l;
        }
        return e;
    }
    
    public static e a(final gb gb, final int n, final int n2) {
        final e e = (e)neat.system.graphics.renderer.e.a.a();
        e.g = gb.a(n, n2);
        e.l = false;
        return e;
    }
    
    public static e a(final gb gb, final int n, final int n2, final int n3) {
        final e e = (e)neat.system.graphics.renderer.e.a.a();
        e.g = gb.a(n, n2, n3);
        e.l = false;
        return e;
    }
    
    public static e a(final gb gb, final kb kb, final j g) {
        final e e = (e)neat.system.graphics.renderer.e.a.a();
        e.d = kb.b();
        e.e = e.a(gb, kb);
        e.g = g;
        if (e.g == null) {
            throw new RuntimeException(neat.system.graphics.renderer.e.z[4] + kb);
        }
        e.l = true;
        return e;
    }
    
    public void f() {
        if (!this.f.e()) {
            throw new RuntimeException(neat.system.graphics.renderer.e.z[6]);
        }
        neat.system.graphics.renderer.e.a.a(this);
    }
    
    public void g() {
        this.e = null;
        this.f = neat.i.k();
        this.h = false;
        this.j = 0;
        this.l = true;
    }
    
    public void h() {
        if (this.f != null) {
            this.f.f();
            this.f = null;
        }
        if (this.e != null) {
            this.e.f();
            this.e = null;
        }
        if (!this.l) {
            this.g.f();
        }
        this.g = null;
        if (this.k != null) {
            this.k.i();
            this.k.f();
            this.k = null;
        }
        if (this.i != null) {
            this.i.b();
            this.i = null;
        }
        if (this.d != null) {
            this.d.f();
            this.d = null;
        }
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public e() {
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.i = null;
        this.k = null;
    }
    
    static {
        final String[] z = new String[10];
        final int n = 0;
        final char[] charArray = "wm\u0010\u00160bj\u0016\u0015".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0097: {
                if (n2 > 1) {
                    break Label_0097;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = '\u0016';
                            break;
                        }
                        case 1: {
                            c2 = '\u0003';
                            break;
                        }
                        case 2: {
                            c2 = 'y';
                            break;
                        }
                        case 3: {
                            c2 = '{';
                            break;
                        }
                        default: {
                            c2 = 'Q';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        z[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "8b\u0017\u0012<".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0213: {
                if (n6 > 1) {
                    break Label_0213;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = '\u0016';
                            break;
                        }
                        case 1: {
                            c4 = '\u0003';
                            break;
                        }
                        case 2: {
                            c4 = 'y';
                            break;
                        }
                        case 3: {
                            c4 = '{';
                            break;
                        }
                        default: {
                            c4 = 'Q';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 > n8) {
                continue;
            }
            break;
        }
        z[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "\u007fm\u000f\u001a=\u007fgY\t4xg\u001c\t4dJ\u0014\u001a6s#\r\u0014qcm\u0015\u0012?}".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0329: {
                if (n10 > 1) {
                    break Label_0329;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = '\u0016';
                            break;
                        }
                        case 1: {
                            c6 = '\u0003';
                            break;
                        }
                        case 2: {
                            c6 = 'y';
                            break;
                        }
                        case 3: {
                            c6 = '{';
                            break;
                        }
                        default: {
                            c6 = 'Q';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 > n12) {
                continue;
            }
            break;
        }
        z[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "df\u0017\u001f4df\u000b\u0012<wd\u001c[?ywY\u00178xh\u001c\u001f".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0445: {
                if (n14 > 1) {
                    break Label_0445;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = '\u0016';
                            break;
                        }
                        case 1: {
                            c8 = '\u0003';
                            break;
                        }
                        case 2: {
                            c8 = 'y';
                            break;
                        }
                        case 3: {
                            c8 = '{';
                            break;
                        }
                        default: {
                            c8 = 'Q';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 > n16) {
                continue;
            }
            break;
        }
        z[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "\u007fn\u0018\u001c46e\u0010\u001746m\u0016\u000fqpl\f\u00155+".toCharArray();
        int length5;
        int n19;
        final int n18 = n19 = (length5 = charArray5.length);
        int n20 = 0;
        while (true) {
            Label_0561: {
                if (n18 > 1) {
                    break Label_0561;
                }
                length5 = (n19 = n20);
                do {
                    final char c9 = charArray5[n19];
                    char c10 = '\0';
                    switch (n20 % 5) {
                        case 0: {
                            c10 = '\u0016';
                            break;
                        }
                        case 1: {
                            c10 = '\u0003';
                            break;
                        }
                        case 2: {
                            c10 = 'y';
                            break;
                        }
                        case 3: {
                            c10 = '{';
                            break;
                        }
                        default: {
                            c10 = 'Q';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 > n20) {
                continue;
            }
            break;
        }
        z[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "8q\u0018\f".toCharArray();
        int length6;
        int n23;
        final int n22 = n23 = (length6 = charArray6.length);
        int n24 = 0;
        while (true) {
            Label_0677: {
                if (n22 > 1) {
                    break Label_0677;
                }
                length6 = (n23 = n24);
                do {
                    final char c11 = charArray6[n23];
                    char c12 = '\0';
                    switch (n24 % 5) {
                        case 0: {
                            c12 = '\u0016';
                            break;
                        }
                        case 1: {
                            c12 = '\u0003';
                            break;
                        }
                        case 2: {
                            c12 = 'y';
                            break;
                        }
                        case 3: {
                            c12 = '{';
                            break;
                        }
                        default: {
                            c12 = 'Q';
                            break;
                        }
                    }
                    charArray6[length6] = (char)(c11 ^ c12);
                    ++n24;
                } while (n22 == 0);
            }
            if (n22 > n24) {
                continue;
            }
            break;
        }
        z[n21] = new String(charArray6).intern();
        final int n25 = 6;
        final char[] charArray7 = "ub\u0017\u0015>b#\u001d\u001e=sw\u001c[3se\u0016\t46q\u001c\u0016>`j\u0017\u001cqzj\u0017\u00104rA\u0010\u000f<ws\n".toCharArray();
        int length7;
        int n27;
        final int n26 = n27 = (length7 = charArray7.length);
        int n28 = 0;
        while (true) {
            Label_0793: {
                if (n26 > 1) {
                    break Label_0793;
                }
                length7 = (n27 = n28);
                do {
                    final char c13 = charArray7[n27];
                    char c14 = '\0';
                    switch (n28 % 5) {
                        case 0: {
                            c14 = '\u0016';
                            break;
                        }
                        case 1: {
                            c14 = '\u0003';
                            break;
                        }
                        case 2: {
                            c14 = 'y';
                            break;
                        }
                        case 3: {
                            c14 = '{';
                            break;
                        }
                        default: {
                            c14 = 'Q';
                            break;
                        }
                    }
                    charArray7[length7] = (char)(c13 ^ c14);
                    ++n28;
                } while (n26 == 0);
            }
            if (n26 > n28) {
                continue;
            }
            break;
        }
        z[n25] = new String(charArray7).intern();
        final int n29 = 7;
        final char[] charArray8 = "tj\r\u00160f#\u0018\u0017#sb\u001d\u0002qzj\u0017\u00104r".toCharArray();
        int length8;
        int n31;
        final int n30 = n31 = (length8 = charArray8.length);
        int n32 = 0;
        while (true) {
            Label_0909: {
                if (n30 > 1) {
                    break Label_0909;
                }
                length8 = (n31 = n32);
                do {
                    final char c15 = charArray8[n31];
                    char c16 = '\0';
                    switch (n32 % 5) {
                        case 0: {
                            c16 = '\u0016';
                            break;
                        }
                        case 1: {
                            c16 = '\u0003';
                            break;
                        }
                        case 2: {
                            c16 = 'y';
                            break;
                        }
                        case 3: {
                            c16 = '{';
                            break;
                        }
                        default: {
                            c16 = 'Q';
                            break;
                        }
                    }
                    charArray8[length8] = (char)(c15 ^ c16);
                    ++n32;
                } while (n30 == 0);
            }
            if (n30 > n32) {
                continue;
            }
            break;
        }
        z[n29] = new String(charArray8).intern();
        final int n33 = 8;
        final char[] charArray9 = "\u007fm\u000f\u001a=\u007fgY\t4xg\u001c\t4dJ\u0014\u001a6s#\r\u0014qzj\u0017\u0010".toCharArray();
        int length9;
        int n35;
        final int n34 = n35 = (length9 = charArray9.length);
        int n36 = 0;
        while (true) {
            Label_1025: {
                if (n34 > 1) {
                    break Label_1025;
                }
                length9 = (n35 = n36);
                do {
                    final char c17 = charArray9[n35];
                    char c18 = '\0';
                    switch (n36 % 5) {
                        case 0: {
                            c18 = '\u0016';
                            break;
                        }
                        case 1: {
                            c18 = '\u0003';
                            break;
                        }
                        case 2: {
                            c18 = 'y';
                            break;
                        }
                        case 3: {
                            c18 = '{';
                            break;
                        }
                        default: {
                            c18 = 'Q';
                            break;
                        }
                    }
                    charArray9[length9] = (char)(c17 ^ c18);
                    ++n36;
                } while (n34 == 0);
            }
            if (n34 > n36) {
                continue;
            }
            break;
        }
        z[n33] = new String(charArray9).intern();
        final int n37 = 9;
        final char[] charArray10 = "xf\u0018\u000f\u007fez\n\u000f4{-\u001e\t0fk\u0010\u0018\"8q\u001c\u00155sq\u001c\t\u007fs".toCharArray();
        int length10;
        int n39;
        final int n38 = n39 = (length10 = charArray10.length);
        int n40 = 0;
        while (true) {
            Label_1145: {
                if (n38 > 1) {
                    break Label_1145;
                }
                length10 = (n39 = n40);
                do {
                    final char c19 = charArray10[n39];
                    char c20 = '\0';
                    switch (n40 % 5) {
                        case 0: {
                            c20 = '\u0016';
                            break;
                        }
                        case 1: {
                            c20 = '\u0003';
                            break;
                        }
                        case 2: {
                            c20 = 'y';
                            break;
                        }
                        case 3: {
                            c20 = '{';
                            break;
                        }
                        default: {
                            c20 = 'Q';
                            break;
                        }
                    }
                    charArray10[length10] = (char)(c19 ^ c20);
                    ++n40;
                } while (n38 == 0);
            }
            if (n38 <= n40) {
                z[n37] = new String(charArray10).intern();
                e.z = z;
                e.a = new f((e.m != null) ? e.m : (e.m = a(e.z[9])));
                e.b = 0;
                e.c = null;
                return;
            }
            continue;
        }
    }
}
