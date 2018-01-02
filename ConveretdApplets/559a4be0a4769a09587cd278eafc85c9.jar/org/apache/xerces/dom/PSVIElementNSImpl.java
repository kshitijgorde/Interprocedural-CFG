// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.apache.xerces.impl.xs.psvi.XSModel;
import org.apache.xerces.impl.xs.psvi.StringList;
import org.apache.xerces.impl.xs.psvi.XSSimpleTypeDefinition;
import org.apache.xerces.impl.xs.psvi.XSNotationDeclaration;
import org.apache.xerces.impl.xs.psvi.XSTypeDefinition;
import org.apache.xerces.impl.xs.psvi.XSElementDeclaration;
import org.apache.xerces.xni.psvi.ElementPSVI;

public class PSVIElementNSImpl extends ElementNSImpl implements ElementPSVI
{
    protected XSElementDeclaration fDeclaration;
    protected XSTypeDefinition fTypeDecl;
    protected boolean fNil;
    protected boolean fSpecified;
    protected String fNormalizedValue;
    protected XSNotationDeclaration fNotation;
    protected XSSimpleTypeDefinition fMemberType;
    protected short fValidationAttempted;
    protected short fValidity;
    protected StringList fErrorCodes;
    protected String fValidationContext;
    protected XSModel fSchemaInformation;
    
    public PSVIElementNSImpl(final CoreDocumentImpl ownerDocument, final String namespaceURI, final String qualifiedName, final String localName) {
        super(ownerDocument, namespaceURI, qualifiedName, localName);
        this.fDeclaration = null;
        this.fTypeDecl = null;
        this.fNil = false;
        this.fSpecified = true;
        this.fNormalizedValue = null;
        this.fNotation = null;
        this.fMemberType = null;
        this.fValidationAttempted = 0;
        this.fValidity = 0;
        this.fErrorCodes = null;
        this.fValidationContext = null;
        this.fSchemaInformation = null;
    }
    
    public PSVIElementNSImpl(final CoreDocumentImpl ownerDocument, final String namespaceURI, final String qualifiedName) {
        super(ownerDocument, namespaceURI, qualifiedName);
        this.fDeclaration = null;
        this.fTypeDecl = null;
        this.fNil = false;
        this.fSpecified = true;
        this.fNormalizedValue = null;
        this.fNotation = null;
        this.fMemberType = null;
        this.fValidationAttempted = 0;
        this.fValidity = 0;
        this.fErrorCodes = null;
        this.fValidationContext = null;
        this.fSchemaInformation = null;
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
    
    public boolean getIsNil() {
        return this.fNil;
    }
    
    public XSNotationDeclaration getNotation() {
        return this.fNotation;
    }
    
    public XSTypeDefinition getTypeDefinition() {
        return this.fTypeDecl;
    }
    
    public XSSimpleTypeDefinition getMemberTypeDefinition() {
        return this.fMemberType;
    }
    
    public XSElementDeclaration getElementDeclaration() {
        return this.fDeclaration;
    }
    
    public XSModel getSchemaInformation() {
        return this.fSchemaInformation;
    }
    
    public void setPSVI(final ElementPSVI elem) {
        this.fDeclaration = elem.getElementDeclaration();
        this.fNotation = elem.getNotation();
        this.fValidationContext = elem.getValidationContext();
        this.fTypeDecl = elem.getTypeDefinition();
        this.fSchemaInformation = elem.getSchemaInformation();
        this.fValidity = elem.getValidity();
        this.fValidationAttempted = elem.getValidationAttempted();
        this.fErrorCodes = elem.getErrorCodes();
        this.fNormalizedValue = elem.getSchemaNormalizedValue();
        this.fMemberType = elem.getMemberTypeDefinition();
        this.fSpecified = elem.getIsSchemaSpecified();
    }
}
