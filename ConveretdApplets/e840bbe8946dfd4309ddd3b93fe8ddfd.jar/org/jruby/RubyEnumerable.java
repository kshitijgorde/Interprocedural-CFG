// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.exceptions.RaiseException;
import org.jruby.util.TypeConverter;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;
import org.jruby.exceptions.JumpException;
import org.jruby.common.IRubyWarnings;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.CallBlock19;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.CallBlock;
import org.jruby.runtime.Arity;
import org.jruby.runtime.BlockCallback;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.anno.JRubyModule;

@JRubyModule(name = { "Enumerable" })
public class RubyEnumerable
{
    public static RubyModule createEnumerableModule(final Ruby runtime) {
        final RubyModule enumModule = runtime.defineModule("Enumerable");
        runtime.setEnumerable(enumModule);
        enumModule.defineAnnotatedMethods(RubyEnumerable.class);
        return enumModule;
    }
    
    public static IRubyObject callEach(final Ruby runtime, final ThreadContext context, final IRubyObject self, final BlockCallback callback) {
        return RuntimeHelpers.invoke(context, self, "each", CallBlock.newCallClosure(self, runtime.getEnumerable(), Arity.OPTIONAL, callback, context));
    }
    
    public static IRubyObject callEach19(final Ruby runtime, final ThreadContext context, final IRubyObject self, final BlockCallback callback) {
        return RuntimeHelpers.invoke(context, self, "each", CallBlock19.newCallClosure(self, runtime.getEnumerable(), Arity.OPTIONAL, callback, context));
    }
    
    @Deprecated
    public static IRubyObject callEach(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject[] args, final BlockCallback callback) {
        return RuntimeHelpers.invoke(context, self, "each", args, CallBlock.newCallClosure(self, runtime.getEnumerable(), Arity.OPTIONAL, callback, context));
    }
    
    public static IRubyObject callEach(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Arity arity, final BlockCallback callback) {
        return RuntimeHelpers.invoke(context, self, "each", CallBlock.newCallClosure(self, runtime.getEnumerable(), arity, callback, context));
    }
    
    public static IRubyObject callEach19(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Arity arity, final BlockCallback callback) {
        return RuntimeHelpers.invoke(context, self, "each", CallBlock19.newCallClosure(self, runtime.getEnumerable(), arity, callback, context));
    }
    
    @Deprecated
    public static IRubyObject callEach(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject[] args, final Arity arity, final BlockCallback callback) {
        return RuntimeHelpers.invoke(context, self, "each", args, CallBlock.newCallClosure(self, runtime.getEnumerable(), arity, callback, context));
    }
    
    private static void checkContext(final ThreadContext firstContext, final ThreadContext secondContext, final String name) {
        if (firstContext != secondContext) {
            throw secondContext.getRuntime().newThreadError("Enumerable#" + name + " cannot be parallelized");
        }
    }
    
    public static IRubyObject checkArgs(final Ruby runtime, final IRubyObject[] largs) {
        return (largs.length == 0) ? runtime.getNil() : largs[0];
    }
    
