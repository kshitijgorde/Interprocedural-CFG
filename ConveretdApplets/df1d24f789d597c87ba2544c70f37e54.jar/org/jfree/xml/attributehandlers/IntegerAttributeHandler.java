// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.attributehandlers;

import org.jfree.util.Log;

public class IntegerAttributeHandler implements AttributeHandler
{
    public String toAttributeValue(final Object o) {
        try {
            return ((Integer)o).toString();
        }
        catch (ClassCastException ex) {
            if (o != null) {
                Log.debug("ClassCastException: Expected Integer, found " + o.getClass());
            }
            throw ex;
        }
    }
    
    public Object toPropertyValue(final String s) {
        return new Integer(s);
    }
}
