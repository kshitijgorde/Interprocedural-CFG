import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class119_Sub3 extends Class119
{
    private int anInt4728;
    private int anInt4729;
    private int anInt4730;
    private int anInt4731;
    private int anInt4732;
    private int anInt4733;
    private int anInt4734;
    private int anInt4735;
    
    @Override
    final void method2178(final int n, final int n2, final int n3) {
    }
    
    @Override
    final void method2179(final byte b, final int n, final int n2) {
        try {
            za_Sub2.method1685(n2 * this.anInt4731 >> -168057524, n2 * this.anInt4728 >> 1090097196, this.anInt4734 * n2 >> -676575636, true, n * this.anInt4732 >> -1065106292, n * this.anInt4733 >> 730639084, n * this.anInt4730 >> -264501236, super.anInt985, n * this.anInt4729 >> 1808074956, n2 * this.anInt4735 >> -1661896212);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pd.E(" + b + ',' + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final void method2174(final int n, final int n2, final int n3) {
        try {
            if (n3 != -5515) {
                this.method2179((byte)87, -113, -49);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pd.C(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static final void method2185(final int n, final int n2) {
        try {
            Class185.method2628(n2, -116, n).method1621(n - 5);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pd.F(" + n + ',' + n2 + ')');
        }
    }
    
    static final void method2186(final int n, final ha ha) {
        try {
            if (n >= -9) {
                method2185(-4, 88);
            }
            if (IOException_Sub1.aClass148_30.method2424(0) != 0) {
                if (Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4042.method583((byte)121) != 0) {
                    if (Class153.aHa1225 == null) {
                        final Canvas canvas = new Canvas();
                        canvas.setSize(36, 32);
                        Class153.aHa1225 = Class76_Sub11.method771(0, canvas, 122, Class284_Sub2_Sub2.aD6203, 0, Class212.aClass207_1603);
                        Class238.aClass43_1828 = Class153.aHa1225.method1804(Class109.method1733((byte)111, 0, Class244.anInt1860, Class36.aClass207_348), Class324.method3680(Class332_Sub2.aClass207_5423, Class244.anInt1860, 0), true);
                    }
                    for (Class98_Sub12 class98_Sub12 = (Class98_Sub12)IOException_Sub1.aClass148_30.method2418(32); class98_Sub12 != null; class98_Sub12 = (Class98_Sub12)IOException_Sub1.aClass148_30.method2417(106)) {
                        Class98_Sub46_Sub19.aClass205_6068.method2722(Class238.aClass43_1828, false, class98_Sub12.anInt3876, class98_Sub12.anInt3873, class98_Sub12.anInt3875, class98_Sub12.aBoolean3878 ? Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aClass313_6518 : null, Class153.aHa1225, class98_Sub12.anInt3871, false, class98_Sub12.anInt3874, ha, false);
                        class98_Sub12.method942(55);
                    }
                }
                else {
                    for (Class98_Sub12 class98_Sub13 = (Class98_Sub12)IOException_Sub1.aClass148_30.method2418(32); class98_Sub13 != null; class98_Sub13 = (Class98_Sub12)IOException_Sub1.aClass148_30.method2417(114)) {
                        Class98_Sub46_Sub19.aClass205_6068.method2722(Class69_Sub2.aClass43_5336, false, class98_Sub13.anInt3876, class98_Sub13.anInt3873, class98_Sub13.anInt3875, class98_Sub13.aBoolean3878 ? Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aClass313_6518 : null, ha, class98_Sub13.anInt3871, false, class98_Sub13.anInt3874, ha, false);
                        class98_Sub13.method942(84);
                    }
                    Class98_Sub43.method1481(2);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pd.D(" + n + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final int method2187(final byte b, final int n) {
        try {
            if (b >= -67) {
                return -87;
            }
            return n & 0xFF;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pd.A(" + b + ',' + n + ')');
        }
    }
    
    Class119_Sub3(final int anInt4728, final int anInt4729, final int anInt4730, final int anInt4731, final int anInt4732, final int anInt4733, final int anInt4734, final int anInt4735, final int n, final int n2) {
        super(-1, n, n2);
        try {
            this.anInt4733 = anInt4731;
            this.anInt4735 = anInt4734;
            this.anInt4734 = anInt4732;
            this.anInt4729 = anInt4729;
            this.anInt4732 = anInt4733;
            this.anInt4728 = anInt4728;
            this.anInt4731 = anInt4730;
            this.anInt4730 = anInt4735;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pd.<init>(" + anInt4728 + ',' + anInt4729 + ',' + anInt4730 + ',' + anInt4731 + ',' + anInt4732 + ',' + anInt4733 + ',' + anInt4734 + ',' + anInt4735 + ',' + n + ',' + n2 + ')');
        }
    }
}
