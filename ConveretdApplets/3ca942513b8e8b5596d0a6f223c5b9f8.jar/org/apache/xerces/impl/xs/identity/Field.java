// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.identity;

import org.apache.xerces.impl.xpath.XPathException;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.xs.XSComplexTypeDefinition;
import org.apache.xerces.xs.XSTypeDefinition;
import org.apache.xerces.impl.xs.util.ShortListImpl;
import org.apache.xerces.xs.ShortList;
import org.apache.xerces.impl.xpath.XPath;

public class Field
{
    protected XPath fXPath;
    protected IdentityConstraint fIdentityConstraint;
    
    public Field(final XPath fxPath, final IdentityConstraint fIdentityConstraint) {
        this.fXPath = fxPath;
        this.fIdentityConstraint = fIdentityConstraint;
    }
    
    public org.apache.xerces.impl.xpath.XPath getXPath() {
        return this.fXPath;
    }
    
    public IdentityConstraint getIdentityConstraint() {
        return this.fIdentityConstraint;
    }
    
    public XPathMatcher createMatcher(final FieldActivator fieldActivator, final ValueStore valueStore) {
        return new Matcher(this.fXPath, fieldActivator, valueStore);
    }
    
    public String toString() {
        return this.fXPath.toString();
    }
    
    protected class Matcher extends XPathMatcher
    {
        protected FieldActivator fFieldActivator;
        protected ValueStore fStore;
        
        public Matcher(final XPath xPath, final FieldActivator fFieldActivator, final ValueStore fStore) {
            super(xPath);
            this.fFieldActivator = fFieldActivator;
            this.fStore = fStore;
        }
        
        protected void matched(final Object o, final short n, final ShortList list, final boolean b) {
            super.matched(o, n, list, b);
            if (b && Field.this.fIdentityConstraint.getCategory() == 1) {
                this.fStore.reportError("KeyMatchesNillable", new Object[] { Field.this.fIdentityConstraint.getElementName() });
            }
            this.fStore.addValue(Field.this, o, this.convertToPrimitiveKind(n), this.convertToPrimitiveKind(list));
            this.fFieldActivator.setMayMatch(Field.this, Boolean.FALSE);
        }
        
        private short convertToPrimitiveKind(final short n) {
            if (n <= 20) {
                return n;
            }
            if (n <= 29) {
                return 2;
            }
            if (n <= 42) {
                return 4;
            }
            return n;
        }
        
        private ShortList convertToPrimitiveKind(final ShortList list) {
            if (list != null) {
                int length;
                int i;
                for (length = list.getLength(), i = 0; i < length; ++i) {
                    final short item = list.item(i);
                    if (item != this.convertToPrimitiveKind(item)) {
                        break;
                    }
                }
                if (i != length) {
                    final short[] array = new short[length];
                    for (int j = 0; j < i; ++j) {
                        array[j] = list.item(j);
                    }
                    while (i < length) {
                        array[i] = this.convertToPrimitiveKind(list.item(i));
                        ++i;
                    }
                    return new ShortListImpl(array, array.length);
                }
            }
            return list;
        }
        
        protected void handleContent(final XSTypeDefinition xsTypeDefinition, final boolean b, final Object fMatchedString, final short n, final ShortList list) {
            if (xsTypeDefinition == null || (xsTypeDefinition.getTypeCategory() == 15 && ((XSComplexTypeDefinition)xsTypeDefinition).getContentType() != 1)) {
                this.fStore.reportError("cvc-id.3", new Object[] { Field.this.fIdentityConstraint.getName(), Field.this.fIdentityConstraint.getElementName() });
            }
            this.matched(super.fMatchedString = fMatchedString, n, list, b);
        }
    }
    
    public static class XPath extends org.apache.xerces.impl.xpath.XPath
    {
        public XPath(final String s, final SymbolTable symbolTable, final NamespaceContext namespaceContext) throws XPathException {
            super((s.trim().startsWith("/") || s.trim().startsWith(".")) ? s : ("./" + s), symbolTable, namespaceContext);
            for (int i = 0; i < super.fLocationPaths.length; ++i) {
                for (int j = 0; j < super.fLocationPaths[i].steps.length; ++j) {
                    if (super.fLocationPaths[i].steps[j].axis.type == 2 && j < super.fLocationPaths[i].steps.length - 1) {
                        throw new XPathException("c-fields-xpaths");
                    }
                }
            }
        }
    }
}
