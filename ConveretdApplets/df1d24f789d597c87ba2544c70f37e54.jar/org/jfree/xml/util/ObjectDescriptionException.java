// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.util;

import org.jfree.util.StackableException;

public class ObjectDescriptionException extends StackableException
{
    public ObjectDescriptionException() {
    }
    
    public ObjectDescriptionException(final String s, final Exception ex) {
        super(s, ex);
    }
    
    public ObjectDescriptionException(final String s) {
        super(s);
    }
}
