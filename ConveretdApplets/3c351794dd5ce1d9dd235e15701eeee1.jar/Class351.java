import java.awt.Rectangle;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.Vector;
import java.io.File;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class351
{
    static Class85 aClass85_2921;
    static int anInt2922;
    static int[] anIntArray2923;
    static Class98_Sub46_Sub16[] aClass98_Sub46_Sub16Array2924;
    
    static final boolean method3845(final int n, final int anInt6369, final Class98_Sub22_Sub1 class98_Sub22_Sub1) {
        try {
            final int bits = class98_Sub22_Sub1.readBits((byte)(-86), 2);
            if (~bits == -1) {
                if (~class98_Sub22_Sub1.readBits((byte)(-110), 1) != -1) {
                    method3845(-2, anInt6369, class98_Sub22_Sub1);
                }
                final int bits2 = class98_Sub22_Sub1.readBits((byte)(-119), 6);
                final int bits3 = class98_Sub22_Sub1.readBits((byte)(-53), 6);
                if (~class98_Sub22_Sub1.readBits((byte)(-15), 1) == 0xFFFFFFFE) {
                    Class65.anIntArray501[Class38.anInt354++] = anInt6369;
                }
                if (Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[anInt6369] != null) {
                    throw new RuntimeException("hr:lr");
                }
                final Class376 class376 = Class306.aClass376Array2562[anInt6369];
                final Class246_Sub3_Sub4_Sub2_Sub2[] aClass246_Sub3_Sub4_Sub2_Sub2Array5030 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030;
                final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub2 = new Class246_Sub3_Sub4_Sub2_Sub2();
                aClass246_Sub3_Sub4_Sub2_Sub2Array5030[anInt6369] = class246_Sub3_Sub4_Sub2_Sub2;
                final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub3 = class246_Sub3_Sub4_Sub2_Sub2;
                class246_Sub3_Sub4_Sub2_Sub3.anInt6369 = anInt6369;
                if (Class224_Sub3_Sub1.aClass98_Sub22Array6146[anInt6369] != null) {
                    class246_Sub3_Sub4_Sub2_Sub3.method3062(Class224_Sub3_Sub1.aClass98_Sub22Array6146[anInt6369], (byte)73);
                }
                class246_Sub3_Sub4_Sub2_Sub3.method3047(class376.anInt3172, true, 61);
                class246_Sub3_Sub4_Sub2_Sub3.anInt6364 = class376.anInt3177;
                final int anInt6370 = class376.anInt3176;
                final int n2 = anInt6370 >> 55960220;
                final int n3 = (0x3FFE75 & anInt6370) >> -1901701522;
                final int n4 = anInt6370 & 0xFF;
                final int n5 = -Class272.anInt2038 + bits2 + (n3 << 847426022);
                class246_Sub3_Sub4_Sub2_Sub3.aBoolean6534 = class376.aBoolean3175;
                final int n6 = bits3 + ((n4 << 1601007846) + -aa_Sub2.anInt3562);
                class246_Sub3_Sub4_Sub2_Sub3.aByteArray6443[0] = Class98_Sub10_Sub21.aByteArray5642[anInt6369];
                final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub4 = class246_Sub3_Sub4_Sub2_Sub3;
                final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub5 = class246_Sub3_Sub4_Sub2_Sub3;
                final byte b = (byte)n2;
                class246_Sub3_Sub4_Sub2_Sub5.aByte5081 = b;
                class246_Sub3_Sub4_Sub2_Sub4.aByte5088 = b;
                if (Class1.method162(n6, (byte)(-104), n5)) {
                    final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub6 = class246_Sub3_Sub4_Sub2_Sub3;
                    ++class246_Sub3_Sub4_Sub2_Sub6.aByte5081;
                }
                class246_Sub3_Sub4_Sub2_Sub3.method3060(n6, n5, 1470);
                class246_Sub3_Sub4_Sub2_Sub3.aBoolean6532 = false;
                Class306.aClass376Array2562[anInt6369] = null;
                return true;
            }
            else {
                if (bits == 1) {
                    final int bits4 = class98_Sub22_Sub1.readBits((byte)(-17), 2);
                    final int anInt6371 = Class306.aClass376Array2562[anInt6369].anInt3176;
                    Class306.aClass376Array2562[anInt6369].anInt3176 = ((0x3 & bits4 + (anInt6371 >> -1513072196)) << 1924544668) + (anInt6371 & 0xFFFFFFF);
                    return false;
                }
                if (bits == 2) {
                    final int bits5 = class98_Sub22_Sub1.readBits((byte)(-75), 5);
                    final int n7 = bits5 >> 1739733571;
                    final int n8 = bits5 & 0x7;
                    final int anInt6372 = Class306.aClass376Array2562[anInt6369].anInt3176;
                    final int n9 = 0x3 & n7 + (anInt6372 >> 1832736924);
                    int n10 = anInt6372 >> 1823883438 & 0xFF;
                    int n11 = anInt6372 & 0xFF;
                    if (~n8 == -1) {
                        --n11;
                        --n10;
                    }
                    if (n8 == 1) {
                        --n11;
                    }
                    if (~n8 == 0xFFFFFFFD) {
                        ++n10;
                        --n11;
                    }
                    if (~n8 == 0xFFFFFFFC) {
                        --n10;
                    }
                    if (n8 == 4) {
                        ++n10;
                    }
                    if (n8 == 5) {
                        --n10;
                        ++n11;
                    }
                    if (n8 == 6) {
                        ++n11;
                    }
                    if (~n8 == 0xFFFFFFF8) {
                        ++n11;
                        ++n10;
                    }
                    Class306.aClass376Array2562[anInt6369].anInt3176 = (n10 << -893106514) + (n9 << 1563645276) - -n11;
                    return false;
                }
                final int bits6 = class98_Sub22_Sub1.readBits((byte)(-63), 18);
                Class306.aClass376Array2562[anInt6369].anInt3176 = ((0xFFE5 & bits6) >> 1140001512 << 40340942) + ((bits6 >> -720323312 << -1914335332) + (0xFF & bits6));
                return false;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vh.A(" + n + ',' + anInt6369 + ',' + ((class98_Sub22_Sub1 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method3846(final int n, final int n2, final int n3, final int n4) {
        try {
            PlayerUpdateMask.method710("tele " + n3 + "," + (n2 >> -351282842) + "," + (n >> -1268340314) + "," + (n2 & 0x3F) + "," + (n & 0x3F), false, true, (byte)117);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vh.C(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    static final boolean method3847(final int n) {
        try {
            final Hashtable<Object, Object> aHashtable118 = new Hashtable<Object, Object>();
            final Enumeration<Object> keys = Class10.aHashtable118.keys();
            while (keys.hasMoreElements()) {
                final Object nextElement = keys.nextElement();
                aHashtable118.put(nextElement, Class10.aHashtable118.get(nextElement));
            }
            try {
                final Class<?> forName = Class.forName("java.lang.reflect.AccessibleObject");
                final Field declaredField = Class.forName("java.lang.ClassLoader").getDeclaredField("nativeLibraries");
                final Method declaredMethod = forName.getDeclaredMethod("setAccessible", Boolean.TYPE);
                declaredMethod.invoke(declaredField, Boolean.TRUE);
                try {
                    final Enumeration<String> keys2 = Class10.aHashtable118.keys();
                    while (keys2.hasMoreElements()) {
                        final String s = keys2.nextElement();
                        try {
                            final File file = Class124.aHashtable1015.get(s);
                            final Vector vector = (Vector)declaredField.get(Class10.aHashtable118.get(s).getClassLoader());
                            for (int n2 = 0; ~n2 > ~vector.size(); ++n2) {
                                try {
                                    final Object element = vector.elementAt(n2);
                                    final Field declaredField2 = element.getClass().getDeclaredField("name");
                                    declaredMethod.invoke(declaredField2, Boolean.TRUE);
                                    try {
                                        final String s2 = (String)declaredField2.get(element);
                                        if (s2 != null && s2.equalsIgnoreCase(file.getCanonicalPath())) {
                                            final Field declaredField3 = element.getClass().getDeclaredField("handle");
                                            final Method declaredMethod2 = element.getClass().getDeclaredMethod("finalize", (Class<?>[])new Class[0]);
                                            declaredMethod.invoke(declaredField3, Boolean.TRUE);
                                            declaredMethod.invoke(declaredMethod2, Boolean.TRUE);
                                            try {
                                                declaredMethod2.invoke(element, new Object[0]);
                                                declaredField3.set(element, new Integer(0));
                                                aHashtable118.remove(s);
                                            }
                                            catch (Throwable t) {}
                                            declaredMethod.invoke(declaredMethod2, Boolean.FALSE);
                                            declaredMethod.invoke(declaredField3, Boolean.FALSE);
                                        }
                                    }
                                    catch (Throwable t2) {}
                                    declaredMethod.invoke(declaredField2, Boolean.FALSE);
                                }
                                catch (Throwable t3) {}
                            }
                        }
                        catch (Throwable t4) {}
                    }
                }
                catch (Throwable t5) {}
                declaredMethod.invoke(declaredField, Boolean.FALSE);
            }
            catch (Throwable t6) {}
            Class10.aHashtable118 = aHashtable118;
            return Class10.aHashtable118.isEmpty();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vh.E(" + n + ')');
        }
    }
    
    public static void method3848(final byte b) {
        try {
            Class351.anIntArray2923 = null;
            Class351.aClass85_2921 = null;
            Class351.aClass98_Sub46_Sub16Array2924 = null;
            if (b != -47) {
                Class351.anIntArray2923 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vh.D(" + b + ')');
        }
    }
    
    static final void method3849(final int n, final int n2, final int n3, final int n4, final int n5) {
        try {
            if (n2 != -8) {
                method3846(76, 117, 69, 21);
            }
            for (int n6 = 0; ~n6 > ~Class69_Sub2.anInt5335; ++n6) {
                final Rectangle rectangle = Class98_Sub35.aRectangleArray4144[n6];
                if (~n3 > ~(rectangle.width + rectangle.x) && n3 + n4 > rectangle.x && ~n5 > ~(rectangle.y + rectangle.height) && n5 + n > rectangle.y) {
                    Class98_Sub10_Sub20.aBooleanArray5639[n6] = true;
                }
            }
            Class93_Sub1_Sub1.method908(n5 + n, n5, false, n3, n4 + n3);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vh.B(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ')');
        }
    }
    
    static {
        Class351.anInt2922 = 0;
        Class351.anIntArray2923 = new int[] { 1, 0, -1, 0 };
        Class351.aClass85_2921 = new Class85(9, 7);
        Class351.aClass98_Sub46_Sub16Array2924 = new Class98_Sub46_Sub16[14];
    }
}
