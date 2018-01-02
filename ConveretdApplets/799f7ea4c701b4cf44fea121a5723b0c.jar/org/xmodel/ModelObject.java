// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel;

import org.xmodel.xml.XmlIO;
import org.xmodel.A.A;
import org.xmodel.A.H;
import org.xmodel.A.D;
import org.xmodel.A.G;
import org.xmodel.A.E;
import org.xmodel.A.B;
import org.xmodel.listeners.ClimbingListener;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.util.Collection;
import org.xmodel.xpath.AttributeNode;
import org.xmodel.log.Log;
import java.util.Map;
import java.util.List;

public class ModelObject implements IModelObject
{
    private IModel J;
    private String O;
    private IModelObject N;
    private List<IModelObject> K;
    private Map<String, Object> I;
    private F M;
    private C H;
    private static Log L;
    
    static {
        ModelObject.L = Log.getLog("org.xmodel");
    }
    
    public ModelObject(final String s) {
        this.O = s.intern();
    }
    
    public ModelObject(final String s, final String s2) {
        this(s);
        this.setAttributeImpl("id", s2);
    }
    
    @Override
    public IModel getModel() {
        if (this.J == null) {
            this.J = ModelRegistry.getInstance().getModel();
        }
        return this.J;
    }
    
    @Override
    public void setID(final String s) {
        this.writeAttributeAccess("id");
        this.setAttribute("id", s);
    }
    
    @Override
    public String getID() {
        this.readAttributeAccess("id");
        return Xlate.get((IModelObject)this, "id", "");
    }
    
    @Override
    public String getType() {
        return this.O;
    }
    
    @Override
    public boolean isType(final String s) {
        return this.O.equals(s);
    }
    
    @Override
    public boolean isDirty() {
        return false;
    }
    
    @Override
    public Object setAttribute(final String s) {
        return this.setAttribute(s, "");
    }
    
    @Override
    public Object setAttribute(final String s, final Object o) {
        this.writeAttributeAccess(s);
        final Object attribute = this.getAttribute(s);
        if (attribute != null && o != null && attribute.equals(o)) {
            return attribute;
        }
        if (this.N != null && s.equals("id")) {
            ModelObject.L.warnf("LATE-ID: %s, id=%s", this, o);
        }
        if (o == null) {
            this.removeAttribute(s);
            return attribute;
        }
        final IModel model = this.getModel();
        final IChangeSet locked = model.isLocked(this);
        if (locked != null) {
            locked.setAttribute(this, s, o);
            return attribute;
        }
        model.startUpdate().A(this, s, o, attribute);
        this.setAttributeImpl(s, o);
        model.lock(this);
        this.A(s, o, attribute);
        model.unlock(this);
        model.endUpdate();
        return attribute;
    }
    
    @Override
    public Object getAttribute(final String s) {
        this.readAttributeAccess(s);
        if (this.I == null) {
            return null;
        }
        return this.I.get(s);
    }
    
    @Override
    public IModelObject getAttributeNode(final String s) {
        if (this.I != null && !this.I.containsKey(s)) {
            return null;
        }
        return new AttributeNode(s, this);
    }
    
    @Override
    public Collection<String> getAttributeNames() {
        this.readAttributeAccess(null);
        if (this.I == null) {
            return (Collection<String>)Collections.emptyList();
        }
        return this.I.keySet();
    }
    
    @Override
    public Object removeAttribute(final String s) {
        this.writeAttributeAccess(s);
        final Object attribute = this.getAttribute(s);
        if (attribute == null) {
            return null;
        }
        final IModel model = this.getModel();
        final IChangeSet locked = model.isLocked(this);
        if (locked != null) {
            locked.removeAttribute(this, s);
            return attribute;
        }
        model.startUpdate().A(this, s, attribute);
        this.removeAttributeImpl(s);
        model.lock(this);
        this.A(s, attribute);
        model.unlock(this);
        model.endUpdate();
        return attribute;
    }
    
    @Override
    public Object setValue(final Object o) {
        return this.setAttribute("", o);
    }
    
    @Override
    public Object getValue() {
        return this.getAttribute("");
    }
    
    @Override
    public Object getChildValue(final String s) {
        final IModelObject firstChild = this.getFirstChild(s);
        if (firstChild != null) {
            return firstChild.getValue();
        }
        return null;
    }
    
    protected Object setAttributeImpl(final String s, final Object o) {
        if (this.I == null) {
            this.I = new HashMap<String, Object>();
        }
        return this.I.put(s.intern(), o);
    }
    
    protected Object removeAttributeImpl(final String s) {
        if (this.I == null) {
            return null;
        }
        return this.I.remove(s);
    }
    
    @Override
    public void addChild(final IModelObject modelObject) {
        this.addChild(modelObject, (this.K != null) ? this.K.size() : 0);
    }
    
