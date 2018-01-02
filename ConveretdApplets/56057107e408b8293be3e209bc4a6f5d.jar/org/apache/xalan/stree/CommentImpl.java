// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.stree;

import org.w3c.dom.Comment;

public class CommentImpl extends TextImpl implements Comment
{
    public CommentImpl(final DocumentImpl doc, final String data) {
        super(doc, data);
    }
    
    public CommentImpl(final DocumentImpl doc, final char[] ch, final int start, final int length) {
        super(doc, ch, start, length);
    }
    
    public String getLocalName() {
        return "#comment";
    }
    
    public String getNodeName() {
        return "#comment";
    }
    
    public short getNodeType() {
        return 8;
    }
}
