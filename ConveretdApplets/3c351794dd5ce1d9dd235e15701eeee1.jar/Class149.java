import java.util.TimeZone;
import java.io.IOException;
import java.util.Calendar;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class149
{
    static Calendar aCalendar1200;
    private char aChar1201;
    int anInt1202;
    String aString1203;
    boolean aBoolean1204;
    static Class83 aClass83_1205;
    static Class204 aClass204_1206;
    static IncomingOpcode aClass58_1207;
    static int anInt1208;
    
    static final void method2430(final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            final byte[] array = new byte[24];
            if (b == 0) {
                if (Class195.aClass225_1502 != null) {
                    try {
                        Class195.aClass225_1502.method2846(0L, 0);
                        Class195.aClass225_1502.method2842(array, b);
                        int n;
                        for (n = 0; n < 24 && ~array[n] == -1; ++n) {}
                        if (n >= 24) {
                            throw new IOException();
                        }
                    }
                    catch (Exception ex2) {
                        for (int i = 0; i < 24; ++i) {
                            array[i] = -1;
                        }
                    }
                }
                class98_Sub22.method1217(array, 24, -1, 0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kc.D(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    final void method2431(final Class98_Sub22 class98_Sub22, final int n) {
        try {
            if (n != -1) {
                Class149.aClass83_1205 = null;
            }
            while (true) {
                final int unsignedByte = class98_Sub22.readUnsignedByte((byte)5);
                if (unsignedByte == 0) {
                    break;
                }
                this.method2434(unsignedByte, class98_Sub22, 1);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kc.A(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    public static void method2432(final int n) {
        try {
            Class149.aClass58_1207 = null;
            Class149.aClass204_1206 = null;
            if (n == 0) {
                Class149.aClass83_1205 = null;
                Class149.aCalendar1200 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kc.F(" + n + ')');
        }
    }
    
    final boolean method2433(final boolean b) {
        try {
            return !b && ~this.aChar1201 == 0xFFFFFF8C;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kc.C(" + b + ')');
        }
    }
    
    private final void method2434(final int n, final Class98_Sub22 class98_Sub22, final int n2) {
        try {
            if (n2 == n) {
                this.aChar1201 = Class64_Sub7.method576(class98_Sub22.readSignedByte((byte)(-19)), (byte)127);
            }
            else if (n == 2) {
                this.anInt1202 = class98_Sub22.readInt(-2);
            }
            else if (n == 4) {
                this.aBoolean1204 = false;
            }
            else if (~n == 0xFFFFFFFA) {
                this.aString1203 = class98_Sub22.readString((byte)84);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kc.E(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n2 + ')');
        }
    }
    
    static final void method2435(final int n, final int n2, final int n3) {
        try {
            if (n2 < 78) {
                Class149.aClass83_1205 = null;
            }
            final Class98_Sub11 method3023 = Class246_Sub3_Sub4.method3023(260, Class151_Sub1.aClass171_4968, Class331.aClass117_2811);
            method3023.aClass98_Sub22_Sub1_3865.writeShortA(n, (byte)126);
            method3023.aClass98_Sub22_Sub1_3865.writeLEInt(n3, 1046032984);
            Class98_Sub10_Sub29.sendPacket(false, method3023);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kc.B(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    public Class149() {
        this.aBoolean1204 = true;
    }
    
    static {
        Class149.aCalendar1200 = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        Class149.aClass204_1206 = new Class204();
        Class149.aClass58_1207 = new IncomingOpcode(53, -1);
    }
}
