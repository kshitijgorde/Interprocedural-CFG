// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.xmodel.util.HashMultiMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import org.xmodel.util.MultiMap;
import org.xmodel.log.Log;

public class Model implements IModel
{
    private static Log E;
    private MultiMap<String, IModelObject> G;
    private List<A> H;
    private List<A> C;
    private List<IModelObject> F;
    private int A;
    private IDispatcher I;
    private boolean D;
    private Map<Class<?>, Object> B;
    
    static {
        Model.E = Log.getLog("org.xmodel");
    }
    
    public Model() {
        this.H = new ArrayList<A>();
        this.C = new ArrayList<A>();
        this.F = new ArrayList<IModelObject>();
        this.G = new HashMultiMap<String, IModelObject>();
        this.A = 1;
    }
    
    @Override
    public void addRoot(final String s, final IModelObject modelObject) {
        this.G.put(s, modelObject);
    }
    
    @Override
    public void removeRoot(final String s, final IModelObject modelObject) {
        this.G.remove(s, modelObject);
    }
    
    @Override
    public void removeRoots(final String s) {
        this.G.removeAll(s);
    }
    
    @Override
    public List<IModelObject> getRoots(final String s) {
        return this.G.get(s);
    }
    
    @Override
    public Set<String> getCollections() {
        return this.G.keySet();
    }
    
    protected boolean hasPredicate(final IPath path) {
        for (int i = 0; i < path.length(); ++i) {
            if (path.getPathElement(i).predicate() != null) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void revert() {
        this.setSyncLock(true);
        final A currentUpdate = this.getCurrentUpdate();
        if (currentUpdate != null) {
            currentUpdate.G();
        }
    }
    
    @Override
    public void restore() {
        final Iterator<A> iterator = this.H.iterator();
        while (iterator.hasNext()) {
            iterator.next().B();
        }
        this.setSyncLock(false);
    }
    
    @Override
    public void lock(final IModelObject modelObject) {
        if (modelObject != null) {
            this.F.add(modelObject);
        }
    }
    
    @Override
    public void unlock(final IModelObject modelObject) {
        if (modelObject != null) {
            this.F.remove(modelObject);
        }
    }
    
    public void unlock() {
        this.F.clear();
    }
    
    @Override
    public IChangeSet isLocked(final IModelObject modelObject) {
        if (!this.F.contains(modelObject)) {
            return null;
        }
        return this.getCurrentUpdate().A();
    }
    
    @Override
    public A startUpdate() {
        final int size = this.H.size();
        if (size > 0 && this.H.get(size - 1).D()) {
            final Throwable t = new Throwable();
            System.err.println("Internal error: illegal update in reverted state: ");
            t.printStackTrace(System.err);
        }
        final int n = size;
        if (n >= this.C.size()) {
            this.C.add(new A());
        }
        final A a = this.C.get(n);
        a.C();
        a.A(this.A++);
        this.H.add(a);
        return a;
    }
    
    @Override
    public List<A> updateStack() {
        return this.H;
    }
    
    @Override
    public void endUpdate() {
        final int n = this.H.size() - 1;
        if (n < 0) {
            throw new IllegalStateException("Update stack is empty.");
        }
        final A a = this.H.remove(n);
        a.C();
        a.A(0);
        a.E();
    }
    
    @Override
    public A getCurrentUpdate() {
        if (this.H.size() == 0) {
            return null;
        }
        return this.H.get(this.H.size() - 1);
    }
    
    @Override
    public int getUpdateID() {
        final A currentUpdate = this.getCurrentUpdate();
        if (currentUpdate != null) {
            return currentUpdate.F();
        }
        return this.A - 1;
    }
    
    @Override
    public void setDispatcher(final IDispatcher i) {
        this.I = i;
    }
    
    @Override
    public IDispatcher getDispatcher() {
        return this.I;
    }
    
    @Override
    public void dispatch(final Runnable runnable) {
        if (this.I == null) {
            throw new IllegalStateException("Dispatcher is not defined.");
        }
        this.I.execute(runnable);
    }
    
    @Override
    public void setSyncLock(final boolean d) {
        this.D = d;
    }
    
    @Override
    public boolean getSyncLock() {
        return this.D;
    }
    
    @Override
    public void handleException(final Exception ex) {
        Model.E.exception(ex);
    }
    
    @Override
    public <T> void setFeature(final Class<?> clazz, final T t) {
        if (this.B == null) {
            this.B = new HashMap<Class<?>, Object>();
        }
        this.B.put(clazz, t);
    }
    
    @Override
    public <T> T getFeature(final Class<T> clazz) {
        if (this.B != null) {
            return (T)this.B.get(clazz);
        }
        return null;
    }
}
