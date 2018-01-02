// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel;

public class ModelListener implements IModelListener
{
    @Override
    public void notifyParent(final IModelObject modelObject, final IModelObject modelObject2, final IModelObject modelObject3) {
    }
    
    @Override
    public void notifyAddChild(final IModelObject modelObject, final IModelObject modelObject2, final int n) {
    }
    
    @Override
    public void notifyRemoveChild(final IModelObject modelObject, final IModelObject modelObject2, final int n) {
    }
    
    @Override
    public void notifyChange(final IModelObject modelObject, final String s, final Object o, final Object o2) {
    }
    
    @Override
    public void notifyClear(final IModelObject modelObject, final String s, final Object o) {
    }
    
    @Override
    public void notifyDirty(final IModelObject modelObject, final boolean b) {
        if (b) {
            modelObject.getChildren();
        }
    }
}
