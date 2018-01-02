// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.dependency;

import java.util.Iterator;
import java.util.Stack;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

public class DependencySorter implements IDependencySorter
{
    List<IDependency> A;
    
    public DependencySorter() {
        this.A = new ArrayList<IDependency>(2);
    }
    
    @Override
    public void add(final IDependency dependency) {
        if (!this.A.contains(dependency)) {
            this.A.add(dependency);
        }
    }
    
    @Override
    public void remove(final IDependency dependency) {
        this.A.remove(dependency);
    }
    
    @Override
    public int count() {
        return this.A.size();
    }
    
    @Override
    public List<Object> sort(final Collection<Object> collection) {
        final ArrayList<Object> list = new ArrayList<Object>(collection);
        final ArrayList<Object> list2 = new ArrayList<Object>(collection.size());
        final Stack<Object> stack = new Stack<Object>();
        while (list.size() > 0) {
            stack.push(list.remove(0));
            while (!stack.empty()) {
                final Object peek = stack.peek();
                boolean b = false;
                final Iterator<Object> iterator = list.iterator();
                while (iterator.hasNext()) {
                    final Object next = iterator.next();
                    for (int i = 0; i < this.A.size(); ++i) {
                        if (this.A.get(i).evaluate(peek, next)) {
                            b = true;
                            stack.push(next);
                            iterator.remove();
                            break;
                        }
                    }
                }
                if (!b) {
                    stack.pop();
                    list2.add(peek);
                }
            }
        }
        return list2;
    }
}
