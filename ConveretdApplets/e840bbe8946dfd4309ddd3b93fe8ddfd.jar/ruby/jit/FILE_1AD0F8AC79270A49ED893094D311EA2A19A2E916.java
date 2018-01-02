// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.Arity;
import org.jruby.RubyHash;
import org.jruby.RubyBoolean;
import org.jruby.runtime.CallSite;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916 extends AbstractScript
{
    public FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916() {
        this.filename = "./lib//lister/runner/questionnaire/radio_question.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("include_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffinclude\uffffF\uffffattr_reader\uffffF\ufffflistener\uffffF\uffff==\uffffN\uffffaction_command\uffffN\ufffftextfield_name\uffffN\uffffsource\uffffV\uffffsource\uffffV\ufffftext_selected=\uffffN\ufffftext_selected=\uffffV\uffffchose_result\uffffN\uffffsource\uffffV\uffffid_for_text\uffffN\uffffsource\uffffV\uffffaction_command\uffffN\ufffftext\uffffN\ufffftextfield\uffffN\uffffsource\uffffV\uffffsource\uffffV\ufffftext_selected=\uffffN\ufffftext_selected=\uffffV\uffffchose_result\uffffN\uffffsource\uffffV\uffffid_for_text\uffffN\uffffsource\uffffV\uffffaction_command\uffffN\ufffftextfield\uffffN\uffffsource\uffffV\ufffftextfield\uffffN\uffffsource\uffffV\ufffftext=\uffffN\ufffftext=\uffffV\ufffflistener\uffffF\uffffempty?\uffffN\ufffftext\uffffN\ufffftextfield\uffffN\uffffsource\uffffV\uffffchose_result\uffffN\uffffsource\uffffV\uffffid_for_text\uffffN\uffffsource\uffffV\ufffftextfield_name\uffffN\uffffsource\uffffV\ufffftext\uffffN\ufffftextfield\uffffN\uffffsource\uffffV\uffffset_selected\uffffN\uffffgroup\uffffN\uffffsource\uffffV\uffffmodel\uffffN\ufffftextfield_button\uffffN\uffffsource\uffffV\uffffeach\uffffN\uffffkeys\uffffN\uffffresult\uffffV\uffffresult\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffffresult\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffffsuper\uffffS\uffffprepare_circles\uffffV\uffffplace_circles\uffffV\uffffplace_other_choice\uffffV\uffffsuper\uffffS\uffffattr_reader\uffffF\uffffattr_accessor\uffffF\ufffftext_selected\uffffV\uffffeach\uffffN\uffffoutcomes\uffffN\uffffdescription\uffffV\uffffuser_input\uffffN\ufffftext\uffffN\ufffflanguage\uffffV\uffffnew\uffffN\uffffnew\uffffN\uffffnew\uffffN\uffffnew\uffffN\ufffftextfield_name\uffffV\uffffadd_action_listener\uffffN\ufffflistener\uffffF\uffffadd_caret_listener\uffffN\ufffftextfield\uffffV\ufffflistener\uffffF\uffffadd\uffffN\ufffftextpanel\uffffV\uffffadd\uffffN\ufffftextpanel\uffffV\ufffftextfield\uffffV\uffffnew\uffffN\ufffftext\uffffN\ufffflanguage\uffffV\uffffadd_action_listener\uffffN\ufffflistener\uffffF\uffffcircles\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffffidentifier\uffffN\uffffadd\uffffN\uffffgroup\uffffV\uffffnew\uffffN\uffffnew\uffffN\uffffeach_pair\uffffN\uffffcircles\uffffV\uffffadd\uffffN\uffffpanel\uffffV\ufffftextpanel\uffffV\uffffadd\uffffN\uffffpanel\uffffV\ufffftextpanel\uffffV\uffff\u0003\u0006\u0001\u0000\u000b\u0000\u0000\u0002\b\u0005\u0000\u0000\n\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(8, "", this.getEncoding0());
        this.setByteList(7, "lister/runner/questionnaire/question", this.getEncoding0());
        this.setByteList(6, "lister/runner/questionnaire/has_listener", this.getEncoding0());
        this.setByteList(5, "java.awt.BorderLayout", this.getEncoding0());
        this.setByteList(2, "javax.swing.ButtonGroup", this.getEncoding0());
        this.setByteList(4, "javax.swing.JTextField", this.getEncoding0());
        this.setByteList(1, "javax.swing.JRadioButton", this.getEncoding0());
        this.setByteList(3, "javax.swing.JPanel", this.getEncoding0());
        this.setByteList(0, "java.awt.GridLayout", this.getEncoding0());
        this.setByteList(9, "instance-variable", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916 file_1AD0F8AC79270A49ED893094D311EA2A19A2E916, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite0().call(threadContext, rubyObject, rubyObject, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getString0(threadContext.runtime, 32));
        file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite1().call(threadContext, rubyObject, rubyObject, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getString1(threadContext.runtime, 32));
        file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite2().call(threadContext, rubyObject, rubyObject, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getString2(threadContext.runtime, 32));
        file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite3().call(threadContext, rubyObject, rubyObject, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getString3(threadContext.runtime, 32));
        file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite4().call(threadContext, rubyObject, rubyObject, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getString4(threadContext.runtime, 32));
        file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite5().call(threadContext, rubyObject, rubyObject, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getString5(threadContext.runtime, 32));
        file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite6().call(threadContext, rubyObject, rubyObject, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getString6(threadContext.runtime, 32));
        file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite7().call(threadContext, rubyObject, rubyObject, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getString7(threadContext.runtime, 32));
        return module__0$RUBY$Lister(file_1AD0F8AC79270A49ED893094D311EA2A19A2E916, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.module__1$RUBY$Questionnaire:(Lruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$Questionnaire(final FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_0        
        //    30: aload_1        
        //    31: ldc_w           "Question"
        //    34: bipush          10
        //    36: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    42: invokestatic    ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.class_2$RUBY$RadioQuestion:(Lruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject class_2$RUBY$RadioQuestion(final FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    29: ldc             "RadioQuestion"
        //    31: swap           
        //    32: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    35: dup            
        //    36: astore_2       
        //    37: aload_1        
        //    38: swap           
        //    39: aload_0        
        //    40: aload_1        
        //    41: ldc             ",0,0,-1"
        //    43: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    46: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClass:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    49: aload_1        
        //    50: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //    53: astore          locals
        //    55: aload_0        
        //    56: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite8:()Lorg/jruby/runtime/CallSite;
        //    59: aload_1        
        //    60: aload_2        
        //    61: aload_2        
        //    62: aload_0        
        //    63: aload_1        
        //    64: ldc             "HasListeners"
        //    66: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    69: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    72: pop            
        //    73: aload_0        
        //    74: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite9:()Lorg/jruby/runtime/CallSite;
        //    77: aload_1        
        //    78: aload_2        
        //    79: aload_2        
        //    80: aload_0        
        //    81: aload_1        
        //    82: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    85: ldc             "circles"
        //    87: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    90: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    93: pop            
        //    94: aload_0        
        //    95: bipush          10
        //    97: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   100: aload_1        
        //   101: aload_2        
        //   102: aload_2        
        //   103: aload_0        
        //   104: aload_1        
        //   105: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   108: ldc             "circles"
        //   110: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   113: aload_1        
        //   114: aload_2        
        //   115: aload_0        
        //   116: aload_1        
        //   117: ldc             "block_0$RUBY$RadioQuestion,-1,,false,0,./lib//lister/runner/questionnaire/radio_question.rb,17,true"
        //   119: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getBlockBody0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   122: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   125: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   128: pop            
        //   129: aload_0        
        //   130: bipush          40
        //   132: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   135: aload_1        
        //   136: aload_2        
        //   137: aload_2        
        //   138: aload_0        
        //   139: aload_1        
        //   140: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   143: ldc_w           "textfield"
        //   146: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   149: aload_1        
        //   150: aload_2        
        //   151: aload_0        
        //   152: aload_1        
        //   153: ldc_w           "block_1$RUBY$RadioQuestion,-1,,false,0,./lib//lister/runner/questionnaire/radio_question.rb,33,true"
        //   156: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getBlockBody1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   159: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   162: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   165: pop            
        //   166: aload_1        
        //   167: aload_2        
        //   168: aload_0        
        //   169: ldc_w           "chose_result"
        //   172: ldc_w           "method__5$RUBY$chose_result"
        //   175: ldc_w           "id;val,1,1,-1"
        //   178: bipush          -2
        //   180: ldc             "./lib//lister/runner/questionnaire/radio_question.rb"
        //   182: ldc_w           44
        //   185: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   188: ldc_w           "qid;oval"
        //   191: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   194: pop            
        //   195: aload_1        
        //   196: aload_2        
        //   197: aload_0        
        //   198: ldc_w           "prepare"
        //   201: ldc_w           "method__6$RUBY$prepare"
        //   204: ldc             ",0,0,-1"
        //   206: iconst_0       
        //   207: ldc             "./lib//lister/runner/questionnaire/radio_question.rb"
        //   209: ldc_w           51
        //   212: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   215: ldc_w           "NONE"
        //   218: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   221: pop            
        //   222: aload_1        
        //   223: aload_2        
        //   224: aload_0        
        //   225: ldc_w           "place"
        //   228: ldc_w           "method__7$RUBY$place"
        //   231: ldc             ",0,0,-1"
        //   233: iconst_0       
        //   234: ldc             "./lib//lister/runner/questionnaire/radio_question.rb"
        //   236: ldc_w           56
        //   239: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   242: ldc_w           "NONE"
        //   245: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   248: pop            
        //   249: aload_1        
        //   250: aload_2        
        //   251: aload_0        
        //   252: ldc             "circles"
        //   254: ldc_w           "method__8$RUBY$circles"
        //   257: ldc             ",0,0,-1"
        //   259: iconst_0       
        //   260: ldc             "./lib//lister/runner/questionnaire/radio_question.rb"
        //   262: ldc_w           62
        //   265: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   268: ldc_w           "NONE"
        //   271: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   274: pop            
        //   275: aload_0        
        //   276: bipush          74
        //   278: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   281: aload_1        
        //   282: aload_2        
        //   283: aload_2        
        //   284: aload_0        
        //   285: aload_1        
        //   286: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   289: ldc_w           "textfield"
        //   292: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   295: aload_0        
        //   296: aload_1        
        //   297: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   300: ldc_w           "textpanel"
        //   303: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   306: aload_0        
        //   307: aload_1        
        //   308: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   311: ldc_w           "textfield_name"
        //   314: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   317: aload_0        
        //   318: aload_1        
        //   319: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   322: ldc_w           "textfield_button"
        //   325: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getSymbol4:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   328: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructObjectArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //   331: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   334: pop            
        //   335: aload_0        
        //   336: bipush          75
        //   338: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   341: aload_1        
        //   342: aload_2        
        //   343: aload_2        
        //   344: aload_0        
        //   345: aload_1        
        //   346: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   349: ldc_w           "text_selected"
        //   352: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getSymbol5:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   355: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   358: pop            
        //   359: aload_1        
        //   360: aload_2        
        //   361: aload_0        
        //   362: ldc_w           "text_selected?"
        //   365: ldc_w           "method__9$RUBY$text_selected_p_"
        //   368: ldc             ",0,0,-1"
        //   370: iconst_0       
        //   371: ldc             "./lib//lister/runner/questionnaire/radio_question.rb"
        //   373: ldc_w           69
        //   376: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   379: ldc_w           "NONE"
        //   382: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   385: pop            
        //   386: aload_1        
        //   387: aload_2        
        //   388: aload_0        
        //   389: ldc_w           "prepare_circles"
        //   392: ldc_w           "method__10$RUBY$prepare_circles"
        //   395: ldc             ",0,0,-1"
        //   397: iconst_0       
        //   398: ldc             "./lib//lister/runner/questionnaire/radio_question.rb"
        //   400: ldc_w           73
        //   403: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   406: ldc_w           "NONE"
        //   409: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   412: pop            
        //   413: aload_1        
        //   414: aload_2        
        //   415: aload_0        
        //   416: ldc_w           "group"
        //   419: ldc_w           "method__11$RUBY$group"
        //   422: ldc             ",0,0,-1"
        //   424: iconst_0       
        //   425: ldc             "./lib//lister/runner/questionnaire/radio_question.rb"
        //   427: ldc_w           95
        //   430: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   433: ldc_w           "NONE"
        //   436: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   439: pop            
        //   440: aload_1        
        //   441: aload_2        
        //   442: aload_0        
        //   443: ldc_w           "place_circles"
        //   446: ldc_w           "method__12$RUBY$place_circles"
        //   449: ldc             ",0,0,-1"
        //   451: iconst_0       
        //   452: ldc             "./lib//lister/runner/questionnaire/radio_question.rb"
        //   454: ldc_w           99
        //   457: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   460: ldc_w           "NONE"
        //   463: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   466: pop            
        //   467: aload_1        
        //   468: aload_2        
        //   469: aload_0        
        //   470: ldc_w           "place_other_choice"
        //   473: ldc_w           "method__13$RUBY$place_other_choice"
        //   476: ldc             ",0,0,-1"
        //   478: iconst_0       
        //   479: ldc             "./lib//lister/runner/questionnaire/radio_question.rb"
        //   481: ldc_w           105
        //   484: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   487: ldc_w           "NONE"
        //   490: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   493: aload_1        
        //   494: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   497: goto            505
        //   500: aload_1        
        //   501: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   504: athrow         
        //   505: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  55     451     4     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  55     493    500    505    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_0$RUBY$RadioQuestion(final FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916 scriptObject, final ThreadContext context, final IRubyObject self, final IRubyObject rubyObject, final Block block) {
        context.getCurrentScope();
        RuntimeHelpers.processBlockArgument(context.runtime, block);
        return RuntimeHelpers.def(context, self, scriptObject, "action_performed", "method__3$RUBY$action_performed", "event,1,0,-1", 1, "./lib//lister/runner/questionnaire/radio_question.rb", 18, CallConfiguration.FrameFullScopeNone, "qevent");
    }
    
    @JRubyMethod(name = "action_performed", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__3$RUBY$action_performed(final FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916 file_1AD0F8AC79270A49ED893094D311EA2A19A2E916, final ThreadContext context, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        IRubyObject rubyObject4;
        if (file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(11).call(context, rubyObject, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(12).call(context, rubyObject, rubyObject2), file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(13).call(context, rubyObject, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(14).call(context, rubyObject, rubyObject))).isTrue()) {
            final IRubyObject call = file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(15).call(context, rubyObject, rubyObject);
            final CallSite selectAttrAsgnCallSite = RuntimeHelpers.selectAttrAsgnCallSite(call, rubyObject, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(16), file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(17));
            final RubyBoolean true = context.runtime.getTrue();
            context.pollThreadEvents();
            RuntimeHelpers.doAttrAsgn(call, selectAttrAsgnCallSite, true, context, rubyObject);
            final CallSite callSite = file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(18);
            final IRubyObject call2 = file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(19).call(context, rubyObject, rubyObject);
            final IRubyObject call3 = file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(20).call(context, rubyObject, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(21).call(context, rubyObject, rubyObject), file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(22).call(context, rubyObject, rubyObject2));
            IRubyObject rubyObject3;
            if (!(rubyObject3 = file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(23).call(context, rubyObject, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(24).call(context, rubyObject, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(25).call(context, rubyObject, rubyObject)))).isTrue()) {
                rubyObject3 = file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getString8(context.runtime, 32);
            }
            rubyObject4 = callSite.call(context, rubyObject, call2, call3, rubyObject3);
        }
        else {
            final IRubyObject call4 = file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(26).call(context, rubyObject, rubyObject);
            final CallSite selectAttrAsgnCallSite2 = RuntimeHelpers.selectAttrAsgnCallSite(call4, rubyObject, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(27), file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(28));
            final RubyBoolean false = context.runtime.getFalse();
            context.pollThreadEvents();
            RuntimeHelpers.doAttrAsgn(call4, selectAttrAsgnCallSite2, false, context, rubyObject);
            file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(29).call(context, rubyObject, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(30).call(context, rubyObject, rubyObject), file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(31).call(context, rubyObject, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(32).call(context, rubyObject, rubyObject), file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(33).call(context, rubyObject, rubyObject2)));
            if (file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(34).call(context, rubyObject, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(35).call(context, rubyObject, rubyObject)).isTrue()) {
                final IRubyObject call5 = file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(36).call(context, rubyObject, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(37).call(context, rubyObject, rubyObject));
                rubyObject4 = RuntimeHelpers.doAttrAsgn(call5, RuntimeHelpers.selectAttrAsgnCallSite(call5, rubyObject, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(38), file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(39)), file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getString8(context.runtime, 32), context, rubyObject);
            }
            else {
                rubyObject4 = context.nil;
            }
        }
        return rubyObject4;
    }
    
    public static IRubyObject block_1$RUBY$RadioQuestion(final FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916 scriptObject, final ThreadContext context, final IRubyObject self, final IRubyObject rubyObject, final Block block) {
        context.getCurrentScope();
        RuntimeHelpers.processBlockArgument(context.runtime, block);
        return RuntimeHelpers.def(context, self, scriptObject, "caret_update", "method__4$RUBY$caret_update", "event,1,0,-1", 1, "./lib//lister/runner/questionnaire/radio_question.rb", 34, CallConfiguration.FrameNoneScopeNone, "qevent");
    }
    
    @JRubyMethod(name = "caret_update", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$caret_update(final FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916 file_1AD0F8AC79270A49ED893094D311EA2A19A2E916, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        IRubyObject rubyObject3;
        if (file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(41).call(threadContext, rubyObject, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(42).call(threadContext, rubyObject, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(43).call(threadContext, rubyObject, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(44).call(threadContext, rubyObject, rubyObject)))).isTrue()) {
            rubyObject3 = threadContext.nil;
        }
        else {
            final CallSite callSite = file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(45);
            final IRubyObject call = file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(46).call(threadContext, rubyObject, rubyObject);
            final IRubyObject call2 = file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(47).call(threadContext, rubyObject, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(48).call(threadContext, rubyObject, rubyObject), file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(49).call(threadContext, rubyObject, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(50).call(threadContext, rubyObject, rubyObject)));
            IRubyObject rubyObject4;
            if (!(rubyObject4 = file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(51).call(threadContext, rubyObject, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(52).call(threadContext, rubyObject, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(53).call(threadContext, rubyObject, rubyObject)))).isTrue()) {
                rubyObject4 = file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getString8(threadContext.runtime, 32);
            }
            callSite.call(threadContext, rubyObject, call, call2, rubyObject4);
            final CallSite callSite2 = file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(54);
            final IRubyObject call3 = file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(55).call(threadContext, rubyObject, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(56).call(threadContext, rubyObject, rubyObject));
            final IRubyObject call4 = file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(57).call(threadContext, rubyObject, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(58).call(threadContext, rubyObject, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(59).call(threadContext, rubyObject, rubyObject)));
            final RubyBoolean true = threadContext.runtime.getTrue();
            threadContext.pollThreadEvents();
            rubyObject3 = callSite2.call(threadContext, rubyObject, call3, call4, true);
        }
        return rubyObject3;
    }
    
    @JRubyMethod(name = "chose_result", frame = true, required = 1, optional = 1, rest = -1)
    public static IRubyObject method__5$RUBY$chose_result(final FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4) {
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
        //    10: aload_3        
        //    11: iconst_1       
        //    12: iconst_2       
        //    13: invokestatic    org/jruby/runtime/Arity.raiseArgumentError:(Lorg/jruby/Ruby;[Lorg/jruby/runtime/builtin/IRubyObject;II)V
        //    16: aload_3        
        //    17: iconst_0       
        //    18: aaload         
        //    19: aload           5
        //    21: swap           
        //    22: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    25: pop            
        //    26: aload_3        
        //    27: iconst_1       
        //    28: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.elementOrNull:([Lorg/jruby/runtime/builtin/IRubyObject;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    31: dup            
        //    32: ifnull          45
        //    35: aload           5
        //    37: swap           
        //    38: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    41: pop            
        //    42: goto            63
        //    45: aload           5
        //    47: aload_1        
        //    48: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    51: invokevirtual   org/jruby/Ruby.getTrue:()Lorg/jruby/RubyBoolean;
        //    54: aload_1        
        //    55: invokevirtual   org/jruby/runtime/ThreadContext.pollThreadEvents:()V
        //    58: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    61: pop            
        //    62: pop            
        //    63: aload_0        
        //    64: bipush          60
        //    66: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    69: aload_1        
        //    70: aload_2        
        //    71: aload_0        
        //    72: bipush          61
        //    74: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    77: aload_1        
        //    78: aload_2        
        //    79: aload_0        
        //    80: bipush          62
        //    82: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    85: aload_1        
        //    86: aload_2        
        //    87: aload_2        
        //    88: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    91: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    94: aload_1        
        //    95: aload_2        
        //    96: aload_0        
        //    97: aload_1        
        //    98: ldc_w           "block_2$RUBY$chose_result,1,k,false,2,./lib//lister/runner/questionnaire/radio_question.rb,45,true"
        //   101: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getBlockBody2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   104: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   107: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   110: pop            
        //   111: aload_0        
        //   112: bipush          66
        //   114: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   117: aload_1        
        //   118: aload_2        
        //   119: aload_2        
        //   120: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   123: dup            
        //   124: aload_2        
        //   125: aload_0        
        //   126: bipush          67
        //   128: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   131: aload_0        
        //   132: bipush          68
        //   134: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   137: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   140: aload           locals
        //   142: aload_1        
        //   143: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   146: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   149: aload           locals
        //   151: aload_1        
        //   152: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   155: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   158: aload_1        
        //   159: aload_2        
        //   160: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   163: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  63     101     5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_2$RUBY$chose_result(final FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          63
        //    28: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_2        
        //    34: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    37: dup            
        //    38: aload_2        
        //    39: aload_0        
        //    40: bipush          64
        //    42: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    45: aload_0        
        //    46: bipush          65
        //    48: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    51: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //    54: aload           k
        //    56: aload_1        
        //    57: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    60: invokevirtual   org/jruby/Ruby.getFalse:()Lorg/jruby/RubyBoolean;
        //    63: aload_1        
        //    64: invokevirtual   org/jruby/runtime/ThreadContext.pollThreadEvents:()V
        //    67: aload_1        
        //    68: aload_2        
        //    69: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    72: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     48      9     k     Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "prepare", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__6$RUBY$prepare(final FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916 file_1AD0F8AC79270A49ED893094D311EA2A19A2E916, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(69).call(context, rubyObject, rubyObject, RuntimeHelpers.getArgValues(context), block);
        return file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(70).call(context, rubyObject, rubyObject);
    }
    
    @JRubyMethod(name = "place", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__7$RUBY$place(final FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916 file_1AD0F8AC79270A49ED893094D311EA2A19A2E916, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(71).call(context, rubyObject, rubyObject);
        file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(72).call(context, rubyObject, rubyObject);
        return file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(73).call(context, rubyObject, rubyObject, RuntimeHelpers.getArgValues(context), block);
    }
    
    @JRubyMethod(name = "circles", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__8$RUBY$circles(final FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916 file_1AD0F8AC79270A49ED893094D311EA2A19A2E916, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@circles") ? file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getByteList9() : null) == null) {
            rubyObject = file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.setVariable0(threadContext.runtime, "@circles", object, RubyHash.newHash(threadContext.runtime));
        }
        else if (!(rubyObject = file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getVariable0(threadContext.runtime, "@circles", object)).isTrue()) {
            rubyObject = file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.setVariable1(threadContext.runtime, "@circles", object, RubyHash.newHash(threadContext.runtime));
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "text_selected?", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__9$RUBY$text_selected_p_(final FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916 file_1AD0F8AC79270A49ED893094D311EA2A19A2E916, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        IRubyObject rubyObject2;
        if ((rubyObject2 = file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(76).call(threadContext, rubyObject, rubyObject)).isTrue()) {
            rubyObject2 = threadContext.runtime.getTrue();
            threadContext.pollThreadEvents();
        }
        return rubyObject2;
    }
    
    @JRubyMethod(name = "prepare_circles", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__10$RUBY$prepare_circles(final FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916 file_1AD0F8AC79270A49ED893094D311EA2A19A2E916, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(77).callIter(threadContext, self, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(78).call(threadContext, self, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(79).call(threadContext, self, self)), RuntimeHelpers.createBlock(threadContext, self, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getBlockBody3(threadContext, "block_3$RUBY$prepare_circles,1,outcome;button,false,2,./lib//lister/runner/questionnaire/radio_question.rb,74,true")));
    }
    
    public static IRubyObject block_3$RUBY$prepare_circles(final FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    13: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    16: astore          10
        //    18: aload_1        
        //    19: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    22: aload           4
        //    24: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    27: aload_3        
        //    28: astore          9
        //    30: pop            
        //    31: aload_1        
        //    32: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    35: astore          button
        //    37: aload_0        
        //    38: bipush          80
        //    40: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    43: aload_1        
        //    44: aload_2        
        //    45: aload           outcome
        //    47: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    55: ifeq            419
        //    58: aload_0        
        //    59: aload_1        
        //    60: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    63: ldc_w           "@textfield_name"
        //    66: aload_2        
        //    67: aload_0        
        //    68: bipush          81
        //    70: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    73: aload_1        
        //    74: aload_2        
        //    75: aload           outcome
        //    77: aload_0        
        //    78: bipush          82
        //    80: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    83: aload_1        
        //    84: aload_2        
        //    85: aload_2        
        //    86: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    89: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    92: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.setVariable2:(Lorg/jruby/Ruby;Ljava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    95: pop            
        //    96: aload_0        
        //    97: aload_1        
        //    98: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   101: ldc_w           "@textpanel"
        //   104: aload_2        
        //   105: aload_0        
        //   106: bipush          83
        //   108: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   111: aload_1        
        //   112: aload_2        
        //   113: aload_0        
        //   114: aload_1        
        //   115: ldc_w           "JPanel"
        //   118: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getConstant1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   121: aload_0        
        //   122: bipush          84
        //   124: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   127: aload_1        
        //   128: aload_2        
        //   129: aload_0        
        //   130: aload_1        
        //   131: ldc_w           "FlowLayout"
        //   134: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getConstant2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   137: aload_0        
        //   138: aload_1        
        //   139: ldc_w           "FlowLayout"
        //   142: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getConstant3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   145: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   148: aload_0        
        //   149: swap           
        //   150: aload_1        
        //   151: ldc_w           "LEFT"
        //   154: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getConstantFrom4:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   157: aload_1        
        //   158: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   161: invokestatic    org/jruby/RubyFixnum.zero:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   164: aload_1        
        //   165: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   168: invokestatic    org/jruby/RubyFixnum.five:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   171: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   174: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   177: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.setVariable3:(Lorg/jruby/Ruby;Ljava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   180: pop            
        //   181: aload_0        
        //   182: aload_1        
        //   183: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   186: ldc_w           "@textfield"
        //   189: aload_2        
        //   190: aload_0        
        //   191: bipush          85
        //   193: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   196: aload_1        
        //   197: aload_2        
        //   198: aload_0        
        //   199: aload_1        
        //   200: ldc_w           "JTextField"
        //   203: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getConstant5:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   206: aload_0        
        //   207: aload_1        
        //   208: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   211: bipush          20
        //   213: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getFixnum0:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   216: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   219: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.setVariable4:(Lorg/jruby/Ruby;Ljava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   222: pop            
        //   223: aload_0        
        //   224: bipush          86
        //   226: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   229: aload_1        
        //   230: aload_2        
        //   231: aload_0        
        //   232: aload_1        
        //   233: ldc_w           "JRadioButton"
        //   236: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getConstant6:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   239: aload_0        
        //   240: bipush          87
        //   242: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   245: aload_1        
        //   246: aload_2        
        //   247: aload_2        
        //   248: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   251: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   254: astore          button
        //   256: aload_0        
        //   257: aload_1        
        //   258: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   261: ldc_w           "@textfield_button"
        //   264: aload_2        
        //   265: aload           button
        //   267: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.setVariable5:(Lorg/jruby/Ruby;Ljava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   270: pop            
        //   271: aload_0        
        //   272: bipush          88
        //   274: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   277: aload_1        
        //   278: aload_2        
        //   279: aload           button
        //   281: aload_0        
        //   282: bipush          89
        //   284: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   287: aload_1        
        //   288: aload_2        
        //   289: aload_2        
        //   290: aload_0        
        //   291: aload_1        
        //   292: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   295: ldc             "circles"
        //   297: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   300: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   303: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   306: pop            
        //   307: aload_0        
        //   308: bipush          90
        //   310: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   313: aload_1        
        //   314: aload_2        
        //   315: aload_0        
        //   316: bipush          91
        //   318: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   321: aload_1        
        //   322: aload_2        
        //   323: aload_2        
        //   324: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   327: aload_0        
        //   328: bipush          92
        //   330: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   333: aload_1        
        //   334: aload_2        
        //   335: aload_2        
        //   336: aload_0        
        //   337: aload_1        
        //   338: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   341: ldc_w           "textfield"
        //   344: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   347: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   350: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   353: pop            
        //   354: aload_0        
        //   355: bipush          93
        //   357: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   360: aload_1        
        //   361: aload_2        
        //   362: aload_0        
        //   363: bipush          94
        //   365: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   368: aload_1        
        //   369: aload_2        
        //   370: aload_2        
        //   371: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   374: aload           button
        //   376: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   379: pop            
        //   380: aload_0        
        //   381: bipush          95
        //   383: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   386: aload_1        
        //   387: aload_2        
        //   388: aload_0        
        //   389: bipush          96
        //   391: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   394: aload_1        
        //   395: aload_2        
        //   396: aload_2        
        //   397: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   400: aload_0        
        //   401: bipush          97
        //   403: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   406: aload_1        
        //   407: aload_2        
        //   408: aload_2        
        //   409: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   412: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   415: pop            
        //   416: goto            551
        //   419: aload_0        
        //   420: bipush          98
        //   422: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   425: aload_1        
        //   426: aload_2        
        //   427: aload_0        
        //   428: aload_1        
        //   429: ldc_w           "JRadioButton"
        //   432: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getConstant7:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   435: aload_0        
        //   436: bipush          99
        //   438: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   441: aload_1        
        //   442: aload_2        
        //   443: aload           outcome
        //   445: aload_0        
        //   446: bipush          100
        //   448: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   451: aload_1        
        //   452: aload_2        
        //   453: aload_2        
        //   454: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   457: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   460: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   463: astore          button
        //   465: aload_0        
        //   466: bipush          101
        //   468: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   471: aload_1        
        //   472: aload_2        
        //   473: aload           button
        //   475: aload_0        
        //   476: bipush          102
        //   478: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   481: aload_1        
        //   482: aload_2        
        //   483: aload_2        
        //   484: aload_0        
        //   485: aload_1        
        //   486: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   489: ldc             "circles"
        //   491: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   494: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   497: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   500: pop            
        //   501: aload_0        
        //   502: bipush          103
        //   504: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   507: aload_1        
        //   508: aload_2        
        //   509: aload_2        
        //   510: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   513: dup            
        //   514: aload_2        
        //   515: aload_0        
        //   516: bipush          104
        //   518: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   521: aload_0        
        //   522: bipush          105
        //   524: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   527: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   530: aload_0        
        //   531: bipush          106
        //   533: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   536: aload_1        
        //   537: aload_2        
        //   538: aload           outcome
        //   540: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   543: aload           button
        //   545: aload_1        
        //   546: aload_2        
        //   547: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   550: pop            
        //   551: aload_0        
        //   552: bipush          107
        //   554: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   557: aload_1        
        //   558: aload_2        
        //   559: aload_0        
        //   560: bipush          108
        //   562: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   565: aload_1        
        //   566: aload_2        
        //   567: aload_2        
        //   568: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   571: aload           button
        //   573: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   576: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name     Signature
        //  -----  ------  ----  -------  ---------------------------------------
        //  31     546     9     outcome  Lorg/jruby/runtime/builtin/IRubyObject;
        //  31     546     10    button   Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "group", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__11$RUBY$group(final FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916 file_1AD0F8AC79270A49ED893094D311EA2A19A2E916, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@group") ? file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getByteList9() : null) == null) {
            rubyObject = file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.setVariable6(threadContext.runtime, "@group", object, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(109).call(threadContext, object, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getConstant8(threadContext, "ButtonGroup")));
        }
        else if (!(rubyObject = file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getVariable1(threadContext.runtime, "@group", object)).isTrue()) {
            rubyObject = file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.setVariable7(threadContext.runtime, "@group", object, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(110).call(threadContext, object, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getConstant9(threadContext, "ButtonGroup")));
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "place_circles", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__12$RUBY$place_circles(final FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916 file_1AD0F8AC79270A49ED893094D311EA2A19A2E916, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(111).callIter(threadContext, self, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(112).call(threadContext, self, self), RuntimeHelpers.createBlock(threadContext, self, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getBlockBody4(threadContext, "block_4$RUBY$place_circles,2,sym;circle,true,1,./lib//lister/runner/questionnaire/radio_question.rb,100,true")));
    }
    
    public static IRubyObject block_4$RUBY$place_circles(final FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    13: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    16: astore          10
        //    18: aload_1        
        //    19: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    22: aload           4
        //    24: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    27: aload_3        
        //    28: aload_1        
        //    29: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    32: iconst_1       
        //    33: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.ensureMultipleAssignableRubyArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;Z)Lorg/jruby/RubyArray;
        //    36: astore          11
        //    38: aload           11
        //    40: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilZero:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    43: astore          9
        //    45: aload           11
        //    47: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilOne:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: astore          10
        //    52: aload           11
        //    54: pop            
        //    55: pop            
        //    56: aload_0        
        //    57: bipush          113
        //    59: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    62: aload_1        
        //    63: aload_2        
        //    64: aload_0        
        //    65: bipush          114
        //    67: invokevirtual   ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    70: aload_1        
        //    71: aload_2        
        //    72: aload_2        
        //    73: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    76: aload           circle
        //    78: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    81: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  ---------------------------------------
        //  56     26      9     sym     Lorg/jruby/runtime/builtin/IRubyObject;
        //  56     26      10    circle  Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "place_other_choice", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__13$RUBY$place_other_choice(final FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916 file_1AD0F8AC79270A49ED893094D311EA2A19A2E916, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(115).call(threadContext, rubyObject, rubyObject).isTrue() ? file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(116).call(threadContext, rubyObject, file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(117).call(threadContext, rubyObject, rubyObject), file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.getCallSite(118).call(threadContext, rubyObject, rubyObject)) : threadContext.nil;
    }
    
    public static IRubyObject class_2$RUBY$RadioQuestion(final FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916 file_1AD0F8AC79270A49ED893094D311EA2A19A2E916, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_2$RUBY$RadioQuestion(file_1AD0F8AC79270A49ED893094D311EA2A19A2E916, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__1$RUBY$Questionnaire(final FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916 file_1AD0F8AC79270A49ED893094D311EA2A19A2E916, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Questionnaire(file_1AD0F8AC79270A49ED893094D311EA2A19A2E916, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916 file_1AD0F8AC79270A49ED893094D311EA2A19A2E916, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_1AD0F8AC79270A49ED893094D311EA2A19A2E916, threadContext, rubyObject, block);
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
        final FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916 file_1AD0F8AC79270A49ED893094D311EA2A19A2E916 = new FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916();
        final String string = FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.class.getClassLoader().getResource("ruby/jit/FILE_1AD0F8AC79270A49ED893094D311EA2A19A2E916.class").toString();
        file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_1AD0F8AC79270A49ED893094D311EA2A19A2E916.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
