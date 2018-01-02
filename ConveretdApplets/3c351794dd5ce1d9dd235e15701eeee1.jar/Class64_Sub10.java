// 
// Decompiled by Procyon v0.5.30
// 

final class Class64_Sub10 extends Class64
{
    static int anInt3664;
    static IncomingOpcode aClass58_3665;
    static int anInt3666;
    static Class85 aClass85_3667;
    
    @Override
    final void method550(final int n, final int anInt494) {
        try {
            super.anInt494 = anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fe.B(" + n + ',' + anInt494 + ')');
        }
    }
    
    Class64_Sub10(final int n, final Class98_Sub27 class98_Sub27) {
        super(n, class98_Sub27);
    }
    
    final int method592(final byte b) {
        try {
            if (b < 119) {
                Class64_Sub10.anInt3666 = -111;
            }
            return super.anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fe.E(" + b + ')');
        }
    }
    
    @Override
    final void method551(final byte b) {
        try {
            if (b <= 118) {
                method593(44);
            }
            if (~super.anInt494 != 0xFFFFFFFE && ~super.anInt494 != -1) {
                super.anInt494 = this.method552(0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fe.C(" + b + ')');
        }
    }
    
    Class64_Sub10(final Class98_Sub27 class98_Sub27) {
        super(class98_Sub27);
    }
    
    @Override
    final int method552(final int n) {
        try {
            if (n != 0) {
                this.method550(110, -78);
            }
            return 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fe.A(" + n + ')');
        }
    }
    
    @Override
    final int method556(final int n, final int n2) {
        try {
            return 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fe.F(" + n + ',' + n2 + ')');
        }
    }
    
    public static void method593(final int n) {
        try {
            Class64_Sub10.aClass58_3665 = null;
            Class64_Sub10.aClass85_3667 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fe.G(" + n + ')');
        }
    }
    
    static final boolean method594(final int n, final int n2, final int n3) {
        try {
            return n2 != 6 || ~(n & 0x100100) != -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fe.D(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static {
        Class64_Sub10.anInt3666 = -1;
        Class64_Sub10.anInt3664 = -1;
        Class64_Sub10.aClass58_3665 = new IncomingOpcode(64, 6);
        Class64_Sub10.aClass85_3667 = new Class85(12, -1);
    }
}
