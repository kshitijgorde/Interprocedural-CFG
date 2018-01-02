// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.B;

import java.util.Iterator;
import java.util.Collection;
import java.util.HashMap;
import org.xmodel.IModelObject;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.List;

public class D
{
    List<L> A;
    Pattern D;
    boolean B;
    boolean C;
    
    public D() {
        this.A = new ArrayList<L>();
        this.C = true;
    }
    
    public void B(final boolean b) {
        this.B = b;
    }
    
    public void A(final String s) {
        this.D = Pattern.compile(s);
    }
    
    public void A(final boolean c) {
        this.C = c;
    }
    
    public boolean B() {
        return this.C;
    }
    
    public List<IModelObject> A() {
        final HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
        for (final L l : this.A) {
            if (!l.B()) {
                continue;
            }
            if (l.A() == L._A.C) {
                for (final IModelObject modelObject : l.D()) {
                    hashMap.put(modelObject, modelObject);
                }
            }
            else {
                final Iterator<IModelObject> iterator3 = l.D().iterator();
                while (iterator3.hasNext()) {
                    hashMap.remove(iterator3.next());
                }
            }
        }
        return new ArrayList<IModelObject>(hashMap.keySet());
    }
    
    void A(final L l) {
        this.A.add(l);
        if (this.B) {
            System.out.println((this.D != null) ? l.A(this.D) : l.toString());
        }
    }
}
