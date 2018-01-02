// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser;

import org.jdom.Element;

class Move extends SimpleAction
{
    Move(final Element root, final Context context) {
        super(root, context);
    }
    
    @Override
    void process() throws Exception {
        final Element node = this.nodeAtPath();
        final String content = this.contentArg();
        if (content != null) {
            final Element cn = this.nodeAtPath(content);
            if (cn != null) {
                Move.log.debug(String.valueOf(this.location()) + "\nMOVING:\n" + this.toString(cn) + "\nTO CHILD OF: " + this.shortString(node));
                node.addContent(cn.detach());
            }
        }
    }
}
