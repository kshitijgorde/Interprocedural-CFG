// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.javasupport;

import org.jruby.exceptions.Unrescuable;
import org.jruby.exceptions.RaiseException;
import java.lang.reflect.Member;
import org.jruby.util.WeakIdentityHashMap;
import java.util.Collections;
import java.util.HashMap;
import org.jruby.RubyModule;
import java.util.concurrent.ConcurrentHashMap;
import org.jruby.RubyClass;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.javasupport.util.ObjectProxyCache;
import org.jruby.Ruby;
import java.util.Map;

public class JavaSupport
{
    private static final Map<String, Class> PRIMITIVE_CLASSES;
    private final Ruby runtime;
    private final ObjectProxyCache<IRubyObject, RubyClass> objectProxyCache;
    private boolean active;
    private final ConcurrentHashMap<Class, JavaClass> javaClassCache;
    private final Map matchCache;
    private RubyModule javaModule;
    private RubyModule javaUtilitiesModule;
    private RubyModule javaArrayUtilitiesModule;
    private RubyClass javaObjectClass;
    private JavaClass objectJavaClass;
    private RubyClass javaClassClass;
    private RubyClass javaArrayClass;
    private RubyClass javaProxyClass;
    private RubyClass arrayJavaProxyCreatorClass;
    private RubyClass javaFieldClass;
    private RubyClass javaMethodClass;
    private RubyClass javaConstructorClass;
    private RubyModule javaInterfaceTemplate;
    private RubyModule packageModuleTemplate;
    private RubyClass arrayProxyClass;
    private RubyClass concreteProxyClass;
    private RubyClass mapJavaProxy;
    private final Map<String, JavaClass> nameClassMap;
    private final Map<Object, Object[]> javaObjectVariables;
    
    public static Class getPrimitiveClass(final String primitiveType) {
        return JavaSupport.PRIMITIVE_CLASSES.get(primitiveType);
    }
    
    public JavaSupport(final Ruby ruby) {
        this.objectProxyCache = new ObjectProxyCache<IRubyObject, RubyClass>(ObjectProxyCache.ReferenceType.WEAK) {
            public IRubyObject allocateProxy(final Object javaObject, final RubyClass clazz) {
                return Java.allocateProxy(javaObject, clazz);
            }
        };
        this.javaClassCache = new ConcurrentHashMap<Class, JavaClass>(128);
        this.matchCache = Collections.synchronizedMap(new HashMap<Object, Object>(128));
        this.nameClassMap = new HashMap<String, JavaClass>();
        this.javaObjectVariables = (Map<Object, Object[]>)new WeakIdentityHashMap();
        this.runtime = ruby;
    }
    
    final Map getMatchCache() {
        return this.matchCache;
    }
    
    public boolean isActive() {
        return this.active;
    }
    
    public void setActive(final boolean active) {
        this.active = active;
    }
    
    private Class loadJavaClass(final String className) throws ClassNotFoundException {
        final Class primitiveClass;
        if ((primitiveClass = JavaSupport.PRIMITIVE_CLASSES.get(className)) != null) {
            return primitiveClass;
        }
        if (!Ruby.isSecurityRestricted()) {
            return Class.forName(className, true, this.runtime.getJRubyClassLoader());
        }
        return Class.forName(className);
    }
    
    public Class loadJavaClassVerbose(final String className) {
        try {
            return this.loadJavaClass(className);
        }
        catch (ClassNotFoundException cnfExcptn) {
            throw this.runtime.newNameError("cannot load Java class " + className, className, cnfExcptn);
        }
        catch (ExceptionInInitializerError eiie) {
            throw this.runtime.newNameError("cannot initialize Java class " + className, className, eiie);
        }
        catch (LinkageError le) {
            throw this.runtime.newNameError("cannot link Java class " + className + ", probable missing dependency: " + le.getLocalizedMessage(), className, le);
        }
        catch (SecurityException se) {
            if (this.runtime.isVerbose()) {
                se.printStackTrace(this.runtime.getErrorStream());
            }
            throw this.runtime.newSecurityError(se.getLocalizedMessage());
        }
    }
    
