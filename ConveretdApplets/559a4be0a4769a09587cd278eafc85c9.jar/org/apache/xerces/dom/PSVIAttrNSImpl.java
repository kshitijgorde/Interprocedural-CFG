// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.apache.xerces.impl.xs.psvi.StringList;
import org.apache.xerces.impl.xs.psvi.XSSimpleTypeDefinition;
import org.apache.xerces.impl.xs.psvi.XSTypeDefinition;
import org.apache.xerces.impl.xs.psvi.XSAttributeDeclaration;
import org.apache.xerces.xni.psvi.AttributePSVI;

public class PSVIAttrNSImpl extends AttrNSImpl implements AttributePSVI
{
    protected XSAttributeDeclaration fDeclaration;
    protected XSTypeDefinition fTypeDecl;
    protected boolean fSpecified;
    protected String fNormalizedValue;
    protected XSSimpleTypeDefinition fMemberType;
    protected short fValidationAttempted;
    protected short fValidity;
    protected StringList fErrorCodes;
    protected String fValidationContext;
    
    public PSVIAttrNSImpl(final CoreDocumentImpl ownerDocument, final String namespaceURI, final String qualifiedName, final String localName) {
        super(ownerDocument, namespaceURI, qualifiedName, localName);
        this.fDeclaration = null;
        this.fTypeDecl = null;
        this.fSpecified = true;
        this.fNormalizedValue = null;
        this.fMemberType = null;
        this.fValidationAttempted = 0;
        this.fValidity = 0;
        this.fErrorCodes = null;
        this.fValidationContext = null;
    }
    
    public PSVIAttrNSImpl(final CoreDocumentImpl ownerDocument, final String namespaceURI, final String qualifiedName) {
        super(ownerDocument, namespaceURI, qualifiedName);
        this.fDeclaration = null;
        this.fTypeDecl = null;
        this.fSpecified = true;
        this.fNormalizedValue = null;
        this.fMemberType = null;
        this.fValidationAttempted = 0;
        this.fValidity = 0;
        this.fErrorCodes = null;
        this.fValidationContext = null;
    }
    
    public String getSchemaDefault() {
        return (this.fDeclaration == null) ? null : this.fDeclaration.getConstraintValue();
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
        return this.fErrorCodes;
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
    
    public void setPSVI(final AttributePSVI attr) {
        this.fDeclaration = attr.getAttributeDeclaration();
        this.fValidationContext = attr.getValidationContext();
        this.fValidity = attr.getValidity();
        this.fValidationAttempted = attr.getValidationAttempted();
        this.fErrorCodes = attr.getErrorCodes();
        this.fNormalizedValue = attr.getSchemaNormalizedValue();
        this.fTypeDecl = attr.getTypeDefinition();
        this.fMemberType = attr.getMemberTypeDefinition();
        this.fSpecified = attr.getIsSchemaSpecified();
    }
}
