// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.identity;

import org.apache.xerces.impl.xs.XSElementDecl;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.impl.xpath.XPathException;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.impl.xpath.XPath;

public class Selector
{
    protected XPath fXPath;
    protected IdentityConstraint fIdentityConstraint;
    protected IdentityConstraint fIDConstraint;
    
    public Selector(final XPath xpath, final IdentityConstraint identityConstraint) {
        this.fXPath = xpath;
        this.fIdentityConstraint = identityConstraint;
    }
    
    public org.apache.xerces.impl.xpath.XPath getXPath() {
        return this.fXPath;
    }
    
    public IdentityConstraint getIDConstraint() {
        return this.fIdentityConstraint;
    }
    
    public XPathMatcher createMatcher(final FieldActivator activator, final int initialDepth) {
        return new Matcher(this.fXPath, activator, initialDepth);
    }
    
    public String toString() {
        return this.fXPath.toString();
    }
    
    public static class XPath extends org.apache.xerces.impl.xpath.XPath
    {
        public XPath(final String xpath, final SymbolTable symbolTable, final NamespaceContext context) throws XPathException {
            super(normalize(xpath), symbolTable, context);
            for (int i = 0; i < super.fLocationPaths.length; ++i) {
                final Axis axis = super.fLocationPaths[i].steps[super.fLocationPaths[i].steps.length - 1].axis;
                if (axis.type == 2) {
                    throw new XPathException("c-selector-xpath");
                }
            }
        }
        
        private static String normalize(String xpath) {
            final StringBuffer modifiedXPath = new StringBuffer(xpath.length() + 5);
            int unionIndex = -1;
            while (true) {
                if (!xpath.trim().startsWith("/") && !xpath.trim().startsWith(".")) {
                    modifiedXPath.append("./");
                }
                unionIndex = xpath.indexOf(124);
                if (unionIndex == -1) {
                    break;
                }
                modifiedXPath.append(xpath.substring(0, unionIndex + 1));
                xpath = xpath.substring(unionIndex + 1, xpath.length());
            }
            modifiedXPath.append(xpath);
            return modifiedXPath.toString();
        }
    }
    
    public class Matcher extends XPathMatcher
    {
        protected FieldActivator fFieldActivator;
        protected int fInitialDepth;
        protected int fElementDepth;
        protected int fMatchedDepth;
        
        public Matcher(final XPath xpath, final FieldActivator activator, final int initialDepth) {
            super(xpath);
            this.fFieldActivator = activator;
            this.fInitialDepth = initialDepth;
        }
        
        public void startDocumentFragment(final SymbolTable symbolTable) throws XNIException {
            super.startDocumentFragment(symbolTable);
            this.fElementDepth = 0;
            this.fMatchedDepth = -1;
        }
        
        public void startElement(final QName element, final XMLAttributes attributes, final XSElementDecl elementDecl) throws XNIException {
            super.startElement(element, attributes, elementDecl);
            ++this.fElementDepth;
            final int matched = this.isMatched();
            if ((this.fMatchedDepth == -1 && (matched & 0x1) == 0x1) || (matched & 0x5) == 0x5) {
                this.fMatchedDepth = this.fElementDepth;
                this.fFieldActivator.startValueScopeFor(Selector.this.fIdentityConstraint, this.fInitialDepth);
                for (int count = Selector.this.fIdentityConstraint.getFieldCount(), i = 0; i < count; ++i) {
                    final Field field = Selector.this.fIdentityConstraint.getFieldAt(i);
                    final XPathMatcher matcher = this.fFieldActivator.activateField(field, this.fInitialDepth);
                    matcher.startElement(element, attributes, elementDecl);
                }
            }
        }
        
        public void endElement(final QName element, final XSElementDecl eDecl, final String value) {
            super.endElement(element, eDecl, value);
            if (this.fElementDepth-- == this.fMatchedDepth) {
                this.fMatchedDepth = -1;
                this.fFieldActivator.endValueScopeFor(Selector.this.fIdentityConstraint, this.fInitialDepth);
            }
        }
        
        public IdentityConstraint getIdentityConstraint() {
            return Selector.this.fIdentityConstraint;
        }
        
        public int getInitialDepth() {
            return this.fInitialDepth;
        }
    }
}
