// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs;

import org.apache.xerces.impl.xs.util.StringListImpl;
import org.apache.xerces.impl.xs.psvi.StringList;
import org.apache.xerces.impl.xs.psvi.XSSimpleTypeDefinition;
import org.apache.xerces.impl.xs.psvi.XSTypeDefinition;
import org.apache.xerces.impl.xs.psvi.XSAttributeDeclaration;
import org.apache.xerces.xni.psvi.AttributePSVI;

public class AttributePSVImpl implements AttributePSVI
{
    protected XSAttributeDeclaration fDeclaration;
    protected XSTypeDefinition fTypeDecl;
    protected boolean fSpecified;
    protected String fNormalizedValue;
    protected XSSimpleTypeDefinition fMemberType;
    protected short fValidationAttempted;
    protected short fValidity;
    protected String[] fErrorCodes;
    protected String fValidationContext;
    protected String fSchemaDefault;
    
    public AttributePSVImpl() {
        this.fDeclaration = null;
        this.fTypeDecl = null;
        this.fSpecified = false;
        this.fNormalizedValue = null;
        this.fMemberType = null;
        this.fValidationAttempted = 0;
        this.fValidity = 0;
        this.fErrorCodes = null;
        this.fValidationContext = null;
        this.fSchemaDefault = null;
    }
    
    public String getSchemaDefault() {
        return this.fSchemaDefault;
    }
    
    public String getSchemaNormalizedValue() {
        return this.fNormalizedValue;
    }
    
    public boolean getIsSchemaSpecified() {
        return this.fSpecified;
    }
    
    public short getValidationAttempted() {
        return this.fValidationAttempted;
    }
    
    public short getValidity() {
        return this.fValidity;
    }
    
    public StringList getErrorCodes() {
        if (this.fErrorCodes == null) {
            return null;
        }
        return new StringListImpl(this.fErrorCodes, this.fErrorCodes.length);
    }
    
    public String getValidationContext() {
        return this.fValidationContext;
    }
    
    public XSTypeDefinition getTypeDefinition() {
        return this.fTypeDecl;
    }
    
    public XSSimpleTypeDefinition getMemberTypeDefinition() {
        return this.fMemberType;
    }
    
    public XSAttributeDeclaration getAttributeDeclaration() {
        return this.fDeclaration;
    }
    
    public void reset() {
        this.fNormalizedValue = null;
        this.fDeclaration = null;
        this.fSchemaDefault = null;
        this.fTypeDecl = null;
        this.fSpecified = false;
        this.fMemberType = null;
        this.fValidationAttempted = 0;
        this.fValidity = 0;
        this.fErrorCodes = null;
        this.fValidationContext = null;
    }
}