    @JRubyMethod
    public static IRubyObject count(final ThreadContext context, final IRubyObject self, final Block block) {
        final Ruby runtime = context.getRuntime();
        int[] result;
        if (block.isGiven()) {
            result = new int[] { 0 };
            callEach(runtime, context, self, block.arity(), new BlockCallback() {
                public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                    checkContext(context, ctx, "count");
                    final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                    if (block.yield(ctx, larg).isTrue()) {
                        final int[] val$result = result;
                        final int n = 0;
                        ++val$result[n];
                    }
                    return runtime.getNil();
                }
            });
        }
        else {
            if (self.respondsTo("size")) {
                return self.callMethod(context, "size");
            }
            result = new int[] { 0 };
            callEach(runtime, context, self, block.arity(), new BlockCallback() {
                public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                    checkContext(context, ctx, "count");
                    final int[] val$result = result;
                    final int n = 0;
                    ++val$result[n];
                    return runtime.getNil();
                }
            });
        }
        return RubyFixnum.newFixnum(runtime, result[0]);
    }
    
    @JRubyMethod
    public static IRubyObject count(final ThreadContext context, final IRubyObject self, final IRubyObject arg, final Block block) {
        final Ruby runtime = context.getRuntime();
        final int[] result = { 0 };
        if (block.isGiven()) {
            runtime.getWarnings().warn(IRubyWarnings.ID.BLOCK_UNUSED, "given block not used", new Object[0]);
        }
        callEach(runtime, context, self, block.arity(), new BlockCallback() {
            public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block block) {
                final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                checkContext(context, ctx, "count");
                if (larg.equals(arg)) {
                    final int[] val$result = result;
                    final int n = 0;
                    ++val$result[n];
                }
                return runtime.getNil();
            }
        });
        return RubyFixnum.newFixnum(runtime, result[0]);
    }
    
    @JRubyMethod
    public static IRubyObject cycle(final ThreadContext context, final IRubyObject self, final Block block) {
        if (!block.isGiven()) {
            return RubyEnumerator.enumeratorize(context.getRuntime(), self, "cycle");
        }
        return cycleCommon(context, self, -1L, block);
    }
    
    @JRubyMethod
    public static IRubyObject cycle(final ThreadContext context, final IRubyObject self, final IRubyObject arg, final Block block) {
        if (arg.isNil()) {
            return cycle(context, self, block);
        }
        if (!block.isGiven()) {
            return RubyEnumerator.enumeratorize(context.getRuntime(), self, "cycle", arg);
        }
        final long times = RubyNumeric.num2long(arg);
        if (times <= 0L) {
            return context.getRuntime().getNil();
        }
        return cycleCommon(context, self, times, block);
    }
    
    private static IRubyObject cycleCommon(final ThreadContext context, final IRubyObject self, long nv, final Block block) {
        final Ruby runtime = context.getRuntime();
        final RubyArray result = runtime.newArray();
        callEach(runtime, context, self, block.arity(), new BlockCallback() {
            public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                synchronized (result) {
                    result.append(larg);
                }
                block.yield(ctx, larg);
                return runtime.getNil();
            }
        });
        final int len = result.size();
        if (len == 0) {
            return runtime.getNil();
        }
        while (nv < 0L || 0L < --nv) {
            for (int i = 0; i < len; ++i) {
                block.yield(context, result.eltInternal(i));
            }
        }
        return runtime.getNil();
    }
    
    @JRubyMethod(name = { "take" })
    public static IRubyObject take(final ThreadContext context, final IRubyObject self, final IRubyObject n, final Block block) {
        final Ruby runtime = context.getRuntime();
        final long len = RubyNumeric.num2long(n);
        if (len < 0L) {
            throw runtime.newArgumentError("attempt to take negative size");
        }
        if (len == 0L) {
            return runtime.newEmptyArray();
        }
        final RubyArray result = runtime.newArray();
        try {
            callEach(runtime, context, self, block.arity(), new BlockCallback() {
                long i = len;
                
                public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                    final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                    synchronized (result) {
                        result.append(larg);
                        final long i = this.i - 1L;
                        this.i = i;
                        if (i == 0L) {
                            throw JumpException.SPECIAL_JUMP;
                        }
                    }
                    return runtime.getNil();
                }
            });
        }
        catch (JumpException.SpecialJump specialJump) {}
        return result;
    }
    
    @JRubyMethod
    public static IRubyObject take_while(final ThreadContext context, final IRubyObject self, final Block block) {
        if (!block.isGiven()) {
            return RubyEnumerator.enumeratorize(context.getRuntime(), self, "take_while");
        }
        final Ruby runtime = context.getRuntime();
        final RubyArray result = runtime.newArray();
        try {
            callEach(runtime, context, self, block.arity(), new BlockCallback() {
                public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                    final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                    if (!block.yield(ctx, larg).isTrue()) {
                        throw JumpException.SPECIAL_JUMP;
                    }
                    synchronized (result) {
                        result.append(larg);
                    }
                    return runtime.getNil();
                }
            });
        }
        catch (JumpException.SpecialJump specialJump) {}
        return result;
    }
    
    @JRubyMethod(name = { "drop" })
    public static IRubyObject drop(final ThreadContext context, final IRubyObject self, final IRubyObject n, final Block block) {
        final Ruby runtime = context.getRuntime();
        final long len = RubyNumeric.num2long(n);
        if (len < 0L) {
            throw runtime.newArgumentError("attempt to drop negative size");
        }
        final RubyArray result = runtime.newArray();
        try {
            callEach(runtime, context, self, block.arity(), new BlockCallback() {
                long i = len;
                
                public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                    final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                    synchronized (result) {
                        if (this.i == 0L) {
                            result.append(larg);
                        }
                        else {
                            --this.i;
                        }
                    }
                    return runtime.getNil();
                }
            });
        }
        catch (JumpException.SpecialJump specialJump) {}
        return result;
    }
    
    @JRubyMethod
    public static IRubyObject drop_while(final ThreadContext context, final IRubyObject self, final Block block) {
        if (!block.isGiven()) {
            return RubyEnumerator.enumeratorize(context.getRuntime(), self, "drop_while");
        }
        final Ruby runtime = context.getRuntime();
        final RubyArray result = runtime.newArray();
        try {
            callEach(runtime, context, self, block.arity(), new BlockCallback() {
                boolean memo = false;
                
                public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                    final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                    if (!this.memo && !block.yield(ctx, larg).isTrue()) {
                        this.memo = true;
                    }
                    if (this.memo) {
                        synchronized (result) {
                            result.append(larg);
                        }
                    }
                    return runtime.getNil();
                }
            });
        }
        catch (JumpException.SpecialJump specialJump) {}
        return result;
    }
    
    @JRubyMethod(name = { "first" })
    public static IRubyObject first(final ThreadContext context, final IRubyObject self) {
        final Ruby runtime = context.getRuntime();
        final IRubyObject[] holder = { runtime.getNil() };
        try {
            callEach(runtime, context, self, Arity.ONE_ARGUMENT, new BlockCallback() {
                public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                    final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                    checkContext(context, ctx, "first");
                    holder[0] = larg;
                    throw JumpException.SPECIAL_JUMP;
                }
            });
        }
        catch (JumpException.SpecialJump specialJump) {}
        return holder[0];
    }
    
    @JRubyMethod(name = { "first" })
    public static IRubyObject first(final ThreadContext context, final IRubyObject self, final IRubyObject num) {
        final Ruby runtime = context.getRuntime();
        final RubyArray result = runtime.newArray();
        final int firstCount = RubyNumeric.fix2int(num);
        if (firstCount < 0) {
            throw runtime.newArgumentError("negative index");
        }
        if (firstCount == 0) {
            return result;
        }
        try {
            callEach(runtime, context, self, Arity.ONE_ARGUMENT, new BlockCallback() {
                private int iter = RubyNumeric.fix2int(num);
                
                public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                    final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                    checkContext(context, ctx, "first");
                    result.append(larg);
                    if (this.iter-- == 1) {
                        throw JumpException.SPECIAL_JUMP;
                    }
                    return runtime.getNil();
                }
            });
        }
        catch (JumpException.SpecialJump specialJump) {}
        return result;
    }
    
    @JRubyMethod(name = { "to_a", "entries" }, compat = CompatVersion.RUBY1_8)
    public static IRubyObject to_a(final ThreadContext context, final IRubyObject self) {
        final Ruby runtime = context.getRuntime();
        final RubyArray result = runtime.newArray();
        callEach(runtime, context, self, Arity.ONE_ARGUMENT, new AppendBlockCallback(runtime, result));
        return result;
    }
    
    @JRubyMethod(name = { "to_a", "entries" }, rest = true, compat = CompatVersion.RUBY1_8)
    public static IRubyObject to_a(final ThreadContext context, final IRubyObject self, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        final RubyArray result = runtime.newArray();
        RuntimeHelpers.invoke(context, self, "each", args, CallBlock.newCallClosure(self, runtime.getEnumerable(), Arity.OPTIONAL, new AppendBlockCallback(runtime, result), context));
        return result;
    }
    
    @JRubyMethod(name = { "to_a", "entries" }, compat = CompatVersion.RUBY1_9)
    public static IRubyObject to_a19(final ThreadContext context, final IRubyObject self) {
        final Ruby runtime = context.getRuntime();
        final RubyArray result = runtime.newArray();
        callEach(runtime, context, self, Arity.ONE_ARGUMENT, new AppendBlockCallback(runtime, result));
        result.infectBy(self);
        return result;
    }
    
    @JRubyMethod(name = { "to_a", "entries" }, rest = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject to_a19(final ThreadContext context, final IRubyObject self, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        final RubyArray result = runtime.newArray();
        RuntimeHelpers.invoke(context, self, "each", args, CallBlock.newCallClosure(self, runtime.getEnumerable(), Arity.OPTIONAL, new AppendBlockCallback(runtime, result), context));
        result.infectBy(self);
        return result;
    }
    
    @JRubyMethod
    public static IRubyObject sort(final ThreadContext context, final IRubyObject self, final Block block) {
        final Ruby runtime = context.getRuntime();
        final RubyArray result = runtime.newArray();
        callEach(runtime, context, self, Arity.ONE_ARGUMENT, new AppendBlockCallback(runtime, result));
        result.sort_bang(context, block);
        return result;
    }
    
    public static IRubyObject sort_byCommon(final ThreadContext context, final IRubyObject self, final Block block) {
        final Ruby runtime = context.getRuntime();
        if (self instanceof RubyArray) {
            final RubyArray selfArray = (RubyArray)self;
            final IRubyObject[][] valuesAndCriteria = new IRubyObject[selfArray.size()][2];
            callEach(runtime, context, self, block.arity(), new BlockCallback() {
                AtomicInteger i = new AtomicInteger(0);
                
                public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                    final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                    final IRubyObject[] myVandC = valuesAndCriteria[this.i.getAndIncrement()];
                    myVandC[0] = larg;
                    myVandC[1] = block.yield(ctx, larg);
                    return runtime.getNil();
                }
            });
            Arrays.sort(valuesAndCriteria, new Comparator<IRubyObject[]>() {
                public int compare(final IRubyObject[] o1, final IRubyObject[] o2) {
                    return RubyNumeric.fix2int(RuntimeHelpers.invokedynamic(context, o1[1], 4, o2[1]));
                }
            });
            final IRubyObject[] dstArray = new IRubyObject[selfArray.size()];
            for (int i = 0; i < dstArray.length; ++i) {
                dstArray[i] = valuesAndCriteria[i][0];
            }
            return runtime.newArrayNoCopy(dstArray);
        }
        final List<IRubyObject[]> valuesAndCriteria2 = new ArrayList<IRubyObject[]>();
        callEach(runtime, context, self, block.arity(), new BlockCallback() {
            public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                final IRubyObject[] myVandC = { larg, block.yield(ctx, larg) };
                valuesAndCriteria2.add(myVandC);
                return runtime.getNil();
            }
        });
        Collections.sort(valuesAndCriteria2, new Comparator<IRubyObject[]>() {
            public int compare(final IRubyObject[] o1, final IRubyObject[] o2) {
                return RubyNumeric.fix2int(RuntimeHelpers.invokedynamic(context, o1[1], 4, o2[1]));
            }
        });
        final IRubyObject[] dstArray2 = new IRubyObject[valuesAndCriteria2.size()];
        for (int j = 0; j < dstArray2.length; ++j) {
            dstArray2[j] = valuesAndCriteria2.get(j)[0];
        }
        return runtime.newArrayNoCopy(dstArray2);
    }
    
    @JRubyMethod
    public static IRubyObject sort_by(final ThreadContext context, final IRubyObject self, final Block block) {
        return block.isGiven() ? sort_byCommon(context, self, block) : RubyEnumerator.enumeratorize(context.getRuntime(), self, "sort_by");
    }
    
    @JRubyMethod
    public static IRubyObject grep(final ThreadContext context, final IRubyObject self, final IRubyObject pattern, final Block block) {
        final Ruby runtime = context.getRuntime();
        final RubyArray result = runtime.newArray();
        if (block.isGiven()) {
            callEach(runtime, context, self, block.arity(), new BlockCallback() {
                public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                    final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                    ctx.setRubyFrameDelta(ctx.getRubyFrameDelta() + 2);
                    if (pattern.callMethod(ctx, "===", larg).isTrue()) {
                        final IRubyObject value = block.yield(ctx, larg);
                        synchronized (result) {
                            result.append(value);
                        }
                    }
                    ctx.setRubyFrameDelta(ctx.getRubyFrameDelta() - 2);
                    return runtime.getNil();
                }
            });
        }
        else {
            callEach(runtime, context, self, block.arity(), new BlockCallback() {
                public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                    final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                    if (pattern.callMethod(ctx, "===", larg).isTrue()) {
                        synchronized (result) {
                            result.append(larg);
                        }
                    }
                    return runtime.getNil();
                }
            });
        }
        return result;
    }
    
    public static IRubyObject detectCommon(final ThreadContext context, final IRubyObject self, final Block block) {
        return detectCommon(context, self, null, block);
    }
    
    public static IRubyObject detectCommon(final ThreadContext context, final IRubyObject self, final IRubyObject ifnone, final Block block) {
        final Ruby runtime = context.getRuntime();
        final IRubyObject[] result = { null };
        try {
            callEach(runtime, context, self, block.arity(), new BlockCallback() {
                public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                    final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                    checkContext(context, ctx, "detect/find");
                    if (block.yield(ctx, larg).isTrue()) {
                        result[0] = larg;
                        throw JumpException.SPECIAL_JUMP;
                    }
                    return runtime.getNil();
                }
            });
        }
        catch (JumpException.SpecialJump sj) {
            return result[0];
        }
        return (ifnone != null) ? ifnone.callMethod(context, "call") : runtime.getNil();
    }
    
    @JRubyMethod
    public static IRubyObject detect(final ThreadContext context, final IRubyObject self, final Block block) {
        final boolean blockGiven = block.isGiven();
        if (self instanceof RubyArray && blockGiven) {
            return ((RubyArray)self).find(context, null, block);
        }
        return block.isGiven() ? detectCommon(context, self, block) : RubyEnumerator.enumeratorize(context.getRuntime(), self, "detect");
    }
    
    @JRubyMethod
    public static IRubyObject detect(final ThreadContext context, final IRubyObject self, final IRubyObject ifnone, final Block block) {
        final boolean blockGiven = block.isGiven();
        if (self instanceof RubyArray && blockGiven) {
            return ((RubyArray)self).find(context, ifnone, block);
        }
        return block.isGiven() ? detectCommon(context, self, ifnone, block) : RubyEnumerator.enumeratorize(context.getRuntime(), self, "detect", ifnone);
    }
    
    @JRubyMethod
    public static IRubyObject find(final ThreadContext context, final IRubyObject self, final Block block) {
        final boolean blockGiven = block.isGiven();
        if (self instanceof RubyArray && blockGiven) {
            return ((RubyArray)self).find(context, null, block);
        }
        return blockGiven ? detectCommon(context, self, block) : RubyEnumerator.enumeratorize(context.getRuntime(), self, "find");
    }
    
    @JRubyMethod
    public static IRubyObject find(final ThreadContext context, final IRubyObject self, final IRubyObject ifnone, final Block block) {
        final boolean blockGiven = block.isGiven();
        if (self instanceof RubyArray && blockGiven) {
            return ((RubyArray)self).find(context, ifnone, block);
        }
        return blockGiven ? detectCommon(context, self, ifnone, block) : RubyEnumerator.enumeratorize(context.getRuntime(), self, "find", ifnone);
    }
    
    @JRubyMethod
    public static IRubyObject find_index(final ThreadContext context, final IRubyObject self, final Block block) {
        final boolean blockGiven = block.isGiven();
        if (self instanceof RubyArray && blockGiven) {
            return ((RubyArray)self).find_index(context, block);
        }
        return blockGiven ? find_indexCommon(context, self, block) : RubyEnumerator.enumeratorize(context.getRuntime(), self, "find_index");
    }
    
    @JRubyMethod
    public static IRubyObject find_index(final ThreadContext context, final IRubyObject self, final IRubyObject cond, final Block block) {
        final Ruby runtime = context.getRuntime();
        if (block.isGiven()) {
            runtime.getWarnings().warn(IRubyWarnings.ID.BLOCK_UNUSED, "given block not used", new Object[0]);
        }
        if (self instanceof RubyArray) {
            return ((RubyArray)self).find_index(context, cond);
        }
        return find_indexCommon(context, self, cond);
    }
    
    public static IRubyObject find_indexCommon(final ThreadContext context, final IRubyObject self, final Block block) {
        final Ruby runtime = context.getRuntime();
        final long[] result = { 0L };
        try {
            callEach(runtime, context, self, block.arity(), new BlockCallback() {
                public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                    final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                    if (block.yield(ctx, larg).isTrue()) {
                        throw JumpException.SPECIAL_JUMP;
                    }
                    final long[] val$result = result;
                    final int n = 0;
                    ++val$result[n];
                    return runtime.getNil();
                }
            });
        }
        catch (JumpException.SpecialJump sj) {
            return RubyFixnum.newFixnum(runtime, result[0]);
        }
        return runtime.getNil();
    }
    
    public static IRubyObject find_indexCommon(final ThreadContext context, final IRubyObject self, final IRubyObject cond) {
        final Ruby runtime = context.getRuntime();
        final long[] result = { 0L };
        try {
            callEach(runtime, context, self, Arity.ONE_ARGUMENT, new BlockCallback() {
                public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                    final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                    if (larg.equals(cond)) {
                        throw JumpException.SPECIAL_JUMP;
                    }
                    final long[] val$result = result;
                    final int n = 0;
                    ++val$result[n];
                    return runtime.getNil();
                }
            });
        }
        catch (JumpException.SpecialJump sj) {
            return RubyFixnum.newFixnum(runtime, result[0]);
        }
        return runtime.getNil();
    }
    
    public static IRubyObject selectCommon(final ThreadContext context, final IRubyObject self, final Block block) {
        final Ruby runtime = context.getRuntime();
        final RubyArray result = runtime.newArray();
        callEach(runtime, context, self, block.arity(), new BlockCallback() {
            public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                if (block.yield(ctx, larg).isTrue()) {
                    synchronized (result) {
                        result.append(larg);
                    }
                }
                return runtime.getNil();
            }
        });
        return result;
    }
    
    @JRubyMethod
    public static IRubyObject select(final ThreadContext context, final IRubyObject self, final Block block) {
        return block.isGiven() ? selectCommon(context, self, block) : RubyEnumerator.enumeratorize(context.getRuntime(), self, "select");
    }
    
    @JRubyMethod
    public static IRubyObject find_all(final ThreadContext context, final IRubyObject self, final Block block) {
        return block.isGiven() ? selectCommon(context, self, block) : RubyEnumerator.enumeratorize(context.getRuntime(), self, "find_all");
    }
    
    public static IRubyObject rejectCommon(final ThreadContext context, final IRubyObject self, final Block block) {
        final Ruby runtime = context.getRuntime();
        final RubyArray result = runtime.newArray();
        callEach(runtime, context, self, block.arity(), new BlockCallback() {
            public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                if (!block.yield(ctx, larg).isTrue()) {
                    synchronized (result) {
                        result.append(larg);
                    }
                }
                return runtime.getNil();
            }
        });
        return result;
    }
    
    @JRubyMethod
    public static IRubyObject reject(final ThreadContext context, final IRubyObject self, final Block block) {
        return block.isGiven() ? rejectCommon(context, self, block) : RubyEnumerator.enumeratorize(context.getRuntime(), self, "reject");
    }
    
    @JRubyMethod(name = { "collect", "map" }, compat = CompatVersion.RUBY1_8)
    public static IRubyObject collect(final ThreadContext context, final IRubyObject self, final Block block) {
        final Ruby runtime = context.getRuntime();
        final RubyArray result = runtime.newArray();
        if (block.isGiven()) {
            callEach(runtime, context, self, block.arity(), new BlockCallback() {
                public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                    final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                    final IRubyObject value = block.yield(ctx, larg);
                    synchronized (result) {
                        result.append(value);
                    }
                    return runtime.getNil();
                }
            });
        }
        else {
            callEach(runtime, context, self, Arity.ONE_ARGUMENT, new AppendBlockCallback(runtime, result));
        }
        return result;
    }
    
    @JRubyMethod(name = { "collect" }, compat = CompatVersion.RUBY1_9)
    public static IRubyObject collect19(final ThreadContext context, final IRubyObject self, final Block block) {
        return collectCommon19(context, self, block, "collect");
    }
    
    @JRubyMethod(name = { "map" }, compat = CompatVersion.RUBY1_9)
    public static IRubyObject map19(final ThreadContext context, final IRubyObject self, final Block block) {
        return collectCommon19(context, self, block, "map");
    }
    
    private static IRubyObject collectCommon19(final ThreadContext context, final IRubyObject self, final Block block, final String methodName) {
        final Ruby runtime = context.getRuntime();
        if (block.isGiven()) {
            final RubyArray result = runtime.newArray();
            callEach19(runtime, context, self, block.arity(), new BlockCallback() {
                public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                    final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                    final IRubyObject value = block.yield(ctx, larg);
                    synchronized (result) {
                        result.append(value);
                    }
                    return runtime.getNil();
                }
            });
            return result;
        }
        return RubyEnumerator.enumeratorize(runtime, self, methodName);
    }
    
    public static IRubyObject collectCommon(final ThreadContext context, final Ruby runtime, final IRubyObject self, final RubyArray result, final Block block, final BlockCallback blockCallback) {
        callEach(runtime, context, self, Arity.ONE_ARGUMENT, blockCallback);
        return result;
    }
    
    @JRubyMethod(name = { "flat_map" }, compat = CompatVersion.RUBY1_9)
    public static IRubyObject flat_map19(final ThreadContext context, final IRubyObject self, final Block block) {
        return flatMapCommon19(context, self, block, "flat_map");
    }
    
    @JRubyMethod(name = { "collect_concat" }, compat = CompatVersion.RUBY1_9)
    public static IRubyObject collect_concat19(final ThreadContext context, final IRubyObject self, final Block block) {
        return flatMapCommon19(context, self, block, "collect_concat");
    }
    
    private static IRubyObject flatMapCommon19(final ThreadContext context, final IRubyObject self, final Block block, final String methodName) {
        final Ruby runtime = context.getRuntime();
        if (block.isGiven()) {
            final RubyArray ary = runtime.newArray();
            callEach(runtime, context, self, block.arity(), new BlockCallback() {
                public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                    final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                    final IRubyObject i = block.yield(ctx, larg);
                    final IRubyObject tmp = i.checkArrayType();
                    synchronized (ary) {
                        if (tmp.isNil()) {
                            ary.append(i);
                        }
                        else {
                            ary.concat(tmp);
                        }
                    }
                    return runtime.getNil();
                }
            });
            return ary;
        }
        return RubyEnumerator.enumeratorize(runtime, self, methodName);
    }
    
    public static IRubyObject injectCommon(final ThreadContext context, final IRubyObject self, final IRubyObject init, final Block block) {
        final Ruby runtime = context.getRuntime();
        final IRubyObject[] result = { init };
        callEach(runtime, context, self, block.arity(), new BlockCallback() {
            public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                checkContext(context, ctx, "inject");
                result[0] = ((result[0] == null) ? larg : block.yieldArray(ctx, runtime.newArray(result[0], larg), null, null));
                return runtime.getNil();
            }
        });
        return (result[0] == null) ? runtime.getNil() : result[0];
    }
    
    @JRubyMethod(name = { "inject", "reduce" })
    public static IRubyObject inject(final ThreadContext context, final IRubyObject self, final Block block) {
        return injectCommon(context, self, null, block);
    }
    
    @JRubyMethod(name = { "inject", "reduce" })
    public static IRubyObject inject(final ThreadContext context, final IRubyObject self, final IRubyObject arg, final Block block) {
        return block.isGiven() ? injectCommon(context, self, arg, block) : inject(context, self, null, arg, block);
    }
    
    @JRubyMethod(name = { "inject", "reduce" })
    public static IRubyObject inject(final ThreadContext context, final IRubyObject self, final IRubyObject init, final IRubyObject method, final Block block) {
        final Ruby runtime = context.getRuntime();
        if (block.isGiven()) {
            runtime.getWarnings().warn(IRubyWarnings.ID.BLOCK_UNUSED, "given block not used", new Object[0]);
        }
        final String methodId = method.asJavaString();
        final IRubyObject[] result = { init };
        callEach(runtime, context, self, block.arity(), new BlockCallback() {
            public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                result[0] = ((result[0] == null) ? larg : result[0].callMethod(ctx, methodId, larg));
                return runtime.getNil();
            }
        });
        return (result[0] == null) ? runtime.getNil() : result[0];
    }
    
    public static IRubyObject partitionCommon(final ThreadContext context, final IRubyObject self, final Block block) {
        final Ruby runtime = context.getRuntime();
        final RubyArray arr_true = runtime.newArray();
        final RubyArray arr_false = runtime.newArray();
        callEach(runtime, context, self, block.arity(), new BlockCallback() {
            public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                if (block.yield(ctx, larg).isTrue()) {
                    synchronized (arr_true) {
                        arr_true.append(larg);
                    }
                }
                else {
                    synchronized (arr_false) {
                        arr_false.append(larg);
                    }
                }
                return runtime.getNil();
            }
        });
        return runtime.newArray(arr_true, arr_false);
    }
    
    @JRubyMethod(name = { "partition" })
    public static IRubyObject partition(final ThreadContext context, final IRubyObject self, final Block block) {
        return block.isGiven() ? partitionCommon(context, self, block) : RubyEnumerator.enumeratorize(context.getRuntime(), self, "partition");
    }
    
    public static IRubyObject each_with_indexCommon(final ThreadContext context, final IRubyObject self, final Block block) {
        callEach(context.getRuntime(), context, self, Arity.TWO_ARGUMENTS, new EachWithIndex(context, block));
        return self;
    }
    
    public static IRubyObject each_with_indexCommon19(final ThreadContext context, final IRubyObject self, final Block block, final IRubyObject[] args) {
        callEach(context.getRuntime(), context, self, args, Arity.TWO_ARGUMENTS, new EachWithIndex(context, block));
        return self;
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_8)
    public static IRubyObject each_with_index(final ThreadContext context, final IRubyObject self, final Block block) {
        return block.isGiven() ? each_with_indexCommon(context, self, block) : RubyEnumerator.enumeratorize(context.getRuntime(), self, "each_with_index");
    }
    
    @JRubyMethod(name = { "each_with_index" }, rest = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject each_with_index19(final ThreadContext context, final IRubyObject self, final IRubyObject[] args, final Block block) {
        return block.isGiven() ? each_with_indexCommon19(context, self, block, args) : RubyEnumerator.enumeratorize(context.getRuntime(), self, "each_with_index", args);
    }
    
    @JRubyMethod
    public static IRubyObject enum_with_index(final ThreadContext context, final IRubyObject self, final Block block) {
        return block.isGiven() ? each_with_indexCommon(context, self, block) : RubyEnumerator.enumeratorize(context.getRuntime(), self, "enum_with_index");
    }
    
    @JRubyMethod(rest = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject each_entry(final ThreadContext context, final IRubyObject self, final IRubyObject[] args, final Block block) {
        return block.isGiven() ? each_entryCommon(context, self, args, block) : RubyEnumerator.enumeratorize(context.getRuntime(), self, "each_entry", args);
    }
    
    private static IRubyObject each_entryCommon(final ThreadContext context, final IRubyObject self, final IRubyObject[] args, final Block block) {
        callEach(context.getRuntime(), context, self, args, block.arity(), new BlockCallback() {
            public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                return block.yieldSpecific(ctx, RubyEnumerable.checkArgs(ctx.getRuntime(), largs));
            }
        });
        return self;
    }
    
    @JRubyMethod
    public static IRubyObject reverse_each(final ThreadContext context, final IRubyObject self, final Block block) {
        return block.isGiven() ? reverse_eachInternal(context, self, to_a(context, self), block) : RubyEnumerator.enumeratorize(context.getRuntime(), self, "reverse_each");
    }
    
    @JRubyMethod(rest = true)
    public static IRubyObject reverse_each(final ThreadContext context, final IRubyObject self, final IRubyObject[] args, final Block block) {
        return block.isGiven() ? reverse_eachInternal(context, self, to_a(context, self, args), block) : RubyEnumerator.enumeratorize(context.getRuntime(), self, "reverse_each", args);
    }
    
    private static IRubyObject reverse_eachInternal(final ThreadContext context, final IRubyObject self, final IRubyObject obj, final Block block) {
        ((RubyArray)obj).reverse_each(context, block);
        return self;
    }
    
    @JRubyMethod(name = { "include?", "member?" }, required = 1)
    public static IRubyObject include_p(final ThreadContext context, final IRubyObject self, final IRubyObject arg) {
        final Ruby runtime = context.getRuntime();
        try {
            callEach(runtime, context, self, Arity.ONE_ARGUMENT, new BlockCallback() {
                public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                    final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                    checkContext(context, ctx, "include?/member?");
                    if (RubyObject.equalInternal(ctx, larg, arg)) {
                        throw JumpException.SPECIAL_JUMP;
                    }
                    return runtime.getNil();
                }
            });
        }
        catch (JumpException.SpecialJump sj) {
            return runtime.getTrue();
        }
        return runtime.getFalse();
    }
    
    @JRubyMethod
    public static IRubyObject max(final ThreadContext context, final IRubyObject self, final Block block) {
        final Ruby runtime = context.getRuntime();
        final IRubyObject[] result = { null };
        if (block.isGiven()) {
            callEach(runtime, context, self, block.arity(), new BlockCallback() {
                public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                    final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                    checkContext(context, ctx, "max{}");
                    if (result[0] == null || RubyComparable.cmpint(ctx, block.yieldArray(ctx, runtime.newArray(larg, result[0]), null, null), larg, result[0]) > 0) {
                        result[0] = larg;
                    }
                    return runtime.getNil();
                }
            });
        }
        else {
            callEach(runtime, context, self, block.arity(), new BlockCallback() {
                public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                    final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                    synchronized (result) {
                        if (result[0] == null || RubyComparable.cmpint(ctx, RuntimeHelpers.invokedynamic(ctx, larg, 4, result[0]), larg, result[0]) > 0) {
                            result[0] = larg;
                        }
                    }
                    return runtime.getNil();
                }
            });
        }
        return (result[0] == null) ? runtime.getNil() : result[0];
    }
    
    @JRubyMethod
    public static IRubyObject max_by(final ThreadContext context, final IRubyObject self, final Block block) {
        final Ruby runtime = context.getRuntime();
        if (!block.isGiven()) {
            return RubyEnumerator.enumeratorize(runtime, self, "max_by");
        }
        final IRubyObject[] result = { runtime.getNil() };
        callEach(runtime, context, self, block.arity(), new BlockCallback() {
            IRubyObject memo = null;
            
            public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                checkContext(context, ctx, "max_by");
                final IRubyObject v = block.yield(ctx, larg);
                if (this.memo == null || RubyComparable.cmpint(ctx, RuntimeHelpers.invokedynamic(ctx, v, 4, this.memo), v, this.memo) > 0) {
                    this.memo = v;
                    result[0] = larg;
                }
                return runtime.getNil();
            }
        });
        return result[0];
    }
    
    @JRubyMethod
    public static IRubyObject min(final ThreadContext context, final IRubyObject self, final Block block) {
        final Ruby runtime = context.getRuntime();
        final IRubyObject[] result = { null };
        if (block.isGiven()) {
            callEach(runtime, context, self, block.arity(), new BlockCallback() {
                public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                    final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                    checkContext(context, ctx, "min{}");
                    if (result[0] == null || RubyComparable.cmpint(ctx, block.yield(ctx, runtime.newArray(larg, result[0])), larg, result[0]) < 0) {
                        result[0] = larg;
                    }
                    return runtime.getNil();
                }
            });
        }
        else {
            callEach(runtime, context, self, block.arity(), new BlockCallback() {
                public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                    final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                    synchronized (result) {
                        if (result[0] == null || RubyComparable.cmpint(ctx, RuntimeHelpers.invokedynamic(ctx, larg, 4, result[0]), larg, result[0]) < 0) {
                            result[0] = larg;
                        }
                    }
                    return runtime.getNil();
                }
            });
        }
        return (result[0] == null) ? runtime.getNil() : result[0];
    }
    
    @JRubyMethod
    public static IRubyObject min_by(final ThreadContext context, final IRubyObject self, final Block block) {
        final Ruby runtime = context.getRuntime();
        if (!block.isGiven()) {
            return RubyEnumerator.enumeratorize(runtime, self, "min_by");
        }
        final IRubyObject[] result = { runtime.getNil() };
        callEach(runtime, context, self, block.arity(), new BlockCallback() {
            IRubyObject memo = null;
            
            public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                checkContext(context, ctx, "min_by");
                final IRubyObject v = block.yield(ctx, larg);
                if (this.memo == null || RubyComparable.cmpint(ctx, RuntimeHelpers.invokedynamic(ctx, v, 4, this.memo), v, this.memo) < 0) {
                    this.memo = v;
                    result[0] = larg;
                }
                return runtime.getNil();
            }
        });
        return result[0];
    }
    
    @JRubyMethod
    public static IRubyObject minmax(final ThreadContext context, final IRubyObject self, final Block block) {
        final Ruby runtime = context.getRuntime();
        final IRubyObject[] result = { null, null };
        if (block.isGiven()) {
            callEach(runtime, context, self, block.arity(), new BlockCallback() {
                public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                    checkContext(context, ctx, "minmax");
                    final IRubyObject arg = RubyEnumerable.checkArgs(runtime, largs);
                    if (result[0] == null) {
                        result[0] = (result[1] = arg);
                    }
                    else {
                        if (RubyComparable.cmpint(ctx, block.yield(ctx, runtime.newArray(arg, result[0])), arg, result[0]) < 0) {
                            result[0] = arg;
                        }
                        if (RubyComparable.cmpint(ctx, block.yield(ctx, runtime.newArray(arg, result[1])), arg, result[1]) > 0) {
                            result[1] = arg;
                        }
                    }
                    return runtime.getNil();
                }
            });
        }
        else {
            callEach(runtime, context, self, block.arity(), new BlockCallback() {
                public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                    final IRubyObject arg = RubyEnumerable.checkArgs(runtime, largs);
                    synchronized (result) {
                        if (result[0] == null) {
                            result[0] = (result[1] = arg);
                        }
                        else {
                            if (RubyComparable.cmpint(ctx, RuntimeHelpers.invokedynamic(ctx, arg, 4, result[0]), arg, result[0]) < 0) {
                                result[0] = arg;
                            }
                            if (RubyComparable.cmpint(ctx, RuntimeHelpers.invokedynamic(ctx, arg, 4, result[1]), arg, result[1]) > 0) {
                                result[1] = arg;
                            }
                        }
                    }
                    return runtime.getNil();
                }
            });
        }
        if (result[0] == null) {
            result[0] = (result[1] = runtime.getNil());
        }
        return runtime.newArrayNoCopy(result);
    }
    
    @JRubyMethod
    public static IRubyObject minmax_by(final ThreadContext context, final IRubyObject self, final Block block) {
        final Ruby runtime = context.getRuntime();
        if (!block.isGiven()) {
            return RubyEnumerator.enumeratorize(runtime, self, "minmax_by");
        }
        final IRubyObject[] result = { runtime.getNil(), runtime.getNil() };
        callEach(runtime, context, self, block.arity(), new BlockCallback() {
            IRubyObject minMemo = null;
            IRubyObject maxMemo = null;
            
            public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                checkContext(context, ctx, "minmax_by");
                final IRubyObject arg = RubyEnumerable.checkArgs(runtime, largs);
                final IRubyObject v = block.yield(ctx, arg);
                if (this.minMemo == null) {
                    final IRubyObject rubyObject = v;
                    this.maxMemo = rubyObject;
                    this.minMemo = rubyObject;
                    result[0] = (result[1] = arg);
                }
                else {
                    if (RubyComparable.cmpint(ctx, RuntimeHelpers.invokedynamic(ctx, v, 4, this.minMemo), v, this.minMemo) < 0) {
                        this.minMemo = v;
                        result[0] = arg;
                    }
                    if (RubyComparable.cmpint(ctx, RuntimeHelpers.invokedynamic(ctx, v, 4, this.maxMemo), v, this.maxMemo) > 0) {
                        this.maxMemo = v;
                        result[1] = arg;
                    }
                }
                return runtime.getNil();
            }
        });
        return runtime.newArrayNoCopy(result);
    }
    
    @JRubyMethod(name = { "none?" })
    public static IRubyObject none_p(final ThreadContext context, final IRubyObject self, final Block block) {
        final Ruby runtime = context.getRuntime();
        try {
            if (block.isGiven()) {
                callEach(runtime, context, self, block.arity(), new BlockCallback() {
                    public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                        checkContext(context, ctx, "none?");
                        final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                        if (block.yield(ctx, larg).isTrue()) {
                            throw JumpException.SPECIAL_JUMP;
                        }
                        return runtime.getNil();
                    }
                });
            }
            else {
                callEach(runtime, context, self, block.arity(), new BlockCallback() {
                    public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                        checkContext(context, ctx, "none?");
                        final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                        if (larg.isTrue()) {
                            throw JumpException.SPECIAL_JUMP;
                        }
                        return runtime.getNil();
                    }
                });
            }
        }
        catch (JumpException.SpecialJump sj) {
            return runtime.getFalse();
        }
        return runtime.getTrue();
    }
    
    @JRubyMethod(name = { "one?" })
    public static IRubyObject one_p(final ThreadContext context, final IRubyObject self, final Block block) {
        final Ruby runtime = context.getRuntime();
        final boolean[] result = { false };
        try {
            if (block.isGiven()) {
                callEach(runtime, context, self, block.arity(), new BlockCallback() {
                    public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                        checkContext(context, ctx, "one?");
                        final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                        if (block.yield(ctx, larg).isTrue()) {
                            if (result[0]) {
                                throw JumpException.SPECIAL_JUMP;
                            }
                            result[0] = true;
                        }
                        return runtime.getNil();
                    }
                });
            }
            else {
                callEach(runtime, context, self, block.arity(), new BlockCallback() {
                    public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                        checkContext(context, ctx, "one?");
                        final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                        if (larg.isTrue()) {
                            if (result[0]) {
                                throw JumpException.SPECIAL_JUMP;
                            }
                            result[0] = true;
                        }
                        return runtime.getNil();
                    }
                });
            }
        }
        catch (JumpException.SpecialJump sj) {
            return runtime.getFalse();
        }
        return result[0] ? runtime.getTrue() : runtime.getFalse();
    }
    
    @JRubyMethod(name = { "all?" })
    public static IRubyObject all_p(final ThreadContext context, final IRubyObject self, final Block block) {
        if (self instanceof RubyArray) {
            return ((RubyArray)self).all_p(context, block);
        }
        return all_pCommon(context, self, block);
    }
    
    public static IRubyObject all_pCommon(final ThreadContext context, final IRubyObject self, final Block block) {
        final Ruby runtime = context.getRuntime();
        try {
            if (block.isGiven()) {
                callEach(runtime, context, self, block.arity(), new BlockCallback() {
                    public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                        checkContext(context, ctx, "all?");
                        final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                        if (!block.yield(ctx, larg).isTrue()) {
                            throw JumpException.SPECIAL_JUMP;
                        }
                        return runtime.getNil();
                    }
                });
            }
            else {
                callEach(runtime, context, self, block.arity(), new BlockCallback() {
                    public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                        checkContext(context, ctx, "all?");
                        final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                        if (!larg.isTrue()) {
                            throw JumpException.SPECIAL_JUMP;
                        }
                        return runtime.getNil();
                    }
                });
            }
        }
        catch (JumpException.SpecialJump sj) {
            return runtime.getFalse();
        }
        return runtime.getTrue();
    }
    
    @JRubyMethod(name = { "any?" })
    public static IRubyObject any_p(final ThreadContext context, final IRubyObject self, final Block block) {
        if (self instanceof RubyArray) {
            return ((RubyArray)self).any_p(context, block);
        }
        return any_pCommon(context, self, block);
    }
    
    public static IRubyObject any_pCommon(final ThreadContext context, final IRubyObject self, final Block block) {
        final Ruby runtime = context.getRuntime();
        try {
            if (block.isGiven()) {
                callEach(runtime, context, self, block.arity(), new BlockCallback() {
                    public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                        checkContext(context, ctx, "any?");
                        final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                        if (block.yield(ctx, larg).isTrue()) {
                            throw JumpException.SPECIAL_JUMP;
                        }
                        return runtime.getNil();
                    }
                });
            }
            else {
                callEach(runtime, context, self, block.arity(), new BlockCallback() {
                    public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                        checkContext(context, ctx, "any?");
                        final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                        if (larg.isTrue()) {
                            throw JumpException.SPECIAL_JUMP;
                        }
                        return runtime.getNil();
                    }
                });
            }
        }
        catch (JumpException.SpecialJump sj) {
            return runtime.getTrue();
        }
        return runtime.getFalse();
    }
    
    @JRubyMethod(rest = true, compat = CompatVersion.RUBY1_8)
    public static IRubyObject zip(final ThreadContext context, final IRubyObject self, final IRubyObject[] args, final Block block) {
        return zipCommon(context, self, args, block);
    }
    
    @JRubyMethod(name = { "zip" }, rest = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject zip19(final ThreadContext context, final IRubyObject self, final IRubyObject[] args, final Block block) {
        return zipCommon19(context, self, args, block);
    }
    
    public static IRubyObject[] zipCommonConvert(final Ruby runtime, final IRubyObject[] args) {
        return zipCommonConvert(runtime, args, "to_a");
    }
    
    public static IRubyObject[] zipCommonConvert(final Ruby runtime, final IRubyObject[] args, final String method) {
        final RubyClass array = runtime.getArray();
        for (int i = 0; i < args.length; ++i) {
            args[i] = TypeConverter.convertToType(args[i], array, method);
        }
        return args;
    }
    
    public static IRubyObject zipCommon(final ThreadContext context, final IRubyObject self, final IRubyObject[] aArgs, final Block block) {
        final Ruby runtime = context.getRuntime();
        final IRubyObject[] args = zipCommonConvert(runtime, aArgs);
        return zipCommonAry(context, self, args, block);
    }
    
    public static IRubyObject zipCommon19(final ThreadContext context, final IRubyObject self, final IRubyObject[] args, final Block block) {
        final Ruby runtime = context.getRuntime();
        final int aLen = args.length + 1;
        final RubyClass array = runtime.getArray();
        final IRubyObject[] newArgs = new IRubyObject[args.length];
        boolean hasUncoercible = false;
        for (int i = 0; i < args.length; ++i) {
            newArgs[i] = TypeConverter.convertToType(args[i], array, "to_ary", false);
            if (newArgs[i].isNil()) {
                hasUncoercible = true;
            }
        }
        if (hasUncoercible) {
            final RubySymbol each = runtime.newSymbol("each");
            for (int j = 0; j < args.length; ++j) {
                newArgs[j] = args[j].callMethod(context, "to_enum", each);
            }
        }
        if (hasUncoercible) {
            return zipCommonEnum(context, self, newArgs, block);
        }
        return zipCommonAry(context, self, newArgs, block);
    }
    
    public static IRubyObject zipCommonAry(final ThreadContext context, final IRubyObject self, final IRubyObject[] args, final Block block) {
        final Ruby runtime = context.getRuntime();
        final int len = args.length + 1;
        if (block.isGiven()) {
            callEach(runtime, context, self, block.arity(), new BlockCallback() {
                AtomicInteger ix = new AtomicInteger(0);
                
                public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                    final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                    final RubyArray array = runtime.newArray(len);
                    final int myIx = this.ix.getAndIncrement();
                    array.append(larg);
                    for (int i = 0, j = args.length; i < j; ++i) {
                        array.append(((RubyArray)args[i]).entry(myIx));
                    }
                    block.yield(ctx, array);
                    return runtime.getNil();
                }
            });
            return runtime.getNil();
        }
        final RubyArray zip = runtime.newArray();
        callEach(runtime, context, self, block.arity(), new BlockCallback() {
            AtomicInteger ix = new AtomicInteger(0);
            
            public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                final RubyArray array = runtime.newArray(len);
                array.append(larg);
                final int myIx = this.ix.getAndIncrement();
                for (int i = 0, j = args.length; i < j; ++i) {
                    array.append(((RubyArray)args[i]).entry(myIx));
                }
                synchronized (zip) {
                    zip.append(array);
                }
                return runtime.getNil();
            }
        });
        return zip;
    }
    
    public static IRubyObject zipCommonEnum(final ThreadContext context, final IRubyObject self, final IRubyObject[] args, final Block block) {
        final Ruby runtime = context.getRuntime();
        final int len = args.length + 1;
        if (block.isGiven()) {
            callEach(runtime, context, self, block.arity(), new BlockCallback() {
                AtomicInteger ix = new AtomicInteger(0);
                
                public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                    final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                    final RubyArray array = runtime.newArray(len);
                    final int myIx = this.ix.getAndIncrement();
                    array.append(larg);
                    for (int i = 0, j = args.length; i < j; ++i) {
                        array.append(zipEnumNext(ctx, args[i]));
                    }
                    block.yield(ctx, array);
                    return runtime.getNil();
                }
            });
            return runtime.getNil();
        }
        final RubyArray zip = runtime.newArray();
        callEach(runtime, context, self, block.arity(), new BlockCallback() {
            AtomicInteger ix = new AtomicInteger(0);
            
            public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                final RubyArray array = runtime.newArray(len);
                array.append(larg);
                final int myIx = this.ix.getAndIncrement();
                for (int i = 0, j = args.length; i < j; ++i) {
                    array.append(zipEnumNext(ctx, args[i]));
                }
                synchronized (zip) {
                    zip.append(array);
                }
                return runtime.getNil();
            }
        });
        return zip;
    }
    
    private static IRubyObject zipEnumNext(final ThreadContext context, final IRubyObject arg) {
        final Ruby runtime = context.runtime;
        if (arg.isNil()) {
            return context.nil;
        }
        try {
            return arg.callMethod(context, "next");
        }
        catch (RaiseException re) {
            if (re.getException().getMetaClass() == runtime.getStopIteration()) {
                return context.nil;
            }
            throw re;
        }
    }
    
    @JRubyMethod
    public static IRubyObject group_by(final ThreadContext context, final IRubyObject self, final Block block) {
        final Ruby runtime = context.getRuntime();
        if (!block.isGiven()) {
            return RubyEnumerator.enumeratorize(runtime, self, "group_by");
        }
        final RubyHash result = new RubyHash(runtime);
        callEach(runtime, context, self, block.arity(), new BlockCallback() {
            public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                final IRubyObject larg = RubyEnumerable.checkArgs(runtime, largs);
                final IRubyObject key = block.yield(ctx, larg);
                synchronized (result) {
                    RubyArray curr = (RubyArray)result.fastARef(key);
                    if (curr == null) {
                        curr = runtime.newArray();
                        result.fastASet(key, curr);
                    }
                    curr.append(larg);
                }
                return runtime.getNil();
            }
        });
        return result;
    }
    
    @Deprecated
    public static IRubyObject chunk(final ThreadContext context, final IRubyObject self, final IRubyObject[] args, final Block block) {
        switch (Arity.checkArgumentCount(context.runtime, args, 0, 1)) {
            case 0: {
                return chunk(context, self, block);
            }
            case 1: {
                return chunk(context, self, args[0], block);
            }
            default: {
                throw context.runtime.newArgumentError(args.length, 0);
            }
        }
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_9)
    public static IRubyObject chunk(final ThreadContext context, final IRubyObject self, final Block block) {
        return chunk(context, self, context.nil, block);
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_9)
    public static IRubyObject chunk(final ThreadContext context, final IRubyObject self, final IRubyObject initialState, final Block block) {
        if (!block.isGiven()) {
            throw context.getRuntime().newArgumentError("no block given");
        }
        final IRubyObject enumerator = context.getRuntime().getEnumerator().allocate();
        enumerator.getInternalVariables().setInternalVariable("chunk_enumerable", self);
        enumerator.getInternalVariables().setInternalVariable("chunk_categorize", RubyProc.newProc(context.getRuntime(), block, block.type));
        enumerator.getInternalVariables().setInternalVariable("chunk_initial_state", initialState);
        RuntimeHelpers.invoke(context, enumerator, "initialize", CallBlock.newCallClosure(self, context.getRuntime().getEnumerable(), Arity.noArguments(), new ChunkedBlockCallback(context.getRuntime(), enumerator), context));
        return enumerator;
    }
    
    @JRubyMethod(name = { "join" }, compat = CompatVersion.RUBY1_9)
    public static IRubyObject join(final ThreadContext context, final IRubyObject self) {
        return join(context, self, context.getRuntime().getGlobalVariables().get("$,"));
    }
    
    @JRubyMethod(name = { "join" }, compat = CompatVersion.RUBY1_9)
    public static IRubyObject join(final ThreadContext context, final IRubyObject self, final IRubyObject sep) {
        return ((RubyArray)to_a19(context, self)).join19(context, sep);
    }
    
    private static class EachWithIndex implements BlockCallback
    {
        private int index;
        private final Block block;
        private final Ruby runtime;
        
        public EachWithIndex(final ThreadContext ctx, final Block block) {
            this.index = 0;
            this.block = block;
            this.runtime = ctx.getRuntime();
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject[] iargs, final Block block) {
            switch (iargs.length) {
                case 0:
                case 1: {
                    this.block.call(context, RubyEnumerable.checkArgs(this.runtime, iargs), this.runtime.newFixnum(this.index++));
                    break;
                }
                case 2: {
                    this.block.call(context, this.runtime.newArrayNoCopy(iargs), this.runtime.newFixnum(this.index++));
                    break;
                }
            }
            return this.runtime.getNil();
        }
    }
    
    static class ChunkArg
    {
        public IRubyObject categorize;
        public IRubyObject state;
        public IRubyObject prev_value;
        public IRubyObject prev_elts;
        public IRubyObject yielder;
    }
    
    public static final class ChunkedBlockCallback implements BlockCallback
    {
        private final Ruby runtime;
        private final IRubyObject enumerator;
        
        public ChunkedBlockCallback(final Ruby runtime, final IRubyObject enumerator) {
            this.runtime = runtime;
            this.enumerator = enumerator;
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject[] largs, final Block blk) {
            final IRubyObject args = RubyEnumerable.checkArgs(this.runtime, largs);
            final ChunkArg arg = new ChunkArg();
            final IRubyObject enumerable = (IRubyObject)this.enumerator.getInternalVariables().getInternalVariable("chunk_enumerable");
            arg.categorize = (IRubyObject)this.enumerator.getInternalVariables().getInternalVariable("chunk_categorize");
            arg.state = (IRubyObject)this.enumerator.getInternalVariables().getInternalVariable("chunk_initial_state");
            arg.prev_value = this.runtime.getNil();
            arg.prev_elts = this.runtime.getNil();
            arg.yielder = args;
            if (!arg.state.isNil()) {
                arg.state = arg.state.dup();
            }
            final IRubyObject alone = this.runtime.newSymbol("_alone");
            final IRubyObject separator = this.runtime.newSymbol("_separator");
            RubyEnumerable.callEach(this.runtime, context, enumerable, Arity.ONE_ARGUMENT, new BlockCallback() {
                public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                    final IRubyObject i = RubyEnumerable.checkArgs(ChunkedBlockCallback.this.runtime, largs);
                    IRubyObject v;
                    if (arg.state.isNil()) {
                        v = arg.categorize.callMethod(ctx, "call", i);
                    }
                    else {
                        v = arg.categorize.callMethod(ctx, "call", new IRubyObject[] { i, arg.state });
                    }
                    if (v == alone) {
                        if (!arg.prev_value.isNil()) {
                            arg.yielder.callMethod(ctx, "<<", ChunkedBlockCallback.this.runtime.newArray(arg.prev_value, arg.prev_elts));
                            final ChunkArg val$arg = arg;
                            final ChunkArg val$arg2 = arg;
                            final IRubyObject nil = ChunkedBlockCallback.this.runtime.getNil();
                            val$arg2.prev_elts = nil;
                            val$arg.prev_value = nil;
                        }
                        arg.yielder.callMethod(ctx, "<<", ChunkedBlockCallback.this.runtime.newArray(v, ChunkedBlockCallback.this.runtime.newArray(i)));
                    }
                    else if (v.isNil() || v == separator) {
                        if (!arg.prev_value.isNil()) {
                            arg.yielder.callMethod(ctx, "<<", ChunkedBlockCallback.this.runtime.newArray(arg.prev_value, arg.prev_elts));
                            final ChunkArg val$arg3 = arg;
                            final ChunkArg val$arg4 = arg;
                            final IRubyObject nil2 = ChunkedBlockCallback.this.runtime.getNil();
                            val$arg4.prev_elts = nil2;
                            val$arg3.prev_value = nil2;
                        }
                    }
                    else {
                        if (v instanceof RubySymbol && v.toString().charAt(0) == '_') {
                            throw ChunkedBlockCallback.this.runtime.newRuntimeError("symbol begins with an underscore is reserved");
                        }
                        if (arg.prev_value.isNil()) {
                            arg.prev_value = v;
                            arg.prev_elts = ChunkedBlockCallback.this.runtime.newArray(i);
                        }
                        else if (arg.prev_value.equals(v)) {
                            ((RubyArray)arg.prev_elts).append(i);
                        }
                        else {
                            arg.yielder.callMethod(ctx, "<<", ChunkedBlockCallback.this.runtime.newArray(arg.prev_value, arg.prev_elts));
                            arg.prev_value = v;
                            arg.prev_elts = ChunkedBlockCallback.this.runtime.newArray(i);
                        }
                    }
                    return ChunkedBlockCallback.this.runtime.getNil();
                }
            });
            if (!arg.prev_elts.isNil()) {
                arg.yielder.callMethod(context, "<<", this.runtime.newArray(arg.prev_value, arg.prev_elts));
            }
            return this.runtime.getNil();
        }
    }
    
    public static final class AppendBlockCallback implements BlockCallback
    {
        private Ruby runtime;
        private RubyArray result;
        
        public AppendBlockCallback(final Ruby runtime, final RubyArray result) {
            this.runtime = runtime;
            this.result = result;
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject[] largs, final Block blk) {
            this.result.append(RubyEnumerable.checkArgs(this.runtime, largs));
            return this.runtime.getNil();
        }
    }
}
