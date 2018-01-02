// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.util;

import org.apache.xerces.xni.NamespaceContext;

public class NamespaceSupport implements NamespaceContext
{
    protected String[] fNamespace;
    protected int fNamespaceSize;
    protected int[] fContext;
    protected int fCurrentContext;
    
    public NamespaceSupport() {
        this.fNamespace = new String[32];
        this.fContext = new int[8];
    }
    
    public NamespaceSupport(NamespaceContext context) {
        this.fNamespace = new String[32];
        this.fContext = new int[8];
        this.pushContext();
        while (context != null) {
            for (int count = context.getDeclaredPrefixCount(), i = 0; i < count; ++i) {
                final String prefix = context.getDeclaredPrefixAt(i);
                String uri = this.getURI(prefix);
                if (uri == null) {
                    uri = context.getURI(prefix);
                    this.declarePrefix(prefix, uri);
                }
            }
            context = context.getParentContext();
        }
    }
    
    public void reset() {
        this.fNamespaceSize = 0;
        this.fCurrentContext = 0;
        this.fContext[this.fCurrentContext] = this.fNamespaceSize;
        this.fNamespace[this.fNamespaceSize++] = XMLSymbols.PREFIX_XML;
        this.fNamespace[this.fNamespaceSize++] = NamespaceContext.XML_URI;
        this.fNamespace[this.fNamespaceSize++] = XMLSymbols.PREFIX_XMLNS;
        this.fNamespace[this.fNamespaceSize++] = NamespaceContext.XMLNS_URI;
        ++this.fCurrentContext;
    }
    
    public void pushContext() {
        if (this.fCurrentContext + 1 == this.fContext.length) {
            final int[] contextarray = new int[this.fContext.length * 2];
            System.arraycopy(this.fContext, 0, contextarray, 0, this.fContext.length);
            this.fContext = contextarray;
        }
        this.fContext[++this.fCurrentContext] = this.fNamespaceSize;
    }
    
    public void popContext() {
        this.fNamespaceSize = this.fContext[this.fCurrentContext--];
    }
    
    public boolean declarePrefix(final String prefix, final String uri) {
        if (prefix == XMLSymbols.PREFIX_XML || prefix == XMLSymbols.PREFIX_XMLNS) {
            return false;
        }
        for (int i = this.fNamespaceSize; i > this.fContext[this.fCurrentContext]; i -= 2) {
            if (this.fNamespace[i - 2] == prefix) {
                this.fNamespace[i - 1] = uri;
                return true;
            }
        }
        if (this.fNamespaceSize == this.fNamespace.length) {
            final String[] namespacearray = new String[this.fNamespaceSize * 2];
            System.arraycopy(this.fNamespace, 0, namespacearray, 0, this.fNamespaceSize);
            this.fNamespace = namespacearray;
        }
        this.fNamespace[this.fNamespaceSize++] = prefix;
        this.fNamespace[this.fNamespaceSize++] = uri;
        return true;
    }
    
    public String getURI(final String prefix) {
        for (int i = this.fNamespaceSize; i > 0; i -= 2) {
            if (this.fNamespace[i - 2] == prefix) {
                return this.fNamespace[i - 1];
            }
        }
        return null;
    }
    
    public String getPrefix(final String uri) {
        for (int i = this.fNamespaceSize; i > 0; i -= 2) {
            if (this.fNamespace[i - 1] == uri && this.getURI(this.fNamespace[i - 2]) == uri) {
                return this.fNamespace[i - 2];
            }
        }
        return null;
    }
    
    public int getDeclaredPrefixCount() {
        return (this.fNamespaceSize - this.fContext[this.fCurrentContext]) / 2;
    }
    
    public String getDeclaredPrefixAt(final int index) {
        return this.fNamespace[this.fContext[this.fCurrentContext] + index * 2];
    }
    
    public NamespaceContext getParentContext() {
        if (this.fCurrentContext == 1) {
            return null;
        }
        return new Context(this.fCurrentContext - 1);
    }
    
    final class Context implements NamespaceContext
    {
        private int fCurrentContext;
        
        public Context(final int currentContext) {
            this.setCurrentContext(currentContext);
        }
        
        public void setCurrentContext(final int currentContext) {
            this.fCurrentContext = currentContext;
        }
        
        public String getURI(final String prefix) {
            for (int i = NamespaceSupport.this.fNamespaceSize; i > 0; i -= 2) {
                if (NamespaceSupport.this.fNamespace[i - 2] == prefix) {
                    return NamespaceSupport.this.fNamespace[i - 1];
                }
            }
            return null;
        }
        
        public int getDeclaredPrefixCount() {
            return (NamespaceSupport.this.fNamespaceSize - NamespaceSupport.this.fContext[this.fCurrentContext]) / 2;
        }
        
        public String getDeclaredPrefixAt(final int index) {
            return NamespaceSupport.this.fNamespace[NamespaceSupport.this.fContext[this.fCurrentContext] + index * 2];
        }
        
        public NamespaceContext getParentContext() {
            if (this.fCurrentContext == 1) {
                return null;
            }
            this.setCurrentContext(this.fCurrentContext - 1);
            return this;
        }
    }
}
