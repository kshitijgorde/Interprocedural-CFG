import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

final class ha_Sub2 extends ha
{
    private boolean aBoolean4472;
    private boolean aBoolean4473;
    private int anInt4474;
    private int anInt4475;
    private Class377 aClass377_4476;
    private int anInt4477;
    Class98_Sub32 aClass98_Sub32_4478;
    private Canvas aCanvas4479;
    private int anInt4480;
    int anInt4481;
    int anInt4482;
    private Class235[] aClass235Array4483;
    int anInt4484;
    private int anInt4485;
    int anInt4486;
    float[] aFloatArray4487;
    float[] aFloatArray4488;
    int anInt4489;
    int anInt4490;
    private boolean aBoolean4491;
    int anInt4492;
    private int anInt4493;
    private Class79 aClass79_4494;
    int anInt4495;
    private int anInt4496;
    private Class79 aClass79_4497;
    private int anInt4498;
    private Class186 aClass186_4499;
    int anInt4500;
    int anInt4501;
    int anInt4502;
    private int anInt4503;
    int[] anIntArray4504;
    int anInt4505;
    int anInt4506;
    int anInt4507;
    int anInt4508;
    int anInt4509;
    int anInt4510;
    int anInt4511;
    private int anInt4512;
    Class111_Sub2 aClass111_Sub2_4513;
    int anInt4514;
    int anInt4515;
    int anInt4516;
    int anInt4517;
    int anInt4518;
    private Class332 aClass332_4519;
    private int anInt4520;
    
    @Override
    final boolean method1767() {
        return false;
    }
    
    @Override
    final Class98_Sub5 method1765(final int n, final int n2, final int n3, final int n4, final int n5, final float n6) {
        return null;
    }
    
    @Override
    final void method1791(final float n, final float n2, final float n3) {
    }
    
    @Override
    final void ZA(final int n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        this.anInt4501 = (int)(n2 * 65535.0f);
        this.anInt4489 = (int)(n3 * 65535.0f);
        final float n7 = (float)Math.sqrt(n4 * n4 + n5 * n5 + n6 * n6);
        this.anInt4481 = (int)(n4 * 65535.0f / n7);
        this.anInt4515 = (int)(n5 * 65535.0f / n7);
        this.anInt4500 = (int)(n6 * 65535.0f / n7);
    }
    
    @Override
    final void a(int i, int j, int n, int n2, int n3, final int n4, final aa aa, final int n5, final int n6, int n7, int n8, int n9) {
        final aa_Sub1 aa_Sub1 = (aa_Sub1)aa;
        final int[] anIntArray3555 = aa_Sub1.anIntArray3555;
        final int[] anIntArray3556 = aa_Sub1.anIntArray3557;
        final int n10 = (this.anInt4495 > n6) ? this.anInt4495 : n6;
        final int n11 = (this.anInt4492 < n6 + anIntArray3555.length) ? this.anInt4492 : (n6 + anIntArray3555.length);
        n9 <<= 8;
        n7 <<= 8;
        n8 <<= 8;
        final int n12 = n7 + n8;
        n9 %= n12;
        n -= i;
        n2 -= j;
        if (n + n2 < 0) {
            n9 = n12 + n7 - n9 - (int)(Math.sqrt(n * n + n2 * n2) * 256.0) % n12;
            n9 %= n12;
            if (n9 < 0) {
                n9 += n12;
            }
            i += n;
            n = -n;
            j += n2;
            n2 = -n2;
        }
        if (n <= n2) {
            i <<= 16;
            i += 32768;
            n <<= 16;
            final int n13 = (int)Math.floor(n / n2 + 0.5);
            final int n14 = (int)Math.sqrt(65536 + (n13 >> 8) * (n13 >> 8));
            n2 += j;
            final int n15 = n3 >>> 24;
            if (n4 == 0 || (n4 == 1 && n15 == 255)) {
                while (j <= n2) {
                    final int n16 = i >> 16;
                    final int n17 = j - n6;
                    if (j >= n10 && j < n11 && n16 >= this.anInt4509 && n16 < this.anInt4507 && n9 < n7 && n16 >= n5 + anIntArray3555[n17] && n16 < n5 + anIntArray3555[n17] + anIntArray3556[n17]) {
                        this.anIntArray4504[n16 + j * this.anInt4505] = n3;
                    }
                    i += n13;
                    ++j;
                    n9 += n14;
                    n9 %= n12;
                }
            }
            else if (n4 == 1) {
                n3 = ((n3 & 0xFF00FF) * n15 >> 8 & 0xFF00FF) + ((n3 & 0xFF00) * n15 >> 8 & 0xFF00) + (n15 << 24);
                final int n18 = 256 - n15;
                while (j <= n2) {
                    final int n19 = i >> 16;
                    final int n20 = j - n6;
                    if (j >= n10 && j < n11 && n19 >= this.anInt4509 && n19 < this.anInt4507 && n9 < n7 && n19 >= n5 + anIntArray3555[n20] && n19 < n5 + anIntArray3555[n20] + anIntArray3556[n20]) {
                        final int n21 = this.anIntArray4504[n19 + j * this.anInt4505];
                        this.anIntArray4504[n19 + j * this.anInt4505] = n3 + (((n21 & 0xFF00FF) * n18 >> 8 & 0xFF00FF) + ((n21 & 0xFF00) * n18 >> 8 & 0xFF00));
                    }
                    i += n13;
                    ++j;
                    n9 += n14;
                    n9 %= n12;
                }
            }
            else {
                if (n4 != 2) {
                    throw new IllegalArgumentException();
                }
                while (j <= n2) {
                    final int n22 = i >> 16;
                    final int n23 = j - n6;
                    if (j >= n10 && j < n11 && n22 >= this.anInt4509 && n22 < this.anInt4507 && n9 < n7 && n22 >= n5 + anIntArray3555[n23] && n22 < n5 + anIntArray3555[n23] + anIntArray3556[n23]) {
                        final int n24 = n22 + j * this.anInt4505;
                        final int n25 = this.anIntArray4504[n24];
                        final int n26 = n3 + n25;
                        final int n27 = (n3 & 0xFF00FF) + (n25 & 0xFF00FF);
                        final int n28 = (n27 & 0x1000100) + (n26 - n27 & 0x10000);
                        this.anIntArray4504[n24] = (n26 - n28 | n28 - (n28 >>> 8));
                    }
                    i += n13;
                    ++j;
                    n9 += n14;
                    n9 %= n12;
                }
            }
            return;
        }
        j <<= 16;
        j += 32768;
        n2 <<= 16;
        final int n29 = (int)Math.floor(n2 / n + 0.5);
        n += i;
        final int n30 = n3 >>> 24;
        final int n31 = (int)Math.sqrt(65536 + (n29 >> 8) * (n29 >> 8));
        if (n4 == 0 || (n4 == 1 && n30 == 255)) {
            while (i <= n) {
                final int n32 = j >> 16;
                final int n33 = n32 - n6;
                if (i >= this.anInt4509 && i < this.anInt4507 && n32 >= n10 && n32 < n11 && n9 < n7) {
                    final int n34 = n5 + anIntArray3555[n33];
                    if (i >= n34 && i < n34 + anIntArray3556[n33]) {
                        this.anIntArray4504[i + n32 * this.anInt4505] = n3;
                    }
                }
                j += n29;
                ++i;
                n9 += n31;
                n9 %= n12;
            }
            return;
        }
        if (n4 == 1) {
            n3 = ((n3 & 0xFF00FF) * n30 >> 8 & 0xFF00FF) + ((n3 & 0xFF00) * n30 >> 8 & 0xFF00) + (n30 << 24);
            final int n35 = 256 - n30;
            while (i <= n) {
                final int n36 = j >> 16;
                final int n37 = n36 - n6;
                if (i >= this.anInt4509 && i < this.anInt4507 && n36 >= n10 && n36 < n11 && n9 < n7) {
                    final int n38 = n5 + anIntArray3555[n37];
                    if (i >= n38 && i < n38 + anIntArray3556[n37]) {
                        final int n39 = i + n36 * this.anInt4505;
                        final int n40 = this.anIntArray4504[n39];
                        this.anIntArray4504[n39] = n3 + (((n40 & 0xFF00FF) * n35 >> 8 & 0xFF00FF) + ((n40 & 0xFF00) * n35 >> 8 & 0xFF00));
                    }
                }
                j += n29;
                ++i;
                n9 += n31;
                n9 %= n12;
            }
            return;
        }
        if (n4 == 2) {
            while (i <= n) {
                final int n41 = j >> 16;
                final int n42 = n41 - n6;
                if (i >= this.anInt4509 && i < this.anInt4507 && n41 >= n10 && n41 < n11 && n9 < n7) {
                    final int n43 = n5 + anIntArray3555[n42];
                    if (i >= n43 && i < n43 + anIntArray3556[n42]) {
                        final int n44 = i + n41 * this.anInt4505;
                        final int n45 = this.anIntArray4504[n44];
                        final int n46 = n3 + n45;
                        final int n47 = (n3 & 0xFF00FF) + (n45 & 0xFF00FF);
                        final int n48 = (n47 & 0x1000100) + (n46 - n47 & 0x10000);
                        this.anIntArray4504[n44] = (n46 - n48 | n48 - (n48 >>> 8));
                    }
                }
                j += n29;
                ++i;
                n9 += n31;
                n9 %= n12;
            }
            return;
        }
        throw new IllegalArgumentException();
    }
    
    @Override
    final void HA(final int n, final int n2, final int n3, final int n4, final int[] array) {
        final float n5 = this.aClass111_Sub2_4513.aFloat4689 + (this.aClass111_Sub2_4513.aFloat4693 * n + this.aClass111_Sub2_4513.aFloat4698 * n2 + this.aClass111_Sub2_4513.aFloat4694 * n3);
        if (n5 < this.anInt4502 || n5 > this.anInt4484) {
            final int n6 = 0;
            final int n7 = 1;
            final int n8 = 2;
            final int n9 = -1;
            array[n8] = n9;
            array[n6] = (array[n7] = n9);
        }
        else {
            final int n10 = (int)(this.anInt4514 * (this.aClass111_Sub2_4513.aFloat4697 + (this.aClass111_Sub2_4513.aFloat4700 * n + this.aClass111_Sub2_4513.aFloat4699 * n2 + this.aClass111_Sub2_4513.aFloat4690 * n3)) / n4);
            final int n11 = (int)(this.anInt4490 * (this.aClass111_Sub2_4513.aFloat4691 + (this.aClass111_Sub2_4513.aFloat4692 * n + this.aClass111_Sub2_4513.aFloat4688 * n2 + this.aClass111_Sub2_4513.aFloat4696 * n3)) / n4);
            if (n10 >= this.anInt4486 && n10 <= this.anInt4517 && n11 >= this.anInt4518 && n11 <= this.anInt4506) {
                array[0] = n10 - this.anInt4486;
                array[1] = n11 - this.anInt4518;
                array[2] = (int)n5;
            }
            else {
                final int n12 = 0;
                final int n13 = 1;
                final int n14 = 2;
                final int n15 = -1;
                array[n14] = n15;
                array[n12] = (array[n13] = n15);
            }
        }
    }
    
    @Override
    final void xa(final float n) {
        this.anInt4516 = (int)(n * 65535.0f);
    }
    
    @Override
    final void P(final int n, int anInt4495, int n2, int n3, final int n4) {
        if (n >= this.anInt4509 && n < this.anInt4507) {
            if (anInt4495 < this.anInt4495) {
                n2 -= this.anInt4495 - anInt4495;
                anInt4495 = this.anInt4495;
            }
            if (anInt4495 + n2 > this.anInt4492) {
                n2 = this.anInt4492 - anInt4495;
            }
            final int n5 = n + anInt4495 * this.anInt4505;
            final int n6 = n3 >>> 24;
            if (n4 == 0 || (n4 == 1 && n6 == 255)) {
                for (int i = 0; i < n2; ++i) {
                    this.anIntArray4504[n5 + i * this.anInt4505] = n3;
                }
            }
            else if (n4 == 1) {
                n3 = ((n3 & 0xFF00FF) * n6 >> 8 & 0xFF00FF) + ((n3 & 0xFF00) * n6 >> 8 & 0xFF00) + (n6 << 24);
                final int n7 = 256 - n6;
                for (int j = 0; j < n2; ++j) {
                    final int n8 = n5 + j * this.anInt4505;
                    final int n9 = this.anIntArray4504[n8];
                    this.anIntArray4504[n8] = n3 + (((n9 & 0xFF00FF) * n7 >> 8 & 0xFF00FF) + ((n9 & 0xFF00) * n7 >> 8 & 0xFF00));
                }
            }
            else {
                if (n4 != 2) {
                    throw new IllegalArgumentException();
                }
                for (int k = 0; k < n2; ++k) {
                    final int n10 = n5 + k * this.anInt4505;
                    final int n11 = this.anIntArray4504[n10];
                    final int n12 = n3 + n11;
                    final int n13 = (n3 & 0xFF00FF) + (n11 & 0xFF00FF);
                    final int n14 = (n13 & 0x1000100) + (n12 - n13 & 0x10000);
                    this.anIntArray4504[n10] = (n12 - n14 | n14 - (n14 >>> 8));
                }
            }
        }
    }
    
    @Override
    final boolean method1802() {
        return false;
    }
    
    @Override
    final void ra(final int anInt1754, final int anInt1755, final int anInt1756, final int n) {
        for (int i = 0; i < this.aClass235Array4483.length; ++i) {
            this.aClass235Array4483[i].anInt1755 = this.aClass235Array4483[i].anInt1763;
            this.aClass235Array4483[i].anInt1754 = anInt1754;
            this.aClass235Array4483[i].anInt1763 = anInt1755;
            this.aClass235Array4483[i].anInt1757 = anInt1756;
            this.aClass235Array4483[i].aBoolean1759 = true;
        }
    }
    
    @Override
    final void method1801(final int[] array) {
        array[0] = this.anInt4505;
        array[1] = this.anInt4480;
    }
    
