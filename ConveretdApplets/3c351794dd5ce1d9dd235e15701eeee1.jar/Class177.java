import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class177
{
    int anInt1373;
    int anInt1374;
    static Class103 aClass103_1375;
    static int anInt1376;
    boolean aBoolean1377;
    static float aFloat1378;
    int anInt1379;
    int anInt1380;
    static IncomingOpcode aClass58_1381;
    static Class332[] aClass332Array1382;
    boolean aBoolean1383;
    int anInt1384;
    
    private final void method2583(final Class98_Sub22 class98_Sub22, final int n, final int n2, final int n3) {
        try {
            if (n2 == 6) {
                if (n != 1) {
                    if (~n == 0xFFFFFFFD) {
                        this.anInt1374 = 1 + class98_Sub22.readShort((byte)127);
                        this.anInt1380 = class98_Sub22.readShort((byte)127) + 1;
                    }
                    else if (~n != 0xFFFFFFFC) {
                        if (~n != 0xFFFFFFFB) {
                            if (~n != 0xFFFFFFFA) {
                                if (n != 6) {
                                    if (n == 7) {
                                        this.aBoolean1377 = true;
                                    }
                                }
                                else {
                                    this.aBoolean1383 = true;
                                }
                            }
                            else {
                                this.anInt1384 = class98_Sub22.readUnsignedByte((byte)22);
                            }
                        }
                        else {
                            this.anInt1379 = class98_Sub22.readUnsignedByte((byte)(-100));
                        }
                    }
                    else {
                        class98_Sub22.readSignedByte((byte)(-19));
                    }
                }
                else {
                    this.anInt1373 = class98_Sub22.readShort((byte)127);
                    if (this.anInt1373 == 65535) {
                        this.anInt1373 = -1;
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lu.B(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static final Class336 method2584(final ha_Sub1 ha_Sub1, final Class345[] array, final boolean b) {
        try {
            for (int n = 0; ~array.length < ~n; ++n) {
                if (array[n] == null || ~array[n].aLong2891 >= -1L) {
                    return null;
                }
            }
            final long glCreateProgramObjectARB = OpenGL.glCreateProgramObjectARB();
            for (int n2 = 0; ~n2 > ~array.length; ++n2) {
                OpenGL.glAttachObjectARB(glCreateProgramObjectARB, array[n2].aLong2891);
            }
            OpenGL.glLinkProgramARB(glCreateProgramObjectARB);
            if (!b) {
                method2584(null, null, false);
            }
            OpenGL.glGetObjectParameterivARB(glCreateProgramObjectARB, 35714, Class145.anIntArray1177, 0);
            if (~Class145.anIntArray1177[0] == -1) {
                if (~Class145.anIntArray1177[0] == -1) {
                    System.out.println("Shader linking failed:");
                }
                OpenGL.glGetObjectParameterivARB(glCreateProgramObjectARB, 35716, Class145.anIntArray1177, 1);
                if (~Class145.anIntArray1177[1] < -2) {
                    final byte[] array2 = new byte[Class145.anIntArray1177[1]];
                    OpenGL.glGetInfoLogARB(glCreateProgramObjectARB, Class145.anIntArray1177[1], Class145.anIntArray1177, 0, array2, 0);
                    System.out.println(new String(array2));
                }
                if (Class145.anIntArray1177[0] == 0) {
                    for (int n3 = 0; ~array.length < ~n3; ++n3) {
                        OpenGL.glDetachObjectARB(glCreateProgramObjectARB, array[n3].aLong2891);
                    }
                    OpenGL.glDeleteObjectARB(glCreateProgramObjectARB);
                    return null;
                }
            }
            return new Class336(ha_Sub1, glCreateProgramObjectARB, array);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lu.A(" + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + ((array != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    public static void method2585(final byte b) {
        try {
            Class177.aClass332Array1382 = null;
            if (b != 70) {
                method2584(null, null, false);
            }
            Class177.aClass58_1381 = null;
            Class177.aClass103_1375 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lu.D(" + b + ')');
        }
    }
    
    final void method2586(final byte b, final Class98_Sub22 class98_Sub22, final int n) {
        try {
            while (true) {
                final int unsignedByte = class98_Sub22.readUnsignedByte((byte)(-125));
                if (~unsignedByte == -1) {
                    break;
                }
                this.method2583(class98_Sub22, unsignedByte, 6, n);
            }
            if (b <= 71) {
                method2585((byte)(-61));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lu.C(" + b + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    public Class177() {
        this.anInt1373 = -1;
        this.anInt1374 = 64;
        this.anInt1380 = 64;
        this.aBoolean1377 = false;
        this.aBoolean1383 = false;
        this.anInt1384 = 1;
        this.anInt1379 = 2;
    }
    
    static {
        Class177.anInt1376 = 0;
        Class177.aFloat1378 = 1.0f;
        Class177.aClass58_1381 = new IncomingOpcode(5, 6);
    }
}
