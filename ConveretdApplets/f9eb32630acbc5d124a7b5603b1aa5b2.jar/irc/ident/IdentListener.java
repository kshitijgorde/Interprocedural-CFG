// 
// Decompiled by Procyon v0.5.30
// 

package irc.ident;

public interface IdentListener
{
    public static final int IDENT_ERROR = -1;
    public static final int IDENT_OK = 0;
    public static final int IDENT_DEFAULT = 1;
    public static final int IDENT_NOT_FOUND = 2;
    
    void identRequested(final String p0, final Integer p1, final String p2);
    
    void identRunning(final Integer p0);
    
    void identLeaving(final String p0);
}
