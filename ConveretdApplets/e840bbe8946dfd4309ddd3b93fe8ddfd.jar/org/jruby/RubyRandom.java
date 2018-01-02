// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.util.ByteList;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.builtin.IRubyObject;
import java.util.Random;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "Random" })
public class RubyRandom extends RubyObject
{
    public static Random globalRandom;
    private static IRubyObject globalSeed;
    private Random random;
    private IRubyObject seed;
    private static ObjectAllocator RANDOM_ALLOCATOR;
    
    public static RubyClass createRandomClass(final Ruby runtime) {
        final RubyClass randomClass = runtime.defineClass("Random", runtime.getObject(), RubyRandom.RANDOM_ALLOCATOR);
        runtime.setRandomClass(randomClass);
        randomClass.defineAnnotatedMethods(RubyRandom.class);
        return randomClass;
    }
    
    private RubyRandom(final Ruby runtime, final RubyClass rubyClass) {
        super(runtime, rubyClass);
        this.random = new Random();
    }
    
    @JRubyMethod(visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public IRubyObject initialize(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        final long seedLong = this.random.nextLong();
        this.seed = RubyBignum.newBignum(runtime, seedLong);
        this.random.setSeed(seedLong);
        return this;
    }
    
    @JRubyMethod(visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public IRubyObject initialize(final ThreadContext context, final IRubyObject arg) {
        long seedLong;
        if (arg instanceof RubyFloat) {
            this.seed = RubyNumeric.num2fix(((RubyFloat)arg).truncate());
            seedLong = RubyNumeric.num2long(this.seed);
        }
        else if (arg instanceof RubyBignum) {
            this.seed = arg;
            seedLong = (long)RubyBignum.big2dbl((RubyBignum)arg);
        }
        else {
            this.seed = arg.convertToInteger();
            seedLong = RubyNumeric.num2long(this.seed);
        }
        this.random.setSeed(seedLong);
        return this;
    }
    
    @JRubyMethod(name = { "seed" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject seed(final ThreadContext context) {
        return this.seed;
    }
    
    @JRubyMethod(name = { "rand" }, meta = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject rand(final ThreadContext context, final IRubyObject recv) {
        return randCommon(context, context.nil, RubyRandom.globalRandom, false);
    }
    
    @JRubyMethod(name = { "rand" }, meta = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject rand(final ThreadContext context, final IRubyObject recv, final IRubyObject arg0) {
        return randCommon(context, arg0, RubyRandom.globalRandom, false);
    }
    
    @JRubyMethod(name = { "rand" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject randObj(final ThreadContext context) {
        return randCommon(context, context.nil, this.random, true);
    }
    
    @JRubyMethod(name = { "rand" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject randObj(final ThreadContext context, final IRubyObject arg0) {
        return randCommon(context, arg0, this.random, true);
    }
    
    private static IRubyObject randCommon(final ThreadContext context, IRubyObject arg, final Random random, final boolean raiseArgError) {
        final Ruby runtime = context.getRuntime();
        if (arg.isNil()) {
            return runtime.newFloat(random.nextFloat());
        }
        if (arg instanceof RubyRange) {
            final RubyRange range = (RubyRange)arg;
            IRubyObject first = range.first();
            IRubyObject last = range.last();
            final boolean returnFloat = first instanceof RubyFloat || last instanceof RubyFloat;
            if (returnFloat) {
                first = first.convertToFloat();
                last = last.convertToFloat();
            }
            if (range.include_p19(context, last).isTrue() && !returnFloat) {
                last = last.callMethod(context, "+", runtime.newFixnum(1));
            }
            if (!first.respondsTo("-") || !first.respondsTo("+") || !last.respondsTo("-") || !last.respondsTo("+")) {
                throw runtime.newArgumentError("invalid argument - " + arg.toString());
            }
            final IRubyObject difference = last.callMethod(context, "-", first);
            if (returnFloat) {
                final double max = RubyNumeric.num2dbl(difference);
                final double rand = random.nextDouble() * ((RubyFloat)difference).getDoubleValue();
                return RubyFloat.newFloat(runtime, ((RubyFloat)first).getDoubleValue() + rand);
            }
            final int max2 = (int)RubyNumeric.num2long(difference);
            final int rand2 = random.nextInt(max2);
            return RubyNumeric.num2fix(first.callMethod(context, "+", runtime.newFixnum(rand2)));
        }
        else if (arg instanceof RubyFloat) {
            final double max3 = RubyNumeric.num2dbl(arg);
            if (max3 <= 0.0 && raiseArgError) {
                throw runtime.newArgumentError("invalid argument - " + arg.toString());
            }
            return runtime.newFloat(random.nextFloat() * max3);
        }
        else {
            int max4 = 0;
            if (arg instanceof RubyBignum) {
                max4 = (int)RubyBignum.big2dbl((RubyBignum)arg);
            }
            else {
                if (arg.respondsTo("to_i")) {
                    arg = arg.callMethod(context, "to_i");
                }
                max4 = (int)RubyNumeric.num2long(arg);
            }
            if (max4 <= 0 && raiseArgError) {
                throw runtime.newArgumentError("invalid argument - " + arg.toString());
            }
            final int rand3 = random.nextInt(max4);
            if (arg instanceof RubyBignum) {
                return RubyBignum.newBignum(runtime, rand3);
            }
            return runtime.newFixnum(rand3);
        }
    }
    
    @JRubyMethod(meta = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject srand(final ThreadContext context, final IRubyObject recv) {
        return srand(context, recv, context.getRuntime().getNil());
    }
    
    @JRubyMethod(meta = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject srand(final ThreadContext context, final IRubyObject recv, final IRubyObject arg) {
        return srandCommon(context, recv, arg, false);
    }
    
    public static IRubyObject srandCommon(final ThreadContext context, final IRubyObject recv, final IRubyObject arg, final boolean acceptZero) {
        final Ruby runtime = context.getRuntime();
        IRubyObject newSeed = arg;
        final IRubyObject previousSeed = RubyRandom.globalSeed;
        long seedArg = 0L;
        if (arg instanceof RubyBignum) {
            seedArg = ((RubyBignum)arg).getValue().longValue();
        }
        else if (!arg.isNil()) {
            seedArg = RubyNumeric.num2long(arg);
        }
        if (arg.isNil() || (!acceptZero && seedArg == 0L)) {
            newSeed = RubyNumeric.int2fix(runtime, System.currentTimeMillis() ^ recv.hashCode() ^ runtime.incrementRandomSeedSequence() ^ runtime.getRandom().nextInt(Math.max(1, Math.abs((int)runtime.getRandomSeed()))));
            seedArg = RubyNumeric.fix2long(newSeed);
        }
        RubyRandom.globalSeed = newSeed;
        RubyRandom.globalRandom.setSeed(seedArg);
        return previousSeed;
    }
    
    @JRubyMethod(name = { "==" }, required = 1, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_equal_19(final ThreadContext context, final IRubyObject obj) {
        final Ruby runtime = context.getRuntime();
        if (!(obj instanceof RubyRandom)) {
            return runtime.getFalse();
        }
        final RubyRandom r2 = (RubyRandom)obj;
        return RuntimeHelpers.invokedynamic(context, this.seed(context), 1, r2.seed(context));
    }
    
    @JRubyMethod(name = { "marshal_dump" }, backtrace = true, compat = CompatVersion.RUBY1_9)
    public IRubyObject marshal_dump(final ThreadContext context) {
        final RubyArray dump = context.getRuntime().newArray(this, this.seed);
        if (this.hasVariables()) {
            dump.syncVariables(this);
        }
        return dump;
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_9)
    public IRubyObject marshal_load(final ThreadContext context, final IRubyObject arg) {
        final RubyArray load = arg.convertToArray();
        if (load.size() > 0) {
            final RubyRandom rand = (RubyRandom)load.eltInternal(0);
            this.seed = load.eltInternal(1);
            this.random.setSeed(this.seed.convertToInteger().getLongValue());
        }
        if (load.hasVariables()) {
            this.syncVariables((IRubyObject)load);
        }
        return this;
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_9)
    public IRubyObject bytes(final ThreadContext context, final IRubyObject arg) {
        final int size = RubyNumeric.num2int(arg);
        final byte[] bytes = new byte[size];
        this.random.nextBytes(bytes);
        return context.getRuntime().newString(new ByteList(bytes));
    }
    
    public static double randomReal(final ThreadContext context, final IRubyObject obj) {
        Random random = null;
        if (obj.equals(context.runtime.getRandomClass())) {
            random = RubyRandom.globalRandom;
        }
        if (obj instanceof RubyRandom) {
            random = ((RubyRandom)obj).random;
        }
        if (random != null) {
            return random.nextDouble();
        }
        final double d = RubyNumeric.num2dbl(RuntimeHelpers.invoke(context, obj, "rand"));
        if (d < 0.0 || d >= 1.0) {
            throw context.runtime.newRangeError("random number too big: " + d);
        }
        return d;
    }
    
    @JRubyMethod(name = { "new_seed" }, meta = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject newSeed(final ThreadContext context, final IRubyObject recv) {
        final Ruby runtime = context.getRuntime();
        RubyRandom.globalRandom = new Random();
        final long rand = RubyRandom.globalRandom.nextLong();
        RubyRandom.globalRandom.setSeed(rand);
        return RubyBignum.newBignum(runtime, rand);
    }
    
    static {
        RubyRandom.globalRandom = new Random();
        RubyRandom.RANDOM_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                return new RubyRandom(runtime, klass, null);
            }
        };
    }
}
