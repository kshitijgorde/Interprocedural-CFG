// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom.output;

import org.jdom.Namespace;
import java.util.Stack;

class NamespaceStack
{
    private static final String CVS_ID = "@(#) $RCSfile: NamespaceStack.java,v $ $Revision: 1.14 $ $Date: 2007/11/10 05:29:01 $ $Name: jdom_1_1_1 $";
    private Stack prefixes;
    private Stack uris;
    
    NamespaceStack() {
        this.prefixes = new Stack();
        this.uris = new Stack();
    }
    
    public void push(final Namespace ns) {
        this.prefixes.push(ns.getPrefix());
        this.uris.push(ns.getURI());
    }
    
    public String pop() {
        final String prefix = this.prefixes.pop();
        this.uris.pop();
        return prefix;
    }
    
    public int size() {
        return this.prefixes.size();
    }
    
    public String getURI(final String prefix) {
        final int index = this.prefixes.lastIndexOf(prefix);
        if (index == -1) {
            return null;
        }
        final String uri = (String)this.uris.elementAt(index);
        return uri;
    }
    
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        final String sep = System.getProperty("line.separator");
        buf.append("Stack: " + this.prefixes.size() + sep);
        for (int i = 0; i < this.prefixes.size(); ++i) {
            buf.append(this.prefixes.elementAt(i) + "&" + this.uris.elementAt(i) + sep);
        }
        return buf.toString();
    }
}
