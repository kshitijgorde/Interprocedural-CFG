// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv;

import java.util.Hashtable;

public abstract class DTDDVFactory
{
    private static final String DEFAULT_FACTORY_CLASS = "org.apache.xerces.impl.dv.dtd.DTDDVFactoryImpl";
    
    public static final synchronized DTDDVFactory getInstance() throws DVFactoryException {
        return getInstance("org.apache.xerces.impl.dv.dtd.DTDDVFactoryImpl");
    }
    
    public static final synchronized DTDDVFactory getInstance(final String s) throws DVFactoryException {
        try {
            return (DTDDVFactory)ObjectFactory.newInstance(s, ObjectFactory.findClassLoader(), true);
        }
        catch (ClassCastException ex) {
            throw new DVFactoryException("DTD factory class " + s + " does not extend from DTDDVFactory.");
        }
    }
    
    public abstract DatatypeValidator getBuiltInDV(final String p0);
    
    public abstract Hashtable getBuiltInTypes();
}
