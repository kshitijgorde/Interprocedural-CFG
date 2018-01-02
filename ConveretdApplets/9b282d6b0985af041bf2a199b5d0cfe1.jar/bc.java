import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class bc extends bb implements i
{
    protected static final int e = 49;
    protected static final int f = 50;
    protected static final int g = 51;
    protected static final int h = 52;
    protected static final int i = 53;
    protected static final int j = 54;
    protected static final int k = 55;
    protected static final int l = 56;
    protected static final int m = 57;
    protected static final int n = 43;
    protected static final int o = 45;
    protected static final int p = 61;
    protected static final int q = 32;
    protected static final int r = 46;
    protected static final int s = 1006;
    protected static final int t = 1007;
    protected static final int u = 1004;
    protected static final int v = 1005;
    protected static final int w = 1002;
    protected static final int x = 1003;
    protected static final int y = 1000;
    protected static final int z = 1008;
    protected static final int A = -1;
    static final int B;
    protected int C;
    protected static final Vector D;
    
    void a() {
        super.d.b().b(this);
    }
    
    bc(final bo d) {
        this.C = 0;
        super.d = d;
        super.d.b().a(this);
    }
    
    static {
        B = (System.getProperty("java.vendor").startsWith("Apple") ? 0 : 5);
        (D = new Vector()).addElement(new Integer(52));
        bc.D.addElement(new Integer(1006));
        bc.D.addElement(new Integer(56));
        bc.D.addElement(new Integer(1004));
        bc.D.addElement(new Integer(54));
        bc.D.addElement(new Integer(1007));
        bc.D.addElement(new Integer(50));
        bc.D.addElement(new Integer(1005));
        bc.D.addElement(new Integer(55));
        bc.D.addElement(new Integer(49));
        bc.D.addElement(new Integer(57));
        bc.D.addElement(new Integer(51));
        bc.D.addElement(new Integer(43));
        bc.D.addElement(new Integer(1002));
        bc.D.addElement(new Integer(61));
        bc.D.addElement(new Integer(45));
        bc.D.addElement(new Integer(1003));
    }
    
    public void a(final a a) {
        switch (a.id) {
            case 401:
            case 403: {
                if (!super.d.a(this) && bc.D.contains(new Integer(a.key)) && super.d.b(this)) {
                    this.C = a.key;
                    return;
                }
                break;
            }
            case 402:
            case 404: {
                if (super.d.a(this)) {
                    if (a.key == this.C) {
                        super.d.c(this);
                        return;
                    }
                    break;
                }
                else {
                    if (a.key == 1000 || a.key == 53 || a.key == -1) {
                        super.d.b(new be(super.d));
                        return;
                    }
                    if (a.key == 46 && (a.modifiers & 0x2) == 0x2) {
                        super.d.b((bb)null);
                        return;
                    }
                    if (a.key == 32 && (a.modifiers & 0x2) == 0x2) {
                        super.d.b(new bh(super.d));
                        return;
                    }
                    if (a.key == 1008 || a.key == bc.B) {
                        super.d.b().f();
                        return;
                    }
                    break;
                }
                break;
            }
            case 1005: {
                super.d.c(this);
            }
        }
    }
    
    boolean a(final float[] array) {
        if (!super.d.a(this)) {
            return false;
        }
        switch (this.C) {
            case 52:
            case 1006: {
                final int n = 0;
                array[n] -= this.b();
                break;
            }
            case 56:
            case 1004: {
                final int n2 = 1;
                array[n2] += this.b();
                break;
            }
            case 54:
            case 1007: {
                final int n3 = 0;
                array[n3] += this.b();
                break;
            }
            case 50:
            case 1005: {
                final int n4 = 1;
                array[n4] -= this.b();
                break;
            }
            case 55: {
                final int n5 = 0;
                array[n5] -= this.b();
                final int n6 = 1;
                array[n6] += this.b();
                break;
            }
            case 49: {
                final int n7 = 0;
                array[n7] -= this.b();
                final int n8 = 1;
                array[n8] -= this.b();
                break;
            }
            case 57: {
                final int n9 = 0;
                array[n9] += this.b();
                final int n10 = 1;
                array[n10] += this.b();
                break;
            }
            case 51: {
                final int n11 = 0;
                array[n11] += this.b();
                final int n12 = 1;
                array[n12] -= this.b();
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
    
    boolean a(final int n) {
        return n == 0;
    }
}
