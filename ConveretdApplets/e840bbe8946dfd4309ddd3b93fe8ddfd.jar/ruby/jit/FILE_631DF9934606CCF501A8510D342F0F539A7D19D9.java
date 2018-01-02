// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.Arity;
import org.jruby.RubyHash;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_631DF9934606CCF501A8510D342F0F539A7D19D9 extends AbstractScript
{
    public FILE_631DF9934606CCF501A8510D342F0F539A7D19D9() {
        this.filename = "./lib//lister/runner/questionnaire/wizard.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("include_class\uffffF\uffffrequire\uffffF\uffffinclude\uffffF\ufffflistener\uffffF\uffffcall_completion_callbacks\uffffN\uffffsource\uffffV\ufffflistener\uffffF\uffffcall_cancellation_callbacks\uffffN\uffffsource\uffffV\uffffeach\uffffN\uffff[]\uffffN\uffffcallbacks\uffffV\uffffcall\uffffN\uffffeach\uffffN\uffff[]\uffffN\uffffcallbacks\uffffV\uffffcall\uffffN\uffff[]\uffffN\uffffcallbacks\uffffV\uffffcallbacks\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffffadd_window_listener\uffffN\ufffflistener\uffffF\uffff<<\uffffN\uffff[]\uffffN\uffffcallbacks\uffffV\uffff[]\uffffN\uffffcallbacks\uffffV\uffffcallbacks\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffffadd_action_listener\uffffN\uffff[]\uffffN\uffffbuttons\uffffN\uffffquestionnaire\uffffV\ufffflistener\uffffF\uffff<<\uffffN\uffff[]\uffffN\uffffcallbacks\uffffV\uffffstore_question\uffffN\uffffquestionnaire\uffffV\uffffresults\uffffN\uffffquestionnaire\uffffV\uffffattr_accessor\uffffF\uffffnew\uffffN\uffffquestionnaire=\uffffN\uffffquestionnaire=\uffffV\uffffstart\uffffN\uffffadd\uffffN\uffffcontent_pane\uffffN\uffff\u0003\u0005\u0000\u0000\u0002\u0000\u0000\u0001\u0002\u0004\u0000\u0000\u0003\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(1, "lister/runner/questionnaire/has_listener", this.getEncoding0());
        this.setByteList(0, "javax.swing.JFrame", this.getEncoding0());
        this.setByteList(2, "instance-variable", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_631DF9934606CCF501A8510D342F0F539A7D19D9 file_631DF9934606CCF501A8510D342F0F539A7D19D9, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite0().call(threadContext, rubyObject, rubyObject, file_631DF9934606CCF501A8510D342F0F539A7D19D9.getString0(threadContext.runtime, 32));
        file_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite1().call(threadContext, rubyObject, rubyObject, file_631DF9934606CCF501A8510D342F0F539A7D19D9.getString1(threadContext.runtime, 32));
        return module__0$RUBY$Lister(file_631DF9934606CCF501A8510D342F0F539A7D19D9, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_631DF9934606CCF501A8510D342F0F539A7D19D9 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.module__1$RUBY$Questionnaire:(Lruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$Questionnaire(final FILE_631DF9934606CCF501A8510D342F0F539A7D19D9 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_0        
        //    30: aload_1        
        //    31: ldc_w           "JFrame"
        //    34: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getConstant1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    37: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    40: invokestatic    ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.class_2$RUBY$Wizard:(Lruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject class_2$RUBY$Wizard(final FILE_631DF9934606CCF501A8510D342F0F539A7D19D9 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: dup            
        //     5: astore          4
        //     7: invokevirtual   org/jruby/runtime/DynamicScope.getValues:()[Lorg/jruby/runtime/builtin/IRubyObject;
        //    10: astore          5
        //    12: aload_1        
        //    13: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    16: aload_2        
        //    17: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareSuperClass:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyClass;
        //    20: aload_1        
        //    21: aload_1        
        //    22: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    25: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    28: swap           
        //    29: ldc             "Wizard"
        //    31: swap           
        //    32: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    35: dup            
        //    36: astore_2       
        //    37: aload_1        
        //    38: swap           
        //    39: aload_0        
        //    40: aload_1        
        //    41: ldc             ",0,0,-1"
        //    43: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    46: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClass:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    49: aload_1        
        //    50: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //    53: astore          locals
        //    55: aload_0        
        //    56: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite2:()Lorg/jruby/runtime/CallSite;
        //    59: aload_1        
        //    60: aload_2        
        //    61: aload_2        
        //    62: aload_0        
        //    63: aload_1        
        //    64: ldc             "HasListeners"
        //    66: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    69: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    72: pop            
        //    73: aload_0        
        //    74: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite3:()Lorg/jruby/runtime/CallSite;
        //    77: aload_1        
        //    78: aload_2        
        //    79: aload_2        
        //    80: aload_0        
        //    81: aload_1        
        //    82: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    85: ldc             "complete"
        //    87: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    90: aload_1        
        //    91: aload_2        
        //    92: aload_0        
        //    93: aload_1        
        //    94: ldc             "block_0$RUBY$Wizard,-1,,false,0,./lib//lister/runner/questionnaire/wizard.rb,9,true"
        //    96: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getBlockBody0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    99: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   102: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   105: pop            
        //   106: aload_0        
        //   107: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite6:()Lorg/jruby/runtime/CallSite;
        //   110: aload_1        
        //   111: aload_2        
        //   112: aload_2        
        //   113: aload_0        
        //   114: aload_1        
        //   115: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   118: ldc             "window"
        //   120: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   123: aload_1        
        //   124: aload_2        
        //   125: aload_0        
        //   126: aload_1        
        //   127: ldc_w           "block_1$RUBY$Wizard,-1,,false,0,./lib//lister/runner/questionnaire/wizard.rb,15,true"
        //   130: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getBlockBody1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   133: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   136: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   139: pop            
        //   140: aload_1        
        //   141: aload_2        
        //   142: aload_0        
        //   143: ldc_w           "call_completion_callbacks"
        //   146: ldc_w           "method__11$RUBY$call_completion_callbacks"
        //   149: ldc             ",0,0,-1"
        //   151: iconst_0       
        //   152: ldc             "./lib//lister/runner/questionnaire/wizard.rb"
        //   154: ldc_w           33
        //   157: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   160: ldc_w           "NONE"
        //   163: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   166: pop            
        //   167: aload_1        
        //   168: aload_2        
        //   169: aload_0        
        //   170: ldc_w           "call_cancellation_callbacks"
        //   173: ldc_w           "method__12$RUBY$call_cancellation_callbacks"
        //   176: ldc             ",0,0,-1"
        //   178: iconst_0       
        //   179: ldc             "./lib//lister/runner/questionnaire/wizard.rb"
        //   181: ldc_w           39
        //   184: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   187: ldc_w           "NONE"
        //   190: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   193: pop            
        //   194: aload_1        
        //   195: aload_2        
        //   196: aload_0        
        //   197: ldc_w           "on_cancellation"
        //   200: ldc_w           "method__13$RUBY$on_cancellation"
        //   203: ldc_w           "blk,0,0,-1"
        //   206: iconst_0       
        //   207: ldc             "./lib//lister/runner/questionnaire/wizard.rb"
        //   209: ldc_w           45
        //   212: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   215: ldc_w           "bblk"
        //   218: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   221: pop            
        //   222: aload_1        
        //   223: aload_2        
        //   224: aload_0        
        //   225: ldc_w           "on_completion"
        //   228: ldc_w           "method__14$RUBY$on_completion"
        //   231: ldc_w           "blk,0,0,-1"
        //   234: iconst_0       
        //   235: ldc             "./lib//lister/runner/questionnaire/wizard.rb"
        //   237: ldc_w           53
        //   240: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   243: ldc_w           "bblk"
        //   246: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   249: pop            
        //   250: aload_1        
        //   251: aload_2        
        //   252: aload_0        
        //   253: ldc_w           "result"
        //   256: ldc_w           "method__15$RUBY$result"
        //   259: ldc             ",0,0,-1"
        //   261: iconst_0       
        //   262: ldc             "./lib//lister/runner/questionnaire/wizard.rb"
        //   264: ldc_w           61
        //   267: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   270: ldc_w           "NONE"
        //   273: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   276: pop            
        //   277: aload_1        
        //   278: aload_2        
        //   279: aload_0        
        //   280: ldc_w           "callbacks"
        //   283: ldc_w           "method__16$RUBY$callbacks"
        //   286: ldc             ",0,0,-1"
        //   288: iconst_0       
        //   289: ldc             "./lib//lister/runner/questionnaire/wizard.rb"
        //   291: ldc_w           66
        //   294: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   297: ldc_w           "NONE"
        //   300: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   303: pop            
        //   304: aload_0        
        //   305: bipush          44
        //   307: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   310: aload_1        
        //   311: aload_2        
        //   312: aload_2        
        //   313: aload_0        
        //   314: aload_1        
        //   315: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   318: ldc_w           "questionnaire"
        //   321: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getSymbol4:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   324: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   327: pop            
        //   328: aload_1        
        //   329: aload_2        
        //   330: aload_2        
        //   331: aload_0        
        //   332: ldc_w           "create"
        //   335: ldc_w           "method__17$RUBY$create"
        //   338: ldc_w           "questionnaire;obj,1,0,-1"
        //   341: iconst_1       
        //   342: ldc             "./lib//lister/runner/questionnaire/wizard.rb"
        //   344: ldc_w           71
        //   347: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   350: ldc_w           "qquestionnaire"
        //   353: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   356: aload_1        
        //   357: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   360: goto            368
        //   363: aload_1        
        //   364: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   367: athrow         
        //   368: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  55     314     4     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  55     356    363    368    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_0$RUBY$Wizard(final FILE_631DF9934606CCF501A8510D342F0F539A7D19D9 scriptObject, final ThreadContext context, final IRubyObject self, final IRubyObject rubyObject, final Block block) {
        context.getCurrentScope();
        RuntimeHelpers.processBlockArgument(context.runtime, block);
        return RuntimeHelpers.def(context, self, scriptObject, "actionPerformed", "method__3$RUBY$actionPerformed", "event,1,0,-1", 1, "./lib//lister/runner/questionnaire/wizard.rb", 10, CallConfiguration.FrameNoneScopeNone, "qevent");
    }
    
    @JRubyMethod(name = "actionPerformed", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__3$RUBY$actionPerformed(final FILE_631DF9934606CCF501A8510D342F0F539A7D19D9 file_631DF9934606CCF501A8510D342F0F539A7D19D9, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite4().call(threadContext, rubyObject, file_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite5().call(threadContext, rubyObject, rubyObject));
    }
    
    public static IRubyObject block_1$RUBY$Wizard(final FILE_631DF9934606CCF501A8510D342F0F539A7D19D9 scriptObject, final ThreadContext context, final IRubyObject self, final IRubyObject rubyObject, final Block block) {
        context.getCurrentScope();
        RuntimeHelpers.processBlockArgument(context.runtime, block);
        RuntimeHelpers.def(context, self, scriptObject, "windowActivated", "method__4$RUBY$windowActivated", "event,1,0,-1", 1, "./lib//lister/runner/questionnaire/wizard.rb", 16, CallConfiguration.FrameNoneScopeNone, "qevent");
        RuntimeHelpers.def(context, self, scriptObject, "windowClosed", "method__5$RUBY$windowClosed", "event,1,0,-1", 1, "./lib//lister/runner/questionnaire/wizard.rb", 18, CallConfiguration.FrameNoneScopeNone, "qevent");
        RuntimeHelpers.def(context, self, scriptObject, "windowClosing", "method__6$RUBY$windowClosing", "event,1,0,-1", 1, "./lib//lister/runner/questionnaire/wizard.rb", 20, CallConfiguration.FrameNoneScopeNone, "qevent");
        RuntimeHelpers.def(context, self, scriptObject, "windowDeactivated", "method__7$RUBY$windowDeactivated", "event,1,0,-1", 1, "./lib//lister/runner/questionnaire/wizard.rb", 23, CallConfiguration.FrameNoneScopeNone, "qevent");
        RuntimeHelpers.def(context, self, scriptObject, "windowDeiconified", "method__8$RUBY$windowDeiconified", "event,1,0,-1", 1, "./lib//lister/runner/questionnaire/wizard.rb", 25, CallConfiguration.FrameNoneScopeNone, "qevent");
        RuntimeHelpers.def(context, self, scriptObject, "windowIconified", "method__9$RUBY$windowIconified", "event,1,0,-1", 1, "./lib//lister/runner/questionnaire/wizard.rb", 27, CallConfiguration.FrameNoneScopeNone, "qevent");
        return RuntimeHelpers.def(context, self, scriptObject, "windowOpened", "method__10$RUBY$windowOpened", "event,1,0,-1", 1, "./lib//lister/runner/questionnaire/wizard.rb", 29, CallConfiguration.FrameNoneScopeNone, "qevent");
    }
    
    @JRubyMethod(name = "windowActivated", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$windowActivated(final FILE_631DF9934606CCF501A8510D342F0F539A7D19D9 file_631DF9934606CCF501A8510D342F0F539A7D19D9, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return threadContext.nil;
    }
    
    @JRubyMethod(name = "windowClosed", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__5$RUBY$windowClosed(final FILE_631DF9934606CCF501A8510D342F0F539A7D19D9 file_631DF9934606CCF501A8510D342F0F539A7D19D9, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return threadContext.nil;
    }
    
    @JRubyMethod(name = "windowClosing", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__6$RUBY$windowClosing(final FILE_631DF9934606CCF501A8510D342F0F539A7D19D9 file_631DF9934606CCF501A8510D342F0F539A7D19D9, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite7().call(threadContext, rubyObject, file_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite8().call(threadContext, rubyObject, rubyObject));
    }
    
    @JRubyMethod(name = "windowDeactivated", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__7$RUBY$windowDeactivated(final FILE_631DF9934606CCF501A8510D342F0F539A7D19D9 file_631DF9934606CCF501A8510D342F0F539A7D19D9, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return threadContext.nil;
    }
    
    @JRubyMethod(name = "windowDeiconified", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__8$RUBY$windowDeiconified(final FILE_631DF9934606CCF501A8510D342F0F539A7D19D9 file_631DF9934606CCF501A8510D342F0F539A7D19D9, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return threadContext.nil;
    }
    
    @JRubyMethod(name = "windowIconified", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__9$RUBY$windowIconified(final FILE_631DF9934606CCF501A8510D342F0F539A7D19D9 file_631DF9934606CCF501A8510D342F0F539A7D19D9, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return threadContext.nil;
    }
    
    @JRubyMethod(name = "windowOpened", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__10$RUBY$windowOpened(final FILE_631DF9934606CCF501A8510D342F0F539A7D19D9 file_631DF9934606CCF501A8510D342F0F539A7D19D9, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return threadContext.nil;
    }
    
    @JRubyMethod(name = "call_completion_callbacks", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__11$RUBY$call_completion_callbacks(final FILE_631DF9934606CCF501A8510D342F0F539A7D19D9 file_631DF9934606CCF501A8510D342F0F539A7D19D9, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite9().callIter(threadContext, self, file_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite(10).call(threadContext, self, file_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite(11).call(threadContext, self, self), file_631DF9934606CCF501A8510D342F0F539A7D19D9.getSymbol0(threadContext.runtime, "complete")), RuntimeHelpers.createBlock(threadContext, self, file_631DF9934606CCF501A8510D342F0F539A7D19D9.getBlockBody2(threadContext, "block_2$RUBY$call_completion_callbacks,1,blk,false,2,./lib//lister/runner/questionnaire/wizard.rb,34,true")));
    }
    
    public static IRubyObject block_2$RUBY$call_completion_callbacks(final FILE_631DF9934606CCF501A8510D342F0F539A7D19D9 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          12
        //    28: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload           blk
        //    35: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    38: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     14      9     blk   Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "call_cancellation_callbacks", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__12$RUBY$call_cancellation_callbacks(final FILE_631DF9934606CCF501A8510D342F0F539A7D19D9 file_631DF9934606CCF501A8510D342F0F539A7D19D9, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite(13).callIter(threadContext, self, file_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite(14).call(threadContext, self, file_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite(15).call(threadContext, self, self), file_631DF9934606CCF501A8510D342F0F539A7D19D9.getSymbol2(threadContext.runtime, "cancel")), RuntimeHelpers.createBlock(threadContext, self, file_631DF9934606CCF501A8510D342F0F539A7D19D9.getBlockBody3(threadContext, "block_3$RUBY$call_cancellation_callbacks,1,blk,false,2,./lib//lister/runner/questionnaire/wizard.rb,40,true")));
    }
    
    public static IRubyObject block_3$RUBY$call_cancellation_callbacks(final FILE_631DF9934606CCF501A8510D342F0F539A7D19D9 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          16
        //    28: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload           blk
        //    35: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    38: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     14      9     blk   Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "on_cancellation", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__13$RUBY$on_cancellation(final FILE_631DF9934606CCF501A8510D342F0F539A7D19D9 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          4
        //     6: aload_1        
        //     7: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    10: aload_3        
        //    11: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    14: aload           4
        //    16: swap           
        //    17: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    20: pop            
        //    21: aload_0        
        //    22: bipush          17
        //    24: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    27: aload_1        
        //    28: aload_2        
        //    29: aload_0        
        //    30: bipush          18
        //    32: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    35: aload_1        
        //    36: aload_2        
        //    37: aload_2        
        //    38: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    41: aload_0        
        //    42: aload_1        
        //    43: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    46: ldc_w           "cancel"
        //    49: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    52: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    55: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    60: ifeq            66
        //    63: goto            154
        //    66: aload_0        
        //    67: bipush          19
        //    69: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    72: aload_1        
        //    73: aload_2        
        //    74: aload_2        
        //    75: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    78: dup            
        //    79: aload_2        
        //    80: aload_0        
        //    81: bipush          20
        //    83: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    86: aload_0        
        //    87: bipush          21
        //    89: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    92: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //    95: aload_0        
        //    96: aload_1        
        //    97: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   100: ldc_w           "cancel"
        //   103: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   106: aload_1        
        //   107: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   110: invokevirtual   org/jruby/Ruby.newArray:()Lorg/jruby/RubyArray;
        //   113: aload_1        
        //   114: aload_2        
        //   115: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   118: pop            
        //   119: aload_0        
        //   120: bipush          22
        //   122: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   125: aload_1        
        //   126: aload_2        
        //   127: aload_2        
        //   128: aload_0        
        //   129: bipush          23
        //   131: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   134: aload_1        
        //   135: aload_2        
        //   136: aload_2        
        //   137: aload_0        
        //   138: aload_1        
        //   139: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   142: ldc             "window"
        //   144: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   147: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   150: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   153: pop            
        //   154: aload_0        
        //   155: bipush          24
        //   157: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   160: aload_1        
        //   161: aload_2        
        //   162: aload_0        
        //   163: bipush          25
        //   165: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   168: aload_1        
        //   169: aload_2        
        //   170: aload_0        
        //   171: bipush          26
        //   173: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   176: aload_1        
        //   177: aload_2        
        //   178: aload_2        
        //   179: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   182: aload_0        
        //   183: aload_1        
        //   184: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   187: ldc_w           "cancel"
        //   190: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   193: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   196: aload           locals
        //   198: aload_1        
        //   199: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   202: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   205: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   208: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  21     188     4     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "on_completion", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__14$RUBY$on_completion(final FILE_631DF9934606CCF501A8510D342F0F539A7D19D9 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          4
        //     6: aload_1        
        //     7: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    10: aload_3        
        //    11: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    14: aload           4
        //    16: swap           
        //    17: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    20: pop            
        //    21: aload_0        
        //    22: bipush          27
        //    24: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    27: aload_1        
        //    28: aload_2        
        //    29: aload_0        
        //    30: bipush          28
        //    32: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    35: aload_1        
        //    36: aload_2        
        //    37: aload_2        
        //    38: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    41: aload_0        
        //    42: aload_1        
        //    43: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    46: ldc             "complete"
        //    48: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    51: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    54: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    59: ifeq            65
        //    62: goto            196
        //    65: aload_0        
        //    66: bipush          29
        //    68: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    71: aload_1        
        //    72: aload_2        
        //    73: aload_2        
        //    74: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    77: dup            
        //    78: aload_2        
        //    79: aload_0        
        //    80: bipush          30
        //    82: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    85: aload_0        
        //    86: bipush          31
        //    88: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    91: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //    94: aload_0        
        //    95: aload_1        
        //    96: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    99: ldc             "complete"
        //   101: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   104: aload_1        
        //   105: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   108: invokevirtual   org/jruby/Ruby.newArray:()Lorg/jruby/RubyArray;
        //   111: aload_1        
        //   112: aload_2        
        //   113: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   116: pop            
        //   117: aload_0        
        //   118: bipush          32
        //   120: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   123: aload_1        
        //   124: aload_2        
        //   125: aload_0        
        //   126: bipush          33
        //   128: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   131: aload_1        
        //   132: aload_2        
        //   133: aload_0        
        //   134: bipush          34
        //   136: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   139: aload_1        
        //   140: aload_2        
        //   141: aload_0        
        //   142: bipush          35
        //   144: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   147: aload_1        
        //   148: aload_2        
        //   149: aload_2        
        //   150: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   153: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   156: aload_0        
        //   157: aload_1        
        //   158: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   161: ldc_w           "done"
        //   164: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   167: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   170: aload_0        
        //   171: bipush          36
        //   173: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   176: aload_1        
        //   177: aload_2        
        //   178: aload_2        
        //   179: aload_0        
        //   180: aload_1        
        //   181: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   184: ldc             "complete"
        //   186: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   189: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   192: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   195: pop            
        //   196: aload_0        
        //   197: bipush          37
        //   199: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   202: aload_1        
        //   203: aload_2        
        //   204: aload_0        
        //   205: bipush          38
        //   207: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   210: aload_1        
        //   211: aload_2        
        //   212: aload_0        
        //   213: bipush          39
        //   215: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   218: aload_1        
        //   219: aload_2        
        //   220: aload_2        
        //   221: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   224: aload_0        
        //   225: aload_1        
        //   226: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   229: ldc             "complete"
        //   231: invokevirtual   ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   234: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   237: aload           locals
        //   239: aload_1        
        //   240: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   243: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   246: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   249: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  21     229     4     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "result", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__15$RUBY$result(final FILE_631DF9934606CCF501A8510D342F0F539A7D19D9 file_631DF9934606CCF501A8510D342F0F539A7D19D9, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        file_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite(40).call(threadContext, rubyObject, file_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite(41).call(threadContext, rubyObject, rubyObject));
        return file_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite(42).call(threadContext, rubyObject, file_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite(43).call(threadContext, rubyObject, rubyObject));
    }
    
    @JRubyMethod(name = "callbacks", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__16$RUBY$callbacks(final FILE_631DF9934606CCF501A8510D342F0F539A7D19D9 file_631DF9934606CCF501A8510D342F0F539A7D19D9, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@callbacks") ? file_631DF9934606CCF501A8510D342F0F539A7D19D9.getByteList2() : null) == null) {
            rubyObject = file_631DF9934606CCF501A8510D342F0F539A7D19D9.setVariable0(threadContext.runtime, "@callbacks", object, RubyHash.newHash(threadContext.runtime));
        }
        else if (!(rubyObject = file_631DF9934606CCF501A8510D342F0F539A7D19D9.getVariable0(threadContext.runtime, "@callbacks", object)).isTrue()) {
            rubyObject = file_631DF9934606CCF501A8510D342F0F539A7D19D9.setVariable1(threadContext.runtime, "@callbacks", object, RubyHash.newHash(threadContext.runtime));
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "create", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__17$RUBY$create(final FILE_631DF9934606CCF501A8510D342F0F539A7D19D9 file_631DF9934606CCF501A8510D342F0F539A7D19D9, final ThreadContext context, final IRubyObject rubyObject, final IRubyObject value, final Block block) {
        final IRubyObject nil = context.nil;
        final IRubyObject call;
        final IRubyObject obj = call = file_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite(45).call(context, rubyObject, rubyObject);
        RuntimeHelpers.doAttrAsgn(call, RuntimeHelpers.selectAttrAsgnCallSite(call, rubyObject, file_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite(46), file_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite(47)), value, context, rubyObject);
        file_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite(48).call(context, rubyObject, value);
        file_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite(49).call(context, rubyObject, file_631DF9934606CCF501A8510D342F0F539A7D19D9.getCallSite(50).call(context, rubyObject, obj), value);
        return obj;
    }
    
    public static IRubyObject class_2$RUBY$Wizard(final FILE_631DF9934606CCF501A8510D342F0F539A7D19D9 file_631DF9934606CCF501A8510D342F0F539A7D19D9, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_2$RUBY$Wizard(file_631DF9934606CCF501A8510D342F0F539A7D19D9, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__1$RUBY$Questionnaire(final FILE_631DF9934606CCF501A8510D342F0F539A7D19D9 file_631DF9934606CCF501A8510D342F0F539A7D19D9, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Questionnaire(file_631DF9934606CCF501A8510D342F0F539A7D19D9, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_631DF9934606CCF501A8510D342F0F539A7D19D9 file_631DF9934606CCF501A8510D342F0F539A7D19D9, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_631DF9934606CCF501A8510D342F0F539A7D19D9, threadContext, rubyObject, block);
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
        final FILE_631DF9934606CCF501A8510D342F0F539A7D19D9 file_631DF9934606CCF501A8510D342F0F539A7D19D9 = new FILE_631DF9934606CCF501A8510D342F0F539A7D19D9();
        final String string = FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.class.getClassLoader().getResource("ruby/jit/FILE_631DF9934606CCF501A8510D342F0F539A7D19D9.class").toString();
        file_631DF9934606CCF501A8510D342F0F539A7D19D9.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_631DF9934606CCF501A8510D342F0F539A7D19D9.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
