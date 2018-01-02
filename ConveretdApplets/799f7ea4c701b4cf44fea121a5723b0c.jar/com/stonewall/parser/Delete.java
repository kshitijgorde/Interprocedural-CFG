// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser;

import java.util.Iterator;
import org.jdom.Attribute;
import org.jdom.Content;
import org.jdom.Element;

class Delete extends SimpleAction
{
    Delete(final Element root, final Context context) {
        super(root, context);
    }
    
    @Override
    void process() throws Exception {
        for (final Object object : this.objsAtPath(this.model(), 0)) {
            if (object instanceof Element) {
                final Element node = (Element)object;
                final Element parent = node.getParentElement();
                parent.removeContent((Content)node);
                Delete.log.debug(String.valueOf(this.location()) + "\nDELETE:\n" + this.toString(node) + "CHILD OF: " + this.shortString(parent));
            }
            else {
                if (!(object instanceof Attribute)) {
                    continue;
                }
                final Attribute a = (Attribute)object;
                final Element parent = a.getParent();
                parent.removeAttribute(a);
                Delete.log.debug(String.valueOf(this.location()) + "\nDELETE ATTRIBUTE: " + a.getName() + "\nFROM: " + this.shortString(parent));
            }
        }
    }
}
