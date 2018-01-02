// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import java.util.ListIterator;
import java.util.Iterator;
import org.jruby.javasupport.JavaUtil;
import org.jruby.java.addons.ArrayJavaAddons;
import java.lang.reflect.Array;
import org.jruby.util.Pack;
import org.jruby.runtime.marshal.UnmarshalStream;
import java.io.IOException;
import org.jruby.runtime.marshal.MarshalStream;
import java.util.Random;
import org.jruby.util.Qsort;
import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.Set;
import org.jruby.util.RecursiveComparator;
import org.jruby.util.TypeConverter;
import org.jruby.util.ByteList;
import org.jruby.runtime.Visibility;
import org.jruby.common.IRubyWarnings;
import org.jruby.runtime.Arity;
import org.jruby.runtime.ThreadContext;
import java.util.Arrays;
import java.util.Collection;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.cext.RArray;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.anno.JRubyClass;
import java.util.List;

@JRubyClass(name = { "Array" })
public class RubyArray extends RubyObject implements List
{
    private static ObjectAllocator ARRAY_ALLOCATOR;
    public static final int ARRAY_DEFAULT_SIZE = 16;
    private volatile IRubyObject[] values;
    private static final int TMPLOCK_ARR_F = 512;
    private static final int TMPLOCK_OR_FROZEN_ARR_F = 516;
    private volatile boolean isShared;
    private int begin;
    private int realLength;
    private RArray rarray;
    private static int SORTED_THRESHOLD;
    
    public static RubyClass createArrayClass(final Ruby runtime) {
        final RubyClass arrayc = runtime.defineClass("Array", runtime.getObject(), RubyArray.ARRAY_ALLOCATOR);
        runtime.setArray(arrayc);
        arrayc.index = 3;
        arrayc.setReifiedClass(RubyArray.class);
        arrayc.kindOf = new RubyModule.KindOf() {
            public boolean isKindOf(final IRubyObject obj, final RubyModule type) {
                return obj instanceof RubyArray;
            }
        };
        arrayc.includeModule(runtime.getEnumerable());
        arrayc.defineAnnotatedMethods(RubyArray.class);
        return arrayc;
    }
    
    public int getNativeTypeIndex() {
        return 3;
    }
    
    private final void concurrentModification() {
        concurrentModification(this.getRuntime());
    }
    
    private static void concurrentModification(final Ruby runtime) {
        throw runtime.newConcurrencyError("Detected invalid array contents due to unsynchronized modifications with concurrent users");
    }
    
    @JRubyMethod(name = { "[]" }, rest = true, meta = true)
    public static IRubyObject create(final IRubyObject klass, final IRubyObject[] args, final Block block) {
        final RubyArray arr = (RubyArray)((RubyClass)klass).allocate();
        if (args.length > 0) {
            System.arraycopy(args, 0, arr.values = new IRubyObject[args.length], 0, args.length);
            arr.realLength = args.length;
        }
        return arr;
    }
    
    public static final RubyArray newArray(final Ruby runtime, final long len) {
        checkLength(runtime, len);
        return newArray(runtime, (int)len);
    }
    
    public static final RubyArray newArrayLight(final Ruby runtime, final long len) {
        checkLength(runtime, len);
        return newArrayLight(runtime, (int)len);
    }
    
    public static final RubyArray newArray(final Ruby runtime, final int len) {
        final RubyArray array = new RubyArray(runtime, len);
        RuntimeHelpers.fillNil(array.values, 0, array.values.length, runtime);
        return array;
    }
    
    public static final RubyArray newArrayLight(final Ruby runtime, final int len) {
        final RubyArray array = new RubyArray(runtime, len, false);
        RuntimeHelpers.fillNil(array.values, 0, array.values.length, runtime);
        return array;
    }
    
    public static final RubyArray newArray(final Ruby runtime) {
        return newArray(runtime, 16);
    }
    
    public static final RubyArray newArrayLight(final Ruby runtime) {
        return newArrayLight(runtime, 16);
    }
    
    public static RubyArray newArray(final Ruby runtime, final IRubyObject obj) {
        return new RubyArray(runtime, new IRubyObject[] { obj });
    }
    
    public static RubyArray newArrayLight(final Ruby runtime, final IRubyObject obj) {
        return new RubyArray(runtime, new IRubyObject[] { obj }, false);
    }
    
    public static RubyArray newArrayLight(final Ruby runtime, final IRubyObject... objs) {
        return new RubyArray(runtime, objs, false);
    }
    
    public static RubyArray newArray(final Ruby runtime, final IRubyObject car, final IRubyObject cdr) {
        return new RubyArray(runtime, new IRubyObject[] { car, cdr });
    }
    
    public static RubyArray newEmptyArray(final Ruby runtime) {
        return new RubyArray(runtime, RubyArray.NULL_ARRAY);
    }
    
    public static RubyArray newArray(final Ruby runtime, final IRubyObject[] args) {
        final RubyArray arr = new RubyArray(runtime, new IRubyObject[args.length]);
        System.arraycopy(args, 0, arr.values, 0, args.length);
        arr.realLength = args.length;
        return arr;
    }
    
    public static RubyArray newArrayNoCopy(final Ruby runtime, final IRubyObject[] args) {
        return new RubyArray(runtime, args);
    }
    
    public static RubyArray newArrayNoCopy(final Ruby runtime, final IRubyObject[] args, final int begin) {
        return new RubyArray(runtime, args, begin);
    }
    
    public static RubyArray newArrayNoCopy(final Ruby runtime, final IRubyObject[] args, final int begin, final int length) {
        assert begin >= 0 : "begin must be >= 0";
        assert length >= 0 : "length must be >= 0";
        return new RubyArray(runtime, args, begin, length);
    }
    
    public static RubyArray newArrayNoCopyLight(final Ruby runtime, final IRubyObject[] args) {
        final RubyArray arr = new RubyArray(runtime, false);
        arr.values = args;
        arr.realLength = args.length;
        return arr;
    }
    
    public static RubyArray newArray(final Ruby runtime, final Collection<? extends IRubyObject> collection) {
        return new RubyArray(runtime, collection.toArray(new IRubyObject[collection.size()]));
    }
    
    private RubyArray(final Ruby runtime, final IRubyObject[] vals) {
        super(runtime, runtime.getArray());
        this.isShared = false;
        this.begin = 0;
        this.realLength = 0;
        this.values = vals;
        this.realLength = vals.length;
    }
    
    private RubyArray(final Ruby runtime, final IRubyObject[] vals, final boolean objectSpace) {
        super(runtime, runtime.getArray(), objectSpace);
        this.isShared = false;
        this.begin = 0;
        this.realLength = 0;
        this.values = vals;
        this.realLength = vals.length;
    }
    
    private RubyArray(final Ruby runtime, final IRubyObject[] vals, final int begin) {
        super(runtime, runtime.getArray());
        this.isShared = false;
        this.begin = 0;
        this.realLength = 0;
        this.values = vals;
        this.begin = begin;
        this.realLength = vals.length - begin;
        this.isShared = true;
    }
    
    private RubyArray(final Ruby runtime, final IRubyObject[] vals, final int begin, final int length) {
        super(runtime, runtime.getArray());
        this.isShared = false;
        this.begin = 0;
        this.realLength = 0;
        this.values = vals;
        this.begin = begin;
        this.realLength = length;
        this.isShared = true;
    }
    
    private RubyArray(final Ruby runtime, final RubyClass metaClass, final IRubyObject[] vals, final int begin, final int length) {
        super(runtime, metaClass);
        this.isShared = false;
        this.begin = 0;
        this.realLength = 0;
        this.values = vals;
        this.begin = begin;
        this.realLength = length;
        this.isShared = true;
    }
    
    private RubyArray(final Ruby runtime, final int length) {
        super(runtime, runtime.getArray());
        this.isShared = false;
        this.begin = 0;
        this.realLength = 0;
        this.values = new IRubyObject[length];
    }
    
    private RubyArray(final Ruby runtime, final int length, final boolean objectspace) {
        super(runtime, runtime.getArray(), objectspace);
        this.isShared = false;
        this.begin = 0;
        this.realLength = 0;
        this.values = new IRubyObject[length];
    }
    
    private RubyArray(final Ruby runtime, final boolean objectSpace) {
        super(runtime, runtime.getArray(), objectSpace);
        this.isShared = false;
        this.begin = 0;
        this.realLength = 0;
    }
    
    private RubyArray(final Ruby runtime, final RubyClass klass) {
        super(runtime, klass);
        this.isShared = false;
        this.begin = 0;
        this.realLength = 0;
    }
    
    private RubyArray(final Ruby runtime, final RubyClass klass, final int length) {
        super(runtime, klass);
        this.isShared = false;
        this.begin = 0;
        this.realLength = 0;
        this.values = new IRubyObject[length];
    }
    
    private RubyArray(final Ruby runtime, final RubyClass klass, final IRubyObject[] vals, final boolean objectspace) {
        super(runtime, klass, objectspace);
        this.isShared = false;
        this.begin = 0;
        this.realLength = 0;
        this.values = vals;
    }
    
    private RubyArray(final Ruby runtime, final RubyClass klass, final boolean objectSpace) {
        super(runtime, klass, objectSpace);
        this.isShared = false;
        this.begin = 0;
        this.realLength = 0;
    }
    
    private RubyArray(final Ruby runtime, final RubyClass klass, final RubyArray original) {
        super(runtime, klass);
        this.isShared = false;
        this.begin = 0;
        this.realLength = 0;
        this.realLength = original.realLength;
        this.values = new IRubyObject[this.realLength];
        this.safeArrayCopy(runtime, original.values, original.begin, this.values, 0, this.realLength);
    }
    
    private RubyArray(final Ruby runtime, final RubyClass klass, final IRubyObject[] vals) {
        super(runtime, klass);
        this.isShared = false;
        this.begin = 0;
        this.realLength = 0;
        this.values = vals;
        this.realLength = vals.length;
    }
    
    private void alloc(final int length) {
        final IRubyObject[] newValues = new IRubyObject[length];
        RuntimeHelpers.fillNil(newValues, this.getRuntime());
        this.values = newValues;
        this.begin = 0;
    }
    
    private void realloc(final int newLength, final int valuesLength) {
        final IRubyObject[] reallocated = new IRubyObject[newLength];
        if (newLength > valuesLength) {
            RuntimeHelpers.fillNil(reallocated, valuesLength, newLength, this.getRuntime());
            this.safeArrayCopy(this.values, this.begin, reallocated, 0, valuesLength);
        }
        else {
            this.safeArrayCopy(this.values, this.begin, reallocated, 0, newLength);
        }
        this.begin = 0;
        this.values = reallocated;
    }
    
    private static void fill(final IRubyObject[] arr, final int from, final int to, final IRubyObject with) {
        for (int i = from; i < to; ++i) {
            arr[i] = with;
        }
    }
    
    private static final void checkLength(final Ruby runtime, final long length) {
        if (length < 0L) {
            throw runtime.newArgumentError("negative array size (or size too big)");
        }
        if (length >= 2147483647L) {
            throw runtime.newArgumentError("array size too big");
        }
    }
    
    public List getList() {
        return Arrays.asList(this.toJavaArray());
    }
    
    public int getLength() {
        return this.realLength;
    }
    
    public void setRArray(final RArray rarray) {
        this.rarray = rarray;
    }
    
    public RArray getRArray() {
        return this.rarray;
    }
    
    public IRubyObject[] toJavaArray() {
        final IRubyObject[] copy = new IRubyObject[this.realLength];
        this.safeArrayCopy(this.values, this.begin, copy, 0, this.realLength);
        return copy;
    }
    
    public IRubyObject[] toJavaArrayUnsafe() {
        return this.isShared ? this.toJavaArray() : this.values;
    }
    
    public IRubyObject[] toJavaArrayMaybeUnsafe() {
        return (!this.isShared && this.begin == 0 && this.values.length == this.realLength) ? this.values : this.toJavaArray();
    }
    
    private RubyArray makeShared() {
        return this.makeShared(this.begin, this.realLength, this.getMetaClass());
    }
    
    private RubyArray makeShared(final int beg, final int len, final RubyClass klass) {
        return this.makeShared(beg, len, new RubyArray(klass.getRuntime(), klass));
    }
    
    private RubyArray makeShared(final int beg, final int len, final RubyArray sharedArray) {
        this.isShared = true;
        sharedArray.values = this.values;
        sharedArray.isShared = true;
        sharedArray.begin = beg;
        sharedArray.realLength = len;
        return sharedArray;
    }
    
    private RubyArray makeSharedFirst(final ThreadContext context, final IRubyObject num, final boolean last, final RubyClass klass) {
        int n = RubyNumeric.num2int(num);
        if (n > this.realLength) {
            n = this.realLength;
        }
        else if (n < 0) {
            throw context.getRuntime().newArgumentError("negative array size");
        }
        return this.makeShared(last ? (this.begin + this.realLength - n) : this.begin, n, klass);
    }
    
    private final void modifyCheck() {
        if ((this.flags & 0x204) != 0x0) {
            if ((this.flags & 0x4) != 0x0) {
                throw this.getRuntime().newFrozenError("array");
            }
            if ((this.flags & 0x200) != 0x0) {
                throw this.getRuntime().newTypeError("can't modify array during iteration");
            }
        }
        if (!this.isTaint() && this.getRuntime().getSafeLevel() >= 4) {
            throw this.getRuntime().newSecurityError("Insecure: can't modify array");
        }
    }
    
    private final void modify() {
        this.modifyCheck();
        if (this.isShared) {
            final IRubyObject[] vals = new IRubyObject[this.realLength];
            this.isShared = false;
            this.safeArrayCopy(this.values, this.begin, vals, 0, this.realLength);
            this.begin = 0;
            this.values = vals;
        }
    }
    
    public IRubyObject initialize(final ThreadContext context, final IRubyObject[] args, final Block block) {
        switch (args.length) {
            case 0: {
                return this.initialize(context, block);
            }
            case 1: {
                return this.initializeCommon(context, args[0], null, block);
            }
            case 2: {
                return this.initializeCommon(context, args[0], args[1], block);
            }
            default: {
                Arity.raiseArgumentError(this.getRuntime(), args.length, 0, 2);
                return null;
            }
        }
    }
    
    @JRubyMethod(visibility = Visibility.PRIVATE)
    public IRubyObject initialize(final ThreadContext context, final Block block) {
        this.modifyCheck();
        final Ruby runtime = context.getRuntime();
        this.realLength = 0;
        if (block.isGiven() && runtime.isVerbose()) {
            runtime.getWarnings().warning(IRubyWarnings.ID.BLOCK_UNUSED, "given block not used", new Object[0]);
        }
        return this;
    }
    
    @JRubyMethod(visibility = Visibility.PRIVATE)
    public IRubyObject initialize(final ThreadContext context, final IRubyObject arg0, final Block block) {
        return this.initializeCommon(context, arg0, null, block);
    }
    
    @JRubyMethod(visibility = Visibility.PRIVATE)
    public IRubyObject initialize(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        return this.initializeCommon(context, arg0, arg1, block);
    }
    