    @Override
    final void method1786(final Canvas canvas) {
        if (this.aCanvas4479 == canvas) {
            this.method1750(null);
        }
        final Class98_Sub32 class98_Sub32 = (Class98_Sub32)this.aClass377_4476.method3990(canvas.hashCode(), -1);
        if (class98_Sub32 != null) {
            class98_Sub32.method942(75);
        }
    }
    
    private ha_Sub2(final d d) {
        super(d);
        this.aBoolean4473 = false;
        this.aBoolean4472 = false;
        this.aClass377_4476 = new Class377(4);
        this.anInt4495 = 0;
        this.anInt4484 = 3500;
        this.aBoolean4491 = false;
        this.anInt4501 = 45823;
        this.anInt4498 = 0;
        this.anInt4509 = 0;
        this.anInt4482 = 128;
        this.anInt4492 = 0;
        this.anInt4493 = 0;
        this.anInt4516 = 75518;
        this.anInt4507 = 0;
        this.anInt4502 = 50;
        this.anInt4489 = 78642;
        this.anInt4490 = 512;
        this.anInt4514 = 512;
        this.aClass79_4497 = new Class79(16);
        this.anInt4520 = -1;
        try {
            this.aClass79_4494 = new Class79(256);
            this.aClass111_Sub2_4513 = new Class111_Sub2();
            this.method1783(1);
            this.method1807(0);
            Class101.method1702(8, true, true);
            this.aBoolean4473 = true;
            this.anInt4475 = (int)Class343.method3819(-47);
        }
        catch (Throwable t) {
            t.printStackTrace();
            this.method1743(-1);
            throw new RuntimeException("");
        }
    }
    
    @Override
    final boolean method1766() {
        return true;
    }
    
    private final void method1911() {
        for (int i = 0; i < this.anInt4508; ++i) {
            this.aClass235Array4483[i].method2888((byte)(-60));
        }
        this.la();
    }
    
    @Override
    final Interface5 method1813(final int n, final int n2) {
        return this.method1739(n, n2, false);
    }
    
    @Override
    final Interface17 method1815(final Interface5 interface5, final Interface13 interface6) {
        return new Class186(this, (Class332)interface5, (Class333)interface6);
    }
    
    @Override
    final void method1783(final int anInt4508) {
        this.anInt4508 = anInt4508;
        this.aClass235Array4483 = new Class235[this.anInt4508];
        for (int i = 0; i < this.anInt4508; ++i) {
            this.aClass235Array4483[i] = new Class235(this);
        }
    }
    
    @Override
    final void method1816(int n, int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        final Class12 aClass12_1767 = this.method1921(Thread.currentThread()).aClass12_1767;
        final int n8 = n3 - n;
        final int n9 = n4 - n2;
        final int n10 = (n8 >= 0) ? n8 : (-n8);
        final int n11 = (n9 >= 0) ? n9 : (-n9);
        int n12 = n10;
        if (n12 < n11) {
            n12 = n11;
        }
        if (n12 != 0) {
            int n13 = (n8 << 16) / n12;
            int n14 = (n9 << 16) / n12;
            final int n15 = n8 + (n13 >> 16);
            final int n16 = n9 + (n14 >> 16);
            if (n14 <= n13) {
                n13 = -n13;
            }
            else {
                n14 = -n14;
            }
            final int n17 = n6 * n14 >> 17;
            final int n18 = n6 * n14 + 1 >> 17;
            final int n19 = n6 * n13 >> 17;
            final int n20 = n6 * n13 + 1 >> 17;
            n -= aClass12_1767.method207();
            n2 -= aClass12_1767.method210();
            final int n21 = n + n17;
            final int n22 = n - n18;
            final int n23 = n + n15 - n18;
            final int n24 = n + n15 + n17;
            final int n25 = n2 + n19;
            final int n26 = n2 - n20;
            final int n27 = n2 + n16 - n20;
            final int n28 = n2 + n16 + n19;
            if (n7 == 0) {
                aClass12_1767.anInt137 = 0;
            }
            else {
                if (n7 != 1) {
                    throw new IllegalArgumentException();
                }
                aClass12_1767.anInt137 = 255 - (n5 >>> 24);
            }
            this.C(false);
            aClass12_1767.aBoolean135 = (n21 < 0 || n21 > aClass12_1767.anInt141 || n22 < 0 || n22 > aClass12_1767.anInt141 || n23 < 0 || n23 > aClass12_1767.anInt141);
            aClass12_1767.method208(n25, n26, n27, n21, n22, n23, 100.0f, 100.0f, 100.0f, n5);
            aClass12_1767.aBoolean135 = (n21 < 0 || n21 > aClass12_1767.anInt141 || n23 < 0 || n23 > aClass12_1767.anInt141 || n24 < 0 || n24 > aClass12_1767.anInt141);
            aClass12_1767.method208(n25, n27, n28, n21, n23, n24, 100.0f, 100.0f, 100.0f, n5);
            this.C(true);
        }
    }
    
    ha_Sub2(final Canvas canvas, final d d, final int n, final int n2) {
        this(d);
        try {
            this.method1741(canvas, n, n2);
            this.method1750(canvas);
        }
        catch (Throwable t) {
            t.printStackTrace();
            this.method1743(-1);
            throw new RuntimeException("");
        }
    }
    
    @Override
    final boolean method1819() {
        return false;
    }
    
    @Override
    final void method1798(final int n) {
        Class146_Sub1.anInt4825 = n;
        Class146_Sub1.anInt4810 = n;
        if (this.anInt4508 > 1) {
            throw new IllegalStateException("No MT");
        }
        this.method1783(this.anInt4508);
        this.method1807(0);
    }
    
    @Override
    final int M() {
        final int anInt4493 = this.anInt4493;
        this.anInt4493 = 0;
        return anInt4493;
    }
    
    @Override
    final int method1777(final int n, final int n2) {
        return n | n2;
    }
    
    @Override
    final int method1822() {
        return 0;
    }
    
    final int method1912(final int n) {
        return super.aD938.method11(n, -28755).anInt1818;
    }
    
    @Override
    final void method1756() {
    }
    
    @Override
    final int I() {
        final int anInt4498 = this.anInt4498;
        this.anInt4498 = 0;
        return anInt4498;
    }
    
    @Override
    final Class332 method1770(final int[] array, final int n, final int n2, final int n3, final int n4, final boolean b) {
        boolean b2 = false;
        int n5 = n;
    Label_0069:
        for (int i = 0; i < n4; ++i) {
            for (int j = 0; j < n3; ++j) {
                final int n6 = array[n5++] >>> 24;
                if (n6 != 0 && n6 != 255) {
                    b2 = true;
                    break Label_0069;
                }
            }
        }
        if (b2) {
            return new Class332_Sub3_Sub1(this, array, n, n2, n3, n4, b);
        }
        return new Class332_Sub3_Sub2(this, array, n, n2, n3, n4, b);
    }
    
    private final void method1913() {
        this.anInt4486 = this.anInt4509 - this.anInt4510;
        this.anInt4517 = this.anInt4507 - this.anInt4510;
        this.anInt4518 = this.anInt4495 - this.anInt4511;
        this.anInt4506 = this.anInt4492 - this.anInt4511;
        for (int i = 0; i < this.anInt4508; ++i) {
            final Class12 aClass12_1767 = this.aClass235Array4483[i].aClass12_1767;
            aClass12_1767.anInt136 = this.anInt4510 - this.anInt4509;
            aClass12_1767.anInt128 = this.anInt4511 - this.anInt4495;
            aClass12_1767.anInt141 = this.anInt4507 - this.anInt4509;
            aClass12_1767.anInt129 = this.anInt4492 - this.anInt4495;
        }
        int n = this.anInt4495 * this.anInt4505 + this.anInt4509;
        for (int j = this.anInt4495; j < this.anInt4492; ++j) {
            for (int k = 0; k < this.anInt4508; ++k) {
                this.aClass235Array4483[k].aClass12_1767.anIntArray133[j - this.anInt4495] = n;
            }
            n += this.anInt4505;
        }
    }
    
    @Override
    final int r(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        final float n8 = this.aClass111_Sub2_4513.aFloat4693 * n + this.aClass111_Sub2_4513.aFloat4698 * n2 + this.aClass111_Sub2_4513.aFloat4694 * n3 + this.aClass111_Sub2_4513.aFloat4689;
        final float n9 = this.aClass111_Sub2_4513.aFloat4693 * n4 + this.aClass111_Sub2_4513.aFloat4698 * n5 + this.aClass111_Sub2_4513.aFloat4694 * n6 + this.aClass111_Sub2_4513.aFloat4689;
        int n10 = 0;
        if (n8 < this.anInt4502 && n9 < this.anInt4502) {
            n10 |= 0x10;
        }
        else if (n8 > this.anInt4484 && n9 > this.anInt4484) {
            n10 |= 0x20;
        }
        final int n11 = (int)(this.anInt4514 * (this.aClass111_Sub2_4513.aFloat4700 * n + this.aClass111_Sub2_4513.aFloat4699 * n2 + this.aClass111_Sub2_4513.aFloat4690 * n3 + this.aClass111_Sub2_4513.aFloat4697) / n7);
        final int n12 = (int)(this.anInt4514 * (this.aClass111_Sub2_4513.aFloat4700 * n4 + this.aClass111_Sub2_4513.aFloat4699 * n5 + this.aClass111_Sub2_4513.aFloat4690 * n6 + this.aClass111_Sub2_4513.aFloat4697) / n7);
        if (n11 < this.anInt4486 && n12 < this.anInt4486) {
            n10 |= 0x1;
        }
        else if (n11 > this.anInt4517 && n12 > this.anInt4517) {
            n10 |= 0x2;
        }
        final int n13 = (int)(this.anInt4490 * (this.aClass111_Sub2_4513.aFloat4692 * n + this.aClass111_Sub2_4513.aFloat4688 * n2 + this.aClass111_Sub2_4513.aFloat4696 * n3 + this.aClass111_Sub2_4513.aFloat4691) / n7);
        final int n14 = (int)(this.anInt4490 * (this.aClass111_Sub2_4513.aFloat4692 * n4 + this.aClass111_Sub2_4513.aFloat4688 * n5 + this.aClass111_Sub2_4513.aFloat4696 * n6 + this.aClass111_Sub2_4513.aFloat4691) / n7);
        if (n13 < this.anInt4518 && n14 < this.anInt4518) {
            n10 |= 0x4;
        }
        else if (n13 > this.anInt4506 && n14 > this.anInt4506) {
            n10 |= 0x8;
        }
        return n10;
    }
    
    private final void method1914(final Class246_Sub4_Sub2 class246_Sub4_Sub2, final int n, final int n2, final int n3, int n4) {
        final int anInt6180 = class246_Sub4_Sub2.anInt6180;
        final int n5 = n4;
        n4 <<= 1;
        if (anInt6180 == -1) {
            this.method1916(n, n2, n3, n5, class246_Sub4_Sub2.anInt6178, 1);
        }
        else {
            if (this.anInt4520 != anInt6180) {
                Class332 method1748 = (Class332)this.aClass79_4497.method802(-119, anInt6180);
                if (method1748 == null) {
                    final int[] method1749 = this.method1915(anInt6180);
                    if (method1749 == null) {
                        return;
                    }
                    final int n6 = this.method1925(anInt6180) ? 64 : this.anInt4482;
                    method1748 = this.method1748(-7962, 0, n6, n6, method1749, n6);
                    this.aClass79_4497.method805(anInt6180, method1748, (byte)(-80));
                }
                this.anInt4520 = anInt6180;
                this.aClass332_4519 = method1748;
            }
            ++n4;
            ((Class332_Sub3)this.aClass332_4519).method3757(n - n5, n2 - n5, n3, n4, n4, 0, class246_Sub4_Sub2.anInt6178, 1, 1);
        }
    }
    
    @Override
    final boolean method1824() {
        return false;
    }
    
    final int[] method1915(final int n) {
        Class98_Sub2 class98_Sub2;
        synchronized (this.aClass79_4494) {
            class98_Sub2 = (Class98_Sub2)this.aClass79_4494.method802(-121, n | Long.MIN_VALUE);
            if (class98_Sub2 == null) {
                if (!super.aD938.method8(-119, n)) {
                    return null;
                }
                final Class238 method11 = super.aD938.method11(n, -28755);
                final int n2 = (method11.aBoolean1822 || this.aBoolean4491) ? 64 : this.anInt4482;
                class98_Sub2 = new Class98_Sub2(n, n2, super.aD938.method13(115, n2, n, 0.7f, true, n2), method11.anInt1818 != 1);
                this.aClass79_4494.method805(n | Long.MIN_VALUE, class98_Sub2, (byte)(-80));
            }
        }
        class98_Sub2.aBoolean3817 = true;
        return class98_Sub2.method948();
    }
    
