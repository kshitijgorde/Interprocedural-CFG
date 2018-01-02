// 
// Decompiled by Procyon v0.5.30
// 

final class i extends Class146 implements Interface9
{
    private ya aYa3292;
    private oa anOa3293;
    Class87[] aClass87Array3294;
    long nativeid;
    Class35[] aClass35Array3295;
    
    private final native void l(final long p0, final int p1, final int[] p2, final int p3, final int p4, final int p5, final int p6, final boolean p7);
    
    @Override
    final native void ia(final short p0, final short p1);
    
    @Override
    final boolean method2333(final int n, final int n2, final Class111 class111, final boolean b, final int n3, final int n4) {
        return this.anOa3293.method1930().method147(this, n, n2, class111, b, n4);
    }
    
    @Override
    final native void a(final int p0);
    
    private final void method2376(final int[] array, final Class111 class111) {
        this.anOa3293.method1930().method143(this, array, class111);
    }
    
    final native void ZA(final i p0, final i p1, final int p2, final boolean p3, final boolean p4);
    
    private final native void oa(final oa p0);
    
    @Override
    final Class35[] method2322() {
        return this.aClass35Array3295;
    }
    
    @Override
    final native void s(final int p0);
    
    @Override
    final native int RA();
    
    @Override
    final native boolean r();
    
    @Override
    final native int V();
    
    @Override
    final boolean method2339(final int n, final int n2, final Class111 class111, final boolean b, final int n3) {
        return this.anOa3293.method1930().method150(this, n, n2, class111, b);
    }
    
    @Override
    final native void LA(final int p0);
    
    @Override
    final void method2331(final Class111 class111, final int n, final boolean b) {
        this.A(((ja)class111).nativeid, n, b);
    }
    
    @Override
    final native int G();
    
    private final native void A(final long p0, final int p1, final boolean p2);
    
    @Override
    final native void aa(final short p0, final short p1);
    
    @Override
    final native int ma();
    
    @Override
    final native int da();
    
    @Override
    final native void p(final int p0, final int p1, final s p2, final s p3, final int p4, final int p5, final int p6);
    
    @Override
    final native r ba(final r p0);
    
    @Override
    final native void VA(final int p0);
    
    @Override
    final void method2343(final Class111 class111) {
        this.method2376(oa.anIntArray3306, class111);
        int n = 0;
        if (this.aClass87Array3294 != null) {
            for (int i = 0; i < this.aClass87Array3294.length; ++i) {
                final Class87 class112 = this.aClass87Array3294[i];
                class112.anInt670 = oa.anIntArray3306[n++];
                class112.anInt668 = oa.anIntArray3306[n++];
                class112.anInt671 = oa.anIntArray3306[n++];
                class112.anInt663 = oa.anIntArray3306[n++];
                class112.anInt664 = oa.anIntArray3306[n++];
                class112.anInt656 = oa.anIntArray3306[n++];
                class112.anInt659 = oa.anIntArray3306[n++];
                class112.anInt669 = oa.anIntArray3306[n++];
                class112.anInt662 = oa.anIntArray3306[n++];
            }
        }
        if (this.aClass35Array3295 != null) {
            for (int j = 0; j < this.aClass35Array3295.length; ++j) {
                Class35 aClass35_328;
                final Class35 class113 = aClass35_328 = this.aClass35Array3295[j];
                if (class113.aClass35_328 != null) {
                    aClass35_328 = class113.aClass35_328;
                }
                if (class113.aClass111_334 != null) {
                    class113.aClass111_334.method2092(class111);
                }
                else {
                    class113.aClass111_334 = class111.method2102();
                }
                aClass35_328.anInt331 = oa.anIntArray3306[n++];
                aClass35_328.anInt330 = oa.anIntArray3306[n++];
                aClass35_328.anInt337 = oa.anIntArray3306[n++];
            }
        }
    }
    
    @Override
    public final native void w(final boolean p0);
    
    @Override
    final Class87[] method2320() {
        return this.aClass87Array3294;
    }
    
