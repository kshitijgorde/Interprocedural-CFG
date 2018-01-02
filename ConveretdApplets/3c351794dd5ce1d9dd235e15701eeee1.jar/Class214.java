import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class214
{
    static final ha method2784(final int n, final int n2, final Canvas canvas, final d d, final Class207 class207) {
        try {
            ha ha;
            try {
                if (!Class319.method3660(true)) {
                    throw new RuntimeException("");
                }
                if (!Class134_Sub1.method2246("jagdx", (byte)(-36))) {
                    throw new RuntimeException("");
                }
                ha = (ha)Class.forName("ha_Sub3_Sub1").getDeclaredMethod("createToolkit", Class.forName("java.awt.Canvas"), Class.forName("d"), Class.forName("Class207"), Class.forName("java.lang.Integer")).invoke(null, canvas, d, class207, new Integer(n2));
            }
            catch (RuntimeException ex) {
                throw ex;
            }
            catch (Throwable t) {
                throw new RuntimeException("");
            }
            return ha;
        }
        catch (RuntimeException ex2) {
            throw ex2;
        }
    }
}
