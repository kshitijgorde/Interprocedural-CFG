// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.util;

import java.util.NoSuchElementException;
import java.util.Enumeration;

public class EmptyEnumeration implements Enumeration
{
    public static final EmptyEnumeration EMPTY_ENUMERATION;
    
    public boolean hasMoreElements() {
        return false;
    }
    
    public Object nextElement() {
        throw new NoSuchElementException();
    }
    
    public static EmptyEnumeration getInstance() {
        return EmptyEnumeration.EMPTY_ENUMERATION;
    }
    
    static {
        EMPTY_ENUMERATION = new EmptyEnumeration();
    }
}
