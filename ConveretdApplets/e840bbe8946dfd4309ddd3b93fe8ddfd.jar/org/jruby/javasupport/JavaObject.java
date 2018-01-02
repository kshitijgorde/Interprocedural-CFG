// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.javasupport;

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
import org.jruby.runtime.Block;
import org.jruby.java.proxies.JavaProxy;
import org.jruby.RubyString;
import org.jruby.RubyFixnum;
import org.jruby.RubyModule;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.RubyClass;
import org.jruby.anno.JRubyClass;
import org.jruby.RubyObject;

@JRubyClass(name = { "Java::JavaObject" })
public class JavaObject extends RubyObject
{
    private static Object NULL_LOCK;
    private final RubyClass.VariableAccessor objectAccessor;
    private static final ObjectAllocator JAVA_OBJECT_ALLOCATOR;
    
    protected JavaObject(final Ruby runtime, final RubyClass rubyClass, final Object value) {
        super(runtime, rubyClass);
        this.objectAccessor = rubyClass.getVariableAccessorForWrite("__wrap_struct__");
        this.dataWrapStruct(value);
    }
    
    public Object dataGetStruct() {
        return this.objectAccessor.get(this);
    }
    
    public void dataWrapStruct(final Object object) {
        this.objectAccessor.set(this, object);
    }
    
    protected JavaObject(final Ruby runtime, final Object value) {
        this(runtime, runtime.getJavaSupport().getJavaObjectClass(), value);
    }
    
    public static JavaObject wrap(final Ruby runtime, final Object value) {
        if (value != null) {
            if (value instanceof Class) {
                return JavaClass.get(runtime, (Class<?>)value);
            }
            if (value.getClass().isArray()) {
                return new JavaArray(runtime, value);
            }
        }
        return new JavaObject(runtime, value);
    }
    
    @JRubyMethod(meta = true)
    public static IRubyObject wrap(final ThreadContext context, final IRubyObject self, final IRubyObject object) {
        final Ruby runtime = context.getRuntime();
        final Object obj = getWrappedObject(object, JavaObject.NEVER);
        if (obj == JavaObject.NEVER) {
            return runtime.getNil();
        }
        return wrap(runtime, obj);
    }
    
    public Class<?> getJavaClass() {
        final Object dataStruct = this.dataGetStruct();
        return (dataStruct != null) ? dataStruct.getClass() : Void.TYPE;
    }
    
    public Object getValue() {
        return this.dataGetStruct();
    }
    
    public static RubyClass createJavaObjectClass(final Ruby runtime, final RubyModule javaModule) {
        final RubyClass result = javaModule.defineClassUnder("JavaObject", runtime.getObject(), JavaObject.JAVA_OBJECT_ALLOCATOR);
        registerRubyMethods(runtime, result);
        result.getMetaClass().undefineMethod("new");
        result.getMetaClass().undefineMethod("allocate");
        return result;
    }
    
    protected static void registerRubyMethods(final Ruby runtime, final RubyClass result) {
        result.defineAnnotatedMethods(JavaObject.class);
    }
    
    public boolean equals(final Object other) {
        final Object myValue = this.getValue();
        Object otherValue = other;
        if (other instanceof IRubyObject) {
            otherValue = getWrappedObject((IRubyObject)other, JavaObject.NEVER);
        }
        return otherValue != JavaObject.NEVER && myValue == otherValue;
    }
    
    public int hashCode() {
        final Object dataStruct = this.dataGetStruct();
        if (dataStruct != null) {
            return dataStruct.hashCode();
        }
        return 0;
    }
    
    @JRubyMethod
    public RubyFixnum hash() {
        return this.getRuntime().newFixnum(this.hashCode());
    }
    
    @JRubyMethod
    public IRubyObject to_s() {
        return to_s(this.getRuntime(), this.dataGetStruct());
    }
    
    public static IRubyObject to_s(final Ruby runtime, final Object dataStruct) {
        if (dataStruct == null) {
            return RubyString.newEmptyString(runtime);
        }
        final String stringValue = dataStruct.toString();
        if (stringValue != null) {
            return RubyString.newUnicodeString(runtime, dataStruct.toString());
        }
        return runtime.getNil();
    }
    
    @JRubyMethod(name = { "==", "eql?" }, required = 1)
    public IRubyObject op_equal(final IRubyObject other) {
        final Object myValue = this.getValue();
        return opEqualShared(myValue, other);
    }
    
