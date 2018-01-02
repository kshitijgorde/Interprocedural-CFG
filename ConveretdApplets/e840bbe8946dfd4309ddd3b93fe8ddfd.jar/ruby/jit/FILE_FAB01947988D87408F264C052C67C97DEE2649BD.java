// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.DynamicScope;
import org.jruby.ast.util.ArgsUtil;
import org.jruby.runtime.Arity;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_FAB01947988D87408F264C052C67C97DEE2649BD extends AbstractScript
{
    public FILE_FAB01947988D87408F264C052C67C97DEE2649BD() {
        this.filename = "./lib//lister/runner/questionnaire/question_table.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("get_column_index_at_x\uffffN\uffffcolumn_model\uffffV\uffffx\uffffN\uffffpoint\uffffN\uffff[]\uffffN\ufffffields\uffffN\uffffdata_model\uffffN\ufffftable_model\uffffN\ufffftable\uffffV\uffffhelp_text_for_field\uffffN\ufffftext_source\uffffN\ufffftable_model\uffffN\ufffftable\uffffV\uffffJTableHeader\uffffN\ufffftable\uffffN\uffffswing\uffffN\uffffjavax\uffffV\uffffinclude\uffffF\uffffnew\uffffN\uffffsetup_combo_boxes!\uffffN\ufffftable_header=\uffffN\ufffftable_header=\uffffV\uffffnew\uffffN\uffffcolumn_model\uffffN\uffffmodel\uffffV\uffffdata_model\uffffN\ufffftable_model\uffffV\uffffmap\uffffN\ufffffields\uffffN\uffffdata_model\uffffV\uffffname\uffffN\uffffselect\uffffN\ufffffields\uffffN\uffffdata_model\uffffV\uffffaccepted\uffffN\uffffeach\uffffN\uffffindex\uffffN\uffffname\uffffN\uffffget_column\uffffN\uffffcolumn_model\uffffV\uffffnew\uffffN\uffffJComboBox\uffffN\uffffswing\uffffN\uffffjavax\uffffV\uffffeach\uffffN\uffffaccepted\uffffN\uffffadd_item\uffffN\ufffftext_for_field_item\uffffN\ufffftext_source\uffffN\ufffftable_model\uffffV\uffffcell_editor=\uffffN\uffffcell_editor=\uffffV\uffffnew\uffffN\uffffDefaultCellEditor\uffffN\uffffswing\uffffN\uffffjavax\uffffV\uffffraise\uffffF\uffffname\uffffN\uffffJTable\uffffN\uffffswing\uffffN\uffffjavax\uffffV\uffff\u0002\u0000\u0000\u0000\u0005\u0000\u0000\u0000\u0000\u0004\u0000\u0000\u0001\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(0, "no such column ", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_FAB01947988D87408F264C052C67C97DEE2649BD file_FAB01947988D87408F264C052C67C97DEE2649BD, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        class_0$RUBY$QuestionTableHeader(file_FAB01947988D87408F264C052C67C97DEE2649BD, threadContext, file_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite(13).call(threadContext, rubyObject, file_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite(14).call(threadContext, rubyObject, file_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite(15).call(threadContext, rubyObject, file_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite(16).call(threadContext, rubyObject, rubyObject)))), Block.NULL_BLOCK);
        return class_2$RUBY$QuestionTable(file_FAB01947988D87408F264C052C67C97DEE2649BD, threadContext, file_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite(58).call(threadContext, rubyObject, file_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite(59).call(threadContext, rubyObject, file_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite(60).call(threadContext, rubyObject, rubyObject))), Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject class_0$RUBY$QuestionTableHeader(final FILE_FAB01947988D87408F264C052C67C97DEE2649BD p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    17: ldc             "QuestionTableHeader"
        //    19: swap           
        //    20: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    23: dup            
        //    24: astore_2       
        //    25: aload_1        
        //    26: swap           
        //    27: aload_0        
        //    28: aload_1        
        //    29: ldc             ",0,0,-1"
        //    31: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    34: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    37: aload_1        
        //    38: aload_2        
        //    39: aload_0        
        //    40: ldc             "getToolTipText"
        //    42: ldc             "method__1$RUBY$getToolTipText"
        //    44: ldc             "event;x;field,1,0,-1"
        //    46: iconst_1       
        //    47: ldc             "./lib//lister/runner/questionnaire/question_table.rb"
        //    49: ldc             2
        //    51: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
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
    
    @JRubyMethod(name = "getToolTipText", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__1$RUBY$getToolTipText(final FILE_FAB01947988D87408F264C052C67C97DEE2649BD p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    17: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite0:()Lorg/jruby/runtime/CallSite;
        //    20: aload_1        
        //    21: aload_2        
        //    22: aload_0        
        //    23: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite1:()Lorg/jruby/runtime/CallSite;
        //    26: aload_1        
        //    27: aload_2        
        //    28: aload_2        
        //    29: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    32: aload_0        
        //    33: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite2:()Lorg/jruby/runtime/CallSite;
        //    36: aload_1        
        //    37: aload_2        
        //    38: aload_0        
        //    39: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite3:()Lorg/jruby/runtime/CallSite;
        //    42: aload_1        
        //    43: aload_2        
        //    44: aload           locals
        //    46: aload_1        
        //    47: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    53: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    56: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    59: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    62: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    65: pop            
        //    66: aload           locals
        //    68: aload_0        
        //    69: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite4:()Lorg/jruby/runtime/CallSite;
        //    72: aload_1        
        //    73: aload_2        
        //    74: aload_0        
        //    75: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite5:()Lorg/jruby/runtime/CallSite;
        //    78: aload_1        
        //    79: aload_2        
        //    80: aload_0        
        //    81: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite6:()Lorg/jruby/runtime/CallSite;
        //    84: aload_1        
        //    85: aload_2        
        //    86: aload_0        
        //    87: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite7:()Lorg/jruby/runtime/CallSite;
        //    90: aload_1        
        //    91: aload_2        
        //    92: aload_0        
        //    93: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite8:()Lorg/jruby/runtime/CallSite;
        //    96: aload_1        
        //    97: aload_2        
        //    98: aload_2        
        //    99: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   102: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   105: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   108: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   111: aload           locals
        //   113: aload_1        
        //   114: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   117: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   120: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   123: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   126: pop            
        //   127: aload_0        
        //   128: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite9:()Lorg/jruby/runtime/CallSite;
        //   131: aload_1        
        //   132: aload_2        
        //   133: aload_0        
        //   134: bipush          10
        //   136: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   139: aload_1        
        //   140: aload_2        
        //   141: aload_0        
        //   142: bipush          11
        //   144: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   147: aload_1        
        //   148: aload_2        
        //   149: aload_0        
        //   150: bipush          12
        //   152: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   155: aload_1        
        //   156: aload_2        
        //   157: aload_2        
        //   158: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   161: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   164: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   167: aload           locals
        //   169: aload_1        
        //   170: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   173: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   176: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   179: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     166     5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject class_0$RUBY$QuestionTableHeader(final FILE_FAB01947988D87408F264C052C67C97DEE2649BD file_FAB01947988D87408F264C052C67C97DEE2649BD, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_0$RUBY$QuestionTableHeader(file_FAB01947988D87408F264C052C67C97DEE2649BD, threadContext, rubyObject, block);
    }
    
    public static IRubyObject class_2$RUBY$QuestionTable(final FILE_FAB01947988D87408F264C052C67C97DEE2649BD p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    17: ldc             "QuestionTable"
        //    19: swap           
        //    20: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    23: dup            
        //    24: astore_2       
        //    25: aload_1        
        //    26: swap           
        //    27: aload_0        
        //    28: aload_1        
        //    29: ldc             ",0,0,-1"
        //    31: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    34: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    37: aload_0        
        //    38: bipush          17
        //    40: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    43: aload_1        
        //    44: aload_2        
        //    45: aload_2        
        //    46: aload_0        
        //    47: aload_1        
        //    48: ldc             "Lister"
        //    50: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    53: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    56: aload_0        
        //    57: swap           
        //    58: aload_1        
        //    59: ldc             "Questionnaire"
        //    61: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getConstantFrom1:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    64: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    67: aload_0        
        //    68: swap           
        //    69: aload_1        
        //    70: ldc             "HasListeners"
        //    72: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getConstantFrom2:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    75: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    78: pop            
        //    79: aload_1        
        //    80: aload_2        
        //    81: aload_2        
        //    82: aload_0        
        //    83: ldc_w           "create"
        //    86: ldc_w           "method__3$RUBY$create"
        //    89: ldc_w           "args;obj,0,0,0"
        //    92: iconst_m1      
        //    93: ldc             "./lib//lister/runner/questionnaire/question_table.rb"
        //    95: ldc_w           11
        //    98: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   101: ldc_w           "rargs"
        //   104: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   107: pop            
        //   108: aload_1        
        //   109: aload_2        
        //   110: aload_0        
        //   111: ldc_w           "table_model"
        //   114: ldc_w           "method__4$RUBY$table_model"
        //   117: ldc             ",0,0,-1"
        //   119: iconst_0       
        //   120: ldc             "./lib//lister/runner/questionnaire/question_table.rb"
        //   122: ldc_w           19
        //   125: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   128: ldc_w           "NONE"
        //   131: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   134: pop            
        //   135: aload_1        
        //   136: aload_2        
        //   137: aload_0        
        //   138: ldc_w           "data_model"
        //   141: ldc_w           "method__5$RUBY$data_model"
        //   144: ldc             ",0,0,-1"
        //   146: iconst_0       
        //   147: ldc             "./lib//lister/runner/questionnaire/question_table.rb"
        //   149: ldc_w           23
        //   152: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   155: ldc_w           "NONE"
        //   158: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   161: pop            
        //   162: aload_1        
        //   163: aload_2        
        //   164: aload_0        
        //   165: ldc_w           "setup_combo_boxes!"
        //   168: ldc_w           "method__6$RUBY$setup_combo_boxes_b_"
        //   171: ldc_w           "all_fields_names;fields,0,0,-1"
        //   174: iconst_0       
        //   175: ldc             "./lib//lister/runner/questionnaire/question_table.rb"
        //   177: ldc_w           28
        //   180: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   183: ldc_w           "NONE"
        //   186: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   189: aload_1        
        //   190: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   193: goto            201
        //   196: aload_1        
        //   197: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   200: athrow         
        //   201: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  37     189    196    201    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "create", frame = true, required = 0, optional = 0, rest = 0)
    public static IRubyObject method__3$RUBY$create(final FILE_FAB01947988D87408F264C052C67C97DEE2649BD file_FAB01947988D87408F264C052C67C97DEE2649BD, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] input, final Block block) {
        final IRubyObject nil = threadContext.nil;
        final IRubyObject args = RuntimeHelpers.createSubarray(input, threadContext.runtime, 0);
        final IRubyObject obj = file_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite(18).call(threadContext, rubyObject, rubyObject, ArgsUtil.convertToJavaArray(RuntimeHelpers.splatValue(args)));
        file_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite(19).call(threadContext, rubyObject, obj);
        final IRubyObject rubyObject2 = obj;
        RuntimeHelpers.doAttrAsgn(rubyObject2, RuntimeHelpers.selectAttrAsgnCallSite(rubyObject2, rubyObject, file_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite(20), file_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite(21)), file_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite(22).call(threadContext, rubyObject, file_FAB01947988D87408F264C052C67C97DEE2649BD.getConstant3(threadContext, "QuestionTableHeader"), file_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite(23).call(threadContext, rubyObject, obj)), threadContext, rubyObject);
        return obj;
    }
    
    @JRubyMethod(name = "table_model", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$table_model(final FILE_FAB01947988D87408F264C052C67C97DEE2649BD file_FAB01947988D87408F264C052C67C97DEE2649BD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite(24).call(threadContext, rubyObject, rubyObject);
    }
    
    @JRubyMethod(name = "data_model", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__5$RUBY$data_model(final FILE_FAB01947988D87408F264C052C67C97DEE2649BD file_FAB01947988D87408F264C052C67C97DEE2649BD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite(25).call(threadContext, rubyObject, file_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite(26).call(threadContext, rubyObject, rubyObject));
    }
    
    @JRubyMethod(name = "setup_combo_boxes!", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__6$RUBY$setup_combo_boxes_b_(final FILE_FAB01947988D87408F264C052C67C97DEE2649BD file_FAB01947988D87408F264C052C67C97DEE2649BD, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(file_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite(27).callIter(threadContext, self, file_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite(28).call(threadContext, self, file_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite(29).call(threadContext, self, self)), RuntimeHelpers.createBlock(threadContext, self, file_FAB01947988D87408F264C052C67C97DEE2649BD.getBlockBody0(threadContext, "block_0$RUBY$setup_combo_boxes!,1,f,false,2,./lib//lister/runner/questionnaire/question_table.rb,29,true"))));
        locals.setValueOneDepthZero(file_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite(31).callIter(threadContext, self, file_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite(32).call(threadContext, self, file_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite(33).call(threadContext, self, self)), RuntimeHelpers.createBlock(threadContext, self, file_FAB01947988D87408F264C052C67C97DEE2649BD.getBlockBody1(threadContext, "block_1$RUBY$setup_combo_boxes!,1,field,false,2,./lib//lister/runner/questionnaire/question_table.rb,31,true"))));
        return file_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite(35).callIter(threadContext, self, locals.getValueOneDepthZeroOrNil(threadContext.nil), RuntimeHelpers.createBlock(threadContext, self, file_FAB01947988D87408F264C052C67C97DEE2649BD.getBlockBody3(threadContext, "block_2$RUBY$setup_combo_boxes!,1,field;idx;col;combo_box,false,2,./lib//lister/runner/questionnaire/question_table.rb,35,false")));
    }
    
    public static IRubyObject block_0$RUBY$setup_combo_boxes!(final FILE_FAB01947988D87408F264C052C67C97DEE2649BD p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          30
        //    28: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload           f
        //    35: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    38: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     14      9     f     Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_1$RUBY$setup_combo_boxes!(final FILE_FAB01947988D87408F264C052C67C97DEE2649BD p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          34
        //    28: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload           field
        //    35: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    38: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name   Signature
        //  -----  ------  ----  -----  ---------------------------------------
        //  25     14      9     field  Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_2$RUBY$setup_combo_boxes!(final FILE_FAB01947988D87408F264C052C67C97DEE2649BD p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    28: aload           5
        //    30: swap           
        //    31: invokevirtual   org/jruby/runtime/DynamicScope.setValueThreeDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    34: pop            
        //    35: aload_1        
        //    36: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    39: aload           4
        //    41: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    44: aload_3        
        //    45: aload           5
        //    47: swap           
        //    48: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    51: pop            
        //    52: pop            
        //    53: aload           locals
        //    55: aload_0        
        //    56: bipush          36
        //    58: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    61: aload_1        
        //    62: aload_2        
        //    63: aload           locals
        //    65: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    68: aload_1        
        //    69: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    72: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    75: aload_0        
        //    76: bipush          37
        //    78: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    81: aload_1        
        //    82: aload_2        
        //    83: aload           locals
        //    85: aload_1        
        //    86: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    89: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    92: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    95: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    98: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   101: pop            
        //   102: aload           locals
        //   104: aload_1        
        //   105: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   108: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   111: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   116: ifeq            341
        //   119: aload           locals
        //   121: aload_0        
        //   122: bipush          38
        //   124: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   127: aload_1        
        //   128: aload_2        
        //   129: aload_0        
        //   130: bipush          39
        //   132: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   135: aload_1        
        //   136: aload_2        
        //   137: aload_2        
        //   138: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   141: aload           locals
        //   143: aload_1        
        //   144: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   147: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   150: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   153: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   156: pop            
        //   157: aload           locals
        //   159: aload_0        
        //   160: bipush          40
        //   162: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   165: aload_1        
        //   166: aload_2        
        //   167: aload_0        
        //   168: bipush          41
        //   170: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   173: aload_1        
        //   174: aload_2        
        //   175: aload_0        
        //   176: bipush          42
        //   178: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   181: aload_1        
        //   182: aload_2        
        //   183: aload_0        
        //   184: bipush          43
        //   186: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   189: aload_1        
        //   190: aload_2        
        //   191: aload_2        
        //   192: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   195: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   198: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   201: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   204: invokevirtual   org/jruby/runtime/DynamicScope.setValueThreeDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   207: pop            
        //   208: aload_0        
        //   209: bipush          44
        //   211: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   214: aload_1        
        //   215: aload_2        
        //   216: aload_0        
        //   217: bipush          45
        //   219: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   222: aload_1        
        //   223: aload_2        
        //   224: aload           locals
        //   226: aload_1        
        //   227: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   230: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   233: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   236: aload_1        
        //   237: aload_2        
        //   238: aload_0        
        //   239: aload_1        
        //   240: ldc_w           "block_3$RUBY$setup_combo_boxes!,1,item,false,2,./lib//lister/runner/questionnaire/question_table.rb,40,true"
        //   243: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getBlockBody2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   246: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   249: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   252: pop            
        //   253: aload           locals
        //   255: aload_1        
        //   256: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   259: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   262: dup            
        //   263: aload_2        
        //   264: aload_0        
        //   265: bipush          50
        //   267: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   270: aload_0        
        //   271: bipush          51
        //   273: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   276: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   279: aload_0        
        //   280: bipush          52
        //   282: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   285: aload_1        
        //   286: aload_2        
        //   287: aload_0        
        //   288: bipush          53
        //   290: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   293: aload_1        
        //   294: aload_2        
        //   295: aload_0        
        //   296: bipush          54
        //   298: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   301: aload_1        
        //   302: aload_2        
        //   303: aload_0        
        //   304: bipush          55
        //   306: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   309: aload_1        
        //   310: aload_2        
        //   311: aload_2        
        //   312: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   315: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   318: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   321: aload           locals
        //   323: aload_1        
        //   324: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   327: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   330: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   333: aload_1        
        //   334: aload_2        
        //   335: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   338: goto            412
        //   341: aload_0        
        //   342: bipush          56
        //   344: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   347: aload_1        
        //   348: aload_2        
        //   349: aload_2        
        //   350: aload_0        
        //   351: aload_1        
        //   352: ldc_w           "RuntimeError"
        //   355: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getConstant4:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   358: aload_1        
        //   359: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   362: ldc_w           20
        //   365: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   368: aload_0        
        //   369: aload_1        
        //   370: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   373: bipush          32
        //   375: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getString0:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   378: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   381: aload_0        
        //   382: bipush          57
        //   384: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   387: aload_1        
        //   388: aload_2        
        //   389: aload           locals
        //   391: aload_1        
        //   392: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   395: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   398: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   401: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   406: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   409: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   412: areturn        
        //   413: pop            
        //   414: goto            53
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  53     360     5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  53     413    413    417    Lorg/jruby/exceptions/JumpException$RedoJump;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_3$RUBY$setup_combo_boxes!(final FILE_FAB01947988D87408F264C052C67C97DEE2649BD p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          46
        //    28: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload           5
        //    35: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    38: aload_1        
        //    39: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    42: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: aload_0        
        //    46: bipush          47
        //    48: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    51: aload_1        
        //    52: aload_2        
        //    53: aload_0        
        //    54: bipush          48
        //    56: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    59: aload_1        
        //    60: aload_2        
        //    61: aload_0        
        //    62: bipush          49
        //    64: invokevirtual   ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    67: aload_1        
        //    68: aload_2        
        //    69: aload_2        
        //    70: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    73: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    76: aload           5
        //    78: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    81: aload_1        
        //    82: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    85: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    88: aload           item
        //    90: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    93: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    96: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     72      9     item  Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject class_2$RUBY$QuestionTable(final FILE_FAB01947988D87408F264C052C67C97DEE2649BD file_FAB01947988D87408F264C052C67C97DEE2649BD, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_2$RUBY$QuestionTable(file_FAB01947988D87408F264C052C67C97DEE2649BD, threadContext, rubyObject, block);
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
        final FILE_FAB01947988D87408F264C052C67C97DEE2649BD file_FAB01947988D87408F264C052C67C97DEE2649BD = new FILE_FAB01947988D87408F264C052C67C97DEE2649BD();
        final String string = FILE_FAB01947988D87408F264C052C67C97DEE2649BD.class.getClassLoader().getResource("ruby/jit/FILE_FAB01947988D87408F264C052C67C97DEE2649BD.class").toString();
        file_FAB01947988D87408F264C052C67C97DEE2649BD.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_FAB01947988D87408F264C052C67C97DEE2649BD.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
