// 
// Decompiled by Procyon v0.5.30
// 

package org.bouncycastle.asn1;

import java.util.Vector;

public class DEREncodableVector
{
    Vector v;
    
    public DEREncodableVector() {
        this.v = new Vector();
    }
    
    public void add(final DEREncodable derEncodable) {
        this.v.addElement(derEncodable);
    }
    
    public DEREncodable get(final int n) {
        return this.v.elementAt(n);
    }
    
    public int size() {
        return this.v.size();
    }
}
