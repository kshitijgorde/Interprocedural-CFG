// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.traversers;

import org.apache.xerces.impl.xs.identity.IdentityConstraint;
import org.apache.xerces.impl.xs.identity.UniqueOrKey;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.w3c.dom.Node;
import org.apache.xerces.util.DOMUtil;
import org.apache.xerces.impl.xs.SchemaGrammar;
import org.apache.xerces.impl.xs.XSElementDecl;
import org.w3c.dom.Element;

class XSDUniqueOrKeyTraverser extends XSDAbstractIDConstraintTraverser
{
    public XSDUniqueOrKeyTraverser(final XSDHandler handler, final XSAttributeChecker gAttrCheck) {
        super(handler, gAttrCheck);
    }
    
    void traverse(final Element uElem, final XSElementDecl element, final XSDocumentInfo schemaDoc, final SchemaGrammar grammar) {
        final Object[] attrValues = super.fAttrChecker.checkAttributes(uElem, false, schemaDoc);
        final String uName = (String)attrValues[XSAttributeChecker.ATTIDX_NAME];
        if (uName == null) {
            this.reportSchemaError("s4s-att-must-appear", new Object[] { DOMUtil.getLocalName(uElem), SchemaSymbols.ATT_NAME }, uElem);
            super.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
            return;
        }
        UniqueOrKey uniqueOrKey = null;
        if (DOMUtil.getLocalName(uElem).equals(SchemaSymbols.ELT_UNIQUE)) {
            uniqueOrKey = new UniqueOrKey(schemaDoc.fTargetNamespace, uName, element.fName, (short)3);
        }
        else {
            uniqueOrKey = new UniqueOrKey(schemaDoc.fTargetNamespace, uName, element.fName, (short)1);
        }
        this.traverseIdentityConstraint(uniqueOrKey, uElem, schemaDoc, attrValues);
        grammar.addIDConstraintDecl(element, uniqueOrKey);
        super.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
    }
}
