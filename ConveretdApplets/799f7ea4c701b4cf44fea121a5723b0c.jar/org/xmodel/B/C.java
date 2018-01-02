// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.B;

import org.xmodel.ModelObject;
import java.util.List;
import org.xmodel.IPathListener;
import org.xmodel.xpath.expression.Context;
import org.xmodel.xml.XmlIO;
import org.xmodel.xpath.XPath;
import org.xmodel.IPath;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.IModelListener;
import org.xmodel.BreadthFirstIterator;
import org.xmodel.IModelObject;

public class C extends J
{
    public C(final A a, final int n) {
        super(a, n);
    }
    
    @Override
    protected void D(final IModelObject modelObject) {
        final BreadthFirstIterator breadthFirstIterator = new BreadthFirstIterator(modelObject);
        while (breadthFirstIterator.hasNext()) {
            breadthFirstIterator.next().addModelListener(this);
        }
    }
    
    @Override
    protected void C(final IModelObject modelObject) {
        final BreadthFirstIterator breadthFirstIterator = new BreadthFirstIterator(modelObject);
        while (breadthFirstIterator.hasNext()) {
            breadthFirstIterator.next().removeModelListener(this);
        }
    }
    
    @Override
    public R A(final A a) {
        return new C(a, this.U);
    }
    
    @Override
    public void notifyAddChild(final IModelObject modelObject, final IModelObject modelObject2, final int n) {
        final BreadthFirstIterator breadthFirstIterator = new BreadthFirstIterator(modelObject2);
        while (breadthFirstIterator.hasNext()) {
            final IModelObject next = breadthFirstIterator.next();
            if (this.V.evaluate(null, null, next)) {
                this.E().B(next);
            }
        }
        this.D(modelObject2);
    }
    
    @Override
    public void notifyRemoveChild(final IModelObject modelObject, final IModelObject modelObject2, final int n) {
        this.C(modelObject2);
        final BreadthFirstIterator breadthFirstIterator = new BreadthFirstIterator(modelObject2);
        while (breadthFirstIterator.hasNext()) {
            final IModelObject next = breadthFirstIterator.next();
            if (this.V.evaluate(null, null, next)) {
                this.E().A(next);
            }
        }
    }
    
    public static void A(final String[] array) throws Exception {
        XPath.createPath("nested::node").addPathListener(new Context(new XmlIO().read("<node id='1'><node id='2'></node></node>")), new IPathListener() {
            private final /* synthetic */ IPath \u00e1 = XPath.createPath("nested::node[ last()]");
            
            @Override
            public void notifyAdd(final IContext context, final IPath path, final int n, final List<IModelObject> list) {
                System.out.println("node: " + list.get(0));
                final IModelObject queryFirst = this.\u00e1.queryFirst(list.get(0));
                if (queryFirst != null) {
                    final ModelObject modelObject = new ModelObject("node");
                    modelObject.setID("3");
                    queryFirst.addChild(modelObject);
                }
            }
            
            @Override
            public void notifyRemove(final IContext context, final IPath path, final int n, final List<IModelObject> list) {
            }
        });
    }
}
