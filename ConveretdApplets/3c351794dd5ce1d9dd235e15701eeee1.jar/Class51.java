// 
// Decompiled by Procyon v0.5.30
// 

final class Class51
{
    Class42_Sub1[] aClass42_Sub1Array420;
    Class42_Sub4 aClass42_Sub4_421;
    Class42_Sub4 aClass42_Sub4_422;
    Class42_Sub1[] aClass42_Sub1Array423;
    boolean aBoolean424;
    Class42_Sub4 aClass42_Sub4_425;
    
    static final void method487(final int n, final Class98_Sub46 aClass98_Sub46_4262, final Class98_Sub46 class98_Sub46) {
        try {
            if (class98_Sub46.aClass98_Sub46_4265 != null) {
                class98_Sub46.method1524((byte)(-90));
            }
            if (n >= 32) {
                class98_Sub46.aClass98_Sub46_4262 = aClass98_Sub46_4262;
                class98_Sub46.aClass98_Sub46_4265 = aClass98_Sub46_4262.aClass98_Sub46_4265;
                class98_Sub46.aClass98_Sub46_4265.aClass98_Sub46_4262 = class98_Sub46;
                class98_Sub46.aClass98_Sub46_4262.aClass98_Sub46_4265 = class98_Sub46;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dja.A(" + n + ',' + ((aClass98_Sub46_4262 != null) ? "{...}" : "null") + ',' + ((class98_Sub46 != null) ? "{...}" : "null") + ')');
        }
    }
    
    Class51(final ha_Sub1 ha_Sub1) {
        this.aClass42_Sub1Array420 = null;
        this.aClass42_Sub1Array423 = null;
        this.aClass42_Sub4_421 = null;
        this.aClass42_Sub4_425 = null;
        this.aClass42_Sub4_422 = null;
        try {
            this.aBoolean424 = ha_Sub1.aBoolean4434;
            Class64_Sub9.method590(true, ha_Sub1);
            if (this.aBoolean424) {
                this.aClass42_Sub4_422 = new Class42_Sub4(ha_Sub1, 6410, 128, 128, 16, Class98_Sub28_Sub1.method1310(false, Class167.anObject1285, false), 6410);
                this.aClass42_Sub4_425 = new Class42_Sub4(ha_Sub1, 6410, 128, 128, 16, Class98_Sub28_Sub1.method1310(false, Class130.anObject1030, false), 6410);
                final Class118 aClass118_4322 = ha_Sub1.aClass118_4322;
                if (aClass118_4322.method2171(true)) {
                    final byte[] method1310 = Class98_Sub28_Sub1.method1310(false, Class98_Sub41.anObject4203, false);
                    this.aClass42_Sub4_421 = new Class42_Sub4(ha_Sub1, 6408, 128, 128, 16);
                    final Class42_Sub4 class42_Sub4 = new Class42_Sub4(ha_Sub1, 6409, 128, 128, 16, method1310, 6409);
                    if (aClass118_4322.method2172(0, class42_Sub4, 2.0f, this.aClass42_Sub4_421)) {
                        this.aClass42_Sub4_421.method371(35);
                    }
                    else {
                        this.aClass42_Sub4_421.method375(true);
                        this.aClass42_Sub4_421 = null;
                    }
                    class42_Sub4.method375(true);
                }
            }
            else {
                this.aClass42_Sub1Array423 = new Class42_Sub1[16];
                for (int n = 0; ~n > -17; ++n) {
                    this.aClass42_Sub1Array423[n] = new Class42_Sub1(ha_Sub1, 3553, 6410, 128, 128, true, Class98_Sub10_Sub20.method1061(2, 32768, 128 * (128 * n) * 2, Class167.anObject1285), 6410, false);
                }
                this.aClass42_Sub1Array420 = new Class42_Sub1[16];
                for (int n2 = 0; ~n2 > -17; ++n2) {
                    this.aClass42_Sub1Array420[n2] = new Class42_Sub1(ha_Sub1, 3553, 6410, 128, 128, true, Class98_Sub10_Sub20.method1061(2, 32768, 16384 * n2 * 2, Class130.anObject1030), 6410, false);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dja.<init>(" + ((ha_Sub1 != null) ? "{...}" : "null") + ')');
        }
    }
}
