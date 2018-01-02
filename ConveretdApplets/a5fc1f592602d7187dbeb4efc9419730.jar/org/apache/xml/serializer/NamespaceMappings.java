// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serializer;

import org.xml.sax.SAXException;
import org.xml.sax.ContentHandler;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Stack;

public class NamespaceMappings
{
    private int count;
    private Stack m_prefixStack;
    private Hashtable m_namespaces;
    private Stack m_nodeStack;
    private static final String EMPTYSTRING = "";
    private static final String XML_PREFIX = "xml";
    
    public NamespaceMappings() {
        this.count = 0;
        this.m_prefixStack = new Stack();
        this.m_namespaces = new Hashtable();
        this.m_nodeStack = new Stack();
        this.initNamespaces();
    }
    
    private void initNamespaces() {
        Stack stack;
        this.m_namespaces.put("", stack = new Stack());
        stack.push("");
        this.m_prefixStack.push("");
        this.m_namespaces.put("xml", stack = new Stack());
        stack.push("http://www.w3.org/XML/1998/namespace");
        this.m_prefixStack.push("xml");
        this.m_nodeStack.push(new Integer(-1));
    }
    
    public String lookupNamespace(final String prefix) {
        final Stack stack = this.m_namespaces.get(prefix);
        return (stack != null && !stack.isEmpty()) ? stack.peek() : null;
    }
    
    public String lookupPrefix(final String uri) {
        String foundPrefix = null;
        final Enumeration prefixes = this.m_namespaces.keys();
        while (prefixes.hasMoreElements()) {
            final String prefix = prefixes.nextElement();
            final String uri2 = this.lookupNamespace(prefix);
            if (uri2 != null && uri2.equals(uri)) {
                foundPrefix = prefix;
                break;
            }
        }
        return foundPrefix;
    }
    
    public boolean popNamespace(final String prefix) {
        if (prefix.startsWith("xml")) {
            return false;
        }
        final Stack stack;
        if ((stack = this.m_namespaces.get(prefix)) != null) {
            stack.pop();
            return true;
        }
        return false;
    }
    
    public boolean pushNamespace(final String prefix, final String uri, final int elemDepth) {
        if (prefix.startsWith("xml")) {
            return false;
        }
        Stack stack;
        if ((stack = this.m_namespaces.get(prefix)) == null) {
            this.m_namespaces.put(prefix, stack = new Stack());
        }
        if (!stack.empty() && uri.equals(stack.peek())) {
            return false;
        }
        stack.push(uri);
        this.m_prefixStack.push(prefix);
        this.m_nodeStack.push(new Integer(elemDepth));
        return true;
    }
    
    public void popNamespaces(final int elemDepth, final ContentHandler saxHandler) {
        while (!this.m_nodeStack.isEmpty()) {
            final Integer i = this.m_nodeStack.peek();
            if (i < elemDepth) {
                return;
            }
            this.m_nodeStack.pop();
            final String prefix = this.m_prefixStack.pop();
            this.popNamespace(prefix);
            if (saxHandler == null) {
                continue;
            }
            try {
                saxHandler.endPrefixMapping(prefix);
            }
            catch (SAXException ex) {}
        }
    }
    
    public String generateNextPrefix() {
        return "ns" + this.count++;
    }
    
    public Object clone() throws CloneNotSupportedException {
        final NamespaceMappings clone = new NamespaceMappings();
        clone.m_prefixStack = (Stack)this.m_prefixStack.clone();
        clone.m_nodeStack = (Stack)this.m_nodeStack.clone();
        clone.m_namespaces = (Hashtable)this.m_namespaces.clone();
        clone.count = this.count;
        return clone;
    }
    
    public final void reset() {
        this.count = 0;
        this.m_namespaces.clear();
        this.m_nodeStack.clear();
        this.m_prefixStack.clear();
        this.initNamespaces();
    }
}
