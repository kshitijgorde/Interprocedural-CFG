// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.lang.text;

import java.text.Format;
import java.util.Locale;

public interface FormatFactory
{
    Format getFormat(final String p0, final String p1, final Locale p2);
}