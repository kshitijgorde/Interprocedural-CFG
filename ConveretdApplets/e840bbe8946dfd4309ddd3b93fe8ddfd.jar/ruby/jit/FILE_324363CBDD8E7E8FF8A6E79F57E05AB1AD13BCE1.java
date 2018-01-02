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

public class FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1 extends AbstractScript
{
    public FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1() {
        this.filename = "./lib//lister/runner/questionnaire/workflow.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("include_class\uffffF\uffffrequire\uffffF\uffffattr_accessor\uffffF\uffffnew\uffffN\uffffpop\uffffN\uffffqueue\uffffV\uffff<<\uffffN\uffffqueue\uffffV\uffffsuper\uffffS\uffff<<\uffffN\uffffwaiting_notifiers\uffffN\uffff<<\uffffN\uffffqueue\uffffV\uffff<<\uffffN\uffffqueue\uffffV\uffffputs\uffffF\uffffqueue=\uffffN\uffffqueue=\uffffV\uffffnew\uffffN\uffffadd_window_listener\uffffN\uffffpop\uffffN\uffffqueue\uffffN\uffffnew\uffffN\uffffadd_action_listener\uffffN\uffffwait\uffffN\uffffnew\uffffN\uffffwait\uffffN\uffff\u0007\u0003\u0000\u0000\n\u0000\u0000\u0001\u0002\u0000\u0000\u0000\u0002\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(1, "thread", this.getEncoding0());
        this.setByteList(0, "java.awt.event.WindowAdapter", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1 file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getCallSite0().call(threadContext, rubyObject, rubyObject, file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getString0(threadContext.runtime, 32));
        return module__0$RUBY$Lister(file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.module__1$RUBY$Questionnaire:(Lruby/jit/FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$Questionnaire(final FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "Questionnaire"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.module__2$RUBY$ApplicationWorkFlow:(Lruby/jit/FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__2$RUBY$ApplicationWorkFlow(final FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "ApplicationWorkFlow"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: invokevirtual   ruby/jit/FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getCallSite1:()Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_2        
        //    34: aload_0        
        //    35: aload_1        
        //    36: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    39: bipush          32
        //    41: invokevirtual   ruby/jit/FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getString1:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    44: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: pop            
        //    48: aload_0        
        //    49: aload_1        
        //    50: aload_2        
        //    51: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    54: invokestatic    ruby/jit/FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.class_3$RUBY$EventNotifier:(Lruby/jit/FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: pop            
        //    58: aload_0        
        //    59: aload_1        
        //    60: aload_0        
        //    61: aload_1        
        //    62: ldc             "EventNotifier"
        //    64: invokevirtual   ruby/jit/FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getConstant1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    67: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    70: invokestatic    ruby/jit/FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.class_6$RUBY$ButtonNotifier:(Lruby/jit/FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    73: pop            
        //    74: aload_0        
        //    75: aload_1        
        //    76: aload_0        
        //    77: aload_1        
        //    78: ldc             "EventNotifier"
        //    80: invokevirtual   ruby/jit/FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getConstant2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    83: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    86: invokestatic    ruby/jit/FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.class_8$RUBY$QuestionnaireNotifier:(Lruby/jit/FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    89: pop            
        //    90: aload_0        
        //    91: aload_1        
        //    92: aload_0        
        //    93: aload_1        
        //    94: ldc_w           "WindowAdapter"
        //    97: invokevirtual   ruby/jit/FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getConstant3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   100: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   103: invokestatic    ruby/jit/FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.class_11$RUBY$WindowQueueNotifier:(Lruby/jit/FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   106: pop            
        //   107: aload_1        
        //   108: aload_2        
        //   109: aload_2        
        //   110: aload_0        
        //   111: ldc_w           "wait_frame"
        //   114: ldc_w           "method__22$RUBY$wait_frame"
        //   117: ldc_w           "frame,1,0,-1"
        //   120: iconst_1       
        //   121: ldc             "./lib//lister/runner/questionnaire/workflow.rb"
        //   123: ldc_w           72
        //   126: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   129: ldc_w           "qframe"
        //   132: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   135: pop            
        //   136: aload_1        
        //   137: aload_2        
        //   138: aload_2        
        //   139: aload_0        
        //   140: ldc_w           "wait_button"
        //   143: ldc_w           "method__23$RUBY$wait_button"
        //   146: ldc_w           "button;notifier,1,0,-1"
        //   149: iconst_1       
        //   150: ldc             "./lib//lister/runner/questionnaire/workflow.rb"
        //   152: ldc_w           78
        //   155: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   158: ldc_w           "qbutton"
        //   161: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   164: pop            
        //   165: aload_1        
        //   166: aload_2        
        //   167: aload_2        
        //   168: aload_0        
        //   169: ldc_w           "wait_questionnaire"
        //   172: ldc_w           "method__24$RUBY$wait_questionnaire"
        //   175: ldc_w           "questionnaire;notifier,1,0,-1"
        //   178: iconst_1       
        //   179: ldc             "./lib//lister/runner/questionnaire/workflow.rb"
        //   181: ldc_w           84
        //   184: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   187: ldc_w           "qquestionnaire"
        //   190: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   193: aload_1        
        //   194: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   197: goto            205
        //   200: aload_1        
        //   201: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   204: athrow         
        //   205: aload_1        
        //   206: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   209: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     197    200    205    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject class_3$RUBY$EventNotifier(final FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    10: ldc             "EventNotifier"
        //    12: swap           
        //    13: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    16: dup            
        //    17: astore_2       
        //    18: aload_1        
        //    19: swap           
        //    20: aload_0        
        //    21: aload_1        
        //    22: ldc             ",0,0,-1"
        //    24: invokevirtual   ruby/jit/FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getScope3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    27: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    30: aload_0        
        //    31: invokevirtual   ruby/jit/FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getCallSite2:()Lorg/jruby/runtime/CallSite;
        //    34: aload_1        
        //    35: aload_2        
        //    36: aload_2        
        //    37: aload_0        
        //    38: aload_1        
        //    39: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    42: ldc             "queue"
        //    44: invokevirtual   ruby/jit/FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    47: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: pop            
        //    51: aload_1        
        //    52: aload_2        
        //    53: aload_0        
        //    54: ldc             "initialize"
        //    56: ldc             "method__4$RUBY$initialize"
        //    58: ldc             ",0,0,-1"
        //    60: iconst_0       
        //    61: ldc             "./lib//lister/runner/questionnaire/workflow.rb"
        //    63: ldc             10
        //    65: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    68: ldc             "NONE"
        //    70: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    73: pop            
        //    74: aload_1        
        //    75: aload_2        
        //    76: aload_0        
        //    77: ldc             "wait"
        //    79: ldc             "method__5$RUBY$wait"
        //    81: ldc             ",0,0,-1"
        //    83: iconst_0       
        //    84: ldc             "./lib//lister/runner/questionnaire/workflow.rb"
        //    86: ldc             14
        //    88: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    91: ldc             "NONE"
        //    93: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    96: aload_1        
        //    97: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   100: goto            108
        //   103: aload_1        
        //   104: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   107: athrow         
        //   108: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  30     96     103    108    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "initialize", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$initialize(final FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1 file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1, final ThreadContext context, final IRubyObject object, final Block block) {
        return file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.setVariable0(context.runtime, "@queue", object, file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getCallSite3().call(context, object, file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getConstant0(context, "Queue")));
    }
    
    @JRubyMethod(name = "wait", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__5$RUBY$wait(final FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1 file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getCallSite4().call(threadContext, rubyObject, file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getCallSite5().call(threadContext, rubyObject, rubyObject));
    }
    
    public static IRubyObject class_3$RUBY$EventNotifier(final FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1 file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_3$RUBY$EventNotifier(file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1, threadContext, rubyObject, block);
    }
    
    public static IRubyObject class_6$RUBY$ButtonNotifier(final FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    17: ldc             "ButtonNotifier"
        //    19: swap           
        //    20: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    23: dup            
        //    24: astore_2       
        //    25: aload_1        
        //    26: swap           
        //    27: aload_0        
        //    28: aload_1        
        //    29: ldc             ",0,0,-1"
        //    31: invokevirtual   ruby/jit/FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getScope4:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    34: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    37: aload_1        
        //    38: aload_2        
        //    39: aload_0        
        //    40: ldc             "action_performed"
        //    42: ldc             "method__7$RUBY$action_performed"
        //    44: ldc             "event,1,0,-1"
        //    46: iconst_1       
        //    47: ldc             "./lib//lister/runner/questionnaire/workflow.rb"
        //    49: ldc             20
        //    51: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    54: ldc             "qevent"
        //    56: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    59: aload_1        
        //    60: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    63: goto            71
        //    66: aload_1        
        //    67: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    70: athrow         
        //    71: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  37     59     66     71     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "action_performed", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__7$RUBY$action_performed(final FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1 file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getCallSite6().call(threadContext, rubyObject, file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getCallSite7().call(threadContext, rubyObject, rubyObject), file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getSymbol1(threadContext.runtime, "pressed"));
    }
    
    public static IRubyObject class_6$RUBY$ButtonNotifier(final FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1 file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_6$RUBY$ButtonNotifier(file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1, threadContext, rubyObject, block);
    }
    
    public static IRubyObject class_8$RUBY$QuestionnaireNotifier(final FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    17: ldc             "QuestionnaireNotifier"
        //    19: swap           
        //    20: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    23: dup            
        //    24: astore_2       
        //    25: aload_1        
        //    26: swap           
        //    27: aload_0        
        //    28: aload_1        
        //    29: ldc             ",0,0,-1"
        //    31: invokevirtual   ruby/jit/FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getScope5:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    34: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    37: aload_1        
        //    38: aload_2        
        //    39: aload_0        
        //    40: ldc             "initialize"
        //    42: ldc_w           "method__9$RUBY$initialize"
        //    45: ldc_w           "questionnaire,1,0,-1"
        //    48: iconst_1       
        //    49: ldc             "./lib//lister/runner/questionnaire/workflow.rb"
        //    51: ldc_w           26
        //    54: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    57: ldc_w           "qquestionnaire"
        //    60: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    63: pop            
        //    64: aload_1        
        //    65: aload_2        
        //    66: aload_0        
        //    67: ldc_w           "questionnaire_ended"
        //    70: ldc_w           "method__10$RUBY$questionnaire_ended"
        //    73: ldc             "event,1,0,-1"
        //    75: iconst_1       
        //    76: ldc             "./lib//lister/runner/questionnaire/workflow.rb"
        //    78: ldc_w           31
        //    81: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    84: ldc             "qevent"
        //    86: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    89: aload_1        
        //    90: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    93: goto            101
        //    96: aload_1        
        //    97: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   100: athrow         
        //   101: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  37     89     96     101    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "initialize", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__9$RUBY$initialize(final FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1 file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getCallSite8().call(threadContext, rubyObject, rubyObject, block);
        return file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getCallSite9().call(threadContext, rubyObject, file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getCallSite(10).call(threadContext, rubyObject, rubyObject2), rubyObject);
    }
    
    @JRubyMethod(name = "questionnaire_ended", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__10$RUBY$questionnaire_ended(final FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1 file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getCallSite(11).call(threadContext, rubyObject, file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getCallSite(12).call(threadContext, rubyObject, rubyObject), file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getSymbol1(threadContext.runtime, "pressed"));
    }
    
    public static IRubyObject class_8$RUBY$QuestionnaireNotifier(final FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1 file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_8$RUBY$QuestionnaireNotifier(file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1, threadContext, rubyObject, block);
    }
    
    public static IRubyObject class_11$RUBY$WindowQueueNotifier(final FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    17: ldc_w           "WindowQueueNotifier"
        //    20: swap           
        //    21: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    24: dup            
        //    25: astore_2       
        //    26: aload_1        
        //    27: swap           
        //    28: aload_0        
        //    29: aload_1        
        //    30: ldc             ",0,0,-1"
        //    32: invokevirtual   ruby/jit/FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getScope6:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    35: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    38: aload_1        
        //    39: aload_2        
        //    40: aload_2        
        //    41: aload_0        
        //    42: ldc_w           "queue="
        //    45: ldc_w           "method__12$RUBY$queue_equal_"
        //    48: ldc_w           "val,1,0,-1"
        //    51: iconst_1       
        //    52: ldc             "./lib//lister/runner/questionnaire/workflow.rb"
        //    54: ldc_w           37
        //    57: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    60: ldc_w           "qval"
        //    63: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    66: pop            
        //    67: aload_1        
        //    68: aload_2        
        //    69: aload_2        
        //    70: aload_0        
        //    71: ldc             "queue"
        //    73: ldc_w           "method__13$RUBY$queue"
        //    76: ldc             ",0,0,-1"
        //    78: iconst_0       
        //    79: ldc             "./lib//lister/runner/questionnaire/workflow.rb"
        //    81: ldc_w           41
        //    84: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    87: ldc             "NONE"
        //    89: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    92: pop            
        //    93: aload_1        
        //    94: aload_2        
        //    95: aload_2        
        //    96: aload_0        
        //    97: ldc_w           "window_opened"
        //   100: ldc_w           "method__14$RUBY$window_opened"
        //   103: ldc             "event,1,0,-1"
        //   105: iconst_1       
        //   106: ldc             "./lib//lister/runner/questionnaire/workflow.rb"
        //   108: ldc_w           45
        //   111: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   114: ldc             "qevent"
        //   116: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   119: pop            
        //   120: aload_1        
        //   121: aload_2        
        //   122: aload_2        
        //   123: aload_0        
        //   124: ldc_w           "window_closing"
        //   127: ldc_w           "method__15$RUBY$window_closing"
        //   130: ldc             "event,1,0,-1"
        //   132: iconst_1       
        //   133: ldc             "./lib//lister/runner/questionnaire/workflow.rb"
        //   135: ldc_w           48
        //   138: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   141: ldc             "qevent"
        //   143: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   146: pop            
        //   147: aload_1        
        //   148: aload_2        
        //   149: aload_2        
        //   150: aload_0        
        //   151: ldc_w           "window_activated"
        //   154: ldc_w           "method__16$RUBY$window_activated"
        //   157: ldc             "event,1,0,-1"
        //   159: iconst_1       
        //   160: ldc             "./lib//lister/runner/questionnaire/workflow.rb"
        //   162: ldc_w           51
        //   165: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   168: ldc             "qevent"
        //   170: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   173: pop            
        //   174: aload_1        
        //   175: aload_2        
        //   176: aload_2        
        //   177: aload_0        
        //   178: ldc_w           "window_deactivated"
        //   181: ldc_w           "method__17$RUBY$window_deactivated"
        //   184: ldc             "event,1,0,-1"
        //   186: iconst_1       
        //   187: ldc             "./lib//lister/runner/questionnaire/workflow.rb"
        //   189: ldc_w           54
        //   192: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   195: ldc             "qevent"
        //   197: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   200: pop            
        //   201: aload_1        
        //   202: aload_2        
        //   203: aload_2        
        //   204: aload_0        
        //   205: ldc_w           "window_iconified"
        //   208: ldc_w           "method__18$RUBY$window_iconified"
        //   211: ldc             "event,1,0,-1"
        //   213: iconst_1       
        //   214: ldc             "./lib//lister/runner/questionnaire/workflow.rb"
        //   216: ldc_w           57
        //   219: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   222: ldc             "qevent"
        //   224: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   227: pop            
        //   228: aload_1        
        //   229: aload_2        
        //   230: aload_2        
        //   231: aload_0        
        //   232: ldc_w           "deiconified"
        //   235: ldc_w           "method__19$RUBY$deiconified"
        //   238: ldc             "event,1,0,-1"
        //   240: iconst_1       
        //   241: ldc             "./lib//lister/runner/questionnaire/workflow.rb"
        //   243: ldc_w           60
        //   246: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   249: ldc             "qevent"
        //   251: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   254: pop            
        //   255: aload_1        
        //   256: aload_2        
        //   257: aload_2        
        //   258: aload_0        
        //   259: ldc_w           "window_closed"
        //   262: ldc_w           "method__20$RUBY$window_closed"
        //   265: ldc             "event,1,0,-1"
        //   267: iconst_1       
        //   268: ldc             "./lib//lister/runner/questionnaire/workflow.rb"
        //   270: ldc_w           63
        //   273: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   276: ldc             "qevent"
        //   278: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   281: pop            
        //   282: aload_1        
        //   283: aload_2        
        //   284: aload_2        
        //   285: aload_0        
        //   286: ldc_w           "method_missing"
        //   289: ldc_w           "method__21$RUBY$method_missing"
        //   292: ldc_w           "meth;args,1,0,1"
        //   295: bipush          -2
        //   297: ldc             "./lib//lister/runner/questionnaire/workflow.rb"
        //   299: ldc_w           67
        //   302: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   305: ldc_w           "qmeth;rargs"
        //   308: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   311: aload_1        
        //   312: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   315: goto            323
        //   318: aload_1        
        //   319: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   322: athrow         
        //   323: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  38     311    318    323    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "queue=", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__12$RUBY$queue_equal_(final FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1 file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1, final ThreadContext threadContext, final IRubyObject object, final IRubyObject value, final Block block) {
        return file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.setVariable1(threadContext.runtime, "@queue", object, value);
    }
    
    @JRubyMethod(name = "queue", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__13$RUBY$queue(final FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1 file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        return file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getVariable0(threadContext.runtime, "@queue", object);
    }
    
    @JRubyMethod(name = "window_opened", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__14$RUBY$window_opened(final FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1 file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return threadContext.nil;
    }
    
    @JRubyMethod(name = "window_closing", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__15$RUBY$window_closing(final FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1 file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return threadContext.nil;
    }
    
    @JRubyMethod(name = "window_activated", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__16$RUBY$window_activated(final FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1 file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return threadContext.nil;
    }
    
    @JRubyMethod(name = "window_deactivated", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__17$RUBY$window_deactivated(final FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1 file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return threadContext.nil;
    }
    
    @JRubyMethod(name = "window_iconified", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__18$RUBY$window_iconified(final FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1 file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return threadContext.nil;
    }
    
    @JRubyMethod(name = "deiconified", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__19$RUBY$deiconified(final FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1 file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return threadContext.nil;
    }
    
    @JRubyMethod(name = "window_closed", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__20$RUBY$window_closed(final FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1 file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getCallSite(13).call(threadContext, rubyObject, file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getCallSite(14).call(threadContext, rubyObject, rubyObject), file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getSymbol2(threadContext.runtime, "done"));
    }
    
    @JRubyMethod(name = "method_missing", frame = true, required = 1, optional = 0, rest = 1)
    public static IRubyObject method__21$RUBY$method_missing(final FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4) {
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
        //    12: iconst_m1      
        //    13: invokestatic    org/jruby/runtime/Arity.raiseArgumentError:(Lorg/jruby/Ruby;[Lorg/jruby/runtime/builtin/IRubyObject;II)V
        //    16: aload_3        
        //    17: iconst_0       
        //    18: aaload         
        //    19: astore          9
        //    21: aload_3        
        //    22: aload_1        
        //    23: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    26: iconst_1       
        //    27: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createSubarray:([Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;I)Lorg/jruby/RubyArray;
        //    30: astore          args
        //    32: aload_0        
        //    33: bipush          15
        //    35: invokevirtual   ruby/jit/FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    38: aload_1        
        //    39: aload_2        
        //    40: aload_2        
        //    41: aload           meth
        //    43: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    46: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  32     15      9     meth  Lorg/jruby/runtime/builtin/IRubyObject;
        //  32     15      10    args  Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject class_11$RUBY$WindowQueueNotifier(final FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1 file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_11$RUBY$WindowQueueNotifier(file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1, threadContext, rubyObject, block);
    }
    
    @JRubyMethod(name = "wait_frame", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__22$RUBY$wait_frame(final FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1 file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1, final ThreadContext context, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        final IRubyObject constant4 = file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getConstant4(context, "WindowQueueNotifier");
        RuntimeHelpers.doAttrAsgn(constant4, RuntimeHelpers.selectAttrAsgnCallSite(constant4, rubyObject, file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getCallSite(16), file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getCallSite(17)), file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getCallSite(18).call(context, rubyObject, file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getConstant5(context, "Queue")), context, rubyObject);
        file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getCallSite(19).call(context, rubyObject, rubyObject2, file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getConstant6(context, "WindowQueueNotifier"));
        return file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getCallSite(20).call(context, rubyObject, file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getCallSite(21).call(context, rubyObject, file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getConstant7(context, "WindowQueueNotifier")));
    }
    
    @JRubyMethod(name = "wait_button", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__23$RUBY$wait_button(final FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1 file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1, final ThreadContext context, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        final IRubyObject nil = context.nil;
        final IRubyObject notifier = file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getCallSite(22).call(context, rubyObject, file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getConstant8(context, "ButtonNotifier"));
        file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getCallSite(23).call(context, rubyObject, rubyObject2, notifier);
        return file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getCallSite(24).call(context, rubyObject, notifier);
    }
    
    @JRubyMethod(name = "wait_questionnaire", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__24$RUBY$wait_questionnaire(final FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1 file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1, final ThreadContext context, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        final IRubyObject nil = context.nil;
        final IRubyObject notifier = file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getCallSite(25).call(context, rubyObject, file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getConstant9(context, "QuestionnaireNotifier"), rubyObject2);
        return file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.getCallSite(26).call(context, rubyObject, notifier);
    }
    
    public static IRubyObject module__2$RUBY$ApplicationWorkFlow(final FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1 file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__2$RUBY$ApplicationWorkFlow(file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__1$RUBY$Questionnaire(final FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1 file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Questionnaire(file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1 file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1, threadContext, rubyObject, block);
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
        final FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1 file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1 = new FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1();
        final String string = FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.class.getClassLoader().getResource("ruby/jit/FILE_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.class").toString();
        file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_324363CBDD8E7E8FF8A6E79F57E05AB1AD13BCE1.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