    @Override
    final void za(final int n, int n2, int n3, int n4, final int n5) {
        if (n3 < 0) {
            n3 = -n3;
        }
        int anInt4495 = n2 - n3;
        if (anInt4495 < this.anInt4495) {
            anInt4495 = this.anInt4495;
        }
        int anInt4496 = n2 + n3 + 1;
        if (anInt4496 > this.anInt4492) {
            anInt4496 = this.anInt4492;
        }
        int i = anInt4495;
        final int n6 = n3 * n3;
        int n7 = 0;
        int n8 = n2 - i;
        int n9 = n8 * n8;
        int n10 = n9 - n8;
        if (n2 > anInt4496) {
            n2 = anInt4496;
        }
        final int n11 = n4 >>> 24;
        if (n5 == 0 || (n5 == 1 && n11 == 255)) {
            while (i < n2) {
                while (n10 <= n6 || n9 <= n6) {
                    n9 += n7 + n7;
                    n10 += n7 + ++n7;
                }
                int anInt4497 = n - n7 + 1;
                if (anInt4497 < this.anInt4509) {
                    anInt4497 = this.anInt4509;
                }
                int anInt4498 = n + n7;
                if (anInt4498 > this.anInt4507) {
                    anInt4498 = this.anInt4507;
                }
                int n12 = anInt4497 + i * this.anInt4505;
                for (int j = anInt4497; j < anInt4498; ++j) {
                    this.anIntArray4504[n12++] = n4;
                }
                ++i;
                n9 -= n8 + --n8;
                n10 -= n8 + n8;
            }
            int n13 = n3;
            int n14 = i - n2;
            final int n15 = n14 * n14 + n6;
            for (int n16 = n15 - n13, n17 = n15 - n14; i < anInt4496; ++i, n17 += n14 + n14, n16 += n14 + ++n14) {
                while (n17 > n6 && n16 > n6) {
                    n17 -= n13 + --n13;
                    n16 -= n13 + n13;
                }
                int anInt4499 = n - n13;
                if (anInt4499 < this.anInt4509) {
                    anInt4499 = this.anInt4509;
                }
                int n18 = n + n13;
                if (n18 > this.anInt4507 - 1) {
                    n18 = this.anInt4507 - 1;
                }
                int n19 = anInt4499 + i * this.anInt4505;
                for (int k = anInt4499; k <= n18; ++k) {
                    this.anIntArray4504[n19++] = n4;
                }
            }
        }
        else if (n5 == 1) {
            n4 = ((n4 & 0xFF00FF) * n11 >> 8 & 0xFF00FF) + ((n4 & 0xFF00) * n11 >> 8 & 0xFF00) + (n11 << 24);
            final int n20 = 256 - n11;
            while (i < n2) {
                while (n10 <= n6 || n9 <= n6) {
                    n9 += n7 + n7;
                    n10 += n7 + ++n7;
                }
                int anInt4500 = n - n7 + 1;
                if (anInt4500 < this.anInt4509) {
                    anInt4500 = this.anInt4509;
                }
                int anInt4501 = n + n7;
                if (anInt4501 > this.anInt4507) {
                    anInt4501 = this.anInt4507;
                }
                int n21 = anInt4500 + i * this.anInt4505;
                for (int l = anInt4500; l < anInt4501; ++l) {
                    final int n22 = this.anIntArray4504[n21];
                    this.anIntArray4504[n21++] = n4 + (((n22 & 0xFF00FF) * n20 >> 8 & 0xFF00FF) + ((n22 & 0xFF00) * n20 >> 8 & 0xFF00));
                }
                ++i;
                n9 -= n8 + --n8;
                n10 -= n8 + n8;
            }
            int n23 = n3;
            int n24 = -n8;
            final int n25 = n24 * n24 + n6;
            for (int n26 = n25 - n23, n27 = n25 - n24; i < anInt4496; ++i, n27 += n24 + n24, n26 += n24 + ++n24) {
                while (n27 > n6 && n26 > n6) {
                    n27 -= n23 + --n23;
                    n26 -= n23 + n23;
                }
                int anInt4502 = n - n23;
                if (anInt4502 < this.anInt4509) {
                    anInt4502 = this.anInt4509;
                }
                int n28 = n + n23;
                if (n28 > this.anInt4507 - 1) {
                    n28 = this.anInt4507 - 1;
                }
                int n29 = anInt4502 + i * this.anInt4505;
                for (int n30 = anInt4502; n30 <= n28; ++n30) {
                    final int n31 = this.anIntArray4504[n29];
                    this.anIntArray4504[n29++] = n4 + (((n31 & 0xFF00FF) * n20 >> 8 & 0xFF00FF) + ((n31 & 0xFF00) * n20 >> 8 & 0xFF00));
                }
            }
        }
        else {
            if (n5 != 2) {
                throw new IllegalArgumentException();
            }
            while (i < n2) {
                while (n10 <= n6 || n9 <= n6) {
                    n9 += n7 + n7;
                    n10 += n7 + ++n7;
                }
                int anInt4503 = n - n7 + 1;
                if (anInt4503 < this.anInt4509) {
                    anInt4503 = this.anInt4509;
                }
                int anInt4504 = n + n7;
                if (anInt4504 > this.anInt4507) {
                    anInt4504 = this.anInt4507;
                }
                int n32 = anInt4503 + i * this.anInt4505;
                for (int n33 = anInt4503; n33 < anInt4504; ++n33) {
                    final int n34 = this.anIntArray4504[n32];
                    final int n35 = n4 + n34;
                    final int n36 = (n4 & 0xFF00FF) + (n34 & 0xFF00FF);
                    final int n37 = (n36 & 0x1000100) + (n35 - n36 & 0x10000);
                    this.anIntArray4504[n32++] = (n35 - n37 | n37 - (n37 >>> 8));
                }
                ++i;
                n9 -= n8 + --n8;
                n10 -= n8 + n8;
            }
            int n38 = n3;
            int n39 = -n8;
            final int n40 = n39 * n39 + n6;
            for (int n41 = n40 - n38, n42 = n40 - n39; i < anInt4496; ++i, n42 += n39 + n39, n41 += n39 + ++n39) {
                while (n42 > n6 && n41 > n6) {
                    n42 -= n38 + --n38;
                    n41 -= n38 + n38;
                }
                int anInt4505 = n - n38;
                if (anInt4505 < this.anInt4509) {
                    anInt4505 = this.anInt4509;
                }
                int n43 = n + n38;
                if (n43 > this.anInt4507 - 1) {
                    n43 = this.anInt4507 - 1;
                }
                int n44 = anInt4505 + i * this.anInt4505;
                for (int n45 = anInt4505; n45 <= n43; ++n45) {
                    final int n46 = this.anIntArray4504[n44];
                    final int n47 = n4 + n46;
                    final int n48 = (n4 & 0xFF00FF) + (n46 & 0xFF00FF);
                    final int n49 = (n48 & 0x1000100) + (n47 - n48 & 0x10000);
                    this.anIntArray4504[n44++] = (n47 - n49 | n49 - (n49 >>> 8));
                }
            }
        }
    }
    
    @Override
    final void method1740(final Interface17 interface17) {
        final Class186 aClass186_4499 = (Class186)interface17;
        this.anInt4505 = aClass186_4499.anInt3426;
        this.anInt4480 = aClass186_4499.anInt3430;
        this.anIntArray4504 = aClass186_4499.anIntArray3427;
        this.aClass186_4499 = aClass186_4499;
        this.anInt4512 = aClass186_4499.anInt3426;
        this.anInt4485 = aClass186_4499.anInt3430;
        this.aFloatArray4487 = aClass186_4499.aFloatArray3429;
        this.method1911();
    }
    
    private final void method1916(final int n, int n2, final int n3, int n4, int n5, final int n6) {
        if (n4 < 0) {
            n4 = -n4;
        }
        int anInt4495 = n2 - n4;
        if (anInt4495 < this.anInt4495) {
            anInt4495 = this.anInt4495;
        }
        int anInt4496 = n2 + n4 + 1;
        if (anInt4496 > this.anInt4492) {
            anInt4496 = this.anInt4492;
        }
        int i = anInt4495;
        final int n7 = n4 * n4;
        int n8 = 0;
        int n9 = n2 - i;
        int n10 = n9 * n9;
        int n11 = n10 - n9;
        if (n2 > anInt4496) {
            n2 = anInt4496;
        }
        final int n12 = n5 >>> 24;
        if (n6 == 0 || (n6 == 1 && n12 == 255)) {
            while (i < n2) {
                while (n11 <= n7 || n10 <= n7) {
                    n10 += n8 + n8;
                    n11 += n8 + ++n8;
                }
                int anInt4497 = n - n8 + 1;
                if (anInt4497 < this.anInt4509) {
                    anInt4497 = this.anInt4509;
                }
                int anInt4498 = n + n8;
                if (anInt4498 > this.anInt4507) {
                    anInt4498 = this.anInt4507;
                }
                int n13 = anInt4497 + i * this.anInt4505;
                for (int j = anInt4497; j < anInt4498; ++j) {
                    if (n3 < this.aFloatArray4487[n13]) {
                        this.anIntArray4504[n13] = n5;
                    }
                    ++n13;
                }
                ++i;
                n10 -= n9 + --n9;
                n11 -= n9 + n9;
            }
            int n14 = n4;
            int n15 = i - n2;
            final int n16 = n15 * n15 + n7;
            for (int n17 = n16 - n14, n18 = n16 - n15; i < anInt4496; ++i, n18 += n15 + n15, n17 += n15 + ++n15) {
                while (n18 > n7 && n17 > n7) {
                    n18 -= n14 + --n14;
                    n17 -= n14 + n14;
                }
                int anInt4499 = n - n14;
                if (anInt4499 < this.anInt4509) {
                    anInt4499 = this.anInt4509;
                }
                int n19 = n + n14;
                if (n19 > this.anInt4507 - 1) {
                    n19 = this.anInt4507 - 1;
                }
                int n20 = anInt4499 + i * this.anInt4505;
                for (int k = anInt4499; k <= n19; ++k) {
                    if (n3 < this.aFloatArray4487[n20]) {
                        this.anIntArray4504[n20] = n5;
                    }
                    ++n20;
                }
            }
        }
        else if (n6 == 1) {
            n5 = ((n5 & 0xFF00FF) * n12 >> 8 & 0xFF00FF) + ((n5 & 0xFF00) * n12 >> 8 & 0xFF00) + (n12 << 24);
            final int n21 = 256 - n12;
            while (i < n2) {
                while (n11 <= n7 || n10 <= n7) {
                    n10 += n8 + n8;
                    n11 += n8 + ++n8;
                }
                int anInt4500 = n - n8 + 1;
                if (anInt4500 < this.anInt4509) {
                    anInt4500 = this.anInt4509;
                }
                int anInt4501 = n + n8;
                if (anInt4501 > this.anInt4507) {
                    anInt4501 = this.anInt4507;
                }
                int n22 = anInt4500 + i * this.anInt4505;
                for (int l = anInt4500; l < anInt4501; ++l) {
                    if (n3 < this.aFloatArray4487[n22]) {
                        final int n23 = this.anIntArray4504[n22];
                        this.anIntArray4504[n22] = n5 + (((n23 & 0xFF00FF) * n21 >> 8 & 0xFF00FF) + ((n23 & 0xFF00) * n21 >> 8 & 0xFF00));
                    }
                    ++n22;
                }
                ++i;
                n10 -= n9 + --n9;
                n11 -= n9 + n9;
            }
            int n24 = n4;
            int n25 = -n9;
            final int n26 = n25 * n25 + n7;
            for (int n27 = n26 - n24, n28 = n26 - n25; i < anInt4496; ++i, n28 += n25 + n25, n27 += n25 + ++n25) {
                while (n28 > n7 && n27 > n7) {
                    n28 -= n24 + --n24;
                    n27 -= n24 + n24;
                }
                int anInt4502 = n - n24;
                if (anInt4502 < this.anInt4509) {
                    anInt4502 = this.anInt4509;
                }
                int n29 = n + n24;
                if (n29 > this.anInt4507 - 1) {
                    n29 = this.anInt4507 - 1;
                }
                int n30 = anInt4502 + i * this.anInt4505;
                for (int n31 = anInt4502; n31 <= n29; ++n31) {
                    if (n3 < this.aFloatArray4487[n30]) {
                        final int n32 = this.anIntArray4504[n30];
                        this.anIntArray4504[n30] = n5 + (((n32 & 0xFF00FF) * n21 >> 8 & 0xFF00FF) + ((n32 & 0xFF00) * n21 >> 8 & 0xFF00));
                    }
                    ++n30;
                }
            }
        }
        else {
            if (n6 != 2) {
                throw new IllegalArgumentException();
            }
            while (i < n2) {
                while (n11 <= n7 || n10 <= n7) {
                    n10 += n8 + n8;
                    n11 += n8 + ++n8;
                }
                int anInt4503 = n - n8 + 1;
                if (anInt4503 < this.anInt4509) {
                    anInt4503 = this.anInt4509;
                }
                int anInt4504 = n + n8;
                if (anInt4504 > this.anInt4507) {
                    anInt4504 = this.anInt4507;
                }
                int n33 = anInt4503 + i * this.anInt4505;
                for (int n34 = anInt4503; n34 < anInt4504; ++n34) {
                    if (n3 < this.aFloatArray4487[n33]) {
                        final int n35 = this.anIntArray4504[n33];
                        final int n36 = n5 + n35;
                        final int n37 = (n5 & 0xFF00FF) + (n35 & 0xFF00FF);
                        final int n38 = (n37 & 0x1000100) + (n36 - n37 & 0x10000);
                        this.anIntArray4504[n33] = (n36 - n38 | n38 - (n38 >>> 8));
                    }
                    ++n33;
                }
                ++i;
                n10 -= n9 + --n9;
                n11 -= n9 + n9;
            }
            int n39 = n4;
            int n40 = -n9;
            final int n41 = n40 * n40 + n7;
            for (int n42 = n41 - n39, n43 = n41 - n40; i < anInt4496; ++i, n43 += n40 + n40, n42 += n40 + ++n40) {
                while (n43 > n7 && n42 > n7) {
                    n43 -= n39 + --n39;
                    n42 -= n39 + n39;
                }
                int anInt4505 = n - n39;
                if (anInt4505 < this.anInt4509) {
                    anInt4505 = this.anInt4509;
                }
                int n44 = n + n39;
                if (n44 > this.anInt4507 - 1) {
                    n44 = this.anInt4507 - 1;
                }
                int n45 = anInt4505 + i * this.anInt4505;
                for (int n46 = anInt4505; n46 <= n44; ++n46) {
                    if (n3 < this.aFloatArray4487[n45]) {
                        final int n47 = this.anIntArray4504[n45];
                        final int n48 = n5 + n47;
                        final int n49 = (n5 & 0xFF00FF) + (n47 & 0xFF00FF);
                        final int n50 = (n49 & 0x1000100) + (n48 - n49 & 0x10000);
                        this.anIntArray4504[n45] = (n48 - n50 | n50 - (n50 >>> 8));
                    }
                    ++n45;
                }
            }
        }
    }
    
    @Override
    final Interface13 method1744(final int n, final int n2) {
        return new Class333(n, n2);
    }
    
    @Override
    final void method1807(final int n) {
        this.aClass235Array4483[n].method2889((byte)34, Thread.currentThread());
    }
    
    @Override
    final Class146 method1790(final Class178 class178, final int n, final int n2, final int n3, final int n4) {
        return new Class146_Sub1(this, class178, n, n3, n4, n2);
    }
    
    @Override
    final Class332 method1739(final int n, final int n2, final boolean b) {
        if (b) {
            return new Class332_Sub3_Sub1(this, n, n2);
        }
        return new Class332_Sub3_Sub2(this, n, n2);
    }
    
