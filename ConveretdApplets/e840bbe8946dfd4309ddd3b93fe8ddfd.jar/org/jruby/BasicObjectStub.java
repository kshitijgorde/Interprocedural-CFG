// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.InstanceVariables;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;
import org.jruby.runtime.builtin.Variable;
import org.jruby.util.TypeConverter;
import org.jruby.runtime.builtin.RubyJavaObject;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.builtin.InternalVariables;

public final class BasicObjectStub
{
    public static final InternalVariables DUMMY_INTERNAL_VARIABLES;
    
    public static IRubyObject callSuper(final IRubyObject self, final ThreadContext context, final IRubyObject[] args, final Block block) {
        return RuntimeHelpers.invokeSuper(context, self, args, block);
    }
    
    public static IRubyObject callMethod(final IRubyObject self, final ThreadContext context, final String name) {
        return RuntimeHelpers.invoke(context, self, name);
    }
    
    public static IRubyObject callMethod(final IRubyObject self, final ThreadContext context, final String name, final IRubyObject arg) {
        return RuntimeHelpers.invoke(context, self, name, arg);
    }
    
    public static IRubyObject callMethod(final IRubyObject self, final ThreadContext context, final String name, final IRubyObject[] args) {
        return RuntimeHelpers.invoke(context, self, name, args);
    }
    
    public static IRubyObject callMethod(final IRubyObject self, final ThreadContext context, final String name, final IRubyObject[] args, final Block block) {
        return RuntimeHelpers.invoke(context, self, name, args, block);
    }
    
    public static IRubyObject callMethod(final IRubyObject self, final ThreadContext context, final int methodIndex, final String name) {
        return RuntimeHelpers.invoke(context, self, name);
    }
    
    public static IRubyObject callMethod(final IRubyObject self, final ThreadContext context, final int methodIndex, final String name, final IRubyObject arg) {
        return RuntimeHelpers.invoke(context, self, name, arg);
    }
    
    public static boolean isNil(final IRubyObject self) {
        return false;
    }
    
    public static boolean isTrue(final IRubyObject self) {
        return true;
    }
    
    public static boolean isTaint(final IRubyObject self) {
        return false;
    }
    
    public static void setTaint(final IRubyObject self, final boolean b) {
    }
    
    public static IRubyObject infectBy(final IRubyObject self, final IRubyObject obj) {
        return self;
    }
    
    public static boolean isFrozen(final IRubyObject self) {
        return false;
    }
    
    public static void setFrozen(final IRubyObject self, final boolean b) {
    }
    
    public static boolean isUntrusted(final IRubyObject self) {
        return false;
    }
    
    public static void setUntrusted(final IRubyObject self, final boolean b) {
    }
    
    public static boolean isImmediate(final IRubyObject self) {
        return false;
    }
    
    public static RubyClass getMetaClass(final IRubyObject self) {
        if (self instanceof RubyBasicObject) {
            return ((RubyBasicObject)self).getMetaClass();
        }
        if (self instanceof RubyJavaObject) {
            return ((RubyJavaObject)self).getMetaClass();
        }
        throw new RuntimeException("unknown object type in BasicObjectStuff.getMetaClass: " + self.getClass());
    }
    
    public static RubyClass getSingletonClass(final IRubyObject self) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public static RubyClass getType(final IRubyObject self) {
        return getMetaClass(self).getRealClass();
    }
    
    public static boolean respondsTo(final IRubyObject self, final String name) {
        if (getMetaClass(self).searchMethod("respond_to?") == getRuntime(self).getRespondToMethod()) {
            return getMetaClass(self).isMethodBound(name, false);
        }
        return callMethod(self, getRuntime(self).getCurrentContext(), "respond_to?", getRuntime(self).newSymbol(name)).isTrue();
    }
    
    public static Ruby getRuntime(final IRubyObject self) {
        return getMetaClass(self).getClassRuntime();
    }
    
    public static Class getJavaClass(final IRubyObject self) {
        return self.getClass();
    }
    
    public static String asJavaString(final IRubyObject self) {
        final IRubyObject asString = checkStringType(self);
        if (!asString.isNil()) {
            return ((RubyString)asString).asJavaString();
        }
        throw getRuntime(self).newTypeError(inspect(self).toString() + " is not a string");
    }
    
