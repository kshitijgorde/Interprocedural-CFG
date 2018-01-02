// 
// Decompiled by Procyon v0.5.30
// 

final class Class174 implements Runnable
{
    private Class98_Sub5[] aClass98_Sub5Array1345;
    private long aLong1346;
    private int[] anIntArray1347;
    private volatile boolean aBoolean1348;
    private volatile boolean aBoolean1349;
    private Class245 aClass245_1350;
    private ha aHa1351;
    private volatile boolean aBoolean1352;
    private int anInt1353;
    
    @Override
    public final void run() {
        while (this.aBoolean1348) {
            this.method2567();
        }
    }
    
    final void method2561() {
        this.aBoolean1349 = true;
        synchronized (this) {
            this.notify();
        }
    }
    
    final long method2562() {
        return this.aLong1346;
    }
    
    final boolean method2563() {
        return this.aClass245_1350 == null || (!this.aBoolean1352 && this.aClass245_1350.method2959(124));
    }
    
    final void method2564(final Class245 aClass245_1350) {
        if (this.aClass245_1350 != null) {
            this.aClass245_1350.method2957(null, false);
        }
        this.aClass245_1350 = aClass245_1350;
        if (this.aClass245_1350 != null) {
            this.aClass245_1350.method2957(this, false);
        }
    }
    
    final void method2565() {
        this.aBoolean1349 = false;
        this.aBoolean1348 = false;
        synchronized (this) {
            this.notify();
        }
    }
    
    final void method2566() {
        this.aBoolean1349 = false;
        synchronized (this) {
            this.notify();
        }
    }
    
    private final void method2567() {
        this.aHa1351.method1807(this.anInt1353);
        while (!this.aBoolean1349 && this.aBoolean1348) {
            if (this.aClass245_1350 != null && !this.aClass245_1350.method2959(114)) {
                this.aBoolean1352 = true;
                final Class246 method2956 = this.aClass245_1350.method2956(0);
                if (method2956 instanceof Class246_Sub3) {
                    final Class246_Sub3 class246_Sub3 = (Class246_Sub3)method2956;
                    if (class246_Sub3.aBoolean5078) {
                        class246_Sub3.method2988(Class98_Sub10_Sub30.aHa5709, 116);
                    }
                    else {
                        Class289.method3407(class246_Sub3, this.aClass98_Sub5Array1345);
                        if (Class284_Sub1.aClass43_5177 == null) {
                            continue;
                        }
                        Class284_Sub1.aClass43_5177.method411((byte)53, class246_Sub3.anInt5080, this.aClass245_1350.aString1866, -256, -16777216, class246_Sub3.anInt5085);
                    }
                }
                else {
                    final int anInt5151 = ((Class246_Sub10)method2956).anInt5151;
                    if (anInt5151 < 1 || anInt5151 > 4) {
                        continue;
                    }
                    final s s = Class78.aSArray594[anInt5151 - 1];
                    for (int i = 0; i < Class259.anInt1959 + Class259.anInt1959; ++i) {
                        for (int j = 0; j < Class259.anInt1959 + Class259.anInt1959; ++j) {
                            if (Class34.aBooleanArrayArrayArray325[anInt5151 - 1][i][j]) {
                                final int n = Class241.anInt1845 - Class259.anInt1959 + i;
                                final int n2 = Class64_Sub26.anInt3714 - Class259.anInt1959 + j;
                                if (n >= 0 && n < s.anInt2203 && n2 >= 0 && n2 < s.anInt2204) {
                                    Class98_Sub10_Sub30.aHa5709.H(n << Class151_Sub8.anInt5015, s.method3420(n2, -12639, n), n2 << Class151_Sub8.anInt5015, this.anIntArray1347);
                                    if (Class200.method2692(this.anIntArray1347[0]) == this.anInt1353 - 1) {
                                        s.method3425(n, n2);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else {
                this.aBoolean1352 = false;
                this.aLong1346 = Class376.aClass142_3174.method2308((byte)69);
                synchronized (this) {
                    try {
                        this.wait();
                    }
                    catch (InterruptedException ex) {}
                }
            }
        }
        this.aHa1351.method1774(this.anInt1353);
        while (this.aBoolean1349 && this.aBoolean1348) {
            synchronized (this) {
                try {
                    this.wait();
                }
                catch (InterruptedException ex2) {}
            }
        }
    }
    
    Class174(final int anInt1353, final ha aHa1351) {
        this.aClass98_Sub5Array1345 = new Class98_Sub5[8];
        this.anIntArray1347 = new int[3];
        this.aBoolean1349 = true;
        this.aBoolean1348 = true;
        this.aBoolean1352 = false;
        this.anInt1353 = anInt1353;
        this.aHa1351 = aHa1351;
    }
}