    @Override
    public void addChild(final IModelObject modelObject, int n) {
        if (modelObject == this) {
            throw new IllegalArgumentException();
        }
        final IModel model = this.getModel();
        final IChangeSet locked = this.getModel().isLocked(this);
        if (locked != null) {
            locked.addChild(this, modelObject, n);
        }
        else {
            this.writeChildrenAccess();
            final IModelObject parent = modelObject.getParent();
            if (parent == this) {
                final int index = this.getChildren().indexOf(modelObject);
                model.startUpdate().A(this, modelObject, index, n);
                if (index < n) {
                    --n;
                }
                this.removeChildImpl(index);
                this.addChildImpl(modelObject, n);
                model.lock(this);
                model.lock(modelObject);
                this.B(modelObject, index);
                this.A(modelObject, n);
                model.unlock(this);
                model.unlock(modelObject);
                model.endUpdate();
            }
            else {
                model.startUpdate().B(this, modelObject, n);
                modelObject.internal_setParent(this);
                int index2 = -1;
                if (parent != null) {
                    index2 = parent.getChildren().indexOf(modelObject);
                    parent.internal_removeChild(index2);
                }
                this.addChildImpl(modelObject, n);
                model.lock(this);
                model.lock(parent);
                model.lock(modelObject);
                modelObject.internal_notifyParent(this, parent);
                if (parent != null) {
                    parent.internal_notifyRemoveChild(modelObject, index2);
                }
                this.A(modelObject, n);
                model.unlock(this);
                model.unlock(parent);
                model.unlock(modelObject);
                model.endUpdate();
            }
        }
    }
    
    protected void addChildImpl(final IModelObject modelObject, final int n) {
        if (this.K == null) {
            this.K = new ArrayList<IModelObject>(1);
        }
        if (n >= 0) {
            this.K.add(n, modelObject);
        }
        else {
            this.K.add(modelObject);
        }
    }
    
    @Override
    public IModelObject removeChild(final int n) {
        final IModel model = this.getModel();
        final IChangeSet locked = this.getModel().isLocked(this);
        if (locked != null) {
            if (this.K == null) {
                return null;
            }
            final IModelObject child = this.getChild(n);
            locked.removeChild(this, child, n);
            return child;
        }
        else {
            this.writeChildrenAccess();
            if (this.K == null) {
                return null;
            }
            final IModelObject modelObject = this.K.get(n);
            if (modelObject != null) {
                model.startUpdate().A(this, modelObject, n);
                this.removeChildImpl(n);
                modelObject.internal_setParent(null);
                model.lock(this);
                model.lock(modelObject);
                this.B(modelObject, n);
                model.unlock(this);
                model.unlock(modelObject);
                model.endUpdate();
            }
            return modelObject;
        }
    }
    
    @Override
    public void removeChild(final IModelObject modelObject) {
        final IModel model = this.getModel();
        final IChangeSet locked = this.getModel().isLocked(this);
        if (locked != null) {
            locked.removeChild(this, modelObject);
        }
        else {
            this.writeChildrenAccess();
            if (this.K == null) {
                return;
            }
            final int index = this.K.indexOf(modelObject);
            if (index >= 0) {
                model.startUpdate().A(this, modelObject, index);
                this.removeChildImpl(index);
                modelObject.internal_setParent(null);
                model.lock(this);
                model.lock(modelObject);
                this.B(modelObject, index);
                model.unlock(this);
                model.unlock(modelObject);
                model.endUpdate();
            }
        }
    }
    
    protected IModelObject removeChildImpl(final int n) {
        if (this.K == null) {
            return null;
        }
        final IModelObject modelObject = this.K.remove(n);
        if (this.K.size() == 0) {
            this.K = null;
        }
        return modelObject;
    }
    
    @Override
    public void removeChildren() {
        while (this.K != null && this.K.size() > 0) {
            this.removeChild(this.K.size() - 1);
        }
    }
    
    @Override
    public void removeChildren(final String s) {
        if (this.K != null) {
            final ArrayList<IModelObject> list = new ArrayList<IModelObject>(this.K.size());
            list.addAll((Collection<?>)this.K);
            for (final IModelObject modelObject : list) {
                if (modelObject.isType(s)) {
                    this.removeChild(modelObject);
                }
            }
        }
    }
    
    @Override
    public void removeFromParent() {
        final IModelObject parent = this.getParent();
        if (parent != null) {
            parent.removeChild(this);
        }
    }
    
    @Override
    public IModelObject getChild(final int n) {
        this.readChildrenAccess();
        if (this.K == null) {
            return null;
        }
        if (n >= this.K.size()) {
            return null;
        }
        return this.K.get(n);
    }
    
