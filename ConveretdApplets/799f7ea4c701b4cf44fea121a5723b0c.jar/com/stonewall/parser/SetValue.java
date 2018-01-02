// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser;

import java.util.Iterator;
import org.jdom.Attribute;
import org.jdom.Element;

class SetValue extends SimpleAction
{
    SetValue(final Element root, final Context context) {
        super(root, context);
    }
    
    @Override
    void process() throws Exception {
        this.createAttribute();
        for (final Object object : this.objsAtPath(this.model(), 0)) {
            if (object instanceof Element) {
                final Element node = (Element)object;
                SetValue.log.debug(String.valueOf(this.location()) + "\nSET-TEXT on:\n" + this.toString(node) + "\nVALUE (" + this.root().getText() + ")");
                node.setText(this.root().getText());
                return;
            }
            if (object instanceof Attribute) {
                final Attribute node2 = (Attribute)object;
                SetValue.log.debug(String.valueOf(this.location()) + "\nSET ATTRIBUTE on:\n" + this.shortString(node2.getParent()) + "/@" + node2.getName() + "\nVALUE (" + this.root().getText() + ")");
                node2.setValue(this.root().getText());
            }
        }
    }
    
    void createAttribute() throws Exception {
        final String path = this.pathArg();
        for (int i = path.length() - 1; i > 0; --i) {
            final char c = path.charAt(i);
            if (c == '/') {
                break;
            }
            if (c == '@') {
                final String p2 = path.substring(0, i - 1);
                final String name = path.substring(i + 1);
                final Element e = this.nodeAtPath(p2);
                e.setAttribute(name, "");
                break;
            }
        }
    }
}
