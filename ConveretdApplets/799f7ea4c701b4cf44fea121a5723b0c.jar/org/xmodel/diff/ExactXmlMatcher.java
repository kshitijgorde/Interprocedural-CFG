// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.diff;

import java.util.List;
import org.xmodel.IChangeSet;
import org.xmodel.IModelObject;

public class ExactXmlMatcher implements IXmlMatcher
{
    @Override
    public void startDiff(final IModelObject modelObject, final IModelObject modelObject2, final IChangeSet set) {
    }
    
    @Override
    public void endDiff(final IModelObject modelObject, final IModelObject modelObject2, final IChangeSet set) {
    }
    
    @Override
    public void enterDiff(final IModelObject modelObject, final IModelObject modelObject2, final IChangeSet set) {
    }
    
    @Override
    public void exitDiff(final IModelObject modelObject, final IModelObject modelObject2, final IChangeSet set) {
    }
    
    @Override
    public int findMatch(final List<IModelObject> list, final IModelObject modelObject) {
        throw new IllegalStateException();
    }
    
    @Override
    public boolean isList(final IModelObject modelObject) {
        return true;
    }
    
    @Override
    public boolean isMatch(final IModelObject modelObject, final IModelObject modelObject2) {
        if (!modelObject.getType().equals(modelObject2.getType())) {
            return false;
        }
        final Object value = modelObject.getValue();
        final Object value2 = modelObject2.getValue();
        if (value == null || value2 == null) {
            return value == value2;
        }
        return value.equals(value2);
    }
    
    @Override
    public boolean shouldDiff(final IModelObject modelObject, final String s, final boolean b) {
        return true;
    }
    
    @Override
    public boolean shouldDiff(final IModelObject modelObject, final boolean b) {
        return true;
    }
}
