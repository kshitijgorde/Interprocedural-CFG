// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom;

import java.util.NoSuchElementException;
import org.jdom.filter.Filter;
import java.util.Iterator;

class FilterIterator implements Iterator
{
    private Iterator iterator;
    private Filter filter;
    private Object nextObject;
    private static final String CVS_ID = "@(#) $RCSfile: FilterIterator.java,v $ $Revision: 1.6 $ $Date: 2007/11/10 05:28:59 $ $Name: jdom_1_1_1 $";
    
    public FilterIterator(final Iterator iterator, final Filter filter) {
        if (iterator == null || filter == null) {
            throw new IllegalArgumentException("null parameter");
        }
        this.iterator = iterator;
        this.filter = filter;
    }
    
    public boolean hasNext() {
        if (this.nextObject != null) {
            return true;
        }
        while (this.iterator.hasNext()) {
            final Object obj = this.iterator.next();
            if (this.filter.matches(obj)) {
                this.nextObject = obj;
                return true;
            }
        }
        return false;
    }
    
    public Object next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        final Object obj = this.nextObject;
        this.nextObject = null;
        return obj;
    }
    
    public void remove() {
        this.iterator.remove();
    }
}
