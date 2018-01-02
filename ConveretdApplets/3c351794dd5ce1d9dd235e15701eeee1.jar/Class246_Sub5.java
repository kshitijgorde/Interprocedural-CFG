// 
// Decompiled by Procyon v0.5.30
// 

final class Class246_Sub5 extends Class246
{
    int anInt5093;
    private static boolean[] aBooleanArray5094;
    Class246_Sub4_Sub2_Sub1[] aClass246_Sub4_Sub2_Sub1Array5095;
    private boolean aBoolean5096;
    private Class218 aClass218_5097;
    private static boolean[] aBooleanArray5098;
    boolean aBoolean5099;
    private long aLong5100;
    private long aLong5101;
    Class148 aClass148_5102;
    private int anInt5103;
    Class242 aClass242_5104;
    private int anInt5105;
    int anInt5106;
    private boolean aBoolean5107;
    boolean aBoolean5108;
    
    final void method3114() {
        this.aBoolean5096 = true;
    }
    
    final Class242 method3115() {
        this.aClass242_5104.aClass358_1850.method3886((byte)(-17));
        for (int i = 0; i < this.aClass246_Sub4_Sub2_Sub1Array5095.length; ++i) {
            if (this.aClass246_Sub4_Sub2_Sub1Array5095[i] != null && this.aClass246_Sub4_Sub2_Sub1Array5095[i].aClass246_Sub9_6492 != null) {
                this.aClass242_5104.aClass358_1850.method3891(this.aClass246_Sub4_Sub2_Sub1Array5095[i], 8);
            }
        }
        return this.aClass242_5104;
    }
    
    final Class242 method3116() {
        return this.aClass242_5104;
    }
    
    static final Class246_Sub5 method3117(final int n, final boolean b) {
        if (Class273.anInt2039 != Class258.anInt1952) {
            final Class246_Sub5 class246_Sub5 = Class373_Sub2.aClass246_Sub5Array5469[Class258.anInt1952];
            Class258.anInt1952 = (Class258.anInt1952 + 1 & Class224_Sub1.anIntArray5034[Class337_Sub1.anInt5497]);
            class246_Sub5.method3124(n, b);
            return class246_Sub5;
        }
        return new Class246_Sub5(n, b);
    }
    
    final void method3118(final ha ha) {
        this.aClass242_5104.aClass358_1850.method3886((byte)99);
        for (Class246_Sub9 class246_Sub9 = (Class246_Sub9)this.aClass218_5097.method2803((byte)15); class246_Sub9 != null; class246_Sub9 = (Class246_Sub9)this.aClass218_5097.method2809(false)) {
            class246_Sub9.method3134(this.aLong5100, ha, (byte)38);
        }
    }
    
    private final void method3119(final ha ha, final Class87[] array, final boolean b) {
        for (int i = 0; i < 32; ++i) {
            Class246_Sub5.aBooleanArray5098[i] = false;
        }
    Label_0149:
        for (Class246_Sub9 class246_Sub9 = (Class246_Sub9)this.aClass218_5097.method2803((byte)15); class246_Sub9 != null; class246_Sub9 = (Class246_Sub9)this.aClass218_5097.method2809(false)) {
            if (array != null) {
                for (int j = 0; j < array.length; ++j) {
                    if (class246_Sub9.aClass87_5131 == array[j] || class246_Sub9.aClass87_5131 == array[j].aClass87_657) {
                        Class246_Sub5.aBooleanArray5098[j] = true;
                        class246_Sub9.method3138(-1);
                        class246_Sub9.aBoolean5139 = false;
                        continue Label_0149;
                    }
                }
            }
            if (!b) {
                if (class246_Sub9.anInt5135 == 0) {
                    class246_Sub9.method2965((byte)122);
                    --this.anInt5103;
                }
                else {
                    class246_Sub9.aBoolean5139 = true;
                }
            }
        }
        if (array != null) {
            for (int n = 0; n < array.length && n != 32; ++n) {
                if (this.anInt5103 == 32) {
                    break;
                }
                if (!Class246_Sub5.aBooleanArray5098[n]) {
                    this.aClass218_5097.method2808(true, new Class246_Sub9(ha, array[n], this, this.aLong5101));
                    ++this.anInt5103;
                    Class246_Sub5.aBooleanArray5098[n] = true;
                }
            }
        }
    }
    
    final void method3120(final ha ha, final long aLong5101, final Class87[] array, final Class35[] array2, final boolean b) {
        if (!this.aBoolean5099) {
            this.method3119(ha, array, b);
            this.method3122(array2, b);
            this.aLong5101 = aLong5101;
        }
    }
    
    final boolean method3121(final ha ha, final long aLong5100) {
        if (this.aLong5101 != this.aLong5100) {
            this.method3114();
        }
        else {
            this.method3128();
        }
        if (aLong5100 - this.aLong5101 > 750L) {
            this.method3129();
            return false;
        }
        final int n = (int)(aLong5100 - this.aLong5100);
        if (this.aBoolean5107) {
            for (Class246_Sub9 class246_Sub9 = (Class246_Sub9)this.aClass218_5097.method2803((byte)15); class246_Sub9 != null; class246_Sub9 = (Class246_Sub9)this.aClass218_5097.method2809(false)) {
                for (int i = 0; i < class246_Sub9.aClass92_5132.anInt784; ++i) {
                    class246_Sub9.method3135(ha, !this.aBoolean5096, aLong5100, -64, 1);
                }
            }
            this.aBoolean5107 = false;
        }
        for (Class246_Sub9 class246_Sub10 = (Class246_Sub9)this.aClass218_5097.method2803((byte)15); class246_Sub10 != null; class246_Sub10 = (Class246_Sub9)this.aClass218_5097.method2809(false)) {
            class246_Sub10.method3135(ha, !this.aBoolean5096, aLong5100, -64, n);
        }
        this.aLong5100 = aLong5100;
        return true;
    }
    
