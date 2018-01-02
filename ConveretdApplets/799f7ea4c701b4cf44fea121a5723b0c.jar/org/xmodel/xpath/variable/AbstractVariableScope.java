// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.variable;

import org.xmodel.A.F;
import org.xmodel.A.B;
import java.util.HashMap;
import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.IModel;
import org.xmodel.ModelRegistry;
import org.xmodel.xpath.expression.P;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Collection;
import java.util.Iterator;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.expression.ExpressionListener;
import java.util.Map;
import org.xmodel.xpath.expression.IExpressionListener;

public abstract class AbstractVariableScope implements IVariableScope
{
    final IExpressionListener F;
    private IVariableSource G;
    protected Map<String, Variable> variables;
    private static /* synthetic */ int[] E;
    
    public AbstractVariableScope() {
        this.F = new ExpressionListener() {
            @Override
            public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
                final Variable variable = AbstractVariableScope.this.getVariable(expression);
                final Iterator iterator = AbstractVariableScope.this.A(variable, context).iterator();
                while (iterator.hasNext()) {
                    iterator.next().listener.notifyAdd(variable.name, AbstractVariableScope.this, context, list);
                }
            }
            
            @Override
            public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
                final Variable variable = AbstractVariableScope.this.getVariable(expression);
                final Iterator iterator = AbstractVariableScope.this.A(variable, context).iterator();
                while (iterator.hasNext()) {
                    iterator.next().listener.notifyRemove(variable.name, AbstractVariableScope.this, context, list);
                }
            }
            
            @Override
            public void notifyChange(final IExpression expression, final IContext context, final boolean b) {
                final Variable variable = AbstractVariableScope.this.getVariable(expression);
                final Iterator iterator = AbstractVariableScope.this.A(variable, context).iterator();
                while (iterator.hasNext()) {
                    iterator.next().listener.notifyChange(variable.name, AbstractVariableScope.this, context, b);
                }
            }
            
            @Override
            public void notifyChange(final IExpression expression, final IContext context, final double n, final double n2) {
                final Variable variable = AbstractVariableScope.this.getVariable(expression);
                final Iterator iterator = AbstractVariableScope.this.A(variable, context).iterator();
                while (iterator.hasNext()) {
                    iterator.next().listener.notifyChange(variable.name, AbstractVariableScope.this, context, n, n2);
                }
            }
            
