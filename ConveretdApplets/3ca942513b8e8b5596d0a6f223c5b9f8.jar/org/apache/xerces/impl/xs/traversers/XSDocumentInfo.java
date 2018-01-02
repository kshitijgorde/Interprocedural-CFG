// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.traversers;

import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.impl.xs.util.XInt;
import org.apache.xerces.impl.xs.XMLSchemaException;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.impl.validation.ValidationState;
import java.util.Vector;
import org.w3c.dom.Element;
import java.util.Stack;
import org.apache.xerces.impl.xs.SchemaNamespaceSupport;

class XSDocumentInfo
{
    protected SchemaNamespaceSupport fNamespaceSupport;
    protected SchemaNamespaceSupport fNamespaceSupportRoot;
    protected Stack SchemaNamespaceSupportStack;
    protected boolean fAreLocalAttributesQualified;
    protected boolean fAreLocalElementsQualified;
    protected short fBlockDefault;
    protected short fFinalDefault;
    String fTargetNamespace;
    protected boolean fIsChameleonSchema;
    protected Element fSchemaElement;
    Vector fImportedNS;
    protected ValidationState fValidationContext;
    SymbolTable fSymbolTable;
    protected XSAttributeChecker fAttrChecker;
    protected Object[] fSchemaAttrs;
    protected XSAnnotationInfo fAnnotations;
    private Vector fReportedTNS;
    
    XSDocumentInfo(final Element fSchemaElement, final XSAttributeChecker fAttrChecker, final SymbolTable symbolTable) throws XMLSchemaException {
        this.SchemaNamespaceSupportStack = new Stack();
        this.fImportedNS = new Vector();
        this.fValidationContext = new ValidationState();
        this.fSymbolTable = null;
        this.fAnnotations = null;
        this.fReportedTNS = null;
        this.fSchemaElement = fSchemaElement;
        (this.fNamespaceSupport = new SchemaNamespaceSupport(fSchemaElement, symbolTable)).reset();
        this.fIsChameleonSchema = false;
        this.fSymbolTable = symbolTable;
        this.fAttrChecker = fAttrChecker;
        if (fSchemaElement != null) {
            this.fSchemaAttrs = fAttrChecker.checkAttributes(fSchemaElement, true, this);
            if (this.fSchemaAttrs == null) {
                throw new XMLSchemaException(null, (Object[])null);
            }
            this.fAreLocalAttributesQualified = (((XInt)this.fSchemaAttrs[XSAttributeChecker.ATTIDX_AFORMDEFAULT]).intValue() == 1);
            this.fAreLocalElementsQualified = (((XInt)this.fSchemaAttrs[XSAttributeChecker.ATTIDX_EFORMDEFAULT]).intValue() == 1);
            this.fBlockDefault = ((XInt)this.fSchemaAttrs[XSAttributeChecker.ATTIDX_BLOCKDEFAULT]).shortValue();
            this.fFinalDefault = ((XInt)this.fSchemaAttrs[XSAttributeChecker.ATTIDX_FINALDEFAULT]).shortValue();
            this.fTargetNamespace = (String)this.fSchemaAttrs[XSAttributeChecker.ATTIDX_TARGETNAMESPACE];
            if (this.fTargetNamespace != null) {
                this.fTargetNamespace = symbolTable.addSymbol(this.fTargetNamespace);
            }
            this.fNamespaceSupportRoot = new SchemaNamespaceSupport(this.fNamespaceSupport);
            this.fValidationContext.setNamespaceSupport(this.fNamespaceSupport);
            this.fValidationContext.setSymbolTable(symbolTable);
        }
    }
    
    void backupNSSupport(SchemaNamespaceSupport fNamespaceSupportRoot) {
        this.SchemaNamespaceSupportStack.push(this.fNamespaceSupport);
        if (fNamespaceSupportRoot == null) {
            fNamespaceSupportRoot = this.fNamespaceSupportRoot;
        }
        this.fNamespaceSupport = new SchemaNamespaceSupport(fNamespaceSupportRoot);
        this.fValidationContext.setNamespaceSupport(this.fNamespaceSupport);
    }
    
    void restoreNSSupport() {
        this.fNamespaceSupport = this.SchemaNamespaceSupportStack.pop();
        this.fValidationContext.setNamespaceSupport(this.fNamespaceSupport);
    }
    
    public String toString() {
        return (this.fTargetNamespace == null) ? "no targetNamspace" : ("targetNamespace is " + this.fTargetNamespace);
    }
    
    public void addAllowedNS(final String s) {
        this.fImportedNS.addElement((s == null) ? "" : s);
    }
    
    public boolean isAllowedNS(final String s) {
        return this.fImportedNS.contains((s == null) ? "" : s);
    }
    
    final boolean needReportTNSError(final String s) {
        if (this.fReportedTNS == null) {
            this.fReportedTNS = new Vector();
        }
        else if (this.fReportedTNS.contains(s)) {
            return false;
        }
        this.fReportedTNS.addElement(s);
        return true;
    }
    
    Object[] getSchemaAttrs() {
        return this.fSchemaAttrs;
    }
    
    void returnSchemaAttrs() {
        this.fAttrChecker.returnAttrArray(this.fSchemaAttrs, null);
        this.fSchemaAttrs = null;
    }
    
    void addAnnotation(final XSAnnotationInfo fAnnotations) {
        fAnnotations.next = this.fAnnotations;
        this.fAnnotations = fAnnotations;
    }
    
    XSAnnotationInfo getAnnotations() {
        return this.fAnnotations;
    }
    
    void removeAnnotations() {
        this.fAnnotations = null;
    }
}
