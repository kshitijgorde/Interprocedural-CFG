import java.lang.reflect.Method;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class85
{
    static int anInt638;
    static Class aClass639;
    
    static final boolean method837(final int n, final int n2) {
        try {
            if (Class246_Sub3_Sub3_Sub1.aBooleanArray6256[n]) {
                return true;
            }
            if (!Class98_Sub17_Sub1.aClass207_5783.method2756(false, n)) {
                return false;
            }
            final int method2761 = Class98_Sub17_Sub1.aClass207_5783.method2761(0, n);
            if (~method2761 == -1) {
                return Class246_Sub3_Sub3_Sub1.aBooleanArray6256[n] = true;
            }
            if (Class159.aClass293ArrayArray1252[n] == null) {
                Class159.aClass293ArrayArray1252[n] = new Class293[method2761];
            }
            for (int i = 0; i < method2761; ++i) {
                if (Class159.aClass293ArrayArray1252[n][i] == null) {
                    final byte[] method2762 = Class98_Sub17_Sub1.aClass207_5783.method2745(i, n, false);
                    if (method2762 != null) {
                        final Class293[] array = Class159.aClass293ArrayArray1252[n];
                        final int n3 = i;
                        final Class293 class293 = new Class293();
                        array[n3] = class293;
                        final Class293 class294 = class293;
                        class294.anInt2248 = (n << -1849090224) - -i;
                        if (method2762[0] != -1) {
                            throw new IllegalStateException("if1");
                        }
                        class294.method3457(new Class98_Sub22(method2762), -947);
                    }
                }
            }
            Class246_Sub3_Sub3_Sub1.aBooleanArray6256[n] = true;
            if (n2 < 1) {
                method838(null);
            }
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fha.A(" + n + ',' + n2 + ')');
        }
    }
    
    static final void method838(final Class84 aClass84_5692) {
        Class98_Sub10_Sub27.aClass84_5692 = aClass84_5692;
    }
    
    static final void method839(final int n) {
        try {
            try {
                final Method method = ((Class85.aClass639 != null) ? Class85.aClass639 : (Class85.aClass639 = method840("java.lang.Runtime"))).getMethod("availableProcessors", (Class[])new Class[0]);
                if (method != null) {
                    try {
                        Class98_Sub46_Sub19.anInt6065 = (int)method.invoke(Runtime.getRuntime(), (Object[])null);
                    }
                    catch (Throwable t) {}
                }
                if (n != 12345) {
                    Class85.anInt638 = 1;
                }
            }
            catch (Exception ex2) {}
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fha.B(" + n + ')');
        }
    }
    
    Class85(final int n, final int n2) {
    }
    
    static Class method840(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
