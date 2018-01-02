// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser;

import org.jdom.Element;

class Log extends SimpleAction
{
    Log(final Element root, final Context context) {
        super(root, context);
    }
    
    @Override
    void process() throws Exception {
        Log.log.info(this.root().getText());
    }
}
