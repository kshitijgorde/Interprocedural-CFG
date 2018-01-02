// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.xinclude;

import org.apache.xerces.util.XMLSymbols;
import java.util.Enumeration;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.util.NamespaceSupport;

public class MultipleScopeNamespaceSupport extends NamespaceSupport
{
    protected int[] fScope;
    protected int fCurrentScope;
    
    public MultipleScopeNamespaceSupport() {
        this.fScope = new int[8];
        this.fCurrentScope = 0;
        this.fScope[0] = 0;
    }
    
    public MultipleScopeNamespaceSupport(final NamespaceContext namespaceContext) {
        super(namespaceContext);
        this.fScope = new int[8];
        this.fCurrentScope = 0;
        this.fScope[0] = 0;
    }
    
    public Enumeration getAllPrefixes() {
        int n = 0;
        if (super.fPrefixes.length < super.fNamespace.length / 2) {
            super.fPrefixes = new String[super.fNamespaceSize];
        }
        int n2 = 1;
        for (int i = super.fContext[this.fScope[this.fCurrentScope]]; i <= super.fNamespaceSize - 2; i += 2) {
            final String s = super.fNamespace[i];
            for (int j = 0; j < n; ++j) {
                if (super.fPrefixes[j] == s) {
                    n2 = 0;
                    break;
                }
            }
            if (n2 != 0) {
                super.fPrefixes[n++] = s;
            }
            n2 = 1;
        }
        return new Prefixes(this, super.fPrefixes, n);
    }
    
    public int getScopeForContext(final int i) {
        int fCurrentScope;
        for (fCurrentScope = this.fCurrentScope; i < this.fScope[fCurrentScope]; --fCurrentScope) {}
        return fCurrentScope;
    }
    
    public String getPrefix(final String s) {
        return this.getPrefix(s, super.fNamespaceSize, super.fContext[this.fScope[this.fCurrentScope]]);
    }
    
    public String getURI(final String s) {
        return this.getURI(s, super.fNamespaceSize, super.fContext[this.fScope[this.fCurrentScope]]);
    }
    
    public String getPrefix(final String s, final int n) {
        return this.getPrefix(s, super.fContext[n + 1], super.fContext[this.fScope[this.getScopeForContext(n)]]);
    }
    
    public String getURI(final String s, final int n) {
        return this.getURI(s, super.fContext[n + 1], super.fContext[this.fScope[this.getScopeForContext(n)]]);
    }
    
    public String getPrefix(final String s, final int n, final int n2) {
        if (s == NamespaceContext.XML_URI) {
            return XMLSymbols.PREFIX_XML;
        }
        if (s == NamespaceContext.XMLNS_URI) {
            return XMLSymbols.PREFIX_XMLNS;
        }
        for (int i = n; i > n2; i -= 2) {
            if (super.fNamespace[i - 1] == s && this.getURI(super.fNamespace[i - 2]) == s) {
                return super.fNamespace[i - 2];
            }
        }
        return null;
    }
    
    public String getURI(final String s, final int n, final int n2) {
        if (s == XMLSymbols.PREFIX_XML) {
            return NamespaceContext.XML_URI;
        }
        if (s == XMLSymbols.PREFIX_XMLNS) {
            return NamespaceContext.XMLNS_URI;
        }
        for (int i = n; i > n2; i -= 2) {
            if (super.fNamespace[i - 2] == s) {
                return super.fNamespace[i - 1];
            }
        }
        return null;
    }
    
    public void reset() {
        super.fCurrentContext = this.fScope[this.fCurrentScope];
        super.fNamespaceSize = super.fContext[super.fCurrentContext];
    }
    
    public void pushScope() {
        if (this.fCurrentScope + 1 == this.fScope.length) {
            final int[] fScope = new int[this.fScope.length * 2];
            System.arraycopy(this.fScope, 0, fScope, 0, this.fScope.length);
            this.fScope = fScope;
        }
        this.pushContext();
        this.fScope[++this.fCurrentScope] = super.fCurrentContext;
    }
    
    public void popScope() {
        super.fCurrentContext = this.fScope[this.fCurrentScope--];
        this.popContext();
    }
}