    public static RubyString asString(final IRubyObject self) {
        final IRubyObject str = RuntimeHelpers.invoke(getRuntime(self).getCurrentContext(), self, "to_s");
        if (!(str instanceof RubyString)) {
            return (RubyString)anyToString(self);
        }
        if (isTaint(self)) {
            str.setTaint(true);
        }
        return (RubyString)str;
    }
    
    public static RubyArray convertToArray(final IRubyObject self) {
        return (RubyArray)TypeConverter.convertToType(self, getRuntime(self).getArray(), "to_ary");
    }
    
    public static RubyHash convertToHash(final IRubyObject self) {
        return (RubyHash)TypeConverter.convertToType(self, getRuntime(self).getHash(), "to_hash");
    }
    
    public static RubyFloat convertToFloat(final IRubyObject self) {
        return (RubyFloat)TypeConverter.convertToType(self, getRuntime(self).getFloat(), "to_f");
    }
    
    public static RubyInteger convertToInteger(final IRubyObject self) {
        return convertToInteger(self, "to_int");
    }
    
    public static RubyInteger convertToInteger(final IRubyObject self, final int convertMethodIndex, final String convertMethod) {
        return convertToInteger(self, convertMethod);
    }
    
    public static RubyInteger convertToInteger(final IRubyObject self, final String convertMethod) {
        final IRubyObject val = TypeConverter.convertToType(self, getRuntime(self).getInteger(), convertMethod, true);
        if (!(val instanceof RubyInteger)) {
            throw getRuntime(self).newTypeError(getMetaClass(self).getName() + "#" + convertMethod + " should return Integer");
        }
        return (RubyInteger)val;
    }
    
    public static RubyString convertToString(final IRubyObject self) {
        return (RubyString)TypeConverter.convertToType(self, getRuntime(self).getString(), "to_str");
    }
    
    public static IRubyObject anyToString(final IRubyObject self) {
        final String cname = getMetaClass(self).getRealClass().getName();
        final RubyString str = getRuntime(self).newString("#<" + cname + ":0x" + Integer.toHexString(System.identityHashCode(self)) + ">");
        str.setTaint(isTaint(self));
        return str;
    }
    
    public static IRubyObject checkStringType(final IRubyObject self) {
        IRubyObject str = TypeConverter.convertToTypeWithCheck(self, getRuntime(self).getString(), "to_str");
        if (!str.isNil() && !(str instanceof RubyString)) {
            str = RubyString.newEmptyString(getRuntime(self));
        }
        return str;
    }
    
    public static IRubyObject checkArrayType(final IRubyObject self) {
        return TypeConverter.convertToTypeWithCheck(self, getRuntime(self).getArray(), "to_ary");
    }
    
    public static Object toJava(final IRubyObject self, final Class cls) {
        if (cls.isAssignableFrom(self.getClass())) {
            return self;
        }
        throw getRuntime(self).newTypeError("could not convert " + self.getClass() + " to " + cls);
    }
    
    public static IRubyObject dup(final IRubyObject self) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public static IRubyObject inspect(final IRubyObject self) {
        final Ruby runtime = getRuntime(self);
        if (hasVariables(self)) {
            final StringBuilder part = new StringBuilder();
            final String cname = getMetaClass(self).getRealClass().getName();
            part.append("#<").append(cname).append(":0x");
            part.append(Integer.toHexString(System.identityHashCode(self)));
            if (runtime.isInspecting(self)) {
                part.append(" ...>");
                return runtime.newString(part.toString());
            }
            try {
                runtime.registerInspecting(self);
                return runtime.newString(inspectObj(self, part).toString());
            }
            finally {
                runtime.unregisterInspecting(self);
            }
        }
        return RuntimeHelpers.invoke(runtime.getCurrentContext(), self, "to_s");
    }
    
    private static StringBuilder inspectObj(final IRubyObject self, final StringBuilder part) {
        final ThreadContext context = getRuntime(self).getCurrentContext();
        String sep = "";
        for (final Variable<IRubyObject> ivar : getInstanceVariables(self).getInstanceVariableList()) {
            part.append(sep).append(" ").append(ivar.getName()).append("=");
            part.append(ivar.getValue().callMethod(context, "inspect"));
            sep = ",";
        }
        part.append(">");
        return part;
    }
    
