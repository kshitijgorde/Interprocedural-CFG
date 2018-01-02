// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.Comment;
import org.w3c.dom.CharacterData;

public class CommentImpl extends CharacterDataImpl implements CharacterData, Comment
{
    static final long serialVersionUID = -2685736833408134044L;
    
    public CommentImpl(final CoreDocumentImpl coreDocumentImpl, final String s) {
        super(coreDocumentImpl, s);
    }
    
    public short getNodeType() {
        return 8;
    }
    
    public String getNodeName() {
        return "#comment";
    }
}
