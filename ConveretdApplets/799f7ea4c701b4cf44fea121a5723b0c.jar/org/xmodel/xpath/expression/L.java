// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.expression;

import java.util.Collection;
import org.xmodel.IChangeSet;
import org.xmodel.IModelObjectFactory;
import org.xmodel.xpath.function.BooleanFunction;
import org.xmodel.xpath.function.StringFunction;
import org.xmodel.xpath.function.NumberFunction;
import org.xmodel.xpath.variable.IVariableSource;
import org.xmodel.xpath.variable.IVariableScope;
import org.xmodel.IModelObject;
import java.util.Collections;
import java.util.Iterator;
import java.util.ArrayList;
import org.xmodel.log.Log;
import java.util.List;

public abstract class L implements IExpression
{
    IExpression k;
    List<IExpression> l;
    private static Log j;
    private static /* synthetic */ int[] i;
    
    static {
        L.j = Log.getLog("org.xmodel.xpath.expression");
    }
    
    @Override
    public void addArgument(final IExpression expression) {
        if (this.l == null) {
            this.l = new ArrayList<IExpression>(1);
        }
        this.l.add(expression);
        expression.internal_setParent(this);
    }
    
    @Override
    public void removeArgument(final IExpression expression) {
        if (this.l != null) {
            this.l.remove(expression);
            expression.internal_setParent(null);
        }
    }
    
    @Override
    public void internal_setParent(final IExpression k) {
        this.k = k;
    }
    
    @Override
    public IExpression getParent() {
        return this.k;
    }
    
    @Override
    public IExpression getRoot() {
        final IExpression parent = this.getParent();
        if (parent != null) {
            return parent.getRoot();
        }
        return this;
    }
    
