// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.dom4j.tree;

import org.jboss.dom4j.Node;
import org.jboss.dom4j.Element;
import org.jboss.dom4j.Text;

public class FlyweightText extends AbstractText implements Text
{
    protected String text;
    
    public FlyweightText(final String text) {
        this.text = text;
    }
    
    public String getText() {
        return this.text;
    }
    
    protected Node createXPathResult(final Element parent) {
        return new DefaultText(parent, this.getText());
    }
}
