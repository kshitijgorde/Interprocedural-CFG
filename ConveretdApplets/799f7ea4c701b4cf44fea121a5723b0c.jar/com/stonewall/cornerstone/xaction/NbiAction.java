// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.xaction;

import java.lang.reflect.Method;
import org.xmodel.xpath.variable.IVariableScope;
import java.lang.reflect.Type;
import com.stonewall.cornerstone.entity.Entity;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.xmodel.xaction.XActionException;
import java.lang.reflect.ParameterizedType;
import org.xmodel.Xlate;
import java.util.ArrayList;
import org.xmodel.xpath.expression.IContext;
import java.util.List;
import org.xmodel.IModelObject;
import org.xmodel.xaction.Conventions;
import org.xmodel.xaction.XActionDocument;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.log.Log;
import org.xmodel.xaction.GuardedAction;

public class NbiAction extends GuardedAction
{
    private static final Log log;
    private String var;
    private IExpression serviceExpr;
    private IExpression[] argExprs;
    private static Map<String, Target> cache;
    
    static {
        log = Log.getLog(NbiAction.class);
        NbiAction.cache = Collections.synchronizedMap(new HashMap<String, Target>());
    }
    
    @Override
    public void configure(final XActionDocument document) {
        super.configure(document);
        this.var = Conventions.getVarName(document.getRoot(), false, new String[0]);
        this.serviceExpr = document.getExpression("service", true);
        final List<IModelObject> argNodes = document.getRoot().getChildren("arg");
        this.argExprs = new IExpression[argNodes.size()];
        for (int i = 0; i < this.argExprs.length; ++i) {
            this.argExprs[i] = document.getExpression(argNodes.get(i));
        }
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        final String service = this.serviceExpr.evaluateString(context);
        final Target target = this.getTarget(service);
        final Object[] args = new Object[this.argExprs.length];
        for (int i = 0; i < args.length; ++i) {
            final IExpression argExpr = this.argExprs[i];
            switch (target.argTypes[i]) {
                case node: {
                    args[i] = argExpr.queryFirst(context);
                    break;
                }
                case string: {
                    args[i] = argExpr.evaluateString(context);
                    break;
                }
                case number: {
                    args[i] = argExpr.evaluateNumber(context);
                    break;
                }
                case bool: {
                    args[i] = argExpr.evaluateBoolean(context);
                    break;
                }
                case entity: {
                    final Class[] argClasses = target.nbiMethod.getParameterTypes();
                    final Constructor<? extends Entity> constructor = this.getEntityConstructor(argClasses[i]);
                    args[i] = this.createEntity(constructor, argExpr.queryFirst(context));
                    break;
                }
                case nodeList: {
                    args[i] = argExpr.evaluateNodes(context);
                    break;
                }
                case stringList: {
                    final List<IModelObject> argValue = argExpr.evaluateNodes(context);
                    final List<String> newValue = new ArrayList<String>(argValue.size());
                    for (int j = 0; j < argValue.size(); ++j) {
                        newValue.add(Xlate.get((IModelObject)argValue.get(j), (String)null));
                    }
                    args[i] = newValue;
                    break;
                }
                case numberList: {
                    final List<IModelObject> argValue = argExpr.evaluateNodes(context);
                    final List<Number> newValue2 = new ArrayList<Number>(argValue.size());
                    for (int j = 0; j < argValue.size(); ++j) {
                        newValue2.add(Xlate.get(argValue.get(i), (Number)null));
                    }
                    args[i] = newValue2;
                    break;
                }
                case boolList: {
                    final List<IModelObject> argValue = argExpr.evaluateNodes(context);
                    final List<Boolean> newValue3 = new ArrayList<Boolean>(argValue.size());
                    for (int j = 0; j < argValue.size(); ++j) {
                        newValue3.add(Xlate.get(argValue.get(i), (Boolean)null));
                    }
                    args[i] = newValue3;
                    break;
                }
                case entityList: {
                    final ParameterizedType paramType = (ParameterizedType)target.nbiMethod.getGenericParameterTypes()[i];
                    final Type[] genericTypes = paramType.getActualTypeArguments();
                    final Class<? extends Entity> genericClass = (Class<? extends Entity>)genericTypes[0];
                    final Constructor<? extends Entity> constructor2 = this.getEntityConstructor(genericClass);
                    final List<IModelObject> argValue2 = argExpr.evaluateNodes(context);
                    final List<Object> newValue4 = new ArrayList<Object>(argValue2.size());
                    for (int k = 0; k < argValue2.size(); ++k) {
                        newValue4.add(this.createEntity(constructor2, argValue2.get(i)));
                    }
                    args[i] = newValue4;
                    break;
                }
            }
        }
        try {
            final Object nbi = target.nbiClass.newInstance();
            final Object result = target.nbiMethod.invoke(nbi, args);
            if (this.var != null) {
                final IVariableScope scope = context.getScope();
                if (scope != null) {
                    scope.set(this.var, this.convertReturnType(result));
                }
            }
        }
        catch (InstantiationException e) {
            throw new XActionException(String.format("Unable to instantiate NBI class: %s.", service));
        }
        catch (InvocationTargetException e2) {
            throw new XActionException(String.format("Unable to invoke service method: %s.", service));
        }
        catch (IllegalAccessException e3) {
            throw new XActionException(String.format("Access denied to service method: %s.", service));
        }
        catch (IllegalArgumentException e4) {
            throw new XActionException(String.format("Illegal argument to service method: %s.", service));
        }
        return null;
    }
    
