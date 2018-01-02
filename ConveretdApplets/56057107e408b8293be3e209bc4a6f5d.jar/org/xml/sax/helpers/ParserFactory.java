// 
// Decompiled by Procyon v0.5.30
// 

package org.xml.sax.helpers;

import org.xml.sax.Parser;

public class ParserFactory
{
    public static Parser makeParser() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NullPointerException, ClassCastException {
        final String className = System.getProperty("org.xml.sax.parser");
        if (className == null) {
            throw new NullPointerException("No value for sax.parser property");
        }
        return makeParser(className);
    }
    
    public static Parser makeParser(final String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException, ClassCastException {
        return (Parser)Class.forName(className).newInstance();
    }
}
