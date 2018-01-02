// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub46_Sub18 extends Class98_Sub46
{
    int anInt6055;
    static boolean aBoolean6056;
    
    public Class98_Sub46_Sub18() {
        this.anInt6055 = 0;
    }
    
    private final void method1627(final int n, final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (b == 95) {
                if (n == 2) {
                    this.anInt6055 = class98_Sub22.readShort((byte)127);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lm.B(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    final void method1628(final Class98_Sub22 class98_Sub22, final int n) {
        try {
            if (n > -124) {
                this.method1628(null, -65);
            }
            while (true) {
                final int unsignedByte = class98_Sub22.readUnsignedByte((byte)124);
                if (~unsignedByte == -1) {
                    break;
                }
                this.method1627(unsignedByte, class98_Sub22, (byte)95);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lm.A(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    static {
        Class98_Sub46_Sub18.aBoolean6056 = false;
    }
}