    private final void method3122(final Class35[] array, final boolean b) {
        for (int i = 0; i < 8; ++i) {
            Class246_Sub5.aBooleanArray5094[i] = false;
        }
    Label_0141:
        for (Class98_Sub46_Sub6 class98_Sub46_Sub6 = (Class98_Sub46_Sub6)this.aClass148_5102.method2418(32); class98_Sub46_Sub6 != null; class98_Sub46_Sub6 = (Class98_Sub46_Sub6)this.aClass148_5102.method2417(91)) {
            if (array != null) {
                for (int j = 0; j < array.length; ++j) {
                    if (class98_Sub46_Sub6.aClass35_5971 == array[j] || class98_Sub46_Sub6.aClass35_5971 == array[j].aClass35_328) {
                        Class246_Sub5.aBooleanArray5094[j] = true;
                        class98_Sub46_Sub6.method1547(-121);
                        continue Label_0141;
                    }
                }
            }
            if (!b) {
                class98_Sub46_Sub6.method942(111);
                --this.anInt5105;
                if (class98_Sub46_Sub6.method1522((byte)92)) {
                    class98_Sub46_Sub6.method1524((byte)(-90));
                    --Class340.anInt2849;
                }
            }
        }
        if (array != null) {
            for (int n = 0; n < array.length && n != 8; ++n) {
                if (this.anInt5105 == 8) {
                    break;
                }
                if (!Class246_Sub5.aBooleanArray5094[n]) {
                    Class98_Sub46 class98_Sub46 = null;
                    if (array[n].method331((byte)110).anInt508 == 1 && Class340.anInt2849 < 32) {
                        class98_Sub46 = new Class98_Sub46_Sub6(array[n], this);
                        Class246_Sub3_Sub3.aClass254_6152.method3185((byte)(-14), class98_Sub46, array[n].anInt329);
                        ++Class340.anInt2849;
                    }
                    if (class98_Sub46 == null) {
                        class98_Sub46 = new Class98_Sub46_Sub6(array[n], this);
                    }
                    this.aClass148_5102.method2419(class98_Sub46, -20911);
                    ++this.anInt5105;
                    Class246_Sub5.aBooleanArray5094[n] = true;
                }
            }
        }
    }
    
    final void method3123(final int anInt5106, final int n, final int n2, final int n3, final int n4) {
        this.anInt5106 = anInt5106;
    }
    
    private final void method3124(final int n, final boolean aBoolean5108) {
        Class267.aClass218_2002.method2808(true, this);
        this.aLong5101 = n;
        this.aLong5100 = n;
        this.aBoolean5107 = true;
        this.aBoolean5108 = aBoolean5108;
    }
    
    public static void method3125() {
        Class246_Sub5.aBooleanArray5098 = null;
        Class246_Sub5.aBooleanArray5094 = null;
    }
    
    final void method3126(final long aLong5101) {
        this.aLong5101 = aLong5101;
    }
    
    final void method3127() {
        this.aBoolean5107 = true;
    }
    
    private final void method3128() {
        this.aBoolean5096 = false;
    }
    
    final void method3129() {
        this.aBoolean5099 = true;
        for (Class98_Sub46_Sub6 class98_Sub46_Sub6 = (Class98_Sub46_Sub6)this.aClass148_5102.method2418(32); class98_Sub46_Sub6 != null; class98_Sub46_Sub6 = (Class98_Sub46_Sub6)this.aClass148_5102.method2417(111)) {
            if (class98_Sub46_Sub6.aClass66_5973.anInt508 == 1) {
                class98_Sub46_Sub6.method1524((byte)(-90));
            }
        }
        for (int i = 0; i < this.aClass246_Sub4_Sub2_Sub1Array5095.length; ++i) {
            if (this.aClass246_Sub4_Sub2_Sub1Array5095[i] != null) {
                this.aClass246_Sub4_Sub2_Sub1Array5095[i].method3113();
                this.aClass246_Sub4_Sub2_Sub1Array5095[i] = null;
            }
        }
        this.anInt5093 = 0;
        this.aClass218_5097 = new Class218();
        this.anInt5103 = 0;
        this.aClass148_5102 = new Class148();
        this.anInt5105 = 0;
        this.method2965((byte)127);
        Class373_Sub2.aClass246_Sub5Array5469[Class273.anInt2039] = this;
        Class273.anInt2039 = (Class273.anInt2039 + 1 & Class224_Sub1.anIntArray5034[Class337_Sub1.anInt5497]);
    }
    
    private Class246_Sub5(final int n, final boolean b) {
        this.anInt5093 = 0;
        this.aBoolean5099 = false;
        this.aBoolean5096 = false;
        this.aClass218_5097 = new Class218();
        this.anInt5103 = 0;
        this.aClass148_5102 = new Class148();
        this.anInt5105 = 0;
        this.aBoolean5108 = false;
        this.aBoolean5107 = false;
        this.aClass242_5104 = new Class242();
        this.aClass246_Sub4_Sub2_Sub1Array5095 = new Class246_Sub4_Sub2_Sub1[8192];
        this.method3124(n, b);
    }
    
    static {
        Class246_Sub5.aBooleanArray5094 = new boolean[8];
        Class246_Sub5.aBooleanArray5098 = new boolean[32];
    }
}
