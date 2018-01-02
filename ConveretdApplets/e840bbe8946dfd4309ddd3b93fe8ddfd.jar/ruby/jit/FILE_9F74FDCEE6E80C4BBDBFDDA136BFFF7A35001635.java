// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.Arity;
import org.jruby.RubyBoolean;
import org.jruby.runtime.CallSite;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635 extends AbstractScript
{
    public FILE_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635() {
        this.filename = "./lib//lister/runner/measurements/poll.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffinclude\uffffF\uffffattr_reader\uffffF\uffffmap\uffffN\uffffquestions\uffffN\uffffclass\uffffN\uffffquestion_for_description\uffffN\uffffinterpret\uffffN\uffffrunner\uffffV\uffffnew\uffffN\uffffquestions\uffffV\uffffinterpret\uffffN\uffffrunner\uffffV\uffffstart\uffffN\uffffquestionnaire\uffffV\uffffadd\uffffN\uffffpoll_panel\uffffN\uffffrunner\uffffV\uffffquestionnaire\uffffV\uffffframe\uffffN\uffffrunner\uffffV\uffffvisible=\uffffN\uffffvisible=\uffffV\uffffwait_questionnaire\uffffN\uffffquestionnaire\uffffV\uffffquestionnaire\uffffV\uffffvisible=\uffffN\uffffvisible=\uffffV\uffffremove\uffffN\uffffframe\uffffN\uffffrunner\uffffV\uffffquestionnaire\uffffV\uffffresults\uffffN\uffffquestionnaire\uffffV\uffff\u0003\u0001\u0000\u0000\t\u0000\u0000\u0000\u0001\u0001\u0000\u0000\u0003\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(0, "lister/measurements/poll", this.getEncoding0());
        this.setByteList(2, "lister/runner/questionnaire/questionnaire", this.getEncoding0());
        this.setByteList(1, "lister/runner/questionnaire/workflow", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635 file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getCallSite0().call(threadContext, rubyObject, rubyObject, file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getString0(threadContext.runtime, 32));
        file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getCallSite1().call(threadContext, rubyObject, rubyObject, file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getString1(threadContext.runtime, 32));
        file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getCallSite2().call(threadContext, rubyObject, rubyObject, file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getString2(threadContext.runtime, 32));
        return module__0$RUBY$Lister(file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.module__1$RUBY$Measurements:(Lruby/jit/FILE_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$Measurements(final FILE_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_0        
        //    30: aload_1        
        //    31: ldc_w           "Measurement"
        //    34: invokevirtual   ruby/jit/FILE_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getConstant8:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    37: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    40: invokestatic    ruby/jit/FILE_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.class_2$RUBY$Poll:(Lruby/jit/FILE_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    43: aload_1        
        //    44: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: goto            55
        //    50: aload_1        
        //    51: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    54: athrow         
        //    55: aload_1        
        //    56: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    59: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     47     50     55     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject class_2$RUBY$Poll(final FILE_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    17: ldc             "Poll"
        //    19: swap           
        //    20: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    23: dup            
        //    24: astore_2       
        //    25: aload_1        
        //    26: swap           
        //    27: aload_0        
        //    28: aload_1        
        //    29: ldc             ",0,0,-1"
        //    31: invokevirtual   ruby/jit/FILE_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    34: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    37: aload_0        
        //    38: invokevirtual   ruby/jit/FILE_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getCallSite3:()Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload_2        
        //    44: aload_0        
        //    45: aload_1        
        //    46: ldc             "Questionnaire"
        //    48: invokevirtual   ruby/jit/FILE_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    51: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    54: aload_0        
        //    55: swap           
        //    56: aload_1        
        //    57: ldc             "HasListeners"
        //    59: invokevirtual   ruby/jit/FILE_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getConstantFrom1:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    62: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    65: pop            
        //    66: aload_0        
        //    67: invokevirtual   ruby/jit/FILE_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getCallSite4:()Lorg/jruby/runtime/CallSite;
        //    70: aload_1        
        //    71: aload_2        
        //    72: aload_2        
        //    73: aload_0        
        //    74: aload_1        
        //    75: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    78: ldc             "questionnaire"
        //    80: invokevirtual   ruby/jit/FILE_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    83: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    86: pop            
        //    87: aload_1        
        //    88: aload_2        
        //    89: aload_0        
        //    90: ldc             "questions"
        //    92: ldc             "method__3$RUBY$questions"
        //    94: ldc             ",0,0,-1"
        //    96: iconst_0       
        //    97: ldc             "./lib//lister/runner/measurements/poll.rb"
        //    99: ldc             15
        //   101: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   104: ldc             "NONE"
        //   106: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   109: pop            
        //   110: aload_1        
        //   111: aload_2        
        //   112: aload_0        
        //   113: ldc_w           "execute"
        //   116: ldc_w           "method__4$RUBY$execute"
        //   119: ldc             ",0,0,-1"
        //   121: iconst_0       
        //   122: ldc             "./lib//lister/runner/measurements/poll.rb"
        //   124: ldc_w           23
        //   127: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   130: ldc             "NONE"
        //   132: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   135: pop            
        //   136: aload_1        
        //   137: aload_2        
        //   138: aload_0        
        //   139: ldc_w           "reports"
        //   142: ldc_w           "method__5$RUBY$reports"
        //   145: ldc             ",0,0,-1"
        //   147: iconst_0       
        //   148: ldc             "./lib//lister/runner/measurements/poll.rb"
        //   150: ldc_w           33
        //   153: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   156: ldc             "NONE"
        //   158: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   161: aload_1        
        //   162: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   165: goto            173
        //   168: aload_1        
        //   169: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   172: athrow         
        //   173: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  37     161    168    173    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "questions", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__3$RUBY$questions(final FILE_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635 file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getCallSite5().callIter(threadContext, self, file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getCallSite6().call(threadContext, self, file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getCallSite7().call(threadContext, self, self)), RuntimeHelpers.createBlock(threadContext, self, file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getBlockBody0(threadContext, "block_0$RUBY$questions,1,description,false,2,./lib//lister/runner/measurements/poll.rb,16,true")));
    }
    
    public static IRubyObject block_0$RUBY$questions(final FILE_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: invokevirtual   ruby/jit/FILE_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getCallSite8:()Lorg/jruby/runtime/CallSite;
        //    29: aload_1        
        //    30: aload_2        
        //    31: aload_0        
        //    32: aload_1        
        //    33: ldc             "Questionnaire"
        //    35: invokevirtual   ruby/jit/FILE_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getConstant2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    38: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    41: aload_0        
        //    42: swap           
        //    43: aload_1        
        //    44: ldc             "Questionnaire"
        //    46: invokevirtual   ruby/jit/FILE_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getConstantFrom3:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: aload           description
        //    51: aload_0        
        //    52: invokevirtual   ruby/jit/FILE_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getCallSite9:()Lorg/jruby/runtime/CallSite;
        //    55: aload_1        
        //    56: aload_2        
        //    57: aload_0        
        //    58: bipush          10
        //    60: invokevirtual   ruby/jit/FILE_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    63: aload_1        
        //    64: aload_2        
        //    65: aload_2        
        //    66: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    69: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    72: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    75: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name         Signature
        //  -----  ------  ----  -----------  ---------------------------------------
        //  25     51      9     description  Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "execute", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$execute(final FILE_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635 file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635, final ThreadContext threadContext, final IRubyObject caller, final Block block) {
        file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.setVariable0(threadContext.runtime, "@questionnaire", caller, file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getCallSite(11).call(threadContext, caller, file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getConstantFrom5(RuntimeHelpers.checkIsModule(file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getConstant4(threadContext, "Questionnaire")), threadContext, "Questionnaire"), file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getCallSite(12).call(threadContext, caller, caller), file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getCallSite(13).call(threadContext, caller, file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getCallSite(14).call(threadContext, caller, caller))));
        file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getCallSite(15).call(threadContext, caller, file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getCallSite(16).call(threadContext, caller, caller));
        file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getCallSite(17).call(threadContext, caller, file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getCallSite(18).call(threadContext, caller, file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getCallSite(19).call(threadContext, caller, caller)), file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getCallSite(20).call(threadContext, caller, caller));
        final IRubyObject call = file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getCallSite(21).call(threadContext, caller, file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getCallSite(22).call(threadContext, caller, caller));
        final CallSite selectAttrAsgnCallSite = RuntimeHelpers.selectAttrAsgnCallSite(call, caller, file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getCallSite(23), file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getCallSite(24));
        final RubyBoolean true = threadContext.runtime.getTrue();
        threadContext.pollThreadEvents();
        RuntimeHelpers.doAttrAsgn(call, selectAttrAsgnCallSite, true, threadContext, caller);
        file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getCallSite(25).call(threadContext, caller, file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getConstantFrom7(RuntimeHelpers.checkIsModule(file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getConstant6(threadContext, "Questionnaire")), threadContext, "ApplicationWorkFlow"), file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getCallSite(26).call(threadContext, caller, caller));
        final IRubyObject call2 = file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getCallSite(27).call(threadContext, caller, caller);
        final CallSite selectAttrAsgnCallSite2 = RuntimeHelpers.selectAttrAsgnCallSite(call2, caller, file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getCallSite(28), file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getCallSite(29));
        final RubyBoolean false = threadContext.runtime.getFalse();
        threadContext.pollThreadEvents();
        RuntimeHelpers.doAttrAsgn(call2, selectAttrAsgnCallSite2, false, threadContext, caller);
        return file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getCallSite(30).call(threadContext, caller, file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getCallSite(31).call(threadContext, caller, file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getCallSite(32).call(threadContext, caller, caller)), file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getCallSite(33).call(threadContext, caller, caller));
    }
    
    @JRubyMethod(name = "reports", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__5$RUBY$reports(final FILE_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635 file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getCallSite(34).call(threadContext, rubyObject, file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.getCallSite(35).call(threadContext, rubyObject, rubyObject));
    }
    
    public static IRubyObject class_2$RUBY$Poll(final FILE_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635 file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_2$RUBY$Poll(file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__1$RUBY$Measurements(final FILE_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635 file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Measurements(file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635 file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635, threadContext, rubyObject, block);
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
        final FILE_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635 file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635 = new FILE_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635();
        final String string = FILE_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.class.getClassLoader().getResource("ruby/jit/FILE_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.class").toString();
        file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_9F74FDCEE6E80C4BBDBFDDA136BFFF7A35001635.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
