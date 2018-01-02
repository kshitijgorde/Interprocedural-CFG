// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.xni.psvi;

import org.apache.xerces.impl.xs.psvi.XSSimpleTypeDefinition;
import org.apache.xerces.impl.xs.psvi.XSTypeDefinition;
import org.apache.xerces.impl.xs.psvi.StringList;

public interface ItemPSVI
{
    public static final short VALIDITY_UNKNOWN = 0;
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
    
    XSTypeDefinition getTypeDefinition();
    
    XSSimpleTypeDefinition getMemberTypeDefinition();
    
    String getSchemaDefault();
    
    boolean getIsSchemaSpecified();
}
