// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.stree;

import org.xml.sax.SAXException;
import org.xml.sax.ContentHandler;

public interface SaxEventDispatch
{
    public static final String SUPPORTSINTERFACE = "http://xml.apache.org/xalan/features/feed-events";
    
    void dispatchCharactersEvent(final ContentHandler p0) throws SAXException;
}
