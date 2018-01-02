// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import org.apache.xerces.xs.XSModel;
import org.apache.xerces.xs.StringList;
import org.apache.xerces.xs.XSSimpleTypeDefinition;
import org.apache.xerces.xs.XSNotationDeclaration;
import org.apache.xerces.xs.ShortList;
import org.apache.xerces.xs.XSTypeDefinition;
import org.apache.xerces.xs.XSElementDeclaration;
import org.apache.xerces.xs.ElementPSVI;

public class PSVIElementNSImpl extends ElementNSImpl implements ElementPSVI
{
    static final long serialVersionUID = 6815489624636016068L;
    protected XSElementDeclaration fDeclaration;
    protected XSTypeDefinition fTypeDecl;
    protected boolean fNil;
    protected boolean fSpecified;
    protected String fNormalizedValue;
    protected Object fActualValue;
    protected short fActualValueType;
    protected ShortList fItemValueTypes;
    protected XSNotationDeclaration fNotation;
    protected XSSimpleTypeDefinition fMemberType;
    protected short fValidationAttempted;
    protected short fValidity;
    protected StringList fErrorCodes;
    protected String fValidationContext;
    protected XSModel fSchemaInformation;
    
    public PSVIElementNSImpl(final CoreDocumentImpl coreDocumentImpl, final String s, final String s2, final String s3) {
        super(coreDocumentImpl, s, s2, s3);
        this.fDeclaration = null;
        this.fTypeDecl = null;
        this.fNil = false;
        this.fSpecified = true;
        this.fNormalizedValue = null;
        this.fActualValue = null;
        this.fActualValueType = 45;
        this.fItemValueTypes = null;
        this.fNotation = null;
        this.fMemberType = null;
        this.fValidationAttempted = 0;
        this.fValidity = 0;
        this.fErrorCodes = null;
        this.fValidationContext = null;
        this.fSchemaInformation = null;
    }
    
    public PSVIElementNSImpl(final CoreDocumentImpl coreDocumentImpl, final String s, final String s2) {
        super(coreDocumentImpl, s, s2);
        this.fDeclaration = null;
        this.fTypeDecl = null;
        this.fNil = false;
        this.fSpecified = true;
        this.fNormalizedValue = null;
        this.fActualValue = null;
        this.fActualValueType = 45;
        this.fItemValueTypes = null;
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
    
    public boolean getNil() {
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
    
    public void setPSVI(final ElementPSVI elementPSVI) {
        this.fDeclaration = elementPSVI.getElementDeclaration();
        this.fNotation = elementPSVI.getNotation();
        this.fValidationContext = elementPSVI.getValidationContext();
        this.fTypeDecl = elementPSVI.getTypeDefinition();
        this.fSchemaInformation = elementPSVI.getSchemaInformation();
        this.fValidity = elementPSVI.getValidity();
        this.fValidationAttempted = elementPSVI.getValidationAttempted();
        this.fErrorCodes = elementPSVI.getErrorCodes();
        this.fNormalizedValue = elementPSVI.getSchemaNormalizedValue();
        this.fActualValue = elementPSVI.getActualNormalizedValue();
        this.fActualValueType = elementPSVI.getActualNormalizedValueType();
        this.fItemValueTypes = elementPSVI.getItemValueTypes();
        this.fMemberType = elementPSVI.getMemberTypeDefinition();
        this.fSpecified = elementPSVI.getIsSchemaSpecified();
    }
    
    public Object getActualNormalizedValue() {
        return this.fActualValue;
    }
    
    public short getActualNormalizedValueType() {
        return this.fActualValueType;
    }
    
    public ShortList getItemValueTypes() {
        return this.fItemValueTypes;
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        throw new NotSerializableException(this.getClass().getName());
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        throw new NotSerializableException(this.getClass().getName());
    }
}
