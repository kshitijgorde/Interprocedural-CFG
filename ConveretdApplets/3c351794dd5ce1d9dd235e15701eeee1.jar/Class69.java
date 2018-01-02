import jaclib.memory.Source;
import jaggl.OpenGL;
import jaclib.memory.Buffer;
import jaggl.MapBuffer;
import jaclib.memory.heap.NativeHeapBuffer;
import java.math.BigInteger;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class69 implements Interface2
{
    private int anInt3212;
    static BigInteger aBigInteger3213;
    private int anInt3214;
    private NativeHeapBuffer aNativeHeapBuffer3215;
    private int anInt3216;
    ha_Sub3_Sub2 aHa_Sub3_Sub2_3217;
    private int anInt3218;
    private int anInt3219;
    static int[] anIntArray3220;
    private boolean aBoolean3221;
    static int[] anIntArray3222;
    static boolean aBoolean3223;
    
    final Buffer method694(final boolean b, final MapBuffer mapBuffer, final int n) {
        try {
            if (this.anInt3216 == 0) {
                this.method702((byte)(-72));
                if (~this.anInt3219 >= -1) {
                    this.anInt3216 = 2;
                    return this.aNativeHeapBuffer3215;
                }
                OpenGL.glBindBufferARB(this.anInt3212, this.anInt3219);
                if (b) {
                    OpenGL.glBufferDataARBub(this.anInt3212, this.anInt3214, null, 0, this.aBoolean3221 ? 35040 : 35044);
                    if (this.anInt3218 <= this.aHa_Sub3_Sub2_3217.aNativeHeapBuffer4521.c) {
                        this.anInt3216 = 1;
                        return this.aHa_Sub3_Sub2_3217.aNativeHeapBuffer4521;
                    }
                }
                if (!mapBuffer.a() && mapBuffer.a(this.anInt3212, this.anInt3218, 35001)) {
                    this.anInt3216 = 2;
                    return mapBuffer;
                }
            }
            if (n != -15793) {
                this.method698(-18);
            }
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ei.M(" + b + ',' + ((mapBuffer != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final long method695(final int n) {
        try {
            if (n != -30277) {
                this.aHa_Sub3_Sub2_3217 = null;
            }
            if (~this.anInt3219 != -1) {
                return 0L;
            }
            return this.aNativeHeapBuffer3215.getAddress();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ei.H(" + n + ')');
        }
    }
    
    static final int method696(final float n, final float n2, final byte b, final float n3) {
        try {
            final float n4 = (n >= 0.0f) ? n : (-n);
            final float n5 = (n3 < 0.0f) ? (-n3) : n3;
            if (b > -51) {
                Class69.aBigInteger3213 = null;
            }
            final float n6 = (n2 >= 0.0f) ? n2 : (-n2);
            if (n5 <= n4 || n6 >= n5) {
                if (n4 >= n6 || n5 >= n6) {
                    if (n <= 0.0f) {
                        return 5;
                    }
                    return 4;
                }
                else {
                    if (n2 > 0.0f) {
                        return 2;
                    }
                    return 3;
                }
            }
            else {
                if (n3 > 0.0f) {
                    return 0;
                }
                return 1;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ei.B(" + n + ',' + n2 + ',' + b + ',' + n3 + ')');
        }
    }
    
    final boolean method697(final Source source, final int n, final int n2) {
        try {
            Label_0170: {
                if (n2 > this.anInt3214) {
                    this.method702((byte)(-76));
                    if (~this.anInt3219 >= -1) {
                        throw new RuntimeException("ARGH!");
                    }
                    OpenGL.glBindBufferARB(this.anInt3212, this.anInt3219);
                    OpenGL.glBufferDataARBa(this.anInt3212, n2, source.getAddress(), this.aBoolean3221 ? 35040 : 35044);
                    final ha_Sub3_Sub2 aHa_Sub3_Sub2_3217 = this.aHa_Sub3_Sub2_3217;
                    aHa_Sub3_Sub2_3217.anInt4538 += n2 - this.anInt3218;
                    this.anInt3214 = n2;
                    if (!client.aBoolean3553) {
                        break Label_0170;
                    }
                }
                if (this.anInt3219 <= 0) {
                    throw new RuntimeException("ARGH!");
                }
                OpenGL.glBindBufferARB(this.anInt3212, this.anInt3219);
                OpenGL.glBufferSubDataARBa(this.anInt3212, 0, this.anInt3218, source.getAddress());
                final ha_Sub3_Sub2 aHa_Sub3_Sub2_3218 = this.aHa_Sub3_Sub2_3217;
                aHa_Sub3_Sub2_3218.anInt4538 += -this.anInt3218 + n2;
            }
            this.anInt3218 = n2;
            return n == 1 || true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ei.C(" + ((source != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    final void method698(final int n) {
        try {
            if (n != 18569) {
                this.anInt3214 = -80;
            }
            if (this.aHa_Sub3_Sub2_3217.aBoolean6137) {
                OpenGL.glBindBufferARB(this.anInt3212, this.anInt3219);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ei.A(" + n + ')');
        }
    }
    
    public static void method699(final int n) {
        try {
            Class69.aBigInteger3213 = null;
            Class69.anIntArray3222 = null;
            Class69.anIntArray3220 = null;
            if (n > -93) {
                method699(25);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ei.D(" + n + ')');
        }
    }
    
    @Override
    protected final void finalize() throws Throwable {
        try {
            this.method72(false);
            super.finalize();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ei.finalize()");
        }
    }
    
    void method76(final int n, final int n2) {
        try {
            if (~n < ~this.anInt3214) {
                this.method702((byte)(-85));
                Label_0109: {
                    if (~this.anInt3219 < -1) {
                        OpenGL.glBindBufferARB(this.anInt3212, this.anInt3219);
                        OpenGL.glBufferDataARBub(this.anInt3212, n, null, 0, this.aBoolean3221 ? 35040 : 35044);
                        final ha_Sub3_Sub2 aHa_Sub3_Sub2_3217 = this.aHa_Sub3_Sub2_3217;
                        aHa_Sub3_Sub2_3217.anInt4538 += -this.anInt3214 + n;
                        if (!client.aBoolean3553) {
                            break Label_0109;
                        }
                    }
                    this.aNativeHeapBuffer3215 = this.aHa_Sub3_Sub2_3217.method1947(n, false, n2 ^ 0x512B);
                }
                this.anInt3214 = n;
            }
            this.anInt3218 = n;
            if (n2 != 20779) {
                Class69.anIntArray3222 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ei.G(" + n + ',' + n2 + ')');
        }
    }
    
    static final void method700(final boolean b, final byte[][][] array, final int n, final byte b2, final int n2, final int n3, final boolean b3) {
        final int n4 = b ? 1 : 0;
        Class302.anInt2523 = 0;
        Class353.anInt3009 = 0;
        ++Class356.anInt3020;
        if ((n3 & 0x2) == 0x0) {
            for (Class246_Sub3 aClass246_Sub3_5090 = Class379.aClass246_Sub3Array3198[n4]; aClass246_Sub3_5090 != null; aClass246_Sub3_5090 = aClass246_Sub3_5090.aClass246_Sub3_5090) {
                if (!InputStream_Sub1.method121(aClass246_Sub3_5090, b, array, n, b2)) {
                    Class111.method2098(aClass246_Sub3_5090);
                    if (aClass246_Sub3_5090.anInt5083 != -1) {
                        Class32.aClass246_Sub3Array307[Class302.anInt2523++] = aClass246_Sub3_5090;
                    }
                }
            }
        }
        if ((n3 & 0x1) == 0x0) {
            for (Class246_Sub3 aClass246_Sub3_5091 = Class359.aClass246_Sub3Array3056[n4]; aClass246_Sub3_5091 != null; aClass246_Sub3_5091 = aClass246_Sub3_5091.aClass246_Sub3_5090) {
                if (!InputStream_Sub1.method121(aClass246_Sub3_5091, b, array, n, b2)) {
                    Class111.method2098(aClass246_Sub3_5091);
                    if (aClass246_Sub3_5091.anInt5083 != -1) {
                        Class246_Sub4_Sub2.aClass246_Sub3Array6173[Class353.anInt3009++] = aClass246_Sub3_5091;
                    }
                }
            }
            for (Class246_Sub3 aClass246_Sub3_5092 = Class130.aClass246_Sub3Array1029[n4]; aClass246_Sub3_5092 != null; aClass246_Sub3_5092 = aClass246_Sub3_5092.aClass246_Sub3_5090) {
                if (!InputStream_Sub1.method121(aClass246_Sub3_5092, b, array, n, b2)) {
                    if (aClass246_Sub3_5092.method2987(6540)) {
                        Class111.method2098(aClass246_Sub3_5092);
                        if (aClass246_Sub3_5092.anInt5083 != -1) {
                            Class246_Sub4_Sub2.aClass246_Sub3Array6173[Class353.anInt3009++] = aClass246_Sub3_5092;
                        }
                    }
                    else {
                        Class111.method2098(aClass246_Sub3_5092);
                        if (aClass246_Sub3_5092.anInt5083 != -1) {
                            Class32.aClass246_Sub3Array307[Class302.anInt2523++] = aClass246_Sub3_5092;
                        }
                    }
                }
            }
            if (!b) {
                for (int i = 0; i < Class347.anInt2907; ++i) {
                    if (!InputStream_Sub1.method121(Class246_Sub3_Sub5_Sub2.aClass246_Sub3_Sub4Array6273[i], b, array, n, b2)) {
                        Class111.method2098(Class246_Sub3_Sub5_Sub2.aClass246_Sub3_Sub4Array6273[i]);
                        if (Class246_Sub3_Sub5_Sub2.aClass246_Sub3_Sub4Array6273[i].anInt5083 != -1) {
                            if (Class246_Sub3_Sub5_Sub2.aClass246_Sub3_Sub4Array6273[i].method2987(6540)) {
                                Class246_Sub4_Sub2.aClass246_Sub3Array6173[Class353.anInt3009++] = Class246_Sub3_Sub5_Sub2.aClass246_Sub3_Sub4Array6273[i];
                            }
                            else {
                                Class32.aClass246_Sub3Array307[Class302.anInt2523++] = Class246_Sub3_Sub5_Sub2.aClass246_Sub3_Sub4Array6273[i];
                            }
                        }
                    }
                }
            }
        }
        if (Class302.anInt2523 > 0) {
            Class283.method3343(Class32.aClass246_Sub3Array307, 0, Class302.anInt2523 - 1);
            for (int j = 0; j < Class302.anInt2523; ++j) {
                Class167.method2529(Class32.aClass246_Sub3Array307[j], true, b3);
            }
        }
        if (Class348.aBoolean2914) {
            Class98_Sub10_Sub30.aHa5709.method1818(0, null);
        }
        if ((n3 & 0x2) == 0x0) {
            for (int k = Class32.anInt305; k < Class364.anInt3103; ++k) {
                if (k >= n && array != null) {
                    int length = Class74.aBooleanArrayArray551.length;
                    if (Class306.anInt2561 + Class74.aBooleanArrayArray551.length > Class366.anInt3112) {
                        length -= Class306.anInt2561 + Class74.aBooleanArrayArray551.length - Class366.anInt3112;
                    }
                    int length2 = Class74.aBooleanArrayArray551[0].length;
                    if (OutgoingOpcode.anInt1318 + Class74.aBooleanArrayArray551[0].length > Class64_Sub9.anInt3662) {
                        length2 -= OutgoingOpcode.anInt1318 + Class74.aBooleanArrayArray551[0].length - Class64_Sub9.anInt3662;
                    }
                    boolean[][] aBooleanArrayArray2702 = Class319.aBooleanArrayArray2702;
                    if (Class302.aBoolean2526) {
                        if (Class375.aBoolean3170) {
                            aBooleanArrayArray2702 = Class34.aBooleanArrayArrayArray325[k];
                        }
                        for (int l = Class67.anInt521; l < length; ++l) {
                            final int n5 = l + Class306.anInt2561 - Class67.anInt521;
                            for (int anInt4184 = Class98_Sub37.anInt4184; anInt4184 < length2; ++anInt4184) {
                                aBooleanArrayArray2702[l][anInt4184] = false;
                                if (Class74.aBooleanArrayArray551[l][anInt4184]) {
                                    final int n6 = anInt4184 + OutgoingOpcode.anInt1318 - Class98_Sub37.anInt4184;
                                    int n7 = k;
                                    while (n7 >= 0) {
                                        if (Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[n7][n5][n6] != null && Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[n7][n5][n6].aByte1322 == k) {
                                            if ((n7 >= n && array[n7][n5][n6] == b2) || Class76_Sub5.method758((byte)95, k, n6, n5)) {
                                                aBooleanArrayArray2702[l][anInt4184] = false;
                                                break;
                                            }
                                            aBooleanArrayArray2702[l][anInt4184] = true;
                                            break;
                                        }
                                        else {
                                            --n7;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (Class375.aBoolean3170) {
                        if (n2 >= 0) {
                            Class78.aSArray594[k].method3416(0, 0, 0, null, false, n2, n3);
                        }
                        else {
                            Class78.aSArray594[k].method3426(0, 0, 0, null, false, n3);
                        }
                        for (int n8 = 0; n8 < Class294.anInt2407; ++n8) {
                            Class98_Sub43_Sub3.aClass245Array5922[n8].method2961(true, new Class246_Sub10(k + 1));
                        }
                    }
                    else if (n2 >= 0) {
                        Class78.aSArray594[k].method3416(Class241.anInt1845, Class64_Sub26.anInt3714, Class259.anInt1959, Class319.aBooleanArrayArray2702, false, n2, n3);
                    }
                    else {
                        Class78.aSArray594[k].method3426(Class241.anInt1845, Class64_Sub26.anInt3714, Class259.anInt1959, Class319.aBooleanArrayArray2702, false, n3);
                    }
                }
                else {
                    int length3 = Class74.aBooleanArrayArray551.length;
                    if (Class306.anInt2561 + Class74.aBooleanArrayArray551.length > Class366.anInt3112) {
                        length3 -= Class306.anInt2561 + Class74.aBooleanArrayArray551.length - Class366.anInt3112;
                    }
                    int length4 = Class74.aBooleanArrayArray551[0].length;
                    if (OutgoingOpcode.anInt1318 + Class74.aBooleanArrayArray551[0].length > Class64_Sub9.anInt3662) {
                        length4 -= OutgoingOpcode.anInt1318 + Class74.aBooleanArrayArray551[0].length - Class64_Sub9.anInt3662;
                    }
                    boolean[][] aBooleanArrayArray2703 = Class319.aBooleanArrayArray2702;
                    if (Class302.aBoolean2526) {
                        if (Class375.aBoolean3170) {
                            aBooleanArrayArray2703 = Class34.aBooleanArrayArrayArray325[k];
                        }
                        for (int anInt4185 = Class67.anInt521; anInt4185 < length3; ++anInt4185) {
                            final int n9 = anInt4185 + Class306.anInt2561 - Class67.anInt521;
                            for (int anInt4186 = Class98_Sub37.anInt4184; anInt4186 < length4; ++anInt4186) {
                                if (Class74.aBooleanArrayArray551[anInt4185][anInt4186] && !Class76_Sub5.method758((byte)110, k, anInt4186 + OutgoingOpcode.anInt1318 - Class98_Sub37.anInt4184, n9)) {
                                    aBooleanArrayArray2703[anInt4185][anInt4186] = true;
                                }
                                else {
                                    aBooleanArrayArray2703[anInt4185][anInt4186] = false;
                                }
                            }
                        }
                    }
                    if (Class375.aBoolean3170) {
                        if (n2 >= 0) {
                            Class78.aSArray594[k].method3416(0, 0, 0, null, false, n2, n3);
                        }
                        else {
                            Class78.aSArray594[k].method3426(0, 0, 0, null, false, n3);
                        }
                        for (int n10 = 0; n10 < Class294.anInt2407; ++n10) {
                            Class98_Sub43_Sub3.aClass245Array5922[n10].method2961(true, new Class246_Sub10(k + 1));
                        }
                    }
                    else if (n2 >= 0) {
                        Class78.aSArray594[k].method3416(Class241.anInt1845, Class64_Sub26.anInt3714, Class259.anInt1959, Class319.aBooleanArrayArray2702, true, n2, n3);
                    }
                    else {
                        Class78.aSArray594[k].method3426(Class241.anInt1845, Class64_Sub26.anInt3714, Class259.anInt1959, Class319.aBooleanArrayArray2702, true, n3);
                    }
                }
            }
        }
        if (Class353.anInt3009 > 0) {
            r_Sub2.method1656(Class246_Sub4_Sub2.aClass246_Sub3Array6173, 0, Class353.anInt3009 - 1);
            for (int n11 = 0; n11 < Class353.anInt3009; ++n11) {
                Class167.method2529(Class246_Sub4_Sub2.aClass246_Sub3Array6173[n11], true, b3);
            }
        }
    }
    
    static final void method701(final int n) {
        if (n == 0) {
            if (Class294.anInt2407 == 2) {
                Class98_Sub46_Sub5.aClass174Array5970[0].method2564(Class98_Sub43_Sub3.aClass245Array5922[0]);
                Class98_Sub46_Sub5.aClass174Array5970[1].method2564(Class98_Sub43_Sub3.aClass245Array5922[1]);
            }
            else if (Class294.anInt2407 == 3) {
                Class98_Sub46_Sub5.aClass174Array5970[0].method2564(Class98_Sub43_Sub3.aClass245Array5922[0]);
                Class98_Sub46_Sub5.aClass174Array5970[1].method2564(Class98_Sub43_Sub3.aClass245Array5922[1]);
                Class98_Sub46_Sub5.aClass174Array5970[2].method2564(Class98_Sub43_Sub3.aClass245Array5922[2]);
            }
            else {
                Class98_Sub46_Sub5.aClass174Array5970[0].method2564(Class98_Sub43_Sub3.aClass245Array5922[0]);
                Class98_Sub46_Sub5.aClass174Array5970[1].method2564(Class98_Sub43_Sub3.aClass245Array5922[1]);
                Class98_Sub46_Sub5.aClass174Array5970[2].method2564(Class98_Sub43_Sub3.aClass245Array5922[2]);
                Class98_Sub46_Sub5.aClass174Array5970[3].method2564(Class98_Sub43_Sub3.aClass245Array5922[3]);
            }
        }
        else if (n == 1) {
            if (Class294.anInt2407 == 2) {
                Class98_Sub46_Sub5.aClass174Array5970[0].method2564(Class98_Sub43_Sub3.aClass245Array5922[2]);
            }
            else if (Class294.anInt2407 == 3) {
                Class98_Sub46_Sub5.aClass174Array5970[0].method2564(Class98_Sub43_Sub3.aClass245Array5922[3]);
                Class98_Sub46_Sub5.aClass174Array5970[1].method2564(Class98_Sub43_Sub3.aClass245Array5922[4]);
            }
            else {
                Class98_Sub46_Sub5.aClass174Array5970[0].method2564(Class98_Sub43_Sub3.aClass245Array5922[4]);
                Class98_Sub46_Sub5.aClass174Array5970[1].method2564(Class98_Sub43_Sub3.aClass245Array5922[5]);
                Class98_Sub46_Sub5.aClass174Array5970[2].method2564(Class98_Sub43_Sub3.aClass245Array5922[6]);
            }
        }
        else if (n == 2) {
            if (Class294.anInt2407 == 2) {
                Class98_Sub46_Sub5.aClass174Array5970[0].method2564(Class98_Sub43_Sub3.aClass245Array5922[3]);
            }
            else if (Class294.anInt2407 == 3) {
                Class98_Sub46_Sub5.aClass174Array5970[0].method2564(Class98_Sub43_Sub3.aClass245Array5922[5]);
            }
            else {
                Class98_Sub46_Sub5.aClass174Array5970[0].method2564(Class98_Sub43_Sub3.aClass245Array5922[7]);
            }
        }
    }
    
    void method72(final boolean b) {
        try {
            if (~this.anInt3219 < -1) {
                this.aHa_Sub3_Sub2_3217.method2084(1, this.anInt3218, this.anInt3219);
                this.anInt3219 = -1;
            }
            if (b) {
                this.method703((byte)(-53), null);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ei.L(" + b + ')');
        }
    }
    
    @Override
    public int method2(final int n) {
        try {
            if (n != 200) {
                Class69.aBoolean3223 = true;
            }
            return this.anInt3218;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ei.E(" + n + ')');
        }
    }
    
    private final void method702(final byte b) {
        try {
            if (b < -53 && this.anInt3219 < 0) {
                if (this.aHa_Sub3_Sub2_3217.aBoolean6137) {
                    OpenGL.glGenBuffersARB(1, Class190.anIntArray1463, 0);
                    this.anInt3219 = Class190.anIntArray1463[0];
                    OpenGL.glBindBufferARB(this.anInt3212, this.anInt3219);
                }
                else {
                    this.anInt3219 = 0;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ei.J(" + b + ')');
        }
    }
    
    Class69(final ha_Sub3_Sub2 aHa_Sub3_Sub2_3217, final int anInt3212, final boolean aBoolean3221) {
        this.anInt3216 = 0;
        this.anInt3219 = -1;
        try {
            this.aBoolean3221 = aBoolean3221;
            this.aHa_Sub3_Sub2_3217 = aHa_Sub3_Sub2_3217;
            this.anInt3212 = anInt3212;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ei.<init>(" + ((aHa_Sub3_Sub2_3217 != null) ? "{...}" : "null") + ',' + anInt3212 + ',' + aBoolean3221 + ')');
        }
    }
    
    final boolean method703(final byte b, final MapBuffer mapBuffer) {
        try {
            boolean b2 = true;
            if (b != -68) {
                method696(-2.5029087f, 1.1315566f, (byte)(-51), -0.1177615f);
            }
            if (this.anInt3216 != 0) {
                Label_0089: {
                    if (this.anInt3219 > 0) {
                        OpenGL.glBindBufferARB(this.anInt3212, this.anInt3219);
                        if (this.anInt3216 != 1) {
                            b2 = mapBuffer.b();
                            if (!client.aBoolean3553) {
                                break Label_0089;
                            }
                        }
                        OpenGL.glBufferSubDataARBa(this.anInt3212, 0, this.anInt3214, this.aHa_Sub3_Sub2_3217.aNativeHeapBuffer4521.getAddress());
                    }
                }
                this.anInt3216 = 0;
            }
            return b2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ei.F(" + b + ',' + ((mapBuffer != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class69.aBigInteger3213 = new BigInteger("974b219bf31b50bc1a89e45f6a0a59f21c9a83e19478ae976391cb262dc9b5f8ecfb2e864356ecbf7ca3cdbacd54ea298db5a159b0a64c25e03330fd744fe0a7181244f6ba5f3025afcb3f4122f5fbda4036b9a1b586eb33e732c5c3fd9a210ac8d42df187317c681060a4de30d9391e0b29ce9a6de27470fa3050f8b0868146efc885c62cf88378c9c19414ed3bbf98a4b8e28a8c9266654eccf773ab928973acc6aeec5648a9781463c5802379e7212fb5c480116a349840c5f04bb4b60f78e79d13a4e64b8843ef9d1273d8f80b198468051a93e6540e2510ffb8003046e10dc9bddd7bc30653936d6b178c2c6d086eeedd0a096878accbd80e6b5919806246167992dfad464b8a83e0b2f2cb3263a12b9937c206238a46ec1aea440d75af5a313de6324ac51a24bcaafeab1181b457145eea7a8ddc4009d1b62b118644ec363c521c6c491a879ace7fe9910b5084f8260855c86667c107164aba828126a12a95913507ae5519fa40452e66213918cc12e99430ffd8284b78bf6c827a4b0fbaeaa838dd9cd2e98bb825ff707df9e4465b51be3601dde7de933191328e1a36b0b950a71b681d35d6103a0d7d451cb32dc4f8dd196722a975a391d0048185e05d3ac876af0f15b3f72cbc9ff8132d4d2f29a315e0ad505e183d87c6a6022699c74038803e879bdd124b6f2f5106ea7d703abaaa2dfa86e5b4db14fadf34694b", 16);
        Class69.anIntArray3220 = new int[4096];
        Class69.anIntArray3222 = new int[200];
        Class69.aBoolean3223 = true;
    }
}