    @Override
    public IModelObject getFirstChild(final String s) {
        this.readChildrenAccess();
        if (this.K != null) {
            for (final IModelObject modelObject : this.K) {
                if (modelObject.isType(s)) {
                    return modelObject;
                }
            }
        }
        return null;
    }
    
    @Override
    public IModelObject getChild(final String s, final String s2) {
        this.readChildrenAccess();
        if (this.K != null) {
            for (final IModelObject modelObject : this.K) {
                if (modelObject.isType(s) && modelObject.getID().equals(s2)) {
                    return modelObject;
                }
            }
        }
        return null;
    }
    
    @Override
    public IModelObject getCreateChild(final String s) {
        IModelObject modelObject = this.getFirstChild(s);
        if (modelObject == null) {
            modelObject = this.createObject(s);
            this.addChild(modelObject);
        }
        return modelObject;
    }
    
    @Override
    public IModelObject getCreateChild(final String s, final String id) {
        IModelObject modelObject = this.getChild(s, id);
        if (modelObject == null) {
            modelObject = this.createObject(s);
            modelObject.setID(id);
            this.addChild(modelObject);
        }
        return modelObject;
    }
    
    @Override
    public List<IModelObject> getChildren(final String s, final String s2) {
        this.readChildrenAccess();
        final ArrayList<IModelObject> list = new ArrayList<IModelObject>(1);
        if (this.K != null) {
            for (final IModelObject modelObject : this.K) {
                if (modelObject.isType(s) && modelObject.getID().equals(s2)) {
                    list.add(modelObject);
                }
            }
        }
        return list;
    }
    
    @Override
    public List<IModelObject> getChildren() {
        this.readChildrenAccess();
        List<IModelObject> list = this.K;
        if (this.K == null) {
            list = Collections.emptyList();
        }
        return list;
    }
    
    @Override
    public List<IModelObject> getChildren(final String s) {
        this.readChildrenAccess();
        if (this.K != null) {
            final ArrayList<IModelObject> list = new ArrayList<IModelObject>(this.K.size());
            for (final IModelObject modelObject : this.K) {
                if (modelObject.isType(s)) {
                    list.add(modelObject);
                }
            }
            return list;
        }
        return Collections.emptyList();
    }
    
    @Override
    public Set<String> getTypesOfChildren() {
        this.readChildrenAccess();
        final HashSet<String> set = new HashSet<String>();
        if (this.K != null) {
            final Iterator<IModelObject> iterator = this.K.iterator();
            while (iterator.hasNext()) {
                set.add(iterator.next().getType());
            }
        }
        return set;
    }
    
    @Override
    public int getNumberOfChildren() {
        this.readChildrenAccess();
        return (this.K == null) ? 0 : this.K.size();
    }
    
    @Override
    public int getNumberOfChildren(final String s) {
        this.readChildrenAccess();
        return this.getChildren(s).size();
    }
    
    @Override
    public void internal_notifyParent(final IModelObject modelObject, final IModelObject modelObject2) {
        this.A(modelObject, modelObject2);
    }
    
    @Override
    public void internal_notifyAddChild(final IModelObject modelObject, final int n) {
        this.A(modelObject, n);
    }
    
    @Override
    public void internal_notifyRemoveChild(final IModelObject modelObject, final int n) {
        this.B(modelObject, n);
    }
    
    @Override
    public void internal_addChild(final IModelObject modelObject, final int n) {
        this.addChildImpl(modelObject, n);
    }
    
    @Override
    public IModelObject internal_removeChild(final int n) {
        return this.removeChildImpl(n);
    }
    
    @Override
    public IModelObject internal_setParent(final IModelObject n) {
        final IModelObject n2 = this.N;
        this.N = n;
        return n2;
    }
    
    @Override
    public IModelObject getParent() {
        return this.N;
    }
    
    @Override
    public IModelObject getAncestor(final String s) {
        IModelObject modelObject;
        for (modelObject = this.getParent(); modelObject != null; modelObject = modelObject.getParent()) {
            if (modelObject.isType(s)) {
                return modelObject;
            }
        }
        return modelObject;
    }
    
    @Override
    public IModelObject getRoot() {
        ModelObject modelObject = this;
        for (IModelObject modelObject2 = modelObject.getParent(); modelObject2 != null; modelObject2 = modelObject2.getParent()) {
            modelObject = (ModelObject)modelObject2;
        }
        return modelObject;
    }
    
    @Override
    public void addModelListener(final IModelListener modelListener) {
        if (this.M == null) {
            this.M = new F();
        }
        this.M.A(modelListener);
    }
    
    @Override
    public void removeModelListener(final IModelListener modelListener) {
        if (this.M == null) {
            return;
        }
        this.M.B(modelListener);
    }
    
    @Override
    public F getModelListeners() {
        return this.M;
    }
    
    @Override
    public C getPathListeners() {
        if (this.H == null) {
            this.H = new C();
        }
        return this.H;
    }
    
