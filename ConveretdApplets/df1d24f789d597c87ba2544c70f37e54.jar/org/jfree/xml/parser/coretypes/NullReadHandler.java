// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.parser.coretypes;

import org.jfree.xml.parser.XmlReaderException;
import org.jfree.xml.parser.AbstractXmlReadHandler;

public class NullReadHandler extends AbstractXmlReadHandler
{
    public Object getObject() throws XmlReaderException {
        return null;
    }
}
