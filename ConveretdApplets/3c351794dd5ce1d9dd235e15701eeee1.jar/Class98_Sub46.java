import jaggl.OpenGL;
import java.io.IOException;
import java.util.Date;

// 
// Decompiled by Procyon v0.5.30
// 

class Class98_Sub46 extends Class98
{
    long aLong4259;
    static int anInt4260;
    static int anInt4261;
    Class98_Sub46 aClass98_Sub46_4262;
    static Class279 aClass279_4263;
    static int anInt4264;
    Class98_Sub46 aClass98_Sub46_4265;
    
    final boolean method1522(final byte b) {
        try {
            if (b <= 34) {
                Class98_Sub46.anInt4260 = -110;
            }
            return this.aClass98_Sub46_4265 != null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tm.H(" + b + ')');
        }
    }
    
    public static void method1523(final int n) {
        try {
            if (n != 0) {
                Class98_Sub46.anInt4260 = 103;
            }
            Class98_Sub46.aClass279_4263 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tm.L(" + n + ')');
        }
    }
    
    final void method1524(final byte b) {
        try {
            if (this.aClass98_Sub46_4265 != null) {
                this.aClass98_Sub46_4265.aClass98_Sub46_4262 = this.aClass98_Sub46_4262;
                this.aClass98_Sub46_4262.aClass98_Sub46_4265 = this.aClass98_Sub46_4265;
                this.aClass98_Sub46_4262 = null;
                if (b != -90) {
                    method1523(-94);
                }
                this.aClass98_Sub46_4265 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tm.J(" + b + ')');
        }
    }
    
    static final void method1525(final String s, final int n) {
        try {
            if (Class98_Sub46_Sub20.aStringArray6073 == null) {
                Class264.method3222((byte)(-43));
            }
            Class149.aCalendar1200.setTime(new Date(Class343.method3819(-47)));
            final int value = Class149.aCalendar1200.get(11);
            final int value2 = Class149.aCalendar1200.get(12);
            final int value3 = Class149.aCalendar1200.get(13);
            final String string = Integer.toString(value / 10) + value % 10 + ":" + value2 / 10 + value2 % 10 + ":" + value3 / 10 + value3 % 10;
            final String[] method2142 = Class112.method2142(s, '\n', false);
            for (int n2 = 0; ~n2 > ~method2142.length; ++n2) {
                for (int anInt4080 = Class98_Sub28.anInt4080; ~anInt4080 < -1; --anInt4080) {
                    Class98_Sub46_Sub20.aStringArray6073[anInt4080] = Class98_Sub46_Sub20.aStringArray6073[-1 + anInt4080];
                }
                Class98_Sub46_Sub20.aStringArray6073[0] = string + ": " + method2142[n2];
                if (Class264.aFileOutputStream1969 != null) {
                    try {
                        Class264.aFileOutputStream1969.write(aa.method152(0, Class98_Sub46_Sub20.aStringArray6073[0] + "\n"));
                    }
                    catch (IOException ex2) {}
                }
                if (Class98_Sub28.anInt4080 < Class98_Sub46_Sub20.aStringArray6073.length - 1) {
                    if (~Class54.anInt3395 < -1) {
                        ++Class54.anInt3395;
                    }
                    ++Class98_Sub28.anInt4080;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tm.I(" + ((s != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    static final Class230 method1526(final byte[] array, final ha_Sub3_Sub2 ha_Sub3_Sub2, final int n, final int n2) {
        try {
            if (array == null || ~array.length == -1) {
                return null;
            }
            final long glCreateShaderObjectARB = OpenGL.glCreateShaderObjectARB(n2);
            OpenGL.glShaderSourceRawARB(glCreateShaderObjectARB, array);
            if (n != -25671) {
                Class98_Sub46.anInt4260 = 21;
            }
            OpenGL.glCompileShaderARB(glCreateShaderObjectARB);
            OpenGL.glGetObjectParameterivARB(glCreateShaderObjectARB, 35713, Class231.anIntArray1734, 0);
            if (~Class231.anIntArray1734[0] == -1) {
                if (Class231.anIntArray1734[0] == 0) {
                    System.out.println("Shader compile failed:");
                }
                OpenGL.glGetObjectParameterivARB(glCreateShaderObjectARB, 35716, Class231.anIntArray1734, 1);
                if (~Class231.anIntArray1734[1] < -2) {
                    final byte[] array2 = new byte[Class231.anIntArray1734[1]];
                    OpenGL.glGetInfoLogARB(glCreateShaderObjectARB, Class231.anIntArray1734[1], Class231.anIntArray1734, 0, array2, 0);
                    System.out.println(new String(array2));
                }
                if (~Class231.anIntArray1734[0] == -1) {
                    OpenGL.glDeleteObjectARB(glCreateShaderObjectARB);
                    return null;
                }
            }
            return new Class230(ha_Sub3_Sub2, glCreateShaderObjectARB, n2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tm.K(" + ((array != null) ? "{...}" : "null") + ',' + ((ha_Sub3_Sub2 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    static {
        Class98_Sub46.anInt4260 = 0;
        Class98_Sub46.aClass279_4263 = new Class279("game4", 3);
    }
}
