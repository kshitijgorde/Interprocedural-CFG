// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.schema.identity;

import org.apache.xerces.framework.XMLAttrList;
import org.apache.xerces.utils.QName;
import org.apache.xerces.utils.NamespacesScope;
import org.apache.xerces.utils.StringPool;

public class Selector
{
    protected XPath fXPath;
    protected IdentityConstraint fIdentityConstraint;
    
    public Selector(final XPath fxPath, final IdentityConstraint fIdentityConstraint) {
        this.fXPath = fxPath;
        this.fIdentityConstraint = fIdentityConstraint;
    }
    
    public org.apache.xerces.validators.schema.identity.XPath getXPath() {
        return this.fXPath;
    }
    
    public IdentityConstraint getIdentityConstraint() {
        return this.fIdentityConstraint;
    }
    
    public XPathMatcher createMatcher(final FieldActivator fieldActivator) {
        return new Matcher(this.fXPath, fieldActivator);
    }
    
    public String toString() {
        return this.fXPath.toString();
    }
    
    public static class XPath extends org.apache.xerces.validators.schema.identity.XPath
    {
        public XPath(final String s, final StringPool stringPool, final NamespacesScope namespacesScope) throws XPathException {
            super("./" + s, stringPool, namespacesScope);
            if (super.fLocationPath.steps[super.fLocationPath.steps.length - 1].axis.type == 2) {
                throw new XPathException("selectors cannot select attributes");
            }
        }
    }
    
    protected class Matcher extends XPathMatcher
    {
        protected FieldActivator fFieldActivator;
        protected int fElementDepth;
        protected int fMatchedDepth;
        
        public Matcher(final XPath xPath, final FieldActivator fFieldActivator) {
            super(xPath);
            this.fFieldActivator = fFieldActivator;
        }
        
        public void startDocumentFragment(final StringPool stringPool, final NamespacesScope namespacesScope) throws Exception {
            super.startDocumentFragment(stringPool, namespacesScope);
            this.fElementDepth = 0;
            this.fMatchedDepth = -1;
        }
        
        public void startElement(final QName qName, final XMLAttrList list, final int n) throws Exception {
            super.startElement(qName, list, n);
            ++this.fElementDepth;
            if (this.fMatchedDepth == -1 && this.isMatched()) {
                this.fMatchedDepth = this.fElementDepth;
                this.fFieldActivator.startValueScopeFor(Selector.this.fIdentityConstraint);
                for (int fieldCount = Selector.this.fIdentityConstraint.getFieldCount(), i = 0; i < fieldCount; ++i) {
                    this.fFieldActivator.activateField(Selector.this.fIdentityConstraint.getFieldAt(i)).startElement(qName, list, n);
                }
            }
        }
        
        public void endElement(final QName qName) throws Exception {
            super.endElement(qName);
            if (this.fElementDepth-- == this.fMatchedDepth) {
                this.fMatchedDepth = -1;
                this.fFieldActivator.endValueScopeFor(Selector.this.fIdentityConstraint);
            }
        }
    }
}
