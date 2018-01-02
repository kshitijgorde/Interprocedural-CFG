// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc;

import java.util.Locale;
import java.text.Collator;

public interface CollatorFactory
{
    Collator getCollator(final String p0, final String p1);
    
    Collator getCollator(final Locale p0);
}