    public Class loadJavaClassQuiet(final String className) {
        try {
            return this.loadJavaClass(className);
        }
        catch (ClassNotFoundException cnfExcptn) {
            throw this.runtime.newNameError("cannot load Java class " + className, className, cnfExcptn, false);
        }
        catch (ExceptionInInitializerError eiie) {
            throw this.runtime.newNameError("cannot initialize Java class " + className, className, eiie, false);
        }
        catch (LinkageError le) {
            throw this.runtime.newNameError("cannot link Java class " + className, className, le, false);
        }
        catch (SecurityException se) {
            throw this.runtime.newSecurityError(se.getLocalizedMessage());
        }
    }
    
    public JavaClass getJavaClassFromCache(final Class clazz) {
        return this.javaClassCache.get(clazz);
    }
    
    public void putJavaClassIntoCache(final JavaClass clazz) {
        this.javaClassCache.put(clazz.javaClass(), clazz);
    }
    
    public void handleNativeException(final Throwable exception, final Member target) {
        if (exception instanceof RaiseException) {
            throw (RaiseException)exception;
        }
        if (exception instanceof Unrescuable) {
            if (exception instanceof Error) {
                throw (Error)exception;
            }
            if (exception instanceof RuntimeException) {
                throw (RuntimeException)exception;
            }
        }
        throw this.createRaiseException(exception, target);
    }
    
    private RaiseException createRaiseException(final Throwable exception, final Member target) {
        return RaiseException.createNativeRaiseException(this.runtime, exception, target);
    }
    
    public ObjectProxyCache<IRubyObject, RubyClass> getObjectProxyCache() {
        return this.objectProxyCache;
    }
    
    public Map<String, JavaClass> getNameClassMap() {
        return this.nameClassMap;
    }
    
    public void setJavaObjectVariable(final Object o, final int i, final Object v) {
        synchronized (this.javaObjectVariables) {
            Object[] vars = this.javaObjectVariables.get(o);
            if (vars == null) {
                vars = new Object[i + 1];
                this.javaObjectVariables.put(o, vars);
            }
            else if (vars.length <= i) {
                final Object[] newVars = new Object[i + 1];
                System.arraycopy(vars, 0, newVars, 0, vars.length);
                this.javaObjectVariables.put(o, newVars);
                vars = newVars;
            }
            vars[i] = v;
        }
    }
    
    public Object getJavaObjectVariable(final Object o, final int i) {
        if (i == -1) {
            return null;
        }
        synchronized (this.javaObjectVariables) {
            final Object[] vars = this.javaObjectVariables.get(o);
            if (vars == null || vars.length <= i) {
                return null;
            }
            return vars[i];
        }
    }
    
    public RubyModule getJavaModule() {
        final RubyModule module;
        if ((module = this.javaModule) != null) {
            return module;
        }
        return this.javaModule = this.runtime.fastGetModule("Java");
    }
    
    public RubyModule getJavaUtilitiesModule() {
        final RubyModule module;
        if ((module = this.javaUtilitiesModule) != null) {
            return module;
        }
        return this.javaUtilitiesModule = this.runtime.fastGetModule("JavaUtilities");
    }
    
    public RubyModule getJavaArrayUtilitiesModule() {
        final RubyModule module;
        if ((module = this.javaArrayUtilitiesModule) != null) {
            return module;
        }
        return this.javaArrayUtilitiesModule = this.runtime.fastGetModule("JavaArrayUtilities");
    }
    
    public RubyClass getJavaObjectClass() {
        final RubyClass clazz;
        if ((clazz = this.javaObjectClass) != null) {
            return clazz;
        }
        return this.javaObjectClass = this.getJavaModule().fastGetClass("JavaObject");
    }
    
    public JavaClass getObjectJavaClass() {
        return this.objectJavaClass;
    }
    
    public void setObjectJavaClass(final JavaClass objectJavaClass) {
        this.objectJavaClass = objectJavaClass;
    }
    
