// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.java.proxies;

import org.jruby.RubyMethod;
import org.jruby.java.invokers.InstanceMethodInvoker;
import org.jruby.java.invokers.StaticMethodInvoker;
import org.jruby.java.invokers.MethodInvoker;
import org.jruby.util.CodegenUtils;
import java.lang.reflect.Method;
import java.io.ObjectInputStream;
import java.io.InputStream;
import org.jruby.util.JRubyObjectInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.jruby.util.ByteList;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import org.jruby.RubyArray;
import org.jruby.javasupport.JavaMethod;
import java.util.Iterator;
import org.jruby.java.invokers.InstanceFieldSetter;
import org.jruby.java.invokers.InstanceFieldGetter;
import org.jruby.java.invokers.StaticFieldSetter;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.java.invokers.StaticFieldGetter;
import java.lang.reflect.Modifier;
import java.lang.reflect.Field;
import org.jruby.RubyHash;
import java.util.HashMap;
import java.util.Map;
import org.jruby.javasupport.JavaClass;
import org.jruby.RubyModule;
import org.jruby.javasupport.Java;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyClass;
import org.jruby.Ruby;
import org.jruby.javasupport.JavaObject;
import org.jruby.RubyObject;

public class JavaProxy extends RubyObject
{
    private static final boolean DEBUG = false;
    private JavaObject javaObject;
    protected Object object;
    
    public JavaProxy(final Ruby runtime, final RubyClass klazz) {
        super(runtime, klazz);
    }
    
    public Object dataGetStruct() {
        this.lazyJavaObject();
        return this.javaObject;
    }
    
    public void dataWrapStruct(final Object object) {
        this.javaObject = (JavaObject)object;
        this.object = this.javaObject.getValue();
    }
    
    public Object getObject() {
        if (this.object == null) {
            if (this.javaObject == null) {
                throw this.getRuntime().newRuntimeError("Java wrapper with no contents: " + this.getMetaClass().getName());
            }
            this.object = this.javaObject.getValue();
        }
        return this.object;
    }
    
    public void setObject(final Object object) {
        this.object = object;
    }
    
    private void lazyJavaObject() {
        if (this.javaObject == null) {
            this.javaObject = JavaObject.wrap(this.getRuntime(), this.object);
        }
    }
    
    public Class getJavaClass() {
        return this.object.getClass();
    }
    
