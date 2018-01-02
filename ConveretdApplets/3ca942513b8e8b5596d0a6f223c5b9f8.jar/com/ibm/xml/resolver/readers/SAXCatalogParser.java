// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.resolver.readers;

import com.ibm.xml.resolver.Catalog;
import org.xml.sax.DocumentHandler;
import org.xml.sax.ContentHandler;

public interface SAXCatalogParser extends ContentHandler, DocumentHandler
{
    void setCatalog(final Catalog p0);
}