    @Override
    public void addAncestorListener(final IAncestorListener ancestorListener) {
        new ClimbingListener(ancestorListener).addListenerToTree(this);
    }
    
    @Override
    public void removeAncestorListener(final IAncestorListener ancestorListener) {
        new ClimbingListener(ancestorListener).removeListenerFromTree(this);
    }
    
    @Override
    public IModelObject cloneObject() {
        final IModelObject object = this.createObject(this.getType());
        ModelAlgorithms.copyAttributes(this, object);
        return object;
    }
    
    @Override
    public IModelObject cloneTree() {
        return ModelAlgorithms.cloneTree(this);
    }
    
    @Override
    public IModelObject createObject(final String s) {
        return new ModelObject(s);
    }
    
    @Override
    public IModelObject getReferent() {
        return this;
    }
    
    @Override
    public void revertUpdate(final B b) {
        if (b instanceof E) {
            final E e = (E)b;
            if (e.G == null) {
                this.I.remove(e.I);
            }
            else {
                this.I.put(e.I, e.G);
            }
        }
        else if (b instanceof G) {
            this.N = ((G)b).O;
        }
        else if (b instanceof org.xmodel.A.C) {
            this.K.remove(((org.xmodel.A.C)b).A);
        }
        else if (b instanceof D) {
            final D d = (D)b;
            if (this.K == null) {
                this.K = new ArrayList<IModelObject>(1);
            }
            this.K.add(d.D, d.F);
        }
        else if (b instanceof H) {
            final H h = (H)b;
            this.K.remove((h.R > h.T) ? (h.R - 1) : h.R);
            this.K.add(h.T, h.U);
        }
        else {
            final A a = (A)b;
            this.I.put(a.X, a.V);
        }
    }
    
    @Override
    public void restoreUpdate(final B b) {
        if (b instanceof E) {
            final E e = (E)b;
            this.I.put(e.I, e.J);
        }
        else if (b instanceof G) {
            this.N = ((G)b).P;
        }
        else if (b instanceof org.xmodel.A.C) {
            final org.xmodel.A.C c = (org.xmodel.A.C)b;
            this.K.add(c.A, c.C);
        }
        else if (b instanceof D) {
            this.K.remove(((D)b).D);
        }
        else if (b instanceof H) {
            final H h = (H)b;
            this.K.remove(h.T);
            this.K.add((h.R > h.T) ? (h.R - 1) : h.R, h.U);
        }
        else {
            this.I.remove(((A)b).X);
        }
    }
    
    protected void readAttributeAccess(final String s) {
    }
    
    protected void readChildrenAccess() {
    }
    
    protected void writeAttributeAccess(final String s) {
    }
    
    protected void writeChildrenAccess() {
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append('<');
        sb.append(this.getType());
        String value = "";
        if (this.I != null) {
            for (final Map.Entry<String, Object> entry : this.I.entrySet()) {
                final String s = entry.getKey();
                if (s.length() > 0 && s.charAt(0) != '!') {
                    sb.append(' ');
                    sb.append(s);
                    sb.append("='");
                    sb.append(entry.getValue());
                    sb.append('\'');
                }
            }
            value = this.I.get("");
        }
        if (this.K != null && this.K.size() > 0) {
            if (value == null || value.equals("")) {
                sb.append(">...");
            }
            else {
                sb.append('>');
                sb.append(value.toString().trim());
                sb.append("...");
            }
        }
        else if (value == null || value.equals("")) {
            sb.append("/>");
        }
        else {
            sb.append('>');
            sb.append((Object)value);
            sb.append("</");
            sb.append(this.getType());
            sb.append('>');
        }
        return sb.toString();
    }
    
    public String toXml() {
        final IModel model = this.getModel();
        final boolean syncLock = model.getSyncLock();
        try {
            model.setSyncLock(true);
            return XmlIO.toString(this);
        }
        finally {
            model.setSyncLock(syncLock);
        }
    }
    
    private void A(final IModelObject modelObject, final IModelObject modelObject2) {
        if (this.M != null) {
            this.M.notifyParent(this, modelObject, modelObject2);
        }
    }
    
    private void A(final IModelObject modelObject, final int n) {
        if (this.M != null) {
            this.M.notifyAddChild(this, modelObject, n);
        }
    }
    
    private void B(final IModelObject modelObject, final int n) {
        if (this.M != null) {
            this.M.notifyRemoveChild(this, modelObject, n);
        }
    }
    
    private void A(final String s, final Object o, final Object o2) {
        if (this.M != null) {
            this.M.notifyChange(this, s, o, o2);
        }
    }
    
    private void A(final String s, final Object o) {
        if (this.M != null) {
            this.M.notifyClear(this, s, o);
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof Reference) {
            return o.equals(this);
        }
        return super.equals(o);
    }
}
