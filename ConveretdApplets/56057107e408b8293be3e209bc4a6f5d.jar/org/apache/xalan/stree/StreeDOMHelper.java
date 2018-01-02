// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.stree;

import org.w3c.dom.Node;
import org.apache.xpath.DOM2Helper;

public class StreeDOMHelper extends DOM2Helper
{
    public short getLevel(final Node node1) {
        try {
            return ((Child)node1).getLevel();
        }
        catch (ClassCastException ex) {
            return super.getLevel(node1);
        }
    }
    
    public String getUniqueID(final Node node) {
        try {
            final int index = ((Child)node).getUid();
            return "N" + Integer.toHexString(index).toUpperCase();
        }
        catch (ClassCastException ex) {
            return super.getUniqueID(node);
        }
    }
    
    public boolean isNamespaceNode(final Node n) {
        try {
            return ((Child)n).isNamespaceNode();
        }
        catch (ClassCastException ex) {
            return super.isNamespaceNode(n);
        }
    }
}
