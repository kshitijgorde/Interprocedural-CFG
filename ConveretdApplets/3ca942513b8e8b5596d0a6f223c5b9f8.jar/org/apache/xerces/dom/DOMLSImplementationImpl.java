// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.apache.xml.serialize.DOMSerializerImpl;
import org.w3c.dom.ls.LSSerializer;
import org.w3c.dom.DOMImplementation;

public class DOMLSImplementationImpl extends DOMImplementationImpl implements DOMImplementation
{
    static DOMLSImplementationImpl singleton;
    
    public static DOMImplementation getDOMImplementation() {
        return DOMLSImplementationImpl.singleton;
    }
    
    public LSSerializer createLSSerializer() {
        return new DOMSerializerImpl();
    }
    
    static {
        DOMLSImplementationImpl.singleton = new DOMLSImplementationImpl();
    }
}
