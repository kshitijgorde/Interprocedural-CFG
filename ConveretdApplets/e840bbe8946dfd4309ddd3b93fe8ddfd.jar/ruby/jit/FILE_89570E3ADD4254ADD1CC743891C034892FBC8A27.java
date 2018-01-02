// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.ast.util.ArgsUtil;
import org.jruby.exceptions.JumpException;
import org.jruby.runtime.DynamicScope;
import org.jruby.runtime.Arity;
import org.jruby.RubyString;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27 extends AbstractScript
{
    public FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27() {
        this.filename = "./lib//lister/measurement.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffrequire\uffffF\uffffinclude\uffffF\uffff==\uffffN\uffffversion\uffffV\uffffempty?\uffffN\uffffto_a\uffffN\uffff+\uffffN\uffffold_versions\uffffV\uffffversion\uffffV\uffffputs\uffffF\uffffname\uffffV\uffffversion\uffffV\uffffdescription\uffffV\uffffurl_chunck_name_for\uffffF\uffffversion\uffffV\uffff+\uffffN\uffffdowncase\uffffN\ufffflast\uffffN\uffffsplit\uffffN\uffffname\uffffV\uffffmap\uffffN\uffffall_versions\uffffV\uffffurl_chunck_name_for\uffffF\uffffattr_reader\uffffF\uffffattr_accessor\uffffF\uffffblock_given?\uffffF\uffffdup\uffffN\uffffeach\uffffN\uffffparameters\uffffV\uffff[]\uffffN\uffffvalid_value?\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffffnew_for\uffffN\uffffadvertise\uffffN\uffffclass\uffffN\ufffflog\uffffN\uffffrunner\uffffV\uffffprepare\uffffV\uffffnow\uffffN\uffffrespond_to?\uffffF\uffffexecute\uffffV\uffffnow\uffffN\uffffduration=\uffffN\uffffduration=\uffffV\uffff-\uffffN\uffffauto_report\uffffV\uffffattr_reader\uffffF\uffffprogress_callback\uffffV\uffffcall\uffffN\uffffprogress_callback\uffffV\uffff\u0003\u0004\u0000\u0000\u0006\u0000\u0000\u0003\u0007\u0002\u0000\u0000\b\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(3, " (", this.getEncoding0());
        this.setByteList(1, "time", this.getEncoding0());
        this.setByteList(2, "dev", this.getEncoding0());
        this.setByteList(4, "): ", this.getEncoding0());
        this.setByteList(6, "::", this.getEncoding0());
        this.setByteList(0, "undersimple", this.getEncoding0());
        this.setByteList(5, "English", this.getEncoding0());
        this.setByteList(7, "-", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27 file_89570E3ADD4254ADD1CC743891C034892FBC8A27, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite0().call(threadContext, rubyObject, rubyObject, file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getString0(threadContext.runtime, 32));
        file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite1().call(threadContext, rubyObject, rubyObject, file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getString1(threadContext.runtime, 32));
        return module__0$RUBY$Lister(file_89570E3ADD4254ADD1CC743891C034892FBC8A27, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "Lister"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    33: invokestatic    ruby/jit/FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27.class_1$RUBY$Measurement:(Lruby/jit/FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject class_1$RUBY$Measurement(final FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    10: ldc             "Measurement"
        //    12: swap           
        //    13: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    16: dup            
        //    17: astore_2       
        //    18: aload_1        
        //    19: swap           
        //    20: aload_0        
        //    21: aload_1        
        //    22: ldc             ",0,0,-1"
        //    24: invokevirtual   ruby/jit/FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    27: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    30: aload_0        
        //    31: invokevirtual   ruby/jit/FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite2:()Lorg/jruby/runtime/CallSite;
        //    34: aload_1        
        //    35: aload_2        
        //    36: aload_2        
        //    37: aload_0        
        //    38: aload_1        
        //    39: ldc             "UnderSimple"
        //    41: invokevirtual   ruby/jit/FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    44: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    47: aload_0        
        //    48: swap           
        //    49: aload_1        
        //    50: ldc             "MeasurementDescription"
        //    52: invokevirtual   ruby/jit/FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27.getConstantFrom1:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    55: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    58: pop            
        //    59: aload_0        
        //    60: aload_1        
        //    61: aload_2        
        //    62: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    65: invokestatic    ruby/jit/FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27.sclass_2$RUBY$__singleton__:(Lruby/jit/FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    68: pop            
        //    69: aload_0        
        //    70: bipush          24
        //    72: invokevirtual   ruby/jit/FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    75: aload_1        
        //    76: aload_2        
        //    77: aload_2        
        //    78: aload_0        
        //    79: aload_1        
        //    80: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    83: ldc_w           "runner"
        //    86: invokevirtual   ruby/jit/FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    89: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    92: pop            
        //    93: aload_0        
        //    94: bipush          25
        //    96: invokevirtual   ruby/jit/FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    99: aload_1        
        //   100: aload_2        
        //   101: aload_2        
        //   102: aload_0        
        //   103: aload_1        
        //   104: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   107: ldc_w           "duration"
        //   110: invokevirtual   ruby/jit/FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   113: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   116: pop            
        //   117: aload_1        
        //   118: aload_2        
        //   119: ldc_w           "params"
        //   122: ldc_w           "parameters"
        //   125: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defineAlias:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/Object;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   128: pop            
        //   129: aload_1        
        //   130: aload_2        
        //   131: aload_0        
        //   132: ldc_w           "initialize"
        //   135: ldc_w           "method__13$RUBY$initialize"
        //   138: ldc_w           "runner;params,1,1,-1"
        //   141: bipush          -2
        //   143: ldc             "./lib//lister/measurement.rb"
        //   145: ldc_w           62
        //   148: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   151: ldc_w           "qrunner;oparams"
        //   154: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   157: pop            
        //   158: aload_1        
        //   159: aload_2        
        //   160: aload_2        
        //   161: aload_0        
        //   162: ldc_w           "fix_params"
        //   165: ldc_w           "method__14$RUBY$fix_params"
        //   168: ldc_w           "params,0,0,-1"
        //   171: iconst_0       
        //   172: ldc             "./lib//lister/measurement.rb"
        //   174: ldc_w           69
        //   177: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   180: ldc             "NONE"
        //   182: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   185: pop            
        //   186: aload_1        
        //   187: aload_2        
        //   188: aload_0        
        //   189: ldc_w           "prepare"
        //   192: ldc_w           "method__16$RUBY$prepare"
        //   195: ldc             ",0,0,-1"
        //   197: iconst_0       
        //   198: ldc             "./lib//lister/measurement.rb"
        //   200: ldc_w           83
        //   203: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   206: ldc             "NONE"
        //   208: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   211: pop            
        //   212: aload_1        
        //   213: aload_2        
        //   214: aload_0        
        //   215: ldc_w           "log"
        //   218: ldc_w           "method__17$RUBY$log"
        //   221: ldc_w           "args,0,0,0"
        //   224: iconst_m1      
        //   225: ldc             "./lib//lister/measurement.rb"
        //   227: ldc_w           87
        //   230: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   233: ldc_w           "rargs"
        //   236: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   239: pop            
        //   240: aload_1        
        //   241: aload_2        
        //   242: aload_0        
        //   243: ldc_w           "perform"
        //   246: ldc_w           "method__18$RUBY$perform"
        //   249: ldc_w           "t0;t1,0,0,-1"
        //   252: iconst_0       
        //   253: ldc             "./lib//lister/measurement.rb"
        //   255: ldc_w           91
        //   258: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   261: ldc             "NONE"
        //   263: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   266: pop            
        //   267: aload_0        
        //   268: bipush          48
        //   270: invokevirtual   ruby/jit/FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   273: aload_1        
        //   274: aload_2        
        //   275: aload_2        
        //   276: aload_0        
        //   277: aload_1        
        //   278: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   281: ldc_w           "progress_callback"
        //   284: invokevirtual   ruby/jit/FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   287: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   290: pop            
        //   291: aload_1        
        //   292: aload_2        
        //   293: aload_0        
        //   294: ldc_w           "on_progress"
        //   297: ldc_w           "method__19$RUBY$on_progress"
        //   300: ldc_w           "blk,0,0,-1"
        //   303: iconst_0       
        //   304: ldc             "./lib//lister/measurement.rb"
        //   306: ldc_w           101
        //   309: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   312: ldc_w           "bblk"
        //   315: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   318: pop            
        //   319: aload_1        
        //   320: aload_2        
        //   321: aload_0        
        //   322: ldc_w           "progress"
        //   325: ldc_w           "method__20$RUBY$progress"
        //   328: ldc_w           "percent,1,0,-1"
        //   331: iconst_1       
        //   332: ldc             "./lib//lister/measurement.rb"
        //   334: ldc_w           105
        //   337: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   340: ldc_w           "qpercent"
        //   343: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   346: aload_1        
        //   347: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   350: goto            358
        //   353: aload_1        
        //   354: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   357: athrow         
        //   358: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  30     346    353    358    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject sclass_2$RUBY$__singleton__(final FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //     4: aload_2        
        //     5: astore          8
        //     7: aload_2        
        //     8: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.getSingletonClass:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyClass;
        //    11: dup            
        //    12: astore_2       
        //    13: aload_1        
        //    14: swap           
        //    15: aload_0        
        //    16: aload_1        
        //    17: ldc             ",0,0,-1"
        //    19: invokevirtual   ruby/jit/FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    22: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    25: aload_1        
        //    26: aload_2        
        //    27: aload_0        
        //    28: ldc             "version"
        //    30: ldc             "method__3$RUBY$version"
        //    32: ldc             "val,0,1,-1"
        //    34: iconst_m1      
        //    35: ldc             "./lib//lister/measurement.rb"
        //    37: ldc             10
        //    39: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    42: ldc             "oval"
        //    44: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: pop            
        //    48: aload_1        
        //    49: aload_2        
        //    50: aload_0        
        //    51: ldc             "in_development?"
        //    53: ldc             "method__4$RUBY$in_development_p_"
        //    55: ldc             ",0,0,-1"
        //    57: iconst_0       
        //    58: ldc             "./lib//lister/measurement.rb"
        //    60: ldc             15
        //    62: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    65: ldc             "NONE"
        //    67: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    70: pop            
        //    71: aload_1        
        //    72: aload_2        
        //    73: aload_0        
        //    74: ldc             "old_versions"
        //    76: ldc             "method__5$RUBY$old_versions"
        //    78: ldc             "vals,0,0,0"
        //    80: iconst_m1      
        //    81: ldc             "./lib//lister/measurement.rb"
        //    83: ldc             19
        //    85: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    88: ldc             "rvals"
        //    90: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    93: pop            
        //    94: aload_1        
        //    95: aload_2        
        //    96: aload_0        
        //    97: ldc_w           "all_versions"
        //   100: ldc_w           "method__6$RUBY$all_versions"
        //   103: ldc             ",0,0,-1"
        //   105: iconst_0       
        //   106: ldc             "./lib//lister/measurement.rb"
        //   108: ldc_w           24
        //   111: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   114: ldc             "NONE"
        //   116: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   119: pop            
        //   120: aload_1        
        //   121: aload_2        
        //   122: aload_0        
        //   123: ldc_w           "advertise"
        //   126: ldc_w           "method__7$RUBY$advertise"
        //   129: ldc             ",0,0,-1"
        //   131: iconst_0       
        //   132: ldc             "./lib//lister/measurement.rb"
        //   134: ldc_w           28
        //   137: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   140: ldc             "NONE"
        //   142: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   145: pop            
        //   146: aload_1        
        //   147: aload_2        
        //   148: aload_0        
        //   149: ldc_w           "url_chunck_name"
        //   152: ldc_w           "method__8$RUBY$url_chunck_name"
        //   155: ldc             ",0,0,-1"
        //   157: iconst_0       
        //   158: ldc             "./lib//lister/measurement.rb"
        //   160: ldc_w           32
        //   163: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   166: ldc             "NONE"
        //   168: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   171: pop            
        //   172: aload_1        
        //   173: aload_2        
        //   174: aload_0        
        //   175: ldc_w           "default_description_tag"
        //   178: ldc_w           "method__9$RUBY$default_description_tag"
        //   181: ldc             ",0,0,-1"
        //   183: iconst_0       
        //   184: ldc             "./lib//lister/measurement.rb"
        //   186: ldc_w           36
        //   189: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   192: ldc             "NONE"
        //   194: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   197: pop            
        //   198: aload_1        
        //   199: aload_2        
        //   200: aload_0        
        //   201: ldc_w           "url_chunck_name_for"
        //   204: ldc_w           "method__10$RUBY$url_chunck_name_for"
        //   207: ldc_w           "version,1,0,-1"
        //   210: iconst_1       
        //   211: ldc             "./lib//lister/measurement.rb"
        //   213: ldc_w           40
        //   216: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   219: ldc_w           "qversion"
        //   222: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   225: pop            
        //   226: aload_1        
        //   227: aload_2        
        //   228: aload_0        
        //   229: ldc_w           "accepted_url_chunck_names"
        //   232: ldc_w           "method__11$RUBY$accepted_url_chunck_names"
        //   235: ldc             ",0,0,-1"
        //   237: iconst_0       
        //   238: ldc             "./lib//lister/measurement.rb"
        //   240: ldc_w           44
        //   243: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   246: ldc             "NONE"
        //   248: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   251: pop            
        //   252: aload_1        
        //   253: aload_2        
        //   254: aload_0        
        //   255: ldc_w           "duration_weight"
        //   258: ldc_w           "method__12$RUBY$duration_weight"
        //   261: ldc             "val,0,1,-1"
        //   263: iconst_m1      
        //   264: ldc             "./lib//lister/measurement.rb"
        //   266: ldc_w           48
        //   269: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   272: ldc             "oval"
        //   274: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   277: aload_1        
        //   278: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   281: goto            289
        //   284: aload_1        
        //   285: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   288: athrow         
        //   289: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  25     277    284    289    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "version", frame = true, required = 0, optional = 1, rest = -1)
    public static IRubyObject method__3$RUBY$version(final FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     4: astore          9
        //     6: aload_1        
        //     7: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    10: aload_3        
        //    11: iconst_0       
        //    12: iconst_1       
        //    13: invokestatic    org/jruby/runtime/Arity.raiseArgumentError:(Lorg/jruby/Ruby;[Lorg/jruby/runtime/builtin/IRubyObject;II)V
        //    16: aload_3        
        //    17: iconst_0       
        //    18: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.elementOrNull:([Lorg/jruby/runtime/builtin/IRubyObject;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    21: dup            
        //    22: ifnull          30
        //    25: astore          9
        //    27: goto            37
        //    30: aload_1        
        //    31: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    34: astore          9
        //    36: pop            
        //    37: aload           val
        //    39: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    44: ifeq            64
        //    47: aload_0        
        //    48: aload_1        
        //    49: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    52: ldc             "@version"
        //    54: aload_2        
        //    55: aload           val
        //    57: invokevirtual   ruby/jit/FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27.setVariable0:(Lorg/jruby/Ruby;Ljava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    60: pop            
        //    61: goto            64
        //    64: aload_0        
        //    65: aload_1        
        //    66: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    69: ldc             "@version"
        //    71: aload_2        
        //    72: invokevirtual   ruby/jit/FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27.getVariable0:(Lorg/jruby/Ruby;Ljava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    75: dup            
        //    76: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    81: ifne            95
        //    84: pop            
        //    85: aload_0        
        //    86: aload_1        
        //    87: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    90: bipush          32
        //    92: invokevirtual   ruby/jit/FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27.getString2:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    95: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  37     59      9     val   Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:657)
        //     at java.util.ArrayList.get(ArrayList.java:433)
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:3035)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "in_development?", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$in_development_p_(final FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27 file_89570E3ADD4254ADD1CC743891C034892FBC8A27, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite3().call(threadContext, rubyObject, file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getString2(threadContext.runtime, 32), file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite4().call(threadContext, rubyObject, rubyObject));
    }
    
    @JRubyMethod(name = "old_versions", frame = true, required = 0, optional = 0, rest = 0)
    public static IRubyObject method__5$RUBY$old_versions(final FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27 file_89570E3ADD4254ADD1CC743891C034892FBC8A27, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] input, final Block block) {
        final IRubyObject nil = threadContext.nil;
        final IRubyObject vals = RuntimeHelpers.createSubarray(input, threadContext.runtime, 0);
        if (!file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite5().call(threadContext, rubyObject, vals).isTrue()) {
            file_89570E3ADD4254ADD1CC743891C034892FBC8A27.setVariable1(threadContext.runtime, "@old_versions", rubyObject, file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite6().call(threadContext, rubyObject, vals));
        }
        IRubyObject rubyObject2;
        if (!(rubyObject2 = file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getVariable1(threadContext.runtime, "@old_versions", rubyObject)).isTrue()) {
            rubyObject2 = threadContext.runtime.newArray();
        }
        return rubyObject2;
    }
    
    @JRubyMethod(name = "all_versions", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__6$RUBY$all_versions(final FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27 file_89570E3ADD4254ADD1CC743891C034892FBC8A27, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite7().call(threadContext, rubyObject, file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite8().call(threadContext, rubyObject, rubyObject), RuntimeHelpers.constructRubyArray(threadContext.runtime, file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite9().call(threadContext, rubyObject, rubyObject)));
    }
    
    @JRubyMethod(name = "advertise", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__7$RUBY$advertise(final FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27 file_89570E3ADD4254ADD1CC743891C034892FBC8A27, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite(10).call(threadContext, rubyObject, rubyObject, RubyString.newStringLight(threadContext.runtime, 20).append(file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite(11).call(threadContext, rubyObject, rubyObject).asString()).append(file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getString3(threadContext.runtime, 32)).append(file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite(12).call(threadContext, rubyObject, rubyObject).asString()).append(file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getString4(threadContext.runtime, 32)).append(file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite(13).call(threadContext, rubyObject, rubyObject).asString()));
    }
    
    @JRubyMethod(name = "url_chunck_name", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__8$RUBY$url_chunck_name(final FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27 file_89570E3ADD4254ADD1CC743891C034892FBC8A27, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite(14).call(threadContext, rubyObject, rubyObject, file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite(15).call(threadContext, rubyObject, rubyObject));
    }
    
    @JRubyMethod(name = "default_description_tag", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__9$RUBY$default_description_tag(final FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27 file_89570E3ADD4254ADD1CC743891C034892FBC8A27, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getString5(threadContext.runtime, 32);
    }
    
    @JRubyMethod(name = "url_chunck_name_for", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__10$RUBY$url_chunck_name_for(final FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          5
        //     6: aload_3        
        //     7: aload           5
        //     9: swap           
        //    10: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    13: pop            
        //    14: aload_0        
        //    15: bipush          16
        //    17: invokevirtual   ruby/jit/FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    20: aload_1        
        //    21: aload_2        
        //    22: aload_0        
        //    23: bipush          17
        //    25: invokevirtual   ruby/jit/FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    28: aload_1        
        //    29: aload_2        
        //    30: aload_0        
        //    31: bipush          18
        //    33: invokevirtual   ruby/jit/FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    36: aload_1        
        //    37: aload_2        
        //    38: aload_0        
        //    39: bipush          19
        //    41: invokevirtual   ruby/jit/FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    44: aload_1        
        //    45: aload_2        
        //    46: aload_0        
        //    47: bipush          20
        //    49: invokevirtual   ruby/jit/FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    52: aload_1        
        //    53: aload_2        
        //    54: aload_2        
        //    55: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    58: aload_0        
        //    59: aload_1        
        //    60: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    63: bipush          32
        //    65: invokevirtual   ruby/jit/FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27.getString6:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    68: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    71: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    74: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    77: aload_1        
        //    78: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    81: ldc_w           20
        //    84: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    87: aload_0        
        //    88: aload_1        
        //    89: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    92: bipush          32
        //    94: invokevirtual   ruby/jit/FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27.getString7:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    97: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   100: aload           locals
        //   102: aload_1        
        //   103: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   106: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   109: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   114: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   117: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   120: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     107     5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "accepted_url_chunck_names", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__11$RUBY$accepted_url_chunck_names(final FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27 file_89570E3ADD4254ADD1CC743891C034892FBC8A27, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite(21).callIter(threadContext, self, file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite(22).call(threadContext, self, self), RuntimeHelpers.createBlock(threadContext, self, file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getBlockBody0(threadContext, "block_0$RUBY$accepted_url_chunck_names,1,i,false,2,./lib//lister/measurement.rb,45,true")));
    }
    
    public static IRubyObject block_0$RUBY$accepted_url_chunck_names(final FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          5
        //     6: aload_1        
        //     7: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    10: astore          9
        //    12: aload_1        
        //    13: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    16: aload           4
        //    18: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    21: aload_3        
        //    22: astore          9
        //    24: pop            
        //    25: aload_0        
        //    26: bipush          23
        //    28: invokevirtual   ruby/jit/FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_2        
        //    34: aload           i
        //    36: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     15      9     i     Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "duration_weight", frame = true, required = 0, optional = 1, rest = -1)
    public static IRubyObject method__12$RUBY$duration_weight(final FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     4: astore          9
        //     6: aload_1        
        //     7: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    10: aload_3        
        //    11: iconst_0       
        //    12: iconst_1       
        //    13: invokestatic    org/jruby/runtime/Arity.raiseArgumentError:(Lorg/jruby/Ruby;[Lorg/jruby/runtime/builtin/IRubyObject;II)V
        //    16: aload_3        
        //    17: iconst_0       
        //    18: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.elementOrNull:([Lorg/jruby/runtime/builtin/IRubyObject;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    21: dup            
        //    22: ifnull          30
        //    25: astore          9
        //    27: goto            37
        //    30: aload_1        
        //    31: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    34: astore          9
        //    36: pop            
        //    37: aload           val
        //    39: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    44: ifeq            65
        //    47: aload_0        
        //    48: aload_1        
        //    49: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    52: ldc_w           "@duration_weight"
        //    55: aload_2        
        //    56: aload           val
        //    58: invokevirtual   ruby/jit/FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27.setVariable2:(Lorg/jruby/Ruby;Ljava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    61: pop            
        //    62: goto            65
        //    65: aload_0        
        //    66: aload_1        
        //    67: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    70: ldc_w           "@duration_weight"
        //    73: aload_2        
        //    74: invokevirtual   ruby/jit/FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27.getVariable2:(Lorg/jruby/Ruby;Ljava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    77: dup            
        //    78: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    83: ifne            94
        //    86: pop            
        //    87: aload_1        
        //    88: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    91: invokestatic    org/jruby/RubyFixnum.one:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //    94: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  37     58      9     val   Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:657)
        //     at java.util.ArrayList.get(ArrayList.java:433)
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:3035)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject sclass_2$RUBY$__singleton__(final FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27 file_89570E3ADD4254ADD1CC743891C034892FBC8A27, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return sclass_2$RUBY$__singleton__(file_89570E3ADD4254ADD1CC743891C034892FBC8A27, threadContext, rubyObject, block);
    }
    
    @JRubyMethod(name = "initialize", frame = true, required = 1, optional = 1, rest = -1)
    public static IRubyObject method__13$RUBY$initialize(final FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     4: astore          10
        //     6: aload_1        
        //     7: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    10: aload_3        
        //    11: iconst_1       
        //    12: iconst_2       
        //    13: invokestatic    org/jruby/runtime/Arity.raiseArgumentError:(Lorg/jruby/Ruby;[Lorg/jruby/runtime/builtin/IRubyObject;II)V
        //    16: aload_3        
        //    17: iconst_0       
        //    18: aaload         
        //    19: astore          9
        //    21: aload_3        
        //    22: iconst_1       
        //    23: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.elementOrNull:([Lorg/jruby/runtime/builtin/IRubyObject;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    26: dup            
        //    27: ifnull          35
        //    30: astore          10
        //    32: goto            45
        //    35: aload_1        
        //    36: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    39: invokestatic    org/jruby/RubyHash.newHash:(Lorg/jruby/Ruby;)Lorg/jruby/RubyHash;
        //    42: astore          10
        //    44: pop            
        //    45: aload_0        
        //    46: aload_1        
        //    47: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    50: ldc_w           "@runner"
        //    53: aload_2        
        //    54: aload           runner
        //    56: invokevirtual   ruby/jit/FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27.setVariable3:(Lorg/jruby/Ruby;Ljava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    59: pop            
        //    60: aload_0        
        //    61: aload_1        
        //    62: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    65: ldc_w           "@parameters"
        //    68: aload_2        
        //    69: aload           params
        //    71: invokevirtual   ruby/jit/FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27.setVariable4:(Lorg/jruby/Ruby;Ljava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    74: pop            
        //    75: aload_0        
        //    76: aload_1        
        //    77: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    80: ldc_w           "@reports"
        //    83: aload_2        
        //    84: aload_1        
        //    85: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    88: invokestatic    org/jruby/RubyHash.newHash:(Lorg/jruby/Ruby;)Lorg/jruby/RubyHash;
        //    91: invokevirtual   ruby/jit/FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27.setVariable5:(Lorg/jruby/Ruby;Ljava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    94: pop            
        //    95: aload_0        
        //    96: bipush          26
        //    98: invokevirtual   ruby/jit/FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   101: aload_1        
        //   102: aload_2        
        //   103: aload_2        
        //   104: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   107: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   112: ifeq            125
        //   115: aload           4
        //   117: aload_1        
        //   118: aload_2        
        //   119: invokevirtual   org/jruby/runtime/Block.yield:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   122: goto            129
        //   125: aload_1        
        //   126: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   129: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  ---------------------------------------
        //  45     85      9     runner  Lorg/jruby/runtime/builtin/IRubyObject;
        //  45     85      10    params  Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "fix_params", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__14$RUBY$fix_params(final FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27 file_89570E3ADD4254ADD1CC743891C034892FBC8A27, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite(27).call(threadContext, self, locals.getValueZeroDepthZeroOrNil(threadContext.nil)));
        file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite(28).callIter(threadContext, self, file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite(29).call(threadContext, self, self), RuntimeHelpers.createBlock(threadContext, self, file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getBlockBody1(threadContext, "block_1$RUBY$fix_params,2,param_name;spec;old_value,true,1,./lib//lister/measurement.rb,71,false")));
        return locals.getValueZeroDepthZeroOrNil(threadContext.nil);
    }
    
    public static IRubyObject block_1$RUBY$fix_params(final FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          5
        //     6: aload_1        
        //     7: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    10: aload           5
        //    12: swap           
        //    13: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    16: aload           5
        //    18: swap           
        //    19: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    22: aload           5
        //    24: swap           
        //    25: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    28: pop            
        //    29: aload_1        
        //    30: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    33: aload           4
        //    35: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    38: aload_3        
        //    39: aload_1        
        //    40: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    43: iconst_1       
        //    44: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.ensureMultipleAssignableRubyArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;Z)Lorg/jruby/RubyArray;
        //    47: astore          9
        //    49: aload           9
        //    51: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilZero:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    54: aload           5
        //    56: swap           
        //    57: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    60: pop            
        //    61: aload           9
        //    63: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilOne:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    66: aload           5
        //    68: swap           
        //    69: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    72: pop            
        //    73: aload           9
        //    75: pop            
        //    76: pop            
        //    77: aload           locals
        //    79: aload_0        
        //    80: bipush          30
        //    82: invokevirtual   ruby/jit/FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    85: aload_1        
        //    86: aload_2        
        //    87: aload           locals
        //    89: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    92: aload_1        
        //    93: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    96: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    99: aload           locals
        //   101: aload_1        
        //   102: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   105: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   108: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   111: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   114: pop            
        //   115: aload_0        
        //   116: bipush          31
        //   118: invokevirtual   ruby/jit/FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   121: aload_1        
        //   122: aload_2        
        //   123: aload           locals
        //   125: aload_1        
        //   126: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   129: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   132: aload           locals
        //   134: aload_1        
        //   135: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   138: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   141: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   144: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   149: ifeq            159
        //   152: aload_1        
        //   153: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   156: goto            168
        //   159: aload_0        
        //   160: aload_1        
        //   161: aload_2        
        //   162: aload_3        
        //   163: aload           4
        //   165: invokestatic    ruby/jit/FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27.chained_15_rescue_1$RUBY$SYNTHETICfix_params:(Lruby/jit/FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   168: areturn        
        //   169: pop            
        //   170: goto            77
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  77     92      5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  77     169    169    173    Lorg/jruby/exceptions/JumpException$RedoJump;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject chained_15_rescue_1$RUBY$SYNTHETICfix_params(final FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27 file_89570E3ADD4254ADD1CC743891C034892FBC8A27, final ThreadContext context, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        final DynamicScope currentScope = context.getCurrentScope();
        final IRubyObject errorInfo = context.getErrorInfo();
        IRubyObject rubyObject3 = null;
        Label_0148: {
            try {
                try {
                    final IRubyObject valueZeroDepthZeroOrNil = currentScope.getNextCapturedScope().getValueZeroDepthZeroOrNil(context.nil);
                    rubyObject3 = RuntimeHelpers.doAttrAsgn(valueZeroDepthZeroOrNil, RuntimeHelpers.selectAttrAsgnCallSite(valueZeroDepthZeroOrNil, rubyObject, file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite(32), file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite(33)), currentScope.getValueZeroDepthZeroOrNil(context.nil), file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite(34).call(context, rubyObject, currentScope.getValueOneDepthZeroOrNil(context.nil), currentScope.getValueTwoDepthZeroOrNil(context.nil)), context, rubyObject);
                }
                catch (JumpException.FlowControlException ex) {
                    throw ex;
                }
                catch (Throwable currentThrowable) {
                    if (RuntimeHelpers.isJavaExceptionHandled(currentThrowable, file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getConstantFrom3(RuntimeHelpers.checkIsModule(file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getConstant2(context, "UnderSimple")), context, "MorphingError"), context).isTrue()) {
                        rubyObject3 = context.nil;
                        RuntimeHelpers.clearErrorInfo(context);
                        break Label_0148;
                    }
                    throw currentThrowable;
                }
            }
            catch (JumpException.FlowControlException ex2) {
                context.setErrorInfo(errorInfo);
                throw ex2;
            }
        }
        context.setErrorInfo(errorInfo);
        return rubyObject3;
    }
    
    @JRubyMethod(name = "prepare", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__16$RUBY$prepare(final FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27 file_89570E3ADD4254ADD1CC743891C034892FBC8A27, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite(35).call(threadContext, rubyObject, file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite(36).call(threadContext, rubyObject, rubyObject));
    }
    
    @JRubyMethod(name = "log", frame = true, required = 0, optional = 0, rest = 0)
    public static IRubyObject method__17$RUBY$log(final FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27 file_89570E3ADD4254ADD1CC743891C034892FBC8A27, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] input, final Block block) {
        final IRubyObject nil = threadContext.nil;
        final IRubyObject args = RuntimeHelpers.createSubarray(input, threadContext.runtime, 0);
        return file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite(37).call(threadContext, rubyObject, file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite(38).call(threadContext, rubyObject, rubyObject), ArgsUtil.convertToJavaArray(RuntimeHelpers.splatValue(args)));
    }
    
    @JRubyMethod(name = "perform", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__18$RUBY$perform(final FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27 file_89570E3ADD4254ADD1CC743891C034892FBC8A27, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        IRubyObject t1 = context.nil;
        file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite(39).call(context, rubyObject, rubyObject);
        final IRubyObject t2 = file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite(40).call(context, rubyObject, file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getConstant4(context, "Time"));
        if (file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite(41).call(context, rubyObject, rubyObject, file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getSymbol2(context.runtime, "execute")).isTrue()) {
            file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite(42).call(context, rubyObject, rubyObject);
        }
        t1 = file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite(43).call(context, rubyObject, file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getConstant5(context, "Time"));
        RuntimeHelpers.doAttrAsgn(rubyObject, RuntimeHelpers.selectAttrAsgnCallSite(rubyObject, rubyObject, file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite(44), file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite(45)), file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite(46).call(context, rubyObject, t1, t2), context, rubyObject);
        return file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite(47).call(context, rubyObject, rubyObject);
    }
    
    @JRubyMethod(name = "on_progress", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__19$RUBY$on_progress(final FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27 file_89570E3ADD4254ADD1CC743891C034892FBC8A27, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        final IRubyObject nil = threadContext.nil;
        final IRubyObject blk = RuntimeHelpers.processBlockArgument(threadContext.runtime, block);
        return file_89570E3ADD4254ADD1CC743891C034892FBC8A27.setVariable6(threadContext.runtime, "@progress_callback", object, blk);
    }
    
    @JRubyMethod(name = "progress", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__20$RUBY$progress(final FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27 file_89570E3ADD4254ADD1CC743891C034892FBC8A27, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite(49).call(threadContext, rubyObject, rubyObject).isTrue() ? file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite(50).call(threadContext, rubyObject, file_89570E3ADD4254ADD1CC743891C034892FBC8A27.getCallSite(51).call(threadContext, rubyObject, rubyObject), rubyObject2) : threadContext.nil;
    }
    
    public static IRubyObject class_1$RUBY$Measurement(final FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27 file_89570E3ADD4254ADD1CC743891C034892FBC8A27, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_1$RUBY$Measurement(file_89570E3ADD4254ADD1CC743891C034892FBC8A27, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27 file_89570E3ADD4254ADD1CC743891C034892FBC8A27, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_89570E3ADD4254ADD1CC743891C034892FBC8A27, threadContext, rubyObject, block);
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
        final FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27 file_89570E3ADD4254ADD1CC743891C034892FBC8A27 = new FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27();
        final String string = FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27.class.getClassLoader().getResource("ruby/jit/FILE_89570E3ADD4254ADD1CC743891C034892FBC8A27.class").toString();
        file_89570E3ADD4254ADD1CC743891C034892FBC8A27.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_89570E3ADD4254ADD1CC743891C034892FBC8A27.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
