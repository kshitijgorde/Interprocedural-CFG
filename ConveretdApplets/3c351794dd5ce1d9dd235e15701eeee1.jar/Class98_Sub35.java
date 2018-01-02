import jaclib.hardware_info.HardwareInfo;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub35 extends Class98
{
    int anInt4129;
    private int anInt4130;
    private String aString4131;
    private boolean aBoolean4132;
    private int anInt4133;
    private int anInt4134;
    private String aString4135;
    private int anInt4136;
    private int anInt4137;
    private boolean aBoolean4138;
    private int anInt4139;
    private int anInt4140;
    private int anInt4141;
    private int anInt4142;
    private String aString4143;
    static Rectangle[] aRectangleArray4144;
    private int anInt4145;
    private String aString4146;
    private int anInt4147;
    private int anInt4148;
    private int anInt4149;
    private int anInt4150;
    static Class63 aClass63_4151;
    
    static final void method1452(final int n) {
        try {
            Class98_Sub37.aHa4185.xa(1.1523438f * (0.7f + 0.1f * Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub19_4057.method630((byte)122)));
            Class98_Sub37.aHa4185.ZA(Class299_Sub2.anInt5298, 0.69921875f, 1.2f, -200.0f, -240.0f, -200.0f);
            Class98_Sub37.aHa4185.L(Class189.anInt1455, -1, n);
            Class98_Sub37.aHa4185.method1775(Class246_Sub7.aClass48_5119);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pg.E(" + n + ')');
        }
    }
    
    final void method1453(final byte b, final Class98_Sub22 class98_Sub22) {
        try {
            class98_Sub22.method1194(5, b - 104);
            class98_Sub22.method1194(this.anInt4147, -115);
            class98_Sub22.method1194(this.aBoolean4132 ? 1 : 0, 85);
            class98_Sub22.method1194(this.anInt4133, b - 137);
            if (b != 17) {
                method1452(-7);
            }
            class98_Sub22.method1194(this.anInt4136, 95);
            class98_Sub22.method1194(this.anInt4137, 114);
            class98_Sub22.method1194(this.anInt4148, b ^ 0x26);
            class98_Sub22.method1194(this.anInt4142, -36);
            class98_Sub22.method1194(this.aBoolean4138 ? 1 : 0, b ^ 0x63);
            class98_Sub22.writeShort(this.anInt4139, 1571862888);
            class98_Sub22.method1194(this.anInt4145, -36);
            class98_Sub22.method1225(-24472, this.anInt4129);
            class98_Sub22.writeShort(this.anInt4150, b + 1571862871);
            class98_Sub22.method1194(this.anInt4141, -101);
            class98_Sub22.method1194(this.anInt4149, 114);
            class98_Sub22.method1194(this.anInt4140, -128);
            class98_Sub22.method1181(this.aString4131, -1);
            class98_Sub22.method1181(this.aString4143, -1);
            class98_Sub22.method1181(this.aString4146, -1);
            class98_Sub22.method1181(this.aString4135, -1);
            class98_Sub22.method1194(this.anInt4130, -76);
            class98_Sub22.writeShort(this.anInt4134, 1571862888);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pg.D(" + b + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final void method1454(final int n) {
        try {
            if (n < -6) {
                if (~this.aString4131.length() < -41) {
                    this.aString4131 = this.aString4131.substring(0, 40);
                }
                if (~this.aString4143.length() < -41) {
                    this.aString4143 = this.aString4143.substring(0, 40);
                }
                if (this.aString4146.length() > 10) {
                    this.aString4146 = this.aString4146.substring(0, 10);
                }
                if (~this.aString4135.length() < -11) {
                    this.aString4135 = this.aString4135.substring(0, 10);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pg.C(" + n + ')');
        }
    }
    
    final int method1455(final byte b) {
        try {
            if (b < 112) {
                return -52;
            }
            return 23 + Class98_Sub26.method1275(this.aString4131, false) + Class98_Sub26.method1275(this.aString4143, false) + Class98_Sub26.method1275(this.aString4146, false) + Class98_Sub26.method1275(this.aString4135, false);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pg.B(" + b + ')');
        }
    }
    
    public static void method1456(final boolean b) {
        try {
            if (b) {
                Class98_Sub35.aRectangleArray4144 = null;
            }
            Class98_Sub35.aRectangleArray4144 = null;
            Class98_Sub35.aClass63_4151 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pg.A(" + b + ')');
        }
    }
    
    public Class98_Sub35() {
    }
    
    Class98_Sub35(final boolean b, final Class88 class88) {
        try {
            if (b) {
                if (!Class88.aString699.startsWith("win")) {
                    if (Class88.aString699.startsWith("mac")) {
                        this.anInt4147 = 2;
                    }
                    else if (Class88.aString699.startsWith("linux")) {
                        this.anInt4147 = 3;
                    }
                    else {
                        this.anInt4147 = 4;
                    }
                }
                else {
                    this.anInt4147 = 1;
                }
                if (!Class88.aString690.startsWith("amd64") && !Class88.aString690.startsWith("x86_64")) {
                    this.aBoolean4132 = false;
                }
                else {
                    this.aBoolean4132 = true;
                }
                if (~this.anInt4147 == 0xFFFFFFFE) {
                    if (Class88.aString676.indexOf("4.0") == -1) {
                        if (~Class88.aString676.indexOf("4.1") != 0x0) {
                            this.anInt4133 = 2;
                        }
                        else if (~Class88.aString676.indexOf("4.9") != 0x0) {
                            this.anInt4133 = 3;
                        }
                        else if (Class88.aString676.indexOf("5.0") == -1) {
                            if (Class88.aString676.indexOf("5.1") == -1) {
                                if (Class88.aString676.indexOf("6.0") == -1) {
                                    if (Class88.aString676.indexOf("6.1") != -1) {
                                        this.anInt4133 = 7;
                                    }
                                }
                                else {
                                    this.anInt4133 = 6;
                                }
                            }
                            else {
                                this.anInt4133 = 5;
                            }
                        }
                        else {
                            this.anInt4133 = 4;
                        }
                    }
                    else {
                        this.anInt4133 = 1;
                    }
                }
                else if (this.anInt4147 == 2) {
                    if (~Class88.aString676.indexOf("10.4") != 0x0) {
                        this.anInt4133 = 20;
                    }
                    else if (~Class88.aString676.indexOf("10.5") == 0x0) {
                        if (Class88.aString676.indexOf("10.6") != -1) {
                            this.anInt4133 = 22;
                        }
                    }
                    else {
                        this.anInt4133 = 21;
                    }
                }
                if (~Class88.aString696.toLowerCase().indexOf("sun") != 0x0) {
                    this.anInt4136 = 1;
                }
                else if (~Class88.aString696.toLowerCase().indexOf("microsoft") != 0x0) {
                    this.anInt4136 = 2;
                }
                else if (Class88.aString696.toLowerCase().indexOf("apple") == -1) {
                    this.anInt4136 = 4;
                }
                else {
                    this.anInt4136 = 3;
                }
                int i = 2;
                int anInt4137 = 0;
                try {
                    while (i < Class88.aString692.length()) {
                        final char char1 = Class88.aString692.charAt(i);
                        if (char1 < '0') {
                            break;
                        }
                        if (char1 > '9') {
                            break;
                        }
                        ++i;
                        anInt4137 = 10 * anInt4137 + (char1 - '0');
                    }
                }
                catch (Exception ex2) {}
                this.anInt4137 = anInt4137;
                int n = 1 + Class88.aString692.indexOf(46, 2);
                int anInt4138 = 0;
                try {
                    while (Class88.aString692.length() > n) {
                        final char char2 = Class88.aString692.charAt(n);
                        if (~char2 > -49) {
                            break;
                        }
                        if (char2 > '9') {
                            break;
                        }
                        ++n;
                        anInt4138 = -48 - -char2 + 10 * anInt4138;
                    }
                }
                catch (Exception ex3) {}
                this.anInt4148 = anInt4138;
                int n2 = Class88.aString692.indexOf(95, 4) + 1;
                int anInt4139 = 0;
                try {
                    while (Class88.aString692.length() > n2) {
                        final char char3 = Class88.aString692.charAt(n2);
                        if (~char3 > -49) {
                            break;
                        }
                        if (char3 > '9') {
                            break;
                        }
                        anInt4139 = char3 + (-48 + anInt4139 * 10);
                        ++n2;
                    }
                }
                catch (Exception ex4) {}
                this.anInt4142 = anInt4139;
                this.anInt4139 = Class292.anInt3359;
                if (!class88.aBoolean682) {
                    this.aBoolean4138 = true;
                }
                else {
                    this.aBoolean4138 = false;
                }
                if (this.anInt4137 <= 3) {
                    this.anInt4145 = 0;
                }
                else {
                    this.anInt4145 = Class98_Sub46_Sub19.anInt6065;
                }
                try {
                    final int[] cpuInfo = HardwareInfo.getCPUInfo();
                    if (cpuInfo != null && ~cpuInfo.length == 0xFFFFFFF8) {
                        this.anInt4149 = cpuInfo[4];
                        this.anInt4140 = cpuInfo[5];
                        this.anInt4141 = cpuInfo[3];
                        this.anInt4129 = cpuInfo[6];
                        this.anInt4150 = cpuInfo[2];
                    }
                }
                catch (Throwable t) {
                    Class305_Sub1.method3585(t, -121, t.getMessage() + " (Recovered)");
                    this.anInt4129 = 0;
                }
            }
            if (this.aString4135 == null) {
                this.aString4135 = "";
            }
            if (this.aString4143 == null) {
                this.aString4143 = "";
            }
            if (this.aString4131 == null) {
                this.aString4131 = "";
            }
            if (this.aString4146 == null) {
                this.aString4146 = "";
            }
            this.method1454(-120);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pg.<init>(" + b + ',' + ((class88 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class98_Sub35.aRectangleArray4144 = new Rectangle[100];
        for (int i = 0; i < 100; ++i) {
            Class98_Sub35.aRectangleArray4144[i] = new Rectangle();
        }
        Class98_Sub35.aClass63_4151 = new Class63();
    }
}
