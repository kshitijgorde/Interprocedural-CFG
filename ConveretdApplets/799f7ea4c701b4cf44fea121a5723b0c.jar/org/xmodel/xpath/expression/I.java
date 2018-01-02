// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.expression;

import org.xmodel.IModel;
import java.util.LinkedHashSet;
import java.util.Iterator;
import java.util.Collections;
import org.xmodel.IChangeSet;
import org.xmodel.IModelObjectFactory;
import java.util.Collection;
import java.util.ArrayList;
import org.xmodel.IModelObject;
import java.util.List;

public class I extends L
{
    private static /* synthetic */ int[] \u00cb;
    
    public I() {
    }
    
    public I(final IExpression expression, final IExpression expression2) {
        this.addArgument(expression);
        this.addArgument(expression2);
    }
    
    @Override
    public String getName() {
        return "filtered";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.NODES;
    }
    
    @Override
    public List<IModelObject> evaluateNodes(final IContext context) throws ExpressionException {
        final IExpression argument = this.getArgument(0);
        final IExpression argument2 = this.getArgument(1);
        final List<IModelObject> evaluateNodes = argument.evaluateNodes(context);
        final ArrayList list = new ArrayList<IModelObject>(evaluateNodes.size());
        for (int i = 0; i < evaluateNodes.size(); ++i) {
            final IModelObject modelObject = evaluateNodes.get(i);
            final SubContext subContext = new SubContext(context, modelObject, i + 1, evaluateNodes.size());
            switch (I()[argument2.getType(context).ordinal()]) {
                case 1: {
                    list.addAll((Collection<?>)argument2.evaluateNodes(subContext));
                    break;
                }
                case 4: {
                    if (argument2.evaluateBoolean(subContext)) {
                        list.add(modelObject);
                        break;
                    }
                    break;
                }
            }
        }
        return (List<IModelObject>)list;
    }
    
    @Override
    public boolean evaluateBoolean(final IContext context) throws ExpressionException {
        final IExpression argument = this.getArgument(0);
        final IExpression argument2 = this.getArgument(1);
        final List<IModelObject> evaluateNodes = argument.evaluateNodes(context);
        for (int i = 0; i < evaluateNodes.size(); ++i) {
            final SubContext subContext = new SubContext(context, evaluateNodes.get(i), i + 1, evaluateNodes.size());
            switch (I()[argument2.getType(context).ordinal()]) {
                case 1: {
                    if (argument2.evaluateNodes(subContext).size() > 0) {
                        return true;
                    }
                    break;
                }
                case 4: {
                    if (argument2.evaluateBoolean(subContext)) {
                        return true;
                    }
                    break;
                }
            }
        }
        return false;
    }
    
    @Override
    public void createSubtree(final IContext context, final IModelObjectFactory modelObjectFactory, final IChangeSet set) {
        final List<IModelObject> query = this.getArgument(0).query(context, null);
        for (int i = 0; i < query.size(); ++i) {
            this.getArgument(1).createSubtree(new SubContext(context, query.get(i), i + 1, query.size()), modelObjectFactory, set);
        }
    }
    
    @Override
    public void bind(final IContext context) {
        final IExpression argument = this.getArgument(0);
        final IExpression argument2 = this.getArgument(1);
        argument.bind(context);
        final List<IModelObject> evaluateNodes = argument.evaluateNodes(context);
        for (int i = 0; i < evaluateNodes.size(); ++i) {
            argument2.bind(new SubContext(context, evaluateNodes.get(i), i + 1, evaluateNodes.size()));
        }
    }
    
    @Override
    public void unbind(final IContext context) {
        final IExpression argument = this.getArgument(0);
        final IExpression argument2 = this.getArgument(1);
        argument.unbind(context);
        final List<IModelObject> evaluateNodes = argument.evaluateNodes(context);
        for (int i = 0; i < evaluateNodes.size(); ++i) {
            argument2.unbind(new SubContext(context, evaluateNodes.get(i), i + 1, evaluateNodes.size()));
        }
    }
    
