// 
// Decompiled by Procyon v0.5.30
// 

package irc;

public interface TextProvider
{
    public static final int USER_BASE = 32768;
    public static final int ERROR_NOT_DEFINED = 65535;
    
    String getString(final int p0, final String[] p1);
    
    String getString(final int p0);
    
    String getString(final int p0, final String p1);
    
    String getString(final int p0, final String p1, final String p2);
    
    String getString(final int p0, final String p1, final String p2, final String p3);
}
