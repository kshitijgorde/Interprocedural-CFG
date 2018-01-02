// 
// Decompiled by Procyon v0.5.30
// 

final class Class141
{
    int anInt1090;
    int anInt1091;
    int anInt1092;
    boolean aBoolean1093;
    short aShort1094;
    int anInt1095;
    int anInt1096;
    int anInt1097;
    private Class377 aClass377_1098;
    int anInt1099;
    int anInt1100;
    int anInt1101;
    int anInt1102;
    byte aByte1103;
    private int anInt1104;
    short[] aShortArray1105;
    boolean aBoolean1106;
    int[] anIntArray1107;
    private short[] aShortArray1108;
    int[] anIntArray1109;
    int anInt1110;
    private byte aByte1111;
    int anInt1112;
    int anInt1113;
    int anInt1114;
    int anInt1115;
    boolean aBoolean1116;
    int[] anIntArray1117;
    int anInt1118;
    private byte aByte1119;
    int anInt1120;
    private int anInt1121;
    byte aByte1122;
    int anInt1123;
    private int[][] anIntArrayArray1124;
    int anInt1125;
    boolean aBoolean1126;
    int anInt1127;
    int anInt1128;
    byte aByte1129;
    boolean aBoolean1130;
    private int anInt1131;
    int anInt1132;
    Class301 aClass301_1133;
    byte aByte1134;
    short aShort1135;
    private byte[] aByteArray1136;
    short[] aShortArray1137;
    private byte aByte1138;
    private byte aByte1139;
    boolean aBoolean1140;
    byte aByte1141;
    private int anInt1142;
    int anInt1143;
    String aString1144;
    int anInt1145;
    private int anInt1146;
    int anInt1147;
    String[] aStringArray1148;
    boolean aBoolean1149;
    static float aFloat1150;
    private int anInt1151;
    int[] anIntArray1152;
    boolean aBoolean1153;
    int anInt1154;
    private short[] aShortArray1155;
    int anInt1156;
    
