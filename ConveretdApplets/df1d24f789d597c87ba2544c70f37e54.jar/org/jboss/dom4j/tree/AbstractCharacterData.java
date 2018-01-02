// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.dom4j.tree;

import org.jboss.dom4j.Element;
import org.jboss.dom4j.CharacterData;

public abstract class AbstractCharacterData extends AbstractNode implements CharacterData
{
    public String getPath(final Element context) {
        final Element parent = this.getParent();
        return (parent != null && parent != context) ? (parent.getPath(context) + "/text()") : "text()";
    }
    
    public String getUniquePath(final Element context) {
        final Element parent = this.getParent();
        return (parent != null && parent != context) ? (parent.getUniquePath(context) + "/text()") : "text()";
    }
    
    public void appendText(final String text) {
        this.setText(this.getText() + text);
    }
}
