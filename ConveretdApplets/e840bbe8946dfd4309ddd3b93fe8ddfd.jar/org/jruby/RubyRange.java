// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.marshal.UnmarshalStream;
import java.util.List;
import org.jruby.runtime.builtin.Variable;
import org.jruby.runtime.component.VariableEntry;
import org.jruby.runtime.marshal.MarshalStream;
import java.io.IOException;
import org.jruby.exceptions.JumpException;
import org.jruby.util.ByteList;
import org.jcodings.Encoding;
import org.jruby.util.TypeConverter;
import org.jruby.runtime.BlockCallback;
import org.jruby.runtime.CallBlock;
import org.jruby.runtime.Arity;
import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.exceptions.RaiseException;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.ObjectMarshal;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "Range" }, include = { "Enumerable" })
public class RubyRange extends RubyObject
{
    private IRubyObject begin;
    private IRubyObject end;
    private boolean isExclusive;
    private static final ObjectAllocator RANGE_ALLOCATOR;
    private static byte[] DOTDOTDOT;
    private static byte[] DOTDOT;
    private static final ObjectMarshal RANGE_MARSHAL;
    
    public static RubyClass createRangeClass(final Ruby runtime) {
        final RubyClass result = runtime.defineClass("Range", runtime.getObject(), RubyRange.RANGE_ALLOCATOR);
        runtime.setRange(result);
        result.index = 18;
        result.setReifiedClass(RubyRange.class);
        result.kindOf = new RubyModule.KindOf() {
            public boolean isKindOf(final IRubyObject obj, final RubyModule type) {
                return obj instanceof RubyRange;
            }
        };
        result.setMarshal(RubyRange.RANGE_MARSHAL);
        result.includeModule(runtime.getEnumerable());
        result.defineAnnotatedMethods(RubyRange.class);
        return result;
    }
    
    private RubyRange(final Ruby runtime, final RubyClass klass) {
        super(runtime, klass);
        final IRubyObject nil = runtime.getNil();
        this.end = nil;
        this.begin = nil;
    }
    
    public static RubyRange newRange(final Ruby runtime, final ThreadContext context, final IRubyObject begin, final IRubyObject end, final boolean isExclusive) {
        final RubyRange range = new RubyRange(runtime, runtime.getRange());
        range.init(context, begin, end, isExclusive);
        return range;
    }
    
    public static RubyRange newExclusiveRange(final Ruby runtime, final ThreadContext context, final IRubyObject begin, final IRubyObject end) {
        final RubyRange range = new RubyRange(runtime, runtime.getRange());
        range.init(context, begin, end, true);
        return range;
    }
    
    public static RubyRange newInclusiveRange(final Ruby runtime, final ThreadContext context, final IRubyObject begin, final IRubyObject end) {
        final RubyRange range = new RubyRange(runtime, runtime.getRange());
        range.init(context, begin, end, false);
        return range;
    }
    
    public void copySpecialInstanceVariables(final IRubyObject clone) {
        final RubyRange range = (RubyRange)clone;
        range.begin = this.begin;
        range.end = this.end;
        range.isExclusive = this.isExclusive;
    }
    
    final boolean checkBegin(final long length) {
        long beg = RubyNumeric.num2long(this.begin);
        if (beg < 0L) {
            beg += length;
            if (beg < 0L) {
                return false;
            }
        }
        else if (length < beg) {
            return false;
        }
        return true;
    }
    
    final long[] begLen(long len, final int err) {
        long beg = RubyNumeric.num2long(this.begin);
        long end = RubyNumeric.num2long(this.end);
        if (beg < 0L) {
            beg += len;
            if (beg < 0L) {
                if (err != 0) {
                    throw this.getRuntime().newRangeError(beg + ".." + (this.isExclusive ? "." : "") + end + " out of range");
                }
                return null;
            }
        }
        if (err == 0 || err == 2) {
            if (beg > len) {
                if (err != 0) {
                    throw this.getRuntime().newRangeError(beg + ".." + (this.isExclusive ? "." : "") + end + " out of range");
                }
                return null;
            }
            else if (end > len) {
                end = len;
            }
        }
        if (end < 0L) {
            end += len;
        }
        if (!this.isExclusive) {
            ++end;
        }
        len = end - beg;
        if (len < 0L) {
            len = 0L;
        }
        return new long[] { beg, len };
    }
    
