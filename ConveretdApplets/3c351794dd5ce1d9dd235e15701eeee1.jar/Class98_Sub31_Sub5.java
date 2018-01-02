// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub31_Sub5 extends Class98_Sub31
{
    private int anInt5868;
    private int anInt5869;
    private int anInt5870;
    private int anInt5871;
    private int anInt5872;
    private boolean aBoolean5873;
    private int anInt5874;
    private int anInt5875;
    private int anInt5876;
    private int anInt5877;
    private int anInt5878;
    private int anInt5879;
    private int anInt5880;
    private int anInt5881;
    private int anInt5882;
    
    private static final int method1393(int n, int n2, final byte[] array, final int[] array2, int anInt5879, int i, final int n3, final int n4, int n5, final int n6, final int n7, final Class98_Sub31_Sub5 class98_Sub31_Sub5, final int n8, final int n9) {
        if (n8 == 0 || (n5 = i + (n7 - anInt5879 + n8 - 257) / n8) > n6) {
            n5 = n6;
        }
        int n10;
        int n11;
        for (i <<= 1, n5 <<= 1; i < n5; n10 = i++, array2[n10] += n * n3 >> 6, n11 = i++, array2[n11] += n * n4 >> 6, anInt5879 += n8) {
            n2 = anInt5879 >> 8;
            n = array[n2];
            n = (n << 8) + (array[n2 + 1] - n) * (anInt5879 & 0xFF);
        }
        if (n8 == 0 || (n5 = (i >> 1) + (n7 - anInt5879 + n8 - 1) / n8) > n6) {
            n5 = n6;
        }
        n5 <<= 1;
        n2 = n9;
        while (i < n5) {
            n = array[anInt5879 >> 8];
            n = (n << 8) + (n2 - n) * (anInt5879 & 0xFF);
            final int n12 = i++;
            array2[n12] += n * n3 >> 6;
            final int n13 = i++;
            array2[n13] += n * n4 >> 6;
            anInt5879 += n8;
        }
        class98_Sub31_Sub5.anInt5879 = anInt5879;
        return i >> 1;
    }
    
    private final int method1394(final int[] array, int n, final int n2, final int n3, final int n4) {
        while (this.anInt5882 > 0) {
            int n5 = n + this.anInt5882;
            if (n5 > n3) {
                n5 = n3;
            }
            this.anInt5882 += n;
            if (this.anInt5874 == -256 && (this.anInt5879 & 0xFF) == 0x0) {
                if (Class151_Sub7.aBoolean5007) {
                    n = method1420(0, ((Class98_Sub24_Sub1)super.aClass98_Sub24_4104).aByteArray5799, array, this.anInt5879, n, this.anInt5876, this.anInt5880, this.anInt5871, this.anInt5868, 0, n5, n2, this);
                }
                else {
                    n = method1418(((Class98_Sub24_Sub1)super.aClass98_Sub24_4104).aByteArray5799, array, this.anInt5879, n, this.anInt5877, this.anInt5878, 0, n5, n2, this);
                }
            }
            else if (Class151_Sub7.aBoolean5007) {
                n = method1409(0, 0, ((Class98_Sub24_Sub1)super.aClass98_Sub24_4104).aByteArray5799, array, this.anInt5879, n, this.anInt5876, this.anInt5880, this.anInt5871, this.anInt5868, 0, n5, n2, this, this.anInt5874, n4);
            }
            else {
                n = method1395(0, 0, ((Class98_Sub24_Sub1)super.aClass98_Sub24_4104).aByteArray5799, array, this.anInt5879, n, this.anInt5877, this.anInt5878, 0, n5, n2, this, this.anInt5874, n4);
            }
            this.anInt5882 -= n;
            if (this.anInt5882 != 0) {
                return n;
            }
            if (this.method1398()) {
                return n3;
            }
        }
        if (this.anInt5874 == -256 && (this.anInt5879 & 0xFF) == 0x0) {
            if (Class151_Sub7.aBoolean5007) {
                return method1429(0, ((Class98_Sub24_Sub1)super.aClass98_Sub24_4104).aByteArray5799, array, this.anInt5879, n, this.anInt5876, this.anInt5880, 0, n3, n2, this);
            }
            return method1414(((Class98_Sub24_Sub1)super.aClass98_Sub24_4104).aByteArray5799, array, this.anInt5879, n, this.anInt5877, 0, n3, n2, this);
        }
        else {
            if (Class151_Sub7.aBoolean5007) {
                return method1406(0, 0, ((Class98_Sub24_Sub1)super.aClass98_Sub24_4104).aByteArray5799, array, this.anInt5879, n, this.anInt5876, this.anInt5880, 0, n3, n2, this, this.anInt5874, n4);
            }
            return method1404(0, 0, ((Class98_Sub24_Sub1)super.aClass98_Sub24_4104).aByteArray5799, array, this.anInt5879, n, this.anInt5877, 0, n3, n2, this, this.anInt5874, n4);
        }
    }
    
    private static final int method1395(int n, int n2, final byte[] array, final int[] array2, int anInt5879, int i, int anInt5880, final int n3, int n4, final int n5, final int n6, final Class98_Sub31_Sub5 class98_Sub31_Sub5, final int n7, final int n8) {
        class98_Sub31_Sub5.anInt5876 -= class98_Sub31_Sub5.anInt5871 * i;
        class98_Sub31_Sub5.anInt5880 -= class98_Sub31_Sub5.anInt5868 * i;
        if (n7 == 0 || (n4 = i + (n6 + 256 - anInt5879 + n7) / n7) > n5) {
            n4 = n5;
        }
        while (i < n4) {
            n2 = anInt5879 >> 8;
            n = array[n2 - 1];
            final int n9 = i++;
            array2[n9] += ((n << 8) + (array[n2] - n) * (anInt5879 & 0xFF)) * anInt5880 >> 6;
            anInt5880 += n3;
            anInt5879 += n7;
        }
        if (n7 == 0 || (n4 = i + (n6 - anInt5879 + n7) / n7) > n5) {
            n4 = n5;
        }
        int n10;
        for (n = n8, n2 = n7; i < n4; n10 = i++, array2[n10] += ((n << 8) + (array[anInt5879 >> 8] - n) * (anInt5879 & 0xFF)) * anInt5880 >> 6, anInt5880 += n3, anInt5879 += n2) {}
        class98_Sub31_Sub5.anInt5876 += class98_Sub31_Sub5.anInt5871 * i;
        class98_Sub31_Sub5.anInt5880 += class98_Sub31_Sub5.anInt5868 * i;
        class98_Sub31_Sub5.anInt5877 = anInt5880;
        class98_Sub31_Sub5.anInt5879 = anInt5879;
        return i;
    }
    
    @Override
    final Class98_Sub31 method1322() {
        return null;
    }
    
    @Override
    final synchronized void method1325(final int[] array, final int n, int n2) {
        if (this.anInt5881 == 0 && this.anInt5882 == 0) {
            this.method1321(n2);
        }
        else {
            final Class98_Sub24_Sub1 class98_Sub24_Sub1 = (Class98_Sub24_Sub1)super.aClass98_Sub24_4104;
            final int n3 = this.anInt5869 << 8;
            final int n4 = this.anInt5870 << 8;
            final int anInt5879 = class98_Sub24_Sub1.aByteArray5799.length << 8;
            final int n5 = n4 - n3;
            if (n5 <= 0) {
                this.anInt5872 = 0;
            }
            int n6 = n;
            n2 += n;
            if (this.anInt5879 < 0) {
                if (this.anInt5874 <= 0) {
                    this.method1413();
                    this.method942(68);
                    return;
                }
                this.anInt5879 = 0;
            }
            if (this.anInt5879 >= anInt5879) {
                if (this.anInt5874 >= 0) {
                    this.method1413();
                    this.method942(60);
                    return;
                }
                this.anInt5879 = anInt5879 - 1;
            }
            if (this.anInt5872 < 0) {
                if (this.aBoolean5873) {
                    if (this.anInt5874 < 0) {
                        n6 = this.method1394(array, n6, n3, n2, class98_Sub24_Sub1.aByteArray5799[this.anInt5869]);
                        if (this.anInt5879 >= n3) {
                            return;
                        }
                        this.anInt5879 = n3 + n3 - 1 - this.anInt5879;
                        this.anInt5874 = -this.anInt5874;
                    }
                    while (true) {
                        final int method1428 = this.method1428(array, n6, n4, n2, class98_Sub24_Sub1.aByteArray5799[this.anInt5870 - 1]);
                        if (this.anInt5879 < n4) {
                            break;
                        }
                        this.anInt5879 = n4 + n4 - 1 - this.anInt5879;
                        this.anInt5874 = -this.anInt5874;
                        n6 = this.method1394(array, method1428, n3, n2, class98_Sub24_Sub1.aByteArray5799[this.anInt5869]);
                        if (this.anInt5879 >= n3) {
                            break;
                        }
                        this.anInt5879 = n3 + n3 - 1 - this.anInt5879;
                        this.anInt5874 = -this.anInt5874;
                    }
                }
                else if (this.anInt5874 < 0) {
                    while (true) {
                        n6 = this.method1394(array, n6, n3, n2, class98_Sub24_Sub1.aByteArray5799[this.anInt5870 - 1]);
                        if (this.anInt5879 >= n3) {
                            break;
                        }
                        this.anInt5879 = n4 - 1 - (n4 - 1 - this.anInt5879) % n5;
                    }
                }
                else {
                    while (true) {
                        n6 = this.method1428(array, n6, n4, n2, class98_Sub24_Sub1.aByteArray5799[this.anInt5869]);
                        if (this.anInt5879 < n4) {
                            break;
                        }
                        this.anInt5879 = n3 + (this.anInt5879 - n3) % n5;
                    }
                }
            }
            else {
                Label_0943: {
                    if (this.anInt5872 > 0) {
                        if (this.aBoolean5873) {
                            if (this.anInt5874 < 0) {
                                n6 = this.method1394(array, n6, n3, n2, class98_Sub24_Sub1.aByteArray5799[this.anInt5869]);
                                if (this.anInt5879 >= n3) {
                                    return;
                                }
                                this.anInt5879 = n3 + n3 - 1 - this.anInt5879;
                                this.anInt5874 = -this.anInt5874;
                                if (--this.anInt5872 == 0) {
                                    break Label_0943;
                                }
                            }
                            do {
                                n6 = this.method1428(array, n6, n4, n2, class98_Sub24_Sub1.aByteArray5799[this.anInt5870 - 1]);
                                if (this.anInt5879 < n4) {
                                    return;
                                }
                                this.anInt5879 = n4 + n4 - 1 - this.anInt5879;
                                this.anInt5874 = -this.anInt5874;
                                if (--this.anInt5872 == 0) {
                                    break;
                                }
                                n6 = this.method1394(array, n6, n3, n2, class98_Sub24_Sub1.aByteArray5799[this.anInt5869]);
                                if (this.anInt5879 >= n3) {
                                    return;
                                }
                                this.anInt5879 = n3 + n3 - 1 - this.anInt5879;
                                this.anInt5874 = -this.anInt5874;
                            } while (--this.anInt5872 != 0);
                        }
                        else if (this.anInt5874 < 0) {
                            while (true) {
                                n6 = this.method1394(array, n6, n3, n2, class98_Sub24_Sub1.aByteArray5799[this.anInt5870 - 1]);
                                if (this.anInt5879 >= n3) {
                                    return;
                                }
                                final int n7 = (n4 - 1 - this.anInt5879) / n5;
                                if (n7 >= this.anInt5872) {
                                    this.anInt5879 += n5 * this.anInt5872;
                                    this.anInt5872 = 0;
                                    break;
                                }
                                this.anInt5879 += n5 * n7;
                                this.anInt5872 -= n7;
                            }
                        }
                        else {
                            while (true) {
                                n6 = this.method1428(array, n6, n4, n2, class98_Sub24_Sub1.aByteArray5799[this.anInt5869]);
                                if (this.anInt5879 < n4) {
                                    return;
                                }
                                final int n8 = (this.anInt5879 - n3) / n5;
                                if (n8 >= this.anInt5872) {
                                    this.anInt5879 -= n5 * this.anInt5872;
                                    this.anInt5872 = 0;
                                    break;
                                }
                                this.anInt5879 -= n5 * n8;
                                this.anInt5872 -= n8;
                            }
                        }
                    }
                }
                if (this.anInt5874 < 0) {
                    this.method1394(array, n6, 0, n2, 0);
                    if (this.anInt5879 < 0) {
                        this.anInt5879 = -1;
                        this.method1413();
                        this.method942(124);
                    }
                }
                else {
                    this.method1428(array, n6, anInt5879, n2, 0);
                    if (this.anInt5879 >= anInt5879) {
                        this.anInt5879 = anInt5879;
                        this.method1413();
                        this.method942(50);
                    }
                }
            }
        }
    }
    
    @Override
    final Class98_Sub31 method1327() {
        return null;
    }
    
    private static final int method1396(final byte[] array, final int[] array2, int n, int i, int n2, int n3, final int n4, int n5, final Class98_Sub31_Sub5 class98_Sub31_Sub5) {
        n >>= 8;
        n5 >>= 8;
        n2 <<= 2;
        if ((n3 = i + n5 - n) > n4) {
            n3 = n4;
        }
        int n6;
        int n7;
        int n8;
        int n9;
        for (n3 -= 3; i < n3; n6 = i++, array2[n6] += array[n++] * n2, n7 = i++, array2[n7] += array[n++] * n2, n8 = i++, array2[n8] += array[n++] * n2, n9 = i++, array2[n9] += array[n++] * n2) {}
        int n10;
        for (n3 += 3; i < n3; n10 = i++, array2[n10] += array[n++] * n2) {}
        class98_Sub31_Sub5.anInt5879 = n << 8;
        return i;
    }
    
    final synchronized void method1397(final int n, final int n2) {
        this.method1412(n, n2, this.method1426());
    }
    
    private final boolean method1398() {
        int anInt5881 = this.anInt5881;
        int method1406;
        int method1405;
        if (anInt5881 == Integer.MIN_VALUE) {
            method1405 = (anInt5881 = (method1406 = 0));
        }
        else {
            method1405 = method1405(anInt5881, this.anInt5875);
            method1406 = method1421(anInt5881, this.anInt5875);
        }
        if (this.anInt5877 != anInt5881 || this.anInt5876 != method1405 || this.anInt5880 != method1406) {
            if (this.anInt5877 < anInt5881) {
                this.anInt5878 = 1;
                this.anInt5882 = anInt5881 - this.anInt5877;
            }
            else if (this.anInt5877 > anInt5881) {
                this.anInt5878 = -1;
                this.anInt5882 = this.anInt5877 - anInt5881;
            }
            else {
                this.anInt5878 = 0;
            }
            if (this.anInt5876 < method1405) {
                this.anInt5871 = 1;
                if (this.anInt5882 == 0 || this.anInt5882 > method1405 - this.anInt5876) {
                    this.anInt5882 = method1405 - this.anInt5876;
                }
            }
            else if (this.anInt5876 > method1405) {
                this.anInt5871 = -1;
                if (this.anInt5882 == 0 || this.anInt5882 > this.anInt5876 - method1405) {
                    this.anInt5882 = this.anInt5876 - method1405;
                }
            }
            else {
                this.anInt5871 = 0;
            }
            if (this.anInt5880 < method1406) {
                this.anInt5868 = 1;
                if (this.anInt5882 == 0 || this.anInt5882 > method1406 - this.anInt5880) {
                    this.anInt5882 = method1406 - this.anInt5880;
                }
            }
            else if (this.anInt5880 > method1406) {
                this.anInt5868 = -1;
                if (this.anInt5882 == 0 || this.anInt5882 > this.anInt5880 - method1406) {
                    this.anInt5882 = this.anInt5880 - method1406;
                }
            }
            else {
                this.anInt5868 = 0;
            }
            return false;
        }
        if (this.anInt5881 == Integer.MIN_VALUE) {
            this.anInt5881 = 0;
            final boolean anInt5882 = false;
            this.anInt5880 = (anInt5882 ? 1 : 0);
            this.anInt5876 = (anInt5882 ? 1 : 0);
            this.anInt5877 = (anInt5882 ? 1 : 0);
            this.method942(64);
            return true;
        }
        this.method1408();
        return false;
    }
    
    final synchronized void method1399(int anInt5879) {
        final int n = ((Class98_Sub24_Sub1)super.aClass98_Sub24_4104).aByteArray5799.length << 8;
        if (anInt5879 < -1) {
            anInt5879 = -1;
        }
        if (anInt5879 > n) {
            anInt5879 = n;
        }
        this.anInt5879 = anInt5879;
    }
    
    final synchronized int method1400() {
        if (this.anInt5881 == Integer.MIN_VALUE) {
            return 0;
        }
        return this.anInt5881;
    }
    
    final boolean method1401() {
        return this.anInt5879 < 0 || this.anInt5879 >= ((Class98_Sub24_Sub1)super.aClass98_Sub24_4104).aByteArray5799.length << 8;
    }
    
    static final Class98_Sub31_Sub5 method1402(final Class98_Sub24_Sub1 class98_Sub24_Sub1, final int n, final int n2, final int n3) {
        if (class98_Sub24_Sub1.aByteArray5799 == null || class98_Sub24_Sub1.aByteArray5799.length == 0) {
            return null;
        }
        return new Class98_Sub31_Sub5(class98_Sub24_Sub1, n, n2, n3);
    }
    
    private static final int method1403(int n, final byte[] array, final int[] array2, int n2, int i, int n3, int n4, int n5, final int n6, int n7, final Class98_Sub31_Sub5 class98_Sub31_Sub5) {
        n2 >>= 8;
        n7 >>= 8;
        n3 <<= 2;
        n4 <<= 2;
        if ((n5 = i + n7 - n2) > n6) {
            n5 = n6;
        }
        int n8;
        int n9;
        int n10;
        int n11;
        int n12;
        int n13;
        int n14;
        int n15;
        for (i <<= 1, n5 <<= 1, n5 -= 6; i < n5; n8 = i++, array2[n8] += n * n3, n9 = i++, array2[n9] += n * n4, n = array[n2++], n10 = i++, array2[n10] += n * n3, n11 = i++, array2[n11] += n * n4, n = array[n2++], n12 = i++, array2[n12] += n * n3, n13 = i++, array2[n13] += n * n4, n = array[n2++], n14 = i++, array2[n14] += n * n3, n15 = i++, array2[n15] += n * n4) {
            n = array[n2++];
        }
        int n16;
        int n17;
        for (n5 += 6; i < n5; n16 = i++, array2[n16] += n * n3, n17 = i++, array2[n17] += n * n4) {
            n = array[n2++];
        }
        class98_Sub31_Sub5.anInt5879 = n2 << 8;
        return i >> 1;
    }
    
    @Override
    final synchronized void method1321(int anInt5882) {
        if (this.anInt5882 > 0) {
            if (anInt5882 >= this.anInt5882) {
                if (this.anInt5881 == Integer.MIN_VALUE) {
                    this.anInt5881 = 0;
                    final boolean anInt5883 = false;
                    this.anInt5880 = (anInt5883 ? 1 : 0);
                    this.anInt5876 = (anInt5883 ? 1 : 0);
                    this.anInt5877 = (anInt5883 ? 1 : 0);
                    this.method942(98);
                    anInt5882 = this.anInt5882;
                }
                this.anInt5882 = 0;
                this.method1408();
            }
            else {
                this.anInt5877 += this.anInt5878 * anInt5882;
                this.anInt5876 += this.anInt5871 * anInt5882;
                this.anInt5880 += this.anInt5868 * anInt5882;
                this.anInt5882 -= anInt5882;
            }
        }
        final Class98_Sub24_Sub1 class98_Sub24_Sub1 = (Class98_Sub24_Sub1)super.aClass98_Sub24_4104;
        final int n = this.anInt5869 << 8;
        final int n2 = this.anInt5870 << 8;
        final int anInt5884 = class98_Sub24_Sub1.aByteArray5799.length << 8;
        final int n3 = n2 - n;
        if (n3 <= 0) {
            this.anInt5872 = 0;
        }
        if (this.anInt5879 < 0) {
            if (this.anInt5874 <= 0) {
                this.method1413();
                this.method942(90);
                return;
            }
            this.anInt5879 = 0;
        }
        if (this.anInt5879 >= anInt5884) {
            if (this.anInt5874 >= 0) {
                this.method1413();
                this.method942(84);
                return;
            }
            this.anInt5879 = anInt5884 - 1;
        }
        this.anInt5879 += this.anInt5874 * anInt5882;
        if (this.anInt5872 < 0) {
            if (this.aBoolean5873) {
                if (this.anInt5874 < 0) {
                    if (this.anInt5879 >= n) {
                        return;
                    }
                    this.anInt5879 = n + n - 1 - this.anInt5879;
                    this.anInt5874 = -this.anInt5874;
                }
                while (this.anInt5879 >= n2) {
                    this.anInt5879 = n2 + n2 - 1 - this.anInt5879;
                    this.anInt5874 = -this.anInt5874;
                    if (this.anInt5879 >= n) {
                        break;
                    }
                    this.anInt5879 = n + n - 1 - this.anInt5879;
                    this.anInt5874 = -this.anInt5874;
                }
            }
            else if (this.anInt5874 < 0) {
                if (this.anInt5879 < n) {
                    this.anInt5879 = n2 - 1 - (n2 - 1 - this.anInt5879) % n3;
                }
            }
            else if (this.anInt5879 >= n2) {
                this.anInt5879 = n + (this.anInt5879 - n) % n3;
            }
        }
        else {
            Label_0792: {
                if (this.anInt5872 > 0) {
                    if (this.aBoolean5873) {
                        if (this.anInt5874 < 0) {
                            if (this.anInt5879 >= n) {
                                return;
                            }
                            this.anInt5879 = n + n - 1 - this.anInt5879;
                            this.anInt5874 = -this.anInt5874;
                            if (--this.anInt5872 == 0) {
                                break Label_0792;
                            }
                        }
                        while (this.anInt5879 >= n2) {
                            this.anInt5879 = n2 + n2 - 1 - this.anInt5879;
                            this.anInt5874 = -this.anInt5874;
                            if (--this.anInt5872 == 0) {
                                break Label_0792;
                            }
                            if (this.anInt5879 >= n) {
                                return;
                            }
                            this.anInt5879 = n + n - 1 - this.anInt5879;
                            this.anInt5874 = -this.anInt5874;
                            if (--this.anInt5872 == 0) {
                                break Label_0792;
                            }
                        }
                        return;
                    }
                    if (this.anInt5874 < 0) {
                        if (this.anInt5879 >= n) {
                            return;
                        }
                        final int n4 = (n2 - 1 - this.anInt5879) / n3;
                        if (n4 < this.anInt5872) {
                            this.anInt5879 += n3 * n4;
                            this.anInt5872 -= n4;
                            return;
                        }
                        this.anInt5879 += n3 * this.anInt5872;
                        this.anInt5872 = 0;
                    }
                    else {
                        if (this.anInt5879 < n2) {
                            return;
                        }
                        final int n5 = (this.anInt5879 - n) / n3;
                        if (n5 < this.anInt5872) {
                            this.anInt5879 -= n3 * n5;
                            this.anInt5872 -= n5;
                            return;
                        }
                        this.anInt5879 -= n3 * this.anInt5872;
                        this.anInt5872 = 0;
                    }
                }
            }
            if (this.anInt5874 < 0) {
                if (this.anInt5879 < 0) {
                    this.anInt5879 = -1;
                    this.method1413();
                    this.method942(82);
                }
            }
            else if (this.anInt5879 >= anInt5884) {
                this.anInt5879 = anInt5884;
                this.method1413();
                this.method942(87);
            }
        }
    }
    
    private static final int method1404(int n, int n2, final byte[] array, final int[] array2, int anInt5879, int i, final int n3, int n4, final int n5, final int n6, final Class98_Sub31_Sub5 class98_Sub31_Sub5, final int n7, final int n8) {
        if (n7 == 0 || (n4 = i + (n6 + 256 - anInt5879 + n7) / n7) > n5) {
            n4 = n5;
        }
        while (i < n4) {
            n2 = anInt5879 >> 8;
            n = array[n2 - 1];
            final int n9 = i++;
            array2[n9] += ((n << 8) + (array[n2] - n) * (anInt5879 & 0xFF)) * n3 >> 6;
            anInt5879 += n7;
        }
        if (n7 == 0 || (n4 = i + (n6 - anInt5879 + n7) / n7) > n5) {
            n4 = n5;
        }
        int n10;
        for (n = n8, n2 = n7; i < n4; n10 = i++, array2[n10] += ((n << 8) + (array[anInt5879 >> 8] - n) * (anInt5879 & 0xFF)) * n3 >> 6, anInt5879 += n2) {}
        class98_Sub31_Sub5.anInt5879 = anInt5879;
        return i;
    }
    
    private static final int method1405(final int n, final int n2) {
        if (n2 < 0) {
            return n;
        }
        return (int)(n * Math.sqrt((16384 - n2) * 1.220703125E-4) + 0.5);
    }
    
    private static final int method1406(int n, int n2, final byte[] array, final int[] array2, int anInt5879, int i, final int n3, final int n4, int n5, final int n6, final int n7, final Class98_Sub31_Sub5 class98_Sub31_Sub5, final int n8, final int n9) {
        if (n8 == 0 || (n5 = i + (n7 + 256 - anInt5879 + n8) / n8) > n6) {
            n5 = n6;
        }
        int n10;
        int n11;
        for (i <<= 1, n5 <<= 1; i < n5; n10 = i++, array2[n10] += n * n3 >> 6, n11 = i++, array2[n11] += n * n4 >> 6, anInt5879 += n8) {
            n2 = anInt5879 >> 8;
            n = array[n2 - 1];
            n = (n << 8) + (array[n2] - n) * (anInt5879 & 0xFF);
        }
        if (n8 == 0 || (n5 = (i >> 1) + (n7 - anInt5879 + n8) / n8) > n6) {
            n5 = n6;
        }
        n5 <<= 1;
        n2 = n9;
        while (i < n5) {
            n = (n2 << 8) + (array[anInt5879 >> 8] - n2) * (anInt5879 & 0xFF);
            final int n12 = i++;
            array2[n12] += n * n3 >> 6;
            final int n13 = i++;
            array2[n13] += n * n4 >> 6;
            anInt5879 += n8;
        }
        class98_Sub31_Sub5.anInt5879 = anInt5879;
        return i >> 1;
    }
    
    final synchronized void method1407(final int anInt5874) {
        if (this.anInt5874 < 0) {
            this.anInt5874 = -anInt5874;
        }
        else {
            this.anInt5874 = anInt5874;
        }
    }
    
    private final void method1408() {
        this.anInt5877 = this.anInt5881;
        this.anInt5876 = method1405(this.anInt5881, this.anInt5875);
        this.anInt5880 = method1421(this.anInt5881, this.anInt5875);
    }
    
    private static final int method1409(int n, int n2, final byte[] array, final int[] array2, int anInt5879, int i, int anInt5880, int anInt5881, final int n3, final int n4, int n5, final int n6, final int n7, final Class98_Sub31_Sub5 class98_Sub31_Sub5, final int n8, final int n9) {
        class98_Sub31_Sub5.anInt5877 -= class98_Sub31_Sub5.anInt5878 * i;
        if (n8 == 0 || (n5 = i + (n7 + 256 - anInt5879 + n8) / n8) > n6) {
            n5 = n6;
        }
        int n10;
        int n11;
        for (i <<= 1, n5 <<= 1; i < n5; n10 = i++, array2[n10] += n * anInt5880 >> 6, anInt5880 += n3, n11 = i++, array2[n11] += n * anInt5881 >> 6, anInt5881 += n4, anInt5879 += n8) {
            n2 = anInt5879 >> 8;
            n = array[n2 - 1];
            n = (n << 8) + (array[n2] - n) * (anInt5879 & 0xFF);
        }
        if (n8 == 0 || (n5 = (i >> 1) + (n7 - anInt5879 + n8) / n8) > n6) {
            n5 = n6;
        }
        n5 <<= 1;
        n2 = n9;
        while (i < n5) {
            n = (n2 << 8) + (array[anInt5879 >> 8] - n2) * (anInt5879 & 0xFF);
            final int n12 = i++;
            array2[n12] += n * anInt5880 >> 6;
            anInt5880 += n3;
            final int n13 = i++;
            array2[n13] += n * anInt5881 >> 6;
            anInt5881 += n4;
            anInt5879 += n8;
        }
        i >>= 1;
        class98_Sub31_Sub5.anInt5877 += class98_Sub31_Sub5.anInt5878 * i;
        class98_Sub31_Sub5.anInt5876 = anInt5880;
        class98_Sub31_Sub5.anInt5880 = anInt5881;
        class98_Sub31_Sub5.anInt5879 = anInt5879;
        return i;
    }
    
    private final synchronized void method1410(final int anInt5881, final int anInt5882) {
        this.anInt5881 = anInt5881;
        this.anInt5875 = anInt5882;
        this.anInt5882 = 0;
        this.method1408();
    }
    
    final synchronized void method1411(final boolean b) {
        this.anInt5874 = (this.anInt5874 ^ this.anInt5874 >> 31) + (this.anInt5874 >>> 31);
        if (b) {
            this.anInt5874 = -this.anInt5874;
        }
    }
    
    final synchronized void method1412(int anInt5882, final int anInt5883, final int anInt5884) {
        if (anInt5882 == 0) {
            this.method1410(anInt5883, anInt5884);
        }
        else {
            final int method1405 = method1405(anInt5883, anInt5884);
            final int method1406 = method1421(anInt5883, anInt5884);
            if (this.anInt5876 == method1405 && this.anInt5880 == method1406) {
                this.anInt5882 = 0;
            }
            else {
                int n = anInt5883 - this.anInt5877;
                if (this.anInt5877 - anInt5883 > n) {
                    n = this.anInt5877 - anInt5883;
                }
                if (method1405 - this.anInt5876 > n) {
                    n = method1405 - this.anInt5876;
                }
                if (this.anInt5876 - method1405 > n) {
                    n = this.anInt5876 - method1405;
                }
                if (method1406 - this.anInt5880 > n) {
                    n = method1406 - this.anInt5880;
                }
                if (this.anInt5880 - method1406 > n) {
                    n = this.anInt5880 - method1406;
                }
                if (anInt5882 > n) {
                    anInt5882 = n;
                }
                this.anInt5882 = anInt5882;
                this.anInt5881 = anInt5883;
                this.anInt5875 = anInt5884;
                this.anInt5878 = (anInt5883 - this.anInt5877) / anInt5882;
                this.anInt5871 = (method1405 - this.anInt5876) / anInt5882;
                this.anInt5868 = (method1406 - this.anInt5880) / anInt5882;
            }
        }
    }
    
    private final void method1413() {
        if (this.anInt5882 != 0) {
            if (this.anInt5881 == Integer.MIN_VALUE) {
                this.anInt5881 = 0;
            }
            this.anInt5882 = 0;
            this.method1408();
        }
    }
    
    private static final int method1414(final byte[] array, final int[] array2, int n, int i, int n2, int n3, final int n4, int n5, final Class98_Sub31_Sub5 class98_Sub31_Sub5) {
        n >>= 8;
        n5 >>= 8;
        n2 <<= 2;
        if ((n3 = i + n - (n5 - 1)) > n4) {
            n3 = n4;
        }
        int n6;
        int n7;
        int n8;
        int n9;
        for (n3 -= 3; i < n3; n6 = i++, array2[n6] += array[n--] * n2, n7 = i++, array2[n7] += array[n--] * n2, n8 = i++, array2[n8] += array[n--] * n2, n9 = i++, array2[n9] += array[n--] * n2) {}
        int n10;
        for (n3 += 3; i < n3; n10 = i++, array2[n10] += array[n--] * n2) {}
        class98_Sub31_Sub5.anInt5879 = n << 8;
        return i;
    }
    
    private static final int method1415(int n, int n2, final byte[] array, final int[] array2, int anInt5879, int i, int anInt5880, int anInt5881, final int n3, final int n4, int n5, final int n6, final int n7, final Class98_Sub31_Sub5 class98_Sub31_Sub5, final int n8, final int n9) {
        class98_Sub31_Sub5.anInt5877 -= class98_Sub31_Sub5.anInt5878 * i;
        if (n8 == 0 || (n5 = i + (n7 - anInt5879 + n8 - 257) / n8) > n6) {
            n5 = n6;
        }
        int n10;
        int n11;
        for (i <<= 1, n5 <<= 1; i < n5; n10 = i++, array2[n10] += n * anInt5880 >> 6, anInt5880 += n3, n11 = i++, array2[n11] += n * anInt5881 >> 6, anInt5881 += n4, anInt5879 += n8) {
            n2 = anInt5879 >> 8;
            n = array[n2];
            n = (n << 8) + (array[n2 + 1] - n) * (anInt5879 & 0xFF);
        }
        if (n8 == 0 || (n5 = (i >> 1) + (n7 - anInt5879 + n8 - 1) / n8) > n6) {
            n5 = n6;
        }
        n5 <<= 1;
        n2 = n9;
        while (i < n5) {
            n = array[anInt5879 >> 8];
            n = (n << 8) + (n2 - n) * (anInt5879 & 0xFF);
            final int n12 = i++;
            array2[n12] += n * anInt5880 >> 6;
            anInt5880 += n3;
            final int n13 = i++;
            array2[n13] += n * anInt5881 >> 6;
            anInt5881 += n4;
            anInt5879 += n8;
        }
        i >>= 1;
        class98_Sub31_Sub5.anInt5877 += class98_Sub31_Sub5.anInt5878 * i;
        class98_Sub31_Sub5.anInt5876 = anInt5880;
        class98_Sub31_Sub5.anInt5880 = anInt5881;
        class98_Sub31_Sub5.anInt5879 = anInt5879;
        return i;
    }
    
    private static final int method1416(final byte[] array, final int[] array2, int n, int i, int n2, int n3, int n4, final int n5, int n6, final Class98_Sub31_Sub5 class98_Sub31_Sub5) {
        n >>= 8;
        n6 >>= 8;
        n2 <<= 2;
        n3 <<= 2;
        if ((n4 = i + n6 - n) > n5) {
            n4 = n5;
        }
        class98_Sub31_Sub5.anInt5876 += class98_Sub31_Sub5.anInt5871 * (n4 - i);
        class98_Sub31_Sub5.anInt5880 += class98_Sub31_Sub5.anInt5868 * (n4 - i);
        int n7;
        int n8;
        int n9;
        int n10;
        for (n4 -= 3; i < n4; n7 = i++, array2[n7] += array[n++] * n2, n2 += n3, n8 = i++, array2[n8] += array[n++] * n2, n2 += n3, n9 = i++, array2[n9] += array[n++] * n2, n2 += n3, n10 = i++, array2[n10] += array[n++] * n2, n2 += n3) {}
        int n11;
        for (n4 += 3; i < n4; n11 = i++, array2[n11] += array[n++] * n2, n2 += n3) {}
        class98_Sub31_Sub5.anInt5877 = n2 >> 2;
        class98_Sub31_Sub5.anInt5879 = n << 8;
        return i;
    }
    
    private static final int method1417(int n, final byte[] array, final int[] array2, int n2, int i, int n3, int n4, int n5, int n6, int n7, final int n8, int n9, final Class98_Sub31_Sub5 class98_Sub31_Sub5) {
        n2 >>= 8;
        n9 >>= 8;
        n3 <<= 2;
        n4 <<= 2;
        n5 <<= 2;
        n6 <<= 2;
        if ((n7 = i + n9 - n2) > n8) {
            n7 = n8;
        }
        class98_Sub31_Sub5.anInt5877 += class98_Sub31_Sub5.anInt5878 * (n7 - i);
        int n10;
        int n11;
        int n12;
        int n13;
        int n14;
        int n15;
        int n16;
        int n17;
        for (i <<= 1, n7 <<= 1, n7 -= 6; i < n7; n10 = i++, array2[n10] += n * n3, n3 += n5, n11 = i++, array2[n11] += n * n4, n4 += n6, n = array[n2++], n12 = i++, array2[n12] += n * n3, n3 += n5, n13 = i++, array2[n13] += n * n4, n4 += n6, n = array[n2++], n14 = i++, array2[n14] += n * n3, n3 += n5, n15 = i++, array2[n15] += n * n4, n4 += n6, n = array[n2++], n16 = i++, array2[n16] += n * n3, n3 += n5, n17 = i++, array2[n17] += n * n4, n4 += n6) {
            n = array[n2++];
        }
        int n18;
        int n19;
        for (n7 += 6; i < n7; n18 = i++, array2[n18] += n * n3, n3 += n5, n19 = i++, array2[n19] += n * n4, n4 += n6) {
            n = array[n2++];
        }
        class98_Sub31_Sub5.anInt5876 = n3 >> 2;
        class98_Sub31_Sub5.anInt5880 = n4 >> 2;
        class98_Sub31_Sub5.anInt5879 = n2 << 8;
        return i >> 1;
    }
    
    private static final int method1418(final byte[] array, final int[] array2, int n, int i, int n2, int n3, int n4, final int n5, int n6, final Class98_Sub31_Sub5 class98_Sub31_Sub5) {
        n >>= 8;
        n6 >>= 8;
        n2 <<= 2;
        n3 <<= 2;
        if ((n4 = i + n - (n6 - 1)) > n5) {
            n4 = n5;
        }
        class98_Sub31_Sub5.anInt5876 += class98_Sub31_Sub5.anInt5871 * (n4 - i);
        class98_Sub31_Sub5.anInt5880 += class98_Sub31_Sub5.anInt5868 * (n4 - i);
        int n7;
        int n8;
        int n9;
        int n10;
        for (n4 -= 3; i < n4; n7 = i++, array2[n7] += array[n--] * n2, n2 += n3, n8 = i++, array2[n8] += array[n--] * n2, n2 += n3, n9 = i++, array2[n9] += array[n--] * n2, n2 += n3, n10 = i++, array2[n10] += array[n--] * n2, n2 += n3) {}
        int n11;
        for (n4 += 3; i < n4; n11 = i++, array2[n11] += array[n--] * n2, n2 += n3) {}
        class98_Sub31_Sub5.anInt5877 = n2 >> 2;
        class98_Sub31_Sub5.anInt5879 = n << 8;
        return i;
    }
    
    private final synchronized void method1419(final int n) {
        this.method1410(n, this.method1426());
    }
    
    private static final int method1420(int n, final byte[] array, final int[] array2, int n2, int i, int n3, int n4, int n5, int n6, int n7, final int n8, int n9, final Class98_Sub31_Sub5 class98_Sub31_Sub5) {
        n2 >>= 8;
        n9 >>= 8;
        n3 <<= 2;
        n4 <<= 2;
        n5 <<= 2;
        n6 <<= 2;
        if ((n7 = i + n2 - (n9 - 1)) > n8) {
            n7 = n8;
        }
        class98_Sub31_Sub5.anInt5877 += class98_Sub31_Sub5.anInt5878 * (n7 - i);
        int n10;
        int n11;
        int n12;
        int n13;
        int n14;
        int n15;
        int n16;
        int n17;
        for (i <<= 1, n7 <<= 1, n7 -= 6; i < n7; n10 = i++, array2[n10] += n * n3, n3 += n5, n11 = i++, array2[n11] += n * n4, n4 += n6, n = array[n2--], n12 = i++, array2[n12] += n * n3, n3 += n5, n13 = i++, array2[n13] += n * n4, n4 += n6, n = array[n2--], n14 = i++, array2[n14] += n * n3, n3 += n5, n15 = i++, array2[n15] += n * n4, n4 += n6, n = array[n2--], n16 = i++, array2[n16] += n * n3, n3 += n5, n17 = i++, array2[n17] += n * n4, n4 += n6) {
            n = array[n2--];
        }
        int n18;
        int n19;
        for (n7 += 6; i < n7; n18 = i++, array2[n18] += n * n3, n3 += n5, n19 = i++, array2[n19] += n * n4, n4 += n6) {
            n = array[n2--];
        }
        class98_Sub31_Sub5.anInt5876 = n3 >> 2;
        class98_Sub31_Sub5.anInt5880 = n4 >> 2;
        class98_Sub31_Sub5.anInt5879 = n2 << 8;
        return i >> 1;
    }
    
    private static final int method1421(final int n, final int n2) {
        if (n2 < 0) {
            return -n;
        }
        return (int)(n * Math.sqrt(n2 * 1.220703125E-4) + 0.5);
    }
    
    final synchronized void method1422(final int anInt5872) {
        this.anInt5872 = anInt5872;
    }
    
    final synchronized void method1423(int anInt5882) {
        if (anInt5882 == 0) {
            this.method1419(0);
            this.method942(87);
        }
        else if (this.anInt5876 == 0 && this.anInt5880 == 0) {
            this.anInt5882 = 0;
            this.anInt5881 = 0;
            this.anInt5877 = 0;
            this.method942(75);
        }
        else {
            int n = -this.anInt5877;
            if (this.anInt5877 > n) {
                n = this.anInt5877;
            }
            if (-this.anInt5876 > n) {
                n = -this.anInt5876;
            }
            if (this.anInt5876 > n) {
                n = this.anInt5876;
            }
            if (-this.anInt5880 > n) {
                n = -this.anInt5880;
            }
            if (this.anInt5880 > n) {
                n = this.anInt5880;
            }
            if (anInt5882 > n) {
                anInt5882 = n;
            }
            this.anInt5882 = anInt5882;
            this.anInt5881 = Integer.MIN_VALUE;
            this.anInt5878 = -this.anInt5877 / anInt5882;
            this.anInt5871 = -this.anInt5876 / anInt5882;
            this.anInt5868 = -this.anInt5880 / anInt5882;
        }
    }
    
    private static final int method1424(int n, int n2, final byte[] array, final int[] array2, int anInt5879, int i, int anInt5880, final int n3, int n4, final int n5, final int n6, final Class98_Sub31_Sub5 class98_Sub31_Sub5, final int n7, final int n8) {
        class98_Sub31_Sub5.anInt5876 -= class98_Sub31_Sub5.anInt5871 * i;
        class98_Sub31_Sub5.anInt5880 -= class98_Sub31_Sub5.anInt5868 * i;
        if (n7 == 0 || (n4 = i + (n6 - anInt5879 + n7 - 257) / n7) > n5) {
            n4 = n5;
        }
        while (i < n4) {
            n2 = anInt5879 >> 8;
            n = array[n2];
            final int n9 = i++;
            array2[n9] += ((n << 8) + (array[n2 + 1] - n) * (anInt5879 & 0xFF)) * anInt5880 >> 6;
            anInt5880 += n3;
            anInt5879 += n7;
        }
        if (n7 == 0 || (n4 = i + (n6 - anInt5879 + n7 - 1) / n7) > n5) {
            n4 = n5;
        }
        int n10;
        for (n2 = n8; i < n4; n10 = i++, array2[n10] += ((n << 8) + (n2 - n) * (anInt5879 & 0xFF)) * anInt5880 >> 6, anInt5880 += n3, anInt5879 += n7) {
            n = array[anInt5879 >> 8];
        }
        class98_Sub31_Sub5.anInt5876 += class98_Sub31_Sub5.anInt5871 * i;
        class98_Sub31_Sub5.anInt5880 += class98_Sub31_Sub5.anInt5868 * i;
        class98_Sub31_Sub5.anInt5877 = anInt5880;
        class98_Sub31_Sub5.anInt5879 = anInt5879;
        return i;
    }
    
    final boolean method1425() {
        return this.anInt5882 != 0;
    }
    
    final synchronized int method1426() {
        if (this.anInt5875 < 0) {
            return -1;
        }
        return this.anInt5875;
    }
    
    final synchronized void method1427(final int n) {
        this.method1410(n << 6, this.method1426());
    }
    
    @Override
    final int method1326() {
        if (this.anInt5881 == 0 && this.anInt5882 == 0) {
            return 0;
        }
        return 1;
    }
    
    private final int method1428(final int[] array, int n, final int n2, final int n3, final int n4) {
        while (this.anInt5882 > 0) {
            int n5 = n + this.anInt5882;
            if (n5 > n3) {
                n5 = n3;
            }
            this.anInt5882 += n;
            if (this.anInt5874 == 256 && (this.anInt5879 & 0xFF) == 0x0) {
                if (Class151_Sub7.aBoolean5007) {
                    n = method1417(0, ((Class98_Sub24_Sub1)super.aClass98_Sub24_4104).aByteArray5799, array, this.anInt5879, n, this.anInt5876, this.anInt5880, this.anInt5871, this.anInt5868, 0, n5, n2, this);
                }
                else {
                    n = method1416(((Class98_Sub24_Sub1)super.aClass98_Sub24_4104).aByteArray5799, array, this.anInt5879, n, this.anInt5877, this.anInt5878, 0, n5, n2, this);
                }
            }
            else if (Class151_Sub7.aBoolean5007) {
                n = method1415(0, 0, ((Class98_Sub24_Sub1)super.aClass98_Sub24_4104).aByteArray5799, array, this.anInt5879, n, this.anInt5876, this.anInt5880, this.anInt5871, this.anInt5868, 0, n5, n2, this, this.anInt5874, n4);
            }
            else {
                n = method1424(0, 0, ((Class98_Sub24_Sub1)super.aClass98_Sub24_4104).aByteArray5799, array, this.anInt5879, n, this.anInt5877, this.anInt5878, 0, n5, n2, this, this.anInt5874, n4);
            }
            this.anInt5882 -= n;
            if (this.anInt5882 != 0) {
                return n;
            }
            if (this.method1398()) {
                return n3;
            }
        }
        if (this.anInt5874 == 256 && (this.anInt5879 & 0xFF) == 0x0) {
            if (Class151_Sub7.aBoolean5007) {
                return method1403(0, ((Class98_Sub24_Sub1)super.aClass98_Sub24_4104).aByteArray5799, array, this.anInt5879, n, this.anInt5876, this.anInt5880, 0, n3, n2, this);
            }
            return method1396(((Class98_Sub24_Sub1)super.aClass98_Sub24_4104).aByteArray5799, array, this.anInt5879, n, this.anInt5877, 0, n3, n2, this);
        }
        else {
            if (Class151_Sub7.aBoolean5007) {
                return method1393(0, 0, ((Class98_Sub24_Sub1)super.aClass98_Sub24_4104).aByteArray5799, array, this.anInt5879, n, this.anInt5876, this.anInt5880, 0, n3, n2, this, this.anInt5874, n4);
            }
            return method1432(0, 0, ((Class98_Sub24_Sub1)super.aClass98_Sub24_4104).aByteArray5799, array, this.anInt5879, n, this.anInt5877, 0, n3, n2, this, this.anInt5874, n4);
        }
    }
    
    private static final int method1429(int n, final byte[] array, final int[] array2, int n2, int i, int n3, int n4, int n5, final int n6, int n7, final Class98_Sub31_Sub5 class98_Sub31_Sub5) {
        n2 >>= 8;
        n7 >>= 8;
        n3 <<= 2;
        n4 <<= 2;
        if ((n5 = i + n2 - (n7 - 1)) > n6) {
            n5 = n6;
        }
        int n8;
        int n9;
        int n10;
        int n11;
        int n12;
        int n13;
        int n14;
        int n15;
        for (i <<= 1, n5 <<= 1, n5 -= 6; i < n5; n8 = i++, array2[n8] += n * n3, n9 = i++, array2[n9] += n * n4, n = array[n2--], n10 = i++, array2[n10] += n * n3, n11 = i++, array2[n11] += n * n4, n = array[n2--], n12 = i++, array2[n12] += n * n3, n13 = i++, array2[n13] += n * n4, n = array[n2--], n14 = i++, array2[n14] += n * n3, n15 = i++, array2[n15] += n * n4) {
            n = array[n2--];
        }
        int n16;
        int n17;
        for (n5 += 6; i < n5; n16 = i++, array2[n16] += n * n3, n17 = i++, array2[n17] += n * n4) {
            n = array[n2--];
        }
        class98_Sub31_Sub5.anInt5879 = n2 << 8;
        return i >> 1;
    }
    
    final synchronized int method1430() {
        if (this.anInt5874 < 0) {
            return -this.anInt5874;
        }
        return this.anInt5874;
    }
    
    final synchronized void method1431(final int n) {
        this.method1410(this.method1400(), n);
    }
    
    private static final int method1432(int n, int n2, final byte[] array, final int[] array2, int anInt5879, int i, final int n3, int n4, final int n5, final int n6, final Class98_Sub31_Sub5 class98_Sub31_Sub5, final int n7, final int n8) {
        if (n7 == 0 || (n4 = i + (n6 - anInt5879 + n7 - 257) / n7) > n5) {
            n4 = n5;
        }
        while (i < n4) {
            n2 = anInt5879 >> 8;
            n = array[n2];
            final int n9 = i++;
            array2[n9] += ((n << 8) + (array[n2 + 1] - n) * (anInt5879 & 0xFF)) * n3 >> 6;
            anInt5879 += n7;
        }
        if (n7 == 0 || (n4 = i + (n6 - anInt5879 + n7 - 1) / n7) > n5) {
            n4 = n5;
        }
        int n10;
        for (n2 = n8; i < n4; n10 = i++, array2[n10] += ((n << 8) + (n2 - n) * (anInt5879 & 0xFF)) * n3 >> 6, anInt5879 += n7) {
            n = array[anInt5879 >> 8];
        }
        class98_Sub31_Sub5.anInt5879 = anInt5879;
        return i;
    }
    
    private Class98_Sub31_Sub5(final Class98_Sub24_Sub1 aClass98_Sub24_4104, final int anInt5874, final int anInt5875, final int anInt5876) {
        super.aClass98_Sub24_4104 = aClass98_Sub24_4104;
        this.anInt5869 = aClass98_Sub24_4104.anInt5798;
        this.anInt5870 = aClass98_Sub24_4104.anInt5796;
        this.aBoolean5873 = aClass98_Sub24_4104.aBoolean5797;
        this.anInt5874 = anInt5874;
        this.anInt5881 = anInt5875;
        this.anInt5875 = anInt5876;
        this.anInt5879 = 0;
        this.method1408();
    }
    
    @Override
    final int method1323() {
        final int n = this.anInt5877 * 3 >> 6;
        int n2 = (n ^ n >> 31) + (n >>> 31);
        if (this.anInt5872 == 0) {
            n2 -= n2 * this.anInt5879 / (((Class98_Sub24_Sub1)super.aClass98_Sub24_4104).aByteArray5799.length << 8);
        }
        else if (this.anInt5872 >= 0) {
            n2 -= n2 * this.anInt5869 / ((Class98_Sub24_Sub1)super.aClass98_Sub24_4104).aByteArray5799.length;
        }
        if (n2 > 255) {
            return 255;
        }
        return n2;
    }
}
