// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.resolver.readers;

import java.io.InputStream;
import com.ibm.xml.resolver.CatalogException;
import java.io.IOException;
import java.net.MalformedURLException;
import com.ibm.xml.resolver.Catalog;

public interface CatalogReader
{
    void readCatalog(final Catalog p0, final String p1) throws MalformedURLException, IOException, CatalogException;
    
    void readCatalog(final Catalog p0, final InputStream p1) throws IOException, CatalogException;
}