    @Override
    final native boolean F();
    
    @Override
    final native boolean NA();
    
    @Override
    final native void wa();
    
    @Override
    final native void I(final int p0, final int[] p1, final int p2, final int p3, final int p4, final boolean p5, final int p6, final int[] p7);
    
    @Override
    final void method2342() {
        if (this.anOa3293.anInt3316 > 1) {
            synchronized (this) {
                while (super.aBoolean1181) {
                    try {
                        this.wait();
                    }
                    catch (InterruptedException ex) {}
                }
                super.aBoolean1181 = true;
            }
        }
    }
    
    @Override
    final native int EA();
    
    @Override
    final native int ua();
    
    @Override
    final boolean method2324() {
        return true;
    }
    
    @Override
    protected final void finalize() {
        if (this.nativeid != 0L) {
            Class192.method2654(false, this);
        }
    }
    
    @Override
    final native void O(final int p0, final int p1, final int p2);
    
    @Override
    final native void v();
    
    @Override
    final native void FA(final int p0);
    
    @Override
    final void method2325(final Class111 class111, final Class246_Sub6 class246_Sub6, final int n) {
        if (class246_Sub6 == null) {
            this.anOa3293.method1930().method149(this, class111, null, n);
        }
        else {
            oa.anIntArray3313[5] = 0;
            this.anOa3293.method1930().method149(this, class111, oa.anIntArray3313, n);
            class246_Sub6.anInt5111 = oa.anIntArray3313[0];
            class246_Sub6.anInt5113 = oa.anIntArray3313[1];
            class246_Sub6.anInt5110 = oa.anIntArray3313[2];
            class246_Sub6.anInt5112 = oa.anIntArray3313[3];
            class246_Sub6.anInt5109 = oa.anIntArray3313[4];
            class246_Sub6.aBoolean5114 = (oa.anIntArray3313[5] != 0);
        }
    }
    
    @Override
    final native void C(final int p0);
    
    @Override
    final void method2332(final Class146 class146, final int n, final int n2, final int n3, final boolean b) {
        this.anOa3293.method1930().method140(this, class146, n, n2, n3, b);
    }
    
    @Override
    final void method2337(final int n, final int n2, final int n3, final int n4) {
    }
    
    @Override
    final native int fa();
    
    @Override
    final native void P(final int p0, final int p1, final int p2, final int p3);
    
    @Override
    final native void k(final int p0);
    
    @Override
    final native void H(final int p0, final int p1, final int p2);
    
    @Override
    final native int WA();
    
    @Override
    final void method2344(final int n, final int[] array, final int n2, final int n3, final int n4, final int n5, final boolean b) {
        this.l(this.nativeid, n, array, n2, n3, n4, n5, b);
    }
    
    @Override
    final void method2326() {
    }
    
    @Override
    final Class146 method2341(final byte b, final int n, final boolean b2) {
        return this.anOa3293.method1930().method137(this, b, n, b2);
    }
    
    @Override
    final void method2327() {
        if (this.anOa3293.anInt3316 > 1) {
            synchronized (this) {
                super.aBoolean1181 = false;
                this.notifyAll();
            }
        }
    }
    
    @Override
    final void method2329(final Class111 class111, final Class246_Sub6 class246_Sub6, final int n, final int n2) {
        if (class246_Sub6 == null) {
            this.anOa3293.method1930().method146(this, class111, null, n, n2);
        }
        else {
            oa.anIntArray3313[5] = 0;
            this.anOa3293.method1930().method146(this, class111, oa.anIntArray3313, n, n2);
            class246_Sub6.anInt5111 = oa.anIntArray3313[0];
            class246_Sub6.anInt5113 = oa.anIntArray3313[1];
            class246_Sub6.anInt5110 = oa.anIntArray3313[2];
            class246_Sub6.anInt5112 = oa.anIntArray3313[3];
            class246_Sub6.anInt5109 = oa.anIntArray3313[4];
            class246_Sub6.aBoolean5114 = (oa.anIntArray3313[5] != 0);
        }
    }
    
