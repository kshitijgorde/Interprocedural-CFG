// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.traversers;

import org.w3c.dom.Element;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.impl.xs.util.XInt;
import org.apache.xerces.impl.xs.XMLSchemaException;
import org.apache.xerces.util.DOMUtil;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.impl.validation.ValidationState;
import java.util.Vector;
import org.w3c.dom.Document;
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
    protected String fTargetNamespace;
    protected boolean fIsChameleonSchema;
    protected Document fSchemaDoc;
    Vector fImportedNS;
    protected ValidationState fValidationContext;
    SymbolTable fSymbolTable;
    private Vector fReportedTNS;
    
    XSDocumentInfo(final Document schemaDoc, final XSAttributeChecker attrChecker, final SymbolTable symbolTable) throws XMLSchemaException {
        this.SchemaNamespaceSupportStack = new Stack();
        this.fImportedNS = new Vector();
        this.fValidationContext = new ValidationState();
        this.fSymbolTable = null;
        this.fReportedTNS = null;
        this.fSchemaDoc = schemaDoc;
        (this.fNamespaceSupport = new SchemaNamespaceSupport()).reset();
        this.fIsChameleonSchema = false;
        this.fSymbolTable = symbolTable;
        if (schemaDoc != null) {
            final Element root = DOMUtil.getRoot(schemaDoc);
            final Object[] schemaAttrs = attrChecker.checkAttributes(root, true, this);
            if (schemaAttrs == null) {
                throw new XMLSchemaException(null, (Object[])null);
            }
            this.fAreLocalAttributesQualified = (((XInt)schemaAttrs[XSAttributeChecker.ATTIDX_AFORMDEFAULT]).intValue() == 1);
            this.fAreLocalElementsQualified = (((XInt)schemaAttrs[XSAttributeChecker.ATTIDX_EFORMDEFAULT]).intValue() == 1);
            this.fBlockDefault = ((XInt)schemaAttrs[XSAttributeChecker.ATTIDX_BLOCKDEFAULT]).shortValue();
            this.fFinalDefault = ((XInt)schemaAttrs[XSAttributeChecker.ATTIDX_FINALDEFAULT]).shortValue();
            this.fTargetNamespace = (String)schemaAttrs[XSAttributeChecker.ATTIDX_TARGETNAMESPACE];
            if (this.fTargetNamespace != null) {
                this.fTargetNamespace = symbolTable.addSymbol(this.fTargetNamespace);
            }
            this.fNamespaceSupportRoot = new SchemaNamespaceSupport(this.fNamespaceSupport);
            this.fValidationContext.setNamespaceSupport(this.fNamespaceSupport);
            this.fValidationContext.setSymbolTable(symbolTable);
            attrChecker.returnAttrArray(schemaAttrs, null);
        }
    }
    
    void backupNSSupport(SchemaNamespaceSupport nsSupport) {
        this.SchemaNamespaceSupportStack.push(this.fNamespaceSupport);
        if (nsSupport == null) {
            nsSupport = this.fNamespaceSupportRoot;
        }
        this.fNamespaceSupport = new SchemaNamespaceSupport(nsSupport);
        this.fValidationContext.setNamespaceSupport(this.fNamespaceSupport);
    }
    
    void restoreNSSupport() {
        this.fNamespaceSupport = this.SchemaNamespaceSupportStack.pop();
        this.fValidationContext.setNamespaceSupport(this.fNamespaceSupport);
    }
    
    public String toString() {
        return (this.fTargetNamespace == null) ? "no targetNamspace" : ("targetNamespace is " + this.fTargetNamespace);
    }
    
    public void addAllowedNS(final String namespace) {
        this.fImportedNS.addElement((namespace == null) ? "" : namespace);
    }
    
    public boolean isAllowedNS(final String namespace) {
        return this.fImportedNS.contains((namespace == null) ? "" : namespace);
    }
    
    final boolean needReportTNSError(final String uri) {
        if (this.fReportedTNS == null) {
            this.fReportedTNS = new Vector();
        }
        else if (this.fReportedTNS.contains(uri)) {
            return false;
        }
        this.fReportedTNS.addElement(uri);
        return true;
    }
}
