// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.Arity;
import org.jruby.Ruby;
import org.jruby.RubyFixnum;
import org.jruby.runtime.DynamicScope;
import org.jruby.RubyString;
import org.jruby.RubyHash;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 extends AbstractScript
{
    public FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86() {
        this.filename = "./lib//lister/runner/questionnaire/questionnaire.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("include_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffinclude\uffffF\uffffinclude\uffffF\ufffflistener\uffffF\uffffclaim_finished\uffffN\uffffsource\uffffV\ufffflistener\uffffF\uffffnext_question\uffffN\uffffsource\uffffV\ufffflistener\uffffF\uffffprevious_question\uffffN\uffffsource\uffffV\ufffflistener\uffffF\uffffshow_help_for_current_question\uffffN\uffffsource\uffffV\uffffcurrent_language\uffffN\uffffinterpret\uffffV\uffffcurrent\uffffV\uffffhelp\uffffN\uffffdescription\uffffN\uffffcurrent\uffffV\ufffflanguage\uffffV\uffffno_help\uffffN\uffffinterpret\uffffV\uffffclean_html_message\uffffF\uffffshow_message_dialog\uffffN\ufffftop_level_ancestor\uffffV\uffffhelp_sentence\uffffV\uffffattr_reader\uffffF\uffffeach\uffffN\uffffsend\uffffF\uffffto_sym\uffffN\uffffnew\uffffN\uffffprevious_sentence\uffffV\uffffadd_action_listener\uffffN\ufffflistener\uffffF\uffffbuttons\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffffnew\uffffN\uffffhelp_sentence\uffffV\uffffadd_action_listener\uffffN\ufffflistener\uffffF\uffffbuttons\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffffnew\uffffN\uffffnext_sentence\uffffV\uffffadd_action_listener\uffffN\ufffflistener\uffffF\uffffbuttons\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffffnew\uffffN\uffffdone_sentence\uffffV\uffffadd_action_listener\uffffN\ufffflistener\uffffF\uffffbuttons\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffffcount_label\uffffV\ufffftext=\uffffN\ufffftext=\uffffV\uffffcount_string\uffffV\uffff<=\uffffN\ufffftotal_count\uffffV\uffff>\uffffN\uffffcount\uffffV\ufffftotal_count\uffffV\uffffcount\uffffV\ufffftotal_count\uffffV\uffffnew\uffffN\uffffcount_string\uffffV\uffffadd\uffffF\uffff[]\uffffN\uffffpanels\uffffV\uffffadd\uffffN\uffff[]\uffffN\uffffpanels\uffffV\uffffcount_label\uffffV\uffffadd\uffffN\uffff[]\uffffN\uffffpanels\uffffV\uffff[]\uffffN\uffffbuttons\uffffV\uffffadd\uffffN\uffff[]\uffffN\uffffpanels\uffffV\uffff[]\uffffN\uffffbuttons\uffffV\uffffadd\uffffN\uffff[]\uffffN\uffffpanels\uffffV\uffff[]\uffffN\uffffbuttons\uffffV\uffffhide\uffffN\uffff[]\uffffN\uffffbuttons\uffffV\uffffpanels\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffffnew\uffffN\uffffrespond_to?\uffffN\uffffcall\uffffN\uffff[]\uffffN\uffffwords\uffffV\uffffsend\uffffN\uffffinterpret\uffffV\uffffword\uffffF\uffffsentence\uffffF\uffffsentence\uffffF\uffffsentence\uffffF\uffffsentence\uffffF\uffffsuper\uffffS\uffffnew\uffffN\uffffpreferred_size=\uffffN\uffffpreferred_size=\uffffV\uffffnew\uffffN\uffffdup\uffffN\uffffsize\uffffN\uffffprepare_buttons\uffffV\uffffprepare_count_label\uffffV\uffffprepare_panels\uffffV\uffffplace_count_label\uffffV\uffffplace_help_button\uffffV\uffffplace_previous_button\uffffV\uffffplace_next_button\uffffV\uffffplace_panels\uffffV\uffff<<\uffffN\uffffdone_questions\uffffV\uffffcurrent\uffffV\uffffempty?\uffffN\uffffdone_questions\uffffV\uffffshow\uffffN\uffff[]\uffffN\uffffbuttons\uffffV\uffffnew\uffffN\uffffeach\uffffN\uffffdone_questions\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffffidentifier\uffffN\uffffdescription\uffffN\uffffresult\uffffN\uffffnext_question\uffffV\ufffftype\uffffN\uffff===\uffffN\uffffnew\uffffN\uffff===\uffffN\uffffnew\uffffN\uffff===\uffffN\uffffnew\uffffN\uffff===\uffffN\uffffnew\uffffN\uffffraise\uffffF\ufffftype\uffffN\uffff===\uffffN\uffff===\uffffN\uffff===\uffffN\uffff===\uffffN\uffff===\uffffN\uffffcurrent=\uffffN\uffffcurrent=\uffffV\uffffquestion_for_description\uffffN\uffffclass\uffffN\uffffinterpret\uffffV\uffffraise\uffffF\uffffunloop_questions\uffffV\uffffcurrent\uffffV\uffffdisplay_current\uffffV\uffffcount\uffffF\uffff-\uffffF\uffffcount=\uffffN\uffffpop\uffffN\uffffdone_questions\uffffV\uffffempty?\uffffN\uffffdone_questions\uffffV\uffffhide\uffffN\uffff[]\uffffN\uffffbuttons\uffffV\uffffrepaint\uffffN\uffff[]\uffffN\uffffpanels\uffffV\uffffcurrent\uffffV\uffffremove\uffffF\uffffcurrent\uffffV\uffffunshift\uffffN\uffffremaining_questions\uffffV\uffffcurrent\uffffV\uffffcurrent=\uffffN\uffffcurrent=\uffffV\uffffshowing?\uffffN\uffff[]\uffffN\uffffbuttons\uffffV\uffffremove\uffffF\uffffgreetings\uffffV\uffffreplace_done_button_by_next\uffffV\uffffdisplay_current\uffffV\uffffraise\uffffF\uffffattr_accessor\uffffF\uffffeach\uffffN\uffffwaiting_notifiers\uffffV\uffff<<\uffffN\uffffqueue\uffffN\uffffshould_progress?\uffffF\uffffstore_question\uffffV\uffffrelease_the_kraken!\uffffF\uffffshow_help_for_invalidity\uffffN\uffffcurrent\uffffV\uffffvalid?\uffffN\uffffcurrent\uffffV\uffffcurrent\uffffV\uffffverify_current_question\uffffV\uffffcurrent\uffffV\uffffshould_progress?\uffffF\uffffshow_help_for_invalidity\uffffN\uffffcurrent\uffffV\uffffstore_question\uffffV\uffffloop_questions\uffffV\uffffcurrent\uffffV\uffffdisplay_current\uffffV\uffffcount\uffffF\uffff+\uffffF\uffffcount=\uffffN\uffffcurrent\uffffV\uffffremove\uffffF\uffffcurrent\uffffV\uffffshift\uffffN\uffffremaining_questions\uffffV\uffffcurrent=\uffffN\uffffcurrent=\uffffV\uffffnil?\uffffN\uffffgreetings\uffffV\uffffempty?\uffffN\uffffremaining_questions\uffffV\uffffreplace_next_button_by_done\uffffV\uffffcurrent=\uffffN\uffffcurrent=\uffffV\uffffgreetings\uffffV\uffffreplace_current_question_by_greetings\uffffV\uffffreplace_next_button_by_done\uffffV\uffffvalidate\uffffV\uffffrepaint\uffffV\uffffadd\uffffF\uffffcurrent\uffffV\uffffhas_help?\uffffN\uffffdescription\uffffN\uffffcurrent\uffffV\ufffflanguage\uffffV\uffffshow\uffffN\uffff[]\uffffN\uffffbuttons\uffffV\uffffhide\uffffN\uffff[]\uffffN\uffffbuttons\uffffV\uffffvalidate\uffffV\uffffrepaint\uffffV\uffffremove\uffffN\uffff[]\uffffN\uffffpanels\uffffV\uffff[]\uffffN\uffffbuttons\uffffV\uffffadd\uffffN\uffff[]\uffffN\uffffpanels\uffffV\uffff[]\uffffN\uffffbuttons\uffffV\uffffremove\uffffN\uffff[]\uffffN\uffffpanels\uffffV\uffff[]\uffffN\uffffbuttons\uffffV\uffffadd\uffffN\uffff[]\uffffN\uffffpanels\uffffV\uffff[]\uffffN\uffffbuttons\uffffV\uffffcurrent\uffffV\uffffremove\uffffF\uffffcurrent\uffffV\uffffgreetings\uffffV\uffff===\uffffN\uffffgreetings\uffffV\uffff===\uffffN\uffffgreetings\uffffV\uffffnew\uffffN\uffffgreetings\uffffV\uffffadd\uffffF\uffffgreetings\uffffV\uffff\u0003\u0015\u0002\u0000(\u0000\u0000\u0005\u0013\u0007\u0000\u0000\u0014\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(15, "", this.getEncoding0());
        this.setByteList(10, "lister/runner/questionnaire/table_question", this.getEncoding0());
        this.setByteList(0, "javax.swing.JButton", this.getEncoding0());
        this.setByteList(2, "javax.swing.JPanel", this.getEncoding0());
        this.setByteList(18, "not supported putting current object: ", this.getEncoding0());
        this.setByteList(1, "javax.swing.JFrame", this.getEncoding0());
        this.setByteList(16, "/", this.getEncoding0());
        this.setByteList(9, "lister/runner/questionnaire/checkbox_question", this.getEncoding0());
        this.setByteList(19, "no previous question but previous button pressed", this.getEncoding0());
        this.setByteList(3, "java.awt.BorderLayout", this.getEncoding0());
        this.setByteList(6, "lister/runner/questionnaire/has_listener", this.getEncoding0());
        this.setByteList(5, "javax.swing.JOptionPane", this.getEncoding0());
        this.setByteList(7, "lister/runner/questionnaire/html", this.getEncoding0());
        this.setByteList(14, "_button", this.getEncoding0());
        this.setByteList(13, "prepare_", this.getEncoding0());
        this.setByteList(8, "lister/runner/questionnaire/radio_question", this.getEncoding0());
        this.setByteList(17, "not implemented ", this.getEncoding0());
        this.setByteList(11, "lister/runner/questionnaire/freetext_question", this.getEncoding0());
        this.setByteList(4, "java.awt.Dimension", this.getEncoding0());
        this.setByteList(12, "instance-variable", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite0().call(threadContext, rubyObject, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getString0(threadContext.runtime, 32));
        file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite1().call(threadContext, rubyObject, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getString1(threadContext.runtime, 32));
        file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite2().call(threadContext, rubyObject, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getString2(threadContext.runtime, 32));
        file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite3().call(threadContext, rubyObject, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getString3(threadContext.runtime, 32));
        file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite4().call(threadContext, rubyObject, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getString4(threadContext.runtime, 32));
        file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite5().call(threadContext, rubyObject, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getString5(threadContext.runtime, 32));
        file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite6().call(threadContext, rubyObject, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getString6(threadContext.runtime, 32));
        file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite7().call(threadContext, rubyObject, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getString7(threadContext.runtime, 32));
        file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite8().call(threadContext, rubyObject, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getString8(threadContext.runtime, 32));
        file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite9().call(threadContext, rubyObject, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getString9(threadContext.runtime, 32));
        file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(10).call(threadContext, rubyObject, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getString(threadContext.runtime, 10, 32));
        file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(11).call(threadContext, rubyObject, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getString(threadContext.runtime, 11, 32));
        return module__0$RUBY$Lister(file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.module__1$RUBY$Questionnaire:(Lruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$Questionnaire(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_0        
        //    30: aload_1        
        //    31: ldc_w           "JPanel"
        //    34: bipush          39
        //    36: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    42: invokestatic    ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.class_2$RUBY$Questionnaire:(Lruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject class_2$RUBY$Questionnaire(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    29: ldc             "Questionnaire"
        //    31: swap           
        //    32: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    35: dup            
        //    36: astore_2       
        //    37: aload_1        
        //    38: swap           
        //    39: aload_0        
        //    40: aload_1        
        //    41: ldc             ",0,0,-1"
        //    43: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    46: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClass:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    49: aload_1        
        //    50: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //    53: astore          locals
        //    55: aload_0        
        //    56: bipush          12
        //    58: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    61: aload_1        
        //    62: aload_2        
        //    63: aload_2        
        //    64: aload_0        
        //    65: aload_1        
        //    66: ldc             "HasListeners"
        //    68: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    71: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    74: pop            
        //    75: aload_0        
        //    76: bipush          13
        //    78: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    81: aload_1        
        //    82: aload_2        
        //    83: aload_2        
        //    84: aload_0        
        //    85: aload_1        
        //    86: ldc             "HasHtml"
        //    88: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstant1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    91: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    94: pop            
        //    95: aload_0        
        //    96: bipush          14
        //    98: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   101: aload_1        
        //   102: aload_2        
        //   103: aload_2        
        //   104: aload_0        
        //   105: aload_1        
        //   106: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   109: ldc             "done"
        //   111: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   114: aload_1        
        //   115: aload_2        
        //   116: aload_0        
        //   117: aload_1        
        //   118: ldc             "block_0$RUBY$Questionnaire,-1,,false,0,./lib//lister/runner/questionnaire/questionnaire.rb,21,true"
        //   120: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getBlockBody0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   123: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   126: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   129: pop            
        //   130: aload_0        
        //   131: bipush          17
        //   133: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   136: aload_1        
        //   137: aload_2        
        //   138: aload_2        
        //   139: aload_0        
        //   140: aload_1        
        //   141: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   144: ldc             "next"
        //   146: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   149: aload_1        
        //   150: aload_2        
        //   151: aload_0        
        //   152: aload_1        
        //   153: ldc_w           "block_1$RUBY$Questionnaire,-1,,false,0,./lib//lister/runner/questionnaire/questionnaire.rb,27,true"
        //   156: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getBlockBody1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   159: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   162: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   165: pop            
        //   166: aload_0        
        //   167: bipush          20
        //   169: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   172: aload_1        
        //   173: aload_2        
        //   174: aload_2        
        //   175: aload_0        
        //   176: aload_1        
        //   177: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   180: ldc_w           "previous"
        //   183: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   186: aload_1        
        //   187: aload_2        
        //   188: aload_0        
        //   189: aload_1        
        //   190: ldc_w           "block_2$RUBY$Questionnaire,-1,,false,0,./lib//lister/runner/questionnaire/questionnaire.rb,33,true"
        //   193: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getBlockBody2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   196: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   199: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   202: pop            
        //   203: aload_0        
        //   204: bipush          23
        //   206: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   209: aload_1        
        //   210: aload_2        
        //   211: aload_2        
        //   212: aload_0        
        //   213: aload_1        
        //   214: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   217: ldc_w           "help"
        //   220: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   223: aload_1        
        //   224: aload_2        
        //   225: aload_0        
        //   226: aload_1        
        //   227: ldc_w           "block_3$RUBY$Questionnaire,-1,,false,0,./lib//lister/runner/questionnaire/questionnaire.rb,39,true"
        //   230: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getBlockBody3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   233: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   236: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   239: pop            
        //   240: aload_1        
        //   241: aload_2        
        //   242: aload_0        
        //   243: ldc_w           "language"
        //   246: ldc_w           "method__7$RUBY$language"
        //   249: ldc             ",0,0,-1"
        //   251: iconst_0       
        //   252: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //   254: ldc_w           45
        //   257: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   260: ldc_w           "NONE"
        //   263: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   266: pop            
        //   267: aload_1        
        //   268: aload_2        
        //   269: aload_0        
        //   270: ldc_w           "show_help_for_current_question"
        //   273: ldc_w           "method__8$RUBY$show_help_for_current_question"
        //   276: ldc_w           "message,0,0,-1"
        //   279: iconst_0       
        //   280: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //   282: ldc_w           49
        //   285: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   288: ldc_w           "NONE"
        //   291: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   294: pop            
        //   295: aload_0        
        //   296: bipush          39
        //   298: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   301: aload_1        
        //   302: aload_2        
        //   303: aload_2        
        //   304: bipush          10
        //   306: anewarray       Lorg/jruby/runtime/builtin/IRubyObject;
        //   309: dup            
        //   310: iconst_0       
        //   311: aload_0        
        //   312: aload_1        
        //   313: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   316: ldc_w           "remaining_questions"
        //   319: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol4:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   322: aastore        
        //   323: dup            
        //   324: iconst_1       
        //   325: aload_0        
        //   326: aload_1        
        //   327: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   330: ldc_w           "current"
        //   333: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol5:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   336: aastore        
        //   337: dup            
        //   338: iconst_2       
        //   339: aload_0        
        //   340: aload_1        
        //   341: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   344: ldc_w           "done_questions"
        //   347: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol6:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   350: aastore        
        //   351: dup            
        //   352: iconst_3       
        //   353: aload_0        
        //   354: aload_1        
        //   355: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   358: ldc_w           "panels"
        //   361: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol7:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   364: aastore        
        //   365: dup            
        //   366: iconst_4       
        //   367: aload_0        
        //   368: aload_1        
        //   369: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   372: ldc_w           "buttons"
        //   375: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol8:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   378: aastore        
        //   379: dup            
        //   380: iconst_5       
        //   381: aload_0        
        //   382: aload_1        
        //   383: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   386: ldc_w           "count_label"
        //   389: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol9:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   392: aastore        
        //   393: dup            
        //   394: bipush          6
        //   396: aload_0        
        //   397: aload_1        
        //   398: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   401: ldc_w           10
        //   404: ldc_w           "total_count"
        //   407: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   410: aastore        
        //   411: dup            
        //   412: bipush          7
        //   414: aload_0        
        //   415: aload_1        
        //   416: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   419: ldc_w           11
        //   422: ldc_w           "count"
        //   425: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   428: aastore        
        //   429: dup            
        //   430: bipush          8
        //   432: aload_0        
        //   433: aload_1        
        //   434: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   437: ldc_w           12
        //   440: ldc_w           "greetings"
        //   443: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   446: aastore        
        //   447: dup            
        //   448: bipush          9
        //   450: aload_0        
        //   451: aload_1        
        //   452: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   455: ldc_w           13
        //   458: ldc_w           "words"
        //   461: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   464: aastore        
        //   465: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   468: pop            
        //   469: aload_1        
        //   470: aload_2        
        //   471: aload_0        
        //   472: ldc_w           "buttons"
        //   475: ldc_w           "method__9$RUBY$buttons"
        //   478: ldc             ",0,0,-1"
        //   480: iconst_0       
        //   481: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //   483: ldc_w           67
        //   486: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   489: ldc_w           "NONE"
        //   492: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   495: pop            
        //   496: aload_1        
        //   497: aload_2        
        //   498: aload_0        
        //   499: ldc_w           "panels"
        //   502: ldc_w           "method__10$RUBY$panels"
        //   505: ldc             ",0,0,-1"
        //   507: iconst_0       
        //   508: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //   510: ldc_w           71
        //   513: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   516: ldc_w           "NONE"
        //   519: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   522: pop            
        //   523: aload_1        
        //   524: aload_2        
        //   525: aload_0        
        //   526: ldc_w           "prepare_buttons"
        //   529: ldc_w           "method__11$RUBY$prepare_buttons"
        //   532: ldc             ",0,0,-1"
        //   534: iconst_0       
        //   535: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //   537: ldc_w           75
        //   540: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   543: ldc_w           "NONE"
        //   546: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   549: pop            
        //   550: aload_1        
        //   551: aload_2        
        //   552: aload_0        
        //   553: ldc_w           "prepare_previous_button"
        //   556: ldc_w           "method__12$RUBY$prepare_previous_button"
        //   559: ldc_w           "button,0,0,-1"
        //   562: iconst_0       
        //   563: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //   565: ldc_w           81
        //   568: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   571: ldc_w           "NONE"
        //   574: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   577: pop            
        //   578: aload_1        
        //   579: aload_2        
        //   580: aload_0        
        //   581: ldc_w           "prepare_help_button"
        //   584: ldc_w           "method__13$RUBY$prepare_help_button"
        //   587: ldc_w           "button,0,0,-1"
        //   590: iconst_0       
        //   591: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //   593: ldc_w           87
        //   596: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   599: ldc_w           "NONE"
        //   602: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   605: pop            
        //   606: aload_1        
        //   607: aload_2        
        //   608: aload_0        
        //   609: ldc_w           "prepare_next_button"
        //   612: ldc_w           "method__14$RUBY$prepare_next_button"
        //   615: ldc_w           "button,0,0,-1"
        //   618: iconst_0       
        //   619: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //   621: ldc_w           93
        //   624: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   627: ldc_w           "NONE"
        //   630: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   633: pop            
        //   634: aload_1        
        //   635: aload_2        
        //   636: aload_0        
        //   637: ldc_w           "prepare_done_button"
        //   640: ldc_w           "method__15$RUBY$prepare_done_button"
        //   643: ldc_w           "button,0,0,-1"
        //   646: iconst_0       
        //   647: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //   649: ldc_w           99
        //   652: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   655: ldc_w           "NONE"
        //   658: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   661: pop            
        //   662: aload_1        
        //   663: aload_2        
        //   664: aload_0        
        //   665: ldc_w           "count="
        //   668: ldc_w           "method__16$RUBY$count_equal_"
        //   671: ldc_w           "val,1,0,-1"
        //   674: iconst_1       
        //   675: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //   677: ldc_w           105
        //   680: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   683: ldc_w           "qval"
        //   686: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   689: pop            
        //   690: aload_1        
        //   691: aload_2        
        //   692: aload_0        
        //   693: ldc_w           "count_string"
        //   696: ldc_w           "method__17$RUBY$count_string"
        //   699: ldc             ",0,0,-1"
        //   701: iconst_0       
        //   702: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //   704: ldc_w           110
        //   707: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   710: ldc_w           "NONE"
        //   713: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   716: pop            
        //   717: aload_1        
        //   718: aload_2        
        //   719: aload_0        
        //   720: ldc_w           "prepare_count_label"
        //   723: ldc_w           "method__18$RUBY$prepare_count_label"
        //   726: ldc             ",0,0,-1"
        //   728: iconst_0       
        //   729: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //   731: ldc_w           118
        //   734: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   737: ldc_w           "NONE"
        //   740: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   743: pop            
        //   744: aload_1        
        //   745: aload_2        
        //   746: aload_0        
        //   747: ldc_w           "place_panels"
        //   750: ldc_w           "method__19$RUBY$place_panels"
        //   753: ldc             ",0,0,-1"
        //   755: iconst_0       
        //   756: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //   758: ldc_w           122
        //   761: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   764: ldc_w           "NONE"
        //   767: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   770: pop            
        //   771: aload_1        
        //   772: aload_2        
        //   773: aload_0        
        //   774: ldc_w           "place_count_label"
        //   777: ldc_w           "method__20$RUBY$place_count_label"
        //   780: ldc             ",0,0,-1"
        //   782: iconst_0       
        //   783: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //   785: ldc_w           126
        //   788: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   791: ldc_w           "NONE"
        //   794: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   797: pop            
        //   798: aload_1        
        //   799: aload_2        
        //   800: aload_0        
        //   801: ldc_w           "place_next_button"
        //   804: ldc_w           "method__21$RUBY$place_next_button"
        //   807: ldc             ",0,0,-1"
        //   809: iconst_0       
        //   810: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //   812: ldc_w           130
        //   815: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   818: ldc_w           "NONE"
        //   821: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   824: pop            
        //   825: aload_1        
        //   826: aload_2        
        //   827: aload_0        
        //   828: ldc_w           "place_help_button"
        //   831: ldc_w           "method__22$RUBY$place_help_button"
        //   834: ldc             ",0,0,-1"
        //   836: iconst_0       
        //   837: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //   839: ldc_w           134
        //   842: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   845: ldc_w           "NONE"
        //   848: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   851: pop            
        //   852: aload_1        
        //   853: aload_2        
        //   854: aload_0        
        //   855: ldc_w           "place_previous_button"
        //   858: ldc_w           "method__23$RUBY$place_previous_button"
        //   861: ldc             ",0,0,-1"
        //   863: iconst_0       
        //   864: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //   866: ldc_w           138
        //   869: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   872: ldc_w           "NONE"
        //   875: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   878: pop            
        //   879: aload_1        
        //   880: aload_2        
        //   881: aload_0        
        //   882: ldc_w           "prepare_panels"
        //   885: ldc_w           "method__24$RUBY$prepare_panels"
        //   888: ldc             ",0,0,-1"
        //   890: iconst_0       
        //   891: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //   893: ldc_w           143
        //   896: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   899: ldc_w           "NONE"
        //   902: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   905: pop            
        //   906: aload_1        
        //   907: aload_2        
        //   908: aload_0        
        //   909: ldc_w           "interpret"
        //   912: ldc_w           "method__25$RUBY$interpret"
        //   915: ldc             ",0,0,-1"
        //   917: iconst_0       
        //   918: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //   920: ldc_w           147
        //   923: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   926: ldc_w           "NONE"
        //   929: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   932: pop            
        //   933: aload_1        
        //   934: aload_2        
        //   935: aload_0        
        //   936: ldc_w           "word"
        //   939: ldc_w           "method__26$RUBY$word"
        //   942: ldc_w           "sym,1,0,-1"
        //   945: iconst_1       
        //   946: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //   948: ldc_w           155
        //   951: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   954: ldc_w           "qsym"
        //   957: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   960: pop            
        //   961: aload_1        
        //   962: aload_2        
        //   963: aload_0        
        //   964: ldc_w           "sentence"
        //   967: ldc_w           "method__27$RUBY$sentence"
        //   970: ldc_w           "sym,1,0,-1"
        //   973: iconst_1       
        //   974: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //   976: ldc_w           159
        //   979: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   982: ldc_w           "qsym"
        //   985: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   988: pop            
        //   989: aload_1        
        //   990: aload_2        
        //   991: aload_0        
        //   992: ldc_w           "help_sentence"
        //   995: ldc_w           "method__28$RUBY$help_sentence"
        //   998: ldc             ",0,0,-1"
        //  1000: iconst_0       
        //  1001: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //  1003: ldc_w           163
        //  1006: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1009: ldc_w           "NONE"
        //  1012: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1015: pop            
        //  1016: aload_1        
        //  1017: aload_2        
        //  1018: aload_0        
        //  1019: ldc_w           "next_sentence"
        //  1022: ldc_w           "method__29$RUBY$next_sentence"
        //  1025: ldc             ",0,0,-1"
        //  1027: iconst_0       
        //  1028: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //  1030: ldc_w           167
        //  1033: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1036: ldc_w           "NONE"
        //  1039: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1042: pop            
        //  1043: aload_1        
        //  1044: aload_2        
        //  1045: aload_0        
        //  1046: ldc_w           "previous_sentence"
        //  1049: ldc_w           "method__30$RUBY$previous_sentence"
        //  1052: ldc             ",0,0,-1"
        //  1054: iconst_0       
        //  1055: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //  1057: ldc_w           171
        //  1060: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1063: ldc_w           "NONE"
        //  1066: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1069: pop            
        //  1070: aload_1        
        //  1071: aload_2        
        //  1072: aload_0        
        //  1073: ldc_w           "done_sentence"
        //  1076: ldc_w           "method__31$RUBY$done_sentence"
        //  1079: ldc             ",0,0,-1"
        //  1081: iconst_0       
        //  1082: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //  1084: ldc_w           175
        //  1087: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1090: ldc_w           "NONE"
        //  1093: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1096: pop            
        //  1097: aload_1        
        //  1098: aload_2        
        //  1099: aload_0        
        //  1100: ldc_w           "initialize"
        //  1103: ldc_w           "method__32$RUBY$initialize"
        //  1106: ldc_w           "questions;interpret;words;greetings,2,2,-1"
        //  1109: bipush          -3
        //  1111: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //  1113: ldc_w           179
        //  1116: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1119: ldc_w           "qquestions;qinterpret;owords;ogreetings"
        //  1122: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1125: pop            
        //  1126: aload_1        
        //  1127: aload_2        
        //  1128: aload_0        
        //  1129: ldc_w           "store_question"
        //  1132: ldc_w           "method__33$RUBY$store_question"
        //  1135: ldc             ",0,0,-1"
        //  1137: iconst_0       
        //  1138: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //  1140: ldc_w           200
        //  1143: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1146: ldc_w           "NONE"
        //  1149: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1152: pop            
        //  1153: aload_1        
        //  1154: aload_2        
        //  1155: aload_0        
        //  1156: ldc_w           "results"
        //  1159: ldc_w           "method__34$RUBY$results"
        //  1162: ldc_w           "h,0,0,-1"
        //  1165: iconst_0       
        //  1166: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //  1168: ldc_w           205
        //  1171: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1174: ldc_w           "NONE"
        //  1177: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1180: pop            
        //  1181: aload_1        
        //  1182: aload_2        
        //  1183: aload_0        
        //  1184: ldc_w           "start"
        //  1187: ldc_w           "method__35$RUBY$start"
        //  1190: ldc             ",0,0,-1"
        //  1192: iconst_0       
        //  1193: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //  1195: ldc_w           213
        //  1198: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1201: ldc_w           "NONE"
        //  1204: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1207: pop            
        //  1208: aload_1        
        //  1209: aload_2        
        //  1210: aload_2        
        //  1211: aload_0        
        //  1212: ldc_w           "question_for_description"
        //  1215: ldc_w           "method__36$RUBY$question_for_description"
        //  1218: ldc_w           "description;interpret;args,2,0,2"
        //  1221: bipush          -3
        //  1223: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //  1225: ldc_w           217
        //  1228: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1231: ldc_w           "qdescription;qinterpret;rargs"
        //  1234: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1237: pop            
        //  1238: aload_1        
        //  1239: aload_2        
        //  1240: aload_0        
        //  1241: ldc_w           "current="
        //  1244: ldc_w           "method__37$RUBY$current_equal_"
        //  1247: ldc_w           "val,1,0,-1"
        //  1250: iconst_1       
        //  1251: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //  1253: ldc_w           232
        //  1256: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1259: ldc_w           "qval"
        //  1262: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1265: pop            
        //  1266: aload_1        
        //  1267: aload_2        
        //  1268: aload_0        
        //  1269: ldc_w           "previous_question"
        //  1272: ldc_w           "method__38$RUBY$previous_question"
        //  1275: ldc             ",0,0,-1"
        //  1277: iconst_0       
        //  1278: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //  1280: ldc_w           245
        //  1283: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1286: ldc_w           "NONE"
        //  1289: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1292: pop            
        //  1293: aload_1        
        //  1294: aload_2        
        //  1295: aload_0        
        //  1296: ldc_w           "unloop_questions"
        //  1299: ldc_w           "method__39$RUBY$unloop_questions"
        //  1302: ldc_w           "previous_question,0,0,-1"
        //  1305: iconst_0       
        //  1306: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //  1308: ldc_w           250
        //  1311: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1314: ldc_w           "NONE"
        //  1317: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1320: pop            
        //  1321: aload_0        
        //  1322: sipush          210
        //  1325: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //  1328: aload_1        
        //  1329: aload_2        
        //  1330: aload_2        
        //  1331: aload_0        
        //  1332: aload_1        
        //  1333: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1336: ldc_w           19
        //  1339: ldc_w           "waiting_notifiers"
        //  1342: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //  1345: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1348: pop            
        //  1349: aload_1        
        //  1350: aload_2        
        //  1351: aload_0        
        //  1352: ldc_w           "awake_notifiers"
        //  1355: ldc_w           "method__40$RUBY$awake_notifiers"
        //  1358: ldc             ",0,0,-1"
        //  1360: iconst_0       
        //  1361: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //  1363: ldc_w           272
        //  1366: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1369: ldc_w           "NONE"
        //  1372: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1375: pop            
        //  1376: aload_1        
        //  1377: aload_2        
        //  1378: ldc_w           "release_the_kraken!"
        //  1381: ldc_w           "awake_notifiers"
        //  1384: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defineAlias:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/Object;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1387: pop            
        //  1388: aload_1        
        //  1389: aload_2        
        //  1390: aload_0        
        //  1391: ldc_w           "claim_finished"
        //  1394: ldc_w           "method__41$RUBY$claim_finished"
        //  1397: ldc             ",0,0,-1"
        //  1399: iconst_0       
        //  1400: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //  1402: ldc_w           278
        //  1405: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1408: ldc_w           "NONE"
        //  1411: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1414: pop            
        //  1415: aload_1        
        //  1416: aload_2        
        //  1417: aload_0        
        //  1418: ldc_w           "verify_current_question"
        //  1421: ldc_w           "method__42$RUBY$verify_current_question"
        //  1424: ldc             ",0,0,-1"
        //  1426: iconst_0       
        //  1427: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //  1429: ldc_w           287
        //  1432: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1435: ldc_w           "NONE"
        //  1438: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1441: pop            
        //  1442: aload_1        
        //  1443: aload_2        
        //  1444: aload_0        
        //  1445: ldc_w           "should_progress?"
        //  1448: ldc_w           "method__43$RUBY$should_progress_p_"
        //  1451: ldc             ",0,0,-1"
        //  1453: iconst_0       
        //  1454: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //  1456: ldc_w           291
        //  1459: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1462: ldc_w           "NONE"
        //  1465: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1468: pop            
        //  1469: aload_1        
        //  1470: aload_2        
        //  1471: aload_0        
        //  1472: ldc_w           "next_question"
        //  1475: ldc_w           "method__44$RUBY$next_question"
        //  1478: ldc             ",0,0,-1"
        //  1480: iconst_0       
        //  1481: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //  1483: ldc_w           299
        //  1486: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1489: ldc_w           "NONE"
        //  1492: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1495: pop            
        //  1496: aload_1        
        //  1497: aload_2        
        //  1498: aload_0        
        //  1499: ldc_w           "loop_questions"
        //  1502: ldc_w           "method__45$RUBY$loop_questions"
        //  1505: ldc_w           "obj,0,0,-1"
        //  1508: iconst_0       
        //  1509: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //  1511: ldc_w           311
        //  1514: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1517: ldc_w           "NONE"
        //  1520: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1523: pop            
        //  1524: aload_1        
        //  1525: aload_2        
        //  1526: aload_0        
        //  1527: ldc_w           "display_current"
        //  1530: ldc_w           "method__46$RUBY$display_current"
        //  1533: ldc             ",0,0,-1"
        //  1535: iconst_0       
        //  1536: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //  1538: ldc_w           327
        //  1541: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1544: ldc_w           "NONE"
        //  1547: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1550: pop            
        //  1551: aload_1        
        //  1552: aload_2        
        //  1553: aload_0        
        //  1554: ldc_w           "replace_next_button_by_done"
        //  1557: ldc_w           "method__47$RUBY$replace_next_button_by_done"
        //  1560: ldc             ",0,0,-1"
        //  1562: iconst_0       
        //  1563: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //  1565: ldc_w           338
        //  1568: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1571: ldc_w           "NONE"
        //  1574: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1577: pop            
        //  1578: aload_1        
        //  1579: aload_2        
        //  1580: aload_0        
        //  1581: ldc_w           "replace_done_button_by_next"
        //  1584: ldc_w           "method__48$RUBY$replace_done_button_by_next"
        //  1587: ldc             ",0,0,-1"
        //  1589: iconst_0       
        //  1590: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //  1592: ldc_w           343
        //  1595: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1598: ldc_w           "NONE"
        //  1601: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1604: pop            
        //  1605: aload_1        
        //  1606: aload_2        
        //  1607: aload_0        
        //  1608: ldc_w           "replace_current_question_by_greetings"
        //  1611: ldc_w           "method__49$RUBY$replace_current_question_by_greetings"
        //  1614: ldc             ",0,0,-1"
        //  1616: iconst_0       
        //  1617: ldc             "./lib//lister/runner/questionnaire/questionnaire.rb"
        //  1619: ldc_w           348
        //  1622: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1625: ldc_w           "NONE"
        //  1628: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1631: aload_1        
        //  1632: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //  1635: goto            1643
        //  1638: aload_1        
        //  1639: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //  1642: athrow         
        //  1643: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  55     1589    4     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  55     1631   1638   1643   Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_0$RUBY$Questionnaire(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 scriptObject, final ThreadContext context, final IRubyObject self, final IRubyObject rubyObject, final Block block) {
        context.getCurrentScope();
        RuntimeHelpers.processBlockArgument(context.runtime, block);
        return RuntimeHelpers.def(context, self, scriptObject, "action_performed", "method__3$RUBY$action_performed", "event,1,0,-1", 1, "./lib//lister/runner/questionnaire/questionnaire.rb", 22, CallConfiguration.FrameNoneScopeNone, "qevent");
    }
    
    @JRubyMethod(name = "action_performed", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__3$RUBY$action_performed(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(15).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(16).call(threadContext, rubyObject, rubyObject));
    }
    
    public static IRubyObject block_1$RUBY$Questionnaire(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 scriptObject, final ThreadContext context, final IRubyObject self, final IRubyObject rubyObject, final Block block) {
        context.getCurrentScope();
        RuntimeHelpers.processBlockArgument(context.runtime, block);
        return RuntimeHelpers.def(context, self, scriptObject, "action_performed", "method__4$RUBY$action_performed", "event,1,0,-1", 1, "./lib//lister/runner/questionnaire/questionnaire.rb", 28, CallConfiguration.FrameNoneScopeNone, "qevent");
    }
    
    @JRubyMethod(name = "action_performed", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$action_performed(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(18).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(19).call(threadContext, rubyObject, rubyObject));
    }
    
    public static IRubyObject block_2$RUBY$Questionnaire(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 scriptObject, final ThreadContext context, final IRubyObject self, final IRubyObject rubyObject, final Block block) {
        context.getCurrentScope();
        RuntimeHelpers.processBlockArgument(context.runtime, block);
        return RuntimeHelpers.def(context, self, scriptObject, "action_performed", "method__5$RUBY$action_performed", "event,1,0,-1", 1, "./lib//lister/runner/questionnaire/questionnaire.rb", 34, CallConfiguration.FrameNoneScopeNone, "qevent");
    }
    
    @JRubyMethod(name = "action_performed", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__5$RUBY$action_performed(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(21).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(22).call(threadContext, rubyObject, rubyObject));
    }
    
    public static IRubyObject block_3$RUBY$Questionnaire(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 scriptObject, final ThreadContext context, final IRubyObject self, final IRubyObject rubyObject, final Block block) {
        context.getCurrentScope();
        RuntimeHelpers.processBlockArgument(context.runtime, block);
        return RuntimeHelpers.def(context, self, scriptObject, "action_performed", "method__6$RUBY$action_performed", "event,1,0,-1", 1, "./lib//lister/runner/questionnaire/questionnaire.rb", 40, CallConfiguration.FrameNoneScopeNone, "qevent");
    }
    
    @JRubyMethod(name = "action_performed", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__6$RUBY$action_performed(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(24).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(25).call(threadContext, rubyObject, rubyObject));
    }
    
    @JRubyMethod(name = "language", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__7$RUBY$language(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(26).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(27).call(threadContext, rubyObject, rubyObject));
    }
    
    @JRubyMethod(name = "show_help_for_current_question", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__8$RUBY$show_help_for_current_question(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        IRubyObject message = context.nil;
        message = (file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(28).call(context, rubyObject, rubyObject).isTrue() ? file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(29).call(context, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(30).call(context, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(31).call(context, rubyObject, rubyObject)), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(32).call(context, rubyObject, rubyObject)) : file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(33).call(context, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(34).call(context, rubyObject, rubyObject)));
        message = file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(35).call(context, rubyObject, rubyObject, message);
        return file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(36).call(context, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstant2(context, "JOptionPane"), RuntimeHelpers.constructObjectArray(file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(37).call(context, rubyObject, rubyObject), message, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(38).call(context, rubyObject, rubyObject), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstantFrom4(RuntimeHelpers.checkIsModule(file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstant3(context, "JOptionPane")), context, "PLAIN_MESSAGE")));
    }
    
    @JRubyMethod(name = "buttons", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__9$RUBY$buttons(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@buttons") ? file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getByteList(12) : null) == null) {
            rubyObject = file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.setVariable0(threadContext.runtime, "@buttons", object, RubyHash.newHash(threadContext.runtime));
        }
        else if (!(rubyObject = file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getVariable0(threadContext.runtime, "@buttons", object)).isTrue()) {
            rubyObject = file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.setVariable1(threadContext.runtime, "@buttons", object, RubyHash.newHash(threadContext.runtime));
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "panels", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__10$RUBY$panels(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@panels") ? file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getByteList(12) : null) == null) {
            rubyObject = file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.setVariable2(threadContext.runtime, "@panels", object, RubyHash.newHash(threadContext.runtime));
        }
        else if (!(rubyObject = file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getVariable1(threadContext.runtime, "@panels", object)).isTrue()) {
            rubyObject = file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.setVariable3(threadContext.runtime, "@panels", object, RubyHash.newHash(threadContext.runtime));
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "prepare_buttons", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__11$RUBY$prepare_buttons(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(40).callIter(threadContext, self, RuntimeHelpers.constructRubyArray(threadContext.runtime, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol3(threadContext.runtime, "help"), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol2(threadContext.runtime, "previous"), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol1(threadContext.runtime, "next"), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol0(threadContext.runtime, "done")), RuntimeHelpers.createBlock(threadContext, self, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getBlockBody4(threadContext, "block_4$RUBY$prepare_buttons,1,sym,false,2,./lib//lister/runner/questionnaire/questionnaire.rb,76,true")));
    }
    
    public static IRubyObject block_4$RUBY$prepare_buttons(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    28: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_2        
        //    34: aload_0        
        //    35: bipush          42
        //    37: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    40: aload_1        
        //    41: aload_2        
        //    42: aload_1        
        //    43: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    46: ldc_w           20
        //    49: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    52: aload_0        
        //    53: aload_1        
        //    54: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    57: bipush          13
        //    59: bipush          32
        //    61: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //    64: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //    67: aload           sym
        //    69: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //    74: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //    77: aload_0        
        //    78: aload_1        
        //    79: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    82: bipush          14
        //    84: bipush          32
        //    86: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //    89: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //    92: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    95: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    98: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     74      9     sym   Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "prepare_previous_button", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__12$RUBY$prepare_previous_button(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        IRubyObject button = threadContext.nil;
        button = file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(43).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstant5(threadContext, "JButton"), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(44).call(threadContext, rubyObject, rubyObject));
        file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(45).call(threadContext, rubyObject, button, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(46).call(threadContext, rubyObject, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol2(threadContext.runtime, "previous")));
        final IRubyObject call = file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(47).call(threadContext, rubyObject, rubyObject);
        return RuntimeHelpers.doAttrAsgn(call, RuntimeHelpers.selectAttrAsgnCallSite(call, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(48), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(49)), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol2(threadContext.runtime, "previous"), button, threadContext, rubyObject);
    }
    
    @JRubyMethod(name = "prepare_help_button", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__13$RUBY$prepare_help_button(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        IRubyObject button = threadContext.nil;
        button = file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(50).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstant6(threadContext, "JButton"), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(51).call(threadContext, rubyObject, rubyObject));
        file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(52).call(threadContext, rubyObject, button, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(53).call(threadContext, rubyObject, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol3(threadContext.runtime, "help")));
        final IRubyObject call = file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(54).call(threadContext, rubyObject, rubyObject);
        return RuntimeHelpers.doAttrAsgn(call, RuntimeHelpers.selectAttrAsgnCallSite(call, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(55), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(56)), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol3(threadContext.runtime, "help"), button, threadContext, rubyObject);
    }
    
    @JRubyMethod(name = "prepare_next_button", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__14$RUBY$prepare_next_button(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        IRubyObject button = threadContext.nil;
        button = file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(57).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstant7(threadContext, "JButton"), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(58).call(threadContext, rubyObject, rubyObject));
        file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(59).call(threadContext, rubyObject, button, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(60).call(threadContext, rubyObject, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol1(threadContext.runtime, "next")));
        final IRubyObject call = file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(61).call(threadContext, rubyObject, rubyObject);
        return RuntimeHelpers.doAttrAsgn(call, RuntimeHelpers.selectAttrAsgnCallSite(call, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(62), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(63)), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol1(threadContext.runtime, "next"), button, threadContext, rubyObject);
    }
    
    @JRubyMethod(name = "prepare_done_button", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__15$RUBY$prepare_done_button(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        IRubyObject button = threadContext.nil;
        button = file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(64).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstant8(threadContext, "JButton"), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(65).call(threadContext, rubyObject, rubyObject));
        file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(66).call(threadContext, rubyObject, button, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(67).call(threadContext, rubyObject, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol0(threadContext.runtime, "done")));
        final IRubyObject call = file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(68).call(threadContext, rubyObject, rubyObject);
        return RuntimeHelpers.doAttrAsgn(call, RuntimeHelpers.selectAttrAsgnCallSite(call, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(69), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(70)), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol0(threadContext.runtime, "done"), button, threadContext, rubyObject);
    }
    
    @JRubyMethod(name = "count=", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__16$RUBY$count_equal_(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext context, final IRubyObject self, final IRubyObject value, final Block block) {
        file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.setVariable4(context.runtime, "@count", self, value);
        final IRubyObject call = file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(71).call(context, self, self);
        return RuntimeHelpers.doAttrAsgn(call, RuntimeHelpers.selectAttrAsgnCallSite(call, self, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(72), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(73)), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(74).call(context, self, self), context, self);
    }
    
    @JRubyMethod(name = "count_string", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__17$RUBY$count_string(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        IRubyObject rubyObject2;
        if (!(rubyObject2 = file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(75).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(76).call(threadContext, rubyObject, rubyObject), 1L)).isTrue()) {
            rubyObject2 = file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(77).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(78).call(threadContext, rubyObject, rubyObject), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(79).call(threadContext, rubyObject, rubyObject));
        }
        return rubyObject2.isTrue() ? file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getString(threadContext.runtime, 15, 32) : RubyString.newStringLight(threadContext.runtime, 20).append(file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(80).call(threadContext, rubyObject, rubyObject).asString()).append(file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getString(threadContext.runtime, 16, 32)).append(file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(81).call(threadContext, rubyObject, rubyObject).asString());
    }
    
    @JRubyMethod(name = "prepare_count_label", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__18$RUBY$prepare_count_label(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext context, final IRubyObject object, final Block block) {
        return file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.setVariable5(context.runtime, "@count_label", object, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(82).call(context, object, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstant9(context, "JLabel"), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(83).call(context, object, object)));
    }
    
    @JRubyMethod(name = "place_panels", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__19$RUBY$place_panels(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(84).call(threadContext, rubyObject, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(85).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(86).call(threadContext, rubyObject, rubyObject), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol8(threadContext.runtime, "buttons")), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstantFrom(RuntimeHelpers.checkIsModule(file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstant(threadContext, "BorderLayout", 10)), threadContext, "SOUTH", 11));
    }
    
    @JRubyMethod(name = "place_count_label", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__20$RUBY$place_count_label(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(87).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(88).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(89).call(threadContext, rubyObject, rubyObject), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol8(threadContext.runtime, "buttons")), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(90).call(threadContext, rubyObject, rubyObject));
    }
    
    @JRubyMethod(name = "place_next_button", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__21$RUBY$place_next_button(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(91).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(92).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(93).call(threadContext, rubyObject, rubyObject), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol8(threadContext.runtime, "buttons")), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(94).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(95).call(threadContext, rubyObject, rubyObject), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol1(threadContext.runtime, "next")));
    }
    
    @JRubyMethod(name = "place_help_button", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__22$RUBY$place_help_button(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(96).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(97).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(98).call(threadContext, rubyObject, rubyObject), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol8(threadContext.runtime, "buttons")), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(99).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(100).call(threadContext, rubyObject, rubyObject), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol3(threadContext.runtime, "help")));
    }
    
    @JRubyMethod(name = "place_previous_button", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__23$RUBY$place_previous_button(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(101).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(102).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(103).call(threadContext, rubyObject, rubyObject), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol8(threadContext.runtime, "buttons")), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(104).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(105).call(threadContext, rubyObject, rubyObject), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol2(threadContext.runtime, "previous")));
        return file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(106).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(107).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(108).call(threadContext, rubyObject, rubyObject), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol2(threadContext.runtime, "previous")));
    }
    
    @JRubyMethod(name = "prepare_panels", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__24$RUBY$prepare_panels(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        final IRubyObject call = file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(109).call(threadContext, rubyObject, rubyObject);
        return RuntimeHelpers.doAttrAsgn(call, RuntimeHelpers.selectAttrAsgnCallSite(call, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(110), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(111)), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol8(threadContext.runtime, "buttons"), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(112).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstant(threadContext, "JPanel", 12)), threadContext, rubyObject);
    }
    
    @JRubyMethod(name = "interpret", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__25$RUBY$interpret(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        return file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(113).call(threadContext, object, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getVariable2(threadContext.runtime, "@interpret", object), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol(threadContext.runtime, 14, "call")).isTrue() ? file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(114).call(threadContext, object, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getVariable3(threadContext.runtime, "@interpret", object)) : file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getVariable4(threadContext.runtime, "@interpret", object);
    }
    
    @JRubyMethod(name = "word", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__26$RUBY$word(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    15: bipush          115
        //    17: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    20: aload_1        
        //    21: aload_2        
        //    22: aload_0        
        //    23: bipush          116
        //    25: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
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
    public static IRubyObject method__27$RUBY$sentence(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(117).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(118).call(threadContext, rubyObject, rubyObject), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(119).call(threadContext, rubyObject, rubyObject, rubyObject2));
    }
    
    @JRubyMethod(name = "help_sentence", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__28$RUBY$help_sentence(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(120).call(threadContext, rubyObject, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol3(threadContext.runtime, "help"));
    }
    
    @JRubyMethod(name = "next_sentence", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__29$RUBY$next_sentence(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(121).call(threadContext, rubyObject, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol1(threadContext.runtime, "next"));
    }
    
    @JRubyMethod(name = "previous_sentence", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__30$RUBY$previous_sentence(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(122).call(threadContext, rubyObject, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol2(threadContext.runtime, "previous"));
    }
    
    @JRubyMethod(name = "done_sentence", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__31$RUBY$done_sentence(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(123).call(threadContext, rubyObject, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol0(threadContext.runtime, "done"));
    }
    
    @JRubyMethod(name = "initialize", frame = true, required = 2, optional = 2, rest = -1)
    public static IRubyObject method__32$RUBY$initialize(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     4: dup            
        //     5: astore          11
        //     7: astore          12
        //     9: aload_1        
        //    10: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    13: aload_3        
        //    14: iconst_2       
        //    15: iconst_4       
        //    16: invokestatic    org/jruby/runtime/Arity.raiseArgumentError:(Lorg/jruby/Ruby;[Lorg/jruby/runtime/builtin/IRubyObject;II)V
        //    19: aload_3        
        //    20: iconst_0       
        //    21: aaload         
        //    22: astore          9
        //    24: aload_3        
        //    25: iconst_1       
        //    26: aaload         
        //    27: astore          10
        //    29: aload_3        
        //    30: iconst_2       
        //    31: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.elementOrNull:([Lorg/jruby/runtime/builtin/IRubyObject;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    34: dup            
        //    35: ifnull          54
        //    38: astore          11
        //    40: aload_3        
        //    41: iconst_3       
        //    42: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.elementOrNull:([Lorg/jruby/runtime/builtin/IRubyObject;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: dup            
        //    46: ifnull          63
        //    49: astore          12
        //    51: goto            70
        //    54: aload_1        
        //    55: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    58: invokestatic    org/jruby/RubyHash.newHash:(Lorg/jruby/Ruby;)Lorg/jruby/RubyHash;
        //    61: astore          11
        //    63: aload_1        
        //    64: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    67: astore          12
        //    69: pop            
        //    70: aload_0        
        //    71: bipush          124
        //    73: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    76: aload_1        
        //    77: aload_2        
        //    78: aload_2        
        //    79: aload_0        
        //    80: bipush          125
        //    82: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    85: aload_1        
        //    86: aload_2        
        //    87: aload_0        
        //    88: aload_1        
        //    89: ldc_w           "BorderLayout"
        //    92: bipush          13
        //    94: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    97: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   100: aload           4
        //   102: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   105: pop            
        //   106: aload_2        
        //   107: dup            
        //   108: aload_2        
        //   109: aload_0        
        //   110: bipush          126
        //   112: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   115: aload_0        
        //   116: bipush          127
        //   118: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   121: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   124: aload_0        
        //   125: sipush          128
        //   128: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   131: aload_1        
        //   132: aload_2        
        //   133: aload_0        
        //   134: aload_1        
        //   135: ldc_w           "Dimension"
        //   138: bipush          14
        //   140: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   143: aload_0        
        //   144: aload_1        
        //   145: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   148: sipush          600
        //   151: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getFixnum0:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   154: aload_0        
        //   155: aload_1        
        //   156: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   159: sipush          500
        //   162: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getFixnum1:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   165: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   168: aload_1        
        //   169: aload_2        
        //   170: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   173: pop            
        //   174: aload_0        
        //   175: aload_1        
        //   176: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   179: ldc_w           "@done_questions"
        //   182: aload_2        
        //   183: aload_1        
        //   184: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   187: invokevirtual   org/jruby/Ruby.newArray:()Lorg/jruby/RubyArray;
        //   190: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.setVariable6:(Lorg/jruby/Ruby;Ljava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   193: pop            
        //   194: aload_0        
        //   195: aload_1        
        //   196: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   199: ldc_w           "@remaining_questions"
        //   202: aload_2        
        //   203: aload_0        
        //   204: sipush          129
        //   207: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   210: aload_1        
        //   211: aload_2        
        //   212: aload           questions
        //   214: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   217: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.setVariable7:(Lorg/jruby/Ruby;Ljava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   220: pop            
        //   221: aload_0        
        //   222: aload_1        
        //   223: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   226: ldc_w           "@greetings"
        //   229: aload_2        
        //   230: aload           greetings
        //   232: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.setVariable8:(Lorg/jruby/Ruby;Ljava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   235: pop            
        //   236: aload_0        
        //   237: aload_1        
        //   238: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   241: ldc_w           "@interpret"
        //   244: aload_2        
        //   245: aload           interpret
        //   247: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.setVariable9:(Lorg/jruby/Ruby;Ljava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   250: pop            
        //   251: aload_0        
        //   252: aload_1        
        //   253: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   256: bipush          10
        //   258: ldc_w           "@words"
        //   261: aload_2        
        //   262: aload           words
        //   264: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.setVariable:(Lorg/jruby/Ruby;ILjava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   267: pop            
        //   268: aload_0        
        //   269: aload_1        
        //   270: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   273: bipush          11
        //   275: ldc_w           "@count"
        //   278: aload_2        
        //   279: aload_1        
        //   280: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   283: invokestatic    org/jruby/RubyFixnum.zero:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   286: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.setVariable:(Lorg/jruby/Ruby;ILjava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   289: pop            
        //   290: aload_0        
        //   291: aload_1        
        //   292: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   295: bipush          12
        //   297: ldc_w           "@total_count"
        //   300: aload_2        
        //   301: aload_0        
        //   302: sipush          130
        //   305: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   308: aload_1        
        //   309: aload_2        
        //   310: aload           questions
        //   312: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   315: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.setVariable:(Lorg/jruby/Ruby;ILjava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   318: pop            
        //   319: aload_0        
        //   320: aload_1        
        //   321: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   324: bipush          13
        //   326: ldc_w           "@waiting_notifiers"
        //   329: aload_2        
        //   330: aload_1        
        //   331: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   334: invokevirtual   org/jruby/Ruby.newArray:()Lorg/jruby/RubyArray;
        //   337: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.setVariable:(Lorg/jruby/Ruby;ILjava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   340: pop            
        //   341: aload_0        
        //   342: sipush          131
        //   345: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   348: aload_1        
        //   349: aload_2        
        //   350: aload_2        
        //   351: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   354: pop            
        //   355: aload_0        
        //   356: sipush          132
        //   359: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   362: aload_1        
        //   363: aload_2        
        //   364: aload_2        
        //   365: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   368: pop            
        //   369: aload_0        
        //   370: sipush          133
        //   373: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   376: aload_1        
        //   377: aload_2        
        //   378: aload_2        
        //   379: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   382: pop            
        //   383: aload_0        
        //   384: sipush          134
        //   387: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   390: aload_1        
        //   391: aload_2        
        //   392: aload_2        
        //   393: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   396: pop            
        //   397: aload_0        
        //   398: sipush          135
        //   401: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   404: aload_1        
        //   405: aload_2        
        //   406: aload_2        
        //   407: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   410: pop            
        //   411: aload_0        
        //   412: sipush          136
        //   415: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   418: aload_1        
        //   419: aload_2        
        //   420: aload_2        
        //   421: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   424: pop            
        //   425: aload_0        
        //   426: sipush          137
        //   429: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   432: aload_1        
        //   433: aload_2        
        //   434: aload_2        
        //   435: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   438: pop            
        //   439: aload_0        
        //   440: sipush          138
        //   443: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   446: aload_1        
        //   447: aload_2        
        //   448: aload_2        
        //   449: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   452: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name       Signature
        //  -----  ------  ----  ---------  ---------------------------------------
        //  70     383     9     questions  Lorg/jruby/runtime/builtin/IRubyObject;
        //  70     383     10    interpret  Lorg/jruby/runtime/builtin/IRubyObject;
        //  70     383     11    words      Lorg/jruby/runtime/builtin/IRubyObject;
        //  70     383     12    greetings  Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "store_question", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__33$RUBY$store_question(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(139).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(140).call(threadContext, rubyObject, rubyObject), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(141).call(threadContext, rubyObject, rubyObject));
        return file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(142).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(143).call(threadContext, rubyObject, rubyObject)).isTrue() ? threadContext.nil : file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(144).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(145).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(146).call(threadContext, rubyObject, rubyObject), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol2(threadContext.runtime, "previous")));
    }
    
    @JRubyMethod(name = "results", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__34$RUBY$results(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext context, final IRubyObject self, final Block block) {
        final DynamicScope locals = context.getCurrentScope();
        locals.setValueZeroDepthZero(file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(147).call(context, self, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstant(context, "Hash", 15)));
        file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(148).callIter(context, self, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(149).call(context, self, self), RuntimeHelpers.createBlock(context, self, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getBlockBody5(context, "block_5$RUBY$results,1,q,false,2,./lib//lister/runner/questionnaire/questionnaire.rb,207,true")));
        return locals.getValueZeroDepthZeroOrNil(context.nil);
    }
    
    public static IRubyObject block_5$RUBY$results(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    40: sipush          150
        //    43: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    46: aload_0        
        //    47: sipush          151
        //    50: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    53: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //    56: aload_0        
        //    57: sipush          152
        //    60: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    63: aload_1        
        //    64: aload_2        
        //    65: aload_0        
        //    66: sipush          153
        //    69: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    72: aload_1        
        //    73: aload_2        
        //    74: aload           q
        //    76: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    79: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    82: aload_0        
        //    83: sipush          154
        //    86: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    89: aload_1        
        //    90: aload_2        
        //    91: aload           q
        //    93: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    96: aload_1        
        //    97: aload_2        
        //    98: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   101: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     77      9     q     Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "start", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__35$RUBY$start(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(155).call(threadContext, rubyObject, rubyObject);
    }
    
    @JRubyMethod(name = "question_for_description", frame = true, required = 2, optional = 0, rest = 2)
    public static IRubyObject method__36$RUBY$question_for_description(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4) {
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
        //    11: iconst_2       
        //    12: iconst_m1      
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
        //    28: aaload         
        //    29: aload           5
        //    31: swap           
        //    32: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    35: pop            
        //    36: aload_3        
        //    37: aload_1        
        //    38: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    41: iconst_2       
        //    42: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createSubarray:([Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;I)Lorg/jruby/RubyArray;
        //    45: aload           5
        //    47: swap           
        //    48: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    51: pop            
        //    52: aload_0        
        //    53: sipush          156
        //    56: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    59: aload_1        
        //    60: aload_2        
        //    61: aload           locals
        //    63: aload_1        
        //    64: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    67: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    70: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    73: aload_1        
        //    74: invokevirtual   org/jruby/runtime/ThreadContext.pollThreadEvents:()V
        //    77: astore          9
        //    79: aload           9
        //    81: instanceof      Lorg/jruby/RubySymbol;
        //    84: ifeq            144
        //    87: aload           9
        //    89: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.isFastSwitchableSymbol:(Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //    92: ifeq            144
        //    95: aload           9
        //    97: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.getFastSwitchSymbol:(Lorg/jruby/runtime/builtin/IRubyObject;)I
        //   100: lookupswitch {
        //          -1537391207: 484
        //          3322014: 278
        //          108270587: 175
        //          110115790: 381
        //          default: 556
        //        }
        //   144: aload_0        
        //   145: sipush          157
        //   148: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   151: aload_1        
        //   152: aload_2        
        //   153: aload           9
        //   155: aload_0        
        //   156: aload_1        
        //   157: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   160: ldc_w           15
        //   163: ldc_w           "radio"
        //   166: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   169: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //   172: ifeq            247
        //   175: aload_0        
        //   176: sipush          158
        //   179: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   182: aload_1        
        //   183: aload_2        
        //   184: aload_0        
        //   185: aload_1        
        //   186: ldc_w           "RadioQuestion"
        //   189: bipush          16
        //   191: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   194: aload           locals
        //   196: aload_1        
        //   197: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   200: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   203: aload           locals
        //   205: aload_1        
        //   206: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   209: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   212: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructObjectArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //   215: aload_1        
        //   216: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   219: swap           
        //   220: invokestatic    org/jruby/RubyArray.newArrayNoCopyLight:(Lorg/jruby/Ruby;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   223: aload           locals
        //   225: aload_1        
        //   226: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   229: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   232: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.splatValue:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   235: invokevirtual   org/jruby/RubyArray.concat:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   238: invokestatic    org/jruby/ast/util/ArgsUtil.convertToJavaArray:(Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //   241: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   244: goto            633
        //   247: aload_0        
        //   248: sipush          159
        //   251: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   254: aload_1        
        //   255: aload_2        
        //   256: aload           9
        //   258: aload_0        
        //   259: aload_1        
        //   260: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   263: ldc_w           16
        //   266: ldc_w           "list"
        //   269: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   272: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //   275: ifeq            350
        //   278: aload_0        
        //   279: sipush          160
        //   282: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   285: aload_1        
        //   286: aload_2        
        //   287: aload_0        
        //   288: aload_1        
        //   289: ldc_w           "CheckBoxQuestion"
        //   292: bipush          17
        //   294: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   297: aload           locals
        //   299: aload_1        
        //   300: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   303: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   306: aload           locals
        //   308: aload_1        
        //   309: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   312: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   315: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructObjectArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //   318: aload_1        
        //   319: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   322: swap           
        //   323: invokestatic    org/jruby/RubyArray.newArrayNoCopyLight:(Lorg/jruby/Ruby;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   326: aload           locals
        //   328: aload_1        
        //   329: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   332: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   335: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.splatValue:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   338: invokevirtual   org/jruby/RubyArray.concat:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   341: invokestatic    org/jruby/ast/util/ArgsUtil.convertToJavaArray:(Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //   344: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   347: goto            633
        //   350: aload_0        
        //   351: sipush          161
        //   354: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   357: aload_1        
        //   358: aload_2        
        //   359: aload           9
        //   361: aload_0        
        //   362: aload_1        
        //   363: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   366: ldc_w           17
        //   369: ldc_w           "table"
        //   372: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   375: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //   378: ifeq            453
        //   381: aload_0        
        //   382: sipush          162
        //   385: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   388: aload_1        
        //   389: aload_2        
        //   390: aload_0        
        //   391: aload_1        
        //   392: ldc_w           "TableQuestion"
        //   395: bipush          18
        //   397: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   400: aload           locals
        //   402: aload_1        
        //   403: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   406: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   409: aload           locals
        //   411: aload_1        
        //   412: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   415: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   418: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructObjectArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //   421: aload_1        
        //   422: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   425: swap           
        //   426: invokestatic    org/jruby/RubyArray.newArrayNoCopyLight:(Lorg/jruby/Ruby;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   429: aload           locals
        //   431: aload_1        
        //   432: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   435: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   438: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.splatValue:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   441: invokevirtual   org/jruby/RubyArray.concat:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   444: invokestatic    org/jruby/ast/util/ArgsUtil.convertToJavaArray:(Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //   447: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   450: goto            633
        //   453: aload_0        
        //   454: sipush          163
        //   457: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   460: aload_1        
        //   461: aload_2        
        //   462: aload           9
        //   464: aload_0        
        //   465: aload_1        
        //   466: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   469: ldc_w           18
        //   472: ldc_w           "freetext"
        //   475: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   478: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //   481: ifeq            556
        //   484: aload_0        
        //   485: sipush          164
        //   488: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   491: aload_1        
        //   492: aload_2        
        //   493: aload_0        
        //   494: aload_1        
        //   495: ldc_w           "FreeTextQuestion"
        //   498: bipush          19
        //   500: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   503: aload           locals
        //   505: aload_1        
        //   506: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   509: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   512: aload           locals
        //   514: aload_1        
        //   515: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   518: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   521: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructObjectArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //   524: aload_1        
        //   525: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   528: swap           
        //   529: invokestatic    org/jruby/RubyArray.newArrayNoCopyLight:(Lorg/jruby/Ruby;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   532: aload           locals
        //   534: aload_1        
        //   535: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   538: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   541: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.splatValue:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   544: invokevirtual   org/jruby/RubyArray.concat:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   547: invokestatic    org/jruby/ast/util/ArgsUtil.convertToJavaArray:(Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //   550: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   553: goto            633
        //   556: aload_0        
        //   557: sipush          165
        //   560: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   563: aload_1        
        //   564: aload_2        
        //   565: aload_2        
        //   566: aload_0        
        //   567: aload_1        
        //   568: ldc_w           "ArgumentError"
        //   571: bipush          20
        //   573: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   576: aload_1        
        //   577: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   580: ldc_w           20
        //   583: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   586: aload_0        
        //   587: aload_1        
        //   588: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   591: bipush          17
        //   593: bipush          32
        //   595: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   598: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   601: aload_0        
        //   602: sipush          166
        //   605: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   608: aload_1        
        //   609: aload_2        
        //   610: aload           locals
        //   612: aload_1        
        //   613: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   616: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   619: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   622: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   627: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   630: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   633: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  52     582     5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "current=", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__37$RUBY$current_equal_(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    30: sipush          167
        //    33: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    36: aload_1        
        //    37: aload_2        
        //    38: aload           9
        //    40: aload_0        
        //    41: aload_1        
        //    42: ldc_w           "Question"
        //    45: bipush          21
        //    47: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //    53: ifeq            82
        //    56: aload_0        
        //    57: aload_1        
        //    58: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    61: bipush          14
        //    63: ldc_w           "@current"
        //    66: aload_2        
        //    67: aload           locals
        //    69: aload_1        
        //    70: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    73: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    76: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.setVariable:(Lorg/jruby/Ruby;ILjava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    79: goto            438
        //    82: aload_0        
        //    83: sipush          168
        //    86: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    89: aload_1        
        //    90: aload_2        
        //    91: aload           9
        //    93: aload_0        
        //    94: aload_1        
        //    95: ldc_w           "JLabel"
        //    98: bipush          22
        //   100: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   103: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //   106: ifeq            135
        //   109: aload_0        
        //   110: aload_1        
        //   111: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   114: bipush          15
        //   116: ldc_w           "@current"
        //   119: aload_2        
        //   120: aload           locals
        //   122: aload_1        
        //   123: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   126: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   129: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.setVariable:(Lorg/jruby/Ruby;ILjava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   132: goto            438
        //   135: aload_0        
        //   136: sipush          169
        //   139: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   142: aload_1        
        //   143: aload_2        
        //   144: aload           9
        //   146: aload_0        
        //   147: aload_1        
        //   148: ldc_w           "JPanel"
        //   151: bipush          23
        //   153: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   156: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //   159: ifeq            188
        //   162: aload_0        
        //   163: aload_1        
        //   164: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   167: bipush          16
        //   169: ldc_w           "@current"
        //   172: aload_2        
        //   173: aload           locals
        //   175: aload_1        
        //   176: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   179: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   182: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.setVariable:(Lorg/jruby/Ruby;ILjava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   185: goto            438
        //   188: aload_0        
        //   189: sipush          170
        //   192: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   195: aload_1        
        //   196: aload_2        
        //   197: aload           9
        //   199: aload_1        
        //   200: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   203: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //   206: ifeq            230
        //   209: aload_0        
        //   210: aload_1        
        //   211: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   214: bipush          17
        //   216: ldc_w           "@current"
        //   219: aload_2        
        //   220: aload_1        
        //   221: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   224: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.setVariable:(Lorg/jruby/Ruby;ILjava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   227: goto            438
        //   230: aload_0        
        //   231: sipush          171
        //   234: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   237: aload_1        
        //   238: aload_2        
        //   239: aload           9
        //   241: aload_0        
        //   242: aload_1        
        //   243: ldc             "Lister"
        //   245: bipush          24
        //   247: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   250: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   253: aload_0        
        //   254: swap           
        //   255: aload_1        
        //   256: ldc_w           "Measurements"
        //   259: bipush          25
        //   261: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   264: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   267: aload_0        
        //   268: swap           
        //   269: aload_1        
        //   270: ldc_w           "Poll"
        //   273: bipush          26
        //   275: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   278: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   281: aload_0        
        //   282: swap           
        //   283: aload_1        
        //   284: ldc_w           "QuestionDescription"
        //   287: bipush          27
        //   289: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   292: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //   295: ifeq            373
        //   298: aload_2        
        //   299: dup            
        //   300: aload_2        
        //   301: aload_0        
        //   302: sipush          172
        //   305: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   308: aload_0        
        //   309: sipush          173
        //   312: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   315: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   318: aload_0        
        //   319: sipush          174
        //   322: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   325: aload_1        
        //   326: aload_2        
        //   327: aload_0        
        //   328: sipush          175
        //   331: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   334: aload_1        
        //   335: aload_2        
        //   336: aload_2        
        //   337: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   340: aload           locals
        //   342: aload_1        
        //   343: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   346: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   349: aload_0        
        //   350: sipush          176
        //   353: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   356: aload_1        
        //   357: aload_2        
        //   358: aload_2        
        //   359: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   362: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   365: aload_1        
        //   366: aload_2        
        //   367: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   370: goto            438
        //   373: aload_0        
        //   374: sipush          177
        //   377: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   380: aload_1        
        //   381: aload_2        
        //   382: aload_2        
        //   383: aload_0        
        //   384: aload_1        
        //   385: ldc_w           "ArgumentError"
        //   388: bipush          28
        //   390: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   393: aload_1        
        //   394: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   397: ldc_w           20
        //   400: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   403: aload_0        
        //   404: aload_1        
        //   405: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   408: bipush          18
        //   410: bipush          32
        //   412: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   415: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   418: aload           locals
        //   420: aload_1        
        //   421: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   424: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   427: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   432: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   435: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   438: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     425     5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "previous_question", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__38$RUBY$previous_question(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(178).call(threadContext, rubyObject, rubyObject);
        return file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(179).call(threadContext, rubyObject, rubyObject).isTrue() ? file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(180).call(threadContext, rubyObject, rubyObject) : threadContext.nil;
    }
    
    @JRubyMethod(name = "unloop_questions", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__39$RUBY$unloop_questions(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        final DynamicScope locals = context.getCurrentScope();
        RuntimeHelpers.opAsgnWithMethod(context, rubyObject, rubyObject, RubyFixnum.one(context.runtime), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(181), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(182), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(183));
        context.pollThreadEvents();
        locals.setValueZeroDepthZero(file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(184).call(context, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(185).call(context, rubyObject, rubyObject)));
        if (file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(186).call(context, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(187).call(context, rubyObject, rubyObject)).isTrue()) {
            file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(188).call(context, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(189).call(context, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(190).call(context, rubyObject, rubyObject), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol2(context.runtime, "previous")));
        }
        file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(191).call(context, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(192).call(context, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(193).call(context, rubyObject, rubyObject), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol8(context.runtime, "buttons")));
        IRubyObject rubyObject2;
        if (locals.getValueZeroDepthZeroOrNil(context.nil).isTrue()) {
            if (file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(194).call(context, rubyObject, rubyObject).isTrue()) {
                file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(195).call(context, rubyObject, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(196).call(context, rubyObject, rubyObject));
            }
            file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(197).call(context, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(198).call(context, rubyObject, rubyObject), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(199).call(context, rubyObject, rubyObject));
            RuntimeHelpers.doAttrAsgn(rubyObject, RuntimeHelpers.selectAttrAsgnCallSite(rubyObject, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(200), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(201)), locals.getValueZeroDepthZeroOrNil(context.nil), context, rubyObject);
            if (file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(202).call(context, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(203).call(context, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(204).call(context, rubyObject, rubyObject), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol0(context.runtime, "done"))).isTrue()) {
                file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(205).call(context, rubyObject, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(206).call(context, rubyObject, rubyObject));
                file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(207).call(context, rubyObject, rubyObject);
            }
            rubyObject2 = file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(208).call(context, rubyObject, rubyObject);
        }
        else {
            rubyObject2 = file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(209).call(context, rubyObject, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstant(context, "RuntimeError", 29), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getString(context.runtime, 19, 32));
        }
        return rubyObject2;
    }
    
    @JRubyMethod(name = "awake_notifiers", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__40$RUBY$awake_notifiers(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(211).callIter(threadContext, self, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(212).call(threadContext, self, self), RuntimeHelpers.createBlock(threadContext, self, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getBlockBody6(threadContext, "block_6$RUBY$awake_notifiers,1,n,false,2,./lib//lister/runner/questionnaire/questionnaire.rb,273,true")));
    }
    
    public static IRubyObject block_6$RUBY$awake_notifiers(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: sipush          213
        //    29: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    32: aload_1        
        //    33: aload_2        
        //    34: aload_0        
        //    35: sipush          214
        //    38: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload           n
        //    45: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    48: aload_0        
        //    49: aload_1        
        //    50: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    53: ldc_w           20
        //    56: ldc_w           "wakeup"
        //    59: invokevirtual   ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    62: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    65: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     41      9     n     Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "claim_finished", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__41$RUBY$claim_finished(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        IRubyObject rubyObject2;
        if (file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(215).call(threadContext, rubyObject, rubyObject).isTrue()) {
            file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(216).call(threadContext, rubyObject, rubyObject);
            rubyObject2 = file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(217).call(threadContext, rubyObject, rubyObject);
        }
        else {
            rubyObject2 = file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(218).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(219).call(threadContext, rubyObject, rubyObject));
        }
        return rubyObject2;
    }
    
    @JRubyMethod(name = "verify_current_question", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__42$RUBY$verify_current_question(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(220).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(221).call(threadContext, rubyObject, rubyObject));
    }
    
    @JRubyMethod(name = "should_progress?", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__43$RUBY$should_progress_p_(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        IRubyObject rubyObject2;
        if (file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(222).call(threadContext, rubyObject, rubyObject).isTrue()) {
            rubyObject2 = file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(223).call(threadContext, rubyObject, rubyObject);
        }
        else {
            rubyObject2 = threadContext.runtime.getTrue();
            threadContext.pollThreadEvents();
        }
        return rubyObject2;
    }
    
    @JRubyMethod(name = "next_question", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__44$RUBY$next_question(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        if (file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(224).call(threadContext, rubyObject, rubyObject).isTrue()) {
            if (!file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(225).call(threadContext, rubyObject, rubyObject).isTrue()) {
                file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(226).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(227).call(threadContext, rubyObject, rubyObject));
                return threadContext.nil;
            }
            file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(228).call(threadContext, rubyObject, rubyObject);
        }
        file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(229).call(threadContext, rubyObject, rubyObject);
        return file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(230).call(threadContext, rubyObject, rubyObject).isTrue() ? file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(231).call(threadContext, rubyObject, rubyObject) : threadContext.nil;
    }
    
    @JRubyMethod(name = "loop_questions", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__45$RUBY$loop_questions(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        IRubyObject obj = context.nil;
        RuntimeHelpers.opAsgnWithMethod(context, rubyObject, rubyObject, RubyFixnum.one(context.runtime), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(232), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(233), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(234));
        context.pollThreadEvents();
        obj = context.nil;
        if (file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(235).call(context, rubyObject, rubyObject).isTrue()) {
            file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(236).call(context, rubyObject, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(237).call(context, rubyObject, rubyObject));
        }
        IRubyObject call;
        if ((obj = file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(238).call(context, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(239).call(context, rubyObject, rubyObject))).isTrue()) {
            RuntimeHelpers.doAttrAsgn(rubyObject, RuntimeHelpers.selectAttrAsgnCallSite(rubyObject, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(240), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(241)), obj, context, rubyObject);
            IRubyObject rubyObject2;
            if ((rubyObject2 = file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(242).call(context, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(243).call(context, rubyObject, rubyObject))).isTrue()) {
                rubyObject2 = file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(244).call(context, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(245).call(context, rubyObject, rubyObject));
            }
            call = (rubyObject2.isTrue() ? file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(246).call(context, rubyObject, rubyObject) : context.nil);
        }
        else {
            RuntimeHelpers.doAttrAsgn(rubyObject, RuntimeHelpers.selectAttrAsgnCallSite(rubyObject, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(247), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(248)), context.nil, context, rubyObject);
            if (file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(249).call(context, rubyObject, rubyObject).isTrue()) {
                file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(250).call(context, rubyObject, rubyObject);
            }
            file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(251).call(context, rubyObject, rubyObject);
            file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(252).call(context, rubyObject, rubyObject);
            call = file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(253).call(context, rubyObject, rubyObject);
        }
        return call;
    }
    
    @JRubyMethod(name = "display_current", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__46$RUBY$display_current(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(254).call(threadContext, rubyObject, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(255).call(threadContext, rubyObject, rubyObject), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstantFrom(RuntimeHelpers.checkIsModule(file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstant(threadContext, "BorderLayout", 30)), threadContext, "CENTER", 31));
        if (file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(256).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(257).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(258).call(threadContext, rubyObject, rubyObject)), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(259).call(threadContext, rubyObject, rubyObject)).isTrue()) {
            file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(260).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(261).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(262).call(threadContext, rubyObject, rubyObject), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol3(threadContext.runtime, "help")));
        }
        else {
            file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(263).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(264).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(265).call(threadContext, rubyObject, rubyObject), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol3(threadContext.runtime, "help")));
        }
        file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(266).call(threadContext, rubyObject, rubyObject);
        return file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(267).call(threadContext, rubyObject, rubyObject);
    }
    
    @JRubyMethod(name = "replace_next_button_by_done", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__47$RUBY$replace_next_button_by_done(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(268).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(269).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(270).call(threadContext, rubyObject, rubyObject), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol8(threadContext.runtime, "buttons")), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(271).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(272).call(threadContext, rubyObject, rubyObject), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol1(threadContext.runtime, "next")));
        return file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(273).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(274).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(275).call(threadContext, rubyObject, rubyObject), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol8(threadContext.runtime, "buttons")), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(276).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(277).call(threadContext, rubyObject, rubyObject), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol0(threadContext.runtime, "done")));
    }
    
    @JRubyMethod(name = "replace_done_button_by_next", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__48$RUBY$replace_done_button_by_next(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(278).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(279).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(280).call(threadContext, rubyObject, rubyObject), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol8(threadContext.runtime, "buttons")), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(281).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(282).call(threadContext, rubyObject, rubyObject), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol0(threadContext.runtime, "done")));
        return file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(283).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(284).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(285).call(threadContext, rubyObject, rubyObject), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol8(threadContext.runtime, "buttons")), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(286).call(threadContext, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(287).call(threadContext, rubyObject, rubyObject), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getSymbol1(threadContext.runtime, "next")));
    }
    
    @JRubyMethod(name = "replace_current_question_by_greetings", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__49$RUBY$replace_current_question_by_greetings(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext context, final IRubyObject caller, final Block block) {
        if (file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(288).call(context, caller, caller).isTrue()) {
            file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(289).call(context, caller, caller, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(290).call(context, caller, caller));
        }
        final Ruby runtime = context.runtime;
        final int i = 18;
        final String name = "@greetings";
        final IRubyObject call = file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(291).call(context, caller, caller);
        context.pollThreadEvents();
        final IRubyObject rubyObject = call;
        file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.setVariable(runtime, i, name, caller, RuntimeHelpers.invokeEqqForCaseWhen(file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(292), context, caller, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstant(context, "JLabel", 32)) ? file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(293).call(context, caller, caller) : (RuntimeHelpers.invokeEqqForCaseWhen(file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(294), context, caller, rubyObject, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstant(context, "JPanel", 33)) ? file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(295).call(context, caller, caller) : file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(296).call(context, caller, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstant(context, "JLabel", 34), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(297).call(context, caller, caller), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstantFrom(RuntimeHelpers.checkIsModule(file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstant(context, "JLabel", 35)), context, "CENTER", 36))));
        return file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(298).call(context, caller, caller, file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getCallSite(299).call(context, caller, caller), file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstantFrom(RuntimeHelpers.checkIsModule(file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.getConstant(context, "BorderLayout", 37)), context, "CENTER", 38));
    }
    
    public static IRubyObject class_2$RUBY$Questionnaire(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_2$RUBY$Questionnaire(file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__1$RUBY$Questionnaire(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Questionnaire(file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86, threadContext, rubyObject, block);
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
        final FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86 = new FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86();
        final String string = FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.class.getClassLoader().getResource("ruby/jit/FILE_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.class").toString();
        file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_CD8A0A65B5F22A0C6A1BEF647F658C7D89F98D86.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
