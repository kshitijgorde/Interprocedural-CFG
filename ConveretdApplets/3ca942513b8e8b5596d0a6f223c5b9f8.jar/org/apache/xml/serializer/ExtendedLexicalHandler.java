// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serializer;

import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;

interface ExtendedLexicalHandler extends LexicalHandler
{
    void comment(final String p0) throws SAXException;
}