    private final native void R(final oa p0, final ya p1, final int p2, final int p3, final int[] p4, final int[] p5, final int[] p6, final int[] p7, final short[] p8, final int p9, final short[] p10, final short[] p11, final short[] p12, final byte[] p13, final byte[] p14, final byte[] p15, final byte[] p16, final short[] p17, final short[] p18, final int[] p19, final byte p20, final short[] p21, final int p22, final byte[] p23, final short[] p24, final short[] p25, final short[] p26, final int[] p27, final int[] p28, final int[] p29, final byte[] p30, final byte[] p31, final int[] p32, final int[] p33, final int[] p34, final int[] p35, final int p36, final int p37, final int p38, final int p39, final int p40, final int p41, final int[] p42);
    
    @Override
    final native int na();
    
    i(final oa anOa3293, final ya aYa3292, final Class178 class178, final int n, final int n2, final int n3, final int n4) {
        this.anOa3293 = anOa3293;
        this.aYa3292 = aYa3292;
        this.aClass87Array3294 = class178.aClass87Array1413;
        this.aClass35Array3295 = class178.aClass35Array1398;
        final int n5 = (class178.aClass87Array1413 == null) ? 0 : class178.aClass87Array1413.length;
        final int n6 = (class178.aClass35Array1398 == null) ? 0 : class178.aClass35Array1398.length;
        int n7 = 0;
        final int[] array = new int[n5 * 3 + n6];
        for (int i = 0; i < n5; ++i) {
            array[n7++] = this.aClass87Array3294[i].anInt666;
            array[n7++] = this.aClass87Array3294[i].anInt661;
            array[n7++] = this.aClass87Array3294[i].anInt674;
        }
        for (int j = 0; j < n6; ++j) {
            array[n7++] = this.aClass35Array3295[j].anInt327;
        }
        final int n8 = (class178.aClass106Array1419 == null) ? 0 : class178.aClass106Array1419.length;
        final int[] array2 = new int[n8 * 8];
        int n9 = 0;
        for (int k = 0; k < n8; ++k) {
            final Class106 class179 = class178.aClass106Array1419[k];
            final Class177 method689 = Class67.method689(class179.anInt905, (byte)(-119));
            array2[n9++] = class179.anInt906;
            array2[n9++] = method689.anInt1374;
            array2[n9++] = method689.anInt1380;
            array2[n9++] = method689.anInt1373;
            array2[n9++] = method689.anInt1384;
            array2[n9++] = method689.anInt1379;
            array2[n9++] = (method689.aBoolean1377 ? -1 : 0);
        }
        for (int l = 0; l < n8; ++l) {
            array2[n9++] = class178.aClass106Array1419[l].anInt908;
        }
        this.R(this.anOa3293, this.aYa3292, class178.anInt1407, class178.anInt1406, class178.anIntArray1416, class178.anIntArray1400, class178.anIntArray1418, class178.anIntArray1417, class178.aShortArray1408, class178.anInt1391, class178.aShortArray1393, class178.aShortArray1410, class178.aShortArray1392, class178.aByteArray1414, class178.aByteArray1402, class178.aByteArray1411, class178.aByteArray1420, class178.aShortArray1415, class178.aShortArray1409, class178.anIntArray1395, class178.aByte1422, class178.aShortArray1394, class178.anInt1396, class178.aByteArray1388, class178.aShortArray1403, class178.aShortArray1421, class178.aShortArray1385, class178.anIntArray1389, class178.anIntArray1404, class178.anIntArray1390, class178.aByteArray1423, class178.aByteArray1399, class178.anIntArray1412, class178.anIntArray1397, class178.anIntArray1386, array, n5, n6, n, n2, n3, n4, array2);
    }
    
    @Override
    final native int HA();
    
    i(final oa anOa3293) {
        this.anOa3293 = anOa3293;
        this.aYa3292 = null;
        this.oa(anOa3293);
    }
}
