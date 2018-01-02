// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.diff;

import org.xmodel.ModelAlgorithms;
import java.util.List;
import org.xmodel.IModelObject;
import org.xmodel.IChangeSet;
import org.xmodel.IModelObjectFactory;

public class ShallowDiffer extends AbstractListDiffer
{
    IModelObjectFactory B;
    IXmlMatcher E;
    IChangeSet C;
    IModelObject A;
    IModelObject D;
    
    public ShallowDiffer() {
        this.E = new DefaultXmlMatcher();
    }
    
    public void setMatcher(final IXmlMatcher e) {
        this.E = e;
    }
    
    public void setFactory(final IModelObjectFactory b) {
        this.B = b;
    }
    
    public void diff(final IModelObject a, final IModelObject d, final IChangeSet c) {
        this.C = c;
        this.A = a;
        this.D = d;
        this.diff(a.getChildren(), d.getChildren());
    }
    
    @Override
    public boolean isMatch(final Object o, final Object o2) {
        return this.E.isMatch((IModelObject)o, (IModelObject)o2);
    }
    
    private IModelObject A(final IModelObject modelObject) {
        if (this.B != null) {
            return ModelAlgorithms.cloneTree(modelObject, this.B);
        }
        return modelObject;
    }
    
    @Override
    public void notifyEqual(final List list, final int n, final int n2, final List list2, final int n3, final int n4) {
    }
    
    @Override
    public void notifyInsert(final List list, final int n, final int n2, final List list2, final int n3, final int n4) {
        for (int i = 0; i < n4; ++i) {
            this.C.addChild(this.A, this.A(this.D.getChild(n3 + i)), n3 + i);
        }
    }
    
    @Override
    public void notifyRemove(final List list, final int n, final int n2, final List list2, final int n3) {
        for (int i = 0; i < n3; ++i) {
            this.C.removeChild(this.A, this.A.getChild(n + i), n + n2);
        }
    }
}
