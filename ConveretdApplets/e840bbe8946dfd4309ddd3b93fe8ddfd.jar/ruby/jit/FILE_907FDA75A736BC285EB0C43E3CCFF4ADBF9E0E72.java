// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.Arity;
import org.jruby.RubyFixnum;
import org.jruby.RubyString;
import org.jruby.runtime.DynamicScope;
import org.jruby.exceptions.JumpException;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72 extends AbstractScript
{
    public FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72() {
        this.filename = "./lib//lister/runner/measurements/netalyzr.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffjoin\uffffN\uffffopen\uffffN\uffffjar\uffffF\ufffflog\uffffF\uffffopen\uffffF\uffffeach\uffffN\uffff<<\uffffN\ufffflog\uffffF\uffffthrow\uffffF\uffffdo_run\uffffF\ufffflog\uffffF\uffffthrow\uffffF\uffffjar\uffffF\ufffflog\uffffF\uffffcmd\uffffF\uffffpopen\uffffN\uffffcmd\uffffF\uffffloop\uffffF\uffffread\uffffN\uffffnil?\uffffN\uffffsplit\uffffN\uffff>\uffffN\uffffsize\uffffN\ufffflast\uffffN\uffffeach\uffffN\uffffparse_line\uffffF\uffffchomp\uffffN\uffffsplit\uffffN\ufffflast\uffffN\uffffeach\uffffN\uffffparse_line\uffffF\uffffchomp\uffffN\uffff===\uffffN\uffff[]\uffffN\uffffsplit\uffffN\ufffflog\uffffF\uffff===\uffffN\uffffto_i\uffffN\uffff[]\uffffN\ufffflast_match\uffffN\uffffto_i\uffffN\uffff[]\uffffN\ufffflast_match\uffffN\uffff>\uffffN\uffffprogress\uffffF\uffffround\uffffN\uffff+\uffffN\uffff/\uffffN\uffff*\uffffN\uffffto_f\uffffN\uffffto_f\uffffN\uffffcatch\uffffF\uffffmktmpdir\uffffN\ufffflog\uffffF\uffffdownload_jar!\uffffF\uffffinclude_jar!\uffffF\uffffprogress\uffffF\uffffrun!\uffffF\ufffffix_files_ownership_if_root\uffffF\ufffffix_files_ownership_if_root\uffffF\ufffflog\uffffF\uffffgod_help_me_delete_these_files_finally_i_dont_deserve_root\uffffF\uffff[]\uffffN\uffff[]\uffffN\ufffflog\uffffF\uffffeach\uffffN\uffffjar\uffffF\ufffflog\uffffF\uffffchown\uffffN\ufffflog\uffffF\uffff`\uffffF\uffff[]\uffffN\uffff[]\uffffN\ufffflog\uffffF\uffff`\uffffF\uffff\u0003\u0002\u0002\u0000\u0016\u0002\u0000\u0000\u0005\t\u0000\u0000 \u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(15, "", this.getEncoding0());
        this.setByteList(13, "java -jar ", this.getEncoding0());
        this.setByteList(19, "netalyzr id is: ", this.getEncoding0());
        this.setByteList(22, "SUDO_UID", this.getEncoding0());
        this.setByteList(25, "cleanly fixing: ", this.getEncoding0());
        this.setByteList(16, "\n", this.getEncoding0());
        this.setByteList(9, "requiring", this.getEncoding0());
        this.setByteList(20, "\\s(\\d+)\\/(\\d+)\\s", this.getEncoding0());
        this.setByteList(12, "error running netalyzr", this.getEncoding0());
        this.setByteList(10, "running", this.getEncoding0());
        this.setByteList(17, "\r", this.getEncoding0());
        this.setByteList(26, "dirty fixing: ", this.getEncoding0());
        this.setByteList(14, "executing: ", this.getEncoding0());
        this.setByteList(7, "downloading from: ", this.getEncoding0());
        this.setByteList(4, "netalyzr-cli.jar", this.getEncoding0());
        this.setByteList(21, "was not allowed to delete the tmpdir", this.getEncoding0());
        this.setByteList(11, "completed", this.getEncoding0());
        this.setByteList(27, "chown ", this.getEncoding0());
        this.setByteList(30, "rm -rf ", this.getEncoding0());
        this.setByteList(23, "SUDO_GID", this.getEncoding0());
        this.setByteList(0, "lister/measurements/netalyzr", this.getEncoding0());
        this.setByteList(5, "downloading", this.getEncoding0());
        this.setByteList(2, "open-uri", this.getEncoding0());
        this.setByteList(8, "could not download the tool properly", this.getEncoding0());
        this.setByteList(24, "fixing ownership of tempdir to work around JRuby bug", this.getEncoding0());
        this.setByteList(3, "http://netalyzr.icsi.berkeley.edu/NetalyzrCLI.jar", this.getEncoding0());
        this.setByteList(18, "^==== ID", this.getEncoding0());
        this.setByteList(31, "In a desperate attempt to delete these files, ", this.getEncoding0());
        this.setByteList(29, " -R ", this.getEncoding0());
        this.setByteList(28, ":", this.getEncoding0());
        this.setByteList(6, "wb", this.getEncoding0());
        this.setByteList(1, "tmpdir", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72 file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite0().call(threadContext, rubyObject, rubyObject, file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getString0(threadContext.runtime, 32));
        file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite1().call(threadContext, rubyObject, rubyObject, file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getString1(threadContext.runtime, 32));
        file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite2().call(threadContext, rubyObject, rubyObject, file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getString2(threadContext.runtime, 32));
        return module__0$RUBY$Lister(file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.module__1$RUBY$Measurements:(Lruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: aload_1        
        //    40: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    43: goto            51
        //    46: aload_1        
        //    47: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    50: athrow         
        //    51: aload_1        
        //    52: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    55: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     43     46     51     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject module__1$RUBY$Measurements(final FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "Measurements"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_0        
        //    30: aload_1        
        //    31: ldc_w           "Measurement"
        //    34: bipush          21
        //    36: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    42: invokestatic    ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.class_2$RUBY$Netalyzr:(Lruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: aload_1        
        //    46: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: goto            57
        //    52: aload_1        
        //    53: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    56: athrow         
        //    57: aload_1        
        //    58: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    61: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     49     52     57     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject class_2$RUBY$Netalyzr(final FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //     4: aload_2        
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareSuperClass:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyClass;
        //     8: aload_1        
        //     9: aload_1        
        //    10: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    13: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    16: swap           
        //    17: ldc             "Netalyzr"
        //    19: swap           
        //    20: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    23: dup            
        //    24: astore_2       
        //    25: aload_1        
        //    26: swap           
        //    27: aload_0        
        //    28: aload_1        
        //    29: ldc             ",0,0,-1"
        //    31: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    34: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    37: aload_0        
        //    38: aload_1        
        //    39: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    42: bipush          32
        //    44: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getString3:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    47: aload_1        
        //    48: ldc             "URL"
        //    50: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    53: pop            
        //    54: aload_1        
        //    55: aload_2        
        //    56: aload_0        
        //    57: ldc             "jar"
        //    59: ldc             "method__3$RUBY$jar"
        //    61: ldc             "path,1,0,-1"
        //    63: iconst_1       
        //    64: ldc             "./lib//lister/runner/measurements/netalyzr.rb"
        //    66: ldc             10
        //    68: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    71: ldc             "qpath"
        //    73: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    76: pop            
        //    77: aload_1        
        //    78: aload_2        
        //    79: aload_0        
        //    80: ldc_w           "download_jar!"
        //    83: ldc_w           "method__4$RUBY$download_jar_b_"
        //    86: ldc_w           "path;err,1,0,-1"
        //    89: iconst_1       
        //    90: ldc             "./lib//lister/runner/measurements/netalyzr.rb"
        //    92: ldc_w           14
        //    95: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    98: ldc             "qpath"
        //   100: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   103: pop            
        //   104: aload_1        
        //   105: aload_2        
        //   106: aload_0        
        //   107: ldc_w           "include_jar!"
        //   110: ldc_w           "method__7$RUBY$include_jar_b_"
        //   113: ldc             "path,1,0,-1"
        //   115: iconst_1       
        //   116: ldc             "./lib//lister/runner/measurements/netalyzr.rb"
        //   118: ldc_w           31
        //   121: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   124: ldc             "qpath"
        //   126: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   129: pop            
        //   130: aload_1        
        //   131: aload_2        
        //   132: aload_0        
        //   133: ldc_w           "run!"
        //   136: ldc_w           "method__8$RUBY$run_b_"
        //   139: ldc_w           "path;err,1,0,-1"
        //   142: iconst_1       
        //   143: ldc             "./lib//lister/runner/measurements/netalyzr.rb"
        //   145: ldc_w           35
        //   148: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   151: ldc             "qpath"
        //   153: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   156: pop            
        //   157: aload_1        
        //   158: aload_2        
        //   159: aload_0        
        //   160: ldc_w           "cmd"
        //   163: ldc_w           "method__11$RUBY$cmd"
        //   166: ldc             "path,1,0,-1"
        //   168: iconst_1       
        //   169: ldc             "./lib//lister/runner/measurements/netalyzr.rb"
        //   171: ldc_w           46
        //   174: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   177: ldc             "qpath"
        //   179: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   182: pop            
        //   183: aload_1        
        //   184: aload_2        
        //   185: aload_0        
        //   186: ldc_w           "do_run"
        //   189: ldc_w           "method__12$RUBY$do_run"
        //   192: ldc_w           "path;io;buf,1,0,-1"
        //   195: iconst_1       
        //   196: ldc             "./lib//lister/runner/measurements/netalyzr.rb"
        //   198: ldc_w           50
        //   201: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   204: ldc             "qpath"
        //   206: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   209: pop            
        //   210: aload_1        
        //   211: aload_2        
        //   212: aload_0        
        //   213: ldc_w           "parse_line"
        //   216: ldc_w           "method__13$RUBY$parse_line"
        //   219: ldc_w           "l;id;idx;tot,1,0,-1"
        //   222: iconst_1       
        //   223: ldc             "./lib//lister/runner/measurements/netalyzr.rb"
        //   225: ldc_w           74
        //   228: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   231: ldc_w           "ql"
        //   234: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   237: pop            
        //   238: aload_1        
        //   239: aload_2        
        //   240: aload_0        
        //   241: ldc_w           "execute"
        //   244: ldc_w           "method__14$RUBY$execute"
        //   247: ldc_w           "tdir,0,0,-1"
        //   250: iconst_0       
        //   251: ldc             "./lib//lister/runner/measurements/netalyzr.rb"
        //   253: ldc_w           88
        //   256: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   259: ldc_w           "NONE"
        //   262: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   265: pop            
        //   266: aload_1        
        //   267: aload_2        
        //   268: aload_0        
        //   269: ldc_w           "fix_files_ownership_if_root"
        //   272: ldc_w           "method__18$RUBY$fix_files_ownership_if_root"
        //   275: ldc_w           "path;uid;gid,1,0,-1"
        //   278: iconst_1       
        //   279: ldc             "./lib//lister/runner/measurements/netalyzr.rb"
        //   281: ldc_w           113
        //   284: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   287: ldc             "qpath"
        //   289: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   292: pop            
        //   293: aload_1        
        //   294: aload_2        
        //   295: aload_0        
        //   296: ldc_w           "god_help_me_delete_these_files_finally_i_dont_deserve_root"
        //   299: ldc_w           "method__21$RUBY$god_help_me_delete_these_files_finally_i_dont_deserve_root"
        //   302: ldc_w           "dir;uid;gid;cmd,1,0,-1"
        //   305: iconst_1       
        //   306: ldc             "./lib//lister/runner/measurements/netalyzr.rb"
        //   308: ldc_w           132
        //   311: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   314: ldc_w           "qdir"
        //   317: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   320: aload_1        
        //   321: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   324: goto            332
        //   327: aload_1        
        //   328: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   331: athrow         
        //   332: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  37     320    327    332    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "jar", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__3$RUBY$jar(final FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72 file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72, final ThreadContext context, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite3().call(context, rubyObject, file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getConstant0(context, "File"), rubyObject2, file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getString4(context.runtime, 32));
    }
    
    @JRubyMethod(name = "download_jar!", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$download_jar_b_(final FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72 file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject valueZeroDepthZero, final Block block) {
        threadContext.getCurrentScope().setValueZeroDepthZero(valueZeroDepthZero);
        return chained_5_rescue_1$RUBY$SYNTHETICdownload_jar_b_(file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72, threadContext, rubyObject, valueZeroDepthZero, block);
    }
    
    public static IRubyObject chained_5_rescue_1$RUBY$SYNTHETICdownload_jar_b_(final FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72 file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72, final ThreadContext context, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        final DynamicScope currentScope = context.getCurrentScope();
        final IRubyObject errorInfo = context.getErrorInfo();
        IRubyObject rubyObject3 = null;
        Label_0156: {
            try {
                try {
                    file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.setVariable0(context.runtime, "@status", rubyObject, file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getString5(context.runtime, 32));
                    rubyObject3 = file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite4().callIter(context, rubyObject, file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getConstant1(context, "File"), file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite5().call(context, rubyObject, rubyObject, currentScope.getValueZeroDepthZeroOrNil(context.nil)), file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getString6(context.runtime, 32), RuntimeHelpers.createBlock(context, rubyObject, file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getBlockBody2(context, "block_0$RUBY$download_jar!,1,f,false,2,./lib//lister/runner/measurements/netalyzr.rb,17,false")));
                }
                catch (JumpException.FlowControlException ex) {
                    throw ex;
                }
                catch (Throwable t) {
                    if (RuntimeHelpers.isJavaExceptionHandled(t, file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getConstant4(context, "Exception"), context).isTrue()) {
                        RuntimeHelpers.storeExceptionInErrorInfo(t, context);
                        rubyObject3 = chained_6_rescue_line_25(file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72, context, rubyObject, rubyObject2, block);
                        RuntimeHelpers.clearErrorInfo(context);
                        break Label_0156;
                    }
                    throw t;
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
    
    public static IRubyObject block_0$RUBY$download_jar!(final FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72 file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72, final ThreadContext threadContext, final IRubyObject self, final IRubyObject valueZeroDepthZero, final Block block) {
        final DynamicScope currentScope = threadContext.getCurrentScope();
        currentScope.setValueZeroDepthZero(threadContext.nil);
        RuntimeHelpers.processBlockArgument(threadContext.runtime, block);
        currentScope.setValueZeroDepthZero(valueZeroDepthZero);
        file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite6().call(threadContext, self, self, RubyString.newStringLight(threadContext.runtime, 20).append(file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getString7(threadContext.runtime, 32)).append(file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getConstant2(threadContext, "URL").asString()));
        return file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite7().callIter(threadContext, self, self, file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getConstant3(threadContext, "URL"), RuntimeHelpers.createBlock(threadContext, self, file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getBlockBody1(threadContext, "block_1$RUBY$download_jar!,1,tmp,false,2,./lib//lister/runner/measurements/netalyzr.rb,19,false")));
    }
    
    public static IRubyObject block_1$RUBY$download_jar!(final FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    16: pop            
        //    17: aload_1        
        //    18: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    21: aload           4
        //    23: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    26: aload_3        
        //    27: aload           5
        //    29: swap           
        //    30: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: pop            
        //    34: pop            
        //    35: aload_0        
        //    36: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite8:()Lorg/jruby/runtime/CallSite;
        //    39: aload_1        
        //    40: aload_2        
        //    41: aload           locals
        //    43: aload_1        
        //    44: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: aload_1        
        //    51: aload_2        
        //    52: aload_0        
        //    53: aload_1        
        //    54: ldc_w           "block_2$RUBY$download_jar!,1,l,false,2,./lib//lister/runner/measurements/netalyzr.rb,20,true"
        //    57: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getBlockBody0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    60: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    63: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    66: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     32      5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_2$RUBY$download_jar!(final FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite9:()Lorg/jruby/runtime/CallSite;
        //    29: aload_1        
        //    30: aload_2        
        //    31: aload           5
        //    33: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    36: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    39: aload_1        
        //    40: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    43: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    46: aload           l
        //    48: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    51: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     27      9     l     Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject chained_6_rescue_line_25(final FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72 file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        threadContext.getCurrentScope().setValueOneDepthZero(RuntimeHelpers.getGlobalVariable(threadContext.runtime, "$!"));
        file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite(10).call(threadContext, rubyObject, rubyObject, file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getString8(threadContext.runtime, 32), file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getSymbol0(threadContext.runtime, "error"));
        return file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite(11).call(threadContext, rubyObject, rubyObject, file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getSymbol1(threadContext.runtime, "stop"));
    }
    
    @JRubyMethod(name = "include_jar!", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__7$RUBY$include_jar_b_(final FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72 file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72, final ThreadContext threadContext, final IRubyObject object, final IRubyObject rubyObject, final Block block) {
        return file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.setVariable1(threadContext.runtime, "@status", object, file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getString9(threadContext.runtime, 32));
    }
    
    @JRubyMethod(name = "run!", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__8$RUBY$run_b_(final FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72 file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject valueZeroDepthZero, final Block block) {
        threadContext.getCurrentScope().setValueZeroDepthZero(valueZeroDepthZero);
        return chained_9_rescue_2$RUBY$SYNTHETICrun_b_(file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72, threadContext, rubyObject, valueZeroDepthZero, block);
    }
    
    public static IRubyObject chained_9_rescue_2$RUBY$SYNTHETICrun_b_(final FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72 file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        final DynamicScope currentScope = threadContext.getCurrentScope();
        final IRubyObject errorInfo = threadContext.getErrorInfo();
        IRubyObject rubyObject3 = null;
        Label_0145: {
            try {
                try {
                    file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.setVariable2(threadContext.runtime, "@status", rubyObject, file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getString(threadContext.runtime, 10, 32));
                    file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite(12).call(threadContext, rubyObject, rubyObject, currentScope.getValueZeroDepthZeroOrNil(threadContext.nil));
                    rubyObject3 = file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.setVariable3(threadContext.runtime, "@status", rubyObject, file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getString(threadContext.runtime, 11, 32));
                }
                catch (JumpException.FlowControlException ex) {
                    throw ex;
                }
                catch (Throwable t) {
                    if (RuntimeHelpers.isJavaExceptionHandled(t, file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getConstant5(threadContext, "Exception"), threadContext).isTrue()) {
                        RuntimeHelpers.storeExceptionInErrorInfo(t, threadContext);
                        rubyObject3 = chained_10_rescue_line_40(file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72, threadContext, rubyObject, rubyObject2, block);
                        RuntimeHelpers.clearErrorInfo(threadContext);
                        break Label_0145;
                    }
                    throw t;
                }
            }
            catch (JumpException.FlowControlException ex2) {
                threadContext.setErrorInfo(errorInfo);
                throw ex2;
            }
        }
        threadContext.setErrorInfo(errorInfo);
        return rubyObject3;
    }
    
    public static IRubyObject chained_10_rescue_line_40(final FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72 file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        threadContext.getCurrentScope().setValueOneDepthZero(RuntimeHelpers.getGlobalVariable(threadContext.runtime, "$!"));
        file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite(13).call(threadContext, rubyObject, rubyObject, file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getString(threadContext.runtime, 12, 32), file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getSymbol0(threadContext.runtime, "error"));
        return file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite(14).call(threadContext, rubyObject, rubyObject, file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getSymbol1(threadContext.runtime, "stop"));
    }
    
    @JRubyMethod(name = "cmd", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__11$RUBY$cmd(final FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72 file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return RubyString.newStringLight(threadContext.runtime, 20).append(file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getString(threadContext.runtime, 13, 32)).append(file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite(15).call(threadContext, rubyObject, rubyObject, rubyObject2).asString());
    }
    
    @JRubyMethod(name = "do_run", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__12$RUBY$do_run(final FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    17: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    20: aload_1        
        //    21: aload_2        
        //    22: aload_2        
        //    23: aload_1        
        //    24: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    27: ldc             20
        //    29: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    32: aload_0        
        //    33: aload_1        
        //    34: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    37: bipush          14
        //    39: bipush          32
        //    41: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //    44: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //    47: aload_0        
        //    48: bipush          17
        //    50: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    53: aload_1        
        //    54: aload_2        
        //    55: aload_2        
        //    56: aload           locals
        //    58: aload_1        
        //    59: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    62: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    65: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    68: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //    73: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //    76: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    79: pop            
        //    80: aload           locals
        //    82: aload_0        
        //    83: bipush          18
        //    85: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    88: aload_1        
        //    89: aload_2        
        //    90: aload_0        
        //    91: aload_1        
        //    92: ldc_w           "IO"
        //    95: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getConstant6:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    98: aload_0        
        //    99: bipush          19
        //   101: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   104: aload_1        
        //   105: aload_2        
        //   106: aload_2        
        //   107: aload           locals
        //   109: aload_1        
        //   110: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   113: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   116: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   119: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   122: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   125: pop            
        //   126: aload           locals
        //   128: aload_0        
        //   129: aload_1        
        //   130: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   133: bipush          15
        //   135: bipush          32
        //   137: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   140: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   143: pop            
        //   144: aload_0        
        //   145: bipush          20
        //   147: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   150: aload_1        
        //   151: aload_2        
        //   152: aload_2        
        //   153: aload_1        
        //   154: aload_2        
        //   155: aload_0        
        //   156: aload_1        
        //   157: ldc_w           "block_3$RUBY$do_run,-1,str;lines;pseudo_lines,false,0,./lib//lister/runner/measurements/netalyzr.rb,55,false"
        //   160: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getBlockBody5:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   163: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   166: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   169: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     156     5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_3$RUBY$do_run(final FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    39: pop            
        //    40: pop            
        //    41: aload           locals
        //    43: aload_0        
        //    44: bipush          21
        //    46: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    49: aload_1        
        //    50: aload_2        
        //    51: aload           locals
        //    53: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    56: aload_1        
        //    57: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    60: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    63: aload_0        
        //    64: aload_1        
        //    65: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    68: bipush          80
        //    70: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getFixnum0:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //    73: aload           locals
        //    75: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    78: aload_1        
        //    79: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    82: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    85: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    88: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    91: pop            
        //    92: aload_0        
        //    93: bipush          22
        //    95: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    98: aload_1        
        //    99: aload_2        
        //   100: aload           locals
        //   102: aload_1        
        //   103: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   106: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   109: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   112: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   117: ifeq            132
        //   120: aload_1        
        //   121: aload_1        
        //   122: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   125: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.breakJump:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   128: pop            
        //   129: goto            132
        //   132: aload           locals
        //   134: aload_0        
        //   135: bipush          23
        //   137: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   140: aload_1        
        //   141: aload_2        
        //   142: aload           locals
        //   144: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   147: aload_1        
        //   148: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   151: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   154: aload_0        
        //   155: aload_1        
        //   156: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   159: bipush          16
        //   161: bipush          32
        //   163: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   166: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   169: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   172: pop            
        //   173: aload_0        
        //   174: bipush          24
        //   176: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   179: aload_1        
        //   180: aload_2        
        //   181: aload_0        
        //   182: bipush          25
        //   184: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   187: aload_1        
        //   188: aload_2        
        //   189: aload           locals
        //   191: aload_1        
        //   192: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   195: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   198: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   201: ldc2_w          1
        //   204: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;J)Lorg/jruby/runtime/builtin/IRubyObject;
        //   207: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   212: ifeq            280
        //   215: aload           locals
        //   217: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   220: aload_0        
        //   221: bipush          26
        //   223: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   226: aload_1        
        //   227: aload_2        
        //   228: aload           locals
        //   230: aload_1        
        //   231: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   234: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   237: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   240: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   243: pop            
        //   244: aload_0        
        //   245: bipush          27
        //   247: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   250: aload_1        
        //   251: aload_2        
        //   252: aload           locals
        //   254: aload_1        
        //   255: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   258: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   261: aload_1        
        //   262: aload_2        
        //   263: aload_0        
        //   264: aload_1        
        //   265: ldc_w           "block_4$RUBY$do_run,1,l,false,2,./lib//lister/runner/measurements/netalyzr.rb,61,true"
        //   268: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getBlockBody3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   271: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   274: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   277: goto            383
        //   280: aload           locals
        //   282: aload_0        
        //   283: bipush          30
        //   285: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   288: aload_1        
        //   289: aload_2        
        //   290: aload           locals
        //   292: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   295: aload_1        
        //   296: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   299: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   302: aload_0        
        //   303: aload_1        
        //   304: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   307: bipush          17
        //   309: bipush          32
        //   311: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   314: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   317: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   320: pop            
        //   321: aload           locals
        //   323: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   326: aload_0        
        //   327: bipush          31
        //   329: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   332: aload_1        
        //   333: aload_2        
        //   334: aload           locals
        //   336: aload_1        
        //   337: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   340: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   343: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   346: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   349: pop            
        //   350: aload_0        
        //   351: bipush          32
        //   353: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   356: aload_1        
        //   357: aload_2        
        //   358: aload           locals
        //   360: aload_1        
        //   361: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   364: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   367: aload_1        
        //   368: aload_2        
        //   369: aload_0        
        //   370: aload_1        
        //   371: ldc_w           "block_5$RUBY$do_run,1,l,false,2,./lib//lister/runner/measurements/netalyzr.rb,67,true"
        //   374: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getBlockBody4:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   377: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   380: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   383: areturn        
        //   384: pop            
        //   385: goto            41
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  41     343     5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  41     384    384    388    Lorg/jruby/exceptions/JumpException$RedoJump;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_4$RUBY$do_run(final FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          28
        //    28: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_2        
        //    34: aload_0        
        //    35: bipush          29
        //    37: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    40: aload_1        
        //    41: aload_2        
        //    42: aload           l
        //    44: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     26      9     l     Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_5$RUBY$do_run(final FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          33
        //    28: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_2        
        //    34: aload_0        
        //    35: bipush          34
        //    37: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    40: aload_1        
        //    41: aload_2        
        //    42: aload           l
        //    44: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     26      9     l     Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "parse_line", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__13$RUBY$parse_line(final FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    14: aload           locals
        //    16: aload_1        
        //    17: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    20: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    23: aload_1        
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.pollThreadEvents:()V
        //    27: astore          9
        //    29: aload_0        
        //    30: bipush          35
        //    32: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    35: aload_1        
        //    36: aload_2        
        //    37: aload           9
        //    39: aload_0        
        //    40: aload_1        
        //    41: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    44: aload_0        
        //    45: bipush          18
        //    47: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getByteList:(I)Lorg/jruby/util/ByteList;
        //    50: ldc_w           512
        //    53: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getRegexp0:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //    56: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //    59: ifeq            184
        //    62: aload           locals
        //    64: aload_0        
        //    65: bipush          36
        //    67: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    70: aload_1        
        //    71: aload_2        
        //    72: aload_0        
        //    73: bipush          37
        //    75: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    78: aload_1        
        //    79: aload_2        
        //    80: aload           locals
        //    82: aload_1        
        //    83: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    86: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    89: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    92: aload_1        
        //    93: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    96: invokestatic    org/jruby/RubyFixnum.two:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //    99: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   102: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   105: pop            
        //   106: aload_0        
        //   107: bipush          38
        //   109: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   112: aload_1        
        //   113: aload_2        
        //   114: aload_2        
        //   115: aload_1        
        //   116: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   119: ldc             20
        //   121: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   124: aload_0        
        //   125: aload_1        
        //   126: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   129: bipush          19
        //   131: bipush          32
        //   133: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   136: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   139: aload           locals
        //   141: aload_1        
        //   142: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   145: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   148: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   153: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   156: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   159: pop            
        //   160: aload_0        
        //   161: aload_1        
        //   162: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   165: ldc_w           "@netalyzr_report_id"
        //   168: aload_2        
        //   169: aload           locals
        //   171: aload_1        
        //   172: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   175: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   178: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.setVariable4:(Lorg/jruby/Ruby;Ljava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   181: goto            485
        //   184: aload_0        
        //   185: bipush          39
        //   187: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   190: aload_1        
        //   191: aload_2        
        //   192: aload           9
        //   194: aload_0        
        //   195: aload_1        
        //   196: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   199: aload_0        
        //   200: bipush          20
        //   202: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getByteList:(I)Lorg/jruby/util/ByteList;
        //   205: ldc_w           512
        //   208: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getRegexp1:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   211: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //   214: ifeq            481
        //   217: aload           locals
        //   219: aload_0        
        //   220: bipush          40
        //   222: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   225: aload_1        
        //   226: aload_2        
        //   227: aload_0        
        //   228: bipush          41
        //   230: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   233: aload_1        
        //   234: aload_2        
        //   235: aload_0        
        //   236: bipush          42
        //   238: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   241: aload_1        
        //   242: aload_2        
        //   243: aload_0        
        //   244: aload_1        
        //   245: ldc_w           "Regexp"
        //   248: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getConstant7:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   251: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   254: aload_1        
        //   255: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   258: invokestatic    org/jruby/RubyFixnum.one:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   261: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   264: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   267: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   270: pop            
        //   271: aload           locals
        //   273: aload_0        
        //   274: bipush          43
        //   276: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   279: aload_1        
        //   280: aload_2        
        //   281: aload_0        
        //   282: bipush          44
        //   284: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   287: aload_1        
        //   288: aload_2        
        //   289: aload_0        
        //   290: bipush          45
        //   292: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   295: aload_1        
        //   296: aload_2        
        //   297: aload_0        
        //   298: aload_1        
        //   299: ldc_w           "Regexp"
        //   302: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getConstant8:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   305: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   308: aload_1        
        //   309: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   312: invokestatic    org/jruby/RubyFixnum.two:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   315: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   318: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   321: invokevirtual   org/jruby/runtime/DynamicScope.setValueThreeDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   324: pop            
        //   325: aload_0        
        //   326: bipush          46
        //   328: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   331: aload_1        
        //   332: aload_2        
        //   333: aload           locals
        //   335: aload_1        
        //   336: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   339: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   342: ldc2_w          1
        //   345: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;J)Lorg/jruby/runtime/builtin/IRubyObject;
        //   348: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   353: ifeq            359
        //   356: goto            365
        //   359: aload_1        
        //   360: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   363: areturn        
        //   364: athrow         
        //   365: aload_0        
        //   366: bipush          47
        //   368: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   371: aload_1        
        //   372: aload_2        
        //   373: aload_2        
        //   374: aload_0        
        //   375: bipush          48
        //   377: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   380: aload_1        
        //   381: aload_2        
        //   382: aload_0        
        //   383: bipush          49
        //   385: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   388: aload_1        
        //   389: aload_2        
        //   390: aload_1        
        //   391: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   394: invokestatic    org/jruby/RubyFixnum.five:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   397: aload_0        
        //   398: bipush          50
        //   400: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   403: aload_1        
        //   404: aload_2        
        //   405: aload_0        
        //   406: bipush          51
        //   408: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   411: aload_1        
        //   412: aload_2        
        //   413: aload_0        
        //   414: aload_1        
        //   415: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   418: bipush          95
        //   420: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getFixnum1:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   423: aload_0        
        //   424: bipush          52
        //   426: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   429: aload_1        
        //   430: aload_2        
        //   431: aload           locals
        //   433: aload_1        
        //   434: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   437: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   440: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   443: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   446: aload_0        
        //   447: bipush          53
        //   449: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   452: aload_1        
        //   453: aload_2        
        //   454: aload           locals
        //   456: aload_1        
        //   457: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   460: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   463: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   466: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   469: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   472: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   475: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   478: goto            485
        //   481: aload_1        
        //   482: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   485: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     472     5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "execute", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__14$RUBY$execute(final FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72 file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(threadContext.nil);
        return chained_15_rescue_3$RUBY$SYNTHETICexecute(file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72, threadContext, rubyObject, block);
    }
    
    public static IRubyObject chained_15_rescue_3$RUBY$SYNTHETICexecute(final FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72 file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72, final ThreadContext context, final IRubyObject self, final Block block) {
        context.getCurrentScope();
        final IRubyObject errorInfo = context.getErrorInfo();
        IRubyObject rubyObject = null;
        Label_0126: {
            try {
                try {
                    rubyObject = file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite(54).callIter(context, self, self, file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getSymbol1(context.runtime, "stop"), RuntimeHelpers.createBlock(context, self, file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getBlockBody7(context, "block_6$RUBY$execute,-1,,false,0,./lib//lister/runner/measurements/netalyzr.rb,91,false")));
                }
                catch (JumpException.FlowControlException ex) {
                    throw ex;
                }
                catch (Throwable t) {
                    if (RuntimeHelpers.isJavaExceptionHandled(t, file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getConstantFrom(RuntimeHelpers.checkIsModule(file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getConstant(context, "Errno", 10)), context, "EACCES", 11), context).isTrue()) {
                        RuntimeHelpers.storeExceptionInErrorInfo(t, context);
                        rubyObject = chained_17_rescue_line_107(file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72, context, self, block);
                        RuntimeHelpers.clearErrorInfo(context);
                        break Label_0126;
                    }
                    throw t;
                }
            }
            catch (JumpException.FlowControlException ex2) {
                context.setErrorInfo(errorInfo);
                throw ex2;
            }
        }
        context.setErrorInfo(errorInfo);
        return rubyObject;
    }
    
    public static IRubyObject block_6$RUBY$execute(final FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          5
        //     6: aload_1        
        //     7: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    10: aload           4
        //    12: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    15: aload_3        
        //    16: pop            
        //    17: pop            
        //    18: aload_0        
        //    19: bipush          55
        //    21: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    24: aload_1        
        //    25: aload_2        
        //    26: aload_0        
        //    27: aload_1        
        //    28: ldc_w           "Dir"
        //    31: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getConstant9:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    34: aload_1        
        //    35: aload_2        
        //    36: aload_0        
        //    37: aload_1        
        //    38: ldc_w           "block_7$RUBY$execute,1,path,false,2,./lib//lister/runner/measurements/netalyzr.rb,92,false"
        //    41: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getBlockBody6:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    44: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    47: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: pop            
        //    51: aload           locals
        //    53: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    56: aload_1        
        //    57: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    60: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    63: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  18     46      5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_7$RUBY$execute(final FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    16: pop            
        //    17: aload_1        
        //    18: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    21: aload           4
        //    23: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    26: aload_3        
        //    27: aload           5
        //    29: swap           
        //    30: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: pop            
        //    34: pop            
        //    35: aload           locals
        //    37: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    40: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    43: aload           locals
        //    45: aload_1        
        //    46: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    52: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    55: pop            
        //    56: aload_0        
        //    57: aload_1        
        //    58: aload_2        
        //    59: aload_3        
        //    60: aload           4
        //    62: invokestatic    ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.chained_16_ensure_1$RUBY$__ensure__:(Lruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    65: areturn        
        //    66: pop            
        //    67: goto            35
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     31      5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  35     66     66     70     Lorg/jruby/exceptions/JumpException$RedoJump;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject chained_16_ensure_1$RUBY$__ensure__(final FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72 file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        final DynamicScope currentScope = threadContext.getCurrentScope();
        try {
            file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite(56).call(threadContext, rubyObject, rubyObject, RubyString.newStringLight(threadContext.runtime, 20).append(currentScope.getValueZeroDepthZeroOrNil(threadContext.nil).asString()));
            file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite(57).call(threadContext, rubyObject, rubyObject, currentScope.getValueZeroDepthZeroOrNil(threadContext.nil));
            file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite(58).call(threadContext, rubyObject, rubyObject, currentScope.getValueZeroDepthZeroOrNil(threadContext.nil));
            file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite(59).call(threadContext, rubyObject, rubyObject, RubyFixnum.five(threadContext.runtime));
            final IRubyObject call = file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite(60).call(threadContext, rubyObject, rubyObject, currentScope.getValueZeroDepthZeroOrNil(threadContext.nil));
            file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite(61).call(threadContext, rubyObject, rubyObject, currentScope.getValueZeroDepthZeroOrNil(threadContext.nil));
            return call;
        }
        finally {
            file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite(62).call(threadContext, rubyObject, rubyObject, currentScope.getValueZeroDepthZeroOrNil(threadContext.nil));
        }
    }
    
    public static IRubyObject chained_17_rescue_line_107(final FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72 file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        final DynamicScope currentScope = threadContext.getCurrentScope();
        file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite(63).call(threadContext, rubyObject, rubyObject, file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getString(threadContext.runtime, 21, 32));
        return file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite(64).call(threadContext, rubyObject, rubyObject, currentScope.getValueZeroDepthZeroOrNil(threadContext.nil));
    }
    
    @JRubyMethod(name = "fix_files_ownership_if_root", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__18$RUBY$fix_files_ownership_if_root(final FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    14: aload           locals
        //    16: aload_0        
        //    17: bipush          65
        //    19: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    22: aload_1        
        //    23: aload_2        
        //    24: aload_0        
        //    25: aload_1        
        //    26: ldc_w           "ENV"
        //    29: bipush          12
        //    31: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    34: aload_0        
        //    35: aload_1        
        //    36: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    39: bipush          22
        //    41: bipush          32
        //    43: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //    46: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    52: pop            
        //    53: aload           locals
        //    55: aload_0        
        //    56: bipush          66
        //    58: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    61: aload_1        
        //    62: aload_2        
        //    63: aload_0        
        //    64: aload_1        
        //    65: ldc_w           "ENV"
        //    68: bipush          13
        //    70: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    73: aload_0        
        //    74: aload_1        
        //    75: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    78: bipush          23
        //    80: bipush          32
        //    82: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //    85: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    88: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    91: pop            
        //    92: aload           locals
        //    94: aload_1        
        //    95: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    98: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   101: dup            
        //   102: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   107: ifeq            120
        //   110: pop            
        //   111: aload           locals
        //   113: aload_1        
        //   114: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   117: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   120: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   125: ifeq            217
        //   128: aload_0        
        //   129: bipush          67
        //   131: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   134: aload_1        
        //   135: aload_2        
        //   136: aload_2        
        //   137: aload_0        
        //   138: aload_1        
        //   139: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   142: bipush          24
        //   144: bipush          32
        //   146: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   149: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   152: pop            
        //   153: aload_0        
        //   154: bipush          68
        //   156: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   159: aload_1        
        //   160: aload_2        
        //   161: aload_1        
        //   162: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   165: aload_0        
        //   166: bipush          69
        //   168: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   171: aload_1        
        //   172: aload_2        
        //   173: aload_2        
        //   174: aload           locals
        //   176: aload_1        
        //   177: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   180: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   183: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   186: aload           locals
        //   188: aload_1        
        //   189: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   192: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   195: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   198: aload_1        
        //   199: aload_2        
        //   200: aload_0        
        //   201: aload_1        
        //   202: ldc_w           "block_8$RUBY$fix_files_ownership_if_root,1,name;cmd,false,2,./lib//lister/runner/measurements/netalyzr.rb,118,false"
        //   205: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getBlockBody8:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   208: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   211: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   214: goto            221
        //   217: aload_1        
        //   218: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   221: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     208     5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_8$RUBY$fix_files_ownership_if_root(final FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72 file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject valueZeroDepthZero, final Block block) {
        final DynamicScope currentScope = threadContext.getCurrentScope();
        currentScope.setValueOneDepthZero(currentScope.setValueZeroDepthZero(threadContext.nil));
        RuntimeHelpers.processBlockArgument(threadContext.runtime, block);
        currentScope.setValueZeroDepthZero(valueZeroDepthZero);
        try {
            return chained_19_rescue_4$RUBY$SYNTHETICfix_files_ownership_if_root(file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72, threadContext, rubyObject, valueZeroDepthZero, block);
        }
        catch (JumpException.RedoJump redoJump) {
            return chained_19_rescue_4$RUBY$SYNTHETICfix_files_ownership_if_root(file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72, threadContext, rubyObject, valueZeroDepthZero, block);
        }
    }
    
    public static IRubyObject chained_19_rescue_4$RUBY$SYNTHETICfix_files_ownership_if_root(final FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72 file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        final DynamicScope currentScope = threadContext.getCurrentScope();
        final IRubyObject errorInfo = threadContext.getErrorInfo();
        IRubyObject rubyObject3;
        try {
            try {
                file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite(70).call(threadContext, rubyObject, rubyObject, RubyString.newStringLight(threadContext.runtime, 20).append(file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getString(threadContext.runtime, 25, 32)).append(currentScope.getValueZeroDepthZeroOrNil(threadContext.nil).asString()));
                rubyObject3 = file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite(71).call(threadContext, rubyObject, file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getConstant(threadContext, "FileUtils", 14), currentScope.getNextCapturedScope().getValueOneDepthZeroOrNil(threadContext.nil), currentScope.getNextCapturedScope().getValueTwoDepthZeroOrNil(threadContext.nil), currentScope.getValueZeroDepthZeroOrNil(threadContext.nil));
            }
            catch (JumpException.FlowControlException ex) {
                throw ex;
            }
            catch (Throwable currentThrowable) {
                if (RuntimeHelpers.isJavaExceptionHandled(currentThrowable, file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getConstantFrom(RuntimeHelpers.checkIsModule(file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getConstant(threadContext, "Errno", 15)), threadContext, "ENOENT", 16), threadContext).isTrue()) {
                    rubyObject3 = threadContext.nil;
                    RuntimeHelpers.clearErrorInfo(threadContext);
                }
                else {
                    if (!RuntimeHelpers.isJavaExceptionHandled(currentThrowable, file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getConstantFrom(RuntimeHelpers.checkIsModule(file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getConstant(threadContext, "Errno", 17)), threadContext, "EACCES", 18), threadContext).isTrue()) {
                        throw currentThrowable;
                    }
                    RuntimeHelpers.storeExceptionInErrorInfo(currentThrowable, threadContext);
                    rubyObject3 = chained_20_rescue_line_123(file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72, threadContext, rubyObject, rubyObject2, block);
                    RuntimeHelpers.clearErrorInfo(threadContext);
                }
            }
        }
        catch (JumpException.FlowControlException ex2) {
            threadContext.setErrorInfo(errorInfo);
            throw ex2;
        }
        threadContext.setErrorInfo(errorInfo);
        return rubyObject3;
    }
    
    public static IRubyObject chained_20_rescue_line_123(final FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72 file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        final DynamicScope currentScope = threadContext.getCurrentScope();
        file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite(72).call(threadContext, rubyObject, rubyObject, RubyString.newStringLight(threadContext.runtime, 20).append(file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getString(threadContext.runtime, 26, 32)).append(currentScope.getValueZeroDepthZeroOrNil(threadContext.nil).asString()));
        currentScope.setValueOneDepthZero(RubyString.newStringLight(threadContext.runtime, 20).append(file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getString(threadContext.runtime, 27, 32)).append(currentScope.getNextCapturedScope().getValueOneDepthZeroOrNil(threadContext.nil).asString()).append(file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getString(threadContext.runtime, 28, 32)).append(currentScope.getNextCapturedScope().getValueTwoDepthZeroOrNil(threadContext.nil).asString()).append(file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getString(threadContext.runtime, 29, 32)).append(currentScope.getValueZeroDepthZeroOrNil(threadContext.nil).asString()));
        return file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite(73).call(threadContext, rubyObject, rubyObject, RubyString.newStringLight(threadContext.runtime, 20).append(currentScope.getValueOneDepthZeroOrNil(threadContext.nil).asString()));
    }
    
    @JRubyMethod(name = "god_help_me_delete_these_files_finally_i_dont_deserve_root", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__21$RUBY$god_help_me_delete_these_files_finally_i_dont_deserve_root(final FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    14: aload           locals
        //    16: aload_0        
        //    17: bipush          74
        //    19: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    22: aload_1        
        //    23: aload_2        
        //    24: aload_0        
        //    25: aload_1        
        //    26: ldc_w           "ENV"
        //    29: bipush          19
        //    31: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    34: aload_0        
        //    35: aload_1        
        //    36: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    39: bipush          22
        //    41: bipush          32
        //    43: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //    46: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    52: pop            
        //    53: aload           locals
        //    55: aload_0        
        //    56: bipush          75
        //    58: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    61: aload_1        
        //    62: aload_2        
        //    63: aload_0        
        //    64: aload_1        
        //    65: ldc_w           "ENV"
        //    68: bipush          20
        //    70: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    73: aload_0        
        //    74: aload_1        
        //    75: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    78: bipush          23
        //    80: bipush          32
        //    82: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //    85: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    88: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    91: pop            
        //    92: aload           locals
        //    94: aload_1        
        //    95: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    98: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   101: dup            
        //   102: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   107: ifeq            120
        //   110: pop            
        //   111: aload           locals
        //   113: aload_1        
        //   114: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   117: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   120: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   125: ifeq            270
        //   128: aload           locals
        //   130: aload_1        
        //   131: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   134: ldc             20
        //   136: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   139: aload_0        
        //   140: aload_1        
        //   141: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   144: bipush          30
        //   146: bipush          32
        //   148: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   151: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   154: aload           locals
        //   156: aload_1        
        //   157: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   160: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   163: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   168: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   171: invokevirtual   org/jruby/runtime/DynamicScope.setValueThreeDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   174: pop            
        //   175: aload_0        
        //   176: bipush          76
        //   178: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   181: aload_1        
        //   182: aload_2        
        //   183: aload_2        
        //   184: aload_1        
        //   185: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   188: ldc             20
        //   190: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   193: aload_0        
        //   194: aload_1        
        //   195: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   198: bipush          31
        //   200: bipush          32
        //   202: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   205: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   208: aload           locals
        //   210: aload_1        
        //   211: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   214: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   217: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   222: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   225: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   228: pop            
        //   229: aload_0        
        //   230: bipush          77
        //   232: invokevirtual   ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   235: aload_1        
        //   236: aload_2        
        //   237: aload_2        
        //   238: aload_1        
        //   239: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   242: ldc             20
        //   244: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   247: aload           locals
        //   249: aload_1        
        //   250: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   253: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   256: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   261: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   264: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   267: goto            274
        //   270: aload_1        
        //   271: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   274: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     261     5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject class_2$RUBY$Netalyzr(final FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72 file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_2$RUBY$Netalyzr(file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__1$RUBY$Measurements(final FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72 file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Measurements(file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72 file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72, threadContext, rubyObject, block);
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
        final FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72 file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72 = new FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72();
        final String string = FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.class.getClassLoader().getResource("ruby/jit/FILE_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.class").toString();
        file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_907FDA75A736BC285EB0C43E3CCFF4ADBF9E0E72.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
