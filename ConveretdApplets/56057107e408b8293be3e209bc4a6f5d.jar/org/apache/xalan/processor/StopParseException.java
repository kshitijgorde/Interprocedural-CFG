// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.processor;

import org.xml.sax.SAXException;

public class StopParseException extends SAXException
{
    StopParseException() {
        super("Stylesheet PIs found, stop the parse");
    }
}
