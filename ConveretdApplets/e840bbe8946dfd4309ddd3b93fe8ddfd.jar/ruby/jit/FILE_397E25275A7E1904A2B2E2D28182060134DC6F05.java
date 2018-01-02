// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.Arity;
import org.jruby.RubyFixnum;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_397E25275A7E1904A2B2E2D28182060134DC6F05 extends AbstractScript
{
    public FILE_397E25275A7E1904A2B2E2D28182060134DC6F05() {
        this.filename = "./lib//lister/runner/questionnaire/freetext_question.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("include_class\uffffF\uffffinclude_class\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffinclude\uffffF\ufffflistener\uffffF\uffffupdate_text\uffffN\uffffsource\uffffV\ufffftext\uffffN\ufffftextarea\uffffN\uffffsource\uffffV\uffffattr_reader\uffffF\uffffsuper\uffffS\uffffprepare_textarea\uffffV\uffffplace_textarea\uffffV\uffffsuper\uffffS\uffffnew\uffffN\uffffadd_caret_listener\uffffN\ufffftextarea\uffffV\ufffflistener\uffffF\uffffadd\uffffN\uffffpanel\uffffV\uffffnew\uffffN\ufffftextarea\uffffV\uffffresult\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffff\u0003\u0001\u0001\u0000\u0004\u0000\u0000\u0000\u0001\u0001\u0000\u0000\u0005\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(3, "lister/runner/questionnaire/question", this.getEncoding0());
        this.setByteList(0, "javax.swing.JTextArea", this.getEncoding0());
        this.setByteList(2, "lister/runner/questionnaire/has_listener", this.getEncoding0());
        this.setByteList(4, "free_text", this.getEncoding0());
        this.setByteList(1, "javax.swing.JScrollPane", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_397E25275A7E1904A2B2E2D28182060134DC6F05 file_397E25275A7E1904A2B2E2D28182060134DC6F05, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_397E25275A7E1904A2B2E2D28182060134DC6F05.getCallSite0().call(threadContext, rubyObject, rubyObject, file_397E25275A7E1904A2B2E2D28182060134DC6F05.getString0(threadContext.runtime, 32));
        file_397E25275A7E1904A2B2E2D28182060134DC6F05.getCallSite1().call(threadContext, rubyObject, rubyObject, file_397E25275A7E1904A2B2E2D28182060134DC6F05.getString1(threadContext.runtime, 32));
        file_397E25275A7E1904A2B2E2D28182060134DC6F05.getCallSite2().call(threadContext, rubyObject, rubyObject, file_397E25275A7E1904A2B2E2D28182060134DC6F05.getString2(threadContext.runtime, 32));
        file_397E25275A7E1904A2B2E2D28182060134DC6F05.getCallSite3().call(threadContext, rubyObject, rubyObject, file_397E25275A7E1904A2B2E2D28182060134DC6F05.getString3(threadContext.runtime, 32));
        return module__0$RUBY$Lister(file_397E25275A7E1904A2B2E2D28182060134DC6F05, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_397E25275A7E1904A2B2E2D28182060134DC6F05 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_397E25275A7E1904A2B2E2D28182060134DC6F05.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_397E25275A7E1904A2B2E2D28182060134DC6F05.module__1$RUBY$Questionnaire:(Lruby/jit/FILE_397E25275A7E1904A2B2E2D28182060134DC6F05;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$Questionnaire(final FILE_397E25275A7E1904A2B2E2D28182060134DC6F05 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_397E25275A7E1904A2B2E2D28182060134DC6F05.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_0        
        //    30: aload_1        
        //    31: ldc_w           "Question"
        //    34: invokevirtual   ruby/jit/FILE_397E25275A7E1904A2B2E2D28182060134DC6F05.getConstant3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    37: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    40: invokestatic    ruby/jit/FILE_397E25275A7E1904A2B2E2D28182060134DC6F05.class_2$RUBY$FreeTextQuestion:(Lruby/jit/FILE_397E25275A7E1904A2B2E2D28182060134DC6F05;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject class_2$RUBY$FreeTextQuestion(final FILE_397E25275A7E1904A2B2E2D28182060134DC6F05 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    29: ldc             "FreeTextQuestion"
        //    31: swap           
        //    32: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    35: dup            
        //    36: astore_2       
        //    37: aload_1        
        //    38: swap           
        //    39: aload_0        
        //    40: aload_1        
        //    41: ldc             ",0,0,-1"
        //    43: invokevirtual   ruby/jit/FILE_397E25275A7E1904A2B2E2D28182060134DC6F05.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    46: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClass:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    49: aload_1        
        //    50: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //    53: astore          locals
        //    55: aload_0        
        //    56: invokevirtual   ruby/jit/FILE_397E25275A7E1904A2B2E2D28182060134DC6F05.getCallSite4:()Lorg/jruby/runtime/CallSite;
        //    59: aload_1        
        //    60: aload_2        
        //    61: aload_2        
        //    62: aload_0        
        //    63: aload_1        
        //    64: ldc             "HasListeners"
        //    66: invokevirtual   ruby/jit/FILE_397E25275A7E1904A2B2E2D28182060134DC6F05.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    69: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    72: pop            
        //    73: aload_0        
        //    74: invokevirtual   ruby/jit/FILE_397E25275A7E1904A2B2E2D28182060134DC6F05.getCallSite5:()Lorg/jruby/runtime/CallSite;
        //    77: aload_1        
        //    78: aload_2        
        //    79: aload_2        
        //    80: aload_0        
        //    81: aload_1        
        //    82: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    85: ldc             "textarea"
        //    87: invokevirtual   ruby/jit/FILE_397E25275A7E1904A2B2E2D28182060134DC6F05.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    90: aload_1        
        //    91: aload_2        
        //    92: aload_0        
        //    93: aload_1        
        //    94: ldc             "block_0$RUBY$FreeTextQuestion,-1,,false,0,./lib//lister/runner/questionnaire/freetext_question.rb,12,true"
        //    96: invokevirtual   ruby/jit/FILE_397E25275A7E1904A2B2E2D28182060134DC6F05.getBlockBody0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    99: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   102: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   105: pop            
        //   106: aload_0        
        //   107: bipush          11
        //   109: invokevirtual   ruby/jit/FILE_397E25275A7E1904A2B2E2D28182060134DC6F05.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   112: aload_1        
        //   113: aload_2        
        //   114: aload_2        
        //   115: aload_0        
        //   116: aload_1        
        //   117: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   120: ldc             "textarea"
        //   122: invokevirtual   ruby/jit/FILE_397E25275A7E1904A2B2E2D28182060134DC6F05.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   125: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   128: pop            
        //   129: aload_1        
        //   130: aload_2        
        //   131: aload_0        
        //   132: ldc             "prepare"
        //   134: ldc             "method__4$RUBY$prepare"
        //   136: ldc             ",0,0,-1"
        //   138: iconst_0       
        //   139: ldc             "./lib//lister/runner/questionnaire/freetext_question.rb"
        //   141: ldc             20
        //   143: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   146: ldc             "NONE"
        //   148: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   151: pop            
        //   152: aload_1        
        //   153: aload_2        
        //   154: aload_0        
        //   155: ldc             "place"
        //   157: ldc             "method__5$RUBY$place"
        //   159: ldc             ",0,0,-1"
        //   161: iconst_0       
        //   162: ldc             "./lib//lister/runner/questionnaire/freetext_question.rb"
        //   164: ldc             25
        //   166: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   169: ldc             "NONE"
        //   171: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   174: pop            
        //   175: aload_1        
        //   176: aload_2        
        //   177: aload_0        
        //   178: ldc_w           "prepare_textarea"
        //   181: ldc_w           "method__6$RUBY$prepare_textarea"
        //   184: ldc             ",0,0,-1"
        //   186: iconst_0       
        //   187: ldc             "./lib//lister/runner/questionnaire/freetext_question.rb"
        //   189: ldc_w           30
        //   192: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   195: ldc             "NONE"
        //   197: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   200: pop            
        //   201: aload_1        
        //   202: aload_2        
        //   203: aload_0        
        //   204: ldc_w           "place_textarea"
        //   207: ldc_w           "method__7$RUBY$place_textarea"
        //   210: ldc             ",0,0,-1"
        //   212: iconst_0       
        //   213: ldc             "./lib//lister/runner/questionnaire/freetext_question.rb"
        //   215: ldc_w           35
        //   218: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   221: ldc             "NONE"
        //   223: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   226: pop            
        //   227: aload_1        
        //   228: aload_2        
        //   229: aload_0        
        //   230: ldc_w           "update_text"
        //   233: ldc_w           "method__8$RUBY$update_text"
        //   236: ldc_w           "val,1,0,-1"
        //   239: iconst_1       
        //   240: ldc             "./lib//lister/runner/questionnaire/freetext_question.rb"
        //   242: ldc_w           39
        //   245: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   248: ldc_w           "qval"
        //   251: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   254: pop            
        //   255: aload_1        
        //   256: aload_2        
        //   257: aload_0        
        //   258: ldc_w           "minimum_number_of_lines"
        //   261: ldc_w           "method__9$RUBY$minimum_number_of_lines"
        //   264: ldc             ",0,0,-1"
        //   266: iconst_0       
        //   267: ldc             "./lib//lister/runner/questionnaire/freetext_question.rb"
        //   269: ldc_w           43
        //   272: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   275: ldc             "NONE"
        //   277: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   280: aload_1        
        //   281: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   284: goto            292
        //   287: aload_1        
        //   288: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   291: athrow         
        //   292: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  55     238     4     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  55     280    287    292    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_0$RUBY$FreeTextQuestion(final FILE_397E25275A7E1904A2B2E2D28182060134DC6F05 scriptObject, final ThreadContext context, final IRubyObject self, final IRubyObject rubyObject, final Block block) {
        context.getCurrentScope();
        RuntimeHelpers.processBlockArgument(context.runtime, block);
        return RuntimeHelpers.def(context, self, scriptObject, "caretUpdate", "method__3$RUBY$caretUpdate", "event,1,0,-1", 1, "./lib//lister/runner/questionnaire/freetext_question.rb", 13, CallConfiguration.FrameNoneScopeNone, "qevent");
    }
    
    @JRubyMethod(name = "caretUpdate", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__3$RUBY$caretUpdate(final FILE_397E25275A7E1904A2B2E2D28182060134DC6F05 file_397E25275A7E1904A2B2E2D28182060134DC6F05, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_397E25275A7E1904A2B2E2D28182060134DC6F05.getCallSite6().call(threadContext, rubyObject, file_397E25275A7E1904A2B2E2D28182060134DC6F05.getCallSite7().call(threadContext, rubyObject, rubyObject), file_397E25275A7E1904A2B2E2D28182060134DC6F05.getCallSite8().call(threadContext, rubyObject, file_397E25275A7E1904A2B2E2D28182060134DC6F05.getCallSite9().call(threadContext, rubyObject, file_397E25275A7E1904A2B2E2D28182060134DC6F05.getCallSite(10).call(threadContext, rubyObject, rubyObject))));
    }
    
    @JRubyMethod(name = "prepare", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$prepare(final FILE_397E25275A7E1904A2B2E2D28182060134DC6F05 file_397E25275A7E1904A2B2E2D28182060134DC6F05, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        file_397E25275A7E1904A2B2E2D28182060134DC6F05.getCallSite(12).call(context, rubyObject, rubyObject, RuntimeHelpers.getArgValues(context), block);
        return file_397E25275A7E1904A2B2E2D28182060134DC6F05.getCallSite(13).call(context, rubyObject, rubyObject);
    }
    
    @JRubyMethod(name = "place", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__5$RUBY$place(final FILE_397E25275A7E1904A2B2E2D28182060134DC6F05 file_397E25275A7E1904A2B2E2D28182060134DC6F05, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        file_397E25275A7E1904A2B2E2D28182060134DC6F05.getCallSite(14).call(context, rubyObject, rubyObject);
        return file_397E25275A7E1904A2B2E2D28182060134DC6F05.getCallSite(15).call(context, rubyObject, rubyObject, RuntimeHelpers.getArgValues(context), block);
    }
    
    @JRubyMethod(name = "prepare_textarea", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__6$RUBY$prepare_textarea(final FILE_397E25275A7E1904A2B2E2D28182060134DC6F05 file_397E25275A7E1904A2B2E2D28182060134DC6F05, final ThreadContext context, final IRubyObject object, final Block block) {
        file_397E25275A7E1904A2B2E2D28182060134DC6F05.setVariable0(context.runtime, "@textarea", object, file_397E25275A7E1904A2B2E2D28182060134DC6F05.getCallSite(16).call(context, object, file_397E25275A7E1904A2B2E2D28182060134DC6F05.getConstant1(context, "JTextArea"), file_397E25275A7E1904A2B2E2D28182060134DC6F05.getFixnum0(context.runtime, 20), file_397E25275A7E1904A2B2E2D28182060134DC6F05.getFixnum0(context.runtime, 20)));
        return file_397E25275A7E1904A2B2E2D28182060134DC6F05.getCallSite(17).call(context, object, file_397E25275A7E1904A2B2E2D28182060134DC6F05.getCallSite(18).call(context, object, object), file_397E25275A7E1904A2B2E2D28182060134DC6F05.getCallSite(19).call(context, object, object, file_397E25275A7E1904A2B2E2D28182060134DC6F05.getSymbol0(context.runtime, "textarea")));
    }
    
    @JRubyMethod(name = "place_textarea", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__7$RUBY$place_textarea(final FILE_397E25275A7E1904A2B2E2D28182060134DC6F05 file_397E25275A7E1904A2B2E2D28182060134DC6F05, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        return file_397E25275A7E1904A2B2E2D28182060134DC6F05.getCallSite(20).call(context, rubyObject, file_397E25275A7E1904A2B2E2D28182060134DC6F05.getCallSite(21).call(context, rubyObject, rubyObject), file_397E25275A7E1904A2B2E2D28182060134DC6F05.getCallSite(22).call(context, rubyObject, file_397E25275A7E1904A2B2E2D28182060134DC6F05.getConstant2(context, "JScrollPane"), file_397E25275A7E1904A2B2E2D28182060134DC6F05.getCallSite(23).call(context, rubyObject, rubyObject)));
    }
    
    @JRubyMethod(name = "update_text", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__8$RUBY$update_text(final FILE_397E25275A7E1904A2B2E2D28182060134DC6F05 file_397E25275A7E1904A2B2E2D28182060134DC6F05, final ThreadContext context, final IRubyObject rubyObject, final IRubyObject value, final Block block) {
        final IRubyObject call = file_397E25275A7E1904A2B2E2D28182060134DC6F05.getCallSite(24).call(context, rubyObject, rubyObject);
        return RuntimeHelpers.doAttrAsgn(call, RuntimeHelpers.selectAttrAsgnCallSite(call, rubyObject, file_397E25275A7E1904A2B2E2D28182060134DC6F05.getCallSite(25), file_397E25275A7E1904A2B2E2D28182060134DC6F05.getCallSite(26)), file_397E25275A7E1904A2B2E2D28182060134DC6F05.getString4(context.runtime, 32), value, context, rubyObject);
    }
    
    @JRubyMethod(name = "minimum_number_of_lines", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__9$RUBY$minimum_number_of_lines(final FILE_397E25275A7E1904A2B2E2D28182060134DC6F05 file_397E25275A7E1904A2B2E2D28182060134DC6F05, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return RubyFixnum.one(threadContext.runtime);
    }
    
    public static IRubyObject class_2$RUBY$FreeTextQuestion(final FILE_397E25275A7E1904A2B2E2D28182060134DC6F05 file_397E25275A7E1904A2B2E2D28182060134DC6F05, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_2$RUBY$FreeTextQuestion(file_397E25275A7E1904A2B2E2D28182060134DC6F05, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__1$RUBY$Questionnaire(final FILE_397E25275A7E1904A2B2E2D28182060134DC6F05 file_397E25275A7E1904A2B2E2D28182060134DC6F05, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Questionnaire(file_397E25275A7E1904A2B2E2D28182060134DC6F05, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_397E25275A7E1904A2B2E2D28182060134DC6F05 file_397E25275A7E1904A2B2E2D28182060134DC6F05, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_397E25275A7E1904A2B2E2D28182060134DC6F05, threadContext, rubyObject, block);
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
        final FILE_397E25275A7E1904A2B2E2D28182060134DC6F05 file_397E25275A7E1904A2B2E2D28182060134DC6F05 = new FILE_397E25275A7E1904A2B2E2D28182060134DC6F05();
        final String string = FILE_397E25275A7E1904A2B2E2D28182060134DC6F05.class.getClassLoader().getResource("ruby/jit/FILE_397E25275A7E1904A2B2E2D28182060134DC6F05.class").toString();
        file_397E25275A7E1904A2B2E2D28182060134DC6F05.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_397E25275A7E1904A2B2E2D28182060134DC6F05.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
