// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser;

import org.jdom.Element;

class Pop extends Action
{
    Pop(final Element root, final Context context) {
        super(root, context);
    }
    
    @Override
    boolean apply() {
        this.pop();
        this.processContent();
        return true;
    }
    
    private void pop() {
        final ParserState state = this.context().parserState();
        String current = state.current();
        String msg = String.valueOf(this.location()) + " state {" + current + "} popped";
        current = state.pop();
        msg = String.valueOf(msg) + ", new state {" + current + "}";
        Pop.log.debug(msg);
    }
}
