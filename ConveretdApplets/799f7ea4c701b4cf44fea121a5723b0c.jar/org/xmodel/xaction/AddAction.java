// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import java.util.Iterator;
import org.xmodel.diff.XmlDiffer;
import org.xmodel.Reference;
import org.xmodel.xpath.expression.ExpressionException;
import java.util.List;
import org.xmodel.IChangeSet;
import org.xmodel.ModelAlgorithms;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.IModelObject;
import org.xmodel.Xlate;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.diff.IXmlMatcher;
import org.xmodel.IModelObjectFactory;

public class AddAction extends GuardedAction
{
    private IModelObjectFactory l;
    private IXmlMatcher p;
    private IExpression o;
    private IExpression s;
    private IExpression m;
    private String r;
    private boolean q;
    private boolean n;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        final IModelObject root = xActionDocument.getRoot();
        this.l = this.getFactory(root);
        this.p = this.getMatcher(root);
        this.o = xActionDocument.getExpression("source", true);
        this.s = xActionDocument.getExpression("target", true);
        this.m = xActionDocument.getExpression("index", true);
        if (this.o == null) {
            this.o = xActionDocument.getExpression();
        }
        if (this.s == null) {
            this.s = xActionDocument.getExpression();
        }
        this.n = Xlate.get(root, "create", Xlate.childGet(root, "create", false));
        this.q = Xlate.get(root, "unique", Xlate.childGet(root, "unique", false));
        this.r = Xlate.get(root, "mode", Xlate.childGet(root, "mode", "copy"));
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        if (this.n) {
            ModelAlgorithms.createPathSubtree(context, this.s, this.l, null);
        }
        final List<IModelObject> query = this.o.query(context, null);
        if (query.size() == 0) {
            return null;
        }
        final List<IModelObject> query2 = this.s.query(context, null);
        if (query2.size() == 0) {
            return null;
        }
        int n = -1;
        try {
            if (this.m != null) {
                n = (int)this.m.evaluateNumber(context);
            }
        }
        catch (ExpressionException ex) {
            this.getDocument().error("Unable to evaluate index expression in AddAction: " + this.toString(), ex);
        }
        for (final IModelObject modelObject : query2) {
            int n2 = (n < 0) ? modelObject.getNumberOfChildren() : n;
            for (final IModelObject modelObject2 : query) {
                if (this.r.startsWith("ref")) {
                    if (!this.q || modelObject.getChild(modelObject2.getType(), modelObject2.getID()) == null) {
                        modelObject.addChild(new Reference(modelObject2), n2);
                    }
                }
                else if (this.r.equals("fk1")) {
                    if (!this.q || modelObject.getChild(modelObject2.getType(), modelObject2.getID()) == null) {
                        final IModelObject object = this.l.createObject(modelObject, modelObject2.getType());
                        object.setValue(modelObject2.getID());
                        modelObject.addChild(object, n2);
                    }
                }
                else if (this.r.equals("fk2")) {
                    if (!this.q || modelObject.getChild(modelObject2.getType(), modelObject2.getID()) == null) {
                        final IModelObject object2 = this.l.createObject(modelObject, modelObject2.getType());
                        object2.setID(modelObject2.getID());
                        modelObject.addChild(object2, n2);
                    }
                }
                else if (this.r.equals("copy")) {
                    if (this.q) {
                        final IModelObject child = modelObject.getChild(modelObject2.getType(), modelObject2.getID());
                        if (child != null) {
                            final XmlDiffer xmlDiffer = new XmlDiffer();
                            if (this.p != null) {
                                xmlDiffer.setMatcher(this.p);
                            }
                            xmlDiffer.diffAndApply(child, modelObject2);
                        }
                        else {
                            modelObject.addChild(ModelAlgorithms.cloneTree(modelObject2, this.l), n2);
                        }
                    }
                    else {
                        modelObject.addChild(ModelAlgorithms.cloneTree(modelObject2, this.l), n2);
                    }
                }
                else if (this.r.equals("move") && (!this.q || modelObject.getChild(modelObject2.getType(), modelObject2.getID()) == null)) {
                    modelObject.addChild(modelObject2, n2);
                }
                ++n2;
            }
        }
        return null;
    }
}
