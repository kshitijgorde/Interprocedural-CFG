// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel;

import org.xmodel.A.B;
import java.util.Set;
import java.util.List;
import java.util.Collection;

public interface IModelObject
{
    IModel getModel();
    
    void setID(final String p0);
    
    String getID();
    
    String getType();
    
    boolean isType(final String p0);
    
    boolean isDirty();
    
    Object setAttribute(final String p0);
    
    Object setAttribute(final String p0, final Object p1);
    
    Object getAttribute(final String p0);
    
    IModelObject getAttributeNode(final String p0);
    
    Collection<String> getAttributeNames();
    
    Object removeAttribute(final String p0);
    
    Object setValue(final Object p0);
    
    Object getValue();
    
    Object getChildValue(final String p0);
    
    void addChild(final IModelObject p0);
    
    void addChild(final IModelObject p0, final int p1);
    
    IModelObject removeChild(final int p0);
    
    void removeChild(final IModelObject p0);
    
    void removeChildren();
    
    void removeChildren(final String p0);
    
    void removeFromParent();
    
    IModelObject getChild(final int p0);
    
    IModelObject getFirstChild(final String p0);
    
    IModelObject getChild(final String p0, final String p1);
    
    IModelObject getCreateChild(final String p0);
    
    IModelObject getCreateChild(final String p0, final String p1);
    
    List<IModelObject> getChildren(final String p0, final String p1);
    
    List<IModelObject> getChildren();
    
    List<IModelObject> getChildren(final String p0);
    
    Set<String> getTypesOfChildren();
    
    int getNumberOfChildren();
    
    int getNumberOfChildren(final String p0);
    
    IModelObject internal_setParent(final IModelObject p0);
    
    void internal_addChild(final IModelObject p0, final int p1);
    
    IModelObject internal_removeChild(final int p0);
    
    void internal_notifyParent(final IModelObject p0, final IModelObject p1);
    
    void internal_notifyAddChild(final IModelObject p0, final int p1);
    
    void internal_notifyRemoveChild(final IModelObject p0, final int p1);
    
    IModelObject getParent();
    
    IModelObject getAncestor(final String p0);
    
    IModelObject getRoot();
    
    void addModelListener(final IModelListener p0);
    
    void removeModelListener(final IModelListener p0);
    
    F getModelListeners();
    
    C getPathListeners();
    
    void addAncestorListener(final IAncestorListener p0);
    
    void removeAncestorListener(final IAncestorListener p0);
    
    IModelObject cloneObject();
    
    IModelObject cloneTree();
    
    IModelObject createObject(final String p0);
    
    IModelObject getReferent();
    
    void revertUpdate(final B p0);
    
    void restoreUpdate(final B p0);
}
