// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

final class by
{
    static Class a;
    
    static Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
}
