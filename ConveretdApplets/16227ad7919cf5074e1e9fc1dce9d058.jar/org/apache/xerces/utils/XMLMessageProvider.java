// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.utils;

import java.util.Locale;

public interface XMLMessageProvider
{
    void setLocale(final Locale p0);
    
    Locale getLocale();
    
    String createMessage(final Locale p0, final int p1, final int p2, final Object[] p3);
}
