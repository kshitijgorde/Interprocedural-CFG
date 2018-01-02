// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser;

import java.util.Iterator;
import org.jdom.Content;
import org.jdom.Element;

class InsertAfter extends SimpleAction
{
    InsertAfter(final Element root, final Context context) {
        super(root, context);
    }
    
    @Override
    void process() throws Exception {
        final Element node = this.nodeAtPath();
        final Element parent = node.getParentElement();
        int index = parent.indexOf((Content)node);
        final String content = this.contentArg();
        if (content != null) {
            final Element cn = this.nodeAtPath(content);
            if (cn != null) {
                InsertAfter.log.debug(String.valueOf(this.location()) + "\nINSERT after: " + index + "\n" + this.toString(cn) + "\nAS CHILD OF: " + this.shortString(parent));
                parent.addContent(++index, (Content)cn.clone());
                return;
            }
        }
        for (final Element child : this.root().getChildren()) {
            InsertAfter.log.debug(String.valueOf(this.location()) + "\nINSERT after: " + index + "\n" + this.toString(child) + "\nAS CHILD OF: " + this.shortString(parent));
            parent.addContent(++index, (Content)child.clone());
        }
    }
}
