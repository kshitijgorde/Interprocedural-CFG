// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom.output;

import org.xml.sax.Locator;
import org.xml.sax.helpers.LocatorImpl;

public class JDOMLocator extends LocatorImpl
{
    private static final String CVS_ID = "@(#) $RCSfile: JDOMLocator.java,v $ $Revision: 1.4 $ $Date: 2007/11/10 05:29:01 $ $Name: jdom_1_1_1 $";
    private Object node;
    
    JDOMLocator() {
    }
    
    JDOMLocator(final Locator locator) {
        super(locator);
        if (locator instanceof JDOMLocator) {
            this.setNode(((JDOMLocator)locator).getNode());
        }
    }
    
    public Object getNode() {
        return this.node;
    }
    
    void setNode(final Object node) {
        this.node = node;
    }
}
