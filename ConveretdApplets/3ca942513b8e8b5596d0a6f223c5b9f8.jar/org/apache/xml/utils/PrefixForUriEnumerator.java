// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils;

import java.util.NoSuchElementException;
import java.util.Enumeration;

class PrefixForUriEnumerator implements Enumeration
{
    private Enumeration allPrefixes;
    private String uri;
    private String lookahead;
    private NamespaceSupport2 nsup;
    
    PrefixForUriEnumerator(final NamespaceSupport2 nsup, final String uri, final Enumeration allPrefixes) {
        this.lookahead = null;
        this.nsup = nsup;
        this.uri = uri;
        this.allPrefixes = allPrefixes;
    }
    
    public boolean hasMoreElements() {
        if (this.lookahead != null) {
            return true;
        }
        while (this.allPrefixes.hasMoreElements()) {
            final String prefix = this.allPrefixes.nextElement();
            if (this.uri.equals(this.nsup.getURI(prefix))) {
                this.lookahead = prefix;
                return true;
            }
        }
        return false;
    }
    
    public Object nextElement() {
        if (this.hasMoreElements()) {
            final String tmp = this.lookahead;
            this.lookahead = null;
            return tmp;
        }
        throw new NoSuchElementException();
    }
}
