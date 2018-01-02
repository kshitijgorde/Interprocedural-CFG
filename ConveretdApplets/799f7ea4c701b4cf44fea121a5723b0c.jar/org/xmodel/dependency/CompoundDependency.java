// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.dependency;

import java.util.ArrayList;
import java.util.List;

public class CompoundDependency implements IDependency
{
    List<IDependency> A;
    
    public CompoundDependency() {
        this.A = new ArrayList<IDependency>();
    }
    
    public void add(final IDependency dependency) {
        this.A.add(dependency);
    }
    
    public void remove(final IDependency dependency) {
        this.A.remove(dependency);
    }
    
    @Override
    public boolean evaluate(final Object o, final Object o2) {
        for (int i = 0; i < this.A.size(); ++i) {
            if (!this.A.get(i).evaluate(o, o2)) {
                return false;
            }
        }
        return true;
    }
}
