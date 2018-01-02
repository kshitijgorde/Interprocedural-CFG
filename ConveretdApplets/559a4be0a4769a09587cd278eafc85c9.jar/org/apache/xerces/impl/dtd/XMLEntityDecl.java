// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dtd;

public class XMLEntityDecl
{
    public String name;
    public String publicId;
    public String systemId;
    public String baseSystemId;
    public String notation;
    public boolean isPE;
    public boolean inExternal;
    public String value;
    
    public void setValues(final String name, final String publicId, final String systemId, final String baseSystemId, final String notation, final boolean isPE, final boolean inExternal) {
        this.setValues(name, publicId, systemId, baseSystemId, notation, null, isPE, inExternal);
    }
    
    public void setValues(final String name, final String publicId, final String systemId, final String baseSystemId, final String notation, final String value, final boolean isPE, final boolean inExternal) {
        this.name = name;
        this.publicId = publicId;
        this.systemId = systemId;
        this.baseSystemId = baseSystemId;
        this.notation = notation;
        this.value = value;
        this.isPE = isPE;
        this.inExternal = inExternal;
    }
    
    public void clear() {
        this.name = null;
        this.publicId = null;
        this.systemId = null;
        this.baseSystemId = null;
        this.notation = null;
        this.value = null;
        this.isPE = false;
        this.inExternal = false;
    }
}
