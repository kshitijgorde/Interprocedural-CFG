// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser;

import java.util.Iterator;
import org.jdom.Content;
import org.jdom.Element;

class Insert extends SimpleAction
{
    Insert(final Element root, final Context context) {
        super(root, context);
    }
    
    @Override
    void process() throws Exception {
        final Element node = this.nodeAtPath();
        final Element parent = node.getParentElement();
        final int index = parent.indexOf((Content)node);
        final String content = this.contentArg();
        if (content != null) {
            final Element cn = this.nodeAtPath(content);
            if (cn != null) {
                Insert.log.debug(String.valueOf(this.location()) + "\nINSERT at: " + index + "\n" + this.toString(cn) + "\nAS CHILD OF: " + this.shortString(parent));
                parent.addContent(index, (Content)cn.clone());
                return;
            }
        }
        for (final Element child : this.root().getChildren()) {
            Insert.log.debug(String.valueOf(this.location()) + "\nINSERT at: " + index + "\n" + this.toString(child) + "\nAS CHILD OF: " + this.shortString(parent));
            parent.addContent(index, (Content)child.clone());
        }
    }
}
