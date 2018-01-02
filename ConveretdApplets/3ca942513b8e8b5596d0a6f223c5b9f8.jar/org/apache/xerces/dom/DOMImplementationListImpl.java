// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.DOMImplementation;
import java.util.Vector;
import org.w3c.dom.DOMImplementationList;

public class DOMImplementationListImpl implements DOMImplementationList
{
    private Vector fImplementations;
    
    public DOMImplementationListImpl() {
        this.fImplementations = new Vector();
    }
    
    public DOMImplementationListImpl(final Vector fImplementations) {
        this.fImplementations = fImplementations;
    }
    
    public DOMImplementation item(final int n) {
        try {
            return this.fImplementations.elementAt(n);
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            return null;
        }
    }
    
    public int getLength() {
        return this.fImplementations.size();
    }
}
