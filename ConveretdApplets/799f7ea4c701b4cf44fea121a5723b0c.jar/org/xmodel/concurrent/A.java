// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.concurrent;

import org.xmodel.ModelAlgorithms;
import org.xmodel.IPath;
import org.xmodel.record.ClearAttributeRecord;
import org.xmodel.record.ChangeAttributeRecord;
import org.xmodel.record.RemoveChildRecord;
import org.xmodel.IChangeRecord;
import org.xmodel.record.AddChildRecord;
import org.xmodel.IModelObject;
import org.xmodel.ModelListener;

class A extends ModelListener
{
    private IModelObject f;
    private QueueMirrorSet g;
    
    public A(final IModelObject f, final QueueMirrorSet g) {
        this.f = f;
        this.g = g;
    }
    
    @Override
    public void notifyParent(final IModelObject modelObject, final IModelObject modelObject2, final IModelObject modelObject3) {
    }
    
    @Override
    public void notifyAddChild(final IModelObject modelObject, final IModelObject modelObject2, final int n) {
        this.g.A(new AddChildRecord(this.E(modelObject), modelObject2, n));
    }
    
    @Override
    public void notifyRemoveChild(final IModelObject modelObject, final IModelObject modelObject2, final int n) {
        this.g.A(new RemoveChildRecord(this.E(modelObject), n));
    }
    
    @Override
    public void notifyChange(final IModelObject modelObject, final String s, final Object o, final Object o2) {
        this.g.A(new ChangeAttributeRecord(this.E(modelObject), s, o));
    }
    
    @Override
    public void notifyClear(final IModelObject modelObject, final String s, final Object o) {
        this.g.A(new ClearAttributeRecord(this.E(modelObject), s));
    }
    
    @Override
    public void notifyDirty(final IModelObject modelObject, final boolean b) {
    }
    
    protected IPath E(final IModelObject modelObject) {
        return ModelAlgorithms.createRelativePath(this.f, modelObject);
    }
}
