// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

public interface PrettyString
{
    String toPrettyString(final String p0);
    
    public interface Appendable
    {
        StringBuffer appendPrettyString(final StringBuffer p0, final String p1);
    }
}
