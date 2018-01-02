// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.identity;

import org.apache.xerces.impl.xs.XSTypeDecl;
import org.apache.xerces.impl.xs.XSComplexTypeDecl;
import org.apache.xerces.impl.xs.XSElementDecl;
import org.apache.xerces.impl.dv.XSSimpleType;
import org.apache.xerces.impl.xpath.XPathException;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.impl.xpath.XPath;

public class Field
{
    protected XPath fXPath;
    protected IdentityConstraint fIdentityConstraint;
    protected boolean mayMatch;
    
    public Field(final XPath xpath, final IdentityConstraint identityConstraint) {
        this.mayMatch = true;
        this.fXPath = xpath;
        this.fIdentityConstraint = identityConstraint;
    }
    
    public void setMayMatch(final boolean b) {
        this.mayMatch = b;
    }
    
    public boolean mayMatch() {
        return this.mayMatch;
    }
    
    public org.apache.xerces.impl.xpath.XPath getXPath() {
        return this.fXPath;
    }
    
    public IdentityConstraint getIdentityConstraint() {
        return this.fIdentityConstraint;
    }
    
    public XPathMatcher createMatcher(final ValueStore store) {
        return new Matcher(this.fXPath, store);
    }
    
    public String toString() {
        return this.fXPath.toString();
    }
    
    public static class XPath extends org.apache.xerces.impl.xpath.XPath
    {
        public XPath(final String xpath, final SymbolTable symbolTable, final NamespaceContext context) throws XPathException {
            super((xpath.trim().startsWith("/") || xpath.trim().startsWith(".")) ? xpath : ("./" + xpath), symbolTable, context);
        }
    }
    
    protected class Matcher extends XPathMatcher
    {
        protected ValueStore fStore;
        
        public Matcher(final XPath xpath, final ValueStore store) {
            super(xpath);
            this.fStore = store;
        }
        
        protected void matched(final String content, final XSSimpleType val, final boolean isNil) {
            super.matched(content, val, isNil);
            if (isNil && Field.this.fIdentityConstraint.getCategory() == 1) {
                final String code = "KeyMatchesNillable";
                this.fStore.reportError(code, new Object[] { Field.this.fIdentityConstraint.getElementName() });
            }
            this.fStore.addValue(Field.this, new IDValue(content, val));
            Field.this.mayMatch = false;
        }
        
        protected void handleContent(final XSElementDecl eDecl, final String value) {
            XSSimpleType val = null;
            if (eDecl != null) {
                final XSTypeDecl type = eDecl.fType;
                if (type != null) {
                    if (type.getTypeCategory() == 13) {
                        final XSComplexTypeDecl ctype = (XSComplexTypeDecl)type;
                        val = (XSSimpleType)ctype.getSimpleType();
                    }
                    else {
                        val = (XSSimpleType)type;
                    }
                }
            }
            if (val == null) {
                final String code = "cvc-id.3";
                final String name = (eDecl == null) ? "null" : eDecl.getName();
                this.fStore.reportError(code, new Object[] { Field.this.fIdentityConstraint.getName(), name });
                return;
            }
            super.fMatchedString = value;
            if (eDecl != null) {
                this.matched(super.fMatchedString, val, eDecl.getIsNillable());
            }
            else {
                this.matched(super.fMatchedString, val, false);
            }
        }
    }
}
