// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath;

import org.xmodel.ModelListener;
import org.xmodel.A.B;
import java.util.Set;
import java.util.Iterator;
import org.xmodel.F;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import org.xmodel.IPathListener;
import org.xmodel.IPath;
import org.xmodel.IModelListener;
import org.xmodel.IAncestorListener;
import org.xmodel.IModel;
import org.xmodel.C;
import org.xmodel.IModelObject;

public class TextNode implements IModelObject
{
    IModelObject D;
    C C;
    
    public TextNode(final IModelObject d) {
        this.D = d;
    }
    
    @Override
    public IModel getModel() {
        return this.D.getModel();
    }
    
    @Override
    public String getID() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void setID(final String s) {
    }
    
    @Override
    public void addAncestorListener(final IAncestorListener ancestorListener) {
    }
    
    @Override
    public void addChild(final IModelObject modelObject) {
    }
    
    @Override
    public void addChild(final IModelObject modelObject, final int n) {
    }
    
    @Override
    public IModelObject getChild(final int n) {
        return null;
    }
    
    @Override
    public IModelObject removeChild(final int n) {
        return null;
    }
    
    @Override
    public void addModelListener(final IModelListener modelListener) {
        this.D.addModelListener(new _A(this, modelListener));
    }
    
    public void addPathListener(final IPath path, final IPathListener pathListener) {
    }
    
    @Override
    public IModelObject cloneObject() {
        return new TextNode(this.D);
    }
    
    @Override
    public IModelObject cloneTree() {
        return this.cloneObject();
    }
    
    @Override
    public IModelObject createObject(final String s) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public IModelObject getReferent() {
        return this;
    }
    
    @Override
    public IModelObject getAncestor(final String s) {
        return null;
    }
    
    @Override
    public Object getAttribute(final String s) {
        return s.equals("") ? this.D.getValue() : null;
    }
    
    @Override
    public IModelObject getAttributeNode(final String s) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Collection<String> getAttributeNames() {
        final ArrayList<String> list = new ArrayList<String>(1);
        list.add("");
        return list;
    }
    
    @Override
    public IModelObject getChild(final String s, final String s2) {
        return null;
    }
    
    @Override
    public List<IModelObject> getChildren() {
        return Collections.emptyList();
    }
    
    @Override
    public List<IModelObject> getChildren(final String s, final String s2) {
        return Collections.emptyList();
    }
    
    @Override
    public List<IModelObject> getChildren(final String s) {
        return Collections.emptyList();
    }
    
    @Override
    public Object getValue() {
        return this.D.getValue();
    }
    
    @Override
    public Object getChildValue(final String s) {
        return null;
    }
    
    @Override
    public IModelObject getCreateChild(final String s, final String s2) {
        return null;
    }
    
    @Override
    public IModelObject getCreateChild(final String s) {
        return null;
    }
    
    @Override
    public IModelObject getFirstChild(final String s) {
        return null;
    }
    
    @Override
    public F getModelListeners() {
        final F modelListeners = this.D.getModelListeners();
        if (modelListeners == null) {
            return null;
        }
        F f = null;
        for (final IModelListener modelListener : modelListeners.F()) {
            if (modelListener instanceof _A) {
                final _A a = (_A)modelListener;
                if (!a.b.equals(this)) {
                    continue;
                }
                if (f == null) {
                    f = new F();
                }
                f.A(a.a);
            }
        }
        return f;
    }
    
    @Override
    public C getPathListeners() {
        if (this.C == null) {
            this.C = new C();
        }
        return this.C;
    }
    
    @Override
    public int getNumberOfChildren() {
        return 0;
    }
    
    @Override
    public int getNumberOfChildren(final String s) {
        return 0;
    }
    
    @Override
    public IModelObject getParent() {
        return this.D;
    }
    
    @Override
    public IModelObject getRoot() {
        return null;
    }
    
    @Override
    public String getType() {
        return "text()";
    }
    
    @Override
    public Set<String> getTypesOfChildren() {
        return Collections.emptySet();
    }
    
    @Override
    public IModelObject internal_setParent(final IModelObject modelObject) {
        return null;
    }
    
    @Override
    public void internal_notifyParent(final IModelObject modelObject, final IModelObject modelObject2) {
    }
    
    @Override
    public void internal_notifyAddChild(final IModelObject modelObject, final int n) {
    }
    
    @Override
    public void internal_notifyRemoveChild(final IModelObject modelObject, final int n) {
    }
    
    @Override
    public void internal_addChild(final IModelObject modelObject, final int n) {
    }
    
    @Override
    public IModelObject internal_removeChild(final int n) {
        return null;
    }
    
    @Override
    public boolean isDirty() {
        return false;
    }
    
    @Override
    public boolean isType(final String s) {
        return s.equals("text()");
    }
    
    @Override
    public void removeAncestorListener(final IAncestorListener ancestorListener) {
    }
    
    @Override
    public Object removeAttribute(final String s) {
        if (s.length() == 0) {
            return this.D.removeAttribute(s);
        }
        return null;
    }
    
    @Override
    public void removeChild(final IModelObject modelObject) {
    }
    
    @Override
    public void removeChildren() {
    }
    
    @Override
    public void removeChildren(final String s) {
    }
    
    @Override
    public void removeFromParent() {
        this.D.removeAttribute("");
    }
    
    @Override
    public void removeModelListener(final IModelListener modelListener) {
        this.D.removeModelListener(new _A(this, modelListener));
    }
    
    public void removePathListener(final IPath path, final IPathListener pathListener) {
    }
    
    @Override
    public Object setAttribute(final String s, final Object o) {
        if (s.length() == 0) {
            return this.D.setAttribute(s, o);
        }
        return null;
    }
    
    @Override
    public Object setAttribute(final String attribute) {
        if (attribute.length() == 0) {
            return this.D.setAttribute(attribute);
        }
        return null;
    }
    
    @Override
    public Object setValue(final Object value) {
        return this.D.setValue(value);
    }
    
    @Override
    public void revertUpdate(final B b) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void restoreUpdate(final B b) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof TextNode) {
            return this.D.equals(((TextNode)o).D);
        }
        return super.equals(o);
    }
    
    @Override
    public int hashCode() {
        return this.D.hashCode() + 1;
    }
    
    @Override
    public String toString() {
        final Object value = this.D.getValue();
        return (value == null) ? "" : value.toString();
    }
    
    private class _A extends ModelListener
    {
        TextNode b;
        IModelListener a;
        
        public _A(final TextNode b, final IModelListener a) {
            this.b = b;
            this.a = a;
        }
        
        @Override
        public void notifyChange(final IModelObject modelObject, final String s, final Object o, final Object o2) {
            if (s.length() == 0) {
                this.a.notifyChange(this.b, "", o, o2);
            }
        }
        
        @Override
        public void notifyClear(final IModelObject modelObject, final String s, final Object o) {
            if (s.length() == 0) {
                this.a.notifyClear(this.b, "", o);
            }
        }
        
        @Override
        public void notifyDirty(final IModelObject modelObject, final boolean b) {
            if (b) {
                modelObject.getValue();
            }
        }
        
        @Override
        public int hashCode() {
            return this.b.hashCode() + this.a.hashCode();
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o instanceof _A) {
                final _A a = (_A)o;
                return this.b.equals(a.b) && this.a.equals(a.a);
            }
            return super.equals(o);
        }
    }
}
