import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

class throw
{
    static final int W = 0;
    static final int X = 1;
    static final int Y = 2;
    static final int Z = 3;
    static final int _a = 4;
    static final int aa = 5;
    static final int ba = 6;
    static final int ca = 7;
    static final int da = 8;
    static final int ea = 9;
    static final int fa = 10;
    static final int ga = 11;
    static final int ha = 12;
    static final int ia = 13;
    static final int ja = 25;
    static final int ka = 30;
    static final int la = 35;
    static final int ma = 40;
    static final int na = 75;
    Rectangle oa;
    private int pa;
    private int qa;
    private String ra;
    
    throw(final int pa, final Rectangle rectangle) {
        this.pa = pa;
        this.oa = new Rectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        this.reset();
    }
    
    void reset() {
        this.qa = -1;
        this.ra = null;
    }
    
    int _() {
        return this.qa;
    }
    
    int a() {
        if (this.qa == -1) {
            this.qa = this.b();
        }
        return this.qa;
    }
    
    String b() {
        return this.ra;
    }
    
    void b(final String ra) {
        this.ra = ra;
    }
    
    int b() {
        switch (this.pa) {
            case 0: {
                return this._(1);
            }
            case 1: {
                return this._(2);
            }
            case 2: {
                return this._(3);
            }
            case 3: {
                return this._(4);
            }
            case 4: {
                return this._(5);
            }
            case 5: {
                return this._(6);
            }
            case 6: {
                return this.a(3);
            }
            case 7: {
                return this.b(0);
            }
            case 8: {
                return this.b(1);
            }
            case 9: {
                return this.g();
            }
            case 10: {
                return this.h();
            }
            case 11: {
                return this.a(4);
            }
            case 12: {
                return this.i();
            }
            case 13: {
                return this.a(5);
            }
            default: {
                return -1;
            }
        }
    }
    
    private int _(final int n) {
        int n2 = 0;
        for (int i = 0; i < 5; ++i) {
            if (synchronized.sa[i] == n) {
                n2 += n;
            }
        }
        return n2;
    }
    
    private int a(final int n) {
        int i = 0;
        int j = 0;
    Label_0073:
        while (j < 6) {
            if (synchronized.ta[j] >= n) {
                switch (n) {
                    default: {
                        break Label_0073;
                    }
                    case 3: {
                        i = this.i();
                        break Label_0073;
                    }
                    case 4: {
                        i = 40;
                        break Label_0073;
                    }
                    case 5: {
                        i = 75;
                        break Label_0073;
                    }
                }
            }
            else {
                ++j;
            }
        }
        return i;
    }
    
    private int b(final int n) {
        int i = this.i();
        for (int j = 0; j < 5; ++j) {
            if (synchronized.sa[j] % 2 == n) {
                i = 0;
                break;
            }
        }
        return i;
    }
    
    private int g() {
        int n = 30;
        for (int i = 0; i < 6; ++i) {
            if (synchronized.ta[i] != 0 && synchronized.ta[i] != 3 && synchronized.ta[i] != 2) {
                n = 0;
                break;
            }
        }
        return n;
    }
    
    private int h() {
        int n = 0;
        if ((synchronized.ta[0] == 0 && synchronized.ta[5] != 0) || (synchronized.ta[0] != 0 && synchronized.ta[5] == 0)) {
            n = 35;
            for (int i = 1; i < 5; ++i) {
                if (synchronized.ta[i] != 1) {
                    n = 0;
                    break;
                }
            }
        }
        return n;
    }
    
    private int i() {
        int n = 0;
        for (int i = 0; i < 5; ++i) {
            n += synchronized.sa[i];
        }
        return n;
    }
    
    public boolean contains(final int n, final int n2) {
        return this.oa.contains(n, n2);
    }
}
