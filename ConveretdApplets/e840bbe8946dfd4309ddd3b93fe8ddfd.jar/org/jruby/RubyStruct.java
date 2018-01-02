// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.marshal.UnmarshalStream;
import java.io.IOException;
import org.jruby.runtime.marshal.MarshalStream;
import org.jruby.util.ByteList;
import org.jruby.exceptions.RaiseException;
import org.jruby.runtime.Arity;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.runtime.Visibility;
import org.jruby.common.IRubyWarnings;
import org.jruby.util.IdUtil;
import org.jruby.runtime.Block;
import org.jruby.anno.JRubyMethod;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "Struct" })
public class RubyStruct extends RubyObject
{
    private IRubyObject[] values;
    private static ObjectAllocator STRUCT_INSTANCE_ALLOCATOR;
    
    public RubyStruct(final Ruby runtime, final RubyClass rubyClass) {
        super(runtime, rubyClass);
        final int size = RubyNumeric.fix2int(getInternalVariable(rubyClass, "__size__"));
        this.values = new IRubyObject[size];
        for (int i = 0; i < size; ++i) {
            this.values[i] = this.getRuntime().getNil();
        }
    }
    
    public static RubyClass createStructClass(final Ruby runtime) {
        final RubyClass structClass = runtime.defineClass("Struct", runtime.getObject(), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
        runtime.setStructClass(structClass);
        structClass.index = 15;
        structClass.includeModule(runtime.getEnumerable());
        structClass.defineAnnotatedMethods(RubyStruct.class);
        return structClass;
    }
    
    public int getNativeTypeIndex() {
        return 15;
    }
    
    private static IRubyObject getInternalVariable(RubyClass type, final String internedName) {
        for (RubyClass structClass = type.getRuntime().getStructClass(); type != null && type != structClass; type = type.getSuperClass()) {
            final IRubyObject variable;
            if ((variable = (IRubyObject)type.fastGetInternalVariable(internedName)) != null) {
                return variable;
            }
        }
        return type.getRuntime().getNil();
    }
    
    private RubyClass classOf() {
        return (this.getMetaClass() instanceof MetaClass) ? this.getMetaClass().getSuperClass() : this.getMetaClass();
    }
    
    private void modify() {
        this.testFrozen();
        if (!this.isTaint() && this.getRuntime().getSafeLevel() >= 4) {
            throw this.getRuntime().newSecurityError("Insecure: can't modify struct");
        }
    }
    
    @JRubyMethod
    public RubyFixnum hash(final ThreadContext context) {
        final Ruby runtime = this.getRuntime();
        int h = this.getMetaClass().getRealClass().hashCode();
        for (int i = 0; i < this.values.length; ++i) {
            h = (h << 1 | ((h < 0) ? 1 : 0));
            h ^= (int)RubyNumeric.num2long(RuntimeHelpers.invokedynamic(context, this.values[i], 3));
        }
        return runtime.newFixnum(h);
    }
    
    private IRubyObject setByName(final String name, final IRubyObject value) {
        final RubyArray member = (RubyArray)getInternalVariable(this.classOf(), "__member__");
        assert !member.isNil() : "uninitialized struct";
        this.modify();
        for (int i = 0, k = member.getLength(); i < k; ++i) {
            if (member.eltInternal(i).asJavaString().equals(name)) {
                return this.values[i] = value;
            }
        }
        throw this.notStructMemberError(name);
    }
    
    private IRubyObject getByName(final String name) {
        final RubyArray member = (RubyArray)getInternalVariable(this.classOf(), "__member__");
        assert !member.isNil() : "uninitialized struct";
        for (int i = 0, k = member.getLength(); i < k; ++i) {
            if (member.eltInternal(i).asJavaString().equals(name)) {
                return this.values[i];
            }
        }
        throw this.notStructMemberError(name);
    }
    
    @JRubyMethod(name = { "new" }, required = 1, rest = true, meta = true)
    public static RubyClass newInstance(final IRubyObject recv, final IRubyObject[] args, final Block block) {
        String name = null;
        boolean nilName = false;
        final Ruby runtime = recv.getRuntime();
        if (args.length > 0) {
            final IRubyObject firstArgAsString = args[0].checkStringType();
            if (!firstArgAsString.isNil()) {
                name = ((RubyString)firstArgAsString).getByteList().toString();
            }
            else if (args[0].isNil()) {
                nilName = true;
            }
        }
        final RubyArray member = runtime.newArray();
        for (int i = (name != null || nilName) ? 1 : 0; i < args.length; ++i) {
            member.append(runtime.newSymbol(args[i].asJavaString()));
        }
        final RubyClass superClass = (RubyClass)recv;
        RubyClass newStruct;
        if (name == null || nilName) {
            newStruct = RubyClass.newClass(runtime, superClass);
            newStruct.setAllocator(RubyStruct.STRUCT_INSTANCE_ALLOCATOR);
            newStruct.makeMetaClass(superClass.getMetaClass());
            newStruct.inherit(superClass);
        }
        else {
            if (!IdUtil.isConstant(name)) {
                throw runtime.newNameError("identifier " + name + " needs to be constant", name);
            }
            final IRubyObject type = superClass.getConstantAt(name);
            if (type != null) {
                final ThreadContext context = runtime.getCurrentContext();
                runtime.getWarnings().warn(IRubyWarnings.ID.STRUCT_CONSTANT_REDEFINED, context.getFile(), context.getLine(), "redefining constant Struct::" + name, name);
                superClass.remove_const(context, runtime.newString(name));
            }
            newStruct = superClass.defineClassUnder(name, superClass, RubyStruct.STRUCT_INSTANCE_ALLOCATOR);
        }
        newStruct.setReifiedClass(RubyStruct.class);
        newStruct.index = 15;
        newStruct.fastSetInternalVariable("__size__", member.length());
        newStruct.fastSetInternalVariable("__member__", member);
        newStruct.getSingletonClass().defineAnnotatedMethods(StructMethods.class);
        for (int j = (name != null || nilName) ? 1 : 0; j < args.length; ++j) {
            final String memberName = args[j].asJavaString();
            final int index = (name == null && !nilName) ? j : (j - 1);
            newStruct.addMethod(memberName, new DynamicMethod(newStruct, Visibility.PUBLIC, CallConfiguration.FrameNoneScopeNone) {
                public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
                    Arity.checkArgumentCount(self.getRuntime(), args, 0, 0);
                    return ((RubyStruct)self).get(index);
                }
                
                public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name) {
                    return ((RubyStruct)self).get(index);
                }
                
                public DynamicMethod dup() {
                    return this;
                }
            });
            newStruct.addMethod(memberName + "=", new DynamicMethod(newStruct, Visibility.PUBLIC, CallConfiguration.FrameNoneScopeNone) {
                public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
                    Arity.checkArgumentCount(self.getRuntime(), args, 1, 1);
                    return ((RubyStruct)self).set(args[0], index);
                }
                
                public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg) {
                    return ((RubyStruct)self).set(arg, index);
                }
                
                public DynamicMethod dup() {
                    return this;
                }
            });
        }
        if (block.isGiven()) {
            block.getBinding().setVisibility(Visibility.PUBLIC);
            block.yieldNonArray(runtime.getCurrentContext(), null, newStruct, newStruct);
        }
        return newStruct;
    }
    
    public static RubyStruct newStruct(final IRubyObject recv, final IRubyObject[] args, final Block block) {
        final RubyStruct struct = new RubyStruct(recv.getRuntime(), (RubyClass)recv);
        struct.callInit(args, block);
        return struct;
    }
    
    public static RubyStruct newStruct(final IRubyObject recv, final Block block) {
        final RubyStruct struct = new RubyStruct(recv.getRuntime(), (RubyClass)recv);
        struct.callInit(block);
        return struct;
    }
    
    public static RubyStruct newStruct(final IRubyObject recv, final IRubyObject arg0, final Block block) {
        final RubyStruct struct = new RubyStruct(recv.getRuntime(), (RubyClass)recv);
        struct.callInit(arg0, block);
        return struct;
    }
    
    public static RubyStruct newStruct(final IRubyObject recv, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        final RubyStruct struct = new RubyStruct(recv.getRuntime(), (RubyClass)recv);
        struct.callInit(arg0, arg1, block);
        return struct;
    }
    
    public static RubyStruct newStruct(final IRubyObject recv, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        final RubyStruct struct = new RubyStruct(recv.getRuntime(), (RubyClass)recv);
        struct.callInit(arg0, arg1, arg2, block);
        return struct;
    }
    
    private void checkSize(final int length) {
        if (length > this.values.length) {
            throw this.getRuntime().newArgumentError("struct size differs (" + length + " for " + this.values.length + ")");
        }
    }
    
    @JRubyMethod(rest = true, visibility = Visibility.PRIVATE)
    public IRubyObject initialize(final ThreadContext context, final IRubyObject[] args) {
        this.modify();
        this.checkSize(args.length);
        System.arraycopy(args, 0, this.values, 0, args.length);
        RuntimeHelpers.fillNil(this.values, args.length, this.values.length, context.runtime);
        return context.nil;
    }
    
    @JRubyMethod(visibility = Visibility.PRIVATE)
    public IRubyObject initialize(final ThreadContext context) {
        final IRubyObject nil = context.nil;
        return this.initializeInternal(context, 0, nil, nil, nil);
    }
    
    @JRubyMethod(visibility = Visibility.PRIVATE)
    public IRubyObject initialize(final ThreadContext context, final IRubyObject arg0) {
        final IRubyObject nil = context.nil;
        return this.initializeInternal(context, 1, arg0, nil, nil);
    }
    
    @JRubyMethod(visibility = Visibility.PRIVATE)
    public IRubyObject initialize(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1) {
        return this.initializeInternal(context, 2, arg0, arg1, context.nil);
    }
    
    @JRubyMethod(visibility = Visibility.PRIVATE)
    public IRubyObject initialize(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2) {
        return this.initializeInternal(context, 3, arg0, arg1, arg2);
    }
    
    public IRubyObject initializeInternal(final ThreadContext context, final int provided, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2) {
        this.modify();
        this.checkSize(provided);
        switch (provided) {
            case 3: {
                this.values[2] = arg2;
            }
            case 2: {
                this.values[1] = arg1;
            }
            case 1: {
                this.values[0] = arg0;
                break;
            }
        }
        if (provided < this.values.length) {
            RuntimeHelpers.fillNil(this.values, provided, this.values.length, context.runtime);
        }
        return this.getRuntime().getNil();
    }
    
    public static RubyArray members(final IRubyObject recv, final Block block) {
        final RubyArray member = (RubyArray)getInternalVariable((RubyClass)recv, "__member__");
        assert !member.isNil() : "uninitialized struct";
        final RubyArray result = recv.getRuntime().newArray(member.getLength());
        for (int i = 0, k = member.getLength(); i < k; ++i) {
            result.append(recv.getRuntime().newString(member.eltInternal(i).asJavaString()));
        }
        return result;
    }
    
    @JRubyMethod
    public RubyArray members() {
        return members(this.classOf(), Block.NULL_BLOCK);
    }
    
    @JRubyMethod
    public RubyArray select(final ThreadContext context, final Block block) {
        final RubyArray array = RubyArray.newArray(context.getRuntime());
        for (int i = 0; i < this.values.length; ++i) {
            if (block.yield(context, this.values[i]).isTrue()) {
                array.append(this.values[i]);
            }
        }
        return array;
    }
    
    public IRubyObject set(final IRubyObject value, final int index) {
        this.modify();
        return this.values[index] = value;
    }
    
    private RaiseException notStructMemberError(final String name) {
        return this.getRuntime().newNameError(name + " is not struct member", name);
    }
    
    public IRubyObject get(final int index) {
        return this.values[index];
    }
    
    public void copySpecialInstanceVariables(final IRubyObject clone) {
        final RubyStruct struct = (RubyStruct)clone;
        struct.values = new IRubyObject[this.values.length];
        System.arraycopy(this.values, 0, struct.values, 0, this.values.length);
    }
    
    @JRubyMethod(name = { "==" }, required = 1)
    public IRubyObject op_equal(final ThreadContext context, final IRubyObject other) {
        if (this == other) {
            return this.getRuntime().getTrue();
        }
        if (!(other instanceof RubyStruct)) {
            return this.getRuntime().getFalse();
        }
        if (this.getMetaClass().getRealClass() != other.getMetaClass().getRealClass()) {
            return this.getRuntime().getFalse();
        }
        final Ruby runtime = this.getRuntime();
        final RubyStruct otherStruct = (RubyStruct)other;
        for (int i = 0; i < this.values.length; ++i) {
            if (!RubyObject.equalInternal(context, this.values[i], otherStruct.values[i])) {
                return runtime.getFalse();
            }
        }
        return runtime.getTrue();
    }
    
    @JRubyMethod(name = { "eql?" }, required = 1)
    public IRubyObject eql_p(final ThreadContext context, final IRubyObject other) {
        if (this == other) {
            return this.getRuntime().getTrue();
        }
        if (!(other instanceof RubyStruct)) {
            return this.getRuntime().getFalse();
        }
        if (this.getMetaClass() != other.getMetaClass()) {
            return this.getRuntime().getFalse();
        }
        final Ruby runtime = this.getRuntime();
        final RubyStruct otherStruct = (RubyStruct)other;
        for (int i = 0; i < this.values.length; ++i) {
            if (!RubyObject.eqlInternal(context, this.values[i], otherStruct.values[i])) {
                return runtime.getFalse();
            }
        }
        return runtime.getTrue();
    }
    
    private IRubyObject inspectStruct(final ThreadContext context) {
        final RubyArray member = (RubyArray)getInternalVariable(this.classOf(), "__member__");
        assert !member.isNil() : "uninitialized struct";
        final ByteList buffer = new ByteList("#<struct ".getBytes());
        buffer.append(this.getMetaClass().getRealClass().getRealClass().getName().getBytes());
        buffer.append(32);
        for (int i = 0, k = member.getLength(); i < k; ++i) {
            if (i > 0) {
                buffer.append(44).append(32);
            }
            buffer.append(RubyString.objAsString(context, member.eltInternal(i)).getByteList());
            buffer.append(61);
            buffer.append(RubyObject.inspect(context, this.values[i]).getByteList());
        }
        buffer.append(62);
        return this.getRuntime().newString(buffer);
    }
    
    @JRubyMethod(name = { "inspect", "to_s" })
    public IRubyObject inspect(final ThreadContext context) {
        if (this.getRuntime().isInspecting(this)) {
            return this.getRuntime().newString("#<struct " + this.getMetaClass().getRealClass().getName() + ":...>");
        }
        try {
            this.getRuntime().registerInspecting(this);
            return this.inspectStruct(context);
        }
        finally {
            this.getRuntime().unregisterInspecting(this);
        }
    }
    
    @JRubyMethod(name = { "to_a", "values" })
    public RubyArray to_a() {
        return this.getRuntime().newArray(this.values);
    }
    
    @JRubyMethod(name = { "size", "length" })
    public RubyFixnum size() {
        return this.getRuntime().newFixnum(this.values.length);
    }
    
    public IRubyObject eachInternal(final ThreadContext context, final Block block) {
        for (int i = 0; i < this.values.length; ++i) {
            block.yield(context, this.values[i]);
        }
        return this;
    }
    
    @JRubyMethod
    public IRubyObject each(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.eachInternal(context, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "each");
    }
    
    public IRubyObject each_pairInternal(final ThreadContext context, final Block block) {
        final RubyArray member = (RubyArray)getInternalVariable(this.classOf(), "__member__");
        assert !member.isNil() : "uninitialized struct";
        for (int i = 0; i < this.values.length; ++i) {
            block.yield(context, this.getRuntime().newArrayNoCopy(member.eltInternal(i), this.values[i]));
        }
        return this;
    }
    
    @JRubyMethod
    public IRubyObject each_pair(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.each_pairInternal(context, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "each_pair");
    }
    
    @JRubyMethod(name = { "[]" }, required = 1)
    public IRubyObject aref(final IRubyObject key) {
        if (key instanceof RubyString || key instanceof RubySymbol) {
            return this.getByName(key.asJavaString());
        }
        int idx = RubyNumeric.fix2int(key);
        idx = ((idx < 0) ? (this.values.length + idx) : idx);
        if (idx < 0) {
            throw this.getRuntime().newIndexError("offset " + idx + " too large for struct (size:" + this.values.length + ")");
        }
        if (idx >= this.values.length) {
            throw this.getRuntime().newIndexError("offset " + idx + " too large for struct (size:" + this.values.length + ")");
        }
        return this.values[idx];
    }
    
    @JRubyMethod(name = { "[]=" }, required = 2)
    public IRubyObject aset(final IRubyObject key, final IRubyObject value) {
        if (key instanceof RubyString || key instanceof RubySymbol) {
            return this.setByName(key.asJavaString(), value);
        }
        int idx = RubyNumeric.fix2int(key);
        idx = ((idx < 0) ? (this.values.length + idx) : idx);
        if (idx < 0) {
            throw this.getRuntime().newIndexError("offset " + idx + " too large for struct (size:" + this.values.length + ")");
        }
        if (idx >= this.values.length) {
            throw this.getRuntime().newIndexError("offset " + idx + " too large for struct (size:" + this.values.length + ")");
        }
        this.modify();
        return this.values[idx] = value;
    }
    
    @JRubyMethod(rest = true)
    public IRubyObject values_at(final IRubyObject[] args) {
        final int olen = this.values.length;
        final RubyArray result = this.getRuntime().newArray(args.length);
        for (int i = 0; i < args.length; ++i) {
            if (args[i] instanceof RubyFixnum) {
                result.append(this.aref(args[i]));
            }
            else if (!(args[i] instanceof RubyRange)) {
                result.append(this.aref(this.getRuntime().newFixnum(RubyNumeric.num2long(args[i]))));
            }
            else {
                final int[] beglen;
                if ((beglen = ((RubyRange)args[i]).begLenInt(olen, 0)) != null) {
                    final int beg = beglen[0];
                    int end;
                    for (int len = end = beglen[1], j = 0; j < end; ++j) {
                        result.append(this.aref(this.getRuntime().newFixnum(j + beg)));
                    }
                }
            }
        }
        return result;
    }
    
    public static void marshalTo(final RubyStruct struct, final MarshalStream output) throws IOException {
        output.registerLinkTarget(struct);
        output.dumpDefaultObjectHeader('S', struct.getMetaClass());
        final RubyArray array = (RubyArray)getInternalVariable(struct.classOf(), "__member__");
        output.writeInt(array.size());
        for (int i = 0; i < array.size(); ++i) {
            final RubySymbol name = (RubySymbol)array.eltInternal(i);
            output.dumpObject(name);
            output.dumpObject(struct.values[i]);
        }
    }
    
    public static RubyStruct unmarshalFrom(final UnmarshalStream input) throws IOException {
        final Ruby runtime = input.getRuntime();
        final RubySymbol className = (RubySymbol)input.unmarshalObject(false);
        final RubyClass rbClass = pathToClass(runtime, className.asJavaString());
        if (rbClass == null) {
            throw runtime.newNameError("uninitialized constant " + className, className.asJavaString());
        }
        final RubyArray mem = members(rbClass, Block.NULL_BLOCK);
        final int len = input.unmarshalInt();
        IRubyObject[] values;
        if (len == 0) {
            values = IRubyObject.NULL_ARRAY;
        }
        else {
            values = new IRubyObject[len];
            RuntimeHelpers.fillNil(values, runtime);
        }
        final RubyStruct result = newStruct(rbClass, values, Block.NULL_BLOCK);
        input.registerLinkTarget(result);
        for (int i = 0; i < len; ++i) {
            final IRubyObject slot = input.unmarshalObject(false);
            if (!mem.eltInternal(i).toString().equals(slot.toString())) {
                throw runtime.newTypeError("struct " + rbClass.getName() + " not compatible (:" + slot + " for :" + mem.eltInternal(i) + ")");
            }
            result.aset(runtime.newFixnum(i), input.unmarshalObject());
        }
        return result;
    }
    
    private static RubyClass pathToClass(final Ruby runtime, final String path) {
        return (RubyClass)runtime.getClassFromPath(path);
    }
    
    @JRubyMethod(required = 1)
    public IRubyObject initialize_copy(final IRubyObject arg) {
        if (this == arg) {
            return this;
        }
        final RubyStruct original = (RubyStruct)arg;
        this.values = new IRubyObject[original.values.length];
        System.arraycopy(original.values, 0, this.values, 0, original.values.length);
        return this;
    }
    
    static {
        RubyStruct.STRUCT_INSTANCE_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                final RubyStruct instance = new RubyStruct(runtime, klass);
                instance.setMetaClass(klass);
                return instance;
            }
        };
    }
    
    public static class StructMethods
    {
        @JRubyMethod(name = { "new", "[]" }, rest = true)
        public static IRubyObject newStruct(final IRubyObject recv, final IRubyObject[] args, final Block block) {
            return RubyStruct.newStruct(recv, args, block);
        }
        
        @JRubyMethod(name = { "new", "[]" })
        public static IRubyObject newStruct(final IRubyObject recv, final Block block) {
            return RubyStruct.newStruct(recv, block);
        }
        
        @JRubyMethod(name = { "new", "[]" })
        public static IRubyObject newStruct(final IRubyObject recv, final IRubyObject arg0, final Block block) {
            return RubyStruct.newStruct(recv, arg0, block);
        }
        
        @JRubyMethod(name = { "new", "[]" })
        public static IRubyObject newStruct(final IRubyObject recv, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
            return RubyStruct.newStruct(recv, arg0, arg1, block);
        }
        
        @JRubyMethod(name = { "new", "[]" })
        public static IRubyObject newStruct(final IRubyObject recv, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
            return RubyStruct.newStruct(recv, arg0, arg1, arg2, block);
        }
        
        @JRubyMethod
        public static IRubyObject members(final IRubyObject recv, final Block block) {
            return RubyStruct.members(recv, block);
        }
    }
}
