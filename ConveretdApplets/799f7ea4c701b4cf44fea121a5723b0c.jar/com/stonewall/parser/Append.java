// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser;

import java.util.Iterator;
import org.jdom.Content;
import org.jdom.Element;

class Append extends SimpleAction
{
    Append(final Element root, final Context context) {
        super(root, context);
    }
    
    @Override
    void process() throws Exception {
        final Element node = this.nodeAtPath();
        final String content = this.contentArg();
        final boolean clean = Boolean.valueOf(this.cleanArg());
        if (clean) {
            node.removeContent();
        }
        if (content != null) {
            final Element cn = this.nodeAtPath(content);
            if (cn != null) {
                Append.log.debug(String.valueOf(this.location()) + "\nAPPENDING:\n" + this.toString(cn) + "\nAS CHILD OF: " + this.shortString(node));
                node.addContent((Content)cn.clone());
                return;
            }
        }
        for (final Element child : this.root().getChildren()) {
            Append.log.debug(String.valueOf(this.location()) + "\nAPPENDING:\n" + this.toString(child) + "\nAS CHILD OF: " + this.shortString(node));
            node.addContent((Content)child.clone());
        }
    }
    
    String cleanArg() {
        return this.root().getAttributeValue("clean", "0");
    }
}
