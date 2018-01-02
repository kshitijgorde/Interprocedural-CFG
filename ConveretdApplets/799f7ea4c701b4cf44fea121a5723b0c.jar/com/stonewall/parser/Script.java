// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser;

import com.stonewall.parser.jdom.SAXHandler;
import org.jdom.Attribute;
import org.jdom.Text;
import org.jdom.Element;

class Script
{
    String text;
    Element parent;
    
    Script(final Text t) {
        this(t.getTextTrim(), t.getParentElement());
    }
    
    Script(final Attribute a) {
        this(a.getValue(), a.getParent());
    }
    
    Script(final String text, final Element parent) {
        this.text = text;
        this.parent = parent;
    }
    
    boolean valid() {
        return this.text != null && this.text.length() > 0;
    }
    
    boolean notValid() {
        return !this.valid();
    }
    
    String location() {
        return SAXHandler.location(this.parent);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\nLocation:\n\t");
        sb.append(this.location());
        sb.append("\nScript:\n");
        sb.append(this.text);
        sb.append("\n");
        return sb.toString();
    }
}
