import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class s extends r
{
    private static final int e = 49;
    private static final int f = 50;
    private static final int g = 51;
    private static final int h = 52;
    private static final int i = 53;
    private static final int j = 54;
    private static final int k = 55;
    private static final int l = 56;
    private static final int m = 57;
    private static final int n = 43;
    private static final int o = 45;
    private static final int p = 61;
    private static final int q = 32;
    private static final int r = 46;
    private static final int s = 1006;
    private static final int t = 1007;
    private static final int u = 1004;
    private static final int v = 1005;
    private static final int w = 1002;
    private static final int x = 1003;
    private static final int y = 1000;
    private static final int z = 1008;
    private static final int A = -1;
    private static final int B;
    private int C;
    private static final Vector D;
    
    s(final bb d) {
        this.C = 0;
        super.d = d;
        super.d.b().a(this);
    }
    
    boolean a(final int n) {
        return n == 0;
    }
    
    boolean a(final float[] array) {
        if (!super.d.c(this)) {
            return false;
        }
        switch (this.C) {
            case 52:
            case 1006: {
                final int n = 0;
                array[n] -= this.c();
                break;
            }
            case 56:
            case 1004: {
                final int n2 = 1;
                array[n2] += this.c();
                break;
            }
            case 54:
            case 1007: {
                final int n3 = 0;
                array[n3] += this.c();
                break;
            }
            case 50:
            case 1005: {
                final int n4 = 1;
                array[n4] -= this.c();
                break;
            }
            case 55: {
                final int n5 = 0;
                array[n5] -= this.c();
                final int n6 = 1;
                array[n6] += this.c();
                break;
            }
            case 49: {
                final int n7 = 0;
                array[n7] -= this.c();
                final int n8 = 1;
                array[n8] -= this.c();
                break;
            }
            case 57: {
                final int n9 = 0;
                array[n9] += this.c();
                final int n10 = 1;
                array[n10] += this.c();
                break;
            }
            case 51: {
                final int n11 = 0;
                array[n11] += this.c();
                final int n12 = 1;
                array[n12] -= this.c();
                break;
            }
            case 43:
            case 61:
            case 1002: {
                array[3] = this.a(array[3], true);
                break;
            }
            case 45:
            case 1003: {
                array[3] = this.a(array[3], false);
                break;
            }
            default: {
                return false;
            }
        }
        return true;
    }
    
    void a() {
        super.d.b().b(this);
    }
    
    void a(final a a) {
        switch (a.id) {
            case 401:
            case 403: {
                if (super.d.c(this)) {
                    break;
                }
                if (!s.D.contains(new Integer(a.key))) {
                    break;
                }
                if (!super.d.a(this)) {
                    break;
                }
                this.C = a.key;
                break;
            }
            case 402:
            case 404: {
                if (super.d.c(this)) {
                    if (a.key == this.C) {
                        super.d.b(this);
                        break;
                    }
                    break;
                }
                else {
                    if (a.key == 1000 || a.key == 53 || a.key == -1) {
                        super.d.a(new t(super.d));
                        break;
                    }
                    if (a.key == 46 && (a.modifiers & 0x2) == 0x2) {
                        super.d.a((r)null);
                        break;
                    }
                    if (a.key == 32 && (a.modifiers & 0x2) == 0x2) {
                        super.d.a(new w(super.d));
                        break;
                    }
                    if (a.key == 1008 || a.key == s.B) {
                        super.d.b().j();
                        break;
                    }
                    break;
                }
                break;
            }
            case 1005: {
                super.d.b(this);
                break;
            }
        }
    }
    
    static {
        B = (System.getProperty("java.vendor").startsWith("Apple") ? 0 : 5);
        (D = new Vector()).addElement(new Integer(52));
        s.D.addElement(new Integer(1006));
        s.D.addElement(new Integer(56));
        s.D.addElement(new Integer(1004));
        s.D.addElement(new Integer(54));
        s.D.addElement(new Integer(1007));
        s.D.addElement(new Integer(50));
        s.D.addElement(new Integer(1005));
        s.D.addElement(new Integer(55));
        s.D.addElement(new Integer(49));
        s.D.addElement(new Integer(57));
        s.D.addElement(new Integer(51));
        s.D.addElement(new Integer(43));
        s.D.addElement(new Integer(1002));
        s.D.addElement(new Integer(61));
        s.D.addElement(new Integer(45));
        s.D.addElement(new Integer(1003));
    }
}
