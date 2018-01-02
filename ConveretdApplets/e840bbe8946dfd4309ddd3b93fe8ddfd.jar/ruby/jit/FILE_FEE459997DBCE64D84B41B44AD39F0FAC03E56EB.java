// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.Arity;
import org.jruby.RubyString;
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

public class FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB extends AbstractScript
{
    public FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB() {
        this.filename = "./lib//lister/runner/questionnaire/checkbox_question.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("include_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffnew\uffffN\uffffinclude\uffffF\ufffflistener\uffffF\uffffupdate_user_input_result_for_id\uffffN\uffffquestion\uffffN\uffffsource\uffffV\uffffidentifier\uffffN\uffffsource\uffffV\ufffftext\uffffN\ufffffield\uffffN\uffffsource\uffffV\ufffflistener\uffffF\uffffat\uffffN\uffffcombo_ids\uffffN\uffffsource\uffffV\uffffselected_index\uffffN\uffffcombobox\uffffN\uffffsource\uffffV\uffffupdate_user_choice_result_for_id\uffffN\uffffquestion\uffffN\uffffsource\uffffV\uffffidentifier\uffffN\uffffsource\uffffV\ufffflistener\uffffF\uffffitem_selectable\uffffN\uffffid_for_text\uffffN\uffffsource\uffffV\ufffftext\uffffN\uffff==\uffffN\uffffstate_change\uffffN\uffffupdate_checked_result_for_id\uffffN\uffffsource\uffffV\uffffattr_reader\uffffF\uffff[]\uffffN\uffffparams\uffffV\uffffsuper\uffffS\uffffprepare_checkboxs\uffffV\uffffplace_boxes\uffffV\uffffsuper\uffffS\uffffresult\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffffresult_updated\uffffV\uffff[]\uffffN\uffffresult\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffffresult_updated\uffffV\uffff[]\uffffN\uffffresult\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffffresult_updated\uffffV\uffffeach\uffffN\uffffoutcomes_and_separators\uffffN\uffffdescription\uffffV\uffffis_a?\uffffN\uffffadd_separator\uffffV\uffffuser_input\uffffN\uffffempty?\uffffN\uffffchoices\uffffN\uffffadd_user_input_textbox_for_outcome\uffffF\uffffadd_user_input_textbox_with_combo_box_for_outcome\uffffF\uffffadd_simple_checkbox_for_outcome\uffffF\uffff<<\uffffN\uffffboxes\uffffV\uffffnew\uffffN\uffffprivate\uffffV\uffffnew\uffffN\ufffftext\uffffN\ufffflanguage\uffffV\uffffselect_by_default?\uffffF\uffffselected=\uffffN\uffffselected=\uffffV\uffffresult\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffffidentifier\uffffN\uffffselected=\uffffN\uffffselected=\uffffV\uffffresult\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffffidentifier\uffffN\uffffadd_item_listener\uffffN\ufffflistener\uffffF\uffff<<\uffffN\uffffboxes\uffffV\uffffsize\uffffN\ufffftext\uffffN\ufffflanguage\uffffV\uffffmax\uffffN\uffffmap\uffffN\uffffchoices\uffffN\uffffsize\uffffN\ufffftext\uffffN\ufffflanguage\uffffV\uffff-\uffffN\uffff-\uffffN\uffffmax\uffffN\uffffmin_textbox_length\uffffV\uffffmin\uffffN\uffffcompact\uffffN\uffffexpected_length\uffffN\ufffftext\uffffN\ufffflanguage\uffffV\uffffmap\uffffN\uffffchoices\uffffN\ufffftext\uffffN\ufffflanguage\uffffV\uffffmap\uffffN\uffffchoices\uffffN\uffffidentifier\uffffN\uffffnew\uffffN\uffffnew\uffffN\uffffnew\uffffN\ufffftextbox_length_for_outcome\uffffF\uffffnew\uffffN\uffffto_java\uffffN\uffffnew\uffffN\uffffidentifier\uffffN\uffffnew\uffffN\uffffresult\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffffidentifier\uffffN\uffffidentifier\uffffN\ufffffirst\uffffN\uffffchoices\uffffN\uffffeach\uffffN\uffffwidgets_order\uffffN\uffffadd\uffffN\uffff[]\uffffN\uffffadd_action_listener\uffffN\ufffflistener\uffffN\uffffadd_caret_listener\uffffN\ufffflistener\uffffN\uffff<<\uffffN\uffffboxes\uffffV\ufffftext\uffffN\ufffflanguage\uffffV\uffffnew\uffffN\uffffnew\uffffN\uffffnew\uffffN\ufffftextbox_length_for_outcome\uffffF\uffffdefault_value\uffffN\ufffftext=\uffffN\ufffftext=\uffffV\uffffdefault_value\uffffN\uffffnew\uffffN\uffffidentifier\uffffN\uffffresult\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffffidentifier\uffffN\uffffadd\uffffN\uffffnew\uffffN\uffffadd\uffffN\uffffadd_caret_listener\uffffN\ufffflistener\uffffN\uffff<<\uffffN\uffffboxes\uffffV\uffffpublic\uffffV\uffffeach\uffffN\uffffboxes\uffffV\uffffrespond_to?\uffffN\uffffadd\uffffN\uffffpanel\uffffV\uffffpanel\uffffN\uffffadd\uffffN\uffffpanel\uffffV\uffff\u0003\r\u0002\u0000\u0019\u0000\u0000\u0001\u0002\n\u0000\u0000\f\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(11, "", this.getEncoding0());
        this.setByteList(8, "lister/runner/questionnaire/question", this.getEncoding0());
        this.setByteList(0, "java.awt.event.ItemEvent", this.getEncoding0());
        this.setByteList(7, "lister/runner/questionnaire/has_listener", this.getEncoding0());
        this.setByteList(6, "javax.swing.JSeparator", this.getEncoding0());
        this.setByteList(1, "javax.swing.JCheckBox", this.getEncoding0());
        this.setByteList(5, "javax.swing.JLabel", this.getEncoding0());
        this.setByteList(2, "javax.swing.JTextField", this.getEncoding0());
        this.setByteList(10, ":", this.getEncoding0());
        this.setByteList(4, "javax.swing.JPanel", this.getEncoding0());
        this.setByteList(3, "javax.swing.JComboBox", this.getEncoding0());
        this.setByteList(9, "instance-variable", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite0().call(threadContext, rubyObject, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getString0(threadContext.runtime, 32));
        file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite1().call(threadContext, rubyObject, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getString1(threadContext.runtime, 32));
        file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite2().call(threadContext, rubyObject, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getString2(threadContext.runtime, 32));
        file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite3().call(threadContext, rubyObject, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getString3(threadContext.runtime, 32));
        file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite4().call(threadContext, rubyObject, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getString4(threadContext.runtime, 32));
        file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite5().call(threadContext, rubyObject, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getString5(threadContext.runtime, 32));
        file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite6().call(threadContext, rubyObject, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getString6(threadContext.runtime, 32));
        file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite7().call(threadContext, rubyObject, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getString7(threadContext.runtime, 32));
        file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite8().call(threadContext, rubyObject, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getString8(threadContext.runtime, 32));
        return module__0$RUBY$Lister(file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.module__1$RUBY$Questionnaire:(Lruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$Questionnaire(final FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_0        
        //    30: aload_1        
        //    31: ldc_w           "Question"
        //    34: bipush          24
        //    36: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    42: invokestatic    ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.class_2$RUBY$CheckBoxQuestion:(Lruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject class_2$RUBY$CheckBoxQuestion(final FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    29: ldc             "CheckBoxQuestion"
        //    31: swap           
        //    32: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    35: dup            
        //    36: astore_2       
        //    37: aload_1        
        //    38: swap           
        //    39: aload_0        
        //    40: aload_1        
        //    41: ldc             ",0,0,-1"
        //    43: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    46: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClass:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    49: aload_1        
        //    50: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //    53: astore          locals
        //    55: aload_0        
        //    56: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite9:()Lorg/jruby/runtime/CallSite;
        //    59: aload_1        
        //    60: aload_2        
        //    61: aload_0        
        //    62: aload_1        
        //    63: ldc             "Struct"
        //    65: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    68: bipush          6
        //    70: anewarray       Lorg/jruby/runtime/builtin/IRubyObject;
        //    73: dup            
        //    74: iconst_0       
        //    75: aload_0        
        //    76: aload_1        
        //    77: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    80: ldc             "identifier"
        //    82: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    85: aastore        
        //    86: dup            
        //    87: iconst_1       
        //    88: aload_0        
        //    89: aload_1        
        //    90: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    93: ldc             "panel"
        //    95: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    98: aastore        
        //    99: dup            
        //   100: iconst_2       
        //   101: aload_0        
        //   102: aload_1        
        //   103: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   106: ldc             "field"
        //   108: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   111: aastore        
        //   112: dup            
        //   113: iconst_3       
        //   114: aload_0        
        //   115: aload_1        
        //   116: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   119: ldc             "combobox"
        //   121: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   124: aastore        
        //   125: dup            
        //   126: iconst_4       
        //   127: aload_0        
        //   128: aload_1        
        //   129: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   132: ldc             "combo_ids"
        //   134: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getSymbol4:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   137: aastore        
        //   138: dup            
        //   139: iconst_5       
        //   140: aload_0        
        //   141: aload_1        
        //   142: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   145: ldc             "question"
        //   147: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getSymbol5:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   150: aastore        
        //   151: aload_1        
        //   152: aload_2        
        //   153: aload_0        
        //   154: aload_1        
        //   155: ldc_w           "block_0$RUBY$CheckBoxQuestion,-1,,false,0,./lib//lister/runner/questionnaire/checkbox_question.rb,16,false"
        //   158: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getBlockBody2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   161: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   164: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   167: aload_1        
        //   168: ldc_w           "TextBox"
        //   171: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   174: pop            
        //   175: aload_0        
        //   176: bipush          32
        //   178: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   181: aload_1        
        //   182: aload_2        
        //   183: aload_2        
        //   184: aload_0        
        //   185: aload_1        
        //   186: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   189: ldc_w           "checkboxs"
        //   192: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getSymbol7:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   195: aload_1        
        //   196: aload_2        
        //   197: aload_0        
        //   198: aload_1        
        //   199: ldc_w           "block_3$RUBY$CheckBoxQuestion,-1,,false,0,./lib//lister/runner/questionnaire/checkbox_question.rb,34,true"
        //   202: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getBlockBody3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   205: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   208: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   211: pop            
        //   212: aload_0        
        //   213: bipush          41
        //   215: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   218: aload_1        
        //   219: aload_2        
        //   220: aload_2        
        //   221: aload_0        
        //   222: aload_1        
        //   223: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   226: ldc_w           "select_by_default"
        //   229: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getSymbol8:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   232: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   235: pop            
        //   236: aload_1        
        //   237: aload_2        
        //   238: aload_0        
        //   239: ldc_w           "select_by_default?"
        //   242: ldc_w           "method__6$RUBY$select_by_default_p_"
        //   245: ldc             ",0,0,-1"
        //   247: iconst_0       
        //   248: ldc             "./lib//lister/runner/questionnaire/checkbox_question.rb"
        //   250: ldc_w           49
        //   253: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   256: ldc_w           "NONE"
        //   259: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   262: pop            
        //   263: aload_1        
        //   264: aload_2        
        //   265: aload_0        
        //   266: ldc_w           "prepare"
        //   269: ldc_w           "method__7$RUBY$prepare"
        //   272: ldc             ",0,0,-1"
        //   274: iconst_0       
        //   275: ldc             "./lib//lister/runner/questionnaire/checkbox_question.rb"
        //   277: ldc_w           53
        //   280: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   283: ldc_w           "NONE"
        //   286: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   289: pop            
        //   290: aload_1        
        //   291: aload_2        
        //   292: aload_0        
        //   293: ldc_w           "place"
        //   296: ldc_w           "method__8$RUBY$place"
        //   299: ldc             ",0,0,-1"
        //   301: iconst_0       
        //   302: ldc             "./lib//lister/runner/questionnaire/checkbox_question.rb"
        //   304: ldc_w           58
        //   307: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   310: ldc_w           "NONE"
        //   313: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   316: pop            
        //   317: aload_1        
        //   318: aload_2        
        //   319: aload_0        
        //   320: ldc_w           "boxes"
        //   323: ldc_w           "method__9$RUBY$boxes"
        //   326: ldc             ",0,0,-1"
        //   328: iconst_0       
        //   329: ldc             "./lib//lister/runner/questionnaire/checkbox_question.rb"
        //   331: ldc_w           63
        //   334: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   337: ldc_w           "NONE"
        //   340: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   343: pop            
        //   344: aload_1        
        //   345: aload_2        
        //   346: aload_0        
        //   347: ldc_w           "update_checked_result_for_id"
        //   350: ldc_w           "method__10$RUBY$update_checked_result_for_id"
        //   353: ldc_w           "id;val,2,0,-1"
        //   356: iconst_2       
        //   357: ldc             "./lib//lister/runner/questionnaire/checkbox_question.rb"
        //   359: ldc_w           67
        //   362: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   365: ldc_w           "qid;qval"
        //   368: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   371: pop            
        //   372: aload_1        
        //   373: aload_2        
        //   374: aload_0        
        //   375: ldc_w           "update_user_input_result_for_id"
        //   378: ldc_w           "method__11$RUBY$update_user_input_result_for_id"
        //   381: ldc_w           "id;val,2,0,-1"
        //   384: iconst_2       
        //   385: ldc             "./lib//lister/runner/questionnaire/checkbox_question.rb"
        //   387: ldc_w           72
        //   390: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   393: ldc_w           "qid;qval"
        //   396: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   399: pop            
        //   400: aload_1        
        //   401: aload_2        
        //   402: aload_0        
        //   403: ldc_w           "update_user_choice_result_for_id"
        //   406: ldc_w           "method__12$RUBY$update_user_choice_result_for_id"
        //   409: ldc_w           "id;val,2,0,-1"
        //   412: iconst_2       
        //   413: ldc             "./lib//lister/runner/questionnaire/checkbox_question.rb"
        //   415: ldc_w           77
        //   418: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   421: ldc_w           "qid;qval"
        //   424: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   427: pop            
        //   428: aload_1        
        //   429: aload_2        
        //   430: aload_0        
        //   431: ldc_w           "prepare_checkboxs"
        //   434: ldc_w           "method__13$RUBY$prepare_checkboxs"
        //   437: ldc             ",0,0,-1"
        //   439: iconst_0       
        //   440: ldc             "./lib//lister/runner/questionnaire/checkbox_question.rb"
        //   442: ldc_w           82
        //   445: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   448: ldc_w           "NONE"
        //   451: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   454: pop            
        //   455: aload_1        
        //   456: aload_2        
        //   457: aload_0        
        //   458: ldc_w           "add_separator"
        //   461: ldc_w           "method__14$RUBY$add_separator"
        //   464: ldc             ",0,0,-1"
        //   466: iconst_0       
        //   467: ldc             "./lib//lister/runner/questionnaire/checkbox_question.rb"
        //   469: ldc_w           100
        //   472: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   475: ldc_w           "NONE"
        //   478: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   481: pop            
        //   482: aload_0        
        //   483: bipush          76
        //   485: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   488: aload_1        
        //   489: aload_2        
        //   490: aload_2        
        //   491: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   494: pop            
        //   495: aload_1        
        //   496: aload_2        
        //   497: aload_0        
        //   498: ldc_w           "add_simple_checkbox_for_outcome"
        //   501: ldc_w           "method__15$RUBY$add_simple_checkbox_for_outcome"
        //   504: ldc_w           "outcome;checkbox,1,0,-1"
        //   507: iconst_1       
        //   508: ldc             "./lib//lister/runner/questionnaire/checkbox_question.rb"
        //   510: ldc_w           106
        //   513: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   516: ldc_w           "qoutcome"
        //   519: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   522: pop            
        //   523: aload_1        
        //   524: aload_2        
        //   525: aload_0        
        //   526: ldc_w           "min_textbox_length"
        //   529: ldc_w           "method__16$RUBY$min_textbox_length"
        //   532: ldc             ",0,0,-1"
        //   534: iconst_0       
        //   535: ldc             "./lib//lister/runner/questionnaire/checkbox_question.rb"
        //   537: ldc_w           120
        //   540: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   543: ldc_w           "NONE"
        //   546: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   549: pop            
        //   550: aload_1        
        //   551: aload_2        
        //   552: aload_0        
        //   553: ldc_w           "textbox_length_for_outcome"
        //   556: ldc_w           "method__17$RUBY$textbox_length_for_outcome"
        //   559: ldc_w           "outcome;txt_len;box_len;len;maxlen,1,0,-1"
        //   562: iconst_1       
        //   563: ldc             "./lib//lister/runner/questionnaire/checkbox_question.rb"
        //   565: ldc_w           124
        //   568: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   571: ldc_w           "qoutcome"
        //   574: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   577: pop            
        //   578: aload_1        
        //   579: aload_2        
        //   580: aload_0        
        //   581: ldc_w           "add_user_input_textbox_with_combo_box_for_outcome"
        //   584: ldc_w           "method__18$RUBY$add_user_input_textbox_with_combo_box_for_outcome"
        //   587: ldc_w           "outcome;text;choices_names;choices_vals;panel;txt;cbx;tb;lbl;widgets,1,0,-1"
        //   590: iconst_1       
        //   591: ldc             "./lib//lister/runner/questionnaire/checkbox_question.rb"
        //   593: ldc_w           132
        //   596: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   599: ldc_w           "qoutcome"
        //   602: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   605: pop            
        //   606: aload_1        
        //   607: aload_2        
        //   608: aload_0        
        //   609: ldc_w           "add_user_input_textbox_for_outcome"
        //   612: ldc_w           "method__19$RUBY$add_user_input_textbox_for_outcome"
        //   615: ldc_w           "outcome;name;panel;field;tb,1,0,-1"
        //   618: iconst_1       
        //   619: ldc             "./lib//lister/runner/questionnaire/checkbox_question.rb"
        //   621: ldc_w           157
        //   624: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   627: ldc_w           "qoutcome"
        //   630: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   633: pop            
        //   634: aload_0        
        //   635: sipush          171
        //   638: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   641: aload_1        
        //   642: aload_2        
        //   643: aload_2        
        //   644: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   647: pop            
        //   648: aload_1        
        //   649: aload_2        
        //   650: aload_0        
        //   651: ldc_w           "place_boxes"
        //   654: ldc_w           "method__20$RUBY$place_boxes"
        //   657: ldc             ",0,0,-1"
        //   659: iconst_0       
        //   660: ldc             "./lib//lister/runner/questionnaire/checkbox_question.rb"
        //   662: ldc_w           175
        //   665: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   668: ldc_w           "NONE"
        //   671: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   674: aload_1        
        //   675: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   678: goto            686
        //   681: aload_1        
        //   682: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   685: athrow         
        //   686: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  55     632     4     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  55     674    681    686    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_0$RUBY$CheckBoxQuestion(final FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB, final ThreadContext context, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        context.getCurrentScope();
        RuntimeHelpers.processBlockArgument(context.runtime, block);
        file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(10).call(context, rubyObject, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getConstant1(context, "HasListeners"));
        file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(11).callIter(context, rubyObject, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getSymbol6(context.runtime, "textfield"), RuntimeHelpers.createBlock(context, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getBlockBody0(context, "block_1$RUBY$CheckBoxQuestion,-1,,false,0,./lib//lister/runner/questionnaire/checkbox_question.rb,18,true")));
        return file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(20).callIter(context, rubyObject, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getSymbol3(context.runtime, "combobox"), RuntimeHelpers.createBlock(context, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getBlockBody1(context, "block_2$RUBY$CheckBoxQuestion,-1,,false,0,./lib//lister/runner/questionnaire/checkbox_question.rb,25,true")));
    }
    
    public static IRubyObject block_1$RUBY$CheckBoxQuestion(final FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB scriptObject, final ThreadContext context, final IRubyObject self, final IRubyObject rubyObject, final Block block) {
        context.getCurrentScope();
        RuntimeHelpers.processBlockArgument(context.runtime, block);
        return RuntimeHelpers.def(context, self, scriptObject, "caret_update", "method__3$RUBY$caret_update", "event,1,0,-1", 1, "./lib//lister/runner/questionnaire/checkbox_question.rb", 19, CallConfiguration.FrameNoneScopeNone, "qevent");
    }
    
    @JRubyMethod(name = "caret_update", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__3$RUBY$caret_update(final FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(12).call(threadContext, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(13).call(threadContext, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(14).call(threadContext, rubyObject, rubyObject)), file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(15).call(threadContext, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(16).call(threadContext, rubyObject, rubyObject)), file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(17).call(threadContext, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(18).call(threadContext, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(19).call(threadContext, rubyObject, rubyObject))));
    }
    
    public static IRubyObject block_2$RUBY$CheckBoxQuestion(final FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB scriptObject, final ThreadContext context, final IRubyObject self, final IRubyObject rubyObject, final Block block) {
        context.getCurrentScope();
        RuntimeHelpers.processBlockArgument(context.runtime, block);
        return RuntimeHelpers.def(context, self, scriptObject, "actionPerformed", "method__4$RUBY$actionPerformed", "event;val,1,0,-1", 1, "./lib//lister/runner/questionnaire/checkbox_question.rb", 26, CallConfiguration.FrameNoneScopeNone, "qevent");
    }
    
    @JRubyMethod(name = "actionPerformed", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$actionPerformed(final FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        final IRubyObject nil = threadContext.nil;
        final IRubyObject val = file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(21).call(threadContext, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(22).call(threadContext, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(23).call(threadContext, rubyObject, rubyObject)), file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(24).call(threadContext, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(25).call(threadContext, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(26).call(threadContext, rubyObject, rubyObject))));
        return file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(27).call(threadContext, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(28).call(threadContext, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(29).call(threadContext, rubyObject, rubyObject)), file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(30).call(threadContext, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(31).call(threadContext, rubyObject, rubyObject)), val);
    }
    
    public static IRubyObject block_3$RUBY$CheckBoxQuestion(final FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB scriptObject, final ThreadContext context, final IRubyObject self, final IRubyObject rubyObject, final Block block) {
        context.getCurrentScope();
        RuntimeHelpers.processBlockArgument(context.runtime, block);
        return RuntimeHelpers.def(context, self, scriptObject, "item_state_changed", "method__5$RUBY$item_state_changed", "event;obj;id;val,1,0,-1", 1, "./lib//lister/runner/questionnaire/checkbox_question.rb", 35, CallConfiguration.FrameNoneScopeDummy, "qevent");
    }
    
    @JRubyMethod(name = "item_state_changed", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__5$RUBY$item_state_changed(final FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        final IRubyObject nil = threadContext.nil;
        final IRubyObject obj = file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(33).call(threadContext, rubyObject, rubyObject2);
        final IRubyObject id = file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(34).call(threadContext, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(35).call(threadContext, rubyObject, rubyObject), file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(36).call(threadContext, rubyObject, obj));
        IRubyObject rubyObject3;
        if (file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(37).call(threadContext, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(38).call(threadContext, rubyObject, rubyObject2), file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getConstantFrom3(RuntimeHelpers.checkIsModule(file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getConstant2(threadContext, "ItemEvent")), threadContext, "DESELECTED")).isTrue()) {
            rubyObject3 = threadContext.runtime.getFalse();
            threadContext.pollThreadEvents();
        }
        else {
            rubyObject3 = threadContext.runtime.getTrue();
            threadContext.pollThreadEvents();
        }
        final IRubyObject val = rubyObject3;
        return file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(39).call(threadContext, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(40).call(threadContext, rubyObject, rubyObject), id, val);
    }
    
    @JRubyMethod(name = "select_by_default?", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__6$RUBY$select_by_default_p_(final FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        IRubyObject rubyObject2;
        if ((rubyObject2 = file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(42).call(threadContext, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(43).call(threadContext, rubyObject, rubyObject), file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getSymbol9(threadContext.runtime, "default"))).isTrue()) {
            rubyObject2 = threadContext.runtime.getTrue();
            threadContext.pollThreadEvents();
        }
        return rubyObject2;
    }
    
    @JRubyMethod(name = "prepare", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__7$RUBY$prepare(final FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(44).call(context, rubyObject, rubyObject, RuntimeHelpers.getArgValues(context), block);
        return file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(45).call(context, rubyObject, rubyObject);
    }
    
    @JRubyMethod(name = "place", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__8$RUBY$place(final FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(46).call(context, rubyObject, rubyObject);
        return file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(47).call(context, rubyObject, rubyObject, RuntimeHelpers.getArgValues(context), block);
    }
    
    @JRubyMethod(name = "boxes", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__9$RUBY$boxes(final FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@boxes") ? file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getByteList9() : null) == null) {
            rubyObject = file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.setVariable0(threadContext.runtime, "@boxes", object, threadContext.runtime.newArray());
        }
        else if (!(rubyObject = file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getVariable0(threadContext.runtime, "@boxes", object)).isTrue()) {
            rubyObject = file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.setVariable1(threadContext.runtime, "@boxes", object, threadContext.runtime.newArray());
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "update_checked_result_for_id", frame = true, required = 2, optional = 0, rest = -1)
    public static IRubyObject method__10$RUBY$update_checked_result_for_id(final FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final IRubyObject p4, final Block p5) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_3        
        //     1: astore          10
        //     3: aload           4
        //     5: astore          val
        //     7: aload_0        
        //     8: bipush          48
        //    10: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    13: aload_1        
        //    14: aload_2        
        //    15: aload_2        
        //    16: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    19: dup            
        //    20: aload_2        
        //    21: aload_0        
        //    22: bipush          49
        //    24: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    27: aload_0        
        //    28: bipush          50
        //    30: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    33: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //    36: aload           id
        //    38: aload           val
        //    40: aload_1        
        //    41: aload_2        
        //    42: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: pop            
        //    46: aload_0        
        //    47: bipush          51
        //    49: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    52: aload_1        
        //    53: aload_2        
        //    54: aload_2        
        //    55: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    58: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  7      52      10    id    Lorg/jruby/runtime/builtin/IRubyObject;
        //  7      52      11    val   Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "update_user_input_result_for_id", frame = true, required = 2, optional = 0, rest = -1)
    public static IRubyObject method__11$RUBY$update_user_input_result_for_id(final FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final IRubyObject p4, final Block p5) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          6
        //     6: aload_3        
        //     7: aload           6
        //     9: swap           
        //    10: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    13: pop            
        //    14: aload           4
        //    16: aload           6
        //    18: swap           
        //    19: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    22: pop            
        //    23: aload_0        
        //    24: bipush          52
        //    26: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    29: aload_1        
        //    30: aload_2        
        //    31: aload_0        
        //    32: bipush          53
        //    34: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    37: aload_1        
        //    38: aload_2        
        //    39: aload_2        
        //    40: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    43: aload           locals
        //    45: aload_1        
        //    46: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    52: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    55: dup            
        //    56: aload_2        
        //    57: aload_0        
        //    58: bipush          54
        //    60: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    63: aload_0        
        //    64: bipush          55
        //    66: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    69: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //    72: aload_1        
        //    73: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    76: invokestatic    org/jruby/RubyFixnum.zero:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //    79: aload           locals
        //    81: aload_1        
        //    82: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    85: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    88: aload_1        
        //    89: aload_2        
        //    90: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    93: pop            
        //    94: aload_0        
        //    95: bipush          56
        //    97: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   100: aload_1        
        //   101: aload_2        
        //   102: aload_2        
        //   103: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   106: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  23     84      6     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "update_user_choice_result_for_id", frame = true, required = 2, optional = 0, rest = -1)
    public static IRubyObject method__12$RUBY$update_user_choice_result_for_id(final FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final IRubyObject p4, final Block p5) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          6
        //     6: aload_3        
        //     7: aload           6
        //     9: swap           
        //    10: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    13: pop            
        //    14: aload           4
        //    16: aload           6
        //    18: swap           
        //    19: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    22: pop            
        //    23: aload_0        
        //    24: bipush          57
        //    26: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    29: aload_1        
        //    30: aload_2        
        //    31: aload_0        
        //    32: bipush          58
        //    34: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    37: aload_1        
        //    38: aload_2        
        //    39: aload_2        
        //    40: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    43: aload           locals
        //    45: aload_1        
        //    46: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    52: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    55: dup            
        //    56: aload_2        
        //    57: aload_0        
        //    58: bipush          59
        //    60: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    63: aload_0        
        //    64: bipush          60
        //    66: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    69: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //    72: aload_1        
        //    73: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    76: invokestatic    org/jruby/RubyFixnum.one:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //    79: aload           locals
        //    81: aload_1        
        //    82: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    85: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    88: aload_1        
        //    89: aload_2        
        //    90: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    93: pop            
        //    94: aload_0        
        //    95: bipush          61
        //    97: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   100: aload_1        
        //   101: aload_2        
        //   102: aload_2        
        //   103: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   106: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  23     84      6     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "prepare_checkboxs", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__13$RUBY$prepare_checkboxs(final FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(62).callIter(threadContext, self, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(63).call(threadContext, self, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(64).call(threadContext, self, self)), RuntimeHelpers.createBlock(threadContext, self, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getBlockBody4(threadContext, "block_4$RUBY$prepare_checkboxs,1,outcome,false,2,./lib//lister/runner/questionnaire/checkbox_question.rb,83,true")));
    }
    
    public static IRubyObject block_4$RUBY$prepare_checkboxs(final FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          65
        //    28: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload           outcome
        //    35: aload_0        
        //    36: aload_1        
        //    37: ldc_w           "QuestionsList"
        //    40: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getConstant4:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    43: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    46: aload_0        
        //    47: swap           
        //    48: aload_1        
        //    49: ldc_w           "QuestionOutcome"
        //    52: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getConstantFrom5:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    55: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    58: aload_0        
        //    59: swap           
        //    60: aload_1        
        //    61: ldc_w           "Separator"
        //    64: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getConstantFrom6:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    67: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    70: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    75: ifeq            93
        //    78: aload_0        
        //    79: bipush          66
        //    81: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    84: aload_1        
        //    85: aload_2        
        //    86: aload_2        
        //    87: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    90: goto            194
        //    93: aload_0        
        //    94: bipush          67
        //    96: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    99: aload_1        
        //   100: aload_2        
        //   101: aload           outcome
        //   103: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   106: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   111: ifeq            180
        //   114: aload_0        
        //   115: bipush          68
        //   117: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   120: aload_1        
        //   121: aload_2        
        //   122: aload_0        
        //   123: bipush          69
        //   125: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   128: aload_1        
        //   129: aload_2        
        //   130: aload           outcome
        //   132: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   135: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   138: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   143: ifeq            163
        //   146: aload_0        
        //   147: bipush          70
        //   149: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   152: aload_1        
        //   153: aload_2        
        //   154: aload_2        
        //   155: aload           outcome
        //   157: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   160: goto            177
        //   163: aload_0        
        //   164: bipush          71
        //   166: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   169: aload_1        
        //   170: aload_2        
        //   171: aload_2        
        //   172: aload           outcome
        //   174: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   177: goto            194
        //   180: aload_0        
        //   181: bipush          72
        //   183: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   186: aload_1        
        //   187: aload_2        
        //   188: aload_2        
        //   189: aload           outcome
        //   191: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   194: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name     Signature
        //  -----  ------  ----  -------  ---------------------------------------
        //  25     170     9     outcome  Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "add_separator", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__14$RUBY$add_separator(final FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        return file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(73).call(context, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(74).call(context, rubyObject, rubyObject), file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(75).call(context, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getConstant7(context, "JSeparator")));
    }
    
    @JRubyMethod(name = "add_simple_checkbox_for_outcome", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__15$RUBY$add_simple_checkbox_for_outcome(final FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB, final ThreadContext context, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        final IRubyObject nil = context.nil;
        final IRubyObject checkbox = file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(77).call(context, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getConstant8(context, "JCheckBox"), file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(78).call(context, rubyObject, rubyObject2, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(79).call(context, rubyObject, rubyObject)));
        if (file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(80).call(context, rubyObject, rubyObject).isTrue()) {
            final IRubyObject rubyObject3 = checkbox;
            final CallSite selectAttrAsgnCallSite = RuntimeHelpers.selectAttrAsgnCallSite(rubyObject3, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(81), file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(82));
            final RubyBoolean true = context.runtime.getTrue();
            context.pollThreadEvents();
            RuntimeHelpers.doAttrAsgn(rubyObject3, selectAttrAsgnCallSite, true, context, rubyObject);
            final IRubyObject call = file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(83).call(context, rubyObject, rubyObject);
            final CallSite selectAttrAsgnCallSite2 = RuntimeHelpers.selectAttrAsgnCallSite(call, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(84), file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(85));
            final IRubyObject call2 = file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(86).call(context, rubyObject, rubyObject2);
            final RubyBoolean true2 = context.runtime.getTrue();
            context.pollThreadEvents();
            RuntimeHelpers.doAttrAsgn(call, selectAttrAsgnCallSite2, call2, true2, context, rubyObject);
        }
        else {
            final IRubyObject rubyObject4 = checkbox;
            final CallSite selectAttrAsgnCallSite3 = RuntimeHelpers.selectAttrAsgnCallSite(rubyObject4, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(87), file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(88));
            final RubyBoolean false = context.runtime.getFalse();
            context.pollThreadEvents();
            RuntimeHelpers.doAttrAsgn(rubyObject4, selectAttrAsgnCallSite3, false, context, rubyObject);
            final IRubyObject call3 = file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(89).call(context, rubyObject, rubyObject);
            final CallSite selectAttrAsgnCallSite4 = RuntimeHelpers.selectAttrAsgnCallSite(call3, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(90), file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(91));
            final IRubyObject call4 = file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(92).call(context, rubyObject, rubyObject2);
            final RubyBoolean false2 = context.runtime.getFalse();
            context.pollThreadEvents();
            RuntimeHelpers.doAttrAsgn(call3, selectAttrAsgnCallSite4, call4, false2, context, rubyObject);
        }
        file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(93).call(context, rubyObject, checkbox, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(94).call(context, rubyObject, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getSymbol7(context.runtime, "checkboxs")));
        return file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(95).call(context, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(96).call(context, rubyObject, rubyObject), checkbox);
    }
    
    @JRubyMethod(name = "min_textbox_length", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__16$RUBY$min_textbox_length(final FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getFixnum0(threadContext.runtime, 15);
    }
    
    @JRubyMethod(name = "textbox_length_for_outcome", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__17$RUBY$textbox_length_for_outcome(final FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          5
        //     6: aload           5
        //     8: invokevirtual   org/jruby/runtime/DynamicScope.getValues:()[Lorg/jruby/runtime/builtin/IRubyObject;
        //    11: astore          6
        //    13: aload           6
        //    15: aload_1        
        //    16: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    19: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.fillNil:([Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;)V
        //    22: aload_3        
        //    23: aload           5
        //    25: swap           
        //    26: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    29: pop            
        //    30: aload           locals
        //    32: aload_0        
        //    33: bipush          97
        //    35: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    38: aload_1        
        //    39: aload_2        
        //    40: aload_0        
        //    41: bipush          98
        //    43: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    46: aload_1        
        //    47: aload_2        
        //    48: aload           locals
        //    50: aload_1        
        //    51: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    54: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: aload_0        
        //    58: bipush          99
        //    60: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    63: aload_1        
        //    64: aload_2        
        //    65: aload_2        
        //    66: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    69: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    72: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    75: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    78: pop            
        //    79: aload           locals
        //    81: aload_0        
        //    82: bipush          100
        //    84: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    87: aload_1        
        //    88: aload_2        
        //    89: aload_0        
        //    90: bipush          101
        //    92: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    95: aload_1        
        //    96: aload_2        
        //    97: aload_0        
        //    98: bipush          102
        //   100: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   103: aload_1        
        //   104: aload_2        
        //   105: aload           locals
        //   107: aload_1        
        //   108: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   111: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   114: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   117: aload_1        
        //   118: aload_2        
        //   119: aload_0        
        //   120: aload_1        
        //   121: ldc_w           "block_5$RUBY$textbox_length_for_outcome,1,o,false,2,./lib//lister/runner/questionnaire/checkbox_question.rb,126,true"
        //   124: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getBlockBody5:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   127: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   130: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   133: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   136: dup            
        //   137: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   142: ifne            153
        //   145: pop            
        //   146: aload_1        
        //   147: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   150: invokestatic    org/jruby/RubyFixnum.zero:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   153: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   156: pop            
        //   157: aload           locals
        //   159: aload_0        
        //   160: bipush          106
        //   162: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   165: aload_1        
        //   166: aload_2        
        //   167: aload_0        
        //   168: bipush          107
        //   170: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   173: aload_1        
        //   174: aload_2        
        //   175: aload_0        
        //   176: aload_1        
        //   177: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   180: bipush          50
        //   182: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getFixnum1:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   185: aload           locals
        //   187: aload_1        
        //   188: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   191: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   194: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   197: aload           locals
        //   199: aload_1        
        //   200: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   203: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   206: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   209: invokevirtual   org/jruby/runtime/DynamicScope.setValueThreeDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   212: pop            
        //   213: aload           6
        //   215: iconst_4       
        //   216: aload_0        
        //   217: bipush          108
        //   219: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   222: aload_1        
        //   223: aload_2        
        //   224: aload_1        
        //   225: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   228: aload_0        
        //   229: bipush          109
        //   231: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   234: aload_1        
        //   235: aload_2        
        //   236: aload_2        
        //   237: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   240: aload           locals
        //   242: aload_1        
        //   243: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   246: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   249: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   252: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   255: aastore        
        //   256: aload_0        
        //   257: bipush          110
        //   259: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   262: aload_1        
        //   263: aload_2        
        //   264: aload_0        
        //   265: bipush          111
        //   267: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   270: aload_1        
        //   271: aload_2        
        //   272: aload_1        
        //   273: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   276: aload           6
        //   278: iconst_4       
        //   279: aaload         
        //   280: aload_0        
        //   281: bipush          112
        //   283: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   286: aload_1        
        //   287: aload_2        
        //   288: aload           locals
        //   290: aload_1        
        //   291: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   294: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   297: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   300: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   303: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   306: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   309: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  30     280     5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_5$RUBY$textbox_length_for_outcome(final FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          103
        //    28: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_0        
        //    34: bipush          104
        //    36: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    39: aload_1        
        //    40: aload_2        
        //    41: aload           o
        //    43: aload_0        
        //    44: bipush          105
        //    46: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    49: aload_1        
        //    50: aload_2        
        //    51: aload_2        
        //    52: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    55: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    58: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    61: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     37      9     o     Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "add_user_input_textbox_with_combo_box_for_outcome", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__18$RUBY$add_user_input_textbox_with_combo_box_for_outcome(final FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          5
        //     6: aload           5
        //     8: invokevirtual   org/jruby/runtime/DynamicScope.getValues:()[Lorg/jruby/runtime/builtin/IRubyObject;
        //    11: astore          6
        //    13: aload           6
        //    15: aload_1        
        //    16: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    19: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.fillNil:([Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;)V
        //    22: aload_3        
        //    23: aload           5
        //    25: swap           
        //    26: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    29: pop            
        //    30: aload           locals
        //    32: aload_0        
        //    33: bipush          113
        //    35: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    38: aload_1        
        //    39: aload_2        
        //    40: aload           locals
        //    42: aload_1        
        //    43: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    46: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: aload_0        
        //    50: bipush          114
        //    52: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    55: aload_1        
        //    56: aload_2        
        //    57: aload_2        
        //    58: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    61: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    64: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    67: pop            
        //    68: aload           locals
        //    70: aload_0        
        //    71: bipush          115
        //    73: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    76: aload_1        
        //    77: aload_2        
        //    78: aload_0        
        //    79: bipush          116
        //    81: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    84: aload_1        
        //    85: aload_2        
        //    86: aload           locals
        //    88: aload_1        
        //    89: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    92: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    95: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    98: aload_1        
        //    99: aload_2        
        //   100: aload_0        
        //   101: aload_1        
        //   102: ldc_w           "block_6$RUBY$add_user_input_textbox_with_combo_box_for_outcome,1,o,false,2,./lib//lister/runner/questionnaire/checkbox_question.rb,134,true"
        //   105: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getBlockBody6:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   108: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   111: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   114: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   117: pop            
        //   118: aload           locals
        //   120: aload_0        
        //   121: bipush          119
        //   123: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   126: aload_1        
        //   127: aload_2        
        //   128: aload_0        
        //   129: bipush          120
        //   131: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   134: aload_1        
        //   135: aload_2        
        //   136: aload           locals
        //   138: aload_1        
        //   139: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   142: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   145: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   148: aload_1        
        //   149: aload_2        
        //   150: aload_0        
        //   151: aload_1        
        //   152: ldc_w           "block_7$RUBY$add_user_input_textbox_with_combo_box_for_outcome,1,o,false,2,./lib//lister/runner/questionnaire/checkbox_question.rb,135,true"
        //   155: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getBlockBody7:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   158: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   161: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   164: invokevirtual   org/jruby/runtime/DynamicScope.setValueThreeDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   167: pop            
        //   168: aload           6
        //   170: iconst_4       
        //   171: aload_0        
        //   172: bipush          122
        //   174: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   177: aload_1        
        //   178: aload_2        
        //   179: aload_0        
        //   180: aload_1        
        //   181: ldc_w           "JPanel"
        //   184: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getConstant9:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   187: aload_0        
        //   188: bipush          123
        //   190: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   193: aload_1        
        //   194: aload_2        
        //   195: aload_0        
        //   196: aload_1        
        //   197: ldc_w           "FlowLayout"
        //   200: bipush          10
        //   202: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   205: aload_0        
        //   206: aload_1        
        //   207: ldc_w           "FlowLayout"
        //   210: bipush          11
        //   212: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   215: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   218: aload_0        
        //   219: swap           
        //   220: aload_1        
        //   221: ldc_w           "LEFT"
        //   224: bipush          12
        //   226: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   229: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   232: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   235: aastore        
        //   236: aload           6
        //   238: iconst_5       
        //   239: aload_0        
        //   240: bipush          124
        //   242: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   245: aload_1        
        //   246: aload_2        
        //   247: aload_0        
        //   248: aload_1        
        //   249: ldc_w           "JTextField"
        //   252: bipush          13
        //   254: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   257: aload_0        
        //   258: bipush          125
        //   260: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   263: aload_1        
        //   264: aload_2        
        //   265: aload_2        
        //   266: aload           locals
        //   268: aload_1        
        //   269: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   272: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   275: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   278: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   281: aastore        
        //   282: aload           6
        //   284: bipush          6
        //   286: aload_0        
        //   287: bipush          126
        //   289: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   292: aload_1        
        //   293: aload_2        
        //   294: aload_0        
        //   295: aload_1        
        //   296: ldc_w           "JComboBox"
        //   299: bipush          14
        //   301: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   304: aload_0        
        //   305: bipush          127
        //   307: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   310: aload_1        
        //   311: aload_2        
        //   312: aload           locals
        //   314: aload_1        
        //   315: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   318: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   321: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   324: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   327: aastore        
        //   328: aload           6
        //   330: bipush          7
        //   332: aload_0        
        //   333: sipush          128
        //   336: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   339: aload_1        
        //   340: aload_2        
        //   341: aload_0        
        //   342: aload_1        
        //   343: ldc_w           "TextBox"
        //   346: bipush          15
        //   348: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   351: bipush          6
        //   353: anewarray       Lorg/jruby/runtime/builtin/IRubyObject;
        //   356: dup            
        //   357: iconst_0       
        //   358: aload_0        
        //   359: sipush          129
        //   362: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   365: aload_1        
        //   366: aload_2        
        //   367: aload           locals
        //   369: aload_1        
        //   370: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   373: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   376: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   379: aastore        
        //   380: dup            
        //   381: iconst_1       
        //   382: aload           6
        //   384: iconst_4       
        //   385: aaload         
        //   386: aastore        
        //   387: dup            
        //   388: iconst_2       
        //   389: aload           6
        //   391: iconst_5       
        //   392: aaload         
        //   393: aastore        
        //   394: dup            
        //   395: iconst_3       
        //   396: aload           6
        //   398: bipush          6
        //   400: aaload         
        //   401: aastore        
        //   402: dup            
        //   403: iconst_4       
        //   404: aload           locals
        //   406: aload_1        
        //   407: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   410: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   413: aastore        
        //   414: dup            
        //   415: iconst_5       
        //   416: aload_2        
        //   417: aastore        
        //   418: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   421: aastore        
        //   422: aload           6
        //   424: bipush          8
        //   426: aload_0        
        //   427: sipush          130
        //   430: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   433: aload_1        
        //   434: aload_2        
        //   435: aload_0        
        //   436: aload_1        
        //   437: ldc_w           "JLabel"
        //   440: bipush          16
        //   442: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   445: aload_1        
        //   446: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   449: ldc_w           20
        //   452: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   455: aload           locals
        //   457: aload_1        
        //   458: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   461: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   464: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   469: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   472: aload_0        
        //   473: aload_1        
        //   474: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   477: bipush          10
        //   479: bipush          32
        //   481: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   484: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   487: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   490: aastore        
        //   491: aload_0        
        //   492: sipush          131
        //   495: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   498: aload_1        
        //   499: aload_2        
        //   500: aload_2        
        //   501: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   504: dup            
        //   505: aload_2        
        //   506: aload_0        
        //   507: sipush          132
        //   510: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   513: aload_0        
        //   514: sipush          133
        //   517: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   520: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   523: aload_0        
        //   524: sipush          134
        //   527: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   530: aload_1        
        //   531: aload_2        
        //   532: aload           locals
        //   534: aload_1        
        //   535: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   538: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   541: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   544: aload_1        
        //   545: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   548: aload_0        
        //   549: aload_1        
        //   550: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   553: bipush          11
        //   555: bipush          32
        //   557: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   560: aload_0        
        //   561: sipush          135
        //   564: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   567: aload_1        
        //   568: aload_2        
        //   569: aload_0        
        //   570: sipush          136
        //   573: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   576: aload_1        
        //   577: aload_2        
        //   578: aload_0        
        //   579: sipush          137
        //   582: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   585: aload_1        
        //   586: aload_2        
        //   587: aload           locals
        //   589: aload_1        
        //   590: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   593: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   596: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   599: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   602: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   605: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   608: aload_1        
        //   609: aload_2        
        //   610: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   613: pop            
        //   614: aload           6
        //   616: bipush          9
        //   618: aload_1        
        //   619: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   622: aload_0        
        //   623: aload_1        
        //   624: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   627: ldc_w           10
        //   630: ldc_w           "lbl"
        //   633: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   636: aload           6
        //   638: bipush          8
        //   640: aaload         
        //   641: aload_0        
        //   642: aload_1        
        //   643: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   646: ldc_w           11
        //   649: ldc_w           "txt"
        //   652: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   655: aload           6
        //   657: iconst_5       
        //   658: aaload         
        //   659: aload_0        
        //   660: aload_1        
        //   661: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   664: ldc_w           12
        //   667: ldc_w           "cbx"
        //   670: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   673: aload           6
        //   675: bipush          6
        //   677: aaload         
        //   678: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructHash:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyHash;
        //   681: aastore        
        //   682: aload_0        
        //   683: sipush          138
        //   686: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   689: aload_1        
        //   690: aload_2        
        //   691: aload_0        
        //   692: sipush          139
        //   695: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   698: aload_1        
        //   699: aload_2        
        //   700: aload           locals
        //   702: aload_1        
        //   703: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   706: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   709: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   712: aload_1        
        //   713: aload_2        
        //   714: aload_0        
        //   715: aload_1        
        //   716: ldc_w           "block_8$RUBY$add_user_input_textbox_with_combo_box_for_outcome,1,sym,false,2,./lib//lister/runner/questionnaire/checkbox_question.rb,147,false"
        //   719: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getBlockBody8:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   722: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   725: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   728: pop            
        //   729: aload_0        
        //   730: sipush          142
        //   733: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   736: aload_1        
        //   737: aload_2        
        //   738: aload           6
        //   740: bipush          6
        //   742: aaload         
        //   743: aload_0        
        //   744: sipush          143
        //   747: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   750: aload_1        
        //   751: aload_2        
        //   752: aload           6
        //   754: bipush          7
        //   756: aaload         
        //   757: aload_0        
        //   758: aload_1        
        //   759: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   762: ldc             "combobox"
        //   764: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   767: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   770: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   773: pop            
        //   774: aload_0        
        //   775: sipush          144
        //   778: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   781: aload_1        
        //   782: aload_2        
        //   783: aload           6
        //   785: iconst_5       
        //   786: aaload         
        //   787: aload_0        
        //   788: sipush          145
        //   791: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   794: aload_1        
        //   795: aload_2        
        //   796: aload           6
        //   798: bipush          7
        //   800: aaload         
        //   801: aload_0        
        //   802: aload_1        
        //   803: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   806: ldc             "textfield"
        //   808: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getSymbol6:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   811: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   814: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   817: pop            
        //   818: aload_0        
        //   819: sipush          146
        //   822: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   825: aload_1        
        //   826: aload_2        
        //   827: aload_0        
        //   828: sipush          147
        //   831: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   834: aload_1        
        //   835: aload_2        
        //   836: aload_2        
        //   837: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   840: aload           6
        //   842: bipush          7
        //   844: aaload         
        //   845: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   848: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  30     819     5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_6$RUBY$add_user_input_textbox_with_combo_box_for_outcome(final FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          117
        //    28: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload           o
        //    35: aload_0        
        //    36: bipush          118
        //    38: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload_2        
        //    44: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     26      9     o     Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_7$RUBY$add_user_input_textbox_with_combo_box_for_outcome(final FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          121
        //    28: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload           o
        //    35: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    38: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     14      9     o     Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_8$RUBY$add_user_input_textbox_with_combo_box_for_outcome(final FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    36: sipush          140
        //    39: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    42: aload_1        
        //    43: aload_2        
        //    44: aload           locals
        //    46: iconst_4       
        //    47: iconst_1       
        //    48: aload_1        
        //    49: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    52: invokevirtual   org/jruby/runtime/DynamicScope.getValueOrNil:(IILorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    55: aload_0        
        //    56: sipush          141
        //    59: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    62: aload_1        
        //    63: aload_2        
        //    64: aload           locals
        //    66: bipush          9
        //    68: iconst_1       
        //    69: aload_1        
        //    70: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    73: invokevirtual   org/jruby/runtime/DynamicScope.getValueOrNil:(IILorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    76: aload           locals
        //    78: aload_1        
        //    79: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    82: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    85: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    88: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    91: areturn        
        //    92: pop            
        //    93: goto            35
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     57      5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  35     92     92     96     Lorg/jruby/exceptions/JumpException$RedoJump;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "add_user_input_textbox_for_outcome", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__19$RUBY$add_user_input_textbox_for_outcome(final FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB, final ThreadContext context, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        final IRubyObject nil = context.nil;
        final IRubyObject name = file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(148).call(context, rubyObject, rubyObject2, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(149).call(context, rubyObject, rubyObject));
        final IRubyObject panel = file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(150).call(context, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getConstant(context, "JPanel", 17), file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(151).call(context, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getConstant(context, "FlowLayout", 18), file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getConstantFrom(RuntimeHelpers.checkIsModule(file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getConstant(context, "FlowLayout", 19)), context, "LEFT", 20)));
        final IRubyObject field = file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(152).call(context, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getConstant(context, "JTextField", 21), file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(153).call(context, rubyObject, rubyObject, rubyObject2));
        if (file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(154).call(context, rubyObject, rubyObject2).isTrue()) {
            final IRubyObject rubyObject3 = field;
            RuntimeHelpers.doAttrAsgn(rubyObject3, RuntimeHelpers.selectAttrAsgnCallSite(rubyObject3, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(155), file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(156)), file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(157).call(context, rubyObject, rubyObject2), context, rubyObject);
        }
        final IRubyObject tb = file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(158).call(context, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getConstant(context, "TextBox", 22), file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(159).call(context, rubyObject, rubyObject2), panel, field, context.nil, context.runtime.newArray(), rubyObject);
        final IRubyObject call = file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(160).call(context, rubyObject, rubyObject);
        RuntimeHelpers.doAttrAsgn(call, RuntimeHelpers.selectAttrAsgnCallSite(call, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(161), file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(162)), file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(163).call(context, rubyObject, rubyObject2), RuntimeHelpers.constructRubyArray(context.runtime, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getString(context.runtime, 11, 32), context.nil), context, rubyObject);
        file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(164).call(context, rubyObject, panel, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(165).call(context, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getConstant(context, "JLabel", 23), RubyString.newStringLight(context.runtime, 20).append(name.asString()).append(file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getString(context.runtime, 10, 32))));
        file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(166).call(context, rubyObject, panel, field);
        file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(167).call(context, rubyObject, field, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(168).call(context, rubyObject, tb, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getSymbol6(context.runtime, "textfield")));
        return file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(169).call(context, rubyObject, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(170).call(context, rubyObject, rubyObject), tb);
    }
    
    @JRubyMethod(name = "place_boxes", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__20$RUBY$place_boxes(final FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(172).callIter(threadContext, self, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite(173).call(threadContext, self, self), RuntimeHelpers.createBlock(threadContext, self, file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getBlockBody9(threadContext, "block_9$RUBY$place_boxes,1,box,false,2,./lib//lister/runner/questionnaire/checkbox_question.rb,176,true")));
    }
    
    public static IRubyObject block_9$RUBY$place_boxes(final FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: sipush          174
        //    29: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    32: aload_1        
        //    33: aload_2        
        //    34: aload           box
        //    36: aload_0        
        //    37: aload_1        
        //    38: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    41: ldc             "panel"
        //    43: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    46: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    54: ifeq            99
        //    57: aload_0        
        //    58: sipush          175
        //    61: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    64: aload_1        
        //    65: aload_2        
        //    66: aload_0        
        //    67: sipush          176
        //    70: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    73: aload_1        
        //    74: aload_2        
        //    75: aload_2        
        //    76: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    79: aload_0        
        //    80: sipush          177
        //    83: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    86: aload_1        
        //    87: aload_2        
        //    88: aload           box
        //    90: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    93: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    96: goto            126
        //    99: aload_0        
        //   100: sipush          178
        //   103: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   106: aload_1        
        //   107: aload_2        
        //   108: aload_0        
        //   109: sipush          179
        //   112: invokevirtual   ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   115: aload_1        
        //   116: aload_2        
        //   117: aload_2        
        //   118: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   121: aload           box
        //   123: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   126: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     102     9     box   Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject class_2$RUBY$CheckBoxQuestion(final FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_2$RUBY$CheckBoxQuestion(file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__1$RUBY$Questionnaire(final FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Questionnaire(file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB, threadContext, rubyObject, block);
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
        final FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB = new FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB();
        final String string = FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.class.getClassLoader().getResource("ruby/jit/FILE_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.class").toString();
        file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_FEE459997DBCE64D84B41B44AD39F0FAC03E56EB.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
