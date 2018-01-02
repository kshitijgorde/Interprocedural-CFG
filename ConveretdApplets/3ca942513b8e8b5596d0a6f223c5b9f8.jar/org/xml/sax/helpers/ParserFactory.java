// 
// Decompiled by Procyon v0.5.30
// 

package org.xml.sax.helpers;

import org.xml.sax.Parser;

public class ParserFactory
{
    public static Parser makeParser() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NullPointerException, ClassCastException {
        final String systemProperty = SecuritySupport.getInstance().getSystemProperty("org.xml.sax.parser");
        if (systemProperty == null) {
            throw new NullPointerException("No value for sax.parser property");
        }
        return makeParser(systemProperty);
    }
    
    public static Parser makeParser(final String s) throws ClassNotFoundException, IllegalAccessException, InstantiationException, ClassCastException {
        return (Parser)NewInstance.newInstance(NewInstance.getClassLoader(), s);
    }
}
