// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import org.xmodel.F;
import org.xmodel.IModelListener;
import org.xmodel.IModelObject;
import org.xmodel.ModelListener;

public class B extends ModelListener
{
    private IExpression Z;
    private IContext Y;
    
    public B(final IExpression z, final IContext y) {
        this.Z = z;
        this.Y = y;
    }
    
    public IContext H() {
        return this.Y;
    }
    
    public static B A(final IModelObject modelObject, final IExpression expression, final IContext context) {
        final F modelListeners = modelObject.getModelListeners();
        if (modelListeners == null) {
            return null;
        }
        for (final IModelListener modelListener : modelListeners.F()) {
            if (modelListener instanceof B) {
                final B b = (B)modelListener;
                if (b.Z.equals(expression) && b.Y.equals(context)) {
                    return b;
                }
                continue;
            }
        }
        return null;
    }
    
    public static List<B> A(final IModelObject modelObject, final IExpression expression) {
        final ArrayList<B> list = new ArrayList<B>();
        final F modelListeners = modelObject.getModelListeners();
        if (modelListeners == null) {
            return list;
        }
        for (final IModelListener modelListener : modelListeners.F()) {
            if (modelListener instanceof B) {
                final B b = (B)modelListener;
                if (!b.Z.equals(expression)) {
                    continue;
                }
                list.add(b);
            }
        }
        return list;
    }
    
    @Override
    public void notifyChange(final IModelObject modelObject, final String s, final Object o, final Object o2) {
        if (s.length() > 0) {
            return;
        }
        this.Z.notifyValue(this.Z, new IContext[] { this.Y }, modelObject, o, o2);
    }
    
    @Override
    public void notifyClear(final IModelObject modelObject, final String s, final Object o) {
        if (s.length() > 0) {
            return;
        }
        if (o != null) {
            this.Z.notifyValue(this.Z, new IContext[] { this.Y }, modelObject, "", o.toString());
        }
    }
    
    @Override
    public void notifyDirty(final IModelObject modelObject, final boolean b) {
        if (b) {
            modelObject.getValue();
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof B) {
            final B b = (B)o;
            return this == b || (b.Z == this.Z && b.Y == this.Y);
        }
        return super.equals(o);
    }
    
    @Override
    public int hashCode() {
        return System.identityHashCode(this.Z) + System.identityHashCode(this.Y);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        sb.append(this.Z);
        sb.append("}, ");
        sb.append(this.Y);
        return sb.toString();
    }
}
