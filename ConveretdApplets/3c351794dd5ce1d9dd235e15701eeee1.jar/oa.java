import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

final class oa extends ha implements Interface9
{
    long nativeid;
    private ya aYa3296;
    private static int[] anIntArray3297;
    private boolean aBoolean3298;
    private Class148 aClass148_3299;
    private static byte[] aByteArray3300;
    private static int[] anIntArray3301;
    private static float[] aFloatArray3302;
    private static int[] anIntArray3303;
    private static int[] anIntArray3304;
    private static short[] aShortArray3305;
    static int[] anIntArray3306;
    private p aP3307;
    private static float[] aFloatArray3308;
    private static int[] anIntArray3309;
    private Class111 aClass111_3310;
    private int anInt3311;
    private int anInt3312;
    static int[] anIntArray3313;
    private Class111 aClass111_3314;
    private Class377 aClass377_3315;
    int anInt3316;
    private a[] anAArray3317;
    private boolean aBoolean3318;
    
    @Override
    final native void P(final int p0, final int p1, final int p2, final int p3, final int p4);
    
    @Override
    final native void pa();
    
    @Override
    final void method1746(final int n, final int n2, final int n3, final int n4) {
    }
    
    @Override
    final native void K(final int[] p0);
    
    @Override
    final boolean method1771() {
        return true;
    }
    
    @Override
    final void method1825() {
    }
    
    @Override
    final Class98_Sub5 method1765(final int n, final int n2, final int n3, final int n4, final int n5, final float n6) {
        return new Class98_Sub5_Sub3(n, n2, n3, n4, n5, n6);
    }
    
