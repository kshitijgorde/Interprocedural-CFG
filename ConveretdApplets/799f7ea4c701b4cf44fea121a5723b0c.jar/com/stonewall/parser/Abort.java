// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser;

import org.jdom.Element;

public class Abort extends Action
{
    Abort(final Element root, final Context context) {
        super(root, context);
        this.stripChildren();
    }
    
    @Override
    boolean apply() {
        Abort.log.debug(this.location());
        return false;
    }
    
    private void stripChildren() {
        if (this.root().getChildren().size() > 0) {
            Abort.log.warn("action: " + this.id() + " may not have content, stripped");
            this.root().removeContent();
        }
    }
}
