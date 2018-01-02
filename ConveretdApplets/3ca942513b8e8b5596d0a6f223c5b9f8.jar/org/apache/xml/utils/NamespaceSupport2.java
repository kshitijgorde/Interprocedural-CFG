// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils;

import java.util.Enumeration;
import java.util.EmptyStackException;
import org.xml.sax.helpers.NamespaceSupport;

public class NamespaceSupport2 extends NamespaceSupport
{
    private Context2 currentContext;
    public static final String XMLNS = "http://www.w3.org/XML/1998/namespace";
    
    public NamespaceSupport2() {
        this.reset();
    }
    
    public void reset() {
        (this.currentContext = new Context2(null)).declarePrefix("xml", "http://www.w3.org/XML/1998/namespace");
    }
    
    public void pushContext() {
        final Context2 parentContext = this.currentContext;
        this.currentContext = parentContext.getChild();
        if (this.currentContext == null) {
            this.currentContext = new Context2(parentContext);
        }
        else {
            this.currentContext.setParent(parentContext);
        }
    }
    
    public void popContext() {
        final Context2 parentContext = this.currentContext.getParent();
        if (parentContext == null) {
            throw new EmptyStackException();
        }
        this.currentContext = parentContext;
    }
    
    public boolean declarePrefix(final String prefix, final String uri) {
        if (prefix.equals("xml") || prefix.equals("xmlns")) {
            return false;
        }
        this.currentContext.declarePrefix(prefix, uri);
        return true;
    }
    
    public String[] processName(final String qName, final String[] parts, final boolean isAttribute) {
        final String[] name = this.currentContext.processName(qName, isAttribute);
        if (name == null) {
            return null;
        }
        System.arraycopy(name, 0, parts, 0, 3);
        return parts;
    }
    
    public String getURI(final String prefix) {
        return this.currentContext.getURI(prefix);
    }
    
    public Enumeration getPrefixes() {
        return this.currentContext.getPrefixes();
    }
    
    public String getPrefix(final String uri) {
        return this.currentContext.getPrefix(uri);
    }
    
    public Enumeration getPrefixes(final String uri) {
        return new PrefixForUriEnumerator(this, uri, this.getPrefixes());
    }
    
    public Enumeration getDeclaredPrefixes() {
        return this.currentContext.getDeclaredPrefixes();
    }
}
