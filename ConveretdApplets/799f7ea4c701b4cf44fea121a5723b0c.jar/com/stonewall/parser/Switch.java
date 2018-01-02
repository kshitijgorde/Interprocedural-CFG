// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser;

import java.util.Iterator;
import org.jdom.Element;

public class Switch extends Action
{
    boolean caseMatched;
    String switchOn;
    
    Switch(final Element root, final Context context) {
        super(root, context);
        this.caseMatched = false;
        this.validateChildren();
    }
    
    void validateChildren() {
        for (final Element child : this.root().getChildren()) {
            final String n = child.getName();
            if (!n.equals("pcase")) {
                Switch.log.error("swtich contains invalid child: " + n + " @ " + this.location());
            }
        }
    }
    
    @Override
    boolean condition() {
        return true;
    }
    
    @Override
    boolean apply() {
        this.caseMatched = false;
        this.switchOn = this.root().getAttributeValue("on");
        return super.apply();
    }
    
    @Override
    boolean lastChild(final Action a) {
        return this.caseMatched;
    }
}
