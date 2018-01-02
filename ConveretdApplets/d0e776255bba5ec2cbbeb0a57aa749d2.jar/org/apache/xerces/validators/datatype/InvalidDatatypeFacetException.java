// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.datatype;

public class InvalidDatatypeFacetException extends XMLException
{
    private int majorCode;
    private int minorCode;
    
    public int getMinorCode() {
        return this.minorCode;
    }
    
    public int getMajorCode() {
        return this.majorCode;
    }
    
    public void setMinorCode(final int majorCode) {
        this.majorCode = majorCode;
    }
    
    public void setMajorCode(final int minorCode) {
        this.minorCode = minorCode;
    }
    
    public InvalidDatatypeFacetException() {
        this.majorCode = -1;
        this.minorCode = -1;
    }
    
    public InvalidDatatypeFacetException(final String s) {
        super(s);
        this.majorCode = -1;
        this.minorCode = -1;
    }
    
    public InvalidDatatypeFacetException(final Exception ex) {
        super(ex);
        this.majorCode = -1;
        this.minorCode = -1;
    }
    
    public InvalidDatatypeFacetException(final String s, final Exception ex) {
        super(s, ex);
        this.majorCode = -1;
        this.minorCode = -1;
    }
}
