// 
// Decompiled by Procyon v0.5.30
// 

final class Class24
{
    static int anInt223;
    int anInt224;
    int anInt225;
    int anInt226;
    private int anInt227;
    int anInt228;
    byte[] aByteArray229;
    boolean aBoolean230;
    private int anInt231;
    String aString232;
    Class341 aClass341_233;
    int[] anIntArray234;
    int anInt235;
    private int anInt236;
    String[] aStringArray237;
    int anInt238;
    int anInt239;
    private int anInt240;
    boolean aBoolean241;
    static int anInt242;
    private int anInt243;
    int anInt244;
    int anInt245;
    int anInt246;
    int anInt247;
    int anInt248;
    int anInt249;
    int anInt250;
    private int anInt251;
    int anInt252;
    int anInt253;
    private int anInt254;
    static int anInt255;
    private Class377 aClass377_256;
    int anInt257;
    boolean aBoolean258;
    private int anInt259;
    private int anInt260;
    boolean aBoolean261;
    int anInt262;
    String aString263;
    int anInt264;
    int[] anIntArray265;
    
    final boolean method284(final int n, final Interface6 interface6) {
        try {
            int n2;
            if (this.anInt260 == -1) {
                if (~this.anInt259 == 0x0) {
                    return true;
                }
                n2 = interface6.method7(this.anInt259, 7373);
            }
            else {
                n2 = interface6.method6(this.anInt260, 83);
            }
            if (~n2 > ~this.anInt251 || this.anInt227 < n2) {
                return false;
            }
            if (n < 6) {
                this.anInt225 = -120;
            }
            int n3;
            if (this.anInt243 == -1) {
                if (this.anInt240 == -1) {
                    return true;
                }
                n3 = interface6.method7(this.anInt240, 7373);
            }
            else {
                n3 = interface6.method6(this.anInt243, 63);
            }
            return ~n3 <= ~this.anInt254 && ~n3 >= ~this.anInt236;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bq.G(" + n + ',' + ((interface6 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final int method285(final int n, final int n2, final int n3) {
        try {
            if (this.aClass377_256 == null) {
                return n3;
            }
            if (n < 47) {
                this.method289(67, null, 54);
            }
            final Class98_Sub34 class98_Sub34 = (Class98_Sub34)this.aClass377_256.method3990(n2, -1);
            if (class98_Sub34 == null) {
                return n3;
            }
            return class98_Sub34.anInt4126;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bq.D(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    final Class332 method286(final ha ha, final byte b) {
        try {
            Class332 method1758 = (Class332)this.aClass341_233.aClass79_2857.method802(-119, 0x20000 | this.anInt231 | ha.anInt937 << 947659261);
            if (method1758 != null) {
                return method1758;
            }
            this.aClass341_233.aClass207_2852.method2742(-84, this.anInt231);
            final Class324 method1759 = Class324.method3685(this.aClass341_233.aClass207_2852, this.anInt231, 0);
            if (method1759 != null) {
                method1758 = ha.method1758(method1759, true);
                this.aClass341_233.aClass79_2857.method805(this.anInt231 | 0x20000 | ha.anInt937 << 160002877, method1758, (byte)(-80));
            }
            if (b != 92) {
                this.method290(null, -73);
            }
            return method1758;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bq.B(" + ((ha != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    final Class332 method287(final byte b, final ha ha, final boolean b2) {
        try {
            final int n = b2 ? this.anInt225 : this.anInt245;
            final int n2 = ha.anInt937 << 548917245 | n;
            if (b != 92) {
                this.anInt257 = 107;
            }
            Class332 method1758 = (Class332)this.aClass341_233.aClass79_2857.method802(b - 214, n2);
            if (method1758 != null) {
                return method1758;
            }
            if (!this.aClass341_233.aClass207_2852.method2742(-52, n)) {
                return null;
            }
            final Class324 method1759 = Class324.method3685(this.aClass341_233.aClass207_2852, n, 0);
            if (method1759 != null) {
                method1758 = ha.method1758(method1759, true);
                this.aClass341_233.aClass79_2857.method805(n2, method1758, (byte)(-80));
            }
            return method1758;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bq.H(" + b + ',' + ((ha != null) ? "{...}" : "null") + ',' + b2 + ')');
        }
    }
    
    private final void method288(final int n, final int n2, final Class98_Sub22 class98_Sub22) {
        try {
            if (n == 27177) {
                if (n2 != 1) {
                    if (~n2 != 0xFFFFFFFD) {
                        if (n2 != 3) {
                            if (n2 != 4) {
                                if (n2 != 5) {
                                    if (~n2 != 0xFFFFFFF9) {
                                        if (n2 != 7) {
                                            if (n2 == 8) {
                                                this.aBoolean261 = (class98_Sub22.readUnsignedByte((byte)113) == 1);
                                            }
                                            else if (~n2 == 0xFFFFFFF6) {
                                                this.anInt259 = class98_Sub22.readShort((byte)127);
                                                if (this.anInt259 == 65535) {
                                                    this.anInt259 = -1;
                                                }
                                                this.anInt260 = class98_Sub22.readShort((byte)127);
                                                if (~this.anInt260 == 0xFFFF0000) {
                                                    this.anInt260 = -1;
                                                }
                                                this.anInt251 = class98_Sub22.readInt(-2);
                                                this.anInt227 = class98_Sub22.readInt(-2);
                                            }
                                            else if (n2 >= 10 && n2 <= 14) {
                                                this.aStringArray237[-10 + n2] = class98_Sub22.readString((byte)84);
                                            }
                                            else if (~n2 != 0xFFFFFFF0) {
                                                if (~n2 == 0xFFFFFFEF) {
                                                    this.aBoolean241 = false;
                                                }
                                                else if (n2 != 17) {
                                                    if (n2 == 18) {
                                                        this.anInt231 = class98_Sub22.readShort((byte)127);
                                                    }
                                                    else if (~n2 != 0xFFFFFFEC) {
                                                        if (~n2 == 0xFFFFFFEB) {
                                                            this.anInt240 = class98_Sub22.readShort((byte)127);
                                                            if (~this.anInt240 == 0xFFFF0000) {
                                                                this.anInt240 = -1;
                                                            }
                                                            this.anInt243 = class98_Sub22.readShort((byte)127);
                                                            if (this.anInt243 == 65535) {
                                                                this.anInt243 = -1;
                                                            }
                                                            this.anInt254 = class98_Sub22.readInt(-2);
                                                            this.anInt236 = class98_Sub22.readInt(-2);
                                                        }
                                                        else if (~n2 != 0xFFFFFFEA) {
                                                            if (~n2 != 0xFFFFFFE9) {
                                                                if (n2 != 23) {
                                                                    if (~n2 == 0xFFFFFFE7) {
                                                                        this.anInt235 = class98_Sub22.readUShort(false);
                                                                        this.anInt252 = class98_Sub22.readUShort(false);
                                                                    }
                                                                    else if (~n2 == 0xFFFFFF06) {
                                                                        final int unsignedByte = class98_Sub22.readUnsignedByte((byte)(-115));
                                                                        if (this.aClass377_256 == null) {
                                                                            this.aClass377_256 = new Class377(Class48.method453(423660257, unsignedByte));
                                                                        }
                                                                        for (int i = 0; i < unsignedByte; ++i) {
                                                                            final boolean b = class98_Sub22.readUnsignedByte((byte)76) == 1;
                                                                            final int method1186 = class98_Sub22.method1186(n ^ 0xFFFF95AD);
                                                                            Class98 class98;
                                                                            if (b) {
                                                                                class98 = new Class98_Sub15(class98_Sub22.readString((byte)84));
                                                                            }
                                                                            else {
                                                                                class98 = new Class98_Sub34(class98_Sub22.readInt(-2));
                                                                            }
                                                                            this.aClass377_256.method3996(class98, method1186, -1);
                                                                        }
                                                                    }
                                                                }
                                                                else {
                                                                    this.anInt250 = class98_Sub22.readUnsignedByte((byte)(-3));
                                                                    this.anInt253 = class98_Sub22.readUnsignedByte((byte)(-109));
                                                                    this.anInt224 = class98_Sub22.readUnsignedByte((byte)(-116));
                                                                }
                                                            }
                                                            else {
                                                                this.anInt226 = class98_Sub22.readInt(-2);
                                                            }
                                                        }
                                                        else {
                                                            this.anInt239 = class98_Sub22.readInt(-2);
                                                        }
                                                    }
                                                    else {
                                                        this.anInt246 = class98_Sub22.readShort((byte)127);
                                                    }
                                                }
                                                else {
                                                    this.aString232 = class98_Sub22.readString((byte)84);
                                                }
                                            }
                                            else {
                                                final int unsignedByte2 = class98_Sub22.readUnsignedByte((byte)(-124));
                                                this.anIntArray265 = new int[unsignedByte2 * 2];
                                                for (int n3 = 0; ~(2 * unsignedByte2) < ~n3; ++n3) {
                                                    this.anIntArray265[n3] = class98_Sub22.readUShort(false);
                                                }
                                                this.anInt249 = class98_Sub22.readInt(-2);
                                                this.anIntArray234 = new int[class98_Sub22.readUnsignedByte((byte)67)];
                                                for (int n4 = 0; ~this.anIntArray234.length < ~n4; ++n4) {
                                                    this.anIntArray234[n4] = class98_Sub22.readInt(n - 27179);
                                                }
                                                this.aByteArray229 = new byte[unsignedByte2];
                                                for (int n5 = 0; ~n5 > ~unsignedByte2; ++n5) {
                                                    this.aByteArray229[n5] = class98_Sub22.readSignedByte((byte)(-19));
                                                }
                                            }
                                        }
                                        else {
                                            final int unsignedByte3 = class98_Sub22.readUnsignedByte((byte)19);
                                            if ((0x2 & unsignedByte3) == 0x2) {
                                                this.aBoolean230 = true;
                                            }
                                            if ((0x1 & unsignedByte3) == 0x0) {
                                                this.aBoolean258 = false;
                                            }
                                        }
                                    }
                                    else {
                                        this.anInt264 = class98_Sub22.readUnsignedByte((byte)48);
                                    }
                                }
                                else {
                                    this.anInt238 = class98_Sub22.method1186(-128);
                                }
                            }
                            else {
                                this.anInt257 = class98_Sub22.method1186(-124);
                            }
                        }
                        else {
                            this.aString263 = class98_Sub22.readString((byte)84);
                        }
                    }
                    else {
                        this.anInt225 = class98_Sub22.readShort((byte)127);
                    }
                }
                else {
                    this.anInt245 = class98_Sub22.readShort((byte)127);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bq.E(" + n + ',' + n2 + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final String method289(final int n, final String s, final int n2) {
        try {
            if (this.aClass377_256 == null) {
                return s;
            }
            final Class98_Sub15 class98_Sub15 = (Class98_Sub15)this.aClass377_256.method3990(n2, -1);
            if (class98_Sub15 == null) {
                return s;
            }
            if (n != -5911) {
                this.anInt248 = 76;
            }
            return class98_Sub15.aString3917;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bq.A(" + n + ',' + ((s != null) ? "{...}" : "null") + ',' + n2 + ')');
        }
    }
    
    final void method290(final Class98_Sub22 class98_Sub22, final int n) {
        try {
            while (true) {
                final int unsignedByte = class98_Sub22.readUnsignedByte((byte)115);
                if (~unsignedByte == -1) {
                    break;
                }
                this.method288(n + 27172, unsignedByte, class98_Sub22);
            }
            if (n != 5) {
                this.method289(-65, null, 33);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bq.F(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final void method291(final int n) {
        try {
            if (this.anIntArray265 != null) {
                for (int n2 = 0; ~this.anIntArray265.length < ~n2; n2 += 2) {
                    if (this.anIntArray265[n2] >= this.anInt244) {
                        if (this.anIntArray265[n2] > this.anInt247) {
                            this.anInt247 = this.anIntArray265[n2];
                        }
                    }
                    else {
                        this.anInt244 = this.anIntArray265[n2];
                    }
                    if (~this.anIntArray265[1 + n2] <= ~this.anInt248) {
                        if (this.anInt262 < this.anIntArray265[n2 + 1]) {
                            this.anInt262 = this.anIntArray265[n2 + 1];
                        }
                    }
                    else {
                        this.anInt248 = this.anIntArray265[n2 + 1];
                    }
                }
            }
            if (n != -25798) {
                this.method289(10, null, -15);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bq.C(" + n + ')');
        }
    }
    
    public Class24() {
        this.anInt240 = -1;
        this.anInt244 = Integer.MAX_VALUE;
        this.anInt246 = -1;
        this.anInt245 = -1;
        this.anInt224 = -1;
        this.anInt248 = Integer.MAX_VALUE;
        this.anInt243 = -1;
        this.anInt231 = -1;
        this.anInt247 = Integer.MIN_VALUE;
        this.anInt238 = -1;
        this.anInt225 = -1;
        this.aBoolean230 = false;
        this.aBoolean261 = true;
        this.aBoolean258 = true;
        this.anInt253 = -1;
        this.anInt259 = -1;
        this.aBoolean241 = true;
        this.anInt260 = -1;
        this.anInt262 = Integer.MIN_VALUE;
        this.anInt264 = 0;
        this.anInt250 = -1;
        this.aStringArray237 = new String[5];
    }
    
    static {
        Class24.anInt255 = 0;
        Class24.anInt242 = 1;
    }
}