    public static IRubyObject op_equal(final JavaProxy self, final IRubyObject other) {
        final Object myValue = self.getObject();
        return opEqualShared(myValue, other);
    }
    
    private static IRubyObject opEqualShared(final Object myValue, final IRubyObject other) {
        final Ruby runtime = other.getRuntime();
        final Object otherValue = getWrappedObject(other, JavaObject.NEVER);
        if (other == JavaObject.NEVER) {
            return runtime.getFalse();
        }
        if (myValue == null && otherValue == null) {
            return runtime.getTrue();
        }
        return runtime.newBoolean(myValue.equals(otherValue));
    }
    
    @JRubyMethod(name = { "equal?" }, required = 1)
    public IRubyObject same(final IRubyObject other) {
        final Ruby runtime = this.getRuntime();
        final Object myValue = this.getValue();
        final Object otherValue = getWrappedObject(other, JavaObject.NEVER);
        if (other == JavaObject.NEVER) {
            return runtime.getFalse();
        }
        if (myValue == null && otherValue == null) {
            return this.getRuntime().getTrue();
        }
        final boolean isSame = this.getValue() == ((JavaObject)other).getValue();
        return isSame ? this.getRuntime().getTrue() : this.getRuntime().getFalse();
    }
    
    private static Object getWrappedObject(final IRubyObject other, final Object def) {
        if (other instanceof JavaObject) {
            return ((JavaObject)other).getValue();
        }
        if (other instanceof JavaProxy) {
            return ((JavaProxy)other).getObject();
        }
        return def;
    }
    
    @JRubyMethod
    public RubyString java_type() {
        return this.getRuntime().newString(this.getJavaClass().getName());
    }
    
    @JRubyMethod
    public IRubyObject java_class() {
        return JavaClass.get(this.getRuntime(), this.getJavaClass());
    }
    
    @JRubyMethod
    public RubyFixnum length() {
        throw this.getRuntime().newTypeError("not a java array");
    }
    
    @JRubyMethod(name = { "[]" }, required = 1)
    public IRubyObject aref(final IRubyObject index) {
        throw this.getRuntime().newTypeError("not a java array");
    }
    
    @JRubyMethod(name = { "[]=" }, required = 2)
    public IRubyObject aset(final IRubyObject index, final IRubyObject someValue) {
        throw this.getRuntime().newTypeError("not a java array");
    }
    
    @JRubyMethod(name = { "fill" }, required = 3)
    public IRubyObject afill(final IRubyObject beginIndex, final IRubyObject endIndex, final IRubyObject someValue) {
        throw this.getRuntime().newTypeError("not a java array");
    }
    
    @JRubyMethod(name = { "java_proxy?" })
    public IRubyObject is_java_proxy() {
        return this.getRuntime().getTrue();
    }
    
    @JRubyMethod(name = { "synchronized" })
    public IRubyObject ruby_synchronized(final ThreadContext context, final Block block) {
        final Object lock = this.getValue();
        final Object o;
        synchronized (o = ((lock != null) ? lock : JavaObject.NULL_LOCK)) {
            return block.yield(context, null);
        }
    }
    
    public static IRubyObject ruby_synchronized(final ThreadContext context, final Object lock, final Block block) {
        Object o;
        Object null_LOCK;
        if (lock != null) {
            o = lock;
            null_LOCK = lock;
        }
        else {
            o = (null_LOCK = JavaObject.NULL_LOCK);
        }
        final Object o2 = null_LOCK;
        synchronized (o) {
            return block.yield(context, null);
        }
    }
    
    @JRubyMethod
    public IRubyObject marshal_dump() {
        if (Serializable.class.isAssignableFrom(this.getJavaClass())) {
            try {
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                final ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(this.getValue());
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
            this.dataWrapStruct(ois.readObject());
            return this;
        }
        catch (IOException ioe) {
            throw context.getRuntime().newIOErrorFromException(ioe);
        }
        catch (ClassNotFoundException cnfe) {
            throw context.getRuntime().newTypeError("Class not found unmarshaling Java type: " + cnfe.getLocalizedMessage());
        }
    }
    
    public Object toJava(final Class cls) {
        if (this.getValue() == null) {
            return this.getValue();
        }
        if (cls.isAssignableFrom(this.getValue().getClass())) {
            return this.getValue();
        }
        return super.toJava(cls);
    }
    
    static {
        JavaObject.NULL_LOCK = new Object();
        JAVA_OBJECT_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klazz) {
                return new JavaObject(runtime, klazz, null);
            }
        };
    }
}
