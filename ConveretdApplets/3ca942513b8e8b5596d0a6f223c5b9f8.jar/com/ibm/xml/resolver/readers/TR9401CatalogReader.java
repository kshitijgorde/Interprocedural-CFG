// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.resolver.readers;

import java.io.IOException;
import java.net.MalformedURLException;
import com.ibm.xml.resolver.CatalogException;
import java.util.Vector;
import com.ibm.xml.resolver.CatalogEntry;
import java.io.InputStream;
import com.ibm.xml.resolver.Catalog;

public class TR9401CatalogReader extends TextCatalogReader
{
    public void readCatalog(final Catalog catalog, final InputStream catfile) throws MalformedURLException, IOException {
        super.catfile = catfile;
        if (super.catfile == null) {
            return;
        }
        Vector<String> vector = null;
        while (true) {
            final String nextToken = this.nextToken();
            if (nextToken == null) {
                break;
            }
            String upperCase;
            if (super.caseSensitive) {
                upperCase = nextToken;
            }
            else {
                upperCase = nextToken.toUpperCase();
            }
            if (upperCase.equals("DELEGATE")) {
                upperCase = "DELEGATE_PUBLIC";
            }
            try {
                final int entryArgCount = CatalogEntry.getEntryArgCount(CatalogEntry.getEntryType(upperCase));
                final Vector<String> vector2 = new Vector<String>();
                if (vector != null) {
                    catalog.unknownEntry(vector);
                    vector = null;
                }
                for (int i = 0; i < entryArgCount; ++i) {
                    vector2.addElement(this.nextToken());
                }
                catalog.addEntry(new CatalogEntry(upperCase, vector2));
            }
            catch (CatalogException ex) {
                if (ex.getExceptionType() == 3) {
                    if (vector == null) {
                        vector = new Vector<String>();
                    }
                    vector.addElement(nextToken);
                }
                else {
                    if (ex.getExceptionType() != 2) {
                        continue;
                    }
                    catalog.getCatalogManager().debug.message(1, "Invalid catalog entry", nextToken);
                    vector = null;
                }
            }
        }
        if (vector != null) {
            catalog.unknownEntry(vector);
        }
        super.catfile.close();
        super.catfile = null;
    }
}