    @Override
    public boolean isAncestor(final IExpression expression) {
        for (IExpression parent = this; parent != null; parent = parent.getParent()) {
            if (parent == expression) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean isAbsolute(final IContext context) {
        if (this.l != null) {
            final Iterator<IExpression> iterator = this.l.iterator();
            while (iterator.hasNext()) {
                if (!iterator.next().isAbsolute(context)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    @Override
    public List<IExpression> getArguments() {
        if (this.l == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList((List<? extends IExpression>)this.l);
    }
    
    @Override
    public IExpression getArgument(final int n) {
        if (this.l == null || this.l.size() <= n) {
            return null;
        }
        return this.l.get(n);
    }
    
    @Override
    public void setVariable(final String s, final Boolean b) {
        this.getLocalScope().set(s, b);
    }
    
    @Override
    public void setVariable(final String s, final Number n) {
        this.getLocalScope().set(s, n);
    }
    
    @Override
    public void setVariable(final String s, final String s2) {
        this.getLocalScope().set(s, s2);
    }
    
    @Override
    public void setVariable(final String s, final IModelObject modelObject) {
        this.getLocalScope().set(s, modelObject);
    }
    
    @Override
    public void setVariable(final String s, final List<IModelObject> list) {
        this.getLocalScope().set(s, list);
    }
    
    @Override
    public void setVariable(final String s, final IExpression expression) {
        this.getLocalScope().define(s, expression);
    }
    
    public IVariableScope getLocalScope() {
        return this.getVariableSource().getScope("local");
    }
    
    @Override
    public IVariableSource getVariableSource() {
        final IExpression root = this.getRoot();
        if (root != this) {
            return root.getVariableSource();
        }
        return null;
    }
    
    @Override
    public ResultType getType(final IContext context) {
        return this.getType();
    }
    
    protected void assertType(final IContext context, final ResultType resultType) throws ExpressionException {
        if (this.l != null) {
            for (int i = 0; i < this.l.size(); ++i) {
                this.assertType(context, i, resultType);
            }
        }
    }
    
    protected void assertType(final IContext context, final int n, final ResultType resultType) throws ExpressionException {
        final IExpression argument = this.getArgument(n);
        if (argument != null) {
            final ResultType type = argument.getType(context);
            if (type == ResultType.UNDEFINED) {
                throw new ExpressionException(this, "Undefined variable in argument: " + argument);
            }
            if (resultType != type) {
                throw new ExpressionException(this, "Illegal argument type: " + type + " in expression: " + this.toString());
            }
        }
    }
    
    protected void assertArgs(final int n, final int n2) throws ExpressionException {
        final List<IExpression> arguments = this.getArguments();
        if (arguments.size() < n || (n2 >= 0 && arguments.size() > n2)) {
            throw new ExpressionException(this, "Error: wrong number of arguments: " + this.toString());
        }
    }
    
    @Override
    public boolean requiresOrdinalContext() {
        if (this.l != null) {
            final Iterator<IExpression> iterator = this.l.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().requiresOrdinalContext()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public boolean evaluateBoolean() throws ExpressionException {
        return this.evaluateBoolean(NullContext.getInstance());
    }
    
    @Override
    public List<IModelObject> evaluateNodes() throws ExpressionException {
        return this.evaluateNodes(NullContext.getInstance());
    }
    
    @Override
    public double evaluateNumber() throws ExpressionException {
        return this.evaluateNumber(NullContext.getInstance());
    }
    
    @Override
    public String evaluateString() throws ExpressionException {
        return this.evaluateString(NullContext.getInstance());
    }
    
    @Override
    public List<IModelObject> evaluateNodes(final IContext context) throws ExpressionException {
        throw new ExpressionException(this, "Expression does not return node-set.");
    }
    
    @Override
    public double evaluateNumber(final IContext context) throws ExpressionException {
        switch (I()[this.getType(context).ordinal()]) {
            case 1: {
                return NumberFunction.numericValue(this.evaluateNodes(context));
            }
            case 2: {
                return NumberFunction.numericValue(this.evaluateString(context));
            }
            case 4: {
                return NumberFunction.numericValue(this.evaluateBoolean(context));
            }
            default: {
                throw new ExpressionException(this, "Expression implementation error.");
            }
        }
    }
    
    @Override
    public String evaluateString(final IContext context) throws ExpressionException {
        switch (I()[this.getType(context).ordinal()]) {
            case 1: {
                return StringFunction.stringValue(this.evaluateNodes(context));
            }
            case 3: {
                return StringFunction.stringValue(this.evaluateNumber(context));
            }
            case 4: {
                return StringFunction.stringValue(this.evaluateBoolean(context));
            }
            default: {
                throw new UnsupportedOperationException();
            }
        }
    }
    
    @Override
    public boolean evaluateBoolean(final IContext context) throws ExpressionException {
        switch (I()[this.getType(context).ordinal()]) {
            case 1: {
                return BooleanFunction.booleanValue(this.evaluateNodes(context));
            }
            case 3: {
                return BooleanFunction.booleanValue(this.evaluateNumber(context));
            }
            case 2: {
                return BooleanFunction.booleanValue(this.evaluateString(context));
            }
            default: {
                throw new UnsupportedOperationException();
            }
        }
    }
    
    @Override
    public List<IModelObject> evaluateNodes(final IContext context, final List<IModelObject> list) {
        try {
            return this.evaluateNodes(context);
        }
        catch (ExpressionException ex) {
            return list;
        }
    }
    
    @Override
    public double evaluateNumber(final IContext context, final double n) {
        try {
            return this.evaluateNumber(context);
        }
        catch (ExpressionException ex) {
            return n;
        }
    }
    
    @Override
    public String evaluateString(final IContext context, final String s) {
        try {
            return this.evaluateString(context);
        }
        catch (ExpressionException ex) {
            return s;
        }
    }
    
    @Override
    public boolean evaluateBoolean(final IContext context, final boolean b) {
        try {
            return this.evaluateBoolean(context);
        }
        catch (ExpressionException ex) {
            L.j.exception(ex);
            return b;
        }
    }
    
    @Override
    public void createSubtree(final IContext context, final IModelObjectFactory modelObjectFactory, final IChangeSet set) {
    }
    
    @Override
    public List<IModelObject> query(final List<IModelObject> list) {
        return this.query(NullContext.getInstance(), list);
    }
    
    @Override
    public IModelObject queryFirst() {
        return this.queryFirst(NullContext.getInstance());
    }
    
    @Override
    public List<IModelObject> query(final IModelObject modelObject, final List<IModelObject> list) {
        try {
            final List<IModelObject> evaluateNodes = this.evaluateNodes(new Context(modelObject));
            if (evaluateNodes == null) {
                return Collections.emptyList();
            }
            if (list == null) {
                return evaluateNodes;
            }
            list.addAll(evaluateNodes);
            return list;
        }
        catch (ExpressionException ex) {
            throw new IllegalStateException(ex.getMessage());
        }
    }
    
    @Override
    public IModelObject queryFirst(final IModelObject modelObject) {
        try {
            final List<IModelObject> evaluateNodes = this.evaluateNodes(new Context(modelObject));
            if (evaluateNodes == null || evaluateNodes.size() == 0) {
                return null;
            }
            return evaluateNodes.get(0);
        }
        catch (ExpressionException ex) {
            throw new IllegalStateException(ex.getMessage());
        }
    }
    
    @Override
    public List<IModelObject> query(final IContext context, final List<IModelObject> list) {
        try {
            final List<IModelObject> evaluateNodes = this.evaluateNodes(context);
            if (evaluateNodes == null) {
                return Collections.emptyList();
            }
            if (list == null) {
                return evaluateNodes;
            }
            list.addAll(evaluateNodes);
            return list;
        }
        catch (ExpressionException ex) {
            throw new IllegalStateException(ex.getMessage());
        }
    }
    
    @Override
    public IModelObject queryFirst(final IContext context) {
        try {
            final List<IModelObject> evaluateNodes = this.evaluateNodes(context);
            if (evaluateNodes == null || evaluateNodes.size() == 0) {
                return null;
            }
            return evaluateNodes.get(0);
        }
        catch (ExpressionException ex) {
            throw new IllegalStateException(ex.getMessage());
        }
    }
    
    @Override
    public void bind(final IContext context) {
        if (this.l != null) {
            final Iterator<IExpression> iterator = this.l.iterator();
            while (iterator.hasNext()) {
                iterator.next().bind(context);
            }
        }
    }
    
    @Override
    public void unbind(final IContext context) {
        if (this.l != null) {
            final Iterator<IExpression> iterator = this.l.iterator();
            while (iterator.hasNext()) {
                iterator.next().unbind(context);
            }
        }
    }
    
    public void rebind(final IContext context) {
        context.getModel().revert();
        this.unbind(context);
        context.getModel().restore();
        this.bind(context);
    }
    
    @Override
    public void addListener(final IContext context, final IExpressionListener expressionListener) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void removeListener(final IContext context, final IExpressionListener expressionListener) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void addNotifyListener(final IContext context, final IExpressionListener expressionListener) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void removeNotifyListener(final IContext context, final IExpressionListener expressionListener) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public ExpressionListenerList getListeners() {
        return null;
    }
    
    @Override
    public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
    }
    
    @Override
    public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final boolean b) {
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final double n, final double n2) {
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context) {
        if (this.k != null) {
            this.k.notifyChange(this, context);
        }
    }
    
    @Override
    public void handleException(final IExpression expression, final IContext context, final Exception ex) {
        if (this.k != null) {
            this.k.handleException(expression, context, ex);
        }
    }
    
    @Override
    public boolean requiresValueNotification(final IExpression expression) {
        return true;
    }
    
    @Override
    public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
        if (this.k != null) {
            this.k.notifyValue(this, array, modelObject, o, o2);
        }
    }
    
    @Override
    public Object clone() {
        final IExpression cloneOne = this.cloneOne();
        final List<IExpression> arguments = this.getArguments();
        if (arguments != null) {
            final Iterator<IExpression> iterator = arguments.iterator();
            while (iterator.hasNext()) {
                cloneOne.addArgument((IExpression)iterator.next().clone());
            }
        }
        return cloneOne;
    }
    
    protected IExpression cloneOne() {
        try {
            return (IExpression)this.getClass().newInstance();
        }
        catch (Exception ex) {
            L.j.exception(ex);
            return null;
        }
    }
    
    static /* synthetic */ int[] I() {
        final int[] i = L.i;
        if (i != null) {
            return i;
        }
        final int[] j = new int[ResultType.values().length];
        try {
            j[ResultType.BOOLEAN.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            j[ResultType.NODES.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError2) {}
        try {
            j[ResultType.NUMBER.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError3) {}
        try {
            j[ResultType.STRING.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError4) {}
        try {
            j[ResultType.UNDEFINED.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError5) {}
        return L.i = j;
    }
}