    @Override
    public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
        final IExpression argument = this.getArgument(0);
        final IExpression argument2 = this.getArgument(1);
        if (expression == argument) {
            final List<IModelObject> evaluateNodes = argument.evaluateNodes(context);
            int indexOfSubList = Collections.indexOfSubList(evaluateNodes, list);
            final int size = evaluateNodes.size();
            if (argument2.getType(context) == IExpression.ResultType.BOOLEAN) {
                final ArrayList<IModelObject> list2 = new ArrayList<IModelObject>(list.size());
                for (final IModelObject modelObject : list) {
                    final SubContext subContext = new SubContext(context, modelObject, indexOfSubList++ + 1, size);
                    if (argument2.evaluateBoolean(subContext, false)) {
                        list2.add(modelObject);
                    }
                    argument2.bind(subContext);
                }
                if (list2.size() > 0) {
                    this.k.notifyAdd(this, context, list2);
                }
            }
            else {
                final ArrayList<Object> list3 = new ArrayList<Object>();
                final Iterator<IModelObject> iterator2 = list.iterator();
                while (iterator2.hasNext()) {
                    final SubContext subContext2 = new SubContext(context, iterator2.next(), indexOfSubList++ + 1, size);
                    argument2.bind(subContext2);
                    list3.addAll(argument2.evaluateNodes(subContext2));
                }
                if (list3.size() > 0) {
                    this.k.notifyAdd(this, context, (List<IModelObject>)list3);
                }
            }
        }
        else {
            if (expression != argument2) {
                throw new IllegalStateException("Notification from expression which is not an argument.");
            }
            this.k.notifyAdd(this, context.getParent(), list);
        }
    }
    
    @Override
    public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
        final IExpression argument = this.getArgument(0);
        final IExpression argument2 = this.getArgument(1);
        if (expression == argument) {
            context.getModel().revert();
            final List<IModelObject> evaluateNodes = argument.evaluateNodes(context);
            context.getModel().restore();
            int indexOfSubList = Collections.indexOfSubList(evaluateNodes, list);
            final int size = evaluateNodes.size();
            if (argument2.getType(context) == IExpression.ResultType.BOOLEAN) {
                context.getModel().revert();
                final ArrayList<IModelObject> list2 = new ArrayList<IModelObject>(list.size());
                for (final IModelObject modelObject : list) {
                    final SubContext subContext = new SubContext(context, modelObject, indexOfSubList++ + 1, size);
                    if (argument2.evaluateBoolean(subContext, false)) {
                        list2.add(modelObject);
                    }
                    argument2.unbind(subContext);
                }
                context.getModel().restore();
                if (list2.size() > 0) {
                    this.k.notifyRemove(this, context, list2);
                }
            }
            else {
                context.getModel().revert();
                final ArrayList<Object> list3 = new ArrayList<Object>();
                final Iterator<IModelObject> iterator2 = list.iterator();
                while (iterator2.hasNext()) {
                    final SubContext subContext2 = new SubContext(context, iterator2.next(), indexOfSubList++ + 1, size);
                    argument2.unbind(subContext2);
                    list3.addAll(argument2.evaluateNodes(subContext2));
                }
                context.getModel().restore();
                if (list3.size() > 0) {
                    this.k.notifyRemove(this, context, (List<IModelObject>)list3);
                }
            }
        }
        else {
            if (expression != argument2) {
                throw new IllegalStateException("Notification from expression which is not an argument.");
            }
            this.k.notifyRemove(this, context.getParent(), list);
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final boolean b) {
        final List<IModelObject> singletonList = Collections.singletonList(context.getObject());
        if (b) {
            this.k.notifyAdd(this, context.getParent(), singletonList);
        }
        else {
            this.k.notifyRemove(this, context.getParent(), singletonList);
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context) {
        final IModel model = context.getModel();
        switch (I()[expression.getType(context).ordinal()]) {
            case 1: {
                model.revert();
                Object evaluateNodes = expression.evaluateNodes(context);
                if (((Collection)evaluateNodes).size() > 3) {
                    evaluateNodes = new LinkedHashSet<Object>((Collection)evaluateNodes);
                }
                model.restore();
                Object evaluateNodes2 = expression.evaluateNodes(context);
                if (((Collection)evaluateNodes2).size() > 3) {
                    evaluateNodes2 = new LinkedHashSet<Object>((Collection)evaluateNodes2);
                }
                final ArrayList list = new ArrayList<IModelObject>(((Collection)evaluateNodes2).size());
                for (final IModelObject modelObject : evaluateNodes) {
                    if (!((Collection)evaluateNodes2).contains(modelObject)) {
                        list.add(modelObject);
                    }
                }
                if (list.size() > 0) {
                    this.notifyRemove(expression, context, (List<IModelObject>)list);
                }
                final ArrayList list2 = new ArrayList<IModelObject>(((Collection)evaluateNodes2).size());
                for (final IModelObject modelObject2 : evaluateNodes2) {
                    if (!((Collection)evaluateNodes).contains(modelObject2)) {
                        list2.add(modelObject2);
                    }
                }
                if (list2.size() > 0) {
                    this.notifyAdd(expression, context, (List<IModelObject>)list2);
                    break;
                }
                break;
            }
            case 4: {
                model.revert();
                final boolean evaluateBoolean = expression.evaluateBoolean(context);
                model.restore();
                final boolean evaluateBoolean2 = expression.evaluateBoolean(context);
                if (evaluateBoolean2 != evaluateBoolean) {
                    this.notifyChange(expression, context, evaluateBoolean2);
                    break;
                }
                break;
            }
        }
    }
    
    @Override
    public boolean requiresValueNotification(final IExpression expression) {
        final IExpression argument = this.getArgument(0);
        final IExpression argument2 = this.getArgument(1);
        final IExpression.ResultType type = argument2.getType();
        return type == IExpression.ResultType.UNDEFINED || (expression == argument && type == IExpression.ResultType.BOOLEAN) || (expression == argument2 && type == IExpression.ResultType.NODES && this.k.requiresValueNotification(this));
    }
    
    @Override
    public void notifyValue(final IExpression expression, IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
        if (expression == this.getArgument(1)) {
            final IContext[] array2 = new IContext[array.length];
            for (int i = 0; i < array2.length; ++i) {
                array2[i] = array[i].getParent();
            }
            array = array2;
        }
        if (expression.getType(array[0]) == IExpression.ResultType.BOOLEAN) {
            modelObject.getModel().revert();
            IContext[] array3;
            for (int length = (array3 = array).length, j = 0; j < length; ++j) {
                this.unbind(array3[j]);
            }
            modelObject.getModel().restore();
            IContext[] array4;
            for (int length2 = (array4 = array).length, k = 0; k < length2; ++k) {
                this.bind(array4[k]);
            }
            IContext[] array5;
            for (int length3 = (array5 = array).length, l = 0; l < length3; ++l) {
                this.notifyChange(this, array5[l]);
            }
        }
        else {
            this.k.notifyValue(this, array, modelObject, o, o2);
        }
    }
    
    @Override
    public String toString() {
        final IExpression argument = this.getArgument(0);
        final IExpression argument2 = this.getArgument(1);
        if (argument2.getType() == IExpression.ResultType.BOOLEAN) {
            return "(" + argument.toString() + ")" + argument2.toString();
        }
        if (!(argument2 instanceof A)) {
            return "(" + argument.toString() + ")/(" + argument2.toString() + ")";
        }
        if (((A)argument2).P().getPathElement(0).hasAxis(32)) {
            return "(" + argument.toString() + ")//" + argument2.toString();
        }
        return "(" + argument.toString() + ")/" + argument2.toString();
    }
    
    static /* synthetic */ int[] I() {
        final int[] \u00eb = I.\u00cb;
        if (\u00eb != null) {
            return \u00eb;
        }
        final int[] \u00eb2 = new int[IExpression.ResultType.values().length];
        try {
            \u00eb2[IExpression.ResultType.BOOLEAN.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            \u00eb2[IExpression.ResultType.NODES.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError2) {}
        try {
            \u00eb2[IExpression.ResultType.NUMBER.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError3) {}
        try {
            \u00eb2[IExpression.ResultType.STRING.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError4) {}
        try {
            \u00eb2[IExpression.ResultType.UNDEFINED.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError5) {}
        return I.\u00cb = \u00eb2;
    }
}
