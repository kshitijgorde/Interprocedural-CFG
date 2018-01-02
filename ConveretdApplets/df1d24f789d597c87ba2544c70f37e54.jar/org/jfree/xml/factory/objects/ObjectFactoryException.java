// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.factory.objects;

import org.jfree.util.StackableException;

public class ObjectFactoryException extends StackableException
{
    public ObjectFactoryException() {
    }
    
    public ObjectFactoryException(final String s) {
        super(s);
    }
    
    public ObjectFactoryException(final String s, final Exception ex) {
        super(s, ex);
    }
}
