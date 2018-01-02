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
    public XSDKeyrefTraverser(final XSDHandler handler, final XSAttributeChecker gAttrCheck) {
        super(handler, gAttrCheck);
    }
    
    void traverse(final Element krElem, final XSElementDecl element, final XSDocumentInfo schemaDoc, final SchemaGrammar grammar) {
        final Object[] attrValues = super.fAttrChecker.checkAttributes(krElem, false, schemaDoc);
        final String krName = (String)attrValues[XSAttributeChecker.ATTIDX_NAME];
        if (krName == null) {
            this.reportSchemaError("s4s-att-must-appear", new Object[] { SchemaSymbols.ELT_KEYREF, SchemaSymbols.ATT_NAME }, krElem);
            super.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
            return;
        }
        final QName kName = (QName)attrValues[XSAttributeChecker.ATTIDX_REFER];
        if (kName == null) {
            this.reportSchemaError("s4s-att-must-appear", new Object[] { SchemaSymbols.ELT_KEYREF, SchemaSymbols.ATT_REFER }, krElem);
            super.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
            return;
        }
        UniqueOrKey key = null;
        final IdentityConstraint ret = (IdentityConstraint)super.fSchemaHandler.getGlobalDecl(schemaDoc, 5, kName, krElem);
        if (ret != null) {
            if (ret.getCategory() == 1 || ret.getCategory() == 3) {
                key = (UniqueOrKey)ret;
            }
            else {
                this.reportSchemaError("src-resolve", new Object[] { kName.rawname, "identity constraint key/unique" }, krElem);
            }
        }
        if (key == null) {
            super.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
            return;
        }
        final KeyRef keyRef = new KeyRef(schemaDoc.fTargetNamespace, krName, element.fName, key);
        this.traverseIdentityConstraint(keyRef, krElem, schemaDoc, attrValues);
        if (key.getFieldCount() != keyRef.getFieldCount()) {
            this.reportSchemaError("c-props-correct.2", new Object[] { krName, key.getIdentityConstraintName() }, krElem);
        }
        else {
            grammar.addIDConstraintDecl(element, keyRef);
        }
        super.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
    }
}