    private final void method2293(final int n, final Class98_Sub22 class98_Sub22, final int n2) {
        try {
            if (n != 0) {
                this.method2295((byte)53);
            }
            if (~n2 == 0xFFFFFFFE) {
                final int i = class98_Sub22.readUnsignedByte((byte)35);
                this.anIntArray1107 = new int[i];
                for (int n3 = 0; i > n3; ++n3) {
                    this.anIntArray1107[n3] = class98_Sub22.readShort((byte)127);
                    if (this.anIntArray1107[n3] == 65535) {
                        this.anIntArray1107[n3] = -1;
                    }
                }
            }
            else if (~n2 != 0xFFFFFFFD) {
                if (n2 == 12) {
                    this.anInt1112 = class98_Sub22.readUnsignedByte((byte)(-101));
                }
                else if (n2 < 30 || ~n2 <= -36) {
                    if (n2 == 40) {
                        final int unsignedByte = class98_Sub22.readUnsignedByte((byte)23);
                        this.aShortArray1105 = new short[unsignedByte];
                        this.aShortArray1108 = new short[unsignedByte];
                        for (int n4 = 0; ~n4 > ~unsignedByte; ++n4) {
                            this.aShortArray1108[n4] = (short)class98_Sub22.readShort((byte)127);
                            this.aShortArray1105[n4] = (short)class98_Sub22.readShort((byte)127);
                        }
                    }
                    else if (~n2 == 0xFFFFFFD6) {
                        final int unsignedByte2 = class98_Sub22.readUnsignedByte((byte)(-111));
                        this.aShortArray1155 = new short[unsignedByte2];
                        this.aShortArray1137 = new short[unsignedByte2];
                        for (int j = 0; j < unsignedByte2; ++j) {
                            this.aShortArray1155[j] = (short)class98_Sub22.readShort((byte)127);
                            this.aShortArray1137[j] = (short)class98_Sub22.readShort((byte)127);
                        }
                    }
                    else if (~n2 == 0xFFFFFFD5) {
                        final int unsignedByte3 = class98_Sub22.readUnsignedByte((byte)84);
                        this.aByteArray1136 = new byte[unsignedByte3];
                        for (int k = 0; k < unsignedByte3; ++k) {
                            this.aByteArray1136[k] = class98_Sub22.readSignedByte((byte)(-19));
                        }
                    }
                    else if (n2 == 60) {
                        final int unsignedByte4 = class98_Sub22.readUnsignedByte((byte)108);
                        this.anIntArray1117 = new int[unsignedByte4];
                        for (int l = 0; l < unsignedByte4; ++l) {
                            this.anIntArray1117[l] = class98_Sub22.readShort((byte)127);
                        }
                    }
                    else if (~n2 != 0xFFFFFFA2) {
                        if (n2 == 95) {
                            this.anInt1115 = class98_Sub22.readShort((byte)127);
                        }
                        else if (~n2 != 0xFFFFFF9E) {
                            if (n2 == 98) {
                                this.anInt1142 = class98_Sub22.readShort((byte)127);
                            }
                            else if (n2 != 99) {
                                if (n2 != 100) {
                                    if (~n2 == 0xFFFFFF9A) {
                                        this.anInt1131 = class98_Sub22.readSignedByte((byte)(-19)) * 5;
                                    }
                                    else if (n2 == 102) {
                                        this.anInt1113 = class98_Sub22.readShort((byte)127);
                                    }
                                    else if (n2 != 103) {
                                        if (n2 == 106 || ~n2 == 0xFFFFFF89) {
                                            this.anInt1146 = class98_Sub22.readShort((byte)127);
                                            if (this.anInt1146 == 65535) {
                                                this.anInt1146 = -1;
                                            }
                                            this.anInt1151 = class98_Sub22.readShort((byte)127);
                                            if (this.anInt1151 == 65535) {
                                                this.anInt1151 = -1;
                                            }
                                            int short1 = -1;
                                            if (n2 == 118) {
                                                short1 = class98_Sub22.readShort((byte)127);
                                                if (~short1 == 0xFFFF0000) {
                                                    short1 = -1;
                                                }
                                            }
                                            final int unsignedByte5 = class98_Sub22.readUnsignedByte((byte)116);
                                            this.anIntArray1109 = new int[unsignedByte5 + 2];
                                            for (int n5 = 0; n5 <= unsignedByte5; ++n5) {
                                                this.anIntArray1109[n5] = class98_Sub22.readShort((byte)127);
                                                if (~this.anIntArray1109[n5] == 0xFFFF0000) {
                                                    this.anIntArray1109[n5] = -1;
                                                }
                                            }
                                            this.anIntArray1109[1 + unsignedByte5] = short1;
                                        }
                                        else if (n2 == 107) {
                                            this.aBoolean1116 = false;
                                        }
                                        else if (~n2 != 0xFFFFFF92) {
                                            if (n2 != 111) {
                                                if (~n2 != 0xFFFFFF8E) {
                                                    if (~n2 == 0xFFFFFF8D) {
                                                        this.aByte1122 = class98_Sub22.readSignedByte((byte)(-19));
                                                        this.aByte1134 = class98_Sub22.readSignedByte((byte)(-19));
                                                    }
                                                    else if (~n2 == 0xFFFFFF88) {
                                                        this.aByte1103 = class98_Sub22.readSignedByte((byte)(-19));
                                                    }
                                                    else if (n2 == 121) {
                                                        this.anIntArrayArray1124 = new int[this.anIntArray1107.length][];
                                                        for (int unsignedByte6 = class98_Sub22.readUnsignedByte((byte)(-126)), n6 = 0; n6 < unsignedByte6; ++n6) {
                                                            final int unsignedByte7 = class98_Sub22.readUnsignedByte((byte)92);
                                                            final int[][] anIntArrayArray1124 = this.anIntArrayArray1124;
                                                            final int n7 = unsignedByte7;
                                                            final int[] array = new int[3];
                                                            anIntArrayArray1124[n7] = array;
                                                            final int[] array2 = array;
                                                            array2[0] = class98_Sub22.readSignedByte((byte)(-19));
                                                            array2[1] = class98_Sub22.readSignedByte((byte)(-19));
                                                            array2[2] = class98_Sub22.readSignedByte((byte)(-19));
                                                        }
                                                    }
                                                    else if (n2 == 122) {
                                                        this.anInt1127 = class98_Sub22.readShort((byte)127);
                                                    }
                                                    else if (n2 == 123) {
                                                        this.anInt1092 = class98_Sub22.readShort((byte)127);
                                                    }
                                                    else if (n2 == 125) {
                                                        this.aByte1141 = class98_Sub22.readSignedByte((byte)(-19));
                                                    }
                                                    else if (n2 == 127) {
                                                        this.anInt1145 = class98_Sub22.readShort((byte)127);
                                                    }
                                                    else if (n2 == 128) {
                                                        class98_Sub22.readUnsignedByte((byte)45);
                                                    }
                                                    else if (~n2 == 0xFFFFFF79) {
                                                        this.anInt1120 = class98_Sub22.readShort((byte)127);
                                                        if (~this.anInt1120 == 0xFFFF0000) {
                                                            this.anInt1120 = -1;
                                                        }
                                                        this.anInt1132 = class98_Sub22.readShort((byte)127);
                                                        if (~this.anInt1132 == 0xFFFF0000) {
                                                            this.anInt1132 = -1;
                                                        }
                                                        this.anInt1102 = class98_Sub22.readShort((byte)127);
                                                        if (~this.anInt1102 == 0xFFFF0000) {
                                                            this.anInt1102 = -1;
                                                        }
                                                        this.anInt1147 = class98_Sub22.readShort((byte)127);
                                                        if (~this.anInt1147 == 0xFFFF0000) {
                                                            this.anInt1147 = -1;
                                                        }
                                                        this.anInt1128 = class98_Sub22.readUnsignedByte((byte)33);
                                                    }
                                                    else if (n2 == 135) {
                                                        this.anInt1143 = class98_Sub22.readUnsignedByte((byte)(-108));
                                                        this.anInt1154 = class98_Sub22.readShort((byte)127);
                                                    }
                                                    else if (n2 == 136) {
                                                        this.anInt1114 = class98_Sub22.readUnsignedByte((byte)(-98));
                                                        this.anInt1110 = class98_Sub22.readShort((byte)127);
                                                    }
                                                    else if (~n2 != 0xFFFFFF76) {
                                                        if (n2 == 138) {
                                                            this.anInt1095 = class98_Sub22.readShort((byte)127);
                                                        }
                                                        else if (n2 != 139) {
                                                            if (~n2 != 0xFFFFFF73) {
                                                                if (~n2 == 0xFFFFFF72) {
                                                                    this.aBoolean1153 = true;
                                                                }
                                                                else if (~n2 == 0xFFFFFF71) {
                                                                    this.anInt1118 = class98_Sub22.readShort((byte)127);
                                                                }
                                                                else if (n2 != 143) {
                                                                    if (~n2 <= -151 && n2 < 155) {
                                                                        this.aStringArray1148[-150 + n2] = class98_Sub22.readString((byte)84);
                                                                        if (!this.aClass301_1133.aBoolean2503) {
                                                                            this.aStringArray1148[-150 + n2] = null;
                                                                        }
                                                                    }
                                                                    else if (n2 != 155) {
                                                                        if (~n2 == 0xFFFFFF61) {
                                                                            this.aByte1129 = 1;
                                                                        }
                                                                        else if (n2 != 159) {
                                                                            if (~n2 != 0xFFFFFF5F) {
                                                                                if (n2 != 162) {
                                                                                    if (~n2 != 0xFFFFFF5C) {
                                                                                        if (~n2 == 0xFFFFFF5B) {
                                                                                            this.anInt1101 = class98_Sub22.readShort((byte)127);
                                                                                            this.anInt1090 = class98_Sub22.readShort((byte)127);
                                                                                        }
                                                                                        else if (n2 != 165) {
                                                                                            if (n2 != 168) {
                                                                                                if (n2 == 249) {
                                                                                                    final int unsignedByte8 = class98_Sub22.readUnsignedByte((byte)65);
                                                                                                    if (this.aClass377_1098 == null) {
                                                                                                        this.aClass377_1098 = new Class377(Class48.method453(423660257, unsignedByte8));
                                                                                                    }
                                                                                                    for (int n8 = 0; n8 < unsignedByte8; ++n8) {
                                                                                                        final boolean b = class98_Sub22.readUnsignedByte((byte)34) == 1;
                                                                                                        final int method1186 = class98_Sub22.method1186(-125);
                                                                                                        Class98 class98;
                                                                                                        if (!b) {
                                                                                                            class98 = new Class98_Sub34(class98_Sub22.readInt(-2));
                                                                                                        }
                                                                                                        else {
                                                                                                            class98 = new Class98_Sub15(class98_Sub22.readString((byte)84));
                                                                                                        }
                                                                                                        this.aClass377_1098.method3996(class98, method1186, -1);
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                            else {
                                                                                                this.anInt1125 = class98_Sub22.readUnsignedByte((byte)15);
                                                                                            }
                                                                                        }
                                                                                        else {
                                                                                            this.anInt1123 = class98_Sub22.readUnsignedByte((byte)108);
                                                                                        }
                                                                                    }
                                                                                    else {
                                                                                        this.anInt1096 = class98_Sub22.readUnsignedByte((byte)89);
                                                                                    }
                                                                                }
                                                                                else {
                                                                                    this.aBoolean1093 = true;
                                                                                }
                                                                            }
                                                                            else {
                                                                                final int unsignedByte9 = class98_Sub22.readUnsignedByte((byte)(-108));
                                                                                this.anIntArray1152 = new int[unsignedByte9];
                                                                                for (int n9 = 0; ~n9 > ~unsignedByte9; ++n9) {
                                                                                    this.anIntArray1152[n9] = class98_Sub22.readShort((byte)127);
                                                                                }
                                                                            }
                                                                        }
                                                                        else {
                                                                            this.aByte1129 = 0;
                                                                        }
                                                                    }
                                                                    else {
                                                                        this.aByte1111 = class98_Sub22.readSignedByte((byte)(-19));
                                                                        this.aByte1139 = class98_Sub22.readSignedByte((byte)(-19));
                                                                        this.aByte1119 = class98_Sub22.readSignedByte((byte)(-19));
                                                                        this.aByte1138 = class98_Sub22.readSignedByte((byte)(-19));
                                                                    }
                                                                }
                                                                else {
                                                                    this.aBoolean1149 = true;
                                                                }
                                                            }
                                                            else {
                                                                this.anInt1156 = class98_Sub22.readUnsignedByte((byte)(-127));
                                                            }
                                                        }
                                                        else {
                                                            this.anInt1100 = class98_Sub22.readShort((byte)127);
                                                        }
                                                    }
                                                    else {
                                                        this.anInt1099 = class98_Sub22.readShort((byte)127);
                                                    }
                                                }
                                                else {
                                                    this.aShort1094 = (short)class98_Sub22.readShort((byte)127);
                                                    this.aShort1135 = (short)class98_Sub22.readShort((byte)127);
                                                }
                                            }
                                            else {
                                                this.aBoolean1130 = false;
                                            }
                                        }
                                        else {
                                            this.aBoolean1126 = false;
                                        }
                                    }
                                    else {
                                        this.anInt1091 = class98_Sub22.readShort((byte)127);
                                    }
                                }
                                else {
                                    this.anInt1104 = class98_Sub22.readSignedByte((byte)(-19));
                                }
                            }
                            else {
                                this.aBoolean1106 = true;
                            }
                        }
                        else {
                            this.anInt1121 = class98_Sub22.readShort((byte)127);
                        }
                    }
                    else {
                        this.aBoolean1140 = false;
                    }
                }
                else {
                    this.aStringArray1148[n2 - 30] = class98_Sub22.readString((byte)84);
                }
            }
            else {
                this.aString1144 = class98_Sub22.readString((byte)84);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jl.F(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n2 + ')');
        }
    }
    
    static final void method2294(final int n) {
        try {
            if (Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub3_4076.method564((byte)125) == 0 && Class115.anInt963 != Class43.anInt377) {
                Class251.method3170(n - 6547, Class275.anInt2047, false, Class160.anInt1258, 11);
            }
            else {
                client.method103(n, Class265.aHa1974);
                if (Class145.anInt1170 != Class43.anInt377) {
                    Class135.method2264((byte)(-94));
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jl.I(" + n + ')');
        }
    }
    
    final void method2295(final byte b) {
        try {
            if (this.anIntArray1107 == null) {
                this.anIntArray1107 = new int[0];
            }
            if (this.aByte1129 == -1) {
                if (s_Sub1.aClass279_5197 == this.aClass301_1133.aClass279_2502) {
                    this.aByte1129 = 1;
                    if (!client.aBoolean3553) {
                        return;
                    }
                }
                this.aByte1129 = 0;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jl.K(" + b + ')');
        }
    }
    
    final boolean method2296(final Interface6 interface6, final byte b) {
        try {
            if (this.anIntArray1109 == null) {
                return true;
            }
            int n = -1;
            if (this.anInt1146 != -1) {
                n = interface6.method7(this.anInt1146, 7373);
                if (!client.aBoolean3553) {
                    return (~n <= -1 && n < -1 + this.anIntArray1109.length && ~this.anIntArray1109[n] != 0x0) || this.anIntArray1109[this.anIntArray1109.length - 1] != -1;
                }
            }
            if (~this.anInt1151 != 0x0) {
                n = interface6.method6(this.anInt1151, 122);
            }
            return (~n <= -1 && n < -1 + this.anIntArray1109.length && ~this.anIntArray1109[n] != 0x0) || this.anIntArray1109[this.anIntArray1109.length - 1] != -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jl.L(" + ((interface6 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    final void method2297(final Class98_Sub22 class98_Sub22, final boolean b) {
        try {
            if (!b) {
                this.method2297(null, false);
            }
            while (true) {
                final int unsignedByte = class98_Sub22.readUnsignedByte((byte)(-125));
                if (unsignedByte == 0) {
                    break;
                }
                this.method2293(0, class98_Sub22, unsignedByte);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jl.A(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    final String method2298(final int n, final int n2, final String s) {
        try {
            if (n2 >= -14) {
                this.method2295((byte)(-67));
            }
            if (this.aClass377_1098 == null) {
                return s;
            }
            final Class98_Sub15 class98_Sub15 = (Class98_Sub15)this.aClass377_1098.method3990(n, -1);
            if (class98_Sub15 == null) {
                return s;
            }
            return class98_Sub15.aString3917;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jl.J(" + n + ',' + n2 + ',' + ((s != null) ? "{...}" : "null") + ')');
        }
    }
    
    final Class146 method2299(final Class97 class97, final boolean b, final Interface6 interface6, final int n, final int n2, final Class183 class98, final int n3, final ha ha, final Class40 class99, final int n4) {
        try {
            if (b) {
                return null;
            }
            if (this.anIntArray1109 != null) {
                final Class141 method2300 = this.method2300(interface6, (byte)78);
                if (method2300 == null) {
                    return null;
                }
                return method2300.method2299(class97, false, interface6, n, n2, class98, n3, ha, class99, n4);
            }
            else {
                if (this.anIntArray1117 == null && (class99 == null || class99.anIntArray366 == null)) {
                    return null;
                }
                int n5 = n3;
                if (class97 != null && ~n2 != 0x0) {
                    n5 |= class97.method932(true, n2, !b, n4);
                }
                long n6 = ha.anInt937 << 431969328 | this.anInt1097;
                if (class99 != null) {
                    n6 |= class99.aLong365 << -466776552;
                }
                Class146 class100;
                synchronized (this.aClass301_1133.aClass79_2510) {
                    class100 = (Class146)this.aClass301_1133.aClass79_2510.method802(-119, n6);
                }
                if (class100 == null || ~(class100.ua() & n5) != ~n5) {
                    if (class100 != null) {
                        n5 |= class100.ua();
                    }
                    int n7 = n5;
                    final int[] array = (class99 != null && class99.anIntArray366 != null) ? class99.anIntArray366 : this.anIntArray1117;
                    boolean b2 = false;
                    synchronized (this.aClass301_1133.aClass207_2506) {
                        for (int n8 = 0; ~n8 > ~array.length; ++n8) {
                            if (!this.aClass301_1133.aClass207_2506.method2751(0, array[n8], -6329)) {
                                b2 = true;
                            }
                        }
                    }
                    if (b2) {
                        return null;
                    }
                    final Class178[] array2 = new Class178[array.length];
                    synchronized (this.aClass301_1133.aClass207_2506) {
                        for (int n9 = 0; ~n9 > ~array.length; ++n9) {
                            array2[n9] = Class98_Sub6.method981(0, -9252, this.aClass301_1133.aClass207_2506, array[n9]);
                        }
                    }
                    for (int n10 = 0; ~array.length < ~n10; ++n10) {
                        if (array2[n10] != null && ~array2[n10].anInt1387 > -14) {
                            array2[n10].method2592(13746, 2);
                        }
                    }
                    Class178 class101;
                    if (array2.length != 1) {
                        class101 = new Class178(array2, array2.length);
                    }
                    else {
                        class101 = array2[0];
                    }
                    if (this.aShortArray1108 != null) {
                        n7 |= 0x4000;
                    }
                    if (this.aShortArray1155 != null) {
                        n7 |= 0x8000;
                    }
                    if (~this.aByte1138 != -1) {
                        n7 |= 0x80000;
                    }
                    class100 = ha.method1790(class101, n7, this.aClass301_1133.anInt2511, 64, 768);
                    if (this.aShortArray1108 != null) {
                        short[] array3;
                        if (class99 == null || class99.aShortArray370 == null) {
                            array3 = this.aShortArray1105;
                        }
                        else {
                            array3 = class99.aShortArray370;
                        }
                        for (int n11 = 0; ~n11 > ~this.aShortArray1108.length; ++n11) {
                            if (this.aByteArray1136 != null && this.aByteArray1136.length > n11) {
                                class100.ia(this.aShortArray1108[n11], Class265.aShortArray1977[0xFF & this.aByteArray1136[n11]]);
                            }
                            else {
                                class100.ia(this.aShortArray1108[n11], array3[n11]);
                            }
                        }
                    }
                    if (this.aShortArray1155 != null) {
                        short[] array4;
                        if (class99 != null && class99.aShortArray368 != null) {
                            array4 = class99.aShortArray368;
                        }
                        else {
                            array4 = this.aShortArray1137;
                        }
                        for (int i = 0; i < this.aShortArray1155.length; ++i) {
                            class100.aa(this.aShortArray1155[i], array4[i]);
                        }
                    }
                    if (this.aByte1138 != 0) {
                        class100.method2337(this.aByte1111, this.aByte1139, this.aByte1119, 0xFF & this.aByte1138);
                    }
                    class100.s(n5);
                    synchronized (this.aClass301_1133.aClass79_2510) {
                        this.aClass301_1133.aClass79_2510.method805(n6, class100, (byte)(-80));
                    }
                }
                if (class97 != null && n2 != -1) {
                    class100 = class97.method937(n4, n, n5, -94, class100, n2);
                }
                class100.s(n3);
                return class100;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jl.M(" + ((class97 != null) ? "{...}" : "null") + ',' + b + ',' + ((interface6 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + ((class98 != null) ? "{...}" : "null") + ',' + n3 + ',' + ((ha != null) ? "{...}" : "null") + ',' + ((class99 != null) ? "{...}" : "null") + ',' + n4 + ')');
        }
    }
    
    final Class141 method2300(final Interface6 interface6, final byte b) {
        try {
            int n = -1;
            Label_0054: {
                if (this.anInt1146 != -1) {
                    n = interface6.method7(this.anInt1146, 7373);
                    if (!client.aBoolean3553) {
                        break Label_0054;
                    }
                }
                if (this.anInt1151 != -1) {
                    n = interface6.method6(this.anInt1151, 28);
                }
            }
            if (b < 19) {
                return null;
            }
            if (n >= 0 && ~(this.anIntArray1109.length - 1) < ~n && this.anIntArray1109[n] != -1) {
                return this.aClass301_1133.method3538(5, this.anIntArray1109[n]);
            }
            final int n2 = this.anIntArray1109[-1 + this.anIntArray1109.length];
            if (~n2 != 0x0) {
                return this.aClass301_1133.method3538(5, n2);
            }
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jl.B(" + ((interface6 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    final Class146 method2301(final int n, final int n2, final int[] array, final int n3, final byte b, final Class97 class97, final Class226[] array2, final ha ha, final int n4, final int n5, final Interface6 interface6, final Class40 class98, final int n6, final Class257 class99, final int n7, final int n8, final Class183 class100, final Class97 class101) {
        try {
            if (this.anIntArray1109 != null) {
                final Class141 method2300 = this.method2300(interface6, (byte)78);
                if (method2300 == null) {
                    return null;
                }
                return method2300.method2301(n, n2, array, n3, (byte)111, class97, array2, ha, n4, n5, interface6, class98, n6, class99, n7, n8, class100, class101);
            }
            else {
                int n9 = n7;
                if (this.anInt1142 != 128) {
                    n9 |= 0x2;
                }
                if (~this.anInt1121 != 0xFFFFFF7F) {
                    n9 |= 0x5;
                }
                boolean b2 = class97 != null || class101 != null;
                boolean b3 = false;
                boolean b4 = false;
                boolean b5 = false;
                boolean b6 = false;
                final int n10 = (array2 != null) ? array2.length : 0;
                for (int n11 = 0; ~n11 > ~n10; ++n11) {
                    Class98_Sub10_Sub26.aClass98_Sub46_Sub16Array5690[n11] = null;
                    if (array2[n11] != null) {
                        final Class97 method2301 = class100.method2623(array2[n11].anInt1700, 16383);
                        if (method2301.anIntArray818 != null) {
                            b2 = true;
                            Class98_Sub50.aClass97Array4293[n11] = method2301;
                            final int anInt1702 = array2[n11].anInt1702;
                            final int anInt1703 = array2[n11].anInt1701;
                            final int n12 = method2301.anIntArray818[anInt1702];
                            Class98_Sub10_Sub26.aClass98_Sub46_Sub16Array5690[n11] = class100.method2624(2, n12 >>> -39687440);
                            final int n13 = n12 & 0xFFFF;
                            Class290.anIntArray2198[n11] = n13;
                            if (Class98_Sub10_Sub26.aClass98_Sub46_Sub16Array5690[n11] != null) {
                                b4 |= Class98_Sub10_Sub26.aClass98_Sub46_Sub16Array5690[n11].method1619(n13, 31239);
                                b3 |= Class98_Sub10_Sub26.aClass98_Sub46_Sub16Array5690[n11].method1617(false, n13);
                                b6 |= Class98_Sub10_Sub26.aClass98_Sub46_Sub16Array5690[n11].method1615(n13, false);
                                b5 |= method2301.aBoolean817;
                            }
                            if ((method2301.aBoolean825 || Class357.aBoolean3027) && ~anInt1703 != 0x0 && ~method2301.anIntArray818.length < ~anInt1703) {
                                Class32.anIntArray311[n11] = method2301.anIntArray811[anInt1702];
                                Class256_Sub1.anIntArray5156[n11] = array2[n11].anInt1707;
                                final int n14 = method2301.anIntArray818[anInt1703];
                                Class98_Sub11.aClass98_Sub46_Sub16Array3870[n11] = class100.method2624(2, n14 >>> -1998137744);
                                final int n15 = n14 & 0xFFFF;
                                Class265.anIntArray1981[n11] = n15;
                                if (Class98_Sub11.aClass98_Sub46_Sub16Array3870[n11] != null) {
                                    b4 |= Class98_Sub11.aClass98_Sub46_Sub16Array3870[n11].method1619(n15, 31239);
                                    b3 |= Class98_Sub11.aClass98_Sub46_Sub16Array3870[n11].method1617(false, n15);
                                    b6 |= Class98_Sub11.aClass98_Sub46_Sub16Array3870[n11].method1615(n15, false);
                                }
                            }
                            else {
                                Class32.anIntArray311[n11] = 0;
                                Class256_Sub1.anIntArray5156[n11] = 0;
                                Class98_Sub11.aClass98_Sub46_Sub16Array3870[n11] = null;
                                Class265.anIntArray1981[n11] = -1;
                            }
                        }
                    }
                }
                if (b <= 91) {
                    return null;
                }
                int n16 = -1;
                int n17 = -1;
                int n18 = 0;
                Class98_Sub46_Sub16 method2302 = null;
                Class98_Sub46_Sub16 method2303 = null;
                int n19 = -1;
                int n20 = -1;
                int n21 = 0;
                Class98_Sub46_Sub16 method2304 = null;
                Class98_Sub46_Sub16 method2305 = null;
                if (b2) {
                    if (class97 != null) {
                        final int n22 = class97.anIntArray818[n];
                        final int n23 = n22 >>> -329523440;
                        n16 = (n22 & 0xFFFF);
                        method2302 = class100.method2624(2, n23);
                        if (method2302 != null) {
                            b4 |= method2302.method1619(n16, 31239);
                            b3 |= method2302.method1617(false, n16);
                            b6 |= method2302.method1615(n16, false);
                            b5 |= class97.aBoolean817;
                        }
                        if ((class97.aBoolean825 || Class357.aBoolean3027) && ~n5 != 0x0 && class97.anIntArray818.length > n5) {
                            final int n24 = class97.anIntArray818[n5];
                            n18 = class97.anIntArray811[n];
                            final int n25 = n24 >>> -924031952;
                            if (n23 != n25) {
                                method2303 = class100.method2624(2, n25);
                            }
                            else {
                                method2303 = method2302;
                            }
                            n17 = (n24 & 0xFFFF);
                            if (method2303 != null) {
                                b4 |= method2303.method1619(n17, 31239);
                                b3 |= method2303.method1617(false, n17);
                                b6 |= method2303.method1615(n17, false);
                            }
                        }
                    }
                    n9 |= 0x20;
                    if (class101 != null) {
                        final int n26 = class101.anIntArray818[n3];
                        final int n27 = n26 >>> 646999344;
                        n19 = (n26 & 0xFFFF);
                        method2304 = class100.method2624(2, n27);
                        if (method2304 != null) {
                            b4 |= method2304.method1619(n19, 31239);
                            b3 |= method2304.method1617(false, n19);
                            b6 |= method2304.method1615(n19, false);
                            b5 |= class101.aBoolean817;
                        }
                        if ((class101.aBoolean825 || Class357.aBoolean3027) && ~n8 != 0x0 && class101.anIntArray818.length > n8) {
                            n21 = class101.anIntArray811[n3];
                            final int n28 = class101.anIntArray818[n8];
                            final int n29 = n28 >>> -36661744;
                            if (~n29 != ~n27) {
                                method2305 = class100.method2624(2, n29);
                            }
                            else {
                                method2305 = method2304;
                            }
                            n20 = (n28 & 0xFFFF);
                            if (method2305 != null) {
                                b4 |= method2305.method1619(n20, 31239);
                                b3 |= method2305.method1617(false, n20);
                                b6 |= method2305.method1615(n20, false);
                            }
                        }
                    }
                    if (b4) {
                        n9 |= 0x80;
                    }
                    if (b3) {
                        n9 |= 0x100;
                    }
                    if (b5) {
                        n9 |= 0x200;
                    }
                    if (b6) {
                        n9 |= 0x400;
                    }
                }
                long n30 = this.anInt1097 | ha.anInt937 << -262111792;
                if (class98 != null) {
                    n30 |= class98.aLong365 << -1822091880;
                }
                Class146 method2306;
                synchronized (this.aClass301_1133.aClass79_2509) {
                    method2306 = (Class146)this.aClass301_1133.aClass79_2509.method802(-125, n30);
                }
                Class294 method2307 = null;
                if (this.anInt1145 != -1) {
                    method2307 = class99.method3199(false, this.anInt1145);
                }
                if (method2306 == null || (n9 & method2306.ua()) != n9) {
                    if (method2306 != null) {
                        n9 |= method2306.ua();
                    }
                    int n31 = n9;
                    final int[] array3 = (class98 != null && class98.anIntArray366 != null) ? class98.anIntArray366 : this.anIntArray1107;
                    boolean b7 = false;
                    synchronized (this.aClass301_1133.aClass207_2506) {
                        for (int i = 0; i < array3.length; ++i) {
                            if (array3[i] != -1 && !this.aClass301_1133.aClass207_2506.method2751(0, array3[i], -6329)) {
                                b7 = true;
                            }
                        }
                    }
                    if (b7) {
                        return null;
                    }
                    final Class178[] array4 = new Class178[array3.length];
                    for (int j = 0; j < array3.length; ++j) {
                        if (~array3[j] != 0x0) {
                            synchronized (this.aClass301_1133.aClass207_2506) {
                                array4[j] = Class98_Sub6.method981(0, -9252, this.aClass301_1133.aClass207_2506, array3[j]);
                            }
                            if (array4[j] != null) {
                                if (~array4[j].anInt1387 > -14) {
                                    array4[j].method2592(13746, 2);
                                }
                                if (this.anIntArrayArray1124 != null && this.anIntArrayArray1124[j] != null) {
                                    array4[j].method2597(this.anIntArrayArray1124[j][2], this.anIntArrayArray1124[j][0], (byte)89, this.anIntArrayArray1124[j][1]);
                                }
                            }
                        }
                    }
                    if (method2307 != null && method2307.anIntArrayArray2366 != null) {
                        for (int k = 0; k < method2307.anIntArrayArray2366.length; ++k) {
                            if (~k > ~array4.length && array4[k] != null) {
                                int n32 = 0;
                                int n33 = 0;
                                int n34 = 0;
                                int n35 = 0;
                                int n36 = 0;
                                int n37 = 0;
                                if (method2307.anIntArrayArray2366[k] != null) {
                                    n32 = method2307.anIntArrayArray2366[k][0];
                                    n37 = method2307.anIntArrayArray2366[k][5] << 591859491;
                                    n33 = method2307.anIntArrayArray2366[k][1];
                                    n36 = method2307.anIntArrayArray2366[k][4] << -915484413;
                                    n34 = method2307.anIntArrayArray2366[k][2];
                                    n35 = method2307.anIntArrayArray2366[k][3] << -1463601021;
                                }
                                if (n35 != 0 || n36 != 0 || ~n37 != -1) {
                                    array4[k].method2600(n37, n35, (byte)117, n36);
                                }
                                if (~n32 != -1 || n33 != 0 || ~n34 != -1) {
                                    array4[k].method2597(n34, n32, (byte)60, n33);
                                }
                            }
                        }
                    }
                    Class178 class102;
                    if (~array4.length == 0xFFFFFFFE) {
                        class102 = array4[0];
                    }
                    else {
                        class102 = new Class178(array4, array4.length);
                    }
                    if (this.aShortArray1108 != null) {
                        n31 |= 0x4000;
                    }
                    if (this.aShortArray1155 != null) {
                        n31 |= 0x8000;
                    }
                    if (~this.aByte1138 != -1) {
                        n31 |= 0x80000;
                    }
                    method2306 = ha.method1790(class102, n31, this.aClass301_1133.anInt2511, 64 + this.anInt1104, this.anInt1131 + 850);
                    if (this.aShortArray1108 != null) {
                        short[] array5;
                        if (class98 != null && class98.aShortArray370 != null) {
                            array5 = class98.aShortArray370;
                        }
                        else {
                            array5 = this.aShortArray1105;
                        }
                        for (int n38 = 0; this.aShortArray1108.length > n38; ++n38) {
                            if (this.aByteArray1136 != null && n38 < this.aByteArray1136.length) {
                                method2306.ia(this.aShortArray1108[n38], Class265.aShortArray1977[this.aByteArray1136[n38] & 0xFF]);
                            }
                            else {
                                method2306.ia(this.aShortArray1108[n38], array5[n38]);
                            }
                        }
                    }
                    if (this.aShortArray1155 != null) {
                        short[] array6;
                        if (class98 == null || class98.aShortArray368 == null) {
                            array6 = this.aShortArray1137;
                        }
                        else {
                            array6 = class98.aShortArray368;
                        }
                        for (int l = 0; l < this.aShortArray1155.length; ++l) {
                            method2306.aa(this.aShortArray1155[l], array6[l]);
                        }
                    }
                    if (~this.aByte1138 != -1) {
                        method2306.method2337(this.aByte1111, this.aByte1139, this.aByte1119, 0xFF & this.aByte1138);
                    }
                    method2306.s(n9);
                    synchronized (this.aClass301_1133.aClass79_2509) {
                        this.aClass301_1133.aClass79_2509.method805(n30, method2306, (byte)(-80));
                    }
                }
                final Class146 method2308 = method2306.method2341((byte)4, n9, true);
                boolean b8 = false;
                if (array != null) {
                    for (int n39 = 0; n39 < 12; ++n39) {
                        if (~array[n39] != 0x0) {
                            b8 = true;
                        }
                    }
                }
                if (!b2 && !b8) {
                    return method2308;
                }
                Class111[] method2309 = null;
                if (method2307 != null) {
                    method2309 = method2307.method3481(ha, (byte)(-80));
                }
                if (b8 && method2309 != null) {
                    for (int n40 = 0; n40 < 12; ++n40) {
                        if (method2309[n40] != null) {
                            method2308.method2331(method2309[n40], 1 << n40, true);
                        }
                    }
                }
                int n41 = 0;
                int n42 = 1;
                while (~n41 > ~n10) {
                    if (Class98_Sub10_Sub26.aClass98_Sub46_Sub16Array5690[n41] != null) {
                        method2308.method2323(Class290.anIntArray2198[n41], -1 + Class256_Sub1.anIntArray5156[n41], Class98_Sub11.aClass98_Sub46_Sub16Array3870[n41], -27033, n42, Class98_Sub10_Sub26.aClass98_Sub46_Sub16Array5690[n41], Class98_Sub50.aClass97Array4293[n41].aBoolean817, Class265.anIntArray1981[n41], null, 0, Class32.anIntArray311[n41]);
                    }
                    n42 <<= 1;
                    ++n41;
                }
                if (b8) {
                    for (int n43 = 0; ~n43 > -13; ++n43) {
                        if (~array[n43] != 0x0) {
                            final int n44 = -n2 + array[n43] & 0x3FFF;
                            final Class111 method2310 = ha.method1821();
                            method2310.method2101(n44);
                            method2308.method2331(method2310, 1 << n43, false);
                        }
                    }
                }
                if (b8 && method2309 != null) {
                    for (int n45 = 0; n45 < 12; ++n45) {
                        if (method2309[n45] != null) {
                            method2308.method2331(method2309[n45], 1 << n45, false);
                        }
                    }
                }
                if (method2302 == null || method2304 == null) {
                    if (method2302 == null) {
                        if (method2304 != null) {
                            method2308.method2338(-1 + n4, method2304, n19, method2305, class101.aBoolean817, 0, 126, n21, n20);
                        }
                    }
                    else {
                        method2308.method2338(n6 - 1, method2302, n16, method2303, class97.aBoolean817, 0, 112, n18, n17);
                    }
                }
                else {
                    method2308.method2321(n18, n16, method2302, method2303, class97.aBooleanArray813, n21, 28777, n19, method2304, n20, n6 - 1, class97.aBoolean817 | class101.aBoolean817, method2305, -1 + n4, n17);
                }
                for (int n46 = 0; ~n10 < ~n46; ++n46) {
                    Class98_Sub10_Sub26.aClass98_Sub46_Sub16Array5690[n46] = null;
                    Class98_Sub11.aClass98_Sub46_Sub16Array3870[n46] = null;
                    Class98_Sub50.aClass97Array4293[n46] = null;
                }
                if (~this.anInt1121 != 0xFFFFFF7F || this.anInt1142 != 128) {
                    method2308.O(this.anInt1121, this.anInt1142, this.anInt1121);
                }
                method2308.s(n7);
                return method2308;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jl.C(" + n + ',' + n2 + ',' + ((array != null) ? "{...}" : "null") + ',' + n3 + ',' + b + ',' + ((class97 != null) ? "{...}" : "null") + ',' + ((array2 != null) ? "{...}" : "null") + ',' + ((ha != null) ? "{...}" : "null") + ',' + n4 + ',' + n5 + ',' + ((interface6 != null) ? "{...}" : "null") + ',' + ((class98 != null) ? "{...}" : "null") + ',' + n6 + ',' + ((class99 != null) ? "{...}" : "null") + ',' + n7 + ',' + n8 + ',' + ((class100 != null) ? "{...}" : "null") + ',' + ((class101 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final boolean method2302(final byte b) {
        try {
            if (b < 16) {
                return false;
            }
            if (this.anIntArray1109 == null) {
                return ~this.anInt1120 != 0x0 || ~this.anInt1102 != 0x0 || this.anInt1147 != -1;
            }
            for (int i = 0; i < this.anIntArray1109.length; ++i) {
                if (~this.anIntArray1109[i] != 0x0) {
                    final Class141 method3538 = this.aClass301_1133.method3538(5, this.anIntArray1109[i]);
                    if (~method3538.anInt1120 != 0x0 || method3538.anInt1102 != -1 || ~method3538.anInt1147 != 0x0) {
                        return true;
                    }
                }
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jl.E(" + b + ')');
        }
    }
    
    static final void method2303(final ha ha, final byte b, final Class207 class207) {
        try {
            final Class324[] method3680 = Class324.method3680(class207, aa.anInt51, 0);
            Class287_Sub2.aClass332Array3275 = new Class332[method3680.length];
            for (int n = 0; method3680.length > n; ++n) {
                Class287_Sub2.aClass332Array3275[n] = ha.method1758(method3680[n], true);
            }
            final Class324[] method3681 = Class324.method3680(class207, Class140.anInt3243, 0);
            Class254.aClass332Array1943 = new Class332[method3681.length];
            for (int i = 0; i < method3681.length; ++i) {
                Class254.aClass332Array1943[i] = ha.method1758(method3681[i], true);
            }
            final Class324[] method3682 = Class324.method3680(class207, Class65.anInt503, 0);
            Class177.aClass332Array1382 = new Class332[method3682.length];
            for (int n2 = 0; ~method3682.length < ~n2; ++n2) {
                Class177.aClass332Array1382[n2] = ha.method1758(method3682[n2], true);
            }
            final Class324[] method3683 = Class324.method3680(class207, Class260.anInt3259, 0);
            Class119_Sub4.aClass332Array4739 = new Class332[method3683.length];
            for (int n3 = 0; method3683.length > n3; ++n3) {
                Class119_Sub4.aClass332Array4739[n3] = ha.method1758(method3683[n3], true);
            }
            final Class324[] method3684 = Class324.method3680(class207, Class251.anInt1916, 0);
            Class2.aClass332Array72 = new Class332[method3684.length];
            for (int n4 = 0; ~method3684.length < ~n4; ++n4) {
                Class2.aClass332Array72[n4] = ha.method1758(method3684[n4], true);
            }
            final Class324[] method3685 = Class324.method3680(class207, Class319.anInt2706, 0);
            Class306.aClass332Array2557 = new Class332[method3685.length];
            for (int j = 0; j < method3685.length; ++j) {
                Class306.aClass332Array2557[j] = ha.method1758(method3685[j], true);
            }
            final Class324[] method3686 = Class324.method3680(class207, Class76_Sub2.anInt3728, 0);
            Class98_Sub46_Sub11.aClass332Array6032 = new Class332[method3686.length];
            for (int n5 = 0; ~method3686.length < ~n5; ++n5) {
                Class98_Sub46_Sub11.aClass332Array6032[n5] = ha.method1758(method3686[n5], true);
            }
            final Class324[] method3687 = Class324.method3680(class207, Class226.anInt1706, 0);
            Class340.aClass332Array2848 = new Class332[method3687.length];
            for (int n6 = 0; ~method3687.length < ~n6; ++n6) {
                Class340.aClass332Array2848[n6] = ha.method1758(method3687[n6], true);
            }
            final Class324[] method3688 = Class324.method3680(class207, Class39.anInt363, 0);
            Class76_Sub7.aClass332Array3764 = new Class332[method3688.length];
            for (int n7 = 0; ~n7 > ~method3688.length; ++n7) {
                Class76_Sub7.aClass332Array3764[n7] = ha.method1758(method3688[n7], true);
            }
            final Class324[] method3689 = Class324.method3680(class207, OutputStream_Sub1.anInt37, 0);
            Class93.aClass332Array3512 = new Class332[method3689.length];
            for (int n8 = 0; ~n8 > ~method3689.length; ++n8) {
                Class93.aClass332Array3512[n8] = ha.method1758(method3689[n8], true);
            }
            final Class324[] method3690 = Class324.method3680(class207, Class243.anInt1852, 0);
            Class64_Sub14.aClass332Array3675 = new Class332[method3690.length];
            for (int k = 0; k < method3690.length; ++k) {
                Class64_Sub14.aClass332Array3675[k] = ha.method1758(method3690[k], true);
            }
            final Class324[] method3691 = Class324.method3680(class207, Class98_Sub31_Sub4.anInt5860, 0);
            Class64_Sub18.aClass332Array3689 = new Class332[method3691.length];
            for (int n9 = 0; method3691.length > n9; ++n9) {
                Class64_Sub18.aClass332Array3689[n9] = ha.method1758(method3691[n9], true);
            }
            Class334.aClass332_3471 = ha.method1758(Class324.method3685(class207, Class111_Sub2.anInt4695, 0), true);
            Class284_Sub2_Sub2.aClass332_6199 = ha.method1758(Class324.method3685(class207, Class264.anInt1972, 0), true);
            final Class324[] method3692 = Class324.method3680(class207, Class76.anInt588, 0);
            Class352.aClass332Array3000 = new Class332[method3692.length];
            for (int l = 0; l < method3692.length; ++l) {
                Class352.aClass332Array3000[l] = ha.method1758(method3692[l], true);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jl.D(" + ((ha != null) ? "{...}" : "null") + ',' + b + ',' + ((class207 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final short[] method2304(final byte b, final short[] array) {
        try {
            if (b < 109) {
                method2294(72);
            }
            if (array == null) {
                return null;
            }
            final short[] array2 = new short[array.length];
            Class236.method2895(array, 0, array2, 0, array.length);
            return array2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jl.H(" + b + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    final int method2305(final int n, final int n2, final byte b) {
        try {
            if (b <= 113) {
                return 85;
            }
            if (this.aClass377_1098 == null) {
                return n2;
            }
            final Class98_Sub34 class98_Sub34 = (Class98_Sub34)this.aClass377_1098.method3990(n, -1);
            if (class98_Sub34 == null) {
                return n2;
            }
            return class98_Sub34.anInt4126;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jl.G(" + n + ',' + n2 + ',' + b + ')');
        }
    }
    
    public Class141() {
        this.aShort1094 = 0;
        this.anInt1092 = -1;
        this.anInt1104 = 0;
        this.anInt1112 = 1;
        this.aByte1122 = -96;
        this.anInt1128 = 0;
        this.anInt1101 = 256;
        this.aBoolean1130 = true;
        this.aByte1129 = -1;
        this.aByte1103 = 0;
        this.anInt1113 = -1;
        this.anInt1095 = -1;
        this.anInt1125 = 0;
        this.anInt1123 = 0;
        this.anInt1118 = -1;
        this.anInt1099 = -1;
        this.anInt1115 = -1;
        this.aBoolean1126 = true;
        this.aBoolean1116 = true;
        this.anInt1127 = -1;
        this.anInt1131 = 0;
        this.anInt1121 = 128;
        this.anInt1114 = -1;
        this.anInt1143 = -1;
        this.aShort1135 = 0;
        this.anInt1142 = 128;
        this.aString1144 = "null";
        this.aBoolean1149 = false;
        this.aByte1141 = 4;
        this.aBoolean1140 = true;
        this.anInt1096 = -1;
        this.anInt1090 = 256;
        this.aByte1134 = -16;
        this.anInt1120 = -1;
        this.anInt1110 = -1;
        this.anInt1091 = 32;
        this.aBoolean1106 = false;
        this.anInt1145 = -1;
        this.anInt1102 = -1;
        this.anInt1100 = -1;
        this.aStringArray1148 = new String[5];
        this.anInt1132 = -1;
        this.anInt1151 = -1;
        this.anInt1146 = -1;
        this.anInt1147 = -1;
        this.aByte1138 = 0;
        this.anInt1154 = -1;
        this.aBoolean1153 = false;
        this.anInt1156 = 255;
    }
}
