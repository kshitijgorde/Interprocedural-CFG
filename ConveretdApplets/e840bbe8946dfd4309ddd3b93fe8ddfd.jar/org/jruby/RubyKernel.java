// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.internal.runtime.methods.JavaMethod;
import java.math.BigInteger;
import java.util.Random;
import org.jruby.platform.Platform;
import java.io.OutputStream;
import org.jruby.util.ShellLauncher;
import java.io.ByteArrayOutputStream;
import java.util.Collection;
import java.util.ArrayList;
import org.jruby.runtime.Binding;
import org.jruby.evaluator.ASTInterpreter;
import org.jruby.runtime.backtrace.RubyStackTraceElement;
import java.util.Iterator;
import org.jruby.exceptions.MainExitException;
import org.jruby.anno.FrameField;
import org.jruby.ast.util.ArgsUtil;
import org.jruby.util.ConvertBytes;
import org.jruby.util.TypeConverter;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.common.IRubyWarnings;
import org.jruby.exceptions.RaiseException;
import org.jruby.runtime.load.LoadService;
import org.jruby.util.IdUtil;
import org.jruby.runtime.load.IAutoloadMethod;
import org.jruby.anno.JRubyMethod;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.CallType;
import org.jruby.runtime.Visibility;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.anno.JRubyModule;

@JRubyModule(name = { "Kernel" })
public class RubyKernel
{
    public static final Class<?> IRUBY_OBJECT;
    private static EvalBinding evalBinding18;
    private static EvalBinding evalBinding19;
    private static final Uncaught uncaught18;
    private static final Uncaught uncaught19;
    
    public static RubyModule createKernelModule(final Ruby runtime) {
        final RubyModule module = runtime.defineModule("Kernel");
        runtime.setKernel(module);
        module.defineAnnotatedMethods(RubyKernel.class);
        runtime.setRespondToMethod(module.searchMethod("respond_to?"));
        module.setFlag(4096, false);
        runtime.setPrivateMethodMissing(new MethodMissingMethod(module) {
            public IRubyObject methodMissing(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
                return methodMissing(context, self, name, Visibility.PRIVATE, CallType.NORMAL, args, block);
            }
        });
        runtime.setProtectedMethodMissing(new MethodMissingMethod(module) {
            public IRubyObject methodMissing(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
                return methodMissing(context, self, name, Visibility.PROTECTED, CallType.NORMAL, args, block);
            }
        });
        runtime.setVariableMethodMissing(new MethodMissingMethod(module) {
            public IRubyObject methodMissing(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
                return methodMissing(context, self, name, Visibility.PUBLIC, CallType.VARIABLE, args, block);
            }
        });
        runtime.setSuperMethodMissing(new MethodMissingMethod(module) {
            public IRubyObject methodMissing(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
                return methodMissing(context, self, name, Visibility.PUBLIC, CallType.SUPER, args, block);
            }
        });
        runtime.setNormalMethodMissing(new MethodMissingMethod(module) {
            public IRubyObject methodMissing(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
                return methodMissing(context, self, name, Visibility.PUBLIC, CallType.NORMAL, args, block);
            }
        });
        if (!runtime.is1_9()) {
            runtime.setDefaultMethodMissing(module.searchMethod("method_missing"));
        }
        return module;
    }
    
