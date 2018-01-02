// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serializer.dom3;

import java.util.Vector;
import org.w3c.dom.DOMStringList;

final class DOMStringListImpl implements DOMStringList
{
    private Vector fStrings;
    
    public DOMStringListImpl() {
        this.fStrings = new Vector();
    }
    
    public DOMStringListImpl(final Vector params) {
        this.fStrings = params;
    }
    
    public DOMStringListImpl(final String[] params) {
        this.fStrings = new Vector();
        if (params != null) {
            for (int i = 0; i < params.length; ++i) {
                this.fStrings.add(params[i]);
            }
        }
    }
    
    public String item(final int index) {
        try {
            return this.fStrings.elementAt(index);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
    
    public int getLength() {
        return this.fStrings.size();
    }
    
    public boolean contains(final String param) {
        return this.fStrings.contains(param);
    }
    
    public void add(final String param) {
        this.fStrings.add(param);
    }
}
