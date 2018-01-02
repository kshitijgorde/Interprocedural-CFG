// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser;

import org.jdom.Element;

class Arg
{
    final Element root;
    final String name;
    final String defaultValue;
    final boolean required;
    
    Arg(final Element root) {
        this.root = root;
        this.name = root.getAttributeValue("name");
        this.defaultValue = root.getAttributeValue("default");
        this.required = Boolean.valueOf(root.getAttributeValue("default", "true"));
    }
    
    Arg updated(final Context context) {
        context.scriptHandler().process(this.root, "body");
        return new Arg(this.root);
    }
}