            @Override
            public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
                final Variable variable = AbstractVariableScope.this.getVariable(expression);
                final Iterator iterator = AbstractVariableScope.this.A(variable, context).iterator();
                while (iterator.hasNext()) {
                    iterator.next().listener.notifyChange(variable.name, AbstractVariableScope.this, context, s, s2);
                }
            }
            
            @Override
            public boolean requiresValueNotification() {
                return false;
            }
        };
    }
    
    @Override
    public void internal_setSource(final IVariableSource g) {
        this.G = g;
    }
    
    @Override
    public IVariableSource getSource() {
        return this.G;
    }
    
    @Override
    public Collection<String> getVariables() {
        if (this.variables == null) {
            return (Collection<String>)Collections.emptyList();
        }
        return this.variables.keySet();
    }
    
    @Override
    public Object set(final String s, final Object o) {
        return this.internal_set(s, o);
    }
    
    @Override
    public void insert(final String s, final Object o) {
        this.insert(s, o, Integer.MAX_VALUE);
    }
    
    @Override
    public void insert(final String s, final Object o, int size) {
        final Variable createVariable = this.getCreateVariable(s);
        if (createVariable.value == null) {
            createVariable.value = new ArrayList();
        }
        if (!(createVariable.value instanceof List)) {
            throw new IllegalStateException("Variable does not contain a sequence.");
        }
        List<Object> value = (List<Object>)createVariable.value;
        if (size == Integer.MAX_VALUE) {
            size = value.size();
        }
        if (!(value instanceof ArrayList)) {
            value = new ArrayList<Object>();
            createVariable.value = value;
        }
        value.add(size, o);
        final List<IModelObject> singletonList = Collections.singletonList(o);
        if (createVariable.bindings != null) {
            Binding[] array;
            for (int length = (array = createVariable.bindings.toArray(new Binding[0])).length, i = 0; i < length; ++i) {
                final Binding binding = array[i];
                binding.listener.notifyAdd(s, this, binding.context, singletonList);
            }
        }
    }
    
    @Override
    public void remove(final String s, final Object o) {
        final Variable variable = this.variables.get(s);
        if (variable == null) {
            return;
        }
        if (!(variable.value instanceof List)) {
            throw new IllegalStateException("Variable does not contain a sequence.");
        }
        final List<IModelObject> singletonList = Collections.singletonList(o);
        if (variable.bindings != null) {
            Binding[] array;
            for (int length = (array = variable.bindings.toArray(new Binding[0])).length, i = 0; i < length; ++i) {
                final Binding binding = array[i];
                binding.listener.notifyRemove(s, this, binding.context, singletonList);
            }
        }
        ((List)variable.value).remove(o);
    }
    
    @Override
    public void remove(final String s, final int n) {
        final Variable variable = this.variables.get(s);
        if (variable == null) {
            return;
        }
        if (!(variable.value instanceof List)) {
            throw new IllegalStateException("Variable does not contain a sequence.");
        }
        final List<IModelObject> singletonList = Collections.singletonList(((List)variable.value).remove(n));
        if (variable.bindings != null) {
            Binding[] array;
            for (int length = (array = variable.bindings.toArray(new Binding[0])).length, i = 0; i < length; ++i) {
                final Binding binding = array[i];
                binding.listener.notifyRemove(s, this, binding.context, singletonList);
            }
        }
    }
    
    @Override
    public List<IModelObject> set(final String s, final IModelObject modelObject) {
        final Object internal_set = this.internal_set(s, (modelObject != null) ? Collections.singletonList(modelObject) : Collections.emptyList());
        if (internal_set instanceof List) {
            return (List<IModelObject>)internal_set;
        }
        return Collections.emptyList();
    }
    
    @Override
    public List<IModelObject> set(final String s, final List<IModelObject> list) {
        final Object internal_set = this.internal_set(s, list);
        if (internal_set instanceof List) {
            return (List<IModelObject>)internal_set;
        }
        return Collections.emptyList();
    }
    
    @Override
    public Boolean set(final String s, final Boolean b) {
        final Object internal_set = this.internal_set(s, b);
        if (internal_set instanceof Boolean) {
            return (Boolean)internal_set;
        }
        return Boolean.FALSE;
    }
    
    @Override
    public Number set(final String s, final Number n) {
        final Object internal_set = this.internal_set(s, n);
        if (internal_set instanceof Number) {
            return (Number)internal_set;
        }
        return 0;
    }
    
    @Override
    public String set(final String s, final String s2) {
        final Object internal_set = this.internal_set(s, s2);
        if (internal_set instanceof String) {
            return (String)internal_set;
        }
        return "";
    }
    
    protected Object internal_set(final String s, final Object o) {
        final Variable createVariable = this.getCreateVariable(s);
        if (createVariable.value == null) {
            createVariable.value = o;
            return null;
        }
        if (createVariable.value != o) {
            final Object value = createVariable.value;
            this.A(createVariable, s, createVariable.value = o, value);
            return value;
        }
        return o;
    }
    
    @Override
    public void define(final String s, final IExpression value) {
        if (this.variables != null) {
            final Variable variable = this.variables.get(s);
            if (variable != null && variable.bindings != null && variable.bindings.size() > 0) {
                throw new IllegalArgumentException("$" + s + " already defined in scope:\n" + this.toString());
            }
            for (final Map.Entry<String, Variable> entry : this.variables.entrySet()) {
                if (entry.getValue() == value) {
                    throw new IllegalArgumentException("Expression is referenced by another variable ($" + entry.getKey() + ") in scope:\n" + this.toString());
                }
            }
        }
        if (!(value instanceof P)) {
            throw new IllegalArgumentException("Assigned expression must be a RootExpression instance.");
        }
        final IVariableSource variableSource = value.getVariableSource();
        if (variableSource != this.G) {
            variableSource.setParent(this.G);
        }
        this.getCreateVariable(s).value = value;
    }
    
    @Override
    public void clear(final String s) {
        if (this.variables != null) {
            this.variables.remove(s);
        }
    }
    
    @Override
    public void copyFrom(final IVariableScope variableScope) {
        for (final String s : variableScope.getAll()) {
            this.internal_set(s, variableScope.get(s));
        }
    }
    
    private void A(final Variable variable, final String s, final Object o, final Object o2) {
        if (variable.bindings == null) {
            return;
        }
        final Binding[] array = new Binding[variable.bindings.size()];
        for (int i = 0; i < variable.bindings.size(); ++i) {
            array[i] = variable.bindings.get(i);
        }
        final IModel model = ModelRegistry.getInstance().getModel();
        model.startUpdate().A(this, s, o, o2);
        try {
            if (o instanceof List) {
                final List list = (List)o;
                final List list2 = (List)o2;
                final ArrayList list3 = new ArrayList<IModelObject>(list.size());
                for (final IModelObject modelObject : list2) {
                    if (!list.contains(modelObject)) {
                        list3.add(modelObject);
                    }
                }
                if (list3.size() > 0) {
                    Binding[] array2;
                    for (int length = (array2 = array).length, j = 0; j < length; ++j) {
                        final Binding binding = array2[j];
                        binding.listener.notifyRemove(s, this, binding.context, (List<IModelObject>)list3);
                    }
                }
                final ArrayList list4 = new ArrayList<IModelObject>(list.size());
                for (final IModelObject modelObject2 : list) {
                    if (!list2.contains(modelObject2)) {
                        list4.add(modelObject2);
                    }
                }
                if (list4.size() > 0) {
                    Binding[] array3;
                    for (int length2 = (array3 = array).length, k = 0; k < length2; ++k) {
                        final Binding binding2 = array3[k];
                        binding2.listener.notifyAdd(s, this, binding2.context, (List<IModelObject>)list4);
                    }
                }
            }
            else if (o instanceof String) {
                Binding[] array4;
                for (int length3 = (array4 = array).length, l = 0; l < length3; ++l) {
                    final Binding binding3 = array4[l];
                    binding3.listener.notifyChange(s, this, binding3.context, (String)o, (String)o2);
                }
            }
            else if (o instanceof Number) {
                Binding[] array5;
                for (int length4 = (array5 = array).length, n = 0; n < length4; ++n) {
                    final Binding binding4 = array5[n];
                    binding4.listener.notifyChange(s, this, binding4.context, (Number)o, (Number)o2);
                }
            }
            else if (o instanceof Boolean) {
                Binding[] array6;
                for (int length5 = (array6 = array).length, n2 = 0; n2 < length5; ++n2) {
                    final Binding binding5 = array6[n2];
                    binding5.listener.notifyChange(s, this, binding5.context, (Boolean)o);
                }
            }
        }
        finally {
            model.endUpdate();
        }
        model.endUpdate();
    }
    
    @Override
    public Object get(final String s) {
        if (this.variables == null) {
            return null;
        }
        final Variable variable = this.variables.get(s);
        if (variable == null) {
            return null;
        }
        return variable.value;
    }
    
    @Override
    public Object get(final String s, final IContext context) throws ExpressionException {
        if (this.variables == null) {
            return null;
        }
        final Variable variable = this.variables.get(s);
        if (variable == null) {
            return null;
        }
        if (variable.value instanceof IExpression) {
            final IExpression expression = (IExpression)variable.value;
            if (expression != null) {
                switch (A()[expression.getType(context).ordinal()]) {
                    case 1: {
                        return expression.evaluateNodes(context);
                    }
                    case 3: {
                        return expression.evaluateNumber(context);
                    }
                    case 2: {
                        return expression.evaluateString(context);
                    }
                    case 4: {
                        return expression.evaluateBoolean(context);
                    }
                }
            }
            return null;
        }
        return variable.value;
    }
    
    @Override
    public Collection<String> getAll() {
        if (this.variables == null) {
            return (Collection<String>)Collections.emptyList();
        }
        return this.variables.keySet();
    }
    
    @Override
    public boolean isDefined(final String s) {
        return this.variables != null && this.variables.get(s) != null;
    }
    
    @Override
    public boolean isBound(final String s) {
        if (this.variables == null) {
            return false;
        }
        final Variable variable = this.variables.get(s);
        return variable != null && (variable.bindings != null && variable.bindings.size() > 0);
    }
    
    @Override
    public IExpression.ResultType getType(final String s) {
        return this.getType(s, null);
    }
    
    @Override
    public IExpression.ResultType getType(final String s, final IContext context) {
        if (this.variables == null) {
            return IExpression.ResultType.UNDEFINED;
        }
        final Variable variable = this.variables.get(s);
        if (variable == null) {
            return IExpression.ResultType.UNDEFINED;
        }
        return getType(variable.value, context);
    }
    
    public static IExpression.ResultType getType(final Object o, final IContext context) {
        if (o instanceof List) {
            return IExpression.ResultType.NODES;
        }
        if (o instanceof Number) {
            return IExpression.ResultType.NUMBER;
        }
        if (o instanceof Boolean) {
            return IExpression.ResultType.BOOLEAN;
        }
        if (!(o instanceof IExpression)) {
            return IExpression.ResultType.STRING;
        }
        if (context != null) {
            return ((IExpression)o).getType(context);
        }
        return ((IExpression)o).getType();
    }
    
    protected Variable getCreateVariable(final String name) {
        if (this.variables == null) {
            this.variables = new HashMap<String, Variable>(3);
        }
        Variable variable = this.variables.get(name);
        if (variable == null) {
            variable = new Variable();
            variable.name = name;
            this.variables.put(name, variable);
        }
        return variable;
    }
    
    protected Variable getVariable(final IExpression expression) {
        for (final Map.Entry<String, Variable> entry : this.variables.entrySet()) {
            if (entry.getValue().value == expression) {
                return entry.getValue();
            }
        }
        return null;
    }
    
    @Override
    public void addListener(final String s, final IContext context, final IVariableListener variableListener) {
        final Variable variable = (this.variables != null) ? this.variables.get(s) : null;
        if (variable == null) {
            throw new IllegalArgumentException("$" + s + " is not defined in scope:\n" + this.toString());
        }
        variable.addBinding(new Binding(context, variableListener));
        if (variable.value instanceof IExpression) {
            ((IExpression)variable.value).addListener(context, this.F);
        }
    }
    
    @Override
    public void removeListener(final String s, final IContext context, final IVariableListener variableListener) {
        final Variable variable = this.variables.get(s);
        if (variable == null) {
            throw new IllegalArgumentException("$" + s + " is not defined in scope:\n" + this.toString());
        }
        if (variable.removeBinding(context, variableListener) && variable.value instanceof IExpression) {
            ((IExpression)variable.value).removeListener(context, this.F);
        }
    }
    
    @Override
    public void revert(final B b) {
        final F f = (F)b;
        final Variable variable = this.variables.get(f.N);
        if (variable != null) {
            variable.value = f.K;
        }
    }
    
    @Override
    public void restore(final B b) {
        final F f = (F)b;
        final Variable variable = this.variables.get(f.N);
        if (variable != null) {
            variable.value = f.M;
        }
    }
    
    private List<Binding> A(final Variable variable, final IContext context) {
        final ArrayList<Binding> list = new ArrayList<Binding>();
        for (final Binding binding : variable.bindings) {
            if (binding.context == context) {
                list.add(binding);
            }
        }
        return list;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        if (this.variables != null) {
            final ArrayList<String> list = new ArrayList<String>(this.getAll());
            Collections.sort((List<Comparable>)list);
            for (final String s : list) {
                sb.append(s);
                sb.append("=");
                final Object value = this.get(s);
                if (value instanceof IExpression) {
                    sb.append("{");
                    sb.append(value);
                    sb.append("}");
                }
                else if (value instanceof List) {
                    sb.append(value);
                }
                else if (value != null) {
                    sb.append("'");
                    sb.append(value);
                    sb.append("'");
                }
                else {
                    sb.append("null, ");
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }
    
    static /* synthetic */ int[] A() {
        final int[] e = AbstractVariableScope.E;
        if (e != null) {
            return e;
        }
        final int[] e2 = new int[IExpression.ResultType.values().length];
        try {
            e2[IExpression.ResultType.BOOLEAN.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            e2[IExpression.ResultType.NODES.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError2) {}
        try {
            e2[IExpression.ResultType.NUMBER.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError3) {}
        try {
            e2[IExpression.ResultType.STRING.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError4) {}
        try {
            e2[IExpression.ResultType.UNDEFINED.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError5) {}
        return AbstractVariableScope.E = e2;
    }
    
    protected class Binding
    {
        public IContext context;
        public IVariableListener listener;
        
        public Binding(final IContext context, final IVariableListener listener) {
            this.context = context;
            this.listener = listener;
        }
    }
    
    protected class Variable
    {
        public String name;
        public Object value;
        public List<Binding> bindings;
        
        public void addBinding(final Binding binding) {
            if (this.bindings == null) {
                this.bindings = new ArrayList<Binding>(1);
            }
            this.bindings.add(binding);
        }
        
        public boolean removeBinding(final IContext context, final IVariableListener variableListener) {
            if (this.bindings != null) {
                for (final Binding binding : this.bindings) {
                    if (binding.context.equals(context) && binding.listener.equals(variableListener)) {
                        this.bindings.remove(binding);
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
