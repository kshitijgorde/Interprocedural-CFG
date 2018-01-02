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
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3 extends AbstractScript
{
    public FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3() {
        this.filename = "./lib//lister/runner/questionnaire/question.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("include_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffinclude\uffffF\uffffinclude\uffffF\uffffattr_accessor\uffffF\uffffattr_reader\uffffF\uffffattr_accessor\uffffF\uffffcurrent_language\uffffN\uffffinterpret\uffffV\uffffnew\uffffN\uffffrespond_to?\uffffN\uffffdescription\uffffV\uffffeach\uffffN\uffffoutcomes\uffffN\uffffdescription\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffffidentifier\uffffN\ufffffind\uffffN\uffffoutcomes\uffffN\uffffdescription\uffffV\uffff==\uffffN\ufffftext\uffffN\ufffflanguage\uffffV\uffffidentifier\uffffN\uffffoutcome_for_text\uffffF\uffffprepare_result_hash\uffffV\uffffprepare_result_hash\uffffV\ufffftext\uffffN\uffffdescription\uffffV\ufffflanguage\uffffV\uffffmap\uffffN\uffffoutcomes\uffffN\uffffdescription\uffffV\ufffftext\uffffN\ufffflanguage\uffffV\uffffattr_reader\uffffF\uffffrespond_to?\uffffN\uffffcall\uffffN\uffff[]\uffffN\uffffwords\uffffV\uffffsend\uffffN\uffffinterpret\uffffV\uffffword\uffffF\uffffvalid_value?\uffffN\uffffdescription\uffffV\uffffmap\uffffN\uffffinvalid_verifications_for_value\uffffN\uffffdescription\uffffV\ufffftext\uffffN\ufffflanguage\uffffV\uffffclean_html_message\uffffF\uffffjoin\uffffN\uffffshow_message_dialog\uffffN\uffffwarning\uffffN\uffffinterpret\uffffV\uffffsuper\uffffS\uffffnew\uffffN\uffffdup\uffffN\uffffprepare\uffffV\uffffplace\uffffV\uffffprepare_question_label\uffffV\uffffprepare_panel\uffffV\uffffplace_question_label\uffffV\uffffplace_panel\uffffV\uffffplace_border\uffffV\uffffclean_html_message\uffffF\uffffquestion\uffffV\uffffnew\uffffN\uffffadd\uffffF\uffffquestion_label\uffffV\uffffmax\uffffN\uffffsize\uffffN\uffffoutcomes_and_separators\uffffN\uffffdescription\uffffV\uffffminimum_number_of_lines\uffffV\uffffnew\uffffN\uffffnew\uffffN\uffffnumber_of_lines\uffffV\uffffadd\uffffF\uffffpanel\uffffV\uffffset_border\uffffF\uffffcreate_empty_border\uffffN\uffff\u0003\n\u0002\u0000\u0010\u0000\u0000\u0004\b\u0004\u0000\u0000\u000e\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(9, "please make a choice", this.getEncoding0());
        this.setByteList(13, "</HTML>", this.getEncoding0());
        this.setByteList(11, "<HTML>", this.getEncoding0());
        this.setByteList(0, "javax.swing.JPanel", this.getEncoding0());
        this.setByteList(12, "\n", this.getEncoding0());
        this.setByteList(3, "javax.swing.BorderFactory", this.getEncoding0());
        this.setByteList(5, "java.awt.GridLayout", this.getEncoding0());
        this.setByteList(2, "javax.swing.JTextArea", this.getEncoding0());
        this.setByteList(4, "java.awt.BorderLayout", this.getEncoding0());
        this.setByteList(7, "lister/runner/questionnaire/has_listener", this.getEncoding0());
        this.setByteList(6, "javax.swing.JOptionPane", this.getEncoding0());
        this.setByteList(8, "lister/runner/questionnaire/html", this.getEncoding0());
        this.setByteList(1, "javax.swing.JLabel", this.getEncoding0());
        this.setByteList(10, "instance-variable", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3 file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite0().call(threadContext, rubyObject, rubyObject, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getString0(threadContext.runtime, 32));
        file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite1().call(threadContext, rubyObject, rubyObject, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getString1(threadContext.runtime, 32));
        file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite2().call(threadContext, rubyObject, rubyObject, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getString2(threadContext.runtime, 32));
        file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite3().call(threadContext, rubyObject, rubyObject, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getString3(threadContext.runtime, 32));
        file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite4().call(threadContext, rubyObject, rubyObject, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getString4(threadContext.runtime, 32));
        file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite5().call(threadContext, rubyObject, rubyObject, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getString5(threadContext.runtime, 32));
        file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite6().call(threadContext, rubyObject, rubyObject, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getString6(threadContext.runtime, 32));
        file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite7().call(threadContext, rubyObject, rubyObject, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getString7(threadContext.runtime, 32));
        file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite8().call(threadContext, rubyObject, rubyObject, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getString8(threadContext.runtime, 32));
        return module__0$RUBY$Lister(file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.module__1$RUBY$Questionnaire:(Lruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$Questionnaire(final FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_0        
        //    30: aload_1        
        //    31: ldc_w           "JPanel"
        //    34: bipush          15
        //    36: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    42: invokestatic    ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.class_2$RUBY$Question:(Lruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject class_2$RUBY$Question(final FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    17: ldc             "Question"
        //    19: swap           
        //    20: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    23: dup            
        //    24: astore_2       
        //    25: aload_1        
        //    26: swap           
        //    27: aload_0        
        //    28: aload_1        
        //    29: ldc             ",0,0,-1"
        //    31: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    34: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    37: aload_0        
        //    38: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite9:()Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload_2        
        //    44: aload_0        
        //    45: aload_1        
        //    46: ldc             "HasListeners"
        //    48: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    51: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    54: pop            
        //    55: aload_0        
        //    56: bipush          10
        //    58: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    61: aload_1        
        //    62: aload_2        
        //    63: aload_2        
        //    64: aload_0        
        //    65: aload_1        
        //    66: ldc             "HasHtml"
        //    68: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getConstant1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    71: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    74: pop            
        //    75: aload_0        
        //    76: bipush          11
        //    78: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    81: aload_1        
        //    82: aload_2        
        //    83: aload_2        
        //    84: aload_0        
        //    85: aload_1        
        //    86: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    89: ldc             "question"
        //    91: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    94: aload_0        
        //    95: aload_1        
        //    96: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    99: ldc             "result"
        //   101: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   104: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   107: pop            
        //   108: aload_0        
        //   109: bipush          12
        //   111: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   114: aload_1        
        //   115: aload_2        
        //   116: aload_2        
        //   117: aload_0        
        //   118: aload_1        
        //   119: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   122: ldc             "panel"
        //   124: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   127: aload_0        
        //   128: aload_1        
        //   129: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   132: ldc             "question_label"
        //   134: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   137: aload_0        
        //   138: aload_1        
        //   139: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   142: ldc             "params"
        //   144: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getSymbol4:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   147: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   150: pop            
        //   151: aload_1        
        //   152: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   155: aload_0        
        //   156: aload_1        
        //   157: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   160: ldc             "default"
        //   162: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getSymbol5:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   165: aload_0        
        //   166: aload_1        
        //   167: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   170: bipush          32
        //   172: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getString9:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   175: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructHash:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyHash;
        //   178: aload_1        
        //   179: ldc             "DEFAULT_CHOICES"
        //   181: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   184: pop            
        //   185: aload_0        
        //   186: bipush          13
        //   188: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   191: aload_1        
        //   192: aload_2        
        //   193: aload_2        
        //   194: aload_0        
        //   195: aload_1        
        //   196: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   199: ldc             "description"
        //   201: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getSymbol6:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   204: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   207: pop            
        //   208: aload_1        
        //   209: aload_2        
        //   210: aload_0        
        //   211: ldc             "language"
        //   213: ldc             "method__3$RUBY$language"
        //   215: ldc             ",0,0,-1"
        //   217: iconst_0       
        //   218: ldc             "./lib//lister/runner/questionnaire/question.rb"
        //   220: ldc             25
        //   222: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   225: ldc             "NONE"
        //   227: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   230: pop            
        //   231: aload_1        
        //   232: aload_2        
        //   233: aload_0        
        //   234: ldc_w           "prepare_result_hash"
        //   237: ldc_w           "method__4$RUBY$prepare_result_hash"
        //   240: ldc_w           "h,0,0,-1"
        //   243: iconst_0       
        //   244: ldc             "./lib//lister/runner/questionnaire/question.rb"
        //   246: ldc_w           29
        //   249: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   252: ldc             "NONE"
        //   254: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   257: pop            
        //   258: aload_1        
        //   259: aload_2        
        //   260: aload_0        
        //   261: ldc_w           "outcome_for_text"
        //   264: ldc_w           "method__5$RUBY$outcome_for_text"
        //   267: ldc_w           "txt,1,0,-1"
        //   270: iconst_1       
        //   271: ldc             "./lib//lister/runner/questionnaire/question.rb"
        //   273: ldc_w           39
        //   276: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   279: ldc_w           "qtxt"
        //   282: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   285: pop            
        //   286: aload_1        
        //   287: aload_2        
        //   288: aload_0        
        //   289: ldc_w           "id_for_text"
        //   292: ldc_w           "method__6$RUBY$id_for_text"
        //   295: ldc_w           "txt,1,0,-1"
        //   298: iconst_1       
        //   299: ldc             "./lib//lister/runner/questionnaire/question.rb"
        //   301: ldc_w           43
        //   304: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   307: ldc_w           "qtxt"
        //   310: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   313: pop            
        //   314: aload_1        
        //   315: aload_2        
        //   316: aload_0        
        //   317: ldc             "result"
        //   319: ldc_w           "method__7$RUBY$result"
        //   322: ldc             ",0,0,-1"
        //   324: iconst_0       
        //   325: ldc             "./lib//lister/runner/questionnaire/question.rb"
        //   327: ldc_w           47
        //   330: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   333: ldc             "NONE"
        //   335: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   338: pop            
        //   339: aload_1        
        //   340: aload_2        
        //   341: aload_0        
        //   342: ldc_w           "result_updated"
        //   345: ldc_w           "method__8$RUBY$result_updated"
        //   348: ldc             ",0,0,-1"
        //   350: iconst_0       
        //   351: ldc             "./lib//lister/runner/questionnaire/question.rb"
        //   353: ldc_w           51
        //   356: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   359: ldc             "NONE"
        //   361: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   364: pop            
        //   365: aload_1        
        //   366: aload_2        
        //   367: aload_0        
        //   368: ldc             "question"
        //   370: ldc_w           "method__9$RUBY$question"
        //   373: ldc             ",0,0,-1"
        //   375: iconst_0       
        //   376: ldc             "./lib//lister/runner/questionnaire/question.rb"
        //   378: ldc_w           54
        //   381: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   384: ldc             "NONE"
        //   386: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   389: pop            
        //   390: aload_1        
        //   391: aload_2        
        //   392: aload_0        
        //   393: ldc_w           "available_choices"
        //   396: ldc_w           "method__10$RUBY$available_choices"
        //   399: ldc             ",0,0,-1"
        //   401: iconst_0       
        //   402: ldc             "./lib//lister/runner/questionnaire/question.rb"
        //   404: ldc_w           58
        //   407: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   410: ldc             "NONE"
        //   412: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   415: pop            
        //   416: aload_0        
        //   417: bipush          43
        //   419: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   422: aload_1        
        //   423: aload_2        
        //   424: aload_2        
        //   425: aload_0        
        //   426: aload_1        
        //   427: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   430: ldc_w           "words"
        //   433: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getSymbol8:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   436: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   439: pop            
        //   440: aload_1        
        //   441: aload_2        
        //   442: aload_0        
        //   443: ldc_w           "interpret"
        //   446: ldc_w           "method__11$RUBY$interpret"
        //   449: ldc             ",0,0,-1"
        //   451: iconst_0       
        //   452: ldc             "./lib//lister/runner/questionnaire/question.rb"
        //   454: ldc_w           66
        //   457: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   460: ldc             "NONE"
        //   462: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   465: pop            
        //   466: aload_1        
        //   467: aload_2        
        //   468: aload_0        
        //   469: ldc_w           "word"
        //   472: ldc_w           "method__12$RUBY$word"
        //   475: ldc_w           "sym,1,0,-1"
        //   478: iconst_1       
        //   479: ldc             "./lib//lister/runner/questionnaire/question.rb"
        //   481: ldc_w           74
        //   484: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   487: ldc_w           "qsym"
        //   490: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   493: pop            
        //   494: aload_1        
        //   495: aload_2        
        //   496: aload_0        
        //   497: ldc_w           "sentence"
        //   500: ldc_w           "method__13$RUBY$sentence"
        //   503: ldc_w           "sym,1,0,-1"
        //   506: iconst_1       
        //   507: ldc             "./lib//lister/runner/questionnaire/question.rb"
        //   509: ldc_w           78
        //   512: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   515: ldc_w           "qsym"
        //   518: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   521: pop            
        //   522: aload_1        
        //   523: aload_2        
        //   524: aload_0        
        //   525: ldc_w           "valid?"
        //   528: ldc_w           "method__14$RUBY$valid_p_"
        //   531: ldc             ",0,0,-1"
        //   533: iconst_0       
        //   534: ldc             "./lib//lister/runner/questionnaire/question.rb"
        //   536: ldc_w           82
        //   539: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   542: ldc             "NONE"
        //   544: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   547: pop            
        //   548: aload_1        
        //   549: aload_2        
        //   550: aload_0        
        //   551: ldc_w           "show_help_for_invalidity"
        //   554: ldc_w           "method__15$RUBY$show_help_for_invalidity"
        //   557: ldc_w           "texts;html,0,0,-1"
        //   560: iconst_0       
        //   561: ldc             "./lib//lister/runner/questionnaire/question.rb"
        //   563: ldc_w           86
        //   566: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   569: ldc             "NONE"
        //   571: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   574: pop            
        //   575: aload_1        
        //   576: aload_2        
        //   577: aload_0        
        //   578: ldc_w           "initialize"
        //   581: ldc_w           "method__16$RUBY$initialize"
        //   584: ldc_w           "description;interpret;params,2,1,-1"
        //   587: bipush          -3
        //   589: ldc             "./lib//lister/runner/questionnaire/question.rb"
        //   591: ldc_w           97
        //   594: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   597: ldc_w           "qdescription;qinterpret;oparams"
        //   600: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   603: pop            
        //   604: aload_1        
        //   605: aload_2        
        //   606: aload_0        
        //   607: ldc_w           "prepare"
        //   610: ldc_w           "method__17$RUBY$prepare"
        //   613: ldc             ",0,0,-1"
        //   615: iconst_0       
        //   616: ldc             "./lib//lister/runner/questionnaire/question.rb"
        //   618: ldc_w           109
        //   621: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   624: ldc             "NONE"
        //   626: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   629: pop            
        //   630: aload_1        
        //   631: aload_2        
        //   632: aload_0        
        //   633: ldc_w           "place"
        //   636: ldc_w           "method__18$RUBY$place"
        //   639: ldc             ",0,0,-1"
        //   641: iconst_0       
        //   642: ldc             "./lib//lister/runner/questionnaire/question.rb"
        //   644: ldc_w           114
        //   647: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   650: ldc             "NONE"
        //   652: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   655: pop            
        //   656: aload_1        
        //   657: aload_2        
        //   658: aload_0        
        //   659: ldc_w           "prepare_question_label"
        //   662: ldc_w           "method__19$RUBY$prepare_question_label"
        //   665: ldc_w           "msg,0,0,-1"
        //   668: iconst_0       
        //   669: ldc             "./lib//lister/runner/questionnaire/question.rb"
        //   671: ldc_w           120
        //   674: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   677: ldc             "NONE"
        //   679: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   682: pop            
        //   683: aload_1        
        //   684: aload_2        
        //   685: aload_0        
        //   686: ldc_w           "place_question_label"
        //   689: ldc_w           "method__20$RUBY$place_question_label"
        //   692: ldc             ",0,0,-1"
        //   694: iconst_0       
        //   695: ldc             "./lib//lister/runner/questionnaire/question.rb"
        //   697: ldc_w           125
        //   700: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   703: ldc             "NONE"
        //   705: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   708: pop            
        //   709: aload_1        
        //   710: aload_2        
        //   711: aload_0        
        //   712: ldc_w           "minimum_number_of_lines"
        //   715: ldc_w           "method__21$RUBY$minimum_number_of_lines"
        //   718: ldc             ",0,0,-1"
        //   720: iconst_0       
        //   721: ldc             "./lib//lister/runner/questionnaire/question.rb"
        //   723: ldc_w           129
        //   726: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   729: ldc             "NONE"
        //   731: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   734: pop            
        //   735: aload_1        
        //   736: aload_2        
        //   737: aload_0        
        //   738: ldc_w           "number_of_lines"
        //   741: ldc_w           "method__22$RUBY$number_of_lines"
        //   744: ldc             ",0,0,-1"
        //   746: iconst_0       
        //   747: ldc             "./lib//lister/runner/questionnaire/question.rb"
        //   749: ldc_w           133
        //   752: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   755: ldc             "NONE"
        //   757: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   760: pop            
        //   761: aload_1        
        //   762: aload_2        
        //   763: aload_0        
        //   764: ldc_w           "prepare_panel"
        //   767: ldc_w           "method__23$RUBY$prepare_panel"
        //   770: ldc             ",0,0,-1"
        //   772: iconst_0       
        //   773: ldc             "./lib//lister/runner/questionnaire/question.rb"
        //   775: ldc_w           137
        //   778: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   781: ldc             "NONE"
        //   783: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   786: pop            
        //   787: aload_1        
        //   788: aload_2        
        //   789: aload_0        
        //   790: ldc_w           "place_panel"
        //   793: ldc_w           "method__24$RUBY$place_panel"
        //   796: ldc             ",0,0,-1"
        //   798: iconst_0       
        //   799: ldc             "./lib//lister/runner/questionnaire/question.rb"
        //   801: ldc_w           141
        //   804: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   807: ldc             "NONE"
        //   809: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   812: pop            
        //   813: aload_1        
        //   814: aload_2        
        //   815: aload_0        
        //   816: ldc_w           "place_border"
        //   819: ldc_w           "method__25$RUBY$place_border"
        //   822: ldc             ",0,0,-1"
        //   824: iconst_0       
        //   825: ldc             "./lib//lister/runner/questionnaire/question.rb"
        //   827: ldc_w           145
        //   830: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   833: ldc             "NONE"
        //   835: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   838: aload_1        
        //   839: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   842: goto            850
        //   845: aload_1        
        //   846: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   849: athrow         
        //   850: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  37     838    845    850    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "language", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__3$RUBY$language(final FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3 file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(14).call(threadContext, rubyObject, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(15).call(threadContext, rubyObject, rubyObject));
    }
    
    @JRubyMethod(name = "prepare_result_hash", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$prepare_result_hash(final FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3 file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3, final ThreadContext context, final IRubyObject self, final Block block) {
        final DynamicScope locals = context.getCurrentScope();
        locals.setValueZeroDepthZero(file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(16).call(context, self, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getConstant2(context, "Hash")));
        if (file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(17).call(context, self, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(18).call(context, self, self), file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getSymbol7(context.runtime, "outcomes")).isTrue()) {
            file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(19).callIter(context, self, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(20).call(context, self, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(21).call(context, self, self)), RuntimeHelpers.createBlock(context, self, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getBlockBody0(context, "block_0$RUBY$prepare_result_hash,1,o,false,2,./lib//lister/runner/questionnaire/question.rb,32,true")));
        }
        return locals.getValueZeroDepthZeroOrNil(context.nil);
    }
    
    public static IRubyObject block_0$RUBY$prepare_result_hash(final FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    25: aload           5
        //    27: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    30: aload_1        
        //    31: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    34: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    37: dup            
        //    38: aload_2        
        //    39: aload_0        
        //    40: bipush          22
        //    42: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    45: aload_0        
        //    46: bipush          23
        //    48: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    51: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //    54: aload_0        
        //    55: bipush          24
        //    57: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    60: aload_1        
        //    61: aload_2        
        //    62: aload           o
        //    64: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    67: aload_1        
        //    68: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    71: aload_1        
        //    72: aload_2        
        //    73: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    76: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     52      9     o     Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "outcome_for_text", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__5$RUBY$outcome_for_text(final FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3 file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3, final ThreadContext threadContext, final IRubyObject self, final IRubyObject valueZeroDepthZero, final Block block) {
        threadContext.getCurrentScope().setValueZeroDepthZero(valueZeroDepthZero);
        return file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(25).callIter(threadContext, self, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(26).call(threadContext, self, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(27).call(threadContext, self, self)), RuntimeHelpers.createBlock(threadContext, self, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getBlockBody1(threadContext, "block_1$RUBY$outcome_for_text,1,o,false,2,./lib//lister/runner/questionnaire/question.rb,40,true")));
    }
    
    public static IRubyObject block_1$RUBY$outcome_for_text(final FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    28: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_0        
        //    34: bipush          29
        //    36: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    39: aload_1        
        //    40: aload_2        
        //    41: aload           o
        //    43: aload_0        
        //    44: bipush          30
        //    46: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    49: aload_1        
        //    50: aload_2        
        //    51: aload_2        
        //    52: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    55: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    58: aload           5
        //    60: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    63: aload_1        
        //    64: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    67: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    70: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    73: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     49      9     o     Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "id_for_text", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__6$RUBY$id_for_text(final FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3 file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(31).call(threadContext, rubyObject, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(32).call(threadContext, rubyObject, rubyObject, rubyObject2));
    }
    
    @JRubyMethod(name = "result", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__7$RUBY$result(final FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3 file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@result") ? file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getByteList(10) : null) == null) {
            rubyObject = file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.setVariable0(threadContext.runtime, "@result", object, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(33).call(threadContext, object, object));
        }
        else if (!(rubyObject = file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getVariable0(threadContext.runtime, "@result", object)).isTrue()) {
            rubyObject = file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.setVariable1(threadContext.runtime, "@result", object, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(34).call(threadContext, object, object));
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "result_updated", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__8$RUBY$result_updated(final FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3 file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return threadContext.nil;
    }
    
    @JRubyMethod(name = "question", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__9$RUBY$question(final FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3 file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(35).call(threadContext, rubyObject, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(36).call(threadContext, rubyObject, rubyObject), file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(37).call(threadContext, rubyObject, rubyObject));
    }
    
    @JRubyMethod(name = "available_choices", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__10$RUBY$available_choices(final FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3 file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(38).callIter(threadContext, self, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(39).call(threadContext, self, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(40).call(threadContext, self, self)), RuntimeHelpers.createBlock(threadContext, self, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getBlockBody2(threadContext, "block_2$RUBY$available_choices,1,outcome,false,2,./lib//lister/runner/questionnaire/question.rb,59,true")));
    }
    
    public static IRubyObject block_2$RUBY$available_choices(final FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          41
        //    28: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload           outcome
        //    35: aload_0        
        //    36: bipush          42
        //    38: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload_2        
        //    44: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name     Signature
        //  -----  ------  ----  -------  ---------------------------------------
        //  25     26      9     outcome  Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "interpret", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__11$RUBY$interpret(final FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3 file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        return file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(44).call(threadContext, object, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getVariable1(threadContext.runtime, "@interpret", object), file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getSymbol9(threadContext.runtime, "call")).isTrue() ? file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(45).call(threadContext, object, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getVariable2(threadContext.runtime, "@interpret", object)) : file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getVariable3(threadContext.runtime, "@interpret", object);
    }
    
    @JRubyMethod(name = "word", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__12$RUBY$word(final FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    15: bipush          46
        //    17: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    20: aload_1        
        //    21: aload_2        
        //    22: aload_0        
        //    23: bipush          47
        //    25: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    28: aload_1        
        //    29: aload_2        
        //    30: aload_2        
        //    31: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    34: aload           locals
        //    36: aload_1        
        //    37: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    40: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    43: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    46: dup            
        //    47: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    52: ifne            65
        //    55: pop            
        //    56: aload           locals
        //    58: aload_1        
        //    59: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    62: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    65: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     52      5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "sentence", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__13$RUBY$sentence(final FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3 file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(48).call(threadContext, rubyObject, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(49).call(threadContext, rubyObject, rubyObject), file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(50).call(threadContext, rubyObject, rubyObject, rubyObject2));
    }
    
    @JRubyMethod(name = "valid?", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__14$RUBY$valid_p_(final FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3 file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(51).call(threadContext, rubyObject, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(52).call(threadContext, rubyObject, rubyObject), rubyObject);
    }
    
    @JRubyMethod(name = "show_help_for_invalidity", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__15$RUBY$show_help_for_invalidity(final FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3 file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        final DynamicScope locals = context.getCurrentScope();
        locals.setValueZeroDepthZero(file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(53).callIter(context, rubyObject, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(54).call(context, rubyObject, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(55).call(context, rubyObject, rubyObject), rubyObject), RuntimeHelpers.createBlock(context, rubyObject, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getBlockBody3(context, "block_3$RUBY$show_help_for_invalidity,1,verification,false,2,./lib//lister/runner/questionnaire/question.rb,87,true"))));
        locals.setValueOneDepthZero(file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(58).call(context, rubyObject, rubyObject, RubyString.newStringLight(context.runtime, 20).append(file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getString(context.runtime, 11, 32)).append(file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(59).call(context, rubyObject, locals.getValueZeroDepthZeroOrNil(context.nil), file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getString(context.runtime, 12, 32)).asString()).append(file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getString(context.runtime, 13, 32))));
        return file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(60).call(context, rubyObject, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getConstant3(context, "JOptionPane"), RuntimeHelpers.constructObjectArray(rubyObject, locals.getValueOneDepthZeroOrNil(context.nil), file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(61).call(context, rubyObject, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(62).call(context, rubyObject, rubyObject)), file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getConstantFrom5(RuntimeHelpers.checkIsModule(file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getConstant4(context, "JOptionPane")), context, "PLAIN_MESSAGE")));
    }
    
    public static IRubyObject block_3$RUBY$show_help_for_invalidity(final FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          56
        //    28: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload           verification
        //    35: aload_0        
        //    36: bipush          57
        //    38: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload_2        
        //    44: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name          Signature
        //  -----  ------  ----  ------------  ---------------------------------------
        //  25     26      9     verification  Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "initialize", frame = true, required = 2, optional = 1, rest = -1)
    public static IRubyObject method__16$RUBY$initialize(final FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     4: astore          11
        //     6: aload_1        
        //     7: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    10: aload_3        
        //    11: iconst_2       
        //    12: iconst_3       
        //    13: invokestatic    org/jruby/runtime/Arity.raiseArgumentError:(Lorg/jruby/Ruby;[Lorg/jruby/runtime/builtin/IRubyObject;II)V
        //    16: aload_3        
        //    17: iconst_0       
        //    18: aaload         
        //    19: astore          9
        //    21: aload_3        
        //    22: iconst_1       
        //    23: aaload         
        //    24: astore          10
        //    26: aload_3        
        //    27: iconst_2       
        //    28: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.elementOrNull:([Lorg/jruby/runtime/builtin/IRubyObject;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    31: dup            
        //    32: ifnull          40
        //    35: astore          11
        //    37: goto            50
        //    40: aload_1        
        //    41: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    44: invokestatic    org/jruby/RubyHash.newHash:(Lorg/jruby/Ruby;)Lorg/jruby/RubyHash;
        //    47: astore          11
        //    49: pop            
        //    50: aload_0        
        //    51: bipush          63
        //    53: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    56: aload_1        
        //    57: aload_2        
        //    58: aload_2        
        //    59: aload_0        
        //    60: bipush          64
        //    62: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    65: aload_1        
        //    66: aload_2        
        //    67: aload_0        
        //    68: aload_1        
        //    69: ldc_w           "BorderLayout"
        //    72: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getConstant6:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    75: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    78: aload           4
        //    80: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    83: pop            
        //    84: aload_0        
        //    85: aload_1        
        //    86: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    89: ldc_w           "@words"
        //    92: aload_2        
        //    93: aload_1        
        //    94: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    97: invokestatic    org/jruby/RubyHash.newHash:(Lorg/jruby/Ruby;)Lorg/jruby/RubyHash;
        //   100: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.setVariable2:(Lorg/jruby/Ruby;Ljava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   103: pop            
        //   104: aload_0        
        //   105: aload_1        
        //   106: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   109: ldc_w           "@params"
        //   112: aload_2        
        //   113: aload_0        
        //   114: bipush          65
        //   116: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   119: aload_1        
        //   120: aload_2        
        //   121: aload           params
        //   123: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   126: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.setVariable3:(Lorg/jruby/Ruby;Ljava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   129: pop            
        //   130: aload_0        
        //   131: aload_1        
        //   132: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   135: ldc_w           "@interpret"
        //   138: aload_2        
        //   139: aload           interpret
        //   141: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.setVariable4:(Lorg/jruby/Ruby;Ljava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   144: pop            
        //   145: aload_0        
        //   146: aload_1        
        //   147: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   150: ldc_w           "@description"
        //   153: aload_2        
        //   154: aload           description
        //   156: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.setVariable5:(Lorg/jruby/Ruby;Ljava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   159: pop            
        //   160: aload_0        
        //   161: bipush          66
        //   163: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   166: aload_1        
        //   167: aload_2        
        //   168: aload_2        
        //   169: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   172: pop            
        //   173: aload_0        
        //   174: bipush          67
        //   176: invokevirtual   ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   179: aload_1        
        //   180: aload_2        
        //   181: aload_2        
        //   182: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   185: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name         Signature
        //  -----  ------  ----  -----------  ---------------------------------------
        //  50     136     9     description  Lorg/jruby/runtime/builtin/IRubyObject;
        //  50     136     10    interpret    Lorg/jruby/runtime/builtin/IRubyObject;
        //  50     136     11    params       Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "prepare", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__17$RUBY$prepare(final FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3 file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(68).call(threadContext, rubyObject, rubyObject);
        return file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(69).call(threadContext, rubyObject, rubyObject);
    }
    
    @JRubyMethod(name = "place", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__18$RUBY$place(final FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3 file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(70).call(threadContext, rubyObject, rubyObject);
        file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(71).call(threadContext, rubyObject, rubyObject);
        return file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(72).call(threadContext, rubyObject, rubyObject);
    }
    
    @JRubyMethod(name = "prepare_question_label", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__19$RUBY$prepare_question_label(final FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3 file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3, final ThreadContext context, final IRubyObject object, final Block block) {
        IRubyObject msg = context.nil;
        msg = file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(73).call(context, object, object, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(74).call(context, object, object));
        return file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.setVariable6(context.runtime, "@question_label", object, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(75).call(context, object, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getConstant7(context, "JLabel"), msg));
    }
    
    @JRubyMethod(name = "place_question_label", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__20$RUBY$place_question_label(final FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3 file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(76).call(threadContext, rubyObject, rubyObject, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(77).call(threadContext, rubyObject, rubyObject), file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getConstantFrom9(RuntimeHelpers.checkIsModule(file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getConstant8(threadContext, "BorderLayout")), threadContext, "NORTH"));
    }
    
    @JRubyMethod(name = "minimum_number_of_lines", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__21$RUBY$minimum_number_of_lines(final FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3 file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getFixnum0(threadContext.runtime, 8);
    }
    
    @JRubyMethod(name = "number_of_lines", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__22$RUBY$number_of_lines(final FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3 file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(78).call(threadContext, rubyObject, RuntimeHelpers.constructRubyArray(threadContext.runtime, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(79).call(threadContext, rubyObject, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(80).call(threadContext, rubyObject, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(81).call(threadContext, rubyObject, rubyObject))), file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(82).call(threadContext, rubyObject, rubyObject)));
    }
    
    @JRubyMethod(name = "prepare_panel", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__23$RUBY$prepare_panel(final FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3 file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        return file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.setVariable7(threadContext.runtime, "@panel", object, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(83).call(threadContext, object, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getConstant(threadContext, "JPanel", 10), file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(84).call(threadContext, object, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getConstant(threadContext, "GridLayout", 11), file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(85).call(threadContext, object, object), RubyFixnum.one(threadContext.runtime))));
    }
    
    @JRubyMethod(name = "place_panel", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__24$RUBY$place_panel(final FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3 file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(86).call(threadContext, rubyObject, rubyObject, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(87).call(threadContext, rubyObject, rubyObject), file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getConstantFrom(RuntimeHelpers.checkIsModule(file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getConstant(threadContext, "BorderLayout", 12)), threadContext, "CENTER", 13));
    }
    
    @JRubyMethod(name = "place_border", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__25$RUBY$place_border(final FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3 file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        return file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(88).call(context, rubyObject, rubyObject, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getCallSite(89).call(context, rubyObject, file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getConstant(context, "BorderFactory", 14), RuntimeHelpers.constructObjectArray(file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getFixnum1(context.runtime, 10), file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getFixnum1(context.runtime, 10), file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getFixnum1(context.runtime, 10), file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.getFixnum1(context.runtime, 10))));
    }
    
    public static IRubyObject class_2$RUBY$Question(final FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3 file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_2$RUBY$Question(file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__1$RUBY$Questionnaire(final FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3 file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Questionnaire(file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3 file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3, threadContext, rubyObject, block);
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
        final FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3 file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3 = new FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3();
        final String string = FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.class.getClassLoader().getResource("ruby/jit/FILE_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.class").toString();
        file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_ADB7738E4741C87A7741FA6C1CA7F7FDE7EFA4F3.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