    private Target getTarget(final String service) {
        Target target = NbiAction.cache.get(service);
        if (target == null) {
            target = new Target(null);
            target.nbiClass = this.getNBI(service);
            if (target.nbiClass == null) {
                throw new XActionException(String.format("Service request does not identify valid NBI class: %s.", service));
            }
            target.nbiMethod = this.getMethod(target.nbiClass, service);
            if (target.nbiMethod == null) {
                throw new XActionException(String.format("Service request does not identify valid NBI method: %s.", service));
            }
            final Type[] argTypes = target.nbiMethod.getGenericParameterTypes();
            if (argTypes.length != this.argExprs.length) {
                throw new XActionException(String.format("Service request has the wrong number of parameters: %s.", service));
            }
            target.argTypes = new ArgType[argTypes.length];
            for (int i = 0; i < argTypes.length; ++i) {
                final ArgType argType = this.getArgumentType(argTypes[i]);
                target.argTypes[i] = argType;
            }
            NbiAction.cache.put(service, target);
        }
        return target;
    }
    
    private Class<?> getNBI(final String service) {
        final int index = service.lastIndexOf(46);
        if (index >= 0) {
            String name = service.substring(0, index);
            name = name.replace("~.", "com.stonewall.cornerstone.");
            name = name.replace("~", "com.stonewall.cornerstone.");
            try {
                return NbiAction.class.getClassLoader().loadClass(name);
            }
            catch (ClassNotFoundException e) {
                NbiAction.log.error(e);
            }
            catch (ClassCastException e2) {
                NbiAction.log.error(e2);
            }
        }
        return null;
    }
    
    private Method getMethod(final Class<?> nbi, final String service) {
        int index = service.lastIndexOf(46);
        if (index++ >= 0 && index < service.length()) {
            final String name = service.substring(index);
            Method[] methods;
            for (int length = (methods = nbi.getMethods()).length, i = 0; i < length; ++i) {
                final Method method = methods[i];
                if (method.getName().equals(name)) {
                    return method;
                }
            }
        }
        return null;
    }
    
    private ArgType getArgumentType(final Type argType) {
        if (argType instanceof ParameterizedType) {
            final ParameterizedType paramType = (ParameterizedType)argType;
            final Class<?> rawType = (Class<?>)paramType.getRawType();
            if (!List.class.isAssignableFrom(rawType)) {
                return ArgType.unknown;
            }
            final Type[] genericTypes = paramType.getActualTypeArguments();
            final Class<?> genericClass = (Class<?>)genericTypes[0];
            if (IModelObject.class.isAssignableFrom(genericClass)) {
                return ArgType.nodeList;
            }
            if (String.class.isAssignableFrom(genericClass)) {
                return ArgType.stringList;
            }
            if (Number.class.isAssignableFrom(genericClass)) {
                return ArgType.numberList;
            }
            if (Boolean.class.isAssignableFrom(genericClass)) {
                return ArgType.boolList;
            }
            if (this.getEntityConstructor(genericClass) != null) {
                return ArgType.entityList;
            }
            throw new XActionException(String.format("Unsupported argument list type: %s.", paramType.toString()));
        }
        else {
            final Class<?> argClass = (Class<?>)argType;
            if (IModelObject.class.isAssignableFrom(argClass)) {
                return ArgType.node;
            }
            if (String.class.isAssignableFrom(argClass)) {
                return ArgType.string;
            }
            if (Number.class.isAssignableFrom(argClass)) {
                return ArgType.number;
            }
            if (Boolean.class.isAssignableFrom(argClass)) {
                return ArgType.bool;
            }
            if (this.getEntityConstructor(argClass) != null) {
                return ArgType.entity;
            }
            throw new XActionException(String.format("Unsupported argument class: %s.", argClass.toString()));
        }
    }
    
    private Constructor<? extends Entity> getEntityConstructor(final Class<?> clazz) {
        try {
            return (Constructor<? extends Entity>)clazz.getConstructor(IModelObject.class);
        }
        catch (Exception e) {
            return null;
        }
    }
    
    private Entity createEntity(final Constructor<? extends Entity> constructor, final IModelObject root) {
        try {
            return (Entity)constructor.newInstance(root);
        }
        catch (InstantiationException e) {
            throw new XActionException(String.format("Unable to instantiate Entity class: %s.", root.getType()));
        }
        catch (InvocationTargetException e2) {
            throw new XActionException(String.format("Unable to invoke Entity constructor: %s.", root.getType()));
        }
        catch (IllegalAccessException e3) {
            throw new XActionException(String.format("Access denied to Entity constructor: %s.", root.getType()));
        }
    }
    
    private Object convertReturnType(final Object object) {
        if (object instanceof Entity) {
            return ((Entity)object).getRoot();
        }
        if (object instanceof IModelObject) {
            return Collections.singletonList(object);
        }
        if (object instanceof String) {
            return object;
        }
        if (object instanceof Number) {
            return object;
        }
        if (object instanceof Boolean) {
            return object;
        }
        try {
            final Method method = object.getClass().getMethod("getRoot", (Class<?>[])new Class[0]);
            return Collections.singletonList(method.invoke(object, new Object[0]));
        }
        catch (NoSuchMethodException ex) {}
        catch (Exception e) {
            NbiAction.log.error(e);
        }
        return object.toString();
    }
    
    private enum ArgType
    {
        node("node", 0), 
        string("string", 1), 
        number("number", 2), 
        bool("bool", 3), 
        entity("entity", 4), 
        nodeList("nodeList", 5), 
        stringList("stringList", 6), 
        numberList("numberList", 7), 
        boolList("boolList", 8), 
        entityList("entityList", 9), 
        unknown("unknown", 10);
        
        private ArgType(final String s, final int n) {
        }
    }
    
    private static class Target
    {
        public Class<?> nbiClass;
        public Method nbiMethod;
        public ArgType[] argTypes;
    }
}
