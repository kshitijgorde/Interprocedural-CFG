// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import org.jdom.input.SAXHandler;

public class SAXBuilder extends org.jdom.input.SAXBuilder
{
    protected SAXHandler createContentHandler() {
        return new com.stonewall.cornerstone.utility.SAXHandler();
    }
}