    public static RubyClass createJavaProxy(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        final RubyClass javaProxy = runtime.defineClass("JavaProxy", runtime.getObject(), new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klazz) {
                return new JavaProxy(runtime, klazz);
            }
        });
        final RubyClass singleton = javaProxy.getSingletonClass();
        singleton.addReadWriteAttribute(context, "java_class");
        javaProxy.defineAnnotatedMethods(JavaProxy.class);
        javaProxy.includeModule(runtime.fastGetModule("JavaProxyMethods"));
        return javaProxy;
    }
    
    @JRubyMethod(frame = true, meta = true)
    public static IRubyObject inherited(final ThreadContext context, final IRubyObject recv, final IRubyObject subclass) {
        IRubyObject subJavaClass = RuntimeHelpers.invoke(context, subclass, "java_class");
        if (subJavaClass.isNil()) {
            subJavaClass = RuntimeHelpers.invoke(context, recv, "java_class");
            RuntimeHelpers.invoke(context, subclass, "java_class=", subJavaClass);
        }
        return RuntimeHelpers.invokeSuper(context, recv, subclass, Block.NULL_BLOCK);
    }
    
    @JRubyMethod(meta = true)
    public static IRubyObject singleton_class(final IRubyObject recv) {
        return ((RubyClass)recv).getSingletonClass();
    }
    
    @JRubyMethod(name = { "[]" }, meta = true, rest = true)
    public static IRubyObject op_aref(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        final IRubyObject javaClass = RuntimeHelpers.invoke(context, recv, "java_class");
        if (args.length > 0) {
            final ArrayJavaProxyCreator ajpc = new ArrayJavaProxyCreator(context.runtime);
            ajpc.setup(context, javaClass, args);
            return ajpc;
        }
        return Java.get_proxy_class(javaClass, RuntimeHelpers.invoke(context, javaClass, "array_class"));
    }
    
    public IRubyObject initialize_copy(final IRubyObject original) {
        super.initialize_copy(original);
        this.setObject(((JavaProxy)original).object);
        return this;
    }
    
    private static Class<?> getJavaClass(final ThreadContext context, final RubyModule module) {
        try {
            final IRubyObject jClass = RuntimeHelpers.invoke(context, module, "java_class");
            return (Class<?>)((jClass instanceof JavaClass) ? ((JavaClass)jClass).javaClass() : null);
        }
        catch (Exception e) {
            return null;
        }
    }
    
    private static Map<String, String> getFieldListFromArgs(final IRubyObject[] args) {
        final Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < args.length; ++i) {
            if (args[i] instanceof RubyHash) {
                ((RubyHash)args[i]).visitAll(new RubyHash.Visitor() {
                    public void visit(final IRubyObject key, final IRubyObject value) {
                        map.put(key.asString().toString(), value.asString().toString());
                    }
                });
            }
            else {
                final String value = args[i].asString().toString();
                map.put(value, value);
            }
        }
        return map;
    }
    
    private static void installField(final ThreadContext context, final Map<String, String> fieldMap, final Field field, final RubyModule module, final boolean asReader, final boolean asWriter) {
        final boolean isFinal = Modifier.isFinal(field.getModifiers());
        final Iterator<Map.Entry<String, String>> iter = fieldMap.entrySet().iterator();
        while (iter.hasNext()) {
            final Map.Entry<String, String> entry = iter.next();
            final String key = entry.getKey();
            if (key.equals(field.getName())) {
                if (Ruby.isSecurityRestricted() && !Modifier.isPublic(field.getModifiers())) {
                    throw context.getRuntime().newSecurityError("Cannot change accessibility on fields in a restricted mode: field '" + field.getName() + "'");
                }
                final String asName = entry.getValue();
                if (Modifier.isStatic(field.getModifiers())) {
                    if (asReader) {
                        module.getSingletonClass().addMethod(asName, new StaticFieldGetter(key, module, field));
                    }
                    if (asWriter) {
                        if (isFinal) {
                            throw context.getRuntime().newSecurityError("Cannot change final field '" + field.getName() + "'");
                        }
                        module.getSingletonClass().addMethod(asName + "=", new StaticFieldSetter(key, module, field));
                    }
                }
                else {
                    if (asReader) {
                        module.addMethod(asName, new InstanceFieldGetter(key, module, field));
                    }
                    if (asWriter) {
                        if (isFinal) {
                            throw context.getRuntime().newSecurityError("Cannot change final field '" + field.getName() + "'");
                        }
                        module.addMethod(asName + "=", new InstanceFieldSetter(key, module, field));
                    }
                }
                iter.remove();
                break;
            }
        }
    }
    
    private static void findFields(final ThreadContext context, final RubyModule topModule, final IRubyObject[] args, final boolean asReader, final boolean asWriter) {
        final Map<String, String> fieldMap = getFieldListFromArgs(args);
        for (RubyModule module = topModule; module != null; module = module.getSuperClass()) {
            final Class<?> javaClass = getJavaClass(context, module);
            if (javaClass != null) {
                final Field[] fields = JavaClass.getDeclaredFields(javaClass);
                for (int j = 0; j < fields.length; ++j) {
                    installField(context, fieldMap, fields[j], module, asReader, asWriter);
                }
            }
        }
        if (!fieldMap.isEmpty()) {
            throw JavaClass.undefinedFieldError(context.getRuntime(), topModule.getName(), fieldMap.keySet().iterator().next());
        }
    }
    
    @JRubyMethod(meta = true, rest = true)
    public static IRubyObject field_accessor(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        findFields(context, (RubyModule)recv, args, true, true);
        return context.getRuntime().getNil();
    }
    
    @JRubyMethod(meta = true, rest = true)
    public static IRubyObject field_reader(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        findFields(context, (RubyModule)recv, args, true, false);
        return context.getRuntime().getNil();
    }
    
    @JRubyMethod(meta = true, rest = true)
    public static IRubyObject field_writer(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        findFields(context, (RubyModule)recv, args, false, true);
        return context.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "equal?" })
    public IRubyObject equal_p(final ThreadContext context, final IRubyObject other) {
        final Ruby runtime = context.getRuntime();
        if (other instanceof JavaProxy) {
            final boolean equal = this.getObject() == ((JavaProxy)other).getObject();
            return runtime.newBoolean(equal);
        }
        return runtime.getFalse();
    }
    
    @JRubyMethod(backtrace = true)
    public IRubyObject java_send(final ThreadContext context, final IRubyObject rubyName) {
        final String name = rubyName.asJavaString();
        final Ruby runtime = context.getRuntime();
        final JavaMethod method = new JavaMethod(runtime, this.getMethod(name, new Class[0]));
        return method.invokeDirect(this.getObject());
    }
    
    @JRubyMethod(backtrace = true)
    public IRubyObject java_send(final ThreadContext context, final IRubyObject rubyName, final IRubyObject argTypes) {
        final String name = rubyName.asJavaString();
        final RubyArray argTypesAry = argTypes.convertToArray();
        final Ruby runtime = context.getRuntime();
        if (argTypesAry.size() != 0) {
            final Class[] argTypesClasses = (Class[])argTypesAry.toArray(new Class[argTypesAry.size()]);
            throw JavaMethod.newArgSizeMismatchError(runtime, argTypesClasses);
        }
        final JavaMethod method = new JavaMethod(runtime, this.getMethod(name, new Class[0]));
        return method.invokeDirect(this.getObject());
    }
    
    @JRubyMethod(backtrace = true)
    public IRubyObject java_send(final ThreadContext context, final IRubyObject rubyName, final IRubyObject argTypes, final IRubyObject arg0) {
        final String name = rubyName.asJavaString();
        final RubyArray argTypesAry = argTypes.convertToArray();
        final Ruby runtime = context.getRuntime();
        if (argTypesAry.size() != 1) {
            final Class[] argTypesClasses = (Class[])argTypesAry.toArray(new Class[argTypesAry.size()]);
            throw JavaMethod.newArgSizeMismatchError(runtime, argTypesClasses);
        }
        final Class argTypeClass = (Class)argTypesAry.eltInternal(0).toJava(Class.class);
        final JavaMethod method = new JavaMethod(runtime, this.getMethod(name, argTypeClass));
        return method.invokeDirect(this.getObject(), arg0.toJava(argTypeClass));
    }
    
    @JRubyMethod(required = 4, rest = true, backtrace = true)
    public IRubyObject java_send(final ThreadContext context, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        final String name = args[0].asJavaString();
        final RubyArray argTypesAry = args[1].convertToArray();
        final int argsLen = args.length - 2;
        if (argTypesAry.size() != argsLen) {
            final Class[] argTypesClasses = (Class[])argTypesAry.toArray(new Class[argTypesAry.size()]);
            throw JavaMethod.newArgSizeMismatchError(runtime, argTypesClasses);
        }
        final Class[] argTypesClasses = (Class[])argTypesAry.toArray(new Class[argsLen]);
        final Object[] argsAry = new Object[argsLen];
        for (int i = 0; i < argsLen; ++i) {
            argsAry[i] = args[i + 2].toJava(argTypesClasses[i]);
        }
        final JavaMethod method = new JavaMethod(runtime, this.getMethod(name, argTypesClasses));
        return method.invokeDirect(this.getObject(), argsAry);
    }
    
    @JRubyMethod(backtrace = true)
    public IRubyObject java_method(final ThreadContext context, final IRubyObject rubyName) {
        final String name = rubyName.asJavaString();
        return this.getRubyMethod(name, new Class[0]);
    }
    
    @JRubyMethod(backtrace = true)
    public IRubyObject java_method(final ThreadContext context, final IRubyObject rubyName, final IRubyObject argTypes) {
        final String name = rubyName.asJavaString();
        final RubyArray argTypesAry = argTypes.convertToArray();
        final Class[] argTypesClasses = (Class[])argTypesAry.toArray(new Class[argTypesAry.size()]);
        return this.getRubyMethod(name, argTypesClasses);
    }
    
    @JRubyMethod
    public IRubyObject marshal_dump() {
        if (Serializable.class.isAssignableFrom(this.object.getClass())) {
            try {
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                final ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(this.object);
                return this.getRuntime().newString(new ByteList(baos.toByteArray()));
            }
            catch (IOException ioe) {
                throw this.getRuntime().newIOErrorFromException(ioe);
            }
        }
        throw this.getRuntime().newTypeError("no marshal_dump is defined for class " + this.getJavaClass());
    }
    
    @JRubyMethod
    public IRubyObject marshal_load(final ThreadContext context, final IRubyObject str) {
        try {
            final ByteList byteList = str.convertToString().getByteList();
            final ByteArrayInputStream bais = new ByteArrayInputStream(byteList.getUnsafeBytes(), byteList.getBegin(), byteList.getRealSize());
            final ObjectInputStream ois = new JRubyObjectInputStream(context.getRuntime(), bais);
            this.object = ois.readObject();
            return this;
        }
        catch (IOException ioe) {
            throw context.getRuntime().newIOErrorFromException(ioe);
        }
        catch (ClassNotFoundException cnfe) {
            throw context.getRuntime().newTypeError("Class not found unmarshaling Java type: " + cnfe.getLocalizedMessage());
        }
    }
    
    protected int inspectHashCode() {
        return System.identityHashCode(this.object);
    }
    
    private Method getMethod(final String name, final Class... argTypes) {
        try {
            return this.getObject().getClass().getMethod(name, (Class<?>[])argTypes);
        }
        catch (NoSuchMethodException nsme) {
            throw JavaMethod.newMethodNotFoundError(this.getRuntime(), this.getObject().getClass(), name + CodegenUtils.prettyParams(argTypes), name);
        }
    }
    
    private MethodInvoker getMethodInvoker(final Method method) {
        if (Modifier.isStatic(method.getModifiers())) {
            return new StaticMethodInvoker(this.metaClass.getMetaClass(), method);
        }
        return new InstanceMethodInvoker(this.metaClass, method);
    }
    
    private RubyMethod getRubyMethod(final String name, final Class... argTypes) {
        final Method jmethod = this.getMethod(name, argTypes);
        if (Modifier.isStatic(jmethod.getModifiers())) {
            return RubyMethod.newMethod(this.metaClass.getSingletonClass(), CodegenUtils.prettyParams(argTypes), this.metaClass.getSingletonClass(), name, this.getMethodInvoker(jmethod), this.getMetaClass());
        }
        return RubyMethod.newMethod(this.metaClass, CodegenUtils.prettyParams(argTypes), this.metaClass, name, this.getMethodInvoker(jmethod), this);
    }
    
    public Object toJava(final Class type) {
        if (type.isAssignableFrom(this.getObject().getClass())) {
            if (Java.OBJECT_PROXY_CACHE) {
                this.getRuntime().getJavaSupport().getObjectProxyCache().put(this.getObject(), this);
            }
            return this.getObject();
        }
        return super.toJava(type);
    }
    
    public Object unwrap() {
        return this.getObject();
    }
}
