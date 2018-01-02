// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serializer;

import java.util.ArrayList;
import org.xml.sax.SAXException;
import org.xml.sax.ContentHandler;
import java.util.Enumeration;
import java.util.Stack;
import java.util.Hashtable;

public class NamespaceMappings
{
    private int count;
    private Hashtable m_namespaces;
    private Stack m_nodeStack;
    private static final String EMPTYSTRING = "";
    private static final String XML_PREFIX = "xml";
    
    public NamespaceMappings() {
        this.count = 0;
        this.m_namespaces = new Hashtable();
        this.m_nodeStack = new Stack();
        this.initNamespaces();
    }
    
    private void initNamespaces() {
        Stack stack;
        this.m_namespaces.put("", stack = new Stack());
        stack.push(new MappingRecord("", "", 0));
        this.m_namespaces.put("xml", stack = new Stack());
        stack.push(new MappingRecord("xml", "http://www.w3.org/XML/1998/namespace", 0));
        this.m_nodeStack.push(new MappingRecord(null, null, -1));
    }
    
    public String lookupNamespace(final String prefix) {
        final Stack stack = this.m_namespaces.get(prefix);
        return (stack != null && !stack.isEmpty()) ? stack.peek().m_uri : null;
    }
    
    MappingRecord getMappingFromPrefix(final String prefix) {
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
    
    MappingRecord getMappingFromURI(final String uri) {
        MappingRecord foundMap = null;
        final Enumeration prefixes = this.m_namespaces.keys();
        while (prefixes.hasMoreElements()) {
            final String prefix = prefixes.nextElement();
            final MappingRecord map2 = this.getMappingFromPrefix(prefix);
            if (map2 != null && map2.m_uri.equals(uri)) {
                foundMap = map2;
                break;
            }
        }
        return foundMap;
    }
    
    boolean popNamespace(final String prefix) {
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
        if (!stack.empty() && uri.equals(stack.peek().m_uri)) {
            return false;
        }
        final MappingRecord map = new MappingRecord(prefix, uri, elemDepth);
        stack.push(map);
        this.m_nodeStack.push(map);
        return true;
    }
    
    void popNamespaces(final int elemDepth, final ContentHandler saxHandler) {
        while (!this.m_nodeStack.isEmpty()) {
            MappingRecord map = this.m_nodeStack.peek();
            final int depth = map.m_declarationDepth;
            if (depth < elemDepth) {
                return;
            }
            map = this.m_nodeStack.pop();
            final String prefix = map.m_prefix;
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
        clone.m_nodeStack = (Stack)this.m_nodeStack.clone();
        clone.m_namespaces = (Hashtable)this.m_namespaces.clone();
        clone.count = this.count;
        return clone;
    }
    
    final void reset() {
        this.count = 0;
        this.m_namespaces.clear();
        this.m_nodeStack.clear();
        this.initNamespaces();
    }
    
    public String[] lookupAllPrefixes(final String uri) {
        final ArrayList foundPrefixes = new ArrayList();
        final Enumeration prefixes = this.m_namespaces.keys();
        while (prefixes.hasMoreElements()) {
            final String prefix = prefixes.nextElement();
            final String uri2 = this.lookupNamespace(prefix);
            if (uri2 != null && uri2.equals(uri)) {
                foundPrefixes.add(prefix);
            }
        }
        final String[] prefixArray = new String[foundPrefixes.size()];
        foundPrefixes.toArray(prefixArray);
        return prefixArray;
    }
    
    class MappingRecord
    {
        final String m_prefix;
        final String m_uri;
        final int m_declarationDepth;
        
        MappingRecord(final String prefix, final String uri, final int depth) {
            this.m_prefix = prefix;
            this.m_uri = uri;
            this.m_declarationDepth = depth;
        }
    }
}
