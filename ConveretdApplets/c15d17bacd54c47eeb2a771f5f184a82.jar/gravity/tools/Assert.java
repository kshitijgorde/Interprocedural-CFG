// 
// Decompiled by Procyon v0.5.30
// 

package gravity.tools;

public class Assert
{
    public static void assert(final boolean b) throws AssertionException {
        if (!b) {
            throw new AssertionException();
        }
    }
    
    public static void assert(final boolean b, final String s) throws AssertionException {
        if (!b) {
            throw new AssertionException(s);
        }
    }
}