    @Override
    final void method1818(final int n, final Class98_Sub5[] array) {
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            oa.anIntArray3303[n2++] = array[i].method954(7019);
            oa.anIntArray3303[n2++] = array[i].method963((byte)115);
            oa.anIntArray3303[n2++] = array[i].method962(28699);
            oa.anIntArray3303[n2++] = array[i].method958(126);
            oa.aFloatArray3308[i] = array[i].method956(false);
            oa.anIntArray3303[n2++] = array[i].method961((byte)(-78));
        }
        this.N(n, oa.anIntArray3303, oa.aFloatArray3308);
    }
    
    @Override
    final void method1761(final boolean b) {
    }
    
    @Override
    final native void GA(final int p0);
    
    @Override
    final aa method1772(final int n, final int n2, final int[] array, final int[] array2) {
        return new na(this, this.aYa3296, n, n2, array, array2);
    }
    
    @Override
    final boolean method1766() {
        return true;
    }
    
    public oa(final Canvas canvas, final d d, final int n, final int n2) {
        super(d);
        this.nativeid = 0L;
        this.aBoolean3298 = false;
        this.aClass148_3299 = new Class148();
        this.anInt3311 = 4096;
        this.anInt3312 = 4096;
        this.aClass377_3315 = new Class377(4);
        this.aBoolean3318 = false;
        try {
            if (!Class134_Sub1.method2246("sw3d", (byte)(-36))) {
                throw new RuntimeException("");
            }
            Class192.method2655(-374);
            this.MA(super.aD938, 0, 0);
            Class101.method1702(8, false, true);
            this.aBoolean3318 = true;
            this.aClass111_3314 = new ja();
            this.a(new ja());
            this.method1783(1);
            this.method1807(0);
            if (canvas != null) {
                this.method1741(canvas, n, n2);
                this.method1750(canvas);
            }
        }
        catch (Throwable t) {
            this.method1743(-1);
            throw new RuntimeException();
        }
    }
    
    @Override
    final Interface13 method1744(final int n, final int n2) {
        return new xa(n, n2);
    }
    
    private final void method1927(final Class242 class242, final boolean b) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        for (Class246_Sub4_Sub2 class246_Sub4_Sub2 = (Class246_Sub4_Sub2)class242.aClass358_1850.method3890((byte)71); class246_Sub4_Sub2 != null; class246_Sub4_Sub2 = (Class246_Sub4_Sub2)class242.aClass358_1850.method3884(101)) {
            oa.anIntArray3301[n++] = class246_Sub4_Sub2.anInt6176;
            oa.anIntArray3301[n++] = class246_Sub4_Sub2.anInt6177;
            oa.anIntArray3301[n++] = class246_Sub4_Sub2.anInt6175;
            oa.anIntArray3304[n2++] = class246_Sub4_Sub2.anInt6178;
            oa.aShortArray3305[n4++] = (short)class246_Sub4_Sub2.anInt6180;
            oa.anIntArray3309[n3++] = class246_Sub4_Sub2.anInt6179;
            if (b) {
                oa.aByteArray3300[n5++] = class246_Sub4_Sub2.aByte6183;
            }
        }
    }
    
    @Override
    final void a(final Rectangle[] array, final int n, final int n2, final int n3) throws Exception_Sub1 {
        if (this.aP3307 == null) {
            throw new IllegalStateException("off");
        }
        this.aP3307.method1445(array, n, n2, n3);
    }
    
    @Override
    final Class62 method1799() {
        return new Class62(0, "SSE", 1, "CPU", 0L);
    }
    
    private final native void AA(final short p0, final short p1, final int p2, final byte p3, final byte p4, final int p5, final boolean p6, final byte p7, final byte p8, final byte p9, final byte p10, final boolean p11, final boolean p12, final boolean p13, final boolean p14, final boolean p15, final byte p16, final boolean p17, final boolean p18, final int p19);
    
    private final native void MA(final d p0, final int p1, final int p2);
    
    @Override
    final native void KA(final int p0, final int p1, final int p2, final int p3);
    
    private final native void CA(final short p0, final int[] p1, final short p2, final int p3, final byte p4, final byte p5, final int p6, final boolean p7, final byte p8, final byte p9, final byte p10, final byte p11, final boolean p12, final boolean p13, final boolean p14, final boolean p15, final boolean p16, final byte p17, final boolean p18, final boolean p19, final int p20);
    
    @Override
    final int method1777(final int n, final int n2) {
        return n | n2;
    }
    
    @Override
    final Class111 method1793() {
        return this.aClass111_3314;
    }
    
    @Override
    final Class332 method1758(final Class324 class324, final boolean b) {
        final j j = new j(this, class324.anIntArray2718, class324.aByteArray2717, class324.aByteArray2723, 0, class324.anInt2722, class324.anInt2722, class324.anInt2720);
        j.method3740(class324.anInt2725, class324.anInt2721, class324.anInt2719, class324.anInt2724);
        return j;
    }
    
    private final native void wa(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5);
    
    @Override
    final native void ya();
    
    @Override
    final void method1795(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.wa(n, n2, n3, n4, n5, n6);
    }
    
    @Override
    final Class332 method1797(final int n, final int n2, final int n3, final int n4, final boolean b) {
        return new j(this, n, n2, n3, n4, !b);
    }
    
    @Override
    final native int XA();
    
    @Override
    final native void H(final int p0, final int p1, final int p2, final int[] p3);
    
    @Override
    final native void la();
    
    @Override
    final boolean method1780() {
        return true;
    }
    
    @Override
    final void a(final Class111 aClass111_3310) {
        this.aClass111_3310 = aClass111_3310;
        this.ma(((ja)aClass111_3310).nativeid);
    }
    
    @Override
    final void method1820(final Class242 class242) {
        if (class242.aClass358_1850.method3885(true) != 0) {
            this.method1927(class242, false);
            this.method1930().method142(this, oa.anIntArray3301, oa.anIntArray3304, oa.anIntArray3309, oa.aShortArray3305, class242.aClass358_1850.method3885(true));
        }
    }
    
    private final native void FA();
    
    @Override
    final Class48 method1769(final Class48 class48, final Class48 class49, final float n, final Class48 class50) {
        return null;
    }
    
    @Override
    final boolean method1819() {
        return true;
    }
    
    @Override
    final native int JA(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5);
    
    @Override
    final native void U(final int p0, final int p1, final int p2, final int p3, final int p4);
    
    @Override
    final native void f(final int p0, final int p1);
    
    @Override
    final za method1762(final int n) {
        final ya ya = new ya(this, n);
        this.aClass148_3299.method2419(ya, -20911);
        return ya;
    }
    
    @Override
    final native int[] Y();
    
    @Override
    final void method1778(final int n) {
        throw new IllegalStateException();
    }
    
    @Override
    final void method1812() {
    }
    
    @Override
    final native void ZA(final int p0, final float p1, final float p2, final float p3, final float p4, final float p5);
    
    @Override
    final native void Q(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final byte[] p6, final int p7, final int p8);
    
    @Override
    final void method1773() {
        if (!this.aBoolean3298) {
            this.anAArray3317 = null;
            this.aP3307 = null;
            this.aYa3296 = null;
            this.aClass111_3314 = null;
            this.aClass377_3315.method3994(-116);
            for (ya ya = (ya)this.aClass148_3299.method2418(32); ya != null; ya = (ya)this.aClass148_3299.method2417(88)) {
                ya.ga();
            }
            this.aClass148_3299.method2422((byte)47);
            this.FA();
            if (this.aBoolean3318) {
                Class18.method248(true, 43, false);
                this.aBoolean3318 = false;
            }
            this.g();
            Class192.method2653(-55);
            this.aBoolean3298 = true;
        }
    }
    
    @Override
    final Class43 method1804(final Class197 class197, final Class324[] array, final boolean b) {
        final int[] array2 = new int[array.length];
        final int[] array3 = new int[array.length];
        boolean b2 = false;
        for (int i = 0; i < array.length; ++i) {
            array2[i] = array[i].anInt2722;
            array3[i] = array[i].anInt2720;
            if (array[i].aByteArray2723 != null) {
                b2 = true;
            }
        }
        if (b) {
            if (b2) {
                throw new IllegalArgumentException("Cannot specify alpha with non-mono font unless someone writes it");
            }
            return new h(this, this.aYa3296, class197, array, null);
        }
        else {
            if (b2) {
                throw new IllegalArgumentException("Cannot specify alpha with non-mono font unless someone writes it");
            }
            return new n(this, this.aYa3296, class197, array, null);
        }
    }
    
    @Override
    final void method1750(final Canvas canvas) {
        if (canvas != null) {
            this.t(this.aP3307 = (p)this.aClass377_3315.method3990(canvas.hashCode(), -1));
        }
        else {
            this.t(this.aP3307 = null);
        }
    }
    
    @Override
    final native void ra(final int p0, final int p1, final int p2, final int p3);
    
    private final boolean method1928(final short n) {
        synchronized (super.aD938) {
            if (!super.aD938.method8(-126, n)) {
                return false;
            }
            final Class238 method11 = super.aD938.method11(n, -28755);
            if (method11 == null) {
                return false;
            }
            int[] array;
            if (method11.anInt1818 != 2) {
                array = super.aD938.method9(n, (byte)(-126), 128, 0.7f, true, 128);
            }
            else {
                array = super.aD938.method13(121, 128, n, 0.7f, true, 128);
            }
            this.CA(n, array, method11.aShort1831, method11.anInt1818, method11.aByte1820, method11.aByte1816, method11.anInt1835, method11.aBoolean1822, method11.aByte1830, method11.aByte1829, method11.aByte1823, method11.aByte1837, method11.aBoolean1825, method11.aBoolean1833, method11.aBoolean1827, method11.aBoolean1826, method11.aBoolean1819, method11.aByte1832, method11.aBoolean1817, method11.aBoolean1824, method11.anInt1821);
        }
        return true;
    }
    
    @Override
    final native int i();
    
    @Override
    final native void HA(final int p0, final int p1, final int p2, final int p3, final int[] p4);
    
    @Override
    final boolean method1767() {
        return false;
    }
    
    @Override
    final void method1774(final int n) {
        this.anAArray3317[n].method139();
    }
    
    @Override
    final boolean method1810() {
        return true;
    }
    
    @Override
    final void method1776() {
        this.method1750(this.aP3307.aCanvas3320);
    }
    
    @Override
    final int method1822() {
        return 4;
    }
    
    @Override
    final native void F(final int p0, final int p1);
    
    @Override
    final void method1791(final float n, final float n2, final float n3) {
    }
    
    @Override
    final Interface5 method1813(final int n, final int n2) {
        return this.method1739(n, n2, false);
    }
    
    private final boolean method1929(final short n) {
        synchronized (this) {
            final Class238 method11 = super.aD938.method11(n, -28755);
            if (method11 == null) {
                return false;
            }
            this.AA(n, method11.aShort1831, method11.anInt1818, method11.aByte1820, method11.aByte1816, method11.anInt1835, method11.aBoolean1822, method11.aByte1830, method11.aByte1829, method11.aByte1823, method11.aByte1837, method11.aBoolean1825, method11.aBoolean1833, method11.aBoolean1827, method11.aBoolean1826, method11.aBoolean1819, method11.aByte1832, method11.aBoolean1817, method11.aBoolean1824, method11.anInt1821);
        }
        return true;
    }
    
    @Override
    final void method1798(final int n) {
        this.anInt3311 = n;
        this.anInt3312 = n;
        if (this.anInt3316 > 1) {
            throw new IllegalStateException("No MT");
        }
        this.method1783(this.anInt3316);
        this.method1807(0);
    }
    
    private final native void d(final int p0);
    
    @Override
    final s a(final int n, final int n2, final int[][] array, final int[][] array2, final int n3, final int n4, final int n5) {
        return new t(this, this.aYa3296, n, n2, array, array2, n3, n4, n5);
    }
    
    private final native void t(final p p0);
    
    @Override
    final native void za(final int p0, final int p1, final int p2, final int p3, final int p4);
    
    @Override
    final native void da(final int p0, final int p1, final int p2, final int[] p3);
    
    @Override
    final native void EA(final int p0, final int p1, final int p2, final int p3);
    
    @Override
    final void method1785(final Class242 class242, final int n) {
        this.method1927(class242, false);
        this.method1930().method142(this, oa.anIntArray3301, oa.anIntArray3304, oa.anIntArray3309, oa.aShortArray3305, class242.aClass358_1850.method3885(true));
    }
    
    @Override
    final boolean method1824() {
        return true;
    }
    
    private final native void Z(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final aa p6, final int p7, final int p8);
    
    @Override
    final void method1751(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10, final int n11, final int n12, final int n13) {
    }
    
    @Override
    final native void X(final int p0);
    
    @Override
    final native void L(final int p0, final int p1, final int p2);
    
    private final native void va(final za p0);
    
    @Override
    final Class111 method1821() {
        return new ja();
    }
    
    @Override
    protected final synchronized void finalize() {
        this.method1743(-1);
        if (this.nativeid != 0L) {
            Class192.method2654(false, this);
        }
    }
    
    @Override
    final Class332 method1739(final int n, final int n2, final boolean b) {
        return new j(this, n, n2);
    }
    
    @Override
    final native int E();
    
    @Override
    final native int I();
    
    @Override
    final native int r(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6);
    
    @Override
    final void a(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final aa aa, final int n7, final int n8, final int n9, final int n10, final int n11) {
    }
    
    @Override
    final boolean method1823() {
        return false;
    }
    
    @Override
    final void method1801(final int[] array) {
        final Dimension size = this.aP3307.aCanvas3320.getSize();
        array[0] = size.width;
        array[1] = size.height;
    }
    
    @Override
    final native void A(final int p0, final aa p1, final int p2, final int p3);
    
    @Override
    final void method1779(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.U(n, n2, n3, n5, n6);
        this.U(n, n2 + n4 - 1, n3, n5, n6);
        this.P(n, n2 + 1, n4 - 1, n5, n6);
        this.P(n + n3 - 1, n2 + 1, n4 - 1, n5, n6);
    }
    
    @Override
    final void a(final za za) {
        this.aYa3296 = (ya)za;
        this.va(za);
    }
    
    private final native void n(final long p0, final long p1);
    
    @Override
    final void method1783(final int anInt3316) {
        this.anInt3316 = anInt3316;
        this.anAArray3317 = new a[this.anInt3316];
        for (int i = 0; i < this.anInt3316; ++i) {
            this.anAArray3317[i] = new a(this, this.anInt3312, this.anInt3311);
        }
    }
    
    @Override
    final void method1764(final int n, final int n2) throws Exception_Sub1 {
        if (this.aP3307 == null) {
            throw new IllegalStateException("off");
        }
        this.aP3307.method1447(n, n2);
    }
    
    @Override
    final void method1786(final Canvas canvas) {
        if (this.aP3307.aCanvas3320 == canvas) {
            this.method1750(null);
        }
        final p p = (p)this.aClass377_3315.method3990(canvas.hashCode(), -1);
        if (p != null) {
            p.method942(115);
            p.method1449();
        }
    }
    
    @Override
    final native int[] na(final int p0, final int p1, final int p2, final int p3);
    
    @Override
    final void method1749(final boolean b) {
    }
    
    @Override
    final boolean method1768() {
        return false;
    }
    
    private final native void ma(final long p0);
    
    @Override
    final void method1756() {
    }
    
    @Override
    final native void C(final boolean p0);
    
    @Override
    final boolean method1788() {
        return true;
    }
    
    @Override
    final native void xa(final float p0);
    
    @Override
    final Class48 method1803(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        return null;
    }
    
    final a method1930() {
        for (int i = 0; i < this.anInt3316; ++i) {
            if (this.anAArray3317[i].aRunnable3281 == Thread.currentThread()) {
                return this.anAArray3317[i];
            }
        }
        return null;
    }
    
    @Override
    final boolean method1747() {
        return true;
    }
    
    @Override
    final native void T(final int p0, final int p1, final int p2, final int p3);
    
    @Override
    final void method1807(final int n) {
        this.anAArray3317[n].method141();
    }
    
    @Override
    public final native void w(final boolean p0);
    
    @Override
    final Class111 method1752() {
        return this.aClass111_3310;
    }
    
    @Override
    final Class146 method1790(final Class178 class178, final int n, final int n2, final int n3, final int n4) {
        return new i(this, this.aYa3296, class178, n, n2, n3, n4);
    }
    
    @Override
    final Interface17 method1815(final Interface5 interface5, final Interface13 interface6) {
        return new wa(this, (j)interface5, (xa)interface6);
    }
    
    private final Object method1931() {
        return new ba(this);
    }
    
    @Override
    final void method1811(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9) {
    }
    
    @Override
    final native void aa(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5);
    
    @Override
    final void method1775(final Class48 class48) {
    }
    
    @Override
    final boolean method1800() {
        return true;
    }
    
    @Override
    final void method1740(final Interface17 interface17) {
        final wa wa = (wa)interface17;
        this.n(wa.aJ3435.nativeid, wa.aXa3436.nativeid);
    }
    
    private final native void N(final int p0, final int[] p1, final float[] p2);
    
    @Override
    final native void DA(final int p0, final int p1, final int p2, final int p3);
    
    @Override
    final void method1741(final Canvas canvas, final int n, final int n2) {
        final p p3 = (p)this.aClass377_3315.method3990(canvas.hashCode(), -1);
        if (p3 == null) {
            try {
                Class.forName("java.awt.Canvas").getMethod("setIgnoreRepaint", Boolean.TYPE).invoke(canvas, Boolean.TRUE);
            }
            catch (Exception ex) {}
            this.aClass377_3315.method3996(new p(this, canvas, n, n2), canvas.hashCode(), -1);
        }
        else if (p3.anInt3319 != n || p3.anInt3322 != n2) {
            p3.method1448(canvas, n, n2);
        }
    }
    
    @Override
    final void method1817() {
    }
    
    @Override
    final void method1782(final Canvas canvas, final int n, final int n2) {
        ((p)this.aClass377_3315.method3990(canvas.hashCode(), -1)).method1448(canvas, n, n2);
        if (canvas != null && canvas == this.aP3307.aCanvas3320) {
            this.method1750(canvas);
        }
    }
    
    @Override
    final boolean method1802() {
        return false;
    }
    
    @Override
    final void a(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final aa aa, final int n7, final int n8) {
        this.Z(n, n2, n3, n4, n5, n6, aa, n7, n8);
    }
    
    @Override
    final int c(final int n, final int n2) {
        final int n3 = n & 0xFFFFF;
        final int n4 = n2 & 0xFFFFF;
        return (n3 & n4) ^ n4;
    }
    
    @Override
    final void method1816(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        this.method1930().method138(this, n, n2, n3, n4, n5, n6, n7);
    }
    
    @Override
    final Class332 method1770(final int[] array, final int n, final int n2, final int n3, final int n4, final boolean b) {
        return new j(this, array, n, n2, n3, n4, false);
    }
    
    @Override
    final native void b(final int p0, final int p1, final int p2, final int p3, final double p4);
    
    @Override
    final native int M();
    
    @Override
    final void method1806(final int n) {
        Class192.method2656(0);
        this.d(n);
        for (ya ya = (ya)this.aClass148_3299.method2418(32); ya != null; ya = (ya)this.aClass148_3299.method2417(97)) {
            ya.r();
        }
    }
    
    @Override
    final void method1814() {
    }
    
    private final void g() {
        System.gc();
        System.runFinalization();
        Class192.method2656(0);
    }
    
    static {
        oa.anIntArray3297 = new int[Math.max(Math.max(104, 20), 24573)];
        oa.anIntArray3301 = oa.anIntArray3297;
        oa.aByteArray3300 = new byte[8191];
        oa.anIntArray3309 = new int[8191];
        oa.anIntArray3306 = oa.anIntArray3297;
        oa.aFloatArray3302 = new float[20];
        oa.aShortArray3305 = new short[8191];
        oa.anIntArray3313 = new int[6];
        oa.aFloatArray3308 = oa.aFloatArray3302;
        oa.anIntArray3304 = new int[8191];
        oa.anIntArray3303 = oa.anIntArray3297;
    }
}
