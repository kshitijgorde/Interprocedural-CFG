// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.util;

import org.apache.xerces.xni.XMLResourceIdentifier;

public class XMLResourceIdentifierImpl implements XMLResourceIdentifier
{
    protected String fPublicId;
    protected String fLiteralSystemId;
    protected String fBaseSystemId;
    protected String fExpandedSystemId;
    
    public XMLResourceIdentifierImpl() {
    }
    
    public XMLResourceIdentifierImpl(final String publicId, final String literalSystemId, final String baseSystemId, final String expandedSystemId) {
        this.setValues(publicId, literalSystemId, baseSystemId, expandedSystemId);
    }
    
    public void setValues(final String publicId, final String literalSystemId, final String baseSystemId, final String expandedSystemId) {
        this.fPublicId = publicId;
        this.fLiteralSystemId = literalSystemId;
        this.fBaseSystemId = baseSystemId;
        this.fExpandedSystemId = expandedSystemId;
    }
    
    public void clear() {
        this.fPublicId = null;
        this.fLiteralSystemId = null;
        this.fBaseSystemId = null;
        this.fExpandedSystemId = null;
    }
    
    public void setPublicId(final String publicId) {
        this.fPublicId = publicId;
    }
    
    public void setLiteralSystemId(final String literalSystemId) {
        this.fLiteralSystemId = literalSystemId;
    }
    
    public void setBaseSystemId(final String baseSystemId) {
        this.fBaseSystemId = baseSystemId;
    }
    
    public void setExpandedSystemId(final String expandedSystemId) {
        this.fExpandedSystemId = expandedSystemId;
    }
    
    public String getPublicId() {
        return this.fPublicId;
    }
    
    public String getLiteralSystemId() {
        return this.fLiteralSystemId;
    }
    
    public String getBaseSystemId() {
        return this.fBaseSystemId;
    }
    
    public String getExpandedSystemId() {
        return this.fExpandedSystemId;
    }
    
    public int hashCode() {
        int code = 0;
        if (this.fPublicId != null) {
            code += this.fPublicId.hashCode();
        }
        if (this.fLiteralSystemId != null) {
            code += this.fLiteralSystemId.hashCode();
        }
        if (this.fBaseSystemId != null) {
            code += this.fBaseSystemId.hashCode();
        }
        if (this.fExpandedSystemId != null) {
            code += this.fExpandedSystemId.hashCode();
        }
        return code;
    }
    
    public String toString() {
        final StringBuffer str = new StringBuffer();
        if (this.fPublicId != null) {
            str.append(this.fPublicId);
        }
        str.append(':');
        if (this.fLiteralSystemId != null) {
            str.append(this.fLiteralSystemId);
        }
        str.append(':');
        if (this.fBaseSystemId != null) {
            str.append(this.fBaseSystemId);
        }
        str.append(':');
        if (this.fExpandedSystemId != null) {
            str.append(this.fExpandedSystemId);
        }
        return str.toString();
    }
}
