// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.util;

class XMLErrorCode
{
    private String fDomain;
    private String fKey;
    
    public XMLErrorCode(final String fDomain, final String fKey) {
        this.fDomain = fDomain;
        this.fKey = fKey;
    }
    
    public void setValues(final String fDomain, final String fKey) {
        this.fDomain = fDomain;
        this.fKey = fKey;
    }
    
    public boolean equals(final Object o) {
        if (!(o instanceof XMLErrorCode)) {
            return false;
        }
        final XMLErrorCode xmlErrorCode = (XMLErrorCode)o;
        return this.fDomain.equals(xmlErrorCode.fDomain) && this.fKey.equals(xmlErrorCode.fKey);
    }
    
    public int hashCode() {
        return this.fDomain.hashCode() + this.fKey.hashCode();
    }
}