    private IRubyObject initializeCommon(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        final Ruby runtime = context.getRuntime();
        if (arg1 == null && !(arg0 instanceof RubyFixnum)) {
            final IRubyObject val = arg0.checkArrayType();
            if (!val.isNil()) {
                this.replace(val);
                return this;
            }
        }
        final long len = RubyNumeric.num2long(arg0);
        if (len < 0L) {
            throw runtime.newArgumentError("negative array size");
        }
        if (len >= 2147483647L) {
            throw runtime.newArgumentError("array size too big");
        }
        final int ilen = (int)len;
        this.modify();
        if (ilen > this.values.length - this.begin) {
            this.values = new IRubyObject[ilen];
            this.begin = 0;
        }
        if (block.isGiven()) {
            if (arg1 != null) {
                runtime.getWarnings().warn(IRubyWarnings.ID.BLOCK_BEATS_DEFAULT_VALUE, "block supersedes default value argument", new Object[0]);
            }
            if (block.getBody().getArgumentType() == 0) {
                final IRubyObject nil = runtime.getNil();
                for (int i = 0; i < ilen; ++i) {
                    this.store(i, block.yield(context, nil));
                    this.realLength = i + 1;
                }
            }
            else {
                for (int j = 0; j < ilen; ++j) {
                    this.store(j, block.yield(context, RubyFixnum.newFixnum(runtime, j)));
                    this.realLength = j + 1;
                }
            }
        }
        else {
            try {
                if (arg1 == null) {
                    RuntimeHelpers.fillNil(this.values, this.begin, this.begin + ilen, runtime);
                }
                else {
                    fill(this.values, this.begin, this.begin + ilen, arg1);
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                this.concurrentModification();
            }
            this.realLength = ilen;
        }
        return this;
    }
    
    @JRubyMethod(name = { "initialize_copy" }, required = 1, visibility = Visibility.PRIVATE)
    public IRubyObject initialize_copy(final IRubyObject orig) {
        return this.replace(orig);
    }
    
    public IRubyObject dup() {
        if (this.metaClass.index != 3) {
            return super.dup();
        }
        final RubyArray rubyArray;
        final RubyArray dup = rubyArray = new RubyArray(this.metaClass.getClassRuntime(), this.values, this.begin, this.realLength);
        final boolean b = true;
        this.isShared = b;
        rubyArray.isShared = b;
        final RubyArray rubyArray2 = dup;
        rubyArray2.flags |= (this.flags & 0x8);
        final RubyArray rubyArray3 = dup;
        rubyArray3.flags |= (this.flags & 0x10);
        return dup;
    }
    
    @JRubyMethod(name = { "replace" }, required = 1)
    public IRubyObject replace(final IRubyObject orig) {
        this.modifyCheck();
        final RubyArray origArr = orig.convertToArray();
        if (this == orig) {
            return this;
        }
        origArr.isShared = true;
        this.isShared = true;
        this.values = origArr.values;
        this.realLength = origArr.realLength;
        this.begin = origArr.begin;
        return this;
    }
    
    @JRubyMethod(name = { "to_s" })
    public IRubyObject to_s() {
        if (this.getRuntime().is1_9()) {
            return this.inspect();
        }
        if (this.realLength == 0) {
            return RubyString.newEmptyString(this.getRuntime());
        }
        return this.join(this.getRuntime().getCurrentContext(), this.getRuntime().getGlobalVariables().get("$,"));
    }
    
    public boolean includes(final ThreadContext context, final IRubyObject item) {
        final int myBegin = this.begin;
        final int end = myBegin + this.realLength;
        final IRubyObject[] values = this.values;
        for (int i = myBegin; i < end; ++i) {
            final IRubyObject value = this.safeArrayRef(values, i);
            if (RubyObject.equalInternal(context, value, item)) {
                return true;
            }
        }
        return false;
    }
    
    @JRubyMethod(name = { "hash" }, compat = CompatVersion.RUBY1_8)
    public RubyFixnum hash(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        if (runtime.isInspecting(this)) {
            return RubyFixnum.zero(runtime);
        }
        try {
            runtime.registerInspecting(this);
            final int myBegin = this.begin;
            int h = this.realLength;
            for (int i = myBegin; i < myBegin + this.realLength; ++i) {
                h = (h << 1 | ((h < 0) ? 1 : 0));
                final IRubyObject value = this.safeArrayRef(this.values, i);
                h ^= (int)RubyNumeric.num2long(RuntimeHelpers.invokedynamic(context, value, 3));
            }
            return runtime.newFixnum(h);
        }
        finally {
            runtime.unregisterInspecting(this);
        }
    }
    
    @JRubyMethod(name = { "hash" }, compat = CompatVersion.RUBY1_9)
    public RubyFixnum hash19(final ThreadContext context) {
        return (RubyFixnum)this.getRuntime().execRecursiveOuter(new Ruby.RecursiveFunction() {
            public IRubyObject call(final IRubyObject obj, final boolean recur) {
                final int begin = RubyArray.this.begin;
                long h = RubyArray.this.realLength;
                if (recur) {
                    h ^= RubyNumeric.num2long(RuntimeHelpers.invokedynamic(context, context.runtime.getArray(), 3));
                }
                else {
                    for (int i = begin; i < begin + RubyArray.this.realLength; ++i) {
                        h = (h << 1 | ((h < 0L) ? 1 : 0));
                        final IRubyObject value = RubyArray.this.safeArrayRef(RubyArray.this.values, i);
                        h ^= RubyNumeric.num2long(RuntimeHelpers.invokedynamic(context, value, 3));
                    }
                }
                return RubyArray.this.getRuntime().newFixnum(h);
            }
        }, this);
    }
    
    public final IRubyObject store(long index, final IRubyObject value) {
        if (index < 0L && (index += this.realLength) < 0L) {
            throw this.getRuntime().newIndexError("index " + (index - this.realLength) + " out of array");
        }
        this.modify();
        if (index >= this.realLength) {
            final int valuesLength = this.values.length - this.begin;
            if (index >= valuesLength) {
                this.storeRealloc(index, valuesLength);
            }
            this.realLength = (int)index + 1;
        }
        this.safeArraySet(this.values, this.begin + (int)index, value);
        return value;
    }
    
    private void storeRealloc(final long index, final int valuesLength) {
        long newLength = valuesLength >> 1;
        if (newLength < 16L) {
            newLength = 16L;
        }
        newLength += index;
        if (index >= 2147483647L || newLength >= 2147483647L) {
            throw this.getRuntime().newArgumentError("index too big");
        }
        this.realloc((int)newLength, valuesLength);
    }
    
    private final IRubyObject elt(final long offset) {
        if (offset < 0L || offset >= this.realLength) {
            return this.getRuntime().getNil();
        }
        return this.eltOk(offset);
    }
    
    public final IRubyObject eltOk(final long offset) {
        return this.safeArrayRef(this.values, this.begin + (int)offset);
    }
    
    public final IRubyObject entry(final long offset) {
        return (offset < 0L) ? this.elt(offset + this.realLength) : this.elt(offset);
    }
    
    public final IRubyObject entry(final int offset) {
        return (offset < 0) ? this.elt(offset + this.realLength) : this.elt(offset);
    }
    
    public final IRubyObject eltInternal(final int offset) {
        return this.values[this.begin + offset];
    }
    
    public final IRubyObject eltInternalSet(final int offset, final IRubyObject item) {
        return this.values[this.begin + offset] = item;
    }
    
    public IRubyObject fetch(final ThreadContext context, final IRubyObject[] args, final Block block) {
        switch (args.length) {
            case 1: {
                return this.fetch(context, args[0], block);
            }
            case 2: {
                return this.fetch(context, args[0], args[1], block);
            }
            default: {
                Arity.raiseArgumentError(this.getRuntime(), args.length, 1, 2);
                return null;
            }
        }
    }
    
    @JRubyMethod
    public IRubyObject fetch(final ThreadContext context, final IRubyObject arg0, final Block block) {
        long index = RubyNumeric.num2long(arg0);
        if (index < 0L) {
            index += this.realLength;
        }
        if (index >= 0L && index < this.realLength) {
            return this.safeArrayRef(this.values, this.begin + (int)index);
        }
        if (block.isGiven()) {
            return block.yield(context, arg0);
        }
        throw this.getRuntime().newIndexError("index " + index + " out of array");
    }
    
    @JRubyMethod
    public IRubyObject fetch(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        if (block.isGiven()) {
            this.getRuntime().getWarnings().warn(IRubyWarnings.ID.BLOCK_BEATS_DEFAULT_VALUE, "block supersedes default value argument", new Object[0]);
        }
        long index = RubyNumeric.num2long(arg0);
        if (index < 0L) {
            index += this.realLength;
        }
        if (index >= 0L && index < this.realLength) {
            return this.safeArrayRef(this.values, this.begin + (int)index);
        }
        if (block.isGiven()) {
            return block.yield(context, arg0);
        }
        return arg1;
    }
    
    private static RubyArray aryToAry(final IRubyObject obj) {
        if (obj instanceof RubyArray) {
            return (RubyArray)obj;
        }
        if (obj.respondsTo("to_ary")) {
            return obj.convertToArray();
        }
        final RubyArray arr = new RubyArray(obj.getRuntime(), false);
        arr.values = new IRubyObject[] { obj };
        arr.realLength = 1;
        return arr;
    }
    
    private final void splice(long beg, long len, final IRubyObject rpl, final boolean oneNine) {
        if (len < 0L) {
            throw this.getRuntime().newIndexError("negative length (" + len + ")");
        }
        if (beg < 0L && (beg += this.realLength) < 0L) {
            throw this.getRuntime().newIndexError("index " + (beg - this.realLength) + " out of array");
        }
        RubyArray rplArr;
        int rlen;
        if (rpl == null || (rpl.isNil() && !oneNine)) {
            rplArr = null;
            rlen = 0;
        }
        else if (rpl.isNil()) {
            rplArr = newArray(this.getRuntime(), rpl);
            rlen = 1;
        }
        else {
            rplArr = aryToAry(rpl);
            rlen = rplArr.realLength;
        }
        this.modify();
        final int valuesLength = this.values.length - this.begin;
        if (beg >= this.realLength) {
            len = beg + rlen;
            if (len >= valuesLength) {
                this.spliceRealloc((int)len, valuesLength);
            }
            try {
                RuntimeHelpers.fillNil(this.values, this.begin + this.realLength, this.begin + (int)beg, this.getRuntime());
            }
            catch (ArrayIndexOutOfBoundsException e) {
                this.concurrentModification();
            }
            this.realLength = (int)len;
        }
        else {
            if (beg + len > this.realLength) {
                len = this.realLength - beg;
            }
            final int alen = this.realLength + rlen - (int)len;
            if (alen >= valuesLength) {
                this.spliceRealloc(alen, valuesLength);
            }
            if (len != rlen) {
                this.safeArrayCopy(this.values, this.begin + (int)(beg + len), this.values, this.begin + (int)beg + rlen, this.realLength - (int)(beg + len));
                this.realLength = alen;
            }
        }
        if (rlen > 0) {
            this.safeArrayCopy(rplArr.values, rplArr.begin, this.values, this.begin + (int)beg, rlen);
        }
    }
    
    private final void spliceOne(long beg, final IRubyObject rpl) {
        if (beg < 0L && (beg += this.realLength) < 0L) {
            throw this.getRuntime().newIndexError("index " + (beg - this.realLength) + " out of array");
        }
        this.modify();
        final int valuesLength = this.values.length - this.begin;
        if (beg >= this.realLength) {
            final int len = (int)beg + 1;
            if (len >= valuesLength) {
                this.spliceRealloc(len, valuesLength);
            }
            RuntimeHelpers.fillNil(this.values, this.begin + this.realLength, this.begin + (int)beg, this.getRuntime());
            this.realLength = len;
        }
        else {
            final int len = (beg > this.realLength) ? (this.realLength - (int)beg) : 0;
            final int alen = this.realLength + 1 - len;
            if (alen >= valuesLength) {
                this.spliceRealloc(alen, valuesLength);
            }
            if (len == 0) {
                this.safeArrayCopy(this.values, this.begin + (int)beg, this.values, this.begin + (int)beg + 1, this.realLength - (int)beg);
                this.realLength = alen;
            }
        }
        this.safeArraySet(this.values, this.begin + (int)beg, rpl);
    }
    
    private void spliceRealloc(final int length, final int valuesLength) {
        final int tryLength = valuesLength + (valuesLength >> 1);
        final int len = (length > tryLength) ? length : tryLength;
        final IRubyObject[] vals = new IRubyObject[len];
        System.arraycopy(this.values, this.begin, vals, 0, this.realLength);
        if (len > length) {
            RuntimeHelpers.fillNil(vals, length, len, this.getRuntime());
        }
        this.begin = 0;
        this.values = vals;
    }
    
    @JRubyMethod
    public IRubyObject insert() {
        throw this.getRuntime().newArgumentError(0, 1);
    }
    
    @JRubyMethod(name = { "insert" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject insert(final IRubyObject arg) {
        return this;
    }
    
    @JRubyMethod(name = { "insert" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject insert19(final IRubyObject arg) {
        this.modifyCheck();
        return this.insert(arg);
    }
    
    @JRubyMethod(name = { "insert" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject insert(final IRubyObject arg1, final IRubyObject arg2) {
        long pos = RubyNumeric.num2long(arg1);
        if (pos == -1L) {
            pos = this.realLength;
        }
        if (pos < 0L) {
            ++pos;
        }
        this.spliceOne(pos, arg2);
        return this;
    }
    
    @JRubyMethod(name = { "insert" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject insert19(final IRubyObject arg1, final IRubyObject arg2) {
        this.modifyCheck();
        return this.insert(arg1, arg2);
    }
    
    @JRubyMethod(name = { "insert" }, required = 1, rest = true, compat = CompatVersion.RUBY1_8)
    public IRubyObject insert(final IRubyObject[] args) {
        if (args.length == 1) {
            return this;
        }
        long pos = RubyNumeric.num2long(args[0]);
        if (pos == -1L) {
            pos = this.realLength;
        }
        if (pos < 0L) {
            ++pos;
        }
        final RubyArray inserted = new RubyArray(this.getRuntime(), false);
        inserted.values = args;
        inserted.begin = 1;
        inserted.realLength = args.length - 1;
        this.splice(pos, 0L, inserted, false);
        return this;
    }
    
    @JRubyMethod(name = { "insert" }, required = 1, rest = true, compat = CompatVersion.RUBY1_9)
    public IRubyObject insert19(final IRubyObject[] args) {
        this.modifyCheck();
        return this.insert(args);
    }
    
    public final RubyArray aryDup() {
        final RubyArray dup = new RubyArray(this.metaClass.getClassRuntime(), this.metaClass, this.values, this.begin, this.realLength);
        dup.isShared = true;
        this.isShared = true;
        final RubyArray rubyArray = dup;
        rubyArray.flags |= (this.flags & 0x18);
        return dup;
    }
    
    @JRubyMethod(name = { "transpose" })
    public RubyArray transpose() {
        RubyArray result = null;
        final int alen = this.realLength;
        if (alen == 0) {
            return this.aryDup();
        }
        final Ruby runtime = this.getRuntime();
        int elen = -1;
        for (int end = this.begin + alen, i = this.begin; i < end; ++i) {
            final RubyArray tmp = this.elt(i).convertToArray();
            if (elen < 0) {
                elen = tmp.realLength;
                result = new RubyArray(runtime, elen);
                for (int j = 0; j < elen; ++j) {
                    result.store(j, new RubyArray(runtime, alen));
                }
            }
            else if (elen != tmp.realLength) {
                throw runtime.newIndexError("element size differs (" + tmp.realLength + " should be " + elen + ")");
            }
            for (int j = 0; j < elen; ++j) {
                ((RubyArray)result.elt(j)).store(i - this.begin, tmp.elt(j));
            }
        }
        return result;
    }
    
    private final IRubyObject values_at(final long olen, final IRubyObject[] args) {
        final RubyArray result = new RubyArray(this.getRuntime(), args.length);
        for (int i = 0; i < args.length; ++i) {
            if (args[i] instanceof RubyFixnum) {
                result.append(this.entry(((RubyFixnum)args[i]).getLongValue()));
            }
            else if (!(args[i] instanceof RubyRange)) {
                result.append(this.entry(RubyNumeric.num2long(args[i])));
            }
            else {
                final long[] beglen;
                if ((beglen = ((RubyRange)args[i]).begLen(olen, 0)) != null) {
                    final int beg = (int)beglen[0];
                    final int len = (int)beglen[1];
                    for (int end = this.begin + len, j = this.begin; j < end; ++j) {
                        result.append(this.entry(j + beg));
                    }
                }
            }
        }
        RuntimeHelpers.fillNil(result.values, result.realLength, result.values.length, this.getRuntime());
        return result;
    }
    
    @JRubyMethod(name = { "values_at" }, rest = true)
    public IRubyObject values_at(final IRubyObject[] args) {
        return this.values_at(this.realLength, args);
    }
    
    public IRubyObject subseq(final long beg, long len) {
        final int realLength = this.realLength;
        if (beg > realLength || beg < 0L || len < 0L) {
            return this.getRuntime().getNil();
        }
        if (beg + len > realLength) {
            len = realLength - beg;
            if (len < 0L) {
                len = 0L;
            }
        }
        if (len == 0L) {
            return new RubyArray(this.getRuntime(), this.getMetaClass(), IRubyObject.NULL_ARRAY);
        }
        return this.makeShared(this.begin + (int)beg, (int)len, this.getMetaClass());
    }
    
    public IRubyObject subseqLight(final long beg, long len) {
        final Ruby runtime = this.getRuntime();
        if (beg > this.realLength || beg < 0L || len < 0L) {
            return runtime.getNil();
        }
        if (beg + len > this.realLength) {
            len = this.realLength - beg;
            if (len < 0L) {
                len = 0L;
            }
        }
        if (len == 0L) {
            return new RubyArray(runtime, this.getMetaClass(), IRubyObject.NULL_ARRAY, false);
        }
        return this.makeShared(this.begin + (int)beg, (int)len, new RubyArray(runtime, this.getMetaClass(), false));
    }
    
    @JRubyMethod(name = { "length" }, alias = { "size" })
    public RubyFixnum length() {
        return this.getRuntime().newFixnum(this.realLength);
    }
    
    @JRubyMethod(name = { "<<" }, required = 1)
    public RubyArray append(final IRubyObject item) {
        this.modify();
        final int valuesLength = this.values.length - this.begin;
        if (this.realLength == valuesLength) {
            if (this.realLength == Integer.MAX_VALUE) {
                throw this.getRuntime().newArgumentError("index too big");
            }
            long newLength = valuesLength + (valuesLength >> 1);
            if (newLength > 2147483647L) {
                newLength = 2147483647L;
            }
            else if (newLength < 16L) {
                newLength = 16L;
            }
            this.realloc((int)newLength, valuesLength);
        }
        this.safeArraySet(this.values, this.begin + this.realLength++, item);
        return this;
    }
    
    @JRubyMethod(name = { "push" }, rest = true, compat = CompatVersion.RUBY1_8)
    public RubyArray push_m(final IRubyObject[] items) {
        for (int i = 0; i < items.length; ++i) {
            this.append(items[i]);
        }
        return this;
    }
    
    @JRubyMethod(name = { "push" }, rest = true, compat = CompatVersion.RUBY1_9)
    public RubyArray push_m19(final IRubyObject[] items) {
        this.modifyCheck();
        return this.push_m(items);
    }
    
    @JRubyMethod
    public IRubyObject pop(final ThreadContext context) {
        this.modifyCheck();
        if (this.realLength == 0) {
            return context.getRuntime().getNil();
        }
        if (this.isShared) {
            final IRubyObject[] values = this.values;
            final int begin = this.begin;
            final int realLength = this.realLength - 1;
            this.realLength = realLength;
            return this.safeArrayRef(values, begin + realLength);
        }
        final int begin2 = this.begin;
        final int realLength2 = this.realLength - 1;
        this.realLength = realLength2;
        final int index = begin2 + realLength2;
        return this.safeArrayRefSet(this.values, index, context.getRuntime().getNil());
    }
    
    @JRubyMethod
    public IRubyObject pop(final ThreadContext context, final IRubyObject num) {
        this.modifyCheck();
        final RubyArray result = this.makeSharedFirst(context, num, true, context.getRuntime().getArray());
        this.realLength -= result.realLength;
        return result;
    }
    
    @JRubyMethod(name = { "shift" })
    public IRubyObject shift(final ThreadContext context) {
        this.modifyCheck();
        final Ruby runtime = context.getRuntime();
        if (this.realLength == 0) {
            return runtime.getNil();
        }
        final IRubyObject obj = this.safeArrayRefCondSet(this.values, this.begin, !this.isShared, runtime.getNil());
        ++this.begin;
        --this.realLength;
        return obj;
    }
    
    @JRubyMethod(name = { "shift" })
    public IRubyObject shift(final ThreadContext context, final IRubyObject num) {
        this.modify();
        final RubyArray result = this.makeSharedFirst(context, num, false, context.getRuntime().getArray());
        final int n = result.realLength;
        this.begin += n;
        this.realLength -= n;
        return result;
    }
    
    @JRubyMethod(name = { "unshift" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject unshift() {
        return this;
    }
    
    @JRubyMethod(name = { "unshift" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject unshift19() {
        this.modifyCheck();
        return this;
    }
    
    @JRubyMethod(name = { "unshift" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject unshift(final IRubyObject item) {
        if (this.begin == 0 || this.isShared) {
            this.modify();
            final int valuesLength = this.values.length - this.begin;
            if (this.realLength == valuesLength) {
                int newLength = valuesLength >> 1;
                if (newLength < 16) {
                    newLength = 16;
                }
                newLength += valuesLength;
                final IRubyObject[] vals = new IRubyObject[newLength];
                this.safeArrayCopy(this.values, this.begin, vals, 1, valuesLength);
                RuntimeHelpers.fillNil(vals, valuesLength + 1, newLength, this.getRuntime());
                this.values = vals;
                this.begin = 0;
            }
            else {
                this.safeArrayCopy(this.values, this.begin, this.values, this.begin + 1, this.realLength);
            }
        }
        else {
            this.modifyCheck();
            --this.begin;
        }
        ++this.realLength;
        this.values[this.begin] = item;
        return this;
    }
    
    @JRubyMethod(name = { "unshift" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject unshift19(final IRubyObject item) {
        this.modifyCheck();
        return this.unshift(item);
    }
    
    @JRubyMethod(name = { "unshift" }, rest = true, compat = CompatVersion.RUBY1_8)
    public IRubyObject unshift(final IRubyObject[] items) {
        final long len = this.realLength;
        if (items.length == 0) {
            return this;
        }
        this.store(len + items.length - 1L, this.getRuntime().getNil());
        try {
            System.arraycopy(this.values, this.begin, this.values, this.begin + items.length, (int)len);
            System.arraycopy(items, 0, this.values, this.begin, items.length);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            this.concurrentModification();
        }
        return this;
    }
    
    @JRubyMethod(name = { "unshift" }, rest = true, compat = CompatVersion.RUBY1_9)
    public IRubyObject unshift19(final IRubyObject[] items) {
        this.modifyCheck();
        return this.unshift(items);
    }
    
    @JRubyMethod(name = { "include?" }, required = 1)
    public RubyBoolean include_p(final ThreadContext context, final IRubyObject item) {
        return context.getRuntime().newBoolean(this.includes(context, item));
    }
    
    @JRubyMethod(name = { "frozen?" })
    public RubyBoolean frozen_p(final ThreadContext context) {
        return context.getRuntime().newBoolean(this.isFrozen() || (this.flags & 0x200) != 0x0);
    }
    
    public IRubyObject aref(final IRubyObject[] args) {
        switch (args.length) {
            case 1: {
                return this.aref(args[0]);
            }
            case 2: {
                return this.aref(args[0], args[1]);
            }
            default: {
                Arity.raiseArgumentError(this.getRuntime(), args.length, 1, 2);
                return null;
            }
        }
    }
    
    @JRubyMethod(name = { "[]", "slice" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject aref(final IRubyObject arg0) {
        assert !arg0.getRuntime().is1_9();
        if (arg0 instanceof RubyFixnum) {
            return this.entry(((RubyFixnum)arg0).getLongValue());
        }
        if (arg0 instanceof RubySymbol) {
            throw this.getRuntime().newTypeError("Symbol as array index");
        }
        return this.arefCommon(arg0);
    }
    
    @JRubyMethod(name = { "[]", "slice" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject aref19(final IRubyObject arg0) {
        return (arg0 instanceof RubyFixnum) ? this.entry(((RubyFixnum)arg0).getLongValue()) : this.arefCommon(arg0);
    }
    
    private IRubyObject arefCommon(final IRubyObject arg0) {
        if (arg0 instanceof RubyRange) {
            final long[] beglen = ((RubyRange)arg0).begLen(this.realLength, 0);
            return (beglen == null) ? this.getRuntime().getNil() : this.subseq(beglen[0], beglen[1]);
        }
        return this.entry(RubyNumeric.num2long(arg0));
    }
    
    @JRubyMethod(name = { "[]", "slice" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject aref(final IRubyObject arg0, final IRubyObject arg1) {
        assert !arg0.getRuntime().is1_9();
        if (arg0 instanceof RubySymbol) {
            throw this.getRuntime().newTypeError("Symbol as array index");
        }
        return this.arefCommon(arg0, arg1);
    }
    
    @JRubyMethod(name = { "[]", "slice" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject aref19(final IRubyObject arg0, final IRubyObject arg1) {
        return this.arefCommon(arg0, arg1);
    }
    
    private IRubyObject arefCommon(final IRubyObject arg0, final IRubyObject arg1) {
        long beg = RubyNumeric.num2long(arg0);
        if (beg < 0L) {
            beg += this.realLength;
        }
        return this.subseq(beg, RubyNumeric.num2long(arg1));
    }
    
    public IRubyObject aset(final IRubyObject[] args) {
        switch (args.length) {
            case 2: {
                return this.aset(args[0], args[1]);
            }
            case 3: {
                return this.aset(args[0], args[1], args[2]);
            }
            default: {
                throw this.getRuntime().newArgumentError("wrong number of arguments (" + args.length + " for 2)");
            }
        }
    }
    
    @JRubyMethod(name = { "[]=" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject aset(final IRubyObject arg0, final IRubyObject arg1) {
        assert !this.getRuntime().is1_9();
        if (arg0 instanceof RubyFixnum) {
            this.store(((RubyFixnum)arg0).getLongValue(), arg1);
        }
        else if (arg0 instanceof RubyRange) {
            final long[] beglen = ((RubyRange)arg0).begLen(this.realLength, 1);
            this.splice(beglen[0], beglen[1], arg1, false);
        }
        else {
            if (arg0 instanceof RubySymbol) {
                throw this.getRuntime().newTypeError("Symbol as array index");
            }
            this.store(RubyNumeric.num2long(arg0), arg1);
        }
        return arg1;
    }
    
    @JRubyMethod(name = { "[]=" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject aset19(final IRubyObject arg0, final IRubyObject arg1) {
        this.modifyCheck();
        if (arg0 instanceof RubyFixnum) {
            this.store(((RubyFixnum)arg0).getLongValue(), arg1);
        }
        else if (arg0 instanceof RubyRange) {
            final long[] beglen = ((RubyRange)arg0).begLen(this.realLength, 1);
            this.splice(beglen[0], beglen[1], arg1, true);
        }
        else {
            this.store(RubyNumeric.num2long(arg0), arg1);
        }
        return arg1;
    }
    
    @JRubyMethod(name = { "[]=" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject aset(final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2) {
        assert !this.getRuntime().is1_9();
        if (arg0 instanceof RubySymbol) {
            throw this.getRuntime().newTypeError("Symbol as array index");
        }
        if (arg1 instanceof RubySymbol) {
            throw this.getRuntime().newTypeError("Symbol as subarray length");
        }
        this.splice(RubyNumeric.num2long(arg0), RubyNumeric.num2long(arg1), arg2, false);
        return arg2;
    }
    
    @JRubyMethod(name = { "[]=" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject aset19(final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2) {
        this.modifyCheck();
        this.splice(RubyNumeric.num2long(arg0), RubyNumeric.num2long(arg1), arg2, true);
        return arg2;
    }
    
    @JRubyMethod(name = { "at" }, required = 1)
    public IRubyObject at(final IRubyObject pos) {
        return this.entry(RubyNumeric.num2long(pos));
    }
    
    @JRubyMethod(name = { "concat" }, required = 1, compat = CompatVersion.RUBY1_8)
    public RubyArray concat(final IRubyObject obj) {
        final RubyArray ary = obj.convertToArray();
        if (ary.realLength > 0) {
            this.splice(this.realLength, 0L, ary, false);
        }
        return this;
    }
    
    @JRubyMethod(name = { "concat" }, required = 1, compat = CompatVersion.RUBY1_9)
    public RubyArray concat19(final IRubyObject obj) {
        this.modifyCheck();
        return this.concat(obj);
    }
    
    private IRubyObject inspectAry(final ThreadContext context) {
        final ByteList buffer = new ByteList();
        buffer.append(91);
        boolean tainted = this.isTaint();
        boolean untrust = this.isUntrusted();
        for (int i = 0; i < this.realLength; ++i) {
            if (i > 0) {
                buffer.append(44).append(32);
            }
            final RubyString str = RubyObject.inspect(context, this.safeArrayRef(this.values, this.begin + i));
            if (str.isTaint()) {
                tainted = true;
            }
            if (str.isUntrusted()) {
                untrust = true;
            }
            buffer.append(str.getByteList());
        }
        buffer.append(93);
        final RubyString str2 = this.getRuntime().newString(buffer);
        if (tainted) {
            str2.setTaint(true);
        }
        if (untrust) {
            str2.setUntrusted(true);
        }
        return str2;
    }
    
    @JRubyMethod(name = { "inspect" })
    public IRubyObject inspect() {
        if (this.realLength == 0) {
            return this.getRuntime().newString("[]");
        }
        if (this.getRuntime().isInspecting(this)) {
            return this.getRuntime().newString("[...]");
        }
        try {
            this.getRuntime().registerInspecting(this);
            return this.inspectAry(this.getRuntime().getCurrentContext());
        }
        finally {
            this.getRuntime().unregisterInspecting(this);
        }
    }
    
    public IRubyObject first(final IRubyObject[] args) {
        switch (args.length) {
            case 0: {
                return this.first();
            }
            case 1: {
                return this.first(args[0]);
            }
            default: {
                Arity.raiseArgumentError(this.getRuntime(), args.length, 0, 1);
                return null;
            }
        }
    }
    
    @JRubyMethod(name = { "first" })
    public IRubyObject first() {
        if (this.realLength == 0) {
            return this.getRuntime().getNil();
        }
        return this.values[this.begin];
    }
    
    @JRubyMethod(name = { "first" })
    public IRubyObject first(final IRubyObject arg0) {
        long n = RubyNumeric.num2long(arg0);
        if (n > this.realLength) {
            n = this.realLength;
        }
        else if (n < 0L) {
            throw this.getRuntime().newArgumentError("negative array size (or size too big)");
        }
        return this.makeShared(this.begin, (int)n, this.getRuntime().getArray());
    }
    
    public IRubyObject last(final IRubyObject[] args) {
        switch (args.length) {
            case 0: {
                return this.last();
            }
            case 1: {
                return this.last(args[0]);
            }
            default: {
                Arity.raiseArgumentError(this.getRuntime(), args.length, 0, 1);
                return null;
            }
        }
    }
    
    @JRubyMethod(name = { "last" })
    public IRubyObject last() {
        if (this.realLength == 0) {
            return this.getRuntime().getNil();
        }
        return this.values[this.begin + this.realLength - 1];
    }
    
    @JRubyMethod(name = { "last" })
    public IRubyObject last(final IRubyObject arg0) {
        long n = RubyNumeric.num2long(arg0);
        if (n > this.realLength) {
            n = this.realLength;
        }
        else if (n < 0L) {
            throw this.getRuntime().newArgumentError("negative array size (or size too big)");
        }
        return this.makeShared(this.begin + this.realLength - (int)n, (int)n, this.getRuntime().getArray());
    }
    
    public IRubyObject eachCommon(final ThreadContext context, final Block block) {
        if (!block.isGiven()) {
            throw context.getRuntime().newLocalJumpErrorNoBlock();
        }
        for (int i = 0; i < this.realLength; ++i) {
            block.yield(context, this.safeArrayRef(this.values, this.begin + i));
        }
        return this;
    }
    
    @JRubyMethod
    public IRubyObject each(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.eachCommon(context, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "each");
    }
    
    public IRubyObject eachSlice(final ThreadContext context, final int size, final Block block) {
        final Ruby runtime = context.getRuntime();
        int localRealLength = this.realLength;
        final IRubyObject[] localValues = this.values;
        int localBegin = this.begin;
        RubyArray window = newArrayNoCopy(runtime, localValues, localBegin, size);
        this.makeShared();
        final boolean specificArity = block.arity().isFixed() && block.arity().required() != 1;
        while (localRealLength >= size) {
            block.yield(context, window);
            if (specificArity) {
                localBegin = (window.begin = localBegin + size);
            }
            else {
                window = newArrayNoCopy(runtime, localValues, localBegin += size, size);
            }
            localRealLength -= size;
        }
        if (localRealLength > 0) {
            window.realLength = localRealLength;
            block.yield(context, window);
        }
        return runtime.getNil();
    }
    
    @JRubyMethod
    public IRubyObject each_slice(final ThreadContext context, final IRubyObject arg, final Block block) {
        final int size = RubyNumeric.num2int(arg);
        final Ruby runtime = context.getRuntime();
        if (size <= 0) {
            throw runtime.newArgumentError("invalid slice size");
        }
        return block.isGiven() ? this.eachSlice(context, size, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "each_slice", arg);
    }
    
    public IRubyObject eachIndex(final ThreadContext context, final Block block) {
        final Ruby runtime = context.getRuntime();
        if (!block.isGiven()) {
            throw runtime.newLocalJumpErrorNoBlock();
        }
        for (int i = 0; i < this.realLength; ++i) {
            block.yield(context, runtime.newFixnum(i));
        }
        return this;
    }
    
    @JRubyMethod
    public IRubyObject each_index(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.eachIndex(context, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "each_index");
    }
    
    public IRubyObject reverseEach(final ThreadContext context, final Block block) {
        for (int len = this.realLength; len-- > 0; len = this.realLength) {
            block.yield(context, this.safeArrayRef(this.values, this.begin + len));
            if (this.realLength < len) {}
        }
        return this;
    }
    
    @JRubyMethod
    public IRubyObject reverse_each(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.reverseEach(context, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "reverse_each");
    }
    
    private IRubyObject inspectJoin(final ThreadContext context, final RubyArray tmp, final IRubyObject sep) {
        final Ruby runtime = context.getRuntime();
        if (runtime.isInspecting(this)) {
            return tmp.join(context, sep);
        }
        try {
            runtime.registerInspecting(this);
            return tmp.join(context, sep);
        }
        finally {
            runtime.unregisterInspecting(this);
        }
    }
    
    @JRubyMethod(name = { "join" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject join(final ThreadContext context, final IRubyObject sep) {
        final Ruby runtime = context.getRuntime();
        if (this.realLength == 0) {
            return RubyString.newEmptyString(runtime);
        }
        boolean taint = this.isTaint() || sep.isTaint();
        boolean untrusted = this.isUntrusted() || sep.isUntrusted();
        int len = 1;
        for (int i = this.begin; i < this.begin + this.realLength; ++i) {
            final IRubyObject value = this.safeArrayRef(this.values, i);
            final IRubyObject tmp = value.checkStringType();
            len += (tmp.isNil() ? 10 : ((RubyString)tmp).getByteList().length());
        }
        ByteList sepBytes = null;
        if (!sep.isNil()) {
            sepBytes = sep.convertToString().getByteList();
            len += sepBytes.getRealSize() * (this.realLength - 1);
        }
        final ByteList buf = new ByteList(len);
        for (int j = 0; j < this.realLength; ++j) {
            IRubyObject tmp2 = this.safeArrayRef(this.values, this.begin + j);
            if (!(tmp2 instanceof RubyString)) {
                if (tmp2 instanceof RubyArray) {
                    if (tmp2 == this || runtime.isInspecting(tmp2)) {
                        tmp2 = runtime.newString("[...]");
                    }
                    else {
                        tmp2 = this.inspectJoin(context, (RubyArray)tmp2, sep);
                    }
                }
                else {
                    tmp2 = RubyString.objAsString(context, tmp2);
                }
            }
            if (j > 0 && sepBytes != null) {
                buf.append(sepBytes);
            }
            buf.append(tmp2.asString().getByteList());
            if (tmp2.isTaint()) {
                taint = true;
            }
            if (tmp2.isUntrusted()) {
                untrusted = true;
            }
        }
        final RubyString result = runtime.newString(buf);
        if (taint) {
            result.setTaint(true);
        }
        if (untrusted) {
            result.untrust(context);
        }
        return result;
    }
    
    @JRubyMethod(name = { "join" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject join(final ThreadContext context) {
        return this.join(context, context.getRuntime().getGlobalVariables().get("$,"));
    }
    
    private boolean[] join0(final ThreadContext context, final ByteList sep, final int max, final ByteList result) {
        boolean t = false;
        boolean u = false;
        try {
            for (int i = this.begin; i < max; ++i) {
                final IRubyObject val = this.values[i];
                if (i > this.begin && sep != null) {
                    result.append(sep);
                }
                result.append(((RubyString)val).getByteList());
                if (val.isTaint()) {
                    t = true;
                }
                if (val.isUntrusted()) {
                    u = true;
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            this.concurrentModification();
            return new boolean[] { t, u };
        }
        return new boolean[] { t, u };
    }
    
    private void join1(final ThreadContext context, IRubyObject obj, final ByteList sep, int i, final ByteList result) {
        while (i < this.begin + this.realLength) {
            if (i > this.begin && sep != null) {
                result.append(sep);
            }
            IRubyObject val = this.safeArrayRef(this.values, i);
            if (val instanceof RubyString) {
                result.append(((RubyString)val).getByteList());
            }
            else if (val instanceof RubyArray) {
                obj = val;
                this.recursiveJoin(context, obj, sep, result, val);
            }
            else {
                IRubyObject tmp = val.checkStringType19();
                if (tmp.isNil()) {
                    tmp = TypeConverter.convertToTypeWithCheck(val, this.getRuntime().getString(), "to_s");
                }
                if (!tmp.isNil()) {
                    val = tmp;
                    result.append(((RubyString)val).getByteList());
                }
                else {
                    tmp = TypeConverter.convertToTypeWithCheck(val, this.getRuntime().getArray(), "to_a");
                    if (!tmp.isNil()) {
                        obj = val;
                        val = tmp;
                        this.recursiveJoin(context, obj, sep, result, val);
                    }
                    else {
                        val = RubyString.objAsString(context, val);
                        result.append(((RubyString)val).getByteList());
                    }
                }
            }
            ++i;
        }
    }
    
    private void recursiveJoin(final ThreadContext context, final IRubyObject obj, final ByteList sep, final ByteList result, final IRubyObject val) {
        if (val == this) {
            throw this.getRuntime().newArgumentError("recursive array join");
        }
        final RubyArray ary = (RubyArray)val;
        this.getRuntime().execRecursive(new Ruby.RecursiveFunction() {
            public IRubyObject call(final IRubyObject obj, final boolean recur) {
                if (recur) {
                    throw RubyArray.this.getRuntime().newArgumentError("recursive array join");
                }
                ary.join1(context, obj, sep, 0, result);
                return RubyArray.this.getRuntime().getNil();
            }
        }, obj);
    }
    
    @JRubyMethod(name = { "join" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject join19(final ThreadContext context, final IRubyObject sep) {
        final Ruby runtime = context.getRuntime();
        if (this.realLength == 0) {
            return RubyString.newEmptyString(runtime);
        }
        final boolean taint = this.isTaint() || sep.isTaint();
        final boolean untrusted = this.isUntrusted() || sep.isUntrusted();
        int len = 1;
        ByteList sepBytes = null;
        if (!sep.isNil()) {
            sepBytes = sep.convertToString().getByteList();
            len += sepBytes.getRealSize() * (this.realLength - 1);
        }
        for (int i = this.begin; i < this.begin + this.realLength; ++i) {
            final IRubyObject val = this.safeArrayRef(this.values, i);
            final IRubyObject tmp = val.checkStringType19();
            if (tmp.isNil() || tmp != val) {
                final ByteList buf = new ByteList(len + (this.begin + this.realLength - i) * 10);
                final boolean[] tu = this.join0(context, sepBytes, i, buf);
                this.join1(context, this, sepBytes, i, buf);
                final RubyString result = runtime.newString(buf);
                if (taint || tu[0]) {
                    result.setTaint(true);
                }
                if (untrusted || tu[1]) {
                    result.untrust(context);
                }
                return result;
            }
            len += ((RubyString)tmp).getByteList().length();
        }
        final ByteList buf2 = new ByteList(len);
        final boolean[] tu2 = this.join0(context, sepBytes, this.begin + this.realLength, buf2);
        final RubyString result2 = runtime.newString(buf2);
        if (taint || tu2[0]) {
            result2.setTaint(true);
        }
        if (untrusted || tu2[1]) {
            result2.untrust(context);
        }
        return result2;
    }
    
    @JRubyMethod(name = { "join" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject join19(final ThreadContext context) {
        return this.join19(context, context.getRuntime().getGlobalVariables().get("$,"));
    }
    
    @JRubyMethod(name = { "to_a" })
    public RubyArray to_a() {
        if (this.getMetaClass() != this.getRuntime().getArray()) {
            final RubyArray dup = new RubyArray(this.getRuntime(), this.getRuntime().isObjectSpaceEnabled());
            this.isShared = true;
            dup.isShared = true;
            dup.values = this.values;
            dup.realLength = this.realLength;
            dup.begin = this.begin;
            return dup;
        }
        return this;
    }
    
    @JRubyMethod(name = { "to_ary" })
    public IRubyObject to_ary() {
        return this;
    }
    
    public RubyArray convertToArray() {
        return this;
    }
    
    public IRubyObject checkArrayType() {
        return this;
    }
    
    @JRubyMethod(name = { "==" }, required = 1)
    public IRubyObject op_equal(final ThreadContext context, final IRubyObject obj) {
        if (this == obj) {
            return context.getRuntime().getTrue();
        }
        if (obj instanceof RubyArray) {
            return RecursiveComparator.compare(context, "==", this, obj, null);
        }
        if (!obj.respondsTo("to_ary")) {
            return context.getRuntime().getFalse();
        }
        return RuntimeHelpers.rbEqual(context, obj, this);
    }
    
    public RubyBoolean compare(final ThreadContext context, final String method, final IRubyObject other, final Set<RecursiveComparator.Pair> seen) {
        final Ruby runtime = context.getRuntime();
        if (!(other instanceof RubyArray)) {
            if (!other.respondsTo("to_ary")) {
                return runtime.getFalse();
            }
            return RuntimeHelpers.rbEqual(context, other, this);
        }
        else {
            final RubyArray ary = (RubyArray)other;
            if (this.realLength != ary.realLength) {
                return runtime.getFalse();
            }
            for (int i = 0; i < this.realLength; ++i) {
                if (!RecursiveComparator.compare(context, method, this.elt(i), ary.elt(i), seen).isTrue()) {
                    return runtime.getFalse();
                }
            }
            return runtime.getTrue();
        }
    }
    
    @JRubyMethod(name = { "eql?" }, required = 1)
    public IRubyObject eql(final ThreadContext context, final IRubyObject obj) {
        return RecursiveComparator.compare(context, "eql?", this, obj, null);
    }
    
    @JRubyMethod(name = { "compact!" })
    public IRubyObject compact_bang() {
        this.modify();
        int t;
        int p = t = this.begin;
        final int end = p + this.realLength;
        try {
            while (t < end) {
                if (this.values[t].isNil()) {
                    ++t;
                }
                else {
                    this.values[p++] = this.values[t++];
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            this.concurrentModification();
        }
        p -= this.begin;
        if (this.realLength == p) {
            return this.getRuntime().getNil();
        }
        this.realloc(p, this.values.length - this.begin);
        this.realLength = p;
        return this;
    }
    
    @JRubyMethod(name = { "compact" })
    public IRubyObject compact() {
        final RubyArray ary = this.aryDup();
        ary.compact_bang();
        return ary;
    }
    
    @JRubyMethod(name = { "empty?" })
    public IRubyObject empty_p() {
        return (this.realLength == 0) ? this.getRuntime().getTrue() : this.getRuntime().getFalse();
    }
    
    @JRubyMethod(name = { "clear" })
    public IRubyObject rb_clear() {
        this.modifyCheck();
        if (this.isShared) {
            this.alloc(16);
            this.isShared = false;
        }
        else if (this.values.length > 32) {
            this.alloc(32);
        }
        else {
            try {
                this.begin = 0;
                RuntimeHelpers.fillNil(this.values, 0, this.realLength, this.getRuntime());
            }
            catch (ArrayIndexOutOfBoundsException e) {
                this.concurrentModification();
            }
        }
        this.realLength = 0;
        return this;
    }
    
    @JRubyMethod
    public IRubyObject fill(final ThreadContext context, final Block block) {
        if (block.isGiven()) {
            return this.fillCommon(context, 0, this.realLength, block);
        }
        throw context.getRuntime().newArgumentError(0, 1);
    }
    
    @JRubyMethod
    public IRubyObject fill(final ThreadContext context, final IRubyObject arg, final Block block) {
        if (!block.isGiven()) {
            return this.fillCommon(context, 0, this.realLength, arg);
        }
        if (arg instanceof RubyRange) {
            final int[] beglen = ((RubyRange)arg).begLenInt(this.realLength, 1);
            return this.fillCommon(context, beglen[0], beglen[1], block);
        }
        final int beg;
        return this.fillCommon(context, beg = this.fillBegin(arg), this.fillLen(beg, null), block);
    }
    
    @JRubyMethod
    public IRubyObject fill(final ThreadContext context, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        if (block.isGiven()) {
            final int beg;
            return this.fillCommon(context, beg = this.fillBegin(arg1), this.fillLen(beg, arg2), block);
        }
        if (arg2 instanceof RubyRange) {
            final int[] beglen = ((RubyRange)arg2).begLenInt(this.realLength, 1);
            return this.fillCommon(context, beglen[0], beglen[1], arg1);
        }
        int beg;
        return this.fillCommon(context, beg = this.fillBegin(arg2), this.fillLen(beg, null), arg1);
    }
    
    @JRubyMethod
    public IRubyObject fill(final ThreadContext context, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final Block block) {
        if (block.isGiven()) {
            throw context.getRuntime().newArgumentError(3, 2);
        }
        final int beg;
        return this.fillCommon(context, beg = this.fillBegin(arg2), this.fillLen(beg, arg3), arg1);
    }
    
    private int fillBegin(final IRubyObject arg) {
        int beg = arg.isNil() ? 0 : RubyNumeric.num2int(arg);
        if (beg < 0) {
            beg += this.realLength;
            if (beg < 0) {
                beg = 0;
            }
        }
        return beg;
    }
    
    private long fillLen(final long beg, final IRubyObject arg) {
        if (arg == null || arg.isNil()) {
            return this.realLength - beg;
        }
        return RubyNumeric.num2long(arg);
    }
    
    private IRubyObject fillCommon(final ThreadContext context, final int beg, final long len, final IRubyObject item) {
        this.modify();
        if (len < 0L) {
            return this;
        }
        if (len > Integer.MAX_VALUE - beg) {
            throw context.getRuntime().newArgumentError("argument too big");
        }
        final int end = (int)(beg + len);
        if (end > this.realLength) {
            final int valuesLength = this.values.length - this.begin;
            if (end >= valuesLength) {
                this.realloc(end, valuesLength);
            }
            this.realLength = end;
        }
        if (len > 0L) {
            try {
                fill(this.values, this.begin + beg, this.begin + end, item);
            }
            catch (ArrayIndexOutOfBoundsException e) {
                this.concurrentModification();
            }
        }
        return this;
    }
    
    private IRubyObject fillCommon(final ThreadContext context, final int beg, final long len, final Block block) {
        this.modify();
        if (len < 0L) {
            return this;
        }
        if (len > Integer.MAX_VALUE - beg) {
            throw this.getRuntime().newArgumentError("argument too big");
        }
        final int end = (int)(beg + len);
        if (end > this.realLength) {
            final int valuesLength = this.values.length - this.begin;
            if (end >= valuesLength) {
                this.realloc(end, valuesLength);
            }
            this.realLength = end;
        }
        final Ruby runtime = context.getRuntime();
        for (int i = beg; i < end; ++i) {
            final IRubyObject v = block.yield(context, runtime.newFixnum(i));
            if (i >= this.realLength) {
                break;
            }
            this.safeArraySet(this.values, this.begin + i, v);
        }
        return this;
    }
    
    public IRubyObject index(final ThreadContext context, final IRubyObject obj) {
        final Ruby runtime = context.getRuntime();
        for (int i = 0; i < this.realLength; ++i) {
            if (RubyObject.equalInternal(context, this.eltOk(i), obj)) {
                return runtime.newFixnum(i);
            }
        }
        return runtime.getNil();
    }
    
    @JRubyMethod(name = { "index", "find_index" })
    public IRubyObject index(final ThreadContext context, final IRubyObject obj, final Block unused) {
        if (unused.isGiven()) {
            context.getRuntime().getWarnings().warn(IRubyWarnings.ID.BLOCK_UNUSED, "given block not used", new Object[0]);
        }
        return this.index(context, obj);
    }
    
    @JRubyMethod(name = { "index", "find_index" })
    public IRubyObject index(final ThreadContext context, final Block block) {
        final Ruby runtime = context.getRuntime();
        if (!block.isGiven()) {
            return RubyEnumerator.enumeratorize(runtime, this, "index");
        }
        for (int i = 0; i < this.realLength; ++i) {
            if (block.yield(context, this.eltOk(i)).isTrue()) {
                return runtime.newFixnum(i);
            }
        }
        return runtime.getNil();
    }
    
    public IRubyObject rindex(final ThreadContext context, final IRubyObject obj) {
        final Ruby runtime = context.getRuntime();
        int i = this.realLength;
        while (i-- > 0) {
            if (i > this.realLength) {
                i = this.realLength;
            }
            else {
                if (RubyObject.equalInternal(context, this.eltOk(i), obj)) {
                    return runtime.newFixnum(i);
                }
                continue;
            }
        }
        return runtime.getNil();
    }
    
    @JRubyMethod
    public IRubyObject rindex(final ThreadContext context, final IRubyObject obj, final Block unused) {
        if (unused.isGiven()) {
            context.getRuntime().getWarnings().warn(IRubyWarnings.ID.BLOCK_UNUSED, "given block not used", new Object[0]);
        }
        return this.rindex(context, obj);
    }
    
    @JRubyMethod
    public IRubyObject rindex(final ThreadContext context, final Block block) {
        final Ruby runtime = context.getRuntime();
        if (!block.isGiven()) {
            return RubyEnumerator.enumeratorize(runtime, this, "rindex");
        }
        int i = this.realLength;
        while (i-- > 0) {
            if (i >= this.realLength) {
                i = this.realLength;
            }
            else {
                if (block.yield(context, this.eltOk(i)).isTrue()) {
                    return runtime.newFixnum(i);
                }
                continue;
            }
        }
        return runtime.getNil();
    }
    
    @JRubyMethod(name = { "indexes", "indices" }, required = 1, rest = true)
    public IRubyObject indexes(final IRubyObject[] args) {
        this.getRuntime().getWarnings().warn(IRubyWarnings.ID.DEPRECATED_METHOD, "Array#indexes is deprecated; use Array#values_at", "Array#indexes", "Array#values_at");
        final RubyArray ary = new RubyArray(this.getRuntime(), args.length);
        for (int i = 0; i < args.length; ++i) {
            ary.append(this.aref(args[i]));
        }
        return ary;
    }
    
    @JRubyMethod(name = { "reverse!" })
    public IRubyObject reverse_bang() {
        this.modify();
        try {
            if (this.realLength > 1) {
                final IRubyObject[] vals = this.values;
                final int p = this.begin;
                for (int len = this.realLength, i = 0; i < len >> 1; ++i) {
                    final IRubyObject tmp = vals[p + i];
                    vals[p + i] = vals[p + len - i - 1];
                    vals[p + len - i - 1] = tmp;
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            this.concurrentModification();
        }
        return this;
    }
    
    @JRubyMethod(name = { "reverse" })
    public IRubyObject reverse() {
        if (this.realLength > 1) {
            final RubyArray safeReverse;
            final RubyArray dup = safeReverse = this.safeReverse();
            safeReverse.flags |= (this.flags & 0x8);
            final RubyArray rubyArray = dup;
            rubyArray.flags |= (this.flags & 0x10);
            return dup;
        }
        return this.dup();
    }
    
    private RubyArray safeReverse() {
        final int length = this.realLength;
        final int myBegin = this.begin;
        final IRubyObject[] myValues = this.values;
        final IRubyObject[] vals = new IRubyObject[length];
        try {
            for (int i = 0; i <= length >> 1; ++i) {
                vals[i] = myValues[myBegin + length - i - 1];
                vals[length - i - 1] = myValues[myBegin + i];
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            this.concurrentModification();
        }
        return new RubyArray(this.getRuntime(), this.getMetaClass(), vals);
    }
    
    @JRubyMethod(name = { "collect", "map" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject collect(final ThreadContext context, final Block block) {
        final Ruby runtime = context.getRuntime();
        if (!block.isGiven()) {
            return new RubyArray(runtime, runtime.getArray(), this);
        }
        final IRubyObject[] arr = new IRubyObject[this.realLength];
        for (int i = 0; i < this.realLength; ++i) {
            arr[i] = block.yield(context, this.safeArrayRef(this.values, i + this.begin));
        }
        return new RubyArray(runtime, arr);
    }
    
    @JRubyMethod(name = { "collect", "map" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject collect19(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.collect(context, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "collect");
    }
    
    public RubyArray collectBang(final ThreadContext context, final Block block) {
        if (!block.isGiven()) {
            throw context.getRuntime().newLocalJumpErrorNoBlock();
        }
        this.modify();
        for (int i = 0, len = this.realLength; i < len; ++i) {
            this.store(i, block.yield(context, this.safeArrayRef(this.values, this.begin + i)));
        }
        return this;
    }
    
    @JRubyMethod(name = { "collect!" })
    public IRubyObject collect_bang(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.collectBang(context, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "collect!");
    }
    
    @JRubyMethod(name = { "map!" })
    public IRubyObject map_bang(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.collectBang(context, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "map!");
    }
    
    public IRubyObject selectCommon(final ThreadContext context, final Block block) {
        final Ruby runtime = context.getRuntime();
        final RubyArray result = new RubyArray(runtime, this.realLength);
        for (int i = 0; i < this.realLength; ++i) {
            final IRubyObject value = this.safeArrayRef(this.values, this.begin + i);
            if (block.yield(context, value).isTrue()) {
                result.append(value);
            }
        }
        RuntimeHelpers.fillNil(result.values, result.realLength, result.values.length, runtime);
        return result;
    }
    
    @JRubyMethod
    public IRubyObject select(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.selectCommon(context, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "select");
    }
    
    @JRubyMethod(name = { "select!" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject select_bang(final ThreadContext context, final Block block) {
        final Ruby runtime = context.getRuntime();
        if (!block.isGiven()) {
            return RubyEnumerator.enumeratorize(runtime, this, "select!");
        }
        int newLength = 0;
        final IRubyObject[] aux = new IRubyObject[this.values.length];
        for (int oldIndex = 0; oldIndex < this.realLength; ++oldIndex) {
            final IRubyObject value = this.safeArrayRef(this.values, this.begin + oldIndex);
            if (block.yield(context, value).isTrue()) {
                aux[this.begin + newLength++] = value;
            }
        }
        if (this.realLength == newLength) {
            return runtime.getNil();
        }
        this.safeArrayCopy(aux, this.begin, this.values, this.begin, newLength);
        this.realLength = newLength;
        return this;
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_9)
    public IRubyObject keep_if(final ThreadContext context, final Block block) {
        if (!block.isGiven()) {
            return RubyEnumerator.enumeratorize(context.getRuntime(), this, "keep_if");
        }
        this.select_bang(context, block);
        return this;
    }
    
    @JRubyMethod(required = 1)
    public IRubyObject delete(final ThreadContext context, final IRubyObject item, final Block block) {
        int i2 = 0;
        final Ruby runtime = context.getRuntime();
        for (int i3 = 0; i3 < this.realLength; ++i3) {
            final IRubyObject e = this.safeArrayRef(this.values, this.begin + i3);
            if (!RubyObject.equalInternal(context, e, item)) {
                if (i3 != i2) {
                    this.store(i2, e);
                }
                ++i2;
            }
        }
        if (this.realLength != i2) {
            this.modify();
            final int myRealLength = this.realLength;
            final int myBegin = this.begin;
            final IRubyObject[] myValues = this.values;
            try {
                if (myRealLength > i2) {
                    RuntimeHelpers.fillNil(myValues, myBegin + i2, myBegin + myRealLength, context.getRuntime());
                    this.realLength = i2;
                    final int valuesLength = myValues.length - myBegin;
                    if (i2 << 1 < valuesLength && valuesLength > 16) {
                        this.realloc(i2 << 1, valuesLength);
                    }
                }
            }
            catch (ArrayIndexOutOfBoundsException e2) {
                this.concurrentModification();
            }
            return item;
        }
        if (block.isGiven()) {
            return block.yield(context, item);
        }
        return runtime.getNil();
    }
    
    private final IRubyObject delete_at(int pos) {
        final int len = this.realLength;
        if (pos >= len || (pos < 0 && (pos += len) < 0)) {
            return this.getRuntime().getNil();
        }
        this.modify();
        final IRubyObject nil = this.getRuntime().getNil();
        final IRubyObject obj = this.values[this.begin + pos];
        try {
            if (pos == 0) {
                this.values[this.begin] = nil;
                ++this.begin;
                --this.realLength;
                return obj;
            }
            if (pos == this.realLength - 1) {
                this.values[this.begin + this.realLength - 1] = nil;
                --this.realLength;
                return obj;
            }
            System.arraycopy(this.values, this.begin + pos + 1, this.values, this.begin + pos, len - (pos + 1));
            this.values[this.begin + len - 1] = this.getRuntime().getNil();
        }
        catch (ArrayIndexOutOfBoundsException e) {
            this.concurrentModification();
        }
        --this.realLength;
        return obj;
    }
    
    @JRubyMethod(name = { "delete_at" }, required = 1)
    public IRubyObject delete_at(final IRubyObject obj) {
        return this.delete_at((int)RubyNumeric.num2long(obj));
    }
    
    public IRubyObject rejectCommon(final ThreadContext context, final Block block) {
        final RubyArray ary = this.aryDup();
        ary.reject_bang(context, block);
        return ary;
    }
    
    @JRubyMethod
    public IRubyObject reject(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.rejectCommon(context, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "reject");
    }
    
    public IRubyObject rejectBang(final ThreadContext context, final Block block) {
        if (!block.isGiven()) {
            throw context.getRuntime().newLocalJumpErrorNoBlock();
        }
        int i2 = 0;
        this.modify();
        for (int i3 = 0; i3 < this.realLength; ++i3) {
            final IRubyObject v = this.safeArrayRef(this.values, this.begin + i3);
            if (!block.yield(context, v).isTrue()) {
                if (i3 != i2) {
                    this.store(i2, v);
                }
                ++i2;
            }
        }
        if (this.realLength == i2) {
            return context.getRuntime().getNil();
        }
        if (i2 < this.realLength) {
            try {
                RuntimeHelpers.fillNil(this.values, this.begin + i2, this.begin + this.realLength, context.getRuntime());
            }
            catch (ArrayIndexOutOfBoundsException e) {
                this.concurrentModification();
            }
            this.realLength = i2;
        }
        return this;
    }
    
    @JRubyMethod(name = { "reject!" })
    public IRubyObject reject_bang(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.rejectBang(context, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "reject!");
    }
    
    public IRubyObject deleteIf(final ThreadContext context, final Block block) {
        this.reject_bang(context, block);
        return this;
    }
    
    @JRubyMethod
    public IRubyObject delete_if(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.deleteIf(context, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "delete_if");
    }
    
    @JRubyMethod(optional = 1, rest = true)
    public IRubyObject zip(final ThreadContext context, IRubyObject[] args, final Block block) {
        final Ruby runtime = context.getRuntime();
        args = RubyEnumerable.zipCommonConvert(runtime, args, "to_ary");
        if (block.isGiven()) {
            for (int i = 0; i < this.realLength; ++i) {
                final IRubyObject[] tmp = new IRubyObject[args.length + 1];
                tmp[0] = this.safeArrayRef(this.values, this.begin + i);
                for (int j = 0; j < args.length; ++j) {
                    tmp[j + 1] = ((RubyArray)args[j]).elt(i);
                }
                block.yield(context, newArrayNoCopyLight(runtime, tmp));
            }
            return runtime.getNil();
        }
        final IRubyObject[] result = new IRubyObject[this.realLength];
        try {
            for (int k = 0; k < this.realLength; ++k) {
                final IRubyObject[] tmp2 = new IRubyObject[args.length + 1];
                tmp2[0] = this.values[this.begin + k];
                for (int l = 0; l < args.length; ++l) {
                    tmp2[l + 1] = ((RubyArray)args[l]).elt(k);
                }
                result[k] = newArrayNoCopyLight(runtime, tmp2);
            }
        }
        catch (ArrayIndexOutOfBoundsException aioob) {
            this.concurrentModification();
        }
        return newArrayNoCopy(runtime, result);
    }
    
    @JRubyMethod(name = { "<=>" }, required = 1)
    public IRubyObject op_cmp(final ThreadContext context, final IRubyObject obj) {
        final Ruby runtime = context.getRuntime();
        IRubyObject ary2 = runtime.getNil();
        final boolean isAnArray = obj instanceof RubyArray || obj.getMetaClass().getSuperClass() == runtime.getArray();
        if (!isAnArray && !obj.respondsTo("to_ary")) {
            return ary2;
        }
        if (!isAnArray) {
            ary2 = obj.callMethod(context, "to_ary");
        }
        else {
            ary2 = obj.convertToArray();
        }
        return this.cmpCommon(context, runtime, (RubyArray)ary2);
    }
    
    private IRubyObject cmpCommon(final ThreadContext context, final Ruby runtime, final RubyArray ary2) {
        if (this == ary2 || runtime.isInspecting(this)) {
            return RubyFixnum.zero(runtime);
        }
        try {
            runtime.registerInspecting(this);
            int len = this.realLength;
            if (len > ary2.realLength) {
                len = ary2.realLength;
            }
            for (int i = 0; i < len; ++i) {
                final IRubyObject v = RuntimeHelpers.invokedynamic(context, this.elt(i), 4, ary2.elt(i));
                if (!(v instanceof RubyFixnum) || ((RubyFixnum)v).getLongValue() != 0L) {
                    return v;
                }
            }
        }
        finally {
            runtime.unregisterInspecting(this);
        }
        int len = this.realLength - ary2.realLength;
        if (len == 0) {
            return RubyFixnum.zero(runtime);
        }
        if (len > 0) {
            return RubyFixnum.one(runtime);
        }
        return RubyFixnum.minus_one(runtime);
    }
    
    public IRubyObject slice_bang(final IRubyObject[] args) {
        switch (args.length) {
            case 1: {
                return this.slice_bang(args[0]);
            }
            case 2: {
                return this.slice_bang(args[0], args[1]);
            }
            default: {
                Arity.raiseArgumentError(this.getRuntime(), args.length, 1, 2);
                return null;
            }
        }
    }
    
    private IRubyObject slice_internal(long pos, long len, final IRubyObject arg0, IRubyObject arg1, final Ruby runtime) {
        if (len < 0L) {
            return runtime.getNil();
        }
        final int orig_len = this.realLength;
        if (pos < 0L) {
            pos += orig_len;
            if (pos < 0L) {
                return runtime.getNil();
            }
        }
        else if (orig_len < pos) {
            return runtime.getNil();
        }
        if (orig_len < pos + len) {
            len = orig_len - pos;
        }
        if (len == 0L) {
            return runtime.newEmptyArray();
        }
        arg1 = this.makeShared(this.begin + (int)pos, (int)len, this.getMetaClass());
        this.splice(pos, len, null, false);
        return arg1;
    }
    
    @JRubyMethod(name = { "slice!" })
    public IRubyObject slice_bang(final IRubyObject arg0) {
        this.modifyCheck();
        final Ruby runtime = this.getRuntime();
        if (!(arg0 instanceof RubyRange)) {
            return this.delete_at((int)RubyNumeric.num2long(arg0));
        }
        final RubyRange range = (RubyRange)arg0;
        if (!range.checkBegin(this.realLength)) {
            return runtime.getNil();
        }
        final long[] beglen = range.begLen(this.realLength, 1);
        final long pos = beglen[0];
        final long len = beglen[1];
        return this.slice_internal(pos, len, arg0, null, runtime);
    }
    
    @JRubyMethod(name = { "slice!" })
    public IRubyObject slice_bang(final IRubyObject arg0, final IRubyObject arg1) {
        this.modifyCheck();
        final long pos = RubyNumeric.num2long(arg0);
        final long len = RubyNumeric.num2long(arg1);
        return this.slice_internal(pos, len, arg0, arg1, this.getRuntime());
    }
    
    @JRubyMethod(name = { "assoc" }, required = 1)
    public IRubyObject assoc(final ThreadContext context, final IRubyObject key) {
        final Ruby runtime = context.getRuntime();
        for (int i = 0; i < this.realLength; ++i) {
            final IRubyObject v = this.eltOk(i);
            if (v instanceof RubyArray) {
                final RubyArray arr = (RubyArray)v;
                if (arr.realLength > 0 && RubyObject.equalInternal(context, arr.elt(0L), key)) {
                    return arr;
                }
            }
        }
        return runtime.getNil();
    }
    
    @JRubyMethod(name = { "rassoc" }, required = 1)
    public IRubyObject rassoc(final ThreadContext context, final IRubyObject value) {
        final Ruby runtime = context.getRuntime();
        for (int i = 0; i < this.realLength; ++i) {
            final IRubyObject v = this.eltOk(i);
            if (v instanceof RubyArray) {
                final RubyArray arr = (RubyArray)v;
                if (arr.realLength > 1 && RubyObject.equalInternal(context, arr.eltOk(1L), value)) {
                    return arr;
                }
            }
        }
        return runtime.getNil();
    }
    
    private boolean flatten(final ThreadContext context, final int level, final RubyArray result) {
        final Ruby runtime = context.getRuntime();
        final RubyArray stack = new RubyArray(runtime, 16, false);
        final IdentityHashMap<Object, Object> memo = new IdentityHashMap<Object, Object>();
        RubyArray ary = this;
        memo.put(ary, RubyArray.NEVER);
        boolean modified = false;
        int i = 0;
        try {
            while (true) {
                if (i < ary.realLength) {
                    final IRubyObject elt = ary.values[ary.begin + i++];
                    final IRubyObject tmp = elt.checkArrayType();
                    if (tmp.isNil() || (level >= 0 && stack.realLength / 2 >= level)) {
                        result.append(elt);
                    }
                    else {
                        modified = true;
                        if (memo.get(tmp) != null) {
                            throw runtime.newArgumentError("tried to flatten recursive array");
                        }
                        memo.put(tmp, RubyArray.NEVER);
                        stack.append(ary);
                        stack.append(RubyFixnum.newFixnum(runtime, i));
                        ary = (RubyArray)tmp;
                        i = 0;
                    }
                }
                else {
                    if (stack.realLength == 0) {
                        break;
                    }
                    memo.remove(ary);
                    final IRubyObject tmp = stack.pop(context);
                    i = (int)((RubyFixnum)tmp).getLongValue();
                    ary = (RubyArray)stack.pop(context);
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException aioob) {
            this.concurrentModification();
        }
        return modified;
    }
    
    @JRubyMethod(name = { "flatten!" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject flatten_bang(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        final RubyArray result = new RubyArray(runtime, this.getMetaClass(), this.realLength);
        if (this.flatten(context, -1, result)) {
            this.modifyCheck();
            this.isShared = false;
            this.begin = 0;
            this.realLength = result.realLength;
            this.values = result.values;
            return this;
        }
        return runtime.getNil();
    }
    
    @JRubyMethod(name = { "flatten!" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject flatten_bang19(final ThreadContext context) {
        this.modifyCheck();
        return this.flatten_bang(context);
    }
    
    @JRubyMethod(name = { "flatten!" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject flatten_bang(final ThreadContext context, final IRubyObject arg) {
        final Ruby runtime = context.getRuntime();
        final int level = RubyNumeric.num2int(arg);
        if (level == 0) {
            return runtime.getNil();
        }
        final RubyArray result = new RubyArray(runtime, this.getMetaClass(), this.realLength);
        if (this.flatten(context, level, result)) {
            this.isShared = false;
            this.begin = 0;
            this.realLength = result.realLength;
            this.values = result.values;
            return this;
        }
        return runtime.getNil();
    }
    
    @JRubyMethod(name = { "flatten!" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject flatten_bang19(final ThreadContext context, final IRubyObject arg) {
        this.modifyCheck();
        return this.flatten_bang(context, arg);
    }
    
    @JRubyMethod(name = { "flatten" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject flatten(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        final RubyArray result = new RubyArray(runtime, this.getMetaClass(), this.realLength);
        this.flatten(context, -1, result);
        result.infectBy(this);
        return result;
    }
    
    @JRubyMethod(name = { "flatten" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject flatten(final ThreadContext context, final IRubyObject arg) {
        final Ruby runtime = context.getRuntime();
        final int level = RubyNumeric.num2int(arg);
        if (level == 0) {
            return this;
        }
        final RubyArray result = new RubyArray(runtime, this.getMetaClass(), this.realLength);
        this.flatten(context, level, result);
        result.infectBy(this);
        return result;
    }
    
    @JRubyMethod(name = { "flatten" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject flatten19(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        final RubyArray result = new RubyArray(runtime, this.getMetaClass(), this.realLength);
        this.flatten(context, -1, result);
        result.infectBy(this);
        return result;
    }
    
    @JRubyMethod(name = { "flatten" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject flatten19(final ThreadContext context, final IRubyObject arg) {
        final Ruby runtime = context.getRuntime();
        final int level = RubyNumeric.num2int(arg);
        if (level == 0) {
            return this.makeShared();
        }
        final RubyArray result = new RubyArray(runtime, this.getMetaClass(), this.realLength);
        this.flatten(context, level, result);
        result.infectBy(this);
        return result;
    }
    
    @JRubyMethod(name = { "count" })
    public IRubyObject count(final ThreadContext context, final Block block) {
        if (block.isGiven()) {
            int n = 0;
            for (int i = 0; i < this.realLength; ++i) {
                if (block.yield(context, this.elt(i)).isTrue()) {
                    ++n;
                }
            }
            return RubyFixnum.newFixnum(context.getRuntime(), n);
        }
        return RubyFixnum.newFixnum(context.getRuntime(), this.realLength);
    }
    
    @JRubyMethod(name = { "count" })
    public IRubyObject count(final ThreadContext context, final IRubyObject obj, final Block block) {
        if (block.isGiven()) {
            context.getRuntime().getWarnings().warn(IRubyWarnings.ID.BLOCK_UNUSED, "given block not used", new Object[0]);
        }
        int n = 0;
        for (int i = 0; i < this.realLength; ++i) {
            if (RubyObject.equalInternal(context, this.elt(i), obj)) {
                ++n;
            }
        }
        return RubyFixnum.newFixnum(context.getRuntime(), n);
    }
    
    @JRubyMethod(name = { "nitems" })
    public IRubyObject nitems() {
        int n = 0;
        for (int i = 0; i < this.realLength; ++i) {
            if (!this.eltOk(i).isNil()) {
                ++n;
            }
        }
        return this.getRuntime().newFixnum(n);
    }
    
    @JRubyMethod(name = { "+" }, required = 1)
    public IRubyObject op_plus(final IRubyObject obj) {
        final RubyArray y = obj.convertToArray();
        final int len = this.realLength + y.realLength;
        final RubyArray z = new RubyArray(this.getRuntime(), len);
        try {
            System.arraycopy(this.values, this.begin, z.values, 0, this.realLength);
            System.arraycopy(y.values, y.begin, z.values, this.realLength, y.realLength);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            this.concurrentModification();
        }
        z.realLength = len;
        return z;
    }
    
    @JRubyMethod(name = { "*" }, required = 1, compat = CompatVersion.RUBY1_8)
    public IRubyObject op_times(final ThreadContext context, final IRubyObject times) {
        final IRubyObject tmp = times.checkStringType();
        if (!tmp.isNil()) {
            return this.join(context, tmp);
        }
        long len = RubyNumeric.num2long(times);
        final Ruby runtime = context.getRuntime();
        if (len == 0L) {
            return new RubyArray(runtime, this.getMetaClass(), IRubyObject.NULL_ARRAY).infectBy(this);
        }
        if (len < 0L) {
            throw runtime.newArgumentError("negative argument");
        }
        if (Long.MAX_VALUE / len < this.realLength) {
            throw runtime.newArgumentError("argument too big");
        }
        len *= this.realLength;
        checkLength(runtime, len);
        final RubyArray ary2 = new RubyArray(runtime, this.getMetaClass(), (int)len);
        ary2.realLength = ary2.values.length;
        try {
            for (int i = 0; i < len; i += this.realLength) {
                System.arraycopy(this.values, this.begin, ary2.values, i, this.realLength);
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            this.concurrentModification();
        }
        ary2.infectBy(this);
        return ary2;
    }
    
    @JRubyMethod(name = { "*" }, required = 1, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_times19(final ThreadContext context, final IRubyObject times) {
        final IRubyObject tmp = times.checkStringType();
        if (!tmp.isNil()) {
            return this.join19(context, tmp);
        }
        long len = RubyNumeric.num2long(times);
        final Ruby runtime = context.getRuntime();
        if (len == 0L) {
            return new RubyArray(runtime, this.getMetaClass(), IRubyObject.NULL_ARRAY).infectBy(this);
        }
        if (len < 0L) {
            throw runtime.newArgumentError("negative argument");
        }
        if (Long.MAX_VALUE / len < this.realLength) {
            throw runtime.newArgumentError("argument too big");
        }
        len *= this.realLength;
        checkLength(runtime, len);
        final RubyArray ary2 = new RubyArray(runtime, this.getMetaClass(), (int)len);
        ary2.realLength = ary2.values.length;
        try {
            for (int i = 0; i < len; i += this.realLength) {
                System.arraycopy(this.values, this.begin, ary2.values, i, this.realLength);
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            this.concurrentModification();
        }
        ary2.infectBy(this);
        return ary2;
    }
    
    private RubyHash makeHash() {
        return this.makeHash(new RubyHash(this.getRuntime(), false));
    }
    
    private RubyHash makeHash(final RubyHash hash) {
        int i;
        for (int myBegin = i = this.begin; i < myBegin + this.realLength; ++i) {
            hash.fastASet(this.values[i], RubyArray.NEVER);
        }
        return hash;
    }
    
    private RubyHash makeHash(final RubyArray ary2) {
        return ary2.makeHash(this.makeHash());
    }
    
    private RubyHash makeHash(final ThreadContext context, final Block block) {
        return this.makeHash(context, new RubyHash(this.getRuntime(), false), block);
    }
    
    private RubyHash makeHash(final ThreadContext context, final RubyHash hash, final Block block) {
        int i;
        for (int myBegin = i = this.begin; i < myBegin + this.realLength; ++i) {
            final IRubyObject v = this.elt(i);
            final IRubyObject k = block.yield(context, v);
            if (hash.fastARef(k) == null) {
                hash.fastASet(k, v);
            }
        }
        return hash;
    }
    
    @JRubyMethod(name = { "uniq!" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject uniq_bang(final ThreadContext context) {
        final RubyHash hash = this.makeHash();
        if (this.realLength == hash.size()) {
            return context.getRuntime().getNil();
        }
        int j = 0;
        for (int i = 0; i < this.realLength; ++i) {
            final IRubyObject v = this.elt(i);
            if (hash.fastDelete(v)) {
                this.store(j++, v);
            }
        }
        this.realLength = j;
        return this;
    }
    
    @JRubyMethod(name = { "uniq!" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject uniq_bang19(final ThreadContext context, final Block block) {
        this.modifyCheck();
        if (!block.isGiven()) {
            return this.uniq_bang(context);
        }
        final RubyHash hash = this.makeHash(context, block);
        if (this.realLength == hash.size()) {
            return context.getRuntime().getNil();
        }
        this.realLength = 0;
        hash.visitAll(new RubyHash.Visitor() {
            public void visit(final IRubyObject key, final IRubyObject value) {
                RubyArray.this.append(value);
            }
        });
        return this;
    }
    
    @JRubyMethod(name = { "uniq" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject uniq(final ThreadContext context) {
        final RubyHash hash = this.makeHash();
        if (this.realLength == hash.size()) {
            return this.makeShared();
        }
        final RubyArray result = new RubyArray(context.getRuntime(), this.getMetaClass(), hash.size());
        int j = 0;
        try {
            for (int i = 0; i < this.realLength; ++i) {
                final IRubyObject v = this.elt(i);
                if (hash.fastDelete(v)) {
                    result.values[j++] = v;
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException aioob) {
            this.concurrentModification();
        }
        result.realLength = j;
        return result;
    }
    
    @JRubyMethod(name = { "uniq" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject uniq19(final ThreadContext context, final Block block) {
        if (!block.isGiven()) {
            return this.uniq(context);
        }
        final RubyHash hash = this.makeHash(context, block);
        final RubyArray result = new RubyArray(context.getRuntime(), this.getMetaClass(), hash.size());
        hash.visitAll(new RubyHash.Visitor() {
            public void visit(final IRubyObject key, final IRubyObject value) {
                result.append(value);
            }
        });
        return result;
    }
    
    @JRubyMethod(name = { "-" }, required = 1)
    public IRubyObject op_diff(final IRubyObject other) {
        final RubyHash hash = other.convertToArray().makeHash();
        final RubyArray ary3 = new RubyArray(this.getRuntime(), 16);
        final int myBegin = this.begin;
        try {
            for (int i = myBegin; i < myBegin + this.realLength; ++i) {
                if (hash.fastARef(this.values[i]) == null) {
                    ary3.append(this.elt(i - myBegin));
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException aioob) {
            this.concurrentModification();
        }
        RuntimeHelpers.fillNil(ary3.values, ary3.realLength, ary3.values.length, this.getRuntime());
        return ary3;
    }
    
    @JRubyMethod(name = { "&" }, required = 1)
    public IRubyObject op_and(final IRubyObject other) {
        final RubyArray ary2 = other.convertToArray();
        final RubyHash hash = ary2.makeHash();
        final RubyArray ary3 = new RubyArray(this.getRuntime(), (this.realLength < ary2.realLength) ? this.realLength : ary2.realLength);
        for (int i = 0; i < this.realLength; ++i) {
            final IRubyObject v = this.elt(i);
            if (hash.fastDelete(v)) {
                ary3.append(v);
            }
        }
        RuntimeHelpers.fillNil(ary3.values, ary3.realLength, ary3.values.length, this.getRuntime());
        return ary3;
    }
    
    @JRubyMethod(name = { "|" }, required = 1)
    public IRubyObject op_or(final IRubyObject other) {
        final RubyArray ary2 = other.convertToArray();
        final RubyHash set = this.makeHash(ary2);
        final RubyArray ary3 = new RubyArray(this.getRuntime(), this.realLength + ary2.realLength);
        for (int i = 0; i < this.realLength; ++i) {
            final IRubyObject v = this.elt(i);
            if (set.fastDelete(v)) {
                ary3.append(v);
            }
        }
        for (int i = 0; i < ary2.realLength; ++i) {
            final IRubyObject v = ary2.elt(i);
            if (set.fastDelete(v)) {
                ary3.append(v);
            }
        }
        RuntimeHelpers.fillNil(ary3.values, ary3.realLength, ary3.values.length, this.getRuntime());
        return ary3;
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_8)
    public RubyArray sort(final ThreadContext context, final Block block) {
        final RubyArray ary = this.aryDup();
        ary.sort_bang(context, block);
        return ary;
    }
    
    @JRubyMethod(name = { "sort" }, compat = CompatVersion.RUBY1_9)
    public RubyArray sort19(final ThreadContext context, final Block block) {
        final RubyArray ary = this.aryDup();
        ary.sort_bang19(context, block);
        return ary;
    }
    
    @JRubyMethod(name = { "sort!" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject sort_bang(final ThreadContext context, final Block block) {
        this.modify();
        if (this.realLength > 1) {
            this.flags |= 0x200;
            try {
                return block.isGiven() ? this.sortInternal(context, block) : this.sortInternal(context, false);
            }
            finally {
                this.flags &= 0xFFFFFDFF;
            }
        }
        return this;
    }
    
    @JRubyMethod(name = { "sort!" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject sort_bang19(final ThreadContext context, final Block block) {
        this.modify();
        if (this.realLength > 1) {
            return block.isGiven() ? this.sortInternal(context, block) : this.sortInternal(context, true);
        }
        return this;
    }
    
    private IRubyObject sortInternal(final ThreadContext context, final boolean honorOverride) {
        final boolean fixnumBypass = !honorOverride || context.getRuntime().newFixnum(0).isBuiltin("<=>");
        final boolean stringBypass = !honorOverride || context.getRuntime().newString("").isBuiltin("<=>");
        try {
            Qsort.sort(this.values, this.begin, this.begin + this.realLength, new Comparator() {
                public int compare(final Object o1, final Object o2) {
                    if (fixnumBypass && o1 instanceof RubyFixnum && o2 instanceof RubyFixnum) {
                        return compareFixnums((RubyFixnum)o1, (RubyFixnum)o2);
                    }
                    if (stringBypass && o1 instanceof RubyString && o2 instanceof RubyString) {
                        return ((RubyString)o1).op_cmp((RubyString)o2);
                    }
                    return compareOthers(context, (IRubyObject)o1, (IRubyObject)o2);
                }
            });
        }
        catch (ArrayIndexOutOfBoundsException aioob) {
            this.concurrentModification();
        }
        return this;
    }
    
    private static int compareFixnums(final RubyFixnum o1, final RubyFixnum o2) {
        final long a = o1.getLongValue();
        final long b = o2.getLongValue();
        return (a > b) ? 1 : ((a == b) ? 0 : -1);
    }
    
    private static int compareOthers(final ThreadContext context, final IRubyObject o1, final IRubyObject o2) {
        final IRubyObject ret = RuntimeHelpers.invokedynamic(context, o1, 4, o2);
        final int n = RubyComparable.cmpint(context, ret, o1, o2);
        return n;
    }
    
    private IRubyObject sortInternal(final ThreadContext context, final Block block) {
        final IRubyObject[] newValues = new IRubyObject[this.realLength];
        final int length = this.realLength;
        this.safeArrayCopy(this.values, this.begin, newValues, 0, length);
        Qsort.sort(newValues, 0, length, new Comparator() {
            public int compare(final Object o1, final Object o2) {
                final IRubyObject obj1 = (IRubyObject)o1;
                final IRubyObject obj2 = (IRubyObject)o2;
                final IRubyObject ret = block.yieldArray(context, RubyArray.this.getRuntime().newArray(obj1, obj2), null, null);
                return RubyComparable.cmpint(context, ret, obj1, obj2);
            }
        });
        this.values = newValues;
        this.begin = 0;
        this.realLength = length;
        return this;
    }
    
    @JRubyMethod(name = { "sort_by!" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject sort_by_bang(final ThreadContext context, final Block block) {
        if (!block.isGiven()) {
            return RubyEnumerator.enumeratorize(context.getRuntime(), this, "sort_by!");
        }
        this.modifyCheck();
        final RubyArray sorted = RuntimeHelpers.invoke(context, this, "sort_by", block).convertToArray();
        this.values = sorted.values;
        this.isShared = false;
        this.begin = 0;
        return this;
    }
    
    @JRubyMethod(name = { "take" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject take(final ThreadContext context, final IRubyObject n) {
        final Ruby runtime = context.getRuntime();
        final long len = RubyNumeric.num2long(n);
        if (len < 0L) {
            throw runtime.newArgumentError("attempt to take negative size");
        }
        return this.subseq(0L, len);
    }
    
    @JRubyMethod(name = { "take_while" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject take_while(final ThreadContext context, final Block block) {
        final Ruby runtime = context.getRuntime();
        if (!block.isGiven()) {
            return RubyEnumerator.enumeratorize(runtime, this, "take_while");
        }
        int i;
        for (i = 0; i < this.realLength && block.yield(context, this.eltOk(i)).isTrue(); ++i) {}
        return this.subseq(0L, i);
    }
    
    @JRubyMethod(name = { "drop" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject drop(final ThreadContext context, final IRubyObject n) {
        final Ruby runtime = context.getRuntime();
        final long pos = RubyNumeric.num2long(n);
        if (pos < 0L) {
            throw runtime.newArgumentError("attempt to drop negative size");
        }
        final IRubyObject result = this.subseq(pos, this.realLength);
        return result.isNil() ? runtime.newEmptyArray() : result;
    }
    
    @JRubyMethod(name = { "drop_while" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject drop_while(final ThreadContext context, final Block block) {
        final Ruby runtime = context.getRuntime();
        if (!block.isGiven()) {
            return RubyEnumerator.enumeratorize(runtime, this, "drop_while");
        }
        int i;
        for (i = 0; i < this.realLength && block.yield(context, this.eltOk(i)).isTrue(); ++i) {}
        final IRubyObject result = this.subseq(i, this.realLength);
        return result.isNil() ? runtime.newEmptyArray() : result;
    }
    
    @JRubyMethod(name = { "cycle" })
    public IRubyObject cycle(final ThreadContext context, final Block block) {
        if (!block.isGiven()) {
            return RubyEnumerator.enumeratorize(context.getRuntime(), this, "cycle");
        }
        return this.cycleCommon(context, -1L, block);
    }
    
    @JRubyMethod(name = { "cycle" })
    public IRubyObject cycle(final ThreadContext context, final IRubyObject arg, final Block block) {
        if (arg.isNil()) {
            return this.cycle(context, block);
        }
        if (!block.isGiven()) {
            return RubyEnumerator.enumeratorize(context.getRuntime(), this, "cycle", arg);
        }
        final long times = RubyNumeric.num2long(arg);
        if (times <= 0L) {
            return context.getRuntime().getNil();
        }
        return this.cycleCommon(context, times, block);
    }
    
    private IRubyObject cycleCommon(final ThreadContext context, long n, final Block block) {
        while (this.realLength > 0 && (n < 0L || 0L < n--)) {
            for (int i = 0; i < this.realLength; ++i) {
                block.yield(context, this.eltOk(i));
            }
        }
        return context.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "product" }, rest = true, compat = CompatVersion.RUBY1_8)
    public IRubyObject product(final ThreadContext context, final IRubyObject[] args) {
        return this.product19(context, args, Block.NULL_BLOCK);
    }
    
    @JRubyMethod(name = { "product" }, rest = true, compat = CompatVersion.RUBY1_9)
    public IRubyObject product19(final ThreadContext context, final IRubyObject[] args, final Block block) {
        final Ruby runtime = context.getRuntime();
        final boolean useBlock = block.isGiven();
        final int n = args.length + 1;
        final RubyArray[] arrays = new RubyArray[n];
        final int[] counters = new int[n];
        arrays[0] = this;
        for (int i = 1; i < n; ++i) {
            arrays[i] = args[i - 1].convertToArray();
        }
        int resultLen = 1;
        for (int j = 0; j < n; ++j) {
            final int k = arrays[j].realLength;
            final int l = resultLen;
            if (k == 0) {
                return useBlock ? this : newEmptyArray(runtime);
            }
            resultLen *= k;
            if ((resultLen < k || resultLen < l || resultLen / k != l) && !block.isGiven()) {
                throw runtime.newRangeError("too big to product");
            }
        }
        final RubyArray result = useBlock ? null : newArray(runtime, resultLen);
        for (int m = 0; m < resultLen; ++m) {
            final RubyArray sub = newArray(runtime, n);
            for (int j2 = 0; j2 < n; ++j2) {
                sub.append(arrays[j2].entry(counters[j2]));
            }
            if (useBlock) {
                block.yieldSpecific(context, sub);
            }
            else {
                result.append(sub);
            }
            int m2 = n - 1;
            final int[] array = counters;
            final int n2 = m2;
            ++array[n2];
            while (m2 > 0 && counters[m2] == arrays[m2].realLength) {
                counters[m2] = 0;
                --m2;
                final int[] array2 = counters;
                final int n3 = m2;
                ++array2[n3];
            }
        }
        return useBlock ? this : result;
    }
    
    private static int combinationLength(int myLength, int n) {
        if (n * 2 > myLength) {
            n = myLength - n;
        }
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            return 0;
        }
        int val = 1;
        for (int i = 1; i <= n; ++i, --myLength) {
            val *= myLength;
            val /= i;
        }
        return val;
    }
    
    @JRubyMethod(name = { "combination" })
    public IRubyObject combination(final ThreadContext context, final IRubyObject num, final Block block) {
        final Ruby runtime = context.getRuntime();
        if (!block.isGiven()) {
            return RubyEnumerator.enumeratorize(runtime, this, "combination", num);
        }
        final int n = RubyNumeric.num2int(num);
        if (n == 0) {
            block.yield(context, newEmptyArray(runtime));
        }
        else if (n == 1) {
            for (int i = 0; i < this.realLength; ++i) {
                block.yield(context, newArray(runtime, this.eltOk(i)));
            }
        }
        else if (n >= 0 && this.realLength >= n) {
            final int[] stack = new int[n + 1];
            final int nlen = combinationLength(this.realLength, n);
            final IRubyObject[] chosen = new IRubyObject[n];
            int lev = 0;
            stack[0] = -1;
            for (int j = 0; j < nlen; ++j) {
                chosen[lev] = this.eltOk(stack[lev + 1]);
                ++lev;
                while (lev < n) {
                    final IRubyObject[] array = chosen;
                    final int n2 = lev;
                    final int[] array2 = stack;
                    final int n3 = lev + 1;
                    final int n4 = stack[lev] + 1;
                    array2[n3] = n4;
                    array[n2] = this.eltOk(n4);
                    ++lev;
                }
                block.yield(context, newArray(runtime, chosen));
                do {
                    final int[] array3 = stack;
                    final int n5 = lev--;
                    ++array3[n5];
                } while (lev != 0 && stack[lev + 1] + n == this.realLength + lev + 1);
            }
        }
        return this;
    }
    
    @JRubyMethod(name = { "repeated_combination" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject repeatedCombination(final ThreadContext context, final IRubyObject num, final Block block) {
        final Ruby runtime = context.getRuntime();
        if (!block.isGiven()) {
            return RubyEnumerator.enumeratorize(runtime, this, "repeated_combination", num);
        }
        final int n = RubyNumeric.num2int(num);
        final int myRealLength = this.realLength;
        final IRubyObject[] myValues = new IRubyObject[this.realLength];
        this.safeArrayCopy(this.values, this.begin, myValues, 0, this.realLength);
        if (n >= 0) {
            if (n == 0) {
                block.yield(context, newEmptyArray(runtime));
            }
            else if (n == 1) {
                for (int i = 0; i < myRealLength; ++i) {
                    block.yield(context, newArray(runtime, myValues[i]));
                }
            }
            else {
                final int[] stack = new int[n];
                repeatCombination(context, runtime, myValues, stack, 0, myRealLength - 1, block);
            }
        }
        return this;
    }
    
    private static void repeatCombination(final ThreadContext context, final Ruby runtime, final IRubyObject[] values, final int[] stack, final int index, final int max, final Block block) {
        if (index == stack.length) {
            final IRubyObject[] obj = new IRubyObject[stack.length];
            for (int i = 0; i < obj.length; ++i) {
                final int idx = stack[i];
                obj[i] = values[idx];
            }
            block.yield(context, newArray(runtime, obj));
        }
        else {
            int minValue = 0;
            if (index > 0) {
                minValue = stack[index - 1];
            }
            for (int i = minValue; i <= max; ++i) {
                stack[index] = i;
                repeatCombination(context, runtime, values, stack, index + 1, max, block);
            }
        }
    }
    
    private void permute(final ThreadContext context, final int n, final int r, final int[] p, final int index, final boolean[] used, final boolean repeat, final RubyArray values, final Block block) {
        for (int i = 0; i < n; ++i) {
            if (repeat || !used[i]) {
                p[index] = i;
                if (index < r - 1) {
                    used[i] = true;
                    this.permute(context, n, r, p, index + 1, used, repeat, values, block);
                    used[i] = false;
                }
                else {
                    final RubyArray result = newArray(context.getRuntime(), r);
                    for (int j = 0; j < r; ++j) {
                        result.values[result.begin + j] = values.values[values.begin + p[j]];
                    }
                    result.realLength = r;
                    block.yield(context, result);
                }
            }
        }
    }
    
    @JRubyMethod(name = { "permutation" })
    public IRubyObject permutation(final ThreadContext context, final IRubyObject num, final Block block) {
        return block.isGiven() ? this.permutationCommon(context, RubyNumeric.num2int(num), false, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "permutation", num);
    }
    
    @JRubyMethod(name = { "permutation" })
    public IRubyObject permutation(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.permutationCommon(context, this.realLength, false, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "permutation");
    }
    
    @JRubyMethod(name = { "repeated_permutation" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject repeated_permutation(final ThreadContext context, final IRubyObject num, final Block block) {
        return block.isGiven() ? this.permutationCommon(context, RubyNumeric.num2int(num), true, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "repeated_permutation", num);
    }
    
    @JRubyMethod(name = { "repeated_permutation" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject repeated_permutation(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.permutationCommon(context, this.realLength, true, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "repeated_permutation");
    }
    
    private IRubyObject permutationCommon(final ThreadContext context, final int r, final boolean repeat, final Block block) {
        if (r == 0) {
            block.yield(context, newEmptyArray(context.getRuntime()));
        }
        else if (r == 1) {
            for (int i = 0; i < this.realLength; ++i) {
                block.yield(context, newArray(context.getRuntime(), this.eltOk(i)));
            }
        }
        else if (r >= 0 && this.realLength >= r) {
            final int n = this.realLength;
            this.permute(context, n, r, new int[n], 0, new boolean[n], repeat, this.makeShared(this.begin, n, this.getMetaClass()), block);
        }
        return this;
    }
    
    @JRubyMethod(name = { "choice" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject choice(final ThreadContext context) {
        if (this.realLength == 0) {
            return context.nil;
        }
        return this.eltOk(context.runtime.getRandom().nextInt(this.realLength));
    }
    
    @JRubyMethod(name = { "shuffle!" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject shuffle_bang(final ThreadContext context) {
        this.modify();
        final Random random = context.getRuntime().getRandom();
        int i = this.realLength;
        try {
            while (i > 0) {
                final int r = random.nextInt(i);
                final IRubyObject tmp = this.values[this.begin + --i];
                this.values[this.begin + i] = this.values[this.begin + r];
                this.values[this.begin + r] = tmp;
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            this.concurrentModification();
        }
        return this;
    }
    
    @JRubyMethod(name = { "shuffle!" }, optional = 1, compat = CompatVersion.RUBY1_9)
    public IRubyObject shuffle_bang(final ThreadContext context, final IRubyObject[] args) {
        this.modify();
        IRubyObject randgen = context.runtime.getRandomClass();
        if (args.length > 0) {
            final IRubyObject hash = TypeConverter.checkHashType(context.runtime, args[args.length - 1]);
            if (!hash.isNil()) {
                randgen = ((RubyHash)hash).fastARef(context.runtime.newSymbol("random"));
            }
        }
        int i = this.realLength;
        try {
            while (i > 0) {
                final int r = this.randomReal(context, randgen, i);
                final IRubyObject tmp = this.values[this.begin + --i];
                this.values[this.begin + i] = this.values[this.begin + r];
                this.values[this.begin + r] = tmp;
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            this.concurrentModification();
        }
        return this;
    }
    
    @JRubyMethod(name = { "shuffle" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject shuffle(final ThreadContext context) {
        final RubyArray ary = this.aryDup();
        ary.shuffle_bang(context);
        return ary;
    }
    
    @JRubyMethod(name = { "shuffle" }, optional = 1, compat = CompatVersion.RUBY1_9)
    public IRubyObject shuffle(final ThreadContext context, final IRubyObject[] args) {
        final RubyArray ary = this.aryDup();
        ary.shuffle_bang(context, args);
        return ary;
    }
    
    @JRubyMethod(name = { "sample" }, optional = 2, compat = CompatVersion.RUBY1_9)
    public IRubyObject sample(final ThreadContext context, IRubyObject[] args) {
        try {
            IRubyObject randgen = context.runtime.getRandomClass();
            if (args.length == 0) {
                if (this.realLength == 0) {
                    return context.nil;
                }
                final int i = (this.realLength == 1) ? 0 : this.randomReal(context, randgen, this.realLength);
                return this.values[this.begin + i];
            }
            else {
                if (args.length > 0) {
                    final IRubyObject hash = TypeConverter.checkHashType(context.runtime, args[args.length - 1]);
                    if (!hash.isNil()) {
                        randgen = ((RubyHash)hash).fastARef(context.runtime.newSymbol("random"));
                        final IRubyObject[] newargs = new IRubyObject[args.length - 1];
                        System.arraycopy(args, 0, newargs, 0, args.length - 1);
                        args = newargs;
                    }
                }
                if (args.length == 0) {
                    if (this.realLength == 0) {
                        return context.nil;
                    }
                    if (this.realLength == 1) {
                        return this.values[0];
                    }
                    return this.values[this.randomReal(context, randgen, this.realLength)];
                }
                else {
                    final Ruby runtime = context.getRuntime();
                    int n = RubyNumeric.num2int(args[0]);
                    if (n < 0) {
                        throw runtime.newArgumentError("negative sample number");
                    }
                    if (n > this.realLength) {
                        n = this.realLength;
                    }
                    final double[] rnds = new double[RubyArray.SORTED_THRESHOLD];
                    if (n <= RubyArray.SORTED_THRESHOLD) {
                        for (int idx = 0; idx < n; ++idx) {
                            rnds[idx] = RubyRandom.randomReal(context, randgen);
                        }
                    }
                    switch (n) {
                        case 0: {
                            return newEmptyArray(runtime);
                        }
                        case 1: {
                            if (this.realLength <= 0) {
                                return newEmptyArray(runtime);
                            }
                            return newArray(runtime, this.values[this.begin + (int)(rnds[0] * this.realLength)]);
                        }
                        case 2: {
                            final int j = (int)(rnds[0] * this.realLength);
                            int k = (int)(rnds[1] * (this.realLength - 1));
                            if (k >= j) {
                                ++k;
                            }
                            return newArray(runtime, this.values[this.begin + j], this.values[this.begin + k]);
                        }
                        case 3: {
                            final int j = (int)(rnds[0] * this.realLength);
                            int k = (int)(rnds[1] * (this.realLength - 1));
                            int l = (int)(rnds[2] * (this.realLength - 2));
                            int m = k;
                            int g = j;
                            if (k >= j) {
                                m = j;
                                g = ++k;
                            }
                            if (l >= m && ++l >= g) {
                                ++l;
                            }
                            return new RubyArray(runtime, new IRubyObject[] { this.values[this.begin + j], this.values[this.begin + k], this.values[this.begin + l] });
                        }
                        default: {
                            int len = this.realLength;
                            if (n > len) {
                                n = len;
                            }
                            if (n < RubyArray.SORTED_THRESHOLD) {
                                final int[] idx2 = new int[RubyArray.SORTED_THRESHOLD];
                                final int[] sorted = new int[RubyArray.SORTED_THRESHOLD];
                                sorted[0] = (idx2[0] = (int)(rnds[0] * len));
                                for (int j = 1; j < n; ++j) {
                                    int k;
                                    int l;
                                    for (l = (int)(rnds[j] * --len), k = 0; k < j && l >= sorted[k]; ++l, ++k) {}
                                    System.arraycopy(sorted, k, sorted, k + 1, j - k);
                                    sorted[k] = (idx2[j] = l);
                                }
                                final IRubyObject[] result = new IRubyObject[n];
                                for (int j = 0; j < n; ++j) {
                                    result[j] = this.values[this.begin + idx2[j]];
                                }
                                return new RubyArray(runtime, result);
                            }
                            final IRubyObject[] result2 = new IRubyObject[len];
                            System.arraycopy(this.values, this.begin, result2, 0, len);
                            for (int j = 0; j < n; ++j) {
                                final int k = this.randomReal(context, randgen, len - j) + j;
                                final IRubyObject tmp = result2[k];
                                result2[k] = result2[j];
                                result2[j] = tmp;
                            }
                            final RubyArray ary = new RubyArray(runtime, result2);
                            ary.realLength = n;
                            return ary;
                        }
                    }
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException aioob) {
            this.concurrentModification();
            return this;
        }
    }
    
    @JRubyMethod(name = { "sample" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject sample(final ThreadContext context, final IRubyObject nv) {
        final Ruby runtime = context.getRuntime();
        final Random random = runtime.getRandom();
        int n = RubyNumeric.num2int(nv);
        if (n < 0) {
            throw runtime.newArgumentError("negative sample number");
        }
        if (n > this.realLength) {
            n = this.realLength;
        }
        try {
            switch (n) {
                case 0: {
                    return newEmptyArray(runtime);
                }
                case 1: {
                    if (this.realLength <= 0) {
                        return newEmptyArray(runtime);
                    }
                    return newArray(runtime, this.values[this.begin + random.nextInt(this.realLength)]);
                }
                case 2: {
                    final int i = random.nextInt(this.realLength);
                    int j = random.nextInt(this.realLength - 1);
                    if (j >= i) {
                        ++j;
                    }
                    return newArray(runtime, this.values[this.begin + i], this.values[this.begin + j]);
                }
                case 3: {
                    final int i = random.nextInt(this.realLength);
                    int j = random.nextInt(this.realLength - 1);
                    int k = random.nextInt(this.realLength - 2);
                    int l = j;
                    int g = i;
                    if (j >= i) {
                        l = i;
                        g = ++j;
                    }
                    if (k >= l && ++k >= g) {
                        ++k;
                    }
                    return new RubyArray(runtime, new IRubyObject[] { this.values[this.begin + i], this.values[this.begin + j], this.values[this.begin + k] });
                }
                default: {
                    int len = this.realLength;
                    if (n > len) {
                        n = len;
                    }
                    if (n < RubyArray.SORTED_THRESHOLD) {
                        final int[] idx = new int[RubyArray.SORTED_THRESHOLD];
                        final int[] sorted = new int[RubyArray.SORTED_THRESHOLD];
                        sorted[0] = (idx[0] = random.nextInt(len));
                        for (int i = 1; i < n; ++i) {
                            int j;
                            int k;
                            for (k = random.nextInt(--len), j = 0; j < i && k >= sorted[j]; ++k, ++j) {}
                            System.arraycopy(sorted, j, sorted, j + 1, i - j);
                            sorted[j] = (idx[i] = k);
                        }
                        final IRubyObject[] result = new IRubyObject[n];
                        for (int i = 0; i < n; ++i) {
                            result[i] = this.values[this.begin + idx[i]];
                        }
                        return new RubyArray(runtime, result);
                    }
                    final IRubyObject[] result2 = new IRubyObject[len];
                    System.arraycopy(this.values, this.begin, result2, 0, len);
                    for (int i = 0; i < n; ++i) {
                        final int j = random.nextInt(len - i) + i;
                        final IRubyObject tmp = result2[j];
                        result2[j] = result2[i];
                        result2[i] = tmp;
                    }
                    final RubyArray ary = new RubyArray(runtime, result2);
                    ary.realLength = n;
                    return ary;
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException aioob) {
            this.concurrentModification();
            return this;
        }
    }
    
    private int randomReal(final ThreadContext context, final IRubyObject randgen, final int len) {
        return (int)(RubyRandom.randomReal(context, randgen) * len);
    }
    
    private static void aryReverse(final IRubyObject[] _p1, int p1, final IRubyObject[] _p2, int p2) {
        while (p1 < p2) {
            final IRubyObject tmp = _p1[p1];
            _p1[p1++] = _p2[p2];
            _p2[p2--] = tmp;
        }
    }
    
    private IRubyObject internalRotateBang(final ThreadContext context, int cnt) {
        this.modify();
        try {
            if (cnt != 0) {
                final IRubyObject[] ptr = this.values;
                int len = this.realLength;
                if (len > 0 && (cnt = rotateCount(cnt, len)) > 0) {
                    --len;
                    if (cnt < len) {
                        aryReverse(ptr, this.begin + cnt, ptr, this.begin + len);
                    }
                    if (--cnt > 0) {
                        aryReverse(ptr, this.begin, ptr, this.begin + cnt);
                    }
                    if (len > 0) {
                        aryReverse(ptr, this.begin, ptr, this.begin + len);
                    }
                    return this;
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException aioob) {
            this.concurrentModification();
        }
        return context.getRuntime().getNil();
    }
    
    private static int rotateCount(final int cnt, final int len) {
        return (cnt < 0) ? (len - ~cnt % len - 1) : (cnt % len);
    }
    
    private IRubyObject internalRotate(final ThreadContext context, int cnt) {
        int len = this.realLength;
        final RubyArray rotated = this.aryDup();
        rotated.modify();
        try {
            if (len > 0) {
                cnt = rotateCount(cnt, len);
                final IRubyObject[] ptr = this.values;
                final IRubyObject[] ptr2 = rotated.values;
                len -= cnt;
                System.arraycopy(ptr, this.begin + cnt, ptr2, 0, len);
                System.arraycopy(ptr, this.begin, ptr2, len, cnt);
            }
        }
        catch (ArrayIndexOutOfBoundsException aioob) {
            this.concurrentModification();
        }
        return rotated;
    }
    
    @JRubyMethod(name = { "rotate!" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject rotate_bang(final ThreadContext context) {
        this.internalRotateBang(context, 1);
        return this;
    }
    
    @JRubyMethod(name = { "rotate!" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject rotate_bang(final ThreadContext context, final IRubyObject cnt) {
        this.internalRotateBang(context, RubyNumeric.fix2int(cnt));
        return this;
    }
    
    @JRubyMethod(name = { "rotate" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject rotate(final ThreadContext context) {
        return this.internalRotate(context, 1);
    }
    
    @JRubyMethod(name = { "rotate" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject rotate(final ThreadContext context, final IRubyObject cnt) {
        return this.internalRotate(context, RubyNumeric.fix2int(cnt));
    }
    
    public IRubyObject all_p(final ThreadContext context, final Block block) {
        if (!this.isBuiltin("each")) {
            return RubyEnumerable.all_pCommon(context, this, block);
        }
        if (!block.isGiven()) {
            return this.all_pBlockless(context);
        }
        for (int i = 0; i < this.realLength; ++i) {
            if (!block.yield(context, this.eltOk(i)).isTrue()) {
                return context.getRuntime().getFalse();
            }
        }
        return context.getRuntime().getTrue();
    }
    
    private IRubyObject all_pBlockless(final ThreadContext context) {
        for (int i = 0; i < this.realLength; ++i) {
            if (!this.eltOk(i).isTrue()) {
                return context.getRuntime().getFalse();
            }
        }
        return context.getRuntime().getTrue();
    }
    
    public IRubyObject any_p(final ThreadContext context, final Block block) {
        if (!this.isBuiltin("each")) {
            return RubyEnumerable.any_pCommon(context, this, block);
        }
        if (!block.isGiven()) {
            return this.any_pBlockless(context);
        }
        for (int i = 0; i < this.realLength; ++i) {
            if (block.yield(context, this.eltOk(i)).isTrue()) {
                return context.getRuntime().getTrue();
            }
        }
        return context.getRuntime().getFalse();
    }
    
    private IRubyObject any_pBlockless(final ThreadContext context) {
        for (int i = 0; i < this.realLength; ++i) {
            if (this.eltOk(i).isTrue()) {
                return context.getRuntime().getTrue();
            }
        }
        return context.getRuntime().getFalse();
    }
    
    public IRubyObject find(final ThreadContext context, final IRubyObject ifnone, final Block block) {
        if (!this.isBuiltin("each")) {
            return RubyEnumerable.detectCommon(context, this, block);
        }
        return this.detectCommon(context, ifnone, block);
    }
    
    public IRubyObject find_index(final ThreadContext context, final Block block) {
        if (!this.isBuiltin("each")) {
            return RubyEnumerable.find_indexCommon(context, this, block);
        }
        for (int i = 0; i < this.realLength; ++i) {
            if (block.yield(context, this.eltOk(i)).isTrue()) {
                return context.getRuntime().newFixnum(i);
            }
        }
        return context.getRuntime().getNil();
    }
    
    public IRubyObject find_index(final ThreadContext context, final IRubyObject cond) {
        if (!this.isBuiltin("each")) {
            return RubyEnumerable.find_indexCommon(context, this, cond);
        }
        for (int i = 0; i < this.realLength; ++i) {
            if (this.eltOk(i).equals(cond)) {
                return context.getRuntime().newFixnum(i);
            }
        }
        return context.getRuntime().getNil();
    }
    
    public IRubyObject detectCommon(final ThreadContext context, final IRubyObject ifnone, final Block block) {
        for (int i = 0; i < this.realLength; ++i) {
            final IRubyObject value = this.eltOk(i);
            if (block.yield(context, value).isTrue()) {
                return value;
            }
        }
        return (ifnone != null) ? ifnone.callMethod(context, "call") : context.getRuntime().getNil();
    }
    
    public static void marshalTo(final RubyArray array, final MarshalStream output) throws IOException {
        output.registerLinkTarget(array);
        final int length = array.realLength;
        final int begin = array.begin;
        final IRubyObject[] ary = array.values;
        output.writeInt(length);
        try {
            for (int i = 0; i < length; ++i) {
                output.dumpObject(ary[i + begin]);
            }
        }
        catch (ArrayIndexOutOfBoundsException aioob) {
            concurrentModification(array.getRuntime());
        }
    }
    
    public static RubyArray unmarshalFrom(final UnmarshalStream input) throws IOException {
        final int size = input.unmarshalInt();
        final RubyArray result = input.getRuntime().newArray(size);
        input.registerLinkTarget(result);
        for (int i = 0; i < size; ++i) {
            result.append(input.unmarshalObject());
        }
        return result;
    }
    
    @JRubyMethod(name = { "try_convert" }, meta = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject try_convert(final ThreadContext context, final IRubyObject self, final IRubyObject arg) {
        return arg.checkArrayType();
    }
    
    @JRubyMethod(name = { "pack" }, required = 1, compat = CompatVersion.RUBY1_8)
    public RubyString pack(final ThreadContext context, final IRubyObject obj) {
        final RubyString iFmt = obj.convertToString();
        try {
            return Pack.pack(this.getRuntime(), this, iFmt.getByteList());
        }
        catch (ArrayIndexOutOfBoundsException aioob) {
            this.concurrentModification();
            return null;
        }
    }
    
    @JRubyMethod(name = { "pack" }, required = 1, compat = CompatVersion.RUBY1_9)
    public RubyString pack19(final ThreadContext context, final IRubyObject obj) {
        final RubyString iFmt = obj.convertToString();
        try {
            return Pack.pack19(context, context.getRuntime(), this, iFmt);
        }
        catch (ArrayIndexOutOfBoundsException aioob) {
            this.concurrentModification();
            return null;
        }
    }
    
    public Class getJavaClass() {
        return List.class;
    }
    
    public int size() {
        return this.realLength;
    }
    
    public boolean isEmpty() {
        return this.realLength == 0;
    }
    
    public boolean contains(final Object element) {
        return this.indexOf(element) != -1;
    }
    
    public Object[] toArray() {
        final Object[] array = new Object[this.realLength];
        for (int i = this.begin; i < this.realLength; ++i) {
            array[i - this.begin] = this.values[i].toJava(Object.class);
        }
        return array;
    }
    
    public Object[] toArray(final Object[] arg) {
        Object[] array = arg;
        final Class type = array.getClass().getComponentType();
        if (array.length < this.realLength) {
            array = (Object[])Array.newInstance(type, this.realLength);
        }
        for (int length = this.realLength - this.begin, i = 0; i < length; ++i) {
            array[i] = this.values[i + this.begin].toJava(type);
        }
        return array;
    }
    
    public Object toJava(final Class target) {
        if (target.isArray()) {
            final Class type = target.getComponentType();
            final Object rawJavaArray = Array.newInstance(type, this.realLength);
            try {
                ArrayJavaAddons.copyDataToJavaArrayDirect(this.getRuntime().getCurrentContext(), this, rawJavaArray);
            }
            catch (ArrayIndexOutOfBoundsException aioob) {
                this.concurrentModification();
            }
            return rawJavaArray;
        }
        return super.toJava(target);
    }
    
    public boolean add(final Object element) {
        this.append(JavaUtil.convertJavaToUsableRubyObject(this.getRuntime(), element));
        return true;
    }
    
    public boolean remove(final Object element) {
        final Ruby runtime = this.getRuntime();
        final ThreadContext context = runtime.getCurrentContext();
        final IRubyObject item = JavaUtil.convertJavaToUsableRubyObject(runtime, element);
        boolean listchanged = false;
        for (int i1 = 0; i1 < this.realLength; ++i1) {
            final IRubyObject e = this.values[this.begin + i1];
            if (RubyObject.equalInternal(context, e, item)) {
                this.delete_at(i1);
                listchanged = true;
                break;
            }
        }
        return listchanged;
    }
    
    public boolean containsAll(final Collection c) {
        final Iterator iter = c.iterator();
        while (iter.hasNext()) {
            if (this.indexOf(iter.next()) == -1) {
                return false;
            }
        }
        return true;
    }
    
    public boolean addAll(final Collection c) {
        final Iterator iter = c.iterator();
        while (iter.hasNext()) {
            this.add(iter.next());
        }
        return !c.isEmpty();
    }
    
    public boolean addAll(final int index, final Collection c) {
        final Iterator iter = c.iterator();
        int i = index;
        while (iter.hasNext()) {
            this.add(i, iter.next());
            ++i;
        }
        return !c.isEmpty();
    }
    
    public boolean removeAll(final Collection c) {
        boolean listChanged = false;
        final Iterator iter = c.iterator();
        while (iter.hasNext()) {
            final IRubyObject deleted = this.delete(this.getRuntime().getCurrentContext(), JavaUtil.convertJavaToUsableRubyObject(this.getRuntime(), iter.next()), Block.NULL_BLOCK);
            if (!deleted.isNil()) {
                listChanged = true;
            }
        }
        return listChanged;
    }
    
    public boolean retainAll(final Collection c) {
        boolean listChanged = false;
        for (final Object element : this) {
            if (!c.contains(element)) {
                this.remove(element);
                listChanged = true;
            }
        }
        return listChanged;
    }
    
    public Object get(final int index) {
        return this.elt(index).toJava(Object.class);
    }
    
    public Object set(final int index, final Object element) {
        return this.store(index, JavaUtil.convertJavaToUsableRubyObject(this.getRuntime(), element));
    }
    
    public void add(final int index, final Object element) {
        this.insert(new IRubyObject[] { RubyFixnum.newFixnum(this.getRuntime(), index), JavaUtil.convertJavaToUsableRubyObject(this.getRuntime(), element) });
    }
    
    public Object remove(final int index) {
        return this.delete_at(index).toJava(Object.class);
    }
    
    public int indexOf(final Object element) {
        final int myBegin = this.begin;
        if (element != null) {
            final IRubyObject convertedElement = JavaUtil.convertJavaToUsableRubyObject(this.getRuntime(), element);
            for (int i = myBegin; i < myBegin + this.realLength; ++i) {
                if (convertedElement.equals(this.values[i])) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public int lastIndexOf(final Object element) {
        final int myBegin = this.begin;
        if (element != null) {
            final IRubyObject convertedElement = JavaUtil.convertJavaToUsableRubyObject(this.getRuntime(), element);
            for (int i = myBegin + this.realLength - 1; i >= myBegin; --i) {
                if (convertedElement.equals(this.values[i])) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public Iterator iterator() {
        return new RubyArrayConversionIterator();
    }
    
    public ListIterator listIterator() {
        return new RubyArrayConversionListIterator();
    }
    
    public ListIterator listIterator(final int index) {
        return new RubyArrayConversionListIterator(index);
    }
    
    public List subList(final int fromIndex, final int toIndex) {
        if (fromIndex < 0 || toIndex > this.size() || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }
        final IRubyObject subList = this.subseq(fromIndex, toIndex - fromIndex + 1);
        return subList.isNil() ? null : ((List)subList);
    }
    
    public void clear() {
        this.rb_clear();
    }
    
    private IRubyObject safeArrayRef(final IRubyObject[] values, final int i) {
        return this.safeArrayRef(this.getRuntime(), values, i);
    }
    
    private IRubyObject safeArrayRef(final Ruby runtime, final IRubyObject[] values, final int i) {
        try {
            return values[i];
        }
        catch (ArrayIndexOutOfBoundsException x) {
            concurrentModification(runtime);
            return null;
        }
    }
    
    private IRubyObject safeArraySet(final IRubyObject[] values, final int i, final IRubyObject value) {
        return safeArraySet(this.getRuntime(), values, i, value);
    }
    
    private static IRubyObject safeArraySet(final Ruby runtime, final IRubyObject[] values, final int i, final IRubyObject value) {
        try {
            return values[i] = value;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            concurrentModification(runtime);
            return null;
        }
    }
    
    private IRubyObject safeArrayRefSet(final IRubyObject[] values, final int i, final IRubyObject value) {
        return safeArrayRefSet(this.getRuntime(), values, i, value);
    }
    
    private static IRubyObject safeArrayRefSet(final Ruby runtime, final IRubyObject[] values, final int i, final IRubyObject value) {
        try {
            final IRubyObject tmp = values[i];
            values[i] = value;
            return tmp;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            concurrentModification(runtime);
            return null;
        }
    }
    
    private IRubyObject safeArrayRefCondSet(final IRubyObject[] values, final int i, final boolean doSet, final IRubyObject value) {
        return safeArrayRefCondSet(this.getRuntime(), values, i, doSet, value);
    }
    
    private static IRubyObject safeArrayRefCondSet(final Ruby runtime, final IRubyObject[] values, final int i, final boolean doSet, final IRubyObject value) {
        try {
            final IRubyObject tmp = values[i];
            if (doSet) {
                values[i] = value;
            }
            return tmp;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            concurrentModification(runtime);
            return null;
        }
    }
    
    private void safeArrayCopy(final IRubyObject[] source, final int sourceStart, final IRubyObject[] target, final int targetStart, final int length) {
        this.safeArrayCopy(this.getRuntime(), source, sourceStart, target, targetStart, length);
    }
    
    private void safeArrayCopy(final Ruby runtime, final IRubyObject[] source, final int sourceStart, final IRubyObject[] target, final int targetStart, final int length) {
        try {
            System.arraycopy(source, sourceStart, target, targetStart, length);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            concurrentModification(runtime);
        }
    }
    
    static {
        RubyArray.ARRAY_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                return new RubyArray(runtime, klass, IRubyObject.NULL_ARRAY, null);
            }
        };
        RubyArray.SORTED_THRESHOLD = 10;
    }
    
    public class RubyArrayConversionIterator implements Iterator
    {
        protected int index;
        protected int last;
        
        public RubyArrayConversionIterator() {
            this.index = 0;
            this.last = -1;
        }
        
        public boolean hasNext() {
            return this.index < RubyArray.this.realLength;
        }
        
        public Object next() {
            final IRubyObject element = RubyArray.this.elt(this.index);
            this.last = this.index++;
            return element.toJava(Object.class);
        }
        
        public void remove() {
            if (this.last == -1) {
                throw new IllegalStateException();
            }
            RubyArray.this.delete_at(this.last);
            if (this.last < this.index) {
                --this.index;
            }
            this.last = -1;
        }
    }
    
    final class RubyArrayConversionListIterator extends RubyArrayConversionIterator implements ListIterator
    {
        public RubyArrayConversionListIterator() {
        }
        
        public RubyArrayConversionListIterator(final int index) {
            this.index = index;
        }
        
        public boolean hasPrevious() {
            return this.index >= 0;
        }
        
        public Object previous() {
            final RubyArray this$0 = RubyArray.this;
            final int n = this.index - 1;
            this.index = n;
            this.last = n;
            return this$0.elt(n).toJava(Object.class);
        }
        
        public int nextIndex() {
            return this.index;
        }
        
        public int previousIndex() {
            return this.index - 1;
        }
        
        public void set(final Object obj) {
            if (this.last == -1) {
                throw new IllegalStateException();
            }
            RubyArray.this.store(this.last, JavaUtil.convertJavaToUsableRubyObject(RubyArray.this.getRuntime(), obj));
        }
        
        public void add(final Object obj) {
            RubyArray.this.insert(new IRubyObject[] { RubyFixnum.newFixnum(RubyArray.this.getRuntime(), this.index++), JavaUtil.convertJavaToUsableRubyObject(RubyArray.this.getRuntime(), obj) });
            this.last = -1;
        }
    }
}
