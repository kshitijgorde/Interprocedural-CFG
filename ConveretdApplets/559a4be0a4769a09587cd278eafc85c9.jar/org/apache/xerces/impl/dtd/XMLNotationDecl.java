// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dtd;

public class XMLNotationDecl
{
    public String name;
    public String publicId;
    public String systemId;
    public String baseSystemId;
    
    public void setValues(final String name, final String publicId, final String systemId, final String baseSystemId) {
        this.name = name;
        this.publicId = publicId;
        this.systemId = systemId;
        this.baseSystemId = baseSystemId;
    }
    
    public void clear() {
        this.name = null;
        this.publicId = null;
        this.systemId = null;
        this.baseSystemId = null;
    }
}
