// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel;

import java.util.Set;
import java.util.HashMap;
import java.util.Map;

public class F implements IModelListener
{
    private static final IModelListener[] W;
    private Map<IModelListener, IModelListener> X;
    
    static {
        W = new IModelListener[0];
    }
    
    public void A(final IModelListener modelListener) {
        if (this.X == null) {
            this.X = new HashMap<IModelListener, IModelListener>();
        }
        this.X.put(modelListener, modelListener);
    }
    
    public void B(final IModelListener modelListener) {
        if (this.X != null) {
            this.X.remove(modelListener);
        }
    }
    
    public Set<IModelListener> F() {
        return this.X.keySet();
    }
    
    public int G() {
        return this.X.size();
    }
    
    @Override
    public void notifyParent(final IModelObject modelObject, final IModelObject modelObject2, final IModelObject modelObject3) {
        IModelListener[] array;
        for (int length = (array = this.X.keySet().toArray(F.W)).length, i = 0; i < length; ++i) {
            final IModelListener modelListener = array[i];
            try {
                modelListener.notifyParent(modelObject, modelObject2, modelObject3);
            }
            catch (Exception ex) {
                modelObject.getModel().handleException(ex);
                continue;
            }
            finally {
                modelObject.getModel().restore();
            }
            modelObject.getModel().restore();
        }
    }
    
    @Override
    public void notifyAddChild(final IModelObject modelObject, final IModelObject modelObject2, final int n) {
        IModelListener[] array;
        for (int length = (array = this.X.keySet().toArray(F.W)).length, i = 0; i < length; ++i) {
            final IModelListener modelListener = array[i];
            try {
                modelListener.notifyAddChild(modelObject, modelObject2, n);
            }
            catch (Exception ex) {
                modelObject.getModel().handleException(ex);
                continue;
            }
            finally {
                modelObject.getModel().restore();
            }
            modelObject.getModel().restore();
        }
    }
    
    @Override
    public void notifyRemoveChild(final IModelObject modelObject, final IModelObject modelObject2, final int n) {
        IModelListener[] array;
        for (int length = (array = this.X.keySet().toArray(F.W)).length, i = 0; i < length; ++i) {
            final IModelListener modelListener = array[i];
            try {
                modelListener.notifyRemoveChild(modelObject, modelObject2, n);
            }
            catch (Exception ex) {
                modelObject.getModel().handleException(ex);
                continue;
            }
            finally {
                modelObject.getModel().restore();
            }
            modelObject.getModel().restore();
        }
    }
    
    @Override
    public void notifyChange(final IModelObject modelObject, final String s, final Object o, final Object o2) {
        IModelListener[] array;
        for (int length = (array = this.X.keySet().toArray(F.W)).length, i = 0; i < length; ++i) {
            final IModelListener modelListener = array[i];
            try {
                modelListener.notifyChange(modelObject, s, o, o2);
            }
            catch (Exception ex) {
                modelObject.getModel().handleException(ex);
                continue;
            }
            finally {
                modelObject.getModel().restore();
            }
            modelObject.getModel().restore();
        }
    }
    
    @Override
    public void notifyClear(final IModelObject modelObject, final String s, final Object o) {
        IModelListener[] array;
        for (int length = (array = this.X.keySet().toArray(F.W)).length, i = 0; i < length; ++i) {
            final IModelListener modelListener = array[i];
            try {
                modelListener.notifyClear(modelObject, s, o);
            }
            catch (Exception ex) {
                modelObject.getModel().handleException(ex);
                continue;
            }
            finally {
                modelObject.getModel().restore();
            }
            modelObject.getModel().restore();
        }
    }
    
    @Override
    public void notifyDirty(final IModelObject modelObject, final boolean b) {
        IModelListener[] array;
        for (int length = (array = this.X.keySet().toArray(F.W)).length, i = 0; i < length; ++i) {
            final IModelListener modelListener = array[i];
            try {
                modelListener.notifyDirty(modelObject, b);
                if (modelObject.isDirty() != b) {
                    break;
                }
            }
            catch (Exception ex) {
                modelObject.getModel().handleException(ex);
                continue;
            }
            finally {
                modelObject.getModel().restore();
            }
            modelObject.getModel().restore();
        }
    }
}
