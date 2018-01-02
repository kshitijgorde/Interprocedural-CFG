import java.awt.Rectangle;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class ha
{
    static int anInt936;
    int anInt937;
    d aD938;
    static OutgoingOpcode aClass171_939;
    static boolean aBoolean940;
    static Class110 aClass110_941;
    static int[] anIntArray942;
    static int anInt943;
    
    abstract Class332 method1739(final int p0, final int p1, final boolean p2);
    
    abstract void method1740(final Interface17 p0);
    
    abstract void method1741(final Canvas p0, final int p1, final int p2);
    
    static final synchronized ha method1742(final byte b, final int n, final Canvas canvas, final d d, final int n2, final Class207 class207, final int n3, final int n4) {
        try {
            if (~n3 == -1) {
                return OutputStream_Sub1.method128(d, n4, n2, canvas, 500);
            }
            if (n3 == 2) {
                return Class109.method1732(n2, (byte)81, n4, canvas, d);
            }
            if (~n3 == 0xFFFFFFFE) {
                return Class110.method2087(canvas, 2, n, d);
            }
            if (~n3 == 0xFFFFFFFA) {
                return Class60.method536(n, class207, d, 0, canvas);
            }
            if (b <= 35) {
                ha.aBoolean940 = true;
            }
            if (n3 == 3) {
                return Class214.method2784(54, n, canvas, d, class207);
            }
            throw new IllegalArgumentException("UM");
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ha.VJ(" + b + ',' + n + ',' + ((canvas != null) ? "{...}" : "null") + ',' + ((d != null) ? "{...}" : "null") + ',' + n2 + ',' + ((class207 != null) ? "{...}" : "null") + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    final void method1743(final int n) {
        try {
            Class98_Sub10_Sub8.aBooleanArray5579[this.anInt937] = false;
            this.method1773();
            if (n != -1) {
                this.H(24, 39, 35, null);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ha.CK(" + n + ')');
        }
    }
    
    abstract Interface13 method1744(final int p0, final int p1);
    
    abstract s a(final int p0, final int p1, final int[][] p2, final int[][] p3, final int p4, final int p5, final int p6);
    
    abstract void method1746(final int p0, final int p1, final int p2, final int p3);
    
    abstract boolean method1747();
    
    abstract void U(final int p0, final int p1, final int p2, final int p3, final int p4);
    
    abstract void ra(final int p0, final int p1, final int p2, final int p3);
    
    abstract void f(final int p0, final int p1);
    
    abstract void C(final boolean p0);
    
    final Class332 method1748(final int n, final int n2, final int n3, final int n4, final int[] array, final int n5) {
        try {
            if (n != -7962) {
                return null;
            }
            return this.method1770(array, n2, n3, n5, n4, true);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ha.WJ(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + ((array != null) ? "{...}" : "null") + ',' + n5 + ')');
        }
    }
    
    abstract void method1749(final boolean p0);
    
    abstract void method1750(final Canvas p0);
    
    abstract void DA(final int p0, final int p1, final int p2, final int p3);
    
    abstract void method1751(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int p8, final int p9, final int p10, final int p11, final int p12);
    
    abstract int XA();
    
    abstract Class111 method1752();
    
    abstract void F(final int p0, final int p1);
    
    final void method1753(final int n, final int n2, final int n3, final int n4, final int n5) {
        try {
            if (n == 22294) {
                this.U(n5, n4, n2, n3, 1);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ha.EK(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ')');
        }
    }
    
    final void method1754(final int n) throws Exception_Sub1 {
        try {
            this.method1764(0, 0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ha.FK(" + n + ')');
        }
    }
    
    final void method1755(final int n, final int n2, final int n3, final int n4, final int n5) {
        try {
            this.P(n3, n2, n5, n4, 1);
            if (n != 8479) {
                return;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ha.TJ(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ')');
        }
    }
    
    abstract void method1756();
    
    abstract int r(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6);
    
    final void method1757(final int n, final int n2, final int n3, final int n4, final int n5) {
        try {
            if (n4 <= 19) {
                this.ZA(-128, 0.4222986f, -0.8555362f, 0.64319474f, 0.16042754f, -0.00591929f);
            }
            this.za(n5, n2, n, n3, 1);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ha.RJ(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ')');
        }
    }
    
    abstract void A(final int p0, final aa p1, final int p2, final int p3);
    
    abstract Class332 method1758(final Class324 p0, final boolean p1);
    
    abstract void a(final Class111 p0);
    
    final void method1760(final int n, final int n2, final int n3, final int n4, final byte b, final int n5) {
        try {
            this.aa(n5, n3, n, n2, n4, 1);
            if (b != -66) {
                ha.aClass171_939 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ha.HK(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + b + ',' + n5 + ')');
        }
    }
    
    abstract void method1761(final boolean p0);
    
    abstract void ya();
    
    abstract za method1762(final int p0);
    
    final void method1763(final int n, final Class332 class332) {
        try {
            if (n > -56) {
                this.K(null);
            }
            this.method1740(this.method1815(class332, this.method1744(class332.method3734(), class332.method3731())));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ha.UJ(" + n + ',' + ((class332 != null) ? "{...}" : "null") + ')');
        }
    }
    
    abstract void method1764(final int p0, final int p1) throws Exception_Sub1;
    
    abstract Class98_Sub5 method1765(final int p0, final int p1, final int p2, final int p3, final int p4, final float p5);
    
    @Override
    protected void finalize() {
        try {
            this.method1743(-1);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ha.finalize()");
        }
    }
    
    abstract boolean method1766();
    
    abstract boolean method1767();
    
    abstract boolean method1768();
    
    abstract Class48 method1769(final Class48 p0, final Class48 p1, final float p2, final Class48 p3);
    
    abstract Class332 method1770(final int[] p0, final int p1, final int p2, final int p3, final int p4, final boolean p5);
    
    abstract void ZA(final int p0, final float p1, final float p2, final float p3, final float p4, final float p5);
    
    abstract boolean method1771();
    
    abstract aa method1772(final int p0, final int p1, final int[] p2, final int[] p3);
    
    abstract void method1773();
    
    abstract void method1774(final int p0);
    
    abstract void T(final int p0, final int p1, final int p2, final int p3);
    
    abstract void P(final int p0, final int p1, final int p2, final int p3, final int p4);
    
    abstract void method1775(final Class48 p0);
    
    abstract void method1776();
    
    abstract int method1777(final int p0, final int p1);
    
    abstract void method1778(final int p0);
    
    abstract void method1779(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5);
    
    abstract boolean method1780();
    
    final void method1781(final boolean b, final int n, final int n2, final int n3, final int n4, final int n5) {
        try {
            this.method1779(n4, n5, n2, n, n3, 1);
            if (!b) {
                return;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ha.SJ(" + b + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ')');
        }
    }
    
    abstract void method1782(final Canvas p0, final int p1, final int p2);
    
    abstract void method1783(final int p0);
    
    abstract int c(final int p0, final int p1);
    
    abstract void method1785(final Class242 p0, final int p1);
    
    abstract void K(final int[] p0);
    
    ha(final d ad938) {
        try {
            this.aD938 = ad938;
            int anInt937 = -1;
            for (int n = 0; ~n > -9; ++n) {
                if (!Class98_Sub10_Sub8.aBooleanArray5579[n]) {
                    Class98_Sub10_Sub8.aBooleanArray5579[n] = true;
                    anInt937 = n;
                    break;
                }
            }
            if (~anInt937 == 0x0) {
                throw new IllegalStateException("NFTI");
            }
            this.anInt937 = anInt937;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ha.<init>(" + ((ad938 != null) ? "{...}" : "null") + ')');
        }
    }
    
    abstract void method1786(final Canvas p0);
    
    abstract int i();
    
    public static void method1787(final boolean b) {
        try {
            ha.aClass110_941 = null;
            ha.anIntArray942 = null;
            if (b) {
                ha.aClass171_939 = null;
            }
            ha.aClass171_939 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ha.DK(" + b + ')');
        }
    }
    
    abstract void GA(final int p0);
    
    abstract int JA(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5);
    
    abstract boolean method1788();
    
    final void method1789(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        try {
            if (n5 != -10550) {
                this.method1825();
            }
            this.method1795(n6, n, n4, n3, n2, 1);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ha.GK(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    abstract void la();
    
    abstract Class146 method1790(final Class178 p0, final int p1, final int p2, final int p3, final int p4);
    
    abstract void method1791(final float p0, final float p1, final float p2);
    
    abstract void a(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final aa p6, final int p7, final int p8, final int p9, final int p10, final int p11);
    
    abstract Class111 method1793();
    
    final void method1794(final Rectangle[] array, final int n, final int n2) throws Exception_Sub1 {
        try {
            if (n2 != 27179) {
                ha.aClass110_941 = null;
            }
            this.a(array, n, 0, 0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ha.AK(" + ((array != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    abstract void method1795(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5);
    
    static final Class337 method1796(final int n, final Class98_Sub22 class98_Sub22) {
        try {
            final int short1 = class98_Sub22.readShort((byte)127);
            final Class63 class63 = Class98_Sub46_Sub13_Sub1.method1595(112)[class98_Sub22.readUnsignedByte((byte)123)];
            if (n != 9342) {
                ha.anInt936 = -127;
            }
            return new Class337(short1, class63, Class331.method3723(256)[class98_Sub22.readUnsignedByte((byte)(-127))], class98_Sub22.readUShort(false), class98_Sub22.readUShort(false));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ha.BK(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
    
    abstract Class332 method1797(final int p0, final int p1, final int p2, final int p3, final boolean p4);
    
    abstract int E();
    
    abstract void method1798(final int p0);
    
    abstract void pa();
    
    abstract Class62 method1799();
    
    abstract boolean method1800();
    
    abstract void method1801(final int[] p0);
    
    abstract void za(final int p0, final int p1, final int p2, final int p3, final int p4);
    
    abstract void da(final int p0, final int p1, final int p2, final int[] p3);
    
    abstract boolean method1802();
    
    abstract Class48 method1803(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5);
    
    abstract Class43 method1804(final Class197 p0, final Class324[] p1, final boolean p2);
    
    abstract void KA(final int p0, final int p1, final int p2, final int p3);
    
    abstract void EA(final int p0, final int p1, final int p2, final int p3);
    
    abstract void b(final int p0, final int p1, final int p2, final int p3, final double p4);
    
    abstract void a(final Rectangle[] p0, final int p1, final int p2, final int p3) throws Exception_Sub1;
    
    abstract void method1806(final int p0);
    
    abstract void method1807(final int p0);
    
    abstract void a(final za p0);
    
    abstract void a(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final aa p6, final int p7, final int p8);
    
    abstract void xa(final float p0);
    
    abstract boolean method1810();
    
    abstract void HA(final int p0, final int p1, final int p2, final int p3, final int[] p4);
    
    abstract void method1811(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int p8);
    
    abstract void X(final int p0);
    
    abstract void method1812();
    
    abstract Interface5 method1813(final int p0, final int p1);
    
    abstract void method1814();
    
    abstract void L(final int p0, final int p1, final int p2);
    
    abstract Interface17 method1815(final Interface5 p0, final Interface13 p1);
    
    abstract void Q(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final byte[] p6, final int p7, final int p8);
    
    abstract void method1816(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6);
    
    abstract int M();
    
    abstract void aa(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5);
    
    abstract void method1817();
    
    abstract void method1818(final int p0, final Class98_Sub5[] p1);
    
    abstract boolean method1819();
    
    abstract void H(final int p0, final int p1, final int p2, final int[] p3);
    
    abstract void method1820(final Class242 p0);
    
    abstract int[] Y();
    
    abstract Class111 method1821();
    
    abstract int method1822();
    
    abstract boolean method1823();
    
    abstract boolean method1824();
    
    abstract void method1825();
    
    abstract int I();
    
    abstract int[] na(final int p0, final int p1, final int p2, final int p3);
    
    static {
        ha.aClass171_939 = new OutgoingOpcode(50, -1);
        ha.aBoolean940 = false;
        ha.aClass110_941 = new Class110();
        ha.anIntArray942 = new int[2];
        ha.anInt943 = 0;
    }
}
