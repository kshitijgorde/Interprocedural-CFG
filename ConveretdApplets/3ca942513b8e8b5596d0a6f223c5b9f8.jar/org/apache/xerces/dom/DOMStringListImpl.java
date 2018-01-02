// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import java.util.Vector;
import org.w3c.dom.DOMStringList;

public class DOMStringListImpl implements DOMStringList
{
    private Vector fStrings;
    
    public DOMStringListImpl() {
        this.fStrings = new Vector();
    }
    
    public DOMStringListImpl(final Vector fStrings) {
        this.fStrings = fStrings;
    }
    
    public String item(final int n) {
        try {
            return this.fStrings.elementAt(n);
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            return null;
        }
    }
    
    public int getLength() {
        return this.fStrings.size();
    }
    
    public boolean contains(final String s) {
        return this.fStrings.contains(s);
    }
    
    public void add(final String s) {
        this.fStrings.add(s);
    }
}
