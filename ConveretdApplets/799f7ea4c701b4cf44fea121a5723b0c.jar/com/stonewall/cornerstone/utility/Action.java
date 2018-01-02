// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import org.jdom.Element;

class Action
{
    final Element root;
    
    static Action newInstance(final Element root) {
        Action result = null;
        final String type = (root == null) ? Type.none.toString() : root.getAttributeValue("type");
        switch (Type.valueOf(type)) {
            case end: {
                result = new EndAction(root);
                break;
            }
            case none: {
                result = new Action(root);
                break;
            }
        }
        return result;
    }
    
    void perform(final Emulation em, final String input) {
    }
    
    Action(final Element root) {
        this.root = root;
    }
    
    enum Type
    {
        none("none", 0), 
        end("end", 1);
        
        private Type(final String s, final int n) {
        }
    }
}
