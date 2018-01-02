// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub31_Sub3 extends Class98_Sub31
{
    private Class148 aClass148_5854;
    private Class148 aClass148_5855;
    private int anInt5856;
    private int anInt5857;
    
    final synchronized void method1371(final Class98_Sub31 class98_Sub31) {
        this.aClass148_5854.method2423(-2, class98_Sub31);
    }
    
    @Override
    final synchronized void method1325(final int[] array, int n, int n2) {
        while (this.anInt5857 >= 0) {
            if (this.anInt5856 + n2 < this.anInt5857) {
                this.anInt5856 += n2;
                this.method1376(array, n, n2);
            }
            else {
                final int n3 = this.anInt5857 - this.anInt5856;
                this.method1376(array, n, n3);
                n += n3;
                n2 -= n3;
                this.anInt5856 += n3;
                this.method1375();
                final Class98_Sub38 class98_Sub38 = (Class98_Sub38)this.aClass148_5855.method2418(32);
                synchronized (class98_Sub38) {
                    final int method1466 = class98_Sub38.method1466(this);
                    if (method1466 < 0) {
                        class98_Sub38.anInt4186 = 0;
                        this.method1378(class98_Sub38);
                    }
                    else {
                        class98_Sub38.anInt4186 = method1466;
                        this.method1372(class98_Sub38.aClass98_836, class98_Sub38);
                    }
                }
                if (n2 != 0) {
                    continue;
                }
            }
            return;
        }
        this.method1376(array, n, n2);
    }
    
    private final void method1372(Class98 aClass98_836, final Class98_Sub38 class98_Sub38) {
        while (aClass98_836 != this.aClass148_5855.aClass98_1198 && ((Class98_Sub38)aClass98_836).anInt4186 <= class98_Sub38.anInt4186) {
            aClass98_836 = aClass98_836.aClass98_836;
        }
        Class279.method3322(aClass98_836, class98_Sub38, (byte)24);
        this.anInt5857 = ((Class98_Sub38)this.aClass148_5855.aClass98_1198.aClass98_836).anInt4186;
    }
    
    private final void method1373(final int n) {
        for (Class98_Sub31 class98_Sub31 = (Class98_Sub31)this.aClass148_5854.method2418(32); class98_Sub31 != null; class98_Sub31 = (Class98_Sub31)this.aClass148_5854.method2417(114)) {
            class98_Sub31.method1321(n);
        }
    }
    
    @Override
    final synchronized void method1321(int n) {
        while (this.anInt5857 >= 0) {
            if (this.anInt5856 + n < this.anInt5857) {
                this.anInt5856 += n;
                this.method1373(n);
            }
            else {
                final int n2 = this.anInt5857 - this.anInt5856;
                this.method1373(n2);
                n -= n2;
                this.anInt5856 += n2;
                this.method1375();
                final Class98_Sub38 class98_Sub38 = (Class98_Sub38)this.aClass148_5855.method2418(32);
                synchronized (class98_Sub38) {
                    final int method1466 = class98_Sub38.method1466(this);
                    if (method1466 < 0) {
                        class98_Sub38.anInt4186 = 0;
                        this.method1378(class98_Sub38);
                    }
                    else {
                        class98_Sub38.anInt4186 = method1466;
                        this.method1372(class98_Sub38.aClass98_836, class98_Sub38);
                    }
                }
                if (n != 0) {
                    continue;
                }
            }
            return;
        }
        this.method1373(n);
    }
    
    final synchronized void method1374(final Class98_Sub31 class98_Sub31) {
        class98_Sub31.method942(44);
    }
    
    private final void method1375() {
        if (this.anInt5856 > 0) {
            for (Class98_Sub38 class98_Sub38 = (Class98_Sub38)this.aClass148_5855.method2418(32); class98_Sub38 != null; class98_Sub38 = (Class98_Sub38)this.aClass148_5855.method2417(104)) {
                final Class98_Sub38 class98_Sub39 = class98_Sub38;
                class98_Sub39.anInt4186 -= this.anInt5856;
            }
            this.anInt5857 -= this.anInt5856;
            this.anInt5856 = 0;
        }
    }
    
    @Override
    final Class98_Sub31 method1327() {
        return (Class98_Sub31)this.aClass148_5854.method2417(106);
    }
    
    @Override
    final int method1326() {
        return 0;
    }
    
    private final void method1376(final int[] array, final int n, final int n2) {
        for (Class98_Sub31 class98_Sub31 = (Class98_Sub31)this.aClass148_5854.method2418(32); class98_Sub31 != null; class98_Sub31 = (Class98_Sub31)this.aClass148_5854.method2417(113)) {
            class98_Sub31.method1324(array, n, n2);
        }
    }
    
    final synchronized int method1377() {
        return this.aClass148_5854.method2424(0);
    }
    
    @Override
    final Class98_Sub31 method1322() {
        return (Class98_Sub31)this.aClass148_5854.method2418(32);
    }
    
    private final void method1378(final Class98_Sub38 class98_Sub38) {
        class98_Sub38.method942(65);
        class98_Sub38.method1465();
        final Class98 aClass98_836 = this.aClass148_5855.aClass98_1198.aClass98_836;
        if (aClass98_836 == this.aClass148_5855.aClass98_1198) {
            this.anInt5857 = -1;
        }
        else {
            this.anInt5857 = ((Class98_Sub38)aClass98_836).anInt4186;
        }
    }
    
    public Class98_Sub31_Sub3() {
        this.aClass148_5854 = new Class148();
        this.aClass148_5855 = new Class148();
        this.anInt5857 = -1;
        this.anInt5856 = 0;
    }
}
