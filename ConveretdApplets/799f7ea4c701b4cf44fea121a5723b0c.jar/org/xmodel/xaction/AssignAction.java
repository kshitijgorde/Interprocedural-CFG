// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import java.util.Collection;
import java.util.Iterator;
import org.xmodel.xpath.variable.IVariableScope;
import org.xmodel.ModelAlgorithms;
import java.util.List;
import org.xmodel.Reference;
import java.util.ArrayList;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.IModelObject;
import org.xmodel.Xlate;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.IModelObjectFactory;

public class AssignAction extends GuardedAction
{
    private String \u00ee;
    private String \u00f1;
    private boolean \u00eb;
    private boolean \u00ef;
    private boolean \u00f2;
    private IModelObjectFactory \u00ec;
    private IExpression \u00f0;
    private static /* synthetic */ int[] \u00ed;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        final IModelObject root = xActionDocument.getRoot();
        this.\u00ee = Conventions.getVarName(root, true, "name");
        this.\u00f0 = xActionDocument.getExpression();
        if (this.\u00f0 == null) {
            this.\u00f0 = xActionDocument.getExpression("source", true);
        }
        this.\u00ec = this.getFactory(root);
        this.\u00f1 = Xlate.get(root, "mode", "direct");
        this.\u00eb = Xlate.get(root, "append", false);
        this.\u00ef = Xlate.get(root, "replace", false);
        this.\u00f2 = Xlate.get(root, "define", false);
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        if (this.\u00ee.length() == 0) {
            throw new IllegalArgumentException("Variable name has zero length in AssignAction: " + this);
        }
        final IVariableScope a = this.A(this.\u00ee, context, this.\u00ef);
        if (a == null) {
            return null;
        }
        if (this.\u00f0 == null) {
            a.set(this.\u00ee, context.getObject());
        }
        else if (this.\u00f2) {
            a.define(this.\u00ee, this.\u00f0);
        }
        else {
            switch (A()[this.\u00f0.getType(context).ordinal()]) {
                case 1: {
                    final List<IModelObject> evaluateNodes = this.\u00f0.evaluateNodes(context);
                    if (this.\u00f1.equals("direct")) {
                        this.A(a, evaluateNodes);
                        break;
                    }
                    if (this.\u00f1.startsWith("ref")) {
                        final ArrayList list = new ArrayList<IModelObject>(evaluateNodes.size());
                        final Iterator<IModelObject> iterator = evaluateNodes.iterator();
                        while (iterator.hasNext()) {
                            list.add(new Reference(iterator.next()));
                        }
                        this.A(a, (List<IModelObject>)list);
                        break;
                    }
                    if (this.\u00f1.equals("copy")) {
                        final ArrayList list2 = new ArrayList<IModelObject>(evaluateNodes.size());
                        final Iterator<IModelObject> iterator2 = evaluateNodes.iterator();
                        while (iterator2.hasNext()) {
                            list2.add(ModelAlgorithms.cloneTree(iterator2.next(), this.\u00ec));
                        }
                        this.A(a, (List<IModelObject>)list2);
                        break;
                    }
                    if (this.\u00f1.equals("fk1")) {
                        final ArrayList list3 = new ArrayList<IModelObject>(evaluateNodes.size());
                        for (final IModelObject modelObject : evaluateNodes) {
                            final IModelObject object = this.\u00ec.createObject(null, modelObject.getType());
                            object.setValue(modelObject.getID());
                            list3.add(object);
                        }
                        this.A(a, (List<IModelObject>)list3);
                        break;
                    }
                    if (this.\u00f1.equals("fk2")) {
                        final ArrayList list4 = new ArrayList<IModelObject>(evaluateNodes.size());
                        for (final IModelObject modelObject2 : evaluateNodes) {
                            final IModelObject object2 = this.\u00ec.createObject(null, modelObject2.getType());
                            object2.setID(modelObject2.getID());
                            list4.add(object2);
                        }
                        this.A(a, (List<IModelObject>)list4);
                        break;
                    }
                    break;
                }
                case 2: {
                    a.set(this.\u00ee, this.\u00f0.evaluateString(context));
                    break;
                }
                case 3: {
                    a.set(this.\u00ee, this.\u00f0.evaluateNumber(context));
                    break;
                }
                case 4: {
                    a.set(this.\u00ee, Boolean.valueOf(this.\u00f0.evaluateBoolean(context)));
                    break;
                }
                case 5: {
                    throw new XActionException("Expression type is undefined: " + this.\u00f0);
                }
            }
        }
        return null;
    }
    
    private void A(final IVariableScope variableScope, final List<IModelObject> list) {
        if (this.\u00eb) {
            final ArrayList list2 = new ArrayList();
            list2.addAll((Collection)variableScope.get(this.\u00ee));
            list2.addAll(list);
        }
        else {
            variableScope.set(this.\u00ee, list);
        }
    }
    
    private IVariableScope A(final String s, final IContext context, final boolean b) {
        if (!b) {
            return context.getScope();
        }
        final IVariableScope scope = context.getScope();
        if (scope == null) {
            return null;
        }
        return scope.getSource().getVariableScope(s);
    }
    
    static /* synthetic */ int[] A() {
        final int[] \u00ed = AssignAction.\u00ed;
        if (\u00ed != null) {
            return \u00ed;
        }
        final int[] \u00ed2 = new int[IExpression.ResultType.values().length];
        try {
            \u00ed2[IExpression.ResultType.BOOLEAN.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            \u00ed2[IExpression.ResultType.NODES.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError2) {}
        try {
            \u00ed2[IExpression.ResultType.NUMBER.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError3) {}
        try {
            \u00ed2[IExpression.ResultType.STRING.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError4) {}
        try {
            \u00ed2[IExpression.ResultType.UNDEFINED.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError5) {}
        return AssignAction.\u00ed = \u00ed2;
    }
}
