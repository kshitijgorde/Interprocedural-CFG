// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.schema.identity;

import org.apache.xerces.utils.NamespacesScope;
import org.apache.xerces.utils.StringPool;
import org.apache.xerces.validators.datatype.DatatypeValidator;

public class Field
{
    protected XPath fXPath;
    protected DatatypeValidator fDatatypeValidator;
    protected IdentityConstraint fIdentityConstraint;
    
    public Field(final XPath fxPath, final DatatypeValidator fDatatypeValidator, final IdentityConstraint fIdentityConstraint) {
        this.fXPath = fxPath;
        this.fDatatypeValidator = fDatatypeValidator;
        this.fIdentityConstraint = fIdentityConstraint;
    }
    
    public org.apache.xerces.validators.schema.identity.XPath getXPath() {
        return this.fXPath;
    }
    
    public DatatypeValidator getDatatypeValidator() {
        return this.fDatatypeValidator;
    }
    
    public IdentityConstraint getIdentityConstraint() {
        return this.fIdentityConstraint;
    }
    
    public XPathMatcher createMatcher(final ValueStore valueStore) {
        return new Matcher(this.fXPath, valueStore);
    }
    
    public String toString() {
        return this.fXPath.toString();
    }
    
    public static class XPath extends org.apache.xerces.validators.schema.identity.XPath
    {
        public XPath(final String s, final StringPool stringPool, final NamespacesScope namespacesScope) throws XPathException {
            super("./" + s, stringPool, namespacesScope);
        }
    }
    
    protected class Matcher extends XPathMatcher
    {
        protected ValueStore fStore;
        
        public Matcher(final XPath xPath, final ValueStore fStore) {
            super(xPath, true);
            this.fStore = fStore;
        }
        
        protected void matched(final String s) throws Exception {
            super.matched(s);
            this.fStore.addValue(Field.this, s);
        }
    }
}
