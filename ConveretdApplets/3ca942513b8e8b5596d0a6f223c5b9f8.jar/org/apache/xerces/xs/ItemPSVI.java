// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.xs;

public interface ItemPSVI
{
    public static final short VALIDITY_NOTKNOWN = 0;
    public static final short VALIDITY_INVALID = 1;
    public static final short VALIDITY_VALID = 2;
    public static final short VALIDATION_NONE = 0;
    public static final short VALIDATION_PARTIAL = 1;
    public static final short VALIDATION_FULL = 2;
    
    String getValidationContext();
    
    short getValidity();
    
    short getValidationAttempted();
    
    StringList getErrorCodes();
    
    String getSchemaNormalizedValue();
    
    Object getActualNormalizedValue() throws XSException;
    
    short getActualNormalizedValueType() throws XSException;
    
    ShortList getItemValueTypes() throws XSException;
    
    XSTypeDefinition getTypeDefinition();
    
    XSSimpleTypeDefinition getMemberTypeDefinition();
    
    String getSchemaDefault();
    
    boolean getIsSchemaSpecified();
}
