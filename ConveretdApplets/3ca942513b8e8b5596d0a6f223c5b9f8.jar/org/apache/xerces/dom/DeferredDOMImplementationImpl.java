// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.DOMImplementation;

public class DeferredDOMImplementationImpl extends DOMImplementationImpl
{
    static DeferredDOMImplementationImpl singleton;
    
    public static DOMImplementation getDOMImplementation() {
        return DeferredDOMImplementationImpl.singleton;
    }
    
    static {
        DeferredDOMImplementationImpl.singleton = new DeferredDOMImplementationImpl();
    }
}
