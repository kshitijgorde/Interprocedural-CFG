// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.writer;

import org.jfree.xml.util.ObjectDescriptionException;

public class XMLWriterException extends ObjectDescriptionException
{
    public XMLWriterException() {
    }
    
    public XMLWriterException(final String s) {
        super(s);
    }
    
    public XMLWriterException(final String s, final Exception ex) {
        super(s, ex);
    }
}
