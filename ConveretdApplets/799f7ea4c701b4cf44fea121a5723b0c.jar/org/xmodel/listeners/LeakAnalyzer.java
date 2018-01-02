// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.listeners;

import org.xmodel.xpath.expression.ExpressionListenerList;
import org.xmodel.IPathListener;
import org.xmodel.B.Q;
import org.xmodel.xpath.expression.IExpressionListener;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.F;
import org.xmodel.IModelListener;
import java.util.Iterator;
import org.xmodel.ModelAlgorithms;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import org.xmodel.external.NonSyncingIterator;
import org.xmodel.util.HashMultiMap;
import org.xmodel.IModelObject;
import org.xmodel.util.MultiMap;
import java.io.PrintStream;

public class LeakAnalyzer
{
    PrintStream B;
    MultiMap<B, IModelObject> A;
    MultiMap<B, IModelObject> C;
    
    public LeakAnalyzer() {
        this.B = System.out;
        this.A = new HashMultiMap<B, IModelObject>();
        this.C = new HashMultiMap<B, IModelObject>();
    }
    
    public LeakAnalyzer(final PrintStream b) {
        this.B = b;
        this.A = new HashMultiMap<B, IModelObject>();
        this.C = new HashMultiMap<B, IModelObject>();
    }
    
    public void analyze(final IModelObject modelObject) {
        final NonSyncingIterator nonSyncingIterator = new NonSyncingIterator(modelObject);
        while (nonSyncingIterator.hasNext()) {
            this.populateClientListeners(nonSyncingIterator.next());
        }
        this.B.println("Listeners:");
        final ArrayList<Object> list = new ArrayList<Object>(this.A.keySet());
        Collections.sort(list, (Comparator<? super Object>)new A());
        for (final B b : list) {
            this.B.println(b);
            final List<IModelObject> value = this.A.get(b);
            final Iterator<IModelObject> iterator2 = value.iterator();
            while (iterator2.hasNext()) {
                this.B.println("  " + ModelAlgorithms.createIdentityPath(iterator2.next()));
            }
            this.B.println("  Total=" + value.size());
        }
        this.B.println("\nLeaks:");
        for (final B b2 : this.C.keySet()) {
            this.B.println(b2);
            final List<IModelObject> value2 = this.A.get(b2);
            final Iterator<IModelObject> iterator4 = value2.iterator();
            while (iterator4.hasNext()) {
                this.B.println("  " + ModelAlgorithms.createIdentityPath(iterator4.next()));
            }
            this.B.println("  Total=" + value2.size());
        }
    }
    
    protected void populateClientListeners(final IModelObject modelObject) {
        final F modelListeners = modelObject.getModelListeners();
        if (modelListeners == null) {
            return;
        }
        final Iterator<IModelListener> iterator = modelListeners.F().iterator();
        while (iterator.hasNext()) {
            this.populateFrom(modelObject, iterator.next());
        }
    }
    
    protected void populateFrom(final IModelObject modelObject, final IModelListener a) {
        if (a instanceof org.xmodel.B.F) {
            final org.xmodel.B.A b = ((org.xmodel.B.F)a).B();
            this.populateFrom(modelObject, b, b.A());
        }
        else {
            final B b2 = new B();
            b2.A = a;
            this.A.put(b2, modelObject);
        }
    }
    
    protected void populateFrom(final IModelObject modelObject, final IContext d, final IExpressionListener b) {
        if (b instanceof Q) {
            final org.xmodel.B.A b2 = ((Q)b).B();
            this.populateFrom(modelObject, b2, b2.A());
        }
        else {
            final B b3 = new B();
            b3.D = d;
            b3.B = b;
            this.A.put(b3, modelObject);
        }
    }
    
    protected void populateFrom(final IModelObject modelObject, final org.xmodel.B.A a, final IPathListener c) {
        if (c instanceof org.xmodel.xpath.expression.A) {
            final ExpressionListenerList listeners = ((org.xmodel.xpath.expression.A)c).getRoot().getListeners();
            if (listeners != null) {
                for (final IContext context : listeners.getContexts()) {
                    final Iterator<IExpressionListener> iterator2 = listeners.getListeners(context).iterator();
                    while (iterator2.hasNext()) {
                        this.populateFrom(modelObject, context, iterator2.next());
                    }
                }
            }
        }
        else {
            final B b = new B();
            b.D = a.B();
            b.C = c;
            this.A.put(b, modelObject);
        }
    }
    
    protected List<B> findLeaks(final List<B> list) {
        return null;
    }
}
