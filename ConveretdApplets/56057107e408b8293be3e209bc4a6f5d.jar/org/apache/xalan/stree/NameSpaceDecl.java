// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.stree;

public class NameSpaceDecl extends AttrImplNS
{
    NameSpaceDecl(final DocumentImpl doc, final String uri, final String name, final String value) {
        super(doc, uri, name, value);
    }
    
    public boolean isNamespaceNode() {
        return true;
    }
}
