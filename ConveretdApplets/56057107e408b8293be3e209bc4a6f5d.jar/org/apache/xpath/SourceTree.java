// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath;

import org.w3c.dom.Node;

public class SourceTree
{
    public String m_url;
    public Node m_root;
    
    public SourceTree(final Node root, final String url) {
        this.m_root = root;
        this.m_url = url;
    }
}
