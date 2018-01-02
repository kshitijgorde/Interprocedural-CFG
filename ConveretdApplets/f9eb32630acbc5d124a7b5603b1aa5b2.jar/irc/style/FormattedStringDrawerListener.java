// 
// Decompiled by Procyon v0.5.30
// 

package irc.style;

public interface FormattedStringDrawerListener
{
    public static final int DATA = 1;
    public static final int SIZE = 2;
    public static final int FRAME = 4;
    
    Boolean displayUpdated(final Object p0, final Integer p1);
}
