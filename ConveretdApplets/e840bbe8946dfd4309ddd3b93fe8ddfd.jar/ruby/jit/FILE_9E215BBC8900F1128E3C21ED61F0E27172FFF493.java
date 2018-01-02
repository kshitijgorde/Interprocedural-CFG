// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.Arity;
import org.jruby.ast.util.ArgsUtil;
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

public class FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 extends AbstractScript
{
    public FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493() {
        this.filename = "./lib//lister/runner/questionnaire/table_question.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffinclude_class\uffffF\uffffJPanel\uffffN\uffffswing\uffffN\uffffjavax\uffffV\uffffinclude_class\uffffF\uffffJScrollPane\uffffN\uffffswing\uffffN\uffffjavax\uffffV\uffffinclude_class\uffffF\uffffJButton\uffffN\uffffswing\uffffN\uffffjavax\uffffV\uffffinclude_class\uffffF\uffffBorderLayout\uffffN\uffffawt\uffffN\uffffjava\uffffV\uffffinclude_class\uffffF\uffffDimension\uffffN\uffffawt\uffffN\uffffjava\uffffV\ufffflistener\uffffF\uffffstart_wizard_to_add_item\uffffN\uffffsource\uffffV\ufffflistener\uffffF\uffffdelete_item_at_idx\uffffN\uffffsource\uffffV\uffffselected_row\uffffN\ufffftable\uffffN\uffffsource\uffffV\uffffattr_reader\uffffF\uffffwizard\uffffV\uffffwizard\uffffN\uffffdata_model\uffffN\ufffftable_model\uffffV\uffffinterpret\uffffV\uffffon_cancellation\uffffN\uffffwizard\uffffV\uffffremove_current_wizard!\uffffF\uffffon_completion\uffffN\uffffwizard\uffffV\uffffadd_item_from_wizard\uffffV\uffffpack\uffffN\uffffwizard\uffffV\uffffwizard\uffffV\uffffvisible=\uffffN\uffffvisible=\uffffV\uffffwizard\uffffV\uffffvisible=\uffffN\uffffvisible=\uffffV\uffffstore_question\uffffN\uffffquestionnaire\uffffN\uffffwizard\uffffV\uffffcreate_item_from_wizard_results\uffffF\uffffresult\uffffN\uffffwizard\uffffV\uffffremove_current_wizard!\uffffF\uffffmerge\uffffN\uffffdefault_values\uffffN\uffffdata_model\uffffN\ufffftable_model\uffffV\uffffdup\uffffN\uffffeach\uffffN\ufffffields\uffffN\uffffdata_model\uffffN\ufffftable_model\uffffV\uffff[]\uffffN\uffffname\uffffN\uffffis_a?\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffffname\uffffN\uffffsend\uffffN\uffffinterpret\uffffV\uffff==\uffffN\uffffkind\uffffN\uffffaccepted\uffffN\uffff[]\uffffN\uffffname\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffffname\uffffN\ufffftext_for_field_item\uffffF\uffffresults_to_raw_line_item\uffffN\uffffdata_model\uffffN\ufffftable_model\uffffV\uffffadd_default_values_of_result_hash\uffffF\ufffftranslate_texts_in_result_hash\uffffF\uffffcreate_item\uffffF\uffffadd_default_values_of_result_hash\uffffF\ufffftranslate_texts_in_result_hash\uffffF\uffffcreate_item\uffffF\uffffcreate_item\uffffN\ufffftable_model\uffffV\uffffdelete_item_at_idx\uffffN\ufffftable_model\uffffV\uffffattr_reader\uffffF\uffffnew\uffffN\uffffdata_model\uffffN\uffffdata_model_klass\uffffN\uffffdescription\uffffV\ufffffind\uffffN\uffffcolumns\uffffN\uffffdescription\uffffV\uffff==\uffffN\uffffidentifier\uffffN\uffffname\uffffN\uffffcolumn_for_field\uffffF\uffffhelp\uffffN\ufffflanguage\uffffV\uffffto_s\uffffN\uffffname\uffffN\uffffcolumn_for_field\uffffF\ufffftext\uffffN\ufffflanguage\uffffV\uffffto_s\uffffN\uffffname\uffffN\ufffffind\uffffN\uffffcolumns\uffffN\uffffdescription\uffffV\uffff==\uffffN\uffffidentifier\uffffN\uffffname\uffffN\ufffftext\uffffN\ufffflanguage\uffffV\ufffffind\uffffN\uffffoutcomes\uffffN\uffff==\uffffN\uffffidentifier\uffffN\ufffftext\uffffN\ufffflanguage\uffffV\uffffsuper\uffffS\uffffcreate\uffffN\ufffftable_model_klass\uffffV\uffffcreate\uffffN\ufffftable_model\uffffV\uffffon_data_change\uffffN\ufffftable_model\uffffV\uffffmap\uffffN\uffffdata\uffffN\ufffftable_model\uffffV\uffffeach\uffffN\ufffffields\uffffN\uffffclass\uffffN\uffffsend\uffffN\uffffname\uffffN\uffffcolumn_for_field\uffffF\ufffffind\uffffN\uffffoutcomes\uffffN\uffff==\uffffN\ufffftext\uffffN\ufffflanguage\uffffV\uffffidentifier\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffffto_s\uffffN\uffffname\uffffN\uffffresult\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffffprepare_table\uffffV\uffffplace_question_label\uffffV\uffffplace_table\uffffV\uffffplace_panel\uffffV\uffffplace_border\uffffV\uffffnew\uffffN\ufffftable\uffffV\ufffftable_scroll_pane\uffffV\uffffpreferred_size=\uffffN\uffffpreferred_size=\uffffV\uffffnew\uffffN\uffffnew\uffffN\uffffnew\uffffN\uffffsentence\uffffF\uffffadd_action_listener\uffffN\uffffadd_button\uffffV\ufffflistener\uffffF\uffffnew\uffffN\uffffsentence\uffffF\uffffadd_action_listener\uffffN\uffffdelete_button\uffffV\ufffflistener\uffffF\uffffnew\uffffN\uffffadd\uffffF\uffffpanel\uffffV\uffffadd\uffffN\uffffpanel\uffffV\ufffftable_scroll_pane\uffffV\uffffadd\uffffN\uffffbuttons_panel\uffffV\uffffadd_button\uffffV\uffffadd\uffffN\uffffbuttons_panel\uffffV\uffffdelete_button\uffffV\uffffadd\uffffN\uffffpanel\uffffV\uffffbuttons_panel\uffffV\uffff\u0003\f\u0002\u0000\u0011\u0000\u0000\u0003\n\f\u0000\u0000\u0005\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(0, "lister/runner/questionnaire/question", this.getEncoding0());
        this.setByteList(4, "items", this.getEncoding0());
        this.setByteList(2, "lister/runner/questionnaire/question_table_model", this.getEncoding0());
        this.setByteList(1, "lister/runner/questionnaire/question_table", this.getEncoding0());
        this.setByteList(3, "missing translation", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 file_9E215BBC8900F1128E3C21ED61F0E27172FFF493, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite0().call(threadContext, rubyObject, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getString0(threadContext.runtime, 32));
        file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite1().call(threadContext, rubyObject, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getString1(threadContext.runtime, 32));
        file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite2().call(threadContext, rubyObject, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getString2(threadContext.runtime, 32));
        file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite3().call(threadContext, rubyObject, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite4().call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite5().call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite6().call(threadContext, rubyObject, rubyObject))));
        file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite7().call(threadContext, rubyObject, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite8().call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite9().call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(10).call(threadContext, rubyObject, rubyObject))));
        file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(11).call(threadContext, rubyObject, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(12).call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(13).call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(14).call(threadContext, rubyObject, rubyObject))));
        file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(15).call(threadContext, rubyObject, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(16).call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(17).call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(18).call(threadContext, rubyObject, rubyObject))));
        file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(19).call(threadContext, rubyObject, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(20).call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(21).call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(22).call(threadContext, rubyObject, rubyObject))));
        return module__0$RUBY$Lister(file_9E215BBC8900F1128E3C21ED61F0E27172FFF493, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.module__1$RUBY$Questionnaire:(Lruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$Questionnaire(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_0        
        //    30: aload_1        
        //    31: ldc_w           "Question"
        //    34: bipush          16
        //    36: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    42: invokestatic    ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.class_2$RUBY$TableQuestion:(Lruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject class_2$RUBY$TableQuestion(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    29: ldc             "TableQuestion"
        //    31: swap           
        //    32: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    35: dup            
        //    36: astore_2       
        //    37: aload_1        
        //    38: swap           
        //    39: aload_0        
        //    40: aload_1        
        //    41: ldc             ",0,0,-1"
        //    43: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    46: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClass:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    49: aload_1        
        //    50: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //    53: astore          locals
        //    55: aload_0        
        //    56: bipush          23
        //    58: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    61: aload_1        
        //    62: aload_2        
        //    63: aload_2        
        //    64: aload_0        
        //    65: aload_1        
        //    66: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    69: ldc             "adder"
        //    71: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    74: aload_1        
        //    75: aload_2        
        //    76: aload_0        
        //    77: aload_1        
        //    78: ldc             "block_0$RUBY$TableQuestion,-1,,false,0,./lib//lister/runner/questionnaire/table_question.rb,13,true"
        //    80: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getBlockBody0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    83: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    86: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    89: pop            
        //    90: aload_0        
        //    91: bipush          26
        //    93: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    96: aload_1        
        //    97: aload_2        
        //    98: aload_2        
        //    99: aload_0        
        //   100: aload_1        
        //   101: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   104: ldc             "deleter"
        //   106: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   109: aload_1        
        //   110: aload_2        
        //   111: aload_0        
        //   112: aload_1        
        //   113: ldc             "block_1$RUBY$TableQuestion,-1,,false,0,./lib//lister/runner/questionnaire/table_question.rb,19,true"
        //   115: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getBlockBody1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   118: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   121: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   124: pop            
        //   125: aload_0        
        //   126: bipush          32
        //   128: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   131: aload_1        
        //   132: aload_2        
        //   133: aload_2        
        //   134: aload_0        
        //   135: aload_1        
        //   136: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   139: ldc             "wizard"
        //   141: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   144: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   147: pop            
        //   148: aload_1        
        //   149: aload_2        
        //   150: aload_0        
        //   151: ldc_w           "start_wizard_to_add_item"
        //   154: ldc_w           "method__5$RUBY$start_wizard_to_add_item"
        //   157: ldc             ",0,0,-1"
        //   159: iconst_0       
        //   160: ldc             "./lib//lister/runner/questionnaire/table_question.rb"
        //   162: ldc_w           27
        //   165: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   168: ldc_w           "NONE"
        //   171: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   174: pop            
        //   175: aload_1        
        //   176: aload_2        
        //   177: aload_0        
        //   178: ldc_w           "remove_current_wizard!"
        //   181: ldc_w           "method__6$RUBY$remove_current_wizard_b_"
        //   184: ldc             ",0,0,-1"
        //   186: iconst_0       
        //   187: ldc             "./lib//lister/runner/questionnaire/table_question.rb"
        //   189: ldc_w           41
        //   192: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   195: ldc_w           "NONE"
        //   198: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   201: pop            
        //   202: aload_1        
        //   203: aload_2        
        //   204: aload_0        
        //   205: ldc_w           "add_item_from_wizard"
        //   208: ldc_w           "method__7$RUBY$add_item_from_wizard"
        //   211: ldc             ",0,0,-1"
        //   213: iconst_0       
        //   214: ldc             "./lib//lister/runner/questionnaire/table_question.rb"
        //   216: ldc_w           46
        //   219: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   222: ldc_w           "NONE"
        //   225: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   228: pop            
        //   229: aload_1        
        //   230: aload_2        
        //   231: aload_0        
        //   232: ldc_w           "add_default_values_of_result_hash"
        //   235: ldc_w           "method__8$RUBY$add_default_values_of_result_hash"
        //   238: ldc_w           "hash,1,0,-1"
        //   241: iconst_1       
        //   242: ldc             "./lib//lister/runner/questionnaire/table_question.rb"
        //   244: ldc_w           52
        //   247: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   250: ldc_w           "qhash"
        //   253: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   256: pop            
        //   257: aload_1        
        //   258: aload_2        
        //   259: aload_0        
        //   260: ldc_w           "translate_texts_in_result_hash"
        //   263: ldc_w           "method__9$RUBY$translate_texts_in_result_hash"
        //   266: ldc_w           "hash;h,1,0,-1"
        //   269: iconst_1       
        //   270: ldc             "./lib//lister/runner/questionnaire/table_question.rb"
        //   272: ldc_w           56
        //   275: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   278: ldc_w           "qhash"
        //   281: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   284: pop            
        //   285: aload_1        
        //   286: aload_2        
        //   287: aload_0        
        //   288: ldc_w           "create_item_from_wizard_results"
        //   291: ldc_w           "method__10$RUBY$create_item_from_wizard_results"
        //   294: ldc_w           "results;h,1,0,-1"
        //   297: iconst_1       
        //   298: ldc             "./lib//lister/runner/questionnaire/table_question.rb"
        //   300: ldc_w           74
        //   303: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   306: ldc_w           "qresults"
        //   309: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   312: pop            
        //   313: aload_1        
        //   314: aload_2        
        //   315: aload_0        
        //   316: ldc_w           "create_default_item"
        //   319: ldc_w           "method__11$RUBY$create_default_item"
        //   322: ldc_w           "h,0,0,-1"
        //   325: iconst_0       
        //   326: ldc             "./lib//lister/runner/questionnaire/table_question.rb"
        //   328: ldc_w           81
        //   331: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   334: ldc_w           "NONE"
        //   337: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   340: pop            
        //   341: aload_1        
        //   342: aload_2        
        //   343: aload_0        
        //   344: ldc_w           "create_item"
        //   347: ldc_w           "method__12$RUBY$create_item"
        //   350: ldc_w           "args,0,0,0"
        //   353: iconst_m1      
        //   354: ldc             "./lib//lister/runner/questionnaire/table_question.rb"
        //   356: ldc_w           88
        //   359: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   362: ldc_w           "rargs"
        //   365: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   368: pop            
        //   369: aload_1        
        //   370: aload_2        
        //   371: aload_0        
        //   372: ldc_w           "delete_item_at_idx"
        //   375: ldc_w           "method__13$RUBY$delete_item_at_idx"
        //   378: ldc_w           "idx,1,0,-1"
        //   381: iconst_1       
        //   382: ldc             "./lib//lister/runner/questionnaire/table_question.rb"
        //   384: ldc_w           92
        //   387: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   390: ldc_w           "qidx"
        //   393: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   396: pop            
        //   397: aload_0        
        //   398: bipush          98
        //   400: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   403: aload_1        
        //   404: aload_2        
        //   405: aload_2        
        //   406: bipush          6
        //   408: anewarray       Lorg/jruby/runtime/builtin/IRubyObject;
        //   411: dup            
        //   412: iconst_0       
        //   413: aload_0        
        //   414: aload_1        
        //   415: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   418: ldc_w           "table"
        //   421: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getSymbol4:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   424: aastore        
        //   425: dup            
        //   426: iconst_1       
        //   427: aload_0        
        //   428: aload_1        
        //   429: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   432: ldc_w           "table_model"
        //   435: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getSymbol5:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   438: aastore        
        //   439: dup            
        //   440: iconst_2       
        //   441: aload_0        
        //   442: aload_1        
        //   443: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   446: ldc_w           "add_button"
        //   449: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getSymbol6:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   452: aastore        
        //   453: dup            
        //   454: iconst_3       
        //   455: aload_0        
        //   456: aload_1        
        //   457: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   460: ldc_w           "delete_button"
        //   463: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getSymbol7:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   466: aastore        
        //   467: dup            
        //   468: iconst_4       
        //   469: aload_0        
        //   470: aload_1        
        //   471: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   474: ldc_w           "buttons_panel"
        //   477: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getSymbol8:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   480: aastore        
        //   481: dup            
        //   482: iconst_5       
        //   483: aload_0        
        //   484: aload_1        
        //   485: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   488: ldc_w           "table_scroll_pane"
        //   491: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getSymbol9:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   494: aastore        
        //   495: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   498: pop            
        //   499: aload_1        
        //   500: aload_2        
        //   501: aload_0        
        //   502: ldc_w           "table_model_klass"
        //   505: ldc_w           "method__14$RUBY$table_model_klass"
        //   508: ldc             ",0,0,-1"
        //   510: iconst_0       
        //   511: ldc             "./lib//lister/runner/questionnaire/table_question.rb"
        //   513: ldc_w           99
        //   516: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   519: ldc_w           "NONE"
        //   522: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   525: pop            
        //   526: aload_1        
        //   527: aload_2        
        //   528: aload_0        
        //   529: ldc_w           "column_for_field"
        //   532: ldc_w           "method__15$RUBY$column_for_field"
        //   535: ldc_w           "field;column,1,0,-1"
        //   538: iconst_1       
        //   539: ldc             "./lib//lister/runner/questionnaire/table_question.rb"
        //   541: ldc_w           107
        //   544: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   547: ldc_w           "qfield"
        //   550: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   553: pop            
        //   554: aload_1        
        //   555: aload_2        
        //   556: aload_0        
        //   557: ldc_w           "help_text_for_field"
        //   560: ldc_w           "method__16$RUBY$help_text_for_field"
        //   563: ldc_w           "field;column,1,0,-1"
        //   566: iconst_1       
        //   567: ldc             "./lib//lister/runner/questionnaire/table_question.rb"
        //   569: ldc_w           113
        //   572: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   575: ldc_w           "qfield"
        //   578: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   581: pop            
        //   582: aload_1        
        //   583: aload_2        
        //   584: aload_0        
        //   585: ldc_w           "text_for_field"
        //   588: ldc_w           "method__17$RUBY$text_for_field"
        //   591: ldc_w           "field;column,1,0,-1"
        //   594: iconst_1       
        //   595: ldc             "./lib//lister/runner/questionnaire/table_question.rb"
        //   597: ldc_w           122
        //   600: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   603: ldc_w           "qfield"
        //   606: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   609: pop            
        //   610: aload_1        
        //   611: aload_2        
        //   612: aload_0        
        //   613: ldc_w           "text_for_field_item"
        //   616: ldc_w           "method__18$RUBY$text_for_field_item"
        //   619: ldc_w           "field;item;ret;column;outcome,2,0,-1"
        //   622: iconst_2       
        //   623: ldc             "./lib//lister/runner/questionnaire/table_question.rb"
        //   625: ldc_w           131
        //   628: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   631: ldc_w           "qfield;qitem"
        //   634: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   637: pop            
        //   638: aload_1        
        //   639: aload_2        
        //   640: aload_0        
        //   641: ldc_w           "prepare"
        //   644: ldc_w           "method__19$RUBY$prepare"
        //   647: ldc             ",0,0,-1"
        //   649: iconst_0       
        //   650: ldc             "./lib//lister/runner/questionnaire/table_question.rb"
        //   652: ldc_w           149
        //   655: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   658: ldc_w           "NONE"
        //   661: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   664: pop            
        //   665: aload_1        
        //   666: aload_2        
        //   667: aload_0        
        //   668: ldc_w           "place"
        //   671: ldc_w           "method__20$RUBY$place"
        //   674: ldc             ",0,0,-1"
        //   676: iconst_0       
        //   677: ldc             "./lib//lister/runner/questionnaire/table_question.rb"
        //   679: ldc_w           182
        //   682: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   685: ldc_w           "NONE"
        //   688: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   691: pop            
        //   692: aload_1        
        //   693: aload_2        
        //   694: aload_0        
        //   695: ldc_w           "prepare_table"
        //   698: ldc_w           "method__21$RUBY$prepare_table"
        //   701: ldc             ",0,0,-1"
        //   703: iconst_0       
        //   704: ldc             "./lib//lister/runner/questionnaire/table_question.rb"
        //   706: ldc_w           189
        //   709: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   712: ldc_w           "NONE"
        //   715: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   718: pop            
        //   719: aload_1        
        //   720: aload_2        
        //   721: aload_0        
        //   722: ldc_w           "prepare_panel"
        //   725: ldc_w           "method__22$RUBY$prepare_panel"
        //   728: ldc             ",0,0,-1"
        //   730: iconst_0       
        //   731: ldc             "./lib//lister/runner/questionnaire/table_question.rb"
        //   733: ldc_w           202
        //   736: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   739: ldc_w           "NONE"
        //   742: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   745: pop            
        //   746: aload_1        
        //   747: aload_2        
        //   748: aload_0        
        //   749: ldc_w           "place_panel"
        //   752: ldc_w           "method__23$RUBY$place_panel"
        //   755: ldc             ",0,0,-1"
        //   757: iconst_0       
        //   758: ldc             "./lib//lister/runner/questionnaire/table_question.rb"
        //   760: ldc_w           206
        //   763: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   766: ldc_w           "NONE"
        //   769: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   772: pop            
        //   773: aload_1        
        //   774: aload_2        
        //   775: aload_0        
        //   776: ldc_w           "place_table"
        //   779: ldc_w           "method__24$RUBY$place_table"
        //   782: ldc             ",0,0,-1"
        //   784: iconst_0       
        //   785: ldc             "./lib//lister/runner/questionnaire/table_question.rb"
        //   787: ldc_w           210
        //   790: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   793: ldc_w           "NONE"
        //   796: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   799: aload_1        
        //   800: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   803: goto            811
        //   806: aload_1        
        //   807: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   810: athrow         
        //   811: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  55     757     4     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  55     799    806    811    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_0$RUBY$TableQuestion(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 scriptObject, final ThreadContext context, final IRubyObject self, final IRubyObject rubyObject, final Block block) {
        context.getCurrentScope();
        RuntimeHelpers.processBlockArgument(context.runtime, block);
        return RuntimeHelpers.def(context, self, scriptObject, "actionPerformed", "method__3$RUBY$actionPerformed", "event,1,0,-1", 1, "./lib//lister/runner/questionnaire/table_question.rb", 14, CallConfiguration.FrameNoneScopeNone, "qevent");
    }
    
    @JRubyMethod(name = "actionPerformed", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__3$RUBY$actionPerformed(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 file_9E215BBC8900F1128E3C21ED61F0E27172FFF493, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(24).call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(25).call(threadContext, rubyObject, rubyObject));
    }
    
    public static IRubyObject block_1$RUBY$TableQuestion(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 scriptObject, final ThreadContext context, final IRubyObject self, final IRubyObject rubyObject, final Block block) {
        context.getCurrentScope();
        RuntimeHelpers.processBlockArgument(context.runtime, block);
        return RuntimeHelpers.def(context, self, scriptObject, "actionPerformed", "method__4$RUBY$actionPerformed", "event,1,0,-1", 1, "./lib//lister/runner/questionnaire/table_question.rb", 20, CallConfiguration.FrameNoneScopeNone, "qevent");
    }
    
    @JRubyMethod(name = "actionPerformed", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$actionPerformed(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 file_9E215BBC8900F1128E3C21ED61F0E27172FFF493, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(27).call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(28).call(threadContext, rubyObject, rubyObject), file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(29).call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(30).call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(31).call(threadContext, rubyObject, rubyObject))));
    }
    
    @JRubyMethod(name = "start_wizard_to_add_item", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__5$RUBY$start_wizard_to_add_item(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 file_9E215BBC8900F1128E3C21ED61F0E27172FFF493, final ThreadContext context, final IRubyObject caller, final Block block) {
        IRubyObject rubyObject;
        if (file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(33).call(context, caller, caller).isTrue()) {
            rubyObject = context.nil;
        }
        else {
            file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.setVariable0(context.runtime, "@wizard", caller, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(34).call(context, caller, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(35).call(context, caller, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(36).call(context, caller, caller)), file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(37).call(context, caller, caller)));
            file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(38).callIter(context, caller, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(39).call(context, caller, caller), RuntimeHelpers.createBlock(context, caller, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getBlockBody2(context, "block_2$RUBY$start_wizard_to_add_item,-1,,false,0,./lib//lister/runner/questionnaire/table_question.rb,30,true")));
            file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(41).callIter(context, caller, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(42).call(context, caller, caller), RuntimeHelpers.createBlock(context, caller, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getBlockBody3(context, "block_3$RUBY$start_wizard_to_add_item,-1,,false,0,./lib//lister/runner/questionnaire/table_question.rb,33,true")));
            file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(44).call(context, caller, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(45).call(context, caller, caller));
            final IRubyObject call = file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(46).call(context, caller, caller);
            final CallSite selectAttrAsgnCallSite = RuntimeHelpers.selectAttrAsgnCallSite(call, caller, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(47), file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(48));
            final RubyBoolean true = context.runtime.getTrue();
            context.pollThreadEvents();
            rubyObject = RuntimeHelpers.doAttrAsgn(call, selectAttrAsgnCallSite, true, context, caller);
        }
        return rubyObject;
    }
    
    public static IRubyObject block_2$RUBY$start_wizard_to_add_item(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 file_9E215BBC8900F1128E3C21ED61F0E27172FFF493, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        threadContext.getCurrentScope();
        RuntimeHelpers.processBlockArgument(threadContext.runtime, block);
        return file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(40).call(threadContext, rubyObject, rubyObject);
    }
    
    public static IRubyObject block_3$RUBY$start_wizard_to_add_item(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 file_9E215BBC8900F1128E3C21ED61F0E27172FFF493, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        threadContext.getCurrentScope();
        RuntimeHelpers.processBlockArgument(threadContext.runtime, block);
        return file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(43).call(threadContext, rubyObject, rubyObject);
    }
    
    @JRubyMethod(name = "remove_current_wizard!", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__6$RUBY$remove_current_wizard_b_(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 file_9E215BBC8900F1128E3C21ED61F0E27172FFF493, final ThreadContext context, final IRubyObject object, final Block block) {
        final IRubyObject call = file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(49).call(context, object, object);
        final CallSite selectAttrAsgnCallSite = RuntimeHelpers.selectAttrAsgnCallSite(call, object, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(50), file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(51));
        final RubyBoolean false = context.runtime.getFalse();
        context.pollThreadEvents();
        RuntimeHelpers.doAttrAsgn(call, selectAttrAsgnCallSite, false, context, object);
        return file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.setVariable1(context.runtime, "@wizard", object, context.nil);
    }
    
    @JRubyMethod(name = "add_item_from_wizard", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__7$RUBY$add_item_from_wizard(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 file_9E215BBC8900F1128E3C21ED61F0E27172FFF493, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(52).call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(53).call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(54).call(threadContext, rubyObject, rubyObject)));
        file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(55).call(threadContext, rubyObject, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(56).call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(57).call(threadContext, rubyObject, rubyObject)));
        return file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(58).call(threadContext, rubyObject, rubyObject);
    }
    
    @JRubyMethod(name = "add_default_values_of_result_hash", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__8$RUBY$add_default_values_of_result_hash(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 file_9E215BBC8900F1128E3C21ED61F0E27172FFF493, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(59).call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(60).call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(61).call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(62).call(threadContext, rubyObject, rubyObject))), rubyObject2);
    }
    
    @JRubyMethod(name = "translate_texts_in_result_hash", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__9$RUBY$translate_texts_in_result_hash(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    17: bipush          63
        //    19: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    22: aload_1        
        //    23: aload_2        
        //    24: aload           locals
        //    26: aload_1        
        //    27: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    30: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    36: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: pop            
        //    40: aload_0        
        //    41: bipush          64
        //    43: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    46: aload_1        
        //    47: aload_2        
        //    48: aload_0        
        //    49: bipush          65
        //    51: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    54: aload_1        
        //    55: aload_2        
        //    56: aload_0        
        //    57: bipush          66
        //    59: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    62: aload_1        
        //    63: aload_2        
        //    64: aload_0        
        //    65: bipush          67
        //    67: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    70: aload_1        
        //    71: aload_2        
        //    72: aload_2        
        //    73: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    76: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    79: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    82: aload_1        
        //    83: aload_2        
        //    84: aload_0        
        //    85: aload_1        
        //    86: ldc_w           "block_4$RUBY$translate_texts_in_result_hash,1,f;current_value;k,false,2,./lib//lister/runner/questionnaire/table_question.rb,58,false"
        //    89: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getBlockBody4:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    92: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    95: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    98: pop            
        //    99: aload           locals
        //   101: aload_1        
        //   102: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   105: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   108: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     95      5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_4$RUBY$translate_texts_in_result_hash(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    39: aload           5
        //    41: swap           
        //    42: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: pop            
        //    46: pop            
        //    47: aload           locals
        //    49: aload_0        
        //    50: bipush          68
        //    52: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    55: aload_1        
        //    56: aload_2        
        //    57: aload           locals
        //    59: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    62: aload_1        
        //    63: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    66: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    69: aload_0        
        //    70: bipush          69
        //    72: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    75: aload_1        
        //    76: aload_2        
        //    77: aload           locals
        //    79: aload_1        
        //    80: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    83: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    86: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    89: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    92: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    95: pop            
        //    96: aload_0        
        //    97: bipush          70
        //    99: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   102: aload_1        
        //   103: aload_2        
        //   104: aload           locals
        //   106: aload_1        
        //   107: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   110: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   113: aload_0        
        //   114: aload_1        
        //   115: ldc_w           "Symbol"
        //   118: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   121: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   124: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   129: ifeq            221
        //   132: aload           locals
        //   134: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   137: aload_1        
        //   138: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   141: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   144: dup            
        //   145: aload_2        
        //   146: aload_0        
        //   147: bipush          71
        //   149: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   152: aload_0        
        //   153: bipush          72
        //   155: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   158: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   161: aload_0        
        //   162: bipush          73
        //   164: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   167: aload_1        
        //   168: aload_2        
        //   169: aload           locals
        //   171: aload_1        
        //   172: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   175: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   178: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   181: aload_0        
        //   182: bipush          74
        //   184: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   187: aload_1        
        //   188: aload_2        
        //   189: aload_0        
        //   190: bipush          75
        //   192: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   195: aload_1        
        //   196: aload_2        
        //   197: aload_2        
        //   198: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   201: aload           locals
        //   203: aload_1        
        //   204: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   207: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   210: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   213: aload_1        
        //   214: aload_2        
        //   215: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   218: goto            446
        //   221: aload_0        
        //   222: bipush          76
        //   224: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   227: aload_1        
        //   228: aload_2        
        //   229: aload_0        
        //   230: bipush          77
        //   232: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   235: aload_1        
        //   236: aload_2        
        //   237: aload           locals
        //   239: aload_1        
        //   240: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   243: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   246: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   249: aload_0        
        //   250: aload_1        
        //   251: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   254: ldc_w           "string"
        //   257: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   260: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   263: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   268: ifeq            442
        //   271: aload_0        
        //   272: bipush          78
        //   274: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   277: aload_1        
        //   278: aload_2        
        //   279: aload           locals
        //   281: aload_1        
        //   282: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   285: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   288: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   291: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   296: ifeq            435
        //   299: aload           locals
        //   301: aload_0        
        //   302: bipush          79
        //   304: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   307: aload_1        
        //   308: aload_2        
        //   309: aload           locals
        //   311: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   314: aload_1        
        //   315: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   318: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   321: aload_0        
        //   322: bipush          80
        //   324: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   327: aload_1        
        //   328: aload_2        
        //   329: aload           locals
        //   331: aload_1        
        //   332: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   335: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   338: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   341: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   344: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   347: pop            
        //   348: aload           locals
        //   350: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   353: aload_1        
        //   354: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   357: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   360: dup            
        //   361: aload_2        
        //   362: aload_0        
        //   363: bipush          81
        //   365: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   368: aload_0        
        //   369: bipush          82
        //   371: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   374: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   377: aload_0        
        //   378: bipush          83
        //   380: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   383: aload_1        
        //   384: aload_2        
        //   385: aload           locals
        //   387: aload_1        
        //   388: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   391: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   394: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   397: aload_0        
        //   398: bipush          84
        //   400: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   403: aload_1        
        //   404: aload_2        
        //   405: aload_2        
        //   406: aload           locals
        //   408: aload_1        
        //   409: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   412: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   415: aload           locals
        //   417: aload_1        
        //   418: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   421: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   424: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   427: aload_1        
        //   428: aload_2        
        //   429: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   432: goto            439
        //   435: aload_1        
        //   436: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   439: goto            446
        //   442: aload_1        
        //   443: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   446: areturn        
        //   447: pop            
        //   448: goto            47
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  47     400     5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  47     447    447    451    Lorg/jruby/exceptions/JumpException$RedoJump;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "create_item_from_wizard_results", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__10$RUBY$create_item_from_wizard_results(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 file_9E215BBC8900F1128E3C21ED61F0E27172FFF493, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        final IRubyObject nil = threadContext.nil;
        IRubyObject h = file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(85).call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(86).call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(87).call(threadContext, rubyObject, rubyObject)), rubyObject2);
        h = file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(88).call(threadContext, rubyObject, rubyObject, h);
        h = file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(89).call(threadContext, rubyObject, rubyObject, h);
        return file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(90).call(threadContext, rubyObject, rubyObject, h);
    }
    
    @JRubyMethod(name = "create_default_item", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__11$RUBY$create_default_item(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 file_9E215BBC8900F1128E3C21ED61F0E27172FFF493, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        IRubyObject h = threadContext.nil;
        h = RubyHash.newHash(threadContext.runtime);
        h = file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(91).call(threadContext, rubyObject, rubyObject, h);
        h = file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(92).call(threadContext, rubyObject, rubyObject, h);
        return file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(93).call(threadContext, rubyObject, rubyObject, h);
    }
    
    @JRubyMethod(name = "create_item", frame = true, required = 0, optional = 0, rest = 0)
    public static IRubyObject method__12$RUBY$create_item(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 file_9E215BBC8900F1128E3C21ED61F0E27172FFF493, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] input, final Block block) {
        final IRubyObject nil = threadContext.nil;
        final IRubyObject args = RuntimeHelpers.createSubarray(input, threadContext.runtime, 0);
        return file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(94).call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(95).call(threadContext, rubyObject, rubyObject), ArgsUtil.convertToJavaArray(RuntimeHelpers.splatValue(args)));
    }
    
    @JRubyMethod(name = "delete_item_at_idx", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__13$RUBY$delete_item_at_idx(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 file_9E215BBC8900F1128E3C21ED61F0E27172FFF493, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(96).call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(97).call(threadContext, rubyObject, rubyObject), rubyObject2);
    }
    
    @JRubyMethod(name = "table_model_klass", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__14$RUBY$table_model_klass(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 file_9E215BBC8900F1128E3C21ED61F0E27172FFF493, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        if (!file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getVariable0(threadContext.runtime, "@table_model_klass", rubyObject).isTrue()) {
            file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.setVariable2(threadContext.runtime, "@table_model_klass", rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(99).call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getConstant1(threadContext, "Class"), file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getConstant2(threadContext, "QuestionTableModel")));
            file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(100).call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getVariable1(threadContext.runtime, "@table_model_klass", rubyObject), file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(101).call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(102).call(threadContext, rubyObject, rubyObject)));
        }
        return file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getVariable2(threadContext.runtime, "@table_model_klass", rubyObject);
    }
    
    @JRubyMethod(name = "column_for_field", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__15$RUBY$column_for_field(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    17: bipush          103
        //    19: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    22: aload_1        
        //    23: aload_2        
        //    24: aload_0        
        //    25: bipush          104
        //    27: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    30: aload_1        
        //    31: aload_2        
        //    32: aload_0        
        //    33: bipush          105
        //    35: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    38: aload_1        
        //    39: aload_2        
        //    40: aload_2        
        //    41: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    44: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: aload_1        
        //    48: aload_2        
        //    49: aload_0        
        //    50: aload_1        
        //    51: ldc_w           "block_5$RUBY$column_for_field,1,c,false,2,./lib//lister/runner/questionnaire/table_question.rb,108,true"
        //    54: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getBlockBody5:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    57: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    60: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    63: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    66: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     53      5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_5$RUBY$column_for_field(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          106
        //    28: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_0        
        //    34: bipush          107
        //    36: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    39: aload_1        
        //    40: aload_2        
        //    41: aload           c
        //    43: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    46: aload_0        
        //    47: bipush          108
        //    49: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    52: aload_1        
        //    53: aload_2        
        //    54: aload           5
        //    56: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    59: aload_1        
        //    60: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    63: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    66: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    69: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    72: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     48      9     c     Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "help_text_for_field", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__16$RUBY$help_text_for_field(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 file_9E215BBC8900F1128E3C21ED61F0E27172FFF493, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        final IRubyObject nil = threadContext.nil;
        final IRubyObject column = file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(109).call(threadContext, rubyObject, rubyObject, rubyObject2);
        return column.isTrue() ? file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(110).call(threadContext, rubyObject, column, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(111).call(threadContext, rubyObject, rubyObject)) : file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(112).call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(113).call(threadContext, rubyObject, rubyObject2));
    }
    
    @JRubyMethod(name = "text_for_field", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__17$RUBY$text_for_field(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 file_9E215BBC8900F1128E3C21ED61F0E27172FFF493, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        final IRubyObject nil = threadContext.nil;
        final IRubyObject column = file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(114).call(threadContext, rubyObject, rubyObject, rubyObject2);
        return column.isTrue() ? file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(115).call(threadContext, rubyObject, column, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(116).call(threadContext, rubyObject, rubyObject)) : file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(117).call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(118).call(threadContext, rubyObject, rubyObject2));
    }
    
    @JRubyMethod(name = "text_for_field_item", frame = true, required = 2, optional = 0, rest = -1)
    public static IRubyObject method__18$RUBY$text_for_field_item(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final IRubyObject p4, final Block p5) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          6
        //     6: aload           6
        //     8: invokevirtual   org/jruby/runtime/DynamicScope.getValues:()[Lorg/jruby/runtime/builtin/IRubyObject;
        //    11: astore          7
        //    13: aload           7
        //    15: aload_1        
        //    16: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    19: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.fillNil:([Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;)V
        //    22: aload_3        
        //    23: aload           6
        //    25: swap           
        //    26: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    29: pop            
        //    30: aload           4
        //    32: aload           6
        //    34: swap           
        //    35: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    38: pop            
        //    39: aload           locals
        //    41: aload_0        
        //    42: aload_1        
        //    43: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    46: bipush          32
        //    48: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getString3:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    51: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    54: pop            
        //    55: aload           locals
        //    57: aload_0        
        //    58: bipush          119
        //    60: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    63: aload_1        
        //    64: aload_2        
        //    65: aload_0        
        //    66: bipush          120
        //    68: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    71: aload_1        
        //    72: aload_2        
        //    73: aload_0        
        //    74: bipush          121
        //    76: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    79: aload_1        
        //    80: aload_2        
        //    81: aload_2        
        //    82: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    85: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    88: aload_1        
        //    89: aload_2        
        //    90: aload_0        
        //    91: aload_1        
        //    92: ldc_w           "block_6$RUBY$text_for_field_item,1,c,false,2,./lib//lister/runner/questionnaire/table_question.rb,134,true"
        //    95: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getBlockBody6:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    98: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   101: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   104: invokevirtual   org/jruby/runtime/DynamicScope.setValueThreeDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   107: pop            
        //   108: aload           locals
        //   110: aload_1        
        //   111: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   114: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   117: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   122: ifeq            260
        //   125: aload_0        
        //   126: bipush          125
        //   128: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   131: aload_1        
        //   132: aload_2        
        //   133: aload           locals
        //   135: aload_1        
        //   136: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   139: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   142: aload_0        
        //   143: bipush          126
        //   145: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   148: aload_1        
        //   149: aload_2        
        //   150: aload_2        
        //   151: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   154: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   157: pop            
        //   158: aload           7
        //   160: iconst_4       
        //   161: aload_0        
        //   162: bipush          127
        //   164: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   167: aload_1        
        //   168: aload_2        
        //   169: aload_0        
        //   170: sipush          128
        //   173: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   176: aload_1        
        //   177: aload_2        
        //   178: aload           locals
        //   180: aload_1        
        //   181: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   184: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   187: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   190: aload_1        
        //   191: aload_2        
        //   192: aload_0        
        //   193: aload_1        
        //   194: ldc_w           "block_7$RUBY$text_for_field_item,1,o,false,2,./lib//lister/runner/questionnaire/table_question.rb,140,true"
        //   197: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getBlockBody7:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   200: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   203: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   206: aastore        
        //   207: aload           7
        //   209: iconst_4       
        //   210: aaload         
        //   211: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   216: ifeq            257
        //   219: aload           locals
        //   221: aload_0        
        //   222: sipush          131
        //   225: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   228: aload_1        
        //   229: aload_2        
        //   230: aload           7
        //   232: iconst_4       
        //   233: aaload         
        //   234: aload_0        
        //   235: sipush          132
        //   238: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   241: aload_1        
        //   242: aload_2        
        //   243: aload_2        
        //   244: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   247: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   250: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   253: pop            
        //   254: goto            257
        //   257: goto            260
        //   260: aload           locals
        //   262: aload_1        
        //   263: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   266: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   269: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  39     231     6     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_6$RUBY$text_for_field_item(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          122
        //    28: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_0        
        //    34: bipush          123
        //    36: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    39: aload_1        
        //    40: aload_2        
        //    41: aload           c
        //    43: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    46: aload_0        
        //    47: bipush          124
        //    49: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    52: aload_1        
        //    53: aload_2        
        //    54: aload           5
        //    56: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    59: aload_1        
        //    60: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    63: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    66: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    69: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    72: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     48      9     c     Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_7$RUBY$text_for_field_item(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: sipush          129
        //    29: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    32: aload_1        
        //    33: aload_2        
        //    34: aload_0        
        //    35: sipush          130
        //    38: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload           o
        //    45: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    48: aload           5
        //    50: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    53: aload_1        
        //    54: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    60: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    63: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     39      9     o     Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "prepare", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__19$RUBY$prepare(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 file_9E215BBC8900F1128E3C21ED61F0E27172FFF493, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(133).call(threadContext, self, self, RuntimeHelpers.getArgValues(threadContext), block);
        file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.setVariable3(threadContext.runtime, "@table_model", self, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(134).call(threadContext, self, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(135).call(threadContext, self, self), self));
        file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.setVariable4(threadContext.runtime, "@table", self, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(136).call(threadContext, self, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getConstant3(threadContext, "QuestionTable"), file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(137).call(threadContext, self, self)));
        file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(138).callIter(threadContext, self, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(139).call(threadContext, self, self), RuntimeHelpers.createBlock(threadContext, self, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getBlockBody(threadContext, 11, "block_8$RUBY$prepare,-1,ary,false,0,./lib//lister/runner/questionnaire/table_question.rb,153,false")));
        return file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(162).call(threadContext, self, self);
    }
    
    public static IRubyObject block_8$RUBY$prepare(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    27: pop            
        //    28: pop            
        //    29: aload           locals
        //    31: aload_0        
        //    32: sipush          140
        //    35: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    38: aload_1        
        //    39: aload_2        
        //    40: aload_0        
        //    41: sipush          141
        //    44: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    47: aload_1        
        //    48: aload_2        
        //    49: aload_0        
        //    50: sipush          142
        //    53: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    56: aload_1        
        //    57: aload_2        
        //    58: aload_2        
        //    59: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    62: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    65: aload_1        
        //    66: aload_2        
        //    67: aload_0        
        //    68: aload_1        
        //    69: bipush          10
        //    71: ldc_w           "block_9$RUBY$prepare,1,item;dump,false,2,./lib//lister/runner/questionnaire/table_question.rb,155,false"
        //    74: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getBlockBody:(Lorg/jruby/runtime/ThreadContext;ILjava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    77: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    80: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    83: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    86: pop            
        //    87: aload_0        
        //    88: sipush          159
        //    91: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    94: aload_1        
        //    95: aload_2        
        //    96: aload_2        
        //    97: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   100: dup            
        //   101: aload_2        
        //   102: aload_0        
        //   103: sipush          160
        //   106: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   109: aload_0        
        //   110: sipush          161
        //   113: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   116: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   119: aload_0        
        //   120: aload_1        
        //   121: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   124: bipush          32
        //   126: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getString4:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   129: aload           locals
        //   131: aload_1        
        //   132: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   135: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   138: aload_1        
        //   139: aload_2        
        //   140: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   143: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  29     115     5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_9$RUBY$prepare(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    22: pop            
        //    23: aload_1        
        //    24: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    27: aload           4
        //    29: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    32: aload_3        
        //    33: aload           5
        //    35: swap           
        //    36: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: pop            
        //    40: pop            
        //    41: aload           locals
        //    43: aload_1        
        //    44: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    47: invokestatic    org/jruby/RubyHash.newHash:(Lorg/jruby/Ruby;)Lorg/jruby/RubyHash;
        //    50: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    53: pop            
        //    54: aload_0        
        //    55: sipush          143
        //    58: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    61: aload_1        
        //    62: aload_2        
        //    63: aload_0        
        //    64: sipush          144
        //    67: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    70: aload_1        
        //    71: aload_2        
        //    72: aload_0        
        //    73: sipush          145
        //    76: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    79: aload_1        
        //    80: aload_2        
        //    81: aload           locals
        //    83: aload_1        
        //    84: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    87: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    90: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    93: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    96: aload_1        
        //    97: aload_2        
        //    98: aload_0        
        //    99: aload_1        
        //   100: ldc_w           "block_10$RUBY$prepare,1,field;field_val;val;column;outcome,false,2,./lib//lister/runner/questionnaire/table_question.rb,158,false"
        //   103: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getBlockBody9:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   106: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   109: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   112: pop            
        //   113: aload           locals
        //   115: aload_1        
        //   116: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   119: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   122: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  41     82      5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_10$RUBY$prepare(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    16: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    19: aload           5
        //    21: swap           
        //    22: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    25: aload           5
        //    27: swap           
        //    28: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    31: aload           5
        //    33: swap           
        //    34: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    37: aload           5
        //    39: swap           
        //    40: invokevirtual   org/jruby/runtime/DynamicScope.setValueThreeDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    43: pop            
        //    44: aload_1        
        //    45: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    48: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.fillNil:([Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;)V
        //    51: aload_1        
        //    52: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    55: aload           4
        //    57: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    60: aload_3        
        //    61: aload           5
        //    63: swap           
        //    64: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    67: pop            
        //    68: pop            
        //    69: aload           locals
        //    71: aload_0        
        //    72: sipush          146
        //    75: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    78: aload_1        
        //    79: aload_2        
        //    80: aload           locals
        //    82: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    85: aload_1        
        //    86: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    89: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    92: aload_0        
        //    93: sipush          147
        //    96: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    99: aload_1        
        //   100: aload_2        
        //   101: aload           locals
        //   103: aload_1        
        //   104: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   107: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   110: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   113: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   116: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   119: pop            
        //   120: aload           locals
        //   122: aload           locals
        //   124: aload_1        
        //   125: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   128: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   131: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   134: pop            
        //   135: aload           locals
        //   137: aload_0        
        //   138: sipush          148
        //   141: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   144: aload_1        
        //   145: aload_2        
        //   146: aload_2        
        //   147: aload           locals
        //   149: aload_1        
        //   150: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   153: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   156: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   159: invokevirtual   org/jruby/runtime/DynamicScope.setValueThreeDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   162: pop            
        //   163: aload           locals
        //   165: aload_1        
        //   166: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   169: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   172: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   177: ifeq            270
        //   180: aload           6
        //   182: iconst_4       
        //   183: aload_0        
        //   184: sipush          149
        //   187: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   190: aload_1        
        //   191: aload_2        
        //   192: aload_0        
        //   193: sipush          150
        //   196: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   199: aload_1        
        //   200: aload_2        
        //   201: aload           locals
        //   203: aload_1        
        //   204: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   207: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   210: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   213: aload_1        
        //   214: aload_2        
        //   215: aload_0        
        //   216: aload_1        
        //   217: ldc_w           "block_11$RUBY$prepare,1,o,false,2,./lib//lister/runner/questionnaire/table_question.rb,163,true"
        //   220: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getBlockBody8:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   223: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   226: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   229: aastore        
        //   230: aload           6
        //   232: iconst_4       
        //   233: aaload         
        //   234: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   239: ifeq            267
        //   242: aload           locals
        //   244: aload_0        
        //   245: sipush          154
        //   248: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   251: aload_1        
        //   252: aload_2        
        //   253: aload           6
        //   255: iconst_4       
        //   256: aaload         
        //   257: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   260: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   263: pop            
        //   264: goto            267
        //   267: goto            270
        //   270: aload           locals
        //   272: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   275: aload_1        
        //   276: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   279: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   282: dup            
        //   283: aload_2        
        //   284: aload_0        
        //   285: sipush          155
        //   288: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   291: aload_0        
        //   292: sipush          156
        //   295: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   298: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   301: aload_0        
        //   302: sipush          157
        //   305: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   308: aload_1        
        //   309: aload_2        
        //   310: aload_0        
        //   311: sipush          158
        //   314: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   317: aload_1        
        //   318: aload_2        
        //   319: aload           locals
        //   321: aload_1        
        //   322: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   325: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   328: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   331: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   334: aload           locals
        //   336: aload_1        
        //   337: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   340: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   343: aload_1        
        //   344: aload_2        
        //   345: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   348: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  69     280     5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_11$RUBY$prepare(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: sipush          151
        //    29: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    32: aload_1        
        //    33: aload_2        
        //    34: aload_0        
        //    35: sipush          152
        //    38: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload           o
        //    45: aload_0        
        //    46: sipush          153
        //    49: invokevirtual   ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    52: aload_1        
        //    53: aload_2        
        //    54: aload_2        
        //    55: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    58: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    61: aload           5
        //    63: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    66: aload_1        
        //    67: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    70: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    73: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    @JRubyMethod(name = "place", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__20$RUBY$place(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 file_9E215BBC8900F1128E3C21ED61F0E27172FFF493, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(163).call(threadContext, rubyObject, rubyObject);
        file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(164).call(threadContext, rubyObject, rubyObject);
        file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(165).call(threadContext, rubyObject, rubyObject);
        return file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(166).call(threadContext, rubyObject, rubyObject);
    }
    
    @JRubyMethod(name = "prepare_table", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__21$RUBY$prepare_table(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 file_9E215BBC8900F1128E3C21ED61F0E27172FFF493, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.setVariable5(threadContext.runtime, "@table_scroll_pane", rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(167).call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getConstant4(threadContext, "JScrollPane"), file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(168).call(threadContext, rubyObject, rubyObject)));
        final IRubyObject call = file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(169).call(threadContext, rubyObject, rubyObject);
        RuntimeHelpers.doAttrAsgn(call, RuntimeHelpers.selectAttrAsgnCallSite(call, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(170), file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(171)), file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(172).call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getConstant5(threadContext, "Dimension"), file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getFixnum0(threadContext.runtime, 500), file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getFixnum1(threadContext.runtime, 200)), threadContext, rubyObject);
        file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.setVariable6(threadContext.runtime, "@buttons_panel", rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(173).call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getConstant6(threadContext, "JPanel")));
        file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.setVariable7(threadContext.runtime, "@add_button", rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(174).call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getConstant7(threadContext, "JButton"), file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(175).call(threadContext, rubyObject, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getSymbol(threadContext.runtime, 10, "add"))));
        file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(176).call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(177).call(threadContext, rubyObject, rubyObject), file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(178).call(threadContext, rubyObject, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getSymbol0(threadContext.runtime, "adder")));
        file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.setVariable8(threadContext.runtime, "@delete_button", rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(179).call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getConstant8(threadContext, "JButton"), file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(180).call(threadContext, rubyObject, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getSymbol(threadContext.runtime, 11, "delete"))));
        return file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(181).call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(182).call(threadContext, rubyObject, rubyObject), file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(183).call(threadContext, rubyObject, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getSymbol1(threadContext.runtime, "deleter")));
    }
    
    @JRubyMethod(name = "prepare_panel", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__22$RUBY$prepare_panel(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 file_9E215BBC8900F1128E3C21ED61F0E27172FFF493, final ThreadContext context, final IRubyObject object, final Block block) {
        return file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.setVariable9(context.runtime, "@panel", object, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(184).call(context, object, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getConstant9(context, "JPanel")));
    }
    
    @JRubyMethod(name = "place_panel", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__23$RUBY$place_panel(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 file_9E215BBC8900F1128E3C21ED61F0E27172FFF493, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(185).call(threadContext, rubyObject, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(186).call(threadContext, rubyObject, rubyObject), file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getConstantFrom(RuntimeHelpers.checkIsModule(file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getConstant(threadContext, "BorderLayout", 10)), threadContext, "CENTER", 11));
    }
    
    @JRubyMethod(name = "place_table", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__24$RUBY$place_table(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 file_9E215BBC8900F1128E3C21ED61F0E27172FFF493, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(187).call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(188).call(threadContext, rubyObject, rubyObject), file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(189).call(threadContext, rubyObject, rubyObject), file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getConstantFrom(RuntimeHelpers.checkIsModule(file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getConstant(threadContext, "BorderLayout", 12)), threadContext, "CENTER", 13));
        file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(190).call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(191).call(threadContext, rubyObject, rubyObject), file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(192).call(threadContext, rubyObject, rubyObject));
        file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(193).call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(194).call(threadContext, rubyObject, rubyObject), file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(195).call(threadContext, rubyObject, rubyObject));
        return file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(196).call(threadContext, rubyObject, file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(197).call(threadContext, rubyObject, rubyObject), file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getCallSite(198).call(threadContext, rubyObject, rubyObject), file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getConstantFrom(RuntimeHelpers.checkIsModule(file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.getConstant(threadContext, "BorderLayout", 14)), threadContext, "SOUTH", 15));
    }
    
    public static IRubyObject class_2$RUBY$TableQuestion(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 file_9E215BBC8900F1128E3C21ED61F0E27172FFF493, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_2$RUBY$TableQuestion(file_9E215BBC8900F1128E3C21ED61F0E27172FFF493, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__1$RUBY$Questionnaire(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 file_9E215BBC8900F1128E3C21ED61F0E27172FFF493, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Questionnaire(file_9E215BBC8900F1128E3C21ED61F0E27172FFF493, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 file_9E215BBC8900F1128E3C21ED61F0E27172FFF493, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_9E215BBC8900F1128E3C21ED61F0E27172FFF493, threadContext, rubyObject, block);
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
        final FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493 file_9E215BBC8900F1128E3C21ED61F0E27172FFF493 = new FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493();
        final String string = FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.class.getClassLoader().getResource("ruby/jit/FILE_9E215BBC8900F1128E3C21ED61F0E27172FFF493.class").toString();
        file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_9E215BBC8900F1128E3C21ED61F0E27172FFF493.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
