// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.B;

import org.xmodel.xpath.AttributeHistoryNode;
import org.xmodel.xpath.TextHistoryNode;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.IPath;
import org.xmodel.IModelListener;
import org.xmodel.IModelObject;

public class H extends J
{
    public H(final A a, final int n) {
        super(a, n);
    }
    
    @Override
    protected void D(final IModelObject modelObject) {
        modelObject.addModelListener(this);
    }
    
    @Override
    protected void C(final IModelObject modelObject) {
        modelObject.removeModelListener(this);
    }
    
    @Override
    public R A(final A a) {
        return new H(a, this.U);
    }
    
    @Override
    public void notifyChange(final IModelObject modelObject, final String s, final Object o, final Object o2) {
        if (o2 == null) {
            final IPath d = this.B().D();
            final IContext b = this.B().B();
            if (s.length() == 0) {
                final IModelObject attributeNode = modelObject.getAttributeNode("");
                if (this.V.evaluate(b, d, attributeNode)) {
                    this.E().B(attributeNode);
                }
            }
            else {
                final IModelObject attributeNode2 = modelObject.getAttributeNode(s);
                if (this.V.evaluate(b, d, attributeNode2)) {
                    this.E().B(attributeNode2);
                }
            }
        }
    }
    
    @Override
    public void notifyClear(final IModelObject modelObject, final String s, final Object o) {
        if (o != null) {
            final IPath d = this.B().D();
            final IContext b = this.B().B();
            if (s.length() == 0) {
                final TextHistoryNode textHistoryNode = new TextHistoryNode(o);
                if (this.V.evaluate(b, d, textHistoryNode)) {
                    this.E().A(textHistoryNode);
                }
            }
            else {
                final AttributeHistoryNode attributeHistoryNode = new AttributeHistoryNode(s, o);
                if (this.V.evaluate(b, d, attributeHistoryNode)) {
                    this.E().A(attributeHistoryNode);
                }
            }
        }
    }
}
