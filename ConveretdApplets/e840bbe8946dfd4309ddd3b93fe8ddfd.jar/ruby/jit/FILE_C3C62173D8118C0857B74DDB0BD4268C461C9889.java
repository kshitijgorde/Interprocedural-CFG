// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.Arity;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_C3C62173D8118C0857B74DDB0BD4268C461C9889 extends AbstractScript
{
    public FILE_C3C62173D8118C0857B74DDB0BD4268C461C9889() {
        this.filename = "./proprietary//undersimple/core/report.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffinclude\uffffF\uffffinclude\uffffF\uffffinclude\uffffF\uffffattr_accessor\uffffF\uffff\u0002\u0001\u0000\u0000\u0003\u0000\u0000\u0000\u0001\u0000\u0000\u0000\u0003\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(0, "undersimple/core/describable", this.getEncoding0());
        this.setByteList(2, "undersimple/core/loosely_typable", this.getEncoding0());
        this.setByteList(1, "undersimple/core/verifiable", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_C3C62173D8118C0857B74DDB0BD4268C461C9889 file_C3C62173D8118C0857B74DDB0BD4268C461C9889, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_C3C62173D8118C0857B74DDB0BD4268C461C9889.getCallSite0().call(threadContext, rubyObject, rubyObject, file_C3C62173D8118C0857B74DDB0BD4268C461C9889.getString0(threadContext.runtime, 32));
        file_C3C62173D8118C0857B74DDB0BD4268C461C9889.getCallSite1().call(threadContext, rubyObject, rubyObject, file_C3C62173D8118C0857B74DDB0BD4268C461C9889.getString1(threadContext.runtime, 32));
        file_C3C62173D8118C0857B74DDB0BD4268C461C9889.getCallSite2().call(threadContext, rubyObject, rubyObject, file_C3C62173D8118C0857B74DDB0BD4268C461C9889.getString2(threadContext.runtime, 32));
        return module__0$RUBY$UnderSimple(file_C3C62173D8118C0857B74DDB0BD4268C461C9889, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$UnderSimple(final FILE_C3C62173D8118C0857B74DDB0BD4268C461C9889 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "UnderSimple"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_C3C62173D8118C0857B74DDB0BD4268C461C9889.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    33: invokestatic    ruby/jit/FILE_C3C62173D8118C0857B74DDB0BD4268C461C9889.class_1$RUBY$ReportDescription:(Lruby/jit/FILE_C3C62173D8118C0857B74DDB0BD4268C461C9889;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    36: aload_1        
        //    37: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    40: goto            48
        //    43: aload_1        
        //    44: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    47: athrow         
        //    48: aload_1        
        //    49: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    52: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     40     43     48     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject class_1$RUBY$ReportDescription(final FILE_C3C62173D8118C0857B74DDB0BD4268C461C9889 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: aload_1        
        //     2: aload_1        
        //     3: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     6: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     9: swap           
        //    10: ldc             "ReportDescription"
        //    12: swap           
        //    13: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    16: dup            
        //    17: astore_2       
        //    18: aload_1        
        //    19: swap           
        //    20: aload_0        
        //    21: aload_1        
        //    22: ldc             ",0,0,-1"
        //    24: invokevirtual   ruby/jit/FILE_C3C62173D8118C0857B74DDB0BD4268C461C9889.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    27: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    30: aload_0        
        //    31: invokevirtual   ruby/jit/FILE_C3C62173D8118C0857B74DDB0BD4268C461C9889.getCallSite3:()Lorg/jruby/runtime/CallSite;
        //    34: aload_1        
        //    35: aload_2        
        //    36: aload_2        
        //    37: aload_0        
        //    38: aload_1        
        //    39: ldc             "Describable"
        //    41: invokevirtual   ruby/jit/FILE_C3C62173D8118C0857B74DDB0BD4268C461C9889.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    44: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: pop            
        //    48: aload_0        
        //    49: invokevirtual   ruby/jit/FILE_C3C62173D8118C0857B74DDB0BD4268C461C9889.getCallSite4:()Lorg/jruby/runtime/CallSite;
        //    52: aload_1        
        //    53: aload_2        
        //    54: aload_2        
        //    55: aload_0        
        //    56: aload_1        
        //    57: ldc             "Verifiable"
        //    59: invokevirtual   ruby/jit/FILE_C3C62173D8118C0857B74DDB0BD4268C461C9889.getConstant1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    62: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    65: pop            
        //    66: aload_0        
        //    67: invokevirtual   ruby/jit/FILE_C3C62173D8118C0857B74DDB0BD4268C461C9889.getCallSite5:()Lorg/jruby/runtime/CallSite;
        //    70: aload_1        
        //    71: aload_2        
        //    72: aload_2        
        //    73: aload_0        
        //    74: aload_1        
        //    75: ldc             "LooselyTypable"
        //    77: invokevirtual   ruby/jit/FILE_C3C62173D8118C0857B74DDB0BD4268C461C9889.getConstant2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    80: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    83: pop            
        //    84: aload_0        
        //    85: invokevirtual   ruby/jit/FILE_C3C62173D8118C0857B74DDB0BD4268C461C9889.getCallSite6:()Lorg/jruby/runtime/CallSite;
        //    88: aload_1        
        //    89: aload_2        
        //    90: aload_2        
        //    91: aload_0        
        //    92: aload_1        
        //    93: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    96: ldc             "name"
        //    98: invokevirtual   ruby/jit/FILE_C3C62173D8118C0857B74DDB0BD4268C461C9889.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   101: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   104: pop            
        //   105: aload_1        
        //   106: aload_2        
        //   107: aload_0        
        //   108: ldc             "initialize"
        //   110: ldc             "method__2$RUBY$initialize"
        //   112: ldc             "name,1,0,-1"
        //   114: iconst_1       
        //   115: ldc             "./proprietary//undersimple/core/report.rb"
        //   117: ldc             11
        //   119: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   122: ldc             "qname"
        //   124: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   127: aload_1        
        //   128: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   131: goto            139
        //   134: aload_1        
        //   135: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   138: athrow         
        //   139: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  30     127    134    139    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "initialize", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__2$RUBY$initialize(final FILE_C3C62173D8118C0857B74DDB0BD4268C461C9889 file_C3C62173D8118C0857B74DDB0BD4268C461C9889, final ThreadContext threadContext, final IRubyObject object, final IRubyObject value, final Block block) {
        return file_C3C62173D8118C0857B74DDB0BD4268C461C9889.setVariable0(threadContext.runtime, "@name", object, value);
    }
    
    public static IRubyObject class_1$RUBY$ReportDescription(final FILE_C3C62173D8118C0857B74DDB0BD4268C461C9889 file_C3C62173D8118C0857B74DDB0BD4268C461C9889, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_1$RUBY$ReportDescription(file_C3C62173D8118C0857B74DDB0BD4268C461C9889, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$UnderSimple(final FILE_C3C62173D8118C0857B74DDB0BD4268C461C9889 file_C3C62173D8118C0857B74DDB0BD4268C461C9889, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$UnderSimple(file_C3C62173D8118C0857B74DDB0BD4268C461C9889, threadContext, rubyObject, block);
    }
    
    @Override
    public IRubyObject load(final ThreadContext context, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        try {
            RuntimeHelpers.preLoad(context, ",0,0,-2");
            final IRubyObject _file__ = __file__(this, context, rubyObject, array, block);
            RuntimeHelpers.postLoad(context);
            return _file__;
        }
        finally {
            RuntimeHelpers.postLoad(context);
        }
    }
    
    public static void main(final String[] argv) {
        final FILE_C3C62173D8118C0857B74DDB0BD4268C461C9889 file_C3C62173D8118C0857B74DDB0BD4268C461C9889 = new FILE_C3C62173D8118C0857B74DDB0BD4268C461C9889();
        final String string = FILE_C3C62173D8118C0857B74DDB0BD4268C461C9889.class.getClassLoader().getResource("ruby/jit/FILE_C3C62173D8118C0857B74DDB0BD4268C461C9889.class").toString();
        file_C3C62173D8118C0857B74DDB0BD4268C461C9889.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_C3C62173D8118C0857B74DDB0BD4268C461C9889.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
