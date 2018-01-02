// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser;

import org.jdom.Element;

class Case extends Action
{
    private final Switch parent;
    
    Case(final Element root, final Context context, final Action parent) {
        super(root, context);
        this.parent = (Switch)parent;
    }
    
    @Override
    void processAttributes() {
        if (this.hasTest()) {
            this.root().setAttribute(Condition.A.text.name(), this.parent.switchOn);
        }
        super.processAttributes();
    }
    
    @Override
    boolean condition() {
        return this.parent.caseMatched = super.condition();
    }
    
    boolean hasTest() {
        if (this.parent.switchOn == null) {
            return false;
        }
        Condition.B[] values;
        for (int length = (values = Condition.B.values()).length, i = 0; i < length; ++i) {
            final Condition.B b = values[i];
            final String v = this.root().getAttributeValue(b.name());
            if (v != null) {
                return true;
            }
        }
        return false;
    }
}
