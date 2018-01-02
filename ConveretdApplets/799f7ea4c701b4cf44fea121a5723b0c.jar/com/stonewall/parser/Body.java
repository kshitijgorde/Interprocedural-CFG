// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser;

import org.jdom.Element;

class Body extends Action
{
    Body(final Element root, final Context context) {
        super(root, context);
    }
    
    @Override
    boolean condition() {
        return true;
    }
}
