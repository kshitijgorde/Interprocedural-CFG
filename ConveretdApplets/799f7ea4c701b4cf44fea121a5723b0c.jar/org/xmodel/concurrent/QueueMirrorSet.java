// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.Iterator;
import org.xmodel.ModelAlgorithms;
import org.xmodel.IModelListener;
import org.xmodel.IChangeRecord;
import java.util.concurrent.ArrayBlockingQueue;
import org.xmodel.ModelObjectFactory;
import java.util.ArrayList;
import java.util.List;
import org.xmodel.IModelObjectFactory;
import org.xmodel.IModelObject;

public class QueueMirrorSet implements IMirrorSet
{
    private IModelObject F;
    private IModelObjectFactory A;
    private ThreadLocal<_A> E;
    private List<_A> D;
    private Object C;
    private int G;
    private boolean B;
    
    public QueueMirrorSet(final IModelObject modelObject) {
        this(modelObject, 100);
    }
    
    public QueueMirrorSet(final IModelObject f, final int g) {
        this.F = f;
        this.E = new ThreadLocal<_A>();
        this.D = new ArrayList<_A>();
        this.G = g;
        this.A = new ModelObjectFactory();
    }
    
    @Override
    public void attach() {
        this.B = true;
    }
    
    @Override
    public void detach() {
    }
    
    @Override
    public IModelObject get(final boolean b) {
        final _A a = this.E.get();
        if (a != null) {
            return a.B;
        }
        if (b) {
            final _A a2 = new _A(null);
            a2.B = this.A();
            a2.A = new ArrayBlockingQueue<IChangeRecord>(this.G);
            a2.C = new A(a2.B, this);
            a2.B.addModelListener(a2.C);
            synchronized (this.C) {
                this.D.add(a2);
            }
            // monitorexit(this.C)
            return a2.B;
        }
        return null;
    }
    
    private IModelObject A() {
        if (this.B) {
            return this.A.createObject(null, this.F.getType());
        }
        return ModelAlgorithms.cloneTree(this.F, this.A);
    }
    
    void A(final IChangeRecord changeRecord) {
        synchronized (this.C) {
            final _A a = this.E.get();
            for (final _A a2 : this.D) {
                if (a2 == a) {
                    continue;
                }
                try {
                    a2.A.put(changeRecord);
                }
                catch (InterruptedException ex) {
                    this.detach();
                    break;
                }
            }
        }
        // monitorexit(this.C)
    }
    
    private static class _A
    {
        public IModelObject B;
        public BlockingQueue<IChangeRecord> A;
        public A C;
    }
}