    public static IRubyObject rbClone(final IRubyObject self) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public static boolean isModule(final IRubyObject self) {
        return false;
    }
    
    public static boolean isClass(final IRubyObject self) {
        return false;
    }
    
    public static void dataWrapStruct(final IRubyObject self, final Object obj) {
    }
    
    public static Object dataGetStruct(final IRubyObject self) {
        return null;
    }
    
    public static Object dataGetStructChecked(final IRubyObject self) {
        return null;
    }
    
    public static IRubyObject id(final IRubyObject self) {
        return getRuntime(self).newFixnum(System.identityHashCode(self));
    }
    
    public static IRubyObject op_equal(final IRubyObject self, final ThreadContext context, final IRubyObject other) {
        return getRuntime(self).newBoolean(self == other);
    }
    
    public static IRubyObject op_eqq(final IRubyObject self, final ThreadContext context, final IRubyObject other) {
        return getRuntime(self).newBoolean(self == other);
    }
    
    public static boolean eql(final IRubyObject self, final IRubyObject other) {
        return self == other;
    }
    
    public static void addFinalizer(final IRubyObject self, final IRubyObject finalizer) {
    }
    
    public static void removeFinalizers(final IRubyObject self) {
    }
    
    public static boolean hasVariables(final IRubyObject self) {
        return false;
    }
    
    public static int getVariableCount(final IRubyObject self) {
        return 0;
    }
    
    public static void syncVariables(final IRubyObject self, final List<Variable<Object>> variables) {
    }
    
    public static List<Variable<Object>> getVariableList(final IRubyObject self) {
        return (List<Variable<Object>>)Collections.EMPTY_LIST;
    }
    
    public static InstanceVariables getInstanceVariables(final IRubyObject self) {
        return new DummyInstanceVariables(getRuntime(self).getNil());
    }
    
    public static InternalVariables getInternalVariables(final IRubyObject self) {
        return BasicObjectStub.DUMMY_INTERNAL_VARIABLES;
    }
    
    public static List<String> getVariableNameList(final IRubyObject self) {
        return (List<String>)Collections.EMPTY_LIST;
    }
    
    public static void copySpecialInstanceVariables(final IRubyObject self, final IRubyObject clone) {
    }
    
    public static Object getVariable(final IRubyObject self, final int index) {
        return null;
    }
    
    public static void setVariable(final IRubyObject self, final int index, final Object value) {
    }
    
    static {
        DUMMY_INTERNAL_VARIABLES = new DummyInternalVariables();
    }
    
    public static class DummyInstanceVariables implements InstanceVariables
    {
        private final IRubyObject nil;
        
        public DummyInstanceVariables(final IRubyObject nil) {
            this.nil = nil;
        }
        
        public boolean hasInstanceVariable(final String name) {
            return false;
        }
        
        public boolean fastHasInstanceVariable(final String internedName) {
            return false;
        }
        
        public IRubyObject getInstanceVariable(final String name) {
            return this.nil;
        }
        
        public IRubyObject fastGetInstanceVariable(final String internedName) {
            return this.nil;
        }
        
        public IRubyObject setInstanceVariable(final String name, final IRubyObject value) {
            return value;
        }
        
        public IRubyObject fastSetInstanceVariable(final String internedName, final IRubyObject value) {
            return value;
        }
        
        public IRubyObject removeInstanceVariable(final String name) {
            return this.nil;
        }
        
        public List<Variable<IRubyObject>> getInstanceVariableList() {
            return (List<Variable<IRubyObject>>)Collections.EMPTY_LIST;
        }
        
        public List<String> getInstanceVariableNameList() {
            return (List<String>)Collections.EMPTY_LIST;
        }
        
        public void copyInstanceVariablesInto(final InstanceVariables other) {
        }
    }
    
    public static class DummyInternalVariables implements InternalVariables
    {
        public boolean hasInternalVariable(final String name) {
            return false;
        }
        
        public boolean fastHasInternalVariable(final String internedName) {
            return false;
        }
        
        public Object getInternalVariable(final String name) {
            return null;
        }
        
        public Object fastGetInternalVariable(final String internedName) {
            return null;
        }
        
        public void setInternalVariable(final String name, final Object value) {
        }
        
        public void fastSetInternalVariable(final String internedName, final Object value) {
        }
        
        public Object removeInternalVariable(final String name) {
            return null;
        }
    }
}
