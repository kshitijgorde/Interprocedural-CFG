// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.util;

import java.util.MissingResourceException;
import java.util.Locale;

public interface MessageFormatter
{
    String formatMessage(final Locale p0, final String p1, final Object[] p2) throws MissingResourceException;
}
