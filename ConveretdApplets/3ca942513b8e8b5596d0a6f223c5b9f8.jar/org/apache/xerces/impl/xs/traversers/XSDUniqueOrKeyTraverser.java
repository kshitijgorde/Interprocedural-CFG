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
    public XSDUniqueOrKeyTraverser(final XSDHandler xsdHandler, final XSAttributeChecker xsAttributeChecker) {
        super(xsdHandler, xsAttributeChecker);
    }
    
    void traverse(final Element element, final XSElementDecl xsElementDecl, final XSDocumentInfo xsDocumentInfo, final SchemaGrammar schemaGrammar) {
        final Object[] checkAttributes = super.fAttrChecker.checkAttributes(element, false, xsDocumentInfo);
        final String s = (String)checkAttributes[XSAttributeChecker.ATTIDX_NAME];
        if (s == null) {
            this.reportSchemaError("s4s-att-must-appear", new Object[] { DOMUtil.getLocalName(element), SchemaSymbols.ATT_NAME }, element);
            super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
            return;
        }
        UniqueOrKey uniqueOrKey;
        if (DOMUtil.getLocalName(element).equals(SchemaSymbols.ELT_UNIQUE)) {
            uniqueOrKey = new UniqueOrKey(xsDocumentInfo.fTargetNamespace, s, xsElementDecl.fName, (short)3);
        }
        else {
            uniqueOrKey = new UniqueOrKey(xsDocumentInfo.fTargetNamespace, s, xsElementDecl.fName, (short)1);
        }
        this.traverseIdentityConstraint(uniqueOrKey, element, xsDocumentInfo, checkAttributes);
        schemaGrammar.addIDConstraintDecl(xsElementDecl, uniqueOrKey);
        super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
    }
}
