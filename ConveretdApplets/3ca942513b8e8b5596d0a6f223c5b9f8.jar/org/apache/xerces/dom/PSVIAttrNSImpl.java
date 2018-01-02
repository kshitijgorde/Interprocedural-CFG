// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import org.apache.xerces.xs.StringList;
import org.apache.xerces.xs.XSSimpleTypeDefinition;
import org.apache.xerces.xs.ShortList;
import org.apache.xerces.xs.XSTypeDefinition;
import org.apache.xerces.xs.XSAttributeDeclaration;
import org.apache.xerces.xs.AttributePSVI;

public class PSVIAttrNSImpl extends AttrNSImpl implements AttributePSVI
{
    static final long serialVersionUID = -3241738699421018889L;
    protected XSAttributeDeclaration fDeclaration;
    protected XSTypeDefinition fTypeDecl;
    protected boolean fSpecified;
    protected String fNormalizedValue;
    protected Object fActualValue;
    protected short fActualValueType;
    protected ShortList fItemValueTypes;
    protected XSSimpleTypeDefinition fMemberType;
    protected short fValidationAttempted;
    protected short fValidity;
    protected StringList fErrorCodes;
    protected String fValidationContext;
    
    public PSVIAttrNSImpl(final CoreDocumentImpl coreDocumentImpl, final String s, final String s2, final String s3) {
        super(coreDocumentImpl, s, s2, s3);
        this.fDeclaration = null;
        this.fTypeDecl = null;
        this.fSpecified = true;
        this.fNormalizedValue = null;
        this.fActualValue = null;
        this.fActualValueType = 45;
        this.fItemValueTypes = null;
        this.fMemberType = null;
        this.fValidationAttempted = 0;
        this.fValidity = 0;
        this.fErrorCodes = null;
        this.fValidationContext = null;
    }
    
    public PSVIAttrNSImpl(final CoreDocumentImpl coreDocumentImpl, final String s, final String s2) {
        super(coreDocumentImpl, s, s2);
        this.fDeclaration = null;
        this.fTypeDecl = null;
        this.fSpecified = true;
        this.fNormalizedValue = null;
        this.fActualValue = null;
        this.fActualValueType = 45;
        this.fItemValueTypes = null;
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
    
    public void setPSVI(final AttributePSVI attributePSVI) {
        this.fDeclaration = attributePSVI.getAttributeDeclaration();
        this.fValidationContext = attributePSVI.getValidationContext();
        this.fValidity = attributePSVI.getValidity();
        this.fValidationAttempted = attributePSVI.getValidationAttempted();
        this.fErrorCodes = attributePSVI.getErrorCodes();
        this.fNormalizedValue = attributePSVI.getSchemaNormalizedValue();
        this.fActualValue = attributePSVI.getActualNormalizedValue();
        this.fActualValueType = attributePSVI.getActualNormalizedValueType();
        this.fItemValueTypes = attributePSVI.getItemValueTypes();
        this.fTypeDecl = attributePSVI.getTypeDefinition();
        this.fMemberType = attributePSVI.getMemberTypeDefinition();
        this.fSpecified = attributePSVI.getIsSchemaSpecified();
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
