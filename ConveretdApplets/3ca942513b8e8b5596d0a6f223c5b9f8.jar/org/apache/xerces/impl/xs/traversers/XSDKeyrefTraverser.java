// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.traversers;

import org.apache.xerces.impl.xs.identity.KeyRef;
import org.apache.xerces.impl.xs.identity.UniqueOrKey;
import org.apache.xerces.impl.xs.identity.IdentityConstraint;
import org.apache.xerces.xni.QName;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.xerces.impl.xs.SchemaGrammar;
import org.apache.xerces.impl.xs.XSElementDecl;
import org.w3c.dom.Element;

class XSDKeyrefTraverser extends XSDAbstractIDConstraintTraverser
{
    public XSDKeyrefTraverser(final XSDHandler xsdHandler, final XSAttributeChecker xsAttributeChecker) {
        super(xsdHandler, xsAttributeChecker);
    }
    
    void traverse(final Element element, final XSElementDecl xsElementDecl, final XSDocumentInfo xsDocumentInfo, final SchemaGrammar schemaGrammar) {
        final Object[] checkAttributes = super.fAttrChecker.checkAttributes(element, false, xsDocumentInfo);
        final String s = (String)checkAttributes[XSAttributeChecker.ATTIDX_NAME];
        if (s == null) {
            this.reportSchemaError("s4s-att-must-appear", new Object[] { SchemaSymbols.ELT_KEYREF, SchemaSymbols.ATT_NAME }, element);
            super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
            return;
        }
        final QName qName = (QName)checkAttributes[XSAttributeChecker.ATTIDX_REFER];
        if (qName == null) {
            this.reportSchemaError("s4s-att-must-appear", new Object[] { SchemaSymbols.ELT_KEYREF, SchemaSymbols.ATT_REFER }, element);
            super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
            return;
        }
        UniqueOrKey uniqueOrKey = null;
        final IdentityConstraint identityConstraint = (IdentityConstraint)super.fSchemaHandler.getGlobalDecl(xsDocumentInfo, 5, qName, element);
        if (identityConstraint != null) {
            if (identityConstraint.getCategory() == 1 || identityConstraint.getCategory() == 3) {
                uniqueOrKey = (UniqueOrKey)identityConstraint;
            }
            else {
                this.reportSchemaError("src-resolve", new Object[] { qName.rawname, "identity constraint key/unique" }, element);
            }
        }
        if (uniqueOrKey == null) {
            super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
            return;
        }
        final KeyRef keyRef = new KeyRef(xsDocumentInfo.fTargetNamespace, s, xsElementDecl.fName, uniqueOrKey);
        this.traverseIdentityConstraint(keyRef, element, xsDocumentInfo, checkAttributes);
        if (uniqueOrKey.getFieldCount() != keyRef.getFieldCount()) {
            this.reportSchemaError("c-props-correct.2", new Object[] { s, uniqueOrKey.getIdentityConstraintName() }, element);
        }
        else {
            schemaGrammar.addIDConstraintDecl(xsElementDecl, keyRef);
        }
        super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
    }
}
