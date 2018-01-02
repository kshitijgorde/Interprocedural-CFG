// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.B;

import org.xmodel.IPath;
import java.util.Iterator;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.IModelListener;
import org.xmodel.util.Fifo;
import java.util.ArrayList;
import org.xmodel.IModelObject;

public class I extends J
{
    public I(final A a, final int n) {
        super(a, n);
    }
    
    @Override
    protected void D(final IModelObject modelObject) {
        final ArrayList<IModelObject> list = new ArrayList<IModelObject>();
        final Fifo<IModelObject> fifo = new Fifo<IModelObject>();
        fifo.push(modelObject);
        while (!fifo.empty()) {
            final IModelObject modelObject2 = fifo.pop();
            modelObject2.addModelListener(this);
            list.clear();
            this.V.query(null, modelObject2, list);
            for (final IModelObject modelObject3 : list) {
                if (modelObject3 != modelObject2) {
                    fifo.push(modelObject3);
                }
            }
        }
    }
    
    @Override
    protected void C(final IModelObject modelObject) {
        final ArrayList<IModelObject> list = new ArrayList<IModelObject>();
        final Fifo<IModelObject> fifo = new Fifo<IModelObject>();
        fifo.push(modelObject);
        while (!fifo.empty()) {
            final IModelObject modelObject2 = fifo.pop();
            modelObject2.removeModelListener(this);
            list.clear();
            this.V.query(null, modelObject2, list);
            for (final IModelObject modelObject3 : list) {
                if (modelObject3 != modelObject2) {
                    fifo.push(modelObject3);
                }
            }
        }
    }
    
    @Override
    public R A(final A a) {
        return new I(a, this.U);
    }
    
    @Override
    public void notifyAddChild(final IModelObject modelObject, final IModelObject modelObject2, final int n) {
        final ArrayList<IModelObject> list = new ArrayList<IModelObject>();
        final Fifo<IModelObject> fifo = new Fifo<IModelObject>();
        fifo.push(modelObject2);
        while (!fifo.empty()) {
            final IModelObject modelObject3 = fifo.pop();
            if (this.V.evaluate(null, null, modelObject3)) {
                this.E().B(modelObject3);
                list.clear();
                this.V.query(null, modelObject3, list);
                for (final IModelObject modelObject4 : list) {
                    if (modelObject4 != modelObject3) {
                        fifo.push(modelObject4);
                    }
                }
            }
        }
        this.D(modelObject2);
    }
    
    @Override
    public void notifyRemoveChild(final IModelObject modelObject, final IModelObject modelObject2, final int n) {
        this.C(modelObject2);
        final ArrayList<IModelObject> list = new ArrayList<IModelObject>();
        final Fifo<IModelObject> fifo = new Fifo<IModelObject>();
        fifo.push(modelObject2);
        while (!fifo.empty()) {
            final IModelObject modelObject3 = fifo.pop();
            if (this.V.evaluate(null, null, modelObject3)) {
                this.E().A(modelObject3);
                list.clear();
                this.V.query(null, modelObject3, list);
                for (final IModelObject modelObject4 : list) {
                    if (modelObject4 != modelObject3) {
                        fifo.push(modelObject4);
                    }
                }
            }
        }
    }
}
