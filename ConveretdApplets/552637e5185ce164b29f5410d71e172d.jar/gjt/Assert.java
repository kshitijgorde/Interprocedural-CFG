// 
// Decompiled by Procyon v0.5.30
// 

package gjt;

public class Assert
{
    public static void notNull(final Object obj) throws IllegalArgumentException {
        if (obj == null) {
            throw new IllegalArgumentException("null argument");
        }
    }
    
    public static void notNull(final Object obj, final String s) throws IllegalArgumentException {
        if (obj == null) {
            throw new IllegalArgumentException(s);
        }
    }
    
    public static void notFalse(final boolean b) throws IllegalArgumentException {
        if (!b) {
            throw new IllegalArgumentException("boolean expression false");
        }
    }
    
    public static void notFalse(final boolean b, final String s) throws IllegalArgumentException {
        if (!b) {
            throw new IllegalArgumentException(s);
        }
    }
}
