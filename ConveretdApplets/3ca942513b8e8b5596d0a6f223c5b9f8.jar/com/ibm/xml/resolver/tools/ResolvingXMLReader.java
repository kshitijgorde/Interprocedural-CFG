// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.resolver.tools;

import com.ibm.xml.resolver.CatalogManager;
import javax.xml.parsers.SAXParserFactory;

public class ResolvingXMLReader extends ResolvingXMLFilter
{
    public ResolvingXMLReader() {
        final SAXParserFactory instance = SAXParserFactory.newInstance();
        try {
            this.setParent(instance.newSAXParser().getXMLReader());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public ResolvingXMLReader(final CatalogManager catalogManager) {
        super(catalogManager);
        final SAXParserFactory instance = SAXParserFactory.newInstance();
        try {
            this.setParent(instance.newSAXParser().getXMLReader());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
