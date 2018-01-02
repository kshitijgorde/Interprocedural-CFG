// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel;

import org.xmodel.xpath.expression.IContext;
import java.util.ArrayList;
import org.xmodel.B.A;
import java.util.List;

public class C
{
    List<A> A;
    
    public C() {
        this.A = new ArrayList<A>(1);
    }
    
    public void A(final A a) {
        this.A.add(a);
    }
    
    public A A(final IContext context, final IPathListener pathListener) {
        for (int i = 0; i < this.A.size(); ++i) {
            final A a = this.A.get(i);
            if (a.B() == context || a.B().equals(context)) {
                if (a.A() == pathListener) {
                    return a;
                }
            }
        }
        return null;
    }
    
    public A B(final IContext context, final IPathListener pathListener) {
        for (int i = 0; i < this.A.size(); ++i) {
            final A a = this.A.get(i);
            if (a.B() == context || a.B().equals(context)) {
                if (a.A() == pathListener) {
                    return this.A.remove(i);
                }
            }
        }
        return null;
    }
}