    public RubyClass getJavaArrayClass() {
        final RubyClass clazz;
        if ((clazz = this.javaArrayClass) != null) {
            return clazz;
        }
        return this.javaArrayClass = this.getJavaModule().fastGetClass("JavaArray");
    }
    
    public RubyClass getJavaClassClass() {
        final RubyClass clazz;
        if ((clazz = this.javaClassClass) != null) {
            return clazz;
        }
        return this.javaClassClass = this.getJavaModule().fastGetClass("JavaClass");
    }
    
    public RubyModule getJavaInterfaceTemplate() {
        final RubyModule module;
        if ((module = this.javaInterfaceTemplate) != null) {
            return module;
        }
        return this.javaInterfaceTemplate = this.runtime.fastGetModule("JavaInterfaceTemplate");
    }
    
    public RubyModule getPackageModuleTemplate() {
        final RubyModule module;
        if ((module = this.packageModuleTemplate) != null) {
            return module;
        }
        return this.packageModuleTemplate = this.runtime.fastGetModule("JavaPackageModuleTemplate");
    }
    
    public RubyClass getJavaProxyClass() {
        final RubyClass clazz;
        if ((clazz = this.javaProxyClass) != null) {
            return clazz;
        }
        return this.javaProxyClass = this.runtime.fastGetClass("JavaProxy");
    }
    
    public RubyClass getArrayJavaProxyCreatorClass() {
        final RubyClass clazz;
        if ((clazz = this.arrayJavaProxyCreatorClass) != null) {
            return clazz;
        }
        return this.arrayJavaProxyCreatorClass = this.runtime.fastGetClass("ArrayJavaProxyCreator");
    }
    
    public RubyClass getConcreteProxyClass() {
        final RubyClass clazz;
        if ((clazz = this.concreteProxyClass) != null) {
            return clazz;
        }
        return this.concreteProxyClass = this.runtime.fastGetClass("ConcreteJavaProxy");
    }
    
    public RubyClass getMapJavaProxyClass() {
        final RubyClass clazz;
        if ((clazz = this.mapJavaProxy) != null) {
            return clazz;
        }
        return this.mapJavaProxy = this.runtime.fastGetClass("MapJavaProxy");
    }
    
    public RubyClass getArrayProxyClass() {
        final RubyClass clazz;
        if ((clazz = this.arrayProxyClass) != null) {
            return clazz;
        }
        return this.arrayProxyClass = this.runtime.fastGetClass("ArrayJavaProxy");
    }
    
    public RubyClass getJavaFieldClass() {
        final RubyClass clazz;
        if ((clazz = this.javaFieldClass) != null) {
            return clazz;
        }
        return this.javaFieldClass = this.getJavaModule().fastGetClass("JavaField");
    }
    
    public RubyClass getJavaMethodClass() {
        final RubyClass clazz;
        if ((clazz = this.javaMethodClass) != null) {
            return clazz;
        }
        return this.javaMethodClass = this.getJavaModule().fastGetClass("JavaMethod");
    }
    
    public RubyClass getJavaConstructorClass() {
        final RubyClass clazz;
        if ((clazz = this.javaConstructorClass) != null) {
            return clazz;
        }
        return this.javaConstructorClass = this.getJavaModule().fastGetClass("JavaConstructor");
    }
    
    static {
        (PRIMITIVE_CLASSES = new HashMap<String, Class>()).put("boolean", Boolean.TYPE);
        JavaSupport.PRIMITIVE_CLASSES.put("byte", Byte.TYPE);
        JavaSupport.PRIMITIVE_CLASSES.put("char", Character.TYPE);
        JavaSupport.PRIMITIVE_CLASSES.put("short", Short.TYPE);
        JavaSupport.PRIMITIVE_CLASSES.put("int", Integer.TYPE);
        JavaSupport.PRIMITIVE_CLASSES.put("long", Long.TYPE);
        JavaSupport.PRIMITIVE_CLASSES.put("float", Float.TYPE);
        JavaSupport.PRIMITIVE_CLASSES.put("double", Double.TYPE);
    }
}
