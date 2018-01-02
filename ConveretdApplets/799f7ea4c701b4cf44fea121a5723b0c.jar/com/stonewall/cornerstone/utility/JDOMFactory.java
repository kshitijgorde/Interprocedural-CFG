// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import org.jdom.Namespace;
import org.jdom.Element;
import org.jdom.DefaultJDOMFactory;

class JDOMFactory extends DefaultJDOMFactory
{
    public Element element(final String name) {
        return new SmartElement(name);
    }
    
    public Element element(final String name, final Namespace ns) {
        return new SmartElement(name, ns);
    }
}