    final int[] begLenInt(int len, final int err) {
        int beg = RubyNumeric.num2int(this.begin);
        int end = RubyNumeric.num2int(this.end);
        if (beg < 0) {
            beg += len;
            if (beg < 0) {
                if (err != 0) {
                    throw this.getRuntime().newRangeError(beg + ".." + (this.isExclusive ? "." : "") + end + " out of range");
                }
                return null;
            }
        }
        if (err == 0 || err == 2) {
            if (beg > len) {
                if (err != 0) {
                    throw this.getRuntime().newRangeError(beg + ".." + (this.isExclusive ? "." : "") + end + " out of range");
                }
                return null;
            }
            else if (end > len) {
                end = len;
            }
        }
        if (end < 0) {
            end += len;
        }
        if (!this.isExclusive) {
            ++end;
        }
        len = end - beg;
        if (len < 0) {
            len = 0;
        }
        return new int[] { beg, len };
    }
    
    private void init(final ThreadContext context, final IRubyObject begin, final IRubyObject end, final boolean isExclusive) {
        Label_0058: {
            if (begin instanceof RubyFixnum) {
                if (end instanceof RubyFixnum) {
                    break Label_0058;
                }
            }
            try {
                final IRubyObject result = RuntimeHelpers.invokedynamic(context, begin, 4, end);
                if (result.isNil()) {
                    throw this.getRuntime().newArgumentError("bad value for range");
                }
            }
            catch (RaiseException re) {
                throw this.getRuntime().newArgumentError("bad value for range");
            }
        }
        this.begin = begin;
        this.end = end;
        this.isExclusive = isExclusive;
    }
    
