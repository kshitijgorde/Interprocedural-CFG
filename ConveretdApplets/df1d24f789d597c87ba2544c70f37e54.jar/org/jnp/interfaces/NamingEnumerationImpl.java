// 
// Decompiled by Procyon v0.5.30
// 

package org.jnp.interfaces;

import java.util.Collection;
import java.util.Iterator;
import javax.naming.NamingEnumeration;

public class NamingEnumerationImpl implements NamingEnumeration
{
    Iterator iter;
    
    NamingEnumerationImpl(final Collection list) {
        this.iter = list.iterator();
    }
    
    public boolean hasMoreElements() {
        return this.iter.hasNext();
    }
    
    public Object nextElement() {
        return this.iter.next();
    }
    
    public boolean hasMore() {
        return this.iter.hasNext();
    }
    
    public Object next() {
        return this.iter.next();
    }
    
    public void close() {
        this.iter = null;
    }
}
