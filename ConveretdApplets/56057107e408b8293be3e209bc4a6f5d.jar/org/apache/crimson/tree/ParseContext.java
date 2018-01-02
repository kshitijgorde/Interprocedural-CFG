// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.tree;

import org.xml.sax.Locator;
import java.util.Locale;
import org.xml.sax.ErrorHandler;

public interface ParseContext
{
    ErrorHandler getErrorHandler();
    
    Locale getLocale();
    
    Locator getLocator();
}