    @JRubyMethod(required = 2, optional = 1, visibility = Visibility.PRIVATE)
    public IRubyObject initialize(final ThreadContext context, final IRubyObject[] args, final Block unusedBlock) {
        if (!this.begin.isNil() || !this.end.isNil()) {
            throw this.getRuntime().newNameError("`initialize' called twice", "initialize");
        }
        this.init(context, args[0], args[1], args.length > 2 && args[2].isTrue());
        return this.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "first", "begin" })
    public IRubyObject first() {
        return this.begin;
    }
    
    @JRubyMethod(name = { "last", "end" })
    public IRubyObject last() {
        return this.end;
    }
    
    @JRubyMethod(name = { "hash" })
    public RubyFixnum hash(final ThreadContext context) {
        final long h;
        long hash = h = (this.isExclusive ? 1 : 0);
        long v = RuntimeHelpers.invokedynamic(context, this.begin, 3).convertToInteger().getLongValue();
        hash ^= v << 1;
        v = RuntimeHelpers.invokedynamic(context, this.end, 3).convertToInteger().getLongValue();
        hash ^= v << 9;
        hash ^= h << 24;
        return this.getRuntime().newFixnum(hash);
    }
    
    @JRubyMethod(name = { "inspect" })
    public IRubyObject inspect(final ThreadContext context) {
        final RubyString str = RubyObject.inspect(context, this.begin).strDup(context.getRuntime());
        final RubyString str2 = RubyObject.inspect(context, this.end);
        str.cat(this.isExclusive ? RubyRange.DOTDOTDOT : RubyRange.DOTDOT);
        str.concat(str2);
        str.infectBy(str2);
        return str;
    }
    
    @JRubyMethod(name = { "to_s" })
    public IRubyObject to_s(final ThreadContext context) {
        final RubyString str = RubyString.objAsString(context, this.begin).strDup(context.getRuntime());
        final RubyString str2 = RubyString.objAsString(context, this.end);
        str.cat(this.isExclusive ? RubyRange.DOTDOTDOT : RubyRange.DOTDOT);
        str.concat(str2);
        str.infectBy(str2);
        return str;
    }
    
    @JRubyMethod(name = { "exclude_end?" })
    public RubyBoolean exclude_end_p() {
        return this.getRuntime().newBoolean(this.isExclusive);
    }
    
    @JRubyMethod(name = { "==" }, required = 1)
    public IRubyObject op_equal(final ThreadContext context, final IRubyObject other) {
        if (this == other) {
            return this.getRuntime().getTrue();
        }
        if (!(other instanceof RubyRange)) {
            return this.getRuntime().getFalse();
        }
        final RubyRange otherRange = (RubyRange)other;
        if (RubyObject.equalInternal(context, this.begin, otherRange.begin) && RubyObject.equalInternal(context, this.end, otherRange.end) && this.isExclusive == otherRange.isExclusive) {
            return this.getRuntime().getTrue();
        }
        return this.getRuntime().getFalse();
    }
    
    @JRubyMethod(name = { "eql?" }, required = 1)
    public IRubyObject eql_p(final ThreadContext context, final IRubyObject other) {
        if (this == other) {
            return this.getRuntime().getTrue();
        }
        if (!(other instanceof RubyRange)) {
            return this.getRuntime().getFalse();
        }
        final RubyRange otherRange = (RubyRange)other;
        if (RubyObject.eqlInternal(context, this.begin, otherRange.begin) && RubyObject.eqlInternal(context, this.end, otherRange.end) && this.isExclusive == otherRange.isExclusive) {
            return this.getRuntime().getTrue();
        }
        return this.getRuntime().getFalse();
    }
    
    private IRubyObject rangeLt(final ThreadContext context, final IRubyObject a, final IRubyObject b) {
        final IRubyObject result = RuntimeHelpers.invokedynamic(context, a, 4, b);
        if (result.isNil()) {
            return null;
        }
        return (RubyComparable.cmpint(context, result, a, b) < 0) ? this.getRuntime().getTrue() : null;
    }
    
    private IRubyObject rangeLe(final ThreadContext context, final IRubyObject a, final IRubyObject b) {
        final IRubyObject result = RuntimeHelpers.invokedynamic(context, a, 4, b);
        if (result.isNil()) {
            return null;
        }
        final int c = RubyComparable.cmpint(context, result, a, b);
        if (c == 0) {
            return RubyFixnum.zero(this.getRuntime());
        }
        return (c < 0) ? this.getRuntime().getTrue() : null;
    }
    
    private void rangeEach(final ThreadContext context, final RangeCallBack callback) {
        IRubyObject v = this.begin;
        if (this.isExclusive) {
            while (this.rangeLt(context, v, this.end) != null) {
                callback.call(context, v);
                v = v.callMethod(context, "succ");
            }
        }
        else {
            IRubyObject c;
            while ((c = this.rangeLe(context, v, this.end)) != null && c.isTrue()) {
                callback.call(context, v);
                if (c == RubyFixnum.zero(this.getRuntime())) {
                    break;
                }
                v = v.callMethod(context, "succ");
            }
        }
    }
    
    @JRubyMethod
    public IRubyObject to_a(final ThreadContext context, final Block block) {
        final Ruby runtime = context.getRuntime();
        if (!(this.begin instanceof RubyFixnum) || !(this.end instanceof RubyFixnum)) {
            return RubyEnumerable.to_a(context, this);
        }
        long lim = ((RubyFixnum)this.end).getLongValue();
        if (!this.isExclusive) {
            ++lim;
        }
        final long base = ((RubyFixnum)this.begin).getLongValue();
        final long size = lim - base;
        if (size > 2147483647L) {
            throw runtime.newRangeError("Range size too large for to_a");
        }
        if (size < 0L) {
            return RubyArray.newEmptyArray(runtime);
        }
        final IRubyObject[] array = new IRubyObject[(int)size];
        for (int i = 0; i < size; ++i) {
            array[i] = RubyFixnum.newFixnum(runtime, base + i);
        }
        return RubyArray.newArrayNoCopy(runtime, array);
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_8)
    public IRubyObject each(final ThreadContext context, final Block block) {
        final Ruby runtime = context.getRuntime();
        if (!block.isGiven()) {
            return RubyEnumerator.enumeratorize(runtime, this, "each");
        }
        if (this.begin instanceof RubyFixnum && this.end instanceof RubyFixnum) {
            this.fixnumEach(context, runtime, block);
        }
        else if (this.begin instanceof RubyString) {
            ((RubyString)this.begin).uptoCommon18(context, this.end, this.isExclusive, block);
        }
        else {
            if (!this.begin.respondsTo("succ")) {
                throw this.getRuntime().newTypeError("can't iterate from " + this.begin.getMetaClass().getName());
            }
            this.rangeEach(context, new RangeCallBack() {
                void call(final ThreadContext context, final IRubyObject arg) {
                    block.yield(context, arg);
                }
            });
        }
        return this;
    }
    
    private void fixnumEach(final ThreadContext context, final Ruby runtime, final Block block) {
        long lim = ((RubyFixnum)this.end).getLongValue();
        if (!this.isExclusive) {
            ++lim;
        }
        if (block.getBody().getArgumentType() == 0) {
            final IRubyObject nil = runtime.getNil();
            for (long i = ((RubyFixnum)this.begin).getLongValue(); i < lim; ++i) {
                block.yield(context, nil);
            }
        }
        else {
            for (long j = ((RubyFixnum)this.begin).getLongValue(); j < lim; ++j) {
                block.yield(context, RubyFixnum.newFixnum(runtime, j));
            }
        }
    }
    
    @JRubyMethod(name = { "each" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject each19(final ThreadContext context, final Block block) {
        final Ruby runtime = context.getRuntime();
        if (!block.isGiven()) {
            return RubyEnumerator.enumeratorize(runtime, this, "each");
        }
        if (this.begin instanceof RubyFixnum && this.end instanceof RubyFixnum) {
            this.fixnumEach(context, runtime, block);
        }
        else if (this.begin instanceof RubyString) {
            ((RubyString)this.begin).uptoCommon19(context, this.end, this.isExclusive, block);
        }
        else if (this.begin instanceof RubySymbol) {
            this.begin.asString().uptoCommon19(context, this.end.asString(), this.isExclusive, block, true);
        }
        else {
            if (!this.begin.respondsTo("succ")) {
                throw this.getRuntime().newTypeError("can't iterate from " + this.begin.getMetaClass().getName());
            }
            this.rangeEach(context, new RangeCallBack() {
                void call(final ThreadContext context, final IRubyObject arg) {
                    block.yield(context, arg);
                }
            });
        }
        return this;
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_8)
    public IRubyObject step(final ThreadContext context, final IRubyObject step, final Block block) {
        return block.isGiven() ? this.stepCommon(context, step, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "step", step);
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_8)
    public IRubyObject step(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.stepCommon(context, RubyFixnum.one(context.getRuntime()), block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "step");
    }
    
    private IRubyObject stepCommon(final ThreadContext context, final IRubyObject step, final Block block) {
        final Ruby runtime = context.getRuntime();
        final long unit = RubyNumeric.num2long(step);
        if (unit < 0L) {
            throw runtime.newArgumentError("step can't be negative");
        }
        if (this.begin instanceof RubyFixnum && this.end instanceof RubyFixnum) {
            if (unit == 0L) {
                throw runtime.newArgumentError("step can't be 0");
            }
            this.fixnumStep(context, runtime, unit, block);
        }
        else {
            final IRubyObject tmp = this.begin.checkStringType();
            if (!tmp.isNil()) {
                if (unit == 0L) {
                    throw runtime.newArgumentError("step can't be 0");
                }
                final StepBlockCallBack callback = new StepBlockCallBack(block, RubyFixnum.one(runtime), step);
                final Block blockCallback = CallBlock.newCallClosure(this, runtime.getRange(), Arity.singleArgument(), callback, context);
                ((RubyString)tmp).uptoCommon18(context, this.end, this.isExclusive, blockCallback);
            }
            else if (this.begin instanceof RubyNumeric) {
                if (RubyObject.equalInternal(context, step, RubyFixnum.zero(runtime))) {
                    throw runtime.newArgumentError("step can't be 0");
                }
                this.numericStep(context, runtime, step, block);
            }
            else {
                if (unit == 0L) {
                    throw runtime.newArgumentError("step can't be 0");
                }
                if (!this.begin.respondsTo("succ")) {
                    throw runtime.newTypeError("can't iterate from " + this.begin.getMetaClass().getName());
                }
                this.rangeEach(context, new StepBlockCallBack(block, RubyFixnum.one(runtime), step));
            }
        }
        return this;
    }
    
    private void fixnumStep(final ThreadContext context, final Ruby runtime, final long unit, final Block block) {
        long e = ((RubyFixnum)this.end).getLongValue();
        if (!this.isExclusive) {
            ++e;
        }
        for (long i = ((RubyFixnum)this.begin).getLongValue(); i < e; i += unit) {
            block.yield(context, RubyFixnum.newFixnum(runtime, i));
        }
    }
    
    private void numericStep(final ThreadContext context, final Ruby runtime, final IRubyObject step, final Block block) {
        final String method = this.isExclusive ? "<" : "<=";
        for (IRubyObject beg = this.begin; beg.callMethod(context, method, this.end).isTrue(); beg = beg.callMethod(context, "+", step)) {
            block.yield(context, beg);
        }
    }
    
    @JRubyMethod(name = { "step" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject step19(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.stepCommon19(context, RubyFixnum.zero(context.getRuntime()), block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "step");
    }
    
    @JRubyMethod(name = { "step" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject step19(final ThreadContext context, IRubyObject step, final Block block) {
        final Ruby runtime = context.getRuntime();
        if (!block.isGiven()) {
            return RubyEnumerator.enumeratorize(runtime, this, "step", step);
        }
        if (!(step instanceof RubyNumeric)) {
            step = step.convertToInteger("to_int");
        }
        final IRubyObject zero = RubyFixnum.zero(runtime);
        if (step.callMethod(context, "<", zero).isTrue()) {
            throw runtime.newArgumentError("step can't be negative");
        }
        if (!step.callMethod(context, ">", zero).isTrue()) {
            throw runtime.newArgumentError("step can't be 0");
        }
        return this.stepCommon19(context, step, block);
    }
    
    private IRubyObject stepCommon19(final ThreadContext context, final IRubyObject step, final Block block) {
        final Ruby runtime = context.getRuntime();
        if (this.begin instanceof RubyFixnum && this.end instanceof RubyFixnum && step instanceof RubyFixnum) {
            this.fixnumStep(context, runtime, ((RubyFixnum)step).getLongValue(), block);
        }
        else if (this.begin instanceof RubyFloat || this.end instanceof RubyFloat || step instanceof RubyFloat) {
            RubyNumeric.floatStep19(context, runtime, this.begin, this.end, step, this.isExclusive, block);
        }
        else if (this.begin instanceof RubyNumeric || !this.checkIntegerType(runtime, this.begin, "to_int").isNil() || !this.checkIntegerType(runtime, this.end, "to_int").isNil()) {
            this.numericStep19(context, runtime, step, block);
        }
        else {
            final IRubyObject tmp = this.begin.checkStringType();
            if (!tmp.isNil()) {
                final StepBlockCallBack callback = new StepBlockCallBack(block, RubyFixnum.one(runtime), step);
                final Block blockCallback = CallBlock.newCallClosure(this, runtime.getRange(), Arity.singleArgument(), callback, context);
                ((RubyString)tmp).uptoCommon19(context, this.end, this.isExclusive, blockCallback);
            }
            else {
                if (!this.begin.respondsTo("succ")) {
                    throw runtime.newTypeError("can't iterate from " + this.begin.getMetaClass().getName());
                }
                this.rangeEach(context, new StepBlockCallBack(block, RubyFixnum.one(runtime), step));
            }
        }
        return this;
    }
    
    private void numericStep19(final ThreadContext context, final Ruby runtime, final IRubyObject step, final Block block) {
        final String method = this.isExclusive ? "<" : "<=";
        IRubyObject beg = this.begin;
        for (long i = 0L; beg.callMethod(context, method, this.end).isTrue(); beg = this.begin.callMethod(context, "+", RubyFixnum.newFixnum(runtime, i).callMethod(context, "*", step))) {
            block.yield(context, beg);
            ++i;
        }
    }
    
    @JRubyMethod(name = { "include?", "member?", "===" }, required = 1, compat = CompatVersion.RUBY1_8)
    public RubyBoolean include_p(final ThreadContext context, final IRubyObject obj) {
        if (this.rangeLe(context, this.begin, obj) != null) {
            if (this.isExclusive) {
                if (this.rangeLt(context, obj, this.end) != null) {
                    return context.getRuntime().getTrue();
                }
            }
            else if (this.rangeLe(context, obj, this.end) != null) {
                return context.getRuntime().getTrue();
            }
        }
        return context.getRuntime().getFalse();
    }
    
    @JRubyMethod(name = { "include?", "member?" }, frame = true, compat = CompatVersion.RUBY1_9)
    public IRubyObject include_p19(final ThreadContext context, final IRubyObject obj) {
        final Ruby runtime = context.getRuntime();
        if (this.begin instanceof RubyNumeric || this.end instanceof RubyNumeric || !TypeConverter.convertToTypeWithCheck(this.begin, runtime.getInteger(), "to_int").isNil() || !TypeConverter.convertToTypeWithCheck(this.end, runtime.getInteger(), "to_int").isNil()) {
            if (this.rangeLe(context, this.begin, obj) != null) {
                if (this.isExclusive) {
                    if (this.rangeLt(context, obj, this.end) != null) {
                        return runtime.getTrue();
                    }
                }
                else if (this.rangeLe(context, obj, this.end) != null) {
                    return runtime.getTrue();
                }
            }
            return runtime.getFalse();
        }
        if (this.begin instanceof RubyString && this.end instanceof RubyString && ((RubyString)this.begin).getByteList().getRealSize() == 1 && ((RubyString)this.end).getByteList().getRealSize() == 1) {
            if (obj.isNil()) {
                return runtime.getFalse();
            }
            if (obj instanceof RubyString) {
                final ByteList Vbytes = ((RubyString)obj).getByteList();
                if (Vbytes.getRealSize() != 1) {
                    return runtime.getFalse();
                }
                final int v = Vbytes.getUnsafeBytes()[Vbytes.getBegin()] & 0xFF;
                final ByteList Bbytes = ((RubyString)this.begin).getByteList();
                final int b = Bbytes.getUnsafeBytes()[Bbytes.getBegin()] & 0xFF;
                final ByteList Ebytes = ((RubyString)this.end).getByteList();
                final int e = Ebytes.getUnsafeBytes()[Ebytes.getBegin()] & 0xFF;
                if (Encoding.isAscii(v) && Encoding.isAscii(b) && Encoding.isAscii(e)) {
                    if ((b <= v && v < e) || (!this.isExclusive && v == e)) {
                        return runtime.getTrue();
                    }
                    return runtime.getFalse();
                }
            }
        }
        return RuntimeHelpers.invokeSuper(context, this, obj, Block.NULL_BLOCK);
    }
    
    @JRubyMethod(name = { "===" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject eqq_p19(final ThreadContext context, final IRubyObject obj) {
        return this.callMethod(context, "include?", obj);
    }
    
    @JRubyMethod(name = { "cover?" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject cover_p(final ThreadContext context, final IRubyObject obj) {
        return this.include_p(context, obj);
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_9, frame = true)
    public IRubyObject min(final ThreadContext context, final Block block) {
        if (block.isGiven()) {
            return RuntimeHelpers.invokeSuper(context, this, block);
        }
        final int c = RubyComparable.cmpint(context, RuntimeHelpers.invokedynamic(context, this.begin, 4, this.end), this.begin, this.end);
        if (c > 0 || (c == 0 && this.isExclusive)) {
            return context.getRuntime().getNil();
        }
        return this.begin;
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_9, frame = true)
    public IRubyObject max(final ThreadContext context, final Block block) {
        if (this.begin.callMethod(context, ">", this.end).isTrue()) {
            return context.getRuntime().getNil();
        }
        if (block.isGiven() || (this.isExclusive && !(this.end instanceof RubyNumeric))) {
            return RuntimeHelpers.invokeSuper(context, this, block);
        }
        final int c = RubyComparable.cmpint(context, RuntimeHelpers.invokedynamic(context, this.begin, 4, this.end), this.begin, this.end);
        final Ruby runtime = context.getRuntime();
        if (!this.isExclusive) {
            return this.end;
        }
        if (!(this.end instanceof RubyInteger)) {
            throw runtime.newTypeError("cannot exclude non Integer end value");
        }
        if (c == 0) {
            return runtime.getNil();
        }
        if (this.end instanceof RubyFixnum) {
            return RubyFixnum.newFixnum(runtime, ((RubyFixnum)this.end).getLongValue() - 1L);
        }
        return this.end.callMethod(context, "-", RubyFixnum.one(runtime));
    }
    
    @JRubyMethod(name = { "first" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject first(final ThreadContext context) {
        return this.begin;
    }
    
    @JRubyMethod(name = { "first" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject first(final ThreadContext context, final IRubyObject arg) {
        final Ruby runtime = context.getRuntime();
        final int num = RubyNumeric.num2int(arg);
        final RubyArray result = runtime.newArray(num);
        try {
            RubyEnumerable.callEach(runtime, context, this, Arity.ONE_ARGUMENT, new BlockCallback() {
                int n = num;
                
                public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                    if (this.n-- <= 0) {
                        throw JumpException.SPECIAL_JUMP;
                    }
                    result.append(largs[0]);
                    return runtime.getNil();
                }
            });
        }
        catch (JumpException.SpecialJump specialJump) {}
        return result;
    }
    
    @JRubyMethod(name = { "last" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject last(final ThreadContext context) {
        return this.end;
    }
    
    @JRubyMethod(name = { "last" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject last(final ThreadContext context, final IRubyObject arg) {
        return ((RubyArray)RubyKernel.new_array(context, this, this)).last(arg);
    }
    
    static {
        RANGE_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                return new RubyRange(runtime, klass, null);
            }
        };
        RubyRange.DOTDOTDOT = "...".getBytes();
        RubyRange.DOTDOT = "..".getBytes();
        RANGE_MARSHAL = new ObjectMarshal() {
            public void marshalTo(final Ruby runtime, final Object obj, final RubyClass type, final MarshalStream marshalStream) throws IOException {
                final RubyRange range = (RubyRange)obj;
                marshalStream.registerLinkTarget(range);
                final List<Variable<Object>> attrs = range.getVariableList();
                attrs.add((Variable<IRubyObject>)new VariableEntry<IRubyObject>("begin", range.begin));
                attrs.add((Variable<IRubyObject>)new VariableEntry<IRubyObject>("end", range.end));
                attrs.add(new VariableEntry<Object>("excl", range.isExclusive ? runtime.getTrue() : runtime.getFalse()));
                marshalStream.dumpVariables(attrs);
            }
            
            public Object unmarshalFrom(final Ruby runtime, final RubyClass type, final UnmarshalStream unmarshalStream) throws IOException {
                final RubyRange range = (RubyRange)type.allocate();
                unmarshalStream.registerLinkTarget(range);
                unmarshalStream.defaultVariablesUnmarshal(range);
                range.begin = (IRubyObject)range.removeInternalVariable("begin");
                range.end = (IRubyObject)range.removeInternalVariable("end");
                range.isExclusive = ((IRubyObject)range.removeInternalVariable("excl")).isTrue();
                return range;
            }
        };
    }
    
    private abstract static class RangeCallBack
    {
        abstract void call(final ThreadContext p0, final IRubyObject p1);
    }
    
    private static final class StepBlockCallBack extends RangeCallBack implements BlockCallback
    {
        final Block block;
        IRubyObject iter;
        final IRubyObject step;
        
        StepBlockCallBack(final Block block, final IRubyObject iter, final IRubyObject step) {
            this.block = block;
            this.iter = iter;
            this.step = step;
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject[] args, final Block originalBlock) {
            this.call(context, args[0]);
            return context.getRuntime().getNil();
        }
        
        void call(final ThreadContext context, final IRubyObject arg) {
            if (this.iter instanceof RubyFixnum) {
                this.iter = RubyFixnum.newFixnum(context.getRuntime(), ((RubyFixnum)this.iter).getLongValue() - 1L);
            }
            else {
                this.iter = this.iter.callMethod(context, "-", RubyFixnum.one(context.getRuntime()));
            }
            if (this.iter == RubyFixnum.zero(context.getRuntime())) {
                this.block.yield(context, arg);
                this.iter = this.step;
            }
        }
    }
}
