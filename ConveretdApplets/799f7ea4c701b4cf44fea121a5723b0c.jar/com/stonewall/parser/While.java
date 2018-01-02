// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser;

import org.jdom.Element;

public class While extends Action
{
    boolean matched;
    static final int MaxRepeat = 100;
    
    While(final Element root, final Context context) {
        super(root, context);
        this.matched = true;
    }
    
    @Override
    boolean condition() {
        return this.matched = super.condition();
    }
    
    @Override
    int repeat() {
        return this.matched ? 100 : 0;
    }
}
