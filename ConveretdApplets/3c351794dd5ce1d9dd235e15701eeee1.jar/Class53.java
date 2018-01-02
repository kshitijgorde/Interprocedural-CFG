// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class53
{
    int anInt426;
    int anInt427;
    static long aLong428;
    int anInt429;
    static int anInt430;
    static IncomingOpcode aClass58_431;
    
    final boolean method493(final int n) {
        try {
            if (n != 9811) {
                this.method496((byte)(-66));
            }
            return ~(this.anInt427 & 0x2) != -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dl.A(" + n + ')');
        }
    }
    
    public static void method494(final byte b) {
        try {
            Class53.aClass58_431 = null;
            if (b != 116) {
                Class53.aLong428 = 16L;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dl.B(" + b + ')');
        }
    }
    
    final boolean method495(final byte b) {
        try {
            if (b <= 119) {
                method494((byte)(-66));
            }
            return (this.anInt427 & 0x8) != 0x0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dl.D(" + b + ')');
        }
    }
    
    final boolean method496(final byte b) {
        try {
            if (b <= 42) {
                this.method496((byte)70);
            }
            return (this.anInt427 & 0x4) != 0x0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dl.C(" + b + ')');
        }
    }
    
    final boolean method497(final boolean b) {
        try {
            return b || (0x1 & this.anInt427) != 0x0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dl.E(" + b + ')');
        }
    }
    
    static {
        Class53.aLong428 = 0L;
        Class53.aClass58_431 = new IncomingOpcode(8, 6);
    }
}
