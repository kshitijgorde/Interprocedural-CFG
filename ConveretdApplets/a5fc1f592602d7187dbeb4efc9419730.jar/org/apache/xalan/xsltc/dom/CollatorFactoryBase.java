// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.dom;

import java.text.Collator;
import java.util.Locale;
import org.apache.xalan.xsltc.CollatorFactory;

public class CollatorFactoryBase implements CollatorFactory
{
    public static final Locale DEFAULT_LOCALE;
    public static final Collator DEFAULT_COLLATOR;
    
    public Collator getCollator(final String lang, final String country) {
        return Collator.getInstance(new Locale(lang, country));
    }
    
    public Collator getCollator(final Locale locale) {
        if (locale == CollatorFactoryBase.DEFAULT_LOCALE) {
            return CollatorFactoryBase.DEFAULT_COLLATOR;
        }
        return Collator.getInstance(locale);
    }
    
    static {
        DEFAULT_LOCALE = Locale.getDefault();
        DEFAULT_COLLATOR = Collator.getInstance();
    }
}
