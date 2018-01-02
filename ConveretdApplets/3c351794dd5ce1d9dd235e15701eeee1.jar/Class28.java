// 
// Decompiled by Procyon v0.5.30
// 

final class Class28
{
    float aFloat281;
    int anInt282;
    int anInt283;
    int anInt284;
    int anInt285;
    static Class128 aClass128_286;
    Class48 aClass48_287;
    float aFloat288;
    float aFloat289;
    int anInt290;
    float aFloat291;
    int anInt292;
    float aFloat293;
    static float[] aFloatArray294;
    float aFloat295;
    
    final void method297(final int n, final Class98_Sub22 class98_Sub22) {
        try {
            this.aFloat295 = 8 * class98_Sub22.readUnsignedByte((byte)96) / 255.0f;
            this.aFloat291 = class98_Sub22.readUnsignedByte((byte)(-122)) * 8 / 255.0f;
            if (n != -50) {
                this.method297(-51, null);
            }
            this.aFloat293 = 8 * class98_Sub22.readUnsignedByte((byte)1) / 255.0f;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ca.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method298(final int n) {
        try {
            Class28.aClass128_286 = null;
            Class28.aFloatArray294 = null;
            if (n != -15136) {
                method298(-41);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ca.B(" + n + ')');
        }
    }
    
    final boolean method299(final boolean b, final Class28 class28) {
        try {
            return b && ~this.anInt290 == ~class28.anInt290 && this.aFloat281 == class28.aFloat281 && this.aFloat288 == class28.aFloat288 && this.aFloat289 == class28.aFloat289 && class28.aFloat291 == this.aFloat291 && class28.aFloat295 == this.aFloat295 && class28.aFloat293 == this.aFloat293 && ~class28.anInt285 == ~this.anInt285 && this.anInt283 == class28.anInt283 && this.aClass48_287 == class28.aClass48_287;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ca.C(" + b + ',' + ((class28 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public Class28() {
        this.aFloat291 = 0.25f;
        this.aFloat293 = 1.0f;
        this.aFloat295 = 1.0f;
        try {
            this.aFloat288 = 0.69921875f;
            this.anInt282 = -50;
            this.aClass48_287 = Class246_Sub7.aClass48_5119;
            this.anInt292 = -50;
            this.anInt285 = Class189.anInt1455;
            this.anInt284 = -60;
            this.anInt290 = Class299_Sub2.anInt5298;
            this.anInt283 = 0;
            this.aFloat281 = 1.1523438f;
            this.aFloat289 = 1.2f;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ca.<init>()");
        }
    }
    
    Class28(final Class98_Sub22 class98_Sub22) {
        this.aFloat291 = 0.25f;
        this.aFloat293 = 1.0f;
        this.aFloat295 = 1.0f;
        try {
            final int unsignedByte = class98_Sub22.readUnsignedByte((byte)(-100));
            if (~Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub10_4070.method592((byte)126) == 0xFFFFFFFE && Class98_Sub37.aHa4185.method1822() > 0) {
                if (~(0x1 & unsignedByte) != -1) {
                    this.anInt290 = class98_Sub22.readInt(-2);
                }
                else {
                    this.anInt290 = Class299_Sub2.anInt5298;
                }
                if ((0x2 & unsignedByte) == 0x0) {
                    this.aFloat281 = 1.1523438f;
                }
                else {
                    this.aFloat281 = class98_Sub22.readShort((byte)127) / 256.0f;
                }
                if ((0x4 & unsignedByte) != 0x0) {
                    this.aFloat288 = class98_Sub22.readShort((byte)127) / 256.0f;
                }
                else {
                    this.aFloat288 = 0.69921875f;
                }
                if (~(0x8 & unsignedByte) != -1) {
                    this.aFloat289 = class98_Sub22.readShort((byte)127) / 256.0f;
                }
                else {
                    this.aFloat289 = 1.2f;
                }
            }
            else {
                if (~(0x1 & unsignedByte) != -1) {
                    class98_Sub22.readInt(-2);
                }
                if (~(0x2 & unsignedByte) != -1) {
                    class98_Sub22.readShort((byte)127);
                }
                if ((0x4 & unsignedByte) != 0x0) {
                    class98_Sub22.readShort((byte)127);
                }
                if ((unsignedByte & 0x8) != 0x0) {
                    class98_Sub22.readShort((byte)127);
                }
                this.aFloat281 = 1.1523438f;
                this.aFloat289 = 1.2f;
                this.anInt290 = Class299_Sub2.anInt5298;
                this.aFloat288 = 0.69921875f;
            }
            if ((0x10 & unsignedByte) != 0x0) {
                this.anInt282 = class98_Sub22.readUShort(false);
                this.anInt284 = class98_Sub22.readUShort(false);
                this.anInt292 = class98_Sub22.readUShort(false);
            }
            else {
                this.anInt284 = -60;
                this.anInt292 = -50;
                this.anInt282 = -50;
            }
            if (~(0x20 & unsignedByte) == -1) {
                this.anInt285 = Class189.anInt1455;
            }
            else {
                this.anInt285 = class98_Sub22.readInt(-2);
            }
            if ((unsignedByte & 0x40) == 0x0) {
                this.anInt283 = 0;
            }
            else {
                this.anInt283 = class98_Sub22.readShort((byte)127);
            }
            if ((unsignedByte & 0x80) == 0x0) {
                this.aClass48_287 = Class246_Sub7.aClass48_5119;
            }
            else {
                this.aClass48_287 = Class13.method217(5, class98_Sub22.readShort((byte)127), class98_Sub22.readShort((byte)127), class98_Sub22.readShort((byte)127), class98_Sub22.readShort((byte)127), class98_Sub22.readShort((byte)127), class98_Sub22.readShort((byte)127));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ca.<init>(" + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class28.aClass128_286 = new Class128();
        Class28.aFloatArray294 = new float[2];
    }
}