    @JRubyMethod(module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject at_exit(final ThreadContext context, final IRubyObject recv, final Block block) {
        return context.getRuntime().pushExitBlock(context.getRuntime().newProc(Block.Type.PROC, block));
    }
    
    @JRubyMethod(name = { "autoload?" }, required = 1, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject autoload_p(final ThreadContext context, final IRubyObject recv, final IRubyObject symbol) {
        final Ruby runtime = context.getRuntime();
        final RubyModule module = getModuleForAutoload(runtime, recv);
        final String name = module.getName() + "::" + symbol.asJavaString();
        final IAutoloadMethod autoloadMethod = runtime.getLoadService().autoloadFor(name);
        if (autoloadMethod == null) {
            return runtime.getNil();
        }
        return runtime.newString(autoloadMethod.file());
    }
    
    @JRubyMethod(required = 2, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject autoload(final IRubyObject recv, final IRubyObject symbol, final IRubyObject file) {
        final Ruby runtime = recv.getRuntime();
        final LoadService loadService = runtime.getLoadService();
        final String nonInternedName = symbol.asJavaString();
        if (!IdUtil.isValidConstantName(nonInternedName)) {
            throw runtime.newNameError("autoload must be constant name", nonInternedName);
        }
        if (!runtime.is1_9() && !(file instanceof RubyString)) {
            throw runtime.newTypeError(file, runtime.getString());
        }
        final RubyString fileString = RubyFile.get_path(runtime.getCurrentContext(), file);
        if (fileString.isEmpty()) {
            throw runtime.newArgumentError("empty file name");
        }
        final String baseName = symbol.asJavaString().intern();
        final RubyModule module = getModuleForAutoload(runtime, recv);
        final String nm = module.getName() + "::" + baseName;
        final IRubyObject existingValue = module.fastFetchConstant(baseName);
        if (existingValue != null && existingValue != RubyObject.UNDEF) {
            return runtime.getNil();
        }
        module.fastStoreConstant(baseName, RubyObject.UNDEF);
        loadService.addAutoload(nm, new IAutoloadMethod() {
            public String file() {
                return file.toString();
            }
            
            public IRubyObject load(final Ruby runtime, final String name) {
                final boolean required = loadService.require(this.file());
                if (!required) {
                    return null;
                }
                return module.fastGetConstant(baseName);
            }
        });
        return runtime.getNil();
    }
    
    private static RubyModule getModuleForAutoload(final Ruby runtime, final IRubyObject recv) {
        RubyModule module = (recv instanceof RubyModule) ? ((RubyModule)recv) : runtime.getObject();
        if (module == runtime.getKernel()) {
            if (runtime.is1_9()) {
                module = runtime.getObject().getSingletonClass();
            }
            else {
                module = runtime.getObject();
            }
        }
        return module;
    }
    
    @JRubyMethod(rest = true, frame = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static IRubyObject method_missing(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        final Visibility lastVis = context.getLastVisibility();
        final CallType lastCallType = context.getLastCallType();
        if (args.length == 0 || !(args[0] instanceof RubySymbol)) {
            throw context.getRuntime().newArgumentError("no id given");
        }
        return methodMissingDirect(context, recv, (RubySymbol)args[0], lastVis, lastCallType, args, block);
    }
    
    protected static IRubyObject methodMissingDirect(final ThreadContext context, final IRubyObject recv, final RubySymbol symbol, final Visibility lastVis, final CallType lastCallType, final IRubyObject[] args, final Block block) {
        final Ruby runtime = context.getRuntime();
        final IRubyObject msg = new RubyNameError.RubyNameErrorMessage(runtime, recv, symbol, lastVis, lastCallType);
        RubyClass exc;
        IRubyObject[] exArgs;
        if (lastCallType != CallType.VARIABLE) {
            exc = runtime.getNoMethodError();
            exArgs = new IRubyObject[] { msg, symbol, RubyArray.newArrayNoCopy(runtime, args, 1) };
        }
        else {
            exc = runtime.getNameError();
            exArgs = new IRubyObject[] { msg, symbol };
        }
        throw new RaiseException((RubyException)exc.newInstance(context, exArgs, Block.NULL_BLOCK));
    }
    
    private static IRubyObject methodMissing(final ThreadContext context, final IRubyObject recv, final String name, final Visibility lastVis, final CallType lastCallType, final IRubyObject[] args, final Block block) {
        final Ruby runtime = context.getRuntime();
        final RubySymbol symbol = runtime.newSymbol(name);
        final IRubyObject msg = new RubyNameError.RubyNameErrorMessage(runtime, recv, symbol, lastVis, lastCallType);
        RubyClass exc;
        IRubyObject[] exArgs;
        if (lastCallType != CallType.VARIABLE) {
            exc = runtime.getNoMethodError();
            exArgs = new IRubyObject[] { msg, symbol, RubyArray.newArrayNoCopy(runtime, args) };
        }
        else {
            exc = runtime.getNameError();
            exArgs = new IRubyObject[] { msg, symbol };
        }
        throw new RaiseException((RubyException)exc.newInstance(context, exArgs, Block.NULL_BLOCK));
    }
    
    @JRubyMethod(required = 1, optional = 2, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static IRubyObject open(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        final String arg = args[0].convertToString().toString();
        final Ruby runtime = context.getRuntime();
        if (arg.startsWith("|")) {
            final String command = arg.substring(1);
            return RubyIO.popen(context, runtime.getIO(), new IRubyObject[] { runtime.newString(command) }, block);
        }
        return RubyIO.open(context, runtime.getFile(), args, block);
    }
    
    @JRubyMethod(name = { "open" }, required = 1, optional = 2, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static IRubyObject open19(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        final Ruby runtime = context.getRuntime();
        if (args[0].respondsTo("to_open")) {
            args[0] = args[0].callMethod(context, "to_open");
            return RubyIO.open(context, runtime.getFile(), args, block);
        }
        return open(context, recv, args, block);
    }
    
    @JRubyMethod(name = { "getc" }, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject getc(final ThreadContext context, final IRubyObject recv) {
        context.getRuntime().getWarnings().warn(IRubyWarnings.ID.DEPRECATED_METHOD, "getc is obsolete; use STDIN.getc instead", "getc", "STDIN.getc");
        final IRubyObject defin = context.getRuntime().getGlobalVariables().get("$stdin");
        return defin.callMethod(context, "getc");
    }
    
    @JRubyMethod(name = { "gets" }, optional = 1, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject gets(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        return RubyArgsFile.gets(context, context.getRuntime().getArgsFile(), args);
    }
    
    @JRubyMethod(name = { "abort" }, optional = 1, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject abort(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        if (args.length == 1) {
            runtime.getGlobalVariables().get("$stderr").callMethod(context, "puts", args[0].convertToString());
        }
        exit(runtime, new IRubyObject[] { runtime.getFalse() }, false);
        return runtime.getNil();
    }
    
    @JRubyMethod(name = { "Array" }, required = 1, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject new_array(final ThreadContext context, final IRubyObject recv, final IRubyObject object) {
        return RuntimeHelpers.arrayValue(context, context.getRuntime(), object);
    }
    
    @JRubyMethod(name = { "Complex" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static IRubyObject new_complex(final ThreadContext context, final IRubyObject recv) {
        return RuntimeHelpers.invoke(context, context.getRuntime().getComplex(), "convert");
    }
    
    @JRubyMethod(name = { "Complex" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static IRubyObject new_complex(final ThreadContext context, final IRubyObject recv, final IRubyObject arg) {
        return RuntimeHelpers.invoke(context, context.getRuntime().getComplex(), "convert", arg);
    }
    
    @JRubyMethod(name = { "Complex" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static IRubyObject new_complex(final ThreadContext context, final IRubyObject recv, final IRubyObject arg0, final IRubyObject arg1) {
        return RuntimeHelpers.invoke(context, context.getRuntime().getComplex(), "convert", arg0, arg1);
    }
    
    @JRubyMethod(name = { "Rational" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static IRubyObject new_rational(final ThreadContext context, final IRubyObject recv) {
        return RuntimeHelpers.invoke(context, context.getRuntime().getRational(), "convert");
    }
    
    @JRubyMethod(name = { "Rational" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static IRubyObject new_rational(final ThreadContext context, final IRubyObject recv, final IRubyObject arg) {
        return RuntimeHelpers.invoke(context, context.getRuntime().getRational(), "convert", arg);
    }
    
    @JRubyMethod(name = { "Rational" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static IRubyObject new_rational(final ThreadContext context, final IRubyObject recv, final IRubyObject arg0, final IRubyObject arg1) {
        return RuntimeHelpers.invoke(context, context.getRuntime().getRational(), "convert", arg0, arg1);
    }
    
    @JRubyMethod(name = { "Float" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static RubyFloat new_float(final IRubyObject recv, final IRubyObject object) {
        if (object instanceof RubyFixnum) {
            return RubyFloat.newFloat(object.getRuntime(), ((RubyFixnum)object).getDoubleValue());
        }
        if (object instanceof RubyFloat) {
            return (RubyFloat)object;
        }
        if (object instanceof RubyBignum) {
            return RubyFloat.newFloat(object.getRuntime(), RubyBignum.big2dbl((RubyBignum)object));
        }
        if (object instanceof RubyString) {
            if (((RubyString)object).getByteList().getRealSize() == 0) {
                throw recv.getRuntime().newArgumentError("invalid value for Float(): " + object.inspect());
            }
            return RubyNumeric.str2fnum(recv.getRuntime(), (RubyString)object, true);
        }
        else {
            if (object.isNil()) {
                throw recv.getRuntime().newTypeError("can't convert nil into Float");
            }
            final RubyFloat rFloat = (RubyFloat)TypeConverter.convertToType(object, recv.getRuntime().getFloat(), "to_f");
            if (Double.isNaN(rFloat.getDoubleValue())) {
                throw recv.getRuntime().newArgumentError("invalid value for Float()");
            }
            return rFloat;
        }
    }
    
    @JRubyMethod(name = { "Float" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static RubyFloat new_float19(final IRubyObject recv, final IRubyObject object) {
        final Ruby runtime = recv.getRuntime();
        if (object instanceof RubyFixnum) {
            return RubyFloat.newFloat(runtime, ((RubyFixnum)object).getDoubleValue());
        }
        if (object instanceof RubyFloat) {
            return (RubyFloat)object;
        }
        if (object instanceof RubyBignum) {
            return RubyFloat.newFloat(runtime, RubyBignum.big2dbl((RubyBignum)object));
        }
        if (object instanceof RubyString) {
            if (((RubyString)object).getByteList().getRealSize() == 0) {
                throw runtime.newArgumentError("invalid value for Float(): " + object.inspect());
            }
            final RubyString arg = (RubyString)object;
            if (arg.toString().startsWith("0x")) {
                return ConvertBytes.byteListToInum19(runtime, arg.getByteList(), 16, true).toFloat();
            }
            return RubyNumeric.str2fnum19(runtime, arg, true);
        }
        else {
            if (object.isNil()) {
                throw runtime.newTypeError("can't convert nil into Float");
            }
            return (RubyFloat)TypeConverter.convertToType19(object, runtime.getFloat(), "to_f");
        }
    }
    
    @JRubyMethod(name = { "Integer" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static IRubyObject new_integer(final ThreadContext context, final IRubyObject recv, final IRubyObject object) {
        if (object instanceof RubyFloat) {
            final double val = ((RubyFloat)object).getDoubleValue();
            if (val >= 9.223372036854776E18 || val < -9.223372036854776E18) {
                return RubyNumeric.dbl2num(context.getRuntime(), ((RubyFloat)object).getDoubleValue());
            }
        }
        else {
            if (object instanceof RubyFixnum || object instanceof RubyBignum) {
                return object;
            }
            if (object instanceof RubyString) {
                return RubyNumeric.str2inum(context.getRuntime(), (RubyString)object, 0, true);
            }
        }
        final IRubyObject tmp = TypeConverter.convertToType(object, context.getRuntime().getInteger(), "to_int", false);
        if (tmp.isNil()) {
            return object.convertToInteger("to_i");
        }
        return tmp;
    }
    
    @JRubyMethod(name = { "Integer" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static IRubyObject new_integer19(final ThreadContext context, final IRubyObject recv, final IRubyObject object) {
        if (object instanceof RubyFloat) {
            final double val = ((RubyFloat)object).getDoubleValue();
            if (val > 9.223372036854776E18 && val < -9.223372036854776E18) {
                return RubyNumeric.dbl2num(context.getRuntime(), ((RubyFloat)object).getDoubleValue());
            }
        }
        else {
            if (object instanceof RubyFixnum || object instanceof RubyBignum) {
                return object;
            }
            if (object instanceof RubyString) {
                return RubyNumeric.str2inum(context.getRuntime(), (RubyString)object, 0, true);
            }
            if (object instanceof RubyNil) {
                throw context.getRuntime().newTypeError("can't convert nil into Integer");
            }
        }
        final IRubyObject tmp = TypeConverter.convertToType(object, context.getRuntime().getInteger(), "to_int", false);
        if (tmp.isNil()) {
            return object.convertToInteger("to_i");
        }
        return tmp;
    }
    
    @JRubyMethod(name = { "Integer" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static IRubyObject new_integer19(final ThreadContext context, final IRubyObject recv, final IRubyObject object, final IRubyObject base) {
        final int bs = RubyNumeric.num2int(base);
        if (object instanceof RubyString) {
            return RubyNumeric.str2inum(context.getRuntime(), (RubyString)object, bs, true);
        }
        final IRubyObject tmp = object.checkStringType();
        if (!tmp.isNil()) {
            return RubyNumeric.str2inum(context.getRuntime(), (RubyString)tmp, bs, true);
        }
        throw context.getRuntime().newArgumentError("base specified for non string value");
    }
    
    @JRubyMethod(name = { "String" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static IRubyObject new_string(final ThreadContext context, final IRubyObject recv, final IRubyObject object) {
        return TypeConverter.convertToType(object, context.getRuntime().getString(), "to_s");
    }
    
    @JRubyMethod(name = { "String" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static IRubyObject new_string19(final ThreadContext context, final IRubyObject recv, final IRubyObject object) {
        return TypeConverter.convertToType19(object, context.getRuntime().getString(), "to_s");
    }
    
    @JRubyMethod(name = { "p" }, rest = true, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject p(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        final IRubyObject defout = runtime.getGlobalVariables().get("$>");
        for (int i = 0; i < args.length; ++i) {
            if (args[i] != null) {
                defout.callMethod(context, "write", RubyObject.inspect(context, args[i]));
                defout.callMethod(context, "write", runtime.newString("\n"));
            }
        }
        IRubyObject result = runtime.getNil();
        if (runtime.is1_9()) {
            if (args.length == 1) {
                result = args[0];
            }
            else if (args.length > 1) {
                result = runtime.newArray(args);
            }
        }
        if (defout instanceof RubyFile) {
            ((RubyFile)defout).flush();
        }
        return result;
    }
    
    @JRubyMethod(name = { "public_method" }, required = 1, module = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject public_method(final ThreadContext context, final IRubyObject recv, final IRubyObject symbol) {
        return recv.getMetaClass().newMethod(recv, symbol.asJavaString(), true, Visibility.PUBLIC, true, false);
    }
    
    @JRubyMethod(name = { "putc" }, required = 1, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject putc(final ThreadContext context, final IRubyObject recv, final IRubyObject ch) {
        final IRubyObject defout = context.getRuntime().getGlobalVariables().get("$>");
        return RubyIO.putc(context, defout, ch);
    }
    
    @JRubyMethod(name = { "puts" }, rest = true, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject puts(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        final IRubyObject defout = context.getRuntime().getGlobalVariables().get("$>");
        return RubyIO.puts(context, defout, args);
    }
    
    @JRubyMethod(name = { "print" }, rest = true, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject print(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        final IRubyObject defout = context.getRuntime().getGlobalVariables().get("$>");
        return RubyIO.print(context, defout, args);
    }
    
    @JRubyMethod(name = { "printf" }, rest = true, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject printf(final ThreadContext context, final IRubyObject recv, IRubyObject[] args) {
        if (args.length != 0) {
            IRubyObject defout = context.getRuntime().getGlobalVariables().get("$>");
            if (!(args[0] instanceof RubyString)) {
                defout = args[0];
                args = ArgsUtil.popArray(args);
            }
            defout.callMethod(context, "write", sprintf(recv, args));
        }
        return context.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "readline" }, optional = 1, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject readline(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        final IRubyObject line = gets(context, recv, args);
        if (line.isNil()) {
            throw context.getRuntime().newEOFError();
        }
        return line;
    }
    
    @JRubyMethod(name = { "readlines" }, optional = 1, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject readlines(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        return RubyArgsFile.readlines(context, context.getRuntime().getArgsFile(), args);
    }
    
    @JRubyMethod(name = { "respond_to_missing?" }, module = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject respond_to_missing_p(final ThreadContext context, final IRubyObject recv, final IRubyObject symbol) {
        return context.getRuntime().getFalse();
    }
    
    @JRubyMethod(name = { "respond_to_missing?" }, module = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject respond_to_missing_p(final ThreadContext context, final IRubyObject recv, final IRubyObject symbol, final IRubyObject isPrivate) {
        return context.getRuntime().getFalse();
    }
    
    private static RubyString getLastlineString(final ThreadContext context, final Ruby runtime) {
        final IRubyObject line = context.getCurrentScope().getLastLine(runtime);
        if (line.isNil()) {
            throw runtime.newTypeError("$_ value need to be String (nil given).");
        }
        if (!(line instanceof RubyString)) {
            throw runtime.newTypeError("$_ value need to be String (" + line.getMetaClass().getName() + " given).");
        }
        return (RubyString)line;
    }
    
    public static IRubyObject sub_bang(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        return getLastlineString(context, context.getRuntime()).sub_bang(context, args, block);
    }
    
    @JRubyMethod(name = { "sub!" }, module = true, visibility = Visibility.PRIVATE, reads = { FrameField.LASTLINE }, compat = CompatVersion.RUBY1_8)
    public static IRubyObject sub_bang(final ThreadContext context, final IRubyObject recv, final IRubyObject arg0, final Block block) {
        return getLastlineString(context, context.getRuntime()).sub_bang(context, arg0, block);
    }
    
    @JRubyMethod(name = { "sub!" }, module = true, visibility = Visibility.PRIVATE, reads = { FrameField.LASTLINE }, compat = CompatVersion.RUBY1_8)
    public static IRubyObject sub_bang(final ThreadContext context, final IRubyObject recv, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        return getLastlineString(context, context.getRuntime()).sub_bang(context, arg0, arg1, block);
    }
    
    public static IRubyObject sub(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        final RubyString str = (RubyString)getLastlineString(context, context.getRuntime()).dup();
        if (!str.sub_bang(context, args, block).isNil()) {
            context.getCurrentScope().setLastLine(str);
        }
        return str;
    }
    
    @JRubyMethod(name = { "sub" }, module = true, visibility = Visibility.PRIVATE, reads = { FrameField.LASTLINE }, writes = { FrameField.LASTLINE }, compat = CompatVersion.RUBY1_8)
    public static IRubyObject sub(final ThreadContext context, final IRubyObject recv, final IRubyObject arg0, final Block block) {
        final RubyString str = (RubyString)getLastlineString(context, context.getRuntime()).dup();
        if (!str.sub_bang(context, arg0, block).isNil()) {
            context.getCurrentScope().setLastLine(str);
        }
        return str;
    }
    
    @JRubyMethod(name = { "sub" }, module = true, visibility = Visibility.PRIVATE, reads = { FrameField.LASTLINE }, writes = { FrameField.LASTLINE }, compat = CompatVersion.RUBY1_8)
    public static IRubyObject sub(final ThreadContext context, final IRubyObject recv, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        final RubyString str = (RubyString)getLastlineString(context, context.getRuntime()).dup();
        if (!str.sub_bang(context, arg0, arg1, block).isNil()) {
            context.getCurrentScope().setLastLine(str);
        }
        return str;
    }
    
    public static IRubyObject gsub_bang(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        return getLastlineString(context, context.getRuntime()).gsub_bang(context, args, block);
    }
    
    @JRubyMethod(name = { "gsub!" }, module = true, visibility = Visibility.PRIVATE, reads = { FrameField.LASTLINE }, writes = { FrameField.LASTLINE }, compat = CompatVersion.RUBY1_8)
    public static IRubyObject gsub_bang(final ThreadContext context, final IRubyObject recv, final IRubyObject arg0, final Block block) {
        return getLastlineString(context, context.getRuntime()).gsub_bang(context, arg0, block);
    }
    
    @JRubyMethod(name = { "gsub!" }, module = true, visibility = Visibility.PRIVATE, reads = { FrameField.LASTLINE }, writes = { FrameField.LASTLINE }, compat = CompatVersion.RUBY1_8)
    public static IRubyObject gsub_bang(final ThreadContext context, final IRubyObject recv, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        return getLastlineString(context, context.getRuntime()).gsub_bang(context, arg0, arg1, block);
    }
    
    public static IRubyObject gsub(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        final RubyString str = (RubyString)getLastlineString(context, context.getRuntime()).dup();
        if (!str.gsub_bang(context, args, block).isNil()) {
            context.getCurrentScope().setLastLine(str);
        }
        return str;
    }
    
    @JRubyMethod(module = true, visibility = Visibility.PRIVATE, reads = { FrameField.LASTLINE }, writes = { FrameField.LASTLINE }, compat = CompatVersion.RUBY1_8)
    public static IRubyObject gsub(final ThreadContext context, final IRubyObject recv, final IRubyObject arg0, final Block block) {
        final RubyString str = (RubyString)getLastlineString(context, context.getRuntime()).dup();
        if (!str.gsub_bang(context, arg0, block).isNil()) {
            context.getCurrentScope().setLastLine(str);
        }
        return str;
    }
    
    @JRubyMethod(module = true, visibility = Visibility.PRIVATE, reads = { FrameField.LASTLINE }, writes = { FrameField.LASTLINE }, compat = CompatVersion.RUBY1_8)
    public static IRubyObject gsub(final ThreadContext context, final IRubyObject recv, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        final RubyString str = (RubyString)getLastlineString(context, context.getRuntime()).dup();
        if (!str.gsub_bang(context, arg0, arg1, block).isNil()) {
            context.getCurrentScope().setLastLine(str);
        }
        return str;
    }
    
    @JRubyMethod(name = { "chop!" }, module = true, visibility = Visibility.PRIVATE, reads = { FrameField.LASTLINE }, writes = { FrameField.LASTLINE }, compat = CompatVersion.RUBY1_8)
    public static IRubyObject chop_bang(final ThreadContext context, final IRubyObject recv, final Block block) {
        return getLastlineString(context, context.getRuntime()).chop_bang(context);
    }
    
    @JRubyMethod(module = true, visibility = Visibility.PRIVATE, reads = { FrameField.LASTLINE }, writes = { FrameField.LASTLINE }, compat = CompatVersion.RUBY1_8)
    public static IRubyObject chop(final ThreadContext context, final IRubyObject recv, final Block block) {
        RubyString str = getLastlineString(context, context.getRuntime());
        if (str.getByteList().getRealSize() > 0) {
            str = (RubyString)str.dup();
            str.chop_bang(context);
            context.getCurrentScope().setLastLine(str);
        }
        return str;
    }
    
    public static IRubyObject chomp_bang(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        return getLastlineString(context, context.getRuntime()).chomp_bang(args);
    }
    
    @JRubyMethod(name = { "chomp!" }, module = true, visibility = Visibility.PRIVATE, reads = { FrameField.LASTLINE }, writes = { FrameField.LASTLINE }, compat = CompatVersion.RUBY1_8)
    public static IRubyObject chomp_bang(final ThreadContext context, final IRubyObject recv) {
        return getLastlineString(context, context.getRuntime()).chomp_bang(context);
    }
    
    @JRubyMethod(name = { "chomp!" }, module = true, visibility = Visibility.PRIVATE, reads = { FrameField.LASTLINE }, writes = { FrameField.LASTLINE }, compat = CompatVersion.RUBY1_8)
    public static IRubyObject chomp_bang(final ThreadContext context, final IRubyObject recv, final IRubyObject arg0) {
        return getLastlineString(context, context.getRuntime()).chomp_bang(context, arg0);
    }
    
    @JRubyMethod(module = true, visibility = Visibility.PRIVATE, reads = { FrameField.LASTLINE }, writes = { FrameField.LASTLINE }, compat = CompatVersion.RUBY1_8)
    public static IRubyObject chomp(final ThreadContext context, final IRubyObject recv) {
        final RubyString str = getLastlineString(context, context.getRuntime());
        final RubyString dup = (RubyString)str.dup();
        if (dup.chomp_bang(context).isNil()) {
            return str;
        }
        context.getCurrentScope().setLastLine(dup);
        return dup;
    }
    
    @JRubyMethod(module = true, visibility = Visibility.PRIVATE, reads = { FrameField.LASTLINE }, writes = { FrameField.LASTLINE }, compat = CompatVersion.RUBY1_8)
    public static IRubyObject chomp(final ThreadContext context, final IRubyObject recv, final IRubyObject arg0) {
        final RubyString str = getLastlineString(context, context.getRuntime());
        final RubyString dup = (RubyString)str.dup();
        if (dup.chomp_bang(context, arg0).isNil()) {
            return str;
        }
        context.getCurrentScope().setLastLine(dup);
        return dup;
    }
    
    public static IRubyObject split(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        return getLastlineString(context, context.getRuntime()).split(context, args);
    }
    
    @JRubyMethod(module = true, visibility = Visibility.PRIVATE, reads = { FrameField.LASTLINE }, writes = { FrameField.LASTLINE, FrameField.BACKREF }, compat = CompatVersion.RUBY1_8)
    public static IRubyObject split(final ThreadContext context, final IRubyObject recv) {
        return getLastlineString(context, context.getRuntime()).split(context);
    }
    
    @JRubyMethod(module = true, visibility = Visibility.PRIVATE, reads = { FrameField.LASTLINE }, writes = { FrameField.LASTLINE, FrameField.BACKREF }, compat = CompatVersion.RUBY1_8)
    public static IRubyObject split(final ThreadContext context, final IRubyObject recv, final IRubyObject arg0) {
        return getLastlineString(context, context.getRuntime()).split(context, arg0);
    }
    
    @JRubyMethod(module = true, visibility = Visibility.PRIVATE, reads = { FrameField.LASTLINE }, writes = { FrameField.LASTLINE, FrameField.BACKREF }, compat = CompatVersion.RUBY1_8)
    public static IRubyObject split(final ThreadContext context, final IRubyObject recv, final IRubyObject arg0, final IRubyObject arg1) {
        return getLastlineString(context, context.getRuntime()).split(context, arg0, arg1);
    }
    
    @JRubyMethod(module = true, visibility = Visibility.PRIVATE, reads = { FrameField.LASTLINE, FrameField.BACKREF }, writes = { FrameField.LASTLINE, FrameField.BACKREF }, compat = CompatVersion.RUBY1_8)
    public static IRubyObject scan(final ThreadContext context, final IRubyObject recv, final IRubyObject pattern, final Block block) {
        return getLastlineString(context, context.getRuntime()).scan(context, pattern, block);
    }
    
    @JRubyMethod(required = 1, optional = 3, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject select(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        return RubyIO.select_static(context, context.getRuntime(), args);
    }
    
    @JRubyMethod(optional = 1, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject sleep(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        long milliseconds;
        if (args.length == 0) {
            milliseconds = 0L;
        }
        else {
            if (!(args[0] instanceof RubyNumeric)) {
                throw context.getRuntime().newTypeError("can't convert " + args[0].getMetaClass().getName() + "into time interval");
            }
            milliseconds = (long)(args[0].convertToFloat().getDoubleValue() * 1000.0);
            if (milliseconds < 0L) {
                throw context.getRuntime().newArgumentError("time interval must be positive");
            }
            if (milliseconds == 0L) {
                return context.getRuntime().newFixnum(0);
            }
        }
        final long startTime = System.currentTimeMillis();
        final RubyThread rubyThread = context.getThread();
        do {
            final long loopStartTime = System.currentTimeMillis();
            try {
                if (!rubyThread.sleep(milliseconds)) {
                    break;
                }
            }
            catch (InterruptedException ex) {}
            milliseconds -= System.currentTimeMillis() - loopStartTime;
        } while (milliseconds > 0L);
        return context.getRuntime().newFixnum(Math.round((System.currentTimeMillis() - startTime) / 1000.0));
    }
    
    @JRubyMethod(name = { "exit" }, optional = 1, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject exit(final IRubyObject recv, final IRubyObject[] args) {
        exit(recv.getRuntime(), args, false);
        return recv.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "exit!" }, optional = 1, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject exit_bang(final IRubyObject recv, final IRubyObject[] args) {
        exit(recv.getRuntime(), args, true);
        return recv.getRuntime().getNil();
    }
    
    private static void exit(final Ruby runtime, final IRubyObject[] args, final boolean hard) {
        runtime.secure(4);
        int status = hard ? 1 : 0;
        if (args.length > 0) {
            final RubyObject argument = (RubyObject)args[0];
            if (argument instanceof RubyBoolean) {
                status = (argument.isFalse() ? 1 : 0);
            }
            else {
                status = RubyNumeric.fix2int(argument);
            }
        }
        if (!hard) {
            throw runtime.newSystemExit(status);
        }
        if (runtime.getInstanceConfig().isHardExit()) {
            System.exit(status);
            return;
        }
        throw new MainExitException(status, true);
    }
    
    @JRubyMethod(name = { "global_variables" }, module = true, visibility = Visibility.PRIVATE)
    public static RubyArray global_variables(final ThreadContext context, final IRubyObject recv) {
        final Ruby runtime = context.getRuntime();
        final RubyArray globalVariables = runtime.newArray();
        for (final String globalVariableName : runtime.getGlobalVariables().getNames()) {
            globalVariables.append(runtime.newString(globalVariableName));
        }
        return globalVariables;
    }
    
    @JRubyMethod(name = { "global_variables" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static RubyArray global_variables19(final ThreadContext context, final IRubyObject recv) {
        final Ruby runtime = context.getRuntime();
        final RubyArray globalVariables = runtime.newArray();
        for (final String globalVariableName : runtime.getGlobalVariables().getNames()) {
            globalVariables.append(runtime.newSymbol(globalVariableName));
        }
        return globalVariables;
    }
    
    @JRubyMethod(name = { "local_variables" }, module = true, visibility = Visibility.PRIVATE)
    public static RubyArray local_variables(final ThreadContext context, final IRubyObject recv) {
        final Ruby runtime = context.getRuntime();
        final RubyArray localVariables = runtime.newArray();
        for (final String name : context.getCurrentScope().getAllNamesInScope()) {
            if (IdUtil.isLocal(name)) {
                localVariables.append(runtime.newString(name));
            }
        }
        return localVariables;
    }
    
    @JRubyMethod(name = { "local_variables" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static RubyArray local_variables19(final ThreadContext context, final IRubyObject recv) {
        final Ruby runtime = context.getRuntime();
        final RubyArray localVariables = runtime.newArray();
        for (final String name : context.getCurrentScope().getAllNamesInScope()) {
            if (IdUtil.isLocal(name)) {
                localVariables.append(runtime.newSymbol(name));
            }
        }
        return localVariables;
    }
    
    @JRubyMethod(module = true, visibility = Visibility.PRIVATE)
    public static RubyBinding binding(final ThreadContext context, final IRubyObject recv, final Block block) {
        return RubyBinding.newBinding(context.getRuntime(), context.currentBinding(recv));
    }
    
    @JRubyMethod(name = { "binding" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static RubyBinding binding19(final ThreadContext context, final IRubyObject recv, final Block block) {
        return RubyBinding.newBinding(context.getRuntime(), context.currentBinding());
    }
    
    @JRubyMethod(name = { "block_given?", "iterator?" }, module = true, visibility = Visibility.PRIVATE)
    public static RubyBoolean block_given_p(final ThreadContext context, final IRubyObject recv) {
        return context.getRuntime().newBoolean(context.getCurrentFrame().getBlock().isGiven());
    }
    
    @Deprecated
    public static IRubyObject sprintf(final IRubyObject recv, final IRubyObject[] args) {
        return sprintf(recv.getRuntime().getCurrentContext(), recv, args);
    }
    
    @JRubyMethod(name = { "sprintf", "format" }, required = 1, rest = true, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject sprintf(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        if (args.length == 0) {
            throw context.getRuntime().newArgumentError("sprintf must have at least one argument");
        }
        final RubyString str = RubyString.stringValue(args[0]);
        final RubyArray newArgs = context.getRuntime().newArrayNoCopy(args);
        newArgs.shift(context);
        return str.op_format(context, newArgs);
    }
    
    @JRubyMethod(name = { "raise", "fail" }, optional = 3, module = true, visibility = Visibility.PRIVATE, omit = true)
    public static IRubyObject raise(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        final Ruby runtime = context.getRuntime();
        RaiseException raise = null;
        switch (args.length) {
            case 0: {
                final IRubyObject lastException = runtime.getGlobalVariables().get("$!");
                if (lastException.isNil()) {
                    raise = new RaiseException(runtime, runtime.getRuntimeError(), "", false);
                    break;
                }
                raise = new RaiseException((RubyException)lastException);
                break;
            }
            case 1: {
                if (args[0] instanceof RubyString) {
                    raise = new RaiseException((RubyException)runtime.getRuntimeError().newInstance(context, args, block));
                    break;
                }
                raise = new RaiseException(convertToException(runtime, args[0], null));
                break;
            }
            case 2: {
                raise = new RaiseException(convertToException(runtime, args[0], args[1]));
                break;
            }
            default: {
                raise = new RaiseException(convertToException(runtime, args[0], args[1]), args[2]);
                break;
            }
        }
        if (runtime.getDebug().isTrue()) {
            printExceptionSummary(context, runtime, raise.getException());
        }
        throw raise;
    }
    
    private static RubyException convertToException(final Ruby runtime, final IRubyObject obj, final IRubyObject optionalMessage) {
        if (!obj.respondsTo("exception")) {
            throw runtime.newTypeError("exception class/object expected");
        }
        IRubyObject exception;
        if (optionalMessage == null) {
            exception = obj.callMethod(runtime.getCurrentContext(), "exception");
        }
        else {
            exception = obj.callMethod(runtime.getCurrentContext(), "exception", optionalMessage);
        }
        try {
            return (RubyException)exception;
        }
        catch (ClassCastException cce) {
            throw runtime.newTypeError("exception object expected");
        }
    }
    
    private static void printExceptionSummary(final ThreadContext context, final Ruby runtime, final RubyException rEx) {
        final RubyStackTraceElement[] elements = rEx.getBacktraceElements();
        final RubyStackTraceElement firstElement = elements[0];
        final String msg = String.format("Exception `%s' at %s:%s - %s\n", rEx.getMetaClass(), firstElement.getFileName(), firstElement.getLineNumber(), runtime.is1_9() ? TypeConverter.convertToType(rEx, runtime.getString(), "to_s") : rEx.convertToString().toString());
        runtime.getErrorStream().print(msg);
    }
    
    @JRubyMethod(module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static IRubyObject require(final IRubyObject recv, final IRubyObject name, final Block block) {
        return requireCommon(recv.getRuntime(), recv, name, block);
    }
    
    @JRubyMethod(name = { "require" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static IRubyObject require19(final ThreadContext context, final IRubyObject recv, IRubyObject name, final Block block) {
        final Ruby runtime = context.getRuntime();
        final IRubyObject tmp = name.checkStringType();
        if (!tmp.isNil()) {
            return requireCommon(runtime, recv, tmp, block);
        }
        return requireCommon(runtime, recv, name.respondsTo("to_path") ? name.callMethod(context, "to_path") : name, block);
    }
    
    private static IRubyObject requireCommon(final Ruby runtime, final IRubyObject recv, final IRubyObject name, final Block block) {
        if (runtime.getLoadService().lockAndRequire(name.convertToString().toString())) {
            return runtime.getTrue();
        }
        return runtime.getFalse();
    }
    
    @JRubyMethod(required = 1, optional = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static IRubyObject load(final IRubyObject recv, final IRubyObject[] args, final Block block) {
        return loadCommon(args[0], recv.getRuntime(), args, block);
    }
    
    @JRubyMethod(name = { "load" }, required = 1, optional = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static IRubyObject load19(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        IRubyObject file = args[0];
        if (!(file instanceof RubyString) && file.respondsTo("to_path")) {
            file = file.callMethod(context, "to_path");
        }
        return loadCommon(file, context.getRuntime(), args, block);
    }
    
    private static IRubyObject loadCommon(final IRubyObject fileName, final Ruby runtime, final IRubyObject[] args, final Block block) {
        final RubyString file = fileName.convertToString();
        final boolean wrap = args.length == 2 && args[1].isTrue();
        runtime.getLoadService().load(file.toString(), wrap);
        return runtime.getTrue();
    }
    
    @JRubyMethod(required = 1, optional = 3, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static IRubyObject eval(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        return evalCommon(context, recv, args, block, RubyKernel.evalBinding18);
    }
    
    @JRubyMethod(name = { "eval" }, required = 1, optional = 3, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static IRubyObject eval19(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        return evalCommon(context, recv, args, block, RubyKernel.evalBinding19);
    }
    
    private static IRubyObject evalCommon(final ThreadContext context, IRubyObject recv, final IRubyObject[] args, final Block block, final EvalBinding evalBinding) {
        final Ruby runtime = context.getRuntime();
        final RubyString src = args[0].convertToString();
        runtime.checkSafeString(src);
        final boolean bindingGiven = args.length > 1 && !args[1].isNil();
        final Binding binding = bindingGiven ? evalBinding.convertToBinding(args[1]) : context.currentBinding();
        if (args.length > 2) {
            binding.setFile(args[2].convertToString().toString());
        }
        else if (!bindingGiven) {
            binding.setFile("(eval)");
        }
        if (args.length > 3) {
            binding.setLine((int)args[3].convertToInteger().getLongValue() - 1);
        }
        else if (!bindingGiven) {
            binding.setLine(0);
        }
        final String frameName = context.getFrameName();
        if (frameName != null) {
            binding.setMethod(frameName);
        }
        if (bindingGiven) {
            recv = binding.getSelf();
        }
        return ASTInterpreter.evalWithBinding(context, recv, src, binding);
    }
    
    @JRubyMethod(module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject callcc(final ThreadContext context, final IRubyObject recv, final Block block) {
        final RubyContinuation continuation = new RubyContinuation(context.getRuntime());
        return continuation.enter(context, continuation, block);
    }
    
    @JRubyMethod(optional = 1, module = true, visibility = Visibility.PRIVATE, omit = true)
    public static IRubyObject caller(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        final int level = (args.length > 0) ? RubyNumeric.fix2int(args[0]) : 1;
        if (level < 0) {
            throw context.getRuntime().newArgumentError("negative level (" + level + ')');
        }
        return context.createCallerBacktrace(context.getRuntime(), level);
    }
    
    @JRubyMethod(name = { "catch" }, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject rbCatch(final ThreadContext context, final IRubyObject recv, final IRubyObject tag, final Block block) {
        final Ruby runtime = context.runtime;
        final RubyContinuation rbContinuation = new RubyContinuation(runtime, stringOrSymbol(tag));
        try {
            context.pushCatch(rbContinuation.getContinuation());
            return rbContinuation.enter(context, rbContinuation, block);
        }
        finally {
            context.popCatch();
        }
    }
    
    @JRubyMethod(name = { "catch" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static IRubyObject rbCatch19(final ThreadContext context, final IRubyObject recv, final Block block) {
        final IRubyObject tag = new RubyObject(context.runtime.getObject());
        return rbCatch19Common(context, tag, block, true);
    }
    
    @JRubyMethod(name = { "catch" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static IRubyObject rbCatch19(final ThreadContext context, final IRubyObject recv, final IRubyObject tag, final Block block) {
        return rbCatch19Common(context, tag, block, false);
    }
    
    private static IRubyObject rbCatch19Common(final ThreadContext context, final IRubyObject tag, final Block block, final boolean yieldTag) {
        final RubyContinuation rbContinuation = new RubyContinuation(context.getRuntime(), tag);
        try {
            context.pushCatch(rbContinuation.getContinuation());
            return rbContinuation.enter(context, yieldTag ? tag : rbContinuation, block);
        }
        finally {
            context.popCatch();
        }
    }
    
    @JRubyMethod(name = { "throw" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static IRubyObject rbThrow(final ThreadContext context, final IRubyObject recv, final IRubyObject tag, final Block block) {
        return rbThrowInternal(context, stringOrSymbol(tag), IRubyObject.NULL_ARRAY, block, RubyKernel.uncaught18);
    }
    
    @JRubyMethod(name = { "throw" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static IRubyObject rbThrow(final ThreadContext context, final IRubyObject recv, final IRubyObject tag, final IRubyObject arg, final Block block) {
        return rbThrowInternal(context, stringOrSymbol(tag), new IRubyObject[] { arg }, block, RubyKernel.uncaught18);
    }
    
    private static RubySymbol stringOrSymbol(final IRubyObject obj) {
        if (obj instanceof RubySymbol) {
            return (RubySymbol)obj;
        }
        return RubySymbol.newSymbol(obj.getRuntime(), obj.asJavaString().intern());
    }
    
    @JRubyMethod(name = { "throw" }, frame = true, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static IRubyObject rbThrow19(final ThreadContext context, final IRubyObject recv, final IRubyObject tag, final Block block) {
        return rbThrowInternal(context, tag, IRubyObject.NULL_ARRAY, block, RubyKernel.uncaught19);
    }
    
    @JRubyMethod(name = { "throw" }, frame = true, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static IRubyObject rbThrow19(final ThreadContext context, final IRubyObject recv, final IRubyObject tag, final IRubyObject arg, final Block block) {
        return rbThrowInternal(context, tag, new IRubyObject[] { arg }, block, RubyKernel.uncaught19);
    }
    
    private static IRubyObject rbThrowInternal(final ThreadContext context, final IRubyObject tag, final IRubyObject[] args, final Block block, final Uncaught uncaught) {
        final Ruby runtime = context.getRuntime();
        final RubyContinuation.Continuation continuation = context.getActiveCatch(tag);
        if (continuation != null) {
            continuation.args = args;
            throw continuation;
        }
        String message = "uncaught throw `" + tag + "'";
        final RubyThread currentThread = context.getThread();
        if (currentThread == runtime.getThreadService().getMainThread()) {
            throw uncaught.uncaughtThrow(runtime, message, tag);
        }
        message = message + " in thread 0x" + Integer.toHexString(RubyNumeric.fix2int(currentThread.id()));
        if (runtime.is1_9()) {
            throw runtime.newArgumentError(message);
        }
        throw runtime.newThreadError(message);
    }
    
    @JRubyMethod(required = 1, optional = 1, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject trap(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        context.getRuntime().getLoadService().require("jsignal_internal");
        return RuntimeHelpers.invoke(context, recv, "__jtrap", args, block);
    }
    
    @JRubyMethod(module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject warn(final ThreadContext context, final IRubyObject recv, final IRubyObject message) {
        final Ruby runtime = context.getRuntime();
        if (runtime.warningsEnabled()) {
            final IRubyObject out = runtime.getGlobalVariables().get("$stderr");
            RuntimeHelpers.invoke(context, out, "write", message);
            RuntimeHelpers.invoke(context, out, "write", runtime.getGlobalVariables().getDefaultSeparator());
        }
        return runtime.getNil();
    }
    
    @JRubyMethod(module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject set_trace_func(final ThreadContext context, final IRubyObject recv, final IRubyObject trace_func, final Block block) {
        if (trace_func.isNil()) {
            context.getRuntime().setTraceFunction(null);
        }
        else {
            if (!(trace_func instanceof RubyProc)) {
                throw context.getRuntime().newTypeError("trace_func needs to be Proc.");
            }
            context.getRuntime().setTraceFunction((RubyProc)trace_func);
        }
        return trace_func;
    }
    
    @JRubyMethod(required = 1, optional = 1, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject trace_var(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        if (args.length == 0) {
            throw context.getRuntime().newArgumentError(0, 1);
        }
        RubyProc proc = null;
        final String var = (args.length > 1) ? args[0].toString() : null;
        if (var.charAt(0) != '$') {
            return context.getRuntime().getNil();
        }
        if (args.length == 1) {
            proc = RubyProc.newProc(context.getRuntime(), block, Block.Type.PROC);
        }
        if (args.length == 2) {
            proc = (RubyProc)TypeConverter.convertToType(args[1], context.getRuntime().getProc(), "to_proc", true);
        }
        context.getRuntime().getGlobalVariables().setTraceVar(var, proc);
        return context.getRuntime().getNil();
    }
    
    @JRubyMethod(required = 1, optional = 1, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject untrace_var(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        if (args.length == 0) {
            throw context.getRuntime().newArgumentError(0, 1);
        }
        final String var = (args.length >= 1) ? args[0].toString() : null;
        if (var.charAt(0) != '$') {
            return context.getRuntime().getNil();
        }
        if (args.length > 1) {
            final ArrayList<IRubyObject> success = new ArrayList<IRubyObject>();
            for (int i = 1; i < args.length; ++i) {
                if (context.getRuntime().getGlobalVariables().untraceVar(var, args[i])) {
                    success.add(args[i]);
                }
            }
            return RubyArray.newArray(context.getRuntime(), success);
        }
        context.getRuntime().getGlobalVariables().untraceVar(var);
        return context.getRuntime().getNil();
    }
    
    @JRubyMethod(module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static IRubyObject singleton_method_added(final ThreadContext context, final IRubyObject recv, final IRubyObject symbolId, final Block block) {
        return context.getRuntime().getNil();
    }
    
    @JRubyMethod(module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static IRubyObject singleton_method_removed(final ThreadContext context, final IRubyObject recv, final IRubyObject symbolId, final Block block) {
        return context.getRuntime().getNil();
    }
    
    @JRubyMethod(module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static IRubyObject singleton_method_undefined(final ThreadContext context, final IRubyObject recv, final IRubyObject symbolId, final Block block) {
        return context.getRuntime().getNil();
    }
    
    @JRubyMethod(required = 1, optional = 1, compat = CompatVersion.RUBY1_9)
    public static IRubyObject define_singleton_method(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        if (args.length == 0) {
            throw context.getRuntime().newArgumentError(0, 1);
        }
        final RubyClass singleton_class = recv.getSingletonClass();
        if (args.length > 1) {
            final IRubyObject arg1 = args[1];
            if (context.runtime.getUnboundMethod().isInstance(args[1])) {
                final RubyUnboundMethod method = (RubyUnboundMethod)arg1;
                final RubyModule owner = (RubyModule)method.owner(context);
                if (owner.isSingleton() && (!recv.getMetaClass().isSingleton() || !recv.getMetaClass().isKindOfModule(owner))) {
                    throw context.runtime.newTypeError("can't bind singleton method to a different class");
                }
            }
            return singleton_class.define_method(context, args[0], args[1], block);
        }
        return singleton_class.define_method(context, args[0], block);
    }
    
    @JRubyMethod(name = { "proc", "lambda" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static RubyProc proc(final ThreadContext context, final IRubyObject recv, final Block block) {
        return context.getRuntime().newProc(Block.Type.LAMBDA, block);
    }
    
    @JRubyMethod(module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static RubyProc lambda(final ThreadContext context, final IRubyObject recv, final Block block) {
        return context.getRuntime().newProc(Block.Type.LAMBDA, block);
    }
    
    @JRubyMethod(name = { "proc" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static RubyProc proc_1_9(final ThreadContext context, final IRubyObject recv, final Block block) {
        return context.getRuntime().newProc(Block.Type.PROC, block);
    }
    
    @JRubyMethod(name = { "loop" }, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject loop(final ThreadContext context, final IRubyObject recv, final Block block) {
        if (context.runtime.is1_9() && !block.isGiven()) {
            return RubyEnumerator.enumeratorize(context.runtime, recv, "loop");
        }
        final IRubyObject nil = context.getRuntime().getNil();
        final RubyClass stopIteration = context.getRuntime().getStopIteration();
        try {
            while (true) {
                block.yieldSpecific(context);
                context.pollThreadEvents();
            }
        }
        catch (RaiseException ex) {
            if (!stopIteration.op_eqq(context, ex.getException()).isTrue()) {
                throw ex;
            }
            return nil;
        }
    }
    
    @JRubyMethod(name = { "test" }, required = 2, optional = 1, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject test(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        if (args.length == 0) {
            throw context.getRuntime().newArgumentError("wrong number of arguments");
        }
        int cmd;
        if (args[0] instanceof RubyFixnum) {
            cmd = (int)((RubyFixnum)args[0]).getLongValue();
        }
        else if (args[0] instanceof RubyString && ((RubyString)args[0]).getByteList().length() > 0) {
            cmd = ((RubyString)args[0]).getByteList().charAt(0);
        }
        else {
            cmd = (int)args[0].convertToInteger().getLongValue();
        }
        switch (cmd) {
            case 45:
            case 60:
            case 61:
            case 62:
            case 65:
            case 67:
            case 71:
            case 77:
            case 79:
            case 82:
            case 83:
            case 87:
            case 88:
            case 98:
            case 99:
            case 100:
            case 101:
            case 102:
            case 103:
            case 107:
            case 108:
            case 111:
            case 112:
            case 114:
            case 115:
            case 117:
            case 119:
            case 120:
            case 122: {
                switch (cmd) {
                    case 45:
                    case 60:
                    case 61:
                    case 62: {
                        if (args.length != 3) {
                            throw context.getRuntime().newArgumentError(args.length, 3);
                        }
                        break;
                    }
                    default: {
                        if (args.length != 2) {
                            throw context.getRuntime().newArgumentError(args.length, 2);
                        }
                        break;
                    }
                }
                switch (cmd) {
                    case 65: {
                        return context.getRuntime().newFileStat(args[1].convertToString().toString(), false).atime();
                    }
                    case 98: {
                        return RubyFileTest.blockdev_p(recv, args[1]);
                    }
                    case 99: {
                        return RubyFileTest.chardev_p(recv, args[1]);
                    }
                    case 67: {
                        return context.getRuntime().newFileStat(args[1].convertToString().toString(), false).ctime();
                    }
                    case 100: {
                        return RubyFileTest.directory_p(recv, args[1]);
                    }
                    case 101: {
                        return RubyFileTest.exist_p(recv, args[1]);
                    }
                    case 102: {
                        return RubyFileTest.file_p(recv, args[1]);
                    }
                    case 103: {
                        return RubyFileTest.setgid_p(recv, args[1]);
                    }
                    case 71: {
                        return RubyFileTest.grpowned_p(recv, args[1]);
                    }
                    case 107: {
                        return RubyFileTest.sticky_p(recv, args[1]);
                    }
                    case 77: {
                        return context.getRuntime().newFileStat(args[1].convertToString().toString(), false).mtime();
                    }
                    case 108: {
                        return RubyFileTest.symlink_p(recv, args[1]);
                    }
                    case 111: {
                        return RubyFileTest.owned_p(recv, args[1]);
                    }
                    case 79: {
                        return RubyFileTest.rowned_p(recv, args[1]);
                    }
                    case 112: {
                        return RubyFileTest.pipe_p(recv, args[1]);
                    }
                    case 114: {
                        return RubyFileTest.readable_p(recv, args[1]);
                    }
                    case 82: {
                        return RubyFileTest.readable_p(recv, args[1]);
                    }
                    case 115: {
                        return RubyFileTest.size_p(recv, args[1]);
                    }
                    case 83: {
                        return RubyFileTest.socket_p(recv, args[1]);
                    }
                    case 117: {
                        return RubyFileTest.setuid_p(recv, args[1]);
                    }
                    case 119: {
                        return RubyFileTest.writable_p(recv, args[1]);
                    }
                    case 87: {
                        return RubyFileTest.writable_p(recv, args[1]);
                    }
                    case 120: {
                        return RubyFileTest.executable_p(recv, args[1]);
                    }
                    case 88: {
                        return RubyFileTest.executable_real_p(recv, args[1]);
                    }
                    case 122: {
                        return RubyFileTest.zero_p(recv, args[1]);
                    }
                    case 61: {
                        return context.getRuntime().newFileStat(args[1].convertToString().toString(), false).mtimeEquals(args[2]);
                    }
                    case 60: {
                        return context.getRuntime().newFileStat(args[1].convertToString().toString(), false).mtimeLessThan(args[2]);
                    }
                    case 62: {
                        return context.getRuntime().newFileStat(args[1].convertToString().toString(), false).mtimeGreaterThan(args[2]);
                    }
                    case 45: {
                        return RubyFileTest.identical_p(recv, args[1], args[2]);
                    }
                    default: {
                        throw new InternalError("unreachable code reached!");
                    }
                }
                break;
            }
            default: {
                throw context.getRuntime().newArgumentError("unknown command ?" + (char)cmd);
            }
        }
    }
    
    @JRubyMethod(name = { "`" }, required = 1, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject backquote(final ThreadContext context, final IRubyObject recv, final IRubyObject aString) {
        final Ruby runtime = context.getRuntime();
        final RubyString string = aString.convertToString();
        final IRubyObject[] args = { string };
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        long[] tuple;
        try {
            tuple = ShellLauncher.runAndWaitPid(runtime, args, output, false);
        }
        catch (Exception e) {
            tuple = new long[] { 127L, -1L };
        }
        context.setLastExitStatus(RubyProcess.RubyStatus.newProcessStatus(runtime, tuple[0], tuple[1]));
        final byte[] out = output.toByteArray();
        int length = out.length;
        if (Platform.IS_WINDOWS) {
            int newPos = 0;
            for (int pos = 0; pos < length; ++pos) {
                final byte curr = out[pos];
                if (pos == length - 1) {
                    out[newPos++] = curr;
                    break;
                }
                final byte next = out[pos + 1];
                if (curr != 13 || next != 10) {
                    out[newPos++] = curr;
                }
            }
            length = newPos;
        }
        return RubyString.newStringNoCopy(runtime, out, 0, length);
    }
    
    @JRubyMethod(name = { "srand" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static RubyInteger srand(final ThreadContext context, final IRubyObject recv) {
        final Ruby runtime = context.getRuntime();
        final long oldRandomSeed = runtime.getRandomSeed();
        runtime.setRandomSeed(System.currentTimeMillis() ^ recv.hashCode() ^ runtime.incrementRandomSeedSequence() ^ runtime.getRandom().nextInt(Math.max(1, Math.abs((int)runtime.getRandomSeed()))));
        runtime.getRandom().setSeed(runtime.getRandomSeed());
        return runtime.newFixnum(oldRandomSeed);
    }
    
    @JRubyMethod(name = { "srand" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static RubyInteger srand(final ThreadContext context, final IRubyObject recv, final IRubyObject arg) {
        final IRubyObject newRandomSeed = arg.convertToInteger("to_int");
        final Ruby runtime = context.getRuntime();
        long seedArg = 0L;
        if (newRandomSeed instanceof RubyBignum) {
            seedArg = ((RubyBignum)newRandomSeed).getValue().longValue();
        }
        else if (!arg.isNil()) {
            seedArg = RubyNumeric.num2long(newRandomSeed);
        }
        final long oldRandomSeed = runtime.getRandomSeed();
        runtime.setRandomSeed(seedArg);
        runtime.getRandom().setSeed(runtime.getRandomSeed());
        return runtime.newFixnum(oldRandomSeed);
    }
    
    @JRubyMethod(name = { "srand" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static IRubyObject srand19(final ThreadContext context, final IRubyObject recv) {
        return RubyRandom.srand(context, recv);
    }
    
    @JRubyMethod(name = { "srand" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static IRubyObject srand19(final ThreadContext context, final IRubyObject recv, final IRubyObject arg) {
        return RubyRandom.srandCommon(context, recv, arg.convertToInteger("to_int"), true);
    }
    
    @JRubyMethod(name = { "rand" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static RubyNumeric rand(final ThreadContext context, final IRubyObject recv) {
        final Ruby runtime = context.getRuntime();
        return RubyFloat.newFloat(runtime, runtime.getRandom().nextDouble());
    }
    
    @JRubyMethod(name = { "rand" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static RubyNumeric rand(final ThreadContext context, final IRubyObject recv, final IRubyObject arg) {
        final Ruby runtime = context.getRuntime();
        final Random random = runtime.getRandom();
        return randCommon(context, runtime, random, recv, arg);
    }
    
    @JRubyMethod(name = { "rand" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static RubyNumeric rand19(final ThreadContext context, final IRubyObject recv) {
        final Ruby runtime = context.getRuntime();
        return RubyFloat.newFloat(runtime, RubyRandom.globalRandom.nextDouble());
    }
    
    @JRubyMethod(name = { "rand" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static RubyNumeric rand19(final ThreadContext context, final IRubyObject recv, final IRubyObject arg) {
        final Ruby runtime = context.getRuntime();
        final Random random = RubyRandom.globalRandom;
        return randCommon(context, runtime, random, recv, arg);
    }
    
    private static RubyNumeric randCommon(final ThreadContext context, final Ruby runtime, final Random random, final IRubyObject recv, final IRubyObject arg) {
        if (arg instanceof RubyBignum) {
            final byte[] bigCeilBytes = ((RubyBignum)arg).getValue().toByteArray();
            final BigInteger bigCeil = new BigInteger(bigCeilBytes).abs();
            final byte[] randBytes = new byte[bigCeilBytes.length];
            random.nextBytes(randBytes);
            final BigInteger result = new BigInteger(randBytes).abs().mod(bigCeil);
            return new RubyBignum(runtime, result);
        }
        final RubyInteger integerCeil = (RubyInteger)new_integer(context, recv, arg);
        final long ceil = Math.abs(integerCeil.getLongValue());
        if (ceil == 0L) {
            return RubyFloat.newFloat(runtime, random.nextDouble());
        }
        if (ceil > 2147483647L) {
            return runtime.newFixnum(Math.abs(random.nextLong()) % ceil);
        }
        return runtime.newFixnum(random.nextInt((int)ceil));
    }
    
    @JRubyMethod(name = { "spawn" }, required = 1, rest = true, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static RubyFixnum spawn(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        final long pid = ShellLauncher.runExternalWithoutWait(runtime, args);
        return RubyFixnum.newFixnum(runtime, pid);
    }
    
    @JRubyMethod(name = { "syscall" }, required = 1, optional = 9, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject syscall(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        throw context.getRuntime().newNotImplementedError("Kernel#syscall is not implemented in JRuby");
    }
    
    @JRubyMethod(name = { "system" }, required = 1, rest = true, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static RubyBoolean system(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        return (systemCommon(context, recv, args) == 0) ? runtime.getTrue() : runtime.getFalse();
    }
    
    @JRubyMethod(name = { "system" }, required = 1, rest = true, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static IRubyObject system19(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        final int resultCode = systemCommon(context, recv, args);
        switch (resultCode) {
            case 0: {
                return runtime.getTrue();
            }
            case 127: {
                return runtime.getNil();
            }
            default: {
                return runtime.getFalse();
            }
        }
    }
    
    private static int systemCommon(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        long[] tuple;
        try {
            if (!Platform.IS_WINDOWS && args[args.length - 1].asJavaString().matches(".*[^&]&\\s*")) {
                ShellLauncher.runWithoutWait(runtime, args);
                return 0;
            }
            tuple = ShellLauncher.runAndWaitPid(runtime, args);
        }
        catch (Exception e) {
            tuple = new long[] { 127L, -1L };
        }
        context.setLastExitStatus(RubyProcess.RubyStatus.newProcessStatus(runtime, tuple[0], tuple[1]));
        return (int)tuple[0];
    }
    
    @JRubyMethod(name = { "exec" }, required = 1, rest = true, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject exec(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        if (args.length == 1 && args[0].convertToString().isEmpty()) {
            throw runtime.newErrnoENOENTError(args[0].convertToString().toString());
        }
        boolean nativeFailed = false;
        int resultCode;
        try {
            try {
                final String[] argv = new String[args.length];
                for (int i = 0; i < args.length; ++i) {
                    argv[i] = args[i].asJavaString();
                }
                resultCode = runtime.getPosix().exec(null, argv);
                nativeFailed = true;
            }
            catch (RaiseException e) {
                resultCode = ShellLauncher.execAndWait(runtime, args);
            }
        }
        catch (RaiseException e) {
            throw e;
        }
        catch (Exception e2) {
            throw runtime.newErrnoENOENTError("cannot execute");
        }
        if (nativeFailed) {
            throw runtime.newErrnoENOENTError("cannot execute");
        }
        exit(runtime, new IRubyObject[] { runtime.newFixnum(resultCode) }, true);
        return runtime.getNil();
    }
    
    @JRubyMethod(name = { "fork" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static IRubyObject fork(final ThreadContext context, final IRubyObject recv, final Block block) {
        final Ruby runtime = context.getRuntime();
        throw runtime.newNotImplementedError("fork is not available on this platform");
    }
    
    @JRubyMethod(name = { "fork" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9, notImplemented = true)
    public static IRubyObject fork19(final ThreadContext context, final IRubyObject recv, final Block block) {
        final Ruby runtime = context.getRuntime();
        throw runtime.newNotImplementedError("fork is not available on this platform");
    }
    
    @JRubyMethod(module = true)
    public static IRubyObject tap(final ThreadContext context, final IRubyObject recv, final Block block) {
        block.yield(context, recv);
        return recv;
    }
    
    @JRubyMethod(name = { "to_enum", "enum_for" }, rest = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject to_enum(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        switch (args.length) {
            case 0: {
                return RubyEnumerator.enumeratorize(runtime, recv, "each");
            }
            case 1: {
                return RubyEnumerator.enumeratorize(runtime, recv, args[0].asJavaString());
            }
            case 2: {
                return RubyEnumerator.enumeratorize(runtime, recv, args[0].asJavaString(), args[1]);
            }
            default: {
                final IRubyObject[] enumArgs = new IRubyObject[args.length - 1];
                System.arraycopy(args, 1, enumArgs, 0, enumArgs.length);
                return RubyEnumerator.enumeratorize(runtime, recv, args[0].asJavaString(), enumArgs);
            }
        }
    }
    
    @JRubyMethod(name = { "__method__", "__callee__" }, module = true, visibility = Visibility.PRIVATE, reads = { FrameField.METHODNAME }, omit = true)
    public static IRubyObject __method__(final ThreadContext context, final IRubyObject recv) {
        final String frameName = context.getFrameName();
        if (frameName == null) {
            return context.nil;
        }
        return context.runtime.newSymbol(frameName);
    }
    
    @JRubyMethod(module = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject singleton_class(final IRubyObject recv) {
        return recv.getSingletonClass();
    }
    
    @JRubyMethod(rest = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject public_send(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        recv.getMetaClass().checkMethodBound(context, args, Visibility.PUBLIC);
        return ((RubyObject)recv).send19(context, args, Block.NULL_BLOCK);
    }
    
    @JRubyMethod(name = { "==" }, required = 1, compat = CompatVersion.RUBY1_8)
    public static IRubyObject op_equal(final ThreadContext context, final IRubyObject self, final IRubyObject other) {
        return ((RubyBasicObject)self).op_equal(context, other);
    }
    
    @JRubyMethod(name = { "equal?" }, required = 1, compat = CompatVersion.RUBY1_8)
    public static IRubyObject equal_p(final ThreadContext context, final IRubyObject self, final IRubyObject other) {
        return ((RubyBasicObject)self).equal_p(context, other);
    }
    
    @JRubyMethod(name = { "eql?" }, required = 1)
    public static IRubyObject eql_p(final IRubyObject self, final IRubyObject obj) {
        return ((RubyBasicObject)self).eql_p(obj);
    }
    
    @JRubyMethod(name = { "===" }, required = 1)
    public static IRubyObject op_eqq(final ThreadContext context, final IRubyObject self, final IRubyObject other) {
        return ((RubyBasicObject)self).op_eqq(context, other);
    }
    
    @JRubyMethod(name = { "<=>" }, required = 1, compat = CompatVersion.RUBY1_9)
    public static IRubyObject op_cmp(final ThreadContext context, final IRubyObject self, final IRubyObject other) {
        return ((RubyBasicObject)self).op_cmp(context, other);
    }
    
    @JRubyMethod(name = { "initialize_copy" }, required = 1, visibility = Visibility.PRIVATE)
    public static IRubyObject initialize_copy(final IRubyObject self, final IRubyObject original) {
        return ((RubyBasicObject)self).initialize_copy(original);
    }
    
    @JRubyMethod(name = { "respond_to?" }, compat = CompatVersion.RUBY1_8)
    public static RubyBoolean respond_to_p(final IRubyObject self, final IRubyObject mname) {
        return ((RubyBasicObject)self).respond_to_p(mname);
    }
    
    @JRubyMethod(name = { "respond_to?" }, compat = CompatVersion.RUBY1_9)
    public static IRubyObject respond_to_p19(final IRubyObject self, final IRubyObject mname) {
        return ((RubyBasicObject)self).respond_to_p19(mname);
    }
    
    @JRubyMethod(name = { "respond_to?" }, compat = CompatVersion.RUBY1_8)
    public static RubyBoolean respond_to_p(final IRubyObject self, final IRubyObject mname, final IRubyObject includePrivate) {
        return ((RubyBasicObject)self).respond_to_p(mname, includePrivate);
    }
    
    @JRubyMethod(name = { "respond_to?" }, compat = CompatVersion.RUBY1_9)
    public static IRubyObject respond_to_p19(final IRubyObject self, final IRubyObject mname, final IRubyObject includePrivate) {
        return ((RubyBasicObject)self).respond_to_p19(mname, includePrivate);
    }
    
    @JRubyMethod(name = { "object_id", "__id__" })
    public static IRubyObject id(final IRubyObject self) {
        return ((RubyBasicObject)self).id();
    }
    
    @JRubyMethod(name = { "id" }, compat = CompatVersion.RUBY1_8)
    public static IRubyObject id_deprecated(final IRubyObject self) {
        return ((RubyBasicObject)self).id_deprecated();
    }
    
    @JRubyMethod(name = { "hash" })
    public static RubyFixnum hash(final IRubyObject self) {
        return ((RubyBasicObject)self).hash();
    }
    
    @JRubyMethod(name = { "class" })
    public static RubyClass type(final IRubyObject self) {
        return ((RubyBasicObject)self).type();
    }
    
    @JRubyMethod(name = { "type" })
    public static RubyClass type_deprecated(final IRubyObject self) {
        return ((RubyBasicObject)self).type_deprecated();
    }
    
    @JRubyMethod(name = { "clone" })
    public static IRubyObject rbClone(final IRubyObject self) {
        return ((RubyBasicObject)self).rbClone();
    }
    
    @JRubyMethod
    public static IRubyObject dup(final IRubyObject self) {
        return ((RubyBasicObject)self).dup();
    }
    
    @JRubyMethod(name = { "display" }, optional = 1)
    public static IRubyObject display(final ThreadContext context, final IRubyObject self, final IRubyObject[] args) {
        return ((RubyBasicObject)self).display(context, args);
    }
    
    @JRubyMethod(name = { "tainted?" })
    public static RubyBoolean tainted_p(final ThreadContext context, final IRubyObject self) {
        return ((RubyBasicObject)self).tainted_p(context);
    }
    
    @JRubyMethod(name = { "taint" })
    public static IRubyObject taint(final ThreadContext context, final IRubyObject self) {
        return ((RubyBasicObject)self).taint(context);
    }
    
    @JRubyMethod(name = { "untaint" })
    public static IRubyObject untaint(final ThreadContext context, final IRubyObject self) {
        return ((RubyBasicObject)self).untaint(context);
    }
    
    @JRubyMethod(name = { "freeze" })
    public static IRubyObject freeze(final ThreadContext context, final IRubyObject self) {
        return ((RubyBasicObject)self).freeze(context);
    }
    
    @JRubyMethod(name = { "frozen?" })
    public static RubyBoolean frozen_p(final ThreadContext context, final IRubyObject self) {
        return ((RubyBasicObject)self).frozen_p(context);
    }
    
    @JRubyMethod(name = { "untrusted?" }, compat = CompatVersion.RUBY1_9)
    public static RubyBoolean untrusted_p(final ThreadContext context, final IRubyObject self) {
        return ((RubyBasicObject)self).untrusted_p(context);
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_9)
    public static IRubyObject untrust(final ThreadContext context, final IRubyObject self) {
        return ((RubyBasicObject)self).untrust(context);
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_9)
    public static IRubyObject trust(final ThreadContext context, final IRubyObject self) {
        return ((RubyBasicObject)self).trust(context);
    }
    
    @JRubyMethod(name = { "inspect" })
    public static IRubyObject inspect(final IRubyObject self) {
        return ((RubyBasicObject)self).inspect();
    }
    
    @JRubyMethod(name = { "instance_of?" }, required = 1)
    public static RubyBoolean instance_of_p(final ThreadContext context, final IRubyObject self, final IRubyObject type) {
        return ((RubyBasicObject)self).instance_of_p(context, type);
    }
    
    @JRubyMethod(name = { "kind_of?", "is_a?" }, required = 1)
    public static RubyBoolean kind_of_p(final ThreadContext context, final IRubyObject self, final IRubyObject type) {
        return ((RubyBasicObject)self).kind_of_p(context, type);
    }
    
    @JRubyMethod(name = { "methods" }, optional = 1, compat = CompatVersion.RUBY1_8)
    public static IRubyObject methods(final ThreadContext context, final IRubyObject self, final IRubyObject[] args) {
        return ((RubyBasicObject)self).methods(context, args);
    }
    
    @JRubyMethod(name = { "methods" }, optional = 1, compat = CompatVersion.RUBY1_9)
    public static IRubyObject methods19(final ThreadContext context, final IRubyObject self, final IRubyObject[] args) {
        return ((RubyBasicObject)self).methods19(context, args);
    }
    
    @JRubyMethod(name = { "public_methods" }, optional = 1, compat = CompatVersion.RUBY1_8)
    public static IRubyObject public_methods(final ThreadContext context, final IRubyObject self, final IRubyObject[] args) {
        return ((RubyBasicObject)self).public_methods(context, args);
    }
    
    @JRubyMethod(name = { "public_methods" }, optional = 1, compat = CompatVersion.RUBY1_9)
    public static IRubyObject public_methods19(final ThreadContext context, final IRubyObject self, final IRubyObject[] args) {
        return ((RubyBasicObject)self).public_methods19(context, args);
    }
    
    @JRubyMethod(name = { "protected_methods" }, optional = 1, compat = CompatVersion.RUBY1_8)
    public static IRubyObject protected_methods(final ThreadContext context, final IRubyObject self, final IRubyObject[] args) {
        return ((RubyBasicObject)self).protected_methods(context, args);
    }
    
    @JRubyMethod(name = { "protected_methods" }, optional = 1, compat = CompatVersion.RUBY1_9)
    public static IRubyObject protected_methods19(final ThreadContext context, final IRubyObject self, final IRubyObject[] args) {
        return ((RubyBasicObject)self).protected_methods19(context, args);
    }
    
    @JRubyMethod(name = { "private_methods" }, optional = 1, compat = CompatVersion.RUBY1_8)
    public static IRubyObject private_methods(final ThreadContext context, final IRubyObject self, final IRubyObject[] args) {
        return ((RubyBasicObject)self).private_methods(context, args);
    }
    
    @JRubyMethod(name = { "private_methods" }, optional = 1, compat = CompatVersion.RUBY1_9)
    public static IRubyObject private_methods19(final ThreadContext context, final IRubyObject self, final IRubyObject[] args) {
        return ((RubyBasicObject)self).private_methods19(context, args);
    }
    
    @JRubyMethod(name = { "singleton_methods" }, optional = 1, compat = CompatVersion.RUBY1_8)
    public static RubyArray singleton_methods(final ThreadContext context, final IRubyObject self, final IRubyObject[] args) {
        return ((RubyBasicObject)self).singleton_methods(context, args);
    }
    
    @JRubyMethod(name = { "singleton_methods" }, optional = 1, compat = CompatVersion.RUBY1_9)
    public static RubyArray singleton_methods19(final ThreadContext context, final IRubyObject self, final IRubyObject[] args) {
        return ((RubyBasicObject)self).singleton_methods19(context, args);
    }
    
    @JRubyMethod(name = { "method" }, required = 1)
    public static IRubyObject method(final IRubyObject self, final IRubyObject symbol) {
        return ((RubyBasicObject)self).method(symbol);
    }
    
    @JRubyMethod(name = { "method" }, required = 1, compat = CompatVersion.RUBY1_9)
    public static IRubyObject method19(final IRubyObject self, final IRubyObject symbol) {
        return ((RubyBasicObject)self).method19(symbol);
    }
    
    @JRubyMethod(name = { "to_s" })
    public static IRubyObject to_s(final IRubyObject self) {
        return ((RubyBasicObject)self).to_s();
    }
    
    @JRubyMethod(name = { "to_a" }, visibility = Visibility.PUBLIC, compat = CompatVersion.RUBY1_8)
    public static RubyArray to_a(final IRubyObject self) {
        return ((RubyBasicObject)self).to_a();
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_8)
    public static IRubyObject instance_eval(final ThreadContext context, final IRubyObject self, final Block block) {
        return ((RubyBasicObject)self).instance_eval(context, block);
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_8)
    public static IRubyObject instance_eval(final ThreadContext context, final IRubyObject self, final IRubyObject arg0, final Block block) {
        return ((RubyBasicObject)self).instance_eval(context, arg0, block);
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_8)
    public static IRubyObject instance_eval(final ThreadContext context, final IRubyObject self, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        return ((RubyBasicObject)self).instance_eval(context, arg0, arg1, block);
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_8)
    public static IRubyObject instance_eval(final ThreadContext context, final IRubyObject self, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        return ((RubyBasicObject)self).instance_eval(context, arg0, arg1, arg2, block);
    }
    
    @JRubyMethod(optional = 3, rest = true, compat = CompatVersion.RUBY1_8)
    public static IRubyObject instance_exec(final ThreadContext context, final IRubyObject self, final IRubyObject[] args, final Block block) {
        return ((RubyBasicObject)self).instance_exec(context, args, block);
    }
    
    @JRubyMethod(name = { "extend" }, required = 1, rest = true)
    public static IRubyObject extend(final IRubyObject self, final IRubyObject[] args) {
        return ((RubyBasicObject)self).extend(args);
    }
    
    @JRubyMethod(name = { "send", "__send__" }, compat = CompatVersion.RUBY1_8)
    public static IRubyObject send(final ThreadContext context, final IRubyObject self, final Block block) {
        return ((RubyBasicObject)self).send(context, block);
    }
    
    @JRubyMethod(name = { "send", "__send__" }, compat = CompatVersion.RUBY1_8)
    public static IRubyObject send(final ThreadContext context, final IRubyObject self, final IRubyObject arg0, final Block block) {
        return ((RubyBasicObject)self).send(context, arg0, block);
    }
    
    @JRubyMethod(name = { "send", "__send__" }, compat = CompatVersion.RUBY1_8)
    public static IRubyObject send(final ThreadContext context, final IRubyObject self, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        return ((RubyBasicObject)self).send(context, arg0, arg1, block);
    }
    
    @JRubyMethod(name = { "send", "__send__" }, compat = CompatVersion.RUBY1_8)
    public static IRubyObject send(final ThreadContext context, final IRubyObject self, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        return ((RubyBasicObject)self).send(context, arg0, arg1, arg2, block);
    }
    
    @JRubyMethod(name = { "send", "__send__" }, rest = true, compat = CompatVersion.RUBY1_8)
    public static IRubyObject send(final ThreadContext context, final IRubyObject self, final IRubyObject[] args, final Block block) {
        return ((RubyBasicObject)self).send(context, args, block);
    }
    
    @JRubyMethod(name = { "send" }, compat = CompatVersion.RUBY1_9)
    public static IRubyObject send19(final ThreadContext context, final IRubyObject self, final Block block) {
        return ((RubyBasicObject)self).send19(context, block);
    }
    
    @JRubyMethod(name = { "send" }, compat = CompatVersion.RUBY1_9)
    public static IRubyObject send19(final ThreadContext context, final IRubyObject self, final IRubyObject arg0, final Block block) {
        return ((RubyBasicObject)self).send19(context, arg0, block);
    }
    
    @JRubyMethod(name = { "send" }, compat = CompatVersion.RUBY1_9)
    public static IRubyObject send19(final ThreadContext context, final IRubyObject self, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        return ((RubyBasicObject)self).send19(context, arg0, arg1, block);
    }
    
    @JRubyMethod(name = { "send" }, compat = CompatVersion.RUBY1_9)
    public static IRubyObject send19(final ThreadContext context, final IRubyObject self, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        return ((RubyBasicObject)self).send19(context, arg0, arg1, arg2, block);
    }
    
    @JRubyMethod(name = { "send" }, rest = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject send19(final ThreadContext context, final IRubyObject self, final IRubyObject[] args, final Block block) {
        return ((RubyBasicObject)self).send19(context, args, block);
    }
    
    @JRubyMethod(name = { "nil?" })
    public static IRubyObject nil_p(final ThreadContext context, final IRubyObject self) {
        return ((RubyBasicObject)self).nil_p(context);
    }
    
    @JRubyMethod(name = { "=~" }, required = 1, compat = CompatVersion.RUBY1_8)
    public static IRubyObject op_match(final ThreadContext context, final IRubyObject self, final IRubyObject arg) {
        return ((RubyBasicObject)self).op_match(context, arg);
    }
    
    @JRubyMethod(name = { "=~" }, required = 1, compat = CompatVersion.RUBY1_9)
    public static IRubyObject op_match19(final ThreadContext context, final IRubyObject self, final IRubyObject arg) {
        return ((RubyBasicObject)self).op_match19(context, arg);
    }
    
    @JRubyMethod(name = { "!~" }, required = 1, compat = CompatVersion.RUBY1_9)
    public static IRubyObject op_not_match(final ThreadContext context, final IRubyObject self, final IRubyObject arg) {
        return ((RubyBasicObject)self).op_not_match(context, arg);
    }
    
    @JRubyMethod(name = { "instance_variable_defined?" }, required = 1)
    public static IRubyObject instance_variable_defined_p(final ThreadContext context, final IRubyObject self, IRubyObject name) {
        return ((RubyBasicObject)self).instance_variable_defined_p(context, name);
    }
    
    @JRubyMethod(name = { "instance_variable_get" }, required = 1)
    public static IRubyObject instance_variable_get(final ThreadContext context, final IRubyObject self, IRubyObject name) {
        return ((RubyBasicObject)self).instance_variable_get(context, name);
    }
    
    @JRubyMethod(name = { "instance_variable_set" }, required = 2)
    public static IRubyObject instance_variable_set(final IRubyObject self, IRubyObject name, final IRubyObject value) {
        return ((RubyBasicObject)self).instance_variable_set(name, value);
    }
    
    @JRubyMethod(visibility = Visibility.PRIVATE)
    public static IRubyObject remove_instance_variable(final ThreadContext context, final IRubyObject self, final IRubyObject name, final Block block) {
        return ((RubyBasicObject)self).remove_instance_variable(context, name, block);
    }
    
    @JRubyMethod(name = { "instance_variables" })
    public static RubyArray instance_variables(final ThreadContext context, final IRubyObject self) {
        return ((RubyBasicObject)self).instance_variables(context);
    }
    
    @JRubyMethod(name = { "instance_variables" }, compat = CompatVersion.RUBY1_9)
    public static RubyArray instance_variables19(final ThreadContext context, final IRubyObject self) {
        return ((RubyBasicObject)self).instance_variables19(context);
    }
    
    static {
        IRUBY_OBJECT = IRubyObject.class;
        RubyKernel.evalBinding18 = new EvalBinding() {
            public Binding convertToBinding(final IRubyObject scope) {
                if (scope instanceof RubyBinding) {
                    return ((RubyBinding)scope).getBinding().clone();
                }
                if (scope instanceof RubyProc) {
                    return ((RubyProc)scope).getBlock().getBinding().clone();
                }
                throw scope.getRuntime().newTypeError("wrong argument type " + scope.getMetaClass() + " (expected Proc/Binding)");
            }
        };
        RubyKernel.evalBinding19 = new EvalBinding() {
            public Binding convertToBinding(final IRubyObject scope) {
                if (scope instanceof RubyBinding) {
                    return ((RubyBinding)scope).getBinding().clone();
                }
                throw scope.getRuntime().newTypeError("wrong argument type " + scope.getMetaClass() + " (expected Binding)");
            }
        };
        uncaught18 = new Uncaught() {
            public RaiseException uncaughtThrow(final Ruby runtime, final String message, final IRubyObject tag) {
                return runtime.newNameError(message, tag.toString());
            }
        };
        uncaught19 = new Uncaught() {
            public RaiseException uncaughtThrow(final Ruby runtime, final String message, final IRubyObject tag) {
                return runtime.newArgumentError(message);
            }
        };
    }
    
    public abstract static class MethodMissingMethod extends JavaMethodNBlock
    {
        public MethodMissingMethod(final RubyModule implementationClass) {
            super(implementationClass, Visibility.PRIVATE, CallConfiguration.FrameFullScopeNone);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
            try {
                this.preFrameOnly(context, self, name, block);
                return this.methodMissing(context, self, clazz, name, args, block);
            }
            finally {
                JavaMethod.postFrameOnly(context);
            }
        }
        
        public abstract IRubyObject methodMissing(final ThreadContext p0, final IRubyObject p1, final RubyModule p2, final String p3, final IRubyObject[] p4, final Block p5);
    }
    
    private abstract static class EvalBinding
    {
        public abstract Binding convertToBinding(final IRubyObject p0);
    }
    
    private abstract static class Uncaught
    {
        public abstract RaiseException uncaughtThrow(final Ruby p0, final String p1, final IRubyObject p2);
    }
}