    @Override
    final void L(final int n, final int n2, final int n3) {
        for (int i = 0; i < this.aClass235Array4483.length; ++i) {
            final Class235 class235 = this.aClass235Array4483[i];
            class235.anInt1763 = (n & 0xFFFFFF);
            int n4 = class235.anInt1763 >>> 16 & 0xFF;
            if (n4 < 2) {
                n4 = 2;
            }
            int n5 = class235.anInt1763 >> 8 & 0xFF;
            if (n5 < 2) {
                n5 = 2;
            }
            int n6 = class235.anInt1763 & 0xFF;
            if (n6 < 2) {
                n6 = 2;
            }
            class235.anInt1763 = (n4 << 16 | n5 << 8 | n6);
            if (n2 < 0) {
                class235.aBoolean1758 = false;
            }
            else {
                class235.aBoolean1758 = true;
            }
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
                return new Class43_Sub3(this, class197, array, array2, array3);
            }
            return new Class43_Sub5(this, class197, array, array2, array3);
        }
        else {
            if (b2) {
                throw new IllegalArgumentException("");
            }
            return new Class43_Sub4(this, class197, array, array2, array3);
        }
    }
    
    @Override
    final void method1746(final int n, final int n2, final int n3, final int n4) {
    }
    
    private final void method1917(final int n, final int n2, final int n3, int n4, final int n5, final int n6, final int n7, final int n8) {
        if (n >= this.anInt4509 && n < this.anInt4507) {
            final int n9 = n + n2 * this.anInt4505;
            final int n10 = n4 >>> 24;
            final int n11 = n6 + n7;
            int n12 = n8 % n11;
            if (n5 == 0 || (n5 == 1 && n10 == 255)) {
                for (int i = 0; i < n3; ++i, n12 = ++n12 % n11) {
                    if (n2 + i >= this.anInt4495 && n2 + i < this.anInt4492 && n12 < n6) {
                        this.anIntArray4504[n9 + i * this.anInt4505] = n4;
                    }
                }
            }
            else if (n5 == 1) {
                n4 = ((n4 & 0xFF00FF) * n10 >> 8 & 0xFF00FF) + ((n4 & 0xFF00) * n10 >> 8 & 0xFF00) + (n10 << 24);
                final int n13 = 256 - n10;
                for (int j = 0; j < n3; ++j, n12 = ++n12 % n11) {
                    if (n2 + j >= this.anInt4495 && n2 + j < this.anInt4492 && n12 < n6) {
                        final int n14 = n9 + j * this.anInt4505;
                        final int n15 = this.anIntArray4504[n14];
                        this.anIntArray4504[n14] = n4 + (((n15 & 0xFF00FF) * n13 >> 8 & 0xFF00FF) + ((n15 & 0xFF00) * n13 >> 8 & 0xFF00));
                    }
                }
            }
            else {
                if (n5 != 2) {
                    throw new IllegalArgumentException();
                }
                for (int k = 0; k < n3; ++k, n12 = ++n12 % n11) {
                    if (n2 + k >= this.anInt4495 && n2 + k < this.anInt4492 && n12 < n6) {
                        final int n16 = n9 + k * this.anInt4505;
                        final int n17 = this.anIntArray4504[n16];
                        final int n18 = n4 + n17;
                        final int n19 = (n4 & 0xFF00FF) + (n17 & 0xFF00FF);
                        final int n20 = (n19 & 0x1000100) + (n18 - n19 & 0x10000);
                        this.anIntArray4504[n16] = (n18 - n20 | n20 - (n20 >>> 8));
                    }
                }
            }
        }
    }
    
    @Override
    final void method1775(final Class48 class48) {
    }
    
    @Override
    final void ya() {
        if (this.anInt4509 == 0 && this.anInt4507 == this.anInt4505 && this.anInt4495 == 0 && this.anInt4492 == this.anInt4480) {
            final int length = this.aFloatArray4487.length;
            int n;
            int i;
            for (n = length - (length & 0x7), i = 0; i < n; this.aFloatArray4487[i++] = 2.14748365E9f, this.aFloatArray4487[i++] = 2.14748365E9f, this.aFloatArray4487[i++] = 2.14748365E9f, this.aFloatArray4487[i++] = 2.14748365E9f, this.aFloatArray4487[i++] = 2.14748365E9f, this.aFloatArray4487[i++] = 2.14748365E9f, this.aFloatArray4487[i++] = 2.14748365E9f, this.aFloatArray4487[i++] = 2.14748365E9f) {}
            while (i < length) {
                this.aFloatArray4487[i++] = 2.14748365E9f;
            }
        }
        else {
            final int n2 = this.anInt4507 - this.anInt4509;
            final int n3 = this.anInt4492 - this.anInt4495;
            final int n4 = this.anInt4505 - n2;
            final int n5 = this.anInt4509 + this.anInt4495 * this.anInt4505;
            int n6 = n2 >> 3;
            int n7 = n2 & 0x7;
            int n8 = n5 - 1;
            for (int j = -n3; j < 0; ++j) {
                if (n6 > 0) {
                    do {
                        this.aFloatArray4487[++n8] = 2.14748365E9f;
                        this.aFloatArray4487[++n8] = 2.14748365E9f;
                        this.aFloatArray4487[++n8] = 2.14748365E9f;
                        this.aFloatArray4487[++n8] = 2.14748365E9f;
                        this.aFloatArray4487[++n8] = 2.14748365E9f;
                        this.aFloatArray4487[++n8] = 2.14748365E9f;
                        this.aFloatArray4487[++n8] = 2.14748365E9f;
                        this.aFloatArray4487[++n8] = 2.14748365E9f;
                    } while (--n6 > 0);
                }
                if (n7 > 0) {
                    do {
                        this.aFloatArray4487[++n8] = 2.14748365E9f;
                    } while (--n7 > 0);
                }
                n8 += n4;
            }
        }
    }
    
    @Override
    final boolean method1771() {
        return false;
    }
    
    @Override
    final boolean method1768() {
        return false;
    }
    
    final boolean method1918(final int n) {
        return super.aD938.method8(-14, n);
    }
    
    @Override
    final Class111 method1821() {
        return new Class111_Sub2();
    }
    
    @Override
    final void f(final int anInt4502, final int anInt4503) {
        final Class235 method1921 = this.method1921(Thread.currentThread());
        this.anInt4502 = anInt4502;
        this.anInt4484 = anInt4503;
        method1921.anInt1761 = this.anInt4484 - 255;
    }
    
    @Override
    final void C(final boolean aBoolean1762) {
        this.method1921(Thread.currentThread()).aBoolean1762 = aBoolean1762;
    }
    
    @Override
    final void A(final int n, final aa aa, final int n2, int anInt4495) {
        final aa_Sub1 aa_Sub1 = (aa_Sub1)aa;
        final int[] anIntArray3555 = aa_Sub1.anIntArray3555;
        final int[] anIntArray3556 = aa_Sub1.anIntArray3557;
        int length;
        if (this.anInt4492 < anInt4495 + anIntArray3555.length) {
            length = this.anInt4492 - anInt4495;
        }
        else {
            length = anIntArray3555.length;
        }
        int n3;
        if (this.anInt4495 > anInt4495) {
            n3 = this.anInt4495 - anInt4495;
            anInt4495 = this.anInt4495;
        }
        else {
            n3 = 0;
        }
        if (length - n3 > 0) {
            int n4 = anInt4495 * this.anInt4505;
            for (int i = n3; i < length; ++i) {
                int anInt4496 = n2 + anIntArray3555[i];
                int n5 = anIntArray3556[i];
                if (this.anInt4509 > anInt4496) {
                    n5 -= this.anInt4509 - anInt4496;
                    anInt4496 = this.anInt4509;
                }
                if (this.anInt4507 < anInt4496 + n5) {
                    n5 = this.anInt4507 - anInt4496;
                }
                int n6 = anInt4496 + n4;
                for (int j = -n5; j < 0; ++j) {
                    this.anIntArray4504[n6++] = n;
                }
                n4 += this.anInt4505;
            }
        }
    }
    
    @Override
    final boolean method1823() {
        return false;
    }
    
    @Override
    final void K(final int[] array) {
        array[0] = this.anInt4509;
        array[1] = this.anInt4495;
        array[2] = this.anInt4507;
        array[3] = this.anInt4492;
    }
    
    @Override
    final Class111 method1793() {
        return this.method1921(Thread.currentThread()).aClass111_Sub2_1760;
    }
    
    @Override
    final void method1776() {
        if (this.aCanvas4479 != null) {
            this.anIntArray4504 = this.aClass98_Sub32_4478.anIntArray4108;
            this.anInt4505 = this.aClass98_Sub32_4478.anInt4105;
            this.anInt4480 = this.aClass98_Sub32_4478.anInt4110;
            this.aFloatArray4487 = this.aFloatArray4488;
            this.anInt4512 = this.anInt4496;
            this.anInt4485 = this.anInt4503;
        }
        else {
            this.anInt4505 = 1;
            this.anInt4480 = 1;
            this.anIntArray4504 = null;
            this.anInt4512 = 1;
            this.anInt4485 = 1;
            this.aFloatArray4487 = null;
        }
        this.aClass186_4499 = null;
        this.method1911();
    }
    
    @Override
    final Class62 method1799() {
        return new Class62(0, "Pure Java", 1, "CPU", 0L);
    }
    
    @Override
    final int[] Y() {
        return new int[] { this.anInt4510, this.anInt4511, this.anInt4514, this.anInt4490 };
    }
    
    @Override
    final boolean method1747() {
        return true;
    }
    
    @Override
    final void pa() {
        for (int i = 0; i < this.aClass235Array4483.length; ++i) {
            this.aClass235Array4483[i].anInt1763 = this.aClass235Array4483[i].anInt1755;
            this.aClass235Array4483[i].aBoolean1759 = false;
        }
    }
    
    @Override
    final void X(final int n) {
    }
    
    @Override
    final void method1741(final Canvas canvas, final int n, final int n2) {
        final Class98_Sub32 class98_Sub32 = (Class98_Sub32)this.aClass377_4476.method3990(canvas.hashCode(), -1);
        if (class98_Sub32 == null) {
            this.aClass377_4476.method3996(Class64_Sub23.method646(n2, n, canvas, (byte)(-128)), canvas.hashCode(), -1);
        }
        else if (class98_Sub32.anInt4105 != n || class98_Sub32.anInt4110 != n2) {
            this.method1782(canvas, n, n2);
        }
    }
    
    @Override
    final za method1762(final int n) {
        return null;
    }
    
    @Override
    final int E() {
        return 0;
    }
    
    @Override
    final s a(final int n, final int n2, final int[][] array, final int[][] array2, final int n3, final int n4, final int n5) {
        return new s_Sub3(this, n4, n5, n, n2, array, array2, n3);
    }
    
    final int[] method1919(final int n) {
        Class98_Sub2 class98_Sub2;
        synchronized (this.aClass79_4494) {
            class98_Sub2 = (Class98_Sub2)this.aClass79_4494.method802(-123, n);
            if (class98_Sub2 == null) {
                if (!super.aD938.method8(71, n)) {
                    return null;
                }
                final Class238 method11 = super.aD938.method11(n, -28755);
                final int n2 = (method11.aBoolean1822 || this.aBoolean4491) ? 64 : this.anInt4482;
                class98_Sub2 = new Class98_Sub2(n, n2, super.aD938.method9(n, (byte)(-125), n2, 0.7f, true, n2), method11.anInt1818 != 1);
                this.aClass79_4494.method805(n, class98_Sub2, (byte)(-80));
            }
        }
        class98_Sub2.aBoolean3817 = true;
        return class98_Sub2.method948();
    }
    
    final boolean method1920() {
        return this.aBoolean4472;
    }
    
    @Override
    final void method1750(final Canvas aCanvas4479) {
        if (aCanvas4479 != null) {
            final Class98_Sub32 aClass98_Sub32_4478 = (Class98_Sub32)this.aClass377_4476.method3990(aCanvas4479.hashCode(), -1);
            if (aClass98_Sub32_4478 != null) {
                this.aCanvas4479 = aCanvas4479;
                final Dimension size = aCanvas4479.getSize();
                this.anInt4474 = size.width;
                this.anInt4477 = size.height;
                this.aClass98_Sub32_4478 = aClass98_Sub32_4478;
                if (this.aClass186_4499 == null) {
                    this.anIntArray4504 = aClass98_Sub32_4478.anIntArray4108;
                    this.anInt4505 = aClass98_Sub32_4478.anInt4105;
                    this.anInt4480 = aClass98_Sub32_4478.anInt4110;
                    if (this.anInt4505 != this.anInt4512 || this.anInt4480 != this.anInt4485) {
                        final int anInt4505 = this.anInt4505;
                        this.anInt4512 = anInt4505;
                        this.anInt4496 = anInt4505;
                        final int anInt4506 = this.anInt4480;
                        this.anInt4485 = anInt4506;
                        this.anInt4503 = anInt4506;
                        final float[] array = new float[this.anInt4512 * this.anInt4485];
                        this.aFloatArray4487 = array;
                        this.aFloatArray4488 = array;
                    }
                    this.method1911();
                }
            }
        }
        else {
            this.aCanvas4479 = null;
            this.aClass98_Sub32_4478 = null;
            if (this.aClass186_4499 == null) {
                this.anIntArray4504 = null;
                final boolean b = true;
                this.anInt4480 = (b ? 1 : 0);
                this.anInt4505 = (b ? 1 : 0);
                final boolean b2 = true;
                this.anInt4485 = (b2 ? 1 : 0);
                this.anInt4512 = (b2 ? 1 : 0);
                this.method1911();
            }
        }
    }
    
    @Override
    final void method1761(final boolean b) {
    }
    
    @Override
    final void method1817() {
    }
    
    final Class235 method1921(final Runnable runnable) {
        for (int i = 0; i < this.anInt4508; ++i) {
            if (this.aClass235Array4483[i].aRunnable1752 == runnable) {
                return this.aClass235Array4483[i];
            }
        }
        return null;
    }
    
    @Override
    final int JA(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        int n7 = 0;
        float n8 = this.aClass111_Sub2_4513.aFloat4693 * n + this.aClass111_Sub2_4513.aFloat4698 * n2 + this.aClass111_Sub2_4513.aFloat4694 * n3 + this.aClass111_Sub2_4513.aFloat4689;
        if (n8 < 1.0f) {
            n8 = 1.0f;
        }
        float n9 = this.aClass111_Sub2_4513.aFloat4693 * n4 + this.aClass111_Sub2_4513.aFloat4698 * n5 + this.aClass111_Sub2_4513.aFloat4694 * n6 + this.aClass111_Sub2_4513.aFloat4689;
        if (n9 < 1.0f) {
            n9 = 1.0f;
        }
        if (n8 < this.anInt4502 && n9 < this.anInt4502) {
            n7 |= 0x10;
        }
        else if (n8 > this.anInt4484 && n9 > this.anInt4484) {
            n7 |= 0x20;
        }
        final int n10 = (int)(this.anInt4514 * (this.aClass111_Sub2_4513.aFloat4700 * n + this.aClass111_Sub2_4513.aFloat4699 * n2 + this.aClass111_Sub2_4513.aFloat4690 * n3 + this.aClass111_Sub2_4513.aFloat4697) / n8);
        final int n11 = (int)(this.anInt4514 * (this.aClass111_Sub2_4513.aFloat4700 * n4 + this.aClass111_Sub2_4513.aFloat4699 * n5 + this.aClass111_Sub2_4513.aFloat4690 * n6 + this.aClass111_Sub2_4513.aFloat4697) / n9);
        if (n10 < this.anInt4486 && n11 < this.anInt4486) {
            n7 |= 0x1;
        }
        else if (n10 > this.anInt4517 && n11 > this.anInt4517) {
            n7 |= 0x2;
        }
        final int n12 = (int)(this.anInt4490 * (this.aClass111_Sub2_4513.aFloat4692 * n + this.aClass111_Sub2_4513.aFloat4688 * n2 + this.aClass111_Sub2_4513.aFloat4696 * n3 + this.aClass111_Sub2_4513.aFloat4691) / n8);
        final int n13 = (int)(this.anInt4490 * (this.aClass111_Sub2_4513.aFloat4692 * n4 + this.aClass111_Sub2_4513.aFloat4688 * n5 + this.aClass111_Sub2_4513.aFloat4696 * n6 + this.aClass111_Sub2_4513.aFloat4691) / n9);
        if (n12 < this.anInt4518 && n13 < this.anInt4518) {
            n7 |= 0x4;
        }
        else if (n12 > this.anInt4506 && n13 > this.anInt4506) {
            n7 |= 0x8;
        }
        return n7;
    }
    
    @Override
    final void a(final Class111 class111) {
        this.aClass111_Sub2_4513 = (Class111_Sub2)class111;
    }
    
    final boolean method1922(final int n) {
        return super.aD938.method11(n, -28755).aBoolean1826 || super.aD938.method11(n, -28755).aBoolean1819;
    }
    
    @Override
    final Class48 method1769(final Class48 class48, final Class48 class49, final float n, final Class48 class50) {
        return null;
    }
    
    @Override
    final void T(final int anInt4509, final int anInt4510, final int anInt4511, final int anInt4512) {
        if (this.anInt4509 < anInt4509) {
            this.anInt4509 = anInt4509;
        }
        if (this.anInt4495 < anInt4510) {
            this.anInt4495 = anInt4510;
        }
        if (this.anInt4507 > anInt4511) {
            this.anInt4507 = anInt4511;
        }
        if (this.anInt4492 > anInt4512) {
            this.anInt4492 = anInt4512;
        }
        this.method1913();
    }
    
    @Override
    final void method1811(int i, int j, int n, int n2, int n3, final int n4, int n5, int n6, int n7) {
        n -= i;
        n2 -= j;
        if (n2 == 0) {
            if (n >= 0) {
                this.method1924(i, j, n + 1, n3, n4, n5, n6, n7);
            }
            else {
                final int n8 = n5 + n6;
                n7 %= n8;
                n7 = n8 + n5 - n7 - (-n + 1) % n8;
                n7 %= n8;
                if (n7 < 0) {
                    n7 += n8;
                }
                this.method1924(i + n, j, -n + 1, n3, n4, n5, n6, n7);
            }
        }
        else if (n == 0) {
            if (n2 >= 0) {
                this.method1917(i, j, n2 + 1, n3, n4, n5, n6, n7);
            }
            else {
                final int n9 = n5 + n6;
                n7 %= n9;
                n7 = n9 + n5 - n7 - (-n2 + 1) % n9;
                n7 %= n9;
                if (n7 < 0) {
                    n7 += n9;
                }
                this.method1917(i, j + n2, -n2 + 1, n3, n4, n5, n6, n7);
            }
        }
        else {
            n7 <<= 8;
            n5 <<= 8;
            n6 <<= 8;
            final int n10 = n5 + n6;
            n7 %= n10;
            if (n + n2 < 0) {
                n7 = n10 + n5 - n7 - (int)(Math.sqrt(n * n + n2 * n2) * 256.0) % n10;
                n7 %= n10;
                if (n7 < 0) {
                    n7 += n10;
                }
                i += n;
                n = -n;
                j += n2;
                n2 = -n2;
            }
            if (n > n2) {
                j <<= 16;
                j += 32768;
                n2 <<= 16;
                final int n11 = (int)Math.floor(n2 / n + 0.5);
                n += i;
                final int n12 = n3 >>> 24;
                final int n13 = (int)Math.sqrt(65536 + (n11 >> 8) * (n11 >> 8));
                if (n4 == 0 || (n4 == 1 && n12 == 255)) {
                    while (i <= n) {
                        final int n14 = j >> 16;
                        if (i >= this.anInt4509 && i < this.anInt4507 && n14 >= this.anInt4495 && n14 < this.anInt4492 && n7 < n5) {
                            this.anIntArray4504[i + n14 * this.anInt4505] = n3;
                        }
                        j += n11;
                        ++i;
                        n7 += n13;
                        n7 %= n10;
                    }
                    return;
                }
                if (n4 == 1) {
                    n3 = ((n3 & 0xFF00FF) * n12 >> 8 & 0xFF00FF) + ((n3 & 0xFF00) * n12 >> 8 & 0xFF00) + (n12 << 24);
                    final int n15 = 256 - n12;
                    while (i <= n) {
                        final int n16 = j >> 16;
                        if (i >= this.anInt4509 && i < this.anInt4507 && n16 >= this.anInt4495 && n16 < this.anInt4492 && n7 < n5) {
                            final int n17 = i + n16 * this.anInt4505;
                            final int n18 = this.anIntArray4504[n17];
                            this.anIntArray4504[n17] = n3 + (((n18 & 0xFF00FF) * n15 >> 8 & 0xFF00FF) + ((n18 & 0xFF00) * n15 >> 8 & 0xFF00));
                        }
                        j += n11;
                        ++i;
                        n7 += n13;
                        n7 %= n10;
                    }
                    return;
                }
                if (n4 == 2) {
                    while (i <= n) {
                        final int n19 = j >> 16;
                        if (i >= this.anInt4509 && i < this.anInt4507 && n19 >= this.anInt4495 && n19 < this.anInt4492 && n7 < n5) {
                            final int n20 = i + n19 * this.anInt4505;
                            final int n21 = this.anIntArray4504[n20];
                            final int n22 = n3 + n21;
                            final int n23 = (n3 & 0xFF00FF) + (n21 & 0xFF00FF);
                            final int n24 = (n23 & 0x1000100) + (n22 - n23 & 0x10000);
                            this.anIntArray4504[n20] = (n22 - n24 | n24 - (n24 >>> 8));
                        }
                        j += n11;
                        ++i;
                        n7 += n13;
                        n7 %= n10;
                    }
                    return;
                }
                throw new IllegalArgumentException();
            }
            else {
                i <<= 16;
                i += 32768;
                n <<= 16;
                final int n25 = (int)Math.floor(n / n2 + 0.5);
                n2 += j;
                final int n26 = n3 >>> 24;
                final int n27 = (int)Math.sqrt(65536 + (n25 >> 8) * (n25 >> 8));
                if (n4 == 0 || (n4 == 1 && n26 == 255)) {
                    while (j <= n2) {
                        final int n28 = i >> 16;
                        if (j >= this.anInt4495 && j < this.anInt4492 && n28 >= this.anInt4509 && n28 < this.anInt4507 && n7 < n5) {
                            this.anIntArray4504[n28 + j * this.anInt4505] = n3;
                        }
                        i += n25;
                        ++j;
                        n7 += n27;
                        n7 %= n10;
                    }
                }
                else if (n4 == 1) {
                    n3 = ((n3 & 0xFF00FF) * n26 >> 8 & 0xFF00FF) + ((n3 & 0xFF00) * n26 >> 8 & 0xFF00) + (n26 << 24);
                    final int n29 = 256 - n26;
                    while (j <= n2) {
                        final int n30 = i >> 16;
                        if (j >= this.anInt4495 && j < this.anInt4492 && n30 >= this.anInt4509 && n30 < this.anInt4507 && n7 < n5) {
                            final int n31 = this.anIntArray4504[n30 + j * this.anInt4505];
                            this.anIntArray4504[n30 + j * this.anInt4505] = n3 + (((n31 & 0xFF00FF) * n29 >> 8 & 0xFF00FF) + ((n31 & 0xFF00) * n29 >> 8 & 0xFF00));
                        }
                        i += n25;
                        ++j;
                        n7 += n27;
                        n7 %= n10;
                    }
                }
                else {
                    if (n4 != 2) {
                        throw new IllegalArgumentException();
                    }
                    while (j <= n2) {
                        final int n32 = i >> 16;
                        if (j >= this.anInt4495 && j < this.anInt4492 && n32 >= this.anInt4509 && n32 < this.anInt4507 && n7 < n5) {
                            final int n33 = n32 + j * this.anInt4505;
                            final int n34 = this.anIntArray4504[n33];
                            final int n35 = n3 + n34;
                            final int n36 = (n3 & 0xFF00FF) + (n34 & 0xFF00FF);
                            final int n37 = (n36 & 0x1000100) + (n35 - n36 & 0x10000);
                            this.anIntArray4504[n33] = (n35 - n37 | n37 - (n37 >>> 8));
                        }
                        i += n25;
                        ++j;
                        n7 += n27;
                        n7 %= n10;
                    }
                }
            }
        }
    }
    
    final void method1923(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int anInt4520, final int n7, final int n8, final int n9) {
        if (n4 != 0 && n5 != 0) {
            if (anInt4520 != 65535 && !super.aD938.method11(anInt4520, -28755).aBoolean1825) {
                if (this.anInt4520 != anInt4520) {
                    Class332 method1748 = (Class332)this.aClass79_4497.method802(-120, anInt4520);
                    if (method1748 == null) {
                        final int[] method1749 = this.method1915(anInt4520);
                        if (method1749 == null) {
                            return;
                        }
                        final int n10 = this.method1925(anInt4520) ? 64 : this.anInt4482;
                        method1748 = this.method1748(-7962, 0, n10, n10, method1749, n10);
                        this.aClass79_4497.method805(anInt4520, method1748, (byte)(-80));
                    }
                    this.anInt4520 = anInt4520;
                    this.aClass332_4519 = method1748;
                }
                ((Class332_Sub3)this.aClass332_4519).method3757(n - n4, n2 - n5, n3, n4 << 1, n5 << 1, n8, n7, n9, 1);
            }
            else {
                this.method1916(n, n2, n3, n4, n7, n9);
            }
        }
    }
    
    private final void method1924(final int n, final int n2, final int n3, int n4, final int n5, final int n6, final int n7, final int n8) {
        if (n2 >= this.anInt4495 && n2 < this.anInt4492) {
            final int n9 = n + n2 * this.anInt4505;
            final int n10 = n4 >>> 24;
            final int n11 = n6 + n7;
            int n12 = n8 % n11;
            if (n5 == 0 || (n5 == 1 && n10 == 255)) {
                for (int i = 0; i < n3; ++i, n12 = ++n12 % n11) {
                    if (n + i >= this.anInt4509 && n + i < this.anInt4507 && n12 < n6) {
                        this.anIntArray4504[n9 + i] = n4;
                    }
                }
            }
            else if (n5 == 1) {
                n4 = ((n4 & 0xFF00FF) * n10 >> 8 & 0xFF00FF) + ((n4 & 0xFF00) * n10 >> 8 & 0xFF00) + (n10 << 24);
                final int n13 = 256 - n10;
                for (int j = 0; j < n3; ++j, n12 = ++n12 % n11) {
                    if (n + j >= this.anInt4509 && n + j < this.anInt4507 && n12 < n6) {
                        final int n14 = this.anIntArray4504[n9 + j];
                        this.anIntArray4504[n9 + j] = n4 + (((n14 & 0xFF00FF) * n13 >> 8 & 0xFF00FF) + ((n14 & 0xFF00) * n13 >> 8 & 0xFF00));
                    }
                }
            }
            else {
                if (n5 != 2) {
                    throw new IllegalArgumentException();
                }
                for (int k = 0; k < n3; ++k, n12 = ++n12 % n11) {
                    if (n + k >= this.anInt4509 && n + k < this.anInt4507 && n12 < n6) {
                        final int n15 = this.anIntArray4504[n9 + k];
                        final int n16 = n4 + n15;
                        final int n17 = (n4 & 0xFF00FF) + (n15 & 0xFF00FF);
                        final int n18 = (n17 & 0x1000100) + (n16 - n17 & 0x10000);
                        this.anIntArray4504[n9 + k] = (n16 - n18 | n18 - (n18 >>> 8));
                    }
                }
            }
        }
    }
    
    @Override
    final void EA(final int anInt1754, final int anInt1755, final int anInt1756, final int n) {
        final Class235 method1921 = this.method1921(Thread.currentThread());
        method1921.anInt1754 = anInt1754;
        method1921.anInt1763 = anInt1755;
        method1921.anInt1757 = anInt1756;
    }
    
    @Override
    final void a(int i, int j, int n, int n2, int n3, final int n4, final aa aa, final int n5, final int n6) {
        final aa_Sub1 aa_Sub1 = (aa_Sub1)aa;
        final int[] anIntArray3555 = aa_Sub1.anIntArray3555;
        final int[] anIntArray3556 = aa_Sub1.anIntArray3557;
        final int n7 = (this.anInt4495 > n6) ? this.anInt4495 : n6;
        final int n8 = (this.anInt4492 < n6 + anIntArray3555.length) ? this.anInt4492 : (n6 + anIntArray3555.length);
        n -= i;
        n2 -= j;
        if (n + n2 < 0) {
            i += n;
            n = -n;
            j += n2;
            n2 = -n2;
        }
        if (n <= n2) {
            i <<= 16;
            i += 32768;
            n <<= 16;
            final int n9 = (int)Math.floor(n / n2 + 0.5);
            n2 += j;
            if (j < n7) {
                i += n9 * (n7 - j);
                j = n7;
            }
            if (n2 >= n8) {
                n2 = n8 - 1;
            }
            final int n10 = n3 >>> 24;
            if (n4 == 0 || (n4 == 1 && n10 == 255)) {
                while (j <= n2) {
                    final int n11 = i >> 16;
                    final int n12 = j - n6;
                    final int n13 = n5 + anIntArray3555[n12];
                    if (n11 >= this.anInt4509 && n11 < this.anInt4507 && n11 >= n13 && n11 < n13 + anIntArray3556[n12]) {
                        this.anIntArray4504[n11 + j * this.anInt4505] = n3;
                    }
                    i += n9;
                    ++j;
                }
            }
            else if (n4 == 1) {
                n3 = ((n3 & 0xFF00FF) * n10 >> 8 & 0xFF00FF) + ((n3 & 0xFF00) * n10 >> 8 & 0xFF00) + (n10 << 24);
                final int n14 = 256 - n10;
                while (j <= n2) {
                    final int n15 = i >> 16;
                    final int n16 = j - n6;
                    final int n17 = n5 + anIntArray3555[n16];
                    if (n15 >= this.anInt4509 && n15 < this.anInt4507 && n15 >= n17 && n15 < n17 + anIntArray3556[n16]) {
                        final int n18 = this.anIntArray4504[n15 + j * this.anInt4505];
                        this.anIntArray4504[n15 + j * this.anInt4505] = n3 + (((n18 & 0xFF00FF) * n14 >> 8 & 0xFF00FF) + ((n18 & 0xFF00) * n14 >> 8 & 0xFF00));
                    }
                    i += n9;
                    ++j;
                }
            }
            else {
                if (n4 != 2) {
                    throw new IllegalArgumentException();
                }
                while (j <= n2) {
                    final int n19 = i >> 16;
                    final int n20 = j - n6;
                    final int n21 = n5 + anIntArray3555[n20];
                    if (n19 >= this.anInt4509 && n19 < this.anInt4507 && n19 >= n21 && n19 < n21 + anIntArray3556[n20]) {
                        final int n22 = n19 + j * this.anInt4505;
                        final int n23 = this.anIntArray4504[n22];
                        final int n24 = n3 + n23;
                        final int n25 = (n3 & 0xFF00FF) + (n23 & 0xFF00FF);
                        final int n26 = (n25 & 0x1000100) + (n24 - n25 & 0x10000);
                        this.anIntArray4504[n22] = (n24 - n26 | n26 - (n26 >>> 8));
                    }
                    i += n9;
                    ++j;
                }
            }
            return;
        }
        j <<= 16;
        j += 32768;
        n2 <<= 16;
        final int n27 = (int)Math.floor(n2 / n + 0.5);
        n += i;
        if (i < this.anInt4509) {
            j += n27 * (this.anInt4509 - i);
            i = this.anInt4509;
        }
        if (n >= this.anInt4507) {
            n = this.anInt4507 - 1;
        }
        final int n28 = n3 >>> 24;
        if (n4 == 0 || (n4 == 1 && n28 == 255)) {
            while (i <= n) {
                final int n29 = j >> 16;
                final int n30 = n29 - n6;
                if (n29 >= n7 && n29 < n8) {
                    final int n31 = n5 + anIntArray3555[n30];
                    if (i >= n31 && i < n31 + anIntArray3556[n30]) {
                        this.anIntArray4504[i + n29 * this.anInt4505] = n3;
                    }
                }
                j += n27;
                ++i;
            }
            return;
        }
        if (n4 == 1) {
            n3 = ((n3 & 0xFF00FF) * n28 >> 8 & 0xFF00FF) + ((n3 & 0xFF00) * n28 >> 8 & 0xFF00) + (n28 << 24);
            final int n32 = 256 - n28;
            while (i <= n) {
                final int n33 = j >> 16;
                final int n34 = n33 - n6;
                if (n33 >= n7 && n33 < n8) {
                    final int n35 = n5 + anIntArray3555[n34];
                    if (i >= n35 && i < n35 + anIntArray3556[n34]) {
                        final int n36 = i + n33 * this.anInt4505;
                        final int n37 = this.anIntArray4504[n36];
                        this.anIntArray4504[n36] = n3 + (((n37 & 0xFF00FF) * n32 >> 8 & 0xFF00FF) + ((n37 & 0xFF00) * n32 >> 8 & 0xFF00));
                    }
                }
                j += n27;
                ++i;
            }
            return;
        }
        if (n4 == 2) {
            while (i <= n) {
                final int n38 = j >> 16;
                final int n39 = n38 - n6;
                if (n38 >= n7 && n38 < n8) {
                    final int n40 = n5 + anIntArray3555[n39];
                    if (i >= n40 && i < n40 + anIntArray3556[n39]) {
                        final int n41 = i + n38 * this.anInt4505;
                        final int n42 = this.anIntArray4504[n41];
                        final int n43 = n3 + n42;
                        final int n44 = (n3 & 0xFF00FF) + (n42 & 0xFF00FF);
                        final int n45 = (n44 & 0x1000100) + (n43 - n44 & 0x10000);
                        this.anIntArray4504[n41] = (n43 - n45 | n45 - (n45 >>> 8));
                    }
                }
                j += n27;
                ++i;
            }
            return;
        }
        throw new IllegalArgumentException();
    }
    
    final boolean method1925(final int n) {
        return this.aBoolean4491 || super.aD938.method11(n, -28755).aBoolean1822;
    }
    
    @Override
    final int XA() {
        return this.anInt4484;
    }
    
    @Override
    final void method1785(final Class242 class242, final int n) {
        final Class235 method1921 = this.method1921(Thread.currentThread());
        for (Class246_Sub4 aClass246_Sub4_3028 = class242.aClass358_1850.aClass246_Sub4_3028, class246_Sub4 = aClass246_Sub4_3028.aClass246_Sub4_5091; class246_Sub4 != aClass246_Sub4_3028; class246_Sub4 = class246_Sub4.aClass246_Sub4_5091) {
            final Class246_Sub4_Sub2 class246_Sub4_Sub2 = (Class246_Sub4_Sub2)class246_Sub4;
            final int n2 = class246_Sub4_Sub2.anInt6176 >> 12;
            final int n3 = class246_Sub4_Sub2.anInt6177 >> 12;
            final int n4 = class246_Sub4_Sub2.anInt6175 >> 12;
            float n5 = this.aClass111_Sub2_4513.aFloat4689 + (this.aClass111_Sub2_4513.aFloat4693 * n2 + this.aClass111_Sub2_4513.aFloat4698 * n3 + this.aClass111_Sub2_4513.aFloat4694 * n4);
            if (n5 >= this.anInt4502 && n5 <= method1921.anInt1761) {
                final int n6 = this.anInt4510 + (int)(this.anInt4514 * (this.aClass111_Sub2_4513.aFloat4697 + (this.aClass111_Sub2_4513.aFloat4700 * n2 + this.aClass111_Sub2_4513.aFloat4699 * n3 + this.aClass111_Sub2_4513.aFloat4690 * n4)) / n);
                final int n7 = this.anInt4511 + (int)(this.anInt4490 * (this.aClass111_Sub2_4513.aFloat4691 + (this.aClass111_Sub2_4513.aFloat4692 * n2 + this.aClass111_Sub2_4513.aFloat4688 * n3 + this.aClass111_Sub2_4513.aFloat4696 * n4)) / n);
                if (n6 >= this.anInt4509 && n6 <= this.anInt4507 && n7 >= this.anInt4495 && n7 <= this.anInt4492) {
                    if (n5 == 0.0f) {
                        n5 = 1.0f;
                    }
                    this.method1914(class246_Sub4_Sub2, n6, n7, (int)n5, (class246_Sub4_Sub2.anInt6179 * this.anInt4514 >> 12) / n);
                }
            }
        }
    }
    
    @Override
    final int[] na(final int n, final int n2, final int n3, final int n4) {
        final int[] array = new int[n3 * n4];
        int n5 = 0;
        for (int i = 0; i < n4; ++i) {
            final int n6 = (n2 + i) * this.anInt4505 + n;
            for (int j = 0; j < n3; ++j) {
                array[n5++] = this.anIntArray4504[n6 + j];
            }
        }
        return array;
    }
    
    @Override
    final void b(final int n, final int n2, final int n3, final int n4, final double n5) {
        final int n6 = this.anInt4512 - n3;
        int n7 = n2 * this.anInt4512 + n;
        final float[] aFloatArray4487 = this.aFloatArray4487;
        for (int i = 0; i < n4; ++i, n7 += n6) {
            for (int j = 0; j < n3; ++j, ++n7) {
                final float n8 = aFloatArray4487[n7];
                if (n8 != 2.14748365E9f) {
                    aFloatArray4487[n7] = (float)(n8 + n5);
                }
            }
        }
    }
    
    @Override
    final Class332 method1797(final int n, final int n2, final int n3, final int n4, final boolean b) {
        final int[] array = new int[n3 * n4];
        int n5 = n2 * this.anInt4505 + n;
        final int n6 = this.anInt4505 - n3;
        for (int i = 0; i < n4; ++i) {
            final int n7 = i * n3;
            for (int j = 0; j < n3; ++j) {
                array[n7 + j] = this.anIntArray4504[n5++];
            }
            n5 += n6;
        }
        if (b) {
            return new Class332_Sub3_Sub1(this, array, n3, n4);
        }
        return new Class332_Sub3_Sub2(this, array, n3, n4);
    }
    
    @Override
    final void la() {
        this.anInt4509 = 0;
        this.anInt4495 = 0;
        this.anInt4507 = this.anInt4505;
        this.anInt4492 = this.anInt4480;
        this.method1913();
    }
    
    final int method1926(final int n) {
        return super.aD938.method11(n, -28755).aShort1831 & 0xFFFF;
    }
    
    @Override
    final void GA(final int n) {
        this.aa(0, 0, this.anInt4505, this.anInt4480, n, 0);
    }
    
    @Override
    final boolean method1810() {
        return true;
    }
    
    @Override
    final int i() {
        return this.anInt4502;
    }
    
    @Override
    final void KA(int anInt4509, int anInt4510, int anInt4511, int anInt4512) {
        if (anInt4509 < 0) {
            anInt4509 = 0;
        }
        if (anInt4510 < 0) {
            anInt4510 = 0;
        }
        if (anInt4511 > this.anInt4505) {
            anInt4511 = this.anInt4505;
        }
        if (anInt4512 > this.anInt4480) {
            anInt4512 = this.anInt4480;
        }
        this.anInt4509 = anInt4509;
        this.anInt4507 = anInt4511;
        this.anInt4495 = anInt4510;
        this.anInt4492 = anInt4512;
        this.method1913();
    }
    
    @Override
    final Class111 method1752() {
        return this.aClass111_Sub2_4513;
    }
    
    @Override
    final void a(final Rectangle[] array, final int n, final int n2, final int n3) throws Exception_Sub1 {
        if (this.aCanvas4479 == null || this.aClass98_Sub32_4478 == null) {
            throw new IllegalStateException("off");
        }
        try {
            final Graphics graphics = this.aCanvas4479.getGraphics();
            for (final Rectangle rectangle : array) {
                if (rectangle.x + n2 <= this.anInt4505 && rectangle.y + n3 <= this.anInt4480 && rectangle.x + n2 + rectangle.width > 0 && rectangle.y + n3 + rectangle.height > 0) {
                    this.aClass98_Sub32_4478.method1434(graphics, rectangle.x, (byte)(-125), rectangle.x + n2, rectangle.width, rectangle.y + n3, rectangle.height, rectangle.y);
                }
            }
        }
        catch (Exception ex) {
            this.aCanvas4479.repaint();
        }
    }
    
    @Override
    final void method1774(final int n) {
        this.aClass235Array4483[n].method2889((byte)34, null);
    }
    
    @Override
    final void method1751(int n, int n2, final int n3, int n4, int n5, final int n6, int n7, int n8, final int n9, final int n10, final int n11, final int n12, final int n13) {
        final Class12 aClass12_1767 = this.method1921(Thread.currentThread()).aClass12_1767;
        aClass12_1767.aBoolean134 = false;
        n -= this.anInt4486;
        n4 -= this.anInt4486;
        n7 -= this.anInt4486;
        n2 -= this.anInt4518;
        n5 -= this.anInt4518;
        n8 -= this.anInt4518;
        aClass12_1767.aBoolean135 = (n < 0 || n > aClass12_1767.anInt141 || n4 < 0 || n4 > aClass12_1767.anInt141 || n7 < 0 || n7 > aClass12_1767.anInt141);
        final int n14 = n10 >>> 24;
        if (n13 == 0 || (n13 == 1 && n14 == 255)) {
            aClass12_1767.anInt137 = 0;
            aClass12_1767.aBoolean130 = false;
            aClass12_1767.method206(n2, n5, n8, n, n4, n7, n3, n6, n9, n10, n11, n12);
        }
        else if (n13 == 1) {
            aClass12_1767.anInt137 = 255 - n14;
            aClass12_1767.aBoolean130 = false;
            aClass12_1767.method206(n2, n5, n8, n, n4, n7, n3, n6, n9, n10, n11, n12);
        }
        else {
            if (n13 != 2) {
                throw new IllegalArgumentException();
            }
            aClass12_1767.anInt137 = 128;
            aClass12_1767.aBoolean130 = true;
            aClass12_1767.method206(n2, n5, n8, n, n4, n7, n3, n6, n9, n10, n11, n12);
        }
        aClass12_1767.aBoolean134 = true;
    }
    
    @Override
    final void method1779(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.U(n, n2, n3, n5, n6);
        this.U(n, n2 + n4 - 1, n3, n5, n6);
        this.P(n, n2 + 1, n4 - 2, n5, n6);
        this.P(n + n3 - 1, n2 + 1, n4 - 2, n5, n6);
    }
    
    @Override
    final void da(final int n, final int n2, final int n3, final int[] array) {
        final float n4 = this.aClass111_Sub2_4513.aFloat4689 + (this.aClass111_Sub2_4513.aFloat4693 * n + this.aClass111_Sub2_4513.aFloat4698 * n2 + this.aClass111_Sub2_4513.aFloat4694 * n3);
        if (n4 < this.anInt4502 || n4 > this.anInt4484) {
            final int n5 = 0;
            final int n6 = 1;
            final int n7 = 2;
            final int n8 = -1;
            array[n7] = n8;
            array[n5] = (array[n6] = n8);
        }
        else {
            final int n9 = (int)(this.anInt4514 * (this.aClass111_Sub2_4513.aFloat4697 + (this.aClass111_Sub2_4513.aFloat4700 * n + this.aClass111_Sub2_4513.aFloat4699 * n2 + this.aClass111_Sub2_4513.aFloat4690 * n3)) / n4);
            final int n10 = (int)(this.anInt4490 * (this.aClass111_Sub2_4513.aFloat4691 + (this.aClass111_Sub2_4513.aFloat4692 * n + this.aClass111_Sub2_4513.aFloat4688 * n2 + this.aClass111_Sub2_4513.aFloat4696 * n3)) / n4);
            if (n9 >= this.anInt4486 && n9 <= this.anInt4517 && n10 >= this.anInt4518 && n10 <= this.anInt4506) {
                array[0] = n9 - this.anInt4486;
                array[1] = n10 - this.anInt4518;
                array[2] = (int)n4;
            }
            else {
                final int n11 = 0;
                final int n12 = 1;
                final int n13 = 2;
                final int n14 = -1;
                array[n13] = n14;
                array[n11] = (array[n12] = n14);
            }
        }
    }
    
    @Override
    final void method1820(final Class242 class242) {
        final Class235 method1921 = this.method1921(Thread.currentThread());
        for (Class246_Sub4 aClass246_Sub4_3028 = class242.aClass358_1850.aClass246_Sub4_3028, class246_Sub4 = aClass246_Sub4_3028.aClass246_Sub4_5091; class246_Sub4 != aClass246_Sub4_3028; class246_Sub4 = class246_Sub4.aClass246_Sub4_5091) {
            final Class246_Sub4_Sub2 class246_Sub4_Sub2 = (Class246_Sub4_Sub2)class246_Sub4;
            final int n = class246_Sub4_Sub2.anInt6176 >> 12;
            final int n2 = class246_Sub4_Sub2.anInt6177 >> 12;
            final int n3 = class246_Sub4_Sub2.anInt6175 >> 12;
            float n4 = this.aClass111_Sub2_4513.aFloat4689 + (this.aClass111_Sub2_4513.aFloat4693 * n + this.aClass111_Sub2_4513.aFloat4698 * n2 + this.aClass111_Sub2_4513.aFloat4694 * n3);
            if (n4 >= this.anInt4502 && n4 <= method1921.anInt1761) {
                final int n5 = this.anInt4510 + (int)(this.anInt4514 * (this.aClass111_Sub2_4513.aFloat4697 + (this.aClass111_Sub2_4513.aFloat4700 * n + this.aClass111_Sub2_4513.aFloat4699 * n2 + this.aClass111_Sub2_4513.aFloat4690 * n3)) / n4);
                final int n6 = this.anInt4511 + (int)(this.anInt4490 * (this.aClass111_Sub2_4513.aFloat4691 + (this.aClass111_Sub2_4513.aFloat4692 * n + this.aClass111_Sub2_4513.aFloat4688 * n2 + this.aClass111_Sub2_4513.aFloat4696 * n3)) / n4);
                if (n5 >= this.anInt4509 && n5 <= this.anInt4507 && n6 >= this.anInt4495 && n6 <= this.anInt4492) {
                    if (n4 == 0.0f) {
                        n4 = 1.0f;
                    }
                    this.method1914(class246_Sub4_Sub2, n5, n6, (int)n4, (int)((class246_Sub4_Sub2.anInt6179 * this.anInt4514 >> 12) / n4));
                }
            }
        }
    }
    
    @Override
    final int c(int n, final int n2) {
        n |= 0x20800;
        return (n & n2) ^ n2;
    }
    
    @Override
    final void aa(int anInt4509, int anInt4510, int n, int n2, int n3, final int n4) {
        if (anInt4509 < this.anInt4509) {
            n -= this.anInt4509 - anInt4509;
            anInt4509 = this.anInt4509;
        }
        if (anInt4510 < this.anInt4495) {
            n2 -= this.anInt4495 - anInt4510;
            anInt4510 = this.anInt4495;
        }
        if (anInt4509 + n > this.anInt4507) {
            n = this.anInt4507 - anInt4509;
        }
        if (anInt4510 + n2 > this.anInt4492) {
            n2 = this.anInt4492 - anInt4510;
        }
        if (n > 0 && n2 > 0 && anInt4509 <= this.anInt4507 && anInt4510 <= this.anInt4492) {
            final int n5 = this.anInt4505 - n;
            int n6 = anInt4509 + anInt4510 * this.anInt4505;
            final int n7 = n3 >>> 24;
            if (n4 == 0 || (n4 == 1 && n7 == 255)) {
                final int n8 = n >> 3;
                final int n9 = n & 0x7;
                n = n6 - 1;
                for (int i = -n2; i < 0; ++i) {
                    if (n8 > 0) {
                        anInt4509 = n8;
                        do {
                            this.anIntArray4504[++n] = n3;
                            this.anIntArray4504[++n] = n3;
                            this.anIntArray4504[++n] = n3;
                            this.anIntArray4504[++n] = n3;
                            this.anIntArray4504[++n] = n3;
                            this.anIntArray4504[++n] = n3;
                            this.anIntArray4504[++n] = n3;
                            this.anIntArray4504[++n] = n3;
                        } while (--anInt4509 > 0);
                    }
                    if (n9 > 0) {
                        anInt4509 = n9;
                        do {
                            this.anIntArray4504[++n] = n3;
                        } while (--anInt4509 > 0);
                    }
                    n += n5;
                }
            }
            else if (n4 == 1) {
                n3 = ((n3 & 0xFF00FF) * n7 >> 8 & 0xFF00FF) + (((n3 & 0xFF00FF00) >>> 8) * n7 & 0xFF00FF00);
                final int n10 = 256 - n7;
                for (int j = 0; j < n2; ++j) {
                    for (int k = -n; k < 0; ++k) {
                        final int n11 = this.anIntArray4504[n6];
                        this.anIntArray4504[n6++] = n3 + (((n11 & 0xFF00FF) * n10 >> 8 & 0xFF00FF) + (((n11 & 0xFF00FF00) >>> 8) * n10 & 0xFF00FF00));
                    }
                    n6 += n5;
                }
            }
            else {
                if (n4 != 2) {
                    throw new IllegalArgumentException();
                }
                for (int l = 0; l < n2; ++l) {
                    for (int n12 = -n; n12 < 0; ++n12) {
                        final int n13 = this.anIntArray4504[n6];
                        final int n14 = n3 + n13;
                        final int n15 = (n3 & 0xFF00FF) + (n13 & 0xFF00FF);
                        final int n16 = (n15 & 0x1000100) + (n14 - n15 & 0x10000);
                        this.anIntArray4504[n6++] = (n14 - n16 | n16 - (n16 >>> 8));
                    }
                    n6 += n5;
                }
            }
        }
    }
    
    @Override
    final Class48 method1803(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        return null;
    }
    
    @Override
    final aa method1772(final int n, final int n2, final int[] array, final int[] array2) {
        return new aa_Sub1(n, n2, array, array2);
    }
    
    @Override
    final void method1825() {
    }
    
    @Override
    final void method1764(final int n, final int n2) throws Exception_Sub1 {
        if (this.aCanvas4479 == null || this.aClass98_Sub32_4478 == null) {
            throw new IllegalStateException("off");
        }
        try {
            this.aClass98_Sub32_4478.method1434(this.aCanvas4479.getGraphics(), 0, (byte)(-125), n, this.anInt4474, n2, this.anInt4477, 0);
        }
        catch (Exception ex) {
            this.aCanvas4479.repaint();
        }
    }
    
    @Override
    final void Q(final int n, final int n2, int n3, int n4, final int n5, final int n6, final byte[] array, final int n7, final int n8) {
        if (n3 > 0 && n4 > 0) {
            int n9 = 0;
            int n10 = 0;
            final int n11 = (n7 << 16) / n3;
            final int n12 = (array.length / n7 << 16) / n4;
            int n13 = n + n2 * this.anInt4505;
            int n14 = this.anInt4505 - n3;
            if (n2 + n4 > this.anInt4492) {
                n4 -= n2 + n4 - this.anInt4492;
            }
            if (n2 < this.anInt4495) {
                final int n15 = this.anInt4495 - n2;
                n4 -= n15;
                n13 += n15 * this.anInt4505;
                n10 += n12 * n15;
            }
            if (n + n3 > this.anInt4507) {
                final int n16 = n + n3 - this.anInt4507;
                n3 -= n16;
                n14 += n16;
            }
            if (n < this.anInt4509) {
                final int n17 = this.anInt4509 - n;
                n3 -= n17;
                n13 += n17;
                n9 += n11 * n17;
                n14 += n17;
            }
            final int n18 = n5 >>> 24;
            final int n19 = n6 >>> 24;
            if (n8 == 0 || (n8 == 1 && n18 == 255 && n19 == 255)) {
                final int n20 = n9;
                for (int i = -n4; i < 0; ++i) {
                    final int n21 = (n10 >> 16) * n7;
                    for (int j = -n3; j < 0; ++j) {
                        if (array[(n9 >> 16) + n21] != 0) {
                            this.anIntArray4504[n13++] = n6;
                        }
                        else {
                            this.anIntArray4504[n13++] = n5;
                        }
                        n9 += n11;
                    }
                    n10 += n12;
                    n9 = n20;
                    n13 += n14;
                }
            }
            else if (n8 == 1) {
                final int n22 = n9;
                for (int k = -n4; k < 0; ++k) {
                    final int n23 = (n10 >> 16) * n7;
                    for (int l = -n3; l < 0; ++l) {
                        int n24 = n5;
                        if (array[(n9 >> 16) + n23] != 0) {
                            n24 = n6;
                        }
                        final int n25 = n24 >>> 24;
                        final int n26 = 255 - n25;
                        final int n27 = this.anIntArray4504[n13];
                        this.anIntArray4504[n13++] = ((n24 & 0xFF00FF) * n25 + (n27 & 0xFF00FF) * n26 & 0xFF00FF00) + ((n24 & 0xFF00) * n25 + (n27 & 0xFF00) * n26 & 0xFF0000) >> 8;
                        n9 += n11;
                    }
                    n10 += n12;
                    n9 = n22;
                    n13 += n14;
                }
            }
            else {
                if (n8 != 2) {
                    throw new IllegalArgumentException();
                }
                final int n28 = n9;
                for (int n29 = -n4; n29 < 0; ++n29) {
                    final int n30 = (n10 >> 16) * n7;
                    for (int n31 = -n3; n31 < 0; ++n31) {
                        int n32 = n5;
                        if (array[(n9 >> 16) + n30] != 0) {
                            n32 = n6;
                        }
                        if (n32 != 0) {
                            final int n33 = this.anIntArray4504[n13];
                            final int n34 = n32 + n33;
                            final int n35 = (n32 & 0xFF00FF) + (n33 & 0xFF00FF);
                            final int n36 = (n35 & 0x1000100) + (n34 - n35 & 0x10000);
                            this.anIntArray4504[n13++] = (n34 - n36 | n36 - (n36 >>> 8));
                        }
                        else {
                            ++n13;
                        }
                        n9 += n11;
                    }
                    n10 += n12;
                    n9 = n28;
                    n13 += n14;
                }
            }
        }
    }
    
    @Override
    final void H(final int n, final int n2, final int n3, final int[] array) {
        final float n4 = this.aClass111_Sub2_4513.aFloat4689 + (this.aClass111_Sub2_4513.aFloat4693 * n + this.aClass111_Sub2_4513.aFloat4698 * n2 + this.aClass111_Sub2_4513.aFloat4694 * n3);
        if (n4 == 0.0f) {
            final int n5 = 0;
            final int n6 = 1;
            final int n7 = 2;
            final int n8 = -1;
            array[n7] = n8;
            array[n5] = (array[n6] = n8);
        }
        else {
            final int n9 = (int)(this.anInt4514 * (this.aClass111_Sub2_4513.aFloat4697 + (this.aClass111_Sub2_4513.aFloat4700 * n + this.aClass111_Sub2_4513.aFloat4699 * n2 + this.aClass111_Sub2_4513.aFloat4690 * n3)) / n4);
            final int n10 = (int)(this.anInt4490 * (this.aClass111_Sub2_4513.aFloat4691 + (this.aClass111_Sub2_4513.aFloat4692 * n + this.aClass111_Sub2_4513.aFloat4688 * n2 + this.aClass111_Sub2_4513.aFloat4696 * n3)) / n4);
            array[0] = n9 - this.anInt4486;
            array[1] = n10 - this.anInt4518;
            array[2] = (int)n4;
        }
    }
    
    @Override
    final boolean method1800() {
        return true;
    }
    
    @Override
    final void method1818(final int n, final Class98_Sub5[] array) {
    }
    
    @Override
    final void method1795(int i, int j, int n, int n2, int n3, final int n4) {
        n -= i;
        n2 -= j;
        if (n2 == 0) {
            if (n >= 0) {
                this.U(i, j, n + 1, n3, n4);
            }
            else {
                this.U(i + n, j, -n + 1, n3, n4);
            }
        }
        else if (n == 0) {
            if (n2 >= 0) {
                this.P(i, j, n2 + 1, n3, n4);
            }
            else {
                this.P(i, j + n2, -n2 + 1, n3, n4);
            }
        }
        else {
            if (n + n2 < 0) {
                i += n;
                n = -n;
                j += n2;
                n2 = -n2;
            }
            if (n > n2) {
                j <<= 16;
                j += 32768;
                n2 <<= 16;
                final int n5 = (int)Math.floor(n2 / n + 0.5);
                n += i;
                if (i < this.anInt4509) {
                    j += n5 * (this.anInt4509 - i);
                    i = this.anInt4509;
                }
                if (n >= this.anInt4507) {
                    n = this.anInt4507 - 1;
                }
                final int n6 = n3 >>> 24;
                if (n4 == 0 || (n4 == 1 && n6 == 255)) {
                    while (i <= n) {
                        final int n7 = j >> 16;
                        if (n7 >= this.anInt4495 && n7 < this.anInt4492) {
                            this.anIntArray4504[i + n7 * this.anInt4505] = n3;
                        }
                        j += n5;
                        ++i;
                    }
                    return;
                }
                if (n4 == 1) {
                    n3 = ((n3 & 0xFF00FF) * n6 >> 8 & 0xFF00FF) + ((n3 & 0xFF00) * n6 >> 8 & 0xFF00) + (n6 << 24);
                    final int n8 = 256 - n6;
                    while (i <= n) {
                        final int n9 = j >> 16;
                        if (n9 >= this.anInt4495 && n9 < this.anInt4492) {
                            final int n10 = i + n9 * this.anInt4505;
                            final int n11 = this.anIntArray4504[n10];
                            this.anIntArray4504[n10] = n3 + (((n11 & 0xFF00FF) * n8 >> 8 & 0xFF00FF) + ((n11 & 0xFF00) * n8 >> 8 & 0xFF00));
                        }
                        j += n5;
                        ++i;
                    }
                    return;
                }
                if (n4 == 2) {
                    while (i <= n) {
                        final int n12 = j >> 16;
                        if (n12 >= this.anInt4495 && n12 < this.anInt4492) {
                            final int n13 = i + n12 * this.anInt4505;
                            final int n14 = this.anIntArray4504[n13];
                            final int n15 = n3 + n14;
                            final int n16 = (n3 & 0xFF00FF) + (n14 & 0xFF00FF);
                            final int n17 = (n16 & 0x1000100) + (n15 - n16 & 0x10000);
                            this.anIntArray4504[n13] = (n15 - n17 | n17 - (n17 >>> 8));
                        }
                        j += n5;
                        ++i;
                    }
                    return;
                }
                throw new IllegalArgumentException();
            }
            else {
                i <<= 16;
                i += 32768;
                n <<= 16;
                final int n18 = (int)Math.floor(n / n2 + 0.5);
                n2 += j;
                if (j < this.anInt4495) {
                    i += n18 * (this.anInt4495 - j);
                    j = this.anInt4495;
                }
                if (n2 >= this.anInt4492) {
                    n2 = this.anInt4492 - 1;
                }
                final int n19 = n3 >>> 24;
                if (n4 == 0 || (n4 == 1 && n19 == 255)) {
                    while (j <= n2) {
                        final int n20 = i >> 16;
                        if (n20 >= this.anInt4509 && n20 < this.anInt4507) {
                            this.anIntArray4504[n20 + j * this.anInt4505] = n3;
                        }
                        i += n18;
                        ++j;
                    }
                }
                else if (n4 == 1) {
                    n3 = ((n3 & 0xFF00FF) * n19 >> 8 & 0xFF00FF) + ((n3 & 0xFF00) * n19 >> 8 & 0xFF00) + (n19 << 24);
                    final int n21 = 256 - n19;
                    while (j <= n2) {
                        final int n22 = i >> 16;
                        if (n22 >= this.anInt4509 && n22 < this.anInt4507) {
                            final int n23 = this.anIntArray4504[n22 + j * this.anInt4505];
                            this.anIntArray4504[n22 + j * this.anInt4505] = n3 + (((n23 & 0xFF00FF) * n21 >> 8 & 0xFF00FF) + ((n23 & 0xFF00) * n21 >> 8 & 0xFF00));
                        }
                        i += n18;
                        ++j;
                    }
                }
                else {
                    if (n4 != 2) {
                        throw new IllegalArgumentException();
                    }
                    while (j <= n2) {
                        final int n24 = i >> 16;
                        if (n24 >= this.anInt4509 && n24 < this.anInt4507) {
                            final int n25 = n24 + j * this.anInt4505;
                            final int n26 = this.anIntArray4504[n25];
                            final int n27 = n3 + n26;
                            final int n28 = (n3 & 0xFF00FF) + (n26 & 0xFF00FF);
                            final int n29 = (n28 & 0x1000100) + (n27 - n28 & 0x10000);
                            this.anIntArray4504[n25] = (n27 - n29 | n29 - (n29 >>> 8));
                        }
                        i += n18;
                        ++j;
                    }
                }
            }
        }
    }
    
    @Override
    final Class332 method1758(final Class324 class324, final boolean b) {
        final int[] anIntArray2718 = class324.anIntArray2718;
        final byte[] aByteArray2717 = class324.aByteArray2717;
        final int anInt2722 = class324.anInt2722;
        final int anInt2723 = class324.anInt2720;
        Class332_Sub3 class332_Sub3;
        if (b && class324.aByteArray2723 == null) {
            final int[] array = new int[anIntArray2718.length];
            final byte[] array2 = new byte[anInt2722 * anInt2723];
            for (int i = 0; i < anInt2723; ++i) {
                final int n = i * anInt2722;
                for (int j = 0; j < anInt2722; ++j) {
                    array2[n + j] = aByteArray2717[n + j];
                }
            }
            for (int k = 0; k < anIntArray2718.length; ++k) {
                array[k] = anIntArray2718[k];
            }
            class332_Sub3 = new Class332_Sub3_Sub3(this, array2, array, anInt2722, anInt2723);
        }
        else {
            final int[] array3 = new int[anInt2722 * anInt2723];
            final byte[] aByteArray2718 = class324.aByteArray2723;
            if (aByteArray2718 != null) {
                for (int l = 0; l < anInt2723; ++l) {
                    final int n2 = l * anInt2722;
                    for (int n3 = 0; n3 < anInt2722; ++n3) {
                        array3[n2 + n3] = (anIntArray2718[aByteArray2717[n2 + n3] & 0xFF] | aByteArray2718[n2 + n3] << 24);
                    }
                }
                class332_Sub3 = new Class332_Sub3_Sub1(this, array3, anInt2722, anInt2723);
            }
            else {
                for (int n4 = 0; n4 < anInt2723; ++n4) {
                    final int n5 = n4 * anInt2722;
                    for (int n6 = 0; n6 < anInt2722; ++n6) {
                        final int n7 = anIntArray2718[aByteArray2717[n5 + n6] & 0xFF];
                        array3[n5 + n6] = ((n7 != 0) ? (0xFF000000 | n7) : 0);
                    }
                }
                class332_Sub3 = new Class332_Sub3_Sub2(this, array3, anInt2722, anInt2723);
            }
        }
        class332_Sub3.method3740(class324.anInt2725, class324.anInt2721, class324.anInt2719, class324.anInt2724);
        return class332_Sub3;
    }
    
    @Override
    final boolean method1788() {
        return false;
    }
    
    @Override
    final void a(final za za) {
    }
    
    @Override
    final void method1806(final int anInt4475) {
        final int n = anInt4475 - this.anInt4475;
        for (Object o = this.aClass79_4494.method803(false); o != null; o = this.aClass79_4494.method804(false)) {
            final Class98_Sub2 class98_Sub2 = (Class98_Sub2)o;
            if (class98_Sub2.aBoolean3817) {
                final Class98_Sub2 class98_Sub3 = class98_Sub2;
                class98_Sub3.anInt3818 += n;
                final int n2 = class98_Sub2.anInt3818 / 20;
                if (n2 > 0) {
                    final Class238 method11 = super.aD938.method11(class98_Sub2.anInt3820, -28755);
                    class98_Sub2.method949(method11.aByte1823 * n * 50 / 1000, method11.aByte1837 * n * 50 / 1000);
                    final Class98_Sub2 class98_Sub4 = class98_Sub2;
                    class98_Sub4.anInt3818 -= n2 * 20;
                }
                class98_Sub2.aBoolean3817 = false;
            }
        }
        this.anInt4475 = anInt4475;
        this.aClass79_4497.method800((byte)62, 5);
        this.aClass79_4494.method800((byte)62, 5);
    }
    
    @Override
    final void method1814() {
    }
    
    @Override
    final void method1778(final int anInt4482) {
        this.anInt4482 = anInt4482;
        this.aClass79_4494.method794(31);
    }
    
    @Override
    final void method1773() {
        if (this.aBoolean4473) {
            Class18.method248(false, 37, true);
            this.aBoolean4473 = false;
        }
        this.aClass98_Sub32_4478 = null;
        this.aCanvas4479 = null;
        this.anInt4474 = 0;
        this.anInt4477 = 0;
        this.aClass377_4476 = null;
        this.aBoolean4472 = true;
    }
    
    @Override
    final void method1782(final Canvas canvas, final int n, final int n2) {
        final Class98_Sub32 class98_Sub32 = (Class98_Sub32)this.aClass377_4476.method3990(canvas.hashCode(), -1);
        if (class98_Sub32 != null) {
            class98_Sub32.method942(116);
            final Class98_Sub32 method646 = Class64_Sub23.method646(n2, n, canvas, (byte)(-111));
            this.aClass377_4476.method3996(method646, canvas.hashCode(), -1);
            if (this.aCanvas4479 == canvas && this.aClass186_4499 == null) {
                final Dimension size = canvas.getSize();
                this.anInt4474 = size.width;
                this.anInt4477 = size.height;
                this.aClass98_Sub32_4478 = method646;
                this.anIntArray4504 = method646.anIntArray4108;
                this.anInt4505 = method646.anInt4105;
                this.anInt4480 = method646.anInt4110;
                if (this.anInt4505 != this.anInt4512 || this.anInt4480 != this.anInt4485) {
                    final int anInt4505 = this.anInt4505;
                    this.anInt4512 = anInt4505;
                    this.anInt4496 = anInt4505;
                    final int anInt4506 = this.anInt4480;
                    this.anInt4485 = anInt4506;
                    this.anInt4503 = anInt4506;
                    final float[] array = new float[this.anInt4512 * this.anInt4485];
                    this.aFloatArray4487 = array;
                    this.aFloatArray4488 = array;
                }
                this.method1911();
            }
        }
    }
    
    @Override
    final void method1812() {
        this.aClass79_4494.method794(82);
        this.aClass79_4497.method794(62);
    }
    
    @Override
    final void DA(final int anInt4510, final int anInt4511, final int anInt4512, final int anInt4513) {
        this.anInt4510 = anInt4510;
        this.anInt4511 = anInt4511;
        this.anInt4514 = anInt4512;
        this.anInt4490 = anInt4513;
        this.method1913();
    }
    
    @Override
    final void U(int anInt4509, final int n, int n2, int n3, final int n4) {
        if (n >= this.anInt4495 && n < this.anInt4492) {
            if (anInt4509 < this.anInt4509) {
                n2 -= this.anInt4509 - anInt4509;
                anInt4509 = this.anInt4509;
            }
            if (anInt4509 + n2 > this.anInt4507) {
                n2 = this.anInt4507 - anInt4509;
            }
            final int n5 = anInt4509 + n * this.anInt4505;
            final int n6 = n3 >>> 24;
            if (n4 == 0 || (n4 == 1 && n6 == 255)) {
                for (int i = 0; i < n2; ++i) {
                    this.anIntArray4504[n5 + i] = n3;
                }
            }
            else if (n4 == 1) {
                n3 = ((n3 & 0xFF00FF) * n6 >> 8 & 0xFF00FF) + ((n3 & 0xFF00) * n6 >> 8 & 0xFF00) + (n6 << 24);
                final int n7 = 256 - n6;
                for (int j = 0; j < n2; ++j) {
                    final int n8 = this.anIntArray4504[n5 + j];
                    this.anIntArray4504[n5 + j] = n3 + (((n8 & 0xFF00FF) * n7 >> 8 & 0xFF00FF) + ((n8 & 0xFF00) * n7 >> 8 & 0xFF00));
                }
            }
            else {
                if (n4 != 2) {
                    throw new IllegalArgumentException();
                }
                for (int k = 0; k < n2; ++k) {
                    final int n9 = this.anIntArray4504[n5 + k];
                    final int n10 = n3 + n9;
                    final int n11 = (n3 & 0xFF00FF) + (n9 & 0xFF00FF);
                    final int n12 = (n11 & 0x1000100) + (n10 - n11 & 0x10000);
                    this.anIntArray4504[n5 + k] = (n10 - n12 | n12 - (n12 >>> 8));
                }
            }
        }
    }
    
    @Override
    final void F(final int n, final int n2) {
        final int n3 = n2 * this.anInt4505 + n;
        final int n4 = n2 * this.anInt4512 + n;
        if (n3 != 0 || n4 != 0) {
            final int[] anIntArray4504 = this.anIntArray4504;
            final float[] aFloatArray4487 = this.aFloatArray4487;
            if (n3 < 0) {
                Class236.method2891(anIntArray4504, -n3, anIntArray4504, 0, anIntArray4504.length + n3);
            }
            else if (n3 > 0) {
                Class236.method2891(anIntArray4504, 0, anIntArray4504, n3, anIntArray4504.length - n3);
            }
            if (n4 < 0) {
                Class236.method2897(aFloatArray4487, -n4, aFloatArray4487, 0, aFloatArray4487.length + n4);
            }
            else if (n4 > 0) {
                Class236.method2897(aFloatArray4487, 0, aFloatArray4487, n4, aFloatArray4487.length - n4);
            }
        }
    }
    
    @Override
    final void method1749(final boolean aBoolean4491) {
        this.aBoolean4491 = aBoolean4491;
        this.aClass79_4494.method794(120);
    }
    
    @Override
    final boolean method1780() {
        return false;
    }
}
