// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.parser;

import org.jfree.xml.util.ObjectDescriptionException;

public class XmlReaderException extends ObjectDescriptionException
{
    public XmlReaderException() {
    }
    
    public XmlReaderException(final String s) {
        super(s);
    }
    
    public XmlReaderException(final String s, final Exception ex) {
        super(s, ex);
    }
}
