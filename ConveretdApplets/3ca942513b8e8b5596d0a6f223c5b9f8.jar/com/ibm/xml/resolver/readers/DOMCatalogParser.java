// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.resolver.readers;

import org.w3c.dom.Node;
import com.ibm.xml.resolver.Catalog;

public interface DOMCatalogParser
{
    void parseCatalogEntry(final Catalog p0, final Node p1);
}
