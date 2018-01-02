// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser;

import org.jdom.Element;

class Push extends Action
{
    Push(final Element root, final Context context) {
        super(root, context);
    }
    
    @Override
    boolean apply() {
        this.push();
        this.processContent();
        return true;
    }
    
    private void push() {
        final String next = this.root().getAttributeValue("state");
        if (next == null) {
            Push.log.error(String.valueOf(this.id()) + " required attribute (state), not-found");
            return;
        }
        final ParserState state = this.context().parserState();
        if (state.domainContains(next)) {
            state.push(next);
            Push.log.debug(String.valueOf(this.location()) + " state {" + next + "} pushed");
        }
        else {
            Push.log.error(String.valueOf(this.location()) + " state {" + next + "} has no matching rules, push-ignored");
        }
    }
}
