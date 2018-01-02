// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs;

import org.apache.xerces.impl.xs.util.StringListImpl;
import org.apache.xerces.xs.StringList;
import org.apache.xerces.xs.XSModel;
import org.apache.xerces.xs.XSSimpleTypeDefinition;
import org.apache.xerces.xs.XSNotationDeclaration;
import org.apache.xerces.xs.ShortList;
import org.apache.xerces.xs.XSTypeDefinition;
import org.apache.xerces.xs.XSElementDeclaration;
import org.apache.xerces.xs.ElementPSVI;

public class ElementPSVImpl implements ElementPSVI
{
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
    protected String[] fErrorCodes;
    protected String fValidationContext;
    protected SchemaGrammar[] fGrammars;
    protected XSModel fSchemaInformation;
    
    public ElementPSVImpl() {
        this.fDeclaration = null;
        this.fTypeDecl = null;
        this.fNil = false;
        this.fSpecified = false;
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
        this.fGrammars = null;
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
        if (this.fErrorCodes == null) {
            return null;
        }
        return new StringListImpl(this.fErrorCodes, this.fErrorCodes.length);
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
    
    public synchronized XSModel getSchemaInformation() {
        if (this.fSchemaInformation == null && this.fGrammars != null) {
            this.fSchemaInformation = new XSModelImpl(this.fGrammars);
        }
        return this.fSchemaInformation;
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
    
    public void reset() {
        this.fDeclaration = null;
        this.fTypeDecl = null;
        this.fNil = false;
        this.fSpecified = false;
        this.fNotation = null;
        this.fMemberType = null;
        this.fValidationAttempted = 0;
        this.fValidity = 0;
        this.fErrorCodes = null;
        this.fValidationContext = null;
        this.fNormalizedValue = null;
        this.fActualValue = null;
        this.fActualValueType = 45;
        this.fItemValueTypes = null;
    }
}
