// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.readers;

import java.io.IOException;
import org.xml.sax.SAXException;
import java.util.Enumeration;
import org.xml.sax.InputSource;
import java.util.Hashtable;
import org.xml.sax.EntityResolver;

public abstract class XMLCatalogHandler implements EntityResolver
{
    private Hashtable publicMap;
    private Hashtable systemMap;
    
    public XMLCatalogHandler() {
        this.publicMap = new Hashtable();
        this.systemMap = new Hashtable();
    }
    
    public abstract void loadCatalog(final InputSource p0) throws Exception;
    
    public void addPublicMapping(final String s, final String s2) {
        this.publicMap.put(s, s2);
    }
    
    public void removePublicMapping(final System system) {
        this.publicMap.remove(system);
    }
    
    public Enumeration getPublicMappingKeys() {
        return this.publicMap.keys();
    }
    
    public String getPublicMapping(final String s) {
        return this.publicMap.get(s);
    }
    
    public void addSystemMapping(final String s, final String s2) {
        this.systemMap.put(s, s2);
    }
    
    public void removeSystemMapping(final String s) {
        this.systemMap.remove(s);
    }
    
    public Enumeration getSystemMappingKeys() {
        return this.systemMap.keys();
    }
    
    public String getSystemMapping(final String s) {
        return this.systemMap.get(s);
    }
    
    public InputSource resolveEntity(final String s, final String s2) throws SAXException, IOException {
        if (s != null) {
            final String publicMapping = this.getPublicMapping(s);
            if (publicMapping != null) {
                return new InputSource(publicMapping);
            }
        }
        if (s2 != null) {
            String systemMapping = this.getSystemMapping(s2);
            if (systemMapping == null) {
                systemMapping = s2;
            }
            return new InputSource(systemMapping);
        }
        return null;
    }
}
