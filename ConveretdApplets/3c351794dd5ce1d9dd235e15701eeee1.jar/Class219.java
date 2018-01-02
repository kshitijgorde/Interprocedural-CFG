// 
// Decompiled by Procyon v0.5.30
// 

final class Class219
{
    int anInt1637;
    int anInt1638;
    int anInt1639;
    static OutgoingOpcode aClass171_1640;
    static Class77 aClass77_1641;
    static Class361 aClass361_1642;
    int anInt1643;
    int anInt1644;
    int anInt1645;
    
    public static void method2813(final boolean b) {
        try {
            if (b) {
                Class219.aClass361_1642 = null;
            }
            Class219.aClass77_1641 = null;
            Class219.aClass361_1642 = null;
            Class219.aClass171_1640 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oaa.B(" + b + ')');
        }
    }
    
    final void method2814(final Class219 class219, final int n) {
        try {
            if (n != 6) {
                this.method2815(127);
            }
            this.anInt1638 = class219.anInt1638;
            this.anInt1639 = class219.anInt1639;
            this.anInt1643 = class219.anInt1643;
            this.anInt1645 = class219.anInt1645;
            this.anInt1644 = class219.anInt1644;
            this.anInt1637 = class219.anInt1637;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oaa.A(" + ((class219 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final Class219 method2815(final int n) {
        try {
            if (n != 128) {
                this.method2814(null, 107);
            }
            return new Class219(this.anInt1643, this.anInt1637, this.anInt1645, this.anInt1638, this.anInt1644, this.anInt1639);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oaa.C(" + n + ')');
        }
    }
    
    Class219(final int anInt1643) {
        this.anInt1637 = 128;
        this.anInt1645 = 128;
        try {
            this.anInt1643 = anInt1643;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oaa.<init>(" + anInt1643 + ')');
        }
    }
    
    private Class219(final int anInt1643, final int anInt1644, final int anInt1645, final int anInt1646, final int anInt1647, final int anInt1648) {
        this.anInt1637 = 128;
        this.anInt1645 = 128;
        try {
            this.anInt1638 = anInt1646;
            this.anInt1644 = anInt1647;
            this.anInt1639 = anInt1648;
            this.anInt1643 = anInt1643;
            this.anInt1645 = anInt1645;
            this.anInt1637 = anInt1644;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oaa.<init>(" + anInt1643 + ',' + anInt1644 + ',' + anInt1645 + ',' + anInt1646 + ',' + anInt1647 + ',' + anInt1648 + ')');
        }
    }
    
    static {
        Class219.aClass171_1640 = new OutgoingOpcode(7, 6);
    }
}
