// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.graphics;

public class Assert
{
    public static void notFalse(final boolean b) throws IllegalArgumentException {
        if (!b) {
            throw new IllegalArgumentException("boolean expression false");
        }
    }
    
    public static void isTrue(final boolean b) {
        notFalse(b);
    }
    
    public static void isFalse(final boolean b) {
        if (b) {
            throw new IllegalArgumentException("boolean expression true");
        }
    }
    
    public static void notNull(final Object o) throws IllegalArgumentException {
        if (o == null) {
            throw new IllegalArgumentException("null argument");
        }
    }
    
    public static void notFalse(final boolean b, final String s) throws IllegalArgumentException {
        if (!b) {
            throw new IllegalArgumentException(s);
        }
    }
    
    public static void isTrue(final boolean b, final String s) {
        notFalse(b, s);
    }
    
    public static void isFalse(final boolean b, final String s) {
        if (b) {
            throw new IllegalArgumentException(s);
        }
    }
    
    public static void notNull(final Object o, final String s) throws IllegalArgumentException {
        if (o == null) {
            throw new IllegalArgumentException(s);
        }
    }
}
